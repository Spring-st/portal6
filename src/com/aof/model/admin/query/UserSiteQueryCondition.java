/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserSiteQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final UserSiteQueryCondition USER_ID_EQ = new UserSiteQueryCondition("user_id_eq");
/*    */   
/*    */   protected UserSiteQueryCondition(String value) {
/* 16 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserSiteQueryCondition getEnum(String value) {
/* 20 */     return (UserSiteQueryCondition)getEnum(UserSiteQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserSiteQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */