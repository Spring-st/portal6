/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReasonCode
/*     */   implements Serializable {
/*   9 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private String domain;
/*     */   private String code;
/*     */   private String instructions;
/*     */   private String describe;
/*     */   private EnabledDisabled status;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  20 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  24 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  28 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  32 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  36 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  40 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  44 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/*  48 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  52 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  56 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getInstructions() {
/*  60 */     return this.instructions;
/*     */   }
/*     */   
/*     */   public void setInstructions(String instructions) {
/*  64 */     this.instructions = instructions;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/*  68 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/*  72 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  76 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  80 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  84 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  88 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractReasonCode() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractReasonCode(Integer id) {
/* 100 */     setId(id);
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
/* 111 */     if (rhs == null)
/* 112 */       return false; 
/* 113 */     if (this == rhs)
/* 114 */       return true; 
/* 115 */     if (!(rhs instanceof ReasonCode))
/* 116 */       return false; 
/* 117 */     ReasonCode that = (ReasonCode)rhs;
/* 118 */     if (getId() != null)
/* 119 */       return getId().equals(that.getId()); 
/* 120 */     return (that.getId() == null);
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
/* 131 */     if (this.hashValue == 0) {
/* 132 */       int result = 17;
/* 133 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 134 */       result = result * 37 + poIdValue;
/* 135 */       this.hashValue = result;
/*     */     } 
/* 137 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractReasonCode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */