/*    */ package com.aof.model.kpi.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class KPISummaryQueryCondition
/*    */   extends Enum {
/*  7 */   public static final KPISummaryQueryCondition ID_EQ = new KPISummaryQueryCondition("id_eq");
/*    */   
/*  9 */   public static final KPISummaryQueryCondition SUMMARYDATE_EQ = new KPISummaryQueryCondition("summaryDate_eq");
/*    */   
/* 11 */   public static final KPISummaryQueryCondition SUMMARYDATE_GE = new KPISummaryQueryCondition("summaryDate_ge");
/*    */   
/* 13 */   public static final KPISummaryQueryCondition SUMMARYDATE_LE = new KPISummaryQueryCondition("summaryDate_le");
/*    */   
/*    */   protected KPISummaryQueryCondition(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static KPISummaryQueryCondition getEnum(String value) {
/* 20 */     return (KPISummaryQueryCondition)getEnum(KPISummaryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/kpi/query/KPISummaryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */