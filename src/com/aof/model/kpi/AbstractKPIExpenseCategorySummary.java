/*     */ package com.aof.model.kpi;
/*     */ 
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractKPIExpenseCategorySummary
/*     */   implements Serializable
/*     */ {
/*  11 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private KPISummary kpiSummary;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private Date summaryDate;
/*     */ 
/*     */   
/*     */   private ExpenseCategory expenseCategory;
/*     */ 
/*     */   
/*     */   private int expenseRequestCreatedToday;
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory getExpenseCategory() {
/*  36 */     return this.expenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseCategory(ExpenseCategory expenseCategory) {
/*  43 */     this.expenseCategory = expenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpenseRequestCreatedToday() {
/*  50 */     return this.expenseRequestCreatedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseRequestCreatedToday(int expenseRequestCreatedToday) {
/*  57 */     this.expenseRequestCreatedToday = expenseRequestCreatedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KPISummary getKpiSummary() {
/*  78 */     return this.kpiSummary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKpiSummary(KPISummary kpiSummary) {
/*  85 */     this.kpiSummary = kpiSummary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  92 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/*  99 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getSummaryDate() {
/* 106 */     return this.summaryDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSummaryDate(Date summaryDate) {
/* 113 */     this.summaryDate = summaryDate;
/*     */   }
/*     */   
/*     */   public boolean equals(Object ks) {
/* 117 */     if (ks == null)
/* 118 */       return false; 
/* 119 */     if (this == ks)
/* 120 */       return true; 
/* 121 */     if (!(ks instanceof KPIExpenseCategorySummary))
/* 122 */       return false; 
/* 123 */     KPIExpenseCategorySummary that = (KPIExpenseCategorySummary)ks;
/* 124 */     if (getId() != null)
/* 125 */       return getId().equals(that.getId()); 
/* 126 */     return (that.getId() == null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 130 */     if (this.hashValue == 0) {
/* 131 */       int result = 17;
/* 132 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 133 */       result = result * 37 + itemIdValue;
/* 134 */       this.hashValue = result;
/*     */     } 
/* 136 */     return this.hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractKPIExpenseCategorySummary() {}
/*     */ 
/*     */   
/*     */   public AbstractKPIExpenseCategorySummary(Integer id) {
/* 145 */     setId(id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/kpi/AbstractKPIExpenseCategorySummary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */