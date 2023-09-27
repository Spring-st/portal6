/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.InventoryMovingDAO;
/*    */ import com.aof.model.admin.query.InventoryMovingQueryOrder;
/*    */ import com.aof.model.inventory.InventoryMoving;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.InventoryMovingManager;
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
/*    */ public class InventoryMovingManagerImpl
/*    */   extends BaseManager
/*    */   implements InventoryMovingManager
/*    */ {
/*    */   private InventoryMovingDAO dao;
/*    */   
/*    */   public void setDao(InventoryMovingDAO dao) {
/* 29 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryMoving getInventoryMoving(Integer id) {
/* 34 */     return this.dao.getInventoryMoving(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getInventoryMovingListCount(Map conditions) {
/* 39 */     return this.dao.getInventoryMovingListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getInventoryMovingList(Map conditions, int pageNo, int pageSize, InventoryMovingQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getInventoryMovingList(conditions, pageNo, pageSize, order, 
/* 46 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryMoving insertInventoryMoving(InventoryMoving moving) {
/* 51 */     return this.dao.insertInventoryMoving(moving);
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryMoving updateInventoryMoving(InventoryMoving moving) {
/* 56 */     return this.dao.updateInventoryMoving(moving);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/InventoryMovingManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */