/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class YesNo
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final YesNo YES = new YesNo(0, "Yes", MetadataType.YES_NO);
/* 13 */   public static final YesNo NO = new YesNo(1, "No", MetadataType.YES_NO);
/*    */ 
/*    */   
/*    */   public YesNo() {}
/*    */ 
/*    */   
/*    */   private YesNo(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equalsYesNo(YesNo yn) {
/* 25 */     return equals(yn);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/YesNo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */