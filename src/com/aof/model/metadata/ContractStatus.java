/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContractStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final ContractStatus NOT_ACTIVED = new ContractStatus(1, "Not Actived", MetadataType.CONTRACT_STATUS);
/* 13 */   public static final ContractStatus ACTIVED = new ContractStatus(2, "Actived", MetadataType.CONTRACT_STATUS);
/* 14 */   public static final ContractStatus EXPIRED = new ContractStatus(3, "Expired", MetadataType.CONTRACT_STATUS);
/*    */ 
/*    */   
/*    */   public ContractStatus() {}
/*    */ 
/*    */   
/*    */   private ContractStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ContractStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */