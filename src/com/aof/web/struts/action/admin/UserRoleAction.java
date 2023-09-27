/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Role;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserDepartment;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.metadata.RoleType;
/*     */ import com.aof.service.admin.RoleManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class UserRoleAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  53 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/*  55 */     Integer id = ActionUtils.parseInt(request.getParameter("userId"));
/*  56 */     User u = um.getUser(id);
/*  57 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */ 
/*     */ 
/*     */     
/*  61 */     request.setAttribute("X_USER", u);
/*     */     
/*  63 */     List userDepartmentList = um.getUserDepartmentListByUser(u);
/*  64 */     List userRoleList = um.getUserRoleListByUser(u);
/*  65 */     if (hasGlobalPower(request)) {
/*  66 */       request.setAttribute("X_GLOBAL", Boolean.TRUE);
/*     */     } else {
/*  68 */       request.setAttribute("X_GLOBAL", Boolean.FALSE);
/*     */       
/*  70 */       List grantedSiteList = getGrantedSiteList(request);
/*  71 */       for (Iterator<UserDepartment> iterator = userDepartmentList.iterator(); iterator.hasNext(); ) {
/*  72 */         UserDepartment ud = iterator.next();
/*  73 */         Site s = ud.getDepartment().getSite();
/*  74 */         if (!grantedSiteList.contains(s)) {
/*  75 */           iterator.remove();
/*     */         }
/*     */       } 
/*  78 */       for (Iterator<UserRole> itor = userRoleList.iterator(); itor.hasNext(); ) {
/*  79 */         UserRole ur = itor.next();
/*  80 */         RoleType rt = ur.getRole().getType();
/*  81 */         if (RoleType.GLOBAL_ADMIN.equals(rt)) {
/*  82 */           itor.remove(); continue;
/*  83 */         }  if (RoleType.SITE_ADMIN.equals(rt)) {
/*  84 */           Site grantedSite = ur.getGrantedSite();
/*  85 */           if (grantedSite == null || !grantedSiteList.contains(grantedSite))
/*  86 */             itor.remove();  continue;
/*     */         } 
/*  88 */         if (RoleType.ENDUSER.equals(rt)) {
/*  89 */           Department grantedDepartment = ur.getGrantedDepartment();
/*  90 */           Site grantedSite = ur.getGrantedSite();
/*  91 */           if (grantedDepartment != null) {
/*  92 */             grantedSite = grantedDepartment.getSite();
/*     */           }
/*  94 */           if (grantedSite == null || !grantedSiteList.contains(grantedSite)) {
/*  95 */             itor.remove();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     request.setAttribute("X_DEPARTMENTLIST", userDepartmentList);
/* 102 */     request.setAttribute("X_RESULTLIST", userRoleList);
/* 103 */     return mapping.findForward("page");
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
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 117 */     if (!isBack(request)) {
/* 118 */       UserManager um = ServiceLocator.getUserManager(request);
/*     */       
/* 120 */       Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
/* 121 */       User u = um.getUser(id);
/* 122 */       if (u == null) throw new ActionException("user.notFound", id);
/*     */       
/* 124 */       checkSite(u.getPrimarySite(), request);
/*     */       
/* 126 */       UserRole ur = new UserRole();
/* 127 */       ur.setUser(u);
/* 128 */       BeanForm userRoleForm = (BeanForm)getForm("/insertUserRole", request);
/* 129 */       userRoleForm.populateToForm(ur);
/*     */     } 
/* 131 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 132 */     List roleList = rm.getAllRoleList();
/*     */     
/* 134 */     if (!hasGlobalPower(request))
/* 135 */       for (Iterator<Role> itor = roleList.iterator(); itor.hasNext(); ) {
/* 136 */         Role r = itor.next();
/* 137 */         if (RoleType.GLOBAL_ADMIN.equals(r.getType())) itor.remove();
/*     */       
/*     */       }  
/* 140 */     request.setAttribute("X_ROLELIST", roleList);
/*     */     
/* 142 */     List siteList = getGrantedSiteDeparmentList(request);
/* 143 */     request.setAttribute("X_SITELIST", siteList);
/* 144 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 157 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 159 */     Integer id = ActionUtils.parseInt(request.getParameter("user_id"));
/* 160 */     User u = um.getUser(id);
/* 161 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */     
/* 163 */     checkSite(u.getPrimarySite(), request);
/*     */     
/* 165 */     BeanForm userRoleForm = (BeanForm)form;
/* 166 */     UserRole ur = new UserRole();
/* 167 */     userRoleForm.populateToBean(ur);
/* 168 */     Iterator<UserRole> itor = um.getUserRoleListByUser(ur.getUser()).iterator();
/* 169 */     while (itor.hasNext()) {
/* 170 */       UserRole oldUr = itor.next();
/* 171 */       Role role = oldUr.getRole();
/* 172 */       if (!role.equals(ur.getRole()))
/* 173 */         continue;  if (RoleType.GLOBAL_ADMIN.equals(role.getType()) || RoleType.COMMON_GLOBAL_LEVEL.equals(role.getType()))
/* 174 */         throw new BackToInputActionException("userRole.duplicate"); 
/* 175 */       if (RoleType.SITE_ADMIN.equals(role.getType())) {
/* 176 */         Site s = oldUr.getGrantedSite();
/* 177 */         if (s.equals(ur.getGrantedSite()))
/* 178 */           throw new BackToInputActionException("userRole.duplicate");  continue;
/*     */       } 
/* 180 */       if (RoleType.ENDUSER.equals(role.getType())) {
/* 181 */         Department d = oldUr.getGrantedDepartment();
/* 182 */         if (d.equals(ur.getGrantedDepartment())) {
/* 183 */           throw new BackToInputActionException("userRole.duplicate");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 189 */     Role r = rm.getRole(ur.getRole().getId());
/* 190 */     RoleType rt = r.getType();
/* 191 */     if (RoleType.GLOBAL_ADMIN.equals(rt)) {
/* 192 */       if (!hasGlobalPower(request)) {
/* 193 */         throw new ActionException("userRole.cannotAssignGlobalRole");
/*     */       }
/* 195 */       ur.setGrantedSite(null);
/* 196 */       ur.setGrantedDepartment(null);
/* 197 */     } else if (RoleType.SITE_ADMIN.equals(rt)) {
/* 198 */       ur.setGrantedDepartment(null);
/* 199 */     } else if (RoleType.COMMON_GLOBAL_LEVEL.equals(rt)) {
/* 200 */       ur.setGrantedSite(null);
/* 201 */       ur.setGrantedDepartment(null);
/*     */     } 
/* 203 */     request.setAttribute("X_OBJECT", um.saveUserRole(ur));
/* 204 */     request.setAttribute("X_ROWPAGE", "userRole/row.jsp");
/*     */     
/* 206 */     return mapping.findForward("success");
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 219 */     if (!isBack(request)) {
/* 220 */       UserManager um = ServiceLocator.getUserManager(request);
/* 221 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 222 */       UserRole ur = um.getUserRole(id);
/* 223 */       if (ur == null) throw new ActionException("userRole.notFound", id);
/*     */ 
/*     */       
/* 226 */       checkSite(ur.getUser().getPrimarySite(), request);
/*     */       
/* 228 */       if (RoleType.GLOBAL_ADMIN.equals(ur.getRole().getType()) && 
/* 229 */         !hasGlobalPower(request)) {
/* 230 */         throw new ActionException("userRole.cannotMaintainGlobalRole");
/*     */       }
/*     */ 
/*     */       
/* 234 */       BeanForm userRoleForm = (BeanForm)getForm("/updateUserRole", request);
/* 235 */       userRoleForm.populateToForm(ur);
/*     */     } 
/* 237 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 238 */     List roleList = rm.getAllRoleList();
/*     */     
/* 240 */     if (!hasGlobalPower(request))
/* 241 */       for (Iterator<Role> itor = roleList.iterator(); itor.hasNext(); ) {
/* 242 */         Role r = itor.next();
/* 243 */         if (RoleType.GLOBAL_ADMIN.equals(r.getType())) itor.remove();
/*     */       
/*     */       }  
/* 246 */     request.setAttribute("X_ROLELIST", roleList);
/* 247 */     List siteList = getGrantedSiteDeparmentList(request);
/* 248 */     request.setAttribute("X_SITELIST", siteList);
/* 249 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 262 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 264 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 265 */     User u = um.getUser(userId);
/* 266 */     if (u == null) throw new ActionException("user.notFound", userId);
/*     */     
/* 268 */     checkSite(u.getPrimarySite(), request);
/*     */     
/* 270 */     BeanForm userRoleForm = (BeanForm)form;
/* 271 */     UserRole ur = new UserRole();
/* 272 */     userRoleForm.populateToBean(ur);
/* 273 */     Integer id = ur.getId();
/* 274 */     Iterator<UserRole> itor = um.getUserRoleListByUser(ur.getUser()).iterator();
/* 275 */     while (itor.hasNext()) {
/* 276 */       UserRole oldUr = itor.next();
/* 277 */       if (oldUr.getId().equals(id))
/* 278 */         continue;  Role role = oldUr.getRole();
/* 279 */       if (!role.equals(ur.getRole()))
/* 280 */         continue;  if (RoleType.GLOBAL_ADMIN.equals(role.getType()) || RoleType.COMMON_GLOBAL_LEVEL.equals(role.getType()))
/* 281 */         throw new BackToInputActionException("userRole.duplicate"); 
/* 282 */       if (RoleType.SITE_ADMIN.equals(role.getType())) {
/* 283 */         Site s = oldUr.getGrantedSite();
/* 284 */         if (s.equals(ur.getGrantedSite()))
/* 285 */           throw new BackToInputActionException("userRole.duplicate");  continue;
/*     */       } 
/* 287 */       if (RoleType.ENDUSER.equals(role.getType())) {
/* 288 */         Department d = oldUr.getGrantedDepartment();
/* 289 */         if (d.equals(ur.getGrantedDepartment())) {
/* 290 */           throw new BackToInputActionException("userRole.duplicate");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 296 */     Role r = rm.getRole(ur.getRole().getId());
/* 297 */     RoleType rt = r.getType();
/* 298 */     if (RoleType.GLOBAL_ADMIN.equals(rt)) {
/* 299 */       if (!hasGlobalPower(request)) {
/* 300 */         throw new ActionException("userRole.cannotAssignGlobalRole");
/*     */       }
/* 302 */       ur.setGrantedSite(null);
/* 303 */       ur.setGrantedDepartment(null);
/* 304 */     } else if (RoleType.SITE_ADMIN.equals(rt)) {
/* 305 */       ur.setGrantedDepartment(null);
/* 306 */     } else if (RoleType.COMMON_GLOBAL_LEVEL.equals(rt)) {
/* 307 */       ur.setGrantedSite(null);
/* 308 */       ur.setGrantedDepartment(null);
/*     */     } 
/* 310 */     request.setAttribute("X_OBJECT", um.saveUserRole(ur));
/* 311 */     request.setAttribute("X_ROWPAGE", "userRole/row.jsp");
/*     */     
/* 313 */     return mapping.findForward("success");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 326 */     UserManager um = ServiceLocator.getUserManager(request);
/* 327 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 328 */     UserRole ur = um.getUserRole(id);
/* 329 */     if (ur == null) throw new ActionException("userRole.notFound", id);
/*     */ 
/*     */     
/* 332 */     checkSite(ur.getUser().getPrimarySite(), request);
/*     */     
/* 334 */     if (RoleType.GLOBAL_ADMIN.equals(ur.getRole().getType()) && 
/* 335 */       !hasGlobalPower(request)) {
/* 336 */       throw new ActionException("userRole.cannotDeleteGlobalRole");
/*     */     }
/*     */ 
/*     */     
/* 340 */     um.removeUserRole(ur);
/*     */     
/* 342 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/UserRoleAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */