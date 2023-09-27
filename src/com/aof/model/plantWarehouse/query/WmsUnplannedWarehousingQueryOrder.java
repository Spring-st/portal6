/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsUnplannedWarehousingQueryOrder extends Enum {
/*  6 */   public static final WmsUnplannedWarehousingQueryOrder ID = new WmsUnplannedWarehousingQueryOrder("id");
/*    */   
/*    */   protected WmsUnplannedWarehousingQueryOrder(String value) {
/*  9 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsUnplannedWarehousingQueryOrder getEnum(String value) {
/* 13 */     return (WmsUnplannedWarehousingQueryOrder)getEnum(WmsUnplannedWarehousingQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/WmsUnplannedWarehousingQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */