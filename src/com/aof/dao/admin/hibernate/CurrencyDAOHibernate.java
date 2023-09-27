/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.CurrencyDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.query.CurrencyQueryCondition;
/*     */ import com.aof.model.admin.query.CurrencyQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class CurrencyDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements CurrencyDAO
/*     */ {
/*  32 */   private Log log = LogFactory.getLog(CurrencyDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Currency c";
/*     */   private static final String SQL = "from Currency c";
/*     */   
/*     */   public Currency getCurrency(String code) {
/*  38 */     if (code == null) {
/*  39 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Currency with null code"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     return (Currency)getHibernateTemplate().get(Currency.class, code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  51 */       { CurrencyQueryCondition.CODE_LIKE, "c.code like ?", new LikeConvert()
/*  52 */       }, { CurrencyQueryCondition.NAME_LIKE, "c.name like ?", new LikeConvert()
/*  53 */       }, { CurrencyQueryCondition.STATUS_EQ, "c.enabled=?" }
/*     */     };
/*     */   
/*  56 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  57 */       { CurrencyQueryOrder.CODE, "c.code"
/*  58 */       }, { CurrencyQueryOrder.STATUS, "c.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCurrencyList(Map conditions, int pageNo, int pageSize, CurrencyQueryOrder order, boolean descend) {
/*  65 */     return getList(conditions, pageNo, pageSize, order, descend, "from Currency c", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrencyListCount(Map conditions) {
/*  72 */     return getListCount(conditions, "select count(*) from Currency c", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency insertCurrency(Currency currency) {
/*  79 */     getHibernateTemplate().save(currency);
/*  80 */     return currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency updateCurrency(Currency currency) {
/*  87 */     String code = currency.getCode();
/*  88 */     if (code == null) {
/*  89 */       throw new RuntimeException("cannot save a currency with null code");
/*     */     }
/*  91 */     Currency oldCurrency = getCurrency(code);
/*  92 */     if (oldCurrency != null) {
/*     */       try {
/*  94 */         PropertyUtils.copyProperties(oldCurrency, currency);
/*     */       }
/*  96 */       catch (Exception e) {
/*     */         
/*  98 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 100 */       getHibernateTemplate().update(oldCurrency);
/* 101 */       return oldCurrency;
/*     */     } 
/*     */ 
/*     */     
/* 105 */     throw new RuntimeException("currency not found");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/CurrencyDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */