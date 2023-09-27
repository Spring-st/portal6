/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitProductionPlanQueryCondition
/*    */   extends Enum {
/*  7 */   public static final JitProductionPlanQueryCondition ID_EQ = new JitProductionPlanQueryCondition("id_eq");
/*  8 */   public static final JitProductionPlanQueryCondition TYPE_EQ = new JitProductionPlanQueryCondition("type_eq");
/*  9 */   public static final JitProductionPlanQueryCondition PRODUCTION_ID_EQ = new JitProductionPlanQueryCondition("production_id_eq");
/* 10 */   public static final JitProductionPlanQueryCondition PRODUCTCLASS_NOT_EQ = new JitProductionPlanQueryCondition("productclass_not_eq");
/* 11 */   public static final JitProductionPlanQueryCondition PRODUCTCLASS_EQ = new JitProductionPlanQueryCondition("productclass_eq");
/* 12 */   public static final JitProductionPlanQueryCondition DATE_GE = new JitProductionPlanQueryCondition("date_ge");
/* 13 */   public static final JitProductionPlanQueryCondition DATE_EQ = new JitProductionPlanQueryCondition("date_eq");
/* 14 */   public static final JitProductionPlanQueryCondition PARTID_VEND_EQ = new JitProductionPlanQueryCondition("partId_vend_eq");
/* 15 */   public static final JitProductionPlanQueryCondition QTY_GT = new JitProductionPlanQueryCondition("qty_gt");
/*    */   protected JitProductionPlanQueryCondition(String name) {
/* 17 */     super(name);
/*    */   }
/*    */   
/*    */   public static JitProductionPlanQueryCondition getEnum(String value) {
/* 21 */     return (JitProductionPlanQueryCondition)getEnum(JitProductionPlanQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/JitProductionPlanQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */