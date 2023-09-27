/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.RoleType;
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
/*     */ public abstract class AbstractRole
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */ 
/*     */   
/*     */   private RoleType type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRole() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRole(Integer id) {
/*  57 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  66 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  73 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  80 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  87 */     this.hashValue = 0;
/*  88 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 102 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RoleType getType() {
/* 109 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(RoleType type) {
/* 116 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 126 */     if (rhs == null) return false; 
/* 127 */     if (this == rhs) return true; 
/* 128 */     if (!(rhs instanceof Role)) return false; 
/* 129 */     Role that = (Role)rhs;
/* 130 */     if (getId() != null) return getId().equals(that.getId()); 
/* 131 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 141 */     if (this.hashValue == 0) {
/*     */       
/* 143 */       int result = 17;
/* 144 */       int roleIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 145 */       result = result * 37 + roleIdValue;
/* 146 */       this.hashValue = result;
/*     */     } 
/* 148 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractRole.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */