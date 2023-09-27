/*     */ package com.aof.service.business.rule.impl;
/*     */ 
/*     */ import com.aof.dao.business.rule.RuleDAO;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.RuleCondition;
/*     */ import com.aof.model.business.rule.RuleConsequence;
/*     */ import com.aof.model.business.rule.query.RuleQueryCondition;
/*     */ import com.aof.model.business.rule.query.RuleQueryOrder;
/*     */ import com.aof.model.metadata.BudgetType;
/*     */ import com.aof.model.metadata.ConditionType;
/*     */ import com.aof.model.metadata.ConsequenceType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.model.metadata.TravelType;
/*     */ import com.aof.model.metadata.TravellingMode;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.ruleengine.EngineFlow;
/*     */ import com.aof.ruleengine.EngineRule;
/*     */ import com.aof.ruleengine.EngineWorkspace;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.business.rule.FlowManager;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public class RuleManagerImpl
/*     */   extends BaseManager
/*     */   implements RuleManager
/*     */ {
/*     */   private RuleDAO dao;
/*     */   private FlowManager flowManager;
/*     */   private EngineWorkspace workspace;
/*     */   
/*     */   public void setRuleDAO(RuleDAO dao) {
/*  63 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlowManager(FlowManager flowManager) {
/*  73 */     this.flowManager = flowManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorkspace(EngineWorkspace workspace) {
/*  83 */     this.workspace = workspace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule(Integer id) {
/*  92 */     return getRule(id, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule(Integer id, boolean loadLazyProperties) {
/* 102 */     return this.dao.getRule(id, loadLazyProperties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule saveRule(Rule r) {
/* 111 */     boolean needUpdateWorkspace = false;
/* 112 */     RuleType rt = r.getType();
/* 113 */     if (ConsequenceType.NOTIFIER.equals(rt.getConsequenceType())) {
/* 114 */       if (r.getId() == null) {
/* 115 */         needUpdateWorkspace = EnabledDisabled.ENABLED.equals(r.getEnabled());
/*     */       } else {
/* 117 */         Rule or = this.dao.getRule(r.getId(), false);
/* 118 */         needUpdateWorkspace = !r.getEnabled().equals(or.getEnabled());
/* 119 */         or.setDescription(r.getDescription());
/* 120 */         or.setEnabled(r.getEnabled());
/* 121 */         r = or;
/*     */       } 
/*     */     }
/* 124 */     r = this.dao.saveRule(r);
/* 125 */     if (needUpdateWorkspace) {
/* 126 */       this.flowManager.constructEngineFlow(r.getSite().getId(), rt);
/*     */     }
/* 128 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRule(Integer id) {
/* 137 */     Rule r = getRule(id, true);
/* 138 */     for (Iterator<RuleCondition> iterator = r.getConditions().iterator(); iterator.hasNext();) {
/* 139 */       this.dao.removeRuleCondition(iterator.next());
/*     */     }
/* 141 */     for (Iterator<RuleConsequence> itor = r.getConsequences().iterator(); itor.hasNext();) {
/* 142 */       this.dao.removeRuleConsequence(itor.next());
/*     */     }
/* 144 */     this.dao.removeRule(id);
/* 145 */     RuleType rt = r.getType();
/* 146 */     if (ConsequenceType.NOTIFIER.equals(rt.getConsequenceType()) && EnabledDisabled.ENABLED.equals(r.getEnabled())) {
/* 147 */       this.flowManager.constructEngineFlow(r.getSite().getId(), rt);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRuleListCount(Map conditions) {
/* 157 */     return this.dao.getRuleListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRuleList(Map conditions, int pageNo, int pageSize, RuleQueryOrder order, boolean descend) {
/* 167 */     return this.dao.getRuleList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRuleInUse(Integer id) {
/* 176 */     return this.dao.isRuleInUse(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiteEnabledRuleList(Integer siteId, RuleType type) {
/* 186 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 187 */     conditions.put(RuleQueryCondition.SITE_ID_EQ, siteId);
/* 188 */     conditions.put(RuleQueryCondition.TYPE_EQ, type.getEnumCode());
/* 189 */     conditions.put(RuleQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 190 */     return getRuleList(conditions, 0, -1, RuleQueryOrder.DESCRIPTION, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition getRuleCondition(Integer ruleId, ConditionType ct) {
/* 200 */     return this.dao.getRuleCondition(ruleId, ct);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition saveRuleCondition(RuleCondition rc) {
/*     */     try {
/* 210 */       return this.dao.saveRuleCondition(rc);
/*     */     } finally {
/* 212 */       updateEngineRuleForRule(rc.getRule().getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition updateRuleCondition(RuleCondition rc) {
/*     */     try {
/* 223 */       return this.dao.updateRuleCondition(rc);
/*     */     } finally {
/* 225 */       updateEngineRuleForRule(rc.getRule().getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRuleCondition(RuleCondition rc) {
/* 235 */     this.dao.removeRuleCondition(rc);
/* 236 */     updateEngineRuleForRule(rc.getRule().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence getRuleConsequence(Integer ruleId, Integer userId) {
/* 246 */     return this.dao.getRuleConsequence(ruleId, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence saveRuleConsequence(RuleConsequence rc) {
/*     */     try {
/* 256 */       if (ConsequenceType.APPROVER.equals(rc.getRule().getType().getConsequenceType())) {
/* 257 */         return saveApproverRuleConsequence(rc);
/*     */       }
/* 259 */       return this.dao.saveRuleConsequence(rc);
/*     */     } finally {
/* 261 */       updateEngineRuleForRule(rc.getRule().getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence updateRuleConsequence(RuleConsequence rc) {
/*     */     try {
/* 272 */       if (ConsequenceType.APPROVER.equals(rc.getRule().getType().getConsequenceType())) {
/* 273 */         return saveApproverRuleConsequence(rc);
/*     */       }
/* 275 */       return this.dao.updateRuleConsequence(rc);
/*     */     } finally {
/* 277 */       updateEngineRuleForRule(rc.getRule().getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRuleConsequence(RuleConsequence rc) {
/*     */     try {
/* 288 */       if (ConsequenceType.APPROVER.equals(rc.getRule().getType().getConsequenceType())) {
/* 289 */         removeApproverRuleConsequence(rc);
/*     */         return;
/*     */       } 
/* 292 */       this.dao.removeRuleConsequence(rc);
/*     */     } finally {
/* 294 */       updateEngineRuleForRule(rc.getRule().getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMaxConsequenceSeqNoForRuleId(Integer ruleId) {
/* 304 */     Integer seq = this.dao.getMaxConsequenceSeqNoForRuleId(ruleId);
/* 305 */     if (seq == null)
/* 306 */       return new Integer(0); 
/* 307 */     return seq;
/*     */   }
/*     */   
/* 310 */   private Comparator ruleConsequenceComparator = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/* 312 */         return ((RuleConsequence)o1).getSeq() - ((RuleConsequence)o2).getSeq();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RuleConsequence saveApproverRuleConsequence(RuleConsequence rc) {
/* 324 */     int newSeq = rc.getSeq();
/* 325 */     RuleConsequence oldOne = null;
/* 326 */     Set<RuleConsequence> smallerSet = new TreeSet(this.ruleConsequenceComparator);
/* 327 */     Set<RuleConsequence> biggerSet = new TreeSet(this.ruleConsequenceComparator);
/* 328 */     for (Iterator<RuleConsequence> itor = this.dao.getRule(rc.getRule().getId(), true).getConsequences().iterator(); itor.hasNext(); ) {
/* 329 */       RuleConsequence rc2 = itor.next();
/* 330 */       int currentSeq = rc2.getSeq();
/* 331 */       if (rc2.equals(rc)) {
/* 332 */         oldOne = rc2;
/* 333 */         oldOne.setCanModify(rc.getCanModify());
/* 334 */         oldOne.setSuperior(rc.getSuperior()); continue;
/*     */       } 
/* 336 */       if (currentSeq >= newSeq) {
/* 337 */         biggerSet.add(rc2); continue;
/*     */       } 
/* 339 */       smallerSet.add(rc2);
/*     */     } 
/*     */ 
/*     */     
/* 343 */     int nextSeq = 1;
/* 344 */     for (Iterator<RuleConsequence> iterator2 = smallerSet.iterator(); iterator2.hasNext(); nextSeq++) {
/* 345 */       RuleConsequence rc2 = iterator2.next();
/* 346 */       int currentSeq = rc2.getSeq();
/* 347 */       if (currentSeq != nextSeq) {
/* 348 */         rc2.setSeq(nextSeq);
/* 349 */         this.dao.updateRuleConsequence(rc2);
/*     */       } 
/*     */     } 
/* 352 */     if (oldOne == null) {
/* 353 */       rc.setSeq(nextSeq++);
/* 354 */       oldOne = this.dao.saveRuleConsequence(rc);
/*     */     } else {
/* 356 */       oldOne.setSeq(nextSeq++);
/* 357 */       oldOne = this.dao.updateRuleConsequence(oldOne);
/*     */     } 
/* 359 */     for (Iterator<RuleConsequence> iterator1 = biggerSet.iterator(); iterator1.hasNext(); nextSeq++) {
/* 360 */       RuleConsequence rc2 = iterator1.next();
/* 361 */       int currentSeq = rc2.getSeq();
/* 362 */       if (currentSeq != nextSeq) {
/* 363 */         rc2.setSeq(nextSeq);
/* 364 */         this.dao.updateRuleConsequence(rc2);
/*     */       } 
/*     */     } 
/* 367 */     return oldOne;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeApproverRuleConsequence(RuleConsequence rc) {
/* 377 */     this.dao.removeRuleConsequence(rc);
/* 378 */     int nextSeq = 1;
/* 379 */     for (Iterator<RuleConsequence> itor = this.dao.getRule(rc.getRule().getId(), true).getConsequences().iterator(); itor.hasNext(); ) {
/* 380 */       RuleConsequence rc2 = itor.next();
/* 381 */       int currentSeq = rc2.getSeq();
/* 382 */       if (currentSeq != nextSeq) {
/* 383 */         rc2.setSeq(nextSeq);
/* 384 */         this.dao.updateRuleConsequence(rc2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateEngineRuleForRule(Integer ruleId) {
/* 390 */     Rule r = this.dao.getRule(ruleId, false);
/* 391 */     List conditions = this.dao.getConditionsForRuleId(ruleId);
/* 392 */     List consequences = this.dao.getConsequencesForRuleId(ruleId);
/* 393 */     EngineFlow flow = this.workspace.getFlow(r.getSite().getId() + r.getType().getPrefixUrl());
/* 394 */     if (flow != null) {
/* 395 */       flow.lockForUpdate();
/*     */       try {
/* 397 */         Set rules = flow.getRulesByExternalId(ruleId);
/* 398 */         if (rules != null) {
/* 399 */           for (Iterator<EngineRule> itor = rules.iterator(); itor.hasNext(); ) {
/* 400 */             EngineRule er = itor.next();
/* 401 */             updateEngineRuleForRule(conditions, consequences, er, r.getType());
/*     */           } 
/*     */         }
/*     */       } finally {
/* 405 */         flow.releaseLock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEngineRuleForRule(Integer ruleId, EngineRule er) {
/* 417 */     Rule r = this.dao.getRule(ruleId, false);
/* 418 */     List conditions = this.dao.getConditionsForRuleId(ruleId);
/* 419 */     List consequences = this.dao.getConsequencesForRuleId(ruleId);
/* 420 */     updateEngineRuleForRule(conditions, consequences, er, r.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledRuleListForRuleType(Integer siteId, RuleType rt) {
/* 430 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 431 */     conditions.put(RuleQueryCondition.SITE_ID_EQ, siteId);
/* 432 */     conditions.put(RuleQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/* 433 */     conditions.put(RuleQueryCondition.TYPE_EQ, rt);
/* 434 */     return this.dao.getRuleList(conditions, 0, -1, null, false);
/*     */   }
/*     */   
/*     */   private void updateEngineRuleForRule(Collection conditions, Collection consequences, EngineRule er, RuleType rt) {
/* 438 */     er.clearConditions();
/* 439 */     for (Iterator<RuleCondition> itor = conditions.iterator(); itor.hasNext(); ) {
/* 440 */       String compareSource; Object compareTarget; RuleCondition rc = itor.next();
/* 441 */       int comparePassCondition = rc.getCompareType().getEngineComparePassCondition();
/* 442 */       ConditionType ct = rc.getType();
/* 443 */       String value = rc.getValue();
/*     */ 
/*     */ 
/*     */       
/* 447 */       if (ConditionType.CAPEX_REQUEST_AMOUNT.equals(ct) || 
/* 448 */         ConditionType.EXPENSE_ENTERED_AMOUNT.equals(ct) || 
/* 449 */         ConditionType.PO_PURCHASE_AMOUNT.equals(ct) || 
/* 450 */         ConditionType.PR_REQUEST_AMOUNT.equals(ct) || 
/* 451 */         ConditionType.YEARLY_BUDGET_AMOUNT.equals(ct) || 
/* 452 */         ConditionType.TRAVEL_FEE.equals(ct)) {
/*     */         
/* 454 */         compareSource = "approveAmount";
/* 455 */         compareTarget = new BigDecimal(value);
/* 456 */       } else if (ConditionType.DEPARTMENT.equals(ct)) {
/* 457 */         compareSource = "approveDepartment";
/* 458 */         compareTarget = ActionUtils.parseInt(value);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 464 */       else if (ConditionType.OVER_BUDGET.equals(ct)) {
/* 465 */         compareSource = "approveOverBudget";
/* 466 */         compareTarget = new BigDecimal(value);
/* 467 */       } else if (ConditionType.DIFFERENCE_AMOUNT_OF_EXPENSE_CLAIMED_AND_ENTERED.equals(ct)) {
/* 468 */         compareSource = "differenceBetweenClaimAmountAndAmount";
/* 469 */         compareTarget = new BigDecimal(value);
/* 470 */       } else if (ConditionType.DIFFERENCE_AMOUNT_OF_PR_PURCHASE_AND_APPROVED.equals(ct)) {
/* 471 */         compareSource = "differenceBetweenPurchaseAmountAndApprovedAmount";
/* 472 */         compareTarget = new BigDecimal(value);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 478 */       else if (ConditionType.OVER_BUDGET_PERCENTAGE.equals(ct)) {
/* 479 */         compareSource = "approveOverBudgetPercentage";
/* 480 */         compareTarget = new BigDecimal(value);
/* 481 */       } else if (ConditionType.DIFFERENCE_AMOUNT_PERCENTAGE_OF_EXPENSE_CLAIMED_AND_ENTERED.equals(ct)) {
/* 482 */         compareSource = "differencePercentageBetweenClaimAmountAndAmount";
/* 483 */         compareTarget = new BigDecimal(value);
/* 484 */       } else if (ConditionType.DIFFERENCE_AMOUNT_PERCENTAGE_OF_PR_PURCHASE_AND_APPROVED.equals(ct)) {
/* 485 */         compareSource = "differencePercentageBetweenPurchaseAmountAndApprovedAmount";
/* 486 */         compareTarget = new BigDecimal(value);
/* 487 */       } else if (ConditionType.EXPENSE_CATEGORY.equals(ct)) {
/* 488 */         compareSource = "approveExpenseCategory";
/* 489 */         compareTarget = ActionUtils.parseInt(value);
/* 490 */       } else if (ConditionType.PURCHASE_CATEGORY.equals(ct)) {
/* 491 */         if (value.charAt(0) == 'p') {
/* 492 */           compareSource = "approvePurchaseCategory";
/*     */         } else {
/* 494 */           compareSource = "approvePurchaseSubCategory";
/*     */         } 
/* 496 */         compareTarget = ActionUtils.parseInt(value.substring(1));
/* 497 */       } else if (ConditionType.TRAVEL_FROM.equals(ct)) {
/* 498 */         compareSource = "approveTravelFrom";
/* 499 */         compareTarget = PersistentEnum.fromEnumCode(TravelType.class, ActionUtils.parseInt(value));
/* 500 */       } else if (ConditionType.TRAVEL_MODE.equals(ct)) {
/* 501 */         compareSource = "approveTravellingMode";
/* 502 */         compareTarget = PersistentEnum.fromEnumCode(TravellingMode.class, ActionUtils.parseInt(value));
/* 503 */       } else if (ConditionType.TRAVEL_TO.equals(ct)) {
/* 504 */         compareSource = "approveTravelTo";
/* 505 */         compareTarget = PersistentEnum.fromEnumCode(TravelType.class, ActionUtils.parseInt(value));
/* 506 */       } else if (ConditionType.BUDGET_TYPE.equals(ct)) {
/* 507 */         compareSource = "notifyBudgetType";
/* 508 */         compareTarget = PersistentEnum.fromEnumCode(BudgetType.class, ActionUtils.parseInt(value));
/* 509 */       } else if (ConditionType.REMAIN_AMOUNT.equals(ct)) {
/* 510 */         compareSource = "notifyRemainAmount";
/* 511 */         compareTarget = new BigDecimal(value);
/* 512 */       } else if (ConditionType.WITH_BUDGET.equals(ct)) {
/* 513 */         compareSource = "approveWithBudget";
/* 514 */         compareTarget = PersistentEnum.fromEnumCode(YesNo.class, ActionUtils.parseInt(value));
/* 515 */       } else if (ConditionType.PR_REQUEST_ITEM_MAX_PRICE.equals(ct)) {
/* 516 */         compareSource = "maxItemPrice";
/* 517 */         compareTarget = new BigDecimal(value);
/*     */       } else {
/*     */         
/* 520 */         throw new UnsupportedOperationException("Missing support for condition type '" + ct.getEngShortDescription() + "'.");
/*     */       } 
/* 522 */       er.addCondition(compareSource, comparePassCondition, compareTarget);
/*     */     } 
/* 524 */     if (ConsequenceType.ACCEPTABLE.equals(rt.getConsequenceType())) {
/* 525 */       er.setConsequencesPass(Boolean.TRUE);
/* 526 */       er.setConsequencesFail(Boolean.FALSE);
/*     */     } else {
/* 528 */       er.setConsequencesPass(consequences);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/rule/impl/RuleManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */