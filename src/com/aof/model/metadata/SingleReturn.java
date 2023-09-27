/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleReturn
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final SingleReturn SINGLE = new SingleReturn(1, "Single", MetadataType.SINGLE_RETURN);
/* 13 */   public static final SingleReturn RETURN = new SingleReturn(2, "Return", MetadataType.SINGLE_RETURN);
/*    */ 
/*    */   
/*    */   public SingleReturn() {}
/*    */ 
/*    */   
/*    */   private SingleReturn(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SingleReturn.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */