/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StockingQueryOrder
/*    */   extends Enum {
/*  7 */   public static final StockingQueryOrder ID = new StockingQueryOrder("id");
/*  8 */   public static final StockingQueryOrder ENGNAME = new StockingQueryOrder("engName");
/*  9 */   public static final StockingQueryOrder CHNNAME = new StockingQueryOrder("chnName");
/* 10 */   public static final StockingQueryOrder ENABLED = new StockingQueryOrder("enabled");
/* 11 */   public static final StockingQueryOrder BOX_LOT_ID = new StockingQueryOrder("box_lot_id");
/* 12 */   public static final StockingQueryOrder LOCATION_ID = new StockingQueryOrder("location_id");
/*    */   
/*    */   protected StockingQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static StockingQueryOrder getEnum(String value) {
/* 19 */     return (StockingQueryOrder)getEnum(StockingQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/StockingQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */