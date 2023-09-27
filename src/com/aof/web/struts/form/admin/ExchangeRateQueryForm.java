/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.ExchangeRateQueryOrder;
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
/*    */ 
/*    */ public class ExchangeRateQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String currencyCode;
/*    */   private String siteId;
/*    */   
/*    */   public String getCurrencyCode() {
/* 32 */     return this.currencyCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCurrencyCode(String currencyCode) {
/* 42 */     this.currencyCode = currencyCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSiteId() {
/* 52 */     return this.siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 62 */     this.siteId = siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ExchangeRateQueryForm() {
/* 68 */     setOrder(ExchangeRateQueryOrder.CODE.getName());
/* 69 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/ExchangeRateQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */