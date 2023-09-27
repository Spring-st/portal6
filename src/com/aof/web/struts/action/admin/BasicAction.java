/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.GlobalParameter;
/*     */ import com.aof.model.admin.Menu;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.GlobalManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.IpAddress;
/*     */ import com.aof.web.domain.SessionList;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.LoginForm;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.utils.MD5;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private static final String DEFAULT_LOCALE = "en";
/*     */   public static final String CHANGE_PASSWORD_USER_KEY = "CHANGE_PASSWORD_USER";
/*     */   public static final String LOGIN_USER_KEY = "LOGIN_USER";
/*     */   public static final String LOGIN_TIME_KEY = "LOGIN_TIME";
/*     */   public static final String LOGIN_IP_KEY = "LOGIN_IP";
/*     */   
/*     */   public ActionForward welcome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  66 */     HttpSession session = request.getSession();
/*  67 */     if (session.getAttribute("LOGIN_USER") == null) {
/*  68 */       return mapping.findForward("login");
/*     */     }
/*  70 */     return mapping.findForward("mainFrame");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getAccountLockTimes(HttpServletRequest request) {
/*  76 */     GlobalManager gm = ServiceLocator.getGlobalManager(request);
/*  77 */     Integer times = gm.getParameter().getAccountLock();
/*  78 */     if (times == null) return 3; 
/*  79 */     return times.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  92 */     if ("POST".equals(request.getMethod())) {
/*  93 */       UserManager um = ServiceLocator.getUserManager(request);
/*  94 */       LoginForm loginForm = (LoginForm)form;
/*  95 */       User u = um.getUserByLoginName(loginForm.getLoginName());
/*  96 */       if (u != null) {
/*  97 */         if (u.getLoginFailedCount() == getAccountLockTimes(request)) throw new BackToInputActionException("login.failedTimesReach"); 
/*  98 */         if (u.getPassword().equalsIgnoreCase(MD5.getDigestString(loginForm.getPassword()))) {
/*  99 */           if (!EnabledDisabled.ENABLED.equals(u.getEnabled())) throw new BackToInputActionException("login.userDisabled"); 
/* 100 */           HttpSession session = request.getSession();
/* 101 */           if (u.getLastLoginTime() == null) {
/*     */             
/* 103 */             session.setAttribute("CHANGE_PASSWORD_USER", u);
/* 104 */             request.setAttribute("x_changePasswordUser", u);
/* 105 */             postGlobalMessage("login.alertChangePassword", request);
/* 106 */             GlobalManager gm = ServiceLocator.getGlobalManager(request);
/* 107 */             GlobalParameter gp = gm.getParameter();
/* 108 */             request.setAttribute("x_minPwdLen", gp.getMinPasswordLength());
/* 109 */             return mapping.findForward("changePwd");
/*     */           } 
/*     */           
/* 112 */           session.setAttribute("LOGIN_USER", u);
/* 113 */           session.setAttribute("LOGIN_TIME", new Date());
/* 114 */           session.setAttribute("LOGIN_IP", new IpAddress(request.getRemoteAddr()));
/* 115 */           SessionList.addSession(session);
/*     */           
/* 117 */           u.setLastLoginTime(new Date());
/* 118 */           u.setLoginFailedCount(0);
/* 119 */           String locale = loginForm.getLocale();
/* 120 */           if ("".equals(locale)) {
/* 121 */             locale = u.getLocale();
/*     */           } else {
/* 123 */             u.setLocale(locale);
/*     */           } 
/* 125 */           if (locale == null || locale.trim().length() == 0) {
/* 126 */             locale = "en";
/*     */           }
/* 128 */           setLocale(request, new Locale(locale));
/* 129 */           um.saveUser(u);
/* 130 */           return mapping.findForward("success");
/*     */         } 
/* 132 */         u.setLoginFailedCount(u.getLoginFailedCount() + 1);
/* 133 */         um.saveUser(u);
/*     */       } 
/* 135 */       throw new BackToInputActionException("login.failed");
/*     */     } 
/* 137 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward changePasswordFirstTime(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 141 */     if ("POST".equals(request.getMethod())) {
/* 142 */       GlobalManager gm = ServiceLocator.getGlobalManager(request);
/* 143 */       GlobalParameter gp = gm.getParameter();
/* 144 */       request.setAttribute("x_minPwdLen", gp.getMinPasswordLength());
/* 145 */       UserManager um = ServiceLocator.getUserManager(request);
/* 146 */       LoginForm loginForm = (LoginForm)form;
/* 147 */       User u = um.getUserByLoginName(loginForm.getLoginName());
/* 148 */       if (u != null) {
/* 149 */         if (u.getLoginFailedCount() == getAccountLockTimes(request)) throw new BackToInputActionException("login.failedTimesReach"); 
/* 150 */         if (u.getPassword().equalsIgnoreCase(MD5.getDigestString(loginForm.getPassword()))) {
/* 151 */           if (!EnabledDisabled.ENABLED.equals(u.getEnabled())) throw new BackToInputActionException("login.userDisabled"); 
/* 152 */           if (!request.getParameter("newPwd").equals(request.getParameter("passwordAgain")))
/* 153 */             throw new BackToInputActionException("user.password.notMatch"); 
/* 154 */           u.setPassword(MD5.getDigestString(request.getParameter("newPwd")));
/* 155 */           HttpSession session = request.getSession();
/*     */           
/* 157 */           session.setAttribute("LOGIN_USER", u);
/* 158 */           session.setAttribute("LOGIN_TIME", new Date());
/* 159 */           session.setAttribute("LOGIN_IP", new IpAddress(request.getRemoteAddr()));
/* 160 */           SessionList.addSession(session);
/*     */           
/* 162 */           u.setLastLoginTime(new Date());
/* 163 */           u.setLoginFailedCount(0);
/* 164 */           u.setLocale("en");
/* 165 */           setLocale(request, new Locale("en"));
/* 166 */           um.saveUser(u);
/* 167 */           return mapping.findForward("success");
/*     */         } 
/* 169 */         u.setLoginFailedCount(u.getLoginFailedCount() + 1);
/* 170 */         um.saveUser(u);
/*     */       } 
/* 172 */       throw new BackToInputActionException("login.failed");
/*     */     } 
/* 174 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward forgetPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 179 */     if ("POST".equals(request.getMethod())) {
/* 180 */       String userName = request.getParameter("loginName");
/* 181 */       if (userName != null) {
/* 182 */         UserManager um = ServiceLocator.getUserManager(request);
/* 183 */         User u = um.getUserByLoginName(userName);
/* 184 */         if (u != null) {
/* 185 */           String newPassword = getNewPassword(request);
/* 186 */           u.setPassword(MD5.getDigestString(newPassword));
/* 187 */           um.saveUser(u);
/* 188 */           EmailManager em = ServiceLocator.getEmailManager(request);
/* 189 */           Map<Object, Object> context = new HashMap<Object, Object>();
/* 190 */           context.put("user", u);
/* 191 */           context.put("newPassword", newPassword);
/* 192 */           em.insertEmail(u.getPrimarySite(), u.getEmail(), "ChangePassword.vm", context);
/*     */         } 
/*     */       } 
/* 195 */       request.setAttribute("success", "1");
/*     */     } 
/* 197 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private String getNewPassword(HttpServletRequest request) {
/* 201 */     GlobalManager gm = ServiceLocator.getGlobalManager(request);
/* 202 */     GlobalParameter gp = gm.getParameter();
/* 203 */     int length = gp.getMinPasswordLength().intValue();
/* 204 */     String newPassword = String.valueOf(getRandomLetter(1)) + getRandomLetter(0);
/* 205 */     for (int index = 2; index < length; index++) {
/* 206 */       newPassword = String.valueOf(newPassword) + getRandomLetter(-1);
/*     */     }
/* 208 */     return newPassword;
/*     */   }
/*     */   
/*     */   private String getRandomLetter(int type) {
/* 212 */     StringBuffer retVal = new StringBuffer();
/* 213 */     Random random = new Random();
/* 214 */     if (type == -1)
/* 215 */       type = random.nextInt(3); 
/* 216 */     switch (type) {
/*     */       case 0:
/* 218 */         retVal.append(random.nextInt(9));
/*     */         break;
/*     */       case 1:
/* 221 */         retVal.append((char)(65 + random.nextInt(25)));
/*     */         break;
/*     */       case 2:
/* 224 */         retVal.append((char)(97 + random.nextInt(25)));
/*     */         break;
/*     */     } 
/* 227 */     return retVal.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 240 */     HttpSession session = request.getSession();
/* 241 */     session.removeAttribute("LOGIN_USER");
/* 242 */     SessionList.removeSession(session);
/* 243 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 256 */     boolean siteHasLogo = false;
/* 257 */     User currentUser = getCurrentUser(request);
/* 258 */     request.setAttribute("X_LOGINED", Boolean.valueOf((currentUser != null)));
/* 259 */     if (currentUser != null) {
/* 260 */       SiteManager sm = ServiceLocator.getSiteManager(request);
/* 261 */       Integer siteId = currentUser.getPrimarySite().getId();
/* 262 */       request.setAttribute("X_SITEID", siteId);
/* 263 */       siteHasLogo = sm.isSiteHasLogo(siteId);
/*     */     } 
/* 265 */     request.setAttribute("X_SITEHASLOGO", Boolean.valueOf(siteHasLogo));
/* 266 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward menu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 279 */     List<String> resultList = new ArrayList();
/* 280 */     UserManager userManager = ServiceLocator.getUserManager(request);
/* 281 */     User user = getCurrentUser(request);
/* 282 */     if (user != null) {
/* 283 */       Set menu = userManager.getGrantedMenuSet(user);
/* 284 */       for (Iterator<Menu> itor = menu.iterator(); itor.hasNext(); ) {
/* 285 */         Menu m = itor.next();
/* 286 */         StringBuffer buf = new StringBuffer();
/* 287 */         buildMenuScript(m, buf, getLocale(request));
/* 288 */         resultList.add(buf.toString());
/*     */       } 
/* 290 */       request.setAttribute("X_MENULIST", resultList);
/* 291 */       return mapping.findForward("page");
/*     */     } 
/* 293 */     return mapping.findForward("login");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildMenuScript(Menu m, StringBuffer buf, Locale locale) {
/* 299 */     buf.append("['").append(Locale.ENGLISH.equals(locale) ? m.getName() : m.getSecondName()).append("',");
/* 300 */     Set children = m.getChildren();
/* 301 */     String url = m.getUrl();
/* 302 */     if (url == null || (children != null && children.isEmpty())) {
/* 303 */       buf.append(" 0");
/* 304 */       if (children != null && !children.isEmpty()) {
/* 305 */         buf.append(',');
/* 306 */         for (Iterator<Menu> itor = children.iterator(); itor.hasNext();) {
/* 307 */           buildMenuScript(itor.next(), buf, locale);
/*     */         }
/*     */       } 
/* 310 */       buf.append("],");
/*     */     } else {
/* 312 */       buf.append('\'').append(url).append(".do'],");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/BasicAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */