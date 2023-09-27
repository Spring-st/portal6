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
/*    */ public class SupplierHistoryQueryCondition
/*    */   extends Enum
/*    */ {
/* 20 */   public static final SupplierHistoryQueryCondition ID_EQ = new SupplierHistoryQueryCondition("id_eq");
/*    */   
/*    */   protected SupplierHistoryQueryCondition(String value) {
/* 23 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierHistoryQueryCondition getEnum(String value) {
/* 27 */     return (SupplierHistoryQueryCondition)getEnum(SupplierHistoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SupplierHistoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */