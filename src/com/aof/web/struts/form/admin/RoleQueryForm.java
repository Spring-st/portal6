/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.RoleQueryOrder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private ComboBox type;
/*    */   
/*    */   public String getId() {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 39 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 46 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 54 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ComboBox getType() {
/* 61 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setType(ComboBox type) {
/* 69 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RoleQueryForm() {
/* 75 */     setType(new ComboBox("enumCode", "enumCode"));
/* 76 */     setOrder(RoleQueryOrder.NAME.getName());
/* 77 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/RoleQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */