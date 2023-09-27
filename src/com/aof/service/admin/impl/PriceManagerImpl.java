/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.PriceDAO;
/*    */ import com.aof.model.admin.Price;
/*    */ import com.aof.model.admin.query.PriceQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.PriceManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class PriceManagerImpl
/*    */   extends BaseManager
/*    */   implements PriceManager
/*    */ {
/*    */   private PriceDAO dao;
/*    */   
/*    */   public void setPriceDAO(PriceDAO dao) {
/* 33 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Price getPrice(Integer id) {
/* 40 */     return this.dao.getPrice(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Price updatePrice(Price function) {
/* 47 */     return this.dao.updatePrice(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Price insertPrice(Price function) {
/* 54 */     return this.dao.insertPrice(function);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriceListCount(Map conditions) {
/* 62 */     return this.dao.getPriceListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPriceList(Map conditions, int pageNo, int pageSize, PriceQueryOrder order, boolean descend) {
/* 69 */     return this.dao.getPriceList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/PriceManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */