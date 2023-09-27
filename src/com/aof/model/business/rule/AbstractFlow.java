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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractFlow
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
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
/*     */   private Set rules;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFlow(Integer id) {
/*  50 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFlow() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  61 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  68 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  75 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  82 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  89 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  96 */     this.hashValue = 0;
/*  97 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getRules() {
/* 104 */     return this.rules;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRules(Set rules) {
/* 111 */     this.rules = rules;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/* 118 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 125 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleType getType() {
/* 132 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(RuleType type) {
/* 139 */     this.type = type;
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
/* 150 */     if (rhs == null) return false; 
/* 151 */     if (this == rhs) return true; 
/* 152 */     if (!(rhs instanceof Flow)) return false; 
/* 153 */     Flow that = (Flow)rhs;
/* 154 */     if (getId() != null) return getId().equals(that.getId()); 
/* 155 */     return (that.getId() == null);
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
/* 166 */     if (this.hashValue == 0) {
/* 167 */       int result = 17;
/* 168 */       int itemIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 169 */       result = result * 37 + itemIdValue;
/* 170 */       this.hashValue = result;
/*     */     } 
/* 172 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractFlow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */