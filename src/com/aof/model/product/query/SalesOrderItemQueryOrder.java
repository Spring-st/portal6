/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesOrderItemQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesOrderItemQueryOrder ID = new SalesOrderItemQueryOrder("id");
/*    */   
/*    */   protected SalesOrderItemQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesOrderItemQueryOrder getEnum(String value) {
/* 15 */     return (SalesOrderItemQueryOrder)getEnum(SalesOrderItemQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesOrderItemQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */