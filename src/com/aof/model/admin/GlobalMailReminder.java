/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.metadata.GlobalMailReminderType;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalMailReminder
/*    */   extends AbstractGlobalMailReminder
/*    */ {
/*    */   public GlobalMailReminder() {}
/*    */   
/*    */   public GlobalMailReminder(GlobalMailReminderType type) {
/* 32 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Date getResponseDate(Date d) {
/* 39 */     Calendar c = Calendar.getInstance();
/* 40 */     c.setTime(d);
/* 41 */     c.add(5, -getDueDay());
/* 42 */     return c.getTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getEmailDate(Date d) {
/* 47 */     Calendar c = Calendar.getInstance();
/* 48 */     c.setTime(d);
/* 49 */     c.add(5, -getIntervalDay());
/* 50 */     c.add(10, 1);
/* 51 */     return c.getTime();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/GlobalMailReminder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */