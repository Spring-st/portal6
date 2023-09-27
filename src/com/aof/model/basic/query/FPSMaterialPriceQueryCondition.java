/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class FPSMaterialPriceQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final FPSMaterialPriceQueryCondition ID_EQ = new FPSMaterialPriceQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final FPSMaterialPriceQueryCondition CODE_EQ = new FPSMaterialPriceQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final FPSMaterialPriceQueryCondition TYPE_EQ = new FPSMaterialPriceQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final FPSMaterialPriceQueryCondition MANUAL_EQ = new FPSMaterialPriceQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final FPSMaterialPriceQueryCondition ENABLED_EQ = new FPSMaterialPriceQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final FPSMaterialPriceQueryCondition CODE_LIKE = new FPSMaterialPriceQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final FPSMaterialPriceQueryCondition IS_ENABLED_EQ = new FPSMaterialPriceQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final FPSMaterialPriceQueryCondition DESCRIBE2_EQ = new FPSMaterialPriceQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final FPSMaterialPriceQueryCondition DESCRIBE1_EQ = new FPSMaterialPriceQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final FPSMaterialPriceQueryCondition PART_EQ = new FPSMaterialPriceQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected FPSMaterialPriceQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static FPSMaterialPriceQueryCondition getEnum(String value) {
/* 34 */     return (FPSMaterialPriceQueryCondition)getEnum(
/* 35 */         FPSMaterialPriceQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/FPSMaterialPriceQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */