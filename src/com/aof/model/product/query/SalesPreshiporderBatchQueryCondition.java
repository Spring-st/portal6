/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderBatchQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderBatchQueryCondition ID_EQ = new SalesPreshiporderBatchQueryCondition("id_eq");
/*  9 */   public static final SalesPreshiporderBatchQueryCondition SPSOITEM_SHIPORDER_ID_EQ = new SalesPreshiporderBatchQueryCondition("spsoItem_shipOrder_Id_eq");
/*    */   
/*    */   protected SalesPreshiporderBatchQueryCondition(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderBatchQueryCondition getEnum(String value) {
/* 16 */     return (SalesPreshiporderBatchQueryCondition)getEnum(SalesPreshiporderBatchQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderBatchQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */