/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.SalesOrderItemDao;
/*    */ import com.aof.model.product.SalesOrder;
/*    */ import com.aof.model.product.SalesOrderItem;
/*    */ import com.aof.model.product.query.SalesOrderItemQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.SalesOrderItemManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SalesOrderItemManagerImpl
/*    */   extends BaseManager
/*    */   implements SalesOrderItemManager {
/*    */   private SalesOrderItemDao dao;
/*    */   
/*    */   public SalesOrderItemDao getDao() {
/* 18 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(SalesOrderItemDao dao) {
/* 22 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SalesOrderItem getById(Integer id) {
/* 26 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public SalesOrderItem insert(SalesOrderItem salesOrderItem) {
/* 30 */     return this.dao.insert(salesOrderItem);
/*    */   }
/*    */   
/*    */   public void remove(SalesOrderItem salesOrderItem) {
/* 34 */     this.dao.remove(salesOrderItem);
/*    */   }
/*    */   
/*    */   public SalesOrderItem update(SalesOrderItem salesOrderItem) {
/* 38 */     return this.dao.update(salesOrderItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 43 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesOrderItemQueryOrder order, boolean descend) {
/* 48 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public Boolean iscloseSalesOrderItem(SalesOrder salesOrder) {
/* 52 */     List<Integer> itemCount = this.dao.getObjectList("select count(*) from SalesOrderItem item where item.soId.id='" + salesOrder.getId() + "'");
/* 53 */     List<Integer> overItemCount = this.dao.getObjectList("select count(*) from SalesOrderItem item where item.soId.id='" + salesOrder.getId() + "' and item.qtyopen=0");
/* 54 */     return Boolean.valueOf(itemCount.equals(overItemCount));
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesOrderItemManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */