/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.SupplierItemQueryOrder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupplierItemQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String currency_code;
/*     */   private String purchaseCategory_id;
/*     */   private String purchaseSubCategory_id;
/*     */   private String supplier_id;
/*     */   private String sepc;
/*     */   private String unitPrice;
/*     */   private String enabled;
/*     */   private String erpNo;
/*  40 */   private String backPage = "edit";
/*     */   
/*  42 */   private String fromPO = "false";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrency_code() {
/*  48 */     return this.currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency_code(String currency_code) {
/*  55 */     this.currency_code = currency_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/*  62 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/*  69 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErpNo() {
/*  76 */     return this.erpNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErpNo(String erpNo) {
/*  83 */     this.erpNo = erpNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  90 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  97 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseSubCategory_id() {
/* 104 */     return this.purchaseSubCategory_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubCategory_id(String purchaseSubCategory_id) {
/* 111 */     this.purchaseSubCategory_id = purchaseSubCategory_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSepc() {
/* 118 */     return this.sepc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSepc(String sepc) {
/* 125 */     this.sepc = sepc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSupplier_id() {
/* 132 */     return this.supplier_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplier_id(String supplier_id) {
/* 139 */     this.supplier_id = supplier_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnitPrice() {
/* 146 */     return this.unitPrice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnitPrice(String unitPrice) {
/* 153 */     this.unitPrice = unitPrice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPurchaseCategory_id() {
/* 160 */     return this.purchaseCategory_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseCategory_id(String purchaseCategory_id) {
/* 167 */     this.purchaseCategory_id = purchaseCategory_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBackPage() {
/* 175 */     return this.backPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackPage(String backPage) {
/* 182 */     this.backPage = backPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFromPO() {
/* 191 */     return this.fromPO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromPO(String fromPO) {
/* 198 */     this.fromPO = fromPO;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierItemQueryForm() {
/* 204 */     setOrder(SupplierItemQueryOrder.CATEGORY.getName());
/* 205 */     setDescend(false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/SupplierItemQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */