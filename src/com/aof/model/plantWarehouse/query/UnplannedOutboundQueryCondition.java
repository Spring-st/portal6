/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedOutboundQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final UnplannedOutboundQueryCondition ID_EQ = new UnplannedOutboundQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final UnplannedOutboundQueryCondition ID_BEGINWITH = new UnplannedOutboundQueryCondition(
/* 11 */       "id_beginwith");
/* 12 */   public static final UnplannedOutboundQueryCondition DESCRIBE_EQ = new UnplannedOutboundQueryCondition(
/* 13 */       "describe_eq");
/* 14 */   public static final UnplannedOutboundQueryCondition ENABLED_EQ = new UnplannedOutboundQueryCondition(
/* 15 */       "enabled_eq");
/* 16 */   public static final UnplannedOutboundQueryCondition CODE_EQ = new UnplannedOutboundQueryCondition(
/* 17 */       "code_eq");
/* 18 */   public static final UnplannedOutboundQueryCondition DELEIVER_EQ = new UnplannedOutboundQueryCondition(
/* 19 */       "deliver_eq");
/* 20 */   public static final UnplannedOutboundQueryCondition DEATETIME_EQ = new UnplannedOutboundQueryCondition(
/* 21 */       "datetime_eq");
/* 22 */   public static final UnplannedOutboundQueryCondition ENDTIME_EQ = new UnplannedOutboundQueryCondition(
/* 23 */       "endtime_eq");
/* 24 */   public static final UnplannedOutboundQueryCondition STATUS_EQ = new UnplannedOutboundQueryCondition(
/* 25 */       "status_eq");
/* 26 */   public static final UnplannedOutboundQueryCondition USER_EQ = new UnplannedOutboundQueryCondition(
/* 27 */       "user_eq");
/* 28 */   public static final UnplannedOutboundQueryCondition TYPE_EQ = new UnplannedOutboundQueryCondition(
/* 29 */       "type_eq");
/*    */   
/*    */   protected UnplannedOutboundQueryCondition(String value) {
/* 32 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedOutboundQueryCondition getEnum(String value) {
/* 36 */     return (UnplannedOutboundQueryCondition)getEnum(
/* 37 */         UnplannedOutboundQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/UnplannedOutboundQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */