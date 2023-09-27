/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.CustomerreturnsDao;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.model.basic.Customerreturns;
/*    */ import com.aof.model.basic.query.CustomerreturnsQueryCondition;
/*    */ import com.aof.model.basic.query.CustomerreturnsQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ public class CustomerreturnsDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements CustomerreturnsDao
/*    */ {
/* 20 */   private Log log = LogFactory.getLog(CustomerreturnsDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from Customerreturns entity";
/*    */   public Customerreturns save(Customerreturns entity) {
/* 22 */     getHibernateTemplate().save(entity);
/* 23 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from Customerreturns entity";
/*    */   public Customerreturns getCustomerreturns(Integer id) {
/* 27 */     if (id == null && 
/* 28 */       this.log.isDebugEnabled()) {
/* 29 */       this.log.debug("get Customerreturns with null id!");
/* 30 */       return null;
/*    */     } 
/*    */     
/* 33 */     return (Customerreturns)getHibernateTemplate().get(Customerreturns.class, id);
/*    */   }
/*    */   
/*    */   public void delete(Customerreturns entity) {
/* 37 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public Customerreturns update(Customerreturns entity) {
/* 41 */     if (entity.getId() == null) {
/* 42 */       throw new RuntimeException("update Customerreturns with null id!");
/*    */     }
/* 44 */     Customerreturns oldEntity = getCustomerreturns(entity.getId());
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
/*    */ 
/*    */ 
/*    */   
/* 62 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 63 */       { CustomerreturnsQueryCondition.ID_EQ, "entity.id = ?"
/* 64 */       }, { CustomerreturnsQueryCondition.RETURNNUMBER_EQ, "entity.returnNumber = ?"
/* 65 */       }, { CustomerreturnsQueryCondition.DELSTATUS_EQ, "entity.delStatus = ?"
/* 66 */       }, { CustomerreturnsQueryCondition.CUSTOMERCODE_EQ, "entity.basicCustomer = ?"
/* 67 */       }, { CustomerreturnsQueryCondition.CUSTOMER_SITE_ID_EQ, "entity.basicCustomer.siteId.id = ?"
/* 68 */       }, { CustomerreturnsQueryCondition.RETURNNUMBER_LIKE, "entity.returnNumber like ?", new LikeConvert() }
/*    */     };
/*    */   
/* 71 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 72 */       { CustomerreturnsQueryOrder.ID, "entity.id"
/* 73 */       }, { CustomerreturnsQueryOrder.RETURNNUMBER, "entity.returnNumber = ?"
/* 74 */       }, { CustomerreturnsQueryOrder.DELSTATUS, "entity.delStatus = ?"
/* 75 */       }, { CustomerreturnsQueryOrder.CUSTOMERCODE, "entity.basicCustomer = ?" }
/*    */     };
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 79 */     return Integer.valueOf(getListCount(conditions, "select count(*) from Customerreturns entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Customerreturns> getList(Map conditions, int pageNum, int pageSize, CustomerreturnsQueryOrder order, boolean descend) {
/* 84 */     return getList(conditions, pageNum, pageSize, order, descend, "from Customerreturns entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/CustomerreturnsDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */