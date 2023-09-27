/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import org.apache.struts.action.ActionForm;
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
/*    */ public class MetadataForm
/*    */   extends ActionForm
/*    */ {
/*    */   private String id;
/*    */   private String description;
/*    */   
/*    */   public String getDescription() {
/* 27 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 34 */     this.description = description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getId() {
/* 41 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 48 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/MetadataForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */