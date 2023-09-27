/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.PurchaseOrderDAO;
/*    */ import com.aof.model.po.PurchaseOrder;
/*    */ import com.aof.model.po.PurchaseOrderDetial;
/*    */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.PurchaseOrderManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PurchaseOrderManagerImpl
/*    */   extends BaseManager
/*    */   implements PurchaseOrderManager {
/*    */   private PurchaseOrderDAO purchaseOrderDAO;
/*    */   
/*    */   public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
/* 18 */     this.purchaseOrderDAO = purchaseOrderDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseOrder getPurchaseOrder(String id) {
/* 23 */     return this.purchaseOrderDAO.getPurchaseOrder(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLastPoApplicationCode() {
/* 28 */     return this.purchaseOrderDAO.getLastPoApplicationCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 33 */     return this.purchaseOrderDAO.getMaxPoApplicationIdBeginWith(prefix);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPurchaseOrderListCount(Map conditions) {
/* 38 */     return this.purchaseOrderDAO.getPurchaseOrderListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPurchaseOrderList(Map conditions, int pageNo, int pageSize, PurchaseOrderQueryOrder order, boolean descend) {
/* 44 */     return this.purchaseOrderDAO.getPurchaseOrderList(conditions, pageNo, 
/* 45 */         pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseOrder insertPurchaseOrder(PurchaseOrder po) {
/* 50 */     return this.purchaseOrderDAO.insertPurchaseOrder(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseOrder updatePurchaseOrder(PurchaseOrder po) {
/* 55 */     return this.purchaseOrderDAO.updatePurchaseOrder(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledPurchaseOrderList() {
/* 60 */     return this.purchaseOrderDAO.getEnabledPurchaseOrderList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void deletePurchaseOrder(PurchaseOrder po) {
/* 65 */     this.purchaseOrderDAO.deletePurchaseOrder(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PurchaseOrderDetial> getPurchaseOrderItemListByOrderId(String id) {
/* 70 */     return this.purchaseOrderDAO.getPurchaseOrderItemListByOrderId(id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPurchaseOrderInspectionPendingItemListByPoipId(String poipId, String partId) {
/* 76 */     return this.purchaseOrderDAO
/* 77 */       .getPurchaseOrderInspectionPendingItemListByPoipId(poipId, 
/* 78 */         partId);
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseOrderDetial getPurchaseOrderDetial(Integer id) {
/* 83 */     return this.purchaseOrderDAO.getPurchaseOrderDetial(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public PurchaseOrderDetial updatePurchaseOrderDetial(PurchaseOrderDetial detial) {
/* 88 */     return this.purchaseOrderDAO.updatePurchaseOrderDetial(detial);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */