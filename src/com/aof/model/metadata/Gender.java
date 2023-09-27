/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Gender
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final Gender MALE = new Gender(0, "Male", MetadataType.GENDER);
/* 13 */   public static final Gender FEMALE = new Gender(1, "Female", MetadataType.GENDER);
/*    */ 
/*    */   
/*    */   public Gender() {}
/*    */   
/*    */   private Gender(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/Gender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */