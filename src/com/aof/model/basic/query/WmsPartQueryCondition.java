/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsPartQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final WmsPartQueryCondition ID_EQ = new WmsPartQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final WmsPartQueryCondition ID_BEGINWITH = new WmsPartQueryCondition(
/* 11 */       "id_beginwith");
/* 12 */   public static final WmsPartQueryCondition ENABLED_EQ = new WmsPartQueryCondition(
/* 13 */       "enabled_eq");
/* 14 */   public static final WmsPartQueryCondition NAME_EQ = new WmsPartQueryCondition(
/* 15 */       "name_eq");
/* 16 */   public static final WmsPartQueryCondition DESCRIBE1_EQ = new WmsPartQueryCondition(
/* 17 */       "describe1_eq");
/* 18 */   public static final WmsPartQueryCondition DESCRIBE2_EQ = new WmsPartQueryCondition(
/* 19 */       "describe2_eq");
/* 20 */   public static final WmsPartQueryCondition UNIT_EQ = new WmsPartQueryCondition(
/* 21 */       "unit_eq");
/* 22 */   public static final WmsPartQueryCondition SUPPLIER_EQ = new WmsPartQueryCondition(
/* 23 */       "supplier_eq");
/* 24 */   public static final WmsPartQueryCondition PARTCODE = new WmsPartQueryCondition(
/* 25 */       "partCode");
/* 26 */   public static final WmsPartQueryCondition WORKCENTER = new WmsPartQueryCondition(
/* 27 */       "workCenter");
/* 28 */   public static final WmsPartQueryCondition QUALITYPE = new WmsPartQueryCondition(
/* 29 */       "qualityType");
/* 30 */   public static final WmsPartQueryCondition GROUP = new WmsPartQueryCondition(
/* 31 */       "group");
/*    */   
/* 33 */   public static final WmsPartQueryCondition DATE_EQ = new WmsPartQueryCondition(
/* 34 */       "date_eq");
/*    */   
/* 36 */   public static final WmsPartQueryCondition IDEQ = new WmsPartQueryCondition(
/* 37 */       "idEq");
/* 38 */   public static final WmsPartQueryCondition FREEZE_STATUS_EQ = new WmsPartQueryCondition(
/* 39 */       "freeze_status_eq");
/* 40 */   public static final WmsPartQueryCondition PRODUCTCLASS_EQ = new WmsPartQueryCondition(
/* 41 */       "productclass_eq");
/* 42 */   public static final WmsPartQueryCondition ASN_EQ = new WmsPartQueryCondition(
/* 43 */       "asn_eq");
/*    */   
/*    */   protected WmsPartQueryCondition(String value) {
/* 46 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsPartQueryCondition getEnum(String value) {
/* 50 */     return (WmsPartQueryCondition)getEnum(WmsPartQueryCondition.class, 
/* 51 */         value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/WmsPartQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */