/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class FinishedToolPutnumberQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final FinishedToolPutnumberQueryCondition ID_EQ = new FinishedToolPutnumberQueryCondition("id_eq");
/*    */ 
/*    */   
/* 12 */   public static final FinishedToolPutnumberQueryCondition TOOLCODE_LIKE = new FinishedToolPutnumberQueryCondition("toolCode_like");
/*    */   
/* 14 */   public static final FinishedToolPutnumberQueryCondition FINISHEDCODE_LIKE = new FinishedToolPutnumberQueryCondition("finishedCode_like");
/*    */   
/* 16 */   public static final FinishedToolPutnumberQueryCondition FINISHEDCODE_EQ = new FinishedToolPutnumberQueryCondition("finishedCode_eq");
/*    */   
/* 18 */   public static final FinishedToolPutnumberQueryCondition ENABLED_EQ = new FinishedToolPutnumberQueryCondition("enabled_eq");
/*    */   
/*    */   protected FinishedToolPutnumberQueryCondition(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static FinishedToolPutnumberQueryCondition getEnum(String value) {
/* 25 */     return (FinishedToolPutnumberQueryCondition)getEnum(FinishedToolPutnumberQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FinishedToolPutnumberQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */