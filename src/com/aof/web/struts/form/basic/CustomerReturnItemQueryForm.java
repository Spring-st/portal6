/*    */ package com.aof.web.struts.form.basic;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomerReturnItemQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String customerreturns;
/*    */   private String batchNumber;
/*    */   private String part;
/*    */   private String materialDescription;
/*    */   private String DPI;
/*    */   private String qty;
/*    */   private String salesDeliveryDate;
/*    */   private String returnStorage;
/*    */   
/*    */   public String getReturnStorage() {
/* 21 */     return this.returnStorage;
/*    */   }
/*    */   public void setReturnStorage(String returnStorage) {
/* 24 */     this.returnStorage = returnStorage;
/*    */   }
/*    */   public String getId() {
/* 27 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 30 */     this.id = id;
/*    */   }
/*    */   public String getCustomerreturns() {
/* 33 */     return this.customerreturns;
/*    */   }
/*    */   public void setCustomerreturns(String customerreturns) {
/* 36 */     this.customerreturns = customerreturns;
/*    */   }
/*    */   public String getBatchNumber() {
/* 39 */     return this.batchNumber;
/*    */   }
/*    */   public void setBatchNumber(String batchNumber) {
/* 42 */     this.batchNumber = batchNumber;
/*    */   }
/*    */   public String getPart() {
/* 45 */     return this.part;
/*    */   }
/*    */   public void setPart(String part) {
/* 48 */     this.part = part;
/*    */   }
/*    */   public String getMaterialDescription() {
/* 51 */     return this.materialDescription;
/*    */   }
/*    */   public void setMaterialDescription(String materialDescription) {
/* 54 */     this.materialDescription = materialDescription;
/*    */   }
/*    */   public String getDPI() {
/* 57 */     return this.DPI;
/*    */   }
/*    */   public void setDPI(String dPI) {
/* 60 */     this.DPI = dPI;
/*    */   }
/*    */   public String getQty() {
/* 63 */     return this.qty;
/*    */   }
/*    */   public void setQty(String qty) {
/* 66 */     this.qty = qty;
/*    */   }
/*    */   public String getSalesDeliveryDate() {
/* 69 */     return this.salesDeliveryDate;
/*    */   }
/*    */   public void setSalesDeliveryDate(String salesDeliveryDate) {
/* 72 */     this.salesDeliveryDate = salesDeliveryDate;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/basic/CustomerReturnItemQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */