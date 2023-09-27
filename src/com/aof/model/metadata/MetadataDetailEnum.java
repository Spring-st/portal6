/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ import com.shcnc.hibernate.PersistentIntegerEnum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MetadataDetailEnum
/*    */   extends PersistentIntegerEnum
/*    */ {
/*    */   private MetadataType type;
/*    */   protected String engDescription;
/*    */   protected String chnDescription;
/*    */   protected String engShortDescription;
/*    */   protected String chnShortDescription;
/*    */   protected String color;
/*    */   public static final int DUMMY_KEY = -1;
/* 21 */   public static final MetadataDetailEnum DUMMY = new MetadataDetailEnum(-1, null, null);
/*    */ 
/*    */   
/*    */   protected MetadataDetailEnum() {}
/*    */   
/*    */   protected MetadataDetailEnum(int metadataId, String defaultLabel, MetadataType type) {
/* 27 */     super(defaultLabel, metadataId);
/* 28 */     this.type = type;
/*    */   }
/*    */   
/*    */   public MetadataType getType() {
/* 32 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getChnDescription() {
/* 36 */     return this.chnDescription;
/*    */   }
/*    */   
/*    */   public void setChnDescription(String chnDescription) {
/* 40 */     this.chnDescription = chnDescription;
/*    */   }
/*    */   
/*    */   public String getChnShortDescription() {
/* 44 */     return this.chnShortDescription;
/*    */   }
/*    */   
/*    */   public void setChnShortDescription(String chnShortDescription) {
/* 48 */     this.chnShortDescription = chnShortDescription;
/*    */   }
/*    */   
/*    */   public String getColor() {
/* 52 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setColor(String color) {
/* 56 */     this.color = color;
/*    */   }
/*    */   
/*    */   public String getEngDescription() {
/* 60 */     return this.engDescription;
/*    */   }
/*    */   
/*    */   public void setEngDescription(String engDescription) {
/* 64 */     this.engDescription = engDescription;
/*    */   }
/*    */   
/*    */   public String getEngShortDescription() {
/* 68 */     return this.engShortDescription;
/*    */   }
/*    */   
/*    */   public void setEngShortDescription(String engShortDescription) {
/* 72 */     this.engShortDescription = engShortDescription;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/MetadataDetailEnum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */