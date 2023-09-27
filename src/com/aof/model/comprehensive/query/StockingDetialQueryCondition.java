/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingDetialQueryCondition
/*    */   extends Enum {
/*  7 */   public static final StockingDetialQueryCondition ID = new StockingDetialQueryCondition("id");
/*  8 */   public static final StockingDetialQueryCondition ENGNAME = new StockingDetialQueryCondition("engName");
/*  9 */   public static final StockingDetialQueryCondition CHNNAME = new StockingDetialQueryCondition("chnName");
/* 10 */   public static final StockingDetialQueryCondition ENABLED = new StockingDetialQueryCondition("enabled");
/* 11 */   public static final StockingDetialQueryCondition STOCKING_ID_EQ = new StockingDetialQueryCondition("stocking_id_eq");
/* 12 */   public static final StockingDetialQueryCondition LOCATION_ID_EQ = new StockingDetialQueryCondition("location_id_eq");
/* 13 */   public static final StockingDetialQueryCondition PART_IS_NOT_NULL = new StockingDetialQueryCondition("part_is_not_null");
/*    */   
/*    */   protected StockingDetialQueryCondition(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingDetialQueryCondition getEnum(String value) {
/* 20 */     return (StockingDetialQueryCondition)getEnum(StockingDetialQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingDetialQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */