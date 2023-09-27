/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuleQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final RuleQueryCondition ID_EQ = new RuleQueryCondition("id_eq");
/* 14 */   public static final RuleQueryCondition DESCRIPTION_LIKE = new RuleQueryCondition("description_like");
/* 15 */   public static final RuleQueryCondition ENABLED_EQ = new RuleQueryCondition("enabled_eq");
/* 16 */   public static final RuleQueryCondition SITE_ID_EQ = new RuleQueryCondition("site_id_eq");
/* 17 */   public static final RuleQueryCondition TYPE_EQ = new RuleQueryCondition("type_eq");
/*    */   
/*    */   protected RuleQueryCondition(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static RuleQueryCondition getEnum(String value) {
/* 24 */     return (RuleQueryCondition)getEnum(RuleQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/RuleQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */