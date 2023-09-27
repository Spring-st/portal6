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
/*     */ 
/*     */ public abstract class AbstractMetadata
/*     */   implements Serializable
/*     */ {
/*  32 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMetadata() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMetadata(Integer id) {
/*  53 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  71 */     this.hashValue = 0;
/*  72 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  81 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  90 */     this.description = description;
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
/* 101 */     if (rhs == null) return false; 
/* 102 */     if (this == rhs) return true; 
/* 103 */     if (!(rhs instanceof Metadata)) return false; 
/* 104 */     Metadata that = (Metadata)rhs;
/* 105 */     if (getId() != null) return getId().equals(that.getId()); 
/* 106 */     return (that.getId() == null);
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
/* 117 */     if (this.hashValue == 0) {
/* 118 */       int result = 17;
/* 119 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 120 */       result = result * 37 + idValue;
/* 121 */       this.hashValue = result;
/*     */     } 
/* 123 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */