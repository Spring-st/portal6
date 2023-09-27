/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.WorkOrderBomDao;
/*    */ import com.aof.model.product.WorkOrderBom;
/*    */ import com.aof.model.product.query.WorkOrderBomQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.WorkOrderBomManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class WorkOrderBomManagerImpl
/*    */   extends BaseManager
/*    */   implements WorkOrderBomManager {
/*    */   private WorkOrderBomDao dao;
/*    */   
/*    */   public WorkOrderBomDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(WorkOrderBomDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public WorkOrderBom getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public WorkOrderBom insert(WorkOrderBom workOrderBom) {
/* 29 */     return this.dao.insert(workOrderBom);
/*    */   }
/*    */   
/*    */   public void remove(WorkOrderBom workOrderBom) {
/* 33 */     this.dao.remove(workOrderBom);
/*    */   }
/*    */   
/*    */   public WorkOrderBom update(WorkOrderBom workOrderBom) {
/* 37 */     return this.dao.update(workOrderBom);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, WorkOrderBomQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/WorkOrderBomManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */