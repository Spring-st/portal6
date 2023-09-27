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
/*     */ public abstract class AbstractPurchaseSubCategory
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private PurchaseCategory purchaseCategory;
/*     */ 
/*     */ 
/*     */   
/*     */   private Supplier defaultSupplier;
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
/*     */   private User inspector;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseSubCategory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseSubCategory(Integer purSubcateId) {
/*  62 */     setPurSubcateId(purSubcateId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public User getInspector() {
/*  68 */     return this.inspector;
/*     */   }
/*     */   
/*     */   public void setInspector(User inspector) {
/*  72 */     this.inspector = inspector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurSubcateId(Integer id) {
/*  90 */     this.hashValue = 0;
/*  91 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Supplier getDefaultSupplier() {
/* 100 */     return this.defaultSupplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultSupplier(Supplier defaultSupplier) {
/* 107 */     this.defaultSupplier = defaultSupplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 114 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 121 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 128 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 135 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory getPurchaseCategory() {
/* 142 */     return this.purchaseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCategory(PurchaseCategory purchaseCategory) {
/* 149 */     this.purchaseCategory = purchaseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 156 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 166 */     if (rhs == null) return false; 
/* 167 */     if (this == rhs) return true; 
/* 168 */     if (!(rhs instanceof PurchaseSubCategory)) return false; 
/* 169 */     PurchaseSubCategory that = (PurchaseSubCategory)rhs;
/* 170 */     if (getId() != null) return getId().equals(that.getId()); 
/* 171 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 181 */     if (this.hashValue == 0) {
/*     */       
/* 183 */       int result = 17;
/* 184 */       int purSubcateIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 185 */       result = result * 37 + purSubcateIdValue;
/* 186 */       this.hashValue = result;
/*     */     } 
/* 188 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractPurchaseSubCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */