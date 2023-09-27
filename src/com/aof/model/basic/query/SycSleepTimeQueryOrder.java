/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SycSleepTimeQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SycSleepTimeQueryOrder ID = new SycSleepTimeQueryOrder("id");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SycSleepTimeQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static SycSleepTimeQueryOrder getEnum(String value) {
/* 18 */     return (SycSleepTimeQueryOrder)getEnum(SycSleepTimeQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/SycSleepTimeQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */