/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesWorkorderQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesWorkorderQueryCondition ID_EQ = new SalesWorkorderQueryCondition("id_eq");
/*  9 */   public static final SalesWorkorderQueryCondition SHIP_ORDER_ID_EQ = new SalesWorkorderQueryCondition("ship_order_id_eq");
/* 10 */   public static final SalesWorkorderQueryCondition SHIPWORKORDER_STATUS_EQ = new SalesWorkorderQueryCondition("shipworkorder_status_eq");
/* 11 */   public static final SalesWorkorderQueryCondition CUSTOMERCODE_EQ = new SalesWorkorderQueryCondition("customercode_eq");
/* 12 */   public static final SalesWorkorderQueryCondition SHIPITEM_ID_EQ = new SalesWorkorderQueryCondition("shipitem_id_eq");
/* 13 */   public static final SalesWorkorderQueryCondition SHIPWORKORDER_STATUS_GE = new SalesWorkorderQueryCondition("shipworkorder_status_ge");
/*    */ 
/*    */   
/* 16 */   public static final SalesWorkorderQueryCondition ID_IN = new SalesWorkorderQueryCondition("id_in");
/*    */   protected SalesWorkorderQueryCondition(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesWorkorderQueryCondition getEnum(String value) {
/* 22 */     return (SalesWorkorderQueryCondition)getEnum(SalesWorkorderQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesWorkorderQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */