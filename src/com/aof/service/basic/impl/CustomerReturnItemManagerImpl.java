/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.CustomerReturnItemDAO;
/*    */ import com.aof.model.basic.CustomerReturnItem;
/*    */ import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.CustomerReturnItemManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CustomerReturnItemManagerImpl
/*    */   extends BaseManager implements CustomerReturnItemManager {
/*    */   private CustomerReturnItemDAO dao;
/*    */   
/*    */   public CustomerReturnItemDAO getDao() {
/* 16 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(CustomerReturnItemDAO dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public CustomerReturnItem save(CustomerReturnItem entity) {
/* 25 */     return this.dao.save(entity);
/*    */   }
/*    */   
/*    */   public CustomerReturnItem getCustomerReturnsItem(Integer id) {
/* 29 */     return this.dao.getCustomerreturnsItem(id);
/*    */   }
/*    */   
/*    */   public void delete(CustomerReturnItem entity) {
/* 33 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public CustomerReturnItem update(CustomerReturnItem entity) {
/* 38 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 43 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNum, int pageSize, CustomerReturnItemQueryOrder order, boolean descend) {
/* 48 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/CustomerReturnItemManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */