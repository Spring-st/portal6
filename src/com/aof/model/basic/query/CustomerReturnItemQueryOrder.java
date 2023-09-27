/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerReturnItemQueryOrder
/*    */   extends Enum {
/*  7 */   public static final CustomerReturnItemQueryOrder ID = new CustomerReturnItemQueryOrder("id");
/*    */   protected CustomerReturnItemQueryOrder(String name) {
/*  9 */     super(name);
/*    */   }
/*    */   
/*    */   public static CustomerReturnItemQueryOrder getEnum(String value) {
/* 13 */     return (CustomerReturnItemQueryOrder)getEnum(CustomerReturnItemQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CustomerReturnItemQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */