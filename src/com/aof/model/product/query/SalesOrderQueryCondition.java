/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesOrderQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesOrderQueryCondition ID_EQ = new SalesOrderQueryCondition("id_eq");
/*    */   
/*    */   protected SalesOrderQueryCondition(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesOrderQueryCondition getEnum(String value) {
/* 15 */     return (SalesOrderQueryCondition)getEnum(SalesOrderQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesOrderQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */