/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnplannedOutboundStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final UnplannedOutboundStatus WAIT = new UnplannedOutboundStatus(1, "wait", MetadataType.UNPLANNEDOUTBOUND_STATUS);
/* 12 */   public static final UnplannedOutboundStatus CONFIRM = new UnplannedOutboundStatus(2, "confirm", MetadataType.UNPLANNEDOUTBOUND_STATUS);
/* 13 */   public static final UnplannedOutboundStatus HASTHE = new UnplannedOutboundStatus(3, "Has the", MetadataType.UNPLANNEDOUTBOUND_STATUS);
/*    */ 
/*    */   
/*    */   public UnplannedOutboundStatus() {}
/*    */   
/*    */   private UnplannedOutboundStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/UnplannedOutboundStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */