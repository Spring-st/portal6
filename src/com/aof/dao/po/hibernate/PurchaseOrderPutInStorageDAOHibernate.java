/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.po.PurchaseOrderPutInStorageDAO;
/*     */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*     */ import com.aof.model.po.query.PurchaseOrderPutInStorageQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderPutInStorageQueryOrder;
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
/*     */ public class PurchaseOrderPutInStorageDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderPutInStorageDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(PurchaseOrderPutInStorageDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrderPutInStorage pop";
/*     */   
/*     */   private static final String SQL = "from PurchaseOrderPutInStorage pop";
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage getPurchaseOrderPutInStorage(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get PurchaseOrderPutInStorage with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (PurchaseOrderPutInStorage)getHibernateTemplate().get(PurchaseOrderPutInStorage.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage updatePurchaseOrderPutInStorage(PurchaseOrderPutInStorage ct) {
/*  53 */     Integer id = ct.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  57 */     PurchaseOrderPutInStorage oldPurchaseOrderPutInStorage = getPurchaseOrderPutInStorage(id);
/*  58 */     if (oldPurchaseOrderPutInStorage != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldPurchaseOrderPutInStorage, ct);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldPurchaseOrderPutInStorage);
/*  65 */       return oldPurchaseOrderPutInStorage;
/*     */     } 
/*  67 */     throw new RuntimeException("ct not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage insertPurchaseOrderPutInStorage(PurchaseOrderPutInStorage ct) {
/*  77 */     getHibernateTemplate().save(ct);
/*  78 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  86 */       { PurchaseOrderPutInStorageQueryCondition.ID_EQ, "pop.id = ?"
/*     */       },
/*  88 */       { PurchaseOrderPutInStorageQueryCondition.ID_BEGINWITH, 
/*  89 */         "pop.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  91 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/*  96 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  97 */       { PurchaseOrderPutInStorageQueryOrder.ID, "pop.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderPutInStorageListCount(Map conditions) {
/* 106 */     return getListCount(conditions, "select count(*) from PurchaseOrderPutInStorage pop", QUERY_CONDITIONS);
/*     */   }
/*     */   public String getLastPoApplicationCode() {
/* 109 */     String result = getHibernateTemplate().find("select max(ta.id) from PurchaseOrderPutInStorage pop where pop.id like 'CA%'").get(0);
/* 110 */     return ((result == null) ? "CA000000" : (String.valueOf(result) + "000000")).substring(0, 8);
/*     */   }
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 114 */     String sql = "select max(pop.id) from PurchaseOrderPutInStorage pop";
/* 115 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 116 */     conds.put(PurchaseOrderPutInStorageQueryCondition.ID_BEGINWITH, prefix);
/* 117 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/* 118 */     if (l.isEmpty()) {
/* 119 */       return null;
/*     */     }
/* 121 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderPutInStorageList(Map conditions, int pageNo, int pageSize, PurchaseOrderPutInStorageQueryOrder order, boolean descend) {
/* 131 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderPutInStorage pop", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderPutInStorageList() {
/* 140 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 141 */     return getPurchaseOrderPutInStorageList(conds, 0, -1, PurchaseOrderPutInStorageQueryOrder.ID, false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderPutInStorageDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */