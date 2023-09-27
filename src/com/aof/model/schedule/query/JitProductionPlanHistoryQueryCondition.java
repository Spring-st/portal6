/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitProductionPlanHistoryQueryCondition extends Enum {
/*  6 */   public static final JitProductionPlanHistoryQueryCondition id_EQ = new JitProductionPlanHistoryQueryCondition("id_eq");
/*    */   protected JitProductionPlanHistoryQueryCondition(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   protected static JitProductionPlanHistoryQueryCondition getEnum(String value) {
/* 11 */     return (JitProductionPlanHistoryQueryCondition)getEnum(JitProductionPlanHistoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/JitProductionPlanHistoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */