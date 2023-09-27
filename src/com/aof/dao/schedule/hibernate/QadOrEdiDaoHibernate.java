/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.QadOrEdiDao;
/*    */ import com.aof.model.schedule.QadOrEdi;
/*    */ import com.aof.model.schedule.query.QadOrEdiQueryCondition;
/*    */ import com.aof.model.schedule.query.QadOrEdiQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class QadOrEdiDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements QadOrEdiDao
/*    */ {
/*    */   private static final String SQL_COUNT = "select count(*) from QadOrEdi entity";
/*    */   private static final String SQL = "from QadOrEdi entity";
/* 20 */   private Log log = LogFactory.getLog(QadOrEdiDaoHibernate.class);
/*    */   public QadOrEdi save(QadOrEdi entity) {
/* 22 */     getHibernateTemplate().save(entity);
/* 23 */     return entity;
/*    */   }
/*    */   public QadOrEdi getQadOrEdi(Integer id) {
/* 26 */     if (id == null && 
/* 27 */       this.log.isDebugEnabled()) {
/* 28 */       this.log.debug("get QadOrEdi with null id!");
/* 29 */       return null;
/*    */     } 
/*    */     
/* 32 */     return (QadOrEdi)getHibernateTemplate().get(QadOrEdi.class, id);
/*    */   }
/*    */   
/*    */   public void delete(QadOrEdi entity) {
/* 36 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public QadOrEdi update(QadOrEdi entity) {
/* 40 */     if (entity.getId() == null) {
/* 41 */       throw new RuntimeException("update QadOrEdi with null id!");
/*    */     }
/* 43 */     QadOrEdi oldEntity = getQadOrEdi(entity.getId());
/* 44 */     if (oldEntity != null) {
/*    */       try {
/* 46 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 47 */       } catch (Exception e) {
/* 48 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 50 */       getHibernateTemplate().update(oldEntity);
/* 51 */       return oldEntity;
/*    */     } 
/* 53 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 62 */       { QadOrEdiQueryCondition.ID_EQ, "entity.id = ?"
/* 63 */       }, { QadOrEdiQueryCondition.MODELS_EQ, "entity.models = ?" }
/*    */     };
/*    */ 
/*    */   
/* 67 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 68 */       { QadOrEdiQueryOrder.ID, "entity.id" }
/*    */     };
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 72 */     return Integer.valueOf(getListCount(conditions, "select count(*) from QadOrEdi entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<QadOrEdi> getList(Map conditions, int pageNum, int pageSize, QadOrEdiQueryOrder order, boolean descend) {
/* 77 */     return getList(conditions, pageNum, pageSize, order, descend, "from QadOrEdi entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/QadOrEdiDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */