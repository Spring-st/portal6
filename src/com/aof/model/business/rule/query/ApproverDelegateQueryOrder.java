/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ApproverDelegateQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ApproverDelegateQueryOrder ID = new ApproverDelegateQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ApproverDelegateQueryOrder TYPE = new ApproverDelegateQueryOrder("type");
/* 12 */   public static final ApproverDelegateQueryOrder FROMDATE = new ApproverDelegateQueryOrder("fromDate");
/* 13 */   public static final ApproverDelegateQueryOrder TODATE = new ApproverDelegateQueryOrder("toDate");
/*    */   
/* 15 */   public static final ApproverDelegateQueryOrder DELEGATEAPPROVER_NAME = new ApproverDelegateQueryOrder("delegateApprover.name");
/*    */   
/*    */   protected ApproverDelegateQueryOrder(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static ApproverDelegateQueryOrder getEnum(String value) {
/* 22 */     return (ApproverDelegateQueryOrder)getEnum(ApproverDelegateQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/ApproverDelegateQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */