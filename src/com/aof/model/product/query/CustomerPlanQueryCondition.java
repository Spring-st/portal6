/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerPlanQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CustomerPlanQueryCondition ID_EQ = new CustomerPlanQueryCondition("id_eq");
/*  9 */   public static final CustomerPlanQueryCondition QTYOPEN_DT = new CustomerPlanQueryCondition("qtyopen_dt");
/* 10 */   public static final CustomerPlanQueryCondition STATUS_EQ = new CustomerPlanQueryCondition("status_eq");
/* 11 */   public static final CustomerPlanQueryCondition STATUS_OPEN_EQ = new CustomerPlanQueryCondition("status_open_eq");
/*    */   protected CustomerPlanQueryCondition(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static CustomerPlanQueryCondition getEnum(String value) {
/* 17 */     return (CustomerPlanQueryCondition)getEnum(CustomerPlanQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/CustomerPlanQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */