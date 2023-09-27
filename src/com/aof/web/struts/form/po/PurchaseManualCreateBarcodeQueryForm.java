/*     */ package com.aof.web.struts.form.po;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import org.apache.struts.upload.FormFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PurchaseManualCreateBarcodeQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String id;
/*     */   private String part;
/*     */   private String partUnit;
/*     */   private String partName;
/*     */   private String supplierName;
/*     */   private String serialDate;
/*     */   private String serialNumber;
/*     */   private String qty;
/*     */   private String model;
/*     */   private FormFile fileContent;
/*     */   
/*     */   public String getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  29 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPart() {
/*  34 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(String part) {
/*  38 */     this.part = part;
/*     */   }
/*     */   
/*     */   public String getPartUnit() {
/*  42 */     return this.partUnit;
/*     */   }
/*     */   
/*     */   public void setPartUnit(String partUnit) {
/*  46 */     this.partUnit = partUnit;
/*     */   }
/*     */   
/*     */   public String getPartName() {
/*  50 */     return this.partName;
/*     */   }
/*     */   
/*     */   public void setPartName(String partName) {
/*  54 */     this.partName = partName;
/*     */   }
/*     */   
/*     */   public String getSupplierName() {
/*  58 */     return this.supplierName;
/*     */   }
/*     */   
/*     */   public void setSupplierName(String supplierName) {
/*  62 */     this.supplierName = supplierName;
/*     */   }
/*     */   
/*     */   public String getSerialDate() {
/*  66 */     return this.serialDate;
/*     */   }
/*     */   
/*     */   public void setSerialDate(String serialDate) {
/*  70 */     this.serialDate = serialDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSerialNumber() {
/*  75 */     return this.serialNumber;
/*     */   }
/*     */   
/*     */   public void setSerialNumber(String serialNumber) {
/*  79 */     this.serialNumber = serialNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQty() {
/*  84 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(String qty) {
/*  88 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getModel() {
/*  92 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(String model) {
/*  96 */     this.model = model;
/*     */   }
/*     */   
/*     */   public FormFile getFileContent() {
/* 100 */     return this.fileContent;
/*     */   }
/*     */   
/*     */   public void setFileContent(FormFile fileContent) {
/* 104 */     this.fileContent = fileContent;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/po/PurchaseManualCreateBarcodeQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */