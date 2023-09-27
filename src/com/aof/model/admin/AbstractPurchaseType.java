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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPurchaseType
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private String code;
/*     */ 
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseType() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseType(String code) {
/*  57 */     setCode(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  66 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  75 */     this.hashValue = 0;
/*  76 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  85 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  94 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 102 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 109 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 116 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 123 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 133 */     if (rhs == null) return false; 
/* 134 */     if (this == rhs) return true; 
/* 135 */     if (!(rhs instanceof PurchaseType)) return false; 
/* 136 */     PurchaseType that = (PurchaseType)rhs;
/* 137 */     if (getCode() != null) return getCode().equals(that.getCode()); 
/* 138 */     return (that.getCode() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 148 */     if (this.hashValue == 0) {
/*     */       
/* 150 */       int result = 17;
/* 151 */       int purTypeCodeValue = (getCode() == null) ? 0 : getCode().hashCode();
/* 152 */       result = result * 37 + purTypeCodeValue;
/* 153 */       this.hashValue = result;
/*     */     } 
/* 155 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractPurchaseType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */