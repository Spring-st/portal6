/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.EdiProductionErrorLogDao;
/*    */ import com.aof.model.schedule.EdiProductionErrorLog;
/*    */ import com.aof.model.schedule.query.EdiProductionErrorLogQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EdiProductionErrorLogDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements EdiProductionErrorLogDao
/*    */ {
/* 19 */   private Log log = LogFactory.getLog(EdiProductionErrorLogDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from EdiProductionErrorLog entity";
/*    */   public EdiProductionErrorLog save(EdiProductionErrorLog entity) {
/* 21 */     getHibernateTemplate().save(entity);
/* 22 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from EdiProductionErrorLog entity";
/*    */   
/*    */   public EdiProductionErrorLog getEdiProductionErrorLog(Integer id) {
/* 27 */     if (id == null && 
/* 28 */       this.log.isDebugEnabled()) {
/* 29 */       this.log.debug("get EdiProductionErrorLog with null id!");
/* 30 */       return null;
/*    */     } 
/*    */     
/* 33 */     return (EdiProductionErrorLog)getHibernateTemplate().get(EdiProductionErrorLog.class, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(EdiProductionErrorLog entity) {
/* 38 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public EdiProductionErrorLog update(EdiProductionErrorLog entity) {
/* 42 */     if (entity.getId() == null) {
/* 43 */       throw new RuntimeException("update EdiProductionErrorLog with null id!");
/*    */     }
/* 45 */     EdiProductionErrorLog oldEntity = getEdiProductionErrorLog(entity.getId());
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
/* 62 */   private static final Object[][] QUERY_CONDITIONS = new Object[0][];
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
/* 74 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 80 */     return Integer.valueOf(getListCount(conditions, "select count(*) from EdiProductionErrorLog entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<EdiProductionErrorLog> getList(Map conditions, int pageNum, int pageSize, EdiProductionErrorLogQueryOrder order, boolean descend) {
/* 85 */     return getList(conditions, pageNum, pageSize, order, descend, "from EdiProductionErrorLog entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/EdiProductionErrorLogDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */