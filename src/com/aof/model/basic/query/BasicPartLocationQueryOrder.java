/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BasicPartLocationQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BasicPartLocationQueryOrder ID = new BasicPartLocationQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final BasicPartLocationQueryOrder ENABLED = new BasicPartLocationQueryOrder("enabled");
/*    */   
/*    */   protected BasicPartLocationQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static BasicPartLocationQueryOrder getEnum(String value) {
/* 18 */     return (BasicPartLocationQueryOrder)getEnum(BasicPartLocationQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BasicPartLocationQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */