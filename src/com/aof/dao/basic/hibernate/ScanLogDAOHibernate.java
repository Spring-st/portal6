/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.ScanLogDAO;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.model.basic.ScanLog;
/*    */ import com.aof.model.basic.query.ScanLogQueryCondition;
/*    */ import com.aof.model.basic.query.ScanLogQueryOrder;
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
/*    */ public class ScanLogDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements ScanLogDAO
/*    */ {
/* 23 */   private Log log = LogFactory.getLog(ScanLogDAOHibernate.class); private static final String SQL_COUNT = "select count(*) from ScanLog sc";
/*    */   
/*    */   public ScanLog getScanLog(Integer id) {
/* 26 */     if (id == null) {
/* 27 */       if (this.log.isDebugEnabled())
/* 28 */         this.log.debug("try to get ScanLog with null id"); 
/* 29 */       return null;
/*    */     } 
/* 31 */     return (ScanLog)getHibernateTemplate().get(ScanLog.class, id);
/*    */   }
/*    */   private static final String SQL = "from ScanLog sc";
/*    */   public ScanLog insertScanLog(ScanLog log) {
/* 35 */     getHibernateTemplate().save(log);
/* 36 */     return log;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 44 */       { ScanLogQueryCondition.ID_EQ, "sc.id = ?"
/* 45 */       }, { ScanLogQueryCondition.TYPE_EQ, "sc.type = ?"
/* 46 */       }, { ScanLogQueryCondition.DESCRIBE_EQ, "sc.describe like ?", 
/* 47 */         new LikeConvert()
/* 48 */       }, { ScanLogQueryCondition.STARTDATE_EQ, "sc.date >= ?"
/* 49 */       }, { ScanLogQueryCondition.ENDDATE_EQ, "sc.date <= ?" }
/*    */     };
/* 51 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { ScanLogQueryOrder.ID, 
/* 52 */         "sc.id" } };
/*    */ 
/*    */ 
/*    */   
/*    */   public List getScanLogList(Map conditions, int pageNo, int pageSize, ScanLogQueryOrder order, boolean descend) {
/* 57 */     return getList(conditions, pageNo, pageSize, order, descend, "from ScanLog sc", 
/* 58 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScanLogListCount(Map conditions) {
/* 63 */     return getListCount(conditions, "select count(*) from ScanLog sc", QUERY_CONDITIONS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/ScanLogDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */