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
/*     */ public abstract class AbstractRoleFunction
/*     */   implements Serializable
/*     */ {
/*  28 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Function function;
/*     */ 
/*     */   
/*     */   private Role role;
/*     */ 
/*     */ 
/*     */   
/*     */   public Function getFunction() {
/*  39 */     return this.function;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunction(Function function) {
/*  46 */     this.function = function;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Role getRole() {
/*  53 */     return this.role;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRole(Role role) {
/*  60 */     this.role = role;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRoleFunction() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRoleFunction(Function function, Role role) {
/*  76 */     setFunction(function);
/*  77 */     setRole(role);
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
/*  88 */     if (rhs == null)
/*  89 */       return false; 
/*  90 */     if (this == rhs) return true; 
/*  91 */     if (!(rhs instanceof RoleFunction))
/*  92 */       return false; 
/*  93 */     RoleFunction that = (RoleFunction)rhs;
/*  94 */     if (getFunction() == null || that.getFunction() == null)
/*     */     {
/*  96 */       return false;
/*     */     }
/*  98 */     if (!getFunction().equals(that.getFunction()))
/*     */     {
/* 100 */       return false;
/*     */     }
/* 102 */     if (getRole() == null || that.getRole() == null)
/*     */     {
/* 104 */       return false;
/*     */     }
/* 106 */     if (!getRole().equals(that.getRole()))
/*     */     {
/* 108 */       return false;
/*     */     }
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 120 */     if (this.hashValue == 0) {
/*     */       
/* 122 */       int result = 17;
/* 123 */       int tFunctionValue = (getFunction() == null) ? 0 : getFunction().hashCode();
/* 124 */       result = result * 37 + tFunctionValue;
/* 125 */       int tRoleValue = (getRole() == null) ? 0 : getRole().hashCode();
/* 126 */       result = result * 37 + tRoleValue;
/* 127 */       this.hashValue = result;
/*     */     } 
/* 129 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractRoleFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */