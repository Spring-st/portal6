/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ public abstract class AbstractSupplierItem
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
/*     */   private Currency currency;
/*     */ 
/*     */ 
/*     */   
/*     */   private PurchaseSubCategory purchaseSubCategory;
/*     */ 
/*     */ 
/*     */   
/*     */   private Supplier supplier;
/*     */ 
/*     */ 
/*     */   
/*     */   private String sepc;
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal unitPrice;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private String erpNo;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierItem() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierItem(Integer id) {
/*  70 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  79 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  88 */     this.hashValue = 0;
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency getCurrency() {
/*  98 */     return this.currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(Currency currency) {
/* 105 */     this.currency = currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 112 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 119 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseSubCategory getPurchaseSubCategory() {
/* 126 */     return this.purchaseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubCategory(PurchaseSubCategory purchaseSubCategory) {
/* 133 */     this.purchaseSubCategory = purchaseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSepc() {
/* 140 */     return this.sepc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSepc(String sepc) {
/* 147 */     this.sepc = sepc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Supplier getSupplier() {
/* 154 */     return this.supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/* 161 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getUnitPrice() {
/* 170 */     return this.unitPrice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnitPrice(BigDecimal unitPrice) {
/* 179 */     this.unitPrice = unitPrice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErpNo() {
/* 189 */     return this.erpNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErpNo(String erpNo) {
/* 196 */     this.erpNo = erpNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 206 */     if (rhs == null) return false; 
/* 207 */     if (this == rhs) return true; 
/* 208 */     if (!(rhs instanceof SupplierItem)) return false; 
/* 209 */     SupplierItem that = (SupplierItem)rhs;
/* 210 */     if (getId() != null) return getId().equals(that.getId()); 
/* 211 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 221 */     if (this.hashValue == 0) {
/*     */       
/* 223 */       int result = 17;
/* 224 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 225 */       result = result * 37 + itemIdValue;
/* 226 */       this.hashValue = result;
/*     */     } 
/* 228 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSupplierItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */