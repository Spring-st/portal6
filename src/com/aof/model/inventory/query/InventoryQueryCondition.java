/*    */ package com.aof.model.inventory.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class InventoryQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final InventoryQueryCondition ID_EQ = new InventoryQueryCondition("id_eq");
/*  9 */   public static final InventoryQueryCondition STATUS_EQ = new InventoryQueryCondition("status_eq");
/* 10 */   public static final InventoryQueryCondition TYPE_EQ = new InventoryQueryCondition("type_eq");
/* 11 */   public static final InventoryQueryCondition PART_TYPE_EQ = new InventoryQueryCondition("part_type_eq");
/* 12 */   public static final InventoryQueryCondition PART_EQ = new InventoryQueryCondition("part_eq");
/* 13 */   public static final InventoryQueryCondition ID_BEGINWITH = new InventoryQueryCondition("id_beginwith");
/* 14 */   public static final InventoryQueryCondition DETAIL_STORAGE_EQ = new InventoryQueryCondition("detail_storage_eq");
/* 15 */   public static final InventoryQueryCondition DETAIL_PART_EQ = new InventoryQueryCondition("detail_part_eq");
/* 16 */   public static final InventoryQueryCondition NUMBER_NE = new InventoryQueryCondition("detail_number_ne");
/* 17 */   public static final InventoryQueryCondition DETAIL_STORAGE_ID_EQ = new InventoryQueryCondition("detail_storage_id_eq");
/* 18 */   public static final InventoryQueryCondition PART_ID_TYPE_LIKE = new InventoryQueryCondition("part_id_type_like");
/* 19 */   public static final InventoryQueryCondition LOCATION_ID_IN = new InventoryQueryCondition("location_id_in");
/* 20 */   public static final InventoryQueryCondition NUMBER_GE = new InventoryQueryCondition("detail_number_ge");
/*    */   
/*    */   protected InventoryQueryCondition(String value) {
/* 23 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryQueryCondition getEnum(String value) {
/* 27 */     return (InventoryQueryCondition)getEnum(InventoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/query/InventoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */