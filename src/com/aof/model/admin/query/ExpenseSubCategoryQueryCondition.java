/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class ExpenseSubCategoryQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final ExpenseSubCategoryQueryCondition ID_EQ = new ExpenseSubCategoryQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final ExpenseSubCategoryQueryCondition EXPENSECATEGORY_ID_EQ = new ExpenseSubCategoryQueryCondition("expenseCategory_id_eq");
/*    */ 
/*    */   
/* 20 */   public static final ExpenseSubCategoryQueryCondition EXPENSECATEGORY_SITE_ID_EQ = new ExpenseSubCategoryQueryCondition("expenseCategory_site_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 24 */   public static final ExpenseSubCategoryQueryCondition DESCRIPTION_LIKE = new ExpenseSubCategoryQueryCondition("description_like");
/*    */   
/* 26 */   public static final ExpenseSubCategoryQueryCondition REFERENCEERPID_LIKE = new ExpenseSubCategoryQueryCondition("referenceErpId_like");
/*    */   
/* 28 */   public static final ExpenseSubCategoryQueryCondition ENABLED_EQ = new ExpenseSubCategoryQueryCondition("enabled_eq");
/*    */   
/* 30 */   public static final ExpenseSubCategoryQueryCondition ISHOTEL_EQ = new ExpenseSubCategoryQueryCondition("isHotel_eq");
/*    */   
/*    */   protected ExpenseSubCategoryQueryCondition(String value) {
/* 33 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpenseSubCategoryQueryCondition getEnum(String value) {
/* 37 */     return (ExpenseSubCategoryQueryCondition)getEnum(ExpenseSubCategoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExpenseSubCategoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */