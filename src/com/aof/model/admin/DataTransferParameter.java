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
/*     */ public class DataTransferParameter
/*     */   extends AbstractDataTransferParameter
/*     */   implements Serializable
/*     */ {
/*     */   private Integer interval;
/*     */   private Integer intervalHour;
/*     */   private Integer intervalType;
/*     */   
/*     */   public DataTransferParameter() {}
/*     */   
/*     */   public DataTransferParameter(Site site) {
/*  32 */     super(site);
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
/*     */   public Integer getInterval() {
/*  45 */     if (getIntervalMin() == null) {
/*  46 */       return null;
/*     */     }
/*  48 */     this.interval = (getIntervalHour().intValue() == 0) ? getIntervalMin() : getIntervalHour();
/*  49 */     return this.interval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterval(Integer interval) {
/*  56 */     this.interval = interval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getIntervalType() {
/*  64 */     if (getIntervalMin() == null) {
/*  65 */       return new Integer(1);
/*     */     }
/*  67 */     this.intervalType = (getIntervalHour().intValue() == 0) ? new Integer(2) : new Integer(1);
/*  68 */     return this.intervalType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntervalType(Integer intervalType) {
/*  75 */     this.intervalType = intervalType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntervalHour(Integer intervalHour) {
/*  83 */     this.intervalHour = intervalHour;
/*     */   }
/*     */   
/*     */   public Integer getIntervalHour() {
/*  87 */     if (getIntervalMin() == null) {
/*  88 */       return new Integer(0);
/*     */     }
/*  90 */     int interval = getIntervalMin().intValue();
/*  91 */     this.intervalHour = (interval % 60 == 0) ? new Integer(interval / 60) : new Integer(0);
/*  92 */     return this.intervalHour;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean schemeEqual(DataTransferParameter that) {
/*  97 */     if (!getId().equals(that.getId())) return false; 
/*  98 */     if (getIntervalMin() != null)
/*  99 */     { if (!getIntervalMin().equals(that.getIntervalMin())) return false;
/*     */        }
/* 101 */     else if (that.getIntervalMin() != null) { return false; }
/*     */     
/* 103 */     if (getStartTime() != null)
/* 104 */     { if (!getStartTime().equals(that.getStartTime())) return false;
/*     */        }
/* 106 */     else if (that.getStartTime() != null) { return false; }
/*     */     
/* 108 */     if (getTimePerDay() != null)
/* 109 */     { if (!getTimePerDay().equals(that.getTimePerDay())) return false;
/*     */        }
/* 111 */     else if (that.getTimePerDay() != null) { return false; }
/*     */     
/* 113 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/DataTransferParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */