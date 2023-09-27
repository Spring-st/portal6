/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderPutInStorageStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderPutInStorageStatus UNFINISHED = new PurchaseOrderPutInStorageStatus(
/* 12 */       1, "unfinished", MetadataType.PRUCHASE_ORDER_PUTINSTORAGE_STATUS);
/* 13 */   public static final PurchaseOrderPutInStorageStatus HASBEENCOMPLETED = new PurchaseOrderPutInStorageStatus(
/* 14 */       2, "Has been completed", 
/* 15 */       MetadataType.PRUCHASE_ORDER_PUTINSTORAGE_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseOrderPutInStorageStatus() {}
/*    */ 
/*    */   
/*    */   private PurchaseOrderPutInStorageStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderPutInStorageStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */