/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTravelGroupDetail
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private ExpenseSubCategory expenseSubCategory;
/*     */ 
/*     */ 
/*     */   
/*     */   private TravelGroup travelGroup;
/*     */ 
/*     */ 
/*     */   
/*     */   private BigDecimal amountLimit;
/*     */ 
/*     */   
/*     */   private BigDecimal abroadAmountLimit;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTravelGroupDetail() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTravelGroupDetail(ExpenseSubCategory expenseSubCategory, TravelGroup travelGroup) {
/*  54 */     setExpenseSubCategory(expenseSubCategory);
/*  55 */     setTravelGroup(travelGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseSubCategory getExpenseSubCategory() {
/*  62 */     return this.expenseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
/*  70 */     this.hashValue = 0;
/*  71 */     this.expenseSubCategory = expenseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TravelGroup getTravelGroup() {
/*  78 */     return this.travelGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTravelGroup(TravelGroup travelGroup) {
/*  86 */     this.hashValue = 0;
/*  87 */     this.travelGroup = travelGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAmountLimit() {
/*  96 */     return this.amountLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAmountLimit(BigDecimal amountLimit) {
/* 105 */     this.amountLimit = amountLimit;
/*     */   }
/*     */   
/*     */   public BigDecimal getAbroadAmountLimit() {
/* 109 */     return this.abroadAmountLimit;
/*     */   }
/*     */   
/*     */   public void setAbroadAmountLimit(BigDecimal abroadAmountLimit) {
/* 113 */     this.abroadAmountLimit = abroadAmountLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 124 */     if (rhs == null)
/* 125 */       return false; 
/* 126 */     if (this == rhs)
/* 127 */       return true; 
/* 128 */     if (!(rhs instanceof TravelGroupDetail))
/* 129 */       return false; 
/* 130 */     TravelGroupDetail that = (TravelGroupDetail)rhs;
/* 131 */     if (getExpenseSubCategory() == null || that.getExpenseSubCategory() == null) {
/* 132 */       return false;
/*     */     }
/* 134 */     if (!getExpenseSubCategory().equals(that.getExpenseSubCategory())) {
/* 135 */       return false;
/*     */     }
/* 137 */     if (getTravelGroup() == null || that.getTravelGroup() == null) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (!getTravelGroup().equals(that.getTravelGroup())) {
/* 141 */       return false;
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 154 */     if (this.hashValue == 0) {
/* 155 */       int result = 17;
/* 156 */       int tExpSubcateValue = (getExpenseSubCategory() == null) ? 0 : getExpenseSubCategory().hashCode();
/* 157 */       result = result * 37 + tExpSubcateValue;
/* 158 */       int tTraGrpValue = (getTravelGroup() == null) ? 0 : getTravelGroup().hashCode();
/* 159 */       result = result * 37 + tTraGrpValue;
/* 160 */       this.hashValue = result;
/*     */     } 
/* 162 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractTravelGroupDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */