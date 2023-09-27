/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String siteId;
/*     */   private String departmentId;
/*     */   private String loginName;
/*     */   private String name;
/*     */   private String enabled;
/*     */   private String roleId;
/*     */   
/*     */   public String getRoleId() {
/*  36 */     return this.roleId;
/*     */   }
/*     */   
/*     */   public void setRoleId(String roleId) {
/*  40 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/*  47 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/*  54 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDepartmentId() {
/*  61 */     return this.departmentId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepartmentId(String departmentId) {
/*  69 */     this.departmentId = departmentId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLoginName() {
/*  77 */     return this.loginName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginName(String loginName) {
/*  85 */     this.loginName = loginName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  92 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 100 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/* 107 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/* 115 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UserQueryForm() {
/* 121 */     setOrder(UserQueryOrder.LOGINNAME.getName());
/* 122 */     setDescend(false);
/* 123 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/UserQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */