/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final StockingQueryCondition ID_EQ = new StockingQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final StockingQueryCondition ID_BEGINWITH = new StockingQueryCondition(
/* 11 */       "id_beginwith");
/* 12 */   public static final StockingQueryCondition DESCRIBE_EQ = new StockingQueryCondition(
/* 13 */       "describe_eq");
/* 14 */   public static final StockingQueryCondition ENABLED_EQ = new StockingQueryCondition(
/* 15 */       "enabled_eq");
/* 16 */   public static final StockingQueryCondition CODE_EQ = new StockingQueryCondition(
/* 17 */       "code_eq");
/* 18 */   public static final StockingQueryCondition DELEIVER_EQ = new StockingQueryCondition(
/* 19 */       "deliver_eq");
/* 20 */   public static final StockingQueryCondition DEATETIME_EQ = new StockingQueryCondition(
/* 21 */       "datetime_eq");
/* 22 */   public static final StockingQueryCondition ENDTIME_EQ = new StockingQueryCondition(
/* 23 */       "endtime_eq");
/* 24 */   public static final StockingQueryCondition STATUS_EQ = new StockingQueryCondition(
/* 25 */       "status_eq");
/* 26 */   public static final StockingQueryCondition USER_EQ = new StockingQueryCondition(
/* 27 */       "user_eq");
/* 28 */   public static final StockingQueryCondition TYPE_EQ = new StockingQueryCondition(
/* 29 */       "type_eq");
/* 30 */   public static final StockingQueryCondition STOCKING_ID_EQ = new StockingQueryCondition(
/* 31 */       "stocking_id_eq");
/* 32 */   public static final StockingQueryCondition BOX_LOT_ID_EQ = new StockingQueryCondition(
/* 33 */       "box_lot_id_eq");
/* 34 */   public static final StockingQueryCondition TYPE_IS_NOT_NULL = new StockingQueryCondition(
/* 35 */       "type_is_not_null");
/* 36 */   public static final StockingQueryCondition STOCKINGRECORD_TYPE_EQ = new StockingQueryCondition(
/* 37 */       "stockingrecord_type_eq");
/* 38 */   public static final StockingQueryCondition STOCKINGRECORD_STATUS_NOT_EQ = new StockingQueryCondition(
/* 39 */       "stockingrecord_status_eq");
/* 40 */   public static final StockingQueryCondition STOCKINGRECORD_DIFFERENCES_GT = new StockingQueryCondition(
/* 41 */       "stockingrecord_differences_gt");
/* 42 */   public static final StockingQueryCondition STOCKINGRECORD_DIFFERENCES_LT = new StockingQueryCondition(
/* 43 */       "stockingrecord_differences_lt");
/*    */   
/*    */   protected StockingQueryCondition(String value) {
/* 46 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingQueryCondition getEnum(String value) {
/* 50 */     return (StockingQueryCondition)getEnum(
/* 51 */         StockingQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */