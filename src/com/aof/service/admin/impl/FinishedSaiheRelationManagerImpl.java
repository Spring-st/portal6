/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.FinishedSaiheRelationDAO;
/*     */ import com.aof.model.admin.FinishedSaiheRelation;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.FinishedSaiheRelationManager;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FinishedSaiheRelationManagerImpl
/*     */   extends BaseManager
/*     */   implements FinishedSaiheRelationManager
/*     */ {
/*     */   private FinishedSaiheRelationDAO dao;
/*     */   
/*     */   public void setFinishedSaiheRelationDAO(FinishedSaiheRelationDAO dao) {
/*  35 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation getFinishedSaiheRelation(Integer id) {
/*  46 */     return this.dao.getFinishedSaiheRelation(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation updateFinishedSaiheRelation(FinishedSaiheRelation function) {
/*  57 */     return this.dao.updateFinishedSaiheRelation(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation insertFinishedSaiheRelation(FinishedSaiheRelation function) {
/*  68 */     return this.dao.insertFinishedSaiheRelation(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFinishedSaiheRelationListCount(Map conditions) {
/*  78 */     return this.dao.getFinishedSaiheRelationListCount(conditions);
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
/*     */   public List getFinishedSaiheRelationList(Map conditions, int pageNo, int pageSize, FinishedSaiheRelationQueryOrder order, boolean descend) {
/*  90 */     return this.dao.getFinishedSaiheRelationList(conditions, pageNo, pageSize, 
/*  91 */         order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteFinishedSaiheRelation(FinishedSaiheRelation finishedSaiheRelation) {
/*  97 */     this.dao.deleteFinishedSaiheRelation(finishedSaiheRelation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDao(FinishedSaiheRelationDAO dao) {
/* 103 */     this.dao = dao;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/FinishedSaiheRelationManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */