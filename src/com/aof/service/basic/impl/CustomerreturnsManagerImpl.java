/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.CustomerreturnsDao;
/*    */ import com.aof.model.basic.Customerreturns;
/*    */ import com.aof.model.basic.query.CustomerreturnsQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.CustomerreturnsManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class CustomerreturnsManagerImpl
/*    */   extends BaseManager
/*    */   implements CustomerreturnsManager
/*    */ {
/*    */   private CustomerreturnsDao dao;
/*    */   
/*    */   public Customerreturns save(Customerreturns entity) {
/* 19 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Customerreturns getCustomerreturns(Integer id) {
/* 24 */     return this.dao.getCustomerreturns(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(Customerreturns entity) {
/* 29 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Customerreturns update(Customerreturns entity) {
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
/*    */   public List<Customerreturns> getList(Map conditions, int pageNum, int pageSize, CustomerreturnsQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(CustomerreturnsDao dao) {
/* 49 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public CustomerreturnsDao getDao() {
/* 53 */     return this.dao;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/CustomerreturnsManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */