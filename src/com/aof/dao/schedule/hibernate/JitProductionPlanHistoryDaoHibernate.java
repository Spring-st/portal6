/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.JitProductionPlanHistoryDao;
/*    */ import com.aof.model.schedule.JitProductionPlanHistory;
/*    */ import com.aof.model.schedule.query.JitProductionPlanHistoryQueryCondition;
/*    */ import com.aof.model.schedule.query.JitProductionPlanHistoryQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class JitProductionPlanHistoryDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements JitProductionPlanHistoryDao {
/* 17 */   private Log log = LogFactory.getLog(JitProductionPlanHistoryDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from JitProductionPlanHistory entity";
/*    */   
/*    */   public JitProductionPlanHistory save(JitProductionPlanHistory entity) {
/* 20 */     getHibernateTemplate().save(entity);
/* 21 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from JitProductionPlanHistory entity";
/*    */   public JitProductionPlanHistory getJitProductionPlanHistory(Integer id) {
/* 25 */     if (id == null && 
/* 26 */       this.log.isDebugEnabled()) {
/* 27 */       this.log.debug("get ProjectedInventory with null id!");
/* 28 */       return null;
/*    */     } 
/*    */     
/* 31 */     return (JitProductionPlanHistory)getHibernateTemplate().get(JitProductionPlanHistory.class, id);
/*    */   }
/*    */   
/*    */   public void delete(JitProductionPlanHistory entity) {
/* 35 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public JitProductionPlanHistory update(JitProductionPlanHistory entity) {
/* 40 */     if (entity.getId() == null) {
/* 41 */       throw new RuntimeException("update JitProductionPlanHistory with null id!");
/*    */     }
/* 43 */     JitProductionPlanHistory oldEntity = getJitProductionPlanHistory(entity.getId());
/* 44 */     if (oldEntity != null) {
/*    */       try {
/* 46 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 47 */       } catch (Exception e) {
/* 48 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 50 */       getHibernateTemplate().update(oldEntity);
/* 51 */       return oldEntity;
/*    */     } 
/* 53 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 61 */       { JitProductionPlanHistoryQueryCondition.id_EQ, "entity.id = ?" }
/*    */     };
/*    */   
/* 64 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 70 */     return Integer.valueOf(getListCount(conditions, "select count(*) from JitProductionPlanHistory entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<JitProductionPlanHistory> getList(Map conditions, int pageNum, int pageSize, JitProductionPlanHistoryQueryOrder order, boolean descend) {
/* 76 */     return getList(conditions, pageNum, pageSize, order, descend, "from JitProductionPlanHistory entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/JitProductionPlanHistoryDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */