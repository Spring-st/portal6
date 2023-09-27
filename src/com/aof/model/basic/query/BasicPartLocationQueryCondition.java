/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicPartLocationQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BasicPartLocationQueryCondition ID_EQ = new BasicPartLocationQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final BasicPartLocationQueryCondition CODE_EQ = new BasicPartLocationQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final BasicPartLocationQueryCondition TYPE_EQ = new BasicPartLocationQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final BasicPartLocationQueryCondition MANUAL_EQ = new BasicPartLocationQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final BasicPartLocationQueryCondition ENABLED_EQ = new BasicPartLocationQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final BasicPartLocationQueryCondition CODE_LIKE = new BasicPartLocationQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final BasicPartLocationQueryCondition IS_ENABLED_EQ = new BasicPartLocationQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final BasicPartLocationQueryCondition DESCRIBE2_EQ = new BasicPartLocationQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final BasicPartLocationQueryCondition DESCRIBE1_EQ = new BasicPartLocationQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final BasicPartLocationQueryCondition PART_EQ = new BasicPartLocationQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected BasicPartLocationQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static BasicPartLocationQueryCondition getEnum(String value) {
/* 34 */     return (BasicPartLocationQueryCondition)getEnum(
/* 35 */         BasicPartLocationQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BasicPartLocationQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */