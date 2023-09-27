/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmailQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final EmailQueryCondition ID_EQ = new EmailQueryCondition("id_eq");
/*    */ 
/*    */   
/* 16 */   public static final EmailQueryCondition MAILFROM_LIKE = new EmailQueryCondition("mailFrom_like");
/*    */   
/* 18 */   public static final EmailQueryCondition MAILTO_LIKE = new EmailQueryCondition("mailTo_like");
/*    */   
/* 20 */   public static final EmailQueryCondition SUBJECT_LIKE = new EmailQueryCondition("subject_like");
/*    */   
/* 22 */   public static final EmailQueryCondition BODY_LIKE = new EmailQueryCondition("body_like");
/*    */   
/* 24 */   public static final EmailQueryCondition CREATETIME_BT = new EmailQueryCondition("createTime_bt");
/*    */   
/* 26 */   public static final EmailQueryCondition SENTTIME_BT = new EmailQueryCondition("sentTime_bt");
/*    */   
/* 28 */   public static final EmailQueryCondition FAILCOUNT_GE = new EmailQueryCondition("failCount_gt");
/*    */   
/* 30 */   public static final EmailQueryCondition WAITTOSEND_EQ = new EmailQueryCondition("waitToSend_eq");
/*    */   
/*    */   protected EmailQueryCondition(String value) {
/* 33 */     super(value);
/*    */   }
/*    */   
/*    */   public static EmailQueryCondition getEnum(String value) {
/* 37 */     return (EmailQueryCondition)getEnum(EmailQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/EmailQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */