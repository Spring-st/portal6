/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ScanLogQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ScanLogQueryOrder ID = new ScanLogQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final ScanLogQueryOrder ENABLED = new ScanLogQueryOrder("enabled");
/*    */   
/*    */   protected ScanLogQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static ScanLogQueryOrder getEnum(String value) {
/* 18 */     return (ScanLogQueryOrder)getEnum(ScanLogQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/ScanLogQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */