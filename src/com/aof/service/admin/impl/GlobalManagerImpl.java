/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.GlobalDAO;
/*    */ import com.aof.model.admin.GlobalParameter;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.GlobalManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalManagerImpl
/*    */   extends BaseManager
/*    */   implements GlobalManager
/*    */ {
/*    */   private GlobalDAO dao;
/*    */   
/*    */   public GlobalParameter getParameter() {
/* 19 */     return this.dao.getParameter();
/*    */   }
/*    */   
/*    */   public GlobalParameter saveUser(GlobalParameter para) {
/* 23 */     return this.dao.saveUser(para);
/*    */   }
/*    */   
/*    */   public void setGlobalDAO(GlobalDAO dao) {
/* 27 */     this.dao = dao;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/GlobalManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */