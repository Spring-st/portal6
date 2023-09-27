/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProductQueryOrder ID = new ProductQueryOrder("id");
/*  8 */   public static final ProductQueryOrder ENGNAME = new ProductQueryOrder("engName");
/*  9 */   public static final ProductQueryOrder CHNNAME = new ProductQueryOrder("chnName");
/* 10 */   public static final ProductQueryOrder ENABLED = new ProductQueryOrder("enabled");
/*    */   
/*    */   protected ProductQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductQueryOrder getEnum(String value) {
/* 17 */     return (ProductQueryOrder)getEnum(ProductQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */