/*     */ package com.aof.web.struts.form.basic;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
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
/*     */ public class WmsPartQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String siteId;
/*     */   private String description;
/*     */   private String status;
/*     */   private String name;
/*     */   private String describe1;
/*     */   private String describe2;
/*     */   private String unit;
/*     */   private String containerType;
/*     */   private String capacity;
/*     */   private String site;
/*     */   
/*     */   public String getDescribe2() {
/*  30 */     return this.describe2;
/*     */   }
/*     */   private String supplier; private String supplierPart; private String supplierPartCode; private String qualityType; private String partType; private String tempDate; private String group; private String productClass; private String props; private String securityQty; private String drwgLoc; private String is_enabled;
/*     */   public void setDescribe2(String describe2) {
/*  34 */     this.describe2 = describe2;
/*     */   }
/*     */   
/*     */   public String getProps() {
/*  38 */     return this.props;
/*     */   }
/*     */   
/*     */   public void setProps(String props) {
/*  42 */     this.props = props;
/*     */   }
/*     */   
/*     */   public String getProductClass() {
/*  46 */     return this.productClass;
/*     */   }
/*     */   
/*     */   public void setProductClass(String productClass) {
/*  50 */     this.productClass = productClass;
/*     */   }
/*     */   
/*     */   public String getGroup() {
/*  54 */     return this.group;
/*     */   }
/*     */   
/*     */   public void setGroup(String group) {
/*  58 */     this.group = group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPartType() {
/*  65 */     return this.partType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartType(String partType) {
/*  73 */     this.partType = partType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQualityType() {
/*  80 */     return this.qualityType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQualityType(String qualityType) {
/*  88 */     this.qualityType = qualityType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplier() {
/*  95 */     return this.supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplier(String supplier) {
/* 103 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierPartCode() {
/* 110 */     return this.supplierPartCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierPartCode(String supplierPartCode) {
/* 118 */     this.supplierPartCode = supplierPartCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite() {
/* 125 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(String site) {
/* 133 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIs_enabled() {
/* 140 */     return this.is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIs_enabled(String is_enabled) {
/* 148 */     this.is_enabled = is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCapacity() {
/* 157 */     return this.capacity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCapacity(String capacity) {
/* 165 */     this.capacity = capacity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContainerType() {
/* 172 */     return this.containerType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContainerType(String containerType) {
/* 180 */     this.containerType = containerType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnit() {
/* 187 */     return this.unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnit(String unit) {
/* 195 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 202 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 210 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe1() {
/* 217 */     return this.describe1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe1(String describe1) {
/* 225 */     this.describe1 = describe1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 232 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 240 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 247 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 255 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 262 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 270 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 277 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 285 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierPart() {
/* 292 */     return this.supplierPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierPart(String supplierPart) {
/* 300 */     this.supplierPart = supplierPart;
/*     */   }
/*     */   
/*     */   public String getTempDate() {
/* 304 */     return this.tempDate;
/*     */   }
/*     */   
/*     */   public void setTempDate(String tempDate) {
/* 308 */     this.tempDate = tempDate;
/*     */   }
/*     */   
/*     */   public String getSecurityQty() {
/* 312 */     return this.securityQty;
/*     */   }
/*     */   
/*     */   public void setSecurityQty(String securityQty) {
/* 316 */     this.securityQty = securityQty;
/*     */   }
/*     */   
/*     */   public String getDrwgLoc() {
/* 320 */     return this.drwgLoc;
/*     */   }
/*     */   
/*     */   public void setDrwgLoc(String drwgLoc) {
/* 324 */     this.drwgLoc = drwgLoc;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/WmsPartQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */