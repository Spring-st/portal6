/*    */ package com.aof.model.plantWarehouse.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsPlanToGoOutQueryOrder extends Enum {
/*  6 */   public static final WmsPlanToGoOutQueryOrder ID = new WmsPlanToGoOutQueryOrder("id");
/*    */   
/*    */   protected WmsPlanToGoOutQueryOrder(String value) {
/*  9 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsPlanToGoOutQueryOrder getEnum(String value) {
/* 13 */     return (WmsPlanToGoOutQueryOrder)getEnum(WmsPlanToGoOutQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/query/WmsPlanToGoOutQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */