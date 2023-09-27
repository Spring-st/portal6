/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PoHighLineStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final PoHighLineStatus HIGHLINE = new PoHighLineStatus(1, "high line", MetadataType.POHIGHLINESTATUS);
/* 12 */   public static final PoHighLineStatus BELOWLINE = new PoHighLineStatus(2, "below line", MetadataType.POHIGHLINESTATUS);
/*    */ 
/*    */   
/*    */   public PoHighLineStatus() {}
/*    */   
/*    */   private PoHighLineStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/PoHighLineStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */