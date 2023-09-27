/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProjectedInventoryQueryOrder
/*    */   extends Enum
/*    */ {
/*  8 */   public static final ProjectedInventoryQueryOrder ID = new ProjectedInventoryQueryOrder("id");
/*    */   
/*    */   protected ProjectedInventoryQueryOrder(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProjectedInventoryQueryOrder getEnum(String value) {
/* 15 */     return (ProjectedInventoryQueryOrder)getEnum(ProjectedInventoryQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProjectedInventoryQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */