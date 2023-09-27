/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerPlanQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CustomerPlanQueryOrder ID = new CustomerPlanQueryOrder("id");
/*    */   
/*    */   protected CustomerPlanQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static CustomerPlanQueryOrder getEnum(String value) {
/* 15 */     return (CustomerPlanQueryOrder)getEnum(CustomerPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/CustomerPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */