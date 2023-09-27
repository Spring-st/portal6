/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsUnplannedWarehousingQueryCondition extends Enum {
/*  6 */   public static final WmsUnplannedWarehousingQueryCondition ID_EQ = new WmsUnplannedWarehousingQueryCondition("id_eq");
/*  7 */   public static final WmsUnplannedWarehousingQueryCondition DESCRIBE_EQ = new WmsUnplannedWarehousingQueryCondition("describe_eq");
/*  8 */   public static final WmsUnplannedWarehousingQueryCondition ENABLED_EQ = new WmsUnplannedWarehousingQueryCondition("enabled_eq");
/*  9 */   public static final WmsUnplannedWarehousingQueryCondition ID_BEGINWITH = new WmsUnplannedWarehousingQueryCondition("ID_BEGINWITH");
/* 10 */   public static final WmsUnplannedWarehousingQueryCondition STATUS = new WmsUnplannedWarehousingQueryCondition("status");
/* 11 */   public static final WmsUnplannedWarehousingQueryCondition STARTTIME = new WmsUnplannedWarehousingQueryCondition("starttime");
/* 12 */   public static final WmsUnplannedWarehousingQueryCondition ENDTIME = new WmsUnplannedWarehousingQueryCondition("endtime");
/* 13 */   public static final WmsUnplannedWarehousingQueryCondition DELIVER = new WmsUnplannedWarehousingQueryCondition("deliver");
/* 14 */   public static final WmsUnplannedWarehousingQueryCondition STATUS_EQ = new WmsUnplannedWarehousingQueryCondition("status_eq");
/* 15 */   public static final WmsUnplannedWarehousingQueryCondition USER_EQ = new WmsUnplannedWarehousingQueryCondition("user_eq");
/* 16 */   public static final WmsUnplannedWarehousingQueryCondition TYPE_EQ = new WmsUnplannedWarehousingQueryCondition("type_eq");
/* 17 */   public static final WmsUnplannedWarehousingQueryCondition CODE_EQ = new WmsUnplannedWarehousingQueryCondition("code_eq");
/*    */   
/*    */   protected WmsUnplannedWarehousingQueryCondition(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsUnplannedWarehousingQueryCondition getEnum(String value) {
/* 24 */     return (WmsUnplannedWarehousingQueryCondition)getEnum(
/* 25 */         WmsUnplannedWarehousingQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/WmsUnplannedWarehousingQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */