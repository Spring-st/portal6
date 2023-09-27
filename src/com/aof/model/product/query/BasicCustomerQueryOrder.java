/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicCustomerQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BasicCustomerQueryOrder ID = new BasicCustomerQueryOrder("id");
/*    */   
/*    */   protected BasicCustomerQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static BasicCustomerQueryOrder getEnum(String value) {
/* 15 */     return (BasicCustomerQueryOrder)getEnum(BasicCustomerQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/BasicCustomerQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */