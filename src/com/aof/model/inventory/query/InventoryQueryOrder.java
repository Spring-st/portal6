/*    */ package com.aof.model.inventory.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class InventoryQueryOrder
/*    */   extends Enum {
/*  7 */   public static final InventoryQueryOrder ID = new InventoryQueryOrder("id");
/*  8 */   public static final InventoryQueryOrder PART_ID = new InventoryQueryOrder("partId");
/*  9 */   public static final InventoryQueryOrder LOCATION_ID = new InventoryQueryOrder("locationId");
/* 10 */   public static final InventoryQueryOrder ENGNAME = new InventoryQueryOrder("engName");
/* 11 */   public static final InventoryQueryOrder CHNNAME = new InventoryQueryOrder("chnName");
/* 12 */   public static final InventoryQueryOrder ENABLED = new InventoryQueryOrder("enabled");
/*    */   
/*    */   protected InventoryQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryQueryOrder getEnum(String value) {
/* 19 */     return (InventoryQueryOrder)getEnum(InventoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/query/InventoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */