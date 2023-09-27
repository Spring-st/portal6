/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.Production72HourPlanDao;
/*    */ import com.aof.model.schedule.Production72HourPlan;
/*    */ import com.aof.model.schedule.query.Production72HourPlanQueryConditions;
/*    */ import com.aof.model.schedule.query.Production72HourPlanQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class Production72HourPlanDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements Production72HourPlanDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(Production72HourPlanDaoHibernate.class); private static final String SQL_COUNT = " select count(*) from Production72HourPlan hour  ";
/*    */   
/*    */   public Production72HourPlan save(Production72HourPlan entity) {
/* 21 */     getHibernateTemplate().save(entity);
/* 22 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from Production72HourPlan hour  ";
/*    */   
/*    */   public Production72HourPlan getProduction72HourPlan(Integer id) {
/* 27 */     if (id == null && 
/* 28 */       this.log.isDebugEnabled()) {
/* 29 */       this.log.debug("get NjitNpoPlan with null id!");
/* 30 */       return null;
/*    */     } 
/*    */     
/* 33 */     return (Production72HourPlan)getHibernateTemplate().get(Production72HourPlan.class, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(Production72HourPlan entity) {
/* 38 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Production72HourPlan update(Production72HourPlan entity) {
/* 43 */     if (entity.getId() == null) {
/* 44 */       throw new RuntimeException("update NjitNpoPlan with null id!");
/*    */     }
/* 46 */     Production72HourPlan oldEntity = getProduction72HourPlan(entity.getId());
/* 47 */     if (oldEntity != null) {
/*    */       try {
/* 49 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 50 */       } catch (Exception e) {
/* 51 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 53 */       getHibernateTemplate().update(oldEntity);
/* 54 */       return oldEntity;
/*    */     } 
/* 56 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 63 */       { Production72HourPlanQueryConditions.ID_EQ, "entity.id = ?" }
/*    */     };
/*    */   
/* 66 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 67 */       { Production72HourPlanQueryOrder.ID, "entity.id" }
/*    */     };
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 72 */     return Integer.valueOf(getListCount(conditions, " select count(*) from Production72HourPlan hour  ", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Production72HourPlan> getList(Map conditions, int pageNum, int pageSize, Production72HourPlanQueryOrder order, boolean descend) {
/* 78 */     return getList(conditions, pageNum, pageSize, order, descend, "from Production72HourPlan hour  ", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/Production72HourPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */