/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.dao.product.ProductOutGolineDAO;
/*    */ import com.aof.model.sync.query.ProductOutGolineQueryCondition;
/*    */ import com.aof.model.sync.query.ProductOutGolineQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductOutGolineDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ProductOutGolineDAO
/*    */ {
/* 20 */   private Log log = LogFactory.getLog(ProductOutGolineDAOHibernate.class);
/*    */   private static final String SQL = "from ProductOutGoline pg";
/*    */   private static final String SQL_COUNT = "select count(*) from ProductOutGoline pg";
/* 23 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 24 */       { ProductOutGolineQueryCondition.ID_EQ, "pg.xxsh_worc_number = ?"
/* 25 */       }, { ProductOutGolineQueryCondition.XXSH_WORC_STATUS_EQ, "pg.xxsh_worc_status = ?"
/* 26 */       }, { ProductOutGolineQueryCondition.XXSH_WORC_ITEM_EQ, "pg.xxsh_worc_item = ?"
/* 27 */       }, { ProductOutGolineQueryCondition.XXSH_WORC_ITEM_LIKE, "pg.xxsh_worc_item like ?", new LikeConvert()
/* 28 */       }, { ProductOutGolineQueryCondition.DATE_GT, "pg.xxsh_worc_cr_date >= ?"
/* 29 */       }, { ProductOutGolineQueryCondition.DATE_LT, "pg.xxsh_worc_cr_date <= ?" }
/*    */     };
/*    */ 
/*    */   
/* 33 */   private static final Object[][] SQL_ORDER = new Object[][] {
/* 34 */       { ProductOutGolineQueryOrder.ID, "pg.xxsh_worc_number"
/* 35 */       }, { ProductOutGolineQueryOrder.ITEM, "pg.xxsh_worc_item" }
/*    */     };
/*    */   
/*    */   public int getProductOutGolineListCount(Map conditions) {
/* 39 */     return getListCount(conditions, "select count(*) from ProductOutGoline pg", QUERY_CONDITIONS);
/*    */   }
/*    */   
/*    */   public List getProductOutGolineList(Map conditions, int pageNo, int pageSize, ProductOutGolineQueryOrder order, boolean descend) {
/* 43 */     return getList(conditions, pageNo, pageSize, order, descend, "from ProductOutGoline pg", QUERY_CONDITIONS, SQL_ORDER);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/ProductOutGolineDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */