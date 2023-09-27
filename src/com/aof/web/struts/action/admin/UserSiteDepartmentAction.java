/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserDepartment;
/*     */ import com.aof.model.admin.UserSite;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserSiteDepartmentAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  59 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/*  61 */     Integer id = ActionUtils.parseInt(request.getParameter("userId"));
/*  62 */     User u = um.getUser(id);
/*  63 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */     
/*  65 */     request.setAttribute("X_USER", u);
/*     */     
/*  67 */     List userSiteList = um.getUserSiteListByUser(u);
/*  68 */     List userDepartmentList = null;
/*  69 */     if (!hasGlobalPower(request)) {
/*  70 */       userDepartmentList = new ArrayList();
/*  71 */       request.setAttribute("X_GLOBAL", Boolean.FALSE);
/*     */       
/*  73 */       List grantedSiteList = getGrantedSiteList(request);
/*  74 */       for (Iterator<UserSite> itor = userSiteList.iterator(); itor.hasNext(); ) {
/*  75 */         UserSite us = itor.next();
/*  76 */         Site s = us.getSite();
/*  77 */         if (!grantedSiteList.contains(s)) {
/*  78 */           itor.remove(); continue;
/*     */         } 
/*  80 */         userDepartmentList.addAll(um.getUserDepartmentListByUserAndSite(u, s));
/*     */       } 
/*     */     } else {
/*     */       
/*  84 */       request.setAttribute("X_GLOBAL", Boolean.TRUE);
/*  85 */       userDepartmentList = um.getUserDepartmentListByUser(u);
/*     */     } 
/*     */     
/*  88 */     request.setAttribute("X_SITELIST", userSiteList);
/*  89 */     request.setAttribute("X_DEPARTMENTLIST", userDepartmentList);
/*  90 */     return mapping.findForward("page");
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
/*     */   public ActionForward newSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 103 */     if (!isBack(request)) {
/* 104 */       UserManager um = ServiceLocator.getUserManager(request);
/*     */       
/* 106 */       Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
/* 107 */       User u = um.getUser(id);
/* 108 */       if (u == null) throw new ActionException("user.notFound", id);
/*     */       
/* 110 */       UserSite us = new UserSite();
/* 111 */       us.setUser(u);
/* 112 */       BeanForm userSiteForm = (BeanForm)getForm("/insertUserSite", request);
/* 113 */       userSiteForm.populateToForm(us);
/*     */     } 
/*     */     
/* 116 */     List siteList = getAndCheckGrantedSiteList(request);
/*     */     
/* 118 */     for (Iterator<Site> itor = siteList.iterator(); itor.hasNext();) {
/* 119 */       site = itor.next();
/*     */     }
/*     */ 
/*     */     
/* 123 */     request.setAttribute("X_SITELIST", siteList);
/* 124 */     return mapping.findForward("page");
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
/*     */   public ActionForward insertSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 137 */     UserManager um = ServiceLocator.getUserManager(request);
/* 138 */     System.out.println("11");
/* 139 */     Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
/* 140 */     User u = um.getUser(id);
/* 141 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */     
/* 143 */     BeanForm userSiteForm = (BeanForm)form;
/* 144 */     UserSite us = new UserSite();
/* 145 */     userSiteForm.populateToBean(us);
/*     */     
/* 147 */     Site s = us.getSite();
/* 148 */     if (s == null) {
/* 149 */       throw new BackToInputActionException("site.notFound", null);
/*     */     }
/* 151 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 152 */     s = sm.getSite(s.getId());
/* 153 */     if (s == null) {
/* 154 */       throw new BackToInputActionException("site.notFound", s.getId());
/*     */     }
/* 156 */     checkSite(s, request);
/*     */     
/* 158 */     if (um.getUserSite(us.getUser().getId(), s.getId()) != null) {
/* 159 */       throw new BackToInputActionException("userSite.duplicate");
/*     */     }
/* 161 */     request.setAttribute("X_OBJECT", um.saveUserSite(us));
/* 162 */     request.setAttribute("X_ROWPAGE", "userSiteDepartment/siteRow.jsp");
/*     */     
/* 164 */     EmailManager em = ServiceLocator.getEmailManager(request);
/* 165 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     List userList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/* 178 */       User user = itor.next();
/* 179 */       if (user.getEmail() != null) {
/* 180 */         Map<Object, Object> context = new HashMap<Object, Object>();
/* 181 */         context.put("user", u);
/* 182 */         context.put("finance", user);
/* 183 */         em.insertEmail(s, user.getEmail(), "NewUserSite.vm", context);
/*     */       } 
/*     */     } 
/* 186 */     return mapping.findForward("success");
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
/*     */   public ActionForward editSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 199 */     UserManager um = ServiceLocator.getUserManager(request);
/* 200 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 201 */     Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
/* 202 */     UserSite us = um.getUserSite(userId, siteId);
/* 203 */     if (us == null) throw new ActionException("userSite.notFound", userId, siteId);
/*     */ 
/*     */     
/* 206 */     checkSite(us.getSite(), request);
/*     */     
/* 208 */     BeanForm userSiteForm = (BeanForm)getForm("/updateUserSite", request);
/* 209 */     if (!isBack(request)) {
/* 210 */       userSiteForm.populateToForm(us);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     return mapping.findForward("page");
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
/*     */   public ActionForward updateSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 229 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 231 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 232 */     Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
/* 233 */     UserSite us = um.getUserSite(userId, siteId);
/* 234 */     if (us == null) throw new ActionException("userSite.notFound", userId, siteId);
/*     */     
/* 236 */     checkSite(us.getSite(), request);
/*     */     
/* 238 */     BeanForm userSiteForm = (BeanForm)form;
/* 239 */     userSiteForm.populateToBean(us);
/* 240 */     request.setAttribute("X_OBJECT", um.updateUserSite(us));
/* 241 */     request.setAttribute("X_ROWPAGE", "userSiteDepartment/siteRow.jsp");
/*     */     
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
/*     */   public ActionForward deleteSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 256 */     UserManager um = ServiceLocator.getUserManager(request);
/* 257 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 258 */     Integer siteId = ActionUtils.parseInt(request.getParameter("site_id"));
/* 259 */     UserSite us = um.getUserSite(userId, siteId);
/* 260 */     if (us == null) throw new ActionException("userSite.notFound", userId, siteId);
/*     */ 
/*     */     
/* 263 */     checkSite(us.getSite(), request);
/*     */     
/* 265 */     um.removeUserSite(us);
/*     */     
/* 267 */     return mapping.findForward("success");
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
/*     */   public ActionForward newDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 280 */     UserManager um = ServiceLocator.getUserManager(request);
/* 281 */     Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
/* 282 */     User u = um.getUser(id);
/* 283 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */     
/* 285 */     if (!isBack(request)) {
/*     */       
/* 287 */       UserDepartment ud = new UserDepartment();
/* 288 */       ud.setUser(u);
/* 289 */       BeanForm userDepartmentForm = (BeanForm)getForm("/insertUserDepartment", request);
/* 290 */       userDepartmentForm.populateToForm(ud);
/*     */     } 
/*     */     
/* 293 */     List<Site> siteList = new ArrayList();
/* 294 */     List userSiteList = um.getUserSiteListByUser(u);
/*     */     
/* 296 */     List grantedSiteList = getGrantedSiteList(request);
/* 297 */     for (Iterator<UserSite> itor = userSiteList.iterator(); itor.hasNext(); ) {
/* 298 */       UserSite us = itor.next();
/* 299 */       Site s = us.getSite();
/* 300 */       if (grantedSiteList.contains(s)) {
/* 301 */         siteList.add(s);
/*     */       }
/*     */     } 
/* 304 */     ServiceLocator.getDepartmentManager(request).fillDepartment(siteList, true);
/* 305 */     request.setAttribute("X_SITELIST", siteList);
/* 306 */     return mapping.findForward("page");
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
/*     */   public ActionForward insertDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 319 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 321 */     BeanForm userDepartmentForm = (BeanForm)form;
/* 322 */     UserDepartment ud = new UserDepartment();
/* 323 */     userDepartmentForm.populateToBean(ud);
/*     */     
/* 325 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 326 */     Department d = ud.getDepartment();
/* 327 */     if (d == null) {
/* 328 */       throw new BackToInputActionException("department.notFound", null);
/*     */     }
/* 330 */     d = dm.getDepartment(d.getId());
/* 331 */     if (d == null) {
/* 332 */       throw new BackToInputActionException("department.notFound", d.getId());
/*     */     }
/*     */     
/* 335 */     checkSite(d.getSite(), request);
/*     */     
/* 337 */     if (um.getUserDepartment(ud.getUser().getId(), ud.getDepartment().getId()) != null) {
/* 338 */       throw new BackToInputActionException("userDepartment.duplicate");
/*     */     }
/* 340 */     request.setAttribute("X_OBJECT", um.saveUserDepartment(ud));
/* 341 */     request.setAttribute("X_ROWPAGE", "userSiteDepartment/departmentRow.jsp");
/*     */     
/* 343 */     return mapping.findForward("success");
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
/*     */   public ActionForward editDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 356 */     if (!isBack(request)) {
/* 357 */       UserManager um = ServiceLocator.getUserManager(request);
/* 358 */       Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 359 */       Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
/* 360 */       UserDepartment ud = um.getUserDepartment(userId, departmentId);
/* 361 */       if (ud == null) throw new ActionException("userDepartment.notFound", userId, departmentId);
/*     */ 
/*     */       
/* 364 */       checkSite(ud.getDepartment().getSite(), request);
/*     */       
/* 366 */       BeanForm userDepartmentForm = (BeanForm)getForm("/updateUserDepartment", request);
/* 367 */       userDepartmentForm.populateToForm(ud);
/*     */     } 
/* 369 */     return mapping.findForward("page");
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
/*     */   public ActionForward updateDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 382 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 384 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 385 */     Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
/* 386 */     UserDepartment ud = um.getUserDepartment(userId, departmentId);
/* 387 */     if (ud == null) throw new ActionException("userDeparmtment.notFound", userId, departmentId);
/*     */     
/* 389 */     checkSite(ud.getDepartment().getSite(), request);
/*     */     
/* 391 */     BeanForm userDepartmentForm = (BeanForm)form;
/* 392 */     userDepartmentForm.populateToBean(ud);
/* 393 */     request.setAttribute("X_OBJECT", um.updateUserDepartment(ud));
/* 394 */     request.setAttribute("X_ROWPAGE", "userSiteDepartment/departmentRow.jsp");
/*     */     
/* 396 */     return mapping.findForward("success");
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
/*     */   public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 409 */     UserManager um = ServiceLocator.getUserManager(request);
/* 410 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 411 */     Integer departmentId = ActionUtils.parseInt(request.getParameter("department_id"));
/* 412 */     UserDepartment ud = um.getUserDepartment(userId, departmentId);
/* 413 */     if (ud == null) throw new ActionException("userDepartment.notFound", userId, departmentId);
/*     */ 
/*     */     
/* 416 */     checkSite(ud.getDepartment().getSite(), request);
/*     */     
/* 418 */     um.removeUserDepartment(ud);
/*     */     
/* 420 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/UserSiteDepartmentAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */