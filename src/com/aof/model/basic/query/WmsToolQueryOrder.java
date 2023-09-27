/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WmsToolQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final WmsToolQueryOrder ID = new WmsToolQueryOrder("id");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected WmsToolQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static WmsToolQueryOrder getEnum(String value) {
/* 18 */     return (WmsToolQueryOrder)getEnum(WmsToolQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/WmsToolQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */