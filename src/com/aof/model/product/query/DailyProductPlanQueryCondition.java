/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class DailyProductPlanQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final DailyProductPlanQueryCondition ID_EQ = new DailyProductPlanQueryCondition("id_eq");
/*    */   
/*    */   protected DailyProductPlanQueryCondition(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static DailyProductPlanQueryCondition getEnum(String value) {
/* 15 */     return (DailyProductPlanQueryCondition)getEnum(DailyProductPlanQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/DailyProductPlanQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */