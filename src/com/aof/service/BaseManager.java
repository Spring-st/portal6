/*    */ package com.aof.service;
/*    */ 
/*    */ import com.aof.dao.business.BaseApproveRequestDAO;
/*    */ import com.aof.model.admin.User;
/*    */ import com.aof.model.business.Approvable;
/*    */ import com.aof.model.business.BaseApproveRequest;
/*    */ import com.aof.model.metadata.ApproveStatus;
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.Iterator;
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
/*    */ public class BaseManager
/*    */ {
/*    */   protected void insertRejectApproveRequest(BaseApproveRequestDAO dao, Approvable target, User user, String comment) {
/* 29 */     if (user == null) {
/* 30 */       user = new User(new Integer(1));
/*    */     }
/* 32 */     int seq = 1;
/* 33 */     BaseApproveRequest ebar = null;
/* 34 */     for (Iterator<BaseApproveRequest> itor = dao.getBaseApproveRequestListByApproveRequestId(target.getApproveRequestId()).iterator(); itor.hasNext(); ) {
/* 35 */       BaseApproveRequest bar = itor.next();
/* 36 */       if (bar.getApprover().equals(user)) {
/* 37 */         ebar = bar;
/*    */         continue;
/*    */       } 
/* 40 */       if (bar.getSeq() != seq) {
/* 41 */         bar.setSeq(seq);
/* 42 */         dao.updateBaseApproveRequest(bar);
/*    */       } 
/* 44 */       seq++;
/*    */     } 
/* 46 */     if (ebar == null) {
/* 47 */       ebar = target.createNewApproveRequestObj();
/* 48 */       ebar.setApproveRequestId(target.getApproveRequestId());
/* 49 */       ebar.setApprover(user);
/* 50 */       ebar.setActualApprover(user);
/* 51 */       ebar.setApproveDate(new Date());
/* 52 */       ebar.setCanModify(YesNo.NO);
/* 53 */       ebar.setComment(comment);
/* 54 */       ebar.setStatus(ApproveStatus.REJECTED);
/* 55 */       ebar.setSeq(seq);
/* 56 */       dao.insertBaseApproveRequest(ebar);
/*    */     } else {
/* 58 */       ebar.setApproveDate(new Date());
/* 59 */       if (ebar.getComment() != null && ebar.getComment().trim().length() != 0) {
/* 60 */         ebar.setComment(String.valueOf(ebar.getComment()) + ";" + comment);
/*    */       } else {
/* 62 */         ebar.setComment(comment);
/*    */       } 
/* 64 */       ebar.setStatus(ApproveStatus.REJECTED);
/* 65 */       ebar.setSeq(seq);
/* 66 */       dao.updateBaseApproveRequest(ebar);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected long getTodayTimeMillis() {
/* 72 */     Calendar c1 = Calendar.getInstance();
/* 73 */     Calendar c2 = Calendar.getInstance();
/* 74 */     c1.clear();
/* 75 */     c1.set(1, c2.get(1));
/* 76 */     c1.set(2, c2.get(2));
/* 77 */     c1.set(5, c2.get(5));
/* 78 */     return c1.getTimeInMillis();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/BaseManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */