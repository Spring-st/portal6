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
/*     */ public abstract class AbstractUserSite
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private User user;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserSite() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUserSite(User user, Site site) {
/*  50 */     setUser(user);
/*  51 */     setSite(site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  58 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/*  66 */     this.hashValue = 0;
/*  67 */     this.site = site;
/*     */   }
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
/*     */   public User getUser() {
/*  89 */     return this.user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(User user) {
/*  97 */     this.hashValue = 0;
/*  98 */     this.user = user;
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
/* 113 */     if (!(rhs instanceof UserSite))
/* 114 */       return false; 
/* 115 */     UserSite that = (UserSite)rhs;
/* 116 */     if (getSite() == null || that.getSite() == null) {
/* 117 */       return false;
/*     */     }
/* 119 */     if (!getSite().equals(that.getSite())) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (getUser() == null || that.getUser() == null) {
/* 123 */       return false;
/*     */     }
/* 125 */     if (!getUser().equals(that.getUser())) {
/* 126 */       return false;
/*     */     }
/* 128 */     return true;
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
/* 139 */     if (this.hashValue == 0) {
/* 140 */       int result = 17;
/* 141 */       int tSiteValue = (getSite() == null) ? 0 : getSite().hashCode();
/* 142 */       result = result * 37 + tSiteValue;
/* 143 */       int tUserValue = (getUser() == null) ? 0 : getUser().hashCode();
/* 144 */       result = result * 37 + tUserValue;
/* 145 */       this.hashValue = result;
/*     */     } 
/* 147 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractUserSite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */