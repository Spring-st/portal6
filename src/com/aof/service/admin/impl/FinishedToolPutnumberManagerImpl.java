/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.FinishedToolPutnumberDAO;
/*     */ import com.aof.model.admin.FinishedToolPutnumber;
/*     */ import com.aof.model.admin.query.FinishedToolPutnumberQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.FinishedToolPutnumberManager;
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
/*     */ public class FinishedToolPutnumberManagerImpl
/*     */   extends BaseManager
/*     */   implements FinishedToolPutnumberManager
/*     */ {
/*     */   private FinishedToolPutnumberDAO dao;
/*     */   
/*     */   public void setFinishedToolPutnumberDAO(FinishedToolPutnumberDAO dao) {
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
/*     */   public FinishedToolPutnumber getFinishedToolPutnumber(Integer id) {
/*  46 */     return this.dao.getFinishedToolPutnumber(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedToolPutnumber updateFinishedToolPutnumber(FinishedToolPutnumber function) {
/*  57 */     return this.dao.updateFinishedToolPutnumber(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedToolPutnumber insertFinishedToolPutnumber(FinishedToolPutnumber function) {
/*  68 */     return this.dao.insertFinishedToolPutnumber(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFinishedToolPutnumberListCount(Map conditions) {
/*  78 */     return this.dao.getFinishedToolPutnumberListCount(conditions);
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
/*     */   public List getFinishedToolPutnumberList(Map conditions, int pageNo, int pageSize, FinishedToolPutnumberQueryOrder order, boolean descend) {
/*  90 */     return this.dao.getFinishedToolPutnumberList(conditions, pageNo, pageSize, 
/*  91 */         order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteFinishedToolPutnumber(FinishedToolPutnumber finishedToolPutnumber) {
/*  97 */     this.dao.deleteFinishedToolPutnumber(finishedToolPutnumber);
/*     */   }
/*     */   
/*     */   public void setDao(FinishedToolPutnumberDAO dao) {
/* 101 */     this.dao = dao;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/FinishedToolPutnumberManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */