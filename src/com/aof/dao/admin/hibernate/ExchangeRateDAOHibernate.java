/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.ExchangeRateDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryCondition;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExchangeRateDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ExchangeRateDAO
/*     */ {
/*  36 */   private Log log = LogFactory.getLog(CurrencyDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ExchangeRate e";
/*     */   private static final String SQL = "from ExchangeRate e";
/*     */   
/*     */   public ExchangeRate getExchangeRate(Integer id) {
/*  42 */     if (id == null) {
/*  43 */       if (this.log.isDebugEnabled()) this.log.debug("try to get ExchangeRate with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (ExchangeRate)getHibernateTemplate().get(ExchangeRate.class, id);
/*     */   }
/*     */   
/*     */   public ExchangeRate getExchangeRate(Currency currency, Site site) {
/*  50 */     List<ExchangeRate> l = getHibernateTemplate().find("from ExchangeRate er where er.currency.code = ? and er.site.id = ?", 
/*  51 */         new Object[] { currency.getCode(), site.getId()
/*  52 */         }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.INTEGER });
/*  53 */     if (l.isEmpty()) return null; 
/*  54 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  62 */       { ExchangeRateQueryCondition.CURRENCY_CODE_LIKE, "e.currency.code like ?", new LikeConvert()
/*  63 */       }, { ExchangeRateQueryCondition.CURRENCY_CODE_EQ, "e.currency.code = ?"
/*  64 */       }, { ExchangeRateQueryCondition.SITE_ID_EQ, "e.site.id=?"
/*  65 */       }, { ExchangeRateQueryCondition.CURRENCY_STATUS_EQ, "e.currency.enabled=?" }
/*     */     };
/*     */   
/*  68 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  69 */       { ExchangeRateQueryOrder.SITE_NAME, "e.site.name"
/*  70 */       }, { ExchangeRateQueryOrder.CODE, "e.currency.code"
/*  71 */       }, { ExchangeRateQueryOrder.EXCHANGERATE, "e.exchangeRate" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExchangeRateListCount(Map conditions) {
/*  78 */     return getListCount(conditions, "select count(*) from ExchangeRate e", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExchangeRateList(Map conditions, int pageNo, int pageSize, ExchangeRateQueryOrder order, boolean descend) {
/*  85 */     return getList(conditions, pageNo, pageSize, order, descend, "from ExchangeRate e", QUERY_CONDITIONS, QUERY_ORDERS);
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
/*     */   public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
/* 101 */     HibernateTemplate template = getHibernateTemplate();
/* 102 */     template.saveOrUpdate(exchangeRate);
/* 103 */     template.flush();
/* 104 */     template.refresh(exchangeRate);
/* 105 */     return exchangeRate;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/ExchangeRateDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */