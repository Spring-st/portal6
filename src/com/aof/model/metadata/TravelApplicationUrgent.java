/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelApplicationUrgent
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final TravelApplicationUrgent NORMAL = new TravelApplicationUrgent(1, "Normal", MetadataType.TRAVEL_APPLICATION_URGENT);
/* 13 */   public static final TravelApplicationUrgent URGENT = new TravelApplicationUrgent(2, "Urgent", MetadataType.TRAVEL_APPLICATION_URGENT);
/*    */ 
/*    */   
/*    */   public TravelApplicationUrgent() {}
/*    */   
/*    */   private TravelApplicationUrgent(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/TravelApplicationUrgent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */