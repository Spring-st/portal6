/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseType;
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
/*     */ public abstract class AbstractExpenseCategory
/*     */   implements Serializable
/*     */ {
/*  26 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
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
/*     */   private ExpenseType type;
/*     */ 
/*     */ 
/*     */   
/*     */   private String referenceErpId;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExpenseCategory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExpenseCategory(Integer id) {
/*  59 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  68 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  77 */     this.hashValue = 0;
/*  78 */     this.id = id;
/*     */   }
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
/*     */   public void setDescription(String description) {
/*  93 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 100 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 108 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReferenceErpId() {
/* 115 */     return this.referenceErpId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferenceErpId(String referenceErpId) {
/* 123 */     this.referenceErpId = referenceErpId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 130 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 138 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseType getType() {
/* 145 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ExpenseType type) {
/* 153 */     this.type = type;
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
/* 164 */     if (rhs == null)
/* 165 */       return false; 
/* 166 */     if (this == rhs)
/* 167 */       return true; 
/* 168 */     if (!(rhs instanceof ExpenseCategory))
/* 169 */       return false; 
/* 170 */     ExpenseCategory that = (ExpenseCategory)rhs;
/* 171 */     if (getId() != null)
/* 172 */       return getId().equals(that.getId()); 
/* 173 */     return (that.getId() == null);
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
/* 184 */     if (this.hashValue == 0) {
/* 185 */       int result = 17;
/* 186 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 187 */       result = result * 37 + idValue;
/* 188 */       this.hashValue = result;
/*     */     } 
/* 190 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractExpenseCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */