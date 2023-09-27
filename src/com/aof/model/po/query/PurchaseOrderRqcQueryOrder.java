/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderRqcQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderRqcQueryOrder ID = new PurchaseOrderRqcQueryOrder("id");
/*  8 */   public static final PurchaseOrderRqcQueryOrder ENGNAME = new PurchaseOrderRqcQueryOrder("engName");
/*  9 */   public static final PurchaseOrderRqcQueryOrder CHNNAME = new PurchaseOrderRqcQueryOrder("chnName");
/* 10 */   public static final PurchaseOrderRqcQueryOrder ENABLED = new PurchaseOrderRqcQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseOrderRqcQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderRqcQueryOrder getEnum(String value) {
/* 17 */     return (PurchaseOrderRqcQueryOrder)getEnum(PurchaseOrderRqcQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderRqcQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */