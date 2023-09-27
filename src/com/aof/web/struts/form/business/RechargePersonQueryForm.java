/*    */ package com.aof.web.struts.form.business;
/*    */ 
/*    */ import com.aof.model.admin.query.UserQueryOrder;
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
/*    */ public class RechargePersonQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private String siteId;
/*    */   private String departmentId;
/*    */   private String loginName;
/*    */   private String name;
/*    */   
/*    */   public String getSiteId() {
/* 33 */     return this.siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSiteId(String siteId) {
/* 41 */     this.siteId = siteId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDepartmentId() {
/* 48 */     return this.departmentId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDepartmentId(String departmentId) {
/* 56 */     this.departmentId = departmentId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLoginName() {
/* 63 */     return this.loginName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLoginName(String loginName) {
/* 71 */     this.loginName = loginName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 78 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 86 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RechargePersonQueryForm() {
/* 92 */     setOrder(UserQueryOrder.LOGINNAME.getName());
/* 93 */     setDescend(false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/business/RechargePersonQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */