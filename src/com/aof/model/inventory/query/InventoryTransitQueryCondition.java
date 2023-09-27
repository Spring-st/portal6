/*    */ package com.aof.model.inventory.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class InventoryTransitQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final InventoryTransitQueryCondition ID_EQ = new InventoryTransitQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final InventoryTransitQueryCondition STATUS_EQ = new InventoryTransitQueryCondition(
/* 11 */       "status_eq");
/* 12 */   public static final InventoryTransitQueryCondition TYPE_EQ = new InventoryTransitQueryCondition(
/* 13 */       "type_eq");
/* 14 */   public static final InventoryTransitQueryCondition PART_TYPE_EQ = new InventoryTransitQueryCondition(
/* 15 */       "part_type_eq");
/* 16 */   public static final InventoryTransitQueryCondition ID_BEGINWITH = new InventoryTransitQueryCondition(
/* 17 */       "id_beginwith");
/* 18 */   public static final InventoryTransitQueryCondition PART_CODE_EQ = new InventoryTransitQueryCondition("part_code_eq");
/*    */   
/*    */   protected InventoryTransitQueryCondition(String value) {
/* 21 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryTransitQueryCondition getEnum(String value) {
/* 25 */     return (InventoryTransitQueryCondition)getEnum(InventoryTransitQueryCondition.class, 
/* 26 */         value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/query/InventoryTransitQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */