/*     */ package com.aof.service.comprehensive.impl;
/*     */ 
/*     */ import com.aof.dao.comprehensive.StockingDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.comprehensive.Stocking;
/*     */ import com.aof.model.comprehensive.StockingDetial;
/*     */ import com.aof.model.comprehensive.StockingRecord;
/*     */ import com.aof.model.comprehensive.StockingScanRecord;
/*     */ import com.aof.model.comprehensive.StockingSweepRecord;
/*     */ import com.aof.model.comprehensive.query.StockingDetialQueryOrder;
/*     */ import com.aof.model.comprehensive.query.StockingQueryOrder;
/*     */ import com.aof.model.comprehensive.query.StockingScanRecordQueryOrder;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.comprehensive.StockingManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class StockingManagerImpl
/*     */   extends BaseManager
/*     */   implements StockingManager
/*     */ {
/*     */   private StockingDAO dao;
/*     */   private StorageLocationManager storageLocationManager;
/*     */   
/*     */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/*  41 */     this.storageLocationManager = storageLocationManager;
/*     */   }
/*     */   
/*     */   public void setDao(StockingDAO dao) {
/*  45 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public Stocking getStocking(Integer id) {
/*  50 */     return this.dao.getStocking(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingByInventory(Integer id) {
/*  55 */     return this.dao.getStockingByInventory(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStockingListCount(Map conditions) {
/*  60 */     return this.dao.getStockingListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStockingList(Map conditions, int pageNo, int pageSize, StockingQueryOrder order, boolean descend) {
/*  66 */     return this.dao.getStockingList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public Stocking insertStocking(Stocking stocking) {
/*  70 */     if (stocking.getCode() == null)
/*     */     {
/*  72 */       stocking.setCode(getLastCode(new Date()));
/*     */     }
/*     */     
/*  75 */     return this.dao.insertStocking(stocking);
/*     */   }
/*     */   
/*     */   private String getLastCode(Date date) {
/*  79 */     StringBuffer sb = new StringBuffer("SM");
/*     */     
/*  81 */     sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
/*  82 */     String prefix = sb.toString();
/*  83 */     String maxId = this.dao.getMaxStockingIdBeginWith(prefix);
/*     */     
/*  85 */     int serialNo = 1;
/*  86 */     if (maxId != null) {
/*     */       
/*  88 */       Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 3));
/*  89 */       if (maxSN == null) throw new RuntimeException("max serial no. is not digit"); 
/*  90 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/*  92 */     String sn = String.valueOf(serialNo);
/*  93 */     for (int i = 0; i < 3 - sn.length(); i++)
/*  94 */       sb.append('0'); 
/*  95 */     sb.append(sn);
/*  96 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Stocking updateStocking(Stocking city) {
/* 101 */     return this.dao.updateStocking(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledStockingList() {
/* 106 */     return this.dao.getEnabledStockingList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteStocking(Stocking city) {
/* 111 */     this.dao.deleteStocking(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxStockingIdBeginWith(String prefix) {
/* 116 */     return this.dao.getMaxStockingIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetial(Integer id) {
/* 121 */     return this.dao.getStockingDetial(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStockingDetialListCount(Map conditions) {
/* 126 */     return this.dao.getStockingDetialListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStockingDetialList(Map conditions, int pageNo, int pageSize, StockingDetialQueryOrder order, boolean descend) {
/* 132 */     return this.dao.getStockingDetialList(conditions, pageNo, pageSize, order, 
/* 133 */         descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingDetial updateStockingDetial(StockingDetial stockingDetial) {
/* 138 */     return this.dao.updateStockingDetial(stockingDetial);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingSweepRecord insertStockingSweepRecord(StockingSweepRecord sweepRecord) {
/* 144 */     return this.dao.insertStockingSweepRecord(sweepRecord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStockingSweepRecordByLotSer(String lotSer, String locationId, String stockCode) {
/* 150 */     return this.dao
/* 151 */       .getStockingSweepRecordByLotSer(lotSer, locationId, stockCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingByStocking(String id) {
/* 156 */     return this.dao.getStockingByStocking(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getStockingByMain(Integer id) {
/* 161 */     return this.dao.getStockingByMain(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetialByLocation(String code, String stockCode) {
/* 167 */     return this.dao.getStockingDetialByLocation(code, stockCode);
/*     */   }
/*     */   public List<StockingDetial> getStockingDetialByLocationList(String code, String stockCode) {
/* 170 */     return this.dao.getStockingDetialByLocationList(code, stockCode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingDetial getStockingDetialByLocationByPart(String code, String part, String stockCode) {
/* 176 */     return this.dao.getStockingDetialByLocationByPart(code, part, stockCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordByBox(Integer id) {
/* 181 */     return this.dao.getStockingSweepRecordByBox(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingSweepRecord> getStockingSweepRecordByItem(Integer id) {
/* 186 */     return this.dao.getStockingSweepRecordByItem(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object[]> getPoIpiBoxByStockingReturnObject(Integer id) {
/* 191 */     return this.dao.getPoIpiBoxByStockingReturnObject(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingSweepRecord> getStockingSweepRecordByMain(Integer id) {
/* 196 */     return this.dao.getStockingSweepRecordByMain(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<StockingSweepRecord> getStockingByPoIpiBoxReturnObject(Integer id) {
/* 202 */     return this.dao.getStockingByPoIpiBoxReturnObject(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object[]> getStockingByInventoryLosses(Integer id) {
/* 207 */     return this.dao.getStockingByInventoryLosses(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingRecord getStockingRecord(Integer id) {
/* 212 */     return this.dao.getStockingRecord(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingRecord insertStockingRecord(StockingRecord record) {
/* 217 */     return this.dao.insertStockingRecord(record);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingRecord> getStockingDifferenceByStockingRecord(Integer id) {
/* 222 */     return this.dao.getStockingDifferenceByStockingRecord(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordByLotser(String lotser, Integer id) {
/* 228 */     return this.dao.getStockingSweepRecordByLotser(lotser, id);
/*     */   }
/*     */   
/*     */   public List getWmsInventoryCountReportBySite(Integer siteid) {
/* 232 */     List<Map> result = new ArrayList<Map>();
/* 233 */     List<Object[]> list = 
/* 234 */       this.dao.getObjectList("select inventory.storeroomId.id,sum(inventory.qty) from Inventory inventory where inventory.location.basic_storeroom_id.site.id=" + 
/* 235 */         siteid + " group by inventory.location.basic_storeroom_id");
/*     */     
/* 237 */     for (Object[] object : list) {
/* 238 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 239 */       Integer id = (Integer)object[0];
/* 240 */       map.put("storeroom", this.dao.getObject(StoreRoom.class, id));
/* 241 */       map.put("count", object[1]);
/* 242 */       result.add(map);
/*     */     } 
/* 244 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertWmsStockingItemAllList(Stocking wmsStocking) {
/* 249 */     this.dao.insertWmsStockingItemAllList(wmsStocking);
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
/*     */   public void insertWmsStockingItemList(String[] locations, Stocking wmsStocking) {
/* 267 */     insertStocking(wmsStocking);
/*     */     
/* 269 */     BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString;
/* 270 */     for (i = (arrayOfString = locations).length, b = 0; b < i; ) { String locationid = arrayOfString[b];
/* 271 */       StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(Integer.valueOf(Integer.parseInt(locationid)));
/*     */       
/* 273 */       List<InventoryDetial> itemList = this.dao.getObjectList("from InventoryDetial item where item.location.id=" + locationid + " and item.number > 0 ");
/*     */       
/* 275 */       for (InventoryDetial item : itemList) {
/* 276 */         StockingDetial wsitem = new StockingDetial();
/*     */         
/* 278 */         wsitem.setPlan_sum_qty(item.getNumber());
/*     */         
/* 280 */         wsitem.setActual_sum_qty(new BigDecimal(0));
/*     */         
/* 282 */         wsitem.setPlan_num(getLotCountByLocationAndPart(locationid, item.getPart().getId()));
/*     */         
/* 284 */         wsitem.setActual_num(Integer.valueOf(0));
/* 285 */         wsitem.setStocking(wmsStocking);
/* 286 */         wsitem.setLocation(item.getLocation());
/*     */         
/* 288 */         wsitem.setPart(item.getPart());
/* 289 */         this.dao.saveObject(wsitem);
/*     */ 
/*     */         
/* 292 */         storageLocation.setFreeae_status(YesNo.YES);
/* 293 */         this.storageLocationManager.updateStorageLocation(storageLocation);
/*     */         
/* 295 */         sum = sum.add(item.getNumber());
/*     */       } 
/*     */       b++; }
/*     */     
/* 299 */     wmsStocking.setPlan_sumQty(sum);
/* 300 */     this.dao.updateStocking(wmsStocking);
/*     */   }
/*     */   
/*     */   public Integer getLotCountByLocationAndPartAll(String locationId, String partCode) {
/* 304 */     return getLotCountByLocationAndPart(locationId, partCode);
/*     */   }
/*     */   
/*     */   private Integer getLotCountByLocationAndPart(String locationId, String partCode) {
/* 308 */     Integer count = Integer.valueOf(0);
/* 309 */     String sql = "select count(*) from Box box where box.location.id='" + locationId + "' and box.part.id = '" + partCode + "' and " + 
/* 310 */       " box.enabled = 0 and box.status = 3 ";
/*     */     
/* 312 */     List<Integer> countList1 = this.dao.getObjectList(sql);
/* 313 */     Integer num = countList1.get(0);
/* 314 */     if (countList1 != null && countList1.size() > 0 && num != null && 
/* 315 */       num.intValue() != 0) {
/* 316 */       count = Integer.valueOf(count.intValue() + ((Integer)countList1.get(0)).intValue());
/*     */     }
/*     */     
/* 319 */     return count;
/*     */   }
/*     */   
/*     */   public List<Map> getWmsStockingDifference(Stocking wmsStocking) {
/* 323 */     List<Map> listMap = new ArrayList<Map>();
/*     */     
/* 325 */     List<StockingDetial> itemList = getWmsStockingByStocking(wmsStocking.getCode());
/* 326 */     for (StockingDetial item : itemList) {
/* 327 */       if (item.getPart() != null) {
/*     */         
/* 329 */         List<Box> boxList = null;
/* 330 */         String locationCode = item.getLocation().getCode();
/*     */ 
/*     */         
/* 333 */         boxList = this.dao.getObjectList("from Box box where box.location.code='" + locationCode + "' " + 
/* 334 */             " and box.status=3 and (box.enabled = 0 or box.enabled is null) " + 
/* 335 */             " and box.id in (select record.box.id from StockingSweepRecord record where " + 
/* 336 */             " record.location.code='" + locationCode + "' and record.stocking_detail_id.id=" + item.getId() + ")");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 342 */         getBox(boxList, item, listMap);
/*     */       } 
/*     */     } 
/*     */     
/* 346 */     return listMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Map> getBox(List<Box> boxList, StockingDetial item, List<Map> listMap) {
/* 351 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*     */ 
/*     */     
/* 354 */     for (Box box : boxList) {
/* 355 */       map = new HashMap<Object, Object>();
/* 356 */       map.put("code", item.getStocking().getCode());
/* 357 */       map.put("location", item.getLocation().getCode());
/* 358 */       map.put("wmsPart", box.getPart());
/* 359 */       map.put("lotSer", box.getLot().getId());
/* 360 */       map.put("amount", box.getNumber());
/* 361 */       map.put("stockingAmount", box.getNumber());
/* 362 */       map.put("difference", new BigDecimal(0));
/* 363 */       listMap.add(map);
/*     */     } 
/*     */ 
/*     */     
/* 367 */     List<StockingSweepRecord> recordList = this.dao
/* 368 */       .getObjectList("from StockingSweepRecord record where record.stocking_detail_id.id = " + 
/*     */         
/* 370 */         item.getId() + 
/* 371 */         " and record.location.code='" + 
/* 372 */         item.getLocation().getCode() + 
/* 373 */         "' " + 
/* 374 */         " and record.box.id not in (select box.id from Box box where box.location.id=" + 
/* 375 */         item.getLocation().getId() + 
/* 376 */         " " + 
/* 377 */         " and box.status=3 and box.enabled = 0 )");
/*     */     
/* 379 */     for (StockingSweepRecord record : recordList) {
/* 380 */       map = new HashMap<Object, Object>();
/* 381 */       map.put("location", item.getLocation().getCode());
/* 382 */       if (record.getBox() != null) {
/* 383 */         map.put("wmsPart", record.getBox().getPart());
/* 384 */         map.put("lotSer", record.getBox().getLot().getId());
/* 385 */         map.put("stockingAmount", record.getBox().getNumber());
/* 386 */         map.put("difference", record.getBox().getNumber());
/*     */       } 
/* 388 */       map.put("amount", new BigDecimal(0));
/* 389 */       listMap.add(map);
/*     */     } 
/*     */ 
/*     */     
/* 393 */     List<Box> boxList2 = this.dao
/* 394 */       .getObjectList("from Box box where box.location.code='" + 
/* 395 */         item.getLocation().getCode() + 
/* 396 */         "' " + 
/* 397 */         " and box.status=3 and box.enabled = 0 " + 
/* 398 */         " and box.lot.id not in (select record.box.lot.id from StockingSweepRecord record where record.location.code='" + 
/* 399 */         item.getLocation().getCode() + 
/* 400 */         "' and record.stocking_detail_id.id=" + item.getId() + 
/* 401 */         ") and box.part.id='" + item.getPart().getId() + "' ");
/*     */     
/* 403 */     for (Box box2 : boxList2) {
/* 404 */       map = new HashMap<Object, Object>();
/* 405 */       map.put("location", item.getLocation().getCode());
/* 406 */       map.put("wmsPart", box2.getPart());
/* 407 */       map.put("lotSer", box2.getLot().getId());
/* 408 */       map.put("amount", box2.getNumber());
/* 409 */       map.put("stockingAmount", new BigDecimal(0));
/* 410 */       map.put("difference", (new BigDecimal(0)).subtract(box2.getNumber()));
/* 411 */       listMap.add(map);
/*     */     } 
/*     */     
/* 414 */     return listMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingDetial> getWmsStockingByStocking(String id) {
/* 419 */     return this.dao.getWmsStockingByStocking(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStockingRecordListCount(Map conditions) {
/* 424 */     return this.dao.getStockingRecordListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStockingRecordList(Map conditions, int pageNo, int pageSize, StockingQueryOrder order, boolean descend) {
/* 430 */     return this.dao.getStockingRecordList(conditions, pageNo, pageSize, order, descend);
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
/*     */   public Stocking updateLocationByStocking(Stocking wmsStocking) {
/* 445 */     this.dao.deleteLocationByAll(wmsStocking.getId());
/*     */     
/* 447 */     this.dao.updateStocking(wmsStocking);
/* 448 */     return wmsStocking;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertWmsStockingRecordList(String[] arrays, Stocking wmsStocking) {
/* 454 */     if (arrays != null) {
/* 455 */       byte b; int i; String[] arrayOfString; for (i = (arrayOfString = arrays).length, b = 0; b < i; ) { String str = arrayOfString[b];
/* 456 */         String[] array = str.split(",");
/* 457 */         String lotSer = array[0];
/* 458 */         String location = array[1];
/* 459 */         String amount = array[2];
/* 460 */         String stockingAmount = array[3];
/* 461 */         String difference = array[4];
/* 462 */         String part = array[5];
/*     */         
/* 464 */         if (lotSer.equals("")) {
/* 465 */           StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 466 */           StockingRecord record = new StockingRecord();
/* 467 */           record.setLocation(storageLocation);
/* 468 */           record.setCreateDate(new Date());
/* 469 */           record.setStocking_qty(new BigDecimal(amount));
/* 470 */           record.setInventory_qty(new BigDecimal(stockingAmount));
/* 471 */           record.setDifferences(new BigDecimal(difference));
/* 472 */           record.setStocking_id(wmsStocking);
/*     */           
/* 474 */           this.dao.insertStockingRecord(record);
/*     */         } else {
/* 476 */           List<Box> box = this.dao.getObjectList("from Box box where box.lot.id='" + lotSer + "' ");
/* 477 */           if (box.size() > 0) {
/* 478 */             StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/* 479 */             StockingRecord record = new StockingRecord();
/* 480 */             record.setBox(box.get(0));
/* 481 */             record.setLocation(storageLocation);
/* 482 */             record.setCreateDate(new Date());
/* 483 */             record.setStocking_qty(new BigDecimal(amount));
/* 484 */             record.setInventory_qty(new BigDecimal(stockingAmount));
/* 485 */             record.setDifferences(new BigDecimal(difference));
/* 486 */             record.setStocking_id(wmsStocking);
/* 487 */             this.dao.insertStockingRecord(record);
/*     */           } 
/*     */         } 
/*     */         b++; }
/*     */     
/*     */     } 
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
/*     */   public String scanningInventory(String lotSer, String location, String order, String userId) {
/* 578 */     ScanLog scanLog = new ScanLog();
/* 579 */     scanLog.setDate(new Date());
/* 580 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + location);
/* 581 */     scanLog.setType(Integer.valueOf(55));
/* 582 */     User user = (User)this.dao.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 583 */     if (user != null) {
/* 584 */       scanLog.setUserId(user);
/*     */     }
/* 586 */     this.dao.saveObject(scanLog);
/*     */     try {
/* 588 */       String sql = " from StockingScanRecord scanRecord where scanRecord.stocking='" + order + "' and scanRecord.lotSer='" + lotSer + "'";
/* 589 */       List<StockingScanRecord> list = this.dao.getObjectList(sql);
/* 590 */       if (list.size() == 0) {
/* 591 */         StockingScanRecord stockingScanRecord = new StockingScanRecord();
/* 592 */         stockingScanRecord.setStocking(order);
/* 593 */         stockingScanRecord.setLocation(location);
/* 594 */         stockingScanRecord.setLotSer(lotSer);
/* 595 */         stockingScanRecord.setUserId(userId);
/* 596 */         stockingScanRecord.setDate(new Date());
/* 597 */         this.dao.saveObject(stockingScanRecord);
/*     */       } else {
/*     */         
/* 600 */         StockingScanRecord stockingScanRecord = list.get(0);
/*     */         
/* 602 */         stockingScanRecord.setLocation(location);
/*     */         
/* 604 */         stockingScanRecord.setUserId(userId);
/* 605 */         stockingScanRecord.setDate(new Date());
/* 606 */         this.dao.updateObject(stockingScanRecord);
/*     */       }
/*     */     
/* 609 */     } catch (Exception e) {
/* 610 */       return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error") + e.getMessage();
/*     */     } 
/*     */     
/* 613 */     return "ok";
/*     */   }
/*     */   public int updateStockingIsSync(Integer stockingId) {
/* 616 */     return this.dao.updateStockingIsSync(stockingId);
/*     */   }
/*     */   
/*     */   public void deleteStockingDetial(StockingDetial city) {
/* 620 */     this.dao.deleteStockingDetial(city);
/*     */   }
/*     */   
/*     */   public void deleteStockingByAll(Stocking wmsStocking) {
/* 624 */     this.dao.deleteStockingSweepRecordByAll(wmsStocking.getId());
/* 625 */     this.dao.deleteLocationByAll(wmsStocking.getId());
/* 626 */     this.dao.deleteStockingDetialByAll(wmsStocking.getId());
/* 627 */     this.dao.deleteStocking(wmsStocking);
/*     */   }
/*     */   public List<Map> getStockingDetialByBoxByPart(StockingDetial item) {
/* 630 */     List<Map> listMap = new ArrayList<Map>();
/* 631 */     Map<Object, Object> map = new HashMap<Object, Object>();
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
/* 647 */     List<StockingSweepRecord> recordList = this.dao
/* 648 */       .getObjectList("from StockingSweepRecord record where record.stocking_detail_id.id = " + 
/*     */         
/* 650 */         item.getId() + 
/* 651 */         " and record.box.part.id='" + 
/* 652 */         item.getPart().getId() + 
/* 653 */         "' " + 
/* 654 */         " and record.box.id not in (select box.id from Box box where box.part.id= '" + 
/* 655 */         item.getPart().getId() + 
/* 656 */         "' " + 
/* 657 */         " and box.status=3 and box.enabled = 0 )");
/*     */     
/* 659 */     for (StockingSweepRecord record : recordList) {
/* 660 */       map = new HashMap<Object, Object>();
/* 661 */       map.put("wmsPart", item.getPart().getId());
/* 662 */       if (record.getBox() != null) {
/*     */         
/* 664 */         map.put("lotSer", record.getBox().getLot().getId());
/* 665 */         map.put("stockingAmount", record.getBox().getNumber());
/* 666 */         map.put("difference", record.getBox().getNumber());
/*     */       } 
/* 668 */       map.put("amount", new BigDecimal(0));
/* 669 */       listMap.add(map);
/*     */     } 
/*     */ 
/*     */     
/* 673 */     List<Box> boxList2 = this.dao
/* 674 */       .getObjectList("from Box box where box.part.id='" + 
/* 675 */         item.getPart().getId() + 
/* 676 */         "' " + 
/* 677 */         " and box.status=3 and box.enabled = 0 " + 
/* 678 */         " and box.lot.id not in (select record.box.lot.id from StockingSweepRecord record where record.box.part.id='" + 
/* 679 */         item.getPart().getId() + 
/* 680 */         "' and record.stocking_detail_id.id=" + item.getId() + 
/* 681 */         ") and box.part.id='" + item.getPart().getId() + "' ");
/*     */     
/* 683 */     for (Box box2 : boxList2) {
/* 684 */       map = new HashMap<Object, Object>();
/*     */ 
/*     */       
/* 687 */       map.put("wmsPart", box2.getPart());
/* 688 */       map.put("lotSer", box2.getLot().getId());
/* 689 */       map.put("amount", box2.getNumber());
/* 690 */       map.put("stockingAmount", new BigDecimal(0));
/* 691 */       map.put("difference", (new BigDecimal(0)).subtract(box2.getNumber()));
/* 692 */       listMap.add(map);
/*     */     } 
/*     */     
/* 695 */     return listMap;
/*     */   }
/*     */   public List<Map> getStockingDetialByBox(StockingDetial item) {
/* 698 */     List<Map> listMap = new ArrayList<Map>();
/* 699 */     Map<Object, Object> map = new HashMap<Object, Object>();
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
/* 715 */     List<StockingSweepRecord> recordList = this.dao
/* 716 */       .getObjectList("from StockingSweepRecord record where record.stocking_detail_id.id = " + 
/*     */         
/* 718 */         item.getId() + 
/* 719 */         " and record.location.code='" + 
/* 720 */         item.getLocation().getCode() + 
/* 721 */         "' " + 
/* 722 */         " and record.box.id not in (select box.id from Box box where box.location.id=" + 
/* 723 */         item.getLocation().getId() + 
/* 724 */         " " + 
/* 725 */         " and box.status=3 and box.enabled = 0 )");
/*     */     
/* 727 */     for (StockingSweepRecord record : recordList) {
/* 728 */       map = new HashMap<Object, Object>();
/* 729 */       map.put("location", item.getLocation().getCode());
/* 730 */       if (record.getBox() != null) {
/* 731 */         map.put("wmsPart", record.getBox().getPart());
/* 732 */         map.put("lotSer", record.getBox().getLot().getId());
/* 733 */         map.put("stockingAmount", record.getBox().getNumber());
/* 734 */         map.put("difference", record.getBox().getNumber());
/*     */       } 
/* 736 */       map.put("amount", new BigDecimal(0));
/* 737 */       listMap.add(map);
/*     */     } 
/*     */ 
/*     */     
/* 741 */     List<Box> boxList2 = this.dao
/* 742 */       .getObjectList("from Box box where box.location.code='" + 
/* 743 */         item.getLocation().getCode() + 
/* 744 */         "' " + 
/* 745 */         " and box.status=3 and box.enabled = 0 " + 
/* 746 */         " and box.lot.id not in (select record.box.lot.id from StockingSweepRecord record where record.location.code='" + 
/* 747 */         item.getLocation().getCode() + 
/* 748 */         "' and record.stocking_detail_id.id=" + item.getId() + 
/* 749 */         ") and box.part.id='" + item.getPart().getId() + "' ");
/*     */     
/* 751 */     for (Box box2 : boxList2) {
/* 752 */       map = new HashMap<Object, Object>();
/*     */       
/* 754 */       map.put("location", null);
/* 755 */       map.put("wmsPart", box2.getPart());
/* 756 */       map.put("lotSer", box2.getLot().getId());
/* 757 */       map.put("amount", box2.getNumber());
/* 758 */       map.put("stockingAmount", new BigDecimal(0));
/* 759 */       map.put("difference", (new BigDecimal(0)).subtract(box2.getNumber()));
/* 760 */       listMap.add(map);
/*     */     } 
/*     */     
/* 763 */     return listMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertWmsStockingRecordMap(Map map, Stocking wmsStocking) {
/* 769 */     if (map != null) {
/* 770 */       String lotSer = (String)map.get("lotSer");
/* 771 */       String location = (String)map.get("location");
/* 772 */       BigDecimal amount = (BigDecimal)map.get("amount");
/* 773 */       BigDecimal stockingAmount = (BigDecimal)map.get("stockingAmount");
/* 774 */       BigDecimal difference = (BigDecimal)map.get("difference");
/*     */       
/* 776 */       if (lotSer.equals("")) {
/* 777 */         StorageLocation storageLocation = null;
/* 778 */         if (location != null) {
/* 779 */           storageLocation = this.storageLocationManager.getStorageLocation(location);
/*     */         }
/* 781 */         StockingRecord record = new StockingRecord();
/* 782 */         record.setLocation(storageLocation);
/* 783 */         record.setCreateDate(new Date());
/* 784 */         record.setStocking_qty(amount);
/* 785 */         record.setInventory_qty(stockingAmount);
/* 786 */         record.setDifferences(difference);
/* 787 */         record.setStocking_id(wmsStocking);
/*     */         
/* 789 */         this.dao.insertStockingRecord(record);
/*     */       } else {
/* 791 */         List<Box> box = this.dao.getObjectList("from Box box where box.lot.id='" + lotSer + "' ");
/* 792 */         if (box.size() > 0) {
/* 793 */           StorageLocation storageLocation = null;
/* 794 */           if (location != null) {
/* 795 */             storageLocation = this.storageLocationManager.getStorageLocation(location);
/*     */           }
/* 797 */           StockingRecord record = new StockingRecord();
/* 798 */           record.setBox(box.get(0));
/* 799 */           record.setLocation(storageLocation);
/* 800 */           record.setCreateDate(new Date());
/* 801 */           record.setStocking_qty(amount);
/* 802 */           record.setInventory_qty(stockingAmount);
/* 803 */           record.setDifferences(difference);
/* 804 */           record.setStocking_id(wmsStocking);
/* 805 */           this.dao.insertStockingRecord(record);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryDetialByPlanSum(Integer id) {
/* 812 */     return this.dao.getInventoryDetialByPlanSum(id);
/*     */   }
/*     */   
/*     */   public StockingRecord updateStockingRecord(StockingRecord record) {
/* 816 */     return this.dao.updateStockingRecord(record);
/*     */   }
/*     */   
/*     */   public Integer updateLocationFreeaeByStocking(Stocking wmsStocking) {
/* 820 */     return this.dao.updateLocationFreeaeByStocking(wmsStocking.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StockingScanRecord> getStockingScanRecordByList(String stockingCode) {
/* 825 */     return this.dao.getStockingScanRecordByList(stockingCode);
/*     */   }
/*     */   
/*     */   public Object getObject(Class object, Serializable id) {
/* 829 */     return this.dao.getObject(object, id);
/*     */   }
/*     */   
/*     */   public void saveObject(Object o) {
/* 833 */     this.dao.saveObject(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateObject(Object o) {
/* 838 */     this.dao.updateObject(o);
/*     */   }
/*     */   
/*     */   public void removeObject(Object o) {
/* 842 */     this.dao.removeObject(o);
/*     */   }
/*     */   
/*     */   public List getObjectList(String sql) {
/* 846 */     return this.dao.getObjectList(sql);
/*     */   }
/*     */   
/*     */   public List<Box> getObjectListBox(String sql) {
/* 850 */     return this.dao.getObjectListBox(sql);
/*     */   }
/*     */   
/*     */   public Stocking getStockingByCode(String code) {
/* 854 */     return this.dao.getStockingByCode(code);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordBylotSer(String lot, String location, String order) {
/* 859 */     return this.dao.getStockingSweepRecordBylotSer(lot, location, order);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordBylotSer(String lot, String order) {
/* 864 */     return this.dao.getStockingSweepRecordBylotSer(lot, order);
/*     */   }
/*     */   
/*     */   public int getStockingScanRecordListCount(Map conditions) {
/* 868 */     return this.dao.getStockingScanRecordListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getStockingScanRecordList(Map conditions, int pageNo, int pageSize, StockingScanRecordQueryOrder order, boolean descend) {
/* 873 */     return this.dao.getStockingScanRecordList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public Integer updateStockingRecordStatus(StockingRecord record, Integer status) {
/* 877 */     return this.dao.updateStockingRecordStatus(record, status);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingSweepRecord getStockingSweepRecordByBoxLotSer(String lotSer, String locationId, String stockCode) {
/* 882 */     return this.dao.getStockingSweepRecordByBoxLotSer(lotSer, locationId, stockCode);
/*     */   }
/*     */   public Integer insertWmsStockingItemList(Stocking wmsStocking, String[] locatins) {
/* 885 */     return this.dao.insertWmsStockingItemList(wmsStocking, locatins);
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryDetialByActualSum(Integer id) {
/* 889 */     return this.dao.getInventoryDetialByActualSum(id);
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryDetialByDifferencesSum(Integer id) {
/* 893 */     return this.dao.getInventoryDetialByDifferencesSum(id);
/*     */   }
/*     */   public Integer insertWmsStockingItemNotBylocationList(Stocking wmsStocking, List<StorageLocation> locationList) {
/* 896 */     return this.dao.insertWmsStockingItemNotBylocationList(wmsStocking, locationList);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer insertWmsStockingItemNotBylocationList(Stocking wmsStocking, String[] locationList) {
/* 901 */     return this.dao.insertWmsStockingItemNotBylocationList(wmsStocking, locationList);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer insertWmsStockingItemListByPart(Stocking wmsStocking, String[] parts) {
/* 906 */     return this.dao.insertWmsStockingItemListByPart(wmsStocking, parts);
/*     */   }
/*     */ 
/*     */   
/*     */   public StockingScanRecord getStockingScanRecordByLotser(String lotser, Integer id) {
/* 911 */     return this.dao.getStockingScanRecordByLotser(lotser, id);
/*     */   }
/*     */   
/*     */   public StockingDetial getStockingDetialByPart(String part, String stockCode) {
/* 915 */     return this.dao.getStockingDetialByPart(part, stockCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningInventoryByPart(String lotSer, String order, String userId) {
/* 920 */     ScanLog scanLog = new ScanLog();
/* 921 */     scanLog.setDate(new Date());
/* 922 */     scanLog.setDescribe(lotSer);
/* 923 */     scanLog.setType(Integer.valueOf(55));
/* 924 */     User user = (User)this.dao.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 925 */     if (user != null) {
/* 926 */       scanLog.setUserId(user);
/*     */     }
/* 928 */     this.dao.saveObject(scanLog);
/*     */     try {
/* 930 */       String sql = " from StockingScanRecord scanRecord where scanRecord.stocking='" + order + "' and scanRecord.lotSer='" + lotSer + "'";
/* 931 */       List<StockingScanRecord> list = this.dao.getObjectList(sql);
/* 932 */       if (list.size() == 0) {
/* 933 */         StockingScanRecord stockingScanRecord = new StockingScanRecord();
/* 934 */         stockingScanRecord.setStocking(order);
/* 935 */         stockingScanRecord.setLotSer(lotSer);
/* 936 */         stockingScanRecord.setUserId(userId);
/* 937 */         stockingScanRecord.setDate(new Date());
/* 938 */         this.dao.saveObject(stockingScanRecord);
/*     */       } else {
/*     */         
/* 941 */         StockingScanRecord stockingScanRecord = list.get(0);
/*     */ 
/*     */         
/* 944 */         stockingScanRecord.setUserId(userId);
/* 945 */         stockingScanRecord.setDate(new Date());
/* 946 */         this.dao.updateObject(stockingScanRecord);
/*     */       }
/*     */     
/* 949 */     } catch (Exception e) {
/* 950 */       return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error") + e.getMessage();
/*     */     } 
/*     */     
/* 953 */     return "ok";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/impl/StockingManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */