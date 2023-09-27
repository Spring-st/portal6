/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DepartmentQueryCondition
/*    */   extends Enum
/*    */ {
/* 14 */   public static final DepartmentQueryCondition SITE_EQ = new DepartmentQueryCondition("site_eq");
/* 15 */   public static final DepartmentQueryCondition ENABLED_EQ = new DepartmentQueryCondition("enabled_eq");
/*    */   
/*    */   protected DepartmentQueryCondition(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static DepartmentQueryCondition getEnum(String value) {
/* 22 */     return (DepartmentQueryCondition)getEnum(DepartmentQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/DepartmentQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */