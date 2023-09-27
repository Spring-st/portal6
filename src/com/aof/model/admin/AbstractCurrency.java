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
/*     */ public abstract class AbstractCurrency
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private String code;
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCurrency() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCurrency(String code) {
/*  48 */     setCode(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  57 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  66 */     this.hashValue = 0;
/*  67 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  71 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  75 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  84 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  93 */     this.enabled = enabled;
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
/* 104 */     if (rhs == null)
/* 105 */       return false; 
/* 106 */     if (this == rhs)
/* 107 */       return true; 
/* 108 */     if (!(rhs instanceof Currency))
/* 109 */       return false; 
/* 110 */     Currency that = (Currency)rhs;
/* 111 */     if (getCode() != null)
/* 112 */       return getCode().equals(that.getCode()); 
/* 113 */     return (that.getCode() == null);
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
/* 124 */     if (this.hashValue == 0) {
/* 125 */       int result = 17;
/* 126 */       int currCodeValue = (getCode() == null) ? 0 : getCode().hashCode();
/* 127 */       result = result * 37 + currCodeValue;
/* 128 */       this.hashValue = result;
/*     */     } 
/* 130 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractCurrency.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */