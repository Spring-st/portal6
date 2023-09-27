/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.BadReasonsType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractBadReasons
/*     */   implements Serializable {
/*  12 */   private int hashValue = 0;
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
/*     */   private BadReasonsType type;
/*     */   
/*     */   public BadReasonsType getType() {
/*  26 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(BadReasonsType type) {
/*  30 */     this.type = type;
/*     */   }
/*     */   
/*     */   public User getUser() {
/*  34 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/*  38 */     this.user = user;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  42 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  46 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  50 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  54 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  58 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  62 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  66 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  70 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getInstructions() {
/*  74 */     return this.instructions;
/*     */   }
/*     */   
/*     */   public void setInstructions(String instructions) {
/*  78 */     this.instructions = instructions;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/*  82 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/*  86 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  90 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  94 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  98 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 102 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBadReasons() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBadReasons(Integer id) {
/* 114 */     setId(id);
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
/* 125 */     if (rhs == null)
/* 126 */       return false; 
/* 127 */     if (this == rhs)
/* 128 */       return true; 
/* 129 */     if (!(rhs instanceof BadReasons))
/* 130 */       return false; 
/* 131 */     BadReasons that = (BadReasons)rhs;
/* 132 */     if (getId() != null)
/* 133 */       return getId().equals(that.getId()); 
/* 134 */     return (that.getId() == null);
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
/* 145 */     if (this.hashValue == 0) {
/* 146 */       int result = 17;
/* 147 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 148 */       result = result * 37 + poIdValue;
/* 149 */       this.hashValue = result;
/*     */     } 
/* 151 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractBadReasons.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */