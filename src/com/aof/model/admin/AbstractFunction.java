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
/*     */ public abstract class AbstractFunction
/*     */   implements Serializable
/*     */ {
/*  23 */   private int hashValue = 0;
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
/*     */   
/*     */   public AbstractFunction() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFunction(Integer id) {
/*  46 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  64 */     this.hashValue = 0;
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  72 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  80 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  89 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  98 */     this.description = description;
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
/* 109 */     if (rhs == null)
/* 110 */       return false; 
/* 111 */     if (this == rhs)
/* 112 */       return true; 
/* 113 */     if (!(rhs instanceof Function))
/* 114 */       return false; 
/* 115 */     Function that = (Function)rhs;
/* 116 */     if (getId() != null)
/* 117 */       return getId().equals(that.getId()); 
/* 118 */     return (that.getId() == null);
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
/* 129 */     if (this.hashValue == 0) {
/* 130 */       int result = 17;
/* 131 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 132 */       result = result * 37 + idValue;
/* 133 */       this.hashValue = result;
/*     */     } 
/* 135 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */