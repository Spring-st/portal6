/*      */ package com.aof.service.po.impl;
/*      */ 
/*      */ import com.aof.dao.DAO;
/*      */ import com.aof.dao.basic.ScanLogDAO;
/*      */ import com.aof.dao.po.BoxDAO;
/*      */ import com.aof.model.admin.FinishedToolPutnumber;
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.basic.BadReasons;
/*      */ import com.aof.model.basic.ScanLog;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.WmsTool;
/*      */ import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
/*      */ import com.aof.model.comprehensive.Bom;
/*      */ import com.aof.model.comprehensive.ProduceBuckleMaterial;
/*      */ import com.aof.model.inventory.InventoryMoving;
/*      */ import com.aof.model.inventory.InventoryTransit;
/*      */ import com.aof.model.inventory.query.InventoryTransitQueryCondition;
/*      */ import com.aof.model.metadata.BoxStatus;
/*      */ import com.aof.model.metadata.BoxStatusRqc;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.InventoryType;
/*      */ import com.aof.model.metadata.PoHighLineStatus;
/*      */ import com.aof.model.metadata.ProductBelowLineStatus;
/*      */ import com.aof.model.metadata.PurchaseOrderPutInStorageStatus;
/*      */ import com.aof.model.metadata.PurchaseOrderStatus;
/*      */ import com.aof.model.metadata.StoreRoomType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.PoHighLineOne;
/*      */ import com.aof.model.po.PoHighLineTwo;
/*      */ import com.aof.model.po.PortalShipOrderItem;
/*      */ import com.aof.model.po.ProduceRejectedMaterial;
/*      */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*      */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*      */ import com.aof.model.po.PurchaseOrderRqcUnqualified;
/*      */ import com.aof.model.po.WmsLot;
/*      */ import com.aof.model.po.query.BoxQueryOrder;
/*      */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*      */ import com.aof.model.product.Product;
/*      */ import com.aof.model.product.ProductBelowLine;
/*      */ import com.aof.model.product.ProductGoline;
/*      */ import com.aof.service.BaseManager;
/*      */ import com.aof.service.InventoryTool;
/*      */ import com.aof.service.Product.ProductGolineManager;
/*      */ import com.aof.service.Properties;
/*      */ import com.aof.service.admin.InventoryMovingManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.basic.StorageLocationManager;
/*      */ import com.aof.service.basic.WmsPartManager;
/*      */ import com.aof.service.basic.WmsToolManager;
/*      */ import com.aof.service.inventory.InventoryManager;
/*      */ import com.aof.service.inventory.InventoryTransitManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.PortalShipOrderItemManager;
/*      */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*      */ import com.aof.web.struts.action.product.ProductGolineAction;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ public class BoxManagerImpl
/*      */   extends BaseManager
/*      */   implements BoxManager
/*      */ {
/*      */   private BoxDAO boxDAO;
/*      */   private InventoryManager inventoryManager;
/*      */   private ScanLogDAO scanLogDAO;
/*      */   private SupplierManager supplierManager;
/*      */   private StorageLocationManager storageLocationManager;
/*      */   private WmsPartManager wmsPartManager;
/*      */   private WmsToolManager wmsToolManager;
/*      */   private ProductGolineManager productGolineManager;
/*      */   private InventoryTransitManager inventoryTransitManager;
/*      */   private InventoryMovingManager inventoryMovingManager;
/*      */   private PortalShipOrderItemManager portalShipOrderItemManager;
/*      */   private PurchaseOrderPutInStorageManager purchaseOrderPutInStorageManager;
/*      */   
/*      */   public PurchaseOrderPutInStorageManager getPurchaseOrderPutInStorageManager() {
/*   89 */     return this.purchaseOrderPutInStorageManager;
/*      */   }
/*      */   
/*      */   public void setPurchaseOrderPutInStorageManager(PurchaseOrderPutInStorageManager purchaseOrderPutInStorageManager) {
/*   93 */     this.purchaseOrderPutInStorageManager = purchaseOrderPutInStorageManager;
/*      */   }
/*      */   
/*      */   public PortalShipOrderItemManager getPortalShipOrderItemManager() {
/*   97 */     return this.portalShipOrderItemManager;
/*      */   }
/*      */   
/*      */   public void setPortalShipOrderItemManager(PortalShipOrderItemManager portalShipOrderItemManager) {
/*  101 */     this.portalShipOrderItemManager = portalShipOrderItemManager;
/*      */   }
/*      */   
/*      */   public InventoryTransitManager getInventoryTransitManager() {
/*  105 */     return this.inventoryTransitManager;
/*      */   }
/*      */   
/*      */   public void setInventoryTransitManager(InventoryTransitManager inventoryTransitManager) {
/*  109 */     this.inventoryTransitManager = inventoryTransitManager;
/*      */   }
/*      */   
/*      */   public void setInventoryMovingManager(InventoryMovingManager inventoryMovingManager) {
/*  113 */     this.inventoryMovingManager = inventoryMovingManager;
/*      */   }
/*      */   
/*      */   public void setProductGolineManager(ProductGolineManager productGolineManager) {
/*  117 */     this.productGolineManager = productGolineManager;
/*      */   }
/*      */   
/*      */   public void setWmsToolManager(WmsToolManager wmsToolManager) {
/*  121 */     this.wmsToolManager = wmsToolManager;
/*      */   }
/*      */   
/*      */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  125 */     this.wmsPartManager = wmsPartManager;
/*      */   }
/*      */   
/*      */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/*  129 */     this.storageLocationManager = storageLocationManager;
/*      */   }
/*      */   
/*      */   public void setSupplierManager(SupplierManager supplierManager) {
/*  133 */     this.supplierManager = supplierManager;
/*      */   }
/*      */   
/*      */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  137 */     this.scanLogDAO = scanLogDAO;
/*      */   }
/*      */   
/*      */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  141 */     this.inventoryManager = inventoryManager;
/*      */   }
/*      */   
/*      */   public void setBoxDAO(BoxDAO boxDAO) {
/*  145 */     this.boxDAO = boxDAO;
/*      */   }
/*      */ 
/*      */   
/*      */   public Box getBox(Integer id) {
/*  150 */     return this.boxDAO.getBox(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBoxListCount(Map conditions) {
/*  155 */     return this.boxDAO.getBoxListCount(conditions);
/*      */   }
/*      */ 
/*      */   
/*      */   public List getBoxList(Map conditions, int pageNo, int pageSize, BoxQueryOrder order, boolean descend) {
/*  160 */     return this.boxDAO.getBoxList(conditions, pageNo, pageSize, order, descend);
/*      */   }
/*      */ 
/*      */   
/*      */   public Integer getBoxByPoipItem(int id) {
/*  165 */     return this.boxDAO.getBoxByPoipItem(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByChildItem(int id) {
/*  170 */     return this.boxDAO.getBoxByChildItem(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxBylotSer(String lotSer) {
/*  175 */     return this.boxDAO.getBoxBylotSer(lotSer);
/*      */   }
/*      */ 
/*      */   
/*      */   public Box getBoxBylotSer2(String lotSer) {
/*  180 */     return this.boxDAO.getBoxBylotSer2(lotSer);
/*      */   }
/*      */ 
/*      */   
/*      */   public Box updateBox(Box box) {
/*  185 */     return this.boxDAO.updateBox(box);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByLocation(String location) {
/*  190 */     return this.boxDAO.getBoxByLocation(location);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByLocationAndPart(String location, String part) {
/*  195 */     return this.boxDAO.getBoxByLocationAndPart(location, part);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByPoritem(Integer id) {
/*  200 */     return this.boxDAO.getBoxByPoritem(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByReceiptsItem(Integer id) {
/*  205 */     return this.boxDAO.getBoxByReceiptsItem(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByLocationAndIsPutIntStorage(Integer id) {
/*  210 */     return this.boxDAO.getBoxByLocationAndIsPutIntStorage(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List exportBoxByDate(Date startDate, Date endDate) {
/*  215 */     return this.boxDAO.exportBoxByDate(startDate, endDate);
/*      */   }
/*      */ 
/*      */   
/*      */   public List exportBoxByendDate(Date endDate) {
/*  220 */     return this.boxDAO.exportBoxByendDate(endDate);
/*      */   }
/*      */ 
/*      */   
/*      */   public Integer getBoxListByReceiptIsAllPrint(String id) {
/*  225 */     return this.boxDAO.getBoxListByReceiptIsAllPrint(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Object[]> getBoxLocationNumberByGroupBy() {
/*  230 */     return this.boxDAO.getBoxLocationNumberByGroupBy();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Map> getBoxLocationNumberByGroupByCountLocationType() {
/*  235 */     return this.boxDAO.getBoxLocationNumberByGroupByCountLocationType();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Object[]> getBoxByLoation(String code) {
/*  240 */     return this.boxDAO.getBoxByLoation(code);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBoxItemListCount(Map conditions) {
/*  245 */     return this.boxDAO.getBoxItemListCount(conditions);
/*      */   }
/*      */ 
/*      */   
/*      */   public List getBoxItemList(Map conditions, int pageNo, int pageSize, PoPartSumNumberQueryOrder order, boolean descend) {
/*  250 */     return this.boxDAO.getBoxItemList(conditions, pageNo, pageSize, order, descend);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByRqcItemAndPart(Integer id, String partId) {
/*  255 */     return this.boxDAO.getBoxByRqcItemAndPart(id, partId);
/*      */   }
/*      */ 
/*      */   
/*      */   public Integer getPwoBoxByPurchaseOrderReceipts(Integer id) {
/*  260 */     return this.boxDAO.getPwoBoxByPurchaseOrderReceipts(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getRecommendLot(String arrays) {
/*  265 */     List<Box> boxs = new ArrayList<Box>();
/*  266 */     String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
/*  267 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  268 */       Box box = this.boxDAO.getBoxBylotSer2(id);
/*  269 */       boxs.add(box);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       b++; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  307 */     return boxs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void insertBox(String part, String tool, String amount, String date, User user) {
/*      */     try {
/*  313 */       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
/*  314 */       Date newDate = dateFormat.parse(date);
/*      */       
/*  316 */       Box box = new Box();
/*  317 */       WmsLot wl = (new InventoryTool((DAO)this.boxDAO)).insertWOBox(part, "yyyyMMdd", newDate);
/*  318 */       WmsPart wmsPart = (WmsPart)this.boxDAO.getObject(WmsPart.class, part);
/*      */ 
/*      */       
/*  321 */       String sql = "from StorageLocation sl where sl.basic_storeroom_id.type=11";
/*  322 */       List<StorageLocation> listLocation = this.boxDAO.getObjectList(sql);
/*  323 */       if (listLocation.size() > 0) {
/*  324 */         StorageLocation location = listLocation.get(0);
/*  325 */         box.setLot(wl);
/*  326 */         box.setLocation(location);
/*  327 */         box.setPart(wmsPart);
/*  328 */         box.setNumber(new BigDecimal(amount));
/*  329 */         box.setIn_date(new Date());
/*  330 */         box.setStatus(BoxStatus.HASBEENINTO);
/*  331 */         box.setStatus_print(YesNo.NO);
/*  332 */         box.setStatus_freeze(YesNo.NO);
/*  333 */         box.setIsSync(YesNo.NO);
/*  334 */         box.setType(YesNo.YES);
/*  335 */         box.setIsPrint(YesNo.NO);
/*  336 */         List<WmsTool> listtool = this.boxDAO.getObjectList("from WmsTool wt where wt.id = '" + tool + "' ");
/*  337 */         if (listtool.size() > 0) {
/*  338 */           WmsTool wmsTool = listtool.get(0);
/*  339 */           box.setTool(wmsTool);
/*      */         } 
/*      */ 
/*      */         
/*  343 */         this.boxDAO.saveObject(box);
/*      */ 
/*      */         
/*  346 */         this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.PRODUCELINE, Boolean.valueOf(false));
/*      */ 
/*      */         
/*  349 */         String sqlBom = "from Bom bom where bom.product_no='" + part + "' ";
/*  350 */         List<Bom> listBom = this.boxDAO.getObjectList(sqlBom);
/*  351 */         for (Bom bom : listBom) {
/*  352 */           WmsPart childPart = bom.getChild_part();
/*      */           
/*  354 */           String sqlpoLine = "from StorageLocation sl where sl.basic_storeroom_id.type=5 ";
/*  355 */           List<StorageLocation> poLineList = this.boxDAO.getObjectList(sqlpoLine);
/*  356 */           if (poLineList.size() > 0) {
/*  357 */             StorageLocation poLocation = poLineList.get(0);
/*  358 */             BigDecimal sumQty = box.getNumber().multiply(bom.getUnit_qty());
/*  359 */             this.inventoryManager.inventoryAdjustmentByWoLotProduce(poLocation.getCode(), childPart, sumQty, 
/*  360 */                 InventoryType.LINELIBRARYOUT, Boolean.valueOf(true));
/*      */             
/*  362 */             ProduceBuckleMaterial material = new ProduceBuckleMaterial();
/*  363 */             material.setBom_id(bom);
/*  364 */             material.setPart(childPart);
/*  365 */             material.setLocation(poLocation);
/*  366 */             material.setDate(new Date());
/*  367 */             material.setIs_sync(YesNo.NO);
/*  368 */             material.setQty(sumQty);
/*      */             
/*  370 */             this.boxDAO.saveObject(material);
/*      */           } 
/*      */         } 
/*      */       } 
/*  374 */     } catch (Exception e) {
/*  375 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public String scanningProductOutbound(String lotSer, String userId) {
/*  380 */     ScanLog scanLog = new ScanLog();
/*  381 */     scanLog.setDate(new Date());
/*  382 */     scanLog.setDescribe(lotSer);
/*  383 */     scanLog.setType(Integer.valueOf(6));
/*  384 */     User user = (User)this.scanLogDAO.getObject(User.class, userId);
/*  385 */     if (user != null) {
/*  386 */       scanLog.setUserId(user);
/*      */     }
/*  388 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */ 
/*      */     
/*      */     try {
/*  392 */       List<Box> boxList = this.boxDAO.getObjectList("from Box box where box.id = '" + lotSer + "' and box.status='3' ");
/*  393 */       if (boxList.size() != 0) {
/*  394 */         Box box = boxList.get(0);
/*      */         
/*  396 */         List<StorageLocation> listLocation = this.boxDAO.getObjectList("from StorageLocation sl where sl.code = '" + 
/*  397 */             box.getLocation().getCode() + "' and sl.freeae_status <> 0 ");
/*  398 */         if (listLocation.size() == 0) {
/*  399 */           return "false";
/*      */         }
/*      */         
/*  402 */         List<WmsPart> listWmsPart = this.boxDAO.getObjectList("from WmsPart part where part.id = '" + box.getPart().getId() + 
/*  403 */             "' and sl.freeze_status <> 0 ");
/*  404 */         if (listWmsPart.size() == 0) {
/*  405 */           return "false";
/*      */         }
/*      */         
/*  408 */         Product product = new Product();
/*  409 */         product.setDate(new Date());
/*  410 */         product.setLocation(box.getLocation());
/*  411 */         product.setUserId(user);
/*  412 */         product.setBox(box);
/*  413 */         product.setQty(box.getNumber());
/*  414 */         product.setIsSync(YesNo.NO);
/*  415 */         this.boxDAO.saveObject(product);
/*      */ 
/*      */         
/*  418 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/*      */ 
/*      */ 
/*      */         
/*  422 */         box.setOut_date(new Date());
/*  423 */         box.setStatus(BoxStatus.HASTHE);
/*  424 */         this.boxDAO.updateObject(box);
/*      */       } 
/*  426 */     } catch (Exception e) {
/*  427 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  430 */     return "true";
/*      */   }
/*      */   
/*      */   public List<Map> updateSelectBoxById(String str) {
/*  434 */     List<Map> list = new ArrayList<Map>();
/*  435 */     String[] ids = str.split(","); byte b; int i; String[] arrayOfString1;
/*  436 */     for (i = (arrayOfString1 = ids).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  437 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*  438 */       Box box = this.boxDAO.getBoxBylotSer2(id);
/*      */       
/*  440 */       if (box.getSupplierCode() != null) {
/*  441 */         Supplier supplier = this.supplierManager.getSupplierByCode(box.getSupplierCode());
/*  442 */         map.put("supplierCode", supplier.getCode());
/*      */       } else {
/*  444 */         map.put("supplierCode", box.getPo_supp());
/*      */       } 
/*  446 */       if (box.getPart() != null) {
/*  447 */         map.put("describe1", box.getPart().getDescribe1());
/*  448 */         map.put("describe2", box.getPart().getDescribe2());
/*  449 */         map.put("part", box.getPart().getId());
/*  450 */         map.put("unit", box.getPart().getUnit());
/*      */       } else {
/*  452 */         map.put("describe1", "");
/*  453 */         map.put("describe2", "");
/*  454 */         map.put("part", "");
/*  455 */         map.put("unit", "");
/*      */       } 
/*  457 */       if (box.getLocation() != null) {
/*  458 */         map.put("location", box.getLocation().getCode());
/*      */       } else {
/*  460 */         map.put("location", "");
/*      */       } 
/*  462 */       map.put("boxId", box.getId());
/*  463 */       map.put("amount", box.getNumber());
/*  464 */       map.put("lot", box.getLot().getId());
/*  465 */       list.add(map);
/*      */       b++; }
/*      */     
/*  468 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getBomByBox(String part) {
/*  473 */     return this.boxDAO.getBomByBox(part);
/*      */   }
/*      */ 
/*      */   
/*      */   public String systemMoveLocationThransfer(String array, String location, String userId) {
/*  478 */     ScanLog scanLog = new ScanLog();
/*  479 */     scanLog.setDate(new Date());
/*  480 */     scanLog.setDescribe(String.valueOf(array) + "|" + location);
/*  481 */     scanLog.setType(Integer.valueOf(15));
/*  482 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/*  483 */     if (user != null) {
/*  484 */       scanLog.setUserId(user);
/*      */     }
/*  486 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     try {
/*  488 */       List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.lot.id='" + array + "' ");
/*  489 */       if (boxs.size() > 0) {
/*  490 */         Box box = boxs.get(0);
/*  491 */         String status = box.getStatus().toString();
/*  492 */         if (status.equals("3") || status.equals("4")) {
/*  493 */           StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/*      */           
/*  495 */           if (storageLocation == null) {
/*  496 */             return String.valueOf(array) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */           }
/*  498 */           if (storageLocation.getFreeae_status() == YesNo.YES) {
/*  499 */             return String.valueOf(storageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.location.freeze");
/*      */           }
/*  501 */           StorageLocation oldstorageLocation = box.getLocation();
/*  502 */           if (oldstorageLocation == null) {
/*  503 */             return String.valueOf(array) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */           }
/*  505 */           if (oldstorageLocation.getFreeae_status() == YesNo.YES) {
/*  506 */             return String.valueOf(array) + "," + oldstorageLocation.getCode() + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*      */           }
/*  508 */           if (storageLocation != null && oldstorageLocation != null) {
/*      */             
/*  510 */             InventoryMoving imr = new InventoryMoving();
/*  511 */             imr.setOld_location(box.getLocation());
/*  512 */             imr.setNew_location(storageLocation);
/*  513 */             imr.setLotSer(box.getLot());
/*  514 */             imr.setPart(box.getPart());
/*  515 */             Site site = (Site)this.boxDAO.getObject(Site.class, Integer.valueOf(2));
/*  516 */             if (site != null) {
/*  517 */               imr.setSite(site);
/*      */             }
/*  519 */             imr.setDomain("YA01");
/*  520 */             imr.setOldLotSer(box.getLot());
/*      */             
/*  522 */             imr.setQty(box.getNumber());
/*  523 */             imr.setRemark("移库扫描：" + array);
/*  524 */             imr.setDate(new Date());
/*  525 */             imr.setIs_sync(YesNo.NO);
/*  526 */             this.boxDAO.saveObject(imr);
/*      */             
/*  528 */             box.setLocation(storageLocation);
/*  529 */             this.boxDAO.updateBox(box);
/*      */ 
/*      */             
/*  532 */             this.inventoryManager.inventoryAdjustmentByWoLot(oldstorageLocation.getCode(), box, InventoryType.COMMONOUT, 
/*  533 */                 Boolean.valueOf(true));
/*  534 */             this.inventoryManager
/*  535 */               .inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */           } else {
/*  537 */             return "F";
/*      */           } 
/*      */         } else {
/*  540 */           return "No";
/*      */         } 
/*      */       } else {
/*  543 */         return "N";
/*      */       }
/*      */     
/*  546 */     } catch (Exception e) {
/*  547 */       return e.getMessage();
/*      */     } 
/*      */     
/*  550 */     return "ok";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String systemMoveLocation(String array, String location, User userId) {
/*  561 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/*  562 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*      */       try {
/*  564 */         ScanLog scanLog = new ScanLog();
/*  565 */         scanLog.setDate(new Date());
/*  566 */         scanLog.setDescribe(String.valueOf(id) + "|" + location);
/*  567 */         scanLog.setType(Integer.valueOf(15));
/*  568 */         scanLog.setUserId(userId);
/*  569 */         this.scanLogDAO.insertScanLog(scanLog);
/*      */         
/*  571 */         List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.lot.id='" + id + "' ");
/*  572 */         if (boxs.size() > 0)
/*  573 */         { Box box = boxs.get(0);
/*      */           
/*  575 */           StorageLocation oldstorageLocation = box.getLocation();
/*  576 */           if (oldstorageLocation.getFreeae_status() == YesNo.YES) {
/*  577 */             return String.valueOf(oldstorageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*      */           }
/*  579 */           StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/*  580 */           if (storageLocation != null && oldstorageLocation != null) {
/*      */             
/*  582 */             if (storageLocation.getFreeae_status() == YesNo.YES) {
/*  583 */               return String.valueOf(storageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.location.freeze");
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  609 */             InventoryMoving imr = new InventoryMoving();
/*  610 */             imr.setOld_location(box.getLocation());
/*  611 */             imr.setNew_location(storageLocation);
/*  612 */             imr.setPart(box.getPart());
/*  613 */             Site site = (Site)this.boxDAO.getObject(Site.class, Integer.valueOf(2));
/*  614 */             if (site != null) {
/*  615 */               imr.setSite(site);
/*      */             }
/*  617 */             imr.setDomain("YA01");
/*  618 */             imr.setOldLotSer(box.getLot());
/*  619 */             imr.setQty(box.getNumber());
/*  620 */             imr.setDate(new Date());
/*  621 */             imr.setLotSer(box.getLot());
/*  622 */             imr.setIs_sync(YesNo.NO);
/*  623 */             imr.setRemark("移库扫描：" + box.getLot().getId());
/*  624 */             this.boxDAO.saveObject(imr);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  635 */             box.setLocation(storageLocation);
/*  636 */             this.boxDAO.updateBox(box);
/*      */ 
/*      */             
/*  639 */             this.inventoryManager.inventoryAdjustmentByWoLot(oldstorageLocation.getCode(), box, InventoryType.COMMONOUT, 
/*  640 */                 Boolean.valueOf(true));
/*  641 */             this.inventoryManager
/*  642 */               .inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */           } else {
/*      */             
/*  645 */             return String.valueOf(id) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */           }  }
/*  647 */         else { return String.valueOf(id) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null"); }
/*      */       
/*  649 */       } catch (Exception e) {
/*  650 */         return e.getMessage();
/*      */       } 
/*      */       b++; }
/*      */     
/*  654 */     return "ok";
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningProductDownlineVi(String locationCode, String tool) {
/*  659 */     List<ProductGoline> go = this.boxDAO.getObjectList("from ProductGoline go where go.shCode = '" + locationCode + "'");
/*  660 */     if (go.size() == 0) {
/*  661 */       return "AB";
/*      */     }
/*  663 */     ProductGoline pro = go.get(0);
/*  664 */     String part = pro.getHncCode();
/*      */     
/*  666 */     List<FinishedToolPutnumber> FinishedTool = this.boxDAO.getObjectList("from FinishedToolPutnumber fi where fi.finishedCode='" + part + "'");
/*  667 */     FinishedToolPutnumber prtool = FinishedTool.get(0);
/*  668 */     BigDecimal toolCount = prtool.getPutNumber();
/*  669 */     String strCount = toolCount.toString();
/*  670 */     return strCount;
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningProductDownline(String locationCode, String tool, String stoLocation, String toolNum, String userId) {
/*  675 */     ScanLog scanLog = new ScanLog();
/*  676 */     scanLog.setDate(new Date());
/*  677 */     scanLog.setDescribe(String.valueOf(locationCode) + "|" + tool + "|" + stoLocation + "|" + toolNum);
/*  678 */     scanLog.setType(Integer.valueOf(12));
/*  679 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/*  680 */     if (user != null) {
/*  681 */       scanLog.setUserId(user);
/*      */     }
/*  683 */     this.scanLogDAO.insertScanLog(scanLog);
/*  684 */     String strCount = null;
/*  685 */     String hnCode = "";
/*  686 */     StorageLocation oldLocation = null;
/*  687 */     List<ProductGoline> golineList = new ArrayList<ProductGoline>();
/*  688 */     InventoryMoving moving = new InventoryMoving();
/*      */     
/*      */     try {
/*  691 */       String subcode = locationCode.substring(23, locationCode.length());
/*      */       
/*  693 */       int toolNumber = Integer.parseInt(toolNum);
/*      */       
/*  695 */       Date date = new Date();
/*      */       
/*  697 */       List<StorageLocation> storage = this.boxDAO.getObjectList("from StorageLocation st where st.code='" + stoLocation + "' ");
/*      */       
/*  699 */       if (storage.size() == 0) {
/*  700 */         return "locationNull2";
/*      */       }
/*      */       
/*  703 */       StorageLocation storagelist = storage.get(0);
/*      */       
/*  705 */       List<FinishedToolPutnumber> FinishedTool = this.boxDAO.getObjectList("from FinishedToolPutnumber fi where fi.toolCode='" + tool + "'");
/*      */       
/*  707 */       if (FinishedTool.size() == 0) {
/*  708 */         return "FinishedToolNull";
/*      */       }
/*      */       
/*  711 */       FinishedToolPutnumber prtool = FinishedTool.get(0);
/*      */       
/*  713 */       List<StorageLocation> storageXBK = this.boxDAO.getObjectList("from StorageLocation st where st.code='XBK001' ");
/*  714 */       StorageLocation storagelistXBK = storageXBK.get(0);
/*  715 */       String XBKID = storagelistXBK.getId().toString();
/*      */       
/*  717 */       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*  718 */       String datetime = format.format(date);
/*      */       
/*  720 */       String sql = "from ProductGoline p where p.totalNumber='" + subcode + "' and p.status=2  and p.locationCode.id='" + 
/*  721 */         XBKID + "' order by p.storageDate";
/*      */       
/*  723 */       List<ProductGoline> subgo = this.boxDAO.getObjectList(sql);
/*  724 */       if (subgo.size() == 0) {
/*  725 */         return "subNull";
/*      */       }
/*      */       
/*  728 */       if (toolNumber > subgo.size()) {
/*  729 */         return "OutOf";
/*      */       }
/*      */       
/*  732 */       for (int i = 0; i < toolNumber; i++) {
/*  733 */         ProductGoline pro = subgo.get(i);
/*  734 */         if (oldLocation == null) {
/*  735 */           oldLocation = pro.getLocationCode();
/*      */         }
/*  737 */         pro.setLocationCode(storagelist);
/*  738 */         pro.setTool(prtool);
/*  739 */         pro.setStatus(Integer.valueOf(3));
/*  740 */         this.productGolineManager.updateProductGoline(pro);
/*      */         
/*  742 */         golineList.add(pro);
/*      */         
/*  744 */         hnCode = ((ProductGoline)golineList.get(0)).getHncCode();
/*      */       } 
/*      */       
/*  747 */       WmsPart wmsPart = this.wmsPartManager.getWmsPart(hnCode);
/*  748 */       BigDecimal qty = new BigDecimal(0);
/*  749 */       BigDecimal bigNum = new BigDecimal(toolNum);
/*  750 */       int bNum = Integer.parseInt(toolNum);
/*  751 */       BigDecimal rqty = qty.add(new BigDecimal(-bNum));
/*  752 */       moving.setOld_location(oldLocation);
/*  753 */       moving.setPart(wmsPart);
/*  754 */       moving.setNew_location(storagelist);
/*  755 */       moving.setQty(bigNum);
/*  756 */       moving.setDate(new Date());
/*  757 */       moving.setIs_sync(YesNo.NO);
/*  758 */       moving.setRemark("成品入库：" + oldLocation.getCode() + "-" + storagelist.getCode() + ",成品号：" + wmsPart.getId());
/*  759 */       this.inventoryMovingManager.insertInventoryMoving(moving);
/*  760 */       ProductGolineAction action = new ProductGolineAction();
/*      */       
/*  762 */       action.insertInventoryDetial(wmsPart, storagelist, this.inventoryManager, bigNum);
/*  763 */       action.insertInventoryDetial(wmsPart, oldLocation, this.inventoryManager, rqty);
/*  764 */       String ref = String.valueOf(subcode) + "," + tool + "," + stoLocation + "," + toolNum;
/*  765 */       return ref;
/*  766 */     } catch (Exception e) {
/*  767 */       e.printStackTrace();
/*  768 */       return "Error";
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getNeedStr(String str, int length) {
/*  924 */     int strLength = length;
/*  925 */     String needStr = "";
/*  926 */     for (int i = 0; i < strLength - str.length(); i++) {
/*  927 */       needStr = String.valueOf(needStr) + "0";
/*      */     }
/*  929 */     needStr = String.valueOf(needStr) + str;
/*  930 */     return needStr;
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningLocationThransferSingle(String locationCode, String locationNew, String Number, String userId) {
/*  935 */     ScanLog scanLog = new ScanLog();
/*  936 */     scanLog.setDate(new Date());
/*  937 */     scanLog.setDescribe(String.valueOf(locationCode) + "|" + locationNew);
/*  938 */     scanLog.setType(Integer.valueOf(7));
/*  939 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/*  940 */     if (user != null) {
/*  941 */       scanLog.setUserId(user);
/*      */     }
/*  943 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/*      */     try {
/*  946 */       String subcode = locationCode.substring(23, locationCode.length());
/*      */       
/*  948 */       List<ProductGoline> golineList = new ArrayList<ProductGoline>();
/*      */       
/*  950 */       InventoryMoving moving = new InventoryMoving();
/*      */       
/*  952 */       StorageLocation oldLocation = null;
/*      */       
/*  954 */       List<StorageLocation> storage = this.boxDAO.getObjectList("from StorageLocation st where st.code='" + locationNew + "'");
/*  955 */       if (storage.size() == 0) {
/*  956 */         return "NullSto";
/*      */       }
/*      */       
/*  959 */       StorageLocation st = storage.get(0);
/*      */ 
/*      */       
/*  962 */       List<ProductGoline> list = this.boxDAO.getObjectList("from ProductGoline p where p.totalNumber='" + subcode + 
/*  963 */           "' and p.status=3 order by p.storageDate ");
/*      */       
/*  965 */       int number = Integer.parseInt(Number);
/*      */       
/*  967 */       if (number > list.size()) {
/*  968 */         return "Null";
/*      */       }
/*      */       
/*  971 */       String hncode = "";
/*  972 */       for (int i = 0; i < number; i++) {
/*  973 */         ProductGoline pro = list.get(i);
/*  974 */         hncode = pro.getHncCode();
/*  975 */         if (locationNew.equals("xbk001")) {
/*      */           
/*  977 */           if (oldLocation == null)
/*      */           {
/*  979 */             oldLocation = pro.getLocationCode();
/*      */           }
/*  981 */           pro.setLocationCode(st);
/*  982 */           pro.setStatus(Integer.valueOf(2));
/*  983 */           pro.setTool(null);
/*  984 */           this.productGolineManager.updateProductGoline(pro);
/*  985 */           golineList.add(pro);
/*      */         } else {
/*  987 */           if (oldLocation == null)
/*      */           {
/*  989 */             oldLocation = pro.getLocationCode();
/*      */           }
/*  991 */           pro.setLocationCode(st);
/*  992 */           this.productGolineManager.updateProductGoline(pro);
/*  993 */           golineList.add(pro);
/*      */         } 
/*      */       } 
/*  996 */       BigDecimal num = new BigDecimal(0);
/*  997 */       BigDecimal qty = num.add(new BigDecimal(-number));
/*  998 */       BigDecimal NewQtyNum = num.add(new BigDecimal(number));
/*      */       
/* 1000 */       WmsPart part = this.wmsPartManager.getWmsPart(hncode);
/* 1001 */       moving.setOld_location(oldLocation);
/* 1002 */       moving.setPart(part);
/* 1003 */       moving.setNew_location(st);
/* 1004 */       moving.setQty(NewQtyNum);
/* 1005 */       moving.setDate(new Date());
/* 1006 */       moving.setIs_sync(YesNo.NO);
/* 1007 */       moving.setRemark("成品移库：" + oldLocation.getCode() + "-" + st.getCode() + ",成品号：" + part.getId());
/* 1008 */       this.inventoryMovingManager.insertInventoryMoving(moving);
/* 1009 */       ProductGolineAction action = new ProductGolineAction();
/* 1010 */       action.insertInventoryDetial(part, oldLocation, this.inventoryManager, qty);
/* 1011 */       action.insertInventoryDetial(part, st, this.inventoryManager, NewQtyNum);
/*      */       
/* 1013 */       String s = NewQtyNum.toString();
/* 1014 */       String ret = String.valueOf(subcode) + "," + locationNew + "," + s;
/* 1015 */       return ret;
/*      */     }
/* 1017 */     catch (Exception e) {
/* 1018 */       e.printStackTrace();
/* 1019 */       return "Error";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningLocationThransfer(String locationCode, String tool, String locationNew, String userId) {
/* 1025 */     ScanLog scanLog = new ScanLog();
/* 1026 */     scanLog.setDate(new Date());
/* 1027 */     scanLog.setDescribe(String.valueOf(locationCode) + "|" + tool + "|" + locationNew);
/* 1028 */     scanLog.setType(Integer.valueOf(7));
/* 1029 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1030 */     if (user != null) {
/* 1031 */       scanLog.setUserId(user);
/*      */     }
/* 1033 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     try {
/* 1035 */       String locationIdString = "";
/* 1036 */       List<ProductGoline> goline = this.boxDAO.getObjectList("from ProductGoline go where go.shCode='" + locationCode + "'");
/* 1037 */       List<StorageLocation> storage = this.boxDAO.getObjectList("from StorageLocation st where st.code='" + locationNew + "'");
/* 1038 */       List<FinishedToolPutnumber> FinishedTool = this.boxDAO.getObjectList("from FinishedToolPutnumber fi where fi.toolCode='" + tool + "'");
/* 1039 */       List<ProductGoline> golineList = new ArrayList<ProductGoline>();
/* 1040 */       ProductGoline pg = null;
/* 1041 */       InventoryMoving moving = new InventoryMoving();
/* 1042 */       StorageLocation oldLocation = null;
/*      */       
/* 1044 */       if (goline.size() > 0) {
/* 1045 */         if (locationNew.equals("xbk001") && 
/* 1046 */           !tool.equals("")) {
/* 1047 */           return "tool";
/*      */         }
/*      */ 
/*      */         
/* 1051 */         if (!locationNew.equals("xbk001") && !tool.equals("")) {
/* 1052 */           ProductGoline go = goline.get(0);
/* 1053 */           if (go.getLocationCode().getCode().equals("xbk001")) {
/* 1054 */             return "noCode";
/*      */           }
/* 1056 */           StorageLocation st = storage.get(0);
/* 1057 */           FinishedToolPutnumber finput = FinishedTool.get(0);
/* 1058 */           int location_code_id = go.getId().intValue();
/* 1059 */           int location_code = st.getId().intValue();
/* 1060 */           String toolcode = null;
/* 1061 */           String hncode = go.getHncCode();
/* 1062 */           String locode = go.getLocationCode().getCode();
/*      */           
/* 1064 */           for (int i = 0; i < goline.size(); i++) {
/* 1065 */             pg = go;
/* 1066 */             if (oldLocation == null) {
/* 1067 */               oldLocation = pg.getLocationCode();
/*      */             }
/* 1069 */             if (st.getCode().equals("XBK001")) {
/* 1070 */               pg.setLocationCode(st);
/*      */               
/* 1072 */               pg.setStatus(Integer.valueOf(2));
/* 1073 */               pg.setTool(null);
/* 1074 */               this.productGolineManager.updateProductGoline(pg);
/*      */ 
/*      */               
/* 1077 */               golineList.add(pg);
/*      */             } else {
/* 1079 */               pg.setLocationCode(st);
/* 1080 */               pg.setTool(finput);
/* 1081 */               this.productGolineManager.updateProductGoline(pg);
/* 1082 */               toolcode = pg.getTool().getToolCode();
/* 1083 */               golineList.add(pg);
/*      */             } 
/*      */           } 
/*      */           
/* 1087 */           BigDecimal num = new BigDecimal(0);
/* 1088 */           BigDecimal qty = num.add(new BigDecimal(-1));
/* 1089 */           BigDecimal NewQtyNum = num.add(new BigDecimal(1));
/*      */           
/* 1091 */           WmsPart part = this.wmsPartManager.getWmsPart(hncode);
/* 1092 */           moving.setOld_location(oldLocation);
/* 1093 */           moving.setPart(part);
/* 1094 */           moving.setNew_location(st);
/* 1095 */           moving.setQty(NewQtyNum);
/* 1096 */           moving.setDate(new Date());
/* 1097 */           moving.setIs_sync(YesNo.NO);
/* 1098 */           moving.setRemark("移库扫描：" + oldLocation.getCode() + "-" + st.getCode() + ",成品号：" + part.getId());
/* 1099 */           this.inventoryMovingManager.insertInventoryMoving(moving);
/* 1100 */           ProductGolineAction action = new ProductGolineAction();
/* 1101 */           action.insertInventoryDetial(part, oldLocation, this.inventoryManager, qty);
/* 1102 */           action.insertInventoryDetial(part, st, this.inventoryManager, num.add(new BigDecimal(1)));
/*      */           
/* 1104 */           String s = Integer.toString(1);
/* 1105 */           String ret = String.valueOf(locationCode) + "," + toolcode + "," + locationNew + "," + s;
/* 1106 */           return ret;
/*      */         } 
/* 1108 */         return "add";
/*      */       } 
/* 1110 */       if (FinishedTool.size() > 0) {
/* 1111 */         FinishedToolPutnumber fin = FinishedTool.get(0);
/* 1112 */         int id = fin.getId().intValue();
/* 1113 */         List<ProductGoline> listgo = this.boxDAO.getObjectList("from ProductGoline go where go.tool='" + id + "' and status=3");
/* 1114 */         ProductGoline productgo = listgo.get(0);
/* 1115 */         StorageLocation st = storage.get(0);
/*      */ 
/*      */         
/* 1118 */         String hncode = productgo.getHncCode();
/* 1119 */         String locode = productgo.getLocationCode().getCode();
/*      */         
/* 1121 */         for (int i = 0; i < listgo.size(); i++) {
/* 1122 */           pg = listgo.get(i);
/* 1123 */           if (oldLocation == null) {
/* 1124 */             oldLocation = pg.getLocationCode();
/*      */           }
/* 1126 */           if (st.getCode().equals("XBK001")) {
/* 1127 */             pg.setLocationCode(st);
/* 1128 */             pg.setStatus(Integer.valueOf(2));
/* 1129 */             pg.setTool(null);
/* 1130 */             this.productGolineManager.updateProductGoline(pg);
/* 1131 */             golineList.add(pg);
/*      */           } else {
/* 1133 */             pg.setLocationCode(st);
/* 1134 */             this.productGolineManager.updateProductGoline(pg);
/* 1135 */             golineList.add(pg);
/*      */           } 
/*      */         } 
/*      */         
/* 1139 */         BigDecimal num = new BigDecimal(0);
/* 1140 */         int oldNum = golineList.size();
/* 1141 */         BigDecimal bigNum = new BigDecimal(oldNum);
/* 1142 */         BigDecimal qty = num.add(new BigDecimal(-oldNum));
/*      */         
/* 1144 */         WmsPart part = this.wmsPartManager.getWmsPart(hncode);
/* 1145 */         moving.setOld_location(oldLocation);
/* 1146 */         moving.setPart(part);
/* 1147 */         moving.setNew_location(st);
/* 1148 */         moving.setQty(bigNum);
/* 1149 */         moving.setDate(new Date());
/* 1150 */         moving.setIs_sync(YesNo.NO);
/* 1151 */         moving.setRemark("移库扫描：" + oldLocation.getCode() + "-" + st.getCode() + ",成品号：" + part.getId());
/* 1152 */         this.inventoryMovingManager.insertInventoryMoving(moving);
/* 1153 */         ProductGolineAction action = new ProductGolineAction();
/* 1154 */         action.insertInventoryDetial(part, oldLocation, this.inventoryManager, qty);
/* 1155 */         action.insertInventoryDetial(part, st, this.inventoryManager, bigNum);
/* 1156 */         int str = listgo.size();
/* 1157 */         String s = Integer.toString(str);
/* 1158 */         String ret = String.valueOf(hncode) + "," + tool + "," + locationNew + "," + s;
/* 1159 */         return ret;
/*      */       } 
/* 1161 */       return "no";
/*      */     }
/* 1163 */     catch (Exception e) {
/* 1164 */       e.printStackTrace();
/* 1165 */       return e.getMessage();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String scanningLocationChange(String lotSer, String locationNew, String userId) {
/* 1172 */     ScanLog scanLog = new ScanLog();
/* 1173 */     scanLog.setDate(new Date());
/* 1174 */     scanLog.setDescribe(String.valueOf(lotSer) + "|" + locationNew);
/* 1175 */     scanLog.setType(Integer.valueOf(7));
/* 1176 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1177 */     if (user != null) {
/* 1178 */       scanLog.setUserId(user);
/*      */     }
/* 1180 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/*      */     try {
/* 1183 */       String locationIdString = "";
/* 1184 */       List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.lot.id='" + lotSer + "'");
/* 1185 */       if (boxs.size() > 0)
/* 1186 */       { Box box = boxs.get(0);
/*      */         
/* 1188 */         String locationOld = box.getLocation().getCode();
/* 1189 */         if (!locationOld.equals(locationNew))
/* 1190 */         { StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(locationNew);
/* 1191 */           StorageLocation oldstorageLocation = this.storageLocationManager.getStorageLocation(locationOld);
/*      */           
/* 1193 */           if (storageLocation != null && oldstorageLocation != null) {
/* 1194 */             locationIdString = String.valueOf(locationIdString) + lotSer + "," + storageLocation.getCode() + ";";
/*      */             
/* 1196 */             InventoryMoving imr = new InventoryMoving();
/* 1197 */             imr.setOld_location(box.getLocation());
/* 1198 */             imr.setNew_location(oldstorageLocation);
/* 1199 */             imr.setPart(box.getPart());
/* 1200 */             imr.setLotSer(box.getLot());
/*      */             
/* 1202 */             Site site = (Site)this.boxDAO.getObject(Site.class, Integer.valueOf(2));
/* 1203 */             if (site != null) {
/* 1204 */               imr.setSite(site);
/*      */             }
/* 1206 */             imr.setOldLotSer(box.getLot());
/* 1207 */             imr.setDomain("YA01");
/* 1208 */             imr.setQty(box.getNumber());
/* 1209 */             imr.setDate(new Date());
/* 1210 */             imr.setIs_sync(YesNo.NO);
/* 1211 */             this.boxDAO.saveObject(imr);
/*      */             
/* 1213 */             box.setLocation(storageLocation);
/* 1214 */             this.boxDAO.updateBox(box);
/*      */ 
/*      */             
/* 1217 */             this.inventoryManager.inventoryAdjustmentByWoLot(oldstorageLocation.getCode(), box, InventoryType.COMMONOUT, 
/* 1218 */                 Boolean.valueOf(true));
/* 1219 */             this.inventoryManager
/* 1220 */               .inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */           } else {
/* 1222 */             return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */           }  }
/* 1224 */         else { return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null"); }
/*      */          }
/* 1226 */       else { return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.polotSer.is.null"); }
/*      */     
/* 1228 */     } catch (Exception e) {
/* 1229 */       return e.getMessage();
/*      */     } 
/*      */     
/* 1232 */     return "ok";
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningLocationChangeByLocation(String locationNew, String locationOld, String userId) {
/* 1237 */     ScanLog scanLog = new ScanLog();
/* 1238 */     scanLog.setDate(new Date());
/* 1239 */     scanLog.setDescribe("locationNew:" + locationNew + "|locationOld" + locationOld);
/* 1240 */     scanLog.setType(Integer.valueOf(15));
/*      */     
/* 1242 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1243 */     if (user != null) {
/* 1244 */       scanLog.setUserId(user);
/*      */     }
/* 1246 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/*      */     try {
/* 1249 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(locationNew);
/* 1250 */       StorageLocation oldstorageLocation = this.storageLocationManager.getStorageLocation(locationOld);
/*      */ 
/*      */       
/* 1253 */       if (storageLocation == null || oldstorageLocation == null) {
/* 1254 */         return String.valueOf(locationOld) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */       }
/*      */ 
/*      */       
/* 1258 */       StoreRoomType newType = storageLocation.getBasic_storeroom_id().getType();
/* 1259 */       StoreRoomType oldType = oldstorageLocation.getBasic_storeroom_id().getType();
/* 1260 */       if (!newType.equals(oldType)) {
/* 1261 */         return String.valueOf(locationOld) + ":" + Properties.getPropertiesValye("scan.sync.error.location.type.error");
/*      */       }
/*      */ 
/*      */       
/* 1265 */       String oldLocationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 1266 */         oldstorageLocation.getCode() + "' ";
/* 1267 */       List<Object> oldLocationsumQtyList = this.boxDAO.getObjectList(oldLocationsumQty);
/* 1268 */       BigDecimal oldSumInit = new BigDecimal(0);
/* 1269 */       if (oldLocationsumQtyList.size() > 0 && oldLocationsumQtyList.get(0) != null) {
/* 1270 */         oldSumInit = (BigDecimal)oldLocationsumQtyList.get(0);
/*      */       }
/*      */ 
/*      */       
/* 1274 */       String locationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 1275 */         storageLocation.getCode() + "' ";
/* 1276 */       List<Object> locationsumQtyList = this.boxDAO.getObjectList(locationsumQty);
/*      */       
/* 1278 */       BigDecimal sumQty = new BigDecimal(0);
/*      */ 
/*      */       
/* 1281 */       if (locationsumQtyList.size() > 0 && locationsumQtyList.get(0) != null) {
/* 1282 */         BigDecimal sumLocationQty = (BigDecimal)locationsumQtyList.get(0);
/*      */         
/* 1284 */         sumQty = oldSumInit.add(sumLocationQty);
/*      */       } else {
/* 1286 */         sumQty = oldSumInit;
/*      */       } 
/*      */       
/* 1289 */       BigDecimal max_inventory = storageLocation.getMax_inventory();
/*      */       
/* 1291 */       if (max_inventory.compareTo(sumQty) == -1) {
/* 1292 */         return String.valueOf(locationNew) + ":" + Properties.getPropertiesValye("scan.sync.error.location.inventory.notEnough");
/*      */       }
/*      */ 
/*      */       
/* 1296 */       List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.location.code='" + locationOld + "'");
/*      */       
/* 1298 */       for (Box box : boxs) {
/* 1299 */         if (box.getLocation().getCode().equals(locationOld)) {
/* 1300 */           if (!box.getStatus().equals(BoxStatus.HASTHE) && !locationOld.equals(locationNew)) {
/*      */             
/* 1302 */             InventoryMoving imr = new InventoryMoving();
/* 1303 */             imr.setOld_location(box.getLocation());
/* 1304 */             imr.setNew_location(oldstorageLocation);
/* 1305 */             imr.setLotSer(box.getLot());
/* 1306 */             imr.setPart(box.getPart());
/*      */             
/* 1308 */             Site site = (Site)this.boxDAO.getObject(Site.class, Integer.valueOf(2));
/* 1309 */             if (site != null) {
/* 1310 */               imr.setSite(site);
/*      */             }
/* 1312 */             imr.setOldLotSer(box.getLot());
/* 1313 */             imr.setDomain("YA01");
/* 1314 */             imr.setQty(box.getNumber());
/* 1315 */             imr.setDate(new Date());
/* 1316 */             imr.setIs_sync(YesNo.NO);
/* 1317 */             this.boxDAO.saveObject(imr);
/* 1318 */             box.setLocation(storageLocation);
/* 1319 */             this.boxDAO.updateBox(box);
/* 1320 */             this.inventoryManager.inventoryAdjustmentByWoLot(oldstorageLocation.getCode(), box, InventoryType.COMMONOUT, 
/* 1321 */                 Boolean.valueOf(true));
/* 1322 */             this.inventoryManager
/* 1323 */               .inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */           }  continue;
/*      */         } 
/* 1326 */         return String.valueOf(locationOld) + ":" + Properties.getPropertiesValye("scan.sync.error.lot_ser.not.in");
/*      */       }
/*      */     
/* 1329 */     } catch (Exception e) {
/* 1330 */       return e.getMessage();
/*      */     } 
/*      */     
/* 1333 */     return "ok";
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningProductPackingBySystem(String wmspart, String tool, String shcode, String userId) {
/* 1338 */     ScanLog scanLog = new ScanLog();
/* 1339 */     scanLog.setDate(new Date());
/* 1340 */     scanLog.setDescribe(wmspart);
/* 1341 */     scanLog.setType(Integer.valueOf(5));
/* 1342 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1343 */     if (user != null) {
/* 1344 */       scanLog.setUserId(user);
/*      */     }
/* 1346 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/*      */     try {
/* 1349 */       WmsPart part = this.wmsPartManager.getWmsPart(wmspart);
/* 1350 */       if (part != null)
/*      */       
/* 1352 */       { ProductBelowLine belowLine = new ProductBelowLine();
/* 1353 */         belowLine.setUser(user);
/* 1354 */         belowLine.setPart(part);
/* 1355 */         belowLine.setQty(new BigDecimal(1));
/* 1356 */         belowLine.setStatus(ProductBelowLineStatus.WAIT);
/* 1357 */         belowLine.setDate(new Date());
/* 1358 */         belowLine.setTest3(shcode);
/*      */         
/* 1360 */         if (tool.equals("")) {
/* 1361 */           List<StorageLocation> locations = this.boxDAO.getObjectList("from StorageLocation sl where sl.code = 'CCP-00A' ");
/* 1362 */           if (locations.size() > 0) {
/* 1363 */             StorageLocation location = locations.get(0);
/* 1364 */             belowLine.setLocation(location);
/*      */ 
/*      */             
/* 1367 */             this.inventoryManager.inventoryProduceByWoLot(location.getCode(), null, 
/* 1368 */                 InventoryType.FINISHPRODUCTINSTORAGEIN, Boolean.valueOf(false), part);
/*      */           } else {
/* 1370 */             return String.valueOf(wmspart) + ":" + Properties.getPropertiesValye("scan.sync.error.tool.or.location.null");
/*      */           } 
/*      */         } else {
/*      */           
/* 1374 */           StorageLocation produceLineLocation = this.storageLocationManager.getProduceLineLocation();
/*      */           
/* 1376 */           belowLine.setLocation(produceLineLocation);
/*      */ 
/*      */           
/* 1379 */           WmsTool wmsTool = this.wmsToolManager.getWmsTool(tool);
/* 1380 */           wmsTool.setStatus(YesNo.YES);
/* 1381 */           if (wmsTool.getQty() == null) {
/* 1382 */             wmsTool.setQty(new BigDecimal(0));
/*      */           }
/* 1384 */           wmsTool.setQty(wmsTool.getQty().add(new BigDecimal(1)));
/* 1385 */           wmsTool.setIn_date(new Date());
/* 1386 */           wmsTool.setPart(part);
/* 1387 */           wmsTool.setLocation(produceLineLocation);
/*      */           
/* 1389 */           belowLine.setTool(wmsTool);
/*      */           
/* 1391 */           if (produceLineLocation != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1396 */             this.inventoryManager.inventoryProduceByWoLot(produceLineLocation.getCode(), wmsTool, 
/* 1397 */                 InventoryType.PRODUCELINE, Boolean.valueOf(false), null);
/*      */           } else {
/* 1399 */             return String.valueOf(wmspart) + ":" + Properties.getPropertiesValye("scan.sync.error.tool.or.location.null");
/*      */           } 
/*      */         } 
/*      */         
/* 1403 */         updatePoHighLine(part, shcode);
/*      */         
/* 1405 */         this.boxDAO.saveObject(belowLine); }
/*      */       else
/* 1407 */       { return String.valueOf(wmspart) + ":" + Properties.getPropertiesValye("scan.sync.error.part.null"); } 
/* 1408 */     } catch (Exception e) {
/* 1409 */       e.printStackTrace();
/* 1410 */       return "no";
/*      */     } 
/*      */     
/* 1413 */     return "ok";
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningProductPacking(String lotSer, String tool, String qty, String userId) {
/* 1418 */     ScanLog scanLog = new ScanLog();
/* 1419 */     scanLog.setDate(new Date());
/* 1420 */     scanLog.setDescribe(lotSer);
/* 1421 */     scanLog.setType(Integer.valueOf(5));
/* 1422 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1423 */     if (user != null) {
/* 1424 */       scanLog.setUserId(user);
/*      */     }
/* 1426 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */ 
/*      */     
/*      */     try {
/* 1430 */       String lot = lotSer.substring(23, lotSer.length());
/* 1431 */       WmsPart part = this.wmsPartManager.getWmsPart(lot);
/* 1432 */       if (part != null)
/*      */       
/* 1434 */       { ProductBelowLine belowLine = new ProductBelowLine();
/* 1435 */         belowLine.setUser(user);
/* 1436 */         belowLine.setPart(part);
/* 1437 */         belowLine.setQty(new BigDecimal(1));
/* 1438 */         belowLine.setStatus(ProductBelowLineStatus.WAIT);
/* 1439 */         belowLine.setTest3(lotSer);
/*      */         
/* 1441 */         if (tool == null) {
/* 1442 */           StorageLocation location = (StorageLocation)this.boxDAO.getObjectList(
/* 1443 */               "from StorageLocation sl where sl.code = 'CCP-00A' ").get(0);
/* 1444 */           belowLine.setLocation(location);
/* 1445 */           if (location != null) {
/*      */             
/* 1447 */             this.inventoryManager.inventoryProduceByWoLot(location.getCode(), null, 
/* 1448 */                 InventoryType.FINISHPRODUCTINSTORAGEIN, Boolean.valueOf(false), part);
/*      */           } else {
/* 1450 */             return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.tool.or.location.null");
/*      */           } 
/*      */         } else {
/*      */           
/* 1454 */           StorageLocation produceLineLocation = this.storageLocationManager.getProduceLineLocation();
/*      */           
/* 1456 */           belowLine.setLocation(produceLineLocation);
/*      */ 
/*      */           
/* 1459 */           WmsTool wmsTool = this.wmsToolManager.getWmsTool(tool);
/* 1460 */           wmsTool.setStatus(YesNo.YES);
/* 1461 */           if (wmsTool.getQty() == null) {
/* 1462 */             wmsTool.setQty(new BigDecimal(0));
/*      */           }
/* 1464 */           wmsTool.setQty(wmsTool.getQty().add(new BigDecimal(1)));
/* 1465 */           wmsTool.setIn_date(new Date());
/* 1466 */           wmsTool.setPart(part);
/* 1467 */           wmsTool.setLocation(produceLineLocation);
/*      */           
/* 1469 */           belowLine.setTool(wmsTool);
/*      */           
/* 1471 */           if (produceLineLocation != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1476 */             this.inventoryManager.inventoryProduceByWoLot(produceLineLocation.getCode(), wmsTool, 
/* 1477 */                 InventoryType.PRODUCELINE, Boolean.valueOf(false), null);
/*      */           } else {
/* 1479 */             return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.tool.or.location.null");
/*      */           } 
/*      */         } 
/*      */         
/* 1483 */         updatePoHighLine(part, lotSer);
/*      */         
/* 1485 */         this.boxDAO.saveObject(belowLine); }
/*      */       else
/* 1487 */       { return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.part.null"); } 
/* 1488 */     } catch (Exception e) {
/* 1489 */       e.printStackTrace();
/* 1490 */       return "no";
/*      */     } 
/*      */     
/* 1493 */     return "ok";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String updatePoHighLine(WmsPart part, String shcode) {
/* 1502 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 1503 */     Date date = new Date();
/*      */     
/*      */     try {
/* 1506 */       String sql = "from PoHighLineOne one where one.test3='" + shcode + "' ";
/* 1507 */       List<PoHighLineOne> highLineOnes = this.boxDAO.getObjectList(sql);
/*      */       
/* 1509 */       if (highLineOnes.size() > 0)
/* 1510 */       { PoHighLineOne lineOne = highLineOnes.get(0);
/* 1511 */         WmsPart childPart = lineOne.getChild_part();
/*      */         
/* 1513 */         String sqlpoLine = "from StorageLocation sl where sl.basic_storeroom_id.type = 12 ";
/* 1514 */         List<StorageLocation> poLineList = this.boxDAO.getObjectList(sqlpoLine);
/*      */         
/* 1516 */         if (poLineList.size() > 0) {
/* 1517 */           StorageLocation poLocation = poLineList.get(0);
/*      */ 
/*      */           
/* 1520 */           StorageLocation linelocation = (StorageLocation)this.boxDAO.getObjectList(
/* 1521 */               "from StorageLocation sl where sl.basic_storeroom_id.type = 5").get(0);
/*      */ 
/*      */           
/* 1524 */           ProduceBuckleMaterial material = new ProduceBuckleMaterial();
/* 1525 */           material.setAssembly(part);
/* 1526 */           material.setPart(childPart);
/* 1527 */           material.setLocation(linelocation);
/* 1528 */           material.setDate(new Date());
/* 1529 */           material.setIs_sync(YesNo.NO);
/* 1530 */           material.setQty(new BigDecimal(1));
/*      */           
/* 1532 */           this.boxDAO.saveObject(material);
/*      */ 
/*      */           
/* 1535 */           lineOne.setAlready_qty(new BigDecimal(1));
/* 1536 */           lineOne.setIs_finish(YesNo.YES);
/* 1537 */           this.boxDAO.updateObject(lineOne);
/*      */ 
/*      */           
/* 1540 */           this.inventoryManager.inventoryAdjustmentByWoLotProduce(poLocation.getCode(), childPart, new BigDecimal(1), 
/* 1541 */               InventoryType.LINELIBRARYOUT, Boolean.valueOf(true));
/*      */ 
/*      */           
/* 1544 */           String sql2 = "from PoHighLineTwo two where two.date='" + format.format(date) + "' and two.serial_number='" + 
/* 1545 */             lineOne.getSerial_number() + "' ";
/* 1546 */           List<PoHighLineTwo> highLineTwos = this.boxDAO.getObjectList(sql2);
/*      */           
/* 1548 */           if (highLineTwos.size() > 0) {
/* 1549 */             PoHighLineTwo lineTwo = highLineTwos.get(0);
/*      */             
/* 1551 */             ProduceBuckleMaterial material2 = new ProduceBuckleMaterial();
/* 1552 */             material2.setAssembly(part);
/* 1553 */             material2.setPart(lineTwo.getChild_part());
/* 1554 */             material2.setLocation((StorageLocation)this.boxDAO.getObjectList(
/* 1555 */                   "from StorageLocation sl where sl.basic_storeroom_id.type = 5").get(0));
/* 1556 */             material2.setDate(new Date());
/* 1557 */             material2.setIs_sync(YesNo.NO);
/* 1558 */             material2.setQty(new BigDecimal(1));
/*      */             
/* 1560 */             this.boxDAO.saveObject(material2);
/*      */             
/* 1562 */             this.inventoryManager.inventoryAdjustmentByWoLotProduce(poLocation.getCode(), lineTwo.getChild_part(), 
/* 1563 */                 new BigDecimal(1), InventoryType.LINELIBRARYOUT, Boolean.valueOf(true));
/*      */ 
/*      */             
/* 1566 */             lineTwo.setAlready_qty(new BigDecimal(1));
/* 1567 */             lineTwo.setIs_finish(YesNo.YES);
/* 1568 */             this.boxDAO.updateObject(lineTwo);
/*      */ 
/*      */             
/* 1571 */             String sqlBom = "from Bom bom where bom.father_part.id='" + part + "' and bom.child_part.type = 3 ";
/* 1572 */             List<Bom> listBom = this.boxDAO.getObjectList(sqlBom);
/* 1573 */             for (Bom bom : listBom) {
/* 1574 */               WmsPart child_part = bom.getChild_part();
/*      */               
/* 1576 */               insertProduceBuckleMaterial(part, child_part, linelocation);
/*      */             } 
/*      */           } else {
/* 1579 */             return String.valueOf(shcode) + ":" + Properties.getPropertiesValye("scan.sync.error.poHighLineTwo.null");
/*      */           } 
/*      */         }  }
/* 1582 */       else { return String.valueOf(shcode) + ":" + Properties.getPropertiesValye("scan.sync.error.poHighLineOne.null"); } 
/* 1583 */     } catch (Exception e) {
/* 1584 */       e.printStackTrace();
/*      */     } 
/* 1586 */     return "ok";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void insertProduceBuckleMaterial(WmsPart part, WmsPart child_part, StorageLocation location) {
/*      */     try {
/* 1597 */       ProduceBuckleMaterial material = new ProduceBuckleMaterial();
/* 1598 */       material.setAssembly(part);
/* 1599 */       material.setPart(child_part);
/* 1600 */       material.setLocation(location);
/* 1601 */       material.setDate(new Date());
/* 1602 */       material.setIs_sync(YesNo.NO);
/* 1603 */       material.setQty(new BigDecimal(1));
/*      */       
/* 1605 */       this.boxDAO.saveObject(material);
/*      */       
/* 1607 */       this.inventoryManager.inventoryAdjustmentByWoLotProduce(location.getCode(), child_part, new BigDecimal(1), 
/* 1608 */           InventoryType.LINELIBRARYOUT, Boolean.valueOf(true));
/* 1609 */     } catch (Exception e) {
/* 1610 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Box insertBox(WmsPart part, WmsTool wmsTool) {
/* 1615 */     WmsLot wl = (new InventoryTool((DAO)this.boxDAO)).insertWOBox(part.getId(), "yyyyMMdd");
/* 1616 */     Box box = new Box();
/* 1617 */     box.setLot(wl);
/* 1618 */     box.setPart(part);
/* 1619 */     box.setNumber(new BigDecimal(1));
/* 1620 */     box.setTool(wmsTool);
/* 1621 */     box.setDate(new Date());
/* 1622 */     box.setPrint_number(Integer.valueOf(0));
/* 1623 */     box.setIsPrint(YesNo.NO);
/* 1624 */     box.setStatus(BoxStatus.HASBEENINTO);
/* 1625 */     box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 1626 */     box.setStatus_freeze(YesNo.NO);
/* 1627 */     box.setEnabled(EnabledDisabled.ENABLED);
/*      */     
/* 1629 */     this.boxDAO.saveObject(box);
/* 1630 */     return box;
/*      */   }
/*      */   
/*      */   public String scanningLotInformation(String lot) {
/* 1634 */     String str = "";
/* 1635 */     List<Box> list = this.boxDAO.getObjectList("from Box box where box.lot.id = '" + lot + "' ");
/* 1636 */     if (list.size() == 0) {
/* 1637 */       return "Null";
/*      */     }
/* 1639 */     if (list.size() > 0) {
/* 1640 */       Box box = list.get(0);
/* 1641 */       str = String.valueOf(str) + box.getLot().getId() + "," + box.getPart().getId() + "," + box.getLocation().getCode() + "," + 
/* 1642 */         box.getNumber() + ",";
/*      */     } 
/*      */     
/* 1645 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   public String boxAbolition(String array) {
/* 1650 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 1651 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1652 */       Box box = this.boxDAO.getBoxBylotSer2(lot);
/* 1653 */       box.setEnabled(EnabledDisabled.ENABLED);
/* 1654 */       box.setStatus(BoxStatus.HASTHE);
/* 1655 */       box.setOut_date(new Date());
/* 1656 */       this.boxDAO.updateBox(box);
/*      */       b++; }
/*      */     
/* 1659 */     return "true";
/*      */   } public String systemPurchaseOrderHighLineOne(String[] lots, String part, String userId, String partname) {
/*      */     try {
/*      */       byte b;
/*      */       int i;
/*      */       String[] arrayOfString;
/* 1665 */       for (i = (arrayOfString = lots).length, b = 0; b < i; ) { String lot = arrayOfString[b];
/* 1666 */         ScanLog scanLog = new ScanLog();
/* 1667 */         scanLog.setDate(new Date());
/* 1668 */         scanLog.setDescribe(lot);
/* 1669 */         scanLog.setType(Integer.valueOf(12));
/* 1670 */         User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1671 */         if (user != null) {
/* 1672 */           scanLog.setUserId(user);
/*      */         }
/* 1674 */         this.scanLogDAO.insertScanLog(scanLog);
/*      */         
/* 1676 */         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*      */         
/*      */         try {
/* 1679 */           List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.lot.id='" + lot + 
/* 1680 */               "' and box.location.basic_storeroom_id.type=5 ");
/*      */           
/* 1682 */           if (boxs.size() > 0) {
/* 1683 */             Box box = boxs.get(0);
/* 1684 */             WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/*      */             
/* 1686 */             PoHighLineOne lineOne = new PoHighLineOne();
/* 1687 */             Date date = new Date();
/* 1688 */             lineOne.setAssembly_part(wmsPart);
/* 1689 */             lineOne.setUserId(user);
/* 1690 */             lineOne.setBox(box);
/* 1691 */             lineOne.setDate(date);
/* 1692 */             lineOne.setChild_part(box.getPart());
/* 1693 */             lineOne.setStatus(PoHighLineStatus.HIGHLINE);
/* 1694 */             lineOne.setIs_finish(YesNo.NO);
/* 1695 */             lineOne.setTest3(partname);
/*      */             
/* 1697 */             String sql = "select pl.serial_number from PoHighLineOne pl where pl.date='" + format.format(date) + 
/* 1698 */               "' order by pl.serial_number desc ";
/* 1699 */             List<Object> obj = this.boxDAO.getObjectList(sql);
/* 1700 */             Integer serialNumber = Integer.valueOf(1);
/* 1701 */             if (obj.size() > 0 && obj.get(0) != null) {
/* 1702 */               serialNumber = (Integer)obj.get(0);
/* 1703 */               serialNumber = Integer.valueOf(serialNumber.intValue() + 1);
/*      */             } 
/* 1705 */             lineOne.setSerial_number(serialNumber);
/*      */             
/* 1707 */             this.boxDAO.saveObject(lineOne);
/*      */ 
/*      */             
/* 1710 */             String sqlLocation = "from StorageLocation sl where sl.basic_storeroom_id.type=12 ";
/* 1711 */             List<StorageLocation> locations = this.boxDAO.getObjectList(sqlLocation);
/*      */ 
/*      */             
/* 1714 */             String sqlLocationLine = "from StorageLocation sl where sl.basic_storeroom_id.type=5 ";
/* 1715 */             List<StorageLocation> locationList = this.boxDAO.getObjectList(sqlLocationLine);
/*      */ 
/*      */             
/* 1718 */             this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locationList.get(0)).getCode(), box, InventoryType.COMMONOUT, 
/* 1719 */                 Boolean.valueOf(true));
/*      */             
/* 1721 */             this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locations.get(0)).getCode(), box, InventoryType.COMMONIN, 
/* 1722 */                 Boolean.valueOf(false));
/*      */ 
/*      */             
/* 1725 */             box.setLocation(locations.get(0));
/*      */             
/* 1727 */             this.boxDAO.updateBox(box);
/*      */           } else {
/* 1729 */             return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */           } 
/* 1731 */         } catch (Exception e) {
/* 1732 */           e.printStackTrace();
/* 1733 */           return "no";
/*      */         } 
/*      */         b++; }
/*      */     
/* 1737 */     } catch (Exception e) {
/* 1738 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1741 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String systemPurchaseOrderHighLineTwo(String[] lots, String userId) {
/* 1746 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); byte b; int i; String[] arrayOfString;
/* 1747 */     for (i = (arrayOfString = lots).length, b = 0; b < i; ) { String lot = arrayOfString[b];
/*      */       try {
/* 1749 */         scanningPurchaseOrderHighLineTwo(lot, userId);
/* 1750 */       } catch (Exception e) {
/* 1751 */         e.printStackTrace();
/*      */       } 
/*      */       b++; }
/*      */     
/* 1755 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String scanningPurchaseOrderHighLineOne(String lot, String part, String userId) {
/* 1760 */     ScanLog scanLog = new ScanLog();
/* 1761 */     scanLog.setDate(new Date());
/* 1762 */     scanLog.setDescribe(lot);
/* 1763 */     scanLog.setType(Integer.valueOf(12));
/* 1764 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1765 */     if (user != null) {
/* 1766 */       scanLog.setUserId(user);
/*      */     }
/* 1768 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/* 1770 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*      */     
/*      */     try {
/* 1773 */       List<Box> boxs = this.boxDAO.getObjectList("from Box box where box.lot.id='" + lot + 
/* 1774 */           "' and box.location.basic_storeroom_id.type=5 ");
/*      */       
/* 1776 */       if (boxs.size() > 0) {
/* 1777 */         Box box = boxs.get(0);
/*      */         
/* 1779 */         String newPart = part.substring(23, part.length());
/* 1780 */         WmsPart wmsPart = this.wmsPartManager.getWmsPart(newPart);
/*      */         
/* 1782 */         if (wmsPart == null) {
/* 1783 */           return String.valueOf(part) + ":" + Properties.getPropertiesValye("scan.sync.part.null");
/*      */         }
/*      */         
/* 1786 */         PoHighLineOne lineOne = new PoHighLineOne();
/* 1787 */         Date date = new Date();
/* 1788 */         lineOne.setAssembly_part(wmsPart);
/* 1789 */         lineOne.setUserId(user);
/* 1790 */         lineOne.setBox(box);
/* 1791 */         lineOne.setDate(date);
/* 1792 */         lineOne.setChild_part(box.getPart());
/* 1793 */         lineOne.setStatus(PoHighLineStatus.HIGHLINE);
/* 1794 */         lineOne.setIs_finish(YesNo.NO);
/* 1795 */         lineOne.setTest3(part);
/*      */         
/* 1797 */         String sql = "select pl.serial_number from PoHighLineOne pl where pl.date='" + format.format(date) + 
/* 1798 */           "' order by pl.serial_number desc ";
/* 1799 */         List<Object> obj = this.boxDAO.getObjectList(sql);
/* 1800 */         Integer serialNumber = Integer.valueOf(1);
/* 1801 */         if (obj.size() > 0 && obj.get(0) != null) {
/* 1802 */           serialNumber = (Integer)obj.get(0);
/* 1803 */           serialNumber = Integer.valueOf(serialNumber.intValue() + 1);
/*      */         } 
/* 1805 */         lineOne.setSerial_number(serialNumber);
/*      */         
/* 1807 */         this.boxDAO.saveObject(lineOne);
/*      */ 
/*      */         
/* 1810 */         String sqlLocation = "from StorageLocation sl where sl.basic_storeroom_id.type=12 ";
/* 1811 */         List<StorageLocation> locations = this.boxDAO.getObjectList(sqlLocation);
/*      */ 
/*      */         
/* 1814 */         String sqlLocationLine = "from StorageLocation sl where sl.basic_storeroom_id.type=5 ";
/* 1815 */         List<StorageLocation> locationList = this.boxDAO.getObjectList(sqlLocationLine);
/*      */ 
/*      */         
/* 1818 */         this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locationList.get(0)).getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/*      */         
/* 1820 */         this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locations.get(0)).getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */ 
/*      */         
/* 1823 */         box.setLocation(locations.get(0));
/*      */         
/* 1825 */         this.boxDAO.updateBox(box);
/*      */       } else {
/* 1827 */         return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */       } 
/* 1829 */     } catch (Exception e) {
/* 1830 */       e.printStackTrace();
/* 1831 */       return "no";
/*      */     } 
/*      */     
/* 1834 */     return "ok";
/*      */   }
/*      */   
/*      */   public String scanningPurchaseOrderHighLineTwo(String lot, String userId) {
/* 1838 */     ScanLog scanLog = new ScanLog();
/* 1839 */     scanLog.setDate(new Date());
/* 1840 */     scanLog.setDescribe(lot);
/* 1841 */     scanLog.setType(Integer.valueOf(13));
/* 1842 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 1843 */     if (user != null) {
/* 1844 */       scanLog.setUserId(user);
/*      */     }
/* 1846 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/* 1848 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*      */     
/*      */     try {
/* 1851 */       String sql1 = "from Box box where box.lot.id='" + lot + "' and box.location.basic_storeroom_id.type=5 ";
/* 1852 */       List<Box> boxs = this.boxDAO.getObjectList(sql1);
/* 1853 */       if (boxs.size() > 0) {
/* 1854 */         Box box = boxs.get(0);
/* 1855 */         Date date = new Date();
/*      */         
/* 1857 */         PoHighLineTwo lineTwo = new PoHighLineTwo();
/* 1858 */         lineTwo.setBox(box);
/* 1859 */         lineTwo.setChild_part(box.getPart());
/* 1860 */         lineTwo.setDate(new Date());
/* 1861 */         lineTwo.setStatus(PoHighLineStatus.HIGHLINE);
/* 1862 */         lineTwo.setIs_finish(YesNo.NO);
/* 1863 */         lineTwo.setUserId(user);
/*      */         
/* 1865 */         String sql = "select pl.serial_number from PoHighLineTwo pl where pl.date='" + format.format(date) + 
/* 1866 */           "' order by pl.serial_number desc ";
/* 1867 */         List<Object> obj = this.boxDAO.getObjectList(sql);
/* 1868 */         Integer serialNumber = Integer.valueOf(1);
/* 1869 */         if (obj.size() > 0 && obj.get(0) != null) {
/* 1870 */           serialNumber = (Integer)obj.get(0);
/* 1871 */           serialNumber = Integer.valueOf(serialNumber.intValue() + 1);
/*      */         } 
/*      */         
/* 1874 */         lineTwo.setSerial_number(serialNumber);
/* 1875 */         this.scanLogDAO.saveObject(lineTwo);
/*      */ 
/*      */         
/* 1878 */         String sqlLocation = "from StorageLocation sl where sl.basic_storeroom_id.type=12 ";
/* 1879 */         List<StorageLocation> locations = this.boxDAO.getObjectList(sqlLocation);
/*      */ 
/*      */         
/* 1882 */         String sqlLocationLine = "from StorageLocation sl where sl.basic_storeroom_id.type=5 ";
/* 1883 */         List<StorageLocation> locationList = this.boxDAO.getObjectList(sqlLocationLine);
/*      */         
/* 1885 */         this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locationList.get(0)).getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 1886 */         this.inventoryManager.inventoryAdjustmentByWoLot(((StorageLocation)locations.get(0)).getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*      */ 
/*      */         
/* 1889 */         box.setLocation(locations.get(0));
/* 1890 */         this.boxDAO.updateBox(box);
/*      */       } else {
/* 1892 */         return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */       } 
/* 1894 */     } catch (Exception e) {
/* 1895 */       e.printStackTrace();
/* 1896 */       return "no";
/*      */     } 
/*      */     
/* 1899 */     return "ok";
/*      */   }
/*      */ 
/*      */   
/*      */   public String checkLot(String lotStr) {
/* 1904 */     String[] lotArray = lotStr.split(",");
/* 1905 */     String str = ""; byte b; int i; String[] arrayOfString1;
/* 1906 */     for (i = (arrayOfString1 = lotArray).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1907 */       Box box = this.boxDAO.getBoxBylotSer2(lot);
/* 1908 */       if (box.getStatus().equals(BoxStatus.HASBEENINTO) && box.getEnabled().equals(EnabledDisabled.ENABLED) && 
/* 1909 */         box.getStatus_freeze().equals(YesNo.NO)) {
/*      */         
/* 1911 */         if (box.getStatus().equals(BoxStatus.HASBEENINTO)) {
/*      */           
/* 1913 */           YesNo status = box.getLocation().getF_in_f_out_status();
/*      */           
/* 1915 */           boolean sign = false;
/* 1916 */           if (status == null) {
/* 1917 */             sign = true;
/* 1918 */           } else if (status.equals(YesNo.YES)) {
/* 1919 */             sign = true;
/*      */           } 
/*      */           
/* 1922 */           if (sign) {
/*      */             
/* 1924 */             Integer num = box.getPart().getRecommend_date();
/* 1925 */             if (num == null) {
/* 1926 */               num = Integer.valueOf(3);
/*      */             }
/*      */             
/* 1929 */             String sqlDate = "select box.date from Box box where box.date is not null and  box.status=3 and box.part='" + 
/* 1930 */               box.getPart().getId() + "' and box.status_freeze = 1 " + 
/* 1931 */               " and box.enabled=0 and box.location.basic_storeroom_id.type<>6 order by box.date ";
/* 1932 */             List<Object> listDate = this.boxDAO.getObjectList(sqlDate);
/*      */             
/* 1934 */             Date curentDate = null;
/* 1935 */             if (listDate.size() > 0 && listDate.get(0) != null) {
/* 1936 */               curentDate = (Date)listDate.get(0);
/*      */             } else {
/* 1938 */               curentDate = new Date();
/*      */             } 
/* 1940 */             Date oldDate = new Date(curentDate.getTime());
/* 1941 */             oldDate.setDate(oldDate.getDate() + num.intValue());
/* 1942 */             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 1943 */             String sql = "from Box box where box.date is not null and  box.status=3 and box.part.id='" + 
/* 1944 */               box.getPart().getId() + "' and box.status_freeze = 1 " + " and box.enabled=0 and box.date >= '" + 
/* 1945 */               format.format(curentDate) + "' and box.location.basic_storeroom_id.type<>6 and box.date <= '" + 
/* 1946 */               format.format(oldDate) + "' and box.lot.id not in ("; byte b1; int j; String[] arrayOfString;
/* 1947 */             for (j = (arrayOfString = lotArray).length, b1 = 0; b1 < j; ) { String s = arrayOfString[b1];
/* 1948 */               sql = String.valueOf(sql) + "'";
/* 1949 */               sql = String.valueOf(sql) + s;
/* 1950 */               sql = String.valueOf(sql) + "',"; b1++; }
/*      */             
/* 1952 */             sql = sql.substring(0, sql.length() - 1);
/* 1953 */             sql = String.valueOf(sql) + ")";
/*      */             
/* 1955 */             String sql2 = "from Box box where box.date is not null and  box.status=3 and box.part.id='" + 
/* 1956 */               box.getPart().getId() + "' and box.status_freeze = 1 " + " and box.enabled=0 and box.date >= '" + 
/* 1957 */               format.format(curentDate) + "' and box.date <= '" + format.format(oldDate) + 
/* 1958 */               "' and box.location.basic_storeroom_id.type<>6 and box.lot.id='" + box.getLot().getId() + "' ";
/*      */             
/* 1960 */             List<Box> list = this.boxDAO.getObjectList(sql);
/* 1961 */             if (list.size() > 0) {
/*      */               
/* 1963 */               List<Box> list2 = this.boxDAO.getObjectList(sql2);
/* 1964 */               if (list2.size() <= 0) {
/*      */ 
/*      */                 
/* 1967 */                 Box recommendBox = list.get(0);
/* 1968 */                 str = String.valueOf(str) + recommendBox.getLot().getId() + ";" + recommendBox.getLocation().getCode();
/*      */               } 
/*      */             } 
/*      */           } else {
/* 1972 */             return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */           } 
/*      */         } else {
/* 1975 */           return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */         } 
/*      */       } else {
/* 1978 */         return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */       }  b++; }
/*      */     
/* 1981 */     return str;
/*      */   }
/*      */   
/*      */   public String scanningPurchaseOrderOutboundCheck(String lot) {
/* 1985 */     String str = "ok";
/* 1986 */     Box box = this.boxDAO.getBoxBylotSer2(lot);
/* 1987 */     if (box == null) {
/* 1988 */       return "Null";
/*      */     }
/* 1990 */     if (box.getStatus().equals(BoxStatus.HASBEENINTO) && box.getEnabled().equals(EnabledDisabled.ENABLED) && 
/* 1991 */       box.getStatus_freeze().equals(YesNo.NO))
/*      */     
/* 1993 */     { if (box.getStatus().equals(BoxStatus.HASBEENINTO)) {
/*      */         
/* 1995 */         YesNo status = box.getLocation().getF_in_f_out_status();
/*      */         
/* 1997 */         boolean sign = false;
/* 1998 */         if (status == null) {
/* 1999 */           sign = true;
/* 2000 */         } else if (status.equals(YesNo.YES)) {
/* 2001 */           sign = true;
/*      */         } 
/*      */         
/* 2004 */         if (sign) {
/*      */           
/* 2006 */           Integer num = box.getPart().getRecommend_date();
/* 2007 */           if (num == null) {
/* 2008 */             num = Integer.valueOf(3);
/*      */           }
/*      */           
/* 2011 */           String sqlDate = "select box.date from Box box where box.date is not null and  box.status=3 and box.part='" + 
/* 2012 */             box.getPart().getId() + "' and box.status_freeze = 1 " + 
/* 2013 */             " and box.enabled=0 and box.location.basic_storeroom_id.type<>6 order by box.date ";
/* 2014 */           List<Object> listDate = this.boxDAO.getObjectList(sqlDate);
/*      */           
/* 2016 */           Date curentDate = null;
/* 2017 */           if (listDate.size() > 0 && listDate.get(0) != null) {
/* 2018 */             curentDate = (Date)listDate.get(0);
/*      */           } else {
/* 2020 */             curentDate = new Date();
/*      */           } 
/* 2022 */           Date oldDate = new Date(curentDate.getTime());
/* 2023 */           oldDate.setDate(oldDate.getDate() + num.intValue());
/* 2024 */           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 2025 */           String sql = "from Box box where box.date is not null and  box.status=3 and box.part.id='" + 
/* 2026 */             box.getPart().getId() + "' and box.status_freeze = 1 " + " and box.enabled=0 and box.date >= '" + 
/* 2027 */             format.format(curentDate) + "' and box.location.basic_storeroom_id.type<>6 and box.date <= '" + 
/* 2028 */             format.format(oldDate) + "' ";
/*      */           
/* 2030 */           String sql2 = "from Box box where box.date is not null and  box.status=3 and box.part.id='" + 
/* 2031 */             box.getPart().getId() + "' and box.status_freeze = 1 " + " and box.enabled=0 and box.date >= '" + 
/* 2032 */             format.format(curentDate) + "' and box.location.basic_storeroom_id.type<>6 and box.date <= '" + 
/* 2033 */             format.format(oldDate) + "' and box.lot.id='" + box.getLot().getId() + "' ";
/*      */           
/* 2035 */           List<Box> list = this.boxDAO.getObjectList(sql);
/* 2036 */           if (list.size() > 0) {
/*      */             
/* 2038 */             List<Box> list2 = this.boxDAO.getObjectList(sql2);
/* 2039 */             if (list2.size() > 0) {
/* 2040 */               str = "ok";
/*      */             } else {
/* 2042 */               Box recommendBox = list.get(0);
/* 2043 */               str = String.valueOf(recommendBox.getLot().getId()) + ";" + recommendBox.getLocation().getCode();
/*      */             } 
/*      */           } 
/*      */         } else {
/* 2047 */           return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */         } 
/*      */       } else {
/* 2050 */         return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */       }  }
/* 2052 */     else { return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null"); }
/*      */     
/* 2054 */     return str;
/*      */   }
/*      */   
/*      */   public String systemProductPacking(String lot, String tool, String qty, String userId) {
/* 2058 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Box> getBoxByLocation(String location, String part) {
/* 2063 */     return this.boxDAO.getBoxByLocation(location, part);
/*      */   }
/*      */   
/*      */   public String scanninghighLineBoxOneCheck(String lot) {
/* 2067 */     String str = "";
/* 2068 */     Box box = this.boxDAO.getBoxBylotSer2(lot);
/*      */ 
/*      */     
/* 2071 */     if (box.getLocation().getBasic_storeroom_id().getType().equals(StoreRoomType.RAWMATERIALSLINE) && 
/* 2072 */       box.getEnabled().equals(EnabledDisabled.ENABLED) && box.getStatus_freeze().equals(YesNo.NO))
/*      */     
/*      */     { 
/* 2075 */       StorageLocation polinelocation = this.storageLocationManager.getProduceLineLocationLine();
/* 2076 */       if (polinelocation != null) {
/* 2077 */         YesNo status = polinelocation.getF_in_f_out_status();
/* 2078 */         if (status != null && status.equals(YesNo.YES))
/*      */         
/* 2080 */         { String sqlDate = "from Box box where box.date is not null and  box.part.id = '" + box.getPart().getId() + 
/* 2081 */             "' and box.status_freeze = 1 " + 
/* 2082 */             " and box.enabled = 0 and box.location.basic_storeroom_id.type = 5 order by box.date ";
/*      */           
/* 2084 */           List<Box> listDate = this.boxDAO.getObjectList(sqlDate);
/* 2085 */           if (listDate.size() > 0) {
/* 2086 */             Box boxDate = listDate.get(0);
/*      */ 
/*      */             
/* 2089 */             if (boxDate.getDate() != null && box.getDate() != null) {
/* 2090 */               if (boxDate.getDate().equals(box.getDate())) {
/* 2091 */                 str = "ok";
/*      */               } else {
/*      */                 
/* 2094 */                 String sqlnewDate = "from Box box where box.date is not null and box.part='" + 
/* 2095 */                   box.getPart().getId() + 
/* 2096 */                   "' and box.status_freeze = 1 and box.location.basic_storeroom_id.type = 5 " + 
/* 2097 */                   " and box.enabled = 0 and box.date='" + boxDate.getDate() + "' ";
/*      */                 
/* 2099 */                 List<Box> listnewDate = this.boxDAO.getObjectList(sqlnewDate);
/* 2100 */                 if (listnewDate.size() > 0) {
/* 2101 */                   box = listnewDate.get(0);
/* 2102 */                   str = String.valueOf(str) + box.getLot().getId() + ";" + box.getLocation().getCode();
/*      */                 } else {
/* 2104 */                   str = String.valueOf(str) + box.getLot().getId() + ";" + box.getLocation().getCode();
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } else {
/* 2109 */             str = "ok";
/*      */           }  }
/* 2111 */         else { str = "ok"; }
/*      */       
/*      */       }  }
/* 2114 */     else { return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null"); }
/*      */ 
/*      */     
/* 2117 */     return str;
/*      */   }
/*      */   
/*      */   public List<Box> gethighLineBoxOneCheck(String lot) {
/* 2121 */     List<Box> listBox = new ArrayList<Box>();
/* 2122 */     Box box = this.boxDAO.getBoxBylotSer2(lot);
/*      */ 
/*      */     
/* 2125 */     if (box.getLocation().getBasic_storeroom_id().getType().equals(StoreRoomType.RAWMATERIALSLINE) && 
/* 2126 */       box.getEnabled().equals(EnabledDisabled.ENABLED) && box.getStatus_freeze().equals(YesNo.NO))
/*      */     
/*      */     { 
/* 2129 */       StorageLocation polinelocation = this.storageLocationManager.getProduceLineLocationLine();
/* 2130 */       if (polinelocation != null) {
/* 2131 */         YesNo status = polinelocation.getF_in_f_out_status();
/* 2132 */         if (status != null && status.equals(YesNo.YES))
/*      */         
/* 2134 */         { String sqlDate = "from Box box where box.date is not null and  box.part.id = '" + box.getPart().getId() + 
/* 2135 */             "' and box.status_freeze = 1 " + 
/* 2136 */             " and box.enabled = 0 and box.location.basic_storeroom_id.type = 5 order by box.date ";
/*      */           
/* 2138 */           List<Box> listDate = this.boxDAO.getObjectList(sqlDate);
/* 2139 */           if (listDate.size() > 0) {
/* 2140 */             Box boxDate = listDate.get(0);
/*      */ 
/*      */             
/* 2143 */             if (boxDate.getDate() != null && box.getDate() != null) {
/* 2144 */               if (boxDate.getDate().equals(box.getDate())) {
/* 2145 */                 listBox.add(box);
/*      */               } else {
/*      */                 
/* 2148 */                 String sqlnewDate = "from Box box where box.date is not null and box.part='" + 
/* 2149 */                   box.getPart().getId() + 
/* 2150 */                   "' and box.status_freeze = 1 and box.location.basic_storeroom_id.type = 5 " + 
/* 2151 */                   " and box.enabled = 0 and box.date='" + boxDate.getDate() + "' ";
/*      */                 
/* 2153 */                 List<Box> listnewDate = this.boxDAO.getObjectList(sqlnewDate);
/* 2154 */                 listBox.addAll(listnewDate);
/*      */               } 
/*      */             }
/*      */           } else {
/* 2158 */             listBox.add(box);
/*      */           }  }
/* 2160 */         else { listBox.add(box); }
/*      */       
/*      */       }  }
/* 2163 */     else { throw new ActionException("该条码已冻结或者已废除，请扫描其他条码进行上线！"); }
/*      */ 
/*      */     
/* 2166 */     return listBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Map> getProduceLineLocationAmunt() {
/* 2171 */     String sql = "select box.part.id, sum(box.number) from Box box where box.location.basic_storeroom_id.type = 12 and box.enabled=0 and box.status_freeze=1 group by box.part.id ";
/* 2172 */     List<Object[]> list = this.boxDAO.getObjectList(sql);
/*      */     
/* 2174 */     List<Map> listMap = new ArrayList<Map>();
/* 2175 */     for (Object[] objects : list) {
/* 2176 */       String part = (String)objects[0];
/* 2177 */       BigDecimal sumqty = (BigDecimal)objects[1];
/*      */       
/* 2179 */       WmsPart wmsPart = (WmsPart)this.boxDAO.getObject(WmsPart.class, part);
/*      */       
/* 2181 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 2182 */       map.put("part", wmsPart);
/* 2183 */       map.put("sumqty", sumqty);
/* 2184 */       listMap.add(map);
/*      */     } 
/*      */     
/* 2187 */     return listMap;
/*      */   }
/*      */   
/*      */   public boolean updatePurchaseOrderBoxFreeze(String array, Boolean type) {
/* 2191 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 2192 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2193 */       Box box = this.boxDAO.getBoxBylotSer2(id);
/* 2194 */       if (type.booleanValue()) {
/* 2195 */         box.setStatus_freeze(YesNo.YES);
/*      */       } else {
/* 2197 */         box.setStatus_freeze(YesNo.NO);
/*      */       } 
/*      */       
/* 2200 */       this.boxDAO.updateBox(box);
/*      */       b++; }
/*      */     
/* 2203 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String hnCodeListBox(String hnCode) {
/* 2209 */     List<Box> list = this.boxDAO.getObjectList("from Box box where box.lot.id='" + hnCode + "'");
/* 2210 */     if (list.size() == 0) {
/* 2211 */       return "NUll";
/*      */     }
/* 2213 */     Box box = list.get(0);
/* 2214 */     String part = box.getPart().getId();
/* 2215 */     String location = box.getLocation().getCode();
/* 2216 */     String describe = box.getPart().getDescribe1();
/* 2217 */     String number = box.getNumber().toString();
/* 2218 */     String supp = box.getPo_supp();
/* 2219 */     String str = String.valueOf(part) + "," + location + "," + describe + "," + number + "," + supp;
/* 2220 */     return str;
/*      */   }
/*      */   
/*      */   public String updateProduceInStorageMaterial(String array, User user) {
/* 2224 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 2225 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2226 */       Box box = this.boxDAO.getBox(Integer.valueOf(Integer.parseInt(id)));
/*      */       
/* 2228 */       ScanLog scanLog = new ScanLog();
/* 2229 */       scanLog.setDate(new Date());
/* 2230 */       scanLog.setDescribe(box.getLot().getId());
/* 2231 */       scanLog.setType(Integer.valueOf(42));
/* 2232 */       if (user != null) {
/* 2233 */         scanLog.setUserId(user);
/*      */       }
/* 2235 */       this.scanLogDAO.insertScanLog(scanLog);
/*      */       
/*      */       try {
/* 2238 */         List<StorageLocation> locations = this.boxDAO
/* 2239 */           .getObjectList("from StorageLocation sl where sl.basic_storeroom_id.type = 4 and sl.freeae_status <> 0");
/* 2240 */         if (locations.size() > 0) {
/* 2241 */           StorageLocation location = locations.get(0);
/* 2242 */           this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RETURNINSTORAGE, 
/* 2243 */               Boolean.valueOf(true));
/*      */         } 
/* 2245 */         PurchaseOrderInspectionPendingItem item = null;
/*      */ 
/*      */         
/* 2248 */         if (box.getSingle() == null) {
/* 2249 */           item = box.getPsoItem().getPoipItem();
/* 2250 */           BigDecimal count = box.getReceivedNumber();
/* 2251 */           count = count.multiply(new BigDecimal(-1));
/*      */           
/* 2253 */           BigDecimal number = box.getNumber();
/* 2254 */           number = number.multiply(new BigDecimal(-1));
/*      */           
/* 2256 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 2257 */           inStorage.setDate(new Date());
/* 2258 */           inStorage.setIs_sync(YesNo.NO);
/* 2259 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 2260 */           inStorage.setPoipItem(box.getPsoItem().getPoipItem());
/* 2261 */           inStorage.setLocation(box.getLocation());
/* 2262 */           inStorage.setPart(box.getPart());
/* 2263 */           inStorage.setReceipts_qty(item.getQty());
/*      */           
/* 2265 */           inStorage.setQty(number);
/* 2266 */           inStorage.setSupper(item.getPoip_number().getSupplier());
/* 2267 */           inStorage.setLine(item.getLine());
/* 2268 */           inStorage.setPo_date(item.getPoip_number().getCreateDate());
/* 2269 */           inStorage.setPo_number(item.getPoip_number().getPo_number());
/* 2270 */           inStorage.setPo_qty(item.getQty());
/* 2271 */           inStorage.setLotSer(box.getLot());
/* 2272 */           this.boxDAO.saveObject(inStorage);
/*      */ 
/*      */           
/* 2275 */           item.setReceiptQty(item.getReceiptQty().subtract(box.getNumber()));
/* 2276 */           item.setQtyOpen(item.getQtyOpen().add(box.getNumber()));
/*      */           
/* 2278 */           item.setInventoryNumber(item.getInventoryNumber().subtract(box.getNumber()));
/* 2279 */           this.boxDAO.updateObject(item);
/*      */         } else {
/* 2281 */           BigDecimal count = box.getReceivedNumber();
/* 2282 */           count = count.multiply(new BigDecimal(-1));
/*      */           
/* 2284 */           BigDecimal number = box.getNumber();
/* 2285 */           number = number.multiply(new BigDecimal(-1));
/*      */           
/* 2287 */           PurchaseOrderCondimentSingle single = box.getSingle();
/* 2288 */           item = box.getSingle().getPo_detial_id();
/* 2289 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 2290 */           inStorage.setDate(new Date());
/* 2291 */           inStorage.setIs_sync(YesNo.NO);
/* 2292 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 2293 */           inStorage.setSingle(box.getSingle());
/* 2294 */           inStorage.setLocation(box.getLocation());
/* 2295 */           inStorage.setPart(box.getPart());
/* 2296 */           inStorage.setReceipts_qty(box.getSingle().getDelivery_qty());
/*      */           
/* 2298 */           inStorage.setQty(number);
/* 2299 */           inStorage.setSupper(single.getPo_detial_id().getPoip_number().getSupplier());
/* 2300 */           inStorage.setLine(single.getPo_detial_id().getLine());
/* 2301 */           inStorage.setPo_date(single.getPo_detial_id().getPoip_number().getCreateDate());
/* 2302 */           inStorage.setPo_number(single.getPo_detial_id().getPoip_number().getPo_number());
/* 2303 */           inStorage.setPo_qty(single.getDelivery_qty());
/* 2304 */           inStorage.setLotSer(box.getLot());
/* 2305 */           this.boxDAO.saveObject(inStorage);
/*      */           
/* 2307 */           single.setPutIn_qty(single.getPutIn_qty().subtract(box.getNumber()));
/* 2308 */           this.boxDAO.updateObject(single);
/*      */           
/* 2310 */           Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 2311 */           conditions.put(InventoryTransitQueryCondition.PART_CODE_EQ, box.getPart().getId());
/* 2312 */           List<InventoryTransit> inventoryTransitList = this.inventoryTransitManager.getInventoryTransitList(conditions, 0, -1, 
/* 2313 */               null, false);
/* 2314 */           if (inventoryTransitList.size() > 0) {
/* 2315 */             InventoryTransit inventoryTransit = inventoryTransitList.get(0);
/* 2316 */             inventoryTransit.setQty(inventoryTransit.getQty().add(box.getNumber()));
/* 2317 */             this.inventoryTransitManager.updateInventoryTransit(inventoryTransit);
/*      */           } 
/*      */           
/* 2320 */           conditions.clear();
/* 2321 */           conditions.put(PortalShipOrderQueryCondition.PO_NUMBER_EQ, box.getPo_number());
/* 2322 */           conditions.put(PortalShipOrderQueryCondition.PART_CODE_EQ, box.getPart().getId());
/* 2323 */           List<PortalShipOrderItem> portalShipOrderItemList = this.portalShipOrderItemManager.getPortalShipOrderItemList(
/* 2324 */               conditions, 0, -1, null, false);
/* 2325 */           if (portalShipOrderItemList.size() > 0) {
/* 2326 */             PortalShipOrderItem portalShipOrderItem = portalShipOrderItemList.get(0);
/* 2327 */             portalShipOrderItem.setAlready_season_qty(portalShipOrderItem.getAlready_season_qty().subtract(
/* 2328 */                   box.getNumber()));
/* 2329 */             portalShipOrderItem.setStatus(YesNo.NO);
/* 2330 */             this.portalShipOrderItemManager.updatePortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2336 */         ProduceRejectedMaterial material = new ProduceRejectedMaterial();
/* 2337 */         material.setBox(box);
/* 2338 */         material.setDate(new Date());
/* 2339 */         material.setCreateUser(user);
/* 2340 */         material.setQty(box.getNumber());
/* 2341 */         material.setStatus(YesNo.YES);
/* 2342 */         material.setLocation(box.getLocation());
/* 2343 */         material.setType(Integer.valueOf(2));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2349 */         this.boxDAO.saveObject(material);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2370 */         box.setVetoQCnumber(box.getNumber());
/* 2371 */         box.setReceivedNumber(new BigDecimal(0));
/* 2372 */         box.setInStorageNumber(new BigDecimal(0));
/* 2373 */         box.setEnabled(EnabledDisabled.DISABLED);
/* 2374 */         box.setStatus(BoxStatus.Withdraw);
/* 2375 */         box.setReturnsNumber(box.getReceivedNumber());
/* 2376 */         this.boxDAO.updateBox(box);
/* 2377 */       } catch (Exception e) {
/* 2378 */         e.printStackTrace();
/*      */       }  b++; }
/*      */     
/* 2381 */     return "true";
/*      */   }
/*      */   
/*      */   public List getBoxSumCount(Integer location, String partId) {
/* 2385 */     return this.boxDAO.getBoxSumCount(location, partId);
/*      */   }
/*      */   
/*      */   public BadReasons getBadReasons(String lotSerId) {
/* 2389 */     return this.boxDAO.getBadReasons(lotSerId);
/*      */   }
/*      */   
/*      */   public String scanningMaterialsOutbound(String lotSer, String userId) {
/* 2393 */     ScanLog scanLog = new ScanLog();
/* 2394 */     scanLog.setDate(new Date());
/* 2395 */     scanLog.setDescribe(lotSer);
/* 2396 */     scanLog.setType(Integer.valueOf(6));
/* 2397 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 2398 */     if (user != null) {
/* 2399 */       scanLog.setUserId(user);
/*      */     }
/* 2401 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */ 
/*      */     
/*      */     try {
/* 2405 */       String sql = "from Box box where box.lot.id ='" + lotSer + "'";
/* 2406 */       List<Box> boxList = this.boxDAO.getObjectList(sql);
/* 2407 */       if (boxList.size() != 0) {
/* 2408 */         Box box = boxList.get(0);
/*      */         
/* 2410 */         List<StorageLocation> listLocation = this.boxDAO.getObjectList("from StorageLocation sl where sl.code = '" + 
/* 2411 */             box.getLocation().getCode() + "' and sl.freeae_status <> 0 ");
/* 2412 */         if (listLocation.size() == 0) {
/* 2413 */           return "false";
/*      */         }
/*      */ 
/*      */         
/* 2417 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RECEIVE_OUT, Boolean.valueOf(true));
/*      */ 
/*      */ 
/*      */         
/* 2421 */         box.setOut_date(new Date());
/* 2422 */         box.setStatus(BoxStatus.HASTHE);
/* 2423 */         this.boxDAO.updateObject(box);
/*      */       } 
/* 2425 */     } catch (Exception e) {
/* 2426 */       e.printStackTrace();
/* 2427 */       return e.getMessage();
/*      */     } 
/*      */     
/* 2430 */     return "ok";
/*      */   }
/*      */   
/*      */   public String scanningStockingInStorage(String lotSer, String location, String userId) {
/* 2434 */     ScanLog scanLog = new ScanLog();
/* 2435 */     scanLog.setDate(new Date());
/* 2436 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 2437 */     scanLog.setType(Integer.valueOf(3));
/* 2438 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 2439 */     if (user != null) {
/* 2440 */       scanLog.setUserId(user);
/*      */     }
/* 2442 */     this.scanLogDAO.insertScanLog(scanLog);
/*      */     
/*      */     try {
/* 2445 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 2446 */       if (storageLocation == null) {
/* 2447 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*      */       }
/* 2449 */       if (storageLocation.getFreeae_status() == YesNo.YES) {
/* 2450 */         return String.valueOf(storageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.location.freeze");
/*      */       }
/*      */       
/* 2453 */       List<Box> boxList = this.boxDAO.getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 2454 */           "' and box.status_freeze=1 ");
/* 2455 */       PurchaseOrderInspectionPendingItem item = null;
/* 2456 */       if (boxList.size() != 0) {
/* 2457 */         Box box = boxList.get(0);
/*      */         
/* 2459 */         boolean partVali = this.wmsPartManager.validatePartIsFreeze(box.getPart().getId());
/* 2460 */         if (!partVali) {
/* 2461 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.partfree.null");
/*      */         }
/*      */ 
/*      */         
/* 2465 */         BigDecimal max_inventory = storageLocation.getMax_inventory();
/* 2466 */         String locationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 2467 */           storageLocation.getCode() + "' ";
/*      */         
/* 2469 */         List<Object> locationsumQtyList = this.boxDAO.getObjectList(locationsumQty);
/* 2470 */         if (locationsumQtyList.size() > 0 && locationsumQtyList.get(0) != null) {
/* 2471 */           BigDecimal sumLocationQty = (BigDecimal)locationsumQtyList.get(0);
/* 2472 */           BigDecimal sumQty = sumLocationQty.add(box.getNumber());
/* 2473 */           if (max_inventory.compareTo(sumQty) == -1) {
/* 2474 */             return String.valueOf(lotSer) + ":" + 
/* 2475 */               Properties.getPropertiesValye("scan.sync.error.location.inventory.notEnough");
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2481 */         this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 2482 */             Boolean.valueOf(false));
/*      */ 
/*      */         
/* 2485 */         box.setInStorageNumber(box.getNumber());
/* 2486 */         box.setLocation(storageLocation);
/* 2487 */         box.setIn_date(new Date());
/* 2488 */         box.setStatus(BoxStatus.HASBEENINTO);
/* 2489 */         this.boxDAO.updateObject(box);
/*      */       } else {
/* 2491 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*      */       } 
/* 2493 */     } catch (Exception e) {
/* 2494 */       e.printStackTrace();
/*      */     } 
/* 2496 */     return "ok";
/*      */   }
/*      */   
/*      */   public String purchaseReturnMaterialByBox(String array, User user) {
/* 2500 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 2501 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*      */       
/* 2503 */       Box box = getBoxBylotSer2(id);
/*      */       
/* 2505 */       ScanLog scanLog = new ScanLog();
/* 2506 */       scanLog.setDate(new Date());
/* 2507 */       scanLog.setDescribe(box.getLot().getId());
/* 2508 */       scanLog.setType(Integer.valueOf(12));
/* 2509 */       if (user != null) {
/* 2510 */         scanLog.setUserId(user);
/*      */       }
/* 2512 */       this.scanLogDAO.insertScanLog(scanLog);
/*      */       
/*      */       try {
/* 2515 */         boolean sign = false;
/*      */         
/* 2517 */         PurchaseOrderCondimentSingle single = box.getSingle();
/* 2518 */         PortalShipOrderItem orderItem = box.getPsoItem();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2536 */         if (single != null) {
/*      */           
/* 2538 */           single.setNumber(single.getNumber().subtract(box.getNumber()));
/* 2539 */           single.setDelivery_qty(single.getNumber());
/* 2540 */           this.boxDAO.updateObject(single);
/* 2541 */           sign = true;
/* 2542 */         } else if (single == null) {
/* 2543 */           PurchaseOrderInspectionPendingItem item = box.getPsoItem().getPoipItem();
/*      */           
/* 2545 */           if (item != null) {
/* 2546 */             item.setReceiptQty(item.getReceiptQty().subtract(box.getNumber()));
/* 2547 */             item.setQtyOpen(item.getQtyOpen().add(box.getNumber()));
/* 2548 */             this.boxDAO.updateObject(item);
/* 2549 */             PurchaseOrderInspectionPending poip = item.getPoip_number();
/* 2550 */             poip.setStatus(PurchaseOrderStatus.WAIT);
/* 2551 */             this.boxDAO.updateObject(poip);
/* 2552 */             sign = true;
/*      */           } else {
/* 2554 */             sign = false;
/*      */           } 
/*      */           
/* 2557 */           if (single == null) {
/* 2558 */             PortalShipOrderItem portalShipOrderItem = box.getPsoItem();
/* 2559 */             portalShipOrderItem.setDeliveryNumber(portalShipOrderItem.getDeliveryNumber().subtract(box.getNumber()));
/* 2560 */             this.portalShipOrderItemManager.updatePortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/*      */           
/* 2563 */           box.setEnabled(EnabledDisabled.DISABLED);
/* 2564 */           box.setStatus(BoxStatus.Wait);
/* 2565 */           this.boxDAO.updateObject(box);
/*      */         } 
/* 2567 */       } catch (Exception e) {
/* 2568 */         e.printStackTrace();
/*      */       }  b++; }
/*      */     
/* 2571 */     return "true";
/*      */   }
/*      */   
/*      */   public List<PurchaseOrderRqcUnqualified> getPurchaseOrderRqcUnqualifiedList(Integer boxId) {
/* 2575 */     return this.boxDAO.getPurchaseOrderRqcUnqualifiedList(boxId);
/*      */   }
/*      */   
/*      */   public List<Box> getBoxByPart(String part) {
/* 2579 */     return this.boxDAO.getBoxByPart(part);
/*      */   }
/*      */ 
/*      */   
/*      */   public void deleteBoxByWmsUWItem(Box box) {
/* 2584 */     this.boxDAO.deleteBoxByWmsUWItem(box);
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/BoxManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */