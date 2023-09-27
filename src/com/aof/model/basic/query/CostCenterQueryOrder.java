/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class CostCenterQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final CostCenterQueryOrder ID = new CostCenterQueryOrder("id");
/*    */ 
/*    */   
/* 11 */   public static final CostCenterQueryOrder ENABLED = new CostCenterQueryOrder("enabled");
/*    */   
/*    */   protected CostCenterQueryOrder(String value) {
/* 14 */     super(value);
/*    */   }
/*    */   
/*    */   public static CostCenterQueryOrder getEnum(String value) {
/* 18 */     return (CostCenterQueryOrder)getEnum(CostCenterQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/CostCenterQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */