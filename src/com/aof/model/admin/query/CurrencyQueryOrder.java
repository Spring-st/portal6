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
/*    */ 
/*    */ public class CurrencyQueryOrder
/*    */   extends Enum
/*    */ {
/* 20 */   public static final CurrencyQueryOrder CODE = new CurrencyQueryOrder("code");
/* 21 */   public static final CurrencyQueryOrder STATUS = new CurrencyQueryOrder("enabled");
/*    */   
/*    */   protected CurrencyQueryOrder(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static CurrencyQueryOrder getEnum(String value) {
/* 28 */     return (CurrencyQueryOrder)getEnum(CurrencyQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CurrencyQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */