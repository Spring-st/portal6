/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsPlanToGoOutQueryCondition extends Enum {
/*  6 */   public static final WmsPlanToGoOutQueryCondition ID_EQ = new WmsPlanToGoOutQueryCondition("id_eq");
/*  7 */   public static final WmsPlanToGoOutQueryCondition DESCRIBE_EQ = new WmsPlanToGoOutQueryCondition("describe_eq");
/*  8 */   public static final WmsPlanToGoOutQueryCondition ENABLED_EQ = new WmsPlanToGoOutQueryCondition("enabled_eq");
/*  9 */   public static final WmsPlanToGoOutQueryCondition ID_BEGINWITH = new WmsPlanToGoOutQueryCondition("ID_BEGINWITH");
/*    */   
/* 11 */   public static final WmsPlanToGoOutQueryCondition CODE_EQ = new WmsPlanToGoOutQueryCondition("code_eq");
/* 12 */   public static final WmsPlanToGoOutQueryCondition DELEIVER_EQ = new WmsPlanToGoOutQueryCondition("deliver_eq");
/* 13 */   public static final WmsPlanToGoOutQueryCondition DEATETIME_EQ = new WmsPlanToGoOutQueryCondition("datetime_eq");
/* 14 */   public static final WmsPlanToGoOutQueryCondition ENDTIME_EQ = new WmsPlanToGoOutQueryCondition("endtime_eq");
/* 15 */   public static final WmsPlanToGoOutQueryCondition STATUS_EQ = new WmsPlanToGoOutQueryCondition("status_eq");
/* 16 */   public static final WmsPlanToGoOutQueryCondition USER_EQ = new WmsPlanToGoOutQueryCondition("user_eq");
/* 17 */   public static final WmsPlanToGoOutQueryCondition TYPE_EQ = new WmsPlanToGoOutQueryCondition("type_eq");
/*    */   
/*    */   protected WmsPlanToGoOutQueryCondition(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsPlanToGoOutQueryCondition getEnum(String value) {
/* 24 */     return (WmsPlanToGoOutQueryCondition)getEnum(
/* 25 */         WmsPlanToGoOutQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/WmsPlanToGoOutQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */