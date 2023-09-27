/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.metadata.SiteMailReminderType;
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
/*    */ 
/*    */ public class SiteMailReminder
/*    */   extends AbstractSiteMailReminder
/*    */ {
/*    */   public SiteMailReminder() {}
/*    */   
/*    */   public SiteMailReminder(Site site, SiteMailReminderType type) {
/* 33 */     super(site, type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Date getApproveDate(Date d) {
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


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/SiteMailReminder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */