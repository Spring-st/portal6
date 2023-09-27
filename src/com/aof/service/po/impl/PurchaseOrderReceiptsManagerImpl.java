/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.basic.SupplierPartDAO;
/*     */ import com.aof.dao.po.PurchaseOrderReceiptsDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.BasicPartLocation;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.basic.SupplierPartSamplingRatio;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.PurchaseOrderPutInStorageStatus;
/*     */ import com.aof.model.metadata.PurchaseOrderRqcStatus;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*     */ import com.aof.model.po.PurchaseOrderReceipts;
/*     */ import com.aof.model.po.PurchaseOrderReceiptsDetial;
/*     */ import com.aof.model.po.PurchaseOrderReceiptsDetialBox;
/*     */ import com.aof.model.po.PurchaseOrderRqc;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.PurchaseOrderCondimentSingleManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.aof.service.po.PurchaseOrderRQCManager;
/*     */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PurchaseOrderReceiptsManagerImpl
/*     */   extends BaseManager
/*     */   implements PurchaseOrderReceiptsManager
/*     */ {
/*     */   private PurchaseOrderReceiptsDAO dao;
/*     */   private PurchaseOrderCondimentSingleManager purchaseOrderCondimentSingleManager;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private StorageLocationManager storageLocationManager;
/*     */   private SupplierPartDAO supplierPartDAO;
/*     */   private PurchaseOrderRQCManager purchaseOrderRQCManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager;
/*     */   
/*     */   public void setPurchaseOrderInspectionPendingManager(PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager) {
/*  66 */     this.purchaseOrderInspectionPendingManager = purchaseOrderInspectionPendingManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  70 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderRQCManager(PurchaseOrderRQCManager purchaseOrderRQCManager) {
/*  75 */     this.purchaseOrderRQCManager = purchaseOrderRQCManager;
/*     */   }
/*     */   
/*     */   public void setSupplierPartDAO(SupplierPartDAO supplierPartDAO) {
/*  79 */     this.supplierPartDAO = supplierPartDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/*  84 */     this.storageLocationManager = storageLocationManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  88 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPurchaseOrderCondimentSingleManager(PurchaseOrderCondimentSingleManager purchaseOrderCondimentSingleManager) {
/*  93 */     this.purchaseOrderCondimentSingleManager = purchaseOrderCondimentSingleManager;
/*     */   }
/*     */   
/*     */   public void setDao(PurchaseOrderReceiptsDAO dao) {
/*  97 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts getPurchaseOrderReceipts(Integer id) {
/* 102 */     return this.dao.getPurchaseOrderReceipts(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLastPoApplicationCode() {
/* 107 */     return this.dao.getLastPoApplicationCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderReceiptsListCount(Map conditions) {
/* 112 */     return this.dao.getPurchaseOrderReceiptsListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderReceiptsList(Map conditions, int pageNo, int pageSize, PurchaseOrderReceiptsQueryOrder order, boolean descend) {
/* 118 */     return this.dao.getPurchaseOrderReceiptsList(conditions, pageNo, pageSize, 
/* 119 */         order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts insertPurchaseOrderReceipts(PurchaseOrderReceipts po) {
/* 125 */     return this.dao.insertPurchaseOrderReceipts(po);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts updatePurchaseOrderReceipts(PurchaseOrderReceipts po) {
/* 131 */     return this.dao.updatePurchaseOrderReceipts(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderReceiptsList() {
/* 136 */     return this.dao.getEnabledPurchaseOrderReceiptsList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePurchaseOrderReceipts(PurchaseOrderReceipts po) {
/* 141 */     this.dao.deletePurchaseOrderReceipts(po);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderReceiptsItemList(Map condtions, int pageNo, int pageSize, PurchaseOrderReceiptsQueryOrder order, boolean descend) {
/* 147 */     return this.dao.getPurchaseOrderReceiptsItemList(condtions, pageNo, 
/* 148 */         pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderReceiptsItemListCount(Map condtions) {
/* 153 */     return this.dao.getPurchaseOrderReceiptsItemListCount(condtions);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceipts getPurchaseOrderReceiptsByPoip(String id) {
/* 158 */     return this.dao.getPurchaseOrderReceiptsByPoip(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceiptsDetial getPurchaseOrderReceiptsDetial(Integer id) {
/* 163 */     return this.dao.getPurchaseOrderReceiptsDetial(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceiptsDetial insertPurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial po) {
/* 169 */     return this.dao.insertPurchaseOrderReceiptsDetial(po);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderReceiptsDetial updatePurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial po) {
/* 175 */     return this.dao.updatePurchaseOrderReceiptsDetial(po);
/*     */   }
/*     */   
/*     */   private String getLastCodeReceipts(Date date) {
/* 179 */     StringBuffer sb = new StringBuffer("PQ");
/* 180 */     for (int i = 0; i < 3; i++)
/* 181 */       sb.append('0'); 
/* 182 */     sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
/* 183 */     String prefix = sb.toString();
/* 184 */     String maxId = this.dao.getMaxPoReceiptsBeginWith(prefix);
/*     */     
/* 186 */     int serialNo = 1;
/* 187 */     if (maxId != null) {
/* 188 */       Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 5));
/* 189 */       if (maxSN == null)
/* 190 */         throw new RuntimeException("max serial no. is not digit"); 
/* 191 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/* 193 */     String sn = String.valueOf(serialNo);
/* 194 */     for (int j = 0; j < 3 - sn.length(); j++)
/* 195 */       sb.append('0'); 
/* 196 */     sb.append(sn);
/* 197 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean insertPurchaseOrderReceiptsDetial(String arrays) {
/* 201 */     String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 202 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 203 */       PurchaseOrderCondimentSingle single = this.purchaseOrderCondimentSingleManager
/* 204 */         .getPurchaseOrderCondimentSingle(Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 206 */       PurchaseOrderReceiptsDetial receiptsDetial = new PurchaseOrderReceiptsDetial();
/* 207 */       receiptsDetial.setPo_detial_id(single.getPo_detial_id());
/* 208 */       receiptsDetial.setPlan_number(single.getNumber());
/* 209 */       receiptsDetial.setActual_number(new BigDecimal(0));
/* 210 */       receiptsDetial.setStatus(YesNo.NO);
/* 211 */       receiptsDetial.setLine(single.getPo_detial_id().getLine());
/* 212 */       receiptsDetial.setPo_order(single.getPo_detial_id()
/* 213 */           .getPoip_number().getPo_number());
/* 214 */       receiptsDetial.setPo_part(single.getPo_detial_id().getItemNumber()
/* 215 */           .getId());
/*     */       
/* 217 */       this.dao.insertPurchaseOrderReceiptsDetial(receiptsDetial);
/*     */ 
/*     */       
/*     */       b++; }
/*     */ 
/*     */     
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBox(PurchaseOrderCondimentSingle single) {
/* 229 */     String suppCode = single.getPo_detial_id().getPoip_number()
/* 230 */       .getSupplier().getCode();
/*     */     
/* 232 */     Date date = single.getDate();
/*     */     
/* 234 */     BigDecimal number = single.getNumber();
/*     */     
/* 236 */     BigDecimal qty_std = single.getPo_detial_id().getQty_std();
/*     */     
/* 238 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/*     */     
/* 240 */     List list = this.dao
/* 241 */       .getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + 
/* 242 */         suppCode + "-" + format.format(date) + "-" + "%'");
/* 243 */     String maxnumber = "";
/* 244 */     String lot = "";
/* 245 */     Integer maxLot = Integer.valueOf(0);
/* 246 */     if (list.size() > 0 && list.get(0) != null) {
/* 247 */       maxnumber = list.get(0).toString();
/* 248 */       lot = maxnumber.substring(maxnumber.length() - 4, 
/* 249 */           maxnumber.length());
/* 250 */       maxLot = Integer.valueOf(Integer.parseInt(lot));
/*     */     } 
/*     */     
/* 253 */     BigDecimal len = number.divide(qty_std, 0, 4);
/* 254 */     for (int i = 0; i < len.intValue() + 1; i++) {
/* 255 */       maxLot = Integer.valueOf(maxLot.intValue() + 1);
/* 256 */       number = number.subtract(qty_std);
/* 257 */       if (number.compareTo(new BigDecimal(0)) == 1) {
/*     */         
/* 259 */         WmsLot wl = insertWmsLot(suppCode, format.format(date), 
/* 260 */             maxLot);
/*     */         
/* 262 */         Box box = new Box();
/* 263 */         box.setLot(wl);
/* 264 */         box.setPart(single.getPart());
/* 265 */         box.setNumber(qty_std);
/* 266 */         box.setStatus(BoxStatus.Wait);
/* 267 */         box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 268 */         box.setStatus_print(YesNo.NO);
/* 269 */         box.setStatus_freeze(YesNo.NO);
/* 270 */         box.setIsPrint(YesNo.NO);
/* 271 */         this.dao.saveObject(box);
/*     */         
/* 273 */         insertPoreceiptsDetialBox(box, single.getPo_detial_id());
/*     */       } else {
/* 275 */         number = number.add(qty_std);
/* 276 */         WmsLot wl = insertWmsLot(suppCode, format.format(date), 
/* 277 */             maxLot);
/*     */         
/* 279 */         Box box = new Box();
/* 280 */         box.setLot(wl);
/* 281 */         box.setPart(single.getPart());
/* 282 */         box.setNumber(number);
/* 283 */         box.setStatus(BoxStatus.Wait);
/* 284 */         box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 285 */         box.setStatus_print(YesNo.NO);
/* 286 */         box.setStatus_freeze(YesNo.NO);
/* 287 */         box.setIsPrint(YesNo.NO);
/* 288 */         this.dao.saveObject(box);
/*     */         
/* 290 */         insertPoreceiptsDetialBox(box, single.getPo_detial_id());
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertPoreceiptsDetialBox(Box box, PurchaseOrderInspectionPendingItem item) {
/* 298 */     PurchaseOrderReceiptsDetialBox detialBox = new PurchaseOrderReceiptsDetialBox();
/* 299 */     detialBox.setBox_id(box);
/* 300 */     detialBox.setPo_receipts_detial_id(item);
/* 301 */     this.dao.saveObject(detialBox);
/*     */   }
/*     */   
/*     */   public WmsLot insertWmsLot(String suppCode, String date, Integer maxLot) {
/* 305 */     DecimalFormat df = new DecimalFormat("0000");
/* 306 */     WmsLot wl = new WmsLot();
/* 307 */     String lotId = String.valueOf(suppCode) + "-" + date + "-" + df.format(maxLot);
/* 308 */     wl.setEnabled(EnabledDisabled.ENABLED);
/* 309 */     wl.setId(lotId);
/* 310 */     this.dao.saveObject(wl);
/* 311 */     return wl;
/*     */   }
/*     */   
/*     */   public List getBoxList(String arrays) {
/* 315 */     List<Box> list = new ArrayList();
/* 316 */     String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 317 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 318 */       List<Box> boxs = this.dao.getBoxList(Integer.valueOf(Integer.parseInt(id)));
/* 319 */       list.addAll(boxs);
/*     */       b++; }
/*     */     
/* 322 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public String systemPurchaseOrderReceipts(String arrays, String location, User user) {
/* 327 */     String[] array = arrays.split(",");
/* 328 */     String sign = ""; byte b; int i; String[] arrayOfString1;
/* 329 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String lotser = arrayOfString1[b];
/* 330 */       String str = scanningPurchaseOrderReceipts(lotser, user
/* 331 */           .getId().toString());
/* 332 */       sign = String.valueOf(sign) + str;
/*     */       b++; }
/*     */     
/* 335 */     return sign;
/*     */   }
/*     */   
/*     */   public String scanningPurchaseOrderReceipts(String lotSer, String userId) {
/* 339 */     ScanLog scanLog = new ScanLog();
/* 340 */     scanLog.setDate(new Date());
/* 341 */     scanLog.setDescribe(lotSer);
/* 342 */     scanLog.setType(Integer.valueOf(1));
/* 343 */     User user = (User)this.scanLogDAO.getObject(User.class, 
/* 344 */         Integer.valueOf(Integer.parseInt(userId)));
/* 345 */     if (user != null) {
/* 346 */       scanLog.setUserId(user);
/*     */     }
/* 348 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */ 
/*     */     
/*     */     try {
/* 352 */       StorageLocation storageLocation = this.storageLocationManager
/* 353 */         .getStorageLocationByDYK();
/* 354 */       if (storageLocation == null) {
/* 355 */         return String.valueOf(lotSer) + 
/* 356 */           ":" + 
/*     */           
/* 358 */           Properties.getPropertiesValye("scan.sync.error.location.null");
/*     */       }
/*     */       
/* 361 */       List<Box> boxList = this.dao
/* 362 */         .getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 363 */           "' and box.status='1' ");
/* 364 */       if (boxList.size() != 0)
/* 365 */       { Box box = boxList.get(0);
/*     */         
/* 367 */         if (box.getSingle() != null) {
/*     */           
/* 369 */           if (box.getSingle().getPo_detial_id().getPoip_number()
/* 370 */             .getStatus().equals(PurchaseOrderStatus.CLOSE)) {
/* 371 */             return String.valueOf(lotSer) + 
/* 372 */               ":" + 
/*     */               
/* 374 */               Properties.getPropertiesValye("scan.sync.error.lot.is.po.close");
/*     */           }
/*     */           
/* 377 */           PurchaseOrderCondimentSingle single = box.getSingle();
/* 378 */           if (single.getDelivery_qty() == null) {
/* 379 */             single.setDelivery_qty(new BigDecimal(0));
/*     */           }
/*     */           
/* 382 */           single.setDelivery_qty(single.getDelivery_qty().add(
/* 383 */                 box.getNumber()));
/* 384 */           this.purchaseOrderCondimentSingleManager
/* 385 */             .updatePurchaseOrderCondimentSingle(single);
/*     */ 
/*     */           
/* 388 */           PurchaseOrderInspectionPendingItem item = single
/* 389 */             .getPo_detial_id();
/* 390 */           if (item.getReceiptQty() == null) {
/* 391 */             item.setReceiptQty(new BigDecimal(0));
/*     */           }
/* 393 */           item.setReceiptQty(item.getReceiptQty()
/* 394 */               .add(box.getNumber()));
/* 395 */           this.dao.updateObject(item);
/*     */ 
/*     */           
/* 398 */           box.setReceivedNumber(box.getNumber());
/* 399 */           box.setDate(new Date());
/* 400 */           box.setLocation(storageLocation);
/* 401 */           box.setStatus(BoxStatus.HAVETHEGOODS);
/*     */ 
/*     */           
/* 404 */           this.inventoryManager.inventoryAdjustmentByWoLot(
/* 405 */               storageLocation.getCode(), box, 
/* 406 */               InventoryType.RECEIPT, Boolean.valueOf(false));
/*     */ 
/*     */           
/* 409 */           String sql = "from BasicPartLocation bpl where bpl.part.id='" + 
/* 410 */             box.getPart().getId() + "' ";
/* 411 */           List<BasicPartLocation> locations = this.dao.getObjectList(sql);
/* 412 */           if (locations.size() > 0) {
/* 413 */             BasicPartLocation location = locations.get(0);
/* 414 */             StoreRoom room = location.getLocation()
/* 415 */               .getBasic_storeroom_id();
/*     */             
/* 417 */             if (room.getType().equals(
/* 418 */                 StoreRoomType.RAWMATERIALSLINE))
/*     */             {
/* 420 */               insertPurchaseOrderPutInStorage(item, box, 
/* 421 */                   location.getLocation());
/*     */               
/* 423 */               box.setIn_date_line(new Date());
/*     */             }
/*     */           
/*     */           }
/* 427 */           else if (single.getNumber().compareTo(
/* 428 */               single.getDelivery_qty()) == 0) {
/* 429 */             insertRqc(box, true);
/*     */           } 
/*     */ 
/*     */           
/* 433 */           String sqlClose = "select sum(item.qty),sum(item.receiptQty) from PurchaseOrderInspectionPendingItem item where item.poip_number.po_number='" + 
/* 434 */             box.getPo_number() + "' ";
/* 435 */           List<Object[]> list = this.dao.getObjectList(sql);
/* 436 */           for (Object[] objects : list) {
/* 437 */             BigDecimal sumQty = (BigDecimal)objects[0];
/* 438 */             BigDecimal sumreceiptQty = (BigDecimal)objects[1];
/* 439 */             if (sumQty.compareTo(sumreceiptQty) == 0) {
/* 440 */               PurchaseOrderInspectionPending po = box.getSingle()
/* 441 */                 .getPo_detial_id().getPoip_number();
/* 442 */               po.setStatus(PurchaseOrderStatus.CLOSE);
/* 443 */               this.dao.updateObject(po);
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 448 */           if (box.getPsoItem().getPoipItem().getPoip_number()
/* 449 */             .getStatus().equals(PurchaseOrderStatus.CLOSE)) {
/* 450 */             return String.valueOf(lotSer) + 
/* 451 */               ":" + 
/*     */               
/* 453 */               Properties.getPropertiesValye("scan.sync.error.lot.is.null");
/*     */           }
/*     */           
/* 456 */           Integer id = box.getPsoItem().getPoipItem().getId();
/* 457 */           String sqlPoip = "from PurchaseOrderInspectionPendingItem item where item.id=" + 
/* 458 */             id + " ";
/* 459 */           List<PurchaseOrderInspectionPendingItem> poipList = this.dao
/* 460 */             .getObjectList(sqlPoip);
/* 461 */           if (poipList.size() > 0) {
/* 462 */             PurchaseOrderInspectionPendingItem item = poipList
/* 463 */               .get(0);
/* 464 */             if (item.getReceiptQty() == null) {
/* 465 */               item.setReceiptQty(new BigDecimal(0));
/*     */             }
/* 467 */             item.setReceiptQty(item.getReceiptQty().add(
/* 468 */                   box.getNumber()));
/* 469 */             this.dao.updateObject(item);
/*     */ 
/*     */             
/* 472 */             box.setReceivedNumber(box.getNumber());
/* 473 */             box.setDate(new Date());
/* 474 */             box.setLocation(storageLocation);
/* 475 */             box.setStatus(BoxStatus.HAVETHEGOODS);
/*     */ 
/*     */             
/* 478 */             this.inventoryManager.inventoryAdjustmentByWoLot(
/* 479 */                 storageLocation.getCode(), box, 
/* 480 */                 InventoryType.RECEIPT, Boolean.valueOf(false));
/*     */ 
/*     */             
/* 483 */             String sql = "from BasicPartLocation bpl where bpl.part.id='" + 
/* 484 */               box.getPart().getId() + "' ";
/* 485 */             List<BasicPartLocation> locations = this.dao
/* 486 */               .getObjectList(sql);
/* 487 */             if (locations.size() > 0) {
/* 488 */               BasicPartLocation location = locations.get(0);
/* 489 */               if (location.getLocation().getBasic_storeroom_id()
/* 490 */                 .getType()
/* 491 */                 .equals(StoreRoomType.RAWMATERIALSLINE)) {
/*     */                 
/* 493 */                 insertPurchaseOrderPutInStorage(item, box, 
/* 494 */                     location.getLocation());
/*     */                 
/* 496 */                 box.setIn_date_line(new Date());
/*     */               } 
/*     */             } else {
/*     */               
/* 500 */               boolean sign = this.dao.validateReceivingEndByPoip(id, 
/* 501 */                   box.getPsoItem().getPoipItem().getQty());
/* 502 */               if (sign) {
/* 503 */                 insertRqc(box, false);
/*     */               }
/*     */             } 
/*     */             
/* 507 */             String sqlClose = "select sum(item.qty),sum(item.receiptQty) from PurchaseOrderInspectionPendingItem item where item.poip_number.po_number='" + 
/* 508 */               box.getPo_number() + "' ";
/*     */             
/* 510 */             List<Object[]> list = this.dao.getObjectList(sqlClose);
/* 511 */             for (Object[] objects : list) {
/* 512 */               BigDecimal sumQty = (BigDecimal)objects[0];
/* 513 */               BigDecimal sumreceiptQty = (BigDecimal)objects[1];
/* 514 */               if (sumQty.compareTo(sumreceiptQty) == 0) {
/* 515 */                 PurchaseOrderInspectionPending po = box
/* 516 */                   .getPsoItem().getPoipItem()
/* 517 */                   .getPoip_number();
/* 518 */                 po.setStatus(PurchaseOrderStatus.CLOSE);
/* 519 */                 this.dao.updateObject(po);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 525 */         this.dao.updateObject(box); }
/*     */       else
/* 527 */       { return String.valueOf(lotSer) + 
/* 528 */           ":" + 
/*     */           
/* 530 */           Properties.getPropertiesValye("scan.sync.error.lot.is.null"); } 
/* 531 */     } catch (Exception e) {
/* 532 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 535 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertPurchaseOrderPutInStorage(PurchaseOrderInspectionPendingItem item, Box box, StorageLocation location) {
/*     */     try {
/* 543 */       String sql1 = "from PurchaseOrderPutInStorage pis where pis.poipItem.id = '" + 
/* 544 */         item.getId() + 
/* 545 */         "' " + 
/* 546 */         " and pis.location.code = '" + 
/* 547 */         location.getCode() + 
/* 548 */         "' and pis.part.id = '" + 
/* 549 */         box.getPart().getId() + "' ";
/* 550 */       List<PurchaseOrderPutInStorage> list1 = this.dao.getObjectList(sql1);
/*     */       
/* 552 */       PurchaseOrderCondimentSingle single = box.getSingle();
/*     */       
/* 554 */       if (list1.size() > 0) {
/* 555 */         PurchaseOrderPutInStorage storage = list1.get(0);
/* 556 */         storage.setQty(storage.getQty().add(box.getNumber()));
/* 557 */         this.dao.updateObject(storage);
/*     */       } else {
/* 559 */         PurchaseOrderPutInStorage inStorage = new PurchaseOrderPutInStorage();
/* 560 */         inStorage.setDate(new Date());
/* 561 */         inStorage.setIs_sync(YesNo.NO);
/* 562 */         inStorage.setStatus(PurchaseOrderPutInStorageStatus.UNFINISHED);
/* 563 */         if (single == null) {
/* 564 */           inStorage.setPoipItem(box.getPsoItem().getPoipItem());
/*     */         } else {
/* 566 */           inStorage.setSingle(box.getSingle());
/*     */         } 
/* 568 */         inStorage.setLocation(location);
/* 569 */         inStorage.setPart(box.getPart());
/* 570 */         inStorage.setReceipts_qty(item.getQty());
/* 571 */         inStorage.setQty(box.getNumber());
/*     */         
/* 573 */         inStorage.setSupper(item.getPoip_number().getSupplier());
/* 574 */         inStorage.setLine(item.getLine());
/* 575 */         inStorage.setPo_date(item.getPoip_number().getCreateDate());
/* 576 */         inStorage.setPo_number(item.getPoip_number().getPo_number());
/* 577 */         inStorage.setPo_qty(item.getQty());
/*     */         
/* 579 */         this.dao.saveObject(inStorage);
/*     */       } 
/*     */       
/* 582 */       if (box.getSingle() == null) {
/*     */         
/* 584 */         BigDecimal amount = new BigDecimal(0);
/* 585 */         if (item.getInventoryNumber() != null) {
/* 586 */           amount = item.getInventoryNumber();
/*     */         }
/* 588 */         item.setInventoryNumber(amount.add(box.getNumber()));
/* 589 */         this.dao.updateObject(item);
/*     */       } else {
/*     */         
/* 592 */         BigDecimal amount = new BigDecimal(0);
/* 593 */         if (single.getPutIn_qty() != null) {
/* 594 */           amount = single.getPutIn_qty();
/*     */         }
/* 596 */         single.setPutIn_qty(amount.add(box.getNumber()));
/* 597 */         this.dao.updateObject(single);
/*     */       } 
/*     */ 
/*     */       
/* 601 */       if (item.getInventoryNumber() == null) {
/* 602 */         item.setInventoryNumber(new BigDecimal(0));
/*     */       }
/* 604 */       item.setInventoryNumber(item.getInventoryNumber().add(
/* 605 */             box.getNumber()));
/* 606 */       this.dao.updateObject(item);
/*     */ 
/*     */       
/* 609 */       this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation()
/* 610 */           .getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 611 */       this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), 
/* 612 */           box, InventoryType.STORAGE, Boolean.valueOf(false));
/*     */ 
/*     */       
/* 615 */       box.setLocation(location);
/* 616 */       box.setIn_date(new Date());
/* 617 */       box.setStatus(BoxStatus.HASBEENINTO);
/* 618 */       this.dao.updateObject(box);
/* 619 */     } catch (Exception e) {
/* 620 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertRqc(Box box, boolean type) {
/* 626 */     String code = null;
/* 627 */     if (type) {
/* 628 */       box.getSingle().getPo_detial_id().getPoip_number().getSupplier()
/* 629 */         .getCode();
/*     */     } else {
/* 631 */       code = box.getPsoItem().getPoipItem().getPoip_number()
/* 632 */         .getSupplier().getCode();
/*     */     } 
/* 634 */     String part = box.getPart().getId();
/* 635 */     String sql = "from SupplierPartSamplingRatio sr where sr.supplierId.code = '" + 
/* 636 */       code + "' and sr.part.id = '" + part + "' ";
/* 637 */     List<SupplierPartSamplingRatio> list = this.dao.getObjectList(sql);
/* 638 */     BigDecimal samplingRate = null;
/* 639 */     if (list.size() > 0) {
/* 640 */       SupplierPartSamplingRatio ratio = list.get(0);
/* 641 */       samplingRate = ratio.getQty();
/*     */     } 
/*     */     
/* 644 */     if (samplingRate != null && 
/* 645 */       samplingRate.compareTo(new BigDecimal(0)) == 1) {
/*     */       
/* 647 */       PurchaseOrderRqc rqc = new PurchaseOrderRqc();
/* 648 */       BigDecimal need_qty_rqc = new BigDecimal(0);
/* 649 */       samplingRate = samplingRate.multiply(new BigDecimal(0.01D));
/* 650 */       if (type) {
/* 651 */         rqc.setPoipItem(box.getSingle().getPo_detial_id());
/* 652 */         rqc.setQty(box.getSingle().getDelivery_qty());
/*     */         
/* 654 */         BigDecimal amount = samplingRate.multiply(box.getSingle()
/* 655 */             .getDelivery_qty());
/* 656 */         need_qty_rqc = need_qty_rqc.add(amount);
/*     */       } else {
/* 658 */         rqc.setPoipItem(box.getPsoItem().getPoipItem());
/* 659 */         rqc.setQty(box.getPsoItem().getPoipItem().getQty());
/*     */         
/* 661 */         BigDecimal amount = samplingRate.multiply(box.getPsoItem()
/* 662 */             .getPoipItem().getReceiptQty());
/* 663 */         need_qty_rqc = need_qty_rqc.add(amount);
/*     */       } 
/*     */       
/* 666 */       rqc.setStatus(PurchaseOrderRqcStatus.Wait);
/* 667 */       rqc.setCreate_date(new Date());
/* 668 */       rqc.setNeed_qty_rqc(need_qty_rqc);
/* 669 */       rqc.setActual_qty_rqc(new BigDecimal(0));
/* 670 */       rqc.setQualified_qty(new BigDecimal(0));
/* 671 */       rqc.setUnqualified_qty(new BigDecimal(0));
/*     */       
/* 673 */       this.purchaseOrderRQCManager.insertPurchaseOrderRqc(rqc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderReceiptsByShipOrder(String code, String userId) {
/* 679 */     int count = 0;
/* 680 */     String str1 = code.substring(0, 2);
/* 681 */     if (str1.equals("sa")) {
/* 682 */       String sql1 = "from Box box where box.psoItem.portalShipOrder.code= '" + 
/* 683 */         code + "' ";
/* 684 */       List<Box> list = this.dao.getObjectList(sql1);
/* 685 */       if (list.size() == 0) {
/* 686 */         return "null";
/*     */       }
/* 688 */       for (Box box : list) {
/* 689 */         if (box.getSingle() != null) {
/* 690 */           return "single";
/*     */         }
/* 692 */         count++;
/*     */       } 
/* 694 */       if (count == list.size()) {
/*     */         
/* 696 */         String sql = "from Box box where box.psoItem.portalShipOrder.code= '" + 
/* 697 */           code + "' ";
/* 698 */         List<Box> list1 = this.dao.getObjectList(sql);
/* 699 */         if (list1.size() == 0) {
/* 700 */           return "null";
/*     */         }
/* 702 */         String str = "";
/* 703 */         for (Box box : list1) {
/* 704 */           str = String.valueOf(str) + scanningPurchaseOrderReceipts(box.getLot()
/* 705 */               .getId(), userId);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 710 */       String sql = "from Box box where box.psoItem.portalShipOrder.code='" + code + "' ";
/* 711 */       List<Box> list = this.dao.getObjectList(sql);
/* 712 */       if (list.size() == 0) {
/* 713 */         return "null";
/*     */       }
/* 715 */       String str = "";
/* 716 */       for (Box box : list) {
/* 717 */         str = String.valueOf(str) + scanningPurchaseOrderReceipts(box.getLot().getId(), 
/* 718 */             userId);
/*     */       }
/*     */     } 
/* 721 */     return "ok";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderReceiptsManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */