/*    */ package com.aof.model.business.rule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class FlowQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final FlowQueryOrder ID = new FlowQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final FlowQueryOrder DESCRIPTION = new FlowQueryOrder("description");
/* 12 */   public static final FlowQueryOrder ENABLED = new FlowQueryOrder("enabled");
/*    */   
/*    */   protected FlowQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static FlowQueryOrder getEnum(String value) {
/* 19 */     return (FlowQueryOrder)getEnum(FlowQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/rule/query/FlowQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */