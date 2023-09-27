/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class Production72HourPlanQueryConditions extends Enum {
/*  6 */   public static final Production72HourPlanQueryConditions ID_EQ = new Production72HourPlanQueryConditions("id_eq");
/*    */   protected Production72HourPlanQueryConditions(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   public static Production72HourPlanQueryConditions getEnum(String value) {
/* 11 */     return (Production72HourPlanQueryConditions)getEnum(Production72HourPlanQueryConditions.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/Production72HourPlanQueryConditions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */