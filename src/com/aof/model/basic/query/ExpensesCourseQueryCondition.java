/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ExpensesCourseQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ExpensesCourseQueryCondition ID_EQ = new ExpensesCourseQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final ExpensesCourseQueryCondition CODE_EQ = new ExpensesCourseQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final ExpensesCourseQueryCondition TYPE_EQ = new ExpensesCourseQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final ExpensesCourseQueryCondition MANUAL_EQ = new ExpensesCourseQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final ExpensesCourseQueryCondition ENABLED_EQ = new ExpensesCourseQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final ExpensesCourseQueryCondition CODE_LIKE = new ExpensesCourseQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final ExpensesCourseQueryCondition IS_ENABLED_EQ = new ExpensesCourseQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final ExpensesCourseQueryCondition DESCRIBE2_EQ = new ExpensesCourseQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final ExpensesCourseQueryCondition DESCRIBE1_EQ = new ExpensesCourseQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final ExpensesCourseQueryCondition PART_EQ = new ExpensesCourseQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected ExpensesCourseQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpensesCourseQueryCondition getEnum(String value) {
/* 34 */     return (ExpensesCourseQueryCondition)getEnum(
/* 35 */         ExpensesCourseQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ExpensesCourseQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */