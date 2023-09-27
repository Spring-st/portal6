/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.FPSMaterialPriceDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.basic.FPSMaterialPrice;
/*     */ import com.aof.model.basic.query.FPSMaterialPriceQueryCondition;
/*     */ import com.aof.model.basic.query.FPSMaterialPriceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
/*     */ 
/*     */ public class FPSMaterialPriceDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements FPSMaterialPriceDAO
/*     */ {
/*  28 */   private Log log = LogFactory.getLog(FPSMaterialPriceDAOHibernate.class);
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPrice(Integer id) {
/*  31 */     if (id == null) {
/*  32 */       if (this.log.isDebugEnabled())
/*  33 */         this.log.debug("try to get FPSMaterialPrice with null id"); 
/*  34 */       return null;
/*     */     } 
/*  36 */     return (FPSMaterialPrice)getHibernateTemplate().get(
/*  37 */         FPSMaterialPrice.class, id);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from FPSMaterialPrice fpsc";
/*     */   
/*     */   private static final String SQL = "from FPSMaterialPrice fpsc";
/*     */   
/*  45 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  46 */       { FPSMaterialPriceQueryCondition.ID_EQ, "fpsc.wmsPart.id = ?"
/*  47 */       }, { FPSMaterialPriceQueryCondition.CODE_LIKE, "fpsc.code like ?", new LikeConvert()
/*  48 */       }, { FPSMaterialPriceQueryCondition.IS_ENABLED_EQ, "fpsc.enabled = ?"
/*  49 */       }, { FPSMaterialPriceQueryCondition.DESCRIBE1_EQ, "fpsc.description1 = ?"
/*  50 */       }, { FPSMaterialPriceQueryCondition.DESCRIBE2_EQ, "fpsc.description2 like ?", new LikeConvert() }
/*     */     };
/*     */ 
/*     */   
/*  54 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  55 */       { FPSMaterialPriceQueryOrder.ID, "fpsc.id" }
/*     */     };
/*     */ 
/*     */   
/*     */   public int getFPSMaterialPriceListCount(Map conditions) {
/*  60 */     return getListCount(conditions, "select count(*) from FPSMaterialPrice fpsc", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFPSMaterialPriceList(Map conditions, int pageNo, int pageSize, FPSMaterialPriceQueryOrder order, boolean descend) {
/*  66 */     return getList(conditions, pageNo, pageSize, order, descend, "from FPSMaterialPrice fpsc", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public FPSMaterialPrice insertFPSMaterialPrice(FPSMaterialPrice fpsDestination) {
/*  70 */     getHibernateTemplate().save(fpsDestination);
/*  71 */     return fpsDestination;
/*     */   }
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice updateFPSMaterialPrice(FPSMaterialPrice fpsDestination) {
/*  76 */     Integer id = fpsDestination.getId();
/*  77 */     if (id == null) {
/*  78 */       throw new RuntimeException("cannot save a fpsDestination with null id");
/*     */     }
/*  80 */     FPSMaterialPrice oldFPSMaterialPrice = getFPSMaterialPrice(id);
/*  81 */     if (oldFPSMaterialPrice != null) {
/*     */       try {
/*  83 */         PropertyUtils.copyProperties(oldFPSMaterialPrice, fpsDestination);
/*  84 */       } catch (Exception e) {
/*  85 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  87 */       getHibernateTemplate().update(oldFPSMaterialPrice);
/*  88 */       return oldFPSMaterialPrice;
/*     */     } 
/*  90 */     throw new RuntimeException("fpsDestination not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledFPSMaterialPriceList() {
/*  95 */     return getFPSMaterialPriceList((Map)null, 0, -1, FPSMaterialPriceQueryOrder.ID, 
/*  96 */         false);
/*     */   }
/*     */   
/*     */   public void deleteFPSMaterialPrice(FPSMaterialPrice fpsDestination) {
/* 100 */     getHibernateTemplate().delete(fpsDestination);
/*     */   }
/*     */   
/*     */   public void updateFPSMaterialPriceAllEanble() {
/* 104 */     String hql = "from FPSMaterialPrice price where price.enabled = 0";
/* 105 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */           {
/*     */             try {
/* 110 */               Query query = session.createQuery("from FPSMaterialPrice price where price.enabled = 0");
/* 111 */               List<FPSMaterialPrice> list = query.list();
/* 112 */               for (FPSMaterialPrice price : list) {
/* 113 */                 price.setEnabled(EnabledDisabled.DISABLED);
/*     */               }
/* 115 */             } catch (Exception e) {
/* 116 */               e.getStackTrace();
/*     */             } finally {
/* 118 */               if (session != null && session.isOpen()) {
/* 119 */                 session.close();
/*     */               }
/*     */             } 
/*     */             
/* 123 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPriceByPart(String partId) {
/* 129 */     String sql = "from FPSMaterialPrice price where price.wmsPart.id = ? and price.enabled = 0";
/* 130 */     List<FPSMaterialPrice> list = getHibernateTemplate().find(sql, partId, (Type)Hibernate.STRING);
/* 131 */     if (list == null || list.isEmpty()) {
/* 132 */       return null;
/*     */     }
/* 134 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public BigDecimal getFPSMaterialPriceBydescribe2(String describe2) {
/* 138 */     String sql = "from FPSMaterialPrice price where price.wmsPart.describe2 = ? and price.enabled = 0";
/* 139 */     List<FPSMaterialPrice> list = getHibernateTemplate().find(sql, describe2, (Type)Hibernate.STRING);
/* 140 */     if (list == null || list.isEmpty()) {
/* 141 */       return null;
/*     */     }
/* 143 */     if (list.size() > 1) {
/* 144 */       return new BigDecimal(0);
/*     */     }
/* 146 */     return ((FPSMaterialPrice)list.get(0)).getUnitPrice();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPriceByPartAndSupp(String part, Integer id) {
/* 152 */     String sql = "from FPSMaterialPrice fmp where fmp.wmsPart.id=? and fmp.supplier.id=?";
/* 153 */     Object[] params = { part, id };
/* 154 */     List<FPSMaterialPrice> list = getHibernateTemplate().find(sql, params);
/* 155 */     if (list == null || list.isEmpty()) {
/* 156 */       return null;
/*     */     }
/* 158 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/FPSMaterialPriceDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */