/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.UnplannedReasonsDAO;
/*     */ import com.aof.model.basic.UnplannedReasons;
/*     */ import com.aof.model.basic.query.UnplannedReasonsQueryCondition;
/*     */ import com.aof.model.basic.query.UnplannedReasonsQueryOrder;
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
/*     */ public class UnplannedReasonsDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements UnplannedReasonsDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(UnplannedReasonsDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from UnplannedReasons br";
/*     */   
/*     */   private static final String SQL = "from UnplannedReasons br";
/*     */ 
/*     */   
/*     */   public UnplannedReasons getUnplannedReasons(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get UnplannedReasons with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (UnplannedReasons)getHibernateTemplate().get(UnplannedReasons.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnplannedReasons updateUnplannedReasons(UnplannedReasons sr) {
/*  55 */     Integer id = sr.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  59 */     UnplannedReasons oldUnplannedReasons = getUnplannedReasons(id);
/*  60 */     if (oldUnplannedReasons != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldUnplannedReasons, sr);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldUnplannedReasons);
/*  67 */       return oldUnplannedReasons;
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
/*     */   public UnplannedReasons insertUnplannedReasons(UnplannedReasons sr) {
/*  79 */     getHibernateTemplate().save(sr);
/*  80 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { UnplannedReasonsQueryCondition.ID_EQ, "br.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  92 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  93 */       { UnplannedReasonsQueryOrder.ID, "br.id"
/*  94 */       }, { UnplannedReasonsQueryOrder.ENABLED, "br.status" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnplannedReasonsListCount(Map conditions) {
/* 103 */     return getListCount(conditions, "select count(*) from UnplannedReasons br", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUnplannedReasonsList(Map conditions, int pageNo, int pageSize, UnplannedReasonsQueryOrder order, boolean descend) {
/* 113 */     return getList(conditions, pageNo, pageSize, order, descend, "from UnplannedReasons br", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUnplannedReasonsList() {
/* 122 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 123 */     conds.put(UnplannedReasonsQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 124 */     return getUnplannedReasonsList(conds, 0, -1, UnplannedReasonsQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteUnplannedReasons(UnplannedReasons sr) {
/* 129 */     getHibernateTemplate().delete(sr);
/*     */   }
/*     */   
/*     */   public UnplannedReasons getUnplannedReasons(String id) {
/* 133 */     String sql = "from UnplannedReasons br where br.describe = ?";
/* 134 */     List<UnplannedReasons> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/* 135 */     if (list == null || list.isEmpty()) {
/* 136 */       return null;
/*     */     }
/* 138 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/UnplannedReasonsDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */