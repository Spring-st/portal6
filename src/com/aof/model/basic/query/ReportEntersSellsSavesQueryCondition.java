/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ReportEntersSellsSavesQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ReportEntersSellsSavesQueryCondition ID_EQ = new ReportEntersSellsSavesQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final ReportEntersSellsSavesQueryCondition CODE_EQ = new ReportEntersSellsSavesQueryCondition(
/* 11 */       "code_eq");
/* 12 */   public static final ReportEntersSellsSavesQueryCondition TYPE_EQ = new ReportEntersSellsSavesQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final ReportEntersSellsSavesQueryCondition MANUAL_EQ = new ReportEntersSellsSavesQueryCondition(
/* 15 */       "manual_eq");
/* 16 */   public static final ReportEntersSellsSavesQueryCondition ENABLED_EQ = new ReportEntersSellsSavesQueryCondition(
/* 17 */       "enabled_eq");
/* 18 */   public static final ReportEntersSellsSavesQueryCondition CODE_LIKE = new ReportEntersSellsSavesQueryCondition(
/* 19 */       "code_like");
/* 20 */   public static final ReportEntersSellsSavesQueryCondition IS_ENABLED_EQ = new ReportEntersSellsSavesQueryCondition(
/* 21 */       "is_enabled_eq");
/* 22 */   public static final ReportEntersSellsSavesQueryCondition DESCRIBE2_EQ = new ReportEntersSellsSavesQueryCondition(
/* 23 */       "describe2_eq");
/* 24 */   public static final ReportEntersSellsSavesQueryCondition DESCRIBE1_EQ = new ReportEntersSellsSavesQueryCondition(
/* 25 */       "describe1_eq");
/* 26 */   public static final ReportEntersSellsSavesQueryCondition PART_EQ = new ReportEntersSellsSavesQueryCondition(
/* 27 */       "part_eq");
/*    */   
/*    */   protected ReportEntersSellsSavesQueryCondition(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static ReportEntersSellsSavesQueryCondition getEnum(String value) {
/* 34 */     return (ReportEntersSellsSavesQueryCondition)getEnum(
/* 35 */         ReportEntersSellsSavesQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ReportEntersSellsSavesQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */