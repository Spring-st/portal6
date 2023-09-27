/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.ProvinceDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.query.ProvinceQueryCondition;
/*     */ import com.aof.model.admin.query.ProvinceQueryOrder;
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
/*     */ public class ProvinceDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProvinceDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(ProvinceDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Province province";
/*     */   
/*     */   private static final String SQL = "from Province province";
/*     */ 
/*     */   
/*     */   public Province getProvince(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get Province with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (Province)getHibernateTemplate().get(Province.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Province updateProvince(Province province) {
/*  55 */     Integer id = province.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a province with null id");
/*     */     }
/*  59 */     Province oldProvince = getProvince(id);
/*  60 */     if (oldProvince != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldProvince, province);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldProvince);
/*  67 */       return oldProvince;
/*     */     } 
/*  69 */     throw new RuntimeException("province not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Province insertProvince(Province province) {
/*  79 */     getHibernateTemplate().save(province);
/*  80 */     return province;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  89 */         ProvinceQueryCondition.ID_EQ, "province.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  96 */         ProvinceQueryCondition.COUNTRY_ID_EQ, "province.country.id = ?"
/*     */       
/*     */       },
/*  99 */       { ProvinceQueryCondition.ENGNAME_LIKE, "province.engName like ?", new LikeConvert()
/* 100 */       }, { ProvinceQueryCondition.CHNNAME_LIKE, "province.chnName like ?", new LikeConvert()
/* 101 */       }, { ProvinceQueryCondition.ENABLED_EQ, "province.enabled = ?" }
/*     */     };
/* 103 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 105 */         ProvinceQueryOrder.ID, "province.id"
/*     */       
/*     */       },
/* 108 */       { ProvinceQueryOrder.ENGNAME, "province.engName" }, { ProvinceQueryOrder.CHNNAME, "province.chnName" }, { ProvinceQueryOrder.ENABLED, "province.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProvinceListCount(Map conditions) {
/* 116 */     return getListCount(conditions, "select count(*) from Province province", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getProvinceList(Map conditions, int pageNo, int pageSize, ProvinceQueryOrder order, boolean descend) {
/* 120 */     return getList(conditions, pageNo, pageSize, order, descend, "from Province province", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledProvinceList() {
/* 129 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 130 */     conds.put(ProvinceQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 131 */     return getProvinceList(conds, 0, -1, ProvinceQueryOrder.ENGNAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledProvinceList(Country c) {
/* 140 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 141 */     conds.put(ProvinceQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 142 */     conds.put(ProvinceQueryCondition.COUNTRY_ID_EQ, c.getId());
/* 143 */     return getProvinceList(conds, 0, -1, ProvinceQueryOrder.ENGNAME, false);
/*     */   }
/*     */   
/*     */   public void deleteProvince(Province p) {
/* 147 */     getHibernateTemplate().delete(p);
/*     */   }
/*     */ 
/*     */   
/*     */   public Province getProvinceByChnName(Country country, String chnName) {
/* 152 */     List<Province> list = getHibernateTemplate().find("from Province p where p.country.id=? and p.chnName=?", new Object[] { country.getId(), chnName });
/* 153 */     if (list.isEmpty())
/* 154 */       return null; 
/* 155 */     if (list.size() == 1)
/* 156 */       return list.get(0); 
/* 157 */     throw new RuntimeException("province.chnName repeat");
/*     */   }
/*     */   
/*     */   public Province getProvinceByEngName(Country country, String engName) {
/* 161 */     List<Province> list = getHibernateTemplate().find("from Province p where p.country.id=? and p.engName=?", new Object[] { country.getId(), engName });
/* 162 */     if (list.isEmpty())
/* 163 */       return null; 
/* 164 */     if (list.size() == 1)
/* 165 */       return list.get(0); 
/* 166 */     throw new RuntimeException("province.engName repeat");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/ProvinceDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */