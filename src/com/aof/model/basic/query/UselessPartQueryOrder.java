/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UselessPartQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final UselessPartQueryOrder ID = new UselessPartQueryOrder("id");
/*    */ 
/*    */ 
/*    */   
/*    */   protected UselessPartQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static UselessPartQueryOrder getEnum(String value) {
/* 17 */     return (UselessPartQueryOrder)getEnum(UselessPartQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/UselessPartQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */