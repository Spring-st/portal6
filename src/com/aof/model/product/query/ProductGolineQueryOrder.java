/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductGolineQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProductGolineQueryOrder ID = new ProductGolineQueryOrder("id");
/*  8 */   public static final ProductGolineQueryOrder HNC_CODE = new ProductGolineQueryOrder("hnc_code");
/*    */   
/*    */   protected ProductGolineQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductGolineQueryOrder getEnum(String value) {
/* 15 */     return (ProductGolineQueryOrder)getEnum(ProductGolineQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductGolineQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */