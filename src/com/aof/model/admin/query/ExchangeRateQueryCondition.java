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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExchangeRateQueryCondition
/*    */   extends Enum
/*    */ {
/* 19 */   public static final ExchangeRateQueryCondition CURRENCY_CODE_LIKE = new ExchangeRateQueryCondition("currency_code_like");
/* 20 */   public static final ExchangeRateQueryCondition CURRENCY_CODE_EQ = new ExchangeRateQueryCondition("currency_code_eq");
/* 21 */   public static final ExchangeRateQueryCondition SITE_ID_EQ = new ExchangeRateQueryCondition("site_id_eq");
/* 22 */   public static final ExchangeRateQueryCondition CURRENCY_STATUS_EQ = new ExchangeRateQueryCondition("currency_status_eq");
/*    */   
/*    */   protected ExchangeRateQueryCondition(String value) {
/* 25 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExchangeRateQueryCondition getEnum(String value) {
/* 29 */     return (ExchangeRateQueryCondition)getEnum(ExchangeRateQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExchangeRateQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */