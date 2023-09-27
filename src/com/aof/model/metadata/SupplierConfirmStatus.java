/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SupplierConfirmStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final SupplierConfirmStatus NEW = new SupplierConfirmStatus(1, "New", MetadataType.SUPPLIER_CONFIRM_STATUS);
/* 13 */   public static final SupplierConfirmStatus MODIFY = new SupplierConfirmStatus(2, "Modify", MetadataType.SUPPLIER_CONFIRM_STATUS);
/*    */ 
/*    */   
/*    */   public SupplierConfirmStatus() {}
/*    */   
/*    */   private SupplierConfirmStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SupplierConfirmStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */