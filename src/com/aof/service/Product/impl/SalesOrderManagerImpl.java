/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.SalesOrderDao;
/*    */ import com.aof.model.product.SalesOrder;
/*    */ import com.aof.model.product.query.SalesOrderQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.SalesOrderManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SalesOrderManagerImpl
/*    */   extends BaseManager
/*    */   implements SalesOrderManager {
/*    */   private SalesOrderDao dao;
/*    */   
/*    */   public SalesOrderDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(SalesOrderDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SalesOrder getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public SalesOrder insert(SalesOrder salesOrder) {
/* 29 */     return this.dao.insert(salesOrder);
/*    */   }
/*    */   
/*    */   public void remove(SalesOrder salesOrder) {
/* 33 */     this.dao.remove(salesOrder);
/*    */   }
/*    */   
/*    */   public SalesOrder update(SalesOrder salesOrder) {
/* 37 */     return this.dao.update(salesOrder);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesOrderQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesOrderManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */