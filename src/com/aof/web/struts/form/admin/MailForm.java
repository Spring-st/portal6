/*    */ package com.aof.web.struts.form.admin;
/*    */ 
/*    */ import org.apache.struts.action.ActionForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailForm
/*    */   extends ActionForm
/*    */ {
/*    */   private String server;
/*    */   private String username;
/*    */   private String password;
/*    */   
/*    */   public String getPassword() {
/* 18 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 21 */     this.password = password;
/*    */   }
/*    */   public String getServer() {
/* 24 */     return this.server;
/*    */   }
/*    */   public void setServer(String server) {
/* 27 */     this.server = server;
/*    */   }
/*    */   public String getUsername() {
/* 30 */     return this.username;
/*    */   }
/*    */   public void setUsername(String username) {
/* 33 */     this.username = username;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/MailForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */