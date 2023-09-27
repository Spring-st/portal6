/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.UnplannedReasonsDAO;
/*    */ import com.aof.model.basic.UnplannedReasons;
/*    */ import com.aof.model.basic.query.UnplannedReasonsQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.UnplannedReasonsManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class UnplannedReasonsManagerImpl
/*    */   extends BaseManager
/*    */   implements UnplannedReasonsManager {
/*    */   private UnplannedReasonsDAO dao;
/*    */   
/*    */   public void setDao(UnplannedReasonsDAO dao) {
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public UnplannedReasons getUnplannedReasons(Integer id) {
/* 22 */     return this.dao.getUnplannedReasons(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUnplannedReasonsListCount(Map conditions) {
/* 27 */     return this.dao.getUnplannedReasonsListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getUnplannedReasonsList(Map conditions, int pageNo, int pageSize, UnplannedReasonsQueryOrder order, boolean descend) {
/* 33 */     return this.dao.getUnplannedReasonsList(conditions, pageNo, pageSize, order, 
/* 34 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public UnplannedReasons insertUnplannedReasons(UnplannedReasons city) {
/* 39 */     return this.dao.insertUnplannedReasons(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public UnplannedReasons updateUnplannedReasons(UnplannedReasons city) {
/* 44 */     return this.dao.updateUnplannedReasons(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledUnplannedReasonsList() {
/* 49 */     return this.dao.getEnabledUnplannedReasonsList();
/*    */   }
/*    */ 
/*    */   
/*    */   public UnplannedReasons getUnplannedReasons(String id) {
/* 54 */     return this.dao.getUnplannedReasons(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/UnplannedReasonsManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */