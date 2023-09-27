/*     */ package com.aof.web.struts.form.business;
/*     */ 
/*     */ import com.aof.model.metadata.ApproveStatus;
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
/*     */ public class BaseApproveQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String code;
/*     */   private String title;
/*     */   private String approveStatus;
/*     */   private String submitDateFrom;
/*     */   private String submitDateTo;
/*     */   
/*     */   public String getApproveStatus() {
/*  37 */     return this.approveStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApproveStatus(String approveStatus) {
/*  44 */     this.approveStatus = approveStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/*  52 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCode(String code) {
/*  59 */     this.code = code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubmitDateFrom() {
/*  66 */     return this.submitDateFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubmitDateFrom(String submitDateFrom) {
/*  73 */     this.submitDateFrom = submitDateFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubmitDateTo() {
/*  80 */     return this.submitDateTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubmitDateTo(String submitDateTo) {
/*  87 */     this.submitDateTo = submitDateTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  94 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 101 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseApproveQueryForm() {
/* 107 */     setApproveStatus(ApproveStatus.WAITING_FOR_APPROVE.getEnumCode().toString());
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/business/BaseApproveQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */