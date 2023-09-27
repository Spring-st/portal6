/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PurchaseOrderCondimentSingleQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PurchaseOrderCondimentSingleQueryCondition ID_EQ = new PurchaseOrderCondimentSingleQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final PurchaseOrderCondimentSingleQueryCondition PROVINCE_ID_EQ = new PurchaseOrderCondimentSingleQueryCondition(
/* 11 */       "province_id_eq");
/*    */   
/* 13 */   public static final PurchaseOrderCondimentSingleQueryCondition ENGNAME_LIKE = new PurchaseOrderCondimentSingleQueryCondition(
/* 14 */       "engName_like");
/* 15 */   public static final PurchaseOrderCondimentSingleQueryCondition CHNNAME_LIKE = new PurchaseOrderCondimentSingleQueryCondition(
/* 16 */       "chnName_like");
/* 17 */   public static final PurchaseOrderCondimentSingleQueryCondition ENABLED_EQ = new PurchaseOrderCondimentSingleQueryCondition(
/* 18 */       "enabled_eq");
/* 19 */   public static final PurchaseOrderCondimentSingleQueryCondition ID_BEGINWITH = new PurchaseOrderCondimentSingleQueryCondition(
/* 20 */       "id_beginwith");
/*    */   
/*    */   protected PurchaseOrderCondimentSingleQueryCondition(String value) {
/* 23 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static PurchaseOrderCondimentSingleQueryCondition getEnum(String value) {
/* 28 */     return (PurchaseOrderCondimentSingleQueryCondition)getEnum(
/* 29 */         PurchaseOrderCondimentSingleQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PurchaseOrderCondimentSingleQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */