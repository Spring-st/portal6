/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.PurchaseManualCreateBarcodeDao;
/*    */ import com.aof.model.po.PurchaseManualCreateBarcode;
/*    */ import com.aof.model.po.query.PurchaseManualCreateBarcodeQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.PurchaseManualCreateBarcodeManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PurchaseManualCreateBarcodeManagerImpl
/*    */   extends BaseManager
/*    */   implements PurchaseManualCreateBarcodeManager
/*    */ {
/*    */   private PurchaseManualCreateBarcodeDao barcodeDao;
/*    */   
/*    */   public PurchaseManualCreateBarcodeDao getBarcodeDao() {
/* 18 */     return this.barcodeDao;
/*    */   }
/*    */   
/*    */   public void setBarcodeDao(PurchaseManualCreateBarcodeDao barcodeDao) {
/* 22 */     this.barcodeDao = barcodeDao;
/*    */   }
/*    */   
/*    */   public PurchaseManualCreateBarcode getPurchaseManualCreateBarcode(Integer id) {
/* 26 */     return this.barcodeDao.getPurchaseManualCreateBarcode(id);
/*    */   }
/*    */   
/*    */   public List list(Map condtions, int pageNo, int pageSize, PurchaseManualCreateBarcodeQueryOrder order, boolean descend) {
/* 30 */     return this.barcodeDao.list(condtions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void update(PurchaseManualCreateBarcode u) {
/* 34 */     this.barcodeDao.update(u);
/*    */   }
/*    */   
/*    */   public void delete(PurchaseManualCreateBarcode u) {
/* 38 */     this.barcodeDao.delete(u);
/*    */   }
/*    */   
/*    */   public PurchaseManualCreateBarcode insert(PurchaseManualCreateBarcode u) {
/* 42 */     return this.barcodeDao.insert(u);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 47 */     return this.barcodeDao.getListCount(conditions);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseManualCreateBarcodeManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */