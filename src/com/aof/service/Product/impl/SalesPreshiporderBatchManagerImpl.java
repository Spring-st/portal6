/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.SalesPreshiporderBatchDao;
/*    */ import com.aof.model.product.SalesPreshiporderBatch;
/*    */ import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.SalesPreshiporderBatchManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SalesPreshiporderBatchManagerImpl
/*    */   extends BaseManager
/*    */   implements SalesPreshiporderBatchManager {
/*    */   private SalesPreshiporderBatchDao dao;
/*    */   
/*    */   public SalesPreshiporderBatchDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(SalesPreshiporderBatchDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SalesPreshiporderBatch getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderBatch insert(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 29 */     return this.dao.insert(salesPreshiporderBatch);
/*    */   }
/*    */   
/*    */   public void remove(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 33 */     this.dao.remove(salesPreshiporderBatch);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderBatch update(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 37 */     return this.dao.update(salesPreshiporderBatch);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderBatchQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public List getNotWorkList(Integer salesPreshiporderId) {
/* 51 */     return this.dao.getNotWorkList(salesPreshiporderId);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesPreshiporderBatchManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */