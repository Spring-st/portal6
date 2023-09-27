/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class ApproverDelegateQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final ApproverDelegateQueryCondition ID_EQ = new ApproverDelegateQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final ApproverDelegateQueryCondition ORIGINALAPPROVER_ID_EQ = new ApproverDelegateQueryCondition("originalApprover_id_eq");
/*    */   
/* 19 */   public static final ApproverDelegateQueryCondition DELEGATEAPPROVER_ID_EQ = new ApproverDelegateQueryCondition("delegateApprover_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 23 */   public static final ApproverDelegateQueryCondition TYPE_EQ = new ApproverDelegateQueryCondition("type_eq");
/*    */   
/* 25 */   public static final ApproverDelegateQueryCondition FROMDATE_EQ = new ApproverDelegateQueryCondition("fromDate_eq");
/*    */   
/* 27 */   public static final ApproverDelegateQueryCondition TODATE_EQ = new ApproverDelegateQueryCondition("toDate_eq");
/*    */ 
/*    */   
/* 30 */   public static final ApproverDelegateQueryCondition FROMDATE_GE = new ApproverDelegateQueryCondition("FROMDATE_GE");
/*    */ 
/*    */   
/* 33 */   public static final ApproverDelegateQueryCondition FROMDATE_LT = new ApproverDelegateQueryCondition("FROMDATE_LT");
/*    */ 
/*    */   
/* 36 */   public static final ApproverDelegateQueryCondition TODATE_GE = new ApproverDelegateQueryCondition("TODATE_GE");
/*    */ 
/*    */   
/* 39 */   public static final ApproverDelegateQueryCondition TODATE_LT = new ApproverDelegateQueryCondition("TODATE_LT");
/*    */   
/*    */   protected ApproverDelegateQueryCondition(String value) {
/* 42 */     super(value);
/*    */   }
/*    */   
/*    */   public static ApproverDelegateQueryCondition getEnum(String value) {
/* 46 */     return (ApproverDelegateQueryCondition)getEnum(ApproverDelegateQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/ApproverDelegateQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */