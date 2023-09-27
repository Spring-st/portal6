/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.PurchaseCategoryDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
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
/*     */ public class PurchaseCategoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseCategoryDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(PurchaseCategoryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseCategory purchaseCategory";
/*     */   
/*     */   private static final String SQL = "from PurchaseCategory purchaseCategory";
/*     */   
/*     */   public PurchaseCategory getPurchaseCategory(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled()) this.log.debug("try to get PurchaseCategory with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (PurchaseCategory)getHibernateTemplate().get(PurchaseCategory.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory updatePurchaseCategory(PurchaseCategory purchaseCategory) {
/*  48 */     Integer id = purchaseCategory.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a purchaseCategory with null id");
/*     */     }
/*  52 */     PurchaseCategory oldPurchaseCategory = getPurchaseCategory(id);
/*  53 */     if (oldPurchaseCategory != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldPurchaseCategory, purchaseCategory);
/*     */       }
/*  57 */       catch (Exception e) {
/*     */         
/*  59 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldPurchaseCategory);
/*  62 */       return oldPurchaseCategory;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     throw new RuntimeException("purchaseCategory not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseCategory insertPurchaseCategory(PurchaseCategory purchaseCategory) {
/*  74 */     getHibernateTemplate().save(purchaseCategory);
/*  75 */     return purchaseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  83 */       { PurchaseCategoryQueryCondition.ID_EQ, "purchaseCategory.id = ?"
/*  84 */       }, { PurchaseCategoryQueryCondition.GLOBAL, "purchaseCategory.site.id is null"
/*  85 */       }, { PurchaseCategoryQueryCondition.SITE_ID_EQ, "purchaseCategory.site.id = ?"
/*  86 */       }, { PurchaseCategoryQueryCondition.GLOBAL_OR_SITE_ID_EQ, "purchaseCategory.site.id is null or purchaseCategory.site.id = ?"
/*  87 */       }, { PurchaseCategoryQueryCondition.DESCRIPTION_LIKE, "purchaseCategory.description like ?", new LikeConvert()
/*  88 */       }, { PurchaseCategoryQueryCondition.ENABLED_EQ, "purchaseCategory.enabled = ?" }
/*     */     };
/*     */   
/*  91 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/*  93 */         PurchaseCategoryQueryOrder.ID, "purchaseCategory.id"
/*     */       
/*     */       },
/*  96 */       { PurchaseCategoryQueryOrder.DESCRIPTION, "purchaseCategory.description"
/*  97 */       }, { PurchaseCategoryQueryOrder.ENABLED, "purchaseCategory.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseCategoryListCount(Map conditions) {
/* 104 */     return getListCount(conditions, "select count(*) from PurchaseCategory purchaseCategory", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseCategoryList(Map conditions, int pageNo, int pageSize, PurchaseCategoryQueryOrder order, boolean descend) {
/* 111 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseCategory purchaseCategory", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/PurchaseCategoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */