/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PartForecastNeedReportQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PartForecastNeedReportQueryOrder PART_ID = new PartForecastNeedReportQueryOrder("part_Id");
/*  9 */   public static final PartForecastNeedReportQueryOrder PART_productspecifications = new PartForecastNeedReportQueryOrder("productspecifications");
/* 10 */   public static final PartForecastNeedReportQueryOrder PART_drwgLoc = new PartForecastNeedReportQueryOrder("part_drwgLoc");
/*    */   protected PartForecastNeedReportQueryOrder(String name) {
/* 12 */     super(name);
/*    */   }
/*    */   
/*    */   public static PartForecastNeedReportQueryOrder getEnum(String value) {
/* 16 */     return (PartForecastNeedReportQueryOrder)getEnum(PartForecastNeedReportQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/PartForecastNeedReportQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */