/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.WebMonitorQueryOrder;
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
/*    */ public class WebMonitorQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String siteId;
/*    */   
/*    */   public String getSiteId() {
/* 26 */     return this.siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 33 */     this.siteId = siteId;
/*    */   }
/*    */ 
/*    */   
/*    */   public WebMonitorQueryForm() {
/* 38 */     setOrder(WebMonitorQueryOrder.SITE.getName());
/* 39 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/WebMonitorQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */