/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProduceRejectedMaterialQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProduceRejectedMaterialQueryCondition ID_EQ = new ProduceRejectedMaterialQueryCondition(
/*  8 */       "id_eq");
/*  9 */   public static final ProduceRejectedMaterialQueryCondition CODE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 10 */       "code_eq");
/* 11 */   public static final ProduceRejectedMaterialQueryCondition SITE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 12 */       "site_eq");
/* 13 */   public static final ProduceRejectedMaterialQueryCondition DEPARTMENT_EQ = new ProduceRejectedMaterialQueryCondition(
/* 14 */       "department_eq");
/* 15 */   public static final ProduceRejectedMaterialQueryCondition STARTPODATE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 16 */       "startPoDate_eq");
/* 17 */   public static final ProduceRejectedMaterialQueryCondition ENDPODATE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 18 */       "endPoDate_eq");
/* 19 */   public static final ProduceRejectedMaterialQueryCondition SUPPLIER_EQ = new ProduceRejectedMaterialQueryCondition(
/* 20 */       "supplier_eq");
/* 21 */   public static final ProduceRejectedMaterialQueryCondition SUPPLIERCODE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 22 */       "supplierCode_eq");
/* 23 */   public static final ProduceRejectedMaterialQueryCondition ID_BEGINWITH = new ProduceRejectedMaterialQueryCondition(
/* 24 */       "id_beginwith");
/* 25 */   public static final ProduceRejectedMaterialQueryCondition STATUS_EQ = new ProduceRejectedMaterialQueryCondition(
/* 26 */       "status_eq");
/* 27 */   public static final ProduceRejectedMaterialQueryCondition TYPE_EQ = new ProduceRejectedMaterialQueryCondition(
/* 28 */       "type_eq");
/*    */   
/*    */   protected ProduceRejectedMaterialQueryCondition(String value) {
/* 31 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProduceRejectedMaterialQueryCondition getEnum(String value) {
/* 35 */     return (ProduceRejectedMaterialQueryCondition)getEnum(
/* 36 */         ProduceRejectedMaterialQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProduceRejectedMaterialQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */