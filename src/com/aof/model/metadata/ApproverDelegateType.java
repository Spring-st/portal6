/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApproverDelegateType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 16 */   public static final ApproverDelegateType NONBUDGET_CAPEX_APPROVER = new ApproverDelegateType(1, "Non-Budget Capex Approver", MetadataType.APPROVER_DELEGATE_TYPE);
/* 17 */   public static final ApproverDelegateType PURCHASE_REQUEST_APPROVER = new ApproverDelegateType(2, "Purchase Request Approver", MetadataType.APPROVER_DELEGATE_TYPE);
/* 18 */   public static final ApproverDelegateType PURCHASE_ORDER_APPROVER = new ApproverDelegateType(3, "Purchase Order Approver", MetadataType.APPROVER_DELEGATE_TYPE);
/* 19 */   public static final ApproverDelegateType EXPENSE_APPROVER = new ApproverDelegateType(4, "Expense Approver", MetadataType.APPROVER_DELEGATE_TYPE);
/* 20 */   public static final ApproverDelegateType TRAVEL_APPLICATION_APPROVER = new ApproverDelegateType(5, "Travel Application Approver", MetadataType.APPROVER_DELEGATE_TYPE);
/*    */ 
/*    */   
/*    */   public ApproverDelegateType() {}
/*    */   
/*    */   private ApproverDelegateType(int metadataId, String defaultLabel, MetadataType type) {
/* 26 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ 
/*    */   
/*    */   public static ApproverDelegateType getApproverDelegateTypeByRuleType(RuleType ruleType) {
/* 31 */     return (ApproverDelegateType)ruleToAdMap.get(ruleType);
/*    */   }
/*    */ 
/*    */   
/*    */   public static RuleType getRuleTypeByApproverDelegateType(ApproverDelegateType type) {
/* 36 */     return (RuleType)adToRuleMap.get(type);
/*    */   }
/*    */   
/* 39 */   private static Map ruleToAdMap = new HashMap<Object, Object>();
/* 40 */   private static Map adToRuleMap = new HashMap<Object, Object>();
/*    */   
/*    */   static {
/* 43 */     ruleToAdMap.put(RuleType.CAPEX_APPROVAL_RULES, NONBUDGET_CAPEX_APPROVER);
/* 44 */     ruleToAdMap.put(RuleType.EXPENSE_APPROVAL_RULES, EXPENSE_APPROVER);
/* 45 */     ruleToAdMap.put(RuleType.TRAVEL_APPROVAL_RULES, TRAVEL_APPLICATION_APPROVER);
/* 46 */     ruleToAdMap.put(RuleType.PO_APPROVAL_RULES, PURCHASE_ORDER_APPROVER);
/* 47 */     ruleToAdMap.put(RuleType.PR_APPROVAL_RULES, PURCHASE_REQUEST_APPROVER);
/*    */     
/* 49 */     for (Iterator iter = ruleToAdMap.keySet().iterator(); iter.hasNext(); ) {
/* 50 */       Object key = iter.next();
/* 51 */       adToRuleMap.put(ruleToAdMap.get(key), key);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ApproverDelegateType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */