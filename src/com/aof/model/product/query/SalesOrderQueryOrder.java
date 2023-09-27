/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesOrderQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesOrderQueryOrder ID = new SalesOrderQueryOrder("id");
/*    */   
/*    */   protected SalesOrderQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesOrderQueryOrder getEnum(String value) {
/* 15 */     return (SalesOrderQueryOrder)getEnum(SalesOrderQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesOrderQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */