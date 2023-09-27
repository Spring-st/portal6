/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderCondimentSingleStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderCondimentSingleStatus Wait = new PurchaseOrderCondimentSingleStatus(
/* 12 */       1, "Wait", MetadataType.PURCHASEORDERCONDIMENTSINGLE_STATUS);
/* 13 */   public static final PurchaseOrderCondimentSingleStatus HasBeenCreated = new PurchaseOrderCondimentSingleStatus(
/* 14 */       2, "Has been created", MetadataType.PURCHASEORDERCONDIMENTSINGLE_STATUS);
/* 15 */   public static final PurchaseOrderCondimentSingleStatus Close = new PurchaseOrderCondimentSingleStatus(
/* 16 */       3, "Close", MetadataType.PURCHASEORDERCONDIMENTSINGLE_STATUS);
/* 17 */   public static final PurchaseOrderCondimentSingleStatus Withdraw = new PurchaseOrderCondimentSingleStatus(
/* 18 */       9, "9", MetadataType.PURCHASEORDERCONDIMENTSINGLE_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseOrderCondimentSingleStatus() {}
/*    */   
/*    */   private PurchaseOrderCondimentSingleStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderCondimentSingleStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */