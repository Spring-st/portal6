/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HotelPromoteStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final HotelPromoteStatus GLOBAL = new HotelPromoteStatus(1, "Global", MetadataType.HOTEL_PROMOTE_STATUS);
/* 13 */   public static final HotelPromoteStatus SITE = new HotelPromoteStatus(2, "Site", MetadataType.HOTEL_PROMOTE_STATUS);
/* 14 */   public static final HotelPromoteStatus REQUEST = new HotelPromoteStatus(3, "Promote Requested", MetadataType.HOTEL_PROMOTE_STATUS);
/*    */ 
/*    */   
/*    */   public HotelPromoteStatus() {}
/*    */   
/*    */   private HotelPromoteStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/HotelPromoteStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */