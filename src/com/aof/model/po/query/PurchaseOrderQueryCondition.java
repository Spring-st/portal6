/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderQueryCondition
/*    */   extends Enum {
/*  7 */   public static final PurchaseOrderQueryCondition ID_EQ = new PurchaseOrderQueryCondition(
/*  8 */       "id_eq");
/*  9 */   public static final PurchaseOrderQueryCondition CODE_EQ = new PurchaseOrderQueryCondition(
/* 10 */       "code_eq");
/* 11 */   public static final PurchaseOrderQueryCondition SITE_EQ = new PurchaseOrderQueryCondition(
/* 12 */       "site_eq");
/* 13 */   public static final PurchaseOrderQueryCondition DEPARTMENT_EQ = new PurchaseOrderQueryCondition(
/* 14 */       "department_eq");
/* 15 */   public static final PurchaseOrderQueryCondition STARTPODATE_EQ = new PurchaseOrderQueryCondition(
/* 16 */       "startPoDate_eq");
/* 17 */   public static final PurchaseOrderQueryCondition ENDPODATE_EQ = new PurchaseOrderQueryCondition(
/* 18 */       "endPoDate_eq");
/* 19 */   public static final PurchaseOrderQueryCondition SUPPLIER_EQ = new PurchaseOrderQueryCondition(
/* 20 */       "supplier_eq");
/* 21 */   public static final PurchaseOrderQueryCondition SUPPLIERCODE_EQ = new PurchaseOrderQueryCondition(
/* 22 */       "supplierCode_eq");
/* 23 */   public static final PurchaseOrderQueryCondition ID_BEGINWITH = new PurchaseOrderQueryCondition(
/* 24 */       "id_beginwith");
/* 25 */   public static final PurchaseOrderQueryCondition STATUS_EQ = new PurchaseOrderQueryCondition(
/* 26 */       "status_eq");
/*    */   
/*    */   protected PurchaseOrderQueryCondition(String value) {
/* 29 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderQueryCondition getEnum(String value) {
/* 33 */     return (PurchaseOrderQueryCondition)getEnum(
/* 34 */         PurchaseOrderQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */