/*     */ package com.aof.model.business.rule;
/*     */ 
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
/*     */ public abstract class AbstractFlowRule
/*     */   implements Serializable
/*     */ {
/*  21 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Flow flow;
/*     */ 
/*     */ 
/*     */   
/*     */   private int seq;
/*     */ 
/*     */   
/*     */   private Rule rule;
/*     */ 
/*     */   
/*     */   private int nextSeqWhenPass;
/*     */ 
/*     */   
/*     */   private int nextSeqWhenFail;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFlowRule(Flow flow, int seq) {
/*  43 */     setFlow(flow);
/*  44 */     setSeq(seq);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFlowRule() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow getFlow() {
/*  55 */     return this.flow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlow(Flow flow) {
/*  62 */     this.hashValue = 0;
/*  63 */     this.flow = flow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextSeqWhenFail() {
/*  70 */     return this.nextSeqWhenFail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextSeqWhenFail(int nextSeqWhenFail) {
/*  77 */     this.nextSeqWhenFail = nextSeqWhenFail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextSeqWhenPass() {
/*  84 */     return this.nextSeqWhenPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextSeqWhenPass(int nextSeqWhenPass) {
/*  91 */     this.nextSeqWhenPass = nextSeqWhenPass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule() {
/*  98 */     return this.rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRule(Rule rule) {
/* 105 */     this.rule = rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeq() {
/* 112 */     this.hashValue = 0;
/* 113 */     return this.seq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeq(int seq) {
/* 120 */     this.seq = seq;
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
/* 131 */     if (rhs == null) return false; 
/* 132 */     if (this == rhs) return true; 
/* 133 */     if (!(rhs instanceof FlowRule)) return false; 
/* 134 */     FlowRule that = (FlowRule)rhs;
/* 135 */     if (getFlow() != null && 
/* 136 */       !getFlow().equals(that.getFlow())) return false;
/*     */     
/* 138 */     return (getSeq() == that.getSeq());
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
/* 149 */     if (this.hashValue == 0) {
/* 150 */       int result = 17;
/* 151 */       int itemFlowValue = (getFlow() == null) ? 0 : getFlow().hashCode();
/* 152 */       result = result * 37 + itemFlowValue;
/* 153 */       result = result * 37 + getSeq();
/* 154 */       this.hashValue = result;
/*     */     } 
/* 156 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/AbstractFlowRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */