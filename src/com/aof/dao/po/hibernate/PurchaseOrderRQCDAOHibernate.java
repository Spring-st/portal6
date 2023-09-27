/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.po.PurchaseOrderRQCDAO;
/*     */ import com.aof.model.po.PurchaseOrderRqc;
/*     */ import com.aof.model.po.query.PurchaseOrderRqcQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderRqcQueryOrder;
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
/*     */ public class PurchaseOrderRQCDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderRQCDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(PurchaseOrderRQCDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrderRqc rqc";
/*     */   
/*     */   private static final String SQL = "from PurchaseOrderRqc rqc";
/*     */ 
/*     */   
/*     */   public PurchaseOrderRqc getPurchaseOrderRqc(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get PurchaseOrderRqc with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (PurchaseOrderRqc)getHibernateTemplate().get(PurchaseOrderRqc.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderRqc updatePurchaseOrderRqc(PurchaseOrderRqc ct) {
/*  53 */     Integer id = ct.getId();
/*  54 */     if (id == null) {
/*  55 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  57 */     PurchaseOrderRqc oldPurchaseOrderRqc = getPurchaseOrderRqc(id);
/*  58 */     if (oldPurchaseOrderRqc != null) {
/*     */       try {
/*  60 */         PropertyUtils.copyProperties(oldPurchaseOrderRqc, ct);
/*  61 */       } catch (Exception e) {
/*  62 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  64 */       getHibernateTemplate().update(oldPurchaseOrderRqc);
/*  65 */       return oldPurchaseOrderRqc;
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
/*     */   public PurchaseOrderRqc insertPurchaseOrderRqc(PurchaseOrderRqc ct) {
/*  77 */     getHibernateTemplate().save(ct);
/*  78 */     return ct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  86 */       { PurchaseOrderRqcQueryCondition.ID_EQ, "rqc.id = ?"
/*  87 */       }, { PurchaseOrderRqcQueryCondition.ID_BEGINWITH, 
/*  88 */         "po.id like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  90 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/*  95 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  96 */       { PurchaseOrderRqcQueryOrder.ID, "rqc.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderRqcListCount(Map conditions) {
/* 105 */     return getListCount(conditions, "select count(*) from PurchaseOrderRqc rqc", QUERY_CONDITIONS);
/*     */   }
/*     */   public String getLastPoApplicationCode() {
/* 108 */     String result = getHibernateTemplate()
/* 109 */       .find(
/* 110 */         "select max(ta.id) from PurchaseOrderRqc po where rqc.id like 'CA%'")
/* 111 */       .get(0);
/* 112 */     return ((result == null) ? "CA000000" : (String.valueOf(result) + "000000"))
/* 113 */       .substring(0, 8);
/*     */   }
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 117 */     String sql = "select max(rqc.id) from PurchaseOrderRqc rqc";
/* 118 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 119 */     conds.put(PurchaseOrderRqcQueryCondition.ID_BEGINWITH, prefix);
/* 120 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, 
/* 121 */         QUERY_ORDERS);
/* 122 */     if (l.isEmpty()) {
/* 123 */       return null;
/*     */     }
/* 125 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderRqcList(Map conditions, int pageNo, int pageSize, PurchaseOrderRqcQueryOrder order, boolean descend) {
/* 135 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderRqc rqc", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderRqcList() {
/* 144 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 145 */     return getPurchaseOrderRqcList(conds, 0, -1, PurchaseOrderRqcQueryOrder.ID, false);
/*     */   }
/*     */   
/*     */   public void deletePurchaseOrderRqc(PurchaseOrderRqc po) {
/* 149 */     getHibernateTemplate().delete(po);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderRQCDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */