/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedWarehousingQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final UnplannedWarehousingQueryCondition ID_EQ = new UnplannedWarehousingQueryCondition(
/*  9 */       "id_eq");
/*    */   
/* 11 */   public static final UnplannedWarehousingQueryCondition ID_BEGINWITH = new UnplannedWarehousingQueryCondition(
/* 12 */       "id_beginwith");
/* 13 */   public static final UnplannedWarehousingQueryCondition DESCRIBE_EQ = new UnplannedWarehousingQueryCondition(
/* 14 */       "describe_eq");
/* 15 */   public static final UnplannedWarehousingQueryCondition ENABLED_EQ = new UnplannedWarehousingQueryCondition(
/* 16 */       "enabled_eq");
/* 17 */   public static final UnplannedWarehousingQueryCondition STATUS = new UnplannedWarehousingQueryCondition(
/* 18 */       "status");
/* 19 */   public static final UnplannedWarehousingQueryCondition STARTTIME = new UnplannedWarehousingQueryCondition(
/* 20 */       "starttime");
/* 21 */   public static final UnplannedWarehousingQueryCondition ENDTIME = new UnplannedWarehousingQueryCondition(
/* 22 */       "endtime");
/* 23 */   public static final UnplannedWarehousingQueryCondition DELIVER = new UnplannedWarehousingQueryCondition(
/* 24 */       "deliver");
/* 25 */   public static final UnplannedWarehousingQueryCondition STATUS_EQ = new UnplannedWarehousingQueryCondition(
/* 26 */       "status_eq");
/* 27 */   public static final UnplannedWarehousingQueryCondition USER_EQ = new UnplannedWarehousingQueryCondition(
/* 28 */       "user_eq");
/* 29 */   public static final UnplannedWarehousingQueryCondition TYPE_EQ = new UnplannedWarehousingQueryCondition(
/* 30 */       "type_eq");
/* 31 */   public static final UnplannedWarehousingQueryCondition CODE_EQ = new UnplannedWarehousingQueryCondition(
/* 32 */       "code_eq");
/*    */   
/*    */   protected UnplannedWarehousingQueryCondition(String value) {
/* 35 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedWarehousingQueryCondition getEnum(String value) {
/* 39 */     return (UnplannedWarehousingQueryCondition)getEnum(
/* 40 */         UnplannedWarehousingQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/UnplannedWarehousingQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */