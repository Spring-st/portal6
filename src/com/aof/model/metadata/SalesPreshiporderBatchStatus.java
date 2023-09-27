/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ public class SalesPreshiporderBatchStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/*  6 */   public static final SalesPreshiporderBatchStatus ON_MATERIALBATCH = new SalesPreshiporderBatchStatus(1, "On MaterialBatch", MetadataType.SALESPRESHIPORDERBATCH_STATUS);
/*  7 */   public static final SalesPreshiporderBatchStatus ON_LOADINGBATCH = new SalesPreshiporderBatchStatus(2, "On LoadingBatch", MetadataType.SALESPRESHIPORDERBATCH_STATUS);
/*  8 */   public static final SalesPreshiporderBatchStatus IN_DELIVERYBATCH = new SalesPreshiporderBatchStatus(3, "In DeliveryBatch", MetadataType.SALESPRESHIPORDERBATCH_STATUS);
/*  9 */   public static final SalesPreshiporderBatchStatus SALES_RETURN = new SalesPreshiporderBatchStatus(4, "Sales Return", MetadataType.SALESPRESHIPORDERBATCH_STATUS);
/*    */ 
/*    */   
/*    */   public SalesPreshiporderBatchStatus() {}
/*    */ 
/*    */   
/*    */   private SalesPreshiporderBatchStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 16 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SalesPreshiporderBatchStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */