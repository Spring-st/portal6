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
/*    */ public class EmailQueryOrder
/*    */   extends Enum
/*    */ {
/* 18 */   public static final EmailQueryOrder ID = new EmailQueryOrder("id");
/* 19 */   public static final EmailQueryOrder MAILFROM = new EmailQueryOrder("mailFrom");
/* 20 */   public static final EmailQueryOrder MAILTO = new EmailQueryOrder("mailTo");
/* 21 */   public static final EmailQueryOrder SUBJECT = new EmailQueryOrder("subject");
/* 22 */   public static final EmailQueryOrder BODY = new EmailQueryOrder("body");
/* 23 */   public static final EmailQueryOrder CREATETIME = new EmailQueryOrder("createTime");
/* 24 */   public static final EmailQueryOrder SENTTIME = new EmailQueryOrder("sentTime");
/* 25 */   public static final EmailQueryOrder FAILCOUNT = new EmailQueryOrder("failCount");
/* 26 */   public static final EmailQueryOrder WAITTOSEND = new EmailQueryOrder("status");
/*    */   
/*    */   protected EmailQueryOrder(String value) {
/* 29 */     super(value);
/*    */   }
/*    */   
/*    */   public static EmailQueryOrder getEnum(String value) {
/* 33 */     return (EmailQueryOrder)getEnum(EmailQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/EmailQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */