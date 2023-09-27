/*     */ package com.aof.web.webService.impl;
/*     */ 
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.Product.SalesPreshiporderManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.service.basic.BadReasonsManager;
/*     */ import com.aof.service.comprehensive.BoxAdjustmentManager;
/*     */ import com.aof.service.comprehensive.StockingManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
/*     */ import com.aof.service.plantWarehouse.WmsUWManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import com.aof.service.po.PurchaseOrderRQCManager;
/*     */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*     */ import com.aof.web.webService.WmsScanningWebService;
/*     */ 
/*     */ 
/*     */ public class WmsScanningWebServiceImpl
/*     */   implements WmsScanningWebService
/*     */ {
/*     */   private BoxManager boxManager;
/*     */   private PurchaseOrderPutInStorageManager purchaseOrderPutInStorageManager;
/*     */   private PurchaseOrderReceiptsManager purchaseOrderReceiptsManager;
/*     */   private PurchaseOrderRQCManager purchaseOrderRQCManager;
/*     */   private ProductManager productManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private BadReasonsManager badReasonsManager;
/*     */   private WmsPlanToGoOutManager wmsPlanToGoOutManager;
/*     */   private WmsUWManager wmsUWManager;
/*     */   private BoxAdjustmentManager boxAdjustmentManager;
/*     */   private SalesPreshiporderManager salesPreshiporderManager;
/*     */   private SalesWorkorderManager salesWorkorderManager;
/*     */   private StockingManager stockingManager;
/*     */   
/*     */   public void setStockingManager(StockingManager stockingManager) {
/*  37 */     this.stockingManager = stockingManager;
/*     */   }
/*     */   
/*     */   public void setSalesWorkorderManager(SalesWorkorderManager salesWorkorderManager) {
/*  41 */     this.salesWorkorderManager = salesWorkorderManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalesPreshiporderManager(SalesPreshiporderManager salesPreshiporderManager) {
/*  46 */     this.salesPreshiporderManager = salesPreshiporderManager;
/*     */   }
/*     */   
/*     */   public void setBoxAdjustmentManager(BoxAdjustmentManager boxAdjustmentManager) {
/*  50 */     this.boxAdjustmentManager = boxAdjustmentManager;
/*     */   }
/*     */   
/*     */   public void setWmsUWManager(WmsUWManager wmsUWManager) {
/*  54 */     this.wmsUWManager = wmsUWManager;
/*     */   }
/*     */   
/*     */   public void setWmsPlanToGoOutManager(WmsPlanToGoOutManager wmsPlanToGoOutManager) {
/*  58 */     this.wmsPlanToGoOutManager = wmsPlanToGoOutManager;
/*     */   }
/*     */   
/*     */   public void setBadReasonsManager(BadReasonsManager badReasonsManager) {
/*  62 */     this.badReasonsManager = badReasonsManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  66 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setProductManager(ProductManager productManager) {
/*  70 */     this.productManager = productManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderRQCManager(PurchaseOrderRQCManager purchaseOrderRQCManager) {
/*  75 */     this.purchaseOrderRQCManager = purchaseOrderRQCManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderReceiptsManager(PurchaseOrderReceiptsManager purchaseOrderReceiptsManager) {
/*  80 */     this.purchaseOrderReceiptsManager = purchaseOrderReceiptsManager;
/*     */   }
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  84 */     this.boxManager = boxManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderPutInStorageManager(PurchaseOrderPutInStorageManager purchaseOrderPutInStorageManager) {
/*  89 */     this.purchaseOrderPutInStorageManager = purchaseOrderPutInStorageManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderReceipts(String lotSer, String userId) {
/*  97 */     return this.purchaseOrderReceiptsManager.scanningPurchaseOrderReceipts(
/*  98 */         lotSer, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderReceiptsByShipOrder(String code, String userId) {
/* 104 */     return this.purchaseOrderReceiptsManager.scanningPurchaseOrderReceiptsByShipOrder(code, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderRQC(String lotSer, String type, String reason, String userId) {
/* 109 */     return this.purchaseOrderRQCManager.scanningPurchaseOrderRQC(lotSer, type, reason, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorage(String lotSer, String location, String userId) {
/* 116 */     return this.purchaseOrderPutInStorageManager
/* 117 */       .scanningPurchaseOrderPutInStorage(lotSer, location, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorages(String codeSer, String location, String userId) {
/* 124 */     return this.purchaseOrderPutInStorageManager.scanningPurchaseOrderPutInStorages(codeSer, location, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorageByRecommendLocation(String lotSer) {
/* 130 */     return this.purchaseOrderPutInStorageManager.scanningPurchaseOrderPutInStorageByRecommendLocation(lotSer);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorageByOrderRecommendLocation(String codeSer) {
/* 135 */     return this.purchaseOrderPutInStorageManager.scanningPurchaseOrderPutInStorageByOrderRecommendLocation(codeSer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderOutbound(String lot, String userId) {
/* 142 */     return this.inventoryManager.scanningPurchaseOrderOutbound(lot, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningOutboundByProduct(String lot, String userId) {
/* 148 */     return this.productManager.scanningOutboundByProduct(lot, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningProductPacking(String part, String tool, String qty, String userId) {
/* 154 */     return this.boxManager.scanningProductPacking(part, tool, qty, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningProductOutbound(String lotSer, String userId) {
/* 160 */     return this.boxManager.scanningProductOutbound(lotSer, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLocationChange(String lotSer, String locationNew, String userId) {
/* 166 */     return this.boxManager.scanningLocationChange(lotSer, locationNew, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLocationChangeByLocation(String locationNew, String locationOld, String userId) {
/* 172 */     return this.boxManager.scanningLocationChangeByLocation(locationNew, locationOld, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] scanningUnqualifiedReason() {
/* 178 */     return this.badReasonsManager.scanningUnqualifiedReason();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] scanningRqcProgress() {
/* 184 */     return this.purchaseOrderRQCManager.scanningRqcProgress();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningUnplannedOutbound(String lotser, String planToGoOutId, String userId) {
/* 190 */     return this.wmsPlanToGoOutManager.scanningUnplannedOutbound(lotser, planToGoOutId, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningWmsUWPutInStorage(String code, String lotSer, String location, String userId) {
/* 196 */     return this.wmsUWManager.scanningWmsUWPutInStorage(code, lotSer, location, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLotInformation(String lotSer) {
/* 202 */     return this.boxManager.scanningLotInformation(lotSer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLotBreakUp(String lotSer, String nums, String userId) {
/* 208 */     return this.boxAdjustmentManager.scanningLotBreakUp(lotSer, nums, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLotMerge(String lots, String userId) {
/* 214 */     return this.boxAdjustmentManager.scanningLotMerge(lots, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningProductOneProductionLine(String part, String lots, String userId) {
/* 220 */     return this.boxManager.scanningPurchaseOrderHighLineOne(part, lots, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningProductTwolineProductionLine(String lots, String userId) {
/* 226 */     return this.boxManager.scanningPurchaseOrderHighLineTwo(lots, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderOutboundCheck(String lot) {
/* 234 */     return this.boxManager.scanningPurchaseOrderOutboundCheck(lot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanninghighLineBoxOneCheck(String lot) {
/* 244 */     return this.boxManager.scanninghighLineBoxOneCheck(lot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLocationThransfer(String locationCode, String tool, String locationNew, String userId) {
/* 251 */     return this.boxManager.scanningLocationThransfer(locationCode, tool, locationNew, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningLocationThransferSingle(String locationCode, String locationNew, String Number, String userId) {
/* 258 */     return this.boxManager.scanningLocationThransferSingle(locationCode, locationNew, Number, userId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningProductDownline(String locationCode, String tool, String stoLocation, String toolNum, String userId) {
/* 265 */     return this.boxManager.scanningProductDownline(locationCode, tool, stoLocation, toolNum, userId);
/*     */   }
/*     */   public String scanningProductDownlineVi(String locationCode, String tool) {
/* 268 */     return this.boxManager.scanningProductDownlineVi(locationCode, tool);
/*     */   }
/*     */   
/*     */   public String systemMoveLocationThransfer(String array, String location, String userId) {
/* 272 */     return this.boxManager.systemMoveLocationThransfer(array, location, userId);
/*     */   }
/*     */   
/*     */   public String BoxAdjustmentBox(String lot, String str, String type, String userId) {
/* 276 */     return this.boxAdjustmentManager.insertBoxAdjustmentBox(lot, str, type, userId);
/*     */   }
/*     */   
/*     */   public String ListBoxInfoNumber(String lot) {
/* 280 */     return this.boxAdjustmentManager.ListBoxInfoNumber(lot);
/*     */   }
/*     */   
/*     */   public String hnCodeListBox(String hnCode) {
/* 284 */     return this.boxManager.hnCodeListBox(hnCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesOutStock(String lotSer, String order, String userId) {
/* 289 */     return this.salesPreshiporderManager.scanningSalesOutStock(lotSer, order, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesDelivery(String lotSer, String order, String userId) {
/* 294 */     return this.salesWorkorderManager.scanningSalesDelivery(lotSer, order, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningInventory(String lotSer, String location, String order, String userId) {
/* 299 */     return this.stockingManager.scanningInventory(lotSer, location, order, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderBatchStorage(String lotSer, String location, String userId) {
/* 304 */     return this.purchaseOrderPutInStorageManager
/* 305 */       .scanningPurchaseOrderPutInStorage(lotSer, location, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesLoadingWithdraw(String lotSer, String order, String userId) {
/* 310 */     return this.salesWorkorderManager.scanningSalesLoadingWithdraw(lotSer, order, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningProductInLocationNumber(String lotSer, String userId) {
/* 315 */     return this.inventoryManager.scanningProductInLocationNumber(lotSer, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesStockToWithdraw(String lotSer, String locationCode, String order, String userId) {
/* 320 */     return this.salesPreshiporderManager.scanningSalesStockToWithdraw(lotSer, locationCode, order, userId);
/*     */   }
/*     */   
/*     */   public String[] scanningSearchInventory(String lotSer, String userId) {
/* 324 */     return this.inventoryManager.scanningSearchInventory(lotSer, userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningInventoryByPart(String lotSer, String order, String userId) {
/* 329 */     return this.stockingManager.scanningInventoryByPart(lotSer, order, userId);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/webService/impl/WmsScanningWebServiceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */