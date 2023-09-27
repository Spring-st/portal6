/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderItemQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderItemQueryOrder ID = new SalesPreshiporderItemQueryOrder("id");
/*    */   
/*    */   protected SalesPreshiporderItemQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderItemQueryOrder getEnum(String value) {
/* 15 */     return (SalesPreshiporderItemQueryOrder)getEnum(SalesPreshiporderItemQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderItemQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */