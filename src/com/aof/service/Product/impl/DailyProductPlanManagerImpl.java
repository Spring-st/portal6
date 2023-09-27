/*    */ package com.aof.service.Product.impl;
/*    */ 
/*    */ import com.aof.dao.product.DailyProductPlanDao;
/*    */ import com.aof.model.product.DailyProductPlan;
/*    */ import com.aof.model.product.query.DailyProductPlanQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.Product.DailyProductPlanManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DailyProductPlanManagerImpl
/*    */   extends BaseManager
/*    */   implements DailyProductPlanManager {
/*    */   private DailyProductPlanDao dao;
/*    */   
/*    */   public DailyProductPlanDao getDao() {
/* 17 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(DailyProductPlanDao dao) {
/* 21 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public DailyProductPlan getById(Integer id) {
/* 25 */     return this.dao.getById(id);
/*    */   }
/*    */   
/*    */   public DailyProductPlan insert(DailyProductPlan dailyProductPlan) {
/* 29 */     return this.dao.insert(dailyProductPlan);
/*    */   }
/*    */   
/*    */   public void remove(DailyProductPlan dailyProductPlan) {
/* 33 */     this.dao.remove(dailyProductPlan);
/*    */   }
/*    */   
/*    */   public DailyProductPlan update(DailyProductPlan dailyProductPlan) {
/* 37 */     return this.dao.update(dailyProductPlan);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 42 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, DailyProductPlanQueryOrder order, boolean descend) {
/* 47 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/DailyProductPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */