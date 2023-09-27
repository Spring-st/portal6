/*    */ package com.aof.model.schedule.query;
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductionDayPlanQueryCondition extends Enum {
/*  5 */   public static final ProductionDayPlanQueryCondition ASNNO_EQ = new ProductionDayPlanQueryCondition("asnno_eq");
/*    */   protected ProductionDayPlanQueryCondition(String name) {
/*  7 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProductionDayPlanQueryCondition getEnum(String value) {
/* 11 */     return (ProductionDayPlanQueryCondition)getEnum(ProductionDayPlanQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProductionDayPlanQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */