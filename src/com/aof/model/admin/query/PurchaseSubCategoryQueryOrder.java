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
/*    */ public class PurchaseSubCategoryQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final PurchaseSubCategoryQueryOrder ID = new PurchaseSubCategoryQueryOrder("id");
/*    */   
/* 21 */   public static final PurchaseSubCategoryQueryOrder DESCRIPTION = new PurchaseSubCategoryQueryOrder("description");
/* 22 */   public static final PurchaseSubCategoryQueryOrder SUPPLIER = new PurchaseSubCategoryQueryOrder("supplier");
/* 23 */   public static final PurchaseSubCategoryQueryOrder ENABLED = new PurchaseSubCategoryQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseSubCategoryQueryOrder(String value) {
/* 26 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseSubCategoryQueryOrder getEnum(String value) {
/* 30 */     return (PurchaseSubCategoryQueryOrder)getEnum(PurchaseSubCategoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PurchaseSubCategoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */