/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ExpenseCategoryQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ExpenseCategoryQueryOrder ID = new ExpenseCategoryQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ExpenseCategoryQueryOrder DESCRIPTION = new ExpenseCategoryQueryOrder("description");
/* 12 */   public static final ExpenseCategoryQueryOrder TYPE = new ExpenseCategoryQueryOrder("type");
/* 13 */   public static final ExpenseCategoryQueryOrder REFERENCEERPID = new ExpenseCategoryQueryOrder("referenceErpId");
/* 14 */   public static final ExpenseCategoryQueryOrder ENABLED = new ExpenseCategoryQueryOrder("enabled");
/*    */   
/*    */   protected ExpenseCategoryQueryOrder(String value) {
/* 17 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpenseCategoryQueryOrder getEnum(String value) {
/* 21 */     return (ExpenseCategoryQueryOrder)getEnum(ExpenseCategoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExpenseCategoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */