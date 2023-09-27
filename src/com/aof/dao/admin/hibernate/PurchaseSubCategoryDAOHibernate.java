/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.PurchaseSubCategoryDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
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
/*     */ public class PurchaseSubCategoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements PurchaseSubCategoryDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(PurchaseSubCategoryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from PurchaseSubCategory purchaseSubCategory";
/*     */   
/*     */   private static final String SQL = "from PurchaseSubCategory purchaseSubCategory";
/*     */   
/*     */   public PurchaseSubCategory getPurchaseSubCategory(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled()) this.log.debug("try to get PurchaseSubCategory with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (PurchaseSubCategory)getHibernateTemplate().get(PurchaseSubCategory.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseSubCategory updatePurchaseSubCategory(PurchaseSubCategory purchaseSubCategory) {
/*  48 */     Integer id = purchaseSubCategory.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a purchaseSubCategory with null id");
/*     */     }
/*  52 */     PurchaseSubCategory oldPurchaseSubCategory = getPurchaseSubCategory(id);
/*  53 */     if (oldPurchaseSubCategory != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldPurchaseSubCategory, purchaseSubCategory);
/*     */       }
/*  57 */       catch (Exception e) {
/*     */         
/*  59 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldPurchaseSubCategory);
/*  62 */       return oldPurchaseSubCategory;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     throw new RuntimeException("purchaseSubCategory not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseSubCategory insertPurchaseSubCategory(PurchaseSubCategory purchaseSubCategory) {
/*  74 */     getHibernateTemplate().save(purchaseSubCategory);
/*  75 */     return purchaseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  84 */         PurchaseSubCategoryQueryCondition.ID_EQ, "purchaseSubCategory.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  91 */         PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_SITE_ID_EQ, "purchaseSubCategory.purchaseCategory.site.id = ?"
/*  92 */       }, { PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_SITE_ID_EQ_OR_NULL, "purchaseSubCategory.purchaseCategory.site.id = ? or purchaseSubCategory.purchaseCategory.site.id is null"
/*  93 */       }, { PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_ID_EQ, "purchaseSubCategory.purchaseCategory.id = ?"
/*  94 */       }, { PurchaseSubCategoryQueryCondition.DEFAULTSUPPLIER_ID_EQ, "purchaseSubCategory.defaultSupplier.id = ?"
/*     */       },
/*     */       {
/*  97 */         PurchaseSubCategoryQueryCondition.DESCRIPTION_LIKE, "purchaseSubCategory.description like ?", new LikeConvert()
/*  98 */       }, { PurchaseSubCategoryQueryCondition.ENABLED_EQ, "purchaseSubCategory.enabled = ?" }
/*     */     };
/*     */   
/* 101 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 103 */         PurchaseSubCategoryQueryOrder.ID, "purchaseSubCategory.id"
/*     */       
/*     */       },
/* 106 */       { PurchaseSubCategoryQueryOrder.SUPPLIER, "purchaseSubCategory.defaultSupplier.name"
/* 107 */       }, { PurchaseSubCategoryQueryOrder.DESCRIPTION, "purchaseSubCategory.description"
/* 108 */       }, { PurchaseSubCategoryQueryOrder.ENABLED, "purchaseSubCategory.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPurchaseSubCategoryListCount(Map conditions) {
/* 115 */     return getListCount(conditions, "select count(*) from PurchaseSubCategory purchaseSubCategory", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseSubCategoryList(Map conditions, int pageNo, int pageSize, PurchaseSubCategoryQueryOrder order, boolean descend) {
/* 122 */     return getList(conditions, pageNo, pageSize, order, descend, "from PurchaseSubCategory purchaseSubCategory", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/PurchaseSubCategoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */