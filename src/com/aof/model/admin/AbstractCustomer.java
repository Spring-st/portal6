/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.CustomerType;
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
/*     */ public abstract class AbstractCustomer
/*     */   implements Serializable
/*     */ {
/*  26 */   private int hashValue = 0;
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
/*     */   private CustomerType type;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCustomer() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCustomer(String code) {
/*  55 */     setCode(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  64 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  73 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  80 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  88 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  95 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 103 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CustomerType getType() {
/* 110 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(CustomerType type) {
/* 118 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 125 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 133 */     this.enabled = enabled;
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
/* 144 */     if (rhs == null)
/* 145 */       return false; 
/* 146 */     if (this == rhs)
/* 147 */       return true; 
/* 148 */     if (!(rhs instanceof Customer))
/* 149 */       return false; 
/* 150 */     Customer that = (Customer)rhs;
/* 151 */     if (getCode() != null)
/* 152 */       return getCode().equals(that.getCode()); 
/* 153 */     return (that.getCode() == null);
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
/* 164 */     if (this.hashValue == 0) {
/* 165 */       int result = 17;
/* 166 */       int custCodeValue = (getCode() == null) ? 0 : getCode().hashCode();
/* 167 */       result = result * 37 + custCodeValue;
/* 168 */       this.hashValue = result;
/*     */     } 
/* 170 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractCustomer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */