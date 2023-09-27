/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.ProductOutStorageDAO;
/*    */ import com.aof.model.product.ProductOutStorage;
/*    */ import com.aof.model.product.query.ProductOutStorageQueryCondition;
/*    */ import com.aof.model.product.query.ProductOutStorageQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ProductOutStorageDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductOutStorageDAO
/*    */ {
/* 17 */   private Log log = LogFactory.getLog(ProductOutStorageDAOHibernate.class); private static final String SQL = "from ProductOutStorage pos";
/*    */   
/*    */   public ProductOutStorage getProductOutStorage(Integer id) {
/* 20 */     if (id == null && 
/* 21 */       this.log.isDebugEnabled()) {
/* 22 */       this.log.debug("get ProductOutStorage with null id!");
/*    */     }
/*    */     
/* 25 */     ProductOutStorage goline = (ProductOutStorage)getHibernateTemplate().get(ProductOutStorage.class, id);
/* 26 */     return goline;
/*    */   }
/*    */   private static final String SQL_COUNT = "select count(*) from ProductOutStorage pos";
/*    */   public ProductOutStorage updateProductOutStorage(ProductOutStorage entity) {
/* 30 */     Integer id = entity.getId();
/* 31 */     if (id == null) {
/* 32 */       if (this.log.isDebugEnabled()) {
/* 33 */         this.log.debug("update ProductOutStorage with null id entity!");
/*    */       }
/*    */     } else {
/* 36 */       getHibernateTemplate().update(entity);
/*    */     } 
/* 38 */     return entity;
/*    */   }
/*    */   
/*    */   public ProductOutStorage insertProductOutStorage(ProductOutStorage entity) {
/* 42 */     getHibernateTemplate().save(entity);
/* 43 */     return entity;
/*    */   }
/*    */   
/*    */   public void deleteProductOutStorage(ProductOutStorage entity) {
/* 47 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   private static final Object[][] SQL_CONDITIONS = new Object[][] {
/* 54 */       { ProductOutStorageQueryCondition.ID_EQ, "pos.id = ?"
/* 55 */       }, { ProductOutStorageQueryCondition.HNCCODE_EQ, "pos.hncCode = ?" }
/*    */     };
/*    */   
/* 58 */   private static final Object[][] SQL_ORDER = new Object[][] {
/* 59 */       { ProductOutStorageQueryOrder.ID, "pos.id" }
/*    */     };
/*    */   
/*    */   public int getProductOutStorageListCount(Map conditions) {
/* 63 */     return getListCount(conditions, "select count(*) from ProductOutStorage pos", SQL_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getProductOutStorageList(Map conditions, int pageNo, int pageSize, ProductOutStorageQueryOrder order, boolean descend) {
/* 68 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProductOutStorage pos", SQL_CONDITIONS, SQL_ORDER);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductOutStorageDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */