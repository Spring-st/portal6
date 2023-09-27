/*     */ package com.aof.model.admin;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractUserDepartment
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Department department;
/*     */ 
/*     */ 
/*     */   
/*     */   private User user;
/*     */ 
/*     */ 
/*     */   
/*     */   private String title;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserDepartment() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserDepartment(User user, Department department) {
/*  50 */     setUser(user);
/*  51 */     setDepartment(department);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department getDepartment() {
/*  58 */     return this.department;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepartment(Department department) {
/*  66 */     this.hashValue = 0;
/*  67 */     this.department = department;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUser() {
/*  74 */     return this.user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(User user) {
/*  82 */     this.hashValue = 0;
/*  83 */     this.user = user;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  88 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  92 */     this.title = title;
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
/* 103 */     if (rhs == null) return false; 
/* 104 */     if (this == rhs) return true; 
/* 105 */     if (!(rhs instanceof UserDepartment)) return false; 
/* 106 */     UserDepartment that = (UserDepartment)rhs;
/* 107 */     if (getDepartment() == null || that.getDepartment() == null) {
/* 108 */       return false;
/*     */     }
/* 110 */     if (!getDepartment().equals(that.getDepartment())) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (getUser() == null || that.getUser() == null) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (!getUser().equals(that.getUser())) {
/* 117 */       return false;
/*     */     }
/* 119 */     return true;
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
/* 130 */     if (this.hashValue == 0) {
/* 131 */       int result = 17;
/* 132 */       int tDepartmentValue = (getDepartment() == null) ? 0 : getDepartment().hashCode();
/* 133 */       result = result * 37 + tDepartmentValue;
/* 134 */       int tUserValue = (getUser() == null) ? 0 : getUser().hashCode();
/* 135 */       result = result * 37 + tUserValue;
/* 136 */       this.hashValue = result;
/*     */     } 
/* 138 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractUserDepartment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */