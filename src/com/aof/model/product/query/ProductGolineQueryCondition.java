/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductGolineQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProductGolineQueryCondition ID_EQ = new ProductGolineQueryCondition("id_eq");
/*  8 */   public static final ProductGolineQueryCondition TOTAL_NUMBER_EQ = new ProductGolineQueryCondition("total_number_eq");
/*  9 */   public static final ProductGolineQueryCondition SH_CODE_EQ = new ProductGolineQueryCondition("sh_code_eq");
/* 10 */   public static final ProductGolineQueryCondition STATUS_EQ = new ProductGolineQueryCondition("status_eq");
/* 11 */   public static final ProductGolineQueryCondition STATUS_NE = new ProductGolineQueryCondition("status_ne");
/* 12 */   public static final ProductGolineQueryCondition HNC_CODE_EQ = new ProductGolineQueryCondition("hncCode_eq");
/* 13 */   public static final ProductGolineQueryCondition LOCATION_CODE_EQ = new ProductGolineQueryCondition(
/* 14 */       "location_code_eq");
/* 15 */   public static final ProductGolineQueryCondition LOCATION_CODE_NE = new ProductGolineQueryCondition(
/* 16 */       "location_code_ne");
/*    */   
/* 18 */   public static final ProductGolineQueryCondition STOREROOM_TYPE_NE = new ProductGolineQueryCondition("locationCode.basic_storeroom_id.type_ne");
/*    */   
/*    */   protected ProductGolineQueryCondition(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductGolineQueryCondition getEnum(String value) {
/* 25 */     return (ProductGolineQueryCondition)getEnum(ProductGolineQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductGolineQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */