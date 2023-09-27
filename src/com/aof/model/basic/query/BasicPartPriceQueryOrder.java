/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicPartPriceQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BasicPartPriceQueryOrder ID = new BasicPartPriceQueryOrder("id");
/*    */   
/*    */   protected BasicPartPriceQueryOrder(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static BasicPartPriceQueryOrder getEnum(String value) {
/* 15 */     return (BasicPartPriceQueryOrder)getEnum(BasicPartPriceQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BasicPartPriceQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */