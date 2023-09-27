/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.CustomerDAO;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.model.admin.Customer;
/*    */ import com.aof.model.admin.query.CustomerQueryCondition;
/*    */ import com.aof.model.admin.query.CustomerQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomerDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements CustomerDAO
/*    */ {
/* 30 */   private Log log = LogFactory.getLog(CustomerDAOHibernate.class);
/*    */   
/*    */   public Customer getCustomer(String code) {
/* 33 */     if (code == null) {
/* 34 */       if (this.log.isDebugEnabled())
/* 35 */         this.log.debug("try to get Customer with null code"); 
/* 36 */       return null;
/*    */     } 
/* 38 */     return (Customer)getHibernateTemplate().get(Customer.class, code);
/*    */   }
/*    */ 
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from Customer c";
/*    */   
/*    */   private static final String SQL = "from Customer c";
/* 45 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 46 */       { CustomerQueryCondition.CODE_EQ, "c.code = ?"
/* 47 */       }, { CustomerQueryCondition.CODE_LIKE, "c.code like ?", new LikeConvert()
/* 48 */       }, { CustomerQueryCondition.DESCRIPTION_LIKE, "c.description like ?", new LikeConvert()
/* 49 */       }, { CustomerQueryCondition.SITE_ID_EQ, "c.site.id = ?"
/* 50 */       }, { CustomerQueryCondition.TYPE_EQ, "c.type = ?"
/* 51 */       }, { CustomerQueryCondition.ENABLED_EQ, "c.enabled = ?" }
/*    */     };
/*    */   
/* 54 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 55 */       { CustomerQueryOrder.CODE, "c.code"
/* 56 */       }, { CustomerQueryOrder.DESCRIPTION, "c.description" } };
/*    */   
/*    */   public int getCustomerListCount(Map conditions) {
/* 59 */     return getListCount(conditions, "select count(*) from Customer c", QUERY_CONDITIONS);
/*    */   }
/*    */   
/*    */   public List getCustomerList(Map conditions, int pageNo, int pageSize, CustomerQueryOrder order, boolean descend) {
/* 63 */     return getList(conditions, pageNo, pageSize, order, descend, "from Customer c", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/CustomerDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */