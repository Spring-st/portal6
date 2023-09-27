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
/*    */ public class ExchangeRateQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final ExchangeRateQueryOrder SITE_NAME = new ExchangeRateQueryOrder("siteName");
/* 20 */   public static final ExchangeRateQueryOrder CODE = new ExchangeRateQueryOrder("code");
/* 21 */   public static final ExchangeRateQueryOrder EXCHANGERATE = new ExchangeRateQueryOrder("exchangeRate");
/*    */ 
/*    */   
/*    */   protected ExchangeRateQueryOrder(String value) {
/* 25 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExchangeRateQueryOrder getEnum(String value) {
/* 29 */     return (ExchangeRateQueryOrder)getEnum(ExchangeRateQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExchangeRateQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */