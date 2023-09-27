/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionDayPlanHistoryQueryOrder extends Enum {
/*    */   protected ProductionDayPlanHistoryQueryOrder(String name) {
/*  7 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProductionDayPlanHistoryQueryOrder getEnum(String value) {
/* 11 */     return (ProductionDayPlanHistoryQueryOrder)getEnum(ProductionDayPlanHistoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProductionDayPlanHistoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */