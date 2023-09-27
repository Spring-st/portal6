/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.ProductGolineDAO;
/*    */ import com.aof.model.product.ProductGoline;
/*    */ import com.aof.model.product.query.ProductGolineQueryCondition;
/*    */ import com.aof.model.product.query.ProductGolineQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductGolineDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductGolineDAO
/*    */ {
/* 19 */   private Log log = LogFactory.getLog(ProductGolineDAOHibernate.class); private static final String SQL = "from ProductGoline pg";
/*    */   
/*    */   public ProductGoline getProductGoline(Integer id) {
/* 22 */     if (id == null && 
/* 23 */       this.log.isDebugEnabled()) {
/* 24 */       this.log.debug("get ProductGoline with null id!");
/*    */     }
/*    */     
/* 27 */     ProductGoline goline = (ProductGoline)getHibernateTemplate().get(ProductGoline.class, id);
/* 28 */     return goline;
/*    */   }
/*    */   private static final String SQL_COUNT = "select count(*) from ProductGoline pg"; private static final String SQL_COUNTAJAX = "select sum(pg.qty) from ProductGoline pg";
/*    */   public ProductGoline updateProductGoline(ProductGoline entity) {
/* 32 */     Integer id = entity.getId();
/* 33 */     if (id == null) {
/* 34 */       if (this.log.isDebugEnabled()) {
/* 35 */         this.log.debug("update ProductGoline with null id entity!");
/*    */       }
/*    */     } else {
/* 38 */       getHibernateTemplate().update(entity);
/*    */     } 
/* 40 */     return entity;
/*    */   }
/*    */   
/*    */   public ProductGoline insertProductGoline(ProductGoline entity) {
/* 44 */     getHibernateTemplate().save(entity);
/* 45 */     return entity;
/*    */   }
/*    */   
/*    */   public void deleteProductGoline(ProductGoline entity) {
/* 49 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   private static final Object[][] SQL_CONDITIONS = new Object[][] { { ProductGolineQueryCondition.ID_EQ, "pg.id = ?"
/* 57 */       }, { ProductGolineQueryCondition.TOTAL_NUMBER_EQ, "pg.totalNumber = ?"
/* 58 */       }, { ProductGolineQueryCondition.SH_CODE_EQ, "pg.shCode = ?"
/* 59 */       }, { ProductGolineQueryCondition.STATUS_EQ, "pg.status = ?"
/* 60 */       }, { ProductGolineQueryCondition.HNC_CODE_EQ, "pg.hncCode = ?"
/* 61 */       }, { ProductGolineQueryCondition.LOCATION_CODE_EQ, "pg.locationCode.id = ?"
/* 62 */       }, { ProductGolineQueryCondition.STATUS_NE, "pg.status <> ? and pg.status <> ?"
/* 63 */       }, { ProductGolineQueryCondition.STOREROOM_TYPE_NE, "pg.locationCode.basic_storeroom_id.type <> ?" } };
/*    */ 
/*    */ 
/*    */   
/* 67 */   private static final Object[][] SQL_ORDER = new Object[][] {
/* 68 */       { ProductGolineQueryOrder.ID, "pg.id"
/* 69 */       }, { ProductGolineQueryOrder.ID, "pg.hncCode" }
/*    */     };
/*    */   
/*    */   public int getProductGolineListCount(Map conditions) {
/* 73 */     return getListCount(conditions, "select count(*) from ProductGoline pg", SQL_CONDITIONS);
/*    */   }
/*    */   public int getProductGolineListCountAjax(Map conditions) {
/* 76 */     return getListCount(conditions, "select sum(pg.qty) from ProductGoline pg", SQL_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getProductGolineList(Map conditions, int pageNo, int pageSize, ProductGolineQueryOrder order, boolean descend) {
/* 81 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProductGoline pg", SQL_CONDITIONS, SQL_ORDER);
/*    */   }
/*    */   
/*    */   public List getTotalNumberList() {
/* 85 */     String sql = "select pg.totalNumber from ProductGoline pg group by pg.totalNumber";
/* 86 */     return getHibernateTemplate().find(sql);
/*    */   }
/*    */   
/*    */   public List getHncCodeList() {
/* 90 */     String sql = "select pg.hncCode from ProductGoline pg group by pg.hncCode";
/* 91 */     return getHibernateTemplate().find(sql);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductGolineDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */