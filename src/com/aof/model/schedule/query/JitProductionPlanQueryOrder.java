/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitProductionPlanQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final JitProductionPlanQueryOrder ID = new JitProductionPlanQueryOrder("id");
/*  9 */   public static final JitProductionPlanQueryOrder DATE = new JitProductionPlanQueryOrder("date");
/* 10 */   public static final JitProductionPlanQueryOrder CURRENTQTY = new JitProductionPlanQueryOrder("currentQty");
/* 11 */   public static final JitProductionPlanQueryOrder HOUR2DEMANDQTY = new JitProductionPlanQueryOrder("hour2DemandQty");
/* 12 */   public static final JitProductionPlanQueryOrder HOUR4DEMANDQTY = new JitProductionPlanQueryOrder("hour4DemandQty");
/* 13 */   public static final JitProductionPlanQueryOrder HOUR6DEMANDQTY = new JitProductionPlanQueryOrder("hour6DemandQty");
/* 14 */   public static final JitProductionPlanQueryOrder HOUR8DEMANDQTY = new JitProductionPlanQueryOrder("hour8DemandQty");
/* 15 */   public static final JitProductionPlanQueryOrder HOUR10DEMANDQTY = new JitProductionPlanQueryOrder("hour10DemandQty");
/* 16 */   public static final JitProductionPlanQueryOrder HOUR12DEMANDQTY = new JitProductionPlanQueryOrder("hour12DemandQty");
/* 17 */   public static final JitProductionPlanQueryOrder HOUR14DEMANDQTY = new JitProductionPlanQueryOrder("hour14DemandQty");
/* 18 */   public static final JitProductionPlanQueryOrder HOUR16DEMANDQTY = new JitProductionPlanQueryOrder("hour16DemandQty");
/* 19 */   public static final JitProductionPlanQueryOrder HOUR18DEMANDQTY = new JitProductionPlanQueryOrder("hour18DemandQty");
/* 20 */   public static final JitProductionPlanQueryOrder HOUR20DEMANDQTY = new JitProductionPlanQueryOrder("hour20DemandQty");
/* 21 */   public static final JitProductionPlanQueryOrder HOUR22DEMANDQTY = new JitProductionPlanQueryOrder("hour22DemandQty");
/* 22 */   public static final JitProductionPlanQueryOrder HOUR24DEMANDQTY = new JitProductionPlanQueryOrder("hour24DemandQty");
/*    */   
/*    */   protected JitProductionPlanQueryOrder(String name) {
/* 25 */     super(name);
/*    */   }
/*    */   
/*    */   public static JitProductionPlanQueryOrder getEnum(String value) {
/* 29 */     return (JitProductionPlanQueryOrder)getEnum(JitProductionPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/JitProductionPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */