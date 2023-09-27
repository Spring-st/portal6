/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrganizationType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final OrganizationType REGIONAL = new OrganizationType(1, "Regional", MetadataType.ORGANIZATION_TYPE);
/* 12 */   public static final OrganizationType SITE = new OrganizationType(2, "Site", MetadataType.ORGANIZATION_TYPE);
/* 13 */   public static final OrganizationType DEPARTMENT = new OrganizationType(3, "Department", MetadataType.ORGANIZATION_TYPE);
/*    */ 
/*    */   
/*    */   public OrganizationType() {}
/*    */   
/*    */   private OrganizationType(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/OrganizationType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */