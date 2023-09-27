/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseRequestStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final PurchaseRequestStatus DRAFT = new PurchaseRequestStatus(1, "Draft", MetadataType.PURCHASE_REQUEST_STATUS);
/* 13 */   public static final PurchaseRequestStatus PENDING = new PurchaseRequestStatus(2, "Pending", MetadataType.PURCHASE_REQUEST_STATUS);
/* 14 */   public static final PurchaseRequestStatus APPROVED = new PurchaseRequestStatus(3, "Approved", MetadataType.PURCHASE_REQUEST_STATUS);
/* 15 */   public static final PurchaseRequestStatus REJECTED = new PurchaseRequestStatus(4, "Rejected", MetadataType.PURCHASE_REQUEST_STATUS);
/* 16 */   public static final PurchaseRequestStatus BOOKED = new PurchaseRequestStatus(5, "Booked", MetadataType.PURCHASE_REQUEST_STATUS);
/* 17 */   public static final PurchaseRequestStatus CLOSED = new PurchaseRequestStatus(6, "Closed", MetadataType.PURCHASE_REQUEST_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseRequestStatus() {}
/*    */ 
/*    */   
/*    */   private PurchaseRequestStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseRequestStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */