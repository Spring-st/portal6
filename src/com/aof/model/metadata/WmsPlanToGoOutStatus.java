/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WmsPlanToGoOutStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final WmsPlanToGoOutStatus WAIT = new WmsPlanToGoOutStatus(1, "wait", MetadataType.WMSPLANTOGOOUTSTATUS);
/* 13 */   public static final WmsPlanToGoOutStatus CONFIRM = new WmsPlanToGoOutStatus(2, "confirm", MetadataType.WMSPLANTOGOOUTSTATUS);
/* 14 */   public static final WmsPlanToGoOutStatus APPROVE = new WmsPlanToGoOutStatus(3, "approve", MetadataType.WMSPLANTOGOOUTSTATUS);
/* 15 */   public static final WmsPlanToGoOutStatus REFUSE = new WmsPlanToGoOutStatus(4, "refuse", MetadataType.WMSPLANTOGOOUTSTATUS);
/*    */ 
/*    */   
/*    */   public WmsPlanToGoOutStatus() {}
/*    */ 
/*    */   
/*    */   private WmsPlanToGoOutStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/WmsPlanToGoOutStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */