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
/*    */ public class FunctionQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final FunctionQueryOrder ID = new FunctionQueryOrder("id");
/* 15 */   public static final FunctionQueryOrder NAME = new FunctionQueryOrder("name");
/* 16 */   public static final FunctionQueryOrder DESCRIPTION = new FunctionQueryOrder("description");
/*    */ 
/*    */   
/*    */   protected FunctionQueryOrder(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static FunctionQueryOrder getEnum(String value) {
/* 24 */     return (FunctionQueryOrder)getEnum(FunctionQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FunctionQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */