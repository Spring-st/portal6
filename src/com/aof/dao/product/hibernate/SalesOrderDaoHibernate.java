/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.SalesOrderDao;
/*    */ import com.aof.model.product.SalesOrder;
/*    */ import com.aof.model.product.query.SalesOrderQueryCondition;
/*    */ import com.aof.model.product.query.SalesOrderQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class SalesOrderDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements SalesOrderDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(SalesOrderDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from SalesOrder salesOrder";
/*    */   
/*    */   private static final String SQL = "from SalesOrder salesOrder";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { SalesOrderQueryCondition.ID_EQ, "salesOrder.id = ?" } };
/* 25 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { SalesOrderQueryOrder.ID, "salesOrder.id" } };
/*    */   
/*    */   public SalesOrder getById(Integer id) {
/* 28 */     if (id == null) {
/* 29 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesOrder with null id"); 
/* 30 */       return null;
/*    */     } 
/* 32 */     return (SalesOrder)getHibernateTemplate().get(SalesOrder.class, id);
/*    */   }
/*    */   
/*    */   public SalesOrder insert(SalesOrder salesOrder) {
/* 36 */     getHibernateTemplate().save(salesOrder);
/* 37 */     return salesOrder;
/*    */   }
/*    */   
/*    */   public void remove(SalesOrder salesOrder) {
/* 41 */     getHibernateTemplate().delete(salesOrder);
/*    */   }
/*    */   
/*    */   public SalesOrder update(SalesOrder salesOrder) {
/* 45 */     if (salesOrder.getId() == null) throw new RuntimeException("update SalesOrder with null id!"); 
/* 46 */     SalesOrder oldEntity = getById(salesOrder.getId());
/* 47 */     if (oldEntity != null) {
/*    */       try {
/* 49 */         PropertyUtils.copyProperties(oldEntity, salesOrder);
/* 50 */       } catch (Exception e) {
/* 51 */         throw new RuntimeException("copy error with SalesOrder" + e);
/*    */       } 
/* 53 */       getHibernateTemplate().update(oldEntity);
/* 54 */       return oldEntity;
/*    */     } 
/* 56 */     throw new RuntimeException("SalesOrder not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 62 */     return getListCount(conditions, "select count(*) from SalesOrder salesOrder", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesOrderQueryOrder order, boolean descend) {
/* 67 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesOrder salesOrder", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesOrderDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */