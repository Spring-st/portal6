/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ public class Site
/*     */   extends AbstractSite
/*     */   implements Serializable
/*     */ {
/*     */   private List departments;
/*     */   private ExpenseCategory enabledTravelExpenseCategory;
/*     */   private List enabledNotTravelExpenseCategoryList;
/*     */   private List enabledPurchaseCategoryList;
/*     */   private List enabledExchangeRateList;
/*     */   private Map mailReminders;
/*     */   
/*     */   public Site() {}
/*     */   
/*     */   public Site(Integer id) {
/*  36 */     super(id);
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
/*     */   public List getDepartments() {
/*  48 */     return this.departments;
/*     */   }
/*     */   public void setDepartments(List departments) {
/*  51 */     this.departments = departments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledNotTravelExpenseCategoryList() {
/*  59 */     return this.enabledNotTravelExpenseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabledNotTravelExpenseCategoryList(List enabledNotTravelExpenseCategoryList) {
/*  66 */     this.enabledNotTravelExpenseCategoryList = enabledNotTravelExpenseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory getEnabledTravelExpenseCategory() {
/*  73 */     return this.enabledTravelExpenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabledTravelExpenseCategory(ExpenseCategory enabledTravelExpenseCategory) {
/*  80 */     this.enabledTravelExpenseCategory = enabledTravelExpenseCategory;
/*     */   }
/*     */   
/*     */   public List getEnabledExpenseCategoryList() {
/*  84 */     List<ExpenseCategory> result = new ArrayList();
/*  85 */     if (this.enabledTravelExpenseCategory != null)
/*  86 */       result.add(0, this.enabledTravelExpenseCategory); 
/*  87 */     result.addAll(this.enabledNotTravelExpenseCategoryList);
/*  88 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseCategoryList() {
/*  95 */     return this.enabledPurchaseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabledPurchaseCategoryList(List enabledPurchaseCategoryList) {
/* 102 */     this.enabledPurchaseCategoryList = enabledPurchaseCategoryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledExchangeRateList() {
/* 109 */     return this.enabledExchangeRateList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabledExchangeRateList(List enabledExchangeRateList) {
/* 116 */     this.enabledExchangeRateList = enabledExchangeRateList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getMailReminders() {
/* 122 */     return this.mailReminders;
/*     */   }
/*     */   
/*     */   public void setMailReminders(Map mailReminder) {
/* 126 */     this.mailReminders = mailReminder;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Site.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */