/*    */ package com.aof.model.sync.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class SyncLogQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final SyncLogQueryOrder ID = new SyncLogQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final SyncLogQueryOrder ENABLED = new SyncLogQueryOrder("enabled");
/*    */   
/*    */   protected SyncLogQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static SyncLogQueryOrder getEnum(String value) {
/* 18 */     return (SyncLogQueryOrder)getEnum(SyncLogQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/query/SyncLogQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */