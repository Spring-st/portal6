/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApproveStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final ApproveStatus NOT_YOUR_TURN = new ApproveStatus(0, "Not your turn", MetadataType.APPROVE_STATUS);
/* 13 */   public static final ApproveStatus WAITING_FOR_APPROVE = new ApproveStatus(1, "Waiting for Approve", MetadataType.APPROVE_STATUS);
/* 14 */   public static final ApproveStatus APPROVED = new ApproveStatus(2, "Approved", MetadataType.APPROVE_STATUS);
/* 15 */   public static final ApproveStatus REJECTED = new ApproveStatus(3, "Rejected", MetadataType.APPROVE_STATUS);
/*    */ 
/*    */   
/*    */   public ApproveStatus() {}
/*    */ 
/*    */   
/*    */   private ApproveStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ApproveStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */