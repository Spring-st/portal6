/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.BasicPartPriceDao;
/*    */ import com.aof.model.basic.BasicPartPrice;
/*    */ import com.aof.model.basic.query.BasicPartPriceQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.BasicPartPriceManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class BasicPartPriceManagerImpl
/*    */   extends BaseManager
/*    */   implements BasicPartPriceManager
/*    */ {
/*    */   private BasicPartPriceDao dao;
/*    */   
/*    */   public BasicPartPrice save(BasicPartPrice basicPartPrice) {
/* 19 */     return this.dao.save(basicPartPrice);
/*    */   }
/*    */ 
/*    */   
/*    */   public BasicPartPrice getBasicPartPrice(Integer id) {
/* 24 */     return this.dao.getBasicPartPrice(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(BasicPartPrice entity) {
/* 29 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public BasicPartPrice update(BasicPartPrice entity) {
/* 34 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 39 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<BasicPartPrice> getList(Map conditions, int pageNum, int pageSize, BasicPartPriceQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(BasicPartPriceDao dao) {
/* 49 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public BasicPartPriceDao getDao() {
/* 53 */     return this.dao;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/BasicPartPriceManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */