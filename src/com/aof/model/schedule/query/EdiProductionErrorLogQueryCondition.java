/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class EdiProductionErrorLogQueryCondition
/*    */   extends Enum {
/*    */   protected EdiProductionErrorLogQueryCondition(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   
/*    */   public static EdiProductionErrorLogQueryCondition getEnum(String value) {
/* 12 */     return (EdiProductionErrorLogQueryCondition)getEnum(EdiProductionErrorLogQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/EdiProductionErrorLogQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */