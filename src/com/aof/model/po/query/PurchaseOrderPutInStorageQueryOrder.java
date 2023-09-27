/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderPutInStorageQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderPutInStorageQueryOrder ID = new PurchaseOrderPutInStorageQueryOrder("id");
/*  8 */   public static final PurchaseOrderPutInStorageQueryOrder ENGNAME = new PurchaseOrderPutInStorageQueryOrder("engName");
/*  9 */   public static final PurchaseOrderPutInStorageQueryOrder CHNNAME = new PurchaseOrderPutInStorageQueryOrder("chnName");
/* 10 */   public static final PurchaseOrderPutInStorageQueryOrder ENABLED = new PurchaseOrderPutInStorageQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseOrderPutInStorageQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderPutInStorageQueryOrder getEnum(String value) {
/* 17 */     return (PurchaseOrderPutInStorageQueryOrder)getEnum(PurchaseOrderPutInStorageQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderPutInStorageQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */