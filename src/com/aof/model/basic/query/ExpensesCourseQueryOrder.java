/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ExpensesCourseQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ExpensesCourseQueryOrder ID = new ExpensesCourseQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ExpensesCourseQueryOrder ENABLED = new ExpensesCourseQueryOrder("enabled");
/*    */   
/*    */   protected ExpensesCourseQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpensesCourseQueryOrder getEnum(String value) {
/* 18 */     return (ExpensesCourseQueryOrder)getEnum(ExpensesCourseQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ExpensesCourseQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */