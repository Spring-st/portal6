/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.FinishedToolPutnumber;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractProductGoline
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String shCode;
/*     */   private String totalNumber;
/*     */   private String hncCode;
/*     */   private String hncDesc;
/*     */   private String partTireCode;
/*     */   private String tireDesc;
/*     */   private String partHubCode;
/*     */   private String hubDesc;
/*     */   private Integer qty;
/*     */   private StorageLocation locationCode;
/*     */   private Date storageDate;
/*     */   private FinishedToolPutnumber tool;
/*     */   private Integer status;
/*     */   private String partValvestemCode;
/*     */   private String valvestemDesc;
/*     */   private Date buckleMaterialDate;
/*     */   
/*     */   public AbstractProductGoline() {}
/*     */   
/*     */   public AbstractProductGoline(Integer id) {
/*  33 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  37 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getShCode() {
/*  45 */     return this.shCode;
/*     */   }
/*     */   
/*     */   public void setShCode(String shCode) {
/*  49 */     this.shCode = shCode;
/*     */   }
/*     */   
/*     */   public String getTotalNumber() {
/*  53 */     return this.totalNumber;
/*     */   }
/*     */   
/*     */   public void setTotalNumber(String totalNumber) {
/*  57 */     this.totalNumber = totalNumber;
/*     */   }
/*     */   
/*     */   public String getHncCode() {
/*  61 */     return this.hncCode;
/*     */   }
/*     */   
/*     */   public void setHncCode(String hncCode) {
/*  65 */     this.hncCode = hncCode;
/*     */   }
/*     */   
/*     */   public String getPartTireCode() {
/*  69 */     return this.partTireCode;
/*     */   }
/*     */   
/*     */   public void setPartTireCode(String partTireCode) {
/*  73 */     this.partTireCode = partTireCode;
/*     */   }
/*     */   
/*     */   public String getTireDesc() {
/*  77 */     return this.tireDesc;
/*     */   }
/*     */   
/*     */   public void setTireDesc(String tireDesc) {
/*  81 */     this.tireDesc = tireDesc;
/*     */   }
/*     */   
/*     */   public String getPartHubCode() {
/*  85 */     return this.partHubCode;
/*     */   }
/*     */   
/*     */   public void setPartHubCode(String partHubCode) {
/*  89 */     this.partHubCode = partHubCode;
/*     */   }
/*     */   
/*     */   public String getHubDesc() {
/*  93 */     return this.hubDesc;
/*     */   }
/*     */   
/*     */   public void setHubDesc(String hubDesc) {
/*  97 */     this.hubDesc = hubDesc;
/*     */   }
/*     */   
/*     */   public Integer getQty() {
/* 101 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(Integer qty) {
/* 105 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocationCode() {
/* 109 */     return this.locationCode;
/*     */   }
/*     */   
/*     */   public void setLocationCode(StorageLocation locationCode) {
/* 113 */     this.locationCode = locationCode;
/*     */   }
/*     */   
/*     */   public Date getStorageDate() {
/* 117 */     return this.storageDate;
/*     */   }
/*     */   
/*     */   public void setStorageDate(Date storageDate) {
/* 121 */     this.storageDate = storageDate;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 125 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 129 */     this.status = status;
/*     */   }
/*     */   
/*     */   public FinishedToolPutnumber getTool() {
/* 133 */     return this.tool;
/*     */   }
/*     */   
/*     */   public void setTool(FinishedToolPutnumber tool) {
/* 137 */     this.tool = tool;
/*     */   }
/*     */   
/*     */   public String getPartValvestemCode() {
/* 141 */     return this.partValvestemCode;
/*     */   }
/*     */   
/*     */   public void setPartValvestemCode(String partValvestemCode) {
/* 145 */     this.partValvestemCode = partValvestemCode;
/*     */   }
/*     */   
/*     */   public String getValvestemDesc() {
/* 149 */     return this.valvestemDesc;
/*     */   }
/*     */   
/*     */   public void setValvestemDesc(String valvestemDesc) {
/* 153 */     this.valvestemDesc = valvestemDesc;
/*     */   }
/*     */   
/*     */   public Date getBuckleMaterialDate() {
/* 157 */     return this.buckleMaterialDate;
/*     */   }
/*     */   
/*     */   public void setBuckleMaterialDate(Date buckleMaterialDate) {
/* 161 */     this.buckleMaterialDate = buckleMaterialDate;
/*     */   }
/*     */   
/*     */   public String getHncDesc() {
/* 165 */     return this.hncDesc;
/*     */   }
/*     */   
/*     */   public void setHncDesc(String hncDesc) {
/* 169 */     this.hncDesc = hncDesc;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractProductGoline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */