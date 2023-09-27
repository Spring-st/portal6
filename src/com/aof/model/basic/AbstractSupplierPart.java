/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.CurrencyType;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public class AbstractSupplierPart
/*     */   implements Serializable
/*     */ {
/*  11 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPart partId;
/*     */   private Supplier supplierId;
/*     */   private String supplierPart;
/*     */   private BigDecimal price;
/*     */   private CurrencyType currencyType;
/*     */   private BigDecimal proportion;
/*     */   private BigDecimal sampling_rate;
/*     */   private BigDecimal capacity;
/*     */   
/*     */   public BigDecimal getCapacity() {
/*  23 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(BigDecimal capacity) {
/*  27 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public BigDecimal getSampling_rate() {
/*  31 */     return this.sampling_rate;
/*     */   }
/*     */   
/*     */   public void setSampling_rate(BigDecimal sampling_rate) {
/*  35 */     this.sampling_rate = sampling_rate;
/*     */   }
/*     */   
/*     */   public BigDecimal getProportion() {
/*  39 */     return this.proportion;
/*     */   }
/*     */   
/*     */   public void setProportion(BigDecimal proportion) {
/*  43 */     this.proportion = proportion;
/*     */   }
/*     */   
/*     */   public CurrencyType getCurrencyType() {
/*  47 */     return this.currencyType;
/*     */   }
/*     */   
/*     */   public void setCurrencyType(CurrencyType currencyType) {
/*  51 */     this.currencyType = currencyType;
/*     */   }
/*     */   
/*     */   public BigDecimal getPrice() {
/*  55 */     return this.price;
/*     */   }
/*     */   
/*     */   public void setPrice(BigDecimal price) {
/*  59 */     this.price = price;
/*     */   }
/*     */   
/*     */   public String getSupplierPart() {
/*  63 */     return this.supplierPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierPart(String supplierPart) {
/*  71 */     this.supplierPart = supplierPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPart getPartId() {
/*  78 */     return this.partId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartId(WmsPart partId) {
/*  86 */     this.partId = partId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Supplier getSupplierId() {
/*  93 */     return this.supplierId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierId(Supplier supplierId) {
/* 101 */     this.supplierId = supplierId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierPart() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierPart(Integer id) {
/* 117 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 126 */     this.hashValue = 0;
/* 127 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 134 */     return this.id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 138 */     if (rhs == null)
/* 139 */       return false; 
/* 140 */     if (this == rhs)
/* 141 */       return true; 
/* 142 */     if (!(rhs instanceof StoreRoom))
/* 143 */       return false; 
/* 144 */     StoreRoom that = (StoreRoom)rhs;
/* 145 */     if (getId() != null)
/* 146 */       return getId().equals(that.getId()); 
/* 147 */     return (that.getId() == null);
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
/* 158 */     if (this.hashValue == 0) {
/* 159 */       int result = 17;
/* 160 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 161 */         .hashCode();
/* 162 */       result = result * 37 + cityIdValue;
/* 163 */       this.hashValue = result;
/*     */     } 
/* 165 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractSupplierPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */