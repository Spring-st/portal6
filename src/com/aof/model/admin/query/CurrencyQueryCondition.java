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
/*    */ public class CurrencyQueryCondition
/*    */   extends Enum
/*    */ {
/* 18 */   public static final CurrencyQueryCondition CODE_LIKE = new CurrencyQueryCondition("code_like");
/* 19 */   public static final CurrencyQueryCondition NAME_LIKE = new CurrencyQueryCondition("name_like");
/*    */   
/* 21 */   public static final CurrencyQueryCondition STATUS_EQ = new CurrencyQueryCondition("status_eq");
/*    */   
/*    */   protected CurrencyQueryCondition(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static CurrencyQueryCondition getEnum(String value) {
/* 28 */     return (CurrencyQueryCondition)getEnum(CurrencyQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CurrencyQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */