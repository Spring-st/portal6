/*     */ package com.aof.web.domain;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.RWLock;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SessionList
/*     */ {
/*  36 */   private static final List sessionList = new ArrayList();
/*     */   
/*  38 */   private static int totalUserVisitCount = 0;
/*     */   
/*     */   public static void resetTotalUserVisitCount() {
/*  41 */     totalUserVisitCount = 0;
/*     */   }
/*     */   
/*     */   public static int getTotalUserVisitCount() {
/*  45 */     return totalUserVisitCount;
/*     */   }
/*     */   
/*  48 */   public static final RWLock lock = new RWLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addSession(HttpSession session) {
/*  56 */     lock.getWriteLock();
/*  57 */     if (!sessionList.contains(session) && 
/*  58 */       session.getAttribute("LOGIN_USER") != null) {
/*     */ 
/*     */       
/*  61 */       sessionList.add(session);
/*  62 */       totalUserVisitCount++;
/*     */     } 
/*     */     
/*  65 */     lock.releaseLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeSession(HttpSession session) {
/*  73 */     lock.getWriteLock();
/*  74 */     if (sessionList.contains(session)) {
/*  75 */       sessionList.remove(session);
/*  76 */       SessionTempFile.clearTempFile(session);
/*     */     } 
/*  78 */     lock.releaseLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getSessionList() {
/*  86 */     return sessionList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getSessionListBySite(Site site, HttpServletRequest request) {
/*  96 */     List<HttpSession> retList = new ArrayList();
/*  97 */     UserManager um = ServiceLocator.getUserManager(request);
/*  98 */     for (int index = 0; index < sessionList.size(); index++) {
/*     */       try {
/* 100 */         HttpSession session = sessionList.get(index);
/* 101 */         User loginUser = (User)session.getAttribute("LOGIN_USER");
/* 102 */         if (um.getUserSite(loginUser.getId(), site.getId()) != null)
/* 103 */           retList.add(session); 
/* 104 */       } catch (Exception exception) {}
/*     */     } 
/* 106 */     return retList;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/domain/SessionList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */