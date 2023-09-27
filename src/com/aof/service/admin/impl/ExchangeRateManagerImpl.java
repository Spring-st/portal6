/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.ExchangeRateDAO;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryCondition;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.ExchangeRateManager;
/*     */ import java.math.BigDecimal;
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
/*     */ public class ExchangeRateManagerImpl
/*     */   extends BaseManager
/*     */   implements ExchangeRateManager
/*     */ {
/*     */   private ExchangeRateDAO dao;
/*     */   
/*     */   public void setExchangeRateDAO(ExchangeRateDAO dao) {
/*  40 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExchangeRate getExchangeRate(Integer id) {
/*  47 */     return this.dao.getExchangeRate(id);
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public ExchangeRate saveExchangeRate(ExchangeRate function) {
/*  72 */     return this.dao.saveExchangeRate(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExchangeRateListCount(Map conditions) {
/*  79 */     return this.dao.getExchangeRateListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExchangeRateList(Map conditions, int pageNo, int pageSize, ExchangeRateQueryOrder order, boolean descend) {
/*  86 */     return this.dao.getExchangeRateList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExchangeRate getExchangeRate(Currency currency, Site site) {
/*  93 */     return this.dao.getExchangeRate(currency, site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExchangeRateListBySite(Site site) {
/* 100 */     Map<Object, Object> condition = new HashMap<Object, Object>();
/* 101 */     condition.put(ExchangeRateQueryCondition.SITE_ID_EQ, site.getId());
/* 102 */     return this.dao.getExchangeRateList(condition, 0, -1, ExchangeRateQueryOrder.CODE, false);
/*     */   }
/*     */   
/*     */   public List getEnabledExchangeRateListBySite(Site site) {
/* 106 */     Map<Object, Object> condition = new HashMap<Object, Object>();
/* 107 */     condition.put(ExchangeRateQueryCondition.SITE_ID_EQ, site.getId());
/* 108 */     condition.put(ExchangeRateQueryCondition.CURRENCY_STATUS_EQ, EnabledDisabled.ENABLED);
/* 109 */     return this.dao.getExchangeRateList(condition, 0, -1, ExchangeRateQueryOrder.CODE, false);
/*     */   }
/*     */   
/*     */   public List getEnabledExchangeRateListBySiteIncludeBaseCurrency(Site site) {
/* 113 */     List<ExchangeRate> currencyList = getEnabledExchangeRateListBySite(site);
/* 114 */     Currency baseCurrency = site.getBaseCurrency();
/* 115 */     boolean containBaseCurrency = false;
/* 116 */     Iterator<ExchangeRate> itor = currencyList.iterator();
/* 117 */     while (itor.hasNext()) {
/* 118 */       ExchangeRate exchangeRate = itor.next();
/* 119 */       if (exchangeRate.getCurrency().equals(baseCurrency)) {
/* 120 */         containBaseCurrency = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 124 */     if (!containBaseCurrency) {
/* 125 */       ExchangeRate baseExchangeRate = new ExchangeRate();
/* 126 */       baseExchangeRate.setCurrency(baseCurrency);
/* 127 */       baseExchangeRate.setSite(site);
/* 128 */       baseExchangeRate.setExchangeRate(new BigDecimal(1.0D));
/* 129 */       currencyList.add(0, baseExchangeRate);
/*     */     } 
/* 131 */     return currencyList;
/*     */   }
/*     */   
/*     */   public void fillEnabledExchangeRateListBySiteListIncludeBaseCurrency(List siteList) {
/* 135 */     for (Iterator<Site> itor = siteList.iterator(); itor.hasNext(); ) {
/* 136 */       Site s = itor.next();
/* 137 */       s.setEnabledExchangeRateList(getEnabledExchangeRateListBySiteIncludeBaseCurrency(s));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ExchangeRateManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */