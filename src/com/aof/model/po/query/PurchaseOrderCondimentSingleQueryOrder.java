/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderCondimentSingleQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderCondimentSingleQueryOrder ID = new PurchaseOrderCondimentSingleQueryOrder("id");
/*  8 */   public static final PurchaseOrderCondimentSingleQueryOrder ENGNAME = new PurchaseOrderCondimentSingleQueryOrder("engName");
/*  9 */   public static final PurchaseOrderCondimentSingleQueryOrder CHNNAME = new PurchaseOrderCondimentSingleQueryOrder("chnName");
/* 10 */   public static final PurchaseOrderCondimentSingleQueryOrder ENABLED = new PurchaseOrderCondimentSingleQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseOrderCondimentSingleQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderCondimentSingleQueryOrder getEnum(String value) {
/* 17 */     return (PurchaseOrderCondimentSingleQueryOrder)getEnum(PurchaseOrderCondimentSingleQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderCondimentSingleQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */