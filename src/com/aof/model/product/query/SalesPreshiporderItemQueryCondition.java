/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderItemQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderItemQueryCondition ID_EQ = new SalesPreshiporderItemQueryCondition("id_eq");
/*  9 */   public static final SalesPreshiporderItemQueryCondition SALESSHIPORDER_ID_EQ = new SalesPreshiporderItemQueryCondition("salesShipOrder_id_eq");
/* 10 */   public static final SalesPreshiporderItemQueryCondition SALESSHIPORDER_TYPE_EQ = new SalesPreshiporderItemQueryCondition("salesshiporder_type_eq");
/*    */   
/*    */   protected SalesPreshiporderItemQueryCondition(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderItemQueryCondition getEnum(String value) {
/* 17 */     return (SalesPreshiporderItemQueryCondition)getEnum(SalesPreshiporderItemQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderItemQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */