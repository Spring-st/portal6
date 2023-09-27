/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryType
/*    */   extends MetadataDetailEnum
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   public static final InventoryType RECEIPT = new InventoryType(1, "采购收货", MetadataType.INVENTORYTYPE);
/* 16 */   public static final InventoryType QUALITY = new InventoryType(2, "采购质检", MetadataType.INVENTORYTYPE);
/* 17 */   public static final InventoryType STORAGE = new InventoryType(3, "采购入库", MetadataType.INVENTORYTYPE);
/* 18 */   public static final InventoryType RETURN = new InventoryType(4, "采购退货", MetadataType.INVENTORYTYPE);
/*    */   
/* 20 */   public static final InventoryType RECEIVE_OUT = new InventoryType(5, "销售出库", MetadataType.INVENTORYTYPE);
/* 21 */   public static final InventoryType LINELIBRARYIN = new InventoryType(6, "原材料移入线边库", MetadataType.INVENTORYTYPE);
/* 22 */   public static final InventoryType LINELIBRARYOUT = new InventoryType(7, "原材料移出线边库", MetadataType.INVENTORYTYPE);
/* 23 */   public static final InventoryType PLANDELIVERY = new InventoryType(8, "计划外入库", MetadataType.INVENTORYTYPE);
/* 24 */   public static final InventoryType PLANTOGOOUT = new InventoryType(9, "计划外出库", MetadataType.INVENTORYTYPE);
/* 25 */   public static final InventoryType COMMONOUT = new InventoryType(10, "普通移出", MetadataType.INVENTORYTYPE);
/* 26 */   public static final InventoryType COMMONIN = new InventoryType(12, "普通移入", MetadataType.INVENTORYTYPE);
/* 27 */   public static final InventoryType FINISHPRODUCTINSTORAGEIN = new InventoryType(13, "成品入库", MetadataType.INVENTORYTYPE);
/* 28 */   public static final InventoryType FINISHPRODUCTINSTORAGEOUT = new InventoryType(14, "成品出库", MetadataType.INVENTORYTYPE);
/* 29 */   public static final InventoryType PRODUCELINE = new InventoryType(15, "成品入线边库", MetadataType.INVENTORYTYPE);
/* 30 */   public static final InventoryType RETURNINSTORAGE = new InventoryType(16, "原材料退料", MetadataType.INVENTORYTYPE);
/*    */ 
/*    */   
/*    */   public InventoryType() {}
/*    */   
/*    */   private InventoryType(int metadataId, String defaultLabel, MetadataType type) {
/* 36 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/InventoryType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */