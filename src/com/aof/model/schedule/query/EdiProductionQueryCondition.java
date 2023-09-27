/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class EdiProductionQueryCondition
/*    */   extends Enum {
/*  7 */   public static final EdiProductionQueryCondition ID_EQ = new EdiProductionQueryCondition("id_eq");
/*  8 */   public static final EdiProductionQueryCondition ID_IN = new EdiProductionQueryCondition("id_in");
/*  9 */   public static final EdiProductionQueryCondition STATUS_NOT_EQ = new EdiProductionQueryCondition("STATUS_NOT_EQ");
/* 10 */   public static final EdiProductionQueryCondition TYPE_EQ = new EdiProductionQueryCondition("TYPE_EQ");
/* 11 */   public static final EdiProductionQueryCondition SUPPLIER_EQ = new EdiProductionQueryCondition("supplier_eq");
/* 12 */   public static final EdiProductionQueryCondition UPLOADER_EQ = new EdiProductionQueryCondition("uploader_eq");
/* 13 */   public static final EdiProductionQueryCondition UPLOADTIME_EQ = new EdiProductionQueryCondition("uploadtime_eq");
/* 14 */   public static final EdiProductionQueryCondition UPLOADFILENAME_EQ = new EdiProductionQueryCondition("uploadfilename_eq");
/* 15 */   public static final EdiProductionQueryCondition HANDSTATUS_EQ = new EdiProductionQueryCondition("handStatus_eq");
/* 16 */   public static final EdiProductionQueryCondition ENABLED_EQ = new EdiProductionQueryCondition("enabled_eq");
/* 17 */   public static final EdiProductionQueryCondition ENABLED_NE = new EdiProductionQueryCondition("enabled_ne");
/*    */   
/*    */   protected EdiProductionQueryCondition(String name) {
/* 20 */     super(name);
/*    */   }
/*    */   
/*    */   public static EdiProductionQueryCondition getEnum(String value) {
/* 24 */     return (EdiProductionQueryCondition)getEnum(EdiProductionQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/EdiProductionQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */