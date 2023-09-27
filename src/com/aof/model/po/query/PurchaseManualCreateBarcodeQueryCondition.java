/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseManualCreateBarcodeQueryCondition
/*    */   extends Enum {
/*  7 */   public static final PurchaseManualCreateBarcodeQueryCondition id_EQ = new PurchaseManualCreateBarcodeQueryCondition("id_eq");
/*  8 */   public static final PurchaseManualCreateBarcodeQueryCondition ID_IN = new PurchaseManualCreateBarcodeQueryCondition("id_in");
/*  9 */   public static final PurchaseManualCreateBarcodeQueryCondition part_EQ = new PurchaseManualCreateBarcodeQueryCondition("part_eq");
/* 10 */   public static final PurchaseManualCreateBarcodeQueryCondition supplierCode_EQ = new PurchaseManualCreateBarcodeQueryCondition("supplierCode_eq");
/* 11 */   public static final PurchaseManualCreateBarcodeQueryCondition serialDate_EQ = new PurchaseManualCreateBarcodeQueryCondition("serialDate_eq");
/* 12 */   public static final PurchaseManualCreateBarcodeQueryCondition serialNumber_EQ = new PurchaseManualCreateBarcodeQueryCondition("serialNumber_eq");
/* 13 */   public static final PurchaseManualCreateBarcodeQueryCondition qty_EQ = new PurchaseManualCreateBarcodeQueryCondition("qty_eq");
/*    */   protected PurchaseManualCreateBarcodeQueryCondition(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   public static PurchaseManualCreateBarcodeQueryCondition getPurchasemanualcreatebarcodeQueryCondition(String value) {
/* 18 */     return (PurchaseManualCreateBarcodeQueryCondition)getEnum(PurchaseManualCreateBarcodeQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseManualCreateBarcodeQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */