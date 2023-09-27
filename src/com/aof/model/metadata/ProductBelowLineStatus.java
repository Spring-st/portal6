/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductBelowLineStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ProductBelowLineStatus WAIT = new ProductBelowLineStatus(
/* 12 */       1, "wait", MetadataType.PRODUCTBELOWLINE_STATUS);
/* 13 */   public static final ProductBelowLineStatus FINISH = new ProductBelowLineStatus(
/* 14 */       2, "finish", MetadataType.PRODUCTBELOWLINE_STATUS);
/*    */ 
/*    */   
/*    */   public ProductBelowLineStatus() {}
/*    */ 
/*    */   
/*    */   private ProductBelowLineStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ProductBelowLineStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */