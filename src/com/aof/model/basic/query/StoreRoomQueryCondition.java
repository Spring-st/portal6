/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StoreRoomQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final StoreRoomQueryCondition ID_EQ = new StoreRoomQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final StoreRoomQueryCondition DESCRIBE_EQ = new StoreRoomQueryCondition(
/* 11 */       "describe_eq");
/* 12 */   public static final StoreRoomQueryCondition ADDRESS_EQ = new StoreRoomQueryCondition(
/* 13 */       "address_eq");
/* 14 */   public static final StoreRoomQueryCondition TYPE_EQ = new StoreRoomQueryCondition(
/* 15 */       "type_eq");
/* 16 */   public static final StoreRoomQueryCondition ENABLED_EQ = new StoreRoomQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final StoreRoomQueryCondition SITE_EQ = new StoreRoomQueryCondition(
/* 19 */       "site_eq");
/* 20 */   public static final StoreRoomQueryCondition SAFETYINVENTORY_EQ = new StoreRoomQueryCondition(
/* 21 */       "safety_inventory_eq");
/*    */   
/*    */   protected StoreRoomQueryCondition(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static StoreRoomQueryCondition getEnum(String value) {
/* 28 */     return (StoreRoomQueryCondition)getEnum(StoreRoomQueryCondition.class, 
/* 29 */         value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/StoreRoomQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */