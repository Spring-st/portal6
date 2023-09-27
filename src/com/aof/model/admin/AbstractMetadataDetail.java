/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMetadataDetail
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Metadata type;
/*     */ 
/*     */ 
/*     */   
/*     */   private String engDescription;
/*     */ 
/*     */ 
/*     */   
/*     */   private String chnDescription;
/*     */ 
/*     */ 
/*     */   
/*     */   private String engShortDescription;
/*     */ 
/*     */ 
/*     */   
/*     */   private String chnShortDescription;
/*     */ 
/*     */   
/*     */   private String color;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMetadataDetail() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMetadataDetail(Integer id, Metadata type) {
/*  67 */     setId(id);
/*  68 */     setType(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  77 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  86 */     this.hashValue = 0;
/*  87 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata getType() {
/*  96 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(Metadata type) {
/* 105 */     this.hashValue = 0;
/* 106 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEngDescription() {
/* 115 */     return this.engDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEngDescription(String engDescription) {
/* 124 */     this.engDescription = engDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChnDescription() {
/* 133 */     return this.chnDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChnDescription(String chnDescription) {
/* 142 */     this.chnDescription = chnDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEngShortDescription() {
/* 151 */     return this.engShortDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEngShortDescription(String engShortDescription) {
/* 160 */     this.engShortDescription = engShortDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChnShortDescription() {
/* 169 */     return this.chnShortDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChnShortDescription(String chnShortDescription) {
/* 178 */     this.chnShortDescription = chnShortDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColor() {
/* 187 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(String color) {
/* 196 */     this.color = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 207 */     if (rhs == null) return false; 
/* 208 */     if (this == rhs) return true; 
/* 209 */     if (!(rhs instanceof MetadataDetail)) return false; 
/* 210 */     MetadataDetail that = (MetadataDetail)rhs;
/* 211 */     if (getId() != null)
/* 212 */     { if (!getId().equals(that.getId())) return false;
/*     */        }
/* 214 */     else if (that.getId() != null) { return false; }
/*     */     
/* 216 */     if (getType() != null)
/* 217 */     { if (!getType().equals(that.getType())) return false;
/*     */        }
/* 219 */     else if (that.getType() != null) { return false; }
/*     */     
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 232 */     if (this.hashValue == 0) {
/* 233 */       int result = 17;
/* 234 */       result = result * 37 + ((getId() == null) ? 0 : getId().hashCode());
/* 235 */       result = result * 37 + ((getType() == null) ? 0 : getType().hashCode());
/* 236 */       this.hashValue = result;
/*     */     } 
/* 238 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractMetadataDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */