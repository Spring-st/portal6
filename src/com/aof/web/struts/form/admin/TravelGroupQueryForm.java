/*    */ package com.aof.web.struts.form.admin;
/*    */ 
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
/*    */ public class TravelGroupQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String site_id;
/*    */   private String name;
/*    */   private String enabled;
/*    */   
/*    */   public String getId() {
/* 27 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 35 */     this.id = id;
/*    */   }
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
/*    */   public String getSite_id() {
/* 49 */     return this.site_id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSite_id(String site_id) {
/* 57 */     this.site_id = site_id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEnabled() {
/* 69 */     return this.enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabled(String enabled) {
/* 77 */     this.enabled = enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 84 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 92 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/TravelGroupQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */