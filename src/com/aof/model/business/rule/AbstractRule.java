/*     */ package com.aof.model.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractRule
/*     */   implements Serializable
/*     */ {
/*  19 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private RuleType type;
/*     */ 
/*     */   
/*     */   private String description;
/*     */ 
/*     */   
/*     */   private EnabledDisabled enabled;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private Set conditions;
/*     */ 
/*     */   
/*     */   private Set consequences;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRule(Integer id) {
/*  47 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractRule() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getConditions() {
/*  58 */     return this.conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConditions(Set conditions) {
/*  65 */     this.conditions = conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getConsequences() {
/*  72 */     return this.consequences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsequences(Set consequences) {
/*  79 */     this.consequences = consequences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  86 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  93 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 100 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 107 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 114 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 121 */     this.hashValue = 0;
/* 122 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 129 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 136 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleType getType() {
/* 143 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(RuleType type) {
/* 150 */     this.type = type;
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
/* 161 */     if (rhs == null) return false; 
/* 162 */     if (this == rhs) return true; 
/* 163 */     if (!(rhs instanceof Rule)) return false; 
/* 164 */     Rule that = (Rule)rhs;
/* 165 */     if (getId() != null) return getId().equals(that.getId()); 
/* 166 */     return (that.getId() == null);
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
/* 177 */     if (this.hashValue == 0) {
/* 178 */       int result = 17;
/* 179 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 180 */       result = result * 37 + itemIdValue;
/* 181 */       this.hashValue = result;
/*     */     } 
/* 183 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */