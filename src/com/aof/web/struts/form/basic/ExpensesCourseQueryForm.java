/*     */ package com.aof.web.struts.form.basic;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import org.apache.struts.upload.FormFile;
/*     */ 
/*     */ public class ExpensesCourseQueryForm
/*     */   extends BaseSessionQueryForm {
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
/*     */   private String supplier;
/*     */   private String supplierPart;
/*     */   private String supplierPartCode;
/*     */   private String qualityType;
/*     */   private String partType;
/*     */   private String tempDate;
/*     */   private String group;
/*     */   private String productClass;
/*     */   private String props;
/*     */   private FormFile myFile;
/*     */   private String is_enabled;
/*     */   
/*     */   public FormFile getMyFile() {
/*  32 */     return this.myFile;
/*     */   }
/*     */   
/*     */   public void setMyFile(FormFile myFile) {
/*  36 */     this.myFile = myFile;
/*     */   }
/*     */   
/*     */   public String getDescribe2() {
/*  40 */     return this.describe2;
/*     */   }
/*     */   
/*     */   public void setDescribe2(String describe2) {
/*  44 */     this.describe2 = describe2;
/*     */   }
/*     */   
/*     */   public String getProps() {
/*  48 */     return this.props;
/*     */   }
/*     */   
/*     */   public void setProps(String props) {
/*  52 */     this.props = props;
/*     */   }
/*     */   
/*     */   public String getProductClass() {
/*  56 */     return this.productClass;
/*     */   }
/*     */   
/*     */   public void setProductClass(String productClass) {
/*  60 */     this.productClass = productClass;
/*     */   }
/*     */   
/*     */   public String getGroup() {
/*  64 */     return this.group;
/*     */   }
/*     */   
/*     */   public void setGroup(String group) {
/*  68 */     this.group = group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPartType() {
/*  75 */     return this.partType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartType(String partType) {
/*  83 */     this.partType = partType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQualityType() {
/*  90 */     return this.qualityType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQualityType(String qualityType) {
/*  98 */     this.qualityType = qualityType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplier() {
/* 105 */     return this.supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplier(String supplier) {
/* 113 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierPartCode() {
/* 120 */     return this.supplierPartCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierPartCode(String supplierPartCode) {
/* 128 */     this.supplierPartCode = supplierPartCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite() {
/* 135 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(String site) {
/* 143 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIs_enabled() {
/* 150 */     return this.is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIs_enabled(String is_enabled) {
/* 158 */     this.is_enabled = is_enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCapacity() {
/* 167 */     return this.capacity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCapacity(String capacity) {
/* 175 */     this.capacity = capacity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContainerType() {
/* 182 */     return this.containerType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContainerType(String containerType) {
/* 190 */     this.containerType = containerType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnit() {
/* 197 */     return this.unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnit(String unit) {
/* 205 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 212 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 220 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe1() {
/* 227 */     return this.describe1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe1(String describe1) {
/* 235 */     this.describe1 = describe1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 242 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 250 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 257 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 265 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 272 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 280 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 287 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 295 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierPart() {
/* 302 */     return this.supplierPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierPart(String supplierPart) {
/* 310 */     this.supplierPart = supplierPart;
/*     */   }
/*     */   
/*     */   public String getTempDate() {
/* 314 */     return this.tempDate;
/*     */   }
/*     */   
/*     */   public void setTempDate(String tempDate) {
/* 318 */     this.tempDate = tempDate;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/ExpensesCourseQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */