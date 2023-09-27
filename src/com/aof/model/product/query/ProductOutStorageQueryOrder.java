/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductOutStorageQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProductOutStorageQueryOrder ID = new ProductOutStorageQueryOrder("id");
/*    */   protected ProductOutStorageQueryOrder(String value) {
/*  9 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductOutStorageQueryOrder getEnum(String value) {
/* 13 */     return (ProductOutStorageQueryOrder)getEnum(ProductOutStorageQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductOutStorageQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */