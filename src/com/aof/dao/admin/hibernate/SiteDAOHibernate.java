/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.SiteDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.SiteLogo;
/*     */ import com.aof.model.admin.query.SiteQueryCondition;
/*     */ import com.aof.model.admin.query.SiteQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Blob;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
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
/*     */ public class SiteDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SiteDAO
/*     */ {
/*  41 */   private Log log = LogFactory.getLog(SiteDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Site s";
/*     */   private static final String SQL = "from Site s";
/*     */   
/*     */   public Site getSite(Integer id) {
/*  47 */     if (id == null) {
/*  48 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Site with null id"); 
/*  49 */       return null;
/*     */     } 
/*  51 */     return (Site)getHibernateTemplate().get(Site.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site saveSite(Site site) {
/*  58 */     getHibernateTemplate().saveOrUpdate(site);
/*  59 */     return site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSite(Integer id) {
/*  66 */     Site site = getSite(id);
/*  67 */     getHibernateTemplate().delete(site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  75 */       { SiteQueryCondition.NAME_LIKE, "s.name like ?", new LikeConvert()
/*  76 */       }, { SiteQueryCondition.ENABLED_EQ, "s.enabled = ?"
/*  77 */       }, { SiteQueryCondition.NAME_EQ, "s.name = ?" }
/*     */     };
/*     */   
/*  80 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  81 */       { SiteQueryOrder.NAME, "s.name"
/*  82 */       }, { SiteQueryOrder.ENABLED, "s.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSiteListCount(Map conditions) {
/*  89 */     return getListCount(conditions, "select count(*) from Site s", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiteList(Map conditions, int pageNo, int pageSize, SiteQueryOrder order, boolean descend) {
/*  96 */     return getList(conditions, pageNo, pageSize, order, descend, "from Site s", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSiteHasLogo(Integer id) {
/* 103 */     return getHibernateTemplate().iterate("select sl.id from SiteLogo sl where sl.id = ? and not sl.logo is null", id, (Type)Hibernate.INTEGER).hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getSiteLogo(final Integer id) {
/* 110 */     return (InputStream)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 113 */             SiteLogo sl = (SiteLogo)session.get(SiteLogo.class, id);
/* 114 */             if (sl == null) return null; 
/* 115 */             Blob logo = sl.getLogo();
/* 116 */             if (logo == null) return null; 
/*     */             try {
/* 118 */               return SiteDAOHibernate.preRead(logo.getBinaryStream());
/* 119 */             } catch (IOException e) {
/* 120 */               throw new HibernateException(e);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSiteLogo(final Integer id, final InputStream in) {
/* 131 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 134 */             SiteLogo sl = (SiteLogo)session.get(SiteLogo.class, id);
/* 135 */             if (sl == null) return null; 
/*     */             try {
/* 137 */               Blob logo = Hibernate.createBlob(in);
/* 138 */               sl.setLogo(logo);
/* 139 */               session.update(sl);
/* 140 */             } catch (IOException e) {
/* 141 */               throw new HibernateException(e);
/*     */             } finally {
/* 143 */               if (session != null && session.isOpen()) {
/* 144 */                 session.close();
/*     */               }
/*     */             } 
/* 147 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledSiteList() {
/* 154 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 155 */     conds.put(SiteQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 156 */     return getSiteList(conds, 0, -1, (SiteQueryOrder)null, false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SiteDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */