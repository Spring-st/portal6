/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class WorkOrderBomQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final WorkOrderBomQueryCondition ID_EQ = new WorkOrderBomQueryCondition("id_eq");
/*    */   
/*    */   protected WorkOrderBomQueryCondition(String value) {
/* 11 */     super(value);
/*    */   }
/*    */   
/*    */   public static WorkOrderBomQueryCondition getEnum(String value) {
/* 15 */     return (WorkOrderBomQueryCondition)getEnum(WorkOrderBomQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/WorkOrderBomQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */