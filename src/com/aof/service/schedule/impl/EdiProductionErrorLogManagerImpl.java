/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.EdiProductionErrorLogDao;
/*    */ import com.aof.model.schedule.EdiProductionErrorLog;
/*    */ import com.aof.model.schedule.query.EdiProductionErrorLogQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.EdiProductionErrorLogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class EdiProductionErrorLogManagerImpl
/*    */   extends BaseManager implements EdiProductionErrorLogManager {
/*    */   private EdiProductionErrorLogDao dao;
/*    */   
/*    */   public EdiProductionErrorLogDao getDao() {
/* 16 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(EdiProductionErrorLogDao dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public EdiProductionErrorLog save(EdiProductionErrorLog entity) {
/* 25 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EdiProductionErrorLog getEdiProductionErrorLog(Integer id) {
/* 30 */     return this.dao.getEdiProductionErrorLog(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(EdiProductionErrorLog entity) {
/* 35 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EdiProductionErrorLog update(EdiProductionErrorLog entity) {
/* 40 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 45 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<EdiProductionErrorLog> getList(Map conditions, int pageNum, int pageSize, EdiProductionErrorLogQueryOrder order, boolean descend) {
/* 50 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/EdiProductionErrorLogManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */