/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelApplicationBookStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final TravelApplicationBookStatus NA = new TravelApplicationBookStatus(1, "----", MetadataType.TRAVEL_APPLICATION_BOOK_STATUS);
/* 13 */   public static final TravelApplicationBookStatus BOOKED = new TravelApplicationBookStatus(2, "Booked", MetadataType.TRAVEL_APPLICATION_BOOK_STATUS);
/* 14 */   public static final TravelApplicationBookStatus RECEIVED = new TravelApplicationBookStatus(3, "Received", MetadataType.TRAVEL_APPLICATION_BOOK_STATUS);
/*    */ 
/*    */   
/*    */   public TravelApplicationBookStatus() {}
/*    */ 
/*    */   
/*    */   private TravelApplicationBookStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/TravelApplicationBookStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */