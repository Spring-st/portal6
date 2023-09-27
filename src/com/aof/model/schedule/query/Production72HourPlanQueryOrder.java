/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class Production72HourPlanQueryOrder extends Enum {
/*  6 */   public static final Production72HourPlanQueryOrder ID = new Production72HourPlanQueryOrder("id");
/*    */   protected Production72HourPlanQueryOrder(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   
/*    */   public static Production72HourPlanQueryOrder getEnum(String value) {
/* 12 */     return (Production72HourPlanQueryOrder)getEnum(Production72HourPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/Production72HourPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */