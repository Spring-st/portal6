/*    */ package com.aof.model.basicDataView.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class JitShipPartQueryOrder
/*    */   extends Enum {
/*  7 */   public static final JitShipPartQueryOrder PART_ID = new JitShipPartQueryOrder("partId");
/*  8 */   public static final JitShipPartQueryOrder PART_CARTYPE = new JitShipPartQueryOrder("partCarType");
/*  9 */   public static final JitShipPartQueryOrder QTY = new JitShipPartQueryOrder("qty");
/* 10 */   public static final JitShipPartQueryOrder part_drwgLoc = new JitShipPartQueryOrder("part_drwgLoc");
/*    */   
/* 12 */   public static final JitShipPartQueryOrder highQty = new JitShipPartQueryOrder("highQty");
/*    */ 
/*    */   
/* 15 */   public static final JitShipPartQueryOrder lowQty = new JitShipPartQueryOrder("lowQty");
/* 16 */   public static final JitShipPartQueryOrder securityQty = new JitShipPartQueryOrder("securityQty");
/* 17 */   public static final JitShipPartQueryOrder currentQty = new JitShipPartQueryOrder("currentQty");
/*    */   
/*    */   protected JitShipPartQueryOrder(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static JitShipPartQueryOrder getEnum(String value) {
/* 24 */     return (JitShipPartQueryOrder)getEnum(JitShipPartQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/query/JitShipPartQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */