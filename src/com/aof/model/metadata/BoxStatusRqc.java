/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BoxStatusRqc
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BoxStatusRqc NotTheQuality = new BoxStatusRqc(1, "Not the quality", MetadataType.BOX_STATUS_RQC);
/* 12 */   public static final BoxStatusRqc QUALIFIED = new BoxStatusRqc(2, "qualified", MetadataType.BOX_STATUS_RQC);
/* 13 */   public static final BoxStatusRqc UNQUALIFIED = new BoxStatusRqc(3, "unqualified", MetadataType.BOX_STATUS_RQC);
/* 14 */   public static final BoxStatusRqc EXEMPTION = new BoxStatusRqc(4, "exemption", MetadataType.BOX_STATUS_RQC);
/*    */ 
/*    */   
/*    */   public BoxStatusRqc() {}
/*    */   
/*    */   private BoxStatusRqc(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BoxStatusRqc.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */