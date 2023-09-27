/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductOutStorageQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProductOutStorageQueryCondition ID_EQ = new ProductOutStorageQueryCondition("id_eq");
/*  8 */   public static final ProductOutStorageQueryCondition HNCCODE_EQ = new ProductOutStorageQueryCondition("hncCode_eq");
/*    */   protected ProductOutStorageQueryCondition(String value) {
/* 10 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductOutStorageQueryCondition getEnum(String value) {
/* 14 */     return (ProductOutStorageQueryCondition)getEnum(ProductOutStorageQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductOutStorageQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */