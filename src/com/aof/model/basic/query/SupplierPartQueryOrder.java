/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SupplierPartQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SupplierPartQueryOrder ID = new SupplierPartQueryOrder("id");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SupplierPartQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierPartQueryOrder getEnum(String value) {
/* 18 */     return (SupplierPartQueryOrder)getEnum(SupplierPartQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/SupplierPartQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */