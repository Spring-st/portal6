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
/*    */ 
/*    */ public class PriceQueryCondition
/*    */   extends Enum
/*    */ {
/* 15 */   public static final PriceQueryCondition ID_EQ = new PriceQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public static final PriceQueryCondition HOTEL_ID_EQ = new PriceQueryCondition("hotel_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 27 */   public static final PriceQueryCondition ROOM_LIKE = new PriceQueryCondition("room_like");
/*    */   
/* 29 */   public static final PriceQueryCondition PRICE_EQ = new PriceQueryCondition("price_eq");
/*    */   
/* 31 */   public static final PriceQueryCondition DISCOUNT_EQ = new PriceQueryCondition("discount_eq");
/*    */   
/* 33 */   public static final PriceQueryCondition DESCRIPTION_LIKE = new PriceQueryCondition("description_like");
/*    */   
/* 35 */   public static final PriceQueryCondition ENABLED_EQ = new PriceQueryCondition("enabled_eq");
/*    */ 
/*    */   
/* 38 */   public static final PriceQueryCondition HOTEL_CITY_ID_EQ = new PriceQueryCondition("hotel_city_id_eq");
/*    */   
/*    */   protected PriceQueryCondition(String value) {
/* 41 */     super(value);
/*    */   }
/*    */   
/*    */   public static PriceQueryCondition getEnum(String value) {
/* 45 */     return (PriceQueryCondition)getEnum(PriceQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PriceQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */