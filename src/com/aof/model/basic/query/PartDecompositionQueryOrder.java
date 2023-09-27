/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PartDecompositionQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PartDecompositionQueryOrder ID = new PartDecompositionQueryOrder("id");
/*    */ 
/*    */   
/*    */   protected PartDecompositionQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static PartDecompositionQueryOrder getEnum(String value) {
/* 16 */     return (PartDecompositionQueryOrder)getEnum(PartDecompositionQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/PartDecompositionQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */