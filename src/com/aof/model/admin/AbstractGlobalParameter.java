/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class AbstractGlobalParameter
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private String smtpAddress;
/*     */ 
/*     */   
/*     */   private String smtpUsername;
/*     */ 
/*     */   
/*     */   private String smtpPassword;
/*     */ 
/*     */   
/*     */   private Integer minPasswordLength;
/*     */ 
/*     */   
/*     */   private Integer passwordExpireDay;
/*     */ 
/*     */   
/*     */   private Integer accountLock;
/*     */ 
/*     */   
/*     */   private YesNo isLdapAuth;
/*     */ 
/*     */   
/*     */   private String ldapServerName;
/*     */ 
/*     */   
/*     */   private String ldapServerIp;
/*     */ 
/*     */   
/*     */   private Integer ldapServerPort;
/*     */ 
/*     */   
/*     */   private String ldapRootDN;
/*     */ 
/*     */   
/*     */   private String ldapUserName;
/*     */ 
/*     */   
/*     */   private String ldapPassword;
/*     */ 
/*     */   
/*     */   private String ldapQuery;
/*     */   
/*     */   private String ldapFilter;
/*     */ 
/*     */   
/*     */   public AbstractGlobalParameter() {}
/*     */ 
/*     */   
/*     */   public AbstractGlobalParameter(Integer id) {
/*  85 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  94 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 103 */     this.hashValue = 0;
/* 104 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSmtpUsername() {
/* 113 */     return this.smtpUsername;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSmtpUsername(String smtpUsername) {
/* 122 */     this.smtpUsername = smtpUsername;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSmtpPassword() {
/* 131 */     return this.smtpPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSmtpPassword(String smtpPassword) {
/* 140 */     this.smtpPassword = smtpPassword;
/*     */   }
/*     */   
/*     */   public Integer getAccountLock() {
/* 144 */     return this.accountLock;
/*     */   }
/*     */   
/*     */   public void setAccountLock(Integer accountLock) {
/* 148 */     this.accountLock = accountLock;
/*     */   }
/*     */   
/*     */   public Integer getMinPasswordLength() {
/* 152 */     return this.minPasswordLength;
/*     */   }
/*     */   
/*     */   public void setMinPasswordLength(Integer minPasswordLength) {
/* 156 */     this.minPasswordLength = minPasswordLength;
/*     */   }
/*     */   
/*     */   public Integer getPasswordExpireDay() {
/* 160 */     return this.passwordExpireDay;
/*     */   }
/*     */   
/*     */   public void setPasswordExpireDay(Integer passwordExpireDay) {
/* 164 */     this.passwordExpireDay = passwordExpireDay;
/*     */   }
/*     */   
/*     */   public String getSmtpAddress() {
/* 168 */     return this.smtpAddress;
/*     */   }
/*     */   
/*     */   public void setSmtpAddress(String smtpAddress) {
/* 172 */     this.smtpAddress = smtpAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getIsLdapAuth() {
/* 179 */     return this.isLdapAuth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsLdapAuth(YesNo isLdapAuth) {
/* 187 */     this.isLdapAuth = isLdapAuth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapFilter() {
/* 194 */     return this.ldapFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapFilter(String ldapFilter) {
/* 202 */     this.ldapFilter = ldapFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapPassword() {
/* 209 */     return this.ldapPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapPassword(String ldapPassword) {
/* 217 */     this.ldapPassword = ldapPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapQuery() {
/* 224 */     return this.ldapQuery;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapQuery(String ldapQuery) {
/* 232 */     this.ldapQuery = ldapQuery;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapRootDN() {
/* 239 */     return this.ldapRootDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapRootDN(String ldapRootDN) {
/* 247 */     this.ldapRootDN = ldapRootDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapServerIp() {
/* 254 */     return this.ldapServerIp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapServerIp(String ldapServerIp) {
/* 262 */     this.ldapServerIp = ldapServerIp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapServerName() {
/* 269 */     return this.ldapServerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapServerName(String ldapServerName) {
/* 277 */     this.ldapServerName = ldapServerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getLdapServerPort() {
/* 284 */     return this.ldapServerPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapServerPort(Integer ldapServerPort) {
/* 292 */     this.ldapServerPort = ldapServerPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapUserName() {
/* 299 */     return this.ldapUserName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapUserName(String ldapUserName) {
/* 307 */     this.ldapUserName = ldapUserName;
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
/* 318 */     if (rhs == null)
/* 319 */       return false; 
/* 320 */     if (this == rhs)
/* 321 */       return true; 
/* 322 */     if (!(rhs instanceof GlobalParameter))
/* 323 */       return false; 
/* 324 */     GlobalParameter that = (GlobalParameter)rhs;
/* 325 */     if (getId() != null)
/* 326 */       return getId().equals(that.getId()); 
/* 327 */     return (that.getId() == null);
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
/* 338 */     if (this.hashValue == 0) {
/* 339 */       int result = 17;
/* 340 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 341 */       result = result * 37 + idValue;
/* 342 */       this.hashValue = result;
/*     */     } 
/* 344 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractGlobalParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */