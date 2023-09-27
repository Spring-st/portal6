/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WmsUnplannedWarehousingStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final WmsUnplannedWarehousingStatus WAIT = new WmsUnplannedWarehousingStatus(1, "wait", MetadataType.WMSUNPLANNEDWAREHOUSING);
/* 13 */   public static final WmsUnplannedWarehousingStatus CONFIRM = new WmsUnplannedWarehousingStatus(2, "confirm", MetadataType.WMSUNPLANNEDWAREHOUSING);
/* 14 */   public static final WmsUnplannedWarehousingStatus APPROVE = new WmsUnplannedWarehousingStatus(3, "approve", MetadataType.WMSUNPLANNEDWAREHOUSING);
/* 15 */   public static final WmsUnplannedWarehousingStatus REFUSE = new WmsUnplannedWarehousingStatus(4, "refuse", MetadataType.WMSUNPLANNEDWAREHOUSING);
/*    */ 
/*    */   
/*    */   public WmsUnplannedWarehousingStatus() {}
/*    */ 
/*    */   
/*    */   private WmsUnplannedWarehousingStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/WmsUnplannedWarehousingStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */