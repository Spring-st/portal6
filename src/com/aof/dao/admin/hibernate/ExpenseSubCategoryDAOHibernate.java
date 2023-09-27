/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.ExpenseSubCategoryDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
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
/*     */ public class ExpenseSubCategoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ExpenseSubCategoryDAO
/*     */ {
/*  29 */   private Log log = LogFactory.getLog(ExpenseSubCategoryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ExpenseSubCategory expenseSubCategory";
/*     */   
/*     */   private static final String SQL = "from ExpenseSubCategory expenseSubCategory";
/*     */   
/*     */   public ExpenseSubCategory getExpenseSubCategory(Integer id) {
/*  36 */     if (id == null) {
/*  37 */       if (this.log.isDebugEnabled()) this.log.debug("try to get ExpenseSubCategory with null id"); 
/*  38 */       return null;
/*     */     } 
/*  40 */     return (ExpenseSubCategory)getHibernateTemplate().get(ExpenseSubCategory.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseSubCategory updateExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
/*  47 */     Integer id = expenseSubCategory.getId();
/*  48 */     if (id == null) {
/*  49 */       throw new RuntimeException("cannot save a expenseSubCategory with null id");
/*     */     }
/*  51 */     ExpenseSubCategory oldExpenseSubCategory = getExpenseSubCategory(id);
/*  52 */     if (oldExpenseSubCategory != null) {
/*     */       try {
/*  54 */         PropertyUtils.copyProperties(oldExpenseSubCategory, expenseSubCategory);
/*     */       }
/*  56 */       catch (Exception e) {
/*     */         
/*  58 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  60 */       getHibernateTemplate().update(oldExpenseSubCategory);
/*  61 */       return oldExpenseSubCategory;
/*     */     } 
/*     */ 
/*     */     
/*  65 */     throw new RuntimeException("expenseSubCategory not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseSubCategory insertExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
/*  73 */     getHibernateTemplate().save(expenseSubCategory);
/*  74 */     return expenseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  83 */         ExpenseSubCategoryQueryCondition.ID_EQ, "expenseSubCategory.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  90 */         ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_ID_EQ, "expenseSubCategory.expenseCategory.id = ?"
/*  91 */       }, { ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_SITE_ID_EQ, "expenseSubCategory.expenseCategory.site.id = ?"
/*     */       },
/*     */       {
/*  94 */         ExpenseSubCategoryQueryCondition.DESCRIPTION_LIKE, "expenseSubCategory.description like ?", new LikeConvert()
/*  95 */       }, { ExpenseSubCategoryQueryCondition.REFERENCEERPID_LIKE, "expenseSubCategory.referenceErpId like ?", new LikeConvert()
/*  96 */       }, { ExpenseSubCategoryQueryCondition.ENABLED_EQ, "expenseSubCategory.enabled = ?"
/*  97 */       }, { ExpenseSubCategoryQueryCondition.ISHOTEL_EQ, "expenseSubCategory.isHotel = ?" }
/*     */     };
/*     */   
/* 100 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 102 */         ExpenseSubCategoryQueryOrder.ID, "expenseSubCategory.id"
/*     */       
/*     */       },
/* 105 */       { ExpenseSubCategoryQueryOrder.DESCRIPTION, "expenseSubCategory.description"
/* 106 */       }, { ExpenseSubCategoryQueryOrder.REFERENCEERPID, "expenseSubCategory.referenceErpId"
/* 107 */       }, { ExpenseSubCategoryQueryOrder.ENABLED, "expenseSubCategory.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpenseSubCategoryListCount(Map conditions) {
/* 114 */     return getListCount(conditions, "select count(*) from ExpenseSubCategory expenseSubCategory", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExpenseSubCategoryList(Map conditions, int pageNo, int pageSize, ExpenseSubCategoryQueryOrder order, boolean descend) {
/* 121 */     return getList(conditions, pageNo, pageSize, order, descend, "from ExpenseSubCategory expenseSubCategory", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/ExpenseSubCategoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */