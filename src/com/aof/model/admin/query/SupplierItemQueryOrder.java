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
/*    */ public class SupplierItemQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final SupplierItemQueryOrder ID = new SupplierItemQueryOrder("id");
/*    */   
/* 21 */   public static final SupplierItemQueryOrder SEPC = new SupplierItemQueryOrder("sepc");
/* 22 */   public static final SupplierItemQueryOrder UNITPRICE = new SupplierItemQueryOrder("unitPrice");
/* 23 */   public static final SupplierItemQueryOrder ENABLED = new SupplierItemQueryOrder("enabled");
/* 24 */   public static final SupplierItemQueryOrder ERPNO = new SupplierItemQueryOrder("erpNo");
/* 25 */   public static final SupplierItemQueryOrder CATEGORY = new SupplierItemQueryOrder("category");
/* 26 */   public static final SupplierItemQueryOrder SUBCATEGORY = new SupplierItemQueryOrder("subCategory");
/*    */   
/*    */   protected SupplierItemQueryOrder(String value) {
/* 29 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierItemQueryOrder getEnum(String value) {
/* 33 */     return (SupplierItemQueryOrder)getEnum(SupplierItemQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierItemQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */