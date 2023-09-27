/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WmsPartType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final WmsPartType OPEN = new WmsPartType(1, "ԭ����", 
/* 12 */       MetadataType.WMSPART_TYPE);
/* 13 */   public static final WmsPartType HASBEENINTO = new WmsPartType(2, "��Ʒ", 
/* 14 */       MetadataType.WMSPART_TYPE);
/*    */ 
/*    */   
/*    */   public WmsPartType() {}
/*    */   
/*    */   private WmsPartType(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/WmsPartType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */