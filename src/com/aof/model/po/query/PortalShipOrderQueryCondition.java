/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PortalShipOrderQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PortalShipOrderQueryCondition ID_EQ = new PortalShipOrderQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final PortalShipOrderQueryCondition SITE_EQ = new PortalShipOrderQueryCondition(
/* 11 */       "site_eq");
/* 12 */   public static final PortalShipOrderQueryCondition CODE_EQ = new PortalShipOrderQueryCondition(
/* 13 */       "code_eq");
/*    */ 
/*    */   
/* 16 */   public static final PortalShipOrderQueryCondition DEPARTMENT_EQ = new PortalShipOrderQueryCondition(
/* 17 */       "department_eq");
/* 18 */   public static final PortalShipOrderQueryCondition CREATEDATE_GE = new PortalShipOrderQueryCondition(
/* 19 */       "createDate_ge");
/* 20 */   public static final PortalShipOrderQueryCondition CREATEDATE_LT = new PortalShipOrderQueryCondition(
/* 21 */       "createDate_lt");
/* 22 */   public static final PortalShipOrderQueryCondition ENABLED_EQ = new PortalShipOrderQueryCondition(
/* 23 */       "enabled_eq");
/* 24 */   public static final PortalShipOrderQueryCondition APPROVEREQUESTID_EQ = new PortalShipOrderQueryCondition(
/* 25 */       "approverequestid_eq");
/* 26 */   public static final PortalShipOrderQueryCondition PORTALSHIPORDER_STATUS_EQ = new PortalShipOrderQueryCondition(
/* 27 */       "portalShipOrder_status_eq");
/* 28 */   public static final PortalShipOrderQueryCondition PO_CODE_EQ = new PortalShipOrderQueryCondition(
/* 29 */       "po_code_eq");
/* 30 */   public static final PortalShipOrderQueryCondition PART_CODE_EQ = new PortalShipOrderQueryCondition(
/* 31 */       "part_code_eq");
/* 32 */   public static final PortalShipOrderQueryCondition ID_BEGINWITH = new PortalShipOrderQueryCondition(
/* 33 */       "ID_BEGINWITH");
/* 34 */   public static final PortalShipOrderQueryCondition STATUS_CONFIRM_EQ = new PortalShipOrderQueryCondition(
/* 35 */       "status_confirm_eq");
/* 36 */   public static final PortalShipOrderQueryCondition STATUS_EQ = new PortalShipOrderQueryCondition(
/* 37 */       "status_eq");
/* 38 */   public static final PortalShipOrderQueryCondition PO_NUMBER_EQ = new PortalShipOrderQueryCondition("po_name_eq");
/* 39 */   public static final PortalShipOrderQueryCondition TYPE_EQ = new PortalShipOrderQueryCondition("type_eq");
/* 40 */   public static final PortalShipOrderQueryCondition SUPPLIER_ID_EQ = new PortalShipOrderQueryCondition("supplier_id_eq");
/* 41 */   public static final PortalShipOrderQueryCondition CREATETYPE_EQ = new PortalShipOrderQueryCondition("createtype_eq");
/* 42 */   public static final PortalShipOrderQueryCondition SYNC_STATUS_EQ = new PortalShipOrderQueryCondition("sync_status_eq");
/* 43 */   public static final PortalShipOrderQueryCondition SO_ID_EQ = new PortalShipOrderQueryCondition("so_id_eq");
/* 44 */   public static final PortalShipOrderQueryCondition CREATEDATE_EQ = new PortalShipOrderQueryCondition("createDate_eq");
/* 45 */   public static final PortalShipOrderQueryCondition ARRIVALDATE_EQ = new PortalShipOrderQueryCondition("arrivalDate_eq");
/* 46 */   public static final PortalShipOrderQueryCondition PART_ID_EQ = new PortalShipOrderQueryCondition("part_id_eq");
/*    */   
/* 48 */   public static final PortalShipOrderQueryCondition PO_ITEM_EQ = new PortalShipOrderQueryCondition("po_item_eq");
/*    */   protected PortalShipOrderQueryCondition(String value) {
/* 50 */     super(value);
/*    */   }
/*    */   
/*    */   public static PortalShipOrderQueryCondition getEnum(String value) {
/* 54 */     return (PortalShipOrderQueryCondition)getEnum(
/* 55 */         PortalShipOrderQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PortalShipOrderQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */