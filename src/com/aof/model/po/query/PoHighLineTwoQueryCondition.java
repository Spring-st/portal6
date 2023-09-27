/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PoHighLineTwoQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PoHighLineTwoQueryCondition ID_EQ = new PoHighLineTwoQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final PoHighLineTwoQueryCondition SITE_EQ = new PoHighLineTwoQueryCondition(
/* 11 */       "site_eq");
/* 12 */   public static final PoHighLineTwoQueryCondition CODE_EQ = new PoHighLineTwoQueryCondition(
/* 13 */       "code_eq");
/*    */ 
/*    */   
/* 16 */   public static final PoHighLineTwoQueryCondition DEPARTMENT_EQ = new PoHighLineTwoQueryCondition(
/* 17 */       "department_eq");
/* 18 */   public static final PoHighLineTwoQueryCondition CREATEDATE_GE = new PoHighLineTwoQueryCondition(
/* 19 */       "createDate_ge");
/* 20 */   public static final PoHighLineTwoQueryCondition CREATEDATE_LT = new PoHighLineTwoQueryCondition(
/* 21 */       "createDate_lt");
/* 22 */   public static final PoHighLineTwoQueryCondition ENABLED_EQ = new PoHighLineTwoQueryCondition(
/* 23 */       "enabled_eq");
/* 24 */   public static final PoHighLineTwoQueryCondition APPROVEREQUESTID_EQ = new PoHighLineTwoQueryCondition(
/* 25 */       "approverequestid_eq");
/* 26 */   public static final PoHighLineTwoQueryCondition PORTALSHIPORDER_STATUS_EQ = new PoHighLineTwoQueryCondition(
/* 27 */       "portalShipOrder_status_eq");
/* 28 */   public static final PoHighLineTwoQueryCondition PO_CODE_EQ = new PoHighLineTwoQueryCondition(
/* 29 */       "po_code_eq");
/* 30 */   public static final PoHighLineTwoQueryCondition PART_CODE_EQ = new PoHighLineTwoQueryCondition(
/* 31 */       "part_code_eq");
/* 32 */   public static final PoHighLineTwoQueryCondition ID_BEGINWITH = new PoHighLineTwoQueryCondition(
/* 33 */       "ID_BEGINWITH");
/* 34 */   public static final PoHighLineTwoQueryCondition STATUS_CONFIRM_EQ = new PoHighLineTwoQueryCondition(
/* 35 */       "status_confirm_eq");
/* 36 */   public static final PoHighLineTwoQueryCondition STATUS_EQ = new PoHighLineTwoQueryCondition(
/* 37 */       "status_eq");
/*    */   
/*    */   protected PoHighLineTwoQueryCondition(String value) {
/* 40 */     super(value);
/*    */   }
/*    */   
/*    */   public static PoHighLineTwoQueryCondition getEnum(String value) {
/* 44 */     return (PoHighLineTwoQueryCondition)getEnum(
/* 45 */         PoHighLineTwoQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PoHighLineTwoQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */