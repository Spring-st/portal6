/*     */ package com.aof.model.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ public abstract class AbstractApproverDelegate
/*     */   implements Serializable
/*     */ {
/*  33 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private ApproverDelegateType type;
/*     */ 
/*     */   
/*     */   private User originalApprover;
/*     */ 
/*     */   
/*     */   private User delegateApprover;
/*     */ 
/*     */   
/*     */   private Date fromDate;
/*     */ 
/*     */   
/*     */   private Date toDate;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractApproverDelegate() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractApproverDelegate(Integer id) {
/*  60 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  69 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  78 */     this.hashValue = 0;
/*  79 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getDelegateApprover() {
/*  87 */     return this.delegateApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDelegateApprover(User delegateApprover) {
/*  94 */     this.delegateApprover = delegateApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getFromDate() {
/* 101 */     return this.fromDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromDate(Date fromDate) {
/* 108 */     this.fromDate = fromDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getOriginalApprover() {
/* 115 */     return this.originalApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalApprover(User originalApprover) {
/* 122 */     this.originalApprover = originalApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getToDate() {
/* 129 */     return this.toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToDate(Date toDate) {
/* 136 */     this.toDate = toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegateType getType() {
/* 143 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ApproverDelegateType type) {
/* 150 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 161 */     if (rhs == null) return false; 
/* 162 */     if (this == rhs) return true; 
/* 163 */     if (!(rhs instanceof ApproverDelegate)) return false; 
/* 164 */     ApproverDelegate that = (ApproverDelegate)rhs;
/* 165 */     if (getId() != null) return getId().equals(that.getId()); 
/* 166 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 177 */     if (this.hashValue == 0) {
/* 178 */       int result = 17;
/* 179 */       int siteIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 180 */       result = result * 37 + siteIdValue;
/* 181 */       this.hashValue = result;
/*     */     } 
/* 183 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractApproverDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */