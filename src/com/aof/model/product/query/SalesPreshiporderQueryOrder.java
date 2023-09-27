/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderQueryOrder ID = new SalesPreshiporderQueryOrder("id");
/*    */   
/*    */   protected SalesPreshiporderQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderQueryOrder getEnum(String value) {
/* 15 */     return (SalesPreshiporderQueryOrder)getEnum(SalesPreshiporderQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */