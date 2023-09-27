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
/*     */ 
/*     */ public abstract class AbstractProvince
/*     */   implements Serializable
/*     */ {
/*     */   private Site site;
/*  32 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Country country;
/*     */ 
/*     */ 
/*     */   
/*     */   private String engName;
/*     */ 
/*     */ 
/*     */   
/*     */   private String chnName;
/*     */ 
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProvince() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractProvince(Integer id) {
/*  62 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  71 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  80 */     this.hashValue = 0;
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country getCountry() {
/*  90 */     return this.country;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountry(Country country) {
/*  99 */     this.country = country;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEngName() {
/* 108 */     return this.engName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEngName(String engName) {
/* 117 */     this.engName = engName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChnName() {
/* 126 */     return this.chnName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChnName(String chnName) {
/* 135 */     this.chnName = chnName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 141 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 145 */     this.enabled = enabled;
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
/* 156 */     if (rhs == null) return false; 
/* 157 */     if (this == rhs) return true; 
/* 158 */     if (!(rhs instanceof Province)) return false; 
/* 159 */     Province that = (Province)rhs;
/* 160 */     if (getId() != null) return getId().equals(that.getId()); 
/* 161 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 171 */     if (this.hashValue == 0) {
/*     */       
/* 173 */       int result = 17;
/* 174 */       int provinceIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 175 */       result = result * 37 + provinceIdValue;
/* 176 */       this.hashValue = result;
/*     */     } 
/* 178 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 182 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 186 */     this.site = site;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractProvince.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */