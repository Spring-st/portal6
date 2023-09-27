/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractUnplannedReasons
/*     */   implements Serializable
/*     */ {
/*  13 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private String domain;
/*     */   private String instructions;
/*     */   private String describe;
/*     */   private EnabledDisabled status;
/*     */   private String remark;
/*     */   private Date date;
/*     */   private User user;
/*     */   private YesNo type;
/*     */   private String expenses_course;
/*     */   private String department_cost;
/*     */   
/*     */   public String getExpenses_course() {
/*  29 */     return this.expenses_course;
/*     */   }
/*     */   
/*     */   public void setExpenses_course(String expenses_course) {
/*  33 */     this.expenses_course = expenses_course;
/*     */   }
/*     */   
/*     */   public String getDepartment_cost() {
/*  37 */     return this.department_cost;
/*     */   }
/*     */   
/*     */   public void setDepartment_cost(String department_cost) {
/*  41 */     this.department_cost = department_cost;
/*     */   }
/*     */   
/*     */   public YesNo getType() {
/*  45 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(YesNo type) {
/*  49 */     this.type = type;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  53 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  57 */     this.user = user;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  61 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  65 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  69 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  73 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  77 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  81 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  85 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  89 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getInstructions() {
/*  93 */     return this.instructions;
/*     */   }
/*     */   
/*     */   public void setInstructions(String instructions) {
/*  97 */     this.instructions = instructions;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 101 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 105 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/* 109 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/* 113 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 117 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 121 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUnplannedReasons() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractUnplannedReasons(Integer id) {
/* 133 */     setId(id);
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
/* 144 */     if (rhs == null)
/* 145 */       return false; 
/* 146 */     if (this == rhs)
/* 147 */       return true; 
/* 148 */     if (!(rhs instanceof UnplannedReasons))
/* 149 */       return false; 
/* 150 */     UnplannedReasons that = (UnplannedReasons)rhs;
/* 151 */     if (getId() != null)
/* 152 */       return getId().equals(that.getId()); 
/* 153 */     return (that.getId() == null);
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
/* 164 */     if (this.hashValue == 0) {
/* 165 */       int result = 17;
/* 166 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 167 */       result = result * 37 + poIdValue;
/* 168 */       this.hashValue = result;
/*     */     } 
/* 170 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractUnplannedReasons.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */