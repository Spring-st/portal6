/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.SystemLogQueryOrder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemLogQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String siteId;
/*     */   private String userId;
/*     */   private String userName;
/*     */   private String targetObject;
/*     */   private String actionDateFrom;
/*     */   private String actionDateTo;
/*     */   
/*     */   public String getActionDateFrom() {
/*  43 */     return this.actionDateFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionDateFrom(String actionDateFrom) {
/*  53 */     this.actionDateFrom = actionDateFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActionDateTo() {
/*  63 */     return this.actionDateTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionDateTo(String actionDateTo) {
/*  73 */     this.actionDateTo = actionDateTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSiteId() {
/*  83 */     return this.siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteId(String siteId) {
/*  93 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTargetObject() {
/* 104 */     return this.targetObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetObject(String targetObject) {
/* 114 */     this.targetObject = targetObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserId() {
/* 124 */     return this.userId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String userId) {
/* 134 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserName() {
/* 144 */     return this.userName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserName(String userName) {
/* 154 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SystemLogQueryForm() {
/* 160 */     setOrder(SystemLogQueryOrder.SITE_NAME.getName());
/* 161 */     setDescend(false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/SystemLogQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */