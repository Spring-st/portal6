/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderInspectionPendingQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderInspectionPendingQueryOrder ID = new PurchaseOrderInspectionPendingQueryOrder("id");
/*  9 */   public static final PurchaseOrderInspectionPendingQueryOrder STATUS = new PurchaseOrderInspectionPendingQueryOrder("status");
/*    */   
/*    */   protected PurchaseOrderInspectionPendingQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderInspectionPendingQueryOrder getEnum(String value) {
/* 16 */     return (PurchaseOrderInspectionPendingQueryOrder)getEnum(PurchaseOrderInspectionPendingQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderInspectionPendingQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */