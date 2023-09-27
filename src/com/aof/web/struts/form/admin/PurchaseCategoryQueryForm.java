/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseCategoryQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String siteId;
/*    */   private String description;
/*    */   private String status;
/*    */   
/*    */   public String getId() {
/* 34 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getStatus() {
/* 46 */     return this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStatus(String status) {
/* 52 */     this.status = status;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSiteId() {
/* 58 */     return this.siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 64 */     this.siteId = siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 71 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 77 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseCategoryQueryForm() {
/* 82 */     setStatus(EnabledDisabled.ENABLED.getEnumCode().toString());
/* 83 */     setOrder(PurchaseCategoryQueryOrder.DESCRIPTION.getName());
/* 84 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/PurchaseCategoryQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */