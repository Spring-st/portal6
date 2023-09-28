/*     */ package com.aof.web.struts.action.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.RuleCondition;
/*     */ import com.aof.model.metadata.BudgetType;
/*     */ import com.aof.model.metadata.ConditionCompareType;
/*     */ import com.aof.model.metadata.ConditionType;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.model.metadata.TravelType;
/*     */ import com.aof.model.metadata.TravellingMode;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class BaseRuleConditionAction
/*     */   extends BaseAction2
/*     */ {
/*     */   protected ActionForward list(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, RuleType type) throws Exception {
/*  50 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*  51 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/*  52 */     Rule r = rm.getRule(id, true);
/*  53 */     if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", id); 
/*  54 */     checkSite(r.getSite(), request);
/*  55 */     request.setAttribute("X_RULE", r);
/*  56 */     for (Iterator<RuleCondition> itor = r.getConditions().iterator(); itor.hasNext(); ) {
/*  57 */       RuleCondition rc = itor.next();
/*  58 */       rc.setDisplayValue(getDisplayValue(rc.getType(), rc.getValue(), request));
/*     */     } 
/*  60 */     return mapping.findForward("pageRuleConditionList");
/*     */   }
/*     */   
/*     */   protected ActionForward newObject(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/*  64 */     Integer typeEnumCode = ActionUtils.parseInt(request.getParameter("type"));
/*  65 */     if (typeEnumCode == null) {
/*  66 */       RuleManager rm = ServiceLocator.getRuleManager(request);
/*  67 */       Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/*  68 */       Rule r = rm.getRule(ruleId, true);
/*  69 */       if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", ruleId); 
/*  70 */       checkSite(r.getSite(), request);
/*  71 */       Set<ConditionType> usedConditions = new HashSet();
/*  72 */       for (Iterator<RuleCondition> itor = r.getConditions().iterator(); itor.hasNext();) {
/*  73 */         usedConditions.add(((RuleCondition)itor.next()).getType());
/*     */       }
/*  75 */       Set availiableConditions = new HashSet();
/*  76 */       availiableConditions.addAll(type.getSupportedConditions());
/*  77 */       availiableConditions.removeAll(usedConditions);
/*  78 */       if (availiableConditions.isEmpty()) throw new ActionException("ruleCondition.noAvailiableCondition"); 
/*  79 */       request.setAttribute("X_RULETYPE", type);
/*  80 */       request.setAttribute("X_AVAILIABLECONDITIONS", availiableConditions);
/*  81 */       return mapping.findForward("pageRuleConditionChooseType");
/*     */     } 
/*     */     
/*  84 */     RuleCondition rc = prepareNewRuleCondition(type, typeEnumCode, request);
/*  85 */     BeanForm ruleConditionForm = (BeanForm)getForm("/insert" + type.getPrefixUrl() + "RuleCondition", request);
/*  86 */     if (!isBack(request)) {
/*  87 */       ruleConditionForm.populateToForm(rc);
/*     */     }
/*     */     
/*  90 */     request.setAttribute("X_RULECONDITION", rc);
/*  91 */     prepareData(rc, ruleConditionForm, request);
/*  92 */     return mapping.findForward("pageRuleConditionNew" + rc.getType().getTypeString());
/*     */   }
/*     */   
/*     */   protected ActionForward insert(ActionMapping mapping, BeanForm ruleConditionForm, HttpServletRequest request, RuleType type) throws Exception {
/*  96 */     Integer typeEnumCode = ActionUtils.parseInt(request.getParameter("type"));
/*  97 */     RuleCondition rc = prepareNewRuleCondition(type, typeEnumCode, request);
/*  98 */     updateAndCheckRuleCondition(rc, request, ruleConditionForm, type);
/*  99 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 101 */     rc = rm.saveRuleCondition(rc);
/* 102 */     rc.setDisplayValue(getDisplayValue(rc.getType(), rc.getValue(), request));
/* 103 */     request.setAttribute("X_OBJECT", rc);
/* 104 */     request.setAttribute("X_ROWPAGE", "ruleCondition/conditionRow.jsp");
/* 105 */     return mapping.findForward("successRuleConditionNew");
/*     */   }
/*     */   
/*     */   protected ActionForward edit(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 109 */     RuleCondition rc = getAndCheckRuleCondition(type, request);
/*     */     
/* 111 */     BeanForm ruleConditionForm = (BeanForm)getForm("/update" + type.getPrefixUrl() + "RuleCondition", request);
/* 112 */     if (!isBack(request)) {
/* 113 */       ruleConditionForm.populateToForm(rc);
/*     */     }
/*     */     
/* 116 */     request.setAttribute("X_RULECONDITION", rc);
/* 117 */     prepareData(rc, ruleConditionForm, request);
/* 118 */     return mapping.findForward("pageRuleConditionEdit" + rc.getType().getTypeString());
/*     */   }
/*     */   
/*     */   protected ActionForward update(ActionMapping mapping, BeanForm ruleConditionForm, HttpServletRequest request, RuleType type) throws Exception {
/* 122 */     RuleCondition rc = getAndCheckRuleCondition(type, request);
/* 123 */     updateAndCheckRuleCondition(rc, request, ruleConditionForm, type);
/* 124 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 126 */     rc = rm.updateRuleCondition(rc);
/* 127 */     rc.setDisplayValue(getDisplayValue(rc.getType(), rc.getValue(), request));
/* 128 */     request.setAttribute("X_OBJECT", rc);
/* 129 */     request.setAttribute("X_ROWPAGE", "ruleCondition/conditionRow.jsp");
/*     */     
/* 131 */     return mapping.findForward("successRuleConditionEdit");
/*     */   }
/*     */   
/*     */   protected ActionForward delete(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 135 */     RuleCondition rc = getAndCheckRuleCondition(type, request);
/* 136 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 138 */     rm.removeRuleCondition(rc);
/* 139 */     return mapping.findForward("successRuleConditionDelete");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getDisplayValue(ConditionType ct, String value, HttpServletRequest request) throws Exception {
/* 150 */     if (value == null) return null; 
/* 151 */     if (ConditionType.DEPARTMENT.equals(ct)) {
/* 152 */       Integer id = ActionUtils.parseInt(value);
/* 153 */       if (id == null) return null; 
/* 154 */       Department d = ServiceLocator.getDepartmentManager(request).getDepartment(id);
/* 155 */       if (d == null) return null; 
/* 156 */       return d.getName();
/*     */     } 
/* 158 */     if (ConditionType.EXPENSE_CATEGORY.equals(ct)) {
/* 159 */       Integer id = ActionUtils.parseInt(value);
/* 160 */       if (id == null) return null; 
/* 161 */       ExpenseCategory ec = ServiceLocator.getExpenseCategoryManager(request).getExpenseCategory(id);
/* 162 */       if (ec == null) return null; 
/* 163 */       return ec.getDescription();
/*     */     } 
/* 165 */     if (ConditionType.EXPENSE_SUBCATEGORY.equals(ct)) {
/* 166 */       Integer id = ActionUtils.parseInt(value.substring(1));
/* 167 */       if (id == null) return null; 
/* 168 */       if (value.charAt(0) == 'p') {
/* 169 */         ExpenseCategory pc = ServiceLocator.getExpenseCategoryManager(request).getExpenseCategory(id);
/* 170 */         if (pc == null) return null; 
/* 171 */         return pc.getDescription();
/*     */       } 
/* 173 */       ExpenseSubCategory psc = ServiceLocator.getExpenseSubCategoryManager(request).getExpenseSubCategory(id);
/* 174 */       if (psc == null) return null; 
/* 175 */       return String.valueOf(psc.getExpenseCategory().getDescription()) + " \\ " + psc.getDescription();
/*     */     } 
/*     */     
/* 178 */     if (ConditionType.PURCHASE_CATEGORY.equals(ct)) {
/* 179 */       Integer id = ActionUtils.parseInt(value.substring(1));
/* 180 */       if (id == null) return null; 
/* 181 */       if (value.charAt(0) == 'p') {
/* 182 */         PurchaseCategory pc = ServiceLocator.getPurchaseCategoryManager(request).getPurchaseCategory(id);
/* 183 */         if (pc == null) return null; 
/* 184 */         return pc.getDescription();
/*     */       } 
/* 186 */       PurchaseSubCategory psc = ServiceLocator.getPurchaseSubCategoryManager(request).getPurchaseSubCategory(id);
/* 187 */       if (psc == null) return null; 
/* 188 */       return String.valueOf(psc.getPurchaseCategory().getDescription()) + " \\ " + psc.getDescription();
/*     */     } 
/*     */     
/* 191 */     if (ConditionType.TRAVEL_FROM.equals(ct) || ConditionType.TRAVEL_TO.equals(ct)) {
/* 192 */       Integer id = ActionUtils.parseInt(value);
/* 193 */       if (id == null) return null; 
/* 194 */       TravelType tt = (TravelType)PersistentEnum.fromEnumCode(TravelType.class, id);
/* 195 */       if (tt == null) return null; 
/* 196 */       return getLocaleShortDescription((MetadataDetailEnum)tt, request);
/*     */     } 
/* 198 */     if (ConditionType.TRAVEL_MODE.equals(ct)) {
/* 199 */       Integer id = ActionUtils.parseInt(value);
/* 200 */       if (id == null) return null; 
/* 201 */       TravellingMode tm = (TravellingMode)PersistentEnum.fromEnumCode(TravellingMode.class, id);
/* 202 */       if (tm == null) return null; 
/* 203 */       return getLocaleShortDescription((MetadataDetailEnum)tm, request);
/*     */     } 
/* 205 */     if (ConditionType.BUDGET_TYPE.equals(ct)) {
/* 206 */       Integer id = ActionUtils.parseInt(value);
/* 207 */       if (id == null) return null; 
/* 208 */       BudgetType bt = (BudgetType)PersistentEnum.fromEnumCode(BudgetType.class, id);
/* 209 */       if (bt == null) return null; 
/* 210 */       return getLocaleShortDescription((MetadataDetailEnum)bt, request);
/*     */     } 
/* 212 */     if (ConditionType.WITH_BUDGET.equals(ct)) {
/* 213 */       Integer id = ActionUtils.parseInt(value);
/* 214 */       if (id == null) return null; 
/* 215 */       YesNo yn = (YesNo)PersistentEnum.fromEnumCode(YesNo.class, id);
/* 216 */       if (yn == null) return null; 
/* 217 */       return getLocaleShortDescription((MetadataDetailEnum)yn, request);
/*     */     } 
/* 219 */     return value;
/*     */   }
/*     */   
/*     */   private RuleCondition prepareNewRuleCondition(RuleType type, Integer typeEnumCode, HttpServletRequest request) throws Exception {
/* 223 */     ConditionType ct = (ConditionType)PersistentEnum.fromEnumCode(ConditionType.class, typeEnumCode);
/* 224 */     if (ct == null) throw new ActionException("ruleCondition.type.notSupported", typeEnumCode);
/*     */     
/* 226 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 227 */     Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/* 228 */     RuleCondition rc = rm.getRuleCondition(ruleId, ct);
/* 229 */     if (rc != null) throw new ActionException("ruleCondition.type.dupliate");
/*     */     
/* 231 */     Rule r = rm.getRule(ruleId);
/* 232 */     if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", ruleId); 
/* 233 */     checkSite(r.getSite(), request);
/*     */     
/* 235 */     rc = new RuleCondition(r, ct);
/* 236 */     return rc;
/*     */   }
/*     */   
/*     */   private RuleCondition getAndCheckRuleCondition(RuleType type, HttpServletRequest request) throws Exception {
/* 240 */     Integer ruleId = ActionUtils.parseInt(request.getParameter("rule_id"));
/* 241 */     Integer typeEnumCode = ActionUtils.parseInt(request.getParameter("type"));
/* 242 */     ConditionType ct = (ConditionType)PersistentEnum.fromEnumCode(ConditionType.class, typeEnumCode);
/* 243 */     if (ct == null) throw new ActionException("ruleCondition.type.notSupported", typeEnumCode); 
/* 244 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 245 */     RuleCondition rc = rm.getRuleCondition(ruleId, ct);
/* 246 */     if (rc == null) throw new ActionException("ruleCondition.notFound", new Object[] { ruleId, typeEnumCode }); 
/* 247 */     Rule r = rc.getRule();
/* 248 */     if (r == null || !type.equals(r.getType())) throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", ruleId); 
/* 249 */     checkSite(r.getSite(), request);
/* 250 */     return rc;
/*     */   }
/*     */   
/*     */   private void updateAndCheckRuleCondition(RuleCondition rc, HttpServletRequest request, BeanForm ruleConditionForm, RuleType rt) {
/* 254 */     ruleConditionForm.populateToBean(rc);
/* 255 */     ConditionType ct = rc.getType();
/* 256 */     if (!rt.getSupportedConditions().contains(ct)) {
/* 257 */       if (getCurrentUser(request).getLocale().equals("en")) {
/* 258 */         throw new ActionException("ruleCondition.type.notSupportedForRule", new Object[] { ct.getEngShortDescription(), rt.getEngShortDescription() });
/*     */       }
/* 260 */       throw new ActionException("ruleCondition.type.notSupportedForRule", new Object[] { ct.getChnShortDescription(), rt.getChnShortDescription() });
/*     */     } 
/* 262 */     if (!ct.getSupportedCompareTypes().contains(rc.getCompareType())) {
/* 263 */       if (getCurrentUser(request).getLocale().equals("en")) {
/* 264 */         throw new ActionException("ruleCondition.compareType.notSupportedForCondition", new Object[] { rc.getCompareType().getEngShortDescription(), ct.getEngShortDescription() });
/*     */       }
/* 266 */       throw new ActionException("ruleCondition.compareType.notSupportedForCondition", new Object[] { rc.getCompareType().getChnShortDescription(), ct.getChnShortDescription() });
/*     */     } 
/* 268 */     String value = ruleConditionForm.getString("value");
/* 269 */     if (ConditionType.PURCHASE_CATEGORY.equals(ct)) {
/* 270 */       Integer intValue = ActionUtils.parseInt(value);
/* 271 */       if (intValue == null) {
/* 272 */         rc.setValue(String.valueOf('p') + ruleConditionForm.getString("pvalue"));
/*     */       } else {
/* 274 */         rc.setValue(String.valueOf('s') + value);
/*     */       } 
/* 276 */     } else if (ConditionType.EXPENSE_SUBCATEGORY.equals(ct)) {
/* 277 */       Integer intValue = ActionUtils.parseInt(value);
/* 278 */       if (intValue == null) {
/* 279 */         rc.setValue(String.valueOf('p') + ruleConditionForm.getString("pvalue"));
/*     */       } else {
/* 281 */         rc.setValue(String.valueOf('s') + value);
/*     */       } 
/*     */     } else {
/* 284 */       rc.setValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void prepareData(RuleCondition rc, BeanForm ruleConditionForm, HttpServletRequest request) throws Exception {
/* 289 */     ConditionType ct = rc.getType();
/* 290 */     Set<ConditionCompareType> supportedCompareTypes = ct.getSupportedCompareTypes();
/* 291 */     if (supportedCompareTypes.size() == 1) {
/* 292 */       ruleConditionForm.set("compareType", ((ConditionCompareType)supportedCompareTypes.iterator().next()).getEnumCode().toString());
/*     */     } else {
/* 294 */       request.setAttribute("X_COMPARETYPELIST", supportedCompareTypes);
/*     */     } 
/*     */     
/* 297 */     Site s = rc.getRule().getSite();
/* 298 */     if (ConditionType.DEPARTMENT.equals(ct)) {
/* 299 */       ServiceLocator.getDepartmentManager(request).fillDepartment(s, true);
/* 300 */       request.setAttribute("X_DEPARTMENTLIST", s.getDepartments());
/*     */       return;
/*     */     } 
/* 303 */     if (ConditionType.EXPENSE_CATEGORY.equals(ct)) {
/* 304 */       request.setAttribute("X_EXPENSECATEGORYLIST", ServiceLocator.getExpenseCategoryManager(request).getEnabledExpenseCategoryOfSite(s));
/*     */       
/*     */       return;
/*     */     } 
/* 308 */     if (ConditionType.EXPENSE_SUBCATEGORY.equals(ct)) {
/* 309 */       String value = ruleConditionForm.getString("value");
/* 310 */       if (value != null && value.length() > 0) {
/* 311 */         if (value.charAt(0) == 'p') {
/* 312 */           ruleConditionForm.set("pvalue", value.substring(1));
/* 313 */           ruleConditionForm.set("value", null);
/*     */         } else {
/* 315 */           Integer id = ActionUtils.parseInt(value.substring(1));
/* 316 */           ExpenseSubCategory psc = ServiceLocator.getExpenseSubCategoryManager(request).getExpenseSubCategory(id);
/* 317 */           if (psc != null) {
/* 318 */             ruleConditionForm.set("pvalue", psc.getExpenseCategory().getId().toString());
/* 319 */             ruleConditionForm.set("value", psc.getId().toString());
/*     */           } 
/*     */         } 
/*     */       }
/* 323 */       request.setAttribute("X_EXPENSECATEGORYLIST", ServiceLocator.getExpenseCategoryManager(request).getEnabledExpenseCategorySubCategoryOfSite(s));
/*     */       return;
/*     */     } 
/* 326 */     if (ConditionType.PURCHASE_CATEGORY.equals(ct)) {
/* 327 */       String value = ruleConditionForm.getString("value");
/* 328 */       if (value != null && value.length() > 0) {
/* 329 */         if (value.charAt(0) == 'p') {
/* 330 */           ruleConditionForm.set("pvalue", value.substring(1));
/* 331 */           ruleConditionForm.set("value", null);
/*     */         } else {
/* 333 */           Integer id = ActionUtils.parseInt(value.substring(1));
/* 334 */           PurchaseSubCategory psc = ServiceLocator.getPurchaseSubCategoryManager(request).getPurchaseSubCategory(id);
/* 335 */           if (psc != null) {
/* 336 */             ruleConditionForm.set("pvalue", psc.getPurchaseCategory().getId().toString());
/* 337 */             ruleConditionForm.set("value", psc.getId().toString());
/*     */           } 
/*     */         } 
/*     */       }
/* 341 */       request.setAttribute("X_PURCHASECATEGORYLIST", ServiceLocator.getPurchaseCategoryManager(request).getEnabledPurchaseCategorySubCategoryOfSiteIncludeGlobal(s));
/*     */       return;
/*     */     } 
/* 344 */     if (ConditionType.TRAVEL_FROM.equals(ct) || ConditionType.TRAVEL_TO.equals(ct)) {
/* 345 */       request.setAttribute("X_TRAVELTYPELIST", PersistentEnum.getEnumList(TravelType.class));
/*     */       return;
/*     */     } 
/* 348 */     if (ConditionType.TRAVEL_MODE.equals(ct)) {
/* 349 */       request.setAttribute("X_TRAVELLINGMODELIST", PersistentEnum.getEnumList(TravellingMode.class));
/*     */       return;
/*     */     } 
/* 352 */     if (ConditionType.BUDGET_TYPE.equals(ct)) {
/* 353 */       request.setAttribute("X_BUDGETTYPELIST", PersistentEnum.getEnumList(BudgetType.class));
/*     */       return;
/*     */     } 
/* 356 */     if (ConditionType.WITH_BUDGET.equals(ct)) {
/* 357 */       request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/rule/BaseRuleConditionAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */