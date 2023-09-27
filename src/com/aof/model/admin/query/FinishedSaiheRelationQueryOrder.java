/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class FinishedSaiheRelationQueryOrder extends Enum {
/*  6 */   public static final FinishedSaiheRelationQueryOrder ID = new FinishedSaiheRelationQueryOrder("id");
/*    */   
/*  8 */   public static final FinishedSaiheRelationQueryOrder SAIHECODE = new FinishedSaiheRelationQueryOrder("saiheCode");
/*    */   
/* 10 */   public static final FinishedSaiheRelationQueryOrder FINISHEDCODE = new FinishedSaiheRelationQueryOrder("finishedCode");
/*    */   protected FinishedSaiheRelationQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static FinishedSaiheRelationQueryOrder getEnum(String value) {
/* 16 */     return (FinishedSaiheRelationQueryOrder)getEnum(FinishedSaiheRelationQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FinishedSaiheRelationQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */