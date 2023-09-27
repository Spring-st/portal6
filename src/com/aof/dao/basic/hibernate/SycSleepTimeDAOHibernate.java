/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.SycSleepTimeDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.query.SycSleepTimeQueryCondition;
/*     */ import com.aof.model.basic.query.SycSleepTimeQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SycSleepTimeDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SycSleepTimeDAO
/*     */ {
/*  36 */   private Log log = LogFactory.getLog(SycSleepTimeDAOHibernate.class);
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from SycSleepTime sst";
/*     */ 
/*     */   
/*     */   private static final String SQL = "from SycSleepTime sst";
/*     */ 
/*     */   
/*  45 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  46 */       { SycSleepTimeQueryCondition.ID_EQ, "sst.id = ?"
/*  47 */       }, { SycSleepTimeQueryCondition.TYPE_EQ, "sst.type = ?"
/*  48 */       }, { SycSleepTimeQueryCondition.SLEEPTIME_EQ, "sst.sleepTime = ?" }
/*     */     };
/*     */   
/*  51 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  52 */       { SycSleepTimeQueryOrder.ID, "sst.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public SycSleepTime getSycSleepTime(Integer id) {
/*  58 */     if (id == null) {
/*  59 */       if (this.log.isDebugEnabled())
/*  60 */         this.log.debug("try to get SycSleepTime with null id"); 
/*  61 */       return null;
/*     */     } 
/*  63 */     return (SycSleepTime)getHibernateTemplate().get(SycSleepTime.class, id);
/*     */   }
/*     */   
/*     */   public int getSycSleepTimeCount(Map conditions) {
/*  67 */     return getListCount(conditions, "select count(*) from SycSleepTime sst", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSycSleepTimeList(Map conditions, int pageNo, int pageSize, SycSleepTimeQueryOrder order, boolean descend) {
/*  72 */     return getList(conditions, pageNo, pageSize, order, descend, "from SycSleepTime sst", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public SycSleepTime insertSycSleepTime(SycSleepTime sycSleepTime) {
/*  76 */     getHibernateTemplate().save(sycSleepTime);
/*  77 */     return sycSleepTime;
/*     */   }
/*     */   
/*     */   public SycSleepTime updateSycSleepTime(SycSleepTime sycSleepTime) {
/*  81 */     Integer id = sycSleepTime.getId();
/*  82 */     if (id == null) {
/*  83 */       throw new RuntimeException("cannot save a sycSleepTime with null id");
/*     */     }
/*  85 */     SycSleepTime sst = getSycSleepTime(id);
/*  86 */     if (sst != null) {
/*     */       try {
/*  88 */         PropertyUtils.copyProperties(sst, sycSleepTime);
/*  89 */       } catch (Exception e) {
/*  90 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  92 */       getHibernateTemplate().update(sst);
/*  93 */       return sst;
/*     */     } 
/*  95 */     throw new RuntimeException("sycSleepTime not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteSycSleepTime(SycSleepTime sycSleepTime) {
/* 100 */     getHibernateTemplate().delete(sycSleepTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public SycSleepTime getSycSleepTime(String type) {
/* 105 */     String sql = "from SycSleepTime sst where sst.type = ?";
/* 106 */     List<SycSleepTime> list = getHibernateTemplate().find(sql, type, (Type)Hibernate.STRING);
/* 107 */     if (list == null || list.isEmpty()) {
/* 108 */       return null;
/*     */     }
/* 110 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/SycSleepTimeDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */