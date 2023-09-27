/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CustomerreturnsQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CustomerreturnsQueryOrder ID = new CustomerreturnsQueryOrder("id");
/*  9 */   public static final CustomerreturnsQueryOrder RETURNNUMBER = new CustomerreturnsQueryOrder("returnnumber");
/* 10 */   public static final CustomerreturnsQueryOrder DELSTATUS = new CustomerreturnsQueryOrder("delstatus");
/* 11 */   public static final CustomerreturnsQueryOrder CUSTOMERCODE = new CustomerreturnsQueryOrder("customerCode");
/*    */ 
/*    */   
/*    */   protected CustomerreturnsQueryOrder(String name) {
/* 15 */     super(name);
/*    */   }
/*    */   
/*    */   public static CustomerreturnsQueryOrder getEnum(String value) {
/* 19 */     return (CustomerreturnsQueryOrder)getEnum(CustomerreturnsQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CustomerreturnsQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */