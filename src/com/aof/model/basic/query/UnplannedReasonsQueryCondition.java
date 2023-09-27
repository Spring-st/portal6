/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedReasonsQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final UnplannedReasonsQueryCondition ID_EQ = new UnplannedReasonsQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final UnplannedReasonsQueryCondition CODE_EQ = new UnplannedReasonsQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final UnplannedReasonsQueryCondition TYPE_EQ = new UnplannedReasonsQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final UnplannedReasonsQueryCondition MANUAL_EQ = new UnplannedReasonsQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final UnplannedReasonsQueryCondition ENABLED_EQ = new UnplannedReasonsQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final UnplannedReasonsQueryCondition CODE_LIKE = new UnplannedReasonsQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final UnplannedReasonsQueryCondition IS_ENABLED_EQ = new UnplannedReasonsQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final UnplannedReasonsQueryCondition DESCRIBE2_EQ = new UnplannedReasonsQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final UnplannedReasonsQueryCondition DESCRIBE1_EQ = new UnplannedReasonsQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final UnplannedReasonsQueryCondition PART_EQ = new UnplannedReasonsQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected UnplannedReasonsQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedReasonsQueryCondition getEnum(String value) {
/* 34 */     return (UnplannedReasonsQueryCondition)getEnum(
/* 35 */         UnplannedReasonsQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/UnplannedReasonsQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */