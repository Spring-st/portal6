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
/*    */ public class DepartmentQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final DepartmentQueryOrder NAME = new DepartmentQueryOrder("name");
/*    */   
/*    */   protected DepartmentQueryOrder(String value) {
/* 17 */     super(value);
/*    */   }
/*    */   
/*    */   public static DepartmentQueryOrder getEnum(String value) {
/* 21 */     return (DepartmentQueryOrder)getEnum(DepartmentQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/DepartmentQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */