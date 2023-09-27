/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryRecordType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final InventoryRecordType PROCUREMENTWAREHOUSING = new InventoryRecordType(
/* 12 */       1, "Procurement warehousing", MetadataType.INVENTORY_RECORD_TYPE);
/* 13 */   public static final InventoryRecordType ACQUISITIONOFWAREHOUSE = new InventoryRecordType(
/* 14 */       2, "Acquisition of warehouse", MetadataType.INVENTORY_RECORD_TYPE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InventoryRecordType() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private InventoryRecordType(int metadataId, String defaultLabel, MetadataType type) {
/* 30 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/InventoryRecordType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */