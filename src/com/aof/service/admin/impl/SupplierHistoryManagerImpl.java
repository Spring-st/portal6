/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.SupplierHistoryDAO;
/*    */ import com.aof.model.admin.SupplierHistory;
/*    */ import com.aof.model.admin.query.SupplierHistoryQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.SupplierHistoryManager;
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
/*    */ public class SupplierHistoryManagerImpl
/*    */   extends BaseManager
/*    */   implements SupplierHistoryManager
/*    */ {
/*    */   private SupplierHistoryDAO dao;
/*    */   
/*    */   public void setSupplierHistoryDAO(SupplierHistoryDAO dao) {
/* 29 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SupplierHistory getSupplierHistory(Integer id) {
/* 33 */     return this.dao.getSupplierHistory(id);
/*    */   }
/*    */   
/*    */   public SupplierHistory updateSupplierHistory(SupplierHistory function) {
/* 37 */     return this.dao.updateSupplierHistory(function);
/*    */   }
/*    */   
/*    */   public SupplierHistory insertSupplierHistory(SupplierHistory function) {
/* 41 */     return this.dao.insertSupplierHistory(function);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSupplierHistoryListCount(Map conditions) {
/* 46 */     return this.dao.getSupplierHistoryListCount(conditions);
/*    */   }
/*    */   
/*    */   public List getSupplierHistoryList(Map conditions, int pageNo, int pageSize, SupplierHistoryQueryOrder order, boolean descend) {
/* 50 */     return this.dao.getSupplierHistoryList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SupplierHistoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */