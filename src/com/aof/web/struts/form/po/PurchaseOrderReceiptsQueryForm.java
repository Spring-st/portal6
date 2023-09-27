/*     */ package com.aof.web.struts.form.po;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import org.apache.struts.upload.FormFile;
/*     */ 
/*     */ public class PurchaseOrderReceiptsQueryForm
/*     */   extends BaseSessionQueryForm {
/*     */   private String id;
/*     */   private String siteId;
/*     */   private String description;
/*     */   private String status;
/*     */   private String site;
/*     */   private String startPoDate;
/*     */   private String endPoDate;
/*     */   private String supplier;
/*     */   private String supplierCode;
/*     */   private String department;
/*     */   private String poOrder;
/*     */   private FormFile myFile;
/*     */   
/*     */   public String getId() {
/*  22 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  26 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getSiteId() {
/*  30 */     return this.siteId;
/*     */   }
/*     */   
/*     */   public void setSiteId(String siteId) {
/*  34 */     this.siteId = siteId;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  38 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  42 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/*  46 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/*  50 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getSite() {
/*  54 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/*  58 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getStartPoDate() {
/*  62 */     return this.startPoDate;
/*     */   }
/*     */   
/*     */   public void setStartPoDate(String startPoDate) {
/*  66 */     this.startPoDate = startPoDate;
/*     */   }
/*     */   
/*     */   public String getEndPoDate() {
/*  70 */     return this.endPoDate;
/*     */   }
/*     */   
/*     */   public void setEndPoDate(String endPoDate) {
/*  74 */     this.endPoDate = endPoDate;
/*     */   }
/*     */   
/*     */   public String getSupplier() {
/*  78 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(String supplier) {
/*  82 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public String getSupplierCode() {
/*  86 */     return this.supplierCode;
/*     */   }
/*     */   
/*     */   public void setSupplierCode(String supplierCode) {
/*  90 */     this.supplierCode = supplierCode;
/*     */   }
/*     */   
/*     */   public String getDepartment() {
/*  94 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(String department) {
/*  98 */     this.department = department;
/*     */   }
/*     */   
/*     */   public String getPoOrder() {
/* 102 */     return this.poOrder;
/*     */   }
/*     */   
/*     */   public void setPoOrder(String poOrder) {
/* 106 */     this.poOrder = poOrder;
/*     */   }
/*     */   
/*     */   public FormFile getMyFile() {
/* 110 */     return this.myFile;
/*     */   }
/*     */   
/*     */   public void setMyFile(FormFile myFile) {
/* 114 */     this.myFile = myFile;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/po/PurchaseOrderReceiptsQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */