/*    */ package com.aof.model.inventory.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class InventoryTransitQueryOrder
/*    */   extends Enum {
/*  7 */   public static final InventoryTransitQueryOrder ID = new InventoryTransitQueryOrder("id");
/*  8 */   public static final InventoryTransitQueryOrder ENGNAME = new InventoryTransitQueryOrder("engName");
/*  9 */   public static final InventoryTransitQueryOrder CHNNAME = new InventoryTransitQueryOrder("chnName");
/* 10 */   public static final InventoryTransitQueryOrder ENABLED = new InventoryTransitQueryOrder("enabled");
/*    */   
/*    */   protected InventoryTransitQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryTransitQueryOrder getEnum(String value) {
/* 17 */     return (InventoryTransitQueryOrder)getEnum(InventoryTransitQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/query/InventoryTransitQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */