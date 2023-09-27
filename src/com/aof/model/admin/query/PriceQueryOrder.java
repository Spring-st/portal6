/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PriceQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final PriceQueryOrder ID = new PriceQueryOrder("id");
/*    */ 
/*    */   
/* 17 */   public static final PriceQueryOrder ROOM = new PriceQueryOrder("room");
/* 18 */   public static final PriceQueryOrder PRICE = new PriceQueryOrder("price");
/* 19 */   public static final PriceQueryOrder DISCOUNT = new PriceQueryOrder("discount");
/* 20 */   public static final PriceQueryOrder DESCRIPTION = new PriceQueryOrder("description");
/* 21 */   public static final PriceQueryOrder ENABLED = new PriceQueryOrder("enabled");
/*    */   
/*    */   protected PriceQueryOrder(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static PriceQueryOrder getEnum(String value) {
/* 28 */     return (PriceQueryOrder)getEnum(PriceQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PriceQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */