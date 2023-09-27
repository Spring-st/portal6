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
/*    */ public class CustomerQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final CustomerQueryOrder CODE = new CustomerQueryOrder("code");
/* 15 */   public static final CustomerQueryOrder DESCRIPTION = new CustomerQueryOrder("description");
/*    */ 
/*    */ 
/*    */   
/*    */   protected CustomerQueryOrder(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static CustomerQueryOrder getEnum(String value) {
/* 24 */     return (CustomerQueryOrder)getEnum(CustomerQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/CustomerQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */