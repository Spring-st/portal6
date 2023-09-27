/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final RoleType GLOBAL_ADMIN = new RoleType(1, "Global Level", MetadataType.ROLE_TYPE);
/* 13 */   public static final RoleType SITE_ADMIN = new RoleType(2, "Site Level", MetadataType.ROLE_TYPE);
/* 14 */   public static final RoleType ENDUSER = new RoleType(3, "Department Level", MetadataType.ROLE_TYPE);
/* 15 */   public static final RoleType COMMON_GLOBAL_LEVEL = new RoleType(4, "Common Global Level", MetadataType.ROLE_TYPE);
/*    */ 
/*    */   
/*    */   public RoleType() {}
/*    */ 
/*    */   
/*    */   private RoleType(int metadataId, String defaultLabel, MetadataType type) {
/* 22 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/RoleType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */