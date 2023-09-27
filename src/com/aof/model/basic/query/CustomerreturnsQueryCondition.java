/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerreturnsQueryCondition
/*    */   extends Enum {
/*  7 */   public static final CustomerreturnsQueryCondition ID_EQ = new CustomerreturnsQueryCondition("id_eq");
/*  8 */   public static final CustomerreturnsQueryCondition RETURNNUMBER_EQ = new CustomerreturnsQueryCondition("returnnumber_eq");
/*  9 */   public static final CustomerreturnsQueryCondition RETURNDATE_GE = new CustomerreturnsQueryCondition("returndate_ge");
/* 10 */   public static final CustomerreturnsQueryCondition RETURNDATE_LT = new CustomerreturnsQueryCondition("returndate_lt");
/* 11 */   public static final CustomerreturnsQueryCondition DELSTATUS_EQ = new CustomerreturnsQueryCondition("delstatus_eq");
/* 12 */   public static final CustomerreturnsQueryCondition CUSTOMERCODE_EQ = new CustomerreturnsQueryCondition("customercode_eq");
/* 13 */   public static final CustomerreturnsQueryCondition CUSTOMER_SITE_ID_EQ = new CustomerreturnsQueryCondition("customer_site_id_eq");
/* 14 */   public static final CustomerreturnsQueryCondition RETURNNUMBER_LIKE = new CustomerreturnsQueryCondition("returnnumber_like");
/*    */   
/*    */   protected CustomerreturnsQueryCondition(String name) {
/* 17 */     super(name);
/*    */   }
/*    */   
/*    */   public static CustomerreturnsQueryCondition getEnum(String value) {
/* 21 */     return (CustomerreturnsQueryCondition)getEnum(CustomerreturnsQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CustomerreturnsQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */