/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StoreRoomQueryOrder
/*    */   extends Enum {
/*  7 */   public static final StoreRoomQueryOrder ID = new StoreRoomQueryOrder("id");
/*  8 */   public static final StoreRoomQueryOrder ENGNAME = new StoreRoomQueryOrder("engName");
/*  9 */   public static final StoreRoomQueryOrder CHNNAME = new StoreRoomQueryOrder("chnName");
/* 10 */   public static final StoreRoomQueryOrder ENABLED = new StoreRoomQueryOrder("enabled");
/*    */   
/*    */   protected StoreRoomQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static StoreRoomQueryOrder getEnum(String value) {
/* 17 */     return (StoreRoomQueryOrder)getEnum(StoreRoomQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/StoreRoomQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */