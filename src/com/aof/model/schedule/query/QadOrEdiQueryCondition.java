/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class QadOrEdiQueryCondition
/*    */   extends Enum {
/*  7 */   public static final QadOrEdiQueryCondition ID_EQ = new QadOrEdiQueryCondition("id_eq");
/*  8 */   public static final QadOrEdiQueryCondition MODELS_EQ = new QadOrEdiQueryCondition("models_eq");
/*    */   
/*    */   protected QadOrEdiQueryCondition(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static QadOrEdiQueryCondition getEnum(String value) {
/* 15 */     return (QadOrEdiQueryCondition)getEnum(QadOrEdiQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/QadOrEdiQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */