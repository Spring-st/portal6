/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.SiteQueryOrder;
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
/*    */ 
/*    */ 
/*    */ public class SiteQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String name;
/*    */   private String enabled;
/*    */   
/*    */   public String getEnabled() {
/* 34 */     return this.enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabled(String enabled) {
/* 41 */     this.enabled = enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 56 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SiteQueryForm() {
/* 63 */     setOrder(SiteQueryOrder.NAME.getName());
/* 64 */     setDescend(false);
/* 65 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/SiteQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */