/*    */ package com.aof.model.sync.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductOutGolineQueryCondition
/*    */   extends Enum
/*    */ {
/* 11 */   public static final ProductOutGolineQueryCondition ID_EQ = new ProductOutGolineQueryCondition("id_eq");
/* 12 */   public static final ProductOutGolineQueryCondition XXSH_WORC_ITEM_EQ = new ProductOutGolineQueryCondition("xxsh_worc_item_eq");
/* 13 */   public static final ProductOutGolineQueryCondition XXSH_WORC_ITEM_LIKE = new ProductOutGolineQueryCondition("xxsh_worc_item_like");
/* 14 */   public static final ProductOutGolineQueryCondition DATE_GT = new ProductOutGolineQueryCondition("xxsh_worc_cr_date_gt");
/* 15 */   public static final ProductOutGolineQueryCondition DATE_LT = new ProductOutGolineQueryCondition("xxsh_worc_cr_date_lt");
/* 16 */   public static final ProductOutGolineQueryCondition XXSH_WORC_STATUS_EQ = new ProductOutGolineQueryCondition("xxsh_worc_status_eq");
/*    */   
/*    */   protected ProductOutGolineQueryCondition(String value) {
/* 19 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductOutGolineQueryCondition getEnum(String value) {
/* 23 */     return (ProductOutGolineQueryCondition)getEnum(ProductOutGolineQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/query/ProductOutGolineQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */