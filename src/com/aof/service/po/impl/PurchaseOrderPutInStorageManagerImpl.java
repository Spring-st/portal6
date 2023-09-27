/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.po.PurchaseOrderPutInStorageDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.PurchaseOrderPutInStorageStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*     */ import com.aof.model.po.PurchaseOrderRqc;
/*     */ import com.aof.model.po.query.PurchaseOrderPutInStorageQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PurchaseOrderPutInStorageManagerImpl
/*     */   extends BaseManager
/*     */   implements PurchaseOrderPutInStorageManager
/*     */ {
/*  51 */   private Log log = LogFactory.getLog(PurchaseOrderPutInStorageManagerImpl.class);
/*     */   private PurchaseOrderPutInStorageDAO dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private StorageLocationManager storageLocationManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private WmsPartManager wmsPartManager;
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  59 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  63 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/*  67 */     this.storageLocationManager = storageLocationManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  71 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setDao(PurchaseOrderPutInStorageDAO dao) {
/*  75 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage getPurchaseOrderPutInStorage(Integer id) {
/*  80 */     return this.dao.getPurchaseOrderPutInStorage(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLastPoApplicationCode() {
/*  85 */     return this.dao.getLastPoApplicationCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/*  90 */     return this.dao.getMaxPoApplicationIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderPutInStorageListCount(Map conditions) {
/*  95 */     return this.dao.getPurchaseOrderPutInStorageListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderPutInStorageList(Map conditions, int pageNo, int pageSize, PurchaseOrderPutInStorageQueryOrder order, boolean descend) {
/* 101 */     return this.dao.getPurchaseOrderPutInStorageList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage insertPurchaseOrderPutInStorage(PurchaseOrderPutInStorage po) {
/* 106 */     return this.dao.insertPurchaseOrderPutInStorage(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderPutInStorage updatePurchaseOrderPutInStorage(PurchaseOrderPutInStorage po) {
/* 111 */     return this.dao.updatePurchaseOrderPutInStorage(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderPutInStorageList() {
/* 116 */     return this.dao.getEnabledPurchaseOrderPutInStorageList();
/*     */   }
/*     */   private void orderCreatText(String obj) throws IOException {
/* 119 */     String filePath = "D:\\webServiceLog\\";
/* 120 */     File file = new File(filePath);
/* 121 */     if (!file.exists()) {
/* 122 */       file.mkdirs();
/*     */     }
/* 124 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 125 */     String fullFileName = String.valueOf(filePath) + format.format(new Date()) + ".txt";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     FileWriter fw = null;
/*     */     
/*     */     try {
/* 133 */       File f = new File(fullFileName);
/* 134 */       fw = new FileWriter(f, true);
/* 135 */     } catch (IOException e) {
/* 136 */       e.printStackTrace();
/*     */     } 
/* 138 */     PrintWriter pw = new PrintWriter(fw);
/* 139 */     pw.println(obj);
/* 140 */     pw.flush();
/*     */     try {
/* 142 */       fw.flush();
/* 143 */       pw.close();
/* 144 */       fw.close();
/* 145 */     } catch (IOException e) {
/* 146 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   public List getRecommendLocationList(String arrays, String sign) {
/* 150 */     String[] array = arrays.split(",");
/* 151 */     List<StorageLocation> list = new ArrayList<StorageLocation>(); byte b; int i; String[] arrayOfString1;
/* 152 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 153 */       List<Box> boxs = this.dao.getObjectList("from Box box where box.lot.id = '" + id + "' ");
/* 154 */       if (boxs.size() > 0) {
/* 155 */         Box box = boxs.get(0);
/* 156 */         List<StorageLocation> locations = this.dao
/* 157 */           .getObjectList("from StorageLocation sl where sl.basic_storeroom_id.type=4 ");
/*     */         
/* 159 */         for (StorageLocation location : locations) {
/*     */ 
/*     */           
/* 162 */           BigDecimal max_inventory = location.getMax_inventory();
/* 163 */           String sql = "select sum(item.number) from InventoryDetial item where item.location.code='" + 
/* 164 */             location.getCode() + "' ";
/* 165 */           List<Object> inventoryDetial = this.dao.getObjectList(sql);
/* 166 */           BigDecimal detialAmount = new BigDecimal(0);
/* 167 */           BigDecimal amount = new BigDecimal(0);
/* 168 */           if (inventoryDetial.size() > 0 && inventoryDetial.get(0) != null) {
/* 169 */             amount = (BigDecimal)inventoryDetial.get(0);
/* 170 */             amount = amount.add(box.getNumber());
/*     */           } 
/*     */           
/* 173 */           if (amount.compareTo(max_inventory) == 0 || amount.compareTo(max_inventory) == -1) {
/* 174 */             list.add(location);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       b++; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getOrderRecommendLocationList(String arrays, String sign) {
/* 197 */     this.log.info(String.valueOf(arrays) + "," + sign);
/* 198 */     String[] array = arrays.split(",");
/* 199 */     List<StorageLocation> list = new ArrayList<StorageLocation>(); byte b; int i; String[] arrayOfString1;
/* 200 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String code = arrayOfString1[b];
/* 201 */       List<Box> boxs = this.dao.getObjectList("from Box box where box.single.code = '" + code + "' ");
/* 202 */       if (boxs.size() > 0) {
/* 203 */         Box box = boxs.get(0);
/* 204 */         List<StorageLocation> locations = this.dao
/* 205 */           .getObjectList("from StorageLocation sl where sl.basic_storeroom_id.type=4 ");
/*     */         
/* 207 */         for (StorageLocation location : locations) {
/*     */ 
/*     */           
/* 210 */           BigDecimal max_inventory = location.getMax_inventory();
/* 211 */           String sql = "select sum(item.number) from InventoryDetial item where item.location.code='" + 
/* 212 */             location.getCode() + "' ";
/* 213 */           List<Object> inventoryDetial = this.dao.getObjectList(sql);
/* 214 */           BigDecimal detialAmount = new BigDecimal(0);
/* 215 */           BigDecimal amount = new BigDecimal(0);
/* 216 */           if (inventoryDetial.size() > 0 && inventoryDetial.get(0) != null) {
/* 217 */             amount = (BigDecimal)inventoryDetial.get(0);
/* 218 */             amount = amount.add(box.getNumber());
/*     */           } 
/*     */           
/* 221 */           if (amount.compareTo(max_inventory) == 0 || amount.compareTo(max_inventory) == -1) {
/* 222 */             list.add(location);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       b++; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     return list;
/*     */   }
/*     */   public String scanningPurchaseOrderBLPInStorage(String lotSer, String location, String userId) {
/* 243 */     ScanLog scanLog = new ScanLog();
/* 244 */     scanLog.setDate(new Date());
/* 245 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 246 */     scanLog.setType(Integer.valueOf(3));
/* 247 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 248 */     if (user != null) {
/* 249 */       scanLog.setUserId(user);
/*     */     }
/* 251 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 254 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 255 */       if (storageLocation == null) {
/* 256 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */       }
/*     */       
/* 259 */       List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + "' ");
/* 260 */       if (boxList.size() == 0) {
/* 261 */         return String.valueOf(lotSer) + ":" + "该批次不存在";
/*     */       }
/*     */ 
/*     */       
/* 265 */       Box box = boxList.get(0);
/*     */ 
/*     */       
/* 268 */       this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, 
/* 269 */           Boolean.valueOf(true));
/* 270 */       this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 271 */           Boolean.valueOf(false));
/*     */ 
/*     */       
/* 274 */       box.setLocation(storageLocation);
/* 275 */       box.setStatus_freeze(YesNo.YES);
/* 276 */       this.dao.updateObject(box);
/* 277 */     } catch (Exception e) {
/* 278 */       e.printStackTrace();
/*     */     } 
/* 280 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorage(String lotSer, String location, String userId) {
/* 290 */     String date1 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:SSS")).format(new Date());
/* 291 */     ScanLog scanLog = new ScanLog();
/* 292 */     scanLog.setDate(new Date());
/* 293 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 294 */     scanLog.setType(Integer.valueOf(3));
/* 295 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 296 */     if (user != null) {
/* 297 */       scanLog.setUserId(user);
/*     */     }
/* 299 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 302 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 303 */       if (storageLocation == null) {
/* 304 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */       }
/* 306 */       if (storageLocation.getFreeae_status() == YesNo.YES) {
/* 307 */         return String.valueOf(storageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.location.freeze");
/*     */       }
/*     */       
/* 310 */       List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 311 */           "' and box.status='2' and box.status_freeze=1 ");
/* 312 */       PurchaseOrderInspectionPendingItem item = null;
/* 313 */       if (boxList.size() != 0) {
/* 314 */         Box box = boxList.get(0);
/*     */         
/* 316 */         boolean partVali = this.wmsPartManager.validatePartIsFreeze(box.getPart().getId());
/* 317 */         if (!partVali) {
/* 318 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.partfree.null");
/*     */         }
/*     */         
/* 321 */         StorageLocation storageLocationa = box.getLocation();
/* 322 */         if (storageLocationa == null) {
/* 323 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */         }
/* 325 */         if (storageLocationa.getFreeae_status() == YesNo.YES) {
/* 326 */           return String.valueOf(lotSer) + "," + storageLocationa.getCode() + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */         }
/* 328 */         BigDecimal max_inventory = storageLocation.getMax_inventory();
/* 329 */         String locationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 330 */           storageLocation.getCode() + "' ";
/*     */         
/* 332 */         List<Object> locationsumQtyList = this.dao.getObjectList(locationsumQty);
/* 333 */         if (locationsumQtyList.size() > 0 && locationsumQtyList.get(0) != null) {
/* 334 */           BigDecimal sumLocationQty = (BigDecimal)locationsumQtyList.get(0);
/* 335 */           BigDecimal sumQty = sumLocationQty.add(box.getNumber());
/* 336 */           if (max_inventory.compareTo(sumQty) == -1) {
/* 337 */             return String.valueOf(lotSer) + ":" + 
/* 338 */               Properties.getPropertiesValye("scan.sync.error.location.inventory.notEnough");
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 343 */         if (box.getSingle() == null) {
/* 344 */           item = box.getPsoItem().getPoipItem();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 370 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 371 */           inStorage.setDate(new Date());
/* 372 */           inStorage.setIs_sync(YesNo.NO);
/* 373 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 374 */           inStorage.setPoipItem(box.getPsoItem().getPoipItem());
/* 375 */           inStorage.setLocation(storageLocation);
/* 376 */           inStorage.setPart(box.getPart());
/* 377 */           inStorage.setReceipts_qty(item.getQty());
/* 378 */           inStorage.setQty(box.getNumber());
/* 379 */           inStorage.setLotSer(box.getLot());
/* 380 */           inStorage.setSupper(item.getPoip_number().getSupplier());
/* 381 */           inStorage.setLine(item.getLine());
/* 382 */           inStorage.setPo_date(item.getPoip_number().getCreateDate());
/* 383 */           inStorage.setPo_number(item.getPoip_number().getPo_number());
/* 384 */           inStorage.setPo_qty(item.getQty());
/*     */           
/* 386 */           this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */ 
/*     */ 
/*     */           
/* 390 */           BigDecimal amount = new BigDecimal(0);
/* 391 */           if (item.getInventoryNumber() != null) {
/* 392 */             amount = item.getInventoryNumber();
/*     */           }
/* 394 */           item.setInventoryNumber(amount.add(box.getNumber()));
/*     */         } else {
/* 396 */           PurchaseOrderCondimentSingle single = box.getSingle();
/* 397 */           item = box.getSingle().getPo_detial_id();
/*     */ 
/*     */           
/* 400 */           String sql = "from PurchaseOrderRqc porqc where porqc.single.id='" + single.getId() + 
/* 401 */             "' and porqc.status=1 ";
/* 402 */           List<PurchaseOrderRqc> listRqc = this.dao.getObjectList(sql);
/* 403 */           if (listRqc.size() > 0) {
/* 404 */             PurchaseOrderRqc rqc = listRqc.get(0);
/* 405 */             if (rqc.getActual_qty_rqc() == null) {
/* 406 */               rqc.setActual_qty_rqc(new BigDecimal(0));
/*     */             }
/* 408 */             if (rqc.getActual_qty_rqc().compareTo(rqc.getNeed_qty_rqc()) == -1) {
/* 409 */               return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.porqc.notFinish");
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 424 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 425 */           inStorage.setDate(new Date());
/* 426 */           inStorage.setIs_sync(YesNo.NO);
/* 427 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 428 */           inStorage.setSingle(box.getSingle());
/* 429 */           inStorage.setLocation(storageLocation);
/* 430 */           inStorage.setPart(box.getPart());
/* 431 */           inStorage.setReceipts_qty(box.getSingle().getDelivery_qty());
/* 432 */           inStorage.setQty(box.getNumber());
/*     */           
/* 434 */           inStorage.setSupper(single.getPo_detial_id().getPoip_number().getSupplier());
/* 435 */           inStorage.setLine(single.getPo_detial_id().getLine());
/* 436 */           inStorage.setPo_date(single.getPo_detial_id().getPoip_number().getCreateDate());
/* 437 */           inStorage.setPo_number(single.getPo_detial_id().getPoip_number().getPo_number());
/* 438 */           inStorage.setPo_qty(single.getDelivery_qty());
/*     */           
/* 440 */           this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */ 
/*     */ 
/*     */           
/* 444 */           BigDecimal amount = new BigDecimal(0);
/* 445 */           if (single.getPutIn_qty() != null) {
/* 446 */             amount = single.getPutIn_qty();
/*     */           }
/* 448 */           single.setPutIn_qty(amount.add(box.getNumber()));
/* 449 */           this.dao.updateObject(single);
/*     */         } 
/*     */         
/* 452 */         this.dao.updateObject(item);
/*     */ 
/*     */         
/* 455 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, 
/* 456 */             Boolean.valueOf(true));
/* 457 */         this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 458 */             Boolean.valueOf(false));
/*     */ 
/*     */         
/* 461 */         box.setInStorageNumber(box.getNumber());
/* 462 */         box.setLocation(storageLocation);
/* 463 */         box.setIn_date(new Date());
/* 464 */         box.setStatus(BoxStatus.HASBEENINTO);
/* 465 */         this.dao.updateObject(box);
/*     */       } else {
/* 467 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*     */       } 
/* 469 */     } catch (Exception e) {
/* 470 */       e.printStackTrace();
/*     */     } 
/*     */     try {
/* 473 */       String date = (new SimpleDateFormat("yyyy/MM/dd HH:mm:SSS")).format(new Date());
/* 474 */       orderCreatText("操作用户:" + user.getName() + ",扫描时间:" + date1 + ",类型:仓库入库 " + ",备注：" + lotSer + "," + location + "," + userId + ",返回时间" + date + "返回值：ok");
/* 475 */     } catch (IOException e) {
/* 476 */       e.printStackTrace();
/*     */     } 
/* 478 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorages(String codeSer, String location, String userId) {
/* 486 */     List<Box> listbox = this.dao.getObjectListBox("from Box box where box.single.code='" + codeSer + "' and box.status=2");
/* 487 */     for (Box boxs : listbox) {
/* 488 */       String lotSer = boxs.getLot().getId();
/* 489 */       ScanLog scanLog = new ScanLog();
/* 490 */       scanLog.setDate(new Date());
/* 491 */       scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 492 */       scanLog.setType(Integer.valueOf(3));
/* 493 */       User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 494 */       if (user != null) {
/* 495 */         scanLog.setUserId(user);
/*     */       }
/* 497 */       this.scanLogDAO.insertScanLog(scanLog);
/*     */ 
/*     */       
/*     */       try {
/* 501 */         StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 502 */         if (storageLocation == null) {
/* 503 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */         }
/*     */         
/* 506 */         List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 507 */             "' and box.status='2' and box.status_freeze=1 ");
/* 508 */         PurchaseOrderInspectionPendingItem item = null;
/* 509 */         if (boxList.size() != 0) {
/* 510 */           Box box = boxList.get(0);
/*     */           
/* 512 */           boolean partVali = this.wmsPartManager.validatePartIsFreeze(box.getPart().getId());
/* 513 */           if (!partVali) {
/* 514 */             return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.partfree.null");
/*     */           }
/*     */           
/* 517 */           BigDecimal max_inventory = storageLocation.getMax_inventory();
/* 518 */           String locationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 519 */             storageLocation.getCode() + "' ";
/*     */           
/* 521 */           List<Object> locationsumQtyList = this.dao.getObjectList(locationsumQty);
/* 522 */           if (locationsumQtyList.size() > 0 && locationsumQtyList.get(0) != null) {
/* 523 */             BigDecimal sumLocationQty = (BigDecimal)locationsumQtyList.get(0);
/* 524 */             BigDecimal sumQty = sumLocationQty.add(box.getNumber());
/* 525 */             if (max_inventory.compareTo(sumQty) == -1) {
/* 526 */               return String.valueOf(lotSer) + ":" + 
/* 527 */                 Properties.getPropertiesValye("scan.sync.error.location.inventory.notEnough");
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 532 */           if (box.getSingle() == null) {
/* 533 */             item = box.getPsoItem().getPoipItem();
/*     */ 
/*     */             
/* 536 */             String sql = "from PurchaseOrderRqc porqc where porqc.poipItem.id='" + item.getId() + 
/* 537 */               "' and porqc.status=1 ";
/* 538 */             List<PurchaseOrderRqc> listRqc = this.dao.getObjectList(sql);
/* 539 */             if (listRqc.size() > 0) {
/* 540 */               PurchaseOrderRqc rqc = listRqc.get(0);
/* 541 */               if (rqc.getActual_qty_rqc() == null) {
/* 542 */                 rqc.setActual_qty_rqc(new BigDecimal(0));
/*     */               }
/* 544 */               if (rqc.getActual_qty_rqc().compareTo(rqc.getNeed_qty_rqc()) == -1) {
/* 545 */                 return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.porqc.notFinish");
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 550 */             String sql1 = "from PurchaseOrderPutInStorage pis where pis.poipItem.id = '" + item.getId() + "' " + 
/* 551 */               " and pis.location.code = '" + location + "' and is_sync = 1 and pis.part.id = '" + box.getPart().getId() + 
/* 552 */               "' ";
/* 553 */             List<PurchaseOrderPutInStorage> list1 = this.dao.getObjectList(sql1);
/*     */             
/* 555 */             if (list1.size() > 0) {
/* 556 */               PurchaseOrderPutInStorage storage = list1.get(0);
/* 557 */               storage.setQty(storage.getQty().add(box.getNumber()));
/* 558 */               this.dao.updatePurchaseOrderPutInStorage(storage);
/*     */             } else {
/* 560 */               PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 561 */               inStorage.setDate(new Date());
/* 562 */               inStorage.setIs_sync(YesNo.NO);
/* 563 */               inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 564 */               inStorage.setPoipItem(box.getPsoItem().getPoipItem());
/* 565 */               inStorage.setLocation(storageLocation);
/* 566 */               inStorage.setPart(box.getPart());
/* 567 */               inStorage.setReceipts_qty(item.getQty());
/* 568 */               inStorage.setQty(box.getNumber());
/*     */               
/* 570 */               inStorage.setSupper(item.getPoip_number().getSupplier());
/* 571 */               inStorage.setLine(item.getLine());
/* 572 */               inStorage.setPo_date(item.getPoip_number().getCreateDate());
/* 573 */               inStorage.setPo_number(item.getPoip_number().getPo_number());
/* 574 */               inStorage.setPo_qty(item.getQty());
/*     */               
/* 576 */               this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */             } 
/*     */ 
/*     */             
/* 580 */             BigDecimal amount = new BigDecimal(0);
/* 581 */             if (item.getInventoryNumber() != null) {
/* 582 */               amount = item.getInventoryNumber();
/*     */             }
/* 584 */             item.setInventoryNumber(amount.add(box.getNumber()));
/*     */           } else {
/* 586 */             PurchaseOrderCondimentSingle single = box.getSingle();
/* 587 */             item = box.getSingle().getPo_detial_id();
/*     */ 
/*     */             
/* 590 */             String sql = "from PurchaseOrderRqc porqc where porqc.single.id='" + single.getId() + 
/* 591 */               "' and porqc.status=1 ";
/* 592 */             List<PurchaseOrderRqc> listRqc = this.dao.getObjectList(sql);
/* 593 */             if (listRqc.size() > 0) {
/* 594 */               PurchaseOrderRqc rqc = listRqc.get(0);
/* 595 */               if (rqc.getActual_qty_rqc() == null) {
/* 596 */                 rqc.setActual_qty_rqc(new BigDecimal(0));
/*     */               }
/* 598 */               if (rqc.getActual_qty_rqc().compareTo(rqc.getNeed_qty_rqc()) == -1) {
/* 599 */                 return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.porqc.notFinish");
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 604 */             String sql2 = "from PurchaseOrderPutInStorage pis where pis.single.id = '" + single.getId() + "' " + 
/* 605 */               " and pis.location.code = '" + location + "' and and is_sync = 1 pis.part.id = '" + 
/* 606 */               single.getPart().getId() + "' ";
/* 607 */             List<PurchaseOrderPutInStorage> list2 = this.dao.getObjectList(sql2);
/*     */             
/* 609 */             if (list2.size() > 0) {
/* 610 */               PurchaseOrderPutInStorage storage = list2.get(0);
/* 611 */               storage.setQty(storage.getQty().add(box.getNumber()));
/* 612 */               this.dao.updatePurchaseOrderPutInStorage(storage);
/*     */             } else {
/* 614 */               PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 615 */               inStorage.setDate(new Date());
/* 616 */               inStorage.setIs_sync(YesNo.NO);
/* 617 */               inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 618 */               inStorage.setSingle(box.getSingle());
/* 619 */               inStorage.setLocation(storageLocation);
/* 620 */               inStorage.setPart(box.getPart());
/* 621 */               inStorage.setReceipts_qty(box.getSingle().getDelivery_qty());
/* 622 */               inStorage.setQty(box.getNumber());
/*     */               
/* 624 */               inStorage.setSupper(single.getPo_detial_id().getPoip_number().getSupplier());
/* 625 */               inStorage.setLine(single.getPo_detial_id().getLine());
/* 626 */               inStorage.setPo_date(single.getPo_detial_id().getPoip_number().getCreateDate());
/* 627 */               inStorage.setPo_number(single.getPo_detial_id().getPoip_number().getPo_number());
/* 628 */               inStorage.setPo_qty(single.getDelivery_qty());
/*     */               
/* 630 */               this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */             } 
/*     */ 
/*     */             
/* 634 */             BigDecimal amount = new BigDecimal(0);
/* 635 */             if (single.getPutIn_qty() != null) {
/* 636 */               amount = single.getPutIn_qty();
/*     */             }
/* 638 */             single.setPutIn_qty(amount.add(box.getNumber()));
/* 639 */             this.dao.updateObject(single);
/*     */           } 
/*     */           
/* 642 */           this.dao.updateObject(item);
/*     */ 
/*     */           
/* 645 */           this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, 
/* 646 */               Boolean.valueOf(true));
/* 647 */           this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 648 */               Boolean.valueOf(false));
/*     */ 
/*     */           
/* 651 */           box.setInStorageNumber(box.getNumber());
/* 652 */           box.setLocation(storageLocation);
/* 653 */           box.setIn_date(new Date());
/* 654 */           box.setStatus(BoxStatus.HASBEENINTO);
/* 655 */           this.dao.updateObject(box); continue;
/*     */         } 
/* 657 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*     */       }
/* 659 */       catch (Exception e) {
/* 660 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 663 */     return "ok";
/*     */   }
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorageByOrderRecommendLocation(String codeSer) {
/* 667 */     String returnValue = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 681 */     return returnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderPutInStorageByRecommendLocation(String lot) {
/* 730 */     String returnValue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 744 */     return returnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRecommendLocationList() {
/* 751 */     List<StorageLocation> locations = this.dao
/* 752 */       .getObjectList("from StorageLocation sl ");
/*     */     
/* 754 */     return locations;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderBatchStorage(String lotSer, String location, String userId) {
/* 760 */     ScanLog scanLog = new ScanLog();
/* 761 */     scanLog.setDate(new Date());
/* 762 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 763 */     scanLog.setType(Integer.valueOf(3));
/* 764 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 765 */     if (user != null) {
/* 766 */       scanLog.setUserId(user);
/*     */     }
/* 768 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 771 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 772 */       if (storageLocation == null) {
/* 773 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */       }
/* 775 */       if (storageLocation.getFreeae_status() == YesNo.YES) {
/* 776 */         return String.valueOf(storageLocation.getCode()) + ":" + Properties.getPropertiesValye("scan.sync.error.location.freeze");
/*     */       }
/*     */       
/* 779 */       List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 780 */           "' and box.status='2' and box.status_freeze=1 ");
/* 781 */       PurchaseOrderInspectionPendingItem item = null;
/* 782 */       if (boxList.size() != 0) {
/* 783 */         Box box = boxList.get(0);
/*     */         
/* 785 */         boolean partVali = this.wmsPartManager.validatePartIsFreeze(box.getPart().getId());
/* 786 */         if (!partVali) {
/* 787 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.partfree.null");
/*     */         }
/*     */         
/* 790 */         StorageLocation storageLocationa = box.getLocation();
/* 791 */         if (storageLocationa == null) {
/* 792 */           return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */         }
/* 794 */         if (storageLocationa.getFreeae_status() == YesNo.YES) {
/* 795 */           return String.valueOf(lotSer) + "," + storageLocationa.getCode() + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */         }
/* 797 */         BigDecimal max_inventory = storageLocation.getMax_inventory();
/* 798 */         String locationsumQty = "select sum(item.number) from InventoryDetial item where item.location.code = '" + 
/* 799 */           storageLocation.getCode() + "' ";
/*     */         
/* 801 */         List<Object> locationsumQtyList = this.dao.getObjectList(locationsumQty);
/* 802 */         if (locationsumQtyList.size() > 0 && locationsumQtyList.get(0) != null) {
/* 803 */           BigDecimal sumLocationQty = (BigDecimal)locationsumQtyList.get(0);
/* 804 */           BigDecimal sumQty = sumLocationQty.add(box.getNumber());
/* 805 */           if (max_inventory.compareTo(sumQty) == -1) {
/* 806 */             return String.valueOf(lotSer) + ":" + 
/* 807 */               Properties.getPropertiesValye("scan.sync.error.location.inventory.notEnough");
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 812 */         if (box.getSingle() == null) {
/* 813 */           item = box.getPsoItem().getPoipItem();
/*     */           
/* 815 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 816 */           inStorage.setDate(new Date());
/* 817 */           inStorage.setIs_sync(YesNo.NO);
/* 818 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 819 */           inStorage.setPoipItem(box.getPsoItem().getPoipItem());
/* 820 */           inStorage.setLocation(storageLocation);
/* 821 */           inStorage.setPart(box.getPart());
/* 822 */           inStorage.setReceipts_qty(item.getQty());
/* 823 */           inStorage.setQty(box.getNumber());
/* 824 */           inStorage.setLotSer(box.getLot());
/* 825 */           inStorage.setSupper(item.getPoip_number().getSupplier());
/* 826 */           inStorage.setLine(item.getLine());
/* 827 */           inStorage.setPo_date(item.getPoip_number().getCreateDate());
/* 828 */           inStorage.setPo_number(item.getPoip_number().getPo_number());
/* 829 */           inStorage.setPo_qty(item.getQty());
/*     */           
/* 831 */           this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */ 
/*     */           
/* 834 */           BigDecimal amount = new BigDecimal(0);
/* 835 */           if (item.getInventoryNumber() != null) {
/* 836 */             amount = item.getInventoryNumber();
/*     */           }
/* 838 */           item.setInventoryNumber(amount.add(box.getNumber()));
/*     */         } else {
/* 840 */           PurchaseOrderCondimentSingle single = box.getSingle();
/* 841 */           item = box.getSingle().getPo_detial_id();
/*     */ 
/*     */           
/* 844 */           String sql = "from PurchaseOrderRqc porqc where porqc.single.id='" + single.getId() + 
/* 845 */             "' and porqc.status=1 ";
/* 846 */           List<PurchaseOrderRqc> listRqc = this.dao.getObjectList(sql);
/* 847 */           if (listRqc.size() > 0) {
/* 848 */             PurchaseOrderRqc rqc = listRqc.get(0);
/* 849 */             if (rqc.getActual_qty_rqc() == null) {
/* 850 */               rqc.setActual_qty_rqc(new BigDecimal(0));
/*     */             }
/* 852 */             if (rqc.getActual_qty_rqc().compareTo(rqc.getNeed_qty_rqc()) == -1) {
/* 853 */               return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.porqc.notFinish");
/*     */             }
/*     */           } 
/*     */           
/* 857 */           PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 858 */           inStorage.setDate(new Date());
/* 859 */           inStorage.setIs_sync(YesNo.NO);
/* 860 */           inStorage.setStatus(PurchaseOrderPutInStorageStatus.HASBEENCOMPLETED);
/* 861 */           inStorage.setSingle(box.getSingle());
/* 862 */           inStorage.setLocation(storageLocation);
/* 863 */           inStorage.setPart(box.getPart());
/* 864 */           inStorage.setReceipts_qty(box.getSingle().getDelivery_qty());
/* 865 */           inStorage.setQty(box.getNumber());
/*     */           
/* 867 */           inStorage.setSupper(single.getPo_detial_id().getPoip_number().getSupplier());
/* 868 */           inStorage.setLine(single.getPo_detial_id().getLine());
/* 869 */           inStorage.setPo_date(single.getPo_detial_id().getPoip_number().getCreateDate());
/* 870 */           inStorage.setPo_number(single.getPo_detial_id().getPoip_number().getPo_number());
/* 871 */           inStorage.setPo_qty(single.getDelivery_qty());
/*     */           
/* 873 */           this.dao.insertPurchaseOrderPutInStorage(inStorage);
/*     */ 
/*     */           
/* 876 */           BigDecimal amount = new BigDecimal(0);
/* 877 */           if (single.getPutIn_qty() != null) {
/* 878 */             amount = single.getPutIn_qty();
/*     */           }
/* 880 */           single.setPutIn_qty(amount.add(box.getNumber()));
/* 881 */           this.dao.updateObject(single);
/*     */         } 
/*     */         
/* 884 */         this.dao.updateObject(item);
/*     */ 
/*     */         
/* 887 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, 
/* 888 */             Boolean.valueOf(true));
/* 889 */         this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 890 */             Boolean.valueOf(false));
/*     */ 
/*     */         
/* 893 */         box.setInStorageNumber(box.getNumber());
/* 894 */         box.setLocation(storageLocation);
/* 895 */         box.setIn_date(new Date());
/* 896 */         box.setStatus(BoxStatus.HASBEENINTO);
/* 897 */         this.dao.updateObject(box);
/*     */       } else {
/* 899 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*     */       } 
/* 901 */     } catch (Exception e) {
/* 902 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 910 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningCustomerreturnInStorages(String lotSer, String location, String userId) {
/* 917 */     ScanLog scanLog = new ScanLog();
/* 918 */     scanLog.setDate(new Date());
/* 919 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 920 */     scanLog.setType(Integer.valueOf(13));
/* 921 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 922 */     if (user != null) {
/* 923 */       scanLog.setUserId(user);
/*     */     }
/* 925 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 928 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/*     */       
/* 930 */       List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + "' ");
/*     */       
/* 932 */       if (boxList.size() != 0) {
/* 933 */         Box box = boxList.get(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 980 */         this.inventoryManager.inventoryAdjustmentByWoLot(storageLocation.getCode(), box, InventoryType.STORAGE, 
/* 981 */             Boolean.valueOf(false));
/*     */ 
/*     */         
/* 984 */         box.setInStorageNumber(box.getNumber());
/* 985 */         box.setLocation(storageLocation);
/* 986 */         box.setIn_date(new Date());
/* 987 */         box.setStatus(BoxStatus.HASBEENINTO);
/* 988 */         box.setEnabled(EnabledDisabled.ENABLED);
/* 989 */         this.dao.updateObject(box);
/*     */       } else {
/* 991 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*     */       } 
/* 993 */     } catch (Exception e) {
/* 994 */       e.printStackTrace();
/*     */     } 
/* 996 */     return "ok";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderPutInStorageManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */