/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.BasicCustomerDao;
/*    */ import com.aof.model.product.BasicCustomer;
/*    */ import com.aof.model.product.query.BasicCustomerQueryCondition;
/*    */ import com.aof.model.product.query.BasicCustomerQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class BasicCustomerDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements BasicCustomerDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(BasicCustomerDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from BasicCustomer basicCustomer";
/*    */   
/*    */   private static final String SQL = "from BasicCustomer basicCustomer";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 25 */       { BasicCustomerQueryCondition.ID_EQ, "basicCustomer.id = ?"
/* 26 */       }, { BasicCustomerQueryCondition.ENABLED_EQ, "basicCustomer.enabled = ?"
/* 27 */       }, { BasicCustomerQueryCondition.CODE_EQ, "basicCustomer.code = ?"
/* 28 */       }, { BasicCustomerQueryCondition.EDITID_NE, "basicCustomer.id != ?"
/* 29 */       }, { BasicCustomerQueryCondition.SITEID_ID_EQ, "basicCustomer.siteId.id = ?" }
/*    */     };
/* 31 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { BasicCustomerQueryOrder.ID, "basicCustomer.id" } };
/*    */   
/*    */   public BasicCustomer getById(Integer id) {
/* 34 */     if (id == null) {
/* 35 */       if (this.log.isDebugEnabled()) this.log.debug("try to get BasicCustomer with null id"); 
/* 36 */       return null;
/*    */     } 
/* 38 */     return (BasicCustomer)getHibernateTemplate().get(BasicCustomer.class, id);
/*    */   }
/*    */   
/*    */   public BasicCustomer insert(BasicCustomer basicCustomer) {
/* 42 */     getHibernateTemplate().save(basicCustomer);
/* 43 */     return basicCustomer;
/*    */   }
/*    */   
/*    */   public void remove(BasicCustomer basicCustomer) {
/* 47 */     getHibernateTemplate().delete(basicCustomer);
/*    */   }
/*    */   
/*    */   public BasicCustomer update(BasicCustomer basicCustomer) {
/* 51 */     if (basicCustomer.getId() == null) throw new RuntimeException("update BasicCustomer with null id!"); 
/* 52 */     BasicCustomer oldEntity = getById(basicCustomer.getId());
/* 53 */     if (oldEntity != null) {
/*    */       try {
/* 55 */         PropertyUtils.copyProperties(oldEntity, basicCustomer);
/* 56 */       } catch (Exception e) {
/* 57 */         throw new RuntimeException("copy error with BasicCustomer" + e);
/*    */       } 
/* 59 */       getHibernateTemplate().update(oldEntity);
/* 60 */       return oldEntity;
/*    */     } 
/* 62 */     throw new RuntimeException("BasicCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 68 */     return getListCount(conditions, "select count(*) from BasicCustomer basicCustomer", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, BasicCustomerQueryOrder order, boolean descend) {
/* 73 */     return getList(conditions, pageNo, pageSize, order, descend, "from BasicCustomer basicCustomer", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/BasicCustomerDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */