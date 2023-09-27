/*     */ package com.aof.web.struts.form.business.rule;
/*     */ 
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
/*     */ public class ApproverDelegateQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String fromDate1;
/*     */   private String fromDate2;
/*     */   private String toDate1;
/*     */   private String toDate2;
/*     */   private String originalApprover_id;
/*     */   private String delegateApprover_id;
/*     */   private String type;
/*     */   private String fromDate;
/*     */   private String toDate;
/*     */   
/*     */   public String getFromDate2() {
/*  31 */     return this.fromDate2;
/*     */   }
/*     */   
/*     */   public void setFromDate2(String fromDate2) {
/*  35 */     this.fromDate2 = fromDate2;
/*     */   }
/*     */   
/*     */   public String getFromDate1() {
/*  39 */     return this.fromDate1;
/*     */   }
/*     */   
/*     */   public void setFromDate1(String fromDate1) {
/*  43 */     this.fromDate1 = fromDate1;
/*     */   }
/*     */   
/*     */   public String getToDate1() {
/*  47 */     return this.toDate1;
/*     */   }
/*     */   
/*     */   public void setToDate1(String toDate1) {
/*  51 */     this.toDate1 = toDate1;
/*     */   }
/*     */   
/*     */   public String getToDate2() {
/*  55 */     return this.toDate2;
/*     */   }
/*     */   
/*     */   public void setToDate2(String toDate2) {
/*  59 */     this.toDate2 = toDate2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  74 */     this.id = id;
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
/*     */ 
/*     */   
/*     */   public String getDelegateApprover_id() {
/*  90 */     return this.delegateApprover_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDelegateApprover_id(String delegateApprover_id) {
/*  98 */     this.delegateApprover_id = delegateApprover_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalApprover_id() {
/* 105 */     return this.originalApprover_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalApprover_id(String originalApprover_id) {
/* 113 */     this.originalApprover_id = originalApprover_id;
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
/*     */   public String getFromDate() {
/* 127 */     return this.fromDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromDate(String fromDate) {
/* 135 */     this.fromDate = fromDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToDate() {
/* 142 */     return this.toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToDate(String toDate) {
/* 150 */     this.toDate = toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 157 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/* 165 */     this.type = type;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/business/rule/ApproverDelegateQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */