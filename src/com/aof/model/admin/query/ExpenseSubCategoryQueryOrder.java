/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ExpenseSubCategoryQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ExpenseSubCategoryQueryOrder ID = new ExpenseSubCategoryQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ExpenseSubCategoryQueryOrder DESCRIPTION = new ExpenseSubCategoryQueryOrder("description");
/* 12 */   public static final ExpenseSubCategoryQueryOrder REFERENCEERPID = new ExpenseSubCategoryQueryOrder("referenceErpId");
/* 13 */   public static final ExpenseSubCategoryQueryOrder ENABLED = new ExpenseSubCategoryQueryOrder("enabled");
/*    */   
/*    */   protected ExpenseSubCategoryQueryOrder(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpenseSubCategoryQueryOrder getEnum(String value) {
/* 20 */     return (ExpenseSubCategoryQueryOrder)getEnum(ExpenseSubCategoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExpenseSubCategoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */