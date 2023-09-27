/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SalesOrderItemQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SalesOrderItemQueryCondition ID_EQ = new SalesOrderItemQueryCondition("id_eq");
/*  9 */   public static final SalesOrderItemQueryCondition QTYOPEN_DT = new SalesOrderItemQueryCondition("qtyopen_dt");
/* 10 */   public static final SalesOrderItemQueryCondition SOIPNUMBER_EQ = new SalesOrderItemQueryCondition("soipnumber_eq");
/* 11 */   public static final SalesOrderItemQueryCondition ITEMNUMBER_EQ = new SalesOrderItemQueryCondition("itemnumber_eq");
/* 12 */   public static final SalesOrderItemQueryCondition DUEDATE_EQ = new SalesOrderItemQueryCondition("duedate_eq");
/* 13 */   public static final SalesOrderItemQueryCondition STATUS_EQ = new SalesOrderItemQueryCondition("status_eq");
/* 14 */   public static final SalesOrderItemQueryCondition STATUS_OPEN_EQ = new SalesOrderItemQueryCondition("status_open_eq");
/*    */   protected SalesOrderItemQueryCondition(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static SalesOrderItemQueryCondition getEnum(String value) {
/* 20 */     return (SalesOrderItemQueryCondition)getEnum(SalesOrderItemQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/SalesOrderItemQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */