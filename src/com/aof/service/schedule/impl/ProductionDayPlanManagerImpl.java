/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.ProductionDayPlanDao;
/*    */ import com.aof.model.schedule.ProductionDayPlan;
/*    */ import com.aof.model.schedule.query.ProductionDayPlanQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.ProductionDayPlanManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductionDayPlanManagerImpl
/*    */   extends BaseManager
/*    */   implements ProductionDayPlanManager {
/*    */   private ProductionDayPlanDao productionDayPlanDao;
/*    */   
/*    */   public ProductionDayPlanDao getProductionDayPlanDao() {
/* 17 */     return this.productionDayPlanDao;
/*    */   }
/*    */   
/*    */   public void setProductionDayPlanDao(ProductionDayPlanDao productionDayPlanDao) {
/* 21 */     this.productionDayPlanDao = productionDayPlanDao;
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductionDayPlan save(ProductionDayPlan entity) {
/* 26 */     return this.productionDayPlanDao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductionDayPlan getProductionDayPlan(Integer id) {
/* 31 */     return this.productionDayPlanDao.getProductionDayPlan(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ProductionDayPlan entity) {
/* 36 */     this.productionDayPlanDao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductionDayPlan update(ProductionDayPlan entity) {
/* 41 */     return this.productionDayPlanDao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 46 */     return this.productionDayPlanDao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ProductionDayPlan> getList(Map conditions, int pageNum, int pageSize, ProductionDayPlanQueryOrder order, boolean descend) {
/* 52 */     return this.productionDayPlanDao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/ProductionDayPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */