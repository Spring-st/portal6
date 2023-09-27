/*    */ package com.aof.web.struts.form.business;
/*    */ 
/*    */ import com.aof.model.admin.query.CustomerQueryOrder;
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
/*    */ public class RechargeCustomerQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String siteId;
/*    */   private String code;
/*    */   private String description;
/*    */   
/*    */   public String getSiteId() {
/* 31 */     return this.siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 39 */     this.siteId = siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 46 */     return this.code;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCode(String code) {
/* 54 */     this.code = code;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 61 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 69 */     this.description = description;
/*    */   }
/*    */   
/*    */   public RechargeCustomerQueryForm() {
/* 73 */     setOrder(CustomerQueryOrder.CODE.getName());
/* 74 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/business/RechargeCustomerQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */