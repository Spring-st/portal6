/*    */ package com.aof.model.kpi.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class KPISummaryQueryOrder extends Enum {
/*  6 */   public static final KPISummaryQueryOrder ID = new KPISummaryQueryOrder("id");
/*  7 */   public static final KPISummaryQueryOrder SUMMARYDATE = new KPISummaryQueryOrder("summaryDate");
/*  8 */   public static final KPISummaryQueryOrder LOGINUSERCOUNT = new KPISummaryQueryOrder("loginUserCount");
/*  9 */   public static final KPISummaryQueryOrder CLOSEDREQUESTNUMTODAY = new KPISummaryQueryOrder("closedRequestNumToday");
/* 10 */   public static final KPISummaryQueryOrder AVGREQUESTCLOSEDDAYS = new KPISummaryQueryOrder("avgRequestClosedDays");
/* 11 */   public static final KPISummaryQueryOrder CREATEDREQUESTNUMTODAY = new KPISummaryQueryOrder("createdRequestNumToday");
/*    */   
/*    */   protected KPISummaryQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static KPISummaryQueryOrder getEnum(String value) {
/* 18 */     return (KPISummaryQueryOrder)getEnum(KPISummaryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/kpi/query/KPISummaryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */