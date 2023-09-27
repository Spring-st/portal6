/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.CustomerReturnItemDAO;
/*    */ import com.aof.model.basic.CustomerReturnItem;
/*    */ import com.aof.model.basic.query.CustomerReturnItemQueryCondition;
/*    */ import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class CustomerReturnItemDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements CustomerReturnItemDAO {
/* 17 */   private Log log = LogFactory.getLog(CustomerReturnItemDAOHibernate.class); private static final String SQL_COUNT = "select count(*) from CustomerReturnItem entity";
/*    */   
/*    */   public CustomerReturnItem save(CustomerReturnItem entity) {
/* 20 */     getHibernateTemplate().save(entity);
/* 21 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from CustomerReturnItem entity";
/*    */   
/*    */   public CustomerReturnItem getCustomerreturnsItem(Integer id) {
/* 26 */     if (id == null && 
/* 27 */       this.log.isDebugEnabled()) {
/* 28 */       this.log.debug("get CustomerReturnItem with null id!");
/* 29 */       return null;
/*    */     } 
/*    */     
/* 32 */     return (CustomerReturnItem)getHibernateTemplate().get(CustomerReturnItem.class, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(CustomerReturnItem entity) {
/* 37 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public CustomerReturnItem update(CustomerReturnItem entity) {
/* 42 */     if (entity.getId() == null) {
/* 43 */       throw new RuntimeException("update CustomerReturnItem with null id!");
/*    */     }
/* 45 */     CustomerReturnItem oldEntity = getCustomerreturnsItem(entity.getId());
/* 46 */     if (oldEntity == null) {
/*    */       try {
/* 48 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 49 */       } catch (Exception e) {
/*    */         
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
/*    */ 
/*    */   
/* 64 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 65 */       { CustomerReturnItemQueryCondition.ID_EQ, "entity.id = ?"
/* 66 */       }, { CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, "entity.customerreturns.id = ?" }
/*    */     };
/*    */   
/* 69 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 70 */       { CustomerReturnItemQueryOrder.ID, "entity.id" } };
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 73 */     return Integer.valueOf(getListCount(conditions, "select count(*) from CustomerReturnItem entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNum, int pageSize, CustomerReturnItemQueryOrder order, boolean descend) {
/* 78 */     return getList(conditions, pageNum, pageSize, order, descend, "from CustomerReturnItem entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/CustomerReturnItemDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */