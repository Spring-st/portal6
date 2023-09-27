/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public abstract class AbstractFinishedSaiheRelation
/*     */   implements Serializable
/*     */ {
/*  22 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private String saiheCode;
/*     */   
/*     */   private String finishedCode;
/*     */   
/*     */   private String finishedProductDesc;
/*     */   
/*     */   private EnabledDisabled status;
/*     */   
/*     */   public Integer getId() {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  40 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSaiheCode() {
/*  45 */     return this.saiheCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSaiheCode(String saiheCode) {
/*  50 */     this.saiheCode = saiheCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFinishedCode() {
/*  55 */     return this.finishedCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFinishedCode(String finishedCode) {
/*  60 */     this.finishedCode = finishedCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFinishedProductDesc() {
/*  65 */     return this.finishedProductDesc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFinishedProductDesc(String finishedProductDesc) {
/*  70 */     this.finishedProductDesc = finishedProductDesc;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  75 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  80 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFinishedSaiheRelation() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFinishedSaiheRelation(Integer id) {
/*  91 */     setId(id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  95 */     if (rhs == null)
/*  96 */       return false; 
/*  97 */     if (this == rhs)
/*  98 */       return true; 
/*  99 */     if (!(rhs instanceof City))
/* 100 */       return false; 
/* 101 */     City that = (City)rhs;
/* 102 */     if (getId() != null)
/* 103 */       return getId().equals(that.getId()); 
/* 104 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 109 */     if (this.hashValue == 0) {
/* 110 */       int result = 17;
/* 111 */       int cityIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 112 */       result = result * 37 + cityIdValue;
/* 113 */       this.hashValue = result;
/*     */     } 
/* 115 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractFinishedSaiheRelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */