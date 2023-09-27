/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseManualCreateBarcodeQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseManualCreateBarcodeQueryOrder id = new PurchaseManualCreateBarcodeQueryOrder("id");
/*  8 */   public static final PurchaseManualCreateBarcodeQueryOrder part = new PurchaseManualCreateBarcodeQueryOrder("part");
/*  9 */   public static final PurchaseManualCreateBarcodeQueryOrder supplierCode = new PurchaseManualCreateBarcodeQueryOrder("supplierCode");
/* 10 */   public static final PurchaseManualCreateBarcodeQueryOrder serialDate = new PurchaseManualCreateBarcodeQueryOrder("serialDate");
/* 11 */   public static final PurchaseManualCreateBarcodeQueryOrder serialNumber = new PurchaseManualCreateBarcodeQueryOrder("serialNumber");
/* 12 */   public static final PurchaseManualCreateBarcodeQueryOrder qty = new PurchaseManualCreateBarcodeQueryOrder("qty");
/*    */   protected PurchaseManualCreateBarcodeQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   public static PurchaseManualCreateBarcodeQueryOrder getPurchasemanualcreatebarcodeQueryOrder(String value) {
/* 17 */     return (PurchaseManualCreateBarcodeQueryOrder)getEnum(PurchaseManualCreateBarcodeQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseManualCreateBarcodeQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */