/*    */ package com.aof.model.sync.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class SyncLogQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final SyncLogQueryCondition ID_EQ = new SyncLogQueryCondition("id_eq");
/*    */   
/* 11 */   public static final SyncLogQueryCondition DESCRIBE_EQ = new SyncLogQueryCondition("describe_eq");
/*    */   
/* 13 */   public static final SyncLogQueryCondition ENABLED_EQ = new SyncLogQueryCondition("enabled_eq");
/*    */ 
/*    */   
/* 16 */   public static final SyncLogQueryCondition DATE_GT = new SyncLogQueryCondition("date1_gt");
/*    */ 
/*    */   
/* 19 */   public static final SyncLogQueryCondition DATE_LT = new SyncLogQueryCondition("date2_lt");
/*    */   
/* 21 */   public static final SyncLogQueryCondition SYNCRESULTS_LT = new SyncLogQueryCondition("sync_results_eq");
/*    */ 
/*    */   
/*    */   protected SyncLogQueryCondition(String value) {
/* 25 */     super(value);
/*    */   }
/*    */   
/*    */   public static SyncLogQueryCondition getEnum(String value) {
/* 29 */     return (SyncLogQueryCondition)getEnum(SyncLogQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/query/SyncLogQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */