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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTravelGroup
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTravelGroup() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTravelGroup(Integer id) {
/*  58 */     this.hashValue = 0;
/*  59 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  68 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  75 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  96 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 103 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 110 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 117 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 127 */     if (rhs == null) return false; 
/* 128 */     if (this == rhs) return true; 
/* 129 */     if (!(rhs instanceof TravelGroup)) return false; 
/* 130 */     TravelGroup that = (TravelGroup)rhs;
/* 131 */     if (getId() != null) return getId().equals(that.getId()); 
/* 132 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 142 */     if (this.hashValue == 0) {
/*     */       
/* 144 */       int result = 17;
/* 145 */       int traGrpIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 146 */       result = result * 37 + traGrpIdValue;
/* 147 */       this.hashValue = result;
/*     */     } 
/* 149 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractTravelGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */