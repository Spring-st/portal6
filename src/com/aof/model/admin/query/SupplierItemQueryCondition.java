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
/*    */ 
/*    */ public class SupplierItemQueryCondition
/*    */   extends Enum
/*    */ {
/* 21 */   public static final SupplierItemQueryCondition ID_EQ = new SupplierItemQueryCondition("id_eq");
/*    */   
/* 23 */   public static final SupplierItemQueryCondition CURRENCY_CODE_EQ = new SupplierItemQueryCondition("currency_code_eq");
/*    */   
/* 25 */   public static final SupplierItemQueryCondition PURCHASECATEGORY_ID_EQ = new SupplierItemQueryCondition("purchaseCategory_id_eq");
/*    */   
/* 27 */   public static final SupplierItemQueryCondition PURCHASESUBCATEGORY_ID_EQ = new SupplierItemQueryCondition("purchaseSubCategory_id_eq");
/*    */   
/* 29 */   public static final SupplierItemQueryCondition SUPPLIER_ID_EQ = new SupplierItemQueryCondition("supplier_id_eq");
/*    */ 
/*    */   
/* 32 */   public static final SupplierItemQueryCondition SEPC_LIKE = new SupplierItemQueryCondition("sepc_like");
/*    */   
/* 34 */   public static final SupplierItemQueryCondition UNITPRICE_EQ = new SupplierItemQueryCondition("unitPrice_eq");
/*    */   
/* 36 */   public static final SupplierItemQueryCondition ENABLED_EQ = new SupplierItemQueryCondition("enabled_eq");
/*    */   
/* 38 */   public static final SupplierItemQueryCondition ERPNO_LIKE = new SupplierItemQueryCondition("erpNo_like");
/*    */   
/*    */   protected SupplierItemQueryCondition(String value) {
/* 41 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierItemQueryCondition getEnum(String value) {
/* 45 */     return (SupplierItemQueryCondition)getEnum(SupplierItemQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierItemQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */