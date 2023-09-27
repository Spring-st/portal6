/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BadReasonsQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final BadReasonsQueryOrder ID = new BadReasonsQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final BadReasonsQueryOrder ENABLED = new BadReasonsQueryOrder("enabled");
/*    */   
/*    */   protected BadReasonsQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static BadReasonsQueryOrder getEnum(String value) {
/* 18 */     return (BadReasonsQueryOrder)getEnum(BadReasonsQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/BadReasonsQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */