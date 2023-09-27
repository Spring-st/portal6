/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingDetialQueryOrder
/*    */   extends Enum {
/*  7 */   public static final StockingDetialQueryOrder ID = new StockingDetialQueryOrder("id");
/*  8 */   public static final StockingDetialQueryOrder ENGNAME = new StockingDetialQueryOrder("engName");
/*  9 */   public static final StockingDetialQueryOrder CHNNAME = new StockingDetialQueryOrder("chnName");
/* 10 */   public static final StockingDetialQueryOrder ENABLED = new StockingDetialQueryOrder("enabled");
/*    */   
/*    */   protected StockingDetialQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingDetialQueryOrder getEnum(String value) {
/* 17 */     return (StockingDetialQueryOrder)getEnum(StockingDetialQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingDetialQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */