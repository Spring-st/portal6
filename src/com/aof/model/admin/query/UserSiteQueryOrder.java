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
/*    */ public class UserSiteQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final UserSiteQueryOrder SITE_NAME = new UserSiteQueryOrder("site_name");
/*    */ 
/*    */   
/*    */   protected UserSiteQueryOrder(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static UserSiteQueryOrder getEnum(String value) {
/* 22 */     return (UserSiteQueryOrder)getEnum(UserSiteQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/UserSiteQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */