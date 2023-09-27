/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PurchaseOrderType DOMESTIC = new PurchaseOrderType(1, "domestic", MetadataType.PURCHASE_ORDER_TYPE);
/* 12 */   public static final PurchaseOrderType FOREIGN = new PurchaseOrderType(2, "foreign", MetadataType.PURCHASE_ORDER_TYPE);
/*    */ 
/*    */   
/*    */   public PurchaseOrderType() {}
/*    */   
/*    */   private PurchaseOrderType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PurchaseOrderType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */