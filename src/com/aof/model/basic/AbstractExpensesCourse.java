/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class AbstractExpensesCourse
/*     */   implements Serializable {
/*   8 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String code;
/*     */   private String description;
/*     */   private String type;
/*     */   private String currency;
/*     */   private String format_position;
/*     */   private String expense_index;
/*     */   private String sun_model;
/*     */   private String statistical_expense;
/*     */   private EnabledDisabled enabled;
/*     */   
/*     */   public Integer getId() {
/*  21 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  25 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  29 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  33 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getType() {
/*  37 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  41 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getCurrency() {
/*  45 */     return this.currency;
/*     */   }
/*     */   
/*     */   public void setCurrency(String currency) {
/*  49 */     this.currency = currency;
/*     */   }
/*     */   
/*     */   public String getFormat_position() {
/*  53 */     return this.format_position;
/*     */   }
/*     */   
/*     */   public void setFormat_position(String formatPosition) {
/*  57 */     this.format_position = formatPosition;
/*     */   }
/*     */   
/*     */   public String getExpense_index() {
/*  61 */     return this.expense_index;
/*     */   }
/*     */   
/*     */   public void setExpense_index(String expenseIndex) {
/*  65 */     this.expense_index = expenseIndex;
/*     */   }
/*     */   
/*     */   public String getSun_model() {
/*  69 */     return this.sun_model;
/*     */   }
/*     */   
/*     */   public void setSun_model(String sunModel) {
/*  73 */     this.sun_model = sunModel;
/*     */   }
/*     */   
/*     */   public String getStatistical_expense() {
/*  77 */     return this.statistical_expense;
/*     */   }
/*     */   
/*     */   public void setStatistical_expense(String statisticalExpense) {
/*  81 */     this.statistical_expense = statisticalExpense;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  85 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  89 */     this.description = description;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  93 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  97 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractExpensesCourse() {}
/*     */   
/*     */   public AbstractExpensesCourse(Integer id) {
/* 104 */     setId(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 109 */     if (rhs == null)
/* 110 */       return false; 
/* 111 */     if (this == rhs)
/* 112 */       return true; 
/* 113 */     if (!(rhs instanceof ExpensesCourse))
/* 114 */       return false; 
/* 115 */     ExpensesCourse that = (ExpensesCourse)rhs;
/* 116 */     if (getCode() != null)
/* 117 */       return getCode().equals(that.getCode()); 
/* 118 */     return (that.getCode() == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 123 */     if (this.hashValue == 0) {
/* 124 */       int result = 17;
/* 125 */       int purCateIdValue = (getCode() == null) ? 0 : getCode()
/* 126 */         .hashCode();
/* 127 */       result = result * 37 + purCateIdValue;
/* 128 */       this.hashValue = result;
/*     */     } 
/* 130 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractExpensesCourse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */