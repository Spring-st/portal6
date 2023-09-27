/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlowQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final FlowQueryCondition ID_EQ = new FlowQueryCondition("id_eq");
/* 14 */   public static final FlowQueryCondition DESCRIPTION_LIKE = new FlowQueryCondition("description_like");
/* 15 */   public static final FlowQueryCondition ENABLED_EQ = new FlowQueryCondition("enabled_eq");
/* 16 */   public static final FlowQueryCondition SITE_ID_EQ = new FlowQueryCondition("site_id_eq");
/* 17 */   public static final FlowQueryCondition TYPE_EQ = new FlowQueryCondition("type_eq");
/*    */   
/*    */   protected FlowQueryCondition(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static FlowQueryCondition getEnum(String value) {
/* 24 */     return (FlowQueryCondition)getEnum(FlowQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/FlowQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */