/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.NjitNpoPlanDao;
/*    */ import com.aof.model.schedule.NjitNpoPlan;
/*    */ import com.aof.model.schedule.query.NjitNpoPlanQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.NjitNpoPlanManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class NjitNpoPlanManagerImpl
/*    */   extends BaseManager
/*    */   implements NjitNpoPlanManager
/*    */ {
/*    */   private NjitNpoPlanDao dao;
/*    */   
/*    */   public NjitNpoPlan save(NjitNpoPlan entity) {
/* 19 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public NjitNpoPlan getNjitNpoPlan(Integer id) {
/* 24 */     return this.dao.getNjitNpoPlan(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(NjitNpoPlan entity) {
/* 29 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public NjitNpoPlan update(NjitNpoPlan entity) {
/* 34 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 39 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<NjitNpoPlan> getList(Map conditions, int pageNum, int pageSize, NjitNpoPlanQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(NjitNpoPlanDao dao) {
/* 49 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public NjitNpoPlanDao getDao() {
/* 53 */     return this.dao;
/*    */   }
/*    */   
/*    */   public NjitNpoPlan getNjitNpoPlanByPart(String partId) {
/* 57 */     return this.dao.getNjitNpoPlanByPart(partId);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/NjitNpoPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */