/*    */ package com.aof.web.domain;
/*    */ 
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
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
/*    */ public class SessionListener
/*    */   implements HttpSessionListener
/*    */ {
/*    */   public void sessionCreated(HttpSessionEvent event) {}
/*    */   
/*    */   public void sessionDestroyed(HttpSessionEvent event) {
/* 31 */     SessionList.removeSession(event.getSession());
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/domain/SessionListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */