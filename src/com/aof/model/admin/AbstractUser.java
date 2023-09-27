/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.Gender;
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
/*     */ 
/*     */ public abstract class AbstractUser
/*     */   implements Serializable
/*     */ {
/*  28 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private String loginName;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   private String password;
/*     */ 
/*     */   
/*     */   private Gender gender;
/*     */ 
/*     */   
/*     */   private String email;
/*     */ 
/*     */   
/*     */   private String telephone;
/*     */ 
/*     */   
/*     */   private Site primarySite;
/*     */ 
/*     */   
/*     */   private String passwdHintQuestion;
/*     */ 
/*     */   
/*     */   private String passwdHintAnswer;
/*     */ 
/*     */   
/*     */   private Date lastLoginTime;
/*     */ 
/*     */   
/*     */   private int loginFailedCount;
/*     */ 
/*     */   
/*     */   private String locale;
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */   
/*     */   private String work_team;
/*     */   
/*     */   private String work_grades;
/*     */   
/*     */   private YesNo type;
/*     */ 
/*     */   
/*     */   public YesNo getType() {
/*  79 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(YesNo type) {
/*  83 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getWork_team() {
/*  87 */     return this.work_team;
/*     */   }
/*     */   
/*     */   public void setWork_team(String work_team) {
/*  91 */     this.work_team = work_team;
/*     */   }
/*     */   
/*     */   public String getWork_grades() {
/*  95 */     return this.work_grades;
/*     */   }
/*     */   
/*     */   public void setWork_grades(String work_grades) {
/*  99 */     this.work_grades = work_grades;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUser() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUser(Integer id) {
/* 114 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 118 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 122 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getLoginName() {
/* 126 */     return this.loginName;
/*     */   }
/*     */   
/*     */   public void setLoginName(String loginName) {
/* 130 */     this.loginName = loginName;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 134 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 138 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 142 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 146 */     this.password = password;
/*     */   }
/*     */   
/*     */   public Gender getGender() {
/* 150 */     return this.gender;
/*     */   }
/*     */   
/*     */   public void setGender(Gender gender) {
/* 154 */     this.gender = gender;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 158 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 162 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getTelephone() {
/* 166 */     return this.telephone;
/*     */   }
/*     */   
/*     */   public void setTelephone(String telephone) {
/* 170 */     this.telephone = telephone;
/*     */   }
/*     */   
/*     */   public Site getPrimarySite() {
/* 174 */     return this.primarySite;
/*     */   }
/*     */   
/*     */   public void setPrimarySite(Site primarySite) {
/* 178 */     this.primarySite = primarySite;
/*     */   }
/*     */   
/*     */   public String getPasswdHintQuestion() {
/* 182 */     return this.passwdHintQuestion;
/*     */   }
/*     */   
/*     */   public void setPasswdHintQuestion(String passwdHintQuestion) {
/* 186 */     this.passwdHintQuestion = passwdHintQuestion;
/*     */   }
/*     */   
/*     */   public String getPasswdHintAnswer() {
/* 190 */     return this.passwdHintAnswer;
/*     */   }
/*     */   
/*     */   public void setPasswdHintAnswer(String passwdHintAnswer) {
/* 194 */     this.passwdHintAnswer = passwdHintAnswer;
/*     */   }
/*     */   
/*     */   public Date getLastLoginTime() {
/* 198 */     return this.lastLoginTime;
/*     */   }
/*     */   
/*     */   public void setLastLoginTime(Date lastLoginTime) {
/* 202 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */   
/*     */   public int getLoginFailedCount() {
/* 206 */     return this.loginFailedCount;
/*     */   }
/*     */   
/*     */   public void setLoginFailedCount(int loginFailedCount) {
/* 210 */     this.loginFailedCount = loginFailedCount;
/*     */   }
/*     */   
/*     */   public String getLocale() {
/* 214 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setLocale(String locale) {
/* 218 */     this.locale = locale;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 222 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 226 */     this.enabled = enabled;
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
/* 237 */     if (rhs == null)
/* 238 */       return false; 
/* 239 */     if (this == rhs)
/* 240 */       return true; 
/* 241 */     if (!(rhs instanceof User))
/* 242 */       return false; 
/* 243 */     User that = (User)rhs;
/* 244 */     if (getId() != null)
/* 245 */       return getId().equals(that.getId()); 
/* 246 */     return (that.getId() == null);
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
/* 257 */     if (this.hashValue == 0) {
/* 258 */       int result = 17;
/* 259 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 260 */       result = result * 37 + idValue;
/* 261 */       this.hashValue = result;
/*     */     } 
/* 263 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractUser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */