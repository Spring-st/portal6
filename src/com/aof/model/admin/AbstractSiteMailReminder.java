/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.SiteMailReminderType;
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
/*     */ 
/*     */ public abstract class AbstractSiteMailReminder
/*     */   implements Serializable
/*     */ {
/*  32 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */ 
/*     */   
/*     */   private SiteMailReminderType type;
/*     */ 
/*     */ 
/*     */   
/*     */   private int dueDay;
/*     */ 
/*     */ 
/*     */   
/*     */   private int intervalDay;
/*     */ 
/*     */ 
/*     */   
/*     */   private int maxTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSiteMailReminder() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSiteMailReminder(Site site, SiteMailReminderType type) {
/*  62 */     this.site = site;
/*  63 */     this.type = type;
/*     */   }
/*     */   
/*     */   public SiteMailReminderType getType() {
/*  67 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(SiteMailReminderType type) {
/*  71 */     this.hashValue = 0;
/*  72 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getDueDay() {
/*  76 */     return this.dueDay;
/*     */   }
/*     */   
/*     */   public void setDueDay(int dueDay) {
/*  80 */     this.dueDay = dueDay;
/*     */   }
/*     */   
/*     */   public int getIntervalDay() {
/*  84 */     return this.intervalDay;
/*     */   }
/*     */   
/*     */   public void setIntervalDay(int intervalDay) {
/*  88 */     this.intervalDay = intervalDay;
/*     */   }
/*     */   
/*     */   public int getMaxTime() {
/*  92 */     return this.maxTime;
/*     */   }
/*     */   
/*     */   public void setMaxTime(int maxTime) {
/*  96 */     this.maxTime = maxTime;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 100 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 104 */     this.hashValue = 0;
/* 105 */     this.site = site;
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
/* 116 */     if (rhs == null) return false; 
/* 117 */     if (this == rhs) return true; 
/* 118 */     if (!(rhs instanceof SiteMailReminder)) return false; 
/* 119 */     SiteMailReminder that = (SiteMailReminder)rhs;
/* 120 */     if (getSite() == null || that.getSite() == null) {
/* 121 */       return false;
/*     */     }
/* 123 */     if (!getType().equals(that.getType())) {
/* 124 */       return false;
/*     */     }
/* 126 */     return true;
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
/* 137 */     if (this.hashValue == 0) {
/* 138 */       int result = 17;
/* 139 */       int tSiteValue = (getSite() == null) ? 0 : getSite().hashCode();
/* 140 */       result = result * 37 + tSiteValue;
/* 141 */       int tTypeValue = (getType() == null) ? 0 : getType().hashCode();
/* 142 */       result = result * 37 + tTypeValue;
/* 143 */       this.hashValue = result;
/*     */     } 
/* 145 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSiteMailReminder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */