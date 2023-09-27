/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class FinishedToolPutnumberQueryOrder extends Enum {
/*  6 */   public static final FinishedToolPutnumberQueryOrder ID = new FinishedToolPutnumberQueryOrder("id");
/*    */   
/*  8 */   public static final FinishedToolPutnumberQueryOrder FINISHEDCODE = new FinishedToolPutnumberQueryOrder("finishedCode");
/*    */   
/*    */   protected FinishedToolPutnumberQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static FinishedToolPutnumberQueryOrder getEnum(String value) {
/* 15 */     return (FinishedToolPutnumberQueryOrder)getEnum(FinishedToolPutnumberQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FinishedToolPutnumberQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */