/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.CurrencyType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractFPSMaterialPrice
/*     */   implements Serializable
/*     */ {
/*  13 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPart wmsPart;
/*     */   private String supplierName;
/*     */   private String unit;
/*     */   private Date startDate;
/*     */   private Date endDate;
/*     */   private EnabledDisabled enabled;
/*     */   private BigDecimal unitPrice;
/*     */   private String description1;
/*     */   private String description2;
/*     */   private Supplier supplier;
/*     */   private CurrencyType currencyType;
/*     */   
/*     */   public CurrencyType getCurrencyType() {
/*  28 */     return this.currencyType;
/*     */   }
/*     */   
/*     */   public void setCurrencyType(CurrencyType currencyType) {
/*  32 */     this.currencyType = currencyType;
/*     */   }
/*     */   
/*     */   public Supplier getSupplier() {
/*  36 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/*  40 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public BigDecimal getUnitPrice() {
/*  44 */     return this.unitPrice;
/*     */   }
/*     */   
/*     */   public String getDescription1() {
/*  48 */     return this.description1;
/*     */   }
/*     */   
/*     */   public void setDescription1(String description1) {
/*  52 */     this.description1 = description1;
/*     */   }
/*     */   
/*     */   public String getDescription2() {
/*  56 */     return this.description2;
/*     */   }
/*     */   
/*     */   public void setDescription2(String description2) {
/*  60 */     this.description2 = description2;
/*     */   }
/*     */   
/*     */   public void setUnitPrice(BigDecimal unitPrice) {
/*  64 */     this.unitPrice = unitPrice;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractFPSMaterialPrice() {}
/*     */   
/*     */   public AbstractFPSMaterialPrice(Integer id) {
/*  71 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  75 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  79 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsPart getWmsPart() {
/*  83 */     return this.wmsPart;
/*     */   }
/*     */   
/*     */   public void setWmsPart(WmsPart wmsPart) {
/*  87 */     this.wmsPart = wmsPart;
/*     */   }
/*     */   
/*     */   public String getSupplierName() {
/*  91 */     return this.supplierName;
/*     */   }
/*     */   
/*     */   public void setSupplierName(String supplierName) {
/*  95 */     this.supplierName = supplierName;
/*     */   }
/*     */   
/*     */   public String getUnit() {
/*  99 */     return this.unit;
/*     */   }
/*     */   
/*     */   public void setUnit(String unit) {
/* 103 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public Date getStartDate() {
/* 107 */     return this.startDate;
/*     */   }
/*     */   
/*     */   public void setStartDate(Date startDate) {
/* 111 */     this.startDate = startDate;
/*     */   }
/*     */   
/*     */   public Date getEndDate() {
/* 115 */     return this.endDate;
/*     */   }
/*     */   
/*     */   public void setEndDate(Date endDate) {
/* 119 */     this.endDate = endDate;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 123 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 127 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 132 */     if (rhs == null)
/* 133 */       return false; 
/* 134 */     if (this == rhs)
/* 135 */       return true; 
/* 136 */     if (!(rhs instanceof FPSMaterialPrice))
/* 137 */       return false; 
/* 138 */     FPSMaterialPrice that = (FPSMaterialPrice)rhs;
/* 139 */     if (getId() != null)
/* 140 */       return getId().equals(that.getId()); 
/* 141 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 146 */     if (this.hashValue == 0) {
/* 147 */       int result = 17;
/* 148 */       int purCateIdValue = (getId() == null) ? 0 : getId()
/* 149 */         .hashCode();
/* 150 */       result = result * 37 + purCateIdValue;
/* 151 */       this.hashValue = result;
/*     */     } 
/* 153 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractFPSMaterialPrice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */