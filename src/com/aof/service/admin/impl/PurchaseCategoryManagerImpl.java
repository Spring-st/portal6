/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.PurchaseCategoryDAO;
/*     */ import com.aof.dao.admin.PurchaseSubCategoryDAO;
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.PurchaseCategoryManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class PurchaseCategoryManagerImpl
/*     */   extends BaseManager
/*     */   implements PurchaseCategoryManager
/*     */ {
/*     */   private PurchaseCategoryDAO dao;
/*     */   private PurchaseSubCategoryDAO purchaseSubCategoryDAO;
/*     */   
/*     */   public void setPurchaseCategoryDAO(PurchaseCategoryDAO dao) {
/*  44 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseSubCategoryDAO(PurchaseSubCategoryDAO purchaseSubCategoryDAO) {
/*  51 */     this.purchaseSubCategoryDAO = purchaseSubCategoryDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory getPurchaseCategory(Integer id) {
/*  58 */     return this.dao.getPurchaseCategory(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory updatePurchaseCategory(PurchaseCategory function) {
/*  65 */     return this.dao.updatePurchaseCategory(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory insertPurchaseCategory(PurchaseCategory function) {
/*  72 */     return this.dao.insertPurchaseCategory(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseCategoryListCount(Map conditions) {
/*  79 */     return this.dao.getPurchaseCategoryListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseCategoryList(Map conditions, int pageNo, int pageSize, PurchaseCategoryQueryOrder order, boolean descend) {
/*  86 */     return this.dao.getPurchaseCategoryList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseCategoryOfSite(Site site) {
/*  93 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  94 */     conds.put(PurchaseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/*  95 */     conds.put(PurchaseCategoryQueryCondition.SITE_ID_EQ, site.getId());
/*  96 */     return getPurchaseCategoryList(conds, 0, -1, PurchaseCategoryQueryOrder.DESCRIPTION, false);
/*     */   }
/*     */   
/*     */   public List getEnabledPurchaseCategoryOfSiteIncludeGlobal(Site site) {
/* 100 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 101 */     conds.put(PurchaseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 102 */     conds.put(PurchaseCategoryQueryCondition.GLOBAL_OR_SITE_ID_EQ, site.getId());
/* 103 */     return getPurchaseCategoryList(conds, 0, -1, PurchaseCategoryQueryOrder.DESCRIPTION, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseCategorySubCategoryOfSite(Site site) {
/* 110 */     List purchaseCategoryList = getEnabledPurchaseCategoryOfSite(site);
/* 111 */     for (Iterator<PurchaseCategory> itor = purchaseCategoryList.iterator(); itor.hasNext();) {
/* 112 */       ((PurchaseCategory)itor.next()).setEnabledPurchaseSubCategoryList(new ArrayList());
/*     */     }
/* 114 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 115 */     conds.put(PurchaseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 116 */     conds.put(PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_SITE_ID_EQ, site.getId());
/* 117 */     for (Iterator<PurchaseSubCategory> iterator = this.purchaseSubCategoryDAO.getPurchaseSubCategoryList(conds, 0, -1, PurchaseSubCategoryQueryOrder.DESCRIPTION, false).iterator(); iterator.hasNext(); ) {
/* 118 */       PurchaseSubCategory psc = iterator.next();
/* 119 */       psc.getPurchaseCategory().addEnabledPurchaseSubCategory(psc);
/*     */     } 
/* 121 */     return purchaseCategoryList;
/*     */   }
/*     */   
/*     */   public List getEnabledPurchaseCategorySubCategoryOfSiteIncludeGlobal(Site site) {
/* 125 */     List purchaseCategoryList = getEnabledPurchaseCategoryOfSiteIncludeGlobal(site);
/* 126 */     for (Iterator<PurchaseCategory> itor = purchaseCategoryList.iterator(); itor.hasNext();) {
/* 127 */       ((PurchaseCategory)itor.next()).setEnabledPurchaseSubCategoryList(new ArrayList());
/*     */     }
/* 129 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 130 */     conds.put(PurchaseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 131 */     conds.put(PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_SITE_ID_EQ_OR_NULL, site.getId());
/* 132 */     for (Iterator<PurchaseSubCategory> iterator = this.purchaseSubCategoryDAO.getPurchaseSubCategoryList(conds, 0, -1, PurchaseSubCategoryQueryOrder.DESCRIPTION, false).iterator(); iterator.hasNext(); ) {
/* 133 */       PurchaseSubCategory psc = iterator.next();
/* 134 */       psc.getPurchaseCategory().addEnabledPurchaseSubCategory(psc);
/*     */     } 
/* 136 */     return purchaseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseCategorySubCategoryOfGlobal() {
/* 143 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 144 */     conds.put(PurchaseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 145 */     conds.put(PurchaseCategoryQueryCondition.GLOBAL, null);
/* 146 */     List purchaseCategoryList = getPurchaseCategoryList(conds, 0, -1, PurchaseCategoryQueryOrder.DESCRIPTION, false);
/* 147 */     for (Iterator<PurchaseCategory> iterator = purchaseCategoryList.iterator(); iterator.hasNext();) {
/* 148 */       ((PurchaseCategory)iterator.next()).setEnabledPurchaseSubCategoryList(new ArrayList());
/*     */     }
/* 150 */     conds.clear();
/* 151 */     conds.put(PurchaseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 152 */     for (Iterator<PurchaseSubCategory> itor = this.purchaseSubCategoryDAO.getPurchaseSubCategoryList(conds, 0, -1, PurchaseSubCategoryQueryOrder.DESCRIPTION, false).iterator(); itor.hasNext(); ) {
/* 153 */       PurchaseSubCategory psc = itor.next();
/* 154 */       psc.getPurchaseCategory().addEnabledPurchaseSubCategory(psc);
/*     */     } 
/* 156 */     return purchaseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillEnabledPurchaseCategorySubCategoryForSiteList(List siteList) {
/* 163 */     for (Iterator<Site> itor = siteList.iterator(); itor.hasNext(); ) {
/* 164 */       Site s = itor.next();
/* 165 */       s.setEnabledPurchaseCategoryList(getEnabledPurchaseCategorySubCategoryOfSiteIncludeGlobal(s));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/PurchaseCategoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */