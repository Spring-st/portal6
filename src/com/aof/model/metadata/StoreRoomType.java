/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StoreRoomType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final StoreRoomType ENTREPOTSTORAGE = new StoreRoomType(1, "entrepot storage", MetadataType.STOREROOM_TYPE);
/* 12 */   public static final StoreRoomType WAITINGFORTHELIBRARY = new StoreRoomType(2, "Waiting for the library", MetadataType.STOREROOM_TYPE);
/* 13 */   public static final StoreRoomType RETURNOFTHELIBRARY = new StoreRoomType(3, "Return of the library", MetadataType.STOREROOM_TYPE);
/* 14 */   public static final StoreRoomType RAWMATERIALSLIBRARY = new StoreRoomType(4, "Raw materials library", MetadataType.STOREROOM_TYPE);
/* 15 */   public static final StoreRoomType RAWMATERIALSLINE = new StoreRoomType(5, "Raw materials line", MetadataType.STOREROOM_TYPE);
/* 16 */   public static final StoreRoomType FINISHEDPRODUCELINEALONGTHE = new StoreRoomType(6, "finished product line along the", MetadataType.STOREROOM_TYPE);
/* 17 */   public static final StoreRoomType BADPRODUCTLIBRARY = new StoreRoomType(7, "Bad product library", MetadataType.STOREROOM_TYPE);
/* 18 */   public static final StoreRoomType DETEMINETHELIBRARY = new StoreRoomType(8, "determine the library", MetadataType.STOREROOM_TYPE);
/* 19 */   public static final StoreRoomType SHIPPINGDEPARTMENT = new StoreRoomType(9, "Shipping department", MetadataType.STOREROOM_TYPE);
/* 20 */   public static final StoreRoomType SORTINGLINE = new StoreRoomType(10, "Sorting line", MetadataType.STOREROOM_TYPE);
/* 21 */   public static final StoreRoomType PRODUCELINE = new StoreRoomType(11, "produce line", MetadataType.STOREROOM_TYPE);
/* 22 */   public static final StoreRoomType HIGHLINE = new StoreRoomType(12, "high line", MetadataType.STOREROOM_TYPE);
/* 23 */   public static final StoreRoomType OUTLOCATION = new StoreRoomType(13, "produce out location", MetadataType.STOREROOM_TYPE);
/* 24 */   public static final StoreRoomType ACCOUNTBADPRODUCTLIBRARY = new StoreRoomType(14, "Account bad product library", MetadataType.STOREROOM_TYPE);
/*    */ 
/*    */   
/*    */   public StoreRoomType() {}
/*    */ 
/*    */   
/*    */   private StoreRoomType(int metadataId, String defaultLabel, MetadataType type) {
/* 31 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/StoreRoomType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */