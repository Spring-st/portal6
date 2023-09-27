/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.UselessPartDAO;
/*    */ import com.aof.model.basic.UselessPart;
/*    */ import com.aof.model.basic.query.UselessPartQueryOrder;
/*    */ import com.aof.service.basic.UselessPartManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UselessPartManagerImpl
/*    */   implements UselessPartManager
/*    */ {
/*    */   private UselessPartDAO uselessPartDAO;
/*    */   
/*    */   public UselessPartDAO getUselessPartDAO() {
/* 18 */     return this.uselessPartDAO;
/*    */   }
/*    */   
/*    */   public void setUselessPartDAO(UselessPartDAO uselessPartDAO) {
/* 22 */     this.uselessPartDAO = uselessPartDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public UselessPart getUselessPart(Integer id) {
/* 27 */     return this.uselessPartDAO.getUselessPart(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUselessPartListCount(Map conditions) {
/* 32 */     return this.uselessPartDAO.getUselessPartListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getUselessPartList(Map conditions, int pageNo, int pageSize, UselessPartQueryOrder order, boolean descend) {
/* 38 */     return this.uselessPartDAO.getUselessPartList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public void insertUselessPart(UselessPart part) {
/* 43 */     this.uselessPartDAO.insertUselessPart(part);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateUselessPart(UselessPart part) {
/* 49 */     this.uselessPartDAO.updateUselessPart(part);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deleteUselessPart(UselessPart part) {
/* 55 */     this.uselessPartDAO.deleteUselessPart(part);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/UselessPartManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */