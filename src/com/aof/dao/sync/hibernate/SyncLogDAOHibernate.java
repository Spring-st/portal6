/*     */ package com.aof.dao.sync.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.sync.SyncLogDAO;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.sync.query.SyncLogQueryCondition;
/*     */ import com.aof.model.sync.query.SyncLogQueryOrder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyncLogDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SyncLogDAO
/*     */ {
/*  32 */   private Log log = LogFactory.getLog(SyncLogDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from SyncLog syncLog";
/*     */   
/*     */   private static final String SQL = "from SyncLog syncLog";
/*     */ 
/*     */   
/*     */   public SyncLog getSyncLog(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get SyncLog with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (SyncLog)getHibernateTemplate().get(SyncLog.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyncLog updateSyncLog(SyncLog syncLog) {
/*  54 */     Integer id = syncLog.getId();
/*  55 */     if (id == null) {
/*  56 */       throw new RuntimeException("cannot save a syncLog with null id");
/*     */     }
/*  58 */     SyncLog oldSyncLog = getSyncLog(id);
/*  59 */     if (oldSyncLog != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldSyncLog, syncLog);
/*  62 */       } catch (Exception e) {
/*  63 */         throw new RuntimeException("copy error");
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldSyncLog);
/*  66 */       return oldSyncLog;
/*     */     } 
/*  68 */     throw new RuntimeException("syncLog not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyncLog insertSyncLog(SyncLog syncLog) {
/*  78 */     getHibernateTemplate().save(syncLog);
/*  79 */     return syncLog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  87 */       { SyncLogQueryCondition.ID_EQ, "syncLog.id = ?"
/*     */       },
/*     */       {
/*  90 */         SyncLogQueryCondition.DATE_GT, "syncLog.sync_date >= ?"
/*  91 */       }, { SyncLogQueryCondition.DATE_LT, "syncLog.sync_date <= ?"
/*  92 */       }, { SyncLogQueryCondition.SYNCRESULTS_LT, "syncLog.sync_results = ?" }
/*     */     };
/*     */   
/*  95 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  96 */       { SyncLogQueryOrder.ID, "syncLog.id"
/*  97 */       }, { SyncLogQueryOrder.ENABLED, "syncLog.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSyncLogListCount(Map conditions) {
/* 106 */     return getListCount(conditions, "select count(*) from SyncLog syncLog", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSyncLogList(Map conditions, int pageNo, int pageSize, SyncLogQueryOrder order, boolean descend) {
/* 116 */     return getList(conditions, pageNo, pageSize, order, descend, "from SyncLog syncLog", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledSyncLogList() {
/* 125 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*     */     
/* 127 */     return getSyncLogList(conds, 0, -1, SyncLogQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteSyncLog(SyncLog syncLog) {
/* 132 */     getHibernateTemplate().delete(syncLog);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/sync/hibernate/SyncLogDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */