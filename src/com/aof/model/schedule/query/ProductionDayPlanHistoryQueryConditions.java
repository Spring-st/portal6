/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionDayPlanHistoryQueryConditions extends Enum {
/*  6 */   public static final ProductionDayPlanHistoryQueryConditions VERSION_EQ = new ProductionDayPlanHistoryQueryConditions("version_eq");
/*    */   protected ProductionDayPlanHistoryQueryConditions(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProductionDayPlanHistoryQueryConditions getEnum(String value) {
/* 12 */     return (ProductionDayPlanHistoryQueryConditions)getEnum(ProductionDayPlanHistoryQueryConditions.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProductionDayPlanHistoryQueryConditions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */