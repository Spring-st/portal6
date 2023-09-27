/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class EdiProductionQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final EdiProductionQueryOrder ID = new EdiProductionQueryOrder("id");
/*  9 */   public static final EdiProductionQueryOrder SYNCTIME = new EdiProductionQueryOrder("syncTime");
/* 10 */   public static final EdiProductionQueryOrder TASKDATE = new EdiProductionQueryOrder("taskDate");
/*    */   
/*    */   protected EdiProductionQueryOrder(String name) {
/* 13 */     super(name);
/*    */   }
/*    */   
/*    */   public static EdiProductionQueryOrder getEnum(String value) {
/* 17 */     return (EdiProductionQueryOrder)getEnum(EdiProductionQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/EdiProductionQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */