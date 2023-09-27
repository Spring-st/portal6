/*    */ package com.aof.service.inventory.impl;
/*    */ 
/*    */ import com.aof.dao.inventory.InventoryTransitDAO;
/*    */ import com.aof.model.inventory.InventoryTransit;
/*    */ import com.aof.model.inventory.query.InventoryTransitQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.inventory.InventoryTransitManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InventoryTransitManagerImpl
/*    */   extends BaseManager
/*    */   implements InventoryTransitManager {
/*    */   private InventoryTransitDAO dao;
/*    */   
/*    */   public void setDao(InventoryTransitDAO dao) {
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryTransit getInventoryTransit(Integer id) {
/* 22 */     return this.dao.getInventoryTransit(id);
/*    */   }
/*    */   
/*    */   public int getInventoryTransitListCount(Map conditions) {
/* 26 */     return this.dao.getInventoryTransitListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getInventoryTransitList(Map conditions, int pageNo, int pageSize, InventoryTransitQueryOrder order, boolean descend) {
/* 34 */     return this.dao.getInventoryTransitList(conditions, pageNo, pageSize, order, 
/* 35 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryTransit insertInventoryTransit(InventoryTransit transit) {
/* 40 */     return this.dao.insertInventoryTransit(transit);
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryTransit updateInventoryTransit(InventoryTransit transit) {
/* 45 */     return this.dao.updateInventoryTransit(transit);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/inventory/impl/InventoryTransitManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */