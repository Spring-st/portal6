/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderInspectionPendingQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderInspectionPendingQueryCondition ID_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final PurchaseOrderInspectionPendingQueryCondition ID_IN = new PurchaseOrderInspectionPendingQueryCondition(
/* 11 */       "id_in");
/*    */   
/* 13 */   public static final PurchaseOrderInspectionPendingQueryCondition PO_ID_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 14 */       "po_id_eq");
/* 15 */   public static final PurchaseOrderInspectionPendingQueryCondition ID_BEGINWITH = new PurchaseOrderInspectionPendingQueryCondition(
/* 16 */       "ID_BEGINWITH");
/* 17 */   public static final PurchaseOrderInspectionPendingQueryCondition SITE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 18 */       "site_eq");
/* 19 */   public static final PurchaseOrderInspectionPendingQueryCondition SITE_ID_IN = new PurchaseOrderInspectionPendingQueryCondition(
/* 20 */       "site_id_in");
/* 21 */   public static final PurchaseOrderInspectionPendingQueryCondition DEPARTMENT_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 22 */       "department_eq");
/* 23 */   public static final PurchaseOrderInspectionPendingQueryCondition STARTPODATE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 24 */       "startPoDate_eq");
/* 25 */   public static final PurchaseOrderInspectionPendingQueryCondition ENDPODATE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 26 */       "endPoDate_eq");
/* 27 */   public static final PurchaseOrderInspectionPendingQueryCondition SUPPLIER_ID_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 28 */       "supplierid_eq");
/* 29 */   public static final PurchaseOrderInspectionPendingQueryCondition SUPPLIER_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 30 */       "supplier_eq");
/* 31 */   public static final PurchaseOrderInspectionPendingQueryCondition SUPPLIERCODE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 32 */       "supplierCode_eq");
/* 33 */   public static final PurchaseOrderInspectionPendingQueryCondition STATUS_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 34 */       "status_eq");
/* 35 */   public static final PurchaseOrderInspectionPendingQueryCondition PONUMBER_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 36 */       "poNumber_eq");
/* 37 */   public static final PurchaseOrderInspectionPendingQueryCondition LINE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 38 */       "line_eq");
/* 39 */   public static final PurchaseOrderInspectionPendingQueryCondition ITEMNUMBER_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 40 */       "itemNumber_eq");
/* 41 */   public static final PurchaseOrderInspectionPendingQueryCondition ITEM_ID_NOT_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 42 */       "item_id_not_eq");
/* 43 */   public static final PurchaseOrderInspectionPendingQueryCondition SUPPLIERITEM_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 44 */       "supplierItemNumber_eq");
/* 45 */   public static final PurchaseOrderInspectionPendingQueryCondition UM_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 46 */       "um_eq");
/* 47 */   public static final PurchaseOrderInspectionPendingQueryCondition DUEDATE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 48 */       "dueDate_eq");
/* 49 */   public static final PurchaseOrderInspectionPendingQueryCondition CREATEDATE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 50 */       "createDate_eq");
/* 51 */   public static final PurchaseOrderInspectionPendingQueryCondition RECEIPTQTY_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 52 */       "receiptQty_eq");
/* 53 */   public static final PurchaseOrderInspectionPendingQueryCondition RECEIVDATE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 54 */       "receivingDate_eq");
/* 55 */   public static final PurchaseOrderInspectionPendingQueryCondition ISPRINT_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 56 */       "isPrint_eq");
/*    */   
/* 58 */   public static final PurchaseOrderInspectionPendingQueryCondition LOTSER_ITEM_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 59 */       "lotser_item_eq");
/* 60 */   public static final PurchaseOrderInspectionPendingQueryCondition FILECODE = new PurchaseOrderInspectionPendingQueryCondition(
/* 61 */       "planfilecode");
/* 62 */   public static final PurchaseOrderInspectionPendingQueryCondition PROPLANTIME = new PurchaseOrderInspectionPendingQueryCondition(
/* 63 */       "proplantime");
/* 64 */   public static final PurchaseOrderInspectionPendingQueryCondition UPLOADDATE = new PurchaseOrderInspectionPendingQueryCondition(
/* 65 */       "uploadDate");
/* 66 */   public static final PurchaseOrderInspectionPendingQueryCondition ISSUCCESS = new PurchaseOrderInspectionPendingQueryCondition(
/* 67 */       "issuccess");
/*    */ 
/*    */   
/* 70 */   public static final PurchaseOrderInspectionPendingQueryCondition qtyOpen_DT = new PurchaseOrderInspectionPendingQueryCondition(
/* 71 */       "qtyOpen_DT");
/* 72 */   public static final PurchaseOrderInspectionPendingQueryCondition ISCONFIRMPO_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 73 */       "ifConfirmPO_eq");
/* 74 */   public static final PurchaseOrderInspectionPendingQueryCondition ISTXT_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 75 */       "istxt_eq");
/* 76 */   public static final PurchaseOrderInspectionPendingQueryCondition IS_NOTTXT_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 77 */       "isnottxt_eq");
/* 78 */   public static final PurchaseOrderInspectionPendingQueryCondition ITEMSTATUS_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 79 */       "itemstatus_eq");
/* 80 */   public static final PurchaseOrderInspectionPendingQueryCondition NOTINITEMSTATUS_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 81 */       "notinitemstatus_eq");
/* 82 */   public static final PurchaseOrderInspectionPendingQueryCondition CREATETYPE_EQ = new PurchaseOrderInspectionPendingQueryCondition(
/* 83 */       "createtype_eq");
/*    */   
/*    */   protected PurchaseOrderInspectionPendingQueryCondition(String value) {
/* 86 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static PurchaseOrderInspectionPendingQueryCondition getEnum(String value) {
/* 91 */     return (PurchaseOrderInspectionPendingQueryCondition)getEnum(
/* 92 */         PurchaseOrderInspectionPendingQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderInspectionPendingQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */