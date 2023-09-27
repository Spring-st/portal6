/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.BadReasonsDAO;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basic.query.BadReasonsQueryCondition;
/*     */ import com.aof.model.basic.query.BadReasonsQueryOrder;
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
/*     */ public class BadReasonsDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BadReasonsDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(BadReasonsDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from BadReasons br";
/*     */   
/*     */   private static final String SQL = "from BadReasons br";
/*     */ 
/*     */   
/*     */   public BadReasons getBadReasons(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get BadReasons with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (BadReasons)getHibernateTemplate().get(BadReasons.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BadReasons updateBadReasons(BadReasons sr) {
/*  55 */     Integer id = sr.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a sr with null id");
/*     */     }
/*  59 */     BadReasons oldBadReasons = getBadReasons(id);
/*  60 */     if (oldBadReasons != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldBadReasons, sr);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldBadReasons);
/*  67 */       return oldBadReasons;
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
/*     */   public BadReasons insertBadReasons(BadReasons sr) {
/*  79 */     getHibernateTemplate().save(sr);
/*  80 */     return sr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  88 */       { BadReasonsQueryCondition.ID_EQ, "br.id = ?" }
/*     */     };
/*     */ 
/*     */   
/*  92 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  93 */       { BadReasonsQueryOrder.ID, "br.id"
/*  94 */       }, { BadReasonsQueryOrder.ENABLED, "br.status" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBadReasonsListCount(Map conditions) {
/* 103 */     return getListCount(conditions, "select count(*) from BadReasons br", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBadReasonsList(Map conditions, int pageNo, int pageSize, BadReasonsQueryOrder order, boolean descend) {
/* 113 */     return getList(conditions, pageNo, pageSize, order, descend, "from BadReasons br", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledBadReasonsList() {
/* 122 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 123 */     conds.put(BadReasonsQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 124 */     return getBadReasonsList(conds, 0, -1, BadReasonsQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteBadReasons(BadReasons sr) {
/* 129 */     getHibernateTemplate().delete(sr);
/*     */   }
/*     */   
/*     */   public BadReasons getBadReasons(String id) {
/* 133 */     String sql = "from BadReasons br where br.describe = ?";
/* 134 */     List<BadReasons> list = getHibernateTemplate().find(sql, id, (Type)Hibernate.STRING);
/* 135 */     if (list == null || list.isEmpty()) {
/* 136 */       return null;
/*     */     }
/* 138 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/BadReasonsDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */