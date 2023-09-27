/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class SupplierPartQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final SupplierPartQueryCondition ID_EQ = new SupplierPartQueryCondition("id_eq");
/*    */   
/* 11 */   public static final SupplierPartQueryCondition SUPPLIER_ID_EQ = new SupplierPartQueryCondition("supplier_id_eq");
/*    */   
/* 13 */   public static final SupplierPartQueryCondition PART_ID_EQ = new SupplierPartQueryCondition("part_id_eq");
/*    */   
/* 15 */   public static final SupplierPartQueryCondition SUPPLIER_PART_EQ = new SupplierPartQueryCondition("supplier_part_eq");
/*    */   protected SupplierPartQueryCondition(String value) {
/* 17 */     super(value);
/*    */   }
/*    */   
/*    */   public static SupplierPartQueryCondition getEnum(String value) {
/* 21 */     return (SupplierPartQueryCondition)getEnum(SupplierPartQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/SupplierPartQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */