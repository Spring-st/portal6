/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BoxStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BoxStatus Wait = new BoxStatus(1, "wait", MetadataType.BOX_STATUS);
/* 12 */   public static final BoxStatus HAVETHEGOODS = new BoxStatus(2, "Have the goods", MetadataType.BOX_STATUS);
/* 13 */   public static final BoxStatus HASBEENINTO = new BoxStatus(3, "Has been into", MetadataType.BOX_STATUS);
/* 14 */   public static final BoxStatus HASTHE = new BoxStatus(4, "Has the", MetadataType.BOX_STATUS);
/* 15 */   public static final BoxStatus WMSPLANTOGOOUT = new BoxStatus(5, "plan to out", MetadataType.BOX_STATUS);
/* 16 */   public static final BoxStatus Withdraw = new BoxStatus(9, "Withdraw", MetadataType.BOX_STATUS);
/*    */   
/*    */   public BoxStatus() {}
/*    */   
/*    */   private BoxStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BoxStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */