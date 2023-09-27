/*     */ package com.aof.model.business;
/*     */ 
/*     */ import com.aof.model.admin.Customer;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.User;
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
/*     */ public abstract class BaseRecharge
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private Customer customer;
/*     */ 
/*     */   
/*     */   private Department personDepartment;
/*     */ 
/*     */   
/*     */   private User person;
/*     */ 
/*     */   
/*     */   private BigDecimal amount;
/*     */ 
/*     */   
/*     */   private BigDecimal totalAmount;
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseRecharge() {}
/*     */ 
/*     */   
/*     */   protected BaseRecharge(Integer id) {
/*  55 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  70 */     this.hashValue = 0;
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Customer getCustomer() {
/*  78 */     return this.customer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomer(Customer customer) {
/*  86 */     this.customer = customer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getAmount() {
/*  93 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAmount(BigDecimal amount) {
/* 101 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getPerson() {
/* 108 */     return this.person;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPerson(User person) {
/* 116 */     this.person = person;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department getPersonDepartment() {
/* 123 */     return this.personDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPersonDepartment(Department personDepartment) {
/* 131 */     this.personDepartment = personDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 150 */     if (this.hashValue == 0) {
/* 151 */       int result = 17;
/* 152 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 153 */       result = result * 37 + idValue;
/* 154 */       this.hashValue = result;
/*     */     } 
/* 156 */     return this.hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFrom(BaseRecharge rechargeObj) {
/* 165 */     setCustomer(rechargeObj.getCustomer());
/* 166 */     setAmount(rechargeObj.getAmount());
/* 167 */     setPersonDepartment(rechargeObj.getPersonDepartment());
/* 168 */     setPerson(rechargeObj.getPerson());
/*     */   }
/*     */   
/*     */   public void setTotalAmount(BigDecimal totalAmount) {
/* 172 */     this.totalAmount = totalAmount;
/*     */   }
/*     */   
/*     */   public BigDecimal getPercentage() {
/* 176 */     if (this.totalAmount == null) {
/* 177 */       return new BigDecimal(0.0D);
/*     */     }
/* 179 */     return this.amount.multiply(new BigDecimal(100.0D)).divide(this.totalAmount, 12, 6);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/BaseRecharge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */