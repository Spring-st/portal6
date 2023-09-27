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
/*    */ 
/*    */ public class LoginForm
/*    */   extends ActionForm
/*    */ {
/*    */   private String loginName;
/*    */   private String password;
/*    */   private String locale;
/*    */   
/*    */   public String getLocale() {
/* 29 */     return this.locale;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLocale(String locale) {
/* 37 */     this.locale = locale;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLoginName() {
/* 44 */     return this.loginName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLoginName(String loginName) {
/* 52 */     this.loginName = loginName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPassword() {
/* 59 */     return this.password;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPassword(String password) {
/* 67 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/LoginForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */