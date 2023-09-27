/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.ExpenseCategoryDAO;
/*     */ import com.aof.dao.admin.ExpenseSubCategoryDAO;
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseType;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class ExpenseCategoryManagerImpl
/*     */   extends BaseManager
/*     */   implements ExpenseCategoryManager
/*     */ {
/*     */   private ExpenseCategoryDAO dao;
/*     */   private ExpenseSubCategoryDAO expenseSubCategoryDAO;
/*     */   
/*     */   public void setExpenseCategoryDAO(ExpenseCategoryDAO dao) {
/*  43 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseSubCategoryDAO(ExpenseSubCategoryDAO expenseSubCategoryDAO) {
/*  50 */     this.expenseSubCategoryDAO = expenseSubCategoryDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory getExpenseCategory(Integer id) {
/*  57 */     return this.dao.getExpenseCategory(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory updateExpenseCategory(ExpenseCategory function) {
/*  64 */     return this.dao.updateExpenseCategory(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory insertExpenseCategory(ExpenseCategory function) {
/*  71 */     return this.dao.insertExpenseCategory(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpenseCategoryListCount(Map conditions) {
/*  78 */     return this.dao.getExpenseCategoryListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getExpenseCategoryList(Map conditions, int pageNo, int pageSize, ExpenseCategoryQueryOrder order, boolean descend) {
/*  85 */     return this.dao.getExpenseCategoryList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory getEnabledTravelExpenseCategoryOfSite(int site_id) {
/*  92 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  93 */     conds.put(ExpenseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/*  94 */     conds.put(ExpenseCategoryQueryCondition.SITE_ID_EQ, new Integer(site_id));
/*  95 */     conds.put(ExpenseCategoryQueryCondition.TYPE_EQ, ExpenseType.TRAVEL.getEnumCode());
/*     */     
/*  97 */     List<ExpenseCategory> l = getExpenseCategoryList(conds, 0, -1, ExpenseCategoryQueryOrder.ID, false);
/*  98 */     if (l.isEmpty()) {
/*  99 */       return null;
/*     */     }
/* 101 */     return l.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledNotTravelExpenseCategoryListOfSite(int site_id) {
/* 106 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 107 */     conds.put(ExpenseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 108 */     conds.put(ExpenseCategoryQueryCondition.SITE_ID_EQ, new Integer(site_id));
/* 109 */     conds.put(ExpenseCategoryQueryCondition.TYPE_NEQ, ExpenseType.TRAVEL.getEnumCode());
/*     */     
/* 111 */     return getExpenseCategoryList(conds, 0, -1, ExpenseCategoryQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledExpenseCategorySubCategoryOfSite(Site site) {
/* 118 */     List expenseCategoryList = getEnabledExpenseCategoryOfSite(site);
/* 119 */     for (Iterator<ExpenseCategory> itor = expenseCategoryList.iterator(); itor.hasNext();) {
/* 120 */       ((ExpenseCategory)itor.next()).setEnabledExpenseSubCategoryList(new ArrayList());
/*     */     }
/*     */     
/* 123 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 124 */     conds.put(ExpenseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 125 */     conds.put(ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_SITE_ID_EQ, site.getId());
/* 126 */     for (Iterator<ExpenseSubCategory> iterator = this.expenseSubCategoryDAO.getExpenseSubCategoryList(conds, 0, -1, ExpenseSubCategoryQueryOrder.DESCRIPTION, false).iterator(); iterator.hasNext(); ) {
/* 127 */       ExpenseSubCategory esc = iterator.next();
/* 128 */       esc.getExpenseCategory().addEnabledExpenseSubCategory(esc);
/*     */     } 
/* 130 */     return expenseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledExpenseCategoryOfSite(Site site) {
/* 137 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 138 */     conds.put(ExpenseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 139 */     conds.put(ExpenseCategoryQueryCondition.SITE_ID_EQ, site.getId());
/* 140 */     return getExpenseCategoryList(conds, 0, -1, ExpenseCategoryQueryOrder.DESCRIPTION, false);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ExpenseCategoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */