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
/*    */ public class UserDepartmentQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final UserDepartmentQueryOrder SITE_NAME = new UserDepartmentQueryOrder("site_name");
/* 15 */   public static final UserDepartmentQueryOrder DEPARTMENT_NAME = new UserDepartmentQueryOrder("department_name");
/*    */ 
/*    */   
/*    */   protected UserDepartmentQueryOrder(String value) {
/* 19 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserDepartmentQueryOrder getEnum(String value) {
/* 23 */     return (UserDepartmentQueryOrder)getEnum(UserDepartmentQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserDepartmentQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */