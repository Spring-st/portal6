/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BadReasonsStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BadReasonsStatus NotTheQuality = new BadReasonsStatus(1, "Not the quality", MetadataType.BADREASONS_STATUS);
/* 12 */   public static final BadReasonsStatus QUALIFIED = new BadReasonsStatus(2, "qualified", MetadataType.BADREASONS_STATUS);
/* 13 */   public static final BadReasonsStatus UNQUALIFIED = new BadReasonsStatus(3, "unqualified", MetadataType.BADREASONS_STATUS);
/* 14 */   public static final BadReasonsStatus EXEMPTION = new BadReasonsStatus(4, "exemption", MetadataType.BADREASONS_STATUS);
/*    */ 
/*    */   
/*    */   public BadReasonsStatus() {}
/*    */   
/*    */   private BadReasonsStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BadReasonsStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */