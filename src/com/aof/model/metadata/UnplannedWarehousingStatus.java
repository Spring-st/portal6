/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnplannedWarehousingStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final UnplannedWarehousingStatus WAIT = new UnplannedWarehousingStatus(1, "wait", MetadataType.UNPLANNEDWAREHOUSING_STATUS);
/* 12 */   public static final UnplannedWarehousingStatus CONFIRM = new UnplannedWarehousingStatus(2, "Has been into", MetadataType.UNPLANNEDWAREHOUSING_STATUS);
/* 13 */   public static final UnplannedWarehousingStatus HASTHE = new UnplannedWarehousingStatus(3, "Has the", MetadataType.UNPLANNEDWAREHOUSING_STATUS);
/*    */ 
/*    */   
/*    */   public UnplannedWarehousingStatus() {}
/*    */   
/*    */   private UnplannedWarehousingStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/UnplannedWarehousingStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */