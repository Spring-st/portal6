/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.SupplierHistoryDAO;
/*     */ import com.aof.model.admin.BaseSupplier;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierHistory;
/*     */ import com.aof.model.admin.query.SupplierHistoryQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierHistoryQueryOrder;
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
/*     */ public class SupplierHistoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SupplierHistoryDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(SupplierHistoryDAOHibernate.class);
/*     */   private static final String SQL_COUNT = "select count(*) from SupplierHistory supplierHistory";
/*     */   
/*     */   public SupplierHistory getSupplierHistory(Integer id) {
/*  33 */     if (id == null) {
/*  34 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SupplierHistory with null id"); 
/*  35 */       return null;
/*     */     } 
/*  37 */     return (SupplierHistory)getHibernateTemplate().get(SupplierHistory.class, id);
/*     */   }
/*     */   private static final String SQL = "from SupplierHistory supplierHistory";
/*     */   public SupplierHistory updateSupplierHistory(SupplierHistory supplierHistory) {
/*  41 */     Integer id = supplierHistory.getId();
/*  42 */     if (id == null) {
/*  43 */       throw new RuntimeException("cannot save a supplierHistory with null id");
/*     */     }
/*  45 */     SupplierHistory oldSupplierHistory = getSupplierHistory(id);
/*  46 */     if (oldSupplierHistory != null) {
/*     */       try {
/*  48 */         PropertyUtils.copyProperties(oldSupplierHistory, supplierHistory);
/*     */       }
/*  50 */       catch (Exception e) {
/*     */         
/*  52 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  54 */       getHibernateTemplate().update(oldSupplierHistory);
/*  55 */       return oldSupplierHistory;
/*     */     } 
/*     */ 
/*     */     
/*  59 */     throw new RuntimeException("supplierHistory not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierHistory insertSupplierHistory(SupplierHistory supplierHistory) {
/*  64 */     getHibernateTemplate().save(supplierHistory);
/*  65 */     return supplierHistory;
/*     */   }
/*     */   
/*     */   public void copySupplier(Supplier supplier) {
/*  69 */     Integer id = supplier.getId();
/*  70 */     if (id == null) {
/*  71 */       throw new RuntimeException("cannot save a supplierHistory with null id");
/*     */     }
/*  73 */     SupplierHistory oldSupplierHistory = getSupplierHistory(id);
/*  74 */     if (oldSupplierHistory != null) {
/*  75 */       oldSupplierHistory.copySupplier((BaseSupplier)supplier);
/*  76 */       getHibernateTemplate().update(oldSupplierHistory);
/*     */     } else {
/*  78 */       SupplierHistory newSupplierHistory = new SupplierHistory();
/*  79 */       newSupplierHistory.copySupplier((BaseSupplier)supplier);
/*  80 */       newSupplierHistory.setSupplier(supplier);
/*  81 */       getHibernateTemplate().save(newSupplierHistory);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  91 */         SupplierHistoryQueryCondition.ID_EQ, "supplierHistory.id = ?"
/*     */       }
/*     */     };
/*  94 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/*  96 */         SupplierHistoryQueryOrder.ID, "supplierHistory.id" }
/*     */     };
/*     */   
/*     */   public int getSupplierHistoryListCount(Map conditions) {
/* 100 */     return getListCount(conditions, "select count(*) from SupplierHistory supplierHistory", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getSupplierHistoryList(Map conditions, int pageNo, int pageSize, SupplierHistoryQueryOrder order, boolean descend) {
/* 104 */     return getList(conditions, pageNo, pageSize, order, descend, "from SupplierHistory supplierHistory", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SupplierHistoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */