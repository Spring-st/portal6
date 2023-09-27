/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SiteDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.SiteQueryCondition;
/*     */ import com.aof.model.admin.query.SiteQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class SiteManagerImpl
/*     */   extends BaseManager
/*     */   implements SiteManager
/*     */ {
/*     */   private SiteDAO dao;
/*     */   
/*     */   public void setSiteDAO(SiteDAO dao) {
/*  40 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite(Integer id) {
/*  47 */     return this.dao.getSite(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site saveSite(Site site) {
/*  54 */     return this.dao.saveSite(site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site saveSite(Site site, InputStream in) {
/*  61 */     Site s = saveSite(site);
/*  62 */     saveSiteLogo(s.getId(), in);
/*  63 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSite(Integer id) {
/*  70 */     this.dao.removeSite(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllEnabledSiteList() {
/*  77 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  78 */     conditions.put(SiteQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/*  79 */     return this.dao.getSiteList(conditions, 0, -1, SiteQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllEnabledSiteListAndIncludeThis(Integer id) {
/*  86 */     Site s = this.dao.getSite(id);
/*  87 */     List<Site> l = getAllEnabledSiteList();
/*  88 */     if (s == null)
/*  89 */       return l; 
/*  90 */     if (l.contains(s))
/*  91 */       return l; 
/*  92 */     for (int i = 0; i < l.size(); i++) {
/*  93 */       Site s2 = l.get(i);
/*  94 */       if (s2.getName().compareTo(s.getName()) > 0) {
/*  95 */         l.add(i, s);
/*  96 */         return l;
/*     */       } 
/*     */     } 
/*  99 */     l.add(s);
/* 100 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSiteListCount(Map conditions) {
/* 107 */     return this.dao.getSiteListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiteList(Map conditions, int pageNo, int pageSize, SiteQueryOrder order, boolean descend) {
/* 114 */     return this.dao.getSiteList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSiteHasLogo(Integer id) {
/* 121 */     return this.dao.isSiteHasLogo(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getSiteLogo(Integer id) {
/* 128 */     return this.dao.getSiteLogo(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSiteLogo(Integer id, InputStream in) {
/* 135 */     this.dao.saveSiteLogo(id, in);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SiteManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */