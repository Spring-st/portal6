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
/*    */ 
/*    */ public class WebMonitorQueryOrder
/*    */   extends Enum
/*    */ {
/* 19 */   public static final WebMonitorQueryOrder SITE = new WebMonitorQueryOrder("site");
/* 20 */   public static final WebMonitorQueryOrder USER_ID = new WebMonitorQueryOrder("user_id");
/* 21 */   public static final WebMonitorQueryOrder USER_NAME = new WebMonitorQueryOrder("user_name");
/* 22 */   public static final WebMonitorQueryOrder IP = new WebMonitorQueryOrder("ip");
/* 23 */   public static final WebMonitorQueryOrder LOGIN_TIME = new WebMonitorQueryOrder("login_time");
/* 24 */   public static final WebMonitorQueryOrder ACCESS_TIME = new WebMonitorQueryOrder("access_time");
/* 25 */   public static final WebMonitorQueryOrder LIVE_TIME = new WebMonitorQueryOrder("live_time");
/*    */ 
/*    */ 
/*    */   
/*    */   protected WebMonitorQueryOrder(String value) {
/* 30 */     super(value);
/*    */   }
/*    */   
/*    */   public static WebMonitorQueryOrder getEnum(String value) {
/* 34 */     return (WebMonitorQueryOrder)getEnum(WebMonitorQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/WebMonitorQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */