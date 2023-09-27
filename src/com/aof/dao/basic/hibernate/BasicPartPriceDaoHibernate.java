/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.BasicPartPriceDao;
/*    */ import com.aof.model.basic.BasicPartPrice;
/*    */ import com.aof.model.basic.query.BasicPartPriceQueryCondition;
/*    */ import com.aof.model.basic.query.BasicPartPriceQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicPartPriceDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements BasicPartPriceDao
/*    */ {
/* 20 */   private Log log = LogFactory.getLog(BasicPartPriceDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from BasicPartPrice partPrice";
/*    */   
/*    */   public BasicPartPrice save(BasicPartPrice basicPartPrice) {
/* 23 */     getHibernateTemplate().save(basicPartPrice);
/* 24 */     return basicPartPrice;
/*    */   }
/*    */   private static final String SQL = "from BasicPartPrice partPrice";
/*    */   public BasicPartPrice getBasicPartPrice(Integer id) {
/* 28 */     if (id == null && 
/* 29 */       this.log.isDebugEnabled()) {
/* 30 */       this.log.debug("get BasicPartPrice with null id!");
/* 31 */       return null;
/*    */     } 
/*    */     
/* 34 */     return (BasicPartPrice)getHibernateTemplate().get(BasicPartPrice.class, id);
/*    */   }
/*    */   
/*    */   public void delete(BasicPartPrice entity) {
/* 38 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public BasicPartPrice update(BasicPartPrice entity) {
/* 42 */     if (entity.getId() == null) {
/* 43 */       throw new RuntimeException("update BasicPartPrice with null id!");
/*    */     }
/* 45 */     BasicPartPrice oldEntity = getBasicPartPrice(entity.getId());
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
/*    */ 
/*    */ 
/*    */   
/* 63 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 64 */       { BasicPartPriceQueryCondition.ID_EQ, "partPrice.id = ?"
/* 65 */       }, { BasicPartPriceQueryCondition.CUSTOMER_EQ, "partPrice.customer = ?"
/* 66 */       }, { BasicPartPriceQueryCondition.PARTID_EQ, "partPrice.partId = ?"
/* 67 */       }, { BasicPartPriceQueryCondition.STARTDATE_LE, "partPrice.startDate <= ?"
/* 68 */       }, { BasicPartPriceQueryCondition.STARTDATE_GE, "partPrice.startDate >= ?"
/* 69 */       }, { BasicPartPriceQueryCondition.EXPIREDATE_LE, "partPrice.expireDate <= ?"
/* 70 */       }, { BasicPartPriceQueryCondition.EXPIREDATE_GE, "partPrice.expireDate >= ?" }
/*    */     };
/*    */   
/* 73 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 74 */       { BasicPartPriceQueryOrder.ID, "partPrice.id" }
/*    */     };
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 78 */     return Integer.valueOf(getListCount(conditions, "select count(*) from BasicPartPrice partPrice", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BasicPartPrice> getList(Map conditions, int pageNum, int pageSize, BasicPartPriceQueryOrder order, boolean descend) {
/* 83 */     return getList(conditions, pageNum, pageSize, order, descend, "from BasicPartPrice partPrice", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/BasicPartPriceDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */