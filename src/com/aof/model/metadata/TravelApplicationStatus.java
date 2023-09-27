/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelApplicationStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final TravelApplicationStatus DRAFT = new TravelApplicationStatus(1, "Draft", MetadataType.TRAVEL_APPLICATION_STATUS);
/* 13 */   public static final TravelApplicationStatus PENDING = new TravelApplicationStatus(2, "Pending", MetadataType.TRAVEL_APPLICATION_STATUS);
/* 14 */   public static final TravelApplicationStatus APPROVED = new TravelApplicationStatus(3, "Approved", MetadataType.TRAVEL_APPLICATION_STATUS);
/* 15 */   public static final TravelApplicationStatus REJECTED = new TravelApplicationStatus(4, "Rejected", MetadataType.TRAVEL_APPLICATION_STATUS);
/*    */ 
/*    */ 
/*    */   
/*    */   public TravelApplicationStatus() {}
/*    */ 
/*    */ 
/*    */   
/*    */   private TravelApplicationStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/TravelApplicationStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */