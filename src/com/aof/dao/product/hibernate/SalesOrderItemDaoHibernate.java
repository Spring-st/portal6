/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.SalesOrderItemDao;
/*    */ import com.aof.model.product.SalesOrderItem;
/*    */ import com.aof.model.product.query.SalesOrderItemQueryCondition;
/*    */ import com.aof.model.product.query.SalesOrderItemQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class SalesOrderItemDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements SalesOrderItemDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(SalesOrderItemDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from SalesOrderItem salesOrderItem";
/*    */   
/*    */   private static final String SQL = "from SalesOrderItem salesOrderItem";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 25 */       { SalesOrderItemQueryCondition.ID_EQ, "salesOrderItem.id = ?"
/* 26 */       }, { SalesOrderItemQueryCondition.QTYOPEN_DT, "salesOrderItem.qtyopen > ?"
/* 27 */       }, { SalesOrderItemQueryCondition.STATUS_OPEN_EQ, "salesOrderItem.status = ? or salesOrderItem.status is null"
/* 28 */       }, { SalesOrderItemQueryCondition.STATUS_EQ, "salesOrderItem.status = ?" }
/*    */     };
/* 30 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { SalesOrderItemQueryOrder.ID, "salesOrderItem.id" } };
/*    */   
/*    */   public SalesOrderItem getById(Integer id) {
/* 33 */     if (id == null) {
/* 34 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesOrderItem with null id"); 
/* 35 */       return null;
/*    */     } 
/* 37 */     return (SalesOrderItem)getHibernateTemplate().get(SalesOrderItem.class, id);
/*    */   }
/*    */   
/*    */   public SalesOrderItem insert(SalesOrderItem salesOrderItem) {
/* 41 */     getHibernateTemplate().save(salesOrderItem);
/* 42 */     return salesOrderItem;
/*    */   }
/*    */   
/*    */   public void remove(SalesOrderItem salesOrderItem) {
/* 46 */     getHibernateTemplate().delete(salesOrderItem);
/*    */   }
/*    */   
/*    */   public SalesOrderItem update(SalesOrderItem salesOrderItem) {
/* 50 */     if (salesOrderItem.getId() == null) throw new RuntimeException("update SalesOrderItem with null id!"); 
/* 51 */     SalesOrderItem oldEntity = getById(salesOrderItem.getId());
/* 52 */     if (oldEntity != null) {
/*    */       try {
/* 54 */         PropertyUtils.copyProperties(oldEntity, salesOrderItem);
/* 55 */       } catch (Exception e) {
/* 56 */         throw new RuntimeException("copy error with SalesOrderItem" + e);
/*    */       } 
/* 58 */       getHibernateTemplate().update(oldEntity);
/* 59 */       return oldEntity;
/*    */     } 
/* 61 */     throw new RuntimeException("SalesOrderItem not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 67 */     return getListCount(conditions, "select count(*) from SalesOrderItem salesOrderItem", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesOrderItemQueryOrder order, boolean descend) {
/* 72 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesOrderItem salesOrderItem", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesOrderItemDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */