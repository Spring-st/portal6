/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class TravelGroupQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final TravelGroupQueryOrder ID = new TravelGroupQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final TravelGroupQueryOrder NAME = new TravelGroupQueryOrder("name");
/* 12 */   public static final TravelGroupQueryOrder ENABLED = new TravelGroupQueryOrder("enabled");
/*    */   
/*    */   protected TravelGroupQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static TravelGroupQueryOrder getEnum(String value) {
/* 19 */     return (TravelGroupQueryOrder)getEnum(TravelGroupQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/TravelGroupQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */