/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class NjitNpoPlanQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final NjitNpoPlanQueryOrder ID = new NjitNpoPlanQueryOrder("id");
/*  9 */   public static final NjitNpoPlanQueryOrder PART_ID = new NjitNpoPlanQueryOrder("partId");
/*    */ 
/*    */   
/*    */   protected NjitNpoPlanQueryOrder(String name) {
/* 13 */     super(name);
/*    */   }
/*    */   
/*    */   public static NjitNpoPlanQueryOrder getEnum(String value) {
/* 17 */     return (NjitNpoPlanQueryOrder)getEnum(NjitNpoPlanQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/NjitNpoPlanQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */