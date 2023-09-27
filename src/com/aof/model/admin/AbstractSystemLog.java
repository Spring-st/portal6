/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
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
/*     */ public abstract class AbstractSystemLog
/*     */   implements Serializable
/*     */ {
/*  24 */   private int hashValue = 0;
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
/*     */   private String target;
/*     */ 
/*     */ 
/*     */   
/*     */   private String targetId;
/*     */ 
/*     */ 
/*     */   
/*     */   private String action;
/*     */ 
/*     */   
/*     */   private String content;
/*     */ 
/*     */   
/*     */   private Date actionTime;
/*     */ 
/*     */   
/*     */   private User user;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSystemLog() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSystemLog(Integer id) {
/*  62 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAction() {
/*  69 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(String action) {
/*  77 */     this.action = action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUser() {
/*  84 */     return this.user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(User user) {
/*  92 */     this.user = user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  99 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 107 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 116 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setId(Integer id) {
/* 125 */     this.hashValue = 0;
/* 126 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTarget() {
/* 135 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(String target) {
/* 144 */     this.target = target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTargetId() {
/* 153 */     return this.targetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetId(String targetId) {
/* 162 */     this.targetId = targetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContent() {
/* 171 */     return this.content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContent(String content) {
/* 180 */     this.content = content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getActionTime() {
/* 189 */     return this.actionTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionTime(Date actionTime) {
/* 198 */     this.actionTime = actionTime;
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
/* 209 */     if (rhs == null)
/* 210 */       return false; 
/* 211 */     if (this == rhs)
/* 212 */       return true; 
/* 213 */     if (!(rhs instanceof City))
/* 214 */       return false; 
/* 215 */     SystemLog that = (SystemLog)rhs;
/* 216 */     if (getId() != null)
/* 217 */       return getId().equals(that.getId()); 
/* 218 */     return (that.getId() == null);
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
/* 229 */     if (this.hashValue == 0) {
/* 230 */       int result = 17;
/* 231 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 232 */       result = result * 37 + idValue;
/* 233 */       this.hashValue = result;
/*     */     } 
/* 235 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSystemLog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */