/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderBatchQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderBatchQueryOrder ID = new SalesPreshiporderBatchQueryOrder("id");
/*  9 */   public static final SalesPreshiporderBatchQueryOrder LOCATIONID = new SalesPreshiporderBatchQueryOrder("locationid");
/* 10 */   public static final SalesPreshiporderBatchQueryOrder PARTID = new SalesPreshiporderBatchQueryOrder("partid");
/*    */   protected SalesPreshiporderBatchQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderBatchQueryOrder getEnum(String value) {
/* 16 */     return (SalesPreshiporderBatchQueryOrder)getEnum(SalesPreshiporderBatchQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderBatchQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */