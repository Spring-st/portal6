/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingScanRecordQueryOrder
/*    */   extends Enum {
/*  7 */   public static final StockingScanRecordQueryOrder ID = new StockingScanRecordQueryOrder("id");
/*    */   
/*    */   protected StockingScanRecordQueryOrder(String value) {
/* 10 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingScanRecordQueryOrder getEnum(String value) {
/* 14 */     return (StockingScanRecordQueryOrder)getEnum(StockingScanRecordQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingScanRecordQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */