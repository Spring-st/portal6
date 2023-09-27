/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class ExpenseCategoryQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final ExpenseCategoryQueryCondition ID_EQ = new ExpenseCategoryQueryCondition("id_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final ExpenseCategoryQueryCondition SITE_ID_EQ = new ExpenseCategoryQueryCondition("site_id_eq");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final ExpenseCategoryQueryCondition DESCRIPTION_LIKE = new ExpenseCategoryQueryCondition("description_like");
/*    */   
/* 23 */   public static final ExpenseCategoryQueryCondition TYPE_EQ = new ExpenseCategoryQueryCondition("type_eq");
/*    */   
/* 25 */   public static final ExpenseCategoryQueryCondition TYPE_NEQ = new ExpenseCategoryQueryCondition("type_neq");
/*    */   
/* 27 */   public static final ExpenseCategoryQueryCondition REFERENCEERPID_LIKE = new ExpenseCategoryQueryCondition("referenceErpId_like");
/*    */   
/* 29 */   public static final ExpenseCategoryQueryCondition ENABLED_EQ = new ExpenseCategoryQueryCondition("enabled_eq");
/*    */   
/*    */   protected ExpenseCategoryQueryCondition(String value) {
/* 32 */     super(value);
/*    */   }
/*    */   
/*    */   public static ExpenseCategoryQueryCondition getEnum(String value) {
/* 36 */     return (ExpenseCategoryQueryCondition)getEnum(ExpenseCategoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/ExpenseCategoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */