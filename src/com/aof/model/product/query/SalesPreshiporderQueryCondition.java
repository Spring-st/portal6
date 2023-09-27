/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesPreshiporderQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesPreshiporderQueryCondition ID_EQ = new SalesPreshiporderQueryCondition("id_eq");
/*  9 */   public static final SalesPreshiporderQueryCondition TYPE_EQ = new SalesPreshiporderQueryCondition("type_eq");
/*    */   
/*    */   protected SalesPreshiporderQueryCondition(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesPreshiporderQueryCondition getEnum(String value) {
/* 16 */     return (SalesPreshiporderQueryCondition)getEnum(SalesPreshiporderQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesPreshiporderQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */