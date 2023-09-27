package com.aof.web.struts.action.admin;

import com.aof.model.admin.Department;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.UserDepartment;
import com.aof.model.admin.UserSite;
import com.aof.service.admin.DepartmentManager;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.SiteManager;
import com.aof.service.admin.UserManager;
import com.aof.web.struts.action.BaseAction2;
import com.aof.web.struts.action.ServiceLocator;
import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.action.BackToInputActionException;
import com.shcnc.struts.form.BeanForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class UserSiteDepartmentAction
        extends BaseAction2
{
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);

        Integer id = ActionUtils.parseInt(request.getParameter("userId"));
        User u = um.getUser(id);
        if (u == null) throw new ActionException("user.notFound", id);

        request.setAttribute("X_USER", u);

        List userSiteList = um.getUserSiteListByUser(u);
        List userDepartmentList = null;
        if (!hasGlobalPower(request)) {
            userDepartmentList = new ArrayList();
            request.setAttribute("X_GLOBAL", Boolean.FALSE);

            List grantedSiteList = getGrantedSiteList(request);
            for (Iterator<UserSite> itor = userSiteList.iterator(); itor.hasNext(); ) {
                UserSite us = itor.next();
                Site s = us.getSite();
                if (!grantedSiteList.contains(s)) {
                    itor.remove(); continue;
                }
                userDepartmentList.addAll(um.getUserDepartmentListByUserAndSite(u, s));
            }
        } else {

            request.setAttribute("X_GLOBAL", Boolean.TRUE);
            userDepartmentList = um.getUserDepartmentListByUser(u);
        }

        request.setAttribute("X_SITELIST", userSiteList);
        request.setAttribute("X_DEPARTMENTLIST", userDepartmentList);
        return mapping.findForward("page");
    }

    public ActionForward newSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!isBack(request)) {
            UserManager um = ServiceLocator.getUserManager(request);

            Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
            User u = um.getUser(id);
            if (u == null) throw new ActionException("user.notFound", id);

            UserSite us = new UserSite();
            us.setUser(u);
            BeanForm userSiteForm = (BeanForm)getForm("/insertUserSite", request);
            userSiteForm.populateToForm(us);
        }

        List siteList = getAndCheckGrantedSiteList(request);

//        for (Iterator<Site> itor = siteList.iterator(); itor.hasNext();) {
//            site = itor.next();
//        }
        request.setAttribute("X_SITELIST", siteList);
        return mapping.findForward("page");
    }

    public ActionForward insertSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);
        System.out.println("11");
        Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
        User u = um.getUser(id);
        if (u == null) throw new ActionException("user.notFound", id);

        BeanForm userSiteForm = (BeanForm)form;
        UserSite us = new UserSite();
        userSiteForm.populateToBean(us);

        Site s = us.getSite();
        if (s == null) {
            throw new BackToInputActionException("site.notFound", (Object[]) null);
        }
        SiteManager sm = ServiceLocator.getSiteManager(request);
        s = sm.getSite(s.getId());
        if (s == null) {
            throw new BackToInputActionException("site.notFound", s.getId());
        }
        checkSite(s, request);

        if (um.getUserSite(us.getUser().getId(), s.getId()) != null) {
            throw new BackToInputActionException("userSite.duplicate");
        }
        request.setAttribute("X_OBJECT", um.saveUserSite(us));
        request.setAttribute("X_ROWPAGE", "userSiteDepartment/siteRow.jsp");

        EmailManager em = ServiceLocator.getEmailManager(request);
        FunctionManager fm = ServiceLocator.getFunctionManager(request);




        List userList = new ArrayList();






        for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
            User user = itor.next();
            if (user.getEmail() != null) {
                Map<Object, Object> context = new HashMap<Object, Object>();
                context.put("user", u);
                context.put("finance", user);
                em.insertEmail(s, user.getEmail(), "NewUserSite.vm", context);
            }
        }
        return mapping.findForward("success");
    }










    public ActionForward editSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);
        Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
        Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
        UserSite us = um.getUserSite(userId, siteId);
        if (us == null) throw new ActionException("userSite.notFound", userId, siteId);


        checkSite(us.getSite(), request);

        BeanForm userSiteForm = (BeanForm)getForm("/updateUserSite", request);
        if (!isBack(request)) {
            userSiteForm.populateToForm(us);
        }




        return mapping.findForward("page");
    }










    public ActionForward updateSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);

        Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
        Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
        UserSite us = um.getUserSite(userId, siteId);
        if (us == null) throw new ActionException("userSite.notFound", userId, siteId);

        checkSite(us.getSite(), request);

        BeanForm userSiteForm = (BeanForm)form;
        userSiteForm.populateToBean(us);
        request.setAttribute("X_OBJECT", um.updateUserSite(us));
        request.setAttribute("X_ROWPAGE", "userSiteDepartment/siteRow.jsp");

        return mapping.findForward("success");
    }










    public ActionForward deleteSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);
        Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
        Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
        UserSite us = um.getUserSite(userId, siteId);
        if (us == null) throw new ActionException("userSite.notFound", userId, siteId);


        checkSite(us.getSite(), request);

        um.removeUserSite(us);

        return mapping.findForward("success");
    }










    public ActionForward newDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);
        Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
        User u = um.getUser(id);
        if (u == null) throw new ActionException("user.notFound", id);

        if (!isBack(request)) {

            UserDepartment ud = new UserDepartment();
            ud.setUser(u);
            BeanForm userDepartmentForm = (BeanForm)getForm("/insertUserDepartment", request);
            userDepartmentForm.populateToForm(ud);
        }

        List<Site> siteList = new ArrayList();
        List userSiteList = um.getUserSiteListByUser(u);

        List grantedSiteList = getGrantedSiteList(request);
        for (Iterator<UserSite> itor = userSiteList.iterator(); itor.hasNext(); ) {
            UserSite us = itor.next();
            Site s = us.getSite();
            if (grantedSiteList.contains(s)) {
                siteList.add(s);
            }
        }
        ServiceLocator.getDepartmentManager(request).fillDepartment(siteList, true);
        request.setAttribute("X_SITELIST", siteList);
        return mapping.findForward("page");
    }










    public ActionForward insertDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);

        BeanForm userDepartmentForm = (BeanForm)form;
        UserDepartment ud = new UserDepartment();
        userDepartmentForm.populateToBean(ud);

        DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
        Department d = ud.getDepartment();
        if (d == null) {
            throw new BackToInputActionException("department.notFound", (Object[]) null);
        }
        d = dm.getDepartment(d.getId());
        if (d == null) {
            throw new BackToInputActionException("department.notFound", d.getId());
        }

        checkSite(d.getSite(), request);

        if (um.getUserDepartment(ud.getUser().getId(), ud.getDepartment().getId()) != null) {
            throw new BackToInputActionException("userDepartment.duplicate");
        }
        request.setAttribute("X_OBJECT", um.saveUserDepartment(ud));
        request.setAttribute("X_ROWPAGE", "userSiteDepartment/departmentRow.jsp");
        return mapping.findForward("success");
    }

    public ActionForward editDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!isBack(request)) {
            UserManager um = ServiceLocator.getUserManager(request);
            Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
            Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
            UserDepartment ud = um.getUserDepartment(userId, departmentId);
            if (ud == null) throw new ActionException("userDepartment.notFound", userId, departmentId);


            checkSite(ud.getDepartment().getSite(), request);

            BeanForm userDepartmentForm = (BeanForm)getForm("/updateUserDepartment", request);
            userDepartmentForm.populateToForm(ud);
        }
        return mapping.findForward("page");
    }


    public ActionForward updateDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);

        Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
        Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
        UserDepartment ud = um.getUserDepartment(userId, departmentId);
        if (ud == null) throw new ActionException("userDeparmtment.notFound", userId, departmentId);

        checkSite(ud.getDepartment().getSite(), request);

        BeanForm userDepartmentForm = (BeanForm)form;
        userDepartmentForm.populateToBean(ud);
        request.setAttribute("X_OBJECT", um.updateUserDepartment(ud));
        request.setAttribute("X_ROWPAGE", "userSiteDepartment/departmentRow.jsp");

        return mapping.findForward("success");
    }

    public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager um = ServiceLocator.getUserManager(request);
        Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
        Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
        UserDepartment ud = um.getUserDepartment(userId, departmentId);
        if (ud == null)
            throw new ActionException("userDepartment.notFound", userId, departmentId);
        checkSite(ud.getDepartment().getSite(), request);

        um.removeUserDepartment(ud);

        return mapping.findForward("success");
    }
}