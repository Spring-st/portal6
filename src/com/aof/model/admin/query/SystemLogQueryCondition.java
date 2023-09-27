/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemLogQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final SystemLogQueryCondition ID_EQ = new SystemLogQueryCondition("id_eq");
/*    */   
/* 15 */   public static final SystemLogQueryCondition SITE_ID_EQ = new SystemLogQueryCondition("site_id_eq");
/*    */   
/* 17 */   public static final SystemLogQueryCondition USER_ID_EQ = new SystemLogQueryCondition("user_id_eq");
/*    */   
/* 19 */   public static final SystemLogQueryCondition USER_NAME_LIKE = new SystemLogQueryCondition("user_name_like");
/*    */   
/* 21 */   public static final SystemLogQueryCondition TARGET_EQ = new SystemLogQueryCondition("target_eq");
/*    */   
/* 23 */   public static final SystemLogQueryCondition TARGET_LIKE = new SystemLogQueryCondition("target_like");
/*    */   
/* 25 */   public static final SystemLogQueryCondition CONTENT_LIKE = new SystemLogQueryCondition("content_like");
/*    */   
/* 27 */   public static final SystemLogQueryCondition ACTION_TIME_GT = new SystemLogQueryCondition("action_time_gt");
/*    */   
/* 29 */   public static final SystemLogQueryCondition ACTION_TIME_LT = new SystemLogQueryCondition("action_time_lt");
/*    */   
/*    */   protected SystemLogQueryCondition(String value) {
/* 32 */     super(value);
/*    */   }
/*    */   
/*    */   public static SystemLogQueryCondition getEnum(String value) {
/* 36 */     return (SystemLogQueryCondition)getEnum(SystemLogQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/SystemLogQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */