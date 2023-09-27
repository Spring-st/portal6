/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.BasicCustomerDao;
/*    */ import com.aof.model.product.BasicCustomer;
/*    */ import com.aof.model.product.query.BasicCustomerQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.BasicCustomerManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BasicCustomerManagerImpl
/*    */   extends BaseManager
/*    */   implements BasicCustomerManager {
/*    */   private BasicCustomerDao dao;
/*    */   
/*    */   public BasicCustomerDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(BasicCustomerDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public BasicCustomer getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public BasicCustomer insert(BasicCustomer basicCustomer) {
/* 29 */     return this.dao.insert(basicCustomer);
/*    */   }
/*    */   
/*    */   public void remove(BasicCustomer basicCustomer) {
/* 33 */     this.dao.remove(basicCustomer);
/*    */   }
/*    */   
/*    */   public BasicCustomer update(BasicCustomer basicCustomer) {
/* 37 */     return this.dao.update(basicCustomer);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, BasicCustomerQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/BasicCustomerManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */