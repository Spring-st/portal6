/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.SalesPreshiporderItemDao;
/*    */ import com.aof.model.product.SalesPreshiporderItem;
/*    */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SalesPreshiporderItemManagerImpl
/*    */   extends BaseManager
/*    */   implements SalesPreshiporderItemManager {
/*    */   private SalesPreshiporderItemDao dao;
/*    */   
/*    */   public SalesPreshiporderItemDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(SalesPreshiporderItemDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem insert(SalesPreshiporderItem salesPreshiporderItem) {
/* 29 */     return this.dao.insert(salesPreshiporderItem);
/*    */   }
/*    */   
/*    */   public void remove(SalesPreshiporderItem salesPreshiporderItem) {
/* 33 */     this.dao.remove(salesPreshiporderItem);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem update(SalesPreshiporderItem salesPreshiporderItem) {
/* 37 */     return this.dao.update(salesPreshiporderItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderItemQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesPreshiporderItemManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */