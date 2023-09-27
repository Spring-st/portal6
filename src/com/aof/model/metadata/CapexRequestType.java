/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CapexRequestType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final CapexRequestType INITIAL = new CapexRequestType(1, "Initial", MetadataType.CAPEX_REQUEST_TYPE);
/* 13 */   public static final CapexRequestType INCREASE = new CapexRequestType(2, "Increase", MetadataType.CAPEX_REQUEST_TYPE);
/*    */ 
/*    */   
/*    */   public CapexRequestType() {}
/*    */ 
/*    */   
/*    */   private CapexRequestType(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/CapexRequestType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */