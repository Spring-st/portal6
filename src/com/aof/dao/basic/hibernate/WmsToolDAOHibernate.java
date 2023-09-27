/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.WmsToolDAO;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.basic.query.WmsToolQueryCondition;
/*     */ import com.aof.model.basic.query.WmsToolQueryOrder;
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
/*     */ public class WmsToolDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements WmsToolDAO
/*     */ {
/*  32 */   private Log log = LogFactory.getLog(WmsToolDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from WmsTool ct";
/*     */   
/*     */   private static final String SQL = "from WmsTool ct";
/*     */ 
/*     */   
/*     */   public WmsTool getWmsTool(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get WmsTool with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (WmsTool)getHibernateTemplate().get(WmsTool.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsTool updateWmsTool(WmsTool ct) {
/*  54 */     Integer id = ct.getId();
/*  55 */     if (id == null) {
/*  56 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  58 */     WmsTool oldWmsTool = getWmsTool(id);
/*  59 */     if (oldWmsTool != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldWmsTool, ct);
/*  62 */       } catch (Exception e) {
/*  63 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldWmsTool);
/*  66 */       return oldWmsTool;
/*     */     } 
/*  68 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsTool insertWmsTool(WmsTool ct) {
/*  78 */     getHibernateTemplate().save(ct);
/*  79 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  87 */       { WmsToolQueryCondition.ID_EQ, "ct.id = ?"
/*  88 */       }, { WmsToolQueryCondition.SEQ_EQ, "ct.seq = ?"
/*  89 */       }, { WmsToolQueryCondition.CODE_EQ, "ct.code = ?"
/*  90 */       }, { WmsToolQueryCondition.ISPRINT_EQ, "ct.isPrint = ?"
/*  91 */       }, { WmsToolQueryCondition.STATUS_EQ, "ct.status = ?" }
/*     */     };
/*     */   
/*  94 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  95 */       { WmsToolQueryOrder.ID, "ct.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmsToolListCount(Map conditions) {
/* 105 */     return getListCount(conditions, "select count(*) from WmsTool ct", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsToolList(Map conditions, int pageNo, int pageSize, WmsToolQueryOrder order, boolean descend) {
/* 115 */     return getList(conditions, pageNo, pageSize, order, descend, "from WmsTool ct", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledWmsToolList() {
/* 126 */     return getWmsToolList((Map)null, 0, -1, WmsToolQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsTool(WmsTool ct) {
/* 131 */     getHibernateTemplate().delete(ct);
/*     */   }
/*     */   
/*     */   public WmsTool getWmsTool(String code) {
/* 135 */     String sql = "from WmsTool por where por.code = ?";
/* 136 */     List<WmsTool> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 137 */     if (list == null || list.isEmpty()) {
/* 138 */       return null;
/*     */     }
/* 140 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public List<BadReasons> getBadReasons() {
/* 144 */     String sql = "from BadReasons br where br.status = 0";
/* 145 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/WmsToolDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */