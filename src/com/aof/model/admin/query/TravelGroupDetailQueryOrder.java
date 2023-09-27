/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TravelGroupDetailQueryOrder
/*    */   extends Enum
/*    */ {
/* 10 */   public static final TravelGroupDetailQueryOrder AMOUNTLIMIT = new TravelGroupDetailQueryOrder("amountLimit");
/*    */   
/*    */   protected TravelGroupDetailQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static TravelGroupDetailQueryOrder getEnum(String value) {
/* 17 */     return (TravelGroupDetailQueryOrder)getEnum(TravelGroupDetailQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/TravelGroupDetailQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */