/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SkPartSumNumberQueryCondition
/*    */   extends Enum {
/*  7 */   public static final SkPartSumNumberQueryCondition PART_EQ = new SkPartSumNumberQueryCondition("part_eq");
/*    */   
/*    */   protected SkPartSumNumberQueryCondition(String value) {
/* 10 */     super(value);
/*    */   }
/*    */   
/*    */   public static SkPartSumNumberQueryCondition getEnum(String value) {
/* 14 */     return (SkPartSumNumberQueryCondition)getEnum(SkPartSumNumberQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/SkPartSumNumberQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */