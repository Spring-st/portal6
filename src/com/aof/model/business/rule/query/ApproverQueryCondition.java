/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import com.aof.model.metadata.RuleType;
/*    */ 
/*    */ 
/*    */ public class ApproverQueryCondition
/*    */ {
/*    */   private Integer siteId;
/*    */   private Integer departmentId;
/*    */   private RuleType ruleType;
/*    */   private String name;
/*    */   
/*    */   public Integer getDepartmentId() {
/* 14 */     return this.departmentId;
/*    */   }
/*    */   public void setDepartmentId(Integer departmentId) {
/* 17 */     this.departmentId = departmentId;
/*    */   }
/*    */   public RuleType getRuleType() {
/* 20 */     return this.ruleType;
/*    */   }
/*    */   public void setRuleType(RuleType ruleType) {
/* 23 */     this.ruleType = ruleType;
/*    */   }
/*    */   public Integer getSiteId() {
/* 26 */     return this.siteId;
/*    */   }
/*    */   public void setSiteId(Integer siteId) {
/* 29 */     this.siteId = siteId;
/*    */   }
/*    */   public String getName() {
/* 32 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 35 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/ApproverQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */