/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class AbstractEmail
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private String mailFrom;
/*     */ 
/*     */   
/*     */   private String mailTo;
/*     */ 
/*     */   
/*     */   private String subject;
/*     */ 
/*     */   
/*     */   private Date createTime;
/*     */ 
/*     */   
/*     */   private Date sentTime;
/*     */ 
/*     */   
/*     */   private Integer failCount;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private YesNo waitToSend;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmail() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmail(Integer id) {
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
/*     */   public String getMailFrom() {
/*  90 */     return this.mailFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailFrom(String mailFrom) {
/*  99 */     this.mailFrom = mailFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailTo() {
/* 108 */     return this.mailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailTo(String mailTo) {
/* 117 */     this.mailTo = mailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubject() {
/* 126 */     return this.subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String subject) {
/* 135 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getWaitToSend() {
/* 142 */     return this.waitToSend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWaitToSend(YesNo waitToSend) {
/* 150 */     this.waitToSend = waitToSend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getCreateTime() {
/* 157 */     return this.createTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 165 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getFailCount() {
/* 172 */     return this.failCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFailCount(Integer failCount) {
/* 180 */     this.failCount = failCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getSentTime() {
/* 187 */     return this.sentTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSentTime(Date sentTime) {
/* 195 */     this.sentTime = sentTime;
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
/* 206 */     if (rhs == null)
/* 207 */       return false; 
/* 208 */     if (this == rhs)
/* 209 */       return true; 
/* 210 */     if (!(rhs instanceof Email))
/* 211 */       return false; 
/* 212 */     Email that = (Email)rhs;
/* 213 */     if (getId() != null)
/* 214 */       return getId().equals(that.getId()); 
/* 215 */     return (that.getId() == null);
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
/* 226 */     if (this.hashValue == 0) {
/* 227 */       int result = 17;
/* 228 */       int emailIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 229 */       result = result * 37 + emailIdValue;
/* 230 */       this.hashValue = result;
/*     */     } 
/* 232 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 236 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 240 */     this.site = site;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractEmail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */