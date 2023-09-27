/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
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
/*     */ public abstract class AbstractExpenseSubCategory
/*     */   implements Serializable
/*     */ {
/*  26 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private ExpenseCategory expenseCategory;
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */ 
/*     */   
/*     */   private String referenceErpId;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private YesNo isHotel;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExpenseSubCategory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExpenseSubCategory(Integer id) {
/*  58 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  67 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  76 */     this.hashValue = 0;
/*  77 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  84 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  92 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  99 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 107 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseCategory getExpenseCategory() {
/* 114 */     return this.expenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseCategory(ExpenseCategory expenseCategory) {
/* 122 */     this.expenseCategory = expenseCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReferenceErpId() {
/* 129 */     return this.referenceErpId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferenceErpId(String referenceErpId) {
/* 137 */     this.referenceErpId = referenceErpId;
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
/* 148 */     if (rhs == null)
/* 149 */       return false; 
/* 150 */     if (this == rhs)
/* 151 */       return true; 
/* 152 */     if (!(rhs instanceof ExpenseSubCategory))
/* 153 */       return false; 
/* 154 */     ExpenseSubCategory that = (ExpenseSubCategory)rhs;
/* 155 */     if (getId() != null)
/* 156 */       return getId().equals(that.getId()); 
/* 157 */     return (that.getId() == null);
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
/* 168 */     if (this.hashValue == 0) {
/* 169 */       int result = 17;
/* 170 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 171 */       result = result * 37 + idValue;
/* 172 */       this.hashValue = result;
/*     */     } 
/* 174 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public YesNo getIsHotel() {
/* 178 */     return this.isHotel;
/*     */   }
/*     */   
/*     */   public void setIsHotel(YesNo isHotel) {
/* 182 */     this.isHotel = isHotel;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractExpenseSubCategory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */