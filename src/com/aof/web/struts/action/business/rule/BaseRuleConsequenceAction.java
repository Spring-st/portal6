/*     */ package com.aof.web.struts.action.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.RuleConsequence;
/*     */ import com.aof.model.metadata.ConsequenceType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.UserQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
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
/*     */ public class BaseRuleConsequenceAction
/*     */   extends BaseAction2
/*     */ {
/*     */   protected ActionForward newObject(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/*  47 */     ConsequenceType ct = type.getConsequenceType();
/*  48 */     if (ConsequenceType.ACCEPTABLE.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl());
/*     */     
/*  50 */     RuleConsequence rc = prepareAndCheckRuleConsequence(type, request);
/*  51 */     BeanForm ruleConsequenceForm = (BeanForm)getForm("/insert" + type.getPrefixUrl() + "RuleConsequence", request);
/*  52 */     if (!isBack(request)) {
/*  53 */       ruleConsequenceForm.populateToForm(rc);
/*     */     }
/*     */     
/*  56 */     prepareData(rc, request);
/*  57 */     request.setAttribute("X_RULECONSEQUENCE", rc);
/*  58 */     return mapping.findForward("pageRuleConsequenceNew" + type.getConsequenceType().getPrefixUrl());
/*     */   }
/*     */   
/*     */   protected ActionForward insert(ActionMapping mapping, BeanForm ruleConsequenceForm, HttpServletRequest request, RuleType type) throws Exception {
/*  62 */     ConsequenceType ct = type.getConsequenceType();
/*  63 */     if (ConsequenceType.ACCEPTABLE.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl());
/*     */     
/*  65 */     RuleConsequence rc = getRuleConsequence(request);
/*  66 */     if (rc != null) throw new BackToInputActionException("ruleConsequence.user.duplicate"); 
/*  67 */     if (ConsequenceType.PURCHASER.equals(ct) || ConsequenceType.NOTIFIER.equals(ct)) {
/*  68 */       ruleConsequenceForm.set("seq", "0");
/*  69 */       ruleConsequenceForm.set("canModify", YesNo.NO.getEnumCode().toString());
/*     */     } 
/*  71 */     rc = prepareAndCheckRuleConsequence(type, request);
/*  72 */     updateAndCheckRuleConsequence(rc, request, ruleConsequenceForm, type);
/*  73 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/*  75 */     rc = rm.saveRuleConsequence(rc);
/*  76 */     if (ConsequenceType.APPROVER.equals(ct)) {
/*  77 */       Rule r = rm.getRule(rc.getRule().getId(), true);
/*  78 */       request.setAttribute("X_OBJECTS", r.getConsequences());
/*  79 */       request.setAttribute("X_ROWPAGE", "ruleConsequence/" + lowerFirstChar(ct.getPrefixUrl()) + "Rows.jsp");
/*     */     } else {
/*  81 */       request.setAttribute("X_OBJECT", rc);
/*  82 */       request.setAttribute("X_ROWPAGE", "ruleConsequence/" + lowerFirstChar(ct.getPrefixUrl()) + "Row.jsp");
/*     */     } 
/*  84 */     return mapping.findForward("successRuleConsequenceNew");
/*     */   }
/*     */   
/*     */   protected ActionForward edit(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/*  88 */     ConsequenceType ct = type.getConsequenceType();
/*  89 */     if (ConsequenceType.ACCEPTABLE.equals(ct) || ConsequenceType.PURCHASER.equals(ct) || ConsequenceType.NOTIFIER.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl());
/*     */     
/*  91 */     RuleConsequence rc = getAndCheckRuleConsequence(type, request);
/*     */     
/*  93 */     BeanForm ruleConditionForm = (BeanForm)getForm("/update" + type.getPrefixUrl() + "RuleConsequence", request);
/*  94 */     if (!isBack(request)) {
/*  95 */       ruleConditionForm.populateToForm(rc);
/*     */     }
/*     */     
/*  98 */     prepareData(rc, request);
/*  99 */     request.setAttribute("X_RULECONSEQUENCE", rc);
/* 100 */     return mapping.findForward("pageRuleConsequenceEdit" + type.getConsequenceType().getPrefixUrl());
/*     */   }
/*     */   
/*     */   protected ActionForward update(ActionMapping mapping, BeanForm ruleConsequenceForm, HttpServletRequest request, RuleType type) throws Exception {
/* 104 */     ConsequenceType ct = type.getConsequenceType();
/* 105 */     if (ConsequenceType.ACCEPTABLE.equals(ct) || ConsequenceType.PURCHASER.equals(ct) || ConsequenceType.NOTIFIER.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl());
/*     */     
/* 107 */     RuleConsequence rc = getAndCheckRuleConsequence(type, request);
/* 108 */     updateAndCheckRuleConsequence(rc, request, ruleConsequenceForm, type);
/* 109 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 111 */     rc = rm.updateRuleConsequence(rc);
/* 112 */     if (ConsequenceType.APPROVER.equals(ct)) {
/* 113 */       Rule r = rm.getRule(rc.getRule().getId(), true);
/* 114 */       request.setAttribute("X_OBJECTS", r.getConsequences());
/* 115 */       request.setAttribute("X_ROWPAGE", "ruleConsequence/" + lowerFirstChar(ct.getPrefixUrl()) + "Rows.jsp");
/*     */     } else {
/* 117 */       request.setAttribute("X_OBJECT", rc);
/* 118 */       request.setAttribute("X_ROWPAGE", "ruleConsequence/" + lowerFirstChar(ct.getPrefixUrl()) + "Row.jsp");
/*     */     } 
/* 120 */     return mapping.findForward("successRuleConsequenceEdit");
/*     */   }
/*     */   
/*     */   protected ActionForward delete(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 124 */     ConsequenceType ct = type.getConsequenceType();
/* 125 */     if (ConsequenceType.ACCEPTABLE.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl());
/*     */     
/* 127 */     RuleConsequence rc = getAndCheckRuleConsequence(type, request);
/* 128 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 130 */     rm.removeRuleConsequence(rc);
/* 131 */     if (ConsequenceType.APPROVER.equals(ct)) {
/* 132 */       Rule r = rm.getRule(rc.getRule().getId(), true);
/* 133 */       request.setAttribute("X_OBJECTS", r.getConsequences());
/* 134 */       request.setAttribute("X_ROWPAGE", "ruleConsequence/" + lowerFirstChar(ct.getPrefixUrl()) + "Rows.jsp");
/*     */     } else {
/* 136 */       request.setAttribute("X_OBJECT", rc);
/*     */     } 
/* 138 */     return mapping.findForward("successRuleConsequenceDelete" + ct.getPrefixUrl());
/*     */   }
/*     */   
/*     */   protected ActionForward selectUser(ActionMapping mapping, UserQueryForm queryForm, HttpServletRequest request, RuleType type) throws Exception {
/* 142 */     ConsequenceType ct = type.getConsequenceType();
/* 143 */     if (ConsequenceType.ACCEPTABLE.equals(ct)) throw new ActionException("ruleConsequence.OperationNotSupported", type.getPrefixUrl()); 
/* 144 */     Function f = null;
/* 145 */     if (!ConsequenceType.NOTIFIER.equals(ct)) {
/* 146 */       f = ServiceLocator.getFunctionManager(request).getFunction(type.getPrefixUrl());
/* 147 */       if (f == null) throw new ActionException("ruleConsequence.functionNotFound", type.getPrefixUrl());
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     List<Site> grantedSiteList = getGrantedSiteDeparmentList(request);
/* 154 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*     */     
/* 156 */     if (hasGlobalPower(request)) {
/* 157 */       request.setAttribute("X_GLOBAL", Boolean.TRUE);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 162 */       Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 163 */       if (siteId == null) {
/* 164 */         siteId = ((Site)grantedSiteList.get(0)).getId();
/* 165 */         queryForm.setSiteId(siteId.toString());
/*     */       } else {
/* 167 */         checkSite(siteId, request);
/*     */       } 
/* 169 */       request.setAttribute("X_GLOBAL", Boolean.FALSE);
/*     */     } 
/*     */     
/* 172 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/* 174 */     Map conditions = constructQueryMap(queryForm);
/* 175 */     conditions.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/*     */     
/* 177 */     if (f != null) {
/* 178 */       conditions.put(UserQueryCondition.FUNCTION_ID_EQ, f.getId());
/*     */     }
/*     */     
/* 181 */     queryForm.setPageSize("10");
/* 182 */     if (queryForm.isFirstInit()) {
/* 183 */       queryForm.init(um.getUserListCount(conditions));
/*     */     } else {
/* 185 */       queryForm.init();
/*     */     } 
/*     */     
/* 188 */     request.setAttribute("X_RULETYPE", type);
/* 189 */     request.setAttribute("X_RESULTLIST", um.getUserList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 190 */     return mapping.findForward("pageRuleConsequenceSelectUser");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(UserQueryForm queryForm) {
/* 194 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 196 */     String loginName = queryForm.getLoginName();
/* 197 */     if (loginName != null) {
/* 198 */       loginName = loginName.trim();
/* 199 */       if (loginName.length() == 0) loginName = null; 
/*     */     } 
/* 201 */     if (loginName != null) conditions.put(UserQueryCondition.LOGINNAME_LIKE, loginName);
/*     */     
/* 203 */     String name = queryForm.getName();
/* 204 */     if (name != null) {
/* 205 */       name = name.trim();
/* 206 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 208 */     if (name != null) conditions.put(UserQueryCondition.NAME_LIKE, name);
/*     */     
/* 210 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 211 */     Integer departmentId = ActionUtils.parseInt(queryForm.getDepartmentId());
/* 212 */     if (departmentId != null) {
/* 213 */       conditions.put(UserQueryCondition.DEPARTMENT_ID_EQ, departmentId);
/* 214 */     } else if (siteId != null) {
/* 215 */       conditions.put(UserQueryCondition.SITE_ID_EQ, siteId);
/*     */     } 
/*     */     
/* 218 */     return conditions;
/*     */   }
/*     */   
/*     */   private String lowerFirstChar(String value) {
/* 222 */     return String.valueOf(Character.toLowerCase(value.charAt(0))) + value.substring(1);
/*     */   }
/*     */   
/*     */   private RuleConsequence getRuleConsequence(HttpServletRequest request) throws Exception {
/* 226 */     Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/* 227 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 228 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 229 */     return rm.getRuleConsequence(ruleId, userId);
/*     */   }
/*     */   
/*     */   private RuleConsequence prepareAndCheckRuleConsequence(RuleType type, HttpServletRequest request) throws Exception {
/* 233 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 234 */     Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/* 235 */     Rule r = rm.getRule(ruleId);
/* 236 */     if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", ruleId); 
/* 237 */     checkSite(r.getSite(), request);
/*     */     
/* 239 */     RuleConsequence rc = new RuleConsequence();
/* 240 */     rc.setRule(r);
/* 241 */     return rc;
/*     */   }
/*     */   
/*     */   private RuleConsequence getAndCheckRuleConsequence(RuleType type, HttpServletRequest request) throws Exception {
/* 245 */     Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/* 246 */     Integer userId = ActionUtils.parseInt(request.getParameter("user_id"));
/* 247 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 248 */     RuleConsequence rc = rm.getRuleConsequence(ruleId, userId);
/* 249 */     if (rc == null) throw new ActionException("ruleConsequence.notFound", new Object[] { ruleId, userId }); 
/* 250 */     Rule r = rc.getRule();
/* 251 */     if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", ruleId); 
/* 252 */     checkSite(r.getSite(), request);
/* 253 */     return rc;
/*     */   }
/*     */   
/*     */   private void updateAndCheckRuleConsequence(RuleConsequence rc, HttpServletRequest request, BeanForm ruleConsequenceForm, RuleType rt) {
/* 257 */     ruleConsequenceForm.populateToBean(rc);
/*     */   }
/*     */   
/*     */   private void prepareData(RuleConsequence rc, HttpServletRequest request) throws Exception {
/* 261 */     Rule r = rc.getRule();
/* 262 */     ConsequenceType ct = r.getType().getConsequenceType();
/* 263 */     if (ConsequenceType.APPROVER.equals(ct)) {
/* 264 */       request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 265 */       request.setAttribute("X_MAXSEQ", ServiceLocator.getRuleManager(request).getMaxConsequenceSeqNoForRuleId(r.getId()));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/rule/BaseRuleConsequenceAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */