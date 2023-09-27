/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.ExpenseSubCategoryDAO;
/*    */ import com.aof.model.admin.ExpenseSubCategory;
/*    */ import com.aof.model.admin.query.ExpenseSubCategoryQueryCondition;
/*    */ import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.ExpenseSubCategoryManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpenseSubCategoryManagerImpl
/*    */   extends BaseManager
/*    */   implements ExpenseSubCategoryManager
/*    */ {
/*    */   private ExpenseSubCategoryDAO dao;
/*    */   
/*    */   public void setExpenseSubCategoryDAO(ExpenseSubCategoryDAO dao) {
/* 34 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExpenseSubCategory getExpenseSubCategory(Integer id) {
/* 41 */     return this.dao.getExpenseSubCategory(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExpenseSubCategory updateExpenseSubCategory(ExpenseSubCategory function) {
/* 48 */     return this.dao.updateExpenseSubCategory(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExpenseSubCategory insertExpenseSubCategory(ExpenseSubCategory function) {
/* 55 */     return this.dao.insertExpenseSubCategory(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpenseSubCategoryListCount(Map conditions) {
/* 63 */     return this.dao.getExpenseSubCategoryListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getExpenseSubCategoryList(Map conditions, int pageNo, int pageSize, ExpenseSubCategoryQueryOrder order, boolean descend) {
/* 70 */     return this.dao.getExpenseSubCategoryList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getChildrenOfExpenseCategory(Integer expenseCategory_id) {
/* 77 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 78 */     conditions.put(ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_ID_EQ, expenseCategory_id);
/* 79 */     return getExpenseSubCategoryList(conditions, 0, -1, ExpenseSubCategoryQueryOrder.ID, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledChildrenOfExpenseCategory(Integer expenseCategory_id) {
/* 86 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 87 */     conditions.put(ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_ID_EQ, expenseCategory_id);
/* 88 */     conditions.put(ExpenseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 89 */     return getExpenseSubCategoryList(conditions, 0, -1, ExpenseSubCategoryQueryOrder.ID, false);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ExpenseSubCategoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */