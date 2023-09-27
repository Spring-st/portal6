/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProjectedInventoryQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProjectedInventoryQueryCondition ID_EQ = new ProjectedInventoryQueryCondition("id_eq");
/*  8 */   public static final ProjectedInventoryQueryCondition PART_ID_EQ = new ProjectedInventoryQueryCondition("part_id_eq");
/*  9 */   public static final ProjectedInventoryQueryCondition Part_VEND_EQ = new ProjectedInventoryQueryCondition("part_vend_eq");
/*    */   protected ProjectedInventoryQueryCondition(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static ProjectedInventoryQueryCondition getEnum(String value) {
/* 15 */     return (ProjectedInventoryQueryCondition)getEnum(ProjectedInventoryQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/ProjectedInventoryQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */