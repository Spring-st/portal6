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
/*    */ public class PurchaseSubCategoryQueryCondition
/*    */   extends Enum
/*    */ {
/* 19 */   public static final PurchaseSubCategoryQueryCondition ID_EQ = new PurchaseSubCategoryQueryCondition("id_eq");
/*    */ 
/*    */   
/* 22 */   public static final PurchaseSubCategoryQueryCondition PURCHASECATEGORY_ID_EQ = new PurchaseSubCategoryQueryCondition("purchaseCategory_id_eq");
/*    */   
/* 24 */   public static final PurchaseSubCategoryQueryCondition PURCHASECATEGORY_SITE_ID_EQ = new PurchaseSubCategoryQueryCondition("purchaseCategory_site_id_eq");
/*    */   
/* 26 */   public static final PurchaseSubCategoryQueryCondition PURCHASECATEGORY_SITE_ID_EQ_OR_NULL = new PurchaseSubCategoryQueryCondition("purchaseCategory_site_id_eq_or_null");
/*    */   
/* 28 */   public static final PurchaseSubCategoryQueryCondition DEFAULTSUPPLIER_ID_EQ = new PurchaseSubCategoryQueryCondition("defaultSupplier_id_eq");
/*    */ 
/*    */   
/* 31 */   public static final PurchaseSubCategoryQueryCondition DESCRIPTION_LIKE = new PurchaseSubCategoryQueryCondition("description_like");
/*    */   
/* 33 */   public static final PurchaseSubCategoryQueryCondition ENABLED_EQ = new PurchaseSubCategoryQueryCondition("enabled_eq");
/*    */   
/*    */   protected PurchaseSubCategoryQueryCondition(String value) {
/* 36 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseSubCategoryQueryCondition getEnum(String value) {
/* 40 */     return (PurchaseSubCategoryQueryCondition)getEnum(PurchaseSubCategoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PurchaseSubCategoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */