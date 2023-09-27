/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortalShipOrderStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final PortalShipOrderStatus DRAFT = new PortalShipOrderStatus(0, "Draft", MetadataType.PORTALSHIPORDER_STATUS);
/* 13 */   public static final PortalShipOrderStatus ON_PASSAGE = new PortalShipOrderStatus(1, "On Passage", MetadataType.PORTALSHIPORDER_STATUS);
/* 14 */   public static final PortalShipOrderStatus RECEIVE = new PortalShipOrderStatus(2, "Receive", MetadataType.PORTALSHIPORDER_STATUS);
/*    */ 
/*    */   
/*    */   public PortalShipOrderStatus() {}
/*    */ 
/*    */   
/*    */   private PortalShipOrderStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PortalShipOrderStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */