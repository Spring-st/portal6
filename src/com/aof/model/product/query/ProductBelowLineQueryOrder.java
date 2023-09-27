/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductBelowLineQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProductBelowLineQueryOrder ID = new ProductBelowLineQueryOrder("id");
/*  8 */   public static final ProductBelowLineQueryOrder ENGNAME = new ProductBelowLineQueryOrder("engName");
/*  9 */   public static final ProductBelowLineQueryOrder CHNNAME = new ProductBelowLineQueryOrder("chnName");
/* 10 */   public static final ProductBelowLineQueryOrder ENABLED = new ProductBelowLineQueryOrder("enabled");
/*    */   
/*    */   protected ProductBelowLineQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductBelowLineQueryOrder getEnum(String value) {
/* 17 */     return (ProductBelowLineQueryOrder)getEnum(ProductBelowLineQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductBelowLineQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */