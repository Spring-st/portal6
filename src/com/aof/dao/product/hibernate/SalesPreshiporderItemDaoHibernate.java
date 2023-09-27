/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.SalesPreshiporderItemDao;
/*    */ import com.aof.model.product.SalesPreshiporderItem;
/*    */ import com.aof.model.product.query.SalesPreshiporderItemQueryCondition;
/*    */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class SalesPreshiporderItemDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements SalesPreshiporderItemDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(SalesPreshiporderItemDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from SalesPreshiporderItem salesPreshiporderItem";
/*    */   
/*    */   private static final String SQL = "from SalesPreshiporderItem salesPreshiporderItem";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 25 */       { SalesPreshiporderItemQueryCondition.ID_EQ, "salesPreshiporderItem.id = ?"
/* 26 */       }, { SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, "salesPreshiporderItem.spsoId.id = ?"
/* 27 */       }, { SalesPreshiporderItemQueryCondition.SALESSHIPORDER_TYPE_EQ, "salesPreshiporderItem.spsoId.type = ?" }
/*    */     };
/* 29 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { SalesPreshiporderItemQueryOrder.ID, "salesPreshiporderItem.id" } };
/*    */   
/*    */   public SalesPreshiporderItem getById(Integer id) {
/* 32 */     if (id == null) {
/* 33 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesPreshiporderItem with null id"); 
/* 34 */       return null;
/*    */     } 
/* 36 */     return (SalesPreshiporderItem)getHibernateTemplate().get(SalesPreshiporderItem.class, id);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem insert(SalesPreshiporderItem salesPreshiporderItem) {
/* 40 */     getHibernateTemplate().save(salesPreshiporderItem);
/* 41 */     return salesPreshiporderItem;
/*    */   }
/*    */   
/*    */   public void remove(SalesPreshiporderItem salesPreshiporderItem) {
/* 45 */     getHibernateTemplate().delete(salesPreshiporderItem);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderItem update(SalesPreshiporderItem salesPreshiporderItem) {
/* 49 */     if (salesPreshiporderItem.getId() == null) throw new RuntimeException("update SalesPreshiporderItem with null id!"); 
/* 50 */     SalesPreshiporderItem oldEntity = getById(salesPreshiporderItem.getId());
/* 51 */     if (oldEntity != null) {
/*    */       try {
/* 53 */         PropertyUtils.copyProperties(oldEntity, salesPreshiporderItem);
/* 54 */       } catch (Exception e) {
/* 55 */         throw new RuntimeException("copy error with SalesPreshiporderItem" + e);
/*    */       } 
/* 57 */       getHibernateTemplate().update(oldEntity);
/* 58 */       return oldEntity;
/*    */     } 
/* 60 */     throw new RuntimeException("SalesPreshiporderItem not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 66 */     return getListCount(conditions, "select count(*) from SalesPreshiporderItem salesPreshiporderItem", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderItemQueryOrder order, boolean descend) {
/* 71 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesPreshiporderItem salesPreshiporderItem", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesPreshiporderItemDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */