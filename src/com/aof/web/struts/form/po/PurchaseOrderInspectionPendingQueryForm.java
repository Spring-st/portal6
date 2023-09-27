/*     */ package com.aof.web.struts.form.po;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PurchaseOrderInspectionPendingQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String siteId;
/*     */   private String description;
/*     */   private String status;
/*     */   private String site;
/*     */   private String startPoDate;
/*     */   private String endPoDate;
/*     */   private String supplier;
/*     */   private String supplierCode;
/*     */   private String poNumber;
/*     */   private String itemNumber;
/*     */   private String supplierItemNumber;
/*     */   private String um;
/*     */   private String dueDate;
/*     */   private String receiptQty;
/*     */   private String receivingDate;
/*     */   private String isReceiving;
/*     */   private String isIqc;
/*     */   private String isPrintLabels;
/*     */   private String department;
/*     */   private String boxCount;
/*     */   private String needRQCnumber;
/*     */   private String ifConfirmPO;
/*     */   
/*     */   public String getNeedRQCnumber() {
/*  48 */     return this.needRQCnumber;
/*     */   }
/*     */   public void setNeedRQCnumber(String needRQCnumber) {
/*  51 */     this.needRQCnumber = needRQCnumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBoxCount() {
/*  57 */     return this.boxCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoxCount(String boxCount) {
/*  63 */     this.boxCount = boxCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDepartment() {
/*  69 */     return this.department;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepartment(String department) {
/*  75 */     this.department = department;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPoNumber() {
/*  83 */     return this.poNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoNumber(String poNumber) {
/*  89 */     this.poNumber = poNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemNumber() {
/*  95 */     return this.itemNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemNumber(String itemNumber) {
/* 101 */     this.itemNumber = itemNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierItemNumber() {
/* 107 */     return this.supplierItemNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierItemNumber(String supplierItemNumber) {
/* 113 */     this.supplierItemNumber = supplierItemNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUm() {
/* 119 */     return this.um;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUm(String um) {
/* 125 */     this.um = um;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDueDate() {
/* 131 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDueDate(String dueDate) {
/* 137 */     this.dueDate = dueDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReceiptQty() {
/* 143 */     return this.receiptQty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReceiptQty(String receiptQty) {
/* 149 */     this.receiptQty = receiptQty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReceivingDate() {
/* 155 */     return this.receivingDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReceivingDate(String receivingDate) {
/* 161 */     this.receivingDate = receivingDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIsReceiving() {
/* 167 */     return this.isReceiving;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsReceiving(String isReceiving) {
/* 173 */     this.isReceiving = isReceiving;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIsIqc() {
/* 179 */     return this.isIqc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsIqc(String isIqc) {
/* 185 */     this.isIqc = isIqc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIsPrintLabels() {
/* 191 */     return this.isPrintLabels;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsPrintLabels(String isPrintLabels) {
/* 197 */     this.isPrintLabels = isPrintLabels;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSite() {
/* 203 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(String site) {
/* 209 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartPoDate() {
/* 216 */     return this.startPoDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPoDate(String startPoDate) {
/* 222 */     this.startPoDate = startPoDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndPoDate() {
/* 228 */     return this.endPoDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPoDate(String endPoDate) {
/* 234 */     this.endPoDate = endPoDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplier() {
/* 240 */     return this.supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplier(String supplier) {
/* 246 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplierCode() {
/* 252 */     return this.supplierCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierCode(String supplierCode) {
/* 258 */     this.supplierCode = supplierCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 264 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/* 270 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 276 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 282 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 288 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 294 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 301 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 307 */     this.description = description;
/*     */   }
/*     */   public String getIfConfirmPO() {
/* 310 */     return this.ifConfirmPO;
/*     */   }
/*     */   public void setIfConfirmPO(String ifConfirmPO) {
/* 313 */     this.ifConfirmPO = ifConfirmPO;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/po/PurchaseOrderInspectionPendingQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */