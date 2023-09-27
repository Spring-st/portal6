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
/*    */ public class FunctionQueryCondition
/*    */   extends Enum
/*    */ {
/* 14 */   public static final FunctionQueryCondition ID_EQ = new FunctionQueryCondition("id_eq");
/* 15 */   public static final FunctionQueryCondition NAME_LIKE = new FunctionQueryCondition("name_like");
/*    */ 
/*    */   
/*    */   protected FunctionQueryCondition(String value) {
/* 19 */     super(value);
/*    */   }
/*    */   
/*    */   public static FunctionQueryCondition getEnum(String value) {
/* 23 */     return (FunctionQueryCondition)getEnum(FunctionQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/FunctionQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */