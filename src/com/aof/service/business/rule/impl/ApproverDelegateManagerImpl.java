/*     */ package com.aof.service.business.rule.impl;
/*     */ 
/*     */ import com.aof.dao.business.rule.ApproverDelegateDAO;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.business.rule.ApproverDelegate;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
/*     */ import com.aof.model.business.rule.query.ApproverQueryCondition;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.service.business.rule.ApproverDelegateManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ApproverDelegateManagerImpl
/*     */   extends BaseManager
/*     */   implements ApproverDelegateManager
/*     */ {
/*     */   private FunctionManager functionManager;
/*     */   private UserManager userManager;
/*     */   private ApproverDelegateDAO dao;
/*     */   
/*     */   public void setApproverDelegateDAO(ApproverDelegateDAO dao) {
/*  41 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegate getApproverDelegate(Integer id) throws Exception {
/*  48 */     return this.dao.getApproverDelegate(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegate updateApproverDelegate(ApproverDelegate function) throws Exception {
/*  55 */     return this.dao.updateApproverDelegate(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApproverDelegate insertApproverDelegate(ApproverDelegate function) throws Exception {
/*  62 */     return this.dao.insertApproverDelegate(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApproverDelegateListCount(Map conditions) throws Exception {
/*  70 */     return this.dao.getApproverDelegateListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getApproverDelegateList(Map conditions, int pageNo, int pageSize, ApproverDelegateQueryOrder order, boolean descend) throws Exception {
/*  77 */     return this.dao.getApproverDelegateList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getApproverList(ApproverQueryCondition cond, int pageNo, int pageSize, ApproverDelegateQueryOrder order, boolean descend) throws Exception {
/*  85 */     return this.userManager.getUserList(getApproveConds(cond), pageNo, pageSize, UserQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getApproveConds(ApproverQueryCondition cond) throws Exception {
/*  90 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  91 */     Function function = this.functionManager.getFunction(cond.getRuleType().getPrefixUrl());
/*  92 */     conds.put(UserQueryCondition.DEPARTMENT_ID_EQ, cond.getDepartmentId());
/*  93 */     conds.put(UserQueryCondition.SITE_ID_EQ, cond.getSiteId());
/*  94 */     conds.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/*  95 */     conds.put(UserQueryCondition.FUNCTION_ID_EQ, function.getId());
/*  96 */     return conds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApproverListCount(ApproverQueryCondition cond) throws Exception {
/* 103 */     return this.userManager.getUserListCount(getApproveConds(cond));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionManager(FunctionManager functionManager) {
/* 110 */     this.functionManager = functionManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserManager(UserManager userManager) {
/* 117 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDelegateApprover(ApproverDelegateType approverDelegateType, Integer originalApproverId, Integer delegateApproverId) {
/* 124 */     return this.dao.isDelegateApprover(approverDelegateType, originalApproverId, delegateApproverId);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/rule/impl/ApproverDelegateManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */