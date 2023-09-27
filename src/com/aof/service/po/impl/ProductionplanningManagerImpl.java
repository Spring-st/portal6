/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.ProductionplanningDao;
/*    */ import com.aof.model.po.Productionplanning;
/*    */ import com.aof.model.po.query.ProductionplanningQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.ProductionplanningManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductionplanningManagerImpl
/*    */   extends BaseManager
/*    */   implements ProductionplanningManager
/*    */ {
/*    */   private ProductionplanningDao productionPlanningDao;
/*    */   
/*    */   public ProductionplanningDao getProductionPlanningDao() {
/* 18 */     return this.productionPlanningDao;
/*    */   }
/*    */   
/*    */   public void setProductionPlanningDao(ProductionplanningDao productionPlanningDao) {
/* 22 */     this.productionPlanningDao = productionPlanningDao;
/*    */   }
/*    */   
/*    */   public Productionplanning getProductionplanning(Integer id) {
/* 26 */     return this.productionPlanningDao.getProductionplanning(id);
/*    */   }
/*    */   
/*    */   public List list(Map condtions, int pageNo, int pageSize, ProductionplanningQueryOrder order, boolean descend) {
/* 30 */     return this.productionPlanningDao.list(condtions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   public int listProductionplanningCount(Map conditions) {
/* 33 */     return this.productionPlanningDao.listProductionplanningCount(conditions);
/*    */   }
/*    */   public void update(Productionplanning u) {
/* 36 */     this.productionPlanningDao.update(u);
/*    */   }
/*    */   
/*    */   public void delete(Productionplanning u) {
/* 40 */     this.productionPlanningDao.delete(u);
/*    */   }
/*    */   
/*    */   public Productionplanning insert(Productionplanning u) {
/* 44 */     return this.productionPlanningDao.insert(u);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/ProductionplanningManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */