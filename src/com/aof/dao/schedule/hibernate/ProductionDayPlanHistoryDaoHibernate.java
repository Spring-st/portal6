/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.ProductionDayPlanHistoryDao;
/*    */ import com.aof.model.schedule.ProductionDayPlanHistory;
/*    */ import com.aof.model.schedule.query.ProductionDayPlanHistoryQueryConditions;
/*    */ import com.aof.model.schedule.query.ProductionDayPlanHistoryQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductionDayPlanHistoryDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductionDayPlanHistoryDao
/*    */ {
/* 20 */   private Log log = LogFactory.getLog(ProductionDayPlanHistoryDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from ProductionDayPlanHistory entity";
/*    */   public ProductionDayPlanHistory save(ProductionDayPlanHistory entity) {
/* 22 */     getHibernateTemplate().save(entity);
/* 23 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from ProductionDayPlanHistory entity";
/*    */   public ProductionDayPlanHistory getProductionDayPlanHistory(Integer id) {
/* 27 */     if (id == null && 
/* 28 */       this.log.isDebugEnabled()) {
/* 29 */       this.log.debug("get ProductionDayPlanHistory with null id!");
/* 30 */       return null;
/*    */     } 
/*    */     
/* 33 */     return (ProductionDayPlanHistory)getHibernateTemplate().get(ProductionDayPlanHistory.class, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(ProductionDayPlanHistory entity) {
/* 38 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public ProductionDayPlanHistory update(ProductionDayPlanHistory entity) {
/* 42 */     if (entity.getId() == null) {
/* 43 */       throw new RuntimeException("update ProductionDayPlanHistory with null id!");
/*    */     }
/* 45 */     ProductionDayPlanHistory oldEntity = getProductionDayPlanHistory(entity.getId());
/* 46 */     if (oldEntity != null) {
/*    */       try {
/* 48 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 49 */       } catch (Exception e) {
/* 50 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 52 */       getHibernateTemplate().update(oldEntity);
/* 53 */       return oldEntity;
/*    */     } 
/* 55 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 60 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 61 */       { ProductionDayPlanHistoryQueryConditions.VERSION_EQ, "entity.version = ?" }
/*    */     };
/* 63 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 68 */     return Integer.valueOf(getListCount(conditions, "select count(*) from ProductionDayPlanHistory entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ProductionDayPlanHistory> getList(Map conditions, int pageNum, int pageSize, ProductionDayPlanHistoryQueryOrder order, boolean descend) {
/* 75 */     return getList(conditions, pageNum, pageSize, order, descend, "from ProductionDayPlanHistory entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/ProductionDayPlanHistoryDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */