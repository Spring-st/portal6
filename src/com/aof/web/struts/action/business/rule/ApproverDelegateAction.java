/*     */ package com.aof.web.struts.action.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.business.rule.ApproverDelegate;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryCondition;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
/*     */ import com.aof.model.business.rule.query.ApproverQueryCondition;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.service.business.rule.ApproverDelegateManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.business.rule.ApproverDelegateQueryForm;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.BeanQueryForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ public class ApproverDelegateAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward listApprover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  78 */     BeanQueryForm queryForm = (BeanQueryForm)form;
/*  79 */     ApproverQueryCondition cond = (ApproverQueryCondition)queryForm.newBean();
/*     */     
/*  81 */     List<Site> siteList = getAndCheckGrantedSiteDeparmentList(request);
/*     */     
/*  83 */     if (cond.getRuleType() == null) {
/*     */       
/*  85 */       cond.setRuleType(RuleType.CAPEX_APPROVAL_RULES);
/*  86 */       if (!isGlobal(request)) {
/*     */         
/*  88 */         Site firstSite = siteList.get(0);
/*  89 */         cond.setSiteId(firstSite.getId());
/*  90 */         Department firstDepartment = firstSite.getDepartments().get(0);
/*  91 */         cond.setDepartmentId(firstDepartment.getId());
/*     */       } 
/*     */     } 
/*  94 */     queryForm.populateToForm(cond);
/*     */     
/*  96 */     Map conds = constructApproverQueryMap(cond, request);
/*     */ 
/*     */     
/*  99 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 100 */       queryForm.setOrder(UserQueryOrder.NAME.getName());
/* 101 */       queryForm.setDescend(false);
/*     */     
/*     */     }
/* 104 */     else if (UserQueryOrder.getEnum(queryForm.getOrder()) == null) {
/* 105 */       throw new RuntimeException("order not found");
/*     */     } 
/*     */     
/* 108 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 110 */     if (queryForm.isFirstInit())
/* 111 */     { Integer pageSize = ActionUtils2.parseInt(queryForm.getPageSize());
/* 112 */       if (pageSize != null) {
/* 113 */         queryForm.init(um.getUserListCount(conds), pageSize.intValue());
/*     */       } else {
/* 115 */         queryForm.init(um.getUserListCount(conds));
/*     */       }  }
/* 117 */     else { queryForm.init(); }
/*     */ 
/*     */     
/* 120 */     List result = um.getUserList(conds, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm
/* 121 */         .isDescend());
/*     */     
/* 123 */     request.setAttribute("X_RESULTLIST", result);
/* 124 */     request.setAttribute("x_siteList", siteList);
/*     */     
/* 126 */     putRuleTypeListToRequest(request);
/*     */     
/* 128 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map constructApproverQueryMap(ApproverQueryCondition cond, HttpServletRequest request) {
/* 133 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 134 */     conds.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 135 */     if (cond.getSiteId() != null)
/* 136 */       conds.put(UserQueryCondition.SITE_ID_EQ, cond.getSiteId()); 
/* 137 */     if (cond.getDepartmentId() != null) {
/* 138 */       conds.put(UserQueryCondition.DEPARTMENT_ID_EQ, cond.getDepartmentId());
/*     */     }
/* 140 */     if (cond.getName() != null) {
/* 141 */       conds.put(UserQueryCondition.NAME_LIKE, cond.getName());
/*     */     }
/* 143 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 144 */     Function function = fm.getFunction(cond.getRuleType().getPrefixUrl());
/* 145 */     conds.put(UserQueryCondition.FUNCTION_ID_EQ, function);
/* 146 */     return conds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void putRuleTypeListToRequest(HttpServletRequest request) {
/* 155 */     List<RuleType> l = new ArrayList();
/* 156 */     l.add(RuleType.CAPEX_APPROVAL_RULES);
/* 157 */     l.add(RuleType.PR_APPROVAL_RULES);
/* 158 */     l.add(RuleType.PO_APPROVAL_RULES);
/* 159 */     l.add(RuleType.EXPENSE_APPROVAL_RULES);
/* 160 */     l.add(RuleType.TRAVEL_APPROVAL_RULES);
/* 161 */     request.setAttribute("x_ruleTypeList", l);
/*     */   }
/*     */   
/*     */   private boolean isSelf(HttpServletRequest request) {
/* 165 */     return isGlobal(request);
/*     */   }
/*     */   
/*     */   private RuleType getRuleTypeFromRequest(HttpServletRequest request) {
/* 169 */     Integer ruleTypeId = ActionUtils2.parseInt(request.getParameter("ruleType"));
/* 170 */     if (ruleTypeId == null) {
/* 171 */       throw new ActionException("approverDelegate.ruleType.notSet");
/*     */     }
/* 173 */     RuleType ruleType = (RuleType)RuleType.fromEnumCode(RuleType.class, ruleTypeId);
/* 174 */     if (ruleType == null)
/* 175 */       throw new ActionException("approverDelegate.ruleType.error"); 
/* 176 */     return ruleType;
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 190 */     processSelfPostfix(request);
/*     */     
/* 192 */     ApproverDelegateManager fm = ServiceLocator.getApproverDelegateManager(request);
/*     */     
/* 194 */     ApproverDelegateQueryForm queryForm = (ApproverDelegateQueryForm)form;
/* 195 */     User originalApprover = null;
/*     */     
/* 197 */     if (isSelf(request)) {
/*     */       
/* 199 */       queryForm.setOriginalApprover_id(getCurrentUser(request).getId().toString());
/* 200 */       originalApprover = getCurrentUser(request);
/* 201 */       List<ApproverDelegateType> typeList = ApproverDelegateType.getEnumList(ApproverDelegateType.class);
/* 202 */       request.setAttribute("x_typeList", typeList);
/* 203 */       if (StringUtils.isEmpty(queryForm.getType()))
/*     */       {
/* 205 */         ApproverDelegateType firstType = typeList.get(0);
/* 206 */         queryForm.setType(firstType.getEnumCode().toString());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 211 */       ApproverDelegateType type = null;
/* 212 */       if (queryForm.getType() == null) {
/* 213 */         RuleType ruleType = getRuleTypeFromRequest(request);
/* 214 */         type = ApproverDelegateType.getApproverDelegateTypeByRuleType(ruleType);
/* 215 */         if (type == null)
/* 216 */           throw new ActionException("approverDelegate.ruleType.notApprove"); 
/* 217 */         queryForm.setType(type.getEnumCode().toString());
/*     */       } else {
/* 219 */         Integer typeId = ActionUtils2.parseInt(queryForm.getType());
/* 220 */         type = (ApproverDelegateType)ApproverDelegateType.fromEnumCode(ApproverDelegateType.class, typeId);
/* 221 */         if (type == null) {
/* 222 */           throw new RuntimeException("type error");
/*     */         }
/*     */       } 
/*     */       
/* 226 */       originalApprover = getAndCheckOriginalApproverFromRequest(request);
/* 227 */       request.setAttribute("x_type", type);
/*     */     } 
/*     */     
/* 230 */     request.setAttribute("x_originalApprover", originalApprover);
/*     */     
/* 232 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 233 */       queryForm.setOrder(ApproverDelegateQueryOrder.DELEGATEAPPROVER_NAME.getName());
/* 234 */       queryForm.setDescend(false);
/* 235 */       queryForm.setFromDate2(ActionUtils2.getTodayAsDisplayDate());
/* 236 */       queryForm.setToDate1(ActionUtils2.getTodayAsDisplayDate());
/* 237 */     } else if (ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()) == null) {
/* 238 */       throw new RuntimeException("order error");
/*     */     } 
/*     */     
/* 241 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 243 */     String exportType = queryForm.getExportType();
/* 244 */     if (StringUtils.isNotEmpty(exportType)) {
/* 245 */       List data = fm.getApproverDelegateList(conditions, 0, -1, ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 247 */       int index = SessionTempFile.createNewTempFile(request);
/* 248 */       String fileName = "approverDelegate";
/* 249 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 252 */               MessageResources messages = ApproverDelegateAction.this.getResources(request);
/* 253 */               row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.delegateApprover.id"));
/* 254 */               row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.fromDate"));
/* 255 */               row.add(messages.getMessage(ApproverDelegateAction.this.getLocale(request), "approverDelegate.toDate"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 259 */               row.add(BeanHelper.getBeanPropertyValue(data, "delegateApprover.name"));
/* 260 */               ApproverDelegate ad = (ApproverDelegate)data;
/* 261 */               row.add(getDateString(ad.getFromDate()));
/* 262 */               row.add(getDateString(ad.getToDate()));
/*     */             }
/*     */             
/*     */             private String getDateString(Date date) {
/* 266 */               if (date == null) {
/* 267 */                 return "";
/*     */               }
/* 269 */               return ActionUtils2.getDisplayDateFromDate(date);
/*     */             }
/*     */           });
/*     */       
/* 273 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 276 */     if (queryForm.isFirstInit()) {
/* 277 */       queryForm.init(fm.getApproverDelegateListCount(conditions));
/*     */     } else {
/* 279 */       queryForm.init();
/*     */     } 
/*     */     
/* 282 */     List result = fm.getApproverDelegateList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 283 */         ApproverDelegateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */     
/* 285 */     request.setAttribute("X_RESULTLIST", result);
/* 286 */     return mapping.findForward("page");
/*     */   }
/*     */
/*     */   private Map constructQueryMap(ApproverDelegateQueryForm queryForm) {
/* 296 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 298 */     Integer id = ActionUtils2.parseInt(queryForm.getId());
/* 299 */     if (id != null) {
/* 300 */       conditions.put(ApproverDelegateQueryCondition.ID_EQ, id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     Integer originalApprover_id = ActionUtils2.parseInt(queryForm.getOriginalApprover_id());
/* 309 */     if (originalApprover_id != null) {
/* 310 */       conditions.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, originalApprover_id);
/*     */     }
/* 312 */     Integer delegateApprover_id = ActionUtils2.parseInt(queryForm.getDelegateApprover_id());
/* 313 */     if (delegateApprover_id != null) {
/* 314 */       conditions.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, delegateApprover_id);
/*     */     }
/*     */ 
/*     */     
/* 318 */     String type = queryForm.getType();
/* 319 */     if (type != null && type.trim().length() != 0) {
/* 320 */       conditions.put(ApproverDelegateQueryCondition.TYPE_EQ, type);
/*     */     }
/* 322 */     String fromDate = queryForm.getFromDate();
/* 323 */     if (fromDate != null && fromDate.trim().length() != 0) {
/* 324 */       conditions.put(ApproverDelegateQueryCondition.FROMDATE_EQ, fromDate);
/*     */     }
/* 326 */     String toDate = queryForm.getToDate();
/* 327 */     if (toDate != null && toDate.trim().length() != 0) {
/* 328 */       conditions.put(ApproverDelegateQueryCondition.TODATE_EQ, toDate);
/*     */     }
/*     */     
/* 331 */     if (StringUtils.isNotEmpty(queryForm.getFromDate1())) {
/*     */       
/* 333 */       Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getFromDate1());
/* 334 */       conditions.put(ApproverDelegateQueryCondition.FROMDATE_GE, d);
/*     */     } 
/*     */     
/* 337 */     if (StringUtils.isNotEmpty(queryForm.getFromDate2())) {
/*     */       
/* 339 */       Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getFromDate2());
/* 340 */       conditions.put(ApproverDelegateQueryCondition.FROMDATE_LT, getNextDate(d));
/*     */     } 
/*     */     
/* 343 */     if (StringUtils.isNotEmpty(queryForm.getToDate1())) {
/*     */       
/* 345 */       Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getToDate1());
/* 346 */       conditions.put(ApproverDelegateQueryCondition.TODATE_GE, d);
/*     */     } 
/*     */     
/* 349 */     if (StringUtils.isNotEmpty(queryForm.getToDate2())) {
/*     */       
/* 351 */       Date d = ActionUtils2.getDateFromDisplayDate(queryForm.getToDate2());
/* 352 */       conditions.put(ApproverDelegateQueryCondition.TODATE_LT, getNextDate(d));
/*     */     } 
/*     */     
/* 355 */     return conditions;
/*     */   }
/*     */   
/*     */   private Date getNextDate(Date d) {
/* 359 */     Calendar c = Calendar.getInstance();
/* 360 */     c.setTime(d);
/* 361 */     c.add(5, 1);
/* 362 */     return c.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ApproverDelegate getApproverDelegateFromRequest(HttpServletRequest request) throws Exception {
/* 373 */     Integer id = ActionUtils2.parseInt(request.getParameter("id"));
/* 374 */     ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
/* 375 */     ApproverDelegate approverDelegate = approverDelegateManager.getApproverDelegate(id);
/* 376 */     if (approverDelegate == null)
/* 377 */       throw new ActionException("approverDelegate.notFound", id); 
/* 378 */     return approverDelegate;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 392 */     processSelfPostfix(request);
/* 393 */     ApproverDelegate approverDelegate = getApproverDelegateFromRequest(request);
/* 394 */     request.setAttribute("x_ad", approverDelegate);
/* 395 */     String today = ActionUtils2.getTodayAsDisplayDate();
/* 396 */     boolean fromDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate()).compareTo(today) <= 0);
/*     */     
/* 398 */     boolean toDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate()).compareTo(today) <= 0);
/*     */     
/* 400 */     if (fromDateBeforeToday)
/* 401 */       request.setAttribute("x_fromBefore", Boolean.TRUE); 
/* 402 */     if (toDateBeforeToday) {
/* 403 */       request.setAttribute("x_toBefore", Boolean.TRUE);
/*     */     }
/* 405 */     if (!isBack(request)) {
/*     */       
/* 407 */       if (isGlobal(request)) {
/*     */         
/* 409 */         if (!approverDelegate.getOriginalApprover().equals(getCurrentUser(request)))
/* 410 */           throw new ActionException("approverDelegate.originalApprover.notSelf"); 
/*     */       } else {
/* 412 */         checkUser(approverDelegate.getOriginalApprover(), request);
/* 413 */         checkUser(approverDelegate.getDelegateApprover(), request);
/*     */       } 
/* 415 */       BeanForm approverDelegateForm = (BeanForm)getForm("/updateApproverDelegate", request);
/* 416 */       approverDelegateForm.populate(approverDelegate, "to_form");
/*     */     } 
/*     */     
/* 419 */     request.setAttribute("x_today", today);
/* 420 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private User getAndCheckOriginalApproverFromRequest(HttpServletRequest request) throws Exception {
/* 431 */     User user = getOriginalApproverFromRequest(request);
/* 432 */     if (!isGlobal(request))
/* 433 */       checkUser(user, request); 
/* 434 */     return user;
/*     */   }
/*     */   
/*     */   private void processSelfPostfix(HttpServletRequest request) {
/* 438 */     if (isGlobal(request)) {
/* 439 */       request.setAttribute("x_postfix", "_self");
/*     */     }
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
/* 453 */     processSelfPostfix(request);
/*     */     
/* 455 */     if (!isBack(request)) {
/* 456 */       User originalApprover = null;
/* 457 */       if (isGlobal(request)) {
/* 458 */         originalApprover = getCurrentUser(request);
/*     */       } else {
/*     */         
/* 461 */         originalApprover = getAndCheckOriginalApproverFromRequest(request);
/* 462 */         checkUser(originalApprover, request);
/*     */       } 
/*     */       
/* 465 */       ApproverDelegateType type = getApproverDelegateTypeFromRequest(request);
/*     */       
/* 467 */       ApproverDelegate approverDelegate = new ApproverDelegate();
/* 468 */       approverDelegate.setType(type);
/* 469 */       approverDelegate.setOriginalApprover(originalApprover);
/*     */       
/* 471 */       BeanForm approverDelegateForm = (BeanForm)getForm("/insertApproverDelegate", request);
/* 472 */       approverDelegateForm.populate(approverDelegate, "to_form");
/*     */     } 
/* 474 */     request.setAttribute("x_today", ActionUtils2.getTodayAsDisplayDate());
/* 475 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ApproverDelegateType getApproverDelegateTypeFromRequest(HttpServletRequest request) {
/* 485 */     Integer id = ActionUtils2.parseInt(request.getParameter("type"));
/* 486 */     if (id == null)
/* 487 */       throw new ActionException("approverDelegate.type.notSet"); 
/* 488 */     ApproverDelegateType type = (ApproverDelegateType)ApproverDelegateType.fromEnumCode(ApproverDelegateType.class, id);
/* 489 */     if (type == null)
/* 490 */       throw new ActionException("approverDelegate.type.notFound", id); 
/* 491 */     return type;
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 505 */     User oUser = getOriginalApproverFromRequest(request);
/* 506 */     User dUser = getDelegateApproverFromRequest(request);
/* 507 */     if (isGlobal(request)) {
/* 508 */       if (!oUser.equals(getCurrentUser(request)))
/* 509 */         throw new ActionException("approverDelegate.originalApprover.notSelf"); 
/*     */     } else {
/* 511 */       checkUser(oUser, request);
/* 512 */       checkUser(dUser, request);
/*     */     } 
/*     */     
/* 515 */     if (oUser.equals(dUser)) {
/* 516 */       throw new ActionException("approverDelegate.select.notSelf");
/*     */     }
/*     */     
/* 519 */     ApproverDelegate approverDelegate = getApproverDelegateFromRequest(request);
/*     */     
/* 521 */     String today = ActionUtils2.getTodayAsDisplayDate();
/* 522 */     boolean oldFromDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate()).compareTo(today) <= 0);
/*     */     
/* 524 */     boolean oldToDateBeforeToday = (ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate()).compareTo(today) <= 0);
/*     */     
/* 526 */     if (oldFromDateBeforeToday && oldToDateBeforeToday) {
/* 527 */       throw new RuntimeException("can't edit old");
/*     */     }
/*     */     
/* 530 */     Date oldFromDate = approverDelegate.getFromDate();
/* 531 */     Date oldToDate = approverDelegate.getToDate();
/* 532 */     User oldDelegateApprover = approverDelegate.getDelegateApprover();
/*     */     
/* 534 */     BeanForm approverDelegateForm = (BeanForm)form;
/*     */     
/* 536 */     approverDelegateForm.populate(approverDelegate, "to_bean");
/*     */     
/* 538 */     approverDelegate.setOriginalApprover(oUser);
/* 539 */     approverDelegate.setDelegateApprover(dUser);
/*     */     
/* 541 */     String sFromDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate());
/* 542 */     String sToDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate());
/*     */     
/* 544 */     if (sFromDate.compareTo(sToDate) > 0) {
/* 545 */       throw new ActionException("approverDelegate.fromDateAfterToDate");
/*     */     }
/* 547 */     if (!oldFromDateBeforeToday && 
/* 548 */       sFromDate.compareTo(today) <= 0) {
/* 549 */       throw new ActionException("approverDelegate.fromDate.notAfterToday");
/*     */     }
/* 551 */     if (!oldToDateBeforeToday && 
/* 552 */       sToDate.compareTo(today) <= 0) {
/* 553 */       throw new ActionException("approverDelegate.toDate.notAfterToday");
/*     */     }
/* 555 */     if (oldFromDateBeforeToday) {
/* 556 */       approverDelegate.setFromDate(oldFromDate);
/* 557 */       approverDelegate.setDelegateApprover(oldDelegateApprover);
/*     */     } 
/*     */     
/* 560 */     if (oldToDateBeforeToday) {
/* 561 */       approverDelegate.setToDate(oldToDate);
/*     */     }
/* 563 */     ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
/*     */     
/* 565 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 566 */     conds.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, dUser.getId());
/* 567 */     conds.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, oUser.getId());
/* 568 */     conds.put(ApproverDelegateQueryCondition.TYPE_EQ, approverDelegate.getType());
/*     */     
/* 570 */     List dList = approverDelegateManager.getApproverDelegateList(conds, 0, -1, ApproverDelegateQueryOrder.FROMDATE, false);
/* 571 */     for (Iterator<ApproverDelegate> iter = dList.iterator(); iter.hasNext(); ) {
/* 572 */       ApproverDelegate ad = iter.next();
/* 573 */       if (ad.equals(approverDelegate))
/*     */         continue; 
/* 575 */       String oldSFromDate = ActionUtils2.getDisplayDateFromDate(ad.getFromDate());
/* 576 */       String oldSToDate = ActionUtils2.getDisplayDateFromDate(ad.getToDate());
/* 577 */       if (sToDate.compareTo(oldSFromDate) < 0)
/*     */         continue; 
/* 579 */       if (sFromDate.compareTo(oldSToDate) > 0)
/*     */         continue; 
/* 581 */       throw new ActionException("approverDelegate.date.overlap");
/*     */     } 
/*     */ 
/*     */     
/* 585 */     approverDelegateManager.updateApproverDelegate(approverDelegate);
/* 586 */     request.setAttribute("X_OBJECT", approverDelegateManager.getApproverDelegate(approverDelegate.getId()));
/* 587 */     request.setAttribute("X_ROWPAGE", "approverDelegate/row.jsp");
/*     */ 
/*     */     
/* 590 */     EmailManager em = ServiceLocator.getEmailManager(request);
/* 591 */     Map<Object, Object> context = new HashMap<Object, Object>();
/* 592 */     context.put("x_ad", approverDelegate);
/* 593 */     em.insertEmail(approverDelegate.getDelegateApprover().getPrimarySite(), approverDelegate.getDelegateApprover().getEmail(), 
/* 594 */         "Delegate.vm", context);
/*     */     
/* 596 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private User getOriginalApproverFromRequest(HttpServletRequest request) throws Exception {
/* 600 */     Integer id = ActionUtils2.parseInt(request.getParameter("originalApprover_id"));
/* 601 */     if (id == null)
/* 602 */       throw new ActionException("approverDelegate.delegateApprover.notSet"); 
/* 603 */     UserManager um = ServiceLocator.getUserManager(request);
/* 604 */     User user = um.getUser(id);
/* 605 */     if (user == null)
/* 606 */       throw new ActionException("approverDelegate.delegateApprover.notFound"); 
/* 607 */     return user;
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 621 */     User originalUser = null;
/* 622 */     User delegateApprover = null;
/* 623 */     if (isGlobal(request)) {
/*     */       
/* 625 */       originalUser = getCurrentUser(request);
/* 626 */       delegateApprover = getDelegateApproverFromRequest(request);
/*     */     } else {
/* 628 */       originalUser = getAndCheckOriginalApproverFromRequest(request);
/* 629 */       delegateApprover = getAndCheckDelegateApproverFromRequest(request);
/*     */     } 
/*     */     
/* 632 */     if (originalUser.equals(delegateApprover)) {
/* 633 */       throw new ActionException("approverDelegate.select.notSelf");
/*     */     }
/*     */     
/* 636 */     BeanForm approverDelegateForm = (BeanForm)form;
/* 637 */     ApproverDelegate approverDelegate = new ApproverDelegate();
/*     */     
/* 639 */     approverDelegateForm.populate(approverDelegate, "to_bean");
/* 640 */     approverDelegate.setOriginalApprover(originalUser);
/* 641 */     approverDelegate.setDelegateApprover(delegateApprover);
/*     */     
/* 643 */     String today = ActionUtils2.getTodayAsDisplayDate();
/* 644 */     String sFromDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getFromDate());
/* 645 */     String sToDate = ActionUtils2.getDisplayDateFromDate(approverDelegate.getToDate());
/* 646 */     if (sFromDate.compareTo(sToDate) > 0) {
/* 647 */       throw new ActionException("approverDelegate.fromDateAfterToDate");
/*     */     }
/* 649 */     if (sFromDate.compareTo(today) <= 0) {
/* 650 */       throw new ActionException("approverDelegate.fromDate.notAfterToday");
/*     */     }
/* 652 */     if (sToDate.compareTo(today) <= 0) {
/* 653 */       throw new ActionException("approverDelegate.toDate.notAfterToday");
/*     */     }
/* 655 */     ApproverDelegateManager approverDelegateManager = ServiceLocator.getApproverDelegateManager(request);
/* 656 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 657 */     conds.put(ApproverDelegateQueryCondition.DELEGATEAPPROVER_ID_EQ, delegateApprover.getId());
/* 658 */     conds.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, originalUser.getId());
/* 659 */     conds.put(ApproverDelegateQueryCondition.TYPE_EQ, approverDelegate.getType());
/*     */     
/* 661 */     List dList = approverDelegateManager.getApproverDelegateList(conds, 0, -1, ApproverDelegateQueryOrder.FROMDATE, false);
/* 662 */     for (Iterator<ApproverDelegate> iter = dList.iterator(); iter.hasNext(); ) {
/* 663 */       ApproverDelegate ad = iter.next();
/* 664 */       String oldSFromDate = ActionUtils2.getDisplayDateFromDate(ad.getFromDate());
/* 665 */       String oldSToDate = ActionUtils2.getDisplayDateFromDate(ad.getToDate());
/* 666 */       if (sToDate.compareTo(oldSFromDate) < 0)
/*     */         continue; 
/* 668 */       if (sFromDate.compareTo(oldSToDate) > 0)
/*     */         continue; 
/* 670 */       throw new ActionException("approverDelegate.date.overlap");
/*     */     } 
/*     */ 
/*     */     
/* 674 */     request.setAttribute("X_OBJECT", approverDelegateManager.insertApproverDelegate(approverDelegate));
/* 675 */     request.setAttribute("X_ROWPAGE", "approverDelegate/row.jsp");
/*     */     
/* 677 */     EmailManager em = ServiceLocator.getEmailManager(request);
/* 678 */     Map<Object, Object> context = new HashMap<Object, Object>();
/* 679 */     context.put("x_ad", approverDelegate);
/* 680 */     em.insertEmail(approverDelegate.getDelegateApprover().getPrimarySite(), approverDelegate.getDelegateApprover().getEmail(), 
/* 681 */         "Delegate.vm", context);
/*     */     
/* 683 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private User getDelegateApproverFromRequest(HttpServletRequest request) throws Exception {
/* 687 */     Integer id = ActionUtils2.parseInt(request.getParameter("delegateApprover_id"));
/* 688 */     if (id == null)
/* 689 */       throw new ActionException("approverDelegate.delegateApprover.notSet"); 
/* 690 */     UserManager um = ServiceLocator.getUserManager(request);
/* 691 */     User user = um.getUser(id);
/* 692 */     if (user == null)
/* 693 */       throw new ActionException("approverDelegate.delegateApprover.notFound"); 
/* 694 */     return user;
/*     */   }
/*     */   
/*     */   private User getAndCheckDelegateApproverFromRequest(HttpServletRequest request) throws Exception {
/* 698 */     User user = getDelegateApproverFromRequest(request);
/* 699 */     if (!isGlobal(request))
/* 700 */       checkUser(user, request); 
/* 701 */     return user;
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
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 715 */     processSelfPostfix(request);
/* 716 */     User originalApprover = getAndCheckOriginalApproverFromRequest(request);
/* 717 */     request.setAttribute("x_originalApprover", originalApprover);
/*     */     
/* 719 */     BeanQueryForm queryForm = (BeanQueryForm)form;
/* 720 */     ApproverQueryCondition cond = (ApproverQueryCondition)queryForm.newBean();
/* 721 */     if (cond.getRuleType() == null) {
/* 722 */       ApproverDelegateType type = getApproverDelegateTypeFromRequest(request);
/* 723 */       RuleType ruleType = ApproverDelegateType.getRuleTypeByApproverDelegateType(type);
/* 724 */       cond.setRuleType(ruleType);
/*     */     } 
/*     */     
/* 727 */     ApproverDelegateType adt = ApproverDelegateType.getApproverDelegateTypeByRuleType(cond.getRuleType());
/* 728 */     if (adt == null)
/* 729 */       throw new ActionException("approverDelegate.rultType.notApprove"); 
/* 730 */     queryForm.populateToForm(cond);
/* 731 */     queryForm.setPageSize("5");
/*     */     
/* 733 */     return listApprover(mapping, form, request, response);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/rule/ApproverDelegateAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */