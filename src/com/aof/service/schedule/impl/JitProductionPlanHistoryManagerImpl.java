/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.JitProductionPlanHistoryDao;
/*    */ import com.aof.model.schedule.JitProductionPlanHistory;
/*    */ import com.aof.model.schedule.query.JitProductionPlanHistoryQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.JitProductionPlanHistoryManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class JitProductionPlanHistoryManagerImpl
/*    */   extends BaseManager implements JitProductionPlanHistoryManager {
/*    */   public JitProductionPlanHistoryDao jitProductionPlanHistoryDao;
/*    */   
/*    */   public JitProductionPlanHistoryDao getJitProductionPlanHistoryDao() {
/* 16 */     return this.jitProductionPlanHistoryDao;
/*    */   }
/*    */   
/*    */   public void setJitProductionPlanHistoryDao(JitProductionPlanHistoryDao jitProductionPlanHistoryDao) {
/* 20 */     this.jitProductionPlanHistoryDao = jitProductionPlanHistoryDao;
/*    */   }
/*    */ 
/*    */   
/*    */   public JitProductionPlanHistory save(JitProductionPlanHistory entity) {
/* 25 */     return this.jitProductionPlanHistoryDao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public JitProductionPlanHistory getJitProductionPlanHistory(Integer id) {
/* 30 */     return this.jitProductionPlanHistoryDao.getJitProductionPlanHistory(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(JitProductionPlanHistory entity) {
/* 35 */     this.jitProductionPlanHistoryDao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public JitProductionPlanHistory update(JitProductionPlanHistory entity) {
/* 40 */     return this.jitProductionPlanHistoryDao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 45 */     return this.jitProductionPlanHistoryDao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<JitProductionPlanHistory> getList(Map conditions, int pageNum, int pageSize, JitProductionPlanHistoryQueryOrder order, boolean descend) {
/* 51 */     return this.jitProductionPlanHistoryDao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/JitProductionPlanHistoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */