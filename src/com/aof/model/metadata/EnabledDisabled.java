/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnabledDisabled
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final EnabledDisabled ENABLED = new EnabledDisabled(0, "Enabled", MetadataType.ENABLED_DISABLED);
/* 13 */   public static final EnabledDisabled DISABLED = new EnabledDisabled(1, "Disabled", MetadataType.ENABLED_DISABLED);
/*    */ 
/*    */   
/*    */   public EnabledDisabled() {}
/*    */   
/*    */   private EnabledDisabled(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/EnabledDisabled.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */