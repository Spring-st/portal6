/*     */ package com.aof.web.domain;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.utils.IpAddress;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class SessionListView
/*     */ {
/*     */   private HttpSession session;
/*     */   private long accessTime;
/*  28 */   private long liveMinute = 0L;
/*  29 */   private long liveSecond = 0L;
/*     */   
/*     */   private User user;
/*     */   private IpAddress ip;
/*     */   private Date loginTime;
/*  34 */   private static final DateFormatter dfDisplayDate = new DateFormatter(
/*  35 */       Date.class, "yyyy-MM-dd hh:mm:ss");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAccessTime(long accessTime) {
/*  43 */     this.accessTime = accessTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAccessTime() {
/*  50 */     Calendar calendar = Calendar.getInstance();
/*  51 */     calendar.setTimeInMillis(this.accessTime);
/*  52 */     return dfDisplayDate.format(calendar.getTime());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLiveMinute() {
/*  58 */     return this.liveMinute;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLiveMinute(long liveMinute) {
/*  64 */     this.liveMinute = liveMinute;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLiveSecond() {
/*  70 */     return this.liveSecond;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLiveSecond(long liveSecond) {
/*  76 */     this.liveSecond = liveSecond;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpSession getSession() {
/*  82 */     return this.session;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSession(HttpSession session) {
/*  88 */     this.session = session;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUser() {
/*  94 */     return this.user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(User user) {
/* 101 */     this.user = user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IpAddress getIp() {
/* 108 */     return this.ip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIp(IpAddress ip) {
/* 115 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLoginTime() {
/* 121 */     return dfDisplayDate.format(this.loginTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginTime(Date loginTime) {
/* 127 */     this.loginTime = loginTime;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/domain/SessionListView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */