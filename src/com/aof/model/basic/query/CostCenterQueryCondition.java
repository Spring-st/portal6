/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CostCenterQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CostCenterQueryCondition ID_EQ = new CostCenterQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final CostCenterQueryCondition CODE_EQ = new CostCenterQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final CostCenterQueryCondition TYPE_EQ = new CostCenterQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final CostCenterQueryCondition MANUAL_EQ = new CostCenterQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final CostCenterQueryCondition ENABLED_EQ = new CostCenterQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final CostCenterQueryCondition CODE_LIKE = new CostCenterQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final CostCenterQueryCondition IS_ENABLED_EQ = new CostCenterQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final CostCenterQueryCondition DESCRIBE2_EQ = new CostCenterQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final CostCenterQueryCondition DESCRIBE1_EQ = new CostCenterQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final CostCenterQueryCondition PART_EQ = new CostCenterQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected CostCenterQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static CostCenterQueryCondition getEnum(String value) {
/* 34 */     return (CostCenterQueryCondition)getEnum(
/* 35 */         CostCenterQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CostCenterQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */