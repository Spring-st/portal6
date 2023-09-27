/*    */ package com.aof.model.business.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ public class BaseApproveQueryCondition
/*    */   extends Enum
/*    */ {
/*  9 */   public static final BaseApproveQueryCondition APPROVER_ID_EQ = new BaseApproveQueryCondition("approver_id_eq");
/*    */   
/* 11 */   public static final BaseApproveQueryCondition SITE_ID_EQ = new BaseApproveQueryCondition("site_id_eq");
/*    */   
/* 13 */   public static final BaseApproveQueryCondition STATUS_EQ = new BaseApproveQueryCondition("status_eq");
/*    */   
/* 15 */   public static final BaseApproveQueryCondition YOUR_TURN_DATE_LE = new BaseApproveQueryCondition("your_turn_date_le");
/*    */   
/* 17 */   public static final BaseApproveQueryCondition LAST_EMAIL_DATE_LE = new BaseApproveQueryCondition("last_email_date_le");
/*    */   
/* 19 */   public static final BaseApproveQueryCondition SENT_EMAIL_TIMES_LT = new BaseApproveQueryCondition("sent_email_times_lt");
/*    */   
/* 21 */   public static final BaseApproveQueryCondition LAST_APPROVED_DATE_GE = new BaseApproveQueryCondition("LAST_APPROVED_DATE_GE");
/*    */   
/* 23 */   public static final BaseApproveQueryCondition LAST_APPROVED_DATE_LE = new BaseApproveQueryCondition("LAST_APPROVED_DATE_LE");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected BaseApproveQueryCondition(String value) {
/* 34 */     super(value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/query/BaseApproveQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */