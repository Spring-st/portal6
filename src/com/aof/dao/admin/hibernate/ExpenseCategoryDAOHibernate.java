/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.ExpenseCategoryDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
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
/*     */ public class ExpenseCategoryDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ExpenseCategoryDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(ExpenseCategoryDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ExpenseCategory expenseCategory";
/*     */   private static final String SQL = "from ExpenseCategory expenseCategory";
/*     */   
/*     */   public ExpenseCategory getExpenseCategory(Integer id) {
/*  36 */     if (id == null) {
/*  37 */       if (this.log.isDebugEnabled())
/*  38 */         this.log.debug("try to get ExpenseCategory with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (ExpenseCategory)getHibernateTemplate().get(ExpenseCategory.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory) {
/*  48 */     Integer id = expenseCategory.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a expenseCategory with null id");
/*     */     }
/*  52 */     ExpenseCategory oldExpenseCategory = getExpenseCategory(id);
/*  53 */     if (oldExpenseCategory != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldExpenseCategory, expenseCategory);
/*  56 */       } catch (Exception e) {
/*  57 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  59 */       getHibernateTemplate().update(oldExpenseCategory);
/*  60 */       return oldExpenseCategory;
/*     */     } 
/*  62 */     throw new RuntimeException("expenseCategory not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory insertExpenseCategory(ExpenseCategory expenseCategory) {
/*  70 */     getHibernateTemplate().save(expenseCategory);
/*  71 */     return expenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  80 */         ExpenseCategoryQueryCondition.ID_EQ, "expenseCategory.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  87 */         ExpenseCategoryQueryCondition.SITE_ID_EQ, "expenseCategory.site.id = ?"
/*     */       
/*     */       },
/*  90 */       { ExpenseCategoryQueryCondition.DESCRIPTION_LIKE, "expenseCategory.description like ?", new LikeConvert()
/*  91 */       }, { ExpenseCategoryQueryCondition.TYPE_EQ, "expenseCategory.type = ?"
/*  92 */       }, { ExpenseCategoryQueryCondition.TYPE_NEQ, "expenseCategory.type <> ?"
/*  93 */       }, { ExpenseCategoryQueryCondition.REFERENCEERPID_LIKE, "expenseCategory.referenceErpId like ?", new LikeConvert()
/*  94 */       }, { ExpenseCategoryQueryCondition.ENABLED_EQ, "expenseCategory.enabled = ?" }
/*     */     };
/*     */ 
/*     */   
/*  98 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 100 */         ExpenseCategoryQueryOrder.ID, "expenseCategory.id"
/*     */       
/*     */       },
/* 103 */       { ExpenseCategoryQueryOrder.DESCRIPTION, "expenseCategory.description" }, { ExpenseCategoryQueryOrder.TYPE, "expenseCategory.type"
/* 104 */       }, { ExpenseCategoryQueryOrder.REFERENCEERPID, "expenseCategory.referenceErpId" }, { ExpenseCategoryQueryOrder.ENABLED, "expenseCategory.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpenseCategoryListCount(Map conditions) {
/* 110 */     return getListCount(conditions, "select count(*) from ExpenseCategory expenseCategory", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExpenseCategoryList(Map conditions, int pageNo, int pageSize, ExpenseCategoryQueryOrder order, boolean descend) {
/* 117 */     return getList(conditions, pageNo, pageSize, order, descend, "from ExpenseCategory expenseCategory", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/ExpenseCategoryDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */