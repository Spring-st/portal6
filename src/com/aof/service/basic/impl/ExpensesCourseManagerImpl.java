/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ExpensesCourseDAO;
/*     */ import com.aof.model.basic.ExpensesCourse;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryCondition;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.basic.ExpensesCourseManager;
/*     */ import java.util.HashMap;
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
/*     */ public class ExpensesCourseManagerImpl
/*     */   extends BaseManager
/*     */   implements ExpensesCourseManager
/*     */ {
/*     */   private ExpensesCourseDAO dao;
/*     */   
/*     */   public void setDao(ExpensesCourseDAO dao) {
/*  34 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpensesCourse getExpensesCourse(Integer id) {
/*  45 */     return this.dao.getExpensesCourse(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpensesCourse updateExpensesCourse(ExpensesCourse function) {
/*  56 */     return this.dao.updateExpensesCourse(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpensesCourse insertExpensesCourse(ExpensesCourse function) {
/*  67 */     return this.dao.insertExpensesCourse(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpensesCourseListCount(Map conditions) {
/*  78 */     return this.dao.getExpensesCourseListCount(conditions);
/*     */   }
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
/*     */   public List getExpensesCourseList(Map conditions, int pageNo, int pageSize, ExpensesCourseQueryOrder order, boolean descend) {
/*  91 */     return this.dao.getExpensesCourseList(conditions, pageNo, pageSize, order, 
/*  92 */         descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promoteExpensesCourse(ExpensesCourse expensesCourse) {
/* 103 */     updateExpensesCourse(expensesCourse);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean deleteExpensesCourse(ExpensesCourse expensesCourse) {
/*     */     try {
/* 109 */       this.dao.deleteExpensesCourse(expensesCourse);
/* 110 */       return true;
/* 111 */     } catch (Throwable t) {
/* 112 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getExpensesCourseListEnabledAll() {
/* 117 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 118 */     conditions.put(ExpensesCourseQueryCondition.ENABLED_EQ, 
/* 119 */         EnabledDisabled.ENABLED);
/* 120 */     return this.dao.getExpensesCourseList(conditions, -1, -1, null, true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/ExpensesCourseManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */