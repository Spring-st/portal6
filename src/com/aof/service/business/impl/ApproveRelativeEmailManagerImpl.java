/*     */ package com.aof.service.business.impl;
/*     */ 
/*     */ import com.aof.dao.business.rule.ApproverDelegateDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.Approvable;
/*     */ import com.aof.model.business.BaseApproveRequest;
/*     */ import com.aof.model.business.rule.ApproverDelegate;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryCondition;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.business.ApproveRelativeEmailManager;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApproveRelativeEmailManagerImpl
/*     */   extends BaseManager
/*     */   implements ApproveRelativeEmailManager
/*     */ {
/*     */   private EmailManager emailManager;
/*     */   private ApproverDelegateDAO approverDelegateDAO;
/*     */   
/*     */   public void setEmailManager(EmailManager emailManager) {
/*  30 */     this.emailManager = emailManager;
/*     */   }
/*     */   
/*     */   public void setApproverDelegateDAO(ApproverDelegateDAO approverDelegateDAO) {
/*  34 */     this.approverDelegateDAO = approverDelegateDAO;
/*     */   }
/*     */   
/*     */   public void sendApprovalEmail(Approvable target, ApproverDelegateType type, BaseApproveRequest approveRequest) {
/*  38 */     User approver = approveRequest.getApprover();
/*  39 */     sendApprovalEmail(target, approver);
/*  40 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  41 */     conditions.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, approver.getId());
/*  42 */     conditions.put(ApproverDelegateQueryCondition.TYPE_EQ, type);
/*  43 */     long now = getTodayTimeMillis();
/*  44 */     conditions.put(ApproverDelegateQueryCondition.FROMDATE_LT, new Date(now + 86400000L));
/*  45 */     conditions.put(ApproverDelegateQueryCondition.TODATE_GE, new Date(now));
/*  46 */     for (Iterator<ApproverDelegate> itor = this.approverDelegateDAO.getApproverDelegateList(conditions, 0, -1, null, false).iterator(); itor.hasNext(); ) {
/*  47 */       ApproverDelegate delegate = itor.next();
/*  48 */       sendApprovalEmail(target, delegate.getDelegateApprover());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendApprovalEmail(Approvable target, User approver) {
/*  53 */     Map<String, User> context = target.getApprovalNotifyEmailContext();
/*  54 */     context.put("user", approver);
/*  55 */     this.emailManager.insertEmailBatch(target.getLogSite(), approver.getEmail(), target.getApprovalNotifyEmailTemplateName(), target.getRefNo(), context, target.getApprovalBatchEmailTemplateName());
/*     */   }
/*     */   
/*     */   public void sendApprovedEmail(Approvable target) {
/*  59 */     User requestor = target.getRequestor();
/*  60 */     Map<String, User> context = target.getApprovalNotifyEmailContext();
/*  61 */     context.put("user", requestor);
/*  62 */     this.emailManager.insertEmail(target.getLogSite(), requestor.getEmail(), target.getApprovedNotifyEmailTemplateName(), context);
/*     */     
/*  64 */     User creator = target.getCreator();
/*  65 */     if (creator != null && !requestor.equals(creator)) {
/*  66 */       Map<String, User> context2 = target.getApprovalNotifyEmailContext();
/*  67 */       context2.put("user", creator);
/*  68 */       this.emailManager.insertEmail(target.getLogSite(), creator.getEmail(), target.getApprovedNotifyEmailTemplateName(), context2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendRejectedEmail(Approvable target, BaseApproveRequest approveRequest) {
/*  73 */     User rejecter = approveRequest.getApprover();
/*  74 */     User requestor = target.getRequestor();
/*  75 */     Map<String, User> context = target.getRejectedNotifyEmailContext();
/*  76 */     context.put("user", requestor);
/*  77 */     context.put("rejecterName", rejecter.getName());
/*  78 */     context.put("comment", approveRequest.getComment());
/*  79 */     this.emailManager.insertEmail(target.getLogSite(), requestor.getEmail(), target.getRejectedNotifyEmailTemplateName(), context);
/*     */     
/*  81 */     User creator = target.getCreator();
/*  82 */     if (creator != null && !requestor.equals(creator)) {
/*  83 */       Map<String, User> context2 = target.getRejectedNotifyEmailContext();
/*  84 */       context2.put("user", creator);
/*  85 */       context2.put("rejecterName", rejecter.getName());
/*  86 */       context2.put("comment", approveRequest.getComment());
/*  87 */       this.emailManager.insertEmail(target.getLogSite(), requestor.getEmail(), target.getRejectedNotifyEmailTemplateName(), context2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendRejectedEmail(Approvable target, String rejecterName, String comment) {
/*  92 */     Map<String, User> context = target.getRejectedNotifyEmailContext();
/*  93 */     User requestor = target.getRequestor();
/*  94 */     context.put("user", requestor);
/*  95 */     context.put("rejecterName", rejecterName);
/*  96 */     context.put("comment", comment);
/*  97 */     this.emailManager.insertEmail(target.getLogSite(), requestor.getEmail(), target.getRejectedNotifyEmailTemplateName(), context);
/*     */     
/*  99 */     User creator = target.getCreator();
/* 100 */     if (creator != null && !requestor.equals(creator)) {
/* 101 */       Map<String, User> context2 = target.getRejectedNotifyEmailContext();
/* 102 */       context2.put("user", creator);
/* 103 */       context2.put("rejecterName", rejecterName);
/* 104 */       context2.put("comment", comment);
/* 105 */       this.emailManager.insertEmail(target.getLogSite(), requestor.getEmail(), target.getRejectedNotifyEmailTemplateName(), context2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deleteWithdrawEmail(Approvable target) {
/* 110 */     String refNo = target.getRefNo();
/* 111 */     this.emailManager.deleteEmailBatch(refNo);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/impl/ApproveRelativeEmailManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */