/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BoxAdjustmentType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BoxAdjustmentType BREAKUP = new BoxAdjustmentType(1, "break up", MetadataType.BOX_ADJUSTMENT_TYPE);
/* 12 */   public static final BoxAdjustmentType MERGE = new BoxAdjustmentType(2, "merge", MetadataType.BOX_ADJUSTMENT_TYPE);
/*    */ 
/*    */   
/*    */   public BoxAdjustmentType() {}
/*    */   
/*    */   private BoxAdjustmentType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BoxAdjustmentType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */