/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderPutInStorageQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderPutInStorageQueryCondition ID_EQ = new PurchaseOrderPutInStorageQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final PurchaseOrderPutInStorageQueryCondition ID_BEGINWITH = new PurchaseOrderPutInStorageQueryCondition(
/* 11 */       "id_beginwith");
/*    */   
/*    */   protected PurchaseOrderPutInStorageQueryCondition(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderPutInStorageQueryCondition getEnum(String value) {
/* 18 */     return (PurchaseOrderPutInStorageQueryCondition)getEnum(
/* 19 */         PurchaseOrderPutInStorageQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderPutInStorageQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */