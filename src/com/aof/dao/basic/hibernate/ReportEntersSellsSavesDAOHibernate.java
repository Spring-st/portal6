/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.ReportEntersSellsSavesDAO;
/*     */ import com.aof.model.basic.ReportEntersSellsSaves;
/*     */ import com.aof.model.basic.query.ReportEntersSellsSavesQueryCondition;
/*     */ import com.aof.model.basic.query.ReportEntersSellsSavesQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.util.HashMap;
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
/*     */ public class ReportEntersSellsSavesDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ReportEntersSellsSavesDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(ReportEntersSellsSavesDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ReportEntersSellsSaves br";
/*     */   
/*     */   private static final String SQL = "from ReportEntersSellsSaves br";
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves getReportEntersSellsSaves(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get ReportEntersSellsSaves with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (ReportEntersSellsSaves)getHibernateTemplate().get(ReportEntersSellsSaves.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves updateReportEntersSellsSaves(ReportEntersSellsSaves sr) {
/*  55 */     Integer id = sr.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  59 */     ReportEntersSellsSaves oldReportEntersSellsSaves = getReportEntersSellsSaves(id);
/*  60 */     if (oldReportEntersSellsSaves != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldReportEntersSellsSaves, sr);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldReportEntersSellsSaves);
/*  67 */       return oldReportEntersSellsSaves;
/*     */     } 
/*  69 */     throw new RuntimeException("sr not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportEntersSellsSaves insertReportEntersSellsSaves(ReportEntersSellsSaves sr) {
/*  79 */     getHibernateTemplate().save(sr);
/*  80 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { ReportEntersSellsSavesQueryCondition.ID_EQ, "br.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  92 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  93 */       { ReportEntersSellsSavesQueryOrder.ID, "br.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReportEntersSellsSavesListCount(Map conditions) {
/* 102 */     return getListCount(conditions, "select count(*) from ReportEntersSellsSaves br", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getReportEntersSellsSavesList(Map conditions, int pageNo, int pageSize, ReportEntersSellsSavesQueryOrder order, boolean descend) {
/* 112 */     return getList(conditions, pageNo, pageSize, order, descend, "from ReportEntersSellsSaves br", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledReportEntersSellsSavesList() {
/* 121 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 122 */     conds.put(ReportEntersSellsSavesQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 123 */     return getReportEntersSellsSavesList(conds, 0, -1, ReportEntersSellsSavesQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteReportEntersSellsSaves(ReportEntersSellsSaves sr) {
/* 128 */     getHibernateTemplate().delete(sr);
/*     */   }
/*     */   
/*     */   public ReportEntersSellsSaves getReportEntersSellsSaves(String id) {
/* 132 */     String sql = "from ReportEntersSellsSaves br where br.describe = ?";
/* 133 */     List<ReportEntersSellsSaves> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/* 134 */     if (list == null || list.isEmpty()) {
/* 135 */       return null;
/*     */     }
/* 137 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/ReportEntersSellsSavesDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */