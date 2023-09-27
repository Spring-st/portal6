/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.CountryDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.query.CountryQueryCondition;
/*     */ import com.aof.model.admin.query.CountryQueryOrder;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.util.HashMap;
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
/*     */ public class CountryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements CountryDAO
/*     */ {
/*  32 */   private Log log = LogFactory.getLog(CountryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Country country";
/*     */   
/*     */   private static final String SQL = "from Country country";
/*     */ 
/*     */   
/*     */   public Country getCountry(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get Country with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (Country)getHibernateTemplate().get(Country.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country updateCountry(Country country) {
/*  54 */     Integer id = country.getId();
/*  55 */     if (id == null) {
/*  56 */       throw new RuntimeException("cannot save a country with null id");
/*     */     }
/*  58 */     Country oldCountry = getCountry(id);
/*  59 */     if (oldCountry != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldCountry, country);
/*  62 */       } catch (Exception e) {
/*  63 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldCountry);
/*  66 */       return oldCountry;
/*     */     } 
/*  68 */     throw new RuntimeException("country not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country insertCountry(Country country) {
/*  78 */     getHibernateTemplate().save(country);
/*  79 */     return country;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  88 */         CountryQueryCondition.ID_EQ, "country.id = ?"
/*     */ 
/*     */ 
/*     */       
/*     */       },
/*     */ 
/*     */       
/*     */       {
/*     */         
/*  97 */         CountryQueryCondition.SHORTNAME_LIKE, "country.shortName like ?", new LikeConvert()
/*  98 */       }, { CountryQueryCondition.ENGNAME_LIKE, "country.engName like ?", new LikeConvert()
/*  99 */       }, { CountryQueryCondition.CHNNAME_LIKE, "country.chnName like ?", new LikeConvert()
/* 100 */       }, { CountryQueryCondition.ENABLED_EQ, "country.enabled = ?" }
/*     */     };
/* 102 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 104 */         CountryQueryOrder.ID, "country.id"
/*     */       
/*     */       },
/* 107 */       { CountryQueryOrder.SHORTNAME, "country.shortName" }, { CountryQueryOrder.ENGNAME, "country.engName" }, { CountryQueryOrder.CHNNAME, "country.chnName"
/* 108 */       }, { CountryQueryOrder.ENABLED, "country.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCountryListCount(Map conditions) {
/* 116 */     return getListCount(conditions, "select count(*) from Country country", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCountryList(Map conditions, int pageNo, int pageSize, CountryQueryOrder order, boolean descend) {
/* 126 */     return getList(conditions, pageNo, pageSize, order, descend, "from Country country", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List listEnabledCountry() {
/* 135 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 136 */     conds.put(CountryQueryCondition.ENABLED_EQ, YesNo.YES.getEnumCode());
/* 137 */     return getCountryList(conds, 0, -1, CountryQueryOrder.ENGNAME, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteCountry(Country country) {
/* 142 */     getHibernateTemplate().delete(country);
/*     */   }
/*     */   
/*     */   public Country getCountryByChnName(String chnName) {
/* 146 */     List<Country> list = getHibernateTemplate().find("from Country c where c.chnName=?", chnName);
/* 147 */     if (list.isEmpty())
/* 148 */       return null; 
/* 149 */     if (list.size() == 1)
/* 150 */       return list.get(0); 
/* 151 */     throw new RuntimeException("country.chnName repeat");
/*     */   }
/*     */   
/*     */   public Country getCountryByEngName(String engName) {
/* 155 */     List<Country> list = getHibernateTemplate().find("from Country c where c.engName=?", engName);
/* 156 */     if (list.isEmpty())
/* 157 */       return null; 
/* 158 */     if (list.size() == 1)
/* 159 */       return list.get(0); 
/* 160 */     throw new RuntimeException("country.engName repeat");
/*     */   }
/*     */   
/*     */   public Country getCountryByShortName(String sn) {
/* 164 */     List<Country> list = getHibernateTemplate().find("from Country c where c.shortName=?", sn);
/* 165 */     if (list.isEmpty())
/* 166 */       return null; 
/* 167 */     if (list.size() == 1)
/* 168 */       return list.get(0); 
/* 169 */     throw new RuntimeException("country.engName repeat");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/CountryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */