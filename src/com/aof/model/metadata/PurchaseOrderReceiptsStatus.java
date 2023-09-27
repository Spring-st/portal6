/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderReceiptsStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderReceiptsStatus WAIT = new PurchaseOrderReceiptsStatus(1, "wait", MetadataType.PURCHASE_ORDER_RECEIPTS_STATUS);
/* 12 */   public static final PurchaseOrderReceiptsStatus UNFINISHED = new PurchaseOrderReceiptsStatus(2, "unfinished", MetadataType.PURCHASE_ORDER_RECEIPTS_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseOrderReceiptsStatus() {}
/*    */   
/*    */   private PurchaseOrderReceiptsStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderReceiptsStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */