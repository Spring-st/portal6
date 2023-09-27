/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.BadReasonsDAO;
/*    */ import com.aof.model.basic.BadReasons;
/*    */ import com.aof.model.basic.query.BadReasonsQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.BadReasonsManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class BadReasonsManagerImpl
/*    */   extends BaseManager
/*    */   implements BadReasonsManager
/*    */ {
/*    */   private BadReasonsDAO dao;
/*    */   
/*    */   public void setDao(BadReasonsDAO dao) {
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public BadReasons getBadReasons(Integer id) {
/* 24 */     return this.dao.getBadReasons(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBadReasonsListCount(Map conditions) {
/* 29 */     return this.dao.getBadReasonsListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getBadReasonsList(Map conditions, int pageNo, int pageSize, BadReasonsQueryOrder order, boolean descend) {
/* 35 */     return this.dao.getBadReasonsList(conditions, pageNo, pageSize, order, 
/* 36 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public BadReasons insertBadReasons(BadReasons city) {
/* 41 */     return this.dao.insertBadReasons(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public BadReasons updateBadReasons(BadReasons city) {
/* 46 */     return this.dao.updateBadReasons(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledBadReasonsList() {
/* 51 */     return this.dao.getEnabledBadReasonsList();
/*    */   }
/*    */   
/*    */   public String[] scanningUnqualifiedReason() {
/* 55 */     List<BadReasons> list = getEnabledBadReasonsList();
/* 56 */     String[] str = new String[list.size()];
/*    */     
/* 58 */     for (int i = 0; i < list.size(); i++) {
/* 59 */       str[i] = ((BadReasons)list.get(i)).getDescribe();
/*    */     }
/*    */     
/* 62 */     return str;
/*    */   }
/*    */ 
/*    */   
/*    */   public BadReasons getBadReasons(String id) {
/* 67 */     return this.dao.getBadReasons(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/BadReasonsManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */