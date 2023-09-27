/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.ProjectedInventoryDao;
/*    */ import com.aof.model.schedule.ProjectedInventory;
/*    */ import com.aof.model.schedule.query.ProjectedInventoryQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.ProjectedInventoryManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProjectedInventoryManagerImpl
/*    */   extends BaseManager
/*    */   implements ProjectedInventoryManager
/*    */ {
/*    */   private ProjectedInventoryDao dao;
/*    */   
/*    */   public ProjectedInventory save(ProjectedInventory entity) {
/* 20 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectedInventory getProjectedInventory(Integer id) {
/* 25 */     return this.dao.getProjectedInventory(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ProjectedInventory entity) {
/* 30 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectedInventory update(ProjectedInventory entity) {
/* 35 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 40 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ProjectedInventory> getList(Map conditions, int pageNum, int pageSize, ProjectedInventoryQueryOrder order, boolean descend) {
/* 46 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(ProjectedInventoryDao dao) {
/* 50 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public ProjectedInventoryDao getDao() {
/* 54 */     return this.dao;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ProjectedInventory> getJitShipPartNumberList(Map conditions, int pageNo, int pageSize, ProjectedInventoryQueryOrder order, boolean descend) {
/* 60 */     return this.dao.getJitShipPartNumberList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/ProjectedInventoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */