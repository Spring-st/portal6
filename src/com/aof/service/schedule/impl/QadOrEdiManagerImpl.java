/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.QadOrEdiDao;
/*    */ import com.aof.model.schedule.QadOrEdi;
/*    */ import com.aof.model.schedule.query.QadOrEdiQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.QadOrEdiManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class QadOrEdiManagerImpl
/*    */   extends BaseManager
/*    */   implements QadOrEdiManager
/*    */ {
/*    */   private QadOrEdiDao dao;
/*    */   
/*    */   public QadOrEdi save(QadOrEdi entity) {
/* 19 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public QadOrEdi getQadOrEdi(Integer id) {
/* 24 */     return this.dao.getQadOrEdi(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(QadOrEdi entity) {
/* 29 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public QadOrEdi update(QadOrEdi entity) {
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
/*    */   public List<QadOrEdi> getList(Map conditions, int pageNum, int pageSize, QadOrEdiQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(QadOrEdiDao dao) {
/* 49 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public QadOrEdiDao getDao() {
/* 53 */     return this.dao;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/QadOrEdiManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */