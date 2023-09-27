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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseCategoryQueryCondition
/*    */   extends Enum
/*    */ {
/* 19 */   public static final PurchaseCategoryQueryCondition ID_EQ = new PurchaseCategoryQueryCondition("id_eq");
/*    */ 
/*    */   
/* 22 */   public static final PurchaseCategoryQueryCondition GLOBAL = new PurchaseCategoryQueryCondition("global");
/*    */ 
/*    */   
/* 25 */   public static final PurchaseCategoryQueryCondition SITE_ID_EQ = new PurchaseCategoryQueryCondition("site_id_eq");
/*    */ 
/*    */   
/* 28 */   public static final PurchaseCategoryQueryCondition GLOBAL_OR_SITE_ID_EQ = new PurchaseCategoryQueryCondition("global_or_site_id_eq");
/*    */ 
/*    */   
/* 31 */   public static final PurchaseCategoryQueryCondition DESCRIPTION_LIKE = new PurchaseCategoryQueryCondition("description_like");
/*    */   
/* 33 */   public static final PurchaseCategoryQueryCondition ENABLED_EQ = new PurchaseCategoryQueryCondition("enabled_eq");
/*    */   
/*    */   protected PurchaseCategoryQueryCondition(String value) {
/* 36 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseCategoryQueryCondition getEnum(String value) {
/* 40 */     return (PurchaseCategoryQueryCondition)getEnum(PurchaseCategoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PurchaseCategoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */