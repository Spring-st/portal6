/*    */ package com.aof.service.impl;
/*    */ 
/*    */ import com.aof.dao.UniversalDAO;
/*    */ import com.aof.service.UniversalManager;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UniversalManagerImpl
/*    */   implements UniversalManager
/*    */ {
/*    */   private UniversalDAO universalDAO;
/*    */   
/*    */   public Object load(Class clazz, Serializable idValue) {
/* 13 */     return this.universalDAO.load(clazz, idValue);
/*    */   }
/*    */   
/*    */   public void setUniversalDAO(UniversalDAO universalDAO) {
/* 17 */     this.universalDAO = universalDAO;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/impl/UniversalManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */