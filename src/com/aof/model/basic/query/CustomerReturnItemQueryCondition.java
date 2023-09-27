/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerReturnItemQueryCondition
/*    */   extends Enum {
/*  7 */   public static final CustomerReturnItemQueryCondition ID_EQ = new CustomerReturnItemQueryCondition("id_eq");
/*  8 */   public static final CustomerReturnItemQueryCondition CUSTOMERRETURNS_ID_EQ = new CustomerReturnItemQueryCondition("customerreturns_id_eq");
/*    */   
/*    */   protected CustomerReturnItemQueryCondition(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static CustomerReturnItemQueryCondition getEnum(String value) {
/* 15 */     return (CustomerReturnItemQueryCondition)getEnum(CustomerReturnItemQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CustomerReturnItemQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */