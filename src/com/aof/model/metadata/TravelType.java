/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final TravelType LOCAL = new TravelType(0, "Local", MetadataType.TRAVEL_TYPE);
/* 13 */   public static final TravelType DOMESTIC = new TravelType(1, "Domestic", MetadataType.TRAVEL_TYPE);
/* 14 */   public static final TravelType OVERSEA = new TravelType(2, "Oversea", MetadataType.TRAVEL_TYPE);
/*    */ 
/*    */   
/*    */   public TravelType() {}
/*    */ 
/*    */   
/*    */   private TravelType(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/TravelType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */