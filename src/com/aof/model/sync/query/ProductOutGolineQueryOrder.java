/*    */ package com.aof.model.sync.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductOutGolineQueryOrder
/*    */   extends Enum
/*    */ {
/* 12 */   public static final ProductOutGolineQueryOrder ID = new ProductOutGolineQueryOrder("xxsh_worc_number");
/*    */ 
/*    */   
/* 15 */   public static final ProductOutGolineQueryOrder ITEM = new ProductOutGolineQueryOrder("xxsh_worc_item");
/*    */ 
/*    */ 
/*    */   
/*    */   protected ProductOutGolineQueryOrder(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductOutGolineQueryOrder getEnum(String value) {
/* 24 */     return (ProductOutGolineQueryOrder)getEnum(ProductOutGolineQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/query/ProductOutGolineQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */