/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderReceiptsQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderReceiptsQueryOrder ID = new PurchaseOrderReceiptsQueryOrder("id");
/*  8 */   public static final PurchaseOrderReceiptsQueryOrder ENGNAME = new PurchaseOrderReceiptsQueryOrder("engName");
/*  9 */   public static final PurchaseOrderReceiptsQueryOrder CHNNAME = new PurchaseOrderReceiptsQueryOrder("chnName");
/* 10 */   public static final PurchaseOrderReceiptsQueryOrder ENABLED = new PurchaseOrderReceiptsQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseOrderReceiptsQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderReceiptsQueryOrder getEnum(String value) {
/* 17 */     return (PurchaseOrderReceiptsQueryOrder)getEnum(PurchaseOrderReceiptsQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderReceiptsQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */