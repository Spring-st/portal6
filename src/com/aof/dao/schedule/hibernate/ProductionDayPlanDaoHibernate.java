/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.ProductionDayPlanDao;
/*    */ import com.aof.model.schedule.ProductionDayPlan;
/*    */ import com.aof.model.schedule.query.ProductionDayPlanQueryCondition;
/*    */ import com.aof.model.schedule.query.ProductionDayPlanQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ProductionDayPlanDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductionDayPlanDao {
/* 17 */   private Log log = LogFactory.getLog(ProductionDayPlanDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from ProductionDayPlan entity";
/*    */   public ProductionDayPlan save(ProductionDayPlan entity) {
/* 19 */     getHibernateTemplate().save(entity);
/* 20 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from ProductionDayPlan entity";
/*    */   
/*    */   public ProductionDayPlan getProductionDayPlan(Integer id) {
/* 25 */     if (id == null && 
/* 26 */       this.log.isDebugEnabled()) {
/* 27 */       this.log.debug("get ProductionDayPlan with null id!");
/* 28 */       return null;
/*    */     } 
/*    */     
/* 31 */     return (ProductionDayPlan)getHibernateTemplate().get(ProductionDayPlan.class, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ProductionDayPlan entity) {
/* 36 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ProductionDayPlan update(ProductionDayPlan entity) {
/* 41 */     if (entity.getId() == null) {
/* 42 */       throw new RuntimeException("update ProductionDayPlan with null id!");
/*    */     }
/* 44 */     ProductionDayPlan oldEntity = getProductionDayPlan(entity.getId());
/* 45 */     if (oldEntity != null) {
/*    */       try {
/* 47 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 48 */       } catch (Exception e) {
/* 49 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 51 */       getHibernateTemplate().update(oldEntity);
/* 52 */       return oldEntity;
/*    */     } 
/* 54 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 59 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 60 */       { ProductionDayPlanQueryCondition.ASNNO_EQ, "entity.asnNo = ?" }
/*    */     };
/* 62 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 67 */     return Integer.valueOf(getListCount(conditions, "select count(*) from ProductionDayPlan entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ProductionDayPlan> getList(Map conditions, int pageNum, int pageSize, ProductionDayPlanQueryOrder order, boolean descend) {
/* 73 */     return getList(conditions, pageNum, pageSize, order, descend, "from ProductionDayPlan entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/ProductionDayPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */