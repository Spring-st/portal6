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
/*    */ 
/*    */ public class SupplierQueryCondition
/*    */   extends Enum
/*    */ {
/* 20 */   public static final SupplierQueryCondition ID_EQ = new SupplierQueryCondition("id_eq");
/*    */   
/* 22 */   public static final SupplierQueryCondition CODE_LIKE = new SupplierQueryCondition("CODE_like");
/*    */   
/* 24 */   public static final SupplierQueryCondition GLOBAL_OR_SITE_ID_EQ = new SupplierQueryCondition("global_or_site_id_eq");
/*    */   
/* 26 */   public static final SupplierQueryCondition SITE_ID_EQ = new SupplierQueryCondition("site_id_eq");
/*    */   
/* 28 */   public static final SupplierQueryCondition COUNTRY_ID_EQ = new SupplierQueryCondition("country_id_eq");
/*    */   
/* 30 */   public static final SupplierQueryCondition CITY_ID_EQ = new SupplierQueryCondition("city_id_eq");
/*    */   
/* 32 */   public static final SupplierQueryCondition NAME_LIKE = new SupplierQueryCondition("name_like");
/*    */   
/* 34 */   public static final SupplierQueryCondition ENABLED_EQ = new SupplierQueryCondition("enabled_eq");
/*    */   
/* 36 */   public static final SupplierQueryCondition DRAFT_EQ = new SupplierQueryCondition("draft_eq");
/*    */   
/* 38 */   public static final SupplierQueryCondition CONFIRM_EQ = new SupplierQueryCondition("confirm_eq");
/*    */   
/* 40 */   public static final SupplierQueryCondition PROMOTE_STATUS_EQ = new SupplierQueryCondition("promote_status_eq");
/*    */   
/* 42 */   public static final SupplierQueryCondition PROMOTE_STATUS_NEQ = new SupplierQueryCondition("promote_status_neq");
/*    */   
/* 44 */   public static final SupplierQueryCondition CONFIRM_TYPE_EQ = new SupplierQueryCondition("confirm_type_eq");
/*    */   
/* 46 */   public static final SupplierQueryCondition CONTRACT_NOT_ACTIVE = new SupplierQueryCondition("contract_not_active");
/*    */   
/* 48 */   public static final SupplierQueryCondition CONTRACT_ACTIVE = new SupplierQueryCondition("contract_active");
/*    */   
/* 50 */   public static final SupplierQueryCondition CONTRACT_EXPIRED = new SupplierQueryCondition("contract_expired");
/* 51 */   public static final SupplierQueryCondition FOR_SITE_ID_EQ = new SupplierQueryCondition("for_site_id_eq");
/*    */   
/* 53 */   public static final SupplierQueryCondition IS_AIRTICKET = new SupplierQueryCondition("is_airticket");
/*    */   
/*    */   protected SupplierQueryCondition(String value) {
/* 56 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierQueryCondition getEnum(String value) {
/* 60 */     return (SupplierQueryCondition)getEnum(SupplierQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */