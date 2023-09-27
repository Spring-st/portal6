/*    */ package com.aof.dao.po.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.dao.po.ProductionplanningDao;
/*    */ import com.aof.model.po.Productionplanning;
/*    */ import com.aof.model.po.query.ProductionplanningQueryCondition;
/*    */ import com.aof.model.po.query.ProductionplanningQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ProductionplanningDAOHibernate
/*    */   extends BaseDAOHibernate implements ProductionplanningDao {
/* 16 */   private Log log = LogFactory.getLog(ProductionplanningDAOHibernate.class);
/*    */   
/*    */   private static final String SQL = "from Productionplanning productionPlanning";
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from Productionplanning productionPlanning";
/* 21 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 22 */       { ProductionplanningQueryCondition.productionPlanningNumber_EQ, " productionPlanning.productionPlanningNumber=?"
/* 23 */       }, { ProductionplanningQueryCondition.uploadDate_EQ, "productionPlanning.uploadDate=?"
/* 24 */       }, { ProductionplanningQueryCondition.uploadUser_EQ, "productionPlanning.uploadUser.loginName=?"
/* 25 */       }, { ProductionplanningQueryCondition.productionPlanningNumber_Like, "productionPlanning.productionPlanningNumber like ?", new LikeConvert() }
/*    */     };
/*    */   
/* 28 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 29 */       { ProductionplanningQueryOrder.productionPlanningNumber, "productionPlanning.productionPlanningNumber" }
/*    */     };
/*    */   
/*    */   public Productionplanning getProductionplanning(Integer id) {
/* 33 */     if (id == null) {
/* 34 */       if (this.log.isDebugEnabled())
/* 35 */         this.log.debug("try to get Productionplanning with null id"); 
/* 36 */       return null;
/*    */     } 
/* 38 */     return (Productionplanning)getHibernateTemplate().get(Productionplanning.class, id);
/*    */   }
/*    */   public int listProductionplanningCount(Map conditions) {
/* 41 */     return getListCount(conditions, "select count(*) from Productionplanning productionPlanning", QUERY_CONDITIONS);
/*    */   }
/*    */   
/*    */   public List list(Map condtions, int pageNo, int pageSize, ProductionplanningQueryOrder order, boolean descend) {
/* 45 */     return getList(condtions, pageNo, pageSize, order, descend, "from Productionplanning productionPlanning", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */   
/*    */   public void update(Productionplanning u) {
/* 49 */     getHibernateTemplate().update(u);
/*    */   }
/*    */   
/*    */   public void delete(Productionplanning u) {
/* 53 */     getHibernateTemplate().delete(u);
/*    */   }
/*    */ 
/*    */   
/*    */   public Productionplanning insert(Productionplanning u) {
/* 58 */     getHibernateTemplate().save(u);
/* 59 */     return u;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/ProductionplanningDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */