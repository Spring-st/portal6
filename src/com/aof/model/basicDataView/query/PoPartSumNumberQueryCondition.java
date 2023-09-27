/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PoPartSumNumberQueryCondition
/*    */   extends Enum {
/*  7 */   public static final PoPartSumNumberQueryCondition PART_EQ = new PoPartSumNumberQueryCondition("part_eq");
/*    */   
/*    */   protected PoPartSumNumberQueryCondition(String value) {
/* 10 */     super(value);
/*    */   }
/*    */   
/*    */   public static PoPartSumNumberQueryCondition getEnum(String value) {
/* 14 */     return (PoPartSumNumberQueryCondition)getEnum(PoPartSumNumberQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/PoPartSumNumberQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */