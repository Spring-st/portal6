/*    */ package com.aof.model.comprehensive.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BoxAdjustmentQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BoxAdjustmentQueryCondition ID_EQ = new BoxAdjustmentQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final BoxAdjustmentQueryCondition ID_BEGINWITH = new BoxAdjustmentQueryCondition(
/* 11 */       "id_beginwith");
/* 12 */   public static final BoxAdjustmentQueryCondition DESCRIBE_EQ = new BoxAdjustmentQueryCondition(
/* 13 */       "describe_eq");
/* 14 */   public static final BoxAdjustmentQueryCondition ENABLED_EQ = new BoxAdjustmentQueryCondition(
/* 15 */       "enabled_eq");
/* 16 */   public static final BoxAdjustmentQueryCondition CODE_EQ = new BoxAdjustmentQueryCondition(
/* 17 */       "code_eq");
/* 18 */   public static final BoxAdjustmentQueryCondition DELEIVER_EQ = new BoxAdjustmentQueryCondition(
/* 19 */       "deliver_eq");
/* 20 */   public static final BoxAdjustmentQueryCondition DEATETIME_EQ = new BoxAdjustmentQueryCondition(
/* 21 */       "datetime_eq");
/* 22 */   public static final BoxAdjustmentQueryCondition ENDTIME_EQ = new BoxAdjustmentQueryCondition(
/* 23 */       "endtime_eq");
/* 24 */   public static final BoxAdjustmentQueryCondition STATUS_EQ = new BoxAdjustmentQueryCondition(
/* 25 */       "status_eq");
/* 26 */   public static final BoxAdjustmentQueryCondition USER_EQ = new BoxAdjustmentQueryCondition(
/* 27 */       "user_eq");
/* 28 */   public static final BoxAdjustmentQueryCondition TYPE_EQ = new BoxAdjustmentQueryCondition(
/* 29 */       "type_eq");
/*    */   
/*    */   protected BoxAdjustmentQueryCondition(String value) {
/* 32 */     super(value);
/*    */   }
/*    */   
/*    */   public static BoxAdjustmentQueryCondition getEnum(String value) {
/* 36 */     return (BoxAdjustmentQueryCondition)getEnum(
/* 37 */         BoxAdjustmentQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/query/BoxAdjustmentQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */