/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderReceiptsType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderReceiptsType NORMAL = new PurchaseOrderReceiptsType(1, "normal", MetadataType.PURCHASE_ORDER_RECEIPTS_TYPE);
/* 12 */   public static final PurchaseOrderReceiptsType TRANSIT = new PurchaseOrderReceiptsType(2, "transit", MetadataType.PURCHASE_ORDER_RECEIPTS_TYPE);
/*    */ 
/*    */   
/*    */   public PurchaseOrderReceiptsType() {}
/*    */   
/*    */   private PurchaseOrderReceiptsType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderReceiptsType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */