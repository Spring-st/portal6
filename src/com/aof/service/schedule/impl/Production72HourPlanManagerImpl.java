/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.Production72HourPlanDao;
/*    */ import com.aof.model.schedule.Production72HourPlan;
/*    */ import com.aof.model.schedule.query.Production72HourPlanQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.Production72HourPlanManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Production72HourPlanManagerImpl
/*    */   extends BaseManager implements Production72HourPlanManager {
/*    */   private Production72HourPlanDao hourPlanDao;
/*    */   
/*    */   public Production72HourPlanDao getHourPlanDao() {
/* 16 */     return this.hourPlanDao;
/*    */   }
/*    */   
/*    */   public void setHourPlanDao(Production72HourPlanDao hourPlanDao) {
/* 20 */     this.hourPlanDao = hourPlanDao;
/*    */   }
/*    */ 
/*    */   
/*    */   public Production72HourPlan save(Production72HourPlan entity) {
/* 25 */     return this.hourPlanDao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Production72HourPlan getProduction72HourPlan(Integer id) {
/* 30 */     return this.hourPlanDao.getProduction72HourPlan(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(Production72HourPlan entity) {
/* 35 */     this.hourPlanDao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Production72HourPlan update(Production72HourPlan entity) {
/* 40 */     return this.hourPlanDao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 45 */     return this.hourPlanDao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Production72HourPlan> getList(Map conditions, int pageNum, int pageSize, Production72HourPlanQueryOrder order, boolean descend) {
/* 51 */     return this.hourPlanDao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/Production72HourPlanManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */