/*     */ package com.aof.dao.po.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.po.PurchaseOrderCondimentSingleDAO;
/*     */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*     */ import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryOrder;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
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
/*     */ public class PurchaseOrderCondimentSingleDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseOrderCondimentSingleDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(PurchaseOrderCondimentSingleDAOHibernate.class);
/*     */   
/*     */   public PurchaseOrderCondimentSingle getPurchaseOrderCondimentSingle(Integer id) {
/*  32 */     if (id == null) {
/*  33 */       if (this.log.isDebugEnabled())
/*  34 */         this.log.debug("try to get PurchaseOrderCondimentSingle with null id"); 
/*  35 */       return null;
/*     */     } 
/*  37 */     return (PurchaseOrderCondimentSingle)getHibernateTemplate().get(PurchaseOrderCondimentSingle.class, id);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseOrderCondimentSingle pocs";
/*     */   private static final String SQL = "from PurchaseOrderCondimentSingle pocs";
/*     */   
/*  44 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  45 */       { PurchaseOrderCondimentSingleQueryCondition.ID_EQ, "pocs.id = ?"
/*  46 */       }, { PurchaseOrderCondimentSingleQueryCondition.ENABLED_EQ, "pocs.status = ?"
/*  47 */       }, { PurchaseOrderCondimentSingleQueryCondition.ID_BEGINWITH, 
/*  48 */         "pocs.code like ?", new QueryParameterConvert() {
/*     */           public Object convert(Object src) {
/*  50 */             return src + "%";
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/*  55 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  56 */       { PurchaseOrderQueryOrder.ID, "pocs.id" }
/*     */     };
/*     */   
/*     */   public int getPurchaseOrderCondimentSingleListCount(Map conditions) {
/*  60 */     return getListCount(conditions, "select count(*) from PurchaseOrderCondimentSingle pocs", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderCondimentSingleList(Map conditions, int pageNo, int pageSize, PurchaseOrderCondimentSingleQueryOrder order, boolean descend) {
/*  67 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseOrderCondimentSingle pocs", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public PurchaseOrderCondimentSingle updatePurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle box) {
/*  71 */     Integer id = box.getId();
/*  72 */     if (id == null) {
/*  73 */       throw new RuntimeException("cannot save a ct with null id");
/*     */     }
/*  75 */     PurchaseOrderCondimentSingle oldPurchaseOrderCondimentSingle = getPurchaseOrderCondimentSingle(id);
/*  76 */     if (oldPurchaseOrderCondimentSingle != null) {
/*     */       try {
/*  78 */         PropertyUtils.copyProperties(oldPurchaseOrderCondimentSingle, box);
/*  79 */       } catch (Exception e) {
/*  80 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  82 */       getHibernateTemplate().update(oldPurchaseOrderCondimentSingle);
/*  83 */       return oldPurchaseOrderCondimentSingle;
/*     */     } 
/*  85 */     throw new RuntimeException("PurchaseOrderCondimentSingle not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle single) {
/*  91 */     getHibernateTemplate().save(single);
/*  92 */     return single;
/*     */   }
/*     */   
/*     */   public String getMaxPoReceiptsBeginWith(String prefix) {
/*  96 */     String sql = "select max(pocs.code) from PurchaseOrderCondimentSingle pocs";
/*  97 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  98 */     conds.put(PurchaseOrderCondimentSingleQueryCondition.ID_BEGINWITH, prefix);
/*  99 */     List<String> l = getList(conds, 0, -1, null, false, sql, QUERY_CONDITIONS, QUERY_ORDERS);
/*     */     
/* 101 */     if (l.isEmpty()) {
/* 102 */       return null;
/*     */     }
/* 104 */     return l.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseOrderCondimentSingleDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */