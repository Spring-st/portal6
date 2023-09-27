/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class UnplannedReasonsQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final UnplannedReasonsQueryOrder ID = new UnplannedReasonsQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final UnplannedReasonsQueryOrder ENABLED = new UnplannedReasonsQueryOrder("enabled");
/*    */   
/*    */   protected UnplannedReasonsQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static UnplannedReasonsQueryOrder getEnum(String value) {
/* 18 */     return (UnplannedReasonsQueryOrder)getEnum(UnplannedReasonsQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/UnplannedReasonsQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */