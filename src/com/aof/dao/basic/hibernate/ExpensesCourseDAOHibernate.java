/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.ExpensesCourseDAO;
/*     */ import com.aof.model.basic.ExpensesCourse;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryCondition;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryOrder;
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
/*     */ 
/*     */ public class ExpensesCourseDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ExpensesCourseDAO
/*     */ {
/*  32 */   private Log log = LogFactory.getLog(ExpensesCourseDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from ExpensesCourse expensesCourse";
/*     */   
/*     */   private static final String SQL = "from ExpensesCourse expensesCourse";
/*     */ 
/*     */   
/*     */   public ExpensesCourse getExpensesCourse(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get ExpensesCourse with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (ExpensesCourse)getHibernateTemplate().get(ExpensesCourse.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpensesCourse updateExpensesCourse(ExpensesCourse expensesCourse) {
/*  54 */     Integer id = expensesCourse.getId();
/*  55 */     if (id == null) {
/*  56 */       throw new RuntimeException("cannot save a expensesCourse with null id");
/*     */     }
/*  58 */     ExpensesCourse oldExpensesCourse = getExpensesCourse(id);
/*  59 */     if (oldExpensesCourse != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldExpensesCourse, expensesCourse);
/*  62 */       } catch (Exception e) {
/*  63 */         throw new RuntimeException("copy error:" + e);
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldExpensesCourse);
/*  66 */       return oldExpensesCourse;
/*     */     } 
/*  68 */     throw new RuntimeException("expensesCourse not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpensesCourse insertExpensesCourse(ExpensesCourse expensesCourse) {
/*  78 */     getHibernateTemplate().save(expensesCourse);
/*  79 */     return expensesCourse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  87 */       { ExpensesCourseQueryCondition.ID_EQ, "expensesCourse.id = ?" }
/*     */     };
/*     */   
/*  90 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  91 */       { ExpensesCourseQueryOrder.ID, "expensesCourse.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpensesCourseListCount(Map conditions) {
/* 100 */     return getListCount(conditions, "select count(*) from ExpensesCourse expensesCourse", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExpensesCourseList(Map conditions, int pageNo, int pageSize, ExpensesCourseQueryOrder order, boolean descend) {
/* 110 */     return getList(conditions, pageNo, pageSize, order, descend, "from ExpensesCourse expensesCourse", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledExpensesCourseList() {
/* 119 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 120 */     conds.put(ExpensesCourseQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 121 */     return getExpensesCourseList(conds, 0, -1, ExpensesCourseQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteExpensesCourse(ExpensesCourse expensesCourse) {
/* 126 */     getHibernateTemplate().delete(expensesCourse);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/ExpensesCourseDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */