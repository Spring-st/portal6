/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SupplierPromoteStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final SupplierPromoteStatus GLOBAL = new SupplierPromoteStatus(1, "Global", MetadataType.SUPPLIER_PROMOTE_STATUS);
/* 13 */   public static final SupplierPromoteStatus SITE = new SupplierPromoteStatus(2, "Site", MetadataType.SUPPLIER_PROMOTE_STATUS);
/* 14 */   public static final SupplierPromoteStatus REQUEST = new SupplierPromoteStatus(3, "Promote Requested", MetadataType.SUPPLIER_PROMOTE_STATUS);
/*    */ 
/*    */   
/*    */   public SupplierPromoteStatus() {}
/*    */ 
/*    */   
/*    */   private SupplierPromoteStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SupplierPromoteStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */