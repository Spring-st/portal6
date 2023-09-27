/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PoPartSumNumberQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PoPartSumNumberQueryOrder PART_ID = new PoPartSumNumberQueryOrder("part");
/*  8 */   public static final PoPartSumNumberQueryOrder PART_YEARFROM1 = new PoPartSumNumberQueryOrder("part_yearFrom1");
/*    */   
/*    */   protected PoPartSumNumberQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static PoPartSumNumberQueryOrder getEnum(String value) {
/* 15 */     return (PoPartSumNumberQueryOrder)getEnum(PoPartSumNumberQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/PoPartSumNumberQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */