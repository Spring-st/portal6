/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class RuleQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final RuleQueryOrder ID = new RuleQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final RuleQueryOrder DESCRIPTION = new RuleQueryOrder("description");
/* 12 */   public static final RuleQueryOrder ENABLED = new RuleQueryOrder("enabled");
/*    */   
/*    */   protected RuleQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static RuleQueryOrder getEnum(String value) {
/* 19 */     return (RuleQueryOrder)getEnum(RuleQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/RuleQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */