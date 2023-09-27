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
/*    */ public class SupplierHistoryQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final SupplierHistoryQueryOrder ID = new SupplierHistoryQueryOrder("id");
/*    */   
/*    */   protected SupplierHistoryQueryOrder(String value) {
/* 22 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierHistoryQueryOrder getEnum(String value) {
/* 26 */     return (SupplierHistoryQueryOrder)getEnum(SupplierHistoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierHistoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */