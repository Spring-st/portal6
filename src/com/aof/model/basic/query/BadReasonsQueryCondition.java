/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BadReasonsQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BadReasonsQueryCondition ID_EQ = new BadReasonsQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final BadReasonsQueryCondition CODE_EQ = new BadReasonsQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final BadReasonsQueryCondition TYPE_EQ = new BadReasonsQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final BadReasonsQueryCondition MANUAL_EQ = new BadReasonsQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final BadReasonsQueryCondition ENABLED_EQ = new BadReasonsQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final BadReasonsQueryCondition CODE_LIKE = new BadReasonsQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final BadReasonsQueryCondition IS_ENABLED_EQ = new BadReasonsQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final BadReasonsQueryCondition DESCRIBE2_EQ = new BadReasonsQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final BadReasonsQueryCondition DESCRIBE1_EQ = new BadReasonsQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final BadReasonsQueryCondition PART_EQ = new BadReasonsQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected BadReasonsQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static BadReasonsQueryCondition getEnum(String value) {
/* 34 */     return (BadReasonsQueryCondition)getEnum(
/* 35 */         BadReasonsQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BadReasonsQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */