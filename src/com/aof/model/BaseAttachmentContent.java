/*     */ package com.aof.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Blob;
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
/*     */ public abstract class BaseAttachmentContent
/*     */   implements Serializable
/*     */ {
/*  23 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Blob fileContent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseAttachmentContent() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseAttachmentContent(Integer id) {
/*  44 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  53 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setId(Integer id) {
/*  62 */     this.hashValue = 0;
/*  63 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Blob getFileContent() {
/*  70 */     return this.fileContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileContent(Blob fileContent) {
/*  78 */     this.fileContent = fileContent;
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
/*  94 */     if (this.hashValue == 0) {
/*  95 */       int result = 17;
/*  96 */       int contractIdValue = (getId() == null) ? 0 : getId().hashCode();
/*  97 */       result = result * 37 + contractIdValue;
/*  98 */       this.hashValue = result;
/*     */     } 
/* 100 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/BaseAttachmentContent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */