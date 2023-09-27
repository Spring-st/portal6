/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.StoreRoomDAO;
/*    */ import com.aof.model.basic.StoreRoom;
/*    */ import com.aof.model.basic.query.StoreRoomQueryCondition;
/*    */ import com.aof.model.basic.query.StoreRoomQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.StoreRoomManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StoreRoomManagerImpl
/*    */   extends BaseManager
/*    */   implements StoreRoomManager {
/*    */   private StoreRoomDAO dao;
/*    */   
/*    */   public void setDao(StoreRoomDAO dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public StoreRoom getStoreRoom(Integer id) {
/* 25 */     return this.dao.getStoreRoom(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStoreRoomListCount(Map conditions) {
/* 30 */     return this.dao.getStoreRoomListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getStoreRoomList(Map conditions, int pageNo, int pageSize, StoreRoomQueryOrder order, boolean descend) {
/* 36 */     return this.dao.getStoreRoomList(conditions, pageNo, pageSize, order, 
/* 37 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public StoreRoom insertStoreRoom(StoreRoom city) {
/* 42 */     return this.dao.insertStoreRoom(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public StoreRoom updateStoreRoom(StoreRoom city) {
/* 47 */     return this.dao.updateStoreRoom(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledStoreRoomList() {
/* 52 */     return this.dao.getEnabledStoreRoomList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteStoreRoom(StoreRoom city) {
/* 57 */     this.dao.deleteStoreRoom(city);
/*    */   }
/*    */   
/*    */   public List getStoreRoomListEnabledAll() {
/* 61 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 62 */     conditions.put(StoreRoomQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/* 63 */     return this.dao.getStoreRoomList(conditions, -1, -1, null, true);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/StoreRoomManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */