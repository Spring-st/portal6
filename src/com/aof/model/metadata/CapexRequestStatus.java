/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CapexRequestStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final CapexRequestStatus DRAFT = new CapexRequestStatus(1, "Draft", MetadataType.CAPEX_REQUEST_STATUS);
/* 13 */   public static final CapexRequestStatus PENDING = new CapexRequestStatus(2, "Pending", MetadataType.CAPEX_REQUEST_STATUS);
/* 14 */   public static final CapexRequestStatus APPROVED = new CapexRequestStatus(3, "Approved", MetadataType.CAPEX_REQUEST_STATUS);
/* 15 */   public static final CapexRequestStatus REJECTED = new CapexRequestStatus(4, "Rejected", MetadataType.CAPEX_REQUEST_STATUS);
/*    */ 
/*    */   
/*    */   public CapexRequestStatus() {}
/*    */ 
/*    */   
/*    */   private CapexRequestStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/CapexRequestStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */