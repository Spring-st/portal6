/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionDayPlanQueryOrder
/*    */   extends Enum {
/*    */   protected ProductionDayPlanQueryOrder(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProductionDayPlanQueryOrder getEnum(String value) {
/* 12 */     return (ProductionDayPlanQueryOrder)getEnum(ProductionDayPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProductionDayPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */