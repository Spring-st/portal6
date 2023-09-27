/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WorkOrderBomQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final WorkOrderBomQueryOrder ID = new WorkOrderBomQueryOrder("id");
/*    */   
/*    */   protected WorkOrderBomQueryOrder(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static WorkOrderBomQueryOrder getEnum(String value) {
/* 15 */     return (WorkOrderBomQueryOrder)getEnum(WorkOrderBomQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/WorkOrderBomQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */