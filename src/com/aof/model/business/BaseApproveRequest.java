/*     */ package com.aof.model.business;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.ApproveStatus;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class BaseApproveRequest
/*     */   implements Serializable
/*     */ {
/*  34 */   private int hashValue = 0;
/*     */   
/*     */   private String approveRequestId;
/*     */   
/*     */   private User approver;
/*     */   
/*     */   private User actualApprover;
/*     */   
/*     */   private int seq;
/*     */   
/*     */   private ApproveStatus status;
/*     */   
/*     */   private Date approveDate;
/*     */   
/*     */   private String comment;
/*     */   private YesNo canModify;
/*     */   private Date yourTurnDate;
/*     */   private Date lastEmailDate;
/*     */   private int sentEmailTimes;
/*     */   
/*     */   public User getActualApprover() {
/*  55 */     return this.actualApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActualApprover(User actualApprover) {
/*  62 */     this.actualApprover = actualApprover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseApproveRequest() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseApproveRequest(String id, User approver) {
/*  77 */     setApproveRequestId(id);
/*  78 */     setApprover(approver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApproveRequestId() {
/*  85 */     return this.approveRequestId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApproveRequestId(String approveRequestId) {
/*  92 */     this.hashValue = 0;
/*  93 */     this.approveRequestId = approveRequestId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getApproveDate() {
/* 100 */     return this.approveDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApproveDate(Date approveDate) {
/* 107 */     this.approveDate = approveDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getApprover() {
/* 114 */     return this.approver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApprover(User approver) {
/* 121 */     this.hashValue = 0;
/* 122 */     this.approver = approver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getCanModify() {
/* 129 */     return this.canModify;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanModify(YesNo canModify) {
/* 136 */     this.canModify = canModify;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComment() {
/* 143 */     return this.comment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComment(String comment) {
/* 150 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeq() {
/* 157 */     return this.seq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeq(int seq) {
/* 164 */     this.seq = seq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproveStatus getStatus() {
/* 171 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(ApproveStatus status) {
/* 178 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getLastEmailDate() {
/* 185 */     return this.lastEmailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastEmailDate(Date lastEmailDate) {
/* 192 */     this.lastEmailDate = lastEmailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSentEmailTimes() {
/* 199 */     return this.sentEmailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSentEmailTimes(int sentEmailTimes) {
/* 206 */     this.sentEmailTimes = sentEmailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getYourTurnDate() {
/* 213 */     return this.yourTurnDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYourTurnDate(Date yourTurnDate) {
/* 220 */     this.yourTurnDate = yourTurnDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 239 */     if (this.hashValue == 0) {
/* 240 */       int result = 17;
/* 241 */       int idValue = (getApproveRequestId() == null) ? 0 : getApproveRequestId().hashCode();
/* 242 */       result = result * 37 + idValue;
/* 243 */       int approverValue = (getApprover() == null) ? 0 : getApprover().hashCode();
/* 244 */       result = result * 37 + approverValue;
/* 245 */       this.hashValue = result;
/*     */     } 
/* 247 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/BaseApproveRequest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */