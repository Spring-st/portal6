/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.CustomerPlanDao;
/*    */ import com.aof.model.product.CustomerPlan;
/*    */ import com.aof.model.product.query.CustomerPlanQueryCondition;
/*    */ import com.aof.model.product.query.CustomerPlanQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class CustomerPlanDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements CustomerPlanDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(CustomerPlanDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from CustomerPlan customerPlan";
/*    */   
/*    */   private static final String SQL = "from CustomerPlan customerPlan";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 25 */       { CustomerPlanQueryCondition.ID_EQ, "customerPlan.id = ?"
/* 26 */       }, { CustomerPlanQueryCondition.QTYOPEN_DT, "customerPlan.qtyOpen > ?"
/* 27 */       }, { CustomerPlanQueryCondition.STATUS_EQ, "customerPlan.status = ?"
/* 28 */       }, { CustomerPlanQueryCondition.STATUS_OPEN_EQ, "customerPlan.status = ? or customerPlan.status is null" }
/*    */     };
/*    */   
/* 31 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { CustomerPlanQueryOrder.ID, "customerPlan.id" } };
/*    */   
/*    */   public CustomerPlan getById(Integer id) {
/* 34 */     if (id == null) {
/* 35 */       if (this.log.isDebugEnabled()) this.log.debug("try to get CustomerPlan with null id"); 
/* 36 */       return null;
/*    */     } 
/* 38 */     return (CustomerPlan)getHibernateTemplate().get(CustomerPlan.class, id);
/*    */   }
/*    */   
/*    */   public CustomerPlan insert(CustomerPlan customerPlan) {
/* 42 */     getHibernateTemplate().save(customerPlan);
/* 43 */     return customerPlan;
/*    */   }
/*    */   
/*    */   public void remove(CustomerPlan customerPlan) {
/* 47 */     getHibernateTemplate().delete(customerPlan);
/*    */   }
/*    */   
/*    */   public CustomerPlan update(CustomerPlan customerPlan) {
/* 51 */     if (customerPlan.getId() == null) throw new RuntimeException("update CustomerPlan with null id!"); 
/* 52 */     CustomerPlan oldEntity = getById(customerPlan.getId());
/* 53 */     if (oldEntity != null) {
/*    */       try {
/* 55 */         PropertyUtils.copyProperties(oldEntity, customerPlan);
/* 56 */       } catch (Exception e) {
/* 57 */         throw new RuntimeException("copy error with CustomerPlan" + e);
/*    */       } 
/* 59 */       getHibernateTemplate().update(oldEntity);
/* 60 */       return oldEntity;
/*    */     } 
/* 62 */     throw new RuntimeException("CustomerPlan not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 68 */     return getListCount(conditions, "select count(*) from CustomerPlan customerPlan", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, CustomerPlanQueryOrder order, boolean descend) {
/* 73 */     return getList(conditions, pageNo, pageSize, order, descend, "from CustomerPlan customerPlan", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/CustomerPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */