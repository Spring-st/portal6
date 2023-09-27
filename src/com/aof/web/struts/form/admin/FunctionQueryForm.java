/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import com.aof.model.admin.query.FunctionQueryOrder;
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
/*    */ public class FunctionQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String name;
/*    */   
/*    */   public String getName() {
/* 26 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 34 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public FunctionQueryForm() {
/* 39 */     setOrder(FunctionQueryOrder.NAME.getName());
/* 40 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/FunctionQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */