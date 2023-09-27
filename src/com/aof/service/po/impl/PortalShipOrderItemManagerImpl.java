/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.PortalShipOrderItemDAO;
/*    */ import com.aof.model.inventory.InventoryTransit;
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import com.aof.model.po.PortalShipOrderItem;
/*    */ import com.aof.model.po.query.PortalShipOrderQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.PortalShipOrderItemManager;
/*    */ import com.shcnc.struts.action.ActionException;
/*    */ import java.math.BigDecimal;
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
/*    */ public class PortalShipOrderItemManagerImpl
/*    */   extends BaseManager
/*    */   implements PortalShipOrderItemManager
/*    */ {
/*    */   private PortalShipOrderItemDAO dao;
/*    */   
/*    */   public void setDao(PortalShipOrderItemDAO dao) {
/* 36 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public PortalShipOrderItem getPortalShipOrderItem(Integer id) {
/* 41 */     return this.dao.getPortalShipOrderItem(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPortalShipOrderItemListCount(Map conditions) {
/* 46 */     return this.dao.getPortalShipOrderItemListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPortalShipOrderItemList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
/* 52 */     return this.dao.getPortalShipOrderItemList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public PortalShipOrderItem insertPortalShipOrderItem(PortalShipOrderItem po) {
/* 57 */     return this.dao.insertPortalShipOrderItem(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public PortalShipOrderItem updatePortalShipOrderItem(PortalShipOrderItem po) {
/* 62 */     return this.dao.updatePortalShipOrderItem(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledPortalShipOrderItemList() {
/* 67 */     return this.dao.getEnabledPortalShipOrderItemList();
/*    */   }
/*    */   
/*    */   public String updatePortalShipOrderWithdraw(String array) {
/* 71 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 72 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 73 */       PortalShipOrderItem item = getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(id)));
/* 74 */       item.setStatus_confirm(YesNo.NO);
/*    */ 
/*    */       
/* 77 */       String sql = "from InventoryTransit itt where itt.part.id='" + item.getPoipItem().getItemNumber().getId() + "' ";
/* 78 */       List<InventoryTransit> list = this.dao.getObjectList(sql);
/* 79 */       if (list.size() > 0) {
/* 80 */         InventoryTransit transit = list.get(0);
/* 81 */         if (transit.getQty() != null && item.getReceived_qty() != null) {
/* 82 */           transit.setQty(transit.getQty().subtract(item.getReceived_qty()));
/* 83 */           this.dao.updateObject(transit);
/*    */ 
/*    */           
/* 86 */           item.setReceived_qty(new BigDecimal(0));
/* 87 */           updatePortalShipOrderItem(item);
/*    */         } else {
/* 89 */           throw new ActionException("收货数量为0，不能撤回！");
/*    */         } 
/*    */       } else {
/* 92 */         throw new ActionException("收货单不存在！");
/*    */       } 
/*    */       b++; }
/*    */     
/* 96 */     return "ok";
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PortalShipOrderItemManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */