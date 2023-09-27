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
/*     */ public abstract class AbstractCountry
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
/*     */   private String shortName;
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
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCountry() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCountry(Integer countryId) {
/*  57 */     setId(countryId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  75 */     this.hashValue = 0;
/*  76 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getShortName() {
/*  85 */     return this.shortName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShortName(String shortName) {
/*  94 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEngName() {
/* 103 */     return this.engName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEngName(String engName) {
/* 112 */     this.engName = engName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChnName() {
/* 121 */     return this.chnName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChnName(String chnName) {
/* 130 */     this.chnName = chnName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 139 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 148 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 155 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 163 */     this.site = site;
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
/* 174 */     if (rhs == null)
/* 175 */       return false; 
/* 176 */     if (this == rhs)
/* 177 */       return true; 
/* 178 */     if (!(rhs instanceof Country))
/* 179 */       return false; 
/* 180 */     Country that = (Country)rhs;
/* 181 */     if (getId() != null)
/* 182 */       return getId().equals(that.getId()); 
/* 183 */     return (that.getId() == null);
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
/* 194 */     if (this.hashValue == 0) {
/* 195 */       int result = 17;
/* 196 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 197 */       result = result * 37 + idValue;
/* 198 */       this.hashValue = result;
/*     */     } 
/* 200 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractCountry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */