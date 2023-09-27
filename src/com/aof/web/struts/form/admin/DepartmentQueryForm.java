/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ import com.shcnc.struts.form.ComboBox;
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
/*    */ public class DepartmentQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/* 21 */   private ComboBox site = new ComboBox("id", "name");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ComboBox getSite() {
/* 27 */     return this.site;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSite(ComboBox site) {
/* 35 */     this.site = site;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/DepartmentQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */