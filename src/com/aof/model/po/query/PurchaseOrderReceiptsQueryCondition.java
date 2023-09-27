/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderReceiptsQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderReceiptsQueryCondition ID_EQ = new PurchaseOrderReceiptsQueryCondition(
/*  9 */       "id_eq");
/*    */   
/* 11 */   public static final PurchaseOrderReceiptsQueryCondition ID_BEGINWITH = new PurchaseOrderReceiptsQueryCondition(
/* 12 */       "id_beginwith");
/*    */   
/*    */   protected PurchaseOrderReceiptsQueryCondition(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderReceiptsQueryCondition getEnum(String value) {
/* 19 */     return (PurchaseOrderReceiptsQueryCondition)getEnum(
/* 20 */         PurchaseOrderReceiptsQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderReceiptsQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */