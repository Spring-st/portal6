/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedWarehousingQueryOrder
/*    */   extends Enum {
/*  7 */   public static final UnplannedWarehousingQueryOrder ID = new UnplannedWarehousingQueryOrder("id");
/*  8 */   public static final UnplannedWarehousingQueryOrder ENGNAME = new UnplannedWarehousingQueryOrder("engName");
/*  9 */   public static final UnplannedWarehousingQueryOrder CHNNAME = new UnplannedWarehousingQueryOrder("chnName");
/* 10 */   public static final UnplannedWarehousingQueryOrder ENABLED = new UnplannedWarehousingQueryOrder("enabled");
/*    */   
/*    */   protected UnplannedWarehousingQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedWarehousingQueryOrder getEnum(String value) {
/* 17 */     return (UnplannedWarehousingQueryOrder)getEnum(UnplannedWarehousingQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/UnplannedWarehousingQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */