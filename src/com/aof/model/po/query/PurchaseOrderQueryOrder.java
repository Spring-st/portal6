/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderQueryOrder
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderQueryOrder ID = new PurchaseOrderQueryOrder("id");
/*  8 */   public static final PurchaseOrderQueryOrder ENGNAME = new PurchaseOrderQueryOrder("engName");
/*  9 */   public static final PurchaseOrderQueryOrder CHNNAME = new PurchaseOrderQueryOrder("chnName");
/* 10 */   public static final PurchaseOrderQueryOrder ENABLED = new PurchaseOrderQueryOrder("enabled");
/*    */   
/* 12 */   public static final PurchaseOrderQueryOrder IN_DATE = new PurchaseOrderQueryOrder("in_date");
/*    */   
/*    */   protected PurchaseOrderQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderQueryOrder getEnum(String value) {
/* 19 */     return (PurchaseOrderQueryOrder)getEnum(PurchaseOrderQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */