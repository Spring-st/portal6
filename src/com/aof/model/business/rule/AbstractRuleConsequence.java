/*     */ package com.aof.model.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.User;
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
/*     */ public abstract class AbstractRuleConsequence
/*     */   implements Serializable
/*     */ {
/*  24 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private int seq;
/*     */ 
/*     */ 
/*     */   
/*     */   private YesNo canModify;
/*     */ 
/*     */   
/*     */   private Rule rule;
/*     */ 
/*     */   
/*     */   private User user;
/*     */ 
/*     */   
/*     */   private User superior;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRuleConsequence(Rule rule, User user) {
/*  46 */     setRule(rule);
/*  47 */     setUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRuleConsequence() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUser() {
/*  58 */     return this.user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(User user) {
/*  65 */     this.hashValue = 0;
/*  66 */     this.user = user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getCanModify() {
/*  73 */     return this.canModify;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanModify(YesNo canModify) {
/*  80 */     this.canModify = canModify;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule() {
/*  87 */     return this.rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRule(Rule rule) {
/*  94 */     this.hashValue = 0;
/*  95 */     this.rule = rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeq() {
/* 102 */     return this.seq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeq(int seq) {
/* 109 */     this.seq = seq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getSuperior() {
/* 116 */     return this.superior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuperior(User superior) {
/* 123 */     this.superior = superior;
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
/* 134 */     if (rhs == null) return false; 
/* 135 */     if (this == rhs) return true; 
/* 136 */     if (!(rhs instanceof RuleConsequence)) return false; 
/* 137 */     RuleConsequence that = (RuleConsequence)rhs;
/* 138 */     if (getRule() != null && 
/* 139 */       !getRule().equals(that.getRule())) return false;
/*     */     
/* 141 */     if (getUser() != null && 
/* 142 */       !getUser().equals(that.getUser())) return false;
/*     */     
/* 144 */     return true;
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
/* 155 */     if (this.hashValue == 0) {
/* 156 */       int result = 17;
/* 157 */       int itemRuleValue = (getRule() == null) ? 0 : getRule().hashCode();
/* 158 */       result = result * 37 + itemRuleValue;
/* 159 */       int itemUserValue = (getUser() == null) ? 0 : getUser().hashCode();
/* 160 */       result = result * 37 + itemUserValue;
/* 161 */       this.hashValue = result;
/*     */     } 
/* 163 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractRuleConsequence.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */