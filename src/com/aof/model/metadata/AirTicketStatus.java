/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AirTicketStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final AirTicketStatus BOOKED = new AirTicketStatus(1, "Booked", MetadataType.AIR_TICKET_STATUS);
/* 13 */   public static final AirTicketStatus RECEIVED = new AirTicketStatus(2, "Received", MetadataType.AIR_TICKET_STATUS);
/* 14 */   public static final AirTicketStatus CANCELLED = new AirTicketStatus(3, "Cancelled", MetadataType.AIR_TICKET_STATUS);
/*    */ 
/*    */   
/*    */   public AirTicketStatus() {}
/*    */ 
/*    */   
/*    */   private AirTicketStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/AirTicketStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */