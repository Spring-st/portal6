/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.UserSiteDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserSite;
/*     */ import com.aof.model.admin.query.UserSiteQueryCondition;
/*     */ import com.aof.model.admin.query.UserSiteQueryOrder;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateTemplate;
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
/*     */ public class UserSiteDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements UserSiteDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(UserSiteDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from UserSite us";
/*     */   private static final String SQL = "from UserSite us";
/*     */   
/*     */   public UserSite getUserSite(Integer userId, Integer siteId) {
/*  37 */     if (userId == null) {
/*  38 */       if (this.log.isDebugEnabled())
/*  39 */         this.log.debug("try to get UserSite with null user id"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     if (siteId == null) {
/*  43 */       if (this.log.isDebugEnabled())
/*  44 */         this.log.debug("try to get UserSite with null site id"); 
/*  45 */       return null;
/*     */     } 
/*  47 */     HibernateTemplate template = getHibernateTemplate();
/*  48 */     User u = (User)template.get(User.class, userId);
/*  49 */     if (u == null) return null; 
/*  50 */     Site s = (Site)template.get(Site.class, siteId);
/*  51 */     if (s == null) return null; 
/*  52 */     return (UserSite)getHibernateTemplate().get(
/*  53 */         UserSite.class, (Serializable)new UserSite(u, s));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserSite saveUserSite(UserSite us) {
/*  60 */     HibernateTemplate template = getHibernateTemplate();
/*  61 */     template.save(us);
/*  62 */     template.flush();
/*  63 */     template.evict(us);
/*  64 */     return getUserSite(us.getUser().getId(), us.getSite().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserSite updateUserSite(UserSite us) {
/*  71 */     HibernateTemplate template = getHibernateTemplate();
/*  72 */     template.update(us);
/*  73 */     template.flush();
/*  74 */     template.evict(us);
/*  75 */     return getUserSite(us.getUser().getId(), us.getSite().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserSite(UserSite us) {
/*  82 */     getHibernateTemplate().delete(us);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  91 */       { UserSiteQueryCondition.USER_ID_EQ, "us.user.id = ?" }
/*     */     };
/*  93 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  94 */       { UserSiteQueryOrder.SITE_NAME, "us.site.name" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserSiteListCount(Map conditions) {
/* 100 */     return getListCount(conditions, "select count(*) from UserSite us", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserSiteList(Map conditions, int pageNo, int pageSize, UserSiteQueryOrder order, boolean descend) {
/* 107 */     return getList(conditions, pageNo, pageSize, order, descend, "from UserSite us", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/UserSiteDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */