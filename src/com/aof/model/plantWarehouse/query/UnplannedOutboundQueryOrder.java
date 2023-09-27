/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedOutboundQueryOrder
/*    */   extends Enum {
/*  7 */   public static final UnplannedOutboundQueryOrder ID = new UnplannedOutboundQueryOrder("id");
/*  8 */   public static final UnplannedOutboundQueryOrder ENGNAME = new UnplannedOutboundQueryOrder("engName");
/*  9 */   public static final UnplannedOutboundQueryOrder CHNNAME = new UnplannedOutboundQueryOrder("chnName");
/* 10 */   public static final UnplannedOutboundQueryOrder ENABLED = new UnplannedOutboundQueryOrder("enabled");
/*    */   
/*    */   protected UnplannedOutboundQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedOutboundQueryOrder getEnum(String value) {
/* 17 */     return (UnplannedOutboundQueryOrder)getEnum(UnplannedOutboundQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/UnplannedOutboundQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */