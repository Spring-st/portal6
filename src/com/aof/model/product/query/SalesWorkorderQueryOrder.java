/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesWorkorderQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesWorkorderQueryOrder ID = new SalesWorkorderQueryOrder("id");
/*  9 */   public static final SalesWorkorderQueryOrder LOTSER_ID = new SalesWorkorderQueryOrder("lotSer_id");
/* 10 */   public static final SalesWorkorderQueryOrder SHIP_ID = new SalesWorkorderQueryOrder("ship_id");
/* 11 */   public static final SalesWorkorderQueryOrder OUT_DATE = new SalesWorkorderQueryOrder("out_date");
/*    */   
/*    */   protected SalesWorkorderQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesWorkorderQueryOrder getEnum(String value) {
/* 18 */     return (SalesWorkorderQueryOrder)getEnum(SalesWorkorderQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesWorkorderQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */