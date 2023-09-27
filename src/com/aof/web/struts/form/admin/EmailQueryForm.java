/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
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
/*     */ 
/*     */ 
/*     */ public class EmailQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String mailFrom;
/*     */   private String mailTo;
/*     */   private String subject;
/*     */   private String createTimeBegin;
/*     */   private String createTimeTo;
/*     */   private String sentTimeBegin;
/*     */   private String sentTimeTo;
/*     */   private String failCount;
/*     */   private String body;
/*     */   private String waitToSend;
/*     */   
/*     */   public String getBody() {
/*  46 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBody(String body) {
/*  53 */     this.body = body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFailCount() {
/*  60 */     return this.failCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFailCount(String failCount) {
/*  67 */     this.failCount = failCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  74 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailFrom() {
/*  88 */     return this.mailFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailFrom(String mailFrom) {
/*  95 */     this.mailFrom = mailFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMailTo() {
/* 102 */     return this.mailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailTo(String mailTo) {
/* 109 */     this.mailTo = mailTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubject() {
/* 116 */     return this.subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String subject) {
/* 123 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreateTimeBegin() {
/* 130 */     return this.createTimeBegin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateTimeBegin(String createTimeBegin) {
/* 138 */     this.createTimeBegin = createTimeBegin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreateTimeTo() {
/* 145 */     return this.createTimeTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateTimeTo(String createTimeTo) {
/* 153 */     this.createTimeTo = createTimeTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSentTimeBegin() {
/* 160 */     return this.sentTimeBegin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSentTimeBegin(String sentTimeBegin) {
/* 168 */     this.sentTimeBegin = sentTimeBegin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSentTimeTo() {
/* 175 */     return this.sentTimeTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSentTimeTo(String sentTimeTo) {
/* 183 */     this.sentTimeTo = sentTimeTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWaitToSend() {
/* 192 */     return this.waitToSend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWaitToSend(String waitToSend) {
/* 199 */     this.waitToSend = waitToSend;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/EmailQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */