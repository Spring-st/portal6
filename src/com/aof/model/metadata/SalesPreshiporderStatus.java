/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ public class SalesPreshiporderStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/*  6 */   public static final SalesPreshiporderStatus ON_MATERIAL = new SalesPreshiporderStatus(1, "On Material", MetadataType.SALESPRESHIPORDER_STATUS);
/*  7 */   public static final SalesPreshiporderStatus IN_DELIVERY = new SalesPreshiporderStatus(2, "In Delivery", MetadataType.SALESPRESHIPORDER_STATUS);
/*    */ 
/*    */ 
/*    */   
/*    */   public SalesPreshiporderStatus() {}
/*    */ 
/*    */   
/*    */   private SalesPreshiporderStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 15 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SalesPreshiporderStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */