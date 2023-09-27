/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class EdiProductionErrorLogQueryOrder
/*    */   extends Enum {
/*    */   protected EdiProductionErrorLogQueryOrder(String name) {
/*  8 */     super(name);
/*    */   }
/*    */   
/*    */   public static EdiProductionErrorLogQueryOrder getEnum(String value) {
/* 12 */     return (EdiProductionErrorLogQueryOrder)getEnum(EdiProductionErrorLogQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/EdiProductionErrorLogQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */