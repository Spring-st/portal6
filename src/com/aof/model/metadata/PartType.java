/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PartType SHOE = new PartType(1, "shoe", MetadataType.PARTTYPE);
/* 12 */   public static final PartType HUB = new PartType(2, "hub", MetadataType.PARTTYPE);
/* 13 */   public static final PartType OTHER = new PartType(3, "other", MetadataType.PARTTYPE);
/*    */ 
/*    */   
/*    */   public PartType() {}
/*    */ 
/*    */   
/*    */   private PartType(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PartType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */