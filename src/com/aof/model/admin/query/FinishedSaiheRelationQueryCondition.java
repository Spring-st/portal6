/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class FinishedSaiheRelationQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final FinishedSaiheRelationQueryCondition ID_EQ = new FinishedSaiheRelationQueryCondition("id_eq");
/*    */ 
/*    */   
/* 12 */   public static final FinishedSaiheRelationQueryCondition SAIHECODE_LIKE = new FinishedSaiheRelationQueryCondition("saiheCode_like");
/*    */   
/* 14 */   public static final FinishedSaiheRelationQueryCondition FINISHEDCODE_LIKE = new FinishedSaiheRelationQueryCondition("finishedCode_like");
/*    */   
/* 16 */   public static final FinishedSaiheRelationQueryCondition FINISHEDPRODUCTDESC_LIKE = new FinishedSaiheRelationQueryCondition("finishedProductDesc_like");
/*    */   
/* 18 */   public static final FinishedSaiheRelationQueryCondition ENABLED_EQ = new FinishedSaiheRelationQueryCondition("enabled_eq");
/*    */   
/* 20 */   public static final FinishedSaiheRelationQueryCondition SAIHECODE_EQ = new FinishedSaiheRelationQueryCondition("saiheCode_eq");
/*    */   
/* 22 */   public static final FinishedSaiheRelationQueryCondition FINISHEDCODE_EQ = new FinishedSaiheRelationQueryCondition("finishedCode_eq");
/*    */   
/*    */   protected FinishedSaiheRelationQueryCondition(String value) {
/* 25 */     super(value);
/*    */   }
/*    */   
/*    */   public static FinishedSaiheRelationQueryCondition getEnum(String value) {
/* 29 */     return (FinishedSaiheRelationQueryCondition)getEnum(FinishedSaiheRelationQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FinishedSaiheRelationQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */