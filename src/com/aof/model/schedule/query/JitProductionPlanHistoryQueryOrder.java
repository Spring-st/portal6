/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitProductionPlanHistoryQueryOrder extends Enum {
/*    */   protected JitProductionPlanHistoryQueryOrder(String name) {
/*  7 */     super(name);
/*    */   }
/*    */   
/*    */   public static JitProductionPlanHistoryQueryOrder getEnum(String value) {
/* 11 */     return (JitProductionPlanHistoryQueryOrder)getEnum(JitProductionPlanHistoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/JitProductionPlanHistoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */