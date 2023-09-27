/*     */ package com.aof.service.inventory.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.inventory.InventoryDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.basicDataView.query.LocationPartNumberQueryOrder;
/*     */ import com.aof.model.inventory.Inventory;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.inventory.InventoryMoving;
/*     */ import com.aof.model.inventory.InventoryRecord;
/*     */ import com.aof.model.inventory.InventoryTotal;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.inventory.query.InventoryQueryOrder;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class InventoryManagerImpl
/*     */   extends BaseManager
/*     */   implements InventoryManager
/*     */ {
/*     */   private InventoryDAO dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private InventoryManager inventoryManager;
/*     */   private BoxManager boxManager;
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  44 */     this.boxManager = boxManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  48 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  52 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setDao(InventoryDAO dao) {
/*  56 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getInventoryListsum(Map conditions) {
/*  61 */     return this.dao.getInventoryListsum(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory(Integer id) {
/*  66 */     return this.dao.getInventory(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part) {
/*  71 */     return this.dao.getInventoryByPart(part);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByInventoryId(Integer InventoryId) {
/*  76 */     return this.dao.getInventoryRecordByInventoryId(InventoryId);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryListCount(Map conditions) {
/*  81 */     return this.dao.getInventoryListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/*  87 */     return this.dao.getInventoryList(conditions, pageNo, pageSize, order, 
/*  88 */         descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory insertInventory(Inventory inventory) {
/*  93 */     return this.dao.insertInventory(inventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryRecord insertInventoryRecord(InventoryRecord inventoryRecord) {
/*  98 */     return this.dao.insertInventoryRecord(inventoryRecord);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory updateInventory(Inventory inventory) {
/* 103 */     return this.dao.updateInventory(inventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteInventory(Inventory inventory) {
/* 108 */     this.dao.deleteInventory(inventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecord(Integer id) {
/* 113 */     return this.dao.getInventoryRecord(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part, Integer site) {
/* 118 */     return this.dao.getInventoryByPart(part);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Inventory> getInventoryByRoomAndPart(Integer room, String part) {
/* 123 */     return this.dao.getInventoryByRoomAndPart(room, part);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Inventory> getInventoryByPart(String part, Integer site, Integer n) {
/* 129 */     return this.dao.getInventoryByPart(part);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryRecord(Date startDate, Date endDate, String part) {
/* 135 */     return this.dao.getInventoryRecord(startDate, endDate, part);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventoryByPartIdAndLocation(String id) {
/* 140 */     return this.dao.getInventoryByPartIdAndLocation(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryRecordReturnPeriodInitialValue(String strLoc, String strPart) {
/* 146 */     return this.dao.getInventoryRecordReturnPeriodInitialValue(strLoc, strPart);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPart(String partId, String location) {
/* 152 */     return this.dao.getInventoryRecordByLocationAndPart(partId);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPart(String partId) {
/* 157 */     return this.dao.getInventoryRecordByLocationAndPart(partId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPartBalance(String partId, String location) {
/* 163 */     return this.dao.getInventoryRecordByLocationAndPartBalance(partId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryRecord getInventoryRecordByLocationAndPartBalance(String partId) {
/* 169 */     return this.dao.getInventoryRecordByLocationAndPartBalance(partId);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryBySemiFinished() {
/* 174 */     return this.dao.getInventoryBySemiFinished();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryByPowderStorage() {
/* 179 */     return this.dao.getInventoryByPowderStorage();
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryDetial(Integer id) {
/* 184 */     return this.dao.getInventoryDetial(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial insertInventoryDetial(InventoryDetial detial) {
/* 189 */     return this.dao.insertInventoryDetial(detial);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial updateInventoryDetial(InventoryDetial detial) {
/* 194 */     return this.dao.updateInventoryDetial(detial);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryTotal getInventoryTotal(Integer id) {
/* 199 */     return this.dao.getInventoryTotal(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryTotal insertInventoryTotal(InventoryTotal inventoryTotal) {
/* 204 */     return this.dao.insertInventoryTotal(inventoryTotal);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryTotal updateInventoryTotal(InventoryTotal inventoryTotal) {
/* 209 */     return this.dao.updateInventoryTotal(inventoryTotal);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryAdjustmentByWoLot(String location, Box box, InventoryType type, Boolean isOutBound) throws Exception {
/* 215 */     InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 216 */     tool.inventoryAdjustmentByWoLot(location, box, type, isOutBound);
/*     */   }
/*     */   
/*     */   public String systemPurchaseOrderOutbound(String arrays, User user) {
/* 220 */     String[] array = arrays.split(",");
/* 221 */     String sign = ""; byte b; int i; String[] arrayOfString1;
/* 222 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String lotser = arrayOfString1[b];
/* 223 */       String str = scanningPurchaseOrderOutbound(lotser, 
/* 224 */           user.getId().toString());
/* 225 */       sign = String.valueOf(sign) + str;
/*     */       b++; }
/*     */     
/* 228 */     return sign;
/*     */   }
/*     */   
/*     */   public String scanningPurchaseOrderOutbound(String lot, String userId) {
/* 232 */     ScanLog scanLog = new ScanLog();
/* 233 */     scanLog.setDate(new Date());
/* 234 */     scanLog.setDescribe(lot);
/* 235 */     scanLog.setType(Integer.valueOf(4));
/* 236 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 237 */     if (user != null) {
/* 238 */       scanLog.setUserId(user);
/*     */     }
/* 240 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 243 */       List<Box> boxList = this.dao.getObjectList("from Box box where box.lot.id = '" + lot + "' " + 
/* 244 */           " and box.status=3 and box.status_freeze = 1 and box.enabled=0");
/*     */       
/* 246 */       if (boxList.size() > 0) {
/* 247 */         Box box = boxList.get(0);
/* 248 */         if (box.getLocation().getFreeae_status() == YesNo.YES) {
/* 249 */           return String.valueOf(box.getLot().getId()) + "," + box.getLocation().getCode() + ":" + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */         }
/* 251 */         List<StorageLocation> locations = this.dao
/* 252 */           .getObjectList("from StorageLocation sl where sl.basic_storeroom_id.type = 5 and sl.freeae_status <> 0");
/* 253 */         if (locations.size() > 0) {
/* 254 */           StorageLocation location = locations.get(0);
/* 255 */           this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.RECEIVE_OUT, Boolean.valueOf(true));
/* 256 */           this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.LINELIBRARYIN, Boolean.valueOf(false));
/*     */ 
/*     */           
/* 259 */           InventoryMoving moving = new InventoryMoving();
/* 260 */           moving.setOld_location(box.getLocation());
/* 261 */           moving.setNew_location(location);
/* 262 */           moving.setQty(box.getNumber());
/* 263 */           moving.setDate(new Date());
/* 264 */           moving.setIs_sync(YesNo.NO);
/* 265 */           moving.setPart(box.getPart());
/* 266 */           moving.setRemark("仓储原材料出库：" + lot);
/* 267 */           this.dao.saveObject(moving);
/*     */ 
/*     */           
/* 270 */           box.setOut_date(new Date());
/* 271 */           box.setLocation(location);
/* 272 */           box.setStatus(BoxStatus.HASTHE);
/* 273 */           box.setIn_date_line(new Date());
/* 274 */           this.dao.updateObject(box);
/*     */         } else {
/* 276 */           return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.locationline.null");
/*     */         } 
/*     */       } else {
/* 279 */         return String.valueOf(lot) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.null");
/*     */       } 
/* 281 */     } catch (Exception e) {
/* 282 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 285 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryAdjustmentByWoLotProduce(String location, WmsPart part, BigDecimal sumQty, InventoryType type, Boolean isOutBound) throws Exception {
/* 292 */     InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 293 */     tool.inventoryAdjustmentByWoLotProduce(location, part, sumQty, type, isOutBound);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryTotalByPart(String part) {
/* 298 */     return this.dao.getInventoryTotalByPart(part);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryReportListCount(Map conditions) {
/* 303 */     return this.dao.getInventoryReportListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryReportList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 309 */     return this.dao.getInventoryReportList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public List test() {
/* 314 */     return this.dao.test();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryDetialListCount(Map conditions) {
/* 319 */     return this.dao.getInventoryDetialListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryDetialList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 325 */     return this.dao.getInventoryDetialList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventoryDetialByPartList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 331 */     return this.dao.getInventoryDetialByPartList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public int getInventoryDetialByPartListCount(Map conditions) {
/* 335 */     return this.dao.getInventoryDetialByPartListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryTotalList(Map conditions) {
/* 340 */     return this.dao.getInventoryTotalList(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getInventoryTotalList(Map conditions, int pageNo, int pageSize, InventoryQueryOrder order, boolean descend) {
/* 345 */     return this.dao.getInventoryTotalList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inventoryProduceByWoLot(String location, WmsTool wmsTool, InventoryType type, Boolean isOutBound, WmsPart part) throws Exception {
/* 350 */     InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 351 */     tool.inventoryProduceByWoLot(location, wmsTool, type, isOutBound, part);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryDetial getInventoryDetialByPartAndLocation(String partId, Integer locationId) {
/* 357 */     return this.dao.getInventoryDetialByPartAndLocation(partId, locationId);
/*     */   }
/*     */   
/*     */   public void updateInventoryDetialCount() {
/* 361 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 362 */     conditions.put(InventoryQueryCondition.PART_ID_TYPE_LIKE, "CL");
/* 363 */     conditions.put(InventoryQueryCondition.DETAIL_STORAGE_ID_EQ, Integer.valueOf(5));
/* 364 */     List<InventoryDetial> datialList = getInventoryDetialList(conditions, 0, -1, null, false);
/* 365 */     for (InventoryDetial inventoryDetial : datialList) {
/* 366 */       List listsCount = this.boxManager.getBoxSumCount(inventoryDetial.getLocation().getId(), inventoryDetial.getPart().getId());
/* 367 */       BigDecimal count = new BigDecimal(0);
/* 368 */       for (Object object : listsCount) {
/* 369 */         count = (BigDecimal)object;
/*     */       }
/* 371 */       inventoryDetial.setNumber(count);
/* 372 */       updateInventoryDetial(inventoryDetial);
/*     */     } 
/* 374 */     List<InventoryDetial> inventoryDatialList = getInventoryDetialList(null, 0, -1, null, false);
/* 375 */     for (InventoryDetial inventoryDetial : inventoryDatialList) {
/* 376 */       List listsCount = this.dao.getBoxSumCount(inventoryDetial.getPart().getId());
/* 377 */       BigDecimal count = new BigDecimal(0);
/* 378 */       for (Object object : listsCount) {
/* 379 */         count = (BigDecimal)object;
/*     */       }
/* 381 */       inventoryDetial.setPart_qty(count);
/* 382 */       updateInventoryDetial(inventoryDetial);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLocationPartNumberListCount(Map conditions) {
/* 387 */     return this.dao.getLocationPartNumberListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getLocationPartNumberList(Map conditions, int pageNo, int pageSize, LocationPartNumberQueryOrder order, boolean descend) {
/* 392 */     return this.dao.getLocationPartNumberList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public String scanningProductInLocationNumber(String lotSer, String userId) {
/* 396 */     String str = "";
/*     */     try {
/* 398 */       Box box = this.boxManager.getBoxBylotSer2(lotSer);
/* 399 */       if (box != null) {
/* 400 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 401 */         conditions.put(InventoryQueryCondition.NUMBER_NE, Integer.valueOf(0));
/* 402 */         conditions.put(InventoryQueryCondition.PART_EQ, box.getPart().getId());
/* 403 */         List<InventoryDetial> result = this.dao.getInventoryDetialList(conditions, 0, -1, 
/* 404 */             InventoryQueryOrder.LOCATION_ID, true);
/* 405 */         if (result.size() > 0) {
/* 406 */           for (InventoryDetial inventoryDetial : result) {
/* 407 */             str = String.valueOf(str) + inventoryDetial.getLocation().getCode();
/* 408 */             str = String.valueOf(str) + "=";
/* 409 */             str = String.valueOf(str) + inventoryDetial.getNumber().toString();
/* 410 */             str = String.valueOf(str) + ">";
/*     */           } 
/*     */         } else {
/*     */           
/* 414 */           return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.part.not.number");
/*     */         } 
/*     */       } else {
/* 417 */         return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not");
/*     */       }
/*     */     
/* 420 */     } catch (Exception e) {
/* 421 */       return e.getMessage();
/*     */     } 
/*     */     
/* 424 */     return str;
/*     */   }
/*     */   
/*     */   public String[] scanningSearchInventory(String lotSer, String userId) {
/* 428 */     String[] str = new String[1];
/*     */     try {
/* 430 */       Box box = this.boxManager.getBoxBylotSer2(lotSer);
/* 431 */       if (box != null) {
/* 432 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 433 */         conditions.put(InventoryQueryCondition.NUMBER_NE, Integer.valueOf(0));
/* 434 */         conditions.put(InventoryQueryCondition.PART_EQ, box.getPart().getId());
/* 435 */         List<InventoryDetial> result = this.dao.getInventoryDetialList(conditions, 0, -1, InventoryQueryOrder.LOCATION_ID, true);
/* 436 */         if (result.size() > 0) {
/* 437 */           str = new String[result.size() + 1];
/* 438 */           str[0] = "ok;" + ((InventoryDetial)result.get(0)).getPart().getId();
/* 439 */           for (int i = 0; i < result.size(); i++) {
/* 440 */             InventoryDetial inventoryDetial = result.get(i);
/* 441 */             str[i + 1] = String.valueOf(inventoryDetial.getLocation().getCode()) + ";" + inventoryDetial.getNumber();
/*     */           } 
/*     */         } else {
/* 444 */           str[0] = String.valueOf(lotSer) + ":" + box.getPart().getId() + ";" + Properties.getPropertiesValye("scan.sync.error.part.not.number");
/* 445 */           return str;
/*     */         } 
/*     */       } else {
/* 448 */         str[0] = String.valueOf(lotSer) + ";" + Properties.getPropertiesValye("scan.sync.error.lotser.is.not");
/* 449 */         return str;
/*     */       } 
/* 451 */     } catch (Exception e) {
/* 452 */       str[0] = String.valueOf(lotSer) + ";" + e.getMessage();
/* 453 */       return str;
/*     */     } 
/* 455 */     return str;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/inventory/impl/InventoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */