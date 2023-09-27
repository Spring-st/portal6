/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.CityDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.query.CityQueryCondition;
/*     */ import com.aof.model.admin.query.CityQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public class CityDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements CityDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(CityDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from City city";
/*     */   
/*     */   private static final String SQL = "from City city";
/*     */ 
/*     */   
/*     */   public City getCity(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get City with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (City)getHibernateTemplate().get(City.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City updateCity(City city) {
/*  55 */     Integer id = city.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a city with null id");
/*     */     }
/*  59 */     City oldCity = getCity(id);
/*  60 */     if (oldCity != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldCity, city);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldCity);
/*  67 */       return oldCity;
/*     */     } 
/*  69 */     throw new RuntimeException("city not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City insertCity(City city) {
/*  79 */     getHibernateTemplate().save(city);
/*  80 */     return city;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { CityQueryCondition.ID_EQ, "city.id = ?"
/*  88 */       }, { CityQueryCondition.PROVINCE_ID_EQ, "city.province.id = ?" }, { CityQueryCondition.ENGNAME_LIKE, "city.engName like ?", new LikeConvert()
/*  89 */       }, { CityQueryCondition.CHNNAME_LIKE, "city.chnName like ?", new LikeConvert() }, { CityQueryCondition.ENABLED_EQ, "city.enabled = ?" } };
/*     */ 
/*     */ 
/*     */   
/*  93 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { CityQueryOrder.ID, "city.id" }, { CityQueryOrder.ENGNAME, "city.engName"
/*  94 */       }, { CityQueryOrder.CHNNAME, "city.chnName" }, { CityQueryOrder.ENABLED, "city.enabled" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCityListCount(Map conditions) {
/* 102 */     return getListCount(conditions, "select count(*) from City city", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCityList(Map conditions, int pageNo, int pageSize, CityQueryOrder order, boolean descend) {
/* 112 */     return getList(conditions, pageNo, pageSize, order, descend, "from City city", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledCityList() {
/* 121 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 122 */     conds.put(CityQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 123 */     return getCityList(conds, 0, -1, CityQueryOrder.ENGNAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledCityList(Province p) {
/* 132 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 133 */     conds.put(CityQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 134 */     conds.put(CityQueryCondition.PROVINCE_ID_EQ, p.getId());
/* 135 */     return getCityList(conds, 0, -1, CityQueryOrder.ENGNAME, false);
/*     */   }
/*     */   
/*     */   public void deleteCity(City city) {
/* 139 */     getHibernateTemplate().delete(city);
/*     */   }
/*     */   
/*     */   public City getCityByChnName(Province p, String chnName) {
/* 143 */     List<City> list = getHibernateTemplate().find("from City city where city.province.id=? and city.chnName=?", new Object[] { p.getId(), chnName });
/* 144 */     if (list.isEmpty())
/* 145 */       return null; 
/* 146 */     if (list.size() == 1)
/* 147 */       return list.get(0); 
/* 148 */     throw new RuntimeException("city.chnName repeat");
/*     */   }
/*     */   
/*     */   public City getCityByEngName(Province p, String engName) {
/* 152 */     List<City> list = getHibernateTemplate().find("from City city where city.province.id=? and city.engName=?", new Object[] { p.getId(), engName });
/* 153 */     if (list.isEmpty())
/* 154 */       return null; 
/* 155 */     if (list.size() == 1)
/* 156 */       return list.get(0); 
/* 157 */     throw new RuntimeException("city.engName repeat");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/CityDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */