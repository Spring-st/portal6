package com.aof.web.struts.action.admin;

import com.aof.model.admin.GlobalParameter;
import com.aof.model.admin.Menu;
import com.aof.model.admin.User;
import com.aof.model.metadata.EnabledDisabled;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.GlobalManager;
import com.aof.service.admin.SiteManager;
import com.aof.service.admin.UserManager;
import com.aof.utils.IpAddress;
import com.aof.web.domain.SessionList;
import com.aof.web.struts.action.BaseAction;
import com.aof.web.struts.action.ServiceLocator;
import com.aof.web.struts.form.admin.LoginForm;
import com.shcnc.struts.action.BackToInputActionException;
import com.shcnc.utils.MD5;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BasicAction extends BaseAction {
    private static final String DEFAULT_LOCALE = "en";
    public static final String CHANGE_PASSWORD_USER_KEY = "CHANGE_PASSWORD_USER";
    public static final String LOGIN_USER_KEY = "LOGIN_USER";
    public static final String LOGIN_TIME_KEY = "LOGIN_TIME";
    public static final String LOGIN_IP_KEY = "LOGIN_IP";

    public ActionForward welcome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("LOGIN_USER") == null) {
            return mapping.findForward("login");
        }
        return mapping.findForward("mainFrame");
    }

    private int getAccountLockTimes(HttpServletRequest request) {
        GlobalManager gm = ServiceLocator.getGlobalManager(request);
        Integer times = gm.getParameter().getAccountLock();
        if (times == null) return 3;
        return times.intValue();
    }

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("POST".equals(request.getMethod())) {
            UserManager um = ServiceLocator.getUserManager(request);
            LoginForm loginForm = (LoginForm)form;
            User u = um.getUserByLoginName(loginForm.getLoginName());
            if (u != null) {
                if (u.getLoginFailedCount() == getAccountLockTimes(request)) throw new BackToInputActionException("login.failedTimesReach");
                if (u.getPassword().equalsIgnoreCase(MD5.getDigestString(loginForm.getPassword()))) {
                    if (!EnabledDisabled.ENABLED.equals(u.getEnabled())) throw new BackToInputActionException("login.userDisabled");
                    HttpSession session = request.getSession();
                    if (u.getLastLoginTime() == null) {

                        session.setAttribute("CHANGE_PASSWORD_USER", u);
                        request.setAttribute("x_changePasswordUser", u);
                        postGlobalMessage("login.alertChangePassword", null);
                        GlobalManager gm = ServiceLocator.getGlobalManager(request);
                        GlobalParameter gp = gm.getParameter();
                        request.setAttribute("x_minPwdLen", gp.getMinPasswordLength());
                        return mapping.findForward("changePwd");
                    }

                    session.setAttribute("LOGIN_USER", u);
                    session.setAttribute("LOGIN_TIME", new Date());
                    session.setAttribute("LOGIN_IP", new IpAddress(request.getRemoteAddr()));
                    SessionList.addSession(session);

                    u.setLastLoginTime(new Date());
                    u.setLoginFailedCount(0);
                    String locale = loginForm.getLocale();
                    if ("".equals(locale)) {
                        locale = u.getLocale();
                    } else {
                        u.setLocale(locale);
                    }
                    if (locale == null || locale.trim().length() == 0) {
                        locale = "en";
                    }
//                    setLocale(request, new Locale(locale));
                    um.saveUser(u);
                    return mapping.findForward("success");
                }
                u.setLoginFailedCount(u.getLoginFailedCount() + 1);
                um.saveUser(u);
            }
            throw new BackToInputActionException("login.failed");
        }
        return mapping.findForward("page");
    }

    public ActionForward changePasswordFirstTime(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("POST".equals(request.getMethod())) {
            GlobalManager gm = ServiceLocator.getGlobalManager(request);
            GlobalParameter gp = gm.getParameter();
            request.setAttribute("x_minPwdLen", gp.getMinPasswordLength());
            UserManager um = ServiceLocator.getUserManager(request);
            LoginForm loginForm = (LoginForm)form;
            User u = um.getUserByLoginName(loginForm.getLoginName());
            if (u != null) {
                if (u.getLoginFailedCount() == getAccountLockTimes(request)) throw new BackToInputActionException("login.failedTimesReach");
                if (u.getPassword().equalsIgnoreCase(MD5.getDigestString(loginForm.getPassword()))) {
                    if (!EnabledDisabled.ENABLED.equals(u.getEnabled())) throw new BackToInputActionException("login.userDisabled");
                    if (!request.getParameter("newPwd").equals(request.getParameter("passwordAgain")))
                        throw new BackToInputActionException("user.password.notMatch");
                    u.setPassword(MD5.getDigestString(request.getParameter("newPwd")));
                    HttpSession session = request.getSession();

                    session.setAttribute("LOGIN_USER", u);
                    session.setAttribute("LOGIN_TIME", new Date());
                    session.setAttribute("LOGIN_IP", new IpAddress(request.getRemoteAddr()));
                    SessionList.addSession(session);

                    u.setLastLoginTime(new Date());
                    u.setLoginFailedCount(0);
                    u.setLocale("en");
//                    setLocale(request, new Locale("en"));
                    um.saveUser(u);
                    return mapping.findForward("success");
                }
                u.setLoginFailedCount(u.getLoginFailedCount() + 1);
                um.saveUser(u);
            }
            throw new BackToInputActionException("login.failed");
        }
        return mapping.findForward("page");
    }


    public ActionForward forgetPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("POST".equals(request.getMethod())) {
            String userName = request.getParameter("loginName");
            if (userName != null) {
                UserManager um = ServiceLocator.getUserManager(request);
                User u = um.getUserByLoginName(userName);
                if (u != null) {
                    String newPassword = getNewPassword(request);
                    u.setPassword(MD5.getDigestString(newPassword));
                    um.saveUser(u);
                    EmailManager em = ServiceLocator.getEmailManager(request);
                    Map<Object, Object> context = new HashMap<Object, Object>();
                    context.put("user", u);
                    context.put("newPassword", newPassword);
                    em.insertEmail(u.getPrimarySite(), u.getEmail(), "ChangePassword.vm", context);
                }
            }
            request.setAttribute("success", "1");
        }
        return mapping.findForward("page");
    }

    private String getNewPassword(HttpServletRequest request) {
        GlobalManager gm = ServiceLocator.getGlobalManager(request);
        GlobalParameter gp = gm.getParameter();
        int length = gp.getMinPasswordLength().intValue();
        String newPassword = String.valueOf(getRandomLetter(1)) + getRandomLetter(0);
        for (int index = 2; index < length; index++) {
            newPassword = String.valueOf(newPassword) + getRandomLetter(-1);
        }
        return newPassword;
    }

    private String getRandomLetter(int type) {
        StringBuffer retVal = new StringBuffer();
        Random random = new Random();
        if (type == -1)
            type = random.nextInt(3);
        switch (type) {
            case 0:
                retVal.append(random.nextInt(9));
                break;
            case 1:
                retVal.append((char)(65 + random.nextInt(25)));
                break;
            case 2:
                retVal.append((char)(97 + random.nextInt(25)));
                break;
        }
        return retVal.toString();
    }










    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("LOGIN_USER");
        SessionList.removeSession(session);
        return mapping.findForward("success");
    }










    public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean siteHasLogo = false;
        User currentUser = getCurrentUser(request);
        request.setAttribute("X_LOGINED", Boolean.valueOf((currentUser != null)));
        if (currentUser != null) {
            SiteManager sm = ServiceLocator.getSiteManager(request);
            Integer siteId = currentUser.getPrimarySite().getId();
            request.setAttribute("X_SITEID", siteId);
            siteHasLogo = sm.isSiteHasLogo(siteId);
        }
        request.setAttribute("X_SITEHASLOGO", Boolean.valueOf(siteHasLogo));
        return mapping.findForward("page");
    }










    public ActionForward menu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> resultList = new ArrayList();
        UserManager userManager = ServiceLocator.getUserManager(request);
        User user = getCurrentUser(request);
        if (user != null) {
            Set menu = userManager.getGrantedMenuSet(user);
            for (Iterator<Menu> itor = menu.iterator(); itor.hasNext(); ) {
                Menu m = itor.next();
                StringBuffer buf = new StringBuffer();
//                buildMenuScript(m, buf, getLocale(request));
                resultList.add(buf.toString());
            }
            request.setAttribute("X_MENULIST", resultList);
            return mapping.findForward("page");
        }
        return mapping.findForward("login");
    }



    private void buildMenuScript(Menu m, StringBuffer buf, Locale locale) {
        buf.append("['").append(Locale.ENGLISH.equals(locale) ? m.getName() : m.getSecondName()).append("',");
        Set children = m.getChildren();
        String url = m.getUrl();
        if (url == null || (children != null && children.isEmpty())) {
            buf.append(" 0");
            if (children != null && !children.isEmpty()) {
                buf.append(',');
                for (Iterator<Menu> itor = children.iterator(); itor.hasNext();) {
                    buildMenuScript(itor.next(), buf, locale);
                }
            }
            buf.append("],");
        } else {
            buf.append('\'').append(url).append(".do'],");
        }
    }
}