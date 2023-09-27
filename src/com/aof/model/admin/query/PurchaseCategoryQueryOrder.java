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
/*    */ public class PurchaseCategoryQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final PurchaseCategoryQueryOrder ID = new PurchaseCategoryQueryOrder("id");
/* 20 */   public static final PurchaseCategoryQueryOrder DESCRIPTION = new PurchaseCategoryQueryOrder("description");
/* 21 */   public static final PurchaseCategoryQueryOrder ENABLED = new PurchaseCategoryQueryOrder("enabled");
/*    */   
/*    */   protected PurchaseCategoryQueryOrder(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseCategoryQueryOrder getEnum(String value) {
/* 28 */     return (PurchaseCategoryQueryOrder)getEnum(PurchaseCategoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/PurchaseCategoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */