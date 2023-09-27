/*      */ package com.aof.service.quartz.job;
/*      */ 
/*      */ import com.aof.dao.admin.SynBaseDAO;
/*      */ import com.aof.dao.sync.SyncDAO;
/*      */ import com.aof.model.basic.CustomerReturnItem;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.SyncLog;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.comprehensive.ProduceBuckleMaterial;
/*      */ import com.aof.model.comprehensive.StockingRecord;
/*      */ import com.aof.model.inventory.InventoryMoving;
/*      */ import com.aof.model.metadata.StoreRoomType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
/*      */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.PortalShipOrder;
/*      */ import com.aof.model.po.PortalShipOrderItem;
/*      */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*      */ import com.aof.model.po.WmsLot;
/*      */ import com.aof.model.product.ProductOutStorage;
/*      */ import com.aof.model.product.SalesOrderItem;
/*      */ import com.aof.model.product.SalesWorkorder;
/*      */ import com.aof.model.sync.shared.DepartmentReceiveParts;
/*      */ import com.aof.model.sync.shared.MakeAnInventory;
/*      */ import com.aof.model.sync.shared.ProductOutStorage;
/*      */ import com.aof.model.sync.shared.PurchaseReceipt;
/*      */ import com.aof.model.sync.shared.QADMessCtrl;
/*      */ import com.aof.model.sync.shared.TransferStorage;
/*      */ import com.aof.model.sync.shared.UnplanInAndOutStorage;
/*      */ import com.aof.model.sync.shared.WorkOrderBackFlushInStroage;
/*      */ import com.aof.model.sync.shared.WorkOrderBuckleMaterial;
/*      */ import com.aof.model.sync.shared.XbipddDet;
/*      */ import com.aof.model.sync.shared.XbipdmMstr;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.collections.map.HashedMap;
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
/*      */ public class DeliverMinuteSyncJob
/*      */ {
/*      */   private SynBaseDAO dao;
/*      */   private SyncDAO daoShared;
/*   64 */   private String siteDomainCode = "YA01";
/*      */   
/*      */   public void setDao(SynBaseDAO dao) {
/*   67 */     this.dao = dao;
/*      */   }
/*      */   
/*      */   public void setDaoShared(SyncDAO daoShared) {
/*   71 */     this.daoShared = daoShared;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void test() {}
/*      */ 
/*      */   
/*      */   public void startSyn() {
/*      */     try {
/*   81 */       insertSystemLog("DailySyncJob", "startSync", "", "0");
/*      */ 
/*      */ 
/*      */       
/*   85 */       Date date = new Date();
/*   86 */       SimpleDateFormat format = new SimpleDateFormat(
/*   87 */           "yyyy/MM/dd hh:mm:ss");
/*   88 */       System.out.println("insertPoIn-------------------------1" + 
/*   89 */           format.format(date));
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
/*  132 */       shipOrder();
/*  133 */       System.out.println("shipOrder-------------------------9 " + 
/*  134 */           format.format(date));
/*  135 */     } catch (Exception e) {
/*  136 */       insertSystemLog("DailySyncJob", "main", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void startSynOne(PortalShipOrder portalShipOrder) {
/*      */     try {
/*  143 */       insertSystemLog("DailySyncJob", "startSync", "", "0");
/*      */ 
/*      */ 
/*      */       
/*  147 */       Date date = new Date();
/*  148 */       SimpleDateFormat format = new SimpleDateFormat(
/*  149 */           "yyyy/MM/dd hh:mm:ss");
/*  150 */       System.out.println("insertPoIn-------------------------1" + 
/*  151 */           format.format(date));
/*      */       
/*  153 */       shipOrderOne(portalShipOrder);
/*  154 */       System.out.println("shipOrder-------------------------9 " + 
/*  155 */           format.format(date));
/*  156 */     } catch (Exception e) {
/*  157 */       insertSystemLog("DailySyncJob", "main", e.getMessage(), "1");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer insertPoIn() {
/*  167 */     int num = 0;
/*      */     
/*      */     try {
/*  170 */       String str = getSeq();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  176 */       QADMessCtrl messCtrl = 
/*  177 */         insertQADMessCtrl("XXMES_PORC_DET", str);
/*  178 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/*      */ 
/*      */       
/*  181 */       List<PurchaseOrderPutInStorage> list = this.dao
/*  182 */         .getObjectList("from PurchaseOrderPutInStorage pps where pps.is_sync = 1");
/*  183 */       HashedMap<String, BigDecimal> hashedMap = new HashedMap();
/*  184 */       for (PurchaseOrderPutInStorage inStorage : list) {
/*  185 */         StorageLocation location = inStorage.getLocation();
/*  186 */         BigDecimal qty = inStorage.getQty();
/*  187 */         WmsPart part = inStorage.getPart();
/*  188 */         String po = inStorage.getPo_number();
/*  189 */         String line = inStorage.getLine();
/*      */         
/*  191 */         String roomCode = location.getCode();
/*  192 */         WmsLot lot = inStorage.getLotSer();
/*  193 */         String date = format2.format(inStorage.getDate());
/*  194 */         String sgin = String.valueOf(po) + "," + line + "," + part.getId() + "," + 
/*  195 */           location.getCode() + "," + roomCode + "," + 
/*  196 */           inStorage.getId() + "," + lot.getId() + "," + date + 
/*  197 */           ",";
/*      */         
/*  199 */         if (!hashedMap.containsKey(sgin)) {
/*  200 */           hashedMap.put(sgin, qty);
/*      */         } else {
/*  202 */           BigDecimal oldQty = hashedMap.get(sgin);
/*  203 */           if (qty != null && !qty.equals("")) {
/*  204 */             hashedMap.put(sgin, oldQty.add(qty));
/*      */           } else {
/*  206 */             hashedMap.put(sgin, oldQty);
/*      */           } 
/*      */         } 
/*  209 */         inStorage.setIs_sync(YesNo.YES);
/*  210 */         inStorage.setIs_sync_date(new Date());
/*  211 */         this.dao.updateObject(inStorage);
/*      */       } 
/*      */       
/*  214 */       Iterator<String> iterator = hashedMap.keySet().iterator();
/*  215 */       while (iterator.hasNext()) {
/*  216 */         String key = iterator.next();
/*  217 */         BigDecimal sumQty = hashedMap.get(key);
/*      */         
/*  219 */         if (key != null) {
/*  220 */           String[] arrays = key.split(",");
/*  221 */           String po = arrays[0];
/*  222 */           String line = arrays[1];
/*  223 */           String part = arrays[2];
/*  224 */           String roomCode = arrays[4];
/*  225 */           Integer storage_det_id = Integer.valueOf(Integer.parseInt(arrays[5]));
/*  226 */           String lotSer = arrays[6];
/*  227 */           String date1 = arrays[7];
/*  228 */           Date date2 = format2.parse(date1);
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
/*  248 */           PurchaseReceipt instroage = new PurchaseReceipt();
/*  249 */           instroage.setXxmes_porc_det_id(storage_det_id);
/*  250 */           instroage.setXxmes_porc_ponbr(po);
/*  251 */           instroage.setXxmes_porc_poline(Integer.valueOf(Integer.parseInt(line)));
/*  252 */           instroage.setXxmes_porc_part(part);
/*      */           
/*  254 */           instroage.setXxmes_porc_date(date2);
/*  255 */           instroage.setXxmes_porc_qty_real(sumQty);
/*  256 */           instroage.setXxmes_porc_loc(roomCode);
/*  257 */           instroage.setXxmes_porc_boxcode(lotSer);
/*  258 */           instroage.setXxmes_porc_potype("M");
/*  259 */           instroage.setXxmes_porc_seq(str);
/*  260 */           instroage.setXxmes_porc_qadread("0");
/*  261 */           instroage.setXxmes_porc_mesread("1");
/*  262 */           instroage.setXxmes_porc_portalread("0");
/*      */           
/*  264 */           instroage.setXxmes_porc_site(this.siteDomainCode);
/*  265 */           instroage.setXxmes_porc_domain(this.siteDomainCode);
/*      */           
/*  267 */           this.daoShared.saveObject(instroage);
/*  268 */           num++;
/*      */         } 
/*      */       } 
/*      */       
/*  272 */       if (num > 0) {
/*  273 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/*  274 */         this.daoShared.saveObject(messCtrl);
/*      */       } 
/*  276 */     } catch (Exception e) {
/*  277 */       e.printStackTrace();
/*  278 */       insertSystemLog("DeliverMinuteSyncJob", "insertPoIn", 
/*  279 */           e.getMessage(), "1");
/*      */     } 
/*      */     
/*  282 */     return Integer.valueOf(num);
/*      */   }
/*      */   
/*      */   private QADMessCtrl insertQADMessCtrl(String tablename, String str) {
/*  286 */     QADMessCtrl messCtrl = new QADMessCtrl();
/*  287 */     messCtrl.setXxmes_seq(str);
/*  288 */     messCtrl.setXxmes_table(tablename);
/*  289 */     messCtrl.setXxmes_qad("0");
/*  290 */     messCtrl.setXxmes_mes("1");
/*  291 */     messCtrl.setXxmes_portal("0");
/*  292 */     messCtrl.setXxmes_edi("0");
/*      */     
/*  294 */     messCtrl.setXxmes_site(this.siteDomainCode);
/*  295 */     messCtrl.setXxmes_domain(this.siteDomainCode);
/*      */     
/*  297 */     return messCtrl;
/*      */   }
/*      */   
/*      */   private String getStoreRoom(StorageLocation moving) {
/*  301 */     String oldlocation = null;
/*  302 */     StoreRoomType locationType = moving.getBasic_storeroom_id().getType();
/*      */     
/*  304 */     if (locationType.equals(StoreRoomType.RAWMATERIALSLIBRARY)) {
/*  305 */       oldlocation = "CK001";
/*  306 */     } else if (locationType.equals(StoreRoomType.RETURNOFTHELIBRARY)) {
/*  307 */       oldlocation = "CK005";
/*  308 */     } else if (locationType.equals(StoreRoomType.RAWMATERIALSLINE)) {
/*  309 */       oldlocation = "CK004";
/*  310 */     } else if (locationType
/*  311 */       .equals(StoreRoomType.FINISHEDPRODUCELINEALONGTHE)) {
/*  312 */       oldlocation = "CK006";
/*  313 */     } else if (locationType.equals(StoreRoomType.DETEMINETHELIBRARY)) {
/*  314 */       oldlocation = "CK008";
/*  315 */     } else if (locationType.equals(StoreRoomType.SHIPPINGDEPARTMENT)) {
/*  316 */       oldlocation = "CK008";
/*  317 */     } else if (locationType.equals(StoreRoomType.PRODUCELINE)) {
/*  318 */       oldlocation = "CK004";
/*  319 */     } else if (locationType.equals(StoreRoomType.RAWMATERIALSLINE)) {
/*  320 */       oldlocation = "CK004";
/*      */     } 
/*      */     
/*  323 */     return oldlocation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer moveLocation() {
/*  332 */     int num = 0;
/*      */     
/*      */     try {
/*  335 */       String str = getSeq();
/*      */       
/*  337 */       QADMessCtrl messCtrl = insertQADMessCtrl("XXMES_IC_DET", str);
/*  338 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/*      */       
/*  340 */       List<InventoryMoving> list = this.dao
/*  341 */         .getObjectList("from InventoryMoving im where im.is_sync = 1");
/*  342 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*  343 */       for (InventoryMoving moving : list) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  348 */         String oldlocationCode = moving.getOld_location().getCode();
/*  349 */         String newlocationCode = moving.getNew_location().getCode();
/*      */         
/*  351 */         WmsPart part = moving.getPart();
/*  352 */         BigDecimal qty = moving.getQty();
/*  353 */         Date date = moving.getDate();
/*  354 */         Integer id = moving.getId();
/*      */         
/*  356 */         WmsLot lot = moving.getOldLotSer();
/*  357 */         WmsLot newlot = moving.getLotSer();
/*      */         
/*  359 */         String sgin = String.valueOf(oldlocationCode) + "," + newlocationCode + "," + 
/*  360 */           part.getId() + "," + date + "," + id + "," + 
/*  361 */           lot.getId() + "," + newlot.getId() + ",";
/*      */         
/*  363 */         if (map.get(sgin) == null) {
/*  364 */           map.put(sgin, qty);
/*      */         } else {
/*  366 */           BigDecimal amount = (BigDecimal)map.get(sgin);
/*  367 */           if (amount != null && !amount.equals("")) {
/*  368 */             map.put(sgin, amount.add(qty));
/*      */           } else {
/*  370 */             map.put(sgin, amount);
/*      */           } 
/*      */         } 
/*  373 */         moving.setIs_sync(YesNo.YES);
/*  374 */         moving.setIs_sync_date(new Date());
/*  375 */         this.dao.updateObject(moving);
/*      */       } 
/*      */       
/*  378 */       Iterator<String> iterator = map.keySet().iterator();
/*  379 */       while (iterator.hasNext()) {
/*  380 */         String key = iterator.next();
/*  381 */         BigDecimal qty = (BigDecimal)map.get(key);
/*      */         
/*  383 */         String[] arrays = key.split(",");
/*  384 */         String oldlocation = arrays[0];
/*  385 */         String newlocation = arrays[1];
/*  386 */         String part = arrays[2];
/*  387 */         String newdate = arrays[3];
/*  388 */         Integer id = Integer.valueOf(Integer.parseInt(arrays[4]));
/*  389 */         String lotSer = arrays[5];
/*  390 */         String newLotSer = arrays[6];
/*      */ 
/*      */         
/*  393 */         Date date = format2.parse(newdate);
/*      */         
/*  395 */         TransferStorage storage = new TransferStorage();
/*  396 */         storage.setXxmes_ic_seq(str);
/*  397 */         storage.setXxmes_ic_det_id(id);
/*  398 */         storage.setXxmes_ic_part(part);
/*  399 */         storage.setXxmes_ic_box_code(lotSer);
/*  400 */         storage.setXxmes_ic_qty(qty);
/*  401 */         storage.setXxmes_ic_loc_from(oldlocation);
/*  402 */         storage.setXxmes_ic_loc_to(newlocation);
/*  403 */         storage.setXxmes_ic_date(date);
/*  404 */         storage.setXxmes_ic_mesread("1");
/*  405 */         storage.setXxmes_ic_qadread("0");
/*  406 */         storage.setXxmes_ic_portalread("1");
/*  407 */         storage.setXxmes_ic_ediread("1");
/*  408 */         storage.setXxmes_ic_box_code_to(newLotSer);
/*  409 */         storage.setXxmes_ic_createdt(new Date());
/*      */         
/*  411 */         storage.setXxmes_ic_site(this.siteDomainCode);
/*  412 */         storage.setXxmes_ic_domain(this.siteDomainCode);
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
/*  424 */         this.daoShared.saveObject(storage);
/*      */         
/*  426 */         num++;
/*      */       } 
/*      */ 
/*      */       
/*  430 */       if (num > 0) {
/*  431 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/*  432 */         this.daoShared.saveObject(messCtrl);
/*      */       } 
/*  434 */     } catch (Exception e) {
/*  435 */       e.printStackTrace();
/*  436 */       insertSystemLog("DeliverMinuteSyncJob", "moveLocation", 
/*  437 */           e.getMessage(), "1");
/*      */     } 
/*      */     
/*  440 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer productOutLocation() {
/*  449 */     int num = 0;
/*      */     
/*      */     try {
/*  452 */       String str = getSeq();
/*      */       
/*  454 */       QADMessCtrl messCtrl = 
/*  455 */         insertQADMessCtrl("XXMES_SHIP_DET", str);
/*      */ 
/*      */       
/*  458 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*      */ 
/*      */       
/*  461 */       List<ProductOutStorage> list = this.dao
/*  462 */         .getObjectList("from ProductOutStorage p where p.status=1 and p.issync=0");
/*  463 */       for (ProductOutStorage product : list) {
/*      */         
/*  465 */         String part = product.getHncCode();
/*  466 */         int qty = product.getQty().intValue();
/*  467 */         Date date = product.getOutDate();
/*      */         
/*  469 */         String CKcode = product.getLocation().getCode();
/*  470 */         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
/*  471 */         SimpleDateFormat format2 = new SimpleDateFormat(
/*  472 */             "yyyy-MM-dd hh:mm:ss");
/*  473 */         BigDecimal big = new BigDecimal(qty);
/*      */         
/*  475 */         String sign = String.valueOf(part) + "," + format1.format(date) + "," + 
/*  476 */           format2.format(date) + "," + CKcode;
/*  477 */         if (map.get(sign) == null) {
/*  478 */           map.put(sign, big);
/*      */         } else {
/*  480 */           BigDecimal sumqty = (BigDecimal)map.get(sign);
/*  481 */           if (sumqty != null && !sumqty.equals("")) {
/*  482 */             map.put(sign, sumqty.add(big));
/*      */           } else {
/*  484 */             map.put(sign, sumqty);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  490 */         product.setIssync(1);
/*      */         
/*  492 */         this.dao.updateObject(product);
/*      */       } 
/*      */       
/*  495 */       Iterator<String> iterator2 = map.keySet().iterator();
/*  496 */       while (iterator2.hasNext()) {
/*  497 */         String key = iterator2.next();
/*  498 */         BigDecimal qty = (BigDecimal)map.get(key);
/*  499 */         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
/*  500 */         SimpleDateFormat format2 = new SimpleDateFormat(
/*  501 */             "yyyy-MM-dd hh:mm:ss");
/*  502 */         String[] arrays = key.split(",");
/*  503 */         String part = arrays[0];
/*  504 */         String date = arrays[1];
/*  505 */         String dateNew = arrays[2];
/*  506 */         String storeroomCode = arrays[3];
/*  507 */         Date datetimeRQ = format1.parse(date);
/*  508 */         Date datetimeSJ = format2.parse(dateNew);
/*  509 */         ProductOutStorage productOutStorage = new ProductOutStorage();
/*  510 */         productOutStorage.setXxmes_ship_seq(str);
/*  511 */         productOutStorage.setXxmes_ship_part(part);
/*  512 */         productOutStorage.setXxmes_ship_qty(qty);
/*  513 */         productOutStorage.setXxmes_ship_date(datetimeRQ);
/*  514 */         productOutStorage.setXxmes_ship_time(datetimeSJ);
/*      */         
/*  516 */         productOutStorage.setXxmes_ship_site(this.siteDomainCode);
/*  517 */         productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
/*  518 */         productOutStorage.setXxmes_ship_loc("CK004");
/*  519 */         productOutStorage.setXxmes_ship_qadread("0");
/*  520 */         productOutStorage.setXxmes_ship_mesread("1");
/*  521 */         productOutStorage.setXxmes_ship_portalread("0");
/*  522 */         productOutStorage.setXxmes_ship_ediread("0");
/*  523 */         productOutStorage.setXxmes_ship_shipto(storeroomCode);
/*      */ 
/*      */         
/*  526 */         this.daoShared.saveObject(productOutStorage);
/*  527 */         num++;
/*      */       } 
/*      */       
/*  530 */       if (num > 0) {
/*  531 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/*  532 */         this.daoShared.saveObject(messCtrl);
/*      */       } 
/*  534 */     } catch (Exception e) {
/*  535 */       insertSystemLog("DeliverMinuteSyncJob", "productOutLocation", 
/*  536 */           e.getMessage(), "1");
/*  537 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  540 */     return Integer.valueOf(num);
/*      */   }
/*      */   
/*      */   private String getSeq() {
/*  544 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/*  545 */     String str = String.valueOf(format.format(new Date())) + "-";
/*      */     
/*  547 */     String sql = "select max(qc.xxmes_seq) from QADMessCtrl qc where qc.xxmes_seq like '" + 
/*  548 */       str + "%' ";
/*  549 */     List<E> listQADMessCtrl = this.daoShared.getObjectList(sql);
/*  550 */     if (listQADMessCtrl.size() == 0 || listQADMessCtrl.get(0) == null) {
/*  551 */       str = String.valueOf(str) + "1";
/*      */     } else {
/*  553 */       String array = listQADMessCtrl.get(0).toString();
/*  554 */       String[] arrays = array.split("-");
/*  555 */       String seq = arrays[1];
/*  556 */       Integer newSeq = Integer.valueOf(Integer.parseInt(seq) + 1);
/*  557 */       str = String.valueOf(str) + newSeq.toString();
/*      */     } 
/*      */     
/*  560 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer unplannedIn() {
/*  569 */     int num = 0;
/*      */     
/*      */     try {
/*  572 */       String strH = getSeq();
/*      */       
/*  574 */       boolean mfg = true;
/*      */ 
/*      */ 
/*      */       
/*  578 */       QADMessCtrl messCtrlHave = insertQADMessCtrl(
/*  579 */           "XXMES_UPISS_DET", strH);
/*      */       
/*  581 */       List<WmsUnplannedWarehousingItem> list1 = this.dao
/*  582 */         .getObjectList("from WmsUnplannedWarehousingItem item where item.is_sync = 1 and item.unplanned_warehousing_id.reason_code is not null ");
/*  583 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/*  584 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*  585 */       for (WmsUnplannedWarehousingItem item : list1) {
/*      */         
/*  587 */         String part = item.getBox_id().getPart().getId();
/*      */         
/*  589 */         BigDecimal qty = item.getQty();
/*  590 */         StorageLocation location = item.getBox_id().getLocation();
/*  591 */         String dept = item.getUnplanned_warehousing_id()
/*  592 */           .getReason_code().getInstructions();
/*  593 */         String project = item.getUnplanned_warehousing_id()
/*  594 */           .getReason_code().getExpenses_course();
/*      */         
/*  596 */         Date date = item.getUnplanned_warehousing_id().getDate();
/*      */ 
/*      */         
/*  599 */         String code = location.getCode();
/*      */         
/*  601 */         String workCenter = item.getUnplanned_warehousing_id()
/*  602 */           .getReason_code().getDepartment_cost();
/*  603 */         String userName = item.getUnplanned_warehousing_id()
/*  604 */           .getReason_code().getUser().getName();
/*      */ 
/*      */         
/*  607 */         String sign = String.valueOf(part) + "," + code + "," + dept + "," + date + "," + 
/*  608 */           project + "," + "," + item.getId() + "," + userName + 
/*  609 */           "," + workCenter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  616 */         if (map.get(sign) == null) {
/*  617 */           map.put(sign, qty);
/*      */         } else {
/*  619 */           BigDecimal sumqty = (BigDecimal)map.get(sign);
/*  620 */           if (sumqty != null && !sumqty.equals("")) {
/*  621 */             map.put(sign, sumqty.add(qty));
/*      */           } else {
/*  623 */             map.put(sign, sumqty);
/*      */           } 
/*      */         } 
/*  626 */         item.setIs_sync(YesNo.YES);
/*  627 */         item.setIs_sync_date(new Date());
/*  628 */         this.dao.updateObject(item);
/*      */       } 
/*      */       
/*  631 */       Iterator<String> iterator = map.keySet().iterator();
/*  632 */       while (iterator.hasNext()) {
/*  633 */         String key = iterator.next();
/*  634 */         BigDecimal sumQty = (BigDecimal)map.get(key);
/*      */         
/*  636 */         String[] arrays = key.split(",");
/*  637 */         String part = arrays[0];
/*  638 */         String code = arrays[1];
/*  639 */         String dept = arrays[2];
/*  640 */         String newdate = arrays[3];
/*  641 */         String project = arrays[4];
/*  642 */         Integer unid = Integer.valueOf(Integer.parseInt(arrays[6]));
/*      */         
/*  644 */         String users = arrays[7];
/*  645 */         String workcenter = arrays[8];
/*      */         
/*  647 */         Date date = format2.parse(newdate);
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
/*  671 */         UnplanInAndOutStorage unplanInAndOutStorage = new UnplanInAndOutStorage();
/*      */         
/*  673 */         unplanInAndOutStorage.setXxmes_upiss_createur(users);
/*  674 */         unplanInAndOutStorage.setXxmes_upiss_reason(dept);
/*  675 */         unplanInAndOutStorage.setXxmes_upiss_workcenter(workcenter);
/*  676 */         unplanInAndOutStorage.setXxmes_upiss_char1(project);
/*  677 */         unplanInAndOutStorage.setXxmes_upiss_seq(strH);
/*  678 */         unplanInAndOutStorage.setXxmes_upiss_part(part);
/*  679 */         unplanInAndOutStorage.setXxmes_upiss_qty(sumQty);
/*  680 */         unplanInAndOutStorage.setXxmes_upiss_loc(code);
/*  681 */         unplanInAndOutStorage.setXxmes_upiss_dir("2");
/*  682 */         unplanInAndOutStorage.setXxmes_upiss_date(date);
/*  683 */         unplanInAndOutStorage.setXxmes_upiss_qadread("0");
/*  684 */         unplanInAndOutStorage.setXxmes_upiss_site(this.siteDomainCode);
/*  685 */         unplanInAndOutStorage.setXxmes_upiss_domain(this.siteDomainCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  692 */         this.daoShared.saveObject(unplanInAndOutStorage);
/*  693 */         num++;
/*      */       } 
/*      */       
/*  696 */       if (num > 0) {
/*  697 */         messCtrlHave.setXxmes_table_qty(new BigDecimal(num));
/*  698 */         this.daoShared.saveObject(messCtrlHave);
/*      */       } 
/*      */       
/*  701 */       if (mfg) {
/*  702 */         String strN = getSeq();
/*      */ 
/*      */ 
/*      */         
/*  706 */         QADMessCtrl messCtrlNot = insertQADMessCtrl(
/*  707 */             "XXMES_LY_DET", strN);
/*      */         
/*  709 */         num = 0;
/*      */         
/*  711 */         List<WmsUnplannedWarehousingItem> list2 = this.dao
/*  712 */           .getObjectList("from WmsUnplannedWarehousingItem item where item.is_sync = 1 and item.unplanned_warehousing_id.reason_code is null ");
/*      */         
/*  714 */         Map<Object, Object> mapN = new HashMap<Object, Object>();
/*  715 */         for (WmsUnplannedWarehousingItem item : list2) {
/*  716 */           String part = item.getBox_id().getPart().getId();
/*      */           
/*  718 */           BigDecimal qty = item.getQty();
/*  719 */           String code = item.getLocation().getCode();
/*  720 */           Date date = item.getUnplanned_warehousing_id().getDate();
/*  721 */           String remark = item.getUnplanned_warehousing_id()
/*  722 */             .getRemark();
/*      */ 
/*      */           
/*  725 */           String sign = String.valueOf(part) + "," + code + "," + date + "," + "," + 
/*  726 */             item.getId() + ",";
/*  727 */           if (mapN.get(sign) == null) {
/*  728 */             mapN.put(sign, qty);
/*      */           } else {
/*  730 */             BigDecimal sumqty = (BigDecimal)mapN.get(sign);
/*  731 */             if (sumqty != null && !sumqty.equals("")) {
/*  732 */               mapN.put(sign, sumqty.add(qty));
/*      */             } else {
/*  734 */               mapN.put(sign, sumqty);
/*      */             } 
/*      */           } 
/*  737 */           item.setIs_sync(YesNo.YES);
/*  738 */           item.setIs_sync_date(new Date());
/*  739 */           this.dao.updateObject(item);
/*      */         } 
/*      */         
/*  742 */         Iterator<String> iterator2 = mapN.keySet().iterator();
/*  743 */         while (iterator2.hasNext()) {
/*  744 */           String key = iterator2.next();
/*  745 */           BigDecimal qty = (BigDecimal)map.get(key);
/*      */           
/*  747 */           String[] arrays = key.split(",");
/*  748 */           String part = arrays[0];
/*  749 */           String code = arrays[1];
/*  750 */           String dateNew = arrays[2];
/*  751 */           Integer unid = Integer.valueOf(Integer.parseInt(arrays[3]));
/*  752 */           Date date = format2.parse(dateNew);
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
/*  773 */           DepartmentReceiveParts departmentReceiveParts = new DepartmentReceiveParts();
/*      */           
/*  775 */           departmentReceiveParts.setXxmes_ly_seq(strN);
/*  776 */           departmentReceiveParts.setXxmes_ly_part(part);
/*  777 */           departmentReceiveParts.setXxmes_ly_qty(qty);
/*  778 */           departmentReceiveParts.setXxmes_ly_loc(code);
/*      */ 
/*      */           
/*  781 */           departmentReceiveParts.setXxmes_ly_date(date);
/*  782 */           departmentReceiveParts.setXxmes_ly_qadread("0");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  789 */           departmentReceiveParts.setXxmes_ly_site(this.siteDomainCode);
/*  790 */           departmentReceiveParts.setXxmes_ly_domain(this.siteDomainCode);
/*      */           
/*  792 */           this.daoShared.saveObject(departmentReceiveParts);
/*  793 */           num++;
/*      */         } 
/*      */         
/*  796 */         if (num > 0) {
/*  797 */           messCtrlNot.setXxmes_table_qty(new BigDecimal(num));
/*  798 */           this.daoShared.saveObject(messCtrlNot);
/*      */         } 
/*      */       } 
/*  801 */     } catch (Exception e) {
/*  802 */       e.printStackTrace();
/*  803 */       insertSystemLog("DeliverMinuteSyncJob", "unplannedIn", 
/*  804 */           e.getMessage(), "1");
/*      */     } 
/*      */     
/*  807 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer unplannedOut() {
/*  816 */     int num = 0;
/*      */     
/*      */     try {
/*  819 */       String strH = getSeq();
/*      */ 
/*      */ 
/*      */       
/*  823 */       QADMessCtrl messCtrlHave = insertQADMessCtrl(
/*  824 */           "XXMES_UPISS_DET", strH);
/*      */ 
/*      */ 
/*      */       
/*  828 */       List<WmsPlanToGoOutSub> list1 = this.dao
/*  829 */         .getObjectList("from WmsPlanToGoOutSub sub where sub.is_sync <> 0  or sub.is_sync is null and sub.unplanned_outbound_detial_id.unplanned_outbound_id.reason_code is not null ");
/*      */       
/*  831 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/*  832 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*  833 */       for (WmsPlanToGoOutSub sub : list1) {
/*  834 */         String part = sub.getBox_id().getPart().getId();
/*      */         
/*  836 */         StorageLocation location = sub.getBox_id().getLocation();
/*      */         
/*  838 */         BigDecimal qty = sub.getBox_id().getNumber();
/*  839 */         Date date = sub.getUnplanned_outbound_detial_id()
/*  840 */           .getUnplanned_outbound_id().getDate();
/*  841 */         String dept = sub.getUnplanned_outbound_detial_id()
/*  842 */           .getUnplanned_outbound_id().getReason_code()
/*  843 */           .getInstructions();
/*  844 */         String project = sub.getUnplanned_outbound_detial_id()
/*  845 */           .getUnplanned_outbound_id().getReason_code()
/*  846 */           .getExpenses_course();
/*      */ 
/*      */         
/*  849 */         String code = location.getCode();
/*  850 */         String lotSer = sub.getBox_id().getLot().getId();
/*  851 */         String userName = sub.getUnplanned_outbound_detial_id()
/*  852 */           .getUnplanned_outbound_id().getReason_code().getUser()
/*  853 */           .getName();
/*  854 */         String workCenter = sub.getUnplanned_outbound_detial_id()
/*  855 */           .getUnplanned_outbound_id().getReason_code()
/*  856 */           .getDepartment_cost();
/*      */         
/*  858 */         String sign = String.valueOf(project) + "," + dept + "," + date + "," + code + 
/*  859 */           "," + part + "," + sub.getId() + "," + userName + "," + 
/*  860 */           workCenter + "," + lotSer;
/*  861 */         if (map.get(sign) == null) {
/*  862 */           map.put(sign, qty);
/*      */         } else {
/*  864 */           BigDecimal sumqty = (BigDecimal)map.get(sign);
/*  865 */           if (sumqty != null && !sumqty.equals("")) {
/*  866 */             map.put(sign, sumqty.add(qty));
/*      */           } else {
/*  868 */             map.put(sign, sumqty);
/*      */           } 
/*      */         } 
/*  871 */         sub.setIs_sync(YesNo.YES);
/*  872 */         sub.setIs_sync_date(new Date());
/*  873 */         this.dao.updateObject(sub);
/*      */       } 
/*      */       
/*  876 */       Iterator<String> iterator2 = map.keySet().iterator();
/*  877 */       while (iterator2.hasNext()) {
/*  878 */         String key = iterator2.next();
/*  879 */         BigDecimal qty = (BigDecimal)map.get(key);
/*      */         
/*  881 */         String[] arrays = key.split(",");
/*  882 */         String project = arrays[0];
/*  883 */         String dept = arrays[1];
/*  884 */         String dateNew = arrays[2];
/*  885 */         String code = arrays[3];
/*  886 */         String part = arrays[4];
/*  887 */         Integer subId = Integer.valueOf(Integer.parseInt(arrays[5]));
/*  888 */         Date date = format2.parse(dateNew);
/*      */         
/*  890 */         String users = arrays[6];
/*  891 */         String workcenter = arrays[7];
/*  892 */         String boxCode = arrays[8];
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
/*  911 */         UnplanInAndOutStorage unplanInAndOutStorage = new UnplanInAndOutStorage();
/*      */         
/*  913 */         unplanInAndOutStorage.setXxmes_upiss_createur(users);
/*  914 */         unplanInAndOutStorage.setXxmes_upiss_reason(dept);
/*  915 */         unplanInAndOutStorage.setXxmes_upiss_workcenter(workcenter);
/*  916 */         unplanInAndOutStorage.setXxmes_upiss_char1(project);
/*  917 */         unplanInAndOutStorage.setXxmes_upiss_seq(strH);
/*  918 */         unplanInAndOutStorage.setXxmes_upiss_part(part);
/*  919 */         unplanInAndOutStorage.setXxmes_upiss_boxcode(boxCode);
/*  920 */         unplanInAndOutStorage.setXxmes_upiss_qty(qty);
/*  921 */         unplanInAndOutStorage.setXxmes_upiss_loc(code);
/*  922 */         unplanInAndOutStorage.setXxmes_upiss_dir("1");
/*  923 */         unplanInAndOutStorage.setXxmes_upiss_date(date);
/*  924 */         unplanInAndOutStorage.setXxmes_upiss_qadread("0");
/*  925 */         unplanInAndOutStorage.setXxmes_upiss_site(this.siteDomainCode);
/*  926 */         unplanInAndOutStorage.setXxmes_upiss_domain(this.siteDomainCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  933 */         this.daoShared.saveObject(unplanInAndOutStorage);
/*  934 */         num++;
/*      */       } 
/*      */       
/*  937 */       if (num > 0) {
/*  938 */         messCtrlHave.setXxmes_table_qty(new BigDecimal(num));
/*  939 */         this.daoShared.saveObject(messCtrlHave);
/*      */       } 
/*      */ 
/*      */       
/*  943 */       String strN = getSeq();
/*      */ 
/*      */ 
/*      */       
/*  947 */       QADMessCtrl messCtrlNot = insertQADMessCtrl("XXMES_LY_DET", 
/*  948 */           strN);
/*      */       
/*  950 */       num = 0;
/*      */ 
/*      */ 
/*      */       
/*  954 */       List<WmsPlanToGoOutSub> list2 = this.dao
/*  955 */         .getObjectList("from WmsPlanToGoOutSub sub where sub.is_sync = 1 and sub.unplanned_outbound_detial_id.unplanned_outbound_id.reason_code is null ");
/*      */       
/*  957 */       Map<Object, Object> mapN = new HashMap<Object, Object>();
/*  958 */       for (WmsPlanToGoOutSub sub : list2) {
/*  959 */         String part = sub.getBox_id().getPart().getId();
/*  960 */         StorageLocation location = sub.getLocation();
/*  961 */         BigDecimal qty = sub.getQty().multiply(new BigDecimal(-1));
/*  962 */         Date date = sub.getUnplanned_outbound_detial_id()
/*  963 */           .getUnplanned_outbound_id().getDate();
/*      */         
/*  965 */         String code = getStoreRoom(location);
/*  966 */         String lotSer = sub.getBox_id().getLot().getId();
/*  967 */         String sign = date + "," + code + "," + part + "," + 
/*  968 */           sub.getId() + "," + lotSer;
/*  969 */         if (mapN.get(sign) == null) {
/*  970 */           mapN.put(sign, qty);
/*      */         } else {
/*  972 */           BigDecimal sumqty = (BigDecimal)mapN.get(sign);
/*  973 */           if (sumqty != null && !sumqty.equals("")) {
/*  974 */             mapN.put(sign, sumqty.add(qty));
/*      */           } else {
/*  976 */             mapN.put(sign, sumqty);
/*      */           } 
/*      */         } 
/*  979 */         sub.setIs_sync(YesNo.YES);
/*  980 */         sub.setIs_sync_date(new Date());
/*  981 */         this.dao.updateObject(sub);
/*      */       } 
/*      */       
/*  984 */       Iterator<String> iterator3 = mapN.keySet().iterator();
/*  985 */       while (iterator3.hasNext()) {
/*  986 */         String key = iterator3.next();
/*  987 */         BigDecimal qty = (BigDecimal)mapN.get(key);
/*      */         
/*  989 */         String[] arrays = key.split(",");
/*  990 */         String dateNew = arrays[0];
/*  991 */         String code = arrays[1];
/*  992 */         String part = arrays[2];
/*  993 */         Integer subid = Integer.valueOf(Integer.parseInt(arrays[3]));
/*  994 */         String boxCode = arrays[4];
/*  995 */         Date date = format2.parse(dateNew);
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
/* 1012 */         DepartmentReceiveParts departmentReceiveParts = new DepartmentReceiveParts();
/* 1013 */         departmentReceiveParts.setXxmes_ly_det_id(subid);
/* 1014 */         departmentReceiveParts.setXxmes_ly_seq(strN);
/* 1015 */         departmentReceiveParts.setXxmes_ly_part(part);
/* 1016 */         departmentReceiveParts.setXxmes_ly_qty(qty);
/* 1017 */         departmentReceiveParts.setXxmes_ly_loc(code);
/* 1018 */         departmentReceiveParts.setXxmes_ly_boxcode(boxCode);
/*      */ 
/*      */         
/* 1021 */         departmentReceiveParts.setXxmes_ly_date(date);
/* 1022 */         departmentReceiveParts.setXxmes_ly_qadread("0");
/* 1023 */         departmentReceiveParts.setXxmes_ly_mesread("1");
/* 1024 */         departmentReceiveParts.setXxmes_ly_portalread("0");
/* 1025 */         departmentReceiveParts.setXxmes_ly_ediread("0");
/* 1026 */         departmentReceiveParts.setXxmes_ly_createdt(new Date());
/* 1027 */         departmentReceiveParts.setXxmes_ly_updatedt(new Date());
/*      */         
/* 1029 */         departmentReceiveParts.setXxmes_ly_site(this.siteDomainCode);
/* 1030 */         departmentReceiveParts.setXxmes_ly_domain(this.siteDomainCode);
/*      */         
/* 1032 */         this.daoShared.saveObject(departmentReceiveParts);
/* 1033 */         num++;
/*      */       } 
/*      */       
/* 1036 */       if (num > 0) {
/* 1037 */         messCtrlNot.setXxmes_table_qty(new BigDecimal(num));
/* 1038 */         this.daoShared.saveObject(messCtrlNot);
/*      */       } 
/* 1040 */     } catch (Exception e) {
/* 1041 */       insertSystemLog("DeliverMinuteSyncJob", "unplannedOut", 
/* 1042 */           e.getMessage(), "1");
/*      */     } 
/*      */     
/* 1045 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer inventory() {
/* 1054 */     int num = 0;
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
/*      */     try {
/* 1066 */       String str = getSeq();
/*      */       
/* 1068 */       QADMessCtrl messCtrl = insertQADMessCtrl("XXMES_TAG_DET", str);
/*      */ 
/*      */       
/* 1071 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 1072 */       List<StockingRecord> list = this.dao
/* 1073 */         .getObjectList("from StockingRecord sr where (sr.is_sync <> 0  or sr.is_sync is null) and sr.stocking_id.confirmStatus=0 and sr.type=2 and sr.status=1 ");
/*      */ 
/*      */       
/* 1076 */       for (StockingRecord record : list) {
/* 1077 */         String part = record.getBox().getPart().getId();
/* 1078 */         StorageLocation location = record.getLocation();
/* 1079 */         String lot = record.getBox().getLot().getId();
/* 1080 */         BigDecimal qty_cnt = record.getStocking_qty();
/* 1081 */         BigDecimal differences = record.getDifferences();
/* 1082 */         if (differences.compareTo(new BigDecimal(0)) == 1) {
/* 1083 */           location = record.getLocation();
/*      */         } else {
/* 1085 */           location = record.getBox().getLocation();
/*      */         } 
/* 1087 */         Date date = record.getCreateDate();
/*      */ 
/*      */         
/* 1090 */         String code = location.getCode();
/*      */         
/* 1092 */         String sign = String.valueOf(code) + "," + part + "," + date + "," + 
/* 1093 */           record.getId() + "," + lot;
/* 1094 */         String strQty = "";
/* 1095 */         if (map.get(sign) == null) {
/* 1096 */           strQty = String.valueOf(strQty) + qty_cnt + "," + differences + ",";
/* 1097 */           map.put(sign, strQty);
/*      */         } else {
/* 1099 */           String new_strQty = "";
/* 1100 */           String sumqtyStr = (String)map.get(sign);
/* 1101 */           if (sumqtyStr != null && !sumqtyStr.equals("")) {
/* 1102 */             String[] arrays = sumqtyStr.split(",");
/* 1103 */             String qty_cnt_new = arrays[0];
/* 1104 */             String differences_new = arrays[1];
/*      */             
/* 1106 */             new_strQty = String.valueOf(new_strQty) + (
/* 1107 */               new BigDecimal(qty_cnt_new)).add(qty_cnt) + 
/* 1108 */               "," + (
/* 1109 */               new BigDecimal(differences_new))
/* 1110 */               .add(differences) + "," + 
/* 1111 */               record.getId() + ",";
/*      */             
/* 1113 */             map.put(sign, new_strQty);
/*      */           } else {
/* 1115 */             String strQty_add = qty_cnt + "," + differences + ",";
/*      */             
/* 1117 */             map.put(sign, strQty_add);
/*      */           } 
/*      */         } 
/*      */         
/* 1121 */         record.setIs_sync(YesNo.YES);
/* 1122 */         record.setIs_sync_date(new Date());
/* 1123 */         this.dao.updateObject(record);
/*      */       } 
/*      */       
/* 1126 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/* 1127 */       Iterator<String> iterator = map.keySet().iterator();
/* 1128 */       while (iterator.hasNext()) {
/* 1129 */         String key = iterator.next();
/* 1130 */         String sumqyt_str = (String)map.get(key);
/*      */         
/* 1132 */         String[] arrays = key.split(",");
/* 1133 */         String code = arrays[0];
/* 1134 */         String part = arrays[1];
/* 1135 */         String datenew = arrays[2];
/* 1136 */         Integer rid = Integer.valueOf(Integer.parseInt(arrays[3]));
/* 1137 */         String lotSer = arrays[4];
/*      */         
/* 1139 */         String[] array = sumqyt_str.split(",");
/* 1140 */         String qty_cnt = array[0];
/* 1141 */         String differences = array[1];
/*      */         
/* 1143 */         Date date = format2.parse(datenew);
/*      */         
/* 1145 */         MakeAnInventory makeAnInventory = new MakeAnInventory();
/* 1146 */         makeAnInventory.setXxmes_tag_det_id(rid);
/* 1147 */         makeAnInventory.setXxmes_tag_seq(str);
/* 1148 */         makeAnInventory.setXxmes_tag_part(part);
/* 1149 */         makeAnInventory.setXxmes_tag_loc(code);
/* 1150 */         makeAnInventory.setXxmes_tag_qty_cnt(new BigDecimal(qty_cnt));
/* 1151 */         makeAnInventory
/* 1152 */           .setXxmes_tag_qty_var(new BigDecimal(differences));
/* 1153 */         makeAnInventory.setXxmes_tag_date(date);
/* 1154 */         makeAnInventory.setXxmes_tag_qadread("0");
/* 1155 */         makeAnInventory.setXxmes_tag_mesread("1");
/* 1156 */         makeAnInventory.setXxmes_tag_createdt(new Date());
/*      */         
/* 1158 */         makeAnInventory.setXxmes_tag_site(this.siteDomainCode);
/* 1159 */         makeAnInventory.setXxmes_tag_domain(this.siteDomainCode);
/* 1160 */         makeAnInventory.setXxmes_tag_boxcode(lotSer);
/*      */         
/* 1162 */         this.daoShared.saveObject(makeAnInventory);
/*      */         
/* 1164 */         num++;
/*      */       } 
/*      */       
/* 1167 */       if (num > 0) {
/* 1168 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/* 1169 */         this.daoShared.saveObject(messCtrl);
/*      */       } 
/* 1171 */     } catch (Exception e) {
/* 1172 */       insertSystemLog("DeliverMinuteSyncJob", "inventory", 
/* 1173 */           e.getMessage(), "1");
/*      */     } 
/*      */     
/* 1176 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer buckleMaterial() {
/* 1185 */     int num = 0;
/*      */     
/*      */     try {
/* 1188 */       String str = getSeq();
/*      */       
/* 1190 */       QADMessCtrl messCtrl = 
/* 1191 */         insertQADMessCtrl("SHMES_WOIS_DET", str);
/*      */ 
/*      */       
/* 1194 */       String str_seq = getSeq();
/*      */       
/* 1196 */       QADMessCtrl messCtrl_buckleMaterial = insertQADMessCtrl(
/* 1197 */           "SHMES_WORC_DET", str_seq);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1206 */       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*      */ 
/*      */ 
/*      */       
/* 1210 */       List<ProduceBuckleMaterial> list = this.dao
/* 1211 */         .getObjectList("from ProduceBuckleMaterial pm where pm.is_sync is null or pm.is_sync<>0 order by pm.id ");
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
/* 1236 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 1237 */       for (ProduceBuckleMaterial material : list) {
/* 1238 */         Date date = material.getDate();
/* 1239 */         String part = material.getPart().getId();
/* 1240 */         StorageLocation location = material.getLocation();
/* 1241 */         BigDecimal qty = material.getQty();
/* 1242 */         String assembly = material.getAssembly().getId();
/* 1243 */         Integer growth = material.getGrowth();
/* 1244 */         String code = "";
/* 1245 */         if (location == null) {
/* 1246 */           code = "";
/*      */         } else {
/*      */           
/* 1249 */           code = location.getCode();
/*      */         } 
/*      */         
/* 1252 */         String sign = String.valueOf(assembly) + "," + part + "," + code + "," + 
/* 1253 */           format.format(date) + "," + material.getId() + "," + 
/* 1254 */           growth + ",";
/* 1255 */         if (map.get(sign) == null) {
/* 1256 */           map.put(sign, qty);
/*      */         } else {
/* 1258 */           BigDecimal sumqty = (BigDecimal)map.get(sign);
/* 1259 */           if (sumqty != null && !sumqty.equals("")) {
/* 1260 */             map.put(sign, sumqty.add(qty));
/*      */           } else {
/* 1262 */             map.put(sign, sumqty);
/*      */           } 
/*      */         } 
/* 1265 */         material.setIs_sync(YesNo.YES);
/* 1266 */         material.setIs_sync_date(new Date());
/* 1267 */         this.dao.updateObject(material);
/*      */       } 
/*      */       
/* 1270 */       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
/* 1271 */       Iterator<String> iterator = map.keySet().iterator();
/* 1272 */       while (iterator.hasNext()) {
/* 1273 */         String key = iterator.next();
/* 1274 */         BigDecimal sumqyt = (BigDecimal)map.get(key);
/*      */         
/* 1276 */         String[] arrays = key.split(",");
/* 1277 */         String assembly = arrays[0];
/* 1278 */         String part = arrays[1];
/* 1279 */         String code = arrays[2];
/* 1280 */         String datenew = arrays[3];
/* 1281 */         Integer mid = Integer.valueOf(Integer.parseInt(arrays[4]));
/* 1282 */         Integer growth = Integer.valueOf(Integer.parseInt(arrays[5]));
/* 1283 */         Date date = format2.parse(datenew);
/*      */ 
/*      */         
/* 1286 */         WorkOrderBackFlushInStroage stroage = new WorkOrderBackFlushInStroage();
/* 1287 */         stroage.setShmes_worc_seq(str);
/* 1288 */         stroage.setShmes_worc_item(assembly);
/* 1289 */         stroage.setShmes_worc_det_id(growth);
/* 1290 */         stroage.setShmes_rworc_id(mid.toString());
/* 1291 */         stroage.setShmes_worc_loc("CK004");
/* 1292 */         stroage.setShmes_worc_qty(sumqyt);
/* 1293 */         stroage.setShmes_worc_date(date);
/* 1294 */         stroage.setShmes_worc_qadread("0");
/* 1295 */         stroage.setShmes_worc_mesread("1");
/* 1296 */         stroage.setShmes_worc_portalread("0");
/* 1297 */         stroage.setShmes_worc_ediread("0");
/* 1298 */         stroage.setShmes_worc_createdt(new Date());
/*      */         
/* 1300 */         stroage.setShmes_worc_site(this.siteDomainCode);
/* 1301 */         stroage.setShmes_worc_domain(this.siteDomainCode);
/* 1302 */         stroage.setShmes_rworc_note("1");
/* 1303 */         if (code.trim().length() == 0) {
/* 1304 */           this.daoShared.saveObject(stroage);
/*      */         }
/*      */         
/* 1307 */         WorkOrderBuckleMaterial buckleMaterial = new WorkOrderBuckleMaterial();
/* 1308 */         buckleMaterial.setShmes_wois_det_id(mid);
/* 1309 */         buckleMaterial.setShmes_wois_seq(str);
/* 1310 */         buckleMaterial.setShmes_wois_item(assembly);
/* 1311 */         buckleMaterial.setShmes_wois_part(part);
/* 1312 */         buckleMaterial.setShmes_wois_loc(code);
/* 1313 */         buckleMaterial.setShmes_wois_qty(sumqyt);
/* 1314 */         buckleMaterial.setShmes_wois_date(date);
/* 1315 */         buckleMaterial.setShmes_wois_qadread("0");
/* 1316 */         buckleMaterial.setShmes_wois_mesread("1");
/* 1317 */         buckleMaterial.setShmes_wois_portalread("0");
/* 1318 */         buckleMaterial.setShmes_wois_ediread("0");
/* 1319 */         buckleMaterial.setShmes_wois_createdt(new Date());
/* 1320 */         buckleMaterial.setShmes_wois_fk(growth.toString());
/*      */ 
/*      */         
/* 1323 */         buckleMaterial.setShmes_worc_site(this.siteDomainCode);
/* 1324 */         buckleMaterial.setShmes_worc_domain(this.siteDomainCode);
/* 1325 */         this.daoShared.saveObject(buckleMaterial);
/* 1326 */         num++;
/*      */       } 
/*      */       
/* 1329 */       if (num > 0) {
/* 1330 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/* 1331 */         this.daoShared.saveObject(messCtrl);
/*      */         
/* 1333 */         messCtrl_buckleMaterial.setXxmes_table_qty(new BigDecimal(num));
/* 1334 */         this.daoShared.saveObject(messCtrl_buckleMaterial);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1341 */     catch (Exception e) {
/* 1342 */       insertSystemLog("DeliverMinuteSyncJob", "buckleMaterial", 
/* 1343 */           e.getMessage(), "1");
/* 1344 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1347 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer workOrder() {
/* 1356 */     int num = 0;
/*      */     
/*      */     try {
/* 1359 */       String str = getSeq();
/*      */       
/* 1361 */       QADMessCtrl messCtrl = 
/* 1362 */         insertQADMessCtrl("XXMES_SHIP_DET", str);
/*      */ 
/*      */       
/* 1365 */       Map<Object, Object> map = new HashMap<Object, Object>();
/*      */ 
/*      */       
/* 1368 */       List<SalesWorkorder> list = this.dao
/* 1369 */         .getObjectList("from SalesWorkorder sw where sw.isSync<>0 and sw.shipId.shPrint=0");
/* 1370 */       for (SalesWorkorder salesWorkorder : list) {
/* 1371 */         Integer orderType = salesWorkorder.getShipId().getType();
/* 1372 */         if (orderType.intValue() == 1) {
/* 1373 */           SalesOrderItem salesOrderItem = salesWorkorder
/* 1374 */             .getShipItemId().getSoipitemId();
/* 1375 */           String str1 = salesWorkorder.getShipItemId()
/* 1376 */             .getSoipitemId().getSoId().getCustCode();
/* 1377 */           String str2 = (salesWorkorder.getShipItemId()
/* 1378 */             .getSoipitemId().getSoId().getCustAddress() == null) ? "" : 
/* 1379 */             salesWorkorder.getShipItemId().getSoipitemId()
/* 1380 */             .getSoId().getCustAddress();
/* 1381 */           String str3 = salesWorkorder.getShipItemId()
/* 1382 */             .getSoipitemId().getItemNumber().getId();
/* 1383 */           BigDecimal bigDecimal = salesWorkorder.getCount();
/* 1384 */           StorageLocation storageLocation = salesWorkorder.getLocation();
/* 1385 */           Date date1 = salesWorkorder.getOutDate();
/* 1386 */           String str4 = salesWorkorder.getShipId().getCode();
/* 1387 */           String str5 = salesWorkorder.getLotSer().getId();
/* 1388 */           String str6 = salesWorkorder.getShipItemId()
/* 1389 */             .getSoipitemId().getLine();
/* 1390 */           SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
/* 1391 */               "yyyy-MM-dd");
/* 1392 */           SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
/* 1393 */               "yyyy-MM-dd hh:mm:ss");
/*      */           
/* 1395 */           String str7 = orderType + "," + 
/* 1396 */             salesOrderItem.getSoipNumber() + "," + 
/* 1397 */             str1 + "," + str2 + "," + str3 + 
/* 1398 */             "," + storageLocation.getCode() + ',' + 
/* 1399 */             simpleDateFormat1.format(date1) + "," + simpleDateFormat2.format(date1) + 
/* 1400 */             "," + str4 + "," + str5 + "," + str6 + 
/* 1401 */             ",";
/* 1402 */           if (map.get(str7) == null) {
/* 1403 */             map.put(str7, bigDecimal);
/*      */           } else {
/* 1405 */             BigDecimal sumqty = (BigDecimal)map.get(str7);
/* 1406 */             if (sumqty != null && !sumqty.equals("")) {
/* 1407 */               map.put(str7, sumqty.add(bigDecimal));
/*      */             } else {
/* 1409 */               map.put(str7, sumqty);
/*      */             } 
/* 1411 */           }  salesWorkorder.setIsSync(YesNo.YES);
/* 1412 */           this.dao.updateObject(salesWorkorder); continue;
/*      */         } 
/* 1414 */         String planNumber = salesWorkorder.getShipItemId()
/* 1415 */           .getCustomerPlanId().getPlanNumbers();
/* 1416 */         String customerCode = salesWorkorder.getShipItemId()
/* 1417 */           .getCustomerPlanId().getCustomer().getCode();
/* 1418 */         String customerAddress = (salesWorkorder.getShipItemId()
/* 1419 */           .getCustomerPlanId().getCustomerAddress() == null) ? "" : 
/* 1420 */           salesWorkorder.getShipItemId()
/* 1421 */           .getCustomerPlanId().getCustomerAddress();
/* 1422 */         String part = salesWorkorder.getShipItemId()
/* 1423 */           .getCustomerPlanId().getWmspart().getId();
/* 1424 */         BigDecimal qty = salesWorkorder.getCount();
/* 1425 */         StorageLocation location = salesWorkorder.getLocation();
/* 1426 */         Date date = salesWorkorder.getOutDate();
/* 1427 */         String workOrder = salesWorkorder.getShipId().getCode();
/* 1428 */         String boxCode = salesWorkorder.getLotSer().getId();
/* 1429 */         String line = salesWorkorder.getShipItemId()
/* 1430 */           .getCustomerPlanId().getLine();
/* 1431 */         SimpleDateFormat format1 = new SimpleDateFormat(
/* 1432 */             "yyyy-MM-dd");
/* 1433 */         SimpleDateFormat format2 = new SimpleDateFormat(
/* 1434 */             "yyyy-MM-dd hh:mm:ss");
/*      */         
/* 1436 */         String sign = orderType + "," + planNumber + "," + 
/* 1437 */           customerCode + "," + customerAddress + "," + part + 
/* 1438 */           "," + location.getCode() + ',' + 
/* 1439 */           format1.format(date) + "," + format2.format(date) + 
/* 1440 */           "," + workOrder + "," + boxCode + "," + line + 
/* 1441 */           ",";
/* 1442 */         if (map.get(sign) == null) {
/* 1443 */           map.put(sign, qty);
/*      */         } else {
/* 1445 */           BigDecimal sumqty = (BigDecimal)map.get(sign);
/* 1446 */           if (sumqty != null && !sumqty.equals("")) {
/* 1447 */             map.put(sign, sumqty.add(qty));
/*      */           } else {
/* 1449 */             map.put(sign, sumqty);
/*      */           } 
/* 1451 */         }  salesWorkorder.setIsSync(YesNo.YES);
/* 1452 */         this.dao.updateObject(salesWorkorder);
/*      */       } 
/*      */       
/* 1455 */       Iterator<String> iterator2 = map.keySet().iterator();
/* 1456 */       while (iterator2.hasNext()) {
/* 1457 */         String key = iterator2.next();
/* 1458 */         BigDecimal qty = (BigDecimal)map.get(key);
/* 1459 */         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
/* 1460 */         SimpleDateFormat format2 = new SimpleDateFormat(
/* 1461 */             "yyyy-MM-dd hh:mm:ss");
/* 1462 */         String[] arrays = key.split(",", -1);
/* 1463 */         String orderType = arrays[0];
/* 1464 */         String shipOrderId = arrays[1];
/* 1465 */         String customerCode = arrays[2];
/* 1466 */         String customerAddress = arrays[3];
/* 1467 */         String part = arrays[4];
/* 1468 */         String localtionCode = arrays[5];
/* 1469 */         String date = arrays[6];
/* 1470 */         String dateNew = arrays[7];
/* 1471 */         String workOrder = arrays[8];
/* 1472 */         String boxCode = arrays[9];
/* 1473 */         String line = arrays[10];
/* 1474 */         Date datetimeRQ = format1.parse(date);
/* 1475 */         Date datetimeSJ = format2.parse(dateNew);
/*      */         
/* 1477 */         ProductOutStorage productOutStorage = new ProductOutStorage();
/* 1478 */         productOutStorage.setXxmes_ship_seq(str);
/* 1479 */         productOutStorage.setXxmes_ship_nbr(shipOrderId);
/* 1480 */         productOutStorage.setXxmes_ship_cust(customerCode);
/* 1481 */         productOutStorage.setXxmes_ship_shipto(customerAddress);
/* 1482 */         productOutStorage.setXxmes_ship_part(part);
/* 1483 */         productOutStorage.setXxmes_ship_qty(qty);
/* 1484 */         productOutStorage.setXxmes_ship_loc(localtionCode);
/* 1485 */         productOutStorage.setXxmes_ship_date(datetimeRQ);
/* 1486 */         productOutStorage.setXxmes_ship_time(datetimeSJ);
/*      */         
/* 1488 */         productOutStorage.setXxmes_ship_boxcode(boxCode);
/* 1489 */         productOutStorage.setXxmes_ship_line(Integer.valueOf(Integer.parseInt(line)));
/* 1490 */         if (Integer.parseInt(orderType) == 2) {
/* 1491 */           productOutStorage.setXxmes_ship_type("S");
/*      */         }
/* 1493 */         productOutStorage.setXxmes_ship_site(this.siteDomainCode);
/* 1494 */         productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
/* 1495 */         productOutStorage.setXxmes_ship_qadread("0");
/* 1496 */         productOutStorage.setXxmes_ship_mesread("1");
/* 1497 */         productOutStorage.setXxmes_ship_portalread("0");
/* 1498 */         productOutStorage.setXxmes_ship_ediread("0");
/* 1499 */         this.daoShared.saveObject(productOutStorage);
/* 1500 */         num++;
/*      */       } 
/* 1502 */       if (num > 0) {
/* 1503 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/* 1504 */         this.daoShared.saveObject(messCtrl);
/*      */       } 
/* 1506 */     } catch (Exception e) {
/* 1507 */       insertSystemLog("DeliverMinuteSyncJob", "workOrderOutLocation", 
/* 1508 */           e.getMessage(), "1");
/* 1509 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1512 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Integer customerReturnsItem() {
/* 1521 */     int num = 0;
/*      */     
/*      */     try {
/* 1524 */       String str = getSeq();
/*      */       
/* 1526 */       QADMessCtrl messCtrl = 
/* 1527 */         insertQADMessCtrl("XXMES_SHIP_DET", str);
/* 1528 */       SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
/* 1529 */       SimpleDateFormat format2 = new SimpleDateFormat(
/* 1530 */           "yyyy-MM-dd hh:mm:ss");
/*      */       
/* 1532 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 1533 */       List<CustomerReturnItem> list = this.dao
/* 1534 */         .getObjectList("from CustomerReturnItem cri where (cri.isSync<>0 or cri.isSync is null) and cri.customerreturns.printStatus=0");
/* 1535 */       for (CustomerReturnItem customerReturnItem : list) {
/* 1536 */         ProductOutStorage productOutStorage = new ProductOutStorage();
/* 1537 */         productOutStorage.setXxmes_ship_seq(str);
/* 1538 */         productOutStorage.setXxmes_ship_nbr(customerReturnItem
/* 1539 */             .getCustomerreturns().getReturnNumber());
/* 1540 */         productOutStorage.setXxmes_ship_cust(customerReturnItem
/* 1541 */             .getCustomerreturns().getBasicCustomer().getCode());
/* 1542 */         productOutStorage.setXxmes_ship_shipto(customerReturnItem
/* 1543 */             .getCustomerreturns().getBasicCustomer().getAddress());
/* 1544 */         productOutStorage.setXxmes_ship_part(customerReturnItem
/* 1545 */             .getPart().getId());
/* 1546 */         BigDecimal qty = new BigDecimal(customerReturnItem.getQty().intValue());
/* 1547 */         BigDecimal qty1 = qty.multiply(new BigDecimal(-1));
/* 1548 */         productOutStorage.setXxmes_ship_qty(qty1);
/* 1549 */         productOutStorage.setXxmes_ship_loc(customerReturnItem
/* 1550 */             .getReturnStorage());
/* 1551 */         productOutStorage.setXxmes_ship_date(format1.parse(format1
/* 1552 */               .format(customerReturnItem.getCustomerreturns()
/* 1553 */                 .getReturnDate())));
/* 1554 */         productOutStorage.setXxmes_ship_time(format2.parse(format2
/* 1555 */               .format(customerReturnItem.getCustomerreturns()
/* 1556 */                 .getReturnDate())));
/*      */         
/* 1558 */         productOutStorage.setXxmes_ship_boxcode(customerReturnItem
/* 1559 */             .getBatchNumber());
/*      */ 
/*      */         
/* 1562 */         productOutStorage.setXxmes_ship_type("S");
/*      */         
/* 1564 */         productOutStorage.setXxmes_ship_site(this.siteDomainCode);
/* 1565 */         productOutStorage.setXxmes_ship_domain(this.siteDomainCode);
/* 1566 */         productOutStorage.setXxmes_ship_qadread("0");
/* 1567 */         productOutStorage.setXxmes_ship_mesread("1");
/* 1568 */         productOutStorage.setXxmes_ship_portalread("0");
/* 1569 */         productOutStorage.setXxmes_ship_ediread("0");
/* 1570 */         this.daoShared.saveObject(productOutStorage);
/*      */         
/* 1572 */         customerReturnItem.setIsSync(YesNo.YES);
/* 1573 */         this.dao.updateObject(customerReturnItem);
/* 1574 */         num++;
/*      */       } 
/* 1576 */       if (num > 0) {
/* 1577 */         messCtrl.setXxmes_table_qty(new BigDecimal(num));
/* 1578 */         this.daoShared.saveObject(messCtrl);
/*      */       }
/*      */     
/* 1581 */     } catch (Exception e) {
/* 1582 */       insertSystemLog("DeliverMinuteSyncJob", " customer_returns_item", 
/* 1583 */           e.getMessage(), "1");
/* 1584 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1587 */     return Integer.valueOf(num);
/*      */   }
/*      */ 
/*      */   
/*      */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 1592 */     SyncLog log = new SyncLog();
/* 1593 */     log.setSync_date(new Date());
/* 1594 */     log.setSync_content(content);
/* 1595 */     log.setSync_describe(sync_describe);
/* 1596 */     log.setSync_object(action);
/* 1597 */     log.setSync_results(syncResults);
/* 1598 */     this.dao.saveObject(log);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shipOrder() {
/*      */     try {
/* 1606 */       int num = 1;
/*      */       
/* 1608 */       List<PortalShipOrder> OrderList = this.dao
/* 1609 */         .getObjectList(" from PortalShipOrder shipOrder where shipOrder.isPrint=0 and (shipOrder.isSync !=1 or shipOrder.isSync is null)");
/* 1610 */       if (OrderList.size() != 0) {
/* 1611 */         for (PortalShipOrder portalShipOrder : OrderList) {
/*      */           
/* 1613 */           List<PortalShipOrderItem> orderItemList = this.dao
/* 1614 */             .getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + 
/* 1615 */               portalShipOrder.getId() + 
/* 1616 */               " and (item.isSync!=1 or item.isSync is null) ");
/* 1617 */           if (orderItemList.size() != 0) {
/* 1618 */             for (PortalShipOrderItem portalShipOrderItem : orderItemList) {
/*      */               
/* 1620 */               List<Box> boxList = this.dao
/* 1621 */                 .getObjectList(" from Box box where box.psoItem.id=" + 
/* 1622 */                   portalShipOrderItem.getId() + 
/* 1623 */                   " and (box.isSync=1 or box.isSync is null)");
/* 1624 */               if (boxList.size() != 0) {
/* 1625 */                 for (Box box : boxList) {
/*      */                   
/* 1627 */                   XbipddDet det = new XbipddDet();
/*      */                   
/* 1629 */                   if (portalShipOrder.getCreateType().equals(
/* 1630 */                       "NJIT_PO")) {
/* 1631 */                     det.setXbipdd_nbr(portalShipOrder
/* 1632 */                         .getCode());
/* 1633 */                     det.setXbipdd_line(Integer.valueOf(num));
/* 1634 */                     det.setXbipdd_ponbr(portalShipOrderItem
/* 1635 */                         .getPoipItem().getPoip_number()
/* 1636 */                         .getPoip_number());
/* 1637 */                     det.setXbipdd_poline(
/* 1638 */                         Integer.valueOf(Integer.parseInt(portalShipOrderItem
/* 1639 */                             .getPoipItem()
/* 1640 */                             .getLine())));
/* 1641 */                     det.setXbipdd_ctnbr(box.getLot()
/* 1642 */                         .getId());
/* 1643 */                     det.setXbipdd_part(portalShipOrderItem
/* 1644 */                         .getPart().getId());
/* 1645 */                     det.setXbipdd_lot(null);
/* 1646 */                     det.setXbipdd_qty(
/* 1647 */                         Integer.valueOf(box.getNumber().intValue()));
/* 1648 */                     det.setXbipdd_createdt(new Date());
/* 1649 */                     this.daoShared.saveObject(det);
/*      */                   } else {
/*      */                     
/* 1652 */                     det.setXbipdd_nbr(portalShipOrder
/* 1653 */                         .getCode());
/* 1654 */                     det.setXbipdd_line(Integer.valueOf(num));
/*      */ 
/*      */                     
/* 1657 */                     det.setXbipdd_ctnbr(box.getLot()
/* 1658 */                         .getId());
/* 1659 */                     det.setXbipdd_part(portalShipOrderItem
/* 1660 */                         .getPart().getId());
/* 1661 */                     det.setXbipdd_lot(null);
/* 1662 */                     det.setXbipdd_qty(
/* 1663 */                         Integer.valueOf(box.getNumber().intValue()));
/* 1664 */                     det.setXbipdd_createdt(new Date());
/* 1665 */                     this.daoShared.saveObject(det);
/*      */                   } 
/*      */                   
/* 1668 */                   box.setIsSync(YesNo.YES);
/* 1669 */                   this.dao.updateObject(box);
/*      */                 } 
/*      */               }
/* 1672 */               num++;
/*      */               
/* 1674 */               portalShipOrderItem.setIsSync(Integer.valueOf(1));
/* 1675 */               this.dao.updateObject(portalShipOrderItem);
/*      */             } 
/*      */           }
/*      */           
/* 1679 */           XbipdmMstr mstr = new XbipdmMstr();
/* 1680 */           mstr.setXbipdm_nbr(portalShipOrder.getCode());
/* 1681 */           mstr.setXbipdm_vend(portalShipOrder.getSupplier().getCode());
/* 1682 */           mstr.setXbipdm_date(portalShipOrder.getCreateDate());
/* 1683 */           mstr.setXbipdm_site("");
/* 1684 */           mstr.setXbipdm_uf1(null);
/* 1685 */           mstr.setXbipdm_uf2(null);
/* 1686 */           mstr.setXbipdm_uf3(null);
/* 1687 */           mstr.setXbipdm_createdt(new Date());
/* 1688 */           this.daoShared.saveObject(mstr);
/*      */           
/* 1690 */           portalShipOrder.setIsSync(Integer.valueOf(1));
/* 1691 */           this.dao.updateObject(portalShipOrder);
/*      */         } 
/*      */       }
/* 1694 */     } catch (Exception e) {
/* 1695 */       e.printStackTrace();
/* 1696 */       insertSystemLog("PortalShipOrder", " SyncShipOrderError", 
/* 1697 */           e.getMessage(), "1");
/* 1698 */       System.out.println("同步送货单到中间表出错------------------------------>" + (
/* 1699 */           new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
/* 1700 */           .format(new Date()));
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
/*      */   public void shipOrderOne(PortalShipOrder portalShipOrder) {
/*      */     try {
/* 1713 */       int num = 1;
/* 1714 */       if (portalShipOrder != null)
/*      */       {
/* 1716 */         List<PortalShipOrderItem> orderItemList = this.dao
/* 1717 */           .getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + 
/* 1718 */             portalShipOrder.getId() + 
/* 1719 */             " and (item.isSync!=1 or item.isSync is null) ");
/* 1720 */         if (orderItemList.size() != 0) {
/* 1721 */           for (PortalShipOrderItem portalShipOrderItem : orderItemList) {
/*      */             
/* 1723 */             List<Box> boxList = this.dao
/* 1724 */               .getObjectList(" from Box box where box.psoItem.id=" + 
/* 1725 */                 portalShipOrderItem.getId() + 
/* 1726 */                 " and (box.isSync=1 or box.isSync is null)");
/* 1727 */             if (boxList.size() != 0) {
/* 1728 */               for (Box box : boxList) {
/*      */                 
/* 1730 */                 XbipddDet det = new XbipddDet();
/*      */                 
/* 1732 */                 if (portalShipOrder.getCreateType().equals(
/* 1733 */                     "NJIT_PO")) {
/* 1734 */                   det.setXbipdd_nbr(portalShipOrder.getCode());
/* 1735 */                   det.setXbipdd_line(Integer.valueOf(num));
/* 1736 */                   det.setXbipdd_ponbr(portalShipOrderItem
/* 1737 */                       .getPoipItem().getPoip_number()
/* 1738 */                       .getPoip_number());
/* 1739 */                   det.setXbipdd_poline(
/* 1740 */                       Integer.valueOf(Integer.parseInt(portalShipOrderItem
/* 1741 */                           .getPoipItem().getLine())));
/* 1742 */                   det.setXbipdd_ctnbr(box.getLot().getId());
/* 1743 */                   det.setXbipdd_part(portalShipOrderItem
/* 1744 */                       .getPart().getId());
/* 1745 */                   det.setXbipdd_lot(null);
/* 1746 */                   det.setXbipdd_qty(
/* 1747 */                       Integer.valueOf(box.getNumber().intValue()));
/* 1748 */                   det.setXbipdd_createdt(new Date());
/* 1749 */                   this.daoShared.saveObject(det);
/*      */                 } else {
/*      */                   
/* 1752 */                   det.setXbipdd_nbr(portalShipOrder.getCode());
/* 1753 */                   det.setXbipdd_line(Integer.valueOf(num));
/*      */ 
/*      */                   
/* 1756 */                   det.setXbipdd_ctnbr(box.getLot().getId());
/* 1757 */                   det.setXbipdd_part(portalShipOrderItem
/* 1758 */                       .getPart().getId());
/* 1759 */                   det.setXbipdd_lot(null);
/* 1760 */                   det.setXbipdd_qty(
/* 1761 */                       Integer.valueOf(box.getNumber().intValue()));
/* 1762 */                   det.setXbipdd_createdt(new Date());
/* 1763 */                   this.daoShared.saveObject(det);
/*      */                 } 
/*      */                 
/* 1766 */                 box.setIsSync(YesNo.YES);
/* 1767 */                 this.dao.updateObject(box);
/*      */               } 
/*      */             }
/* 1770 */             num++;
/*      */             
/* 1772 */             portalShipOrderItem.setIsSync(Integer.valueOf(1));
/* 1773 */             this.dao.updateObject(portalShipOrderItem);
/*      */           } 
/*      */         }
/*      */         
/* 1777 */         XbipdmMstr mstr = new XbipdmMstr();
/* 1778 */         mstr.setXbipdm_nbr(portalShipOrder.getCode());
/* 1779 */         mstr.setXbipdm_vend(portalShipOrder.getSupplier().getCode());
/* 1780 */         mstr.setXbipdm_date(portalShipOrder.getCreateDate());
/* 1781 */         mstr.setXbipdm_site("");
/* 1782 */         mstr.setXbipdm_uf1(null);
/* 1783 */         mstr.setXbipdm_uf2(null);
/* 1784 */         mstr.setXbipdm_uf3(null);
/* 1785 */         mstr.setXbipdm_createdt(new Date());
/* 1786 */         this.daoShared.saveObject(mstr);
/*      */         
/* 1788 */         portalShipOrder.setIsSync(Integer.valueOf(1));
/* 1789 */         this.dao.updateObject(portalShipOrder);
/*      */       }
/*      */     
/* 1792 */     } catch (Exception e) {
/* 1793 */       e.printStackTrace();
/* 1794 */       insertSystemLog("PortalShipOrder", " SyncShipOrderError", 
/* 1795 */           e.getMessage(), "1");
/* 1796 */       System.out.println("同步送货单到中间表出错------------------------------>" + (
/* 1797 */           new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
/* 1798 */           .format(new Date()));
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/quartz/job/DeliverMinuteSyncJob.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */