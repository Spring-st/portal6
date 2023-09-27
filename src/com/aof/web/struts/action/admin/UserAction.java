/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.GlobalParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.Gender;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.GlobalManager;
/*     */ import com.aof.service.admin.RoleManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.PDFReport;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.UserQueryForm;
/*     */ import com.lowagie.text.Document;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.Element;
/*     */ import com.lowagie.text.Font;
/*     */ import com.lowagie.text.PageSize;
/*     */ import com.lowagie.text.pdf.PdfPCell;
/*     */ import com.lowagie.text.pdf.PdfPTable;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import com.shcnc.utils.MD5;
/*     */ import java.awt.Color;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
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
/*     */ public class UserAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private static final String DEFAULT_LOCALE = "en";
/*     */   
/*     */   public ActionForward userRoleReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  86 */     UserManager um = ServiceLocator.getUserManager(request);
/*  87 */     UserQueryForm queryForm = (UserQueryForm)form;
/*  88 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     List<Site> grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/*  95 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*     */     
/*  97 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/*  98 */     if (siteId == null) {
/*  99 */       siteId = ((Site)grantedSiteList.get(0)).getId();
/* 100 */       queryForm.setSiteId(siteId.toString());
/* 101 */       queryForm.setOrder(UserQueryOrder.NAME.getName());
/*     */     } else {
/* 103 */       checkSite(siteId, request);
/*     */     } 
/*     */ 
/*     */     
/* 107 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 109 */     String exportType = queryForm.getExportType();
/* 110 */     if (exportType != null && exportType.length() > 0) {
/* 111 */       List data = um.getUserRoleList(conditions, UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 113 */       exportPDF(ActionUtils.parseInt(queryForm.getSiteId()), data, "userRole", request, response);
/* 114 */       return null;
/*     */     } 
/*     */     
/* 117 */     if (queryForm.isFirstInit()) {
/* 118 */       queryForm.init(um.getUserListCount(conditions));
/*     */     } else {
/* 120 */       queryForm.init();
/*     */     } 
/*     */     
/* 123 */     List userList = um.getUserList(
/* 124 */         conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 125 */     um.fillUserRole(userList);
/* 126 */     request.setAttribute("X_RESULTLIST", userList);
/*     */     
/* 128 */     RoleManager rm = ServiceLocator.getRoleManager(request);
/* 129 */     request.setAttribute("x_roleList", rm.getAllRoleList());
/* 130 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void exportPDF(Integer siteId, List data, String filename, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, DocumentException, IOException {
/* 134 */     MessageResources messages = getResources(request);
/* 135 */     Locale locale = getLocale(request);
/*     */     
/* 137 */     PDFReport report = PDFReport.createReport(siteId, "user.userRole.report", request, messages, locale, PageSize.A4.rotate());
/* 138 */     Document document = report.getDocument();
/*     */     
/* 140 */     PdfPTable table = PDFReport.createTable(new float[] { 1.0F, 2.0F, 1.0F, 1.0F, 1.0F }, 100, 0.5F);
/* 141 */     table.setHeaderRows(1);
/* 142 */     table.setSplitLate(false);
/* 143 */     PdfPCell defaultCell = table.getDefaultCell();
/* 144 */     Color defaultBackgroundColor = defaultCell.backgroundColor();
/*     */     
/* 146 */     defaultCell.setHorizontalAlignment(1);
/* 147 */     defaultCell.setBackgroundColor(new Color(153, 153, 255));
/*     */     
/* 149 */     Font headFont = PDFReport.getFont(1, Color.BLACK);
/* 150 */     report.addCell(table, "user.name", headFont, true);
/* 151 */     report.addCell(table, "userRole.role", headFont, true);
/* 152 */     report.addCell(table, "role.type", headFont, true);
/* 153 */     report.addCell(table, "userDepartment.site", headFont, true);
/* 154 */     report.addCell(table, "userDepartment.department", headFont, true);
/*     */ 
/*     */     
/* 157 */     defaultCell.setBackgroundColor(defaultBackgroundColor);
/*     */ 
/*     */     
/* 160 */     for (Iterator<User> itor = data.iterator(); itor.hasNext(); ) {
/* 161 */       User user = itor.next();
/* 162 */       defaultCell.setHorizontalAlignment(0);
/* 163 */       report.addCell(table, user.getName());
/*     */       
/* 165 */       PdfPTable table2 = PDFReport.createTable(new float[] { 2.0F, 1.0F, 1.0F, 1.0F }, 100, 0.5F);
/*     */       
/* 167 */       boolean hasItem = false;
/* 168 */       for (Iterator<UserRole> itor2 = user.getUserRoleList().iterator(); itor2.hasNext(); ) {
/* 169 */         hasItem = true;
/* 170 */         UserRole ur = itor2.next();
/* 171 */         report.addCell(table2, ur.getRole().getName());
/* 172 */         report.addCell(table2, ur.getRole().getType().getEngDescription());
/* 173 */         if (ur.getGrantedSite() != null) {
/* 174 */           report.addCell(table2, ur.getGrantedSite().getName());
/*     */         } else {
/* 176 */           report.addCell(table2, null);
/*     */         } 
/* 178 */         if (ur.getGrantedDepartment() != null) {
/* 179 */           report.addCell(table2, ur.getGrantedDepartment().getName()); continue;
/*     */         } 
/* 181 */         report.addCell(table2, null);
/*     */       } 
/*     */ 
/*     */       
/* 185 */       if (!hasItem) {
/* 186 */         report.addCell(table2, null);
/* 187 */         report.addCell(table2, null);
/* 188 */         report.addCell(table2, null);
/*     */       } 
/* 190 */       PDFReport.AddTableToTable(table, table2, 4);
/*     */     } 
/*     */     
/* 193 */     document.add((Element)table);
/*     */     
/* 195 */     report.output(filename, response);
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
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 211 */     UserQueryForm queryForm = (UserQueryForm)form;
/* 212 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     List<Site> grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/* 219 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*     */     
/* 221 */     if (hasGlobalPower(request)) {
/* 222 */       request.setAttribute("X_GLOBAL", Boolean.TRUE);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 227 */       Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 228 */       if (siteId == null) {
/* 229 */         siteId = ((Site)grantedSiteList.get(0)).getId();
/* 230 */         queryForm.setSiteId(siteId.toString());
/*     */       } else {
/* 232 */         checkSite(siteId, request);
/*     */       } 
/* 234 */       request.setAttribute("X_GLOBAL", Boolean.FALSE);
/*     */     } 
/*     */     
/* 237 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 239 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 241 */     String exportType = queryForm.getExportType();
/* 242 */     if (exportType != null && exportType.length() > 0) {
/* 243 */       List data = um.getUserList(conditions, 0, -1, UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 244 */       int index = SessionTempFile.createNewTempFile(request);
/* 245 */       String fileName = "user";
/* 246 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 249 */               MessageResources messages = UserAction.this.getResources(request);
/* 250 */               row.add(messages.getMessage(UserAction.this.getLocale(request), "user.loginName"));
/* 251 */               row.add(messages.getMessage(UserAction.this.getLocale(request), "user.name"));
/* 252 */               row.add(messages.getMessage(UserAction.this.getLocale(request), "user.email"));
/* 253 */               row.add(messages.getMessage(UserAction.this.getLocale(request), "user.telephone"));
/* 254 */               row.add(messages.getMessage(UserAction.this.getLocale(request), "user.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 258 */               row.add(BeanUtils.getProperty(data, "loginName"));
/* 259 */               row.add(BeanUtils.getProperty(data, "name"));
/* 260 */               row.add(BeanUtils.getProperty(data, "email"));
/* 261 */               row.add(BeanUtils.getProperty(data, "telephone"));
/* 262 */               String locale = UserAction.this.getCurrentUser(request).getLocale();
/* 263 */               if ("en".equals(locale)) {
/* 264 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/* 266 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 270 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 273 */     if (queryForm.isFirstInit()) {
/* 274 */       queryForm.init(um.getUserListCount(conditions));
/*     */     } else {
/* 276 */       queryForm.init();
/*     */     } 
/*     */     
/* 279 */     request.setAttribute("X_RESULTLIST", um.getUserList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 280 */     return mapping.findForward("page");
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
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 293 */     UserQueryForm queryForm = (UserQueryForm)form;
/* 294 */     queryForm.setPageSize("10");
/* 295 */     return list(mapping, form, request, response);
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(UserQueryForm queryForm) {
/* 299 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 301 */     String loginName = queryForm.getLoginName();
/* 302 */     if (loginName != null) {
/* 303 */       loginName = loginName.trim();
/* 304 */       if (loginName.length() == 0) loginName = null; 
/*     */     } 
/* 306 */     if (loginName != null) conditions.put(UserQueryCondition.LOGINNAME_LIKE, loginName);
/*     */     
/* 308 */     String name = queryForm.getName();
/* 309 */     if (name != null) {
/* 310 */       name = name.trim();
/* 311 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 313 */     if (name != null) conditions.put(UserQueryCondition.NAME_LIKE, name);
/*     */     
/* 315 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 316 */     Integer departmentId = ActionUtils.parseInt(queryForm.getDepartmentId());
/* 317 */     if (departmentId != null) {
/* 318 */       conditions.put(UserQueryCondition.DEPARTMENT_ID_EQ, departmentId);
/* 319 */     } else if (siteId != null) {
/* 320 */       conditions.put(UserQueryCondition.PRIMARY_OR_SITE_ID_EQ, siteId);
/*     */     } 
/*     */     
/* 323 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 324 */     if (enabled != null) {
/* 325 */       conditions.put(UserQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*     */ 
/*     */     
/* 329 */     Integer roleId = ActionUtils.parseInt(queryForm.getRoleId());
/* 330 */     if (roleId != null) {
/* 331 */       conditions.put(UserQueryCondition.ROLE_ID_EQ, roleId);
/*     */     }
/*     */     
/* 334 */     return conditions;
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 347 */     if (!isBack(request)) {
/* 348 */       User u = new User();
/* 349 */       BeanForm userForm = (BeanForm)getForm("/insertUser", request);
/* 350 */       userForm.populateToForm(u, new String[] { "password" });
/*     */     } 
/* 352 */     request.setAttribute("X_GENDERLIST", PersistentEnum.getEnumList(Gender.class));
/* 353 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 354 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 355 */     return mapping.findForward("page");
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
/* 368 */     UserManager um = ServiceLocator.getUserManager(request);
/* 369 */     BeanForm userForm = (BeanForm)form;
/* 370 */     User u = new User();
/* 371 */     userForm.populateToBean(u);
/* 372 */     if (um.getUserByLoginName(u.getLoginName()) != null) {
/* 373 */       throw new BackToInputActionException("user.loginName.existed", u.getLoginName());
/*     */     }
/* 375 */     checkSite(u.getPrimarySite().getId(), request);
/* 376 */     u.setPassword(MD5.getDigestString(u.getPassword()));
/* 377 */     u.setLocale("en");
/* 378 */     u.setLastLoginTime(new Date());
/* 379 */     request.setAttribute("X_OBJECT", um.saveUser(u));
/* 380 */     request.setAttribute("X_ROWPAGE", "user/row.jsp");
/*     */     
/* 382 */     EmailManager em = ServiceLocator.getEmailManager(request);
/* 383 */     Map<Object, Object> context = new HashMap<Object, Object>();
/* 384 */     context.put("user", u);
/* 385 */     context.put("password", userForm.get("password"));
/* 386 */     em.insertEmail(u.getPrimarySite(), u.getEmail(), "NewUser.vm", context);
/* 387 */     return mapping.findForward("success");
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
/* 400 */     if (!isBack(request)) {
/* 401 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 402 */       UserManager um = ServiceLocator.getUserManager(request);
/* 403 */       User u = um.getUser(id);
/* 404 */       if (u == null) throw new ActionException("user.notFound", id); 
/* 405 */       checkSite(u.getPrimarySite(), request);
/* 406 */       BeanForm userForm = (BeanForm)getForm("/updateUser", request);
/* 407 */       userForm.populateToForm(u, new String[] { "password" });
/*     */     } 
/* 409 */     request.setAttribute("X_GENDERLIST", PersistentEnum.getEnumList(Gender.class));
/* 410 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 411 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 412 */     return mapping.findForward("page");
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
/* 425 */     UserManager um = ServiceLocator.getUserManager(request);
/* 426 */     BeanForm userForm = (BeanForm)form;
/* 427 */     Integer id = ActionUtils.parseInt((String)userForm.get("id"));
/* 428 */     User u = um.getUser(id);
/* 429 */     if (u == null) throw new ActionException("user.notFound", id);
/*     */     
/* 431 */     List siteList = getAndCheckGrantedSiteList(request);
/* 432 */     Site s = u.getPrimarySite();
/* 433 */     if (!siteList.contains(s)) {
/* 434 */       throw new ActionException("errors.noSitePermission", s.getName());
/*     */     }
/* 436 */     EnabledDisabled oldEnabled = u.getEnabled();
/*     */     
/* 438 */     String oldPassword = u.getPassword();
/*     */     
/* 440 */     String loginName = u.getLoginName();
/* 441 */     userForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 442 */     userForm.populateToBean(u);
/*     */     
/* 444 */     s = u.getPrimarySite();
/* 445 */     if (s == null) {
/* 446 */       throw new BackToInputActionException("site.notFound", ActionUtils.parseInt(userForm.getString("primarySite_id")));
/*     */     }
/* 448 */     if (!siteList.contains(s)) {
/* 449 */       throw new BackToInputActionException("errors.noSitePermission", s.getName());
/*     */     }
/* 451 */     String newPassword = u.getPassword();
/* 452 */     if (newPassword == null || newPassword.length() == 0) {
/* 453 */       u.setPassword(oldPassword);
/*     */     } else {
/* 455 */       u.setPassword(MD5.getDigestString(newPassword));
/*     */     } 
/* 457 */     u.setLoginName(loginName);
/* 458 */     if (Boolean.TRUE.equals(userForm.get("resetLoginFailedCount"))) {
/* 459 */       u.setLoginFailedCount(0);
/*     */     }
/* 461 */     if (EnabledDisabled.ENABLED.equals(oldEnabled) && EnabledDisabled.DISABLED.equals(u.getEnabled())) {
/* 462 */       FunctionManager functionManager = ServiceLocator.getFunctionManager(request);
/* 463 */       Function f = functionManager.getFunction("siteUserMaintainance");
/* 464 */       if (f != null) {
/* 465 */         EmailManager emailManager = ServiceLocator.getEmailManager(request);
/* 466 */         Map<Object, Object> context = new HashMap<Object, Object>();
/* 467 */         for (Iterator<User> itor = um.getEnabledUserList(f, s).iterator(); itor.hasNext(); ) {
/* 468 */           User admin = itor.next();
/* 469 */           context.put("user", admin);
/* 470 */           context.put("target", u);
/* 471 */           emailManager.insertEmail(s, admin.getEmail(), "UserDisabled.vm", context);
/*     */         } 
/*     */       } 
/*     */     } 
/* 475 */     request.setAttribute("X_OBJECT", um.saveUser(u));
/* 476 */     request.setAttribute("X_ROWPAGE", "user/row.jsp");
/*     */     
/* 478 */     return mapping.findForward("success");
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
/*     */   public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 491 */     GlobalManager gm = ServiceLocator.getGlobalManager(request);
/* 492 */     GlobalParameter gp = gm.getParameter();
/* 493 */     request.setAttribute("x_minPwdLen", gp.getMinPasswordLength());
/* 494 */     if (!request.getMethod().equals("POST") || isBack(request)) return mapping.findForward("page");
/*     */     
/* 496 */     Integer userId = getCurrentUser(request).getId();
/* 497 */     UserManager um = ServiceLocator.getUserManager(request);
/* 498 */     User u = um.getUser(userId);
/* 499 */     if (u == null) throw new ActionException("user.notFound", userId); 
/* 500 */     if (!u.getPassword().equalsIgnoreCase(MD5.getDigestString(request.getParameter("oldPwd")))) throw new BackToInputActionException("user.changePassword.passwordIncorrect"); 
/* 501 */     u.setPassword(MD5.getDigestString(request.getParameter("newPwd")));
/* 502 */     um.saveUser(u);
/* 503 */     return mapping.findForward("success");
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
/*     */   public ActionForward switchLocale(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 516 */     User currentUser = getCurrentUser(request);
/* 517 */     String locale = currentUser.getLocale();
/* 518 */     if ("en".equals(locale)) {
/* 519 */       locale = "zh";
/*     */     } else {
/* 521 */       locale = "en";
/*     */     } 
/* 523 */     UserManager um = ServiceLocator.getUserManager(request);
/* 524 */     User u = um.getUser(currentUser.getId());
/* 525 */     u.setLocale(locale);
/* 526 */     um.saveUser(u);
/* 527 */     currentUser.setLocale(locale);
/* 528 */     setLocale(request, new Locale(locale));
/* 529 */     return mapping.findForward("page");
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
/*     */   public ActionForward checkLoginName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 542 */     if ("POST".equals(request.getMethod())) {
/* 543 */       String loginName = request.getParameter("loginName");
/* 544 */       UserManager um = ServiceLocator.getUserManager(request);
/* 545 */       request.setAttribute("X_LOGINNAME", loginName);
/* 546 */       request.setAttribute("X_USEREXISTED", Boolean.valueOf((um.getUserByLoginName(loginName) != null)));
/*     */     } 
/* 548 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/UserAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */