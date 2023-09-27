/*     */ package com.aof.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ public abstract class BaseAttachment
/*     */   implements Serializable
/*     */ {
/*  23 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private String fileName;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private Date uploadDate;
/*     */   
/*     */   private int fileSize;
/*     */   
/*     */   public int getFileSize() {
/*  36 */     return this.fileSize;
/*     */   }
/*     */   
/*     */   public void setFileSize(int fileSize) {
/*  40 */     this.fileSize = fileSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseAttachment() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseAttachment(Integer id) {
/*  55 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  62 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  70 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  77 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileName(String fileName) {
/*  85 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setId(Integer id) {
/* 100 */     this.hashValue = 0;
/* 101 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getUploadDate() {
/* 108 */     return this.uploadDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUploadDate(Date uploadDate) {
/* 116 */     this.uploadDate = uploadDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 132 */     if (this.hashValue == 0) {
/* 133 */       int result = 17;
/* 134 */       int contractIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 135 */       result = result * 37 + contractIdValue;
/* 136 */       this.hashValue = result;
/*     */     } 
/* 138 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/BaseAttachment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */