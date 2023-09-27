/*    */ package com.aof.utils;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateUtils
/*    */ {
/*    */   public static Date getStartDate(Date date) {
/* 12 */     Calendar calendar = Calendar.getInstance();
/* 13 */     calendar.setTime(date);
/* 14 */     calendar.set(11, 0);
/* 15 */     calendar.set(12, 0);
/* 16 */     calendar.set(13, 0);
/* 17 */     return calendar.getTime();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Date getExpireDate(Date date) {
/* 25 */     Calendar calendar = Calendar.getInstance();
/* 26 */     calendar.setTime(date);
/* 27 */     calendar.set(11, 23);
/* 28 */     calendar.set(12, 59);
/* 29 */     calendar.set(13, 59);
/* 30 */     return calendar.getTime();
/*    */   }
/*    */   
/*    */   public static int getDateDiff(Date startDate, Date endDate) {
/* 34 */     Date targetStartDate = getStartDate(startDate);
/* 35 */     Date targetEndDate = getStartDate(endDate);
/* 36 */     long oneDateMill = 86400000L;
/* 37 */     return (int)((targetEndDate.getTime() - targetStartDate.getTime()) / oneDateMill);
/*    */   }
/*    */   
/*    */   public static Date dateAdd(Date targDate, int field, int amount) {
/* 41 */     Calendar calendar = Calendar.getInstance();
/* 42 */     calendar.setTime(targDate);
/* 43 */     calendar.add(field, amount);
/* 44 */     return calendar.getTime();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/DateUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */