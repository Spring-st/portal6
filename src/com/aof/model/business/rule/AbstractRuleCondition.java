/*     */ package com.aof.model.business.rule;
/*     */ 
/*     */ import com.aof.model.metadata.ConditionCompareType;
/*     */ import com.aof.model.metadata.ConditionType;
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
/*     */ public abstract class AbstractRuleCondition
/*     */   implements Serializable
/*     */ {
/*  22 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Rule rule;
/*     */ 
/*     */ 
/*     */   
/*     */   private ConditionType type;
/*     */ 
/*     */   
/*     */   private ConditionCompareType compareType;
/*     */ 
/*     */   
/*     */   private String value;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRuleCondition(Rule rule, ConditionType type) {
/*  41 */     setRule(rule);
/*  42 */     setType(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRuleCondition() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionCompareType getCompareType() {
/*  53 */     return this.compareType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompareType(ConditionCompareType compareType) {
/*  60 */     this.compareType = compareType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule() {
/*  67 */     return this.rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRule(Rule rule) {
/*  74 */     this.hashValue = 0;
/*  75 */     this.rule = rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionType getType() {
/*  82 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ConditionType type) {
/*  89 */     this.hashValue = 0;
/*  90 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  97 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String value) {
/* 104 */     this.value = value;
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
/* 115 */     if (rhs == null) return false; 
/* 116 */     if (this == rhs) return true; 
/* 117 */     if (!(rhs instanceof RuleCondition)) return false; 
/* 118 */     RuleCondition that = (RuleCondition)rhs;
/* 119 */     if (getRule() != null && 
/* 120 */       !getRule().equals(that.getRule())) return false;
/*     */     
/* 122 */     if (getType() != null && 
/* 123 */       !getType().equals(that.getType())) return false;
/*     */     
/* 125 */     return true;
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
/* 136 */     if (this.hashValue == 0) {
/* 137 */       int result = 17;
/* 138 */       int itemRuleValue = (getRule() == null) ? 0 : getRule().hashCode();
/* 139 */       result = result * 37 + itemRuleValue;
/* 140 */       int itemTypeValue = (getType() == null) ? 0 : getType().hashCode();
/* 141 */       result = result * 37 + itemTypeValue;
/* 142 */       this.hashValue = result;
/*     */     } 
/* 144 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractRuleCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */