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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemLogQueryOrder
/*    */   extends Enum
/*    */ {
/* 18 */   public static final SystemLogQueryOrder ID = new SystemLogQueryOrder("id");
/* 19 */   public static final SystemLogQueryOrder SITE_NAME = new SystemLogQueryOrder("site_name");
/* 20 */   public static final SystemLogQueryOrder TARGET = new SystemLogQueryOrder("target");
/* 21 */   public static final SystemLogQueryOrder ACTION = new SystemLogQueryOrder("action");
/* 22 */   public static final SystemLogQueryOrder ACTIONTIME = new SystemLogQueryOrder("actionTime");
/* 23 */   public static final SystemLogQueryOrder USER_ID = new SystemLogQueryOrder("user_id");
/* 24 */   public static final SystemLogQueryOrder USER_NAME = new SystemLogQueryOrder("user_name");
/*    */   
/*    */   protected SystemLogQueryOrder(String value) {
/* 27 */     super(value);
/*    */   }
/*    */   
/*    */   public static SystemLogQueryOrder getEnum(String value) {
/* 31 */     return (SystemLogQueryOrder)getEnum(SystemLogQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SystemLogQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */