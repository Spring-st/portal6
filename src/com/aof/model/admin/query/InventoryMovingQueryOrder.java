/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class InventoryMovingQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final InventoryMovingQueryOrder ID = new InventoryMovingQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final InventoryMovingQueryOrder ENGNAME = new InventoryMovingQueryOrder("engName");
/* 12 */   public static final InventoryMovingQueryOrder CHNNAME = new InventoryMovingQueryOrder("chnName");
/* 13 */   public static final InventoryMovingQueryOrder ENABLED = new InventoryMovingQueryOrder("enabled");
/*    */   
/*    */   protected InventoryMovingQueryOrder(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static InventoryMovingQueryOrder getEnum(String value) {
/* 20 */     return (InventoryMovingQueryOrder)getEnum(InventoryMovingQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/InventoryMovingQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */