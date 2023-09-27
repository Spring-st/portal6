/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.GlobalMailReminderType;
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
/*     */ public abstract class AbstractGlobalMailReminder
/*     */   implements Serializable
/*     */ {
/*  32 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private GlobalMailReminderType type;
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
/*     */   public AbstractGlobalMailReminder() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractGlobalMailReminder(GlobalMailReminderType type) {
/*  57 */     this.type = type;
/*     */   }
/*     */   
/*     */   public GlobalMailReminderType getType() {
/*  61 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(GlobalMailReminderType type) {
/*  65 */     this.hashValue = 0;
/*  66 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getDueDay() {
/*  70 */     return this.dueDay;
/*     */   }
/*     */   
/*     */   public void setDueDay(int dueDay) {
/*  74 */     this.dueDay = dueDay;
/*     */   }
/*     */   
/*     */   public int getIntervalDay() {
/*  78 */     return this.intervalDay;
/*     */   }
/*     */   
/*     */   public void setIntervalDay(int intervalDay) {
/*  82 */     this.intervalDay = intervalDay;
/*     */   }
/*     */   
/*     */   public int getMaxTime() {
/*  86 */     return this.maxTime;
/*     */   }
/*     */   
/*     */   public void setMaxTime(int maxTime) {
/*  90 */     this.maxTime = maxTime;
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
/* 101 */     if (rhs == null)
/* 102 */       return false; 
/* 103 */     if (this == rhs)
/* 104 */       return true; 
/* 105 */     if (!(rhs instanceof GlobalMailReminder))
/* 106 */       return false; 
/* 107 */     GlobalMailReminder that = (GlobalMailReminder)rhs;
/* 108 */     if (getType() != null)
/* 109 */       return getType().equals(that.getType()); 
/* 110 */     return (that.getType() == null);
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
/* 121 */     if (this.hashValue == 0) {
/* 122 */       int result = 17;
/* 123 */       int typeValue = (getType() == null) ? 0 : getType().hashCode();
/* 124 */       result = result * 37 + typeValue;
/* 125 */       this.hashValue = result;
/*     */     } 
/* 127 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractGlobalMailReminder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */