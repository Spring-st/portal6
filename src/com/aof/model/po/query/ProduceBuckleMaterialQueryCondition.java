/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProduceBuckleMaterialQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ProduceBuckleMaterialQueryCondition ID_EQ = new ProduceBuckleMaterialQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final ProduceBuckleMaterialQueryCondition SITE_EQ = new ProduceBuckleMaterialQueryCondition(
/* 11 */       "site_eq");
/* 12 */   public static final ProduceBuckleMaterialQueryCondition CODE_EQ = new ProduceBuckleMaterialQueryCondition(
/* 13 */       "code_eq");
/*    */ 
/*    */   
/* 16 */   public static final ProduceBuckleMaterialQueryCondition DEPARTMENT_EQ = new ProduceBuckleMaterialQueryCondition(
/* 17 */       "department_eq");
/* 18 */   public static final ProduceBuckleMaterialQueryCondition CREATEDATE_GE = new ProduceBuckleMaterialQueryCondition(
/* 19 */       "createDate_ge");
/* 20 */   public static final ProduceBuckleMaterialQueryCondition CREATEDATE_LT = new ProduceBuckleMaterialQueryCondition(
/* 21 */       "createDate_lt");
/* 22 */   public static final ProduceBuckleMaterialQueryCondition ENABLED_EQ = new ProduceBuckleMaterialQueryCondition(
/* 23 */       "enabled_eq");
/* 24 */   public static final ProduceBuckleMaterialQueryCondition APPROVEREQUESTID_EQ = new ProduceBuckleMaterialQueryCondition(
/* 25 */       "approverequestid_eq");
/* 26 */   public static final ProduceBuckleMaterialQueryCondition PORTALSHIPORDER_STATUS_EQ = new ProduceBuckleMaterialQueryCondition(
/* 27 */       "portalShipOrder_status_eq");
/* 28 */   public static final ProduceBuckleMaterialQueryCondition PO_CODE_EQ = new ProduceBuckleMaterialQueryCondition(
/* 29 */       "po_code_eq");
/* 30 */   public static final ProduceBuckleMaterialQueryCondition PART_CODE_EQ = new ProduceBuckleMaterialQueryCondition(
/* 31 */       "part_code_eq");
/* 32 */   public static final ProduceBuckleMaterialQueryCondition ID_BEGINWITH = new ProduceBuckleMaterialQueryCondition(
/* 33 */       "ID_BEGINWITH");
/* 34 */   public static final ProduceBuckleMaterialQueryCondition STATUS_CONFIRM_EQ = new ProduceBuckleMaterialQueryCondition(
/* 35 */       "status_confirm_eq");
/* 36 */   public static final ProduceBuckleMaterialQueryCondition STATUS_EQ = new ProduceBuckleMaterialQueryCondition(
/* 37 */       "status_eq");
/*    */   
/*    */   protected ProduceBuckleMaterialQueryCondition(String value) {
/* 40 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProduceBuckleMaterialQueryCondition getEnum(String value) {
/* 44 */     return (ProduceBuckleMaterialQueryCondition)getEnum(
/* 45 */         ProduceBuckleMaterialQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProduceBuckleMaterialQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */