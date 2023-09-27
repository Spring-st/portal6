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
/*     */ public abstract class AbstractUserRole
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private User user;
/*     */ 
/*     */ 
/*     */   
/*     */   private Role role;
/*     */ 
/*     */ 
/*     */   
/*     */   private Site grantedSite;
/*     */ 
/*     */ 
/*     */   
/*     */   private Department grantedDepartment;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserRole() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserRole(Integer id) {
/*  59 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  63 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  67 */     this.id = id;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  71 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  75 */     this.user = user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department getGrantedDepartment() {
/*  82 */     return this.grantedDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGrantedDepartment(Department grantedDepartment) {
/*  90 */     this.grantedDepartment = grantedDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Role getRole() {
/*  97 */     return this.role;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRole(Role role) {
/* 105 */     this.hashValue = 0;
/* 106 */     this.role = role;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getGrantedSite() {
/* 113 */     return this.grantedSite;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGrantedSite(Site grantedSite) {
/* 121 */     this.grantedSite = grantedSite;
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
/* 132 */     if (rhs == null) return false; 
/* 133 */     if (this == rhs) return true; 
/* 134 */     if (!(rhs instanceof UserRole)) return false; 
/* 135 */     UserRole that = (UserRole)rhs;
/* 136 */     if (getId() == null) return (that.getId() == null); 
/* 137 */     return getId().equals(that.getId());
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
/* 148 */     if (this.hashValue == 0) {
/* 149 */       int result = 17;
/* 150 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 151 */       result = result * 37 + idValue;
/* 152 */       this.hashValue = result;
/*     */     } 
/* 154 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractUserRole.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */