/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class DailyProductPlanQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final DailyProductPlanQueryOrder ID = new DailyProductPlanQueryOrder("id");
/*    */   
/*    */   protected DailyProductPlanQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static DailyProductPlanQueryOrder getEnum(String value) {
/* 15 */     return (DailyProductPlanQueryOrder)getEnum(DailyProductPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/DailyProductPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */