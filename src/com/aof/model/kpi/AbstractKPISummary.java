/*     */ package com.aof.model.kpi;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractKPISummary
/*     */   implements Serializable {
/*   9 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Date summaryDate;
/*     */ 
/*     */   
/*     */   private int loginUserCount;
/*     */ 
/*     */   
/*     */   private int closedTANumToday;
/*     */ 
/*     */   
/*     */   private BigDecimal avgTAClosedDays;
/*     */ 
/*     */   
/*     */   private int createdTANumToday;
/*     */ 
/*     */   
/*     */   private int closedCapexNumToday;
/*     */ 
/*     */   
/*     */   private BigDecimal avgCapexClosedDays;
/*     */ 
/*     */   
/*     */   private int createdCapexNumToday;
/*     */ 
/*     */   
/*     */   private int closedPRNumToday;
/*     */ 
/*     */   
/*     */   private BigDecimal avgPRClosedDays;
/*     */ 
/*     */   
/*     */   private int createdPRNumToday;
/*     */ 
/*     */   
/*     */   private int closedExpenseNumToday;
/*     */ 
/*     */   
/*     */   private BigDecimal avgExpenseClosedDays;
/*     */ 
/*     */   
/*     */   private int createdExpenseNumToday;
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  69 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLoginUserCount() {
/*  77 */     return this.loginUserCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginUserCount(int loginUserCount) {
/*  85 */     this.loginUserCount = loginUserCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAvgCapexClosedDays() {
/*  93 */     return this.avgCapexClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAvgCapexClosedDays(BigDecimal avgCapexClosedDays) {
/* 101 */     this.avgCapexClosedDays = avgCapexClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAvgExpenseClosedDays() {
/* 109 */     return this.avgExpenseClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAvgExpenseClosedDays(BigDecimal avgExpenseClosedDays) {
/* 117 */     this.avgExpenseClosedDays = avgExpenseClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAvgPRClosedDays() {
/* 125 */     return this.avgPRClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAvgPRClosedDays(BigDecimal avgPRClosedDays) {
/* 133 */     this.avgPRClosedDays = avgPRClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAvgTAClosedDays() {
/* 141 */     return this.avgTAClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAvgTAClosedDays(BigDecimal avgTAClosedDays) {
/* 149 */     this.avgTAClosedDays = avgTAClosedDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClosedCapexNumToday() {
/* 157 */     return this.closedCapexNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClosedCapexNumToday(int closedCapexNumToday) {
/* 165 */     this.closedCapexNumToday = closedCapexNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClosedExpenseNumToday() {
/* 173 */     return this.closedExpenseNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClosedExpenseNumToday(int closedExpenseNumToday) {
/* 181 */     this.closedExpenseNumToday = closedExpenseNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClosedPRNumToday() {
/* 189 */     return this.closedPRNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClosedPRNumToday(int closedPRNumToday) {
/* 197 */     this.closedPRNumToday = closedPRNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClosedTANumToday() {
/* 205 */     return this.closedTANumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClosedTANumToday(int closedTANumToday) {
/* 213 */     this.closedTANumToday = closedTANumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreatedCapexNumToday() {
/* 221 */     return this.createdCapexNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedCapexNumToday(int createdCapexNumToday) {
/* 229 */     this.createdCapexNumToday = createdCapexNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreatedExpenseNumToday() {
/* 237 */     return this.createdExpenseNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedExpenseNumToday(int createdExpenseNumToday) {
/* 245 */     this.createdExpenseNumToday = createdExpenseNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreatedPRNumToday() {
/* 253 */     return this.createdPRNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedPRNumToday(int createdPRNumToday) {
/* 261 */     this.createdPRNumToday = createdPRNumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreatedTANumToday() {
/* 269 */     return this.createdTANumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedTANumToday(int createdTANumToday) {
/* 277 */     this.createdTANumToday = createdTANumToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getSummaryDate() {
/* 285 */     return this.summaryDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSummaryDate(Date summaryDate) {
/* 293 */     this.summaryDate = summaryDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractKPISummary() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractKPISummary(Integer id) {
/* 308 */     setId(id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object ks) {
/* 312 */     if (ks == null)
/* 313 */       return false; 
/* 314 */     if (this == ks)
/* 315 */       return true; 
/* 316 */     if (!(ks instanceof KPISummary))
/* 317 */       return false; 
/* 318 */     KPISummary that = (KPISummary)ks;
/* 319 */     if (getId() != null)
/* 320 */       return getId().equals(that.getId()); 
/* 321 */     return (that.getId() == null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 325 */     if (this.hashValue == 0) {
/* 326 */       int result = 17;
/* 327 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 328 */       result = result * 37 + itemIdValue;
/* 329 */       this.hashValue = result;
/*     */     } 
/* 331 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/kpi/AbstractKPISummary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */