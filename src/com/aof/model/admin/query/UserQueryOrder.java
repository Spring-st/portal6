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
/*    */ public class UserQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final UserQueryOrder LOGINNAME = new UserQueryOrder("loginName");
/* 15 */   public static final UserQueryOrder NAME = new UserQueryOrder("name");
/* 16 */   public static final UserQueryOrder EMAIL = new UserQueryOrder("email");
/* 17 */   public static final UserQueryOrder TELEPHONE = new UserQueryOrder("telephone");
/*    */ 
/*    */ 
/*    */   
/*    */   protected UserQueryOrder(String value) {
/* 22 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserQueryOrder getEnum(String value) {
/* 26 */     return (UserQueryOrder)getEnum(UserQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */