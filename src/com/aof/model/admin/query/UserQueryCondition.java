/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final UserQueryCondition LOGINNAME_EQ = new UserQueryCondition("loginName_eq");
/* 14 */   public static final UserQueryCondition LOGINNAME_LIKE = new UserQueryCondition("loginName_like");
/* 15 */   public static final UserQueryCondition NAME_LIKE = new UserQueryCondition("name_like");
/* 16 */   public static final UserQueryCondition SITE_ID_EQ = new UserQueryCondition("site_eq");
/* 17 */   public static final UserQueryCondition PRIMARY_OR_SITE_ID_EQ = new UserQueryCondition("primary_or_site_eq");
/* 18 */   public static final UserQueryCondition DEPARTMENT_ID_EQ = new UserQueryCondition("department_eq");
/* 19 */   public static final UserQueryCondition ENABLED_EQ = new UserQueryCondition("enabled_eq");
/* 20 */   public static final UserQueryCondition FUNCTION_ID_EQ = new UserQueryCondition("function_id_eq");
/* 21 */   public static final UserQueryCondition ROLE_ID_EQ = new UserQueryCondition("role_id_eq");
/*    */   
/*    */   protected UserQueryCondition(String value) {
/* 24 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserQueryCondition getEnum(String value) {
/* 28 */     return (UserQueryCondition)getEnum(UserQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */