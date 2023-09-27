/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final PurchaseOrderStatus WAIT = new PurchaseOrderStatus(1, "wait", MetadataType.PURCHASE_ORDER_STATUS);
/* 13 */   public static final PurchaseOrderStatus CLOSE = new PurchaseOrderStatus(2, "close", MetadataType.PURCHASE_ORDER_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseOrderStatus() {}
/*    */ 
/*    */   
/*    */   private PurchaseOrderStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */