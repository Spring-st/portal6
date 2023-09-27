/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class FPSMaterialPriceQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final FPSMaterialPriceQueryOrder ID = new FPSMaterialPriceQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final FPSMaterialPriceQueryOrder ENABLED = new FPSMaterialPriceQueryOrder("enabled");
/*    */   
/*    */   protected FPSMaterialPriceQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static FPSMaterialPriceQueryOrder getEnum(String value) {
/* 18 */     return (FPSMaterialPriceQueryOrder)getEnum(FPSMaterialPriceQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/FPSMaterialPriceQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */