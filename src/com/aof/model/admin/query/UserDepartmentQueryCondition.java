/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserDepartmentQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final UserDepartmentQueryCondition USER_ID_EQ = new UserDepartmentQueryCondition("user_id_eq");
/* 14 */   public static final UserDepartmentQueryCondition SITE_ID_EQ = new UserDepartmentQueryCondition("site_id_eq");
/*    */   
/*    */   protected UserDepartmentQueryCondition(String value) {
/* 17 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserDepartmentQueryCondition getEnum(String value) {
/* 21 */     return (UserDepartmentQueryCondition)getEnum(UserDepartmentQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserDepartmentQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */