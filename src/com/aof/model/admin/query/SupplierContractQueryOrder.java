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
/*    */ public class SupplierContractQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final SupplierContractQueryOrder ID = new SupplierContractQueryOrder("id");
/* 20 */   public static final SupplierContractQueryOrder FILESIZE = new SupplierContractQueryOrder("fileSize");
/* 21 */   public static final SupplierContractQueryOrder FILENAME = new SupplierContractQueryOrder("fileName");
/* 22 */   public static final SupplierContractQueryOrder DESCRIPTION = new SupplierContractQueryOrder("description");
/* 23 */   public static final SupplierContractQueryOrder UPLOADDATE = new SupplierContractQueryOrder("uploadDate");
/*    */   
/*    */   protected SupplierContractQueryOrder(String value) {
/* 26 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierContractQueryOrder getEnum(String value) {
/* 30 */     return (SupplierContractQueryOrder)getEnum(SupplierContractQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierContractQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */