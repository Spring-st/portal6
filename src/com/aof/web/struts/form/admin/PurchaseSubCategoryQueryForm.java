/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseSubCategoryQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String purchaseCategory_id;
/*    */   private String defaultSupplier_id;
/*    */   private String description;
/*    */   
/*    */   public String getDefaultSupplier_id() {
/* 28 */     return this.defaultSupplier_id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDefaultSupplier_id(String defaultSupplier_id) {
/* 34 */     this.defaultSupplier_id = defaultSupplier_id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPurchaseCategory_id() {
/* 40 */     return this.purchaseCategory_id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPurchaseCategory_id(String purchaseCategory_id) {
/* 46 */     this.purchaseCategory_id = purchaseCategory_id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 52 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 58 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseSubCategoryQueryForm() {
/* 63 */     setOrder(PurchaseSubCategoryQueryOrder.DESCRIPTION.getName());
/* 64 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/PurchaseSubCategoryQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */