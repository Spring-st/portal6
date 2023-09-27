/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SycSleepTimeQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SycSleepTimeQueryCondition ID_EQ = new SycSleepTimeQueryCondition("id_eq");
/*  9 */   public static final SycSleepTimeQueryCondition TYPE_EQ = new SycSleepTimeQueryCondition("type_eq");
/* 10 */   public static final SycSleepTimeQueryCondition SLEEPTIME_EQ = new SycSleepTimeQueryCondition("sleepTime_eq");
/*    */   
/*    */   protected SycSleepTimeQueryCondition(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static SycSleepTimeQueryCondition getEnum(String value) {
/* 17 */     return (SycSleepTimeQueryCondition)getEnum(SycSleepTimeQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/SycSleepTimeQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */