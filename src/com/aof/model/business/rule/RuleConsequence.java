/*    */ package com.aof.model.business.rule;
/*    */ 
/*    */ import com.aof.model.admin.User;
/*    */ import com.aof.model.metadata.YesNo;
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
/*    */ public class RuleConsequence
/*    */   extends AbstractRuleConsequence
/*    */   implements Serializable
/*    */ {
/*    */   public RuleConsequence() {
/* 26 */     setCanModify(YesNo.NO);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RuleConsequence(Rule rule, User approver) {
/* 35 */     super(rule, approver);
/* 36 */     setCanModify(YesNo.NO);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/RuleConsequence.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */