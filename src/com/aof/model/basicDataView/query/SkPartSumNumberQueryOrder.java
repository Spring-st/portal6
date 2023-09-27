/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SkPartSumNumberQueryOrder
/*    */   extends Enum {
/*  7 */   public static final SkPartSumNumberQueryOrder PART_ID = new SkPartSumNumberQueryOrder("part");
/*  8 */   public static final SkPartSumNumberQueryOrder SUMNUMBER = new SkPartSumNumberQueryOrder("sumNumber");
/*  9 */   public static final SkPartSumNumberQueryOrder COUNT = new SkPartSumNumberQueryOrder("counnt");
/*    */   
/*    */   protected SkPartSumNumberQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static SkPartSumNumberQueryOrder getEnum(String value) {
/* 16 */     return (SkPartSumNumberQueryOrder)getEnum(SkPartSumNumberQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/SkPartSumNumberQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */