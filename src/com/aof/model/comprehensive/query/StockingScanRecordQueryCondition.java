/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingScanRecordQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final StockingScanRecordQueryCondition ID_EQ = new StockingScanRecordQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final StockingScanRecordQueryCondition STOCKING_EQ = new StockingScanRecordQueryCondition(
/* 11 */       "stocking_eq");
/*    */ 
/*    */   
/*    */   protected StockingScanRecordQueryCondition(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingScanRecordQueryCondition getEnum(String value) {
/* 19 */     return (StockingScanRecordQueryCondition)getEnum(
/* 20 */         StockingScanRecordQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingScanRecordQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */