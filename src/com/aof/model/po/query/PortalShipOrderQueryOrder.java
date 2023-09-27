/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PortalShipOrderQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PortalShipOrderQueryOrder ID = new PortalShipOrderQueryOrder("id");
/*    */   
/* 10 */   public static final PortalShipOrderQueryOrder CREATEDATE = new PortalShipOrderQueryOrder("createDate");
/* 11 */   public static final PortalShipOrderQueryOrder ARRIVALDATE = new PortalShipOrderQueryOrder("arrivalDate");
/* 12 */   public static final PortalShipOrderQueryOrder ENABLED = new PortalShipOrderQueryOrder("enabled");
/*    */   
/*    */   protected PortalShipOrderQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static PortalShipOrderQueryOrder getEnum(String value) {
/* 19 */     return (PortalShipOrderQueryOrder)getEnum(PortalShipOrderQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PortalShipOrderQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */