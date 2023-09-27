/*     */ package com.aof.ruleengine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EngineRule
/*     */ {
/*     */   private Object externalId;
/*     */   private int nextSeqPass;
/*     */   private int nextSeqFail;
/*     */   private List conditions;
/*     */   private Object consequencesPass;
/*     */   private Object consequencesFail;
/*     */   int inDegree;
/*     */   EngineRule nextRulePass;
/*     */   EngineRule nextRuleFail;
/*     */   EngineFlow flow;
/*     */   
/*     */   public EngineRule(Object externalId) {
/*  48 */     this.externalId = externalId;
/*  49 */     this.conditions = new ArrayList();
/*  50 */     this.nextSeqPass = -1;
/*  51 */     this.nextSeqFail = -1;
/*  52 */     this.flow = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getExternalId() {
/*  61 */     return this.externalId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearConditions() {
/*  69 */     if (this.flow != null && this.flow.locking.get() == null)
/*  70 */       throw new RuntimeException("Lock flow before clear rule condition."); 
/*  71 */     this.conditions.clear();
/*     */   }
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
/*     */   public void addCondition(String compareSource, int comparePassCondition, Object compareTarget) {
/*  85 */     if (this.flow != null && this.flow.locking.get() == null)
/*  86 */       throw new RuntimeException("Lock flow before add rule condition."); 
/*  87 */     EngineCondition condition = new EngineCondition(compareSource, comparePassCondition, compareTarget);
/*  88 */     this.conditions.add(condition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List getConditions() {
/*  95 */     return this.conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object getConsequencesPass() {
/* 102 */     return this.consequencesPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsequencesPass(Object consequencesPass) {
/* 110 */     if (this.flow != null && this.flow.locking.get() == null)
/* 111 */       throw new RuntimeException("Lock flow before change rule consequence."); 
/* 112 */     this.consequencesPass = consequencesPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConsequencesFail() {
/* 119 */     return this.consequencesFail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsequencesFail(Object consequencesFail) {
/* 127 */     if (this.flow != null && this.flow.locking.get() == null)
/* 128 */       throw new RuntimeException("Lock flow before change rule consequence."); 
/* 129 */     this.consequencesFail = consequencesFail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextSeqFail() {
/* 136 */     return this.nextSeqFail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextSeqFail(int nextSeqFail) {
/* 144 */     if (this.flow != null && this.flow.locking.get() == null)
/* 145 */       throw new RuntimeException("Lock flow before change next seq when fail."); 
/* 146 */     if (this.nextSeqFail != nextSeqFail) {
/* 147 */       this.nextSeqFail = nextSeqFail;
/* 148 */       if (this.flow != null) {
/* 149 */         this.flow.canUse = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextSeqPass() {
/* 157 */     return this.nextSeqPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextSeqPass(int nextSeqPass) {
/* 165 */     if (this.flow != null && this.flow.locking.get() == null)
/* 166 */       throw new RuntimeException("Lock flow before change next seq when pass."); 
/* 167 */     if (this.nextSeqPass != nextSeqPass) {
/* 168 */       this.nextSeqPass = nextSeqPass;
/* 169 */       if (this.flow != null)
/* 170 */         this.flow.canUse = false; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/ruleengine/EngineRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */