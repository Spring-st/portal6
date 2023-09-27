/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
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
/*     */ public class ExpenseCategoryQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String site_id;
/*     */   private String description;
/*     */   private String type;
/*     */   private String referenceErpId;
/*     */   private String enabled;
/*     */   
/*     */   public String getId() {
/*  30 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  38 */     this.id = id;
/*     */   }
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
/*     */   public String getSite_id() {
/*  52 */     return this.site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite_id(String site_id) {
/*  60 */     this.site_id = site_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  70 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  78 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  87 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/*  95 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReferenceErpId() {
/* 104 */     return this.referenceErpId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferenceErpId(String referenceErpId) {
/* 112 */     this.referenceErpId = referenceErpId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/* 121 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/* 129 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategoryQueryForm() {
/* 135 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/* 136 */     setOrder(ExpenseCategoryQueryOrder.DESCRIPTION.getName());
/* 137 */     setDescend(false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/ExpenseCategoryQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */