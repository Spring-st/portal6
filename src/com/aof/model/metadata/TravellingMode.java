/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravellingMode
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final TravellingMode AIR = new TravellingMode(1, "Air Plane", MetadataType.TRAVELLING_MODE);
/* 13 */   public static final TravellingMode BUS = new TravellingMode(2, "Coach", MetadataType.TRAVELLING_MODE);
/* 14 */   public static final TravellingMode TRAIN = new TravellingMode(3, "Train", MetadataType.TRAVELLING_MODE);
/* 15 */   public static final TravellingMode SHIP = new TravellingMode(4, "Ship", MetadataType.TRAVELLING_MODE);
/*    */ 
/*    */   
/*    */   public TravellingMode() {}
/*    */ 
/*    */   
/*    */   private TravellingMode(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/TravellingMode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */