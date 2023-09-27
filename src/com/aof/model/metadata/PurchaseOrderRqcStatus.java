/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderRqcStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderRqcStatus Wait = new PurchaseOrderRqcStatus(1, "Wait", MetadataType.PRUCHASE_ORDER_RQC_STATUS);
/* 12 */   public static final PurchaseOrderRqcStatus FINISH = new PurchaseOrderRqcStatus(2, "Finish", MetadataType.PRUCHASE_ORDER_RQC_STATUS);
/* 13 */   public static final PurchaseOrderRqcStatus CLOSE = new PurchaseOrderRqcStatus(3, "close", MetadataType.PRUCHASE_ORDER_RQC_STATUS);
/*    */ 
/*    */   
/*    */   public PurchaseOrderRqcStatus() {}
/*    */   
/*    */   private PurchaseOrderRqcStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderRqcStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */