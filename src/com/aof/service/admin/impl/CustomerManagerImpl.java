/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.CustomerDAO;
/*    */ import com.aof.model.admin.Customer;
/*    */ import com.aof.model.admin.query.CustomerQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.CustomerManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomerManagerImpl
/*    */   extends BaseManager
/*    */   implements CustomerManager
/*    */ {
/*    */   private CustomerDAO dao;
/*    */   
/*    */   public void setCustomerDAO(CustomerDAO dao) {
/* 36 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Customer getCustomer(String code) {
/* 45 */     return this.dao.getCustomer(code);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCustomerListCount(Map conditions) {
/* 54 */     return this.dao.getCustomerListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getCustomerList(Map conditions, int pageNo, int pageSize, CustomerQueryOrder order, boolean descend) {
/* 64 */     return this.dao.getCustomerList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/CustomerManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */