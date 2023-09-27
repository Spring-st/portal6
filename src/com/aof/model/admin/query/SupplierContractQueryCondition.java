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
/*    */ public class SupplierContractQueryCondition
/*    */   extends Enum
/*    */ {
/* 20 */   public static final SupplierContractQueryCondition ID_EQ = new SupplierContractQueryCondition("id_eq");
/*    */   
/* 22 */   public static final SupplierContractQueryCondition SUPPLIER_ID_EQ = new SupplierContractQueryCondition("Supplier_id_eq");
/*    */   
/* 24 */   public static final SupplierContractQueryCondition FILESIZE_EQ = new SupplierContractQueryCondition("fileSize_eq");
/*    */   
/* 26 */   public static final SupplierContractQueryCondition FILENAME_LIKE = new SupplierContractQueryCondition("fileName_like");
/*    */   
/* 28 */   public static final SupplierContractQueryCondition DESCRIPTION_LIKE = new SupplierContractQueryCondition("description_like");
/*    */   
/* 30 */   public static final SupplierContractQueryCondition UPLOADDATE_EQ = new SupplierContractQueryCondition("uploadDate_eq");
/*    */   
/*    */   protected SupplierContractQueryCondition(String value) {
/* 33 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierContractQueryCondition getEnum(String value) {
/* 37 */     return (SupplierContractQueryCondition)getEnum(SupplierContractQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierContractQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */