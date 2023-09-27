/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.SystemLogDAO;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.model.admin.SystemLog;
/*    */ import com.aof.model.admin.query.SystemLogQueryCondition;
/*    */ import com.aof.model.admin.query.SystemLogQueryOrder;
/*    */ import java.util.Date;
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
/*    */ public class SystemLogDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements SystemLogDAO
/*    */ {
/* 29 */   private Log log = LogFactory.getLog(SystemLogDAOHibernate.class);
/*    */   private static final String SQL_COUNT = "select count(*) from SystemLog s";
/*    */   
/*    */   public SystemLog getSystemLog(Integer id) {
/* 33 */     if (id == null) {
/* 34 */       if (this.log.isDebugEnabled()) this.log.debug("try to get system log with null id"); 
/* 35 */       return null;
/*    */     } 
/* 37 */     return (SystemLog)getHibernateTemplate().get(SystemLog.class, id);
/*    */   }
/*    */   private static final String SQL = "from SystemLog s";
/*    */   public SystemLog insertSystemLog(SystemLog systemLog) {
/* 41 */     systemLog.setActionTime(new Date());
/* 42 */     getHibernateTemplate().save(systemLog);
/* 43 */     return systemLog;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 51 */       { SystemLogQueryCondition.ID_EQ, "s.id = ?"
/* 52 */       }, { SystemLogQueryCondition.CONTENT_LIKE, "s.content like ?", new LikeConvert()
/* 53 */       }, { SystemLogQueryCondition.SITE_ID_EQ, "s.site.id = ?"
/* 54 */       }, { SystemLogQueryCondition.TARGET_LIKE, "s.target like ?", new LikeConvert()
/* 55 */       }, { SystemLogQueryCondition.TARGET_EQ, "s.target = ?"
/* 56 */       }, { SystemLogQueryCondition.USER_NAME_LIKE, "s.user.name like ?", new LikeConvert()
/* 57 */       }, { SystemLogQueryCondition.USER_ID_EQ, "s.user.loginName = ?"
/* 58 */       }, { SystemLogQueryCondition.ACTION_TIME_GT, "s.actionTime >= ?"
/* 59 */       }, { SystemLogQueryCondition.ACTION_TIME_LT, "s.actionTime <= ?" }
/*    */     };
/*    */   
/* 62 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 63 */       { SystemLogQueryOrder.ID, "s.id"
/* 64 */       }, { SystemLogQueryOrder.ACTION, "s.action"
/* 65 */       }, { SystemLogQueryOrder.ACTIONTIME, "s.actionTime"
/* 66 */       }, { SystemLogQueryOrder.SITE_NAME, "s.site.name"
/* 67 */       }, { SystemLogQueryOrder.TARGET, "s.target"
/* 68 */       }, { SystemLogQueryOrder.USER_NAME, "s.user.name"
/* 69 */       }, { SystemLogQueryOrder.USER_ID, "s.user.loginName" }
/*    */     };
/*    */   
/*    */   public int getSystemLogListCount(Map conditions) {
/* 73 */     return getListCount(conditions, "select count(*) from SystemLog s", QUERY_CONDITIONS);
/*    */   }
/*    */   
/*    */   public List getSystemLogList(Map conditions, int pageNo, int pageSize, SystemLogQueryOrder order, boolean descend) {
/* 77 */     return getList(conditions, pageNo, pageSize, order, descend, "from SystemLog s", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SystemLogDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */