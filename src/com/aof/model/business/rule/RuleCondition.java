/*    */ package com.aof.model.business.rule;
/*    */ 
/*    */ import com.aof.model.metadata.ConditionType;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuleCondition
/*    */   extends AbstractRuleCondition
/*    */   implements Serializable
/*    */ {
/*    */   private String displayValue;
/*    */   
/*    */   public RuleCondition() {}
/*    */   
/*    */   public RuleCondition(Rule rule, ConditionType type) {
/* 33 */     super(rule, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayValue() {
/* 44 */     return (this.displayValue == null) ? getValue() : this.displayValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDisplayValue(String displayValue) {
/* 52 */     this.displayValue = displayValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/RuleCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */