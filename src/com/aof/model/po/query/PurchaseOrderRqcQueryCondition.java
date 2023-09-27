/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderRqcQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderRqcQueryCondition ID_EQ = new PurchaseOrderRqcQueryCondition(
/*  9 */       "id_eq");
/*    */   
/* 11 */   public static final PurchaseOrderRqcQueryCondition ID_BEGINWITH = new PurchaseOrderRqcQueryCondition(
/* 12 */       "id_beginwith");
/*    */   protected PurchaseOrderRqcQueryCondition(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static PurchaseOrderRqcQueryCondition getEnum(String value) {
/* 18 */     return (PurchaseOrderRqcQueryCondition)getEnum(
/* 19 */         PurchaseOrderRqcQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderRqcQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */