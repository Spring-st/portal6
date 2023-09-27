/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public abstract class AbstractDepartment
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Department parentDepartment;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   private User manager;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractDepartment() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractDepartment(Integer id) {
/*  54 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  63 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  72 */     this.hashValue = 0;
/*  73 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  80 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  88 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getManager() {
/*  95 */     return this.manager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setManager(User manager) {
/* 103 */     this.manager = manager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 110 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 118 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department getParentDepartment() {
/* 125 */     return this.parentDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParentDepartment(Department parentDepartment) {
/* 133 */     this.parentDepartment = parentDepartment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 140 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 148 */     this.site = site;
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
/* 159 */     if (rhs == null)
/* 160 */       return false; 
/* 161 */     if (this == rhs)
/* 162 */       return true; 
/* 163 */     if (!(rhs instanceof Department))
/* 164 */       return false; 
/* 165 */     Department that = (Department)rhs;
/* 166 */     if (getId() != null)
/* 167 */       return getId().equals(that.getId()); 
/* 168 */     return (that.getId() == null);
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
/* 179 */     if (this.hashValue == 0) {
/* 180 */       int result = 17;
/* 181 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 182 */       result = result * 37 + idValue;
/* 183 */       this.hashValue = result;
/*     */     } 
/* 185 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractDepartment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */