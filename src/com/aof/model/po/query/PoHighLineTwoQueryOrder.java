/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class PoHighLineTwoQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final PoHighLineTwoQueryOrder ID = new PoHighLineTwoQueryOrder("id");
/*    */ 
/*    */   
/*    */   protected PoHighLineTwoQueryOrder(String value) {
/* 12 */     super(value);
/*    */   }
/*    */   
/*    */   public static PoHighLineTwoQueryOrder getEnum(String value) {
/* 16 */     return (PoHighLineTwoQueryOrder)getEnum(PoHighLineTwoQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/PoHighLineTwoQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */