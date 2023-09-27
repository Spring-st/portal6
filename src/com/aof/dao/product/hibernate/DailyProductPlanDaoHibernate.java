/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.DailyProductPlanDao;
/*    */ import com.aof.model.product.DailyProductPlan;
/*    */ import com.aof.model.product.query.DailyProductPlanQueryCondition;
/*    */ import com.aof.model.product.query.DailyProductPlanQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class DailyProductPlanDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements DailyProductPlanDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(DailyProductPlanDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from DailyProductPlan dailyProductPlan";
/*    */   
/*    */   private static final String SQL = "from DailyProductPlan dailyProductPlan";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { DailyProductPlanQueryCondition.ID_EQ, "dailyProductPlan.id = ?" } };
/* 25 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { DailyProductPlanQueryOrder.ID, "dailyProductPlan.id" } };
/*    */   
/*    */   public DailyProductPlan getById(Integer id) {
/* 28 */     if (id == null) {
/* 29 */       if (this.log.isDebugEnabled()) this.log.debug("try to get DailyProductPlan with null id"); 
/* 30 */       return null;
/*    */     } 
/* 32 */     return (DailyProductPlan)getHibernateTemplate().get(DailyProductPlan.class, id);
/*    */   }
/*    */   
/*    */   public DailyProductPlan insert(DailyProductPlan dailyProductPlan) {
/* 36 */     getHibernateTemplate().save(dailyProductPlan);
/* 37 */     return dailyProductPlan;
/*    */   }
/*    */   
/*    */   public void remove(DailyProductPlan dailyProductPlan) {
/* 41 */     getHibernateTemplate().delete(dailyProductPlan);
/*    */   }
/*    */   
/*    */   public DailyProductPlan update(DailyProductPlan dailyProductPlan) {
/* 45 */     if (dailyProductPlan.getId() == null) throw new RuntimeException("update DailyProductPlan with null id!"); 
/* 46 */     DailyProductPlan oldEntity = getById(dailyProductPlan.getId());
/* 47 */     if (oldEntity != null) {
/*    */       try {
/* 49 */         PropertyUtils.copyProperties(oldEntity, dailyProductPlan);
/* 50 */       } catch (Exception e) {
/* 51 */         throw new RuntimeException("copy error with DailyProductPlan" + e);
/*    */       } 
/* 53 */       getHibernateTemplate().update(oldEntity);
/* 54 */       return oldEntity;
/*    */     } 
/* 56 */     throw new RuntimeException("DailyProductPlan not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 62 */     return getListCount(conditions, "select count(*) from DailyProductPlan dailyProductPlan", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, DailyProductPlanQueryOrder order, boolean descend) {
/* 67 */     return getList(conditions, pageNo, pageSize, order, descend, "from DailyProductPlan dailyProductPlan", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/DailyProductPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */