/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlightClass
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final FlightClass FIRST = new FlightClass(1, "First", MetadataType.FLIGHT_CLASS);
/* 13 */   public static final FlightClass BUSINESS = new FlightClass(2, "Business", MetadataType.FLIGHT_CLASS);
/* 14 */   public static final FlightClass ECONOMY = new FlightClass(3, "Economy", MetadataType.FLIGHT_CLASS);
/*    */ 
/*    */   
/*    */   public FlightClass() {}
/*    */ 
/*    */   
/*    */   private FlightClass(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/FlightClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */