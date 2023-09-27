/*      */ package com.aof.service;
/*      */ 
/*      */ import com.aof.dao.DAO;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.StoreRoom;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.WmsTool;
/*      */ import com.aof.model.inventory.Inventory;
/*      */ import com.aof.model.inventory.InventoryDetial;
/*      */ import com.aof.model.inventory.InventoryRecord;
/*      */ import com.aof.model.inventory.InventoryTotal;
/*      */ import com.aof.model.metadata.BoxStatus;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.InventoryRecordType;
/*      */ import com.aof.model.metadata.InventoryType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.WmsLot;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.springframework.beans.BeanUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InventoryTool
/*      */ {
/*   35 */   DAO dao = null;
/*      */   public InventoryTool(DAO dao) {
/*   37 */     this.dao = dao;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventoryAdjustmentByLot(String location, Box box, InventoryType type, Boolean isOutBound, BigDecimal newCount) throws Exception {
/*   43 */     StorageLocation storageLocation = null;
/*   44 */     List<StorageLocation> lists = this.dao.getObjectList("from StorageLocation storageLocation where storageLocation.code='" + location + "'");
/*   45 */     if (lists != null && lists.size() > 0) {
/*   46 */       storageLocation = lists.get(0);
/*      */     }
/*   48 */     if (storageLocation == null) {
/*   49 */       throw new ActionException("storageLocation:" + location + "-is.null");
/*      */     }
/*   51 */     if (storageLocation.getFreeae_status() != null && storageLocation.getFreeae_status().equals(YesNo.YES)) {
/*   52 */       throw new ActionException("storageLocation.Freeze");
/*      */     }
/*   54 */     if (box.getStatus_freeze() != null && 
/*   55 */       box.getStatus_freeze().equals(YesNo.YES) && 
/*   56 */       type != InventoryType.STORAGE && type != InventoryType.COMMONOUT && type != InventoryType.COMMONIN)
/*      */     {
/*      */       
/*   59 */       throw new ActionException("lotset.Freeze");
/*      */     }
/*      */ 
/*      */     
/*   63 */     Inventory inventory = inventoryUpdate(storageLocation, box, newCount, isOutBound, null);
/*      */ 
/*      */     
/*   66 */     if (isOutBound.booleanValue()) {
/*   67 */       newCount = newCount.multiply(new BigDecimal(-1));
/*      */     }
/*   69 */     updateInventoryChanges(box.getPart(), storageLocation, newCount, new Date(), type);
/*      */     
/*   71 */     if (isOutBound.booleanValue()) {
/*   72 */       box.setLocation(null);
/*   73 */       box.setStatus(BoxStatus.HASTHE);
/*      */     } else {
/*   75 */       box.setLocation(storageLocation);
/*      */     } 
/*   77 */     this.dao.updateObject(box);
/*   78 */     if (!isOutBound.booleanValue()) {
/*   79 */       checkLocationScattered(box);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void inventoryAdjustmentByWoLot(String location, Box box, InventoryType type, Boolean isOutBound, BigDecimal newCount) throws Exception {
/*   85 */     StorageLocation storageLocation = null;
/*   86 */     List<StorageLocation> lists = this.dao.getObjectList("from StorageLocation storageLocation where storageLocation.code='" + location + "'");
/*   87 */     if (lists != null && lists.size() > 0) {
/*   88 */       storageLocation = lists.get(0);
/*      */     } else {
/*   90 */       throw new ActionException("storageLocation:" + location + "-is.null");
/*      */     } 
/*      */     
/*   93 */     if (storageLocation.getFreeae_status() != null && storageLocation.getFreeae_status() == YesNo.YES)
/*      */     {
/*   95 */       throw new ActionException("storageLocation.Freeze");
/*      */     }
/*   97 */     if (isOutBound.booleanValue()) {
/*   98 */       newCount = newCount.multiply(new BigDecimal(-1));
/*      */     }
/*      */ 
/*      */     
/*  102 */     Inventory inventory = inventoryUpdate(storageLocation, box, newCount, isOutBound, null);
/*      */ 
/*      */     
/*  105 */     InventoryDetial total = updateInventoryChanges(box.getPart(), storageLocation, newCount, new Date(), type);
/*      */ 
/*      */     
/*  108 */     inventoryRecordUpdate(inventory, storageLocation, newCount, isOutBound, box, type, total);
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
/*      */   public void inventoryProduceByWoLot(String location, WmsTool wmsTool, InventoryType type, Boolean isOutBound, BigDecimal newCount, WmsPart part) throws Exception {
/*  128 */     StorageLocation storageLocation = null;
/*  129 */     List<StorageLocation> lists = this.dao.getObjectList("from StorageLocation storageLocation where storageLocation.code='" + location + "'");
/*  130 */     if (lists != null && lists.size() > 0) {
/*  131 */       storageLocation = lists.get(0);
/*      */     } else {
/*  133 */       throw new ActionException("storageLocation:" + location + "-is.null");
/*      */     } 
/*      */     
/*  136 */     if (storageLocation.getFreeae_status() != null && storageLocation.getFreeae_status() == YesNo.YES) {
/*  137 */       throw new ActionException("storageLocation.Freeze");
/*      */     }
/*  139 */     if (isOutBound.booleanValue()) {
/*  140 */       newCount = newCount.multiply(new BigDecimal(-1));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  147 */     if (wmsTool != null) {
/*  148 */       InventoryDetial inventoryDetial = updateInventoryChanges(wmsTool.getPart(), storageLocation, newCount, new Date(), type);
/*      */     } else {
/*  150 */       InventoryDetial inventoryDetial = updateInventoryChanges(part, storageLocation, newCount, new Date(), type);
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
/*      */   public void inventoryAdjustmentByWoLotProduceNxt(String location, WmsPart part, BigDecimal newCount, InventoryType type, Boolean isOutBound) throws Exception {
/*  170 */     StorageLocation storageLocation = null;
/*  171 */     List<StorageLocation> lists = this.dao.getObjectList("from StorageLocation storageLocation where storageLocation.code='" + location + "'");
/*  172 */     if (lists != null && lists.size() > 0) {
/*  173 */       storageLocation = lists.get(0);
/*      */     } else {
/*  175 */       throw new ActionException("storageLocation:" + location + "-is.null");
/*      */     } 
/*      */     
/*  178 */     if (storageLocation.getFreeae_status() != null && storageLocation.getFreeae_status() == YesNo.YES) {
/*  179 */       throw new ActionException("storageLocation.Freeze");
/*      */     }
/*      */     
/*  182 */     if (isOutBound.booleanValue()) {
/*  183 */       newCount = newCount.multiply(new BigDecimal(-1));
/*      */     }
/*      */ 
/*      */     
/*  187 */     InventoryDetial total = updateInventoryChanges(part, storageLocation, newCount, new Date(), type);
/*      */ 
/*      */     
/*  190 */     inventoryRecordUpdate(storageLocation, newCount, isOutBound, type, total);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventoryAdjustmentByLot(String location, Box box, InventoryType type, Boolean isOutBound) throws Exception {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventoryAdjustmentByWoLot(String location, Box box, InventoryType type, Boolean isOutBound) throws Exception {
/*  200 */     inventoryAdjustmentByWoLot(location, box, type, isOutBound, box.getNumber());
/*      */   }
/*      */ 
/*      */   
/*      */   public void inventoryProduceByWoLot(String location, WmsTool wmsTool, InventoryType type, Boolean isOutBound, WmsPart part) throws Exception {
/*  205 */     if (wmsTool == null) {
/*  206 */       inventoryProduceByWoLot(location, wmsTool, type, isOutBound, new BigDecimal(1), part);
/*      */     } else {
/*  208 */       inventoryProduceByWoLot(location, wmsTool, type, isOutBound, wmsTool.getQty(), part);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void inventoryAdjustmentByWoLotProduce(String location, WmsPart part, BigDecimal sumQty, InventoryType type, Boolean isOutBound) throws Exception {
/*  213 */     inventoryAdjustmentByWoLotProduceNxt(location, part, sumQty, type, isOutBound);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventoryAdjustment(String location, WmsPart part, BigDecimal count, InventoryType type, Boolean isOutBound, WmsLot lotSer) {
/*  220 */     StorageLocation storageLocation = null;
/*  221 */     List<StorageLocation> lists = this.dao.getObjectList("from StorageLocation storageLocation where storageLocation.code='" + location + "'");
/*  222 */     if (lists != null && lists.size() > 0) {
/*  223 */       storageLocation = lists.get(0);
/*      */     }
/*  225 */     if (storageLocation == null) {
/*  226 */       throw new ActionException("storageLocation:" + location + "-is.null");
/*      */     }
/*  228 */     if (storageLocation.getFreeae_status() != null && storageLocation.getFreeae_status().equals(YesNo.YES)) {
/*  229 */       throw new ActionException("storageLocation.Freeze");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  234 */     if (isOutBound.booleanValue()) {
/*  235 */       count = count.multiply(new BigDecimal(-1));
/*      */     }
/*  237 */     updateInventoryChanges(part, storageLocation, count, new Date(), type);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void inventoryAdjustment(String location, WmsPart part, BigDecimal count, InventoryType type, Boolean isOutBound) {
/*  243 */     inventoryAdjustment(location, part, count, type, isOutBound, null);
/*      */   }
/*      */ 
/*      */   
/*      */   private Inventory inventoryUpdate(StorageLocation location, Box box, BigDecimal number, Boolean isOutBound, WmsTool wmsTool) {
/*  248 */     Inventory inventory = null;
/*  249 */     if (wmsTool == null) {
/*  250 */       String sql = "from Inventory iv where iv.box.lot.id = '" + box.getLot().getId() + "' ";
/*  251 */       List<Inventory> list = this.dao.getObjectList(sql);
/*      */       
/*  253 */       String sql2 = "from InventoryDetial ivd where ivd.location.code = '" + location.getCode() + "' and ivd.part.id='" + box.getPart().getId() + "' ";
/*  254 */       List<InventoryDetial> locationQtyList = this.dao.getObjectList(sql2);
/*  255 */       BigDecimal locationQty = new BigDecimal(0);
/*  256 */       if (locationQtyList.size() > 0) {
/*  257 */         InventoryDetial detial = locationQtyList.get(0);
/*  258 */         locationQty = detial.getNumber().add(number);
/*      */       } 
/*  260 */       if (list.size() == 0) {
/*  261 */         inventory = new Inventory();
/*  262 */         inventory.setLocation(location);
/*  263 */         inventory.setPart(box.getPart());
/*  264 */         inventory.setQty(locationQty);
/*  265 */         inventory.setBox(box);
/*  266 */         inventory.setDate(new Date());
/*  267 */         inventory.setEnabled(EnabledDisabled.ENABLED);
/*  268 */         inventory.setTool(box.getTool());
/*      */         
/*  270 */         this.dao.saveObject(inventory);
/*      */       } else {
/*  272 */         inventory = list.get(0);
/*  273 */         inventory.setLocation(location);
/*  274 */         inventory.setQty(locationQty);
/*  275 */         inventory.setDate(new Date());
/*  276 */         inventory.setTool(box.getTool());
/*  277 */         this.dao.updateObject(inventory);
/*      */       } 
/*      */     } else {
/*  280 */       String sql = "from Inventory iv where iv.tool.id = '" + wmsTool.getId() + "' ";
/*  281 */       List<Inventory> list = this.dao.getObjectList(sql);
/*      */       
/*  283 */       String sql2 = "from InventoryDetial ivd where ivd.location.code = '" + location.getCode() + "' and ivd.part.id='" + box.getPart().getId() + "' ";
/*  284 */       List<InventoryDetial> locationQtyList = this.dao.getObjectList(sql2);
/*  285 */       BigDecimal locationQty = new BigDecimal(0);
/*  286 */       if (locationQtyList.size() > 0) {
/*  287 */         InventoryDetial detial = locationQtyList.get(0);
/*  288 */         locationQty = detial.getNumber().add(number);
/*      */       } 
/*  290 */       if (list.size() == 0) {
/*  291 */         inventory = new Inventory();
/*  292 */         inventory.setLocation(location);
/*  293 */         inventory.setPart(box.getPart());
/*  294 */         inventory.setQty(locationQty);
/*  295 */         inventory.setTool(wmsTool);
/*  296 */         inventory.setDate(new Date());
/*  297 */         inventory.setEnabled(EnabledDisabled.ENABLED);
/*      */         
/*  299 */         this.dao.saveObject(inventory);
/*      */       } else {
/*  301 */         inventory = list.get(0);
/*  302 */         inventory.setLocation(location);
/*  303 */         inventory.setQty(locationQty);
/*  304 */         inventory.setDate(new Date());
/*  305 */         this.dao.updateObject(inventory);
/*      */       } 
/*      */     } 
/*      */     
/*  309 */     return inventory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void inventoryRecordUpdate(Inventory inventory, StorageLocation storageLocation, BigDecimal number, Boolean isOutBound, Box box, InventoryType type, InventoryDetial total) {
/*  316 */     InventoryRecord inventoryRecord = new InventoryRecord();
/*  317 */     inventoryRecord.setDate(new Date());
/*  318 */     inventoryRecord.setQty(number);
/*  319 */     if (box != null) {
/*  320 */       inventoryRecord.setLot(box.getLot());
/*      */     }
/*  322 */     if (number.compareTo(new BigDecimal(0)) == 1) {
/*  323 */       inventoryRecord.setType(InventoryRecordType.PROCUREMENTWAREHOUSING);
/*      */     } else {
/*  325 */       inventoryRecord.setType(InventoryRecordType.ACQUISITIONOFWAREHOUSE);
/*      */     } 
/*      */     
/*  328 */     if (storageLocation.getId() != null) {
/*  329 */       inventoryRecord.setLocation(storageLocation);
/*      */     }
/*      */     
/*  332 */     BigDecimal initCount = getInventoryinitCount(inventory.getPart(), storageLocation);
/*  333 */     inventoryRecord.setLocation_init_number(initCount);
/*      */     
/*  335 */     BigDecimal suminitCount = getInventorySuminitCount(inventory.getPart(), storageLocation);
/*  336 */     inventoryRecord.setPart_init_number(suminitCount);
/*      */     
/*  338 */     inventoryRecord.setInventory_detial_id(total);
/*  339 */     inventoryRecord.setPart(box.getPart().getId());
/*  340 */     this.dao.saveObject(inventoryRecord);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void inventoryRecordUpdate(StorageLocation storageLocation, BigDecimal number, Boolean isOutBound, InventoryType type, InventoryDetial total) {
/*  346 */     InventoryRecord inventoryRecord = new InventoryRecord();
/*  347 */     inventoryRecord.setDate(new Date());
/*  348 */     inventoryRecord.setQty(number);
/*      */     
/*  350 */     if (number.compareTo(new BigDecimal(0)) == 1) {
/*  351 */       inventoryRecord.setType(InventoryRecordType.PROCUREMENTWAREHOUSING);
/*      */     } else {
/*  353 */       inventoryRecord.setType(InventoryRecordType.ACQUISITIONOFWAREHOUSE);
/*      */     } 
/*      */     
/*  356 */     if (storageLocation.getId() != null) {
/*  357 */       inventoryRecord.setLocation(storageLocation);
/*      */     }
/*      */     
/*  360 */     inventoryRecord.setInventory_detial_id(total);
/*  361 */     inventoryRecord.setPart(total.getPart().getId());
/*  362 */     this.dao.saveObject(inventoryRecord);
/*      */   }
/*      */   
/*      */   private BigDecimal getInventoryinitCount(WmsPart part, StorageLocation storageLocation) {
/*  366 */     BigDecimal locationPartCount = new BigDecimal(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  375 */     return locationPartCount;
/*      */   }
/*      */ 
/*      */   
/*      */   private BigDecimal getInventorySuminitCount(WmsPart part, StorageLocation storageLocation) {
/*  380 */     BigDecimal locationPartCount = new BigDecimal(0);
/*  381 */     InventoryTotal total = null;
/*  382 */     List<InventoryTotal> itemList = this.dao.getObjectList("from InventoryTotal total where total.part.id='" + part.getId() + "' ");
/*  383 */     if (itemList.size() == 0) {
/*  384 */       locationPartCount = new BigDecimal(0);
/*      */     } else {
/*  386 */       total = itemList.get(0);
/*  387 */       locationPartCount = new BigDecimal(total.getNumber().toString());
/*      */     } 
/*      */     
/*  390 */     return locationPartCount;
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
/*      */   private InventoryDetial updateInventoryChanges(WmsPart part, StorageLocation storageLocation, BigDecimal count, Date date, InventoryType type) {
/*  405 */     String sql = "from InventoryDetial item where item.part.id='" + part.getId() + "' and item.location.code = '" + storageLocation.getCode() + "' ";
/*  406 */     List<InventoryDetial> list = this.dao.getObjectList(sql);
/*  407 */     if (list.size() > 0) {
/*  408 */       InventoryDetial detial = list.get(0);
/*  409 */       if (detial.getNumber() != null) {
/*      */         
/*  411 */         String sqlItem = "from InventoryDetial item where item.part.id='" + part.getId() + "' ";
/*  412 */         List<InventoryDetial> listItem = this.dao.getObjectList(sqlItem);
/*      */         
/*  414 */         String sqlSumPart = "select sum(item.number) from InventoryDetial item where item.part.id='" + part.getId() + "' ";
/*  415 */         List<Object> sumParts = this.dao.getObjectList(sqlSumPart);
/*      */         
/*  417 */         BigDecimal sumPart = new BigDecimal(0);
/*  418 */         if (sumParts.size() > 0 && sumParts.get(0) != null) {
/*  419 */           sumPart = (BigDecimal)sumParts.get(0);
/*      */         }
/*      */         
/*  422 */         for (InventoryDetial inventoryDetial : listItem) {
/*  423 */           if (inventoryDetial.getPart_qty() == null) {
/*  424 */             inventoryDetial.setPart_qty(new BigDecimal(0)); continue;
/*      */           } 
/*  426 */           inventoryDetial.setPart_qty(sumPart.add(count));
/*  427 */           this.dao.updateObject(inventoryDetial);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  432 */         detial.setNumber(detial.getNumber().add(count));
/*  433 */         this.dao.updateObject(detial);
/*      */       } 
/*      */     } else {
/*      */       
/*  437 */       String sqlItem = "from InventoryDetial item where item.part.id='" + part.getId() + "' ";
/*  438 */       List<InventoryDetial> listItem = this.dao.getObjectList(sqlItem);
/*      */ 
/*      */       
/*  441 */       String sqlSumPart = "select sum(item.number) from InventoryDetial item where item.part.id='" + part.getId() + "' ";
/*  442 */       List<Object> sumParts = this.dao.getObjectList(sqlSumPart);
/*      */       
/*  444 */       BigDecimal sumPart = new BigDecimal(0);
/*  445 */       if (sumParts.size() > 0 && sumParts.get(0) != null) {
/*  446 */         sumPart = (BigDecimal)sumParts.get(0);
/*      */       }
/*      */       
/*  449 */       for (InventoryDetial inventoryDetial : listItem) {
/*  450 */         if (inventoryDetial.getPart_qty() == null) {
/*  451 */           inventoryDetial.setPart_qty(new BigDecimal(0)); continue;
/*      */         } 
/*  453 */         inventoryDetial.setPart_qty(sumPart.add(count));
/*  454 */         this.dao.updateObject(inventoryDetial);
/*      */       } 
/*      */ 
/*      */       
/*  458 */       InventoryDetial item = new InventoryDetial();
/*  459 */       item.setNumber(count);
/*  460 */       item.setLocation(storageLocation);
/*  461 */       item.setPart(part);
/*      */       
/*  463 */       item.setPart_qty(sumPart.add(count));
/*  464 */       this.dao.saveObject(item);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     return null;
/*      */   }
/*      */   
/*      */   public WmsLot insertWOBox(String lotStart, String lotDateSDF) {
/*  518 */     return insertWOBox(lotStart, lotDateSDF, new Date());
/*      */   }
/*      */   
/*      */   public WmsLot insertWOBox(String suppCode, String lotDateSDF, Date date) {
/*  522 */     if (date == null) {
/*  523 */       date = new Date();
/*      */     }
/*      */     
/*  526 */     Integer maxLot = Integer.valueOf(0);
/*  527 */     DecimalFormat df = new DecimalFormat("0000");
/*  528 */     SimpleDateFormat format = new SimpleDateFormat(lotDateSDF);
/*  529 */     String time = format.format(date);
/*      */     
/*  531 */     List<E> list = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + suppCode + time + "%'");
/*  532 */     String maxnumber = "";
/*  533 */     String lot = "";
/*  534 */     if (list.size() == 0) {
/*  535 */       maxLot = Integer.valueOf(0);
/*      */     }
/*  537 */     else if (list.get(0) == null) {
/*  538 */       maxLot = Integer.valueOf(0);
/*      */     } else {
/*  540 */       maxnumber = list.get(0).toString();
/*  541 */       String lotOne = maxnumber.substring(maxnumber.length() - 2, maxnumber.length());
/*  542 */       if (lotOne.equals("-N")) {
/*  543 */         lot = maxnumber.substring(maxnumber.length() - 6, maxnumber.length() - 2);
/*      */       } else {
/*  545 */         lot = maxnumber.substring(maxnumber.length() - 4, maxnumber.length());
/*      */       } 
/*      */       
/*  548 */       maxLot = Integer.valueOf(Integer.parseInt(lot));
/*      */     } 
/*      */     
/*  551 */     maxLot = Integer.valueOf(maxLot.intValue() + 1);
/*      */     
/*  553 */     WmsLot wl = new WmsLot();
/*  554 */     String lotId = String.valueOf(suppCode) + time + df.format(maxLot);
/*  555 */     wl.setEnabled(EnabledDisabled.ENABLED);
/*  556 */     wl.setId(lotId);
/*  557 */     this.dao.saveObject(wl);
/*  558 */     wl = (WmsLot)this.dao.getObject(WmsLot.class, lotId);
/*  559 */     return wl;
/*      */   }
/*      */   
/*      */   public WmsLot insertWOBox1(String suppCode, String lotDateSDF, String lotBox) {
/*  563 */     Integer maxLot = Integer.valueOf(0);
/*  564 */     DecimalFormat df = new DecimalFormat("0000");
/*  565 */     SimpleDateFormat format = new SimpleDateFormat(lotDateSDF);
/*  566 */     String lotSer = lotBox.substring(5, 13);
/*  567 */     List<E> list = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + suppCode + lotSer + "%'");
/*  568 */     String maxnumber = "";
/*  569 */     String lot = "";
/*  570 */     if (list.size() == 0) {
/*  571 */       maxLot = Integer.valueOf(0);
/*      */     }
/*  573 */     else if (list.get(0) == null) {
/*  574 */       maxLot = Integer.valueOf(0);
/*      */     } else {
/*  576 */       maxnumber = list.get(0).toString();
/*  577 */       String lotOne = maxnumber.substring(maxnumber.length() - 2, maxnumber.length());
/*  578 */       if (lotOne.equals("-N")) {
/*  579 */         lot = maxnumber.substring(maxnumber.length() - 6, maxnumber.length() - 2);
/*      */       } else {
/*  581 */         lot = maxnumber.substring(maxnumber.length() - 4, maxnumber.length());
/*      */       } 
/*      */       
/*  584 */       maxLot = Integer.valueOf(Integer.parseInt(lot));
/*      */     } 
/*      */     
/*  587 */     maxLot = Integer.valueOf(maxLot.intValue() + 1);
/*      */     
/*  589 */     WmsLot wl = new WmsLot();
/*  590 */     String lotId = String.valueOf(suppCode) + lotSer + df.format(maxLot);
/*  591 */     wl.setEnabled(EnabledDisabled.ENABLED);
/*  592 */     wl.setId(lotId);
/*  593 */     this.dao.saveObject(wl);
/*  594 */     wl = (WmsLot)this.dao.getObject(WmsLot.class, lotId);
/*  595 */     return wl;
/*      */   }
/*      */   public WmsLot insertWOBoxAdjustment(String suppCode, String lotDateSDF, String lotBox) {
/*  598 */     Integer maxLot = Integer.valueOf(0);
/*  599 */     DecimalFormat df = new DecimalFormat("0000");
/*  600 */     SimpleDateFormat format = new SimpleDateFormat(lotDateSDF);
/*      */     
/*  602 */     String lotSer = format.format(new Date());
/*  603 */     List<E> list = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + suppCode + lotSer + "%'");
/*  604 */     String maxnumber = "";
/*  605 */     String lot = "";
/*  606 */     if (list.size() == 0) {
/*  607 */       maxLot = Integer.valueOf(0);
/*      */     }
/*  609 */     else if (list.get(0) == null) {
/*  610 */       maxLot = Integer.valueOf(0);
/*      */     } else {
/*  612 */       maxnumber = list.get(0).toString();
/*  613 */       String lotOne = maxnumber.substring(maxnumber.length() - 2, maxnumber.length());
/*  614 */       if (lotOne.equals("-N")) {
/*  615 */         lot = maxnumber.substring(maxnumber.length() - 6, maxnumber.length() - 2);
/*      */       } else {
/*  617 */         lot = maxnumber.substring(maxnumber.length() - 4, maxnumber.length());
/*      */       } 
/*      */       
/*  620 */       maxLot = Integer.valueOf(Integer.parseInt(lot));
/*      */     } 
/*      */     
/*  623 */     maxLot = Integer.valueOf(maxLot.intValue() + 1);
/*      */     
/*  625 */     WmsLot wl = new WmsLot();
/*  626 */     String lotId = String.valueOf(suppCode) + lotSer + df.format(maxLot);
/*  627 */     wl.setEnabled(EnabledDisabled.ENABLED);
/*  628 */     wl.setId(lotId);
/*  629 */     this.dao.saveObject(wl);
/*  630 */     wl = (WmsLot)this.dao.getObject(WmsLot.class, lotId);
/*  631 */     return wl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WmsLot insertNBox(String suppCode, String lotDateSDF, Date date) {
/*  641 */     if (date == null) {
/*  642 */       date = new Date();
/*      */     }
/*      */     
/*  645 */     Integer maxLot = Integer.valueOf(0);
/*  646 */     DecimalFormat df = new DecimalFormat("0000");
/*  647 */     SimpleDateFormat format = new SimpleDateFormat(lotDateSDF);
/*  648 */     String time = format.format(date);
/*      */     
/*  650 */     List<E> list = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + suppCode + time + "%'");
/*  651 */     String maxnumber = "";
/*  652 */     String lot = "";
/*  653 */     if (list.size() == 0) {
/*  654 */       maxLot = Integer.valueOf(0);
/*      */     }
/*  656 */     else if (list.get(0) == null) {
/*  657 */       maxLot = Integer.valueOf(0);
/*      */     } else {
/*  659 */       maxnumber = list.get(0).toString();
/*  660 */       String lotOne = maxnumber.substring(maxnumber.length() - 2, maxnumber.length());
/*  661 */       if (lotOne.equals("-N")) {
/*  662 */         lot = maxnumber.substring(maxnumber.length() - 6, maxnumber.length() - 2);
/*      */       } else {
/*  664 */         lot = maxnumber.substring(maxnumber.length() - 4, maxnumber.length());
/*      */       } 
/*  666 */       maxLot = Integer.valueOf(Integer.parseInt(lot));
/*      */     } 
/*      */     
/*  669 */     maxLot = Integer.valueOf(maxLot.intValue() + 1);
/*      */     
/*  671 */     WmsLot wl = new WmsLot();
/*  672 */     String lotId = String.valueOf(suppCode) + time + df.format(maxLot) + "-N";
/*  673 */     wl.setEnabled(EnabledDisabled.ENABLED);
/*  674 */     wl.setId(lotId);
/*  675 */     this.dao.saveObject(wl);
/*  676 */     wl = (WmsLot)this.dao.getObject(WmsLot.class, lotId);
/*  677 */     return wl;
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
/*      */   public Map<String, Object> getRecommendLotSer(WmsPart part, BigDecimal count, Boolean isSplit) {
/*  822 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/*      */ 
/*      */     
/*  825 */     List<BigDecimal> wmsPartCount = this.dao.getObjectList("select sum(box.count) from Box box  where box.isRecommend=1 and box.wmsPart.id='" + 
/*  826 */         part.getId() + "' " + 
/*  827 */         " and box.enabled = 0 and box.isOutStorage = 1 and box.isInStorage = 0 and box.location.storeroom_id.id in (35,38) ");
/*      */     
/*  829 */     if (wmsPartCount == null || wmsPartCount.size() == 0 || wmsPartCount.get(0) == null) {
/*  830 */       resultMap.put("part", part);
/*  831 */       resultMap.put("result", "1");
/*  832 */       resultMap.put("list", new ArrayList());
/*  833 */       resultMap.put("count", new BigDecimal(0));
/*  834 */       return resultMap;
/*      */     } 
/*      */ 
/*      */     
/*  838 */     if (count.compareTo(wmsPartCount.get(0)) == 1) {
/*  839 */       resultMap.put("part", part);
/*  840 */       resultMap.put("result", "1");
/*  841 */       resultMap.put("list", new ArrayList());
/*  842 */       resultMap.put("count", wmsPartCount.get(0));
/*  843 */       return resultMap;
/*      */     } 
/*  845 */     List<Box> resultList = new ArrayList<Box>();
/*  846 */     List<Box> boxList = this.dao.getObjectList("from Box box where box.isRecommend=1 and box.wmsPart.id='" + part.getId() + "' " + 
/*  847 */         "and box.enabled = 0 and box.isOutStorage = 1 and box.isInStorage = 0 and box.location.storeroom_id.id in (35,38)" + 
/*  848 */         " order by box.date,box.id");
/*      */     
/*  850 */     BigDecimal currentCount = new BigDecimal(count.toString()); Box box; Iterator<Box> iterator;
/*  851 */     for (iterator = boxList.iterator(), box = iterator.next(); iterator.hasNext() && 
/*  852 */       currentCount.compareTo(new BigDecimal(0)) == 1; ) {
/*      */ 
/*      */       
/*  855 */       if (currentCount.compareTo(box.getNumber()) != -1) {
/*  856 */         currentCount = currentCount.subtract(box.getNumber());
/*  857 */         resultList.add(box); continue;
/*  858 */       }  if (currentCount.compareTo(box.getNumber()) == -1 && currentCount.compareTo(new BigDecimal(0)) != 0) {
/*  859 */         if (isSplit.booleanValue()) {
/*  860 */           List<Box> list = new ArrayList();
/*  861 */           list.add(box);
/*  862 */           resultMap.put("part", part);
/*  863 */           resultMap.put("result", "2");
/*  864 */           resultMap.put("list", list);
/*  865 */           resultMap.put("count", currentCount);
/*  866 */           return resultMap;
/*      */         } 
/*  868 */         currentCount = currentCount.subtract(box.getNumber());
/*  869 */         resultList.add(box);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  874 */     resultMap.put("part", part);
/*  875 */     resultMap.put("result", "0");
/*  876 */     resultMap.put("list", resultList);
/*  877 */     resultMap.put("count", wmsPartCount.get(0));
/*      */     
/*  879 */     return resultMap;
/*      */   }
/*      */   
/*      */   public String checkInventory(StorageLocation storageLocation, WmsPart partId, BigDecimal number, Boolean isOutBound) {
/*  883 */     Inventory inventory = null;
/*  884 */     StoreRoom roid = storageLocation.getBasic_storeroom_id();
/*  885 */     List<Inventory> list = this.dao.getObjectList("from Inventory inventory where inventory.storeroomId.id=" + roid.getId() + " and inventory.partCode.id='" + partId.getId() + "'");
/*  886 */     if (list.size() == 0) {
/*  887 */       inventory = new Inventory();
/*  888 */       inventory.setLocation(storageLocation);
/*  889 */       inventory.setPart(partId);
/*  890 */       inventory.setQty(new BigDecimal(0));
/*  891 */       this.dao.saveObject(inventory);
/*      */     } else {
/*  893 */       inventory = list.get(0);
/*      */     } 
/*      */     
/*  896 */     if (isOutBound.booleanValue()) {
/*  897 */       BigDecimal count = inventory.getQty().subtract(number);
/*  898 */       count.doubleValue();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  903 */     return "ok";
/*      */   }
/*      */   
/*      */   public BigDecimal getInventoryAvailableCount(WmsPart partid) {
/*  907 */     List<Object> counts = this.dao.getObjectList("select sum(item.count) from InventoryDetial item where item.location.issue=0 and item.inventoryTotal.part.id='" + partid.getId() + "'");
/*  908 */     if (counts == null || counts.size() == 0 || counts.get(0) == null) {
/*  909 */       return new BigDecimal(0);
/*      */     }
/*  911 */     return (BigDecimal)counts.get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkLocationScattered(Box box) {
/*  916 */     if (box != null && box.getLocation() != null && 
/*  917 */       box.getNumber().compareTo(new BigDecimal(1)) != 0) {
/*  918 */       integralizationZero(box);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void integralizationZero(Box box) {
/*  924 */     if (box != null) {
/*  925 */       BigDecimal count = box.getNumber();
/*      */       
/*  927 */       Double number = Double.valueOf(0.0D);
/*      */       
/*  929 */       Double fraction = Double.valueOf(count.doubleValue() % 1.0D);
/*  930 */       if (fraction.doubleValue() != 0.0D) {
/*  931 */         number = Double.valueOf((count.doubleValue() - fraction.doubleValue()) / 1.0D);
/*      */       } else {
/*  933 */         number = Double.valueOf(count.doubleValue() / 1.0D);
/*      */       } 
/*      */       
/*  936 */       for (Double d = Double.valueOf(0.0D); d.doubleValue() < number.doubleValue(); d = Double.valueOf(d.doubleValue() + 1.0D)) {
/*  937 */         Box newBox = new Box();
/*  938 */         BeanUtils.copyProperties(box, newBox);
/*  939 */         newBox.setNumber(new BigDecimal(1));
/*  940 */         newBox.setLot(insertWOBox(box.getPsoItem().getPoipItem().getPoip_number().getSupplier().getCode(), "yyyyMMdd", box.getDate()));
/*  941 */         this.dao.saveObject(newBox);
/*      */       } 
/*      */       
/*  944 */       if (fraction.doubleValue() != 0.0D) {
/*  945 */         Box newBox = new Box();
/*  946 */         BeanUtils.copyProperties(box, newBox);
/*  947 */         newBox.setNumber(new BigDecimal(fraction.doubleValue()));
/*  948 */         newBox.setLot(insertWOBox(box.getPsoItem().getPoipItem().getPoip_number().getSupplier().getCode(), "yyyyMMdd", box.getDate()));
/*  949 */         this.dao.saveObject(newBox);
/*      */       } 
/*      */       
/*  952 */       box.setLocation(null);
/*  953 */       box.setStatus(BoxStatus.HASTHE);
/*  954 */       this.dao.updateObject(box);
/*      */     } 
/*      */   }
/*      */   public List getinvenDetialByPartBycount(String part, BigDecimal count) {
/*  958 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/*  959 */     List<Map<String, Object>> returnlist = new ArrayList();
/*  960 */     List<BigDecimal> wmsPartCount = this.dao.getObjectList("select sum(box.number) from Box box where box.part.id='" + part + "'  and box.location.basic_storeroom_id.type=4 and box.enabled=0 and box.status_freeze=1 and" + 
/*  961 */         " box.id not in (select bacth.box.id from SalesPreshiporderBatch bacth where bacth.enabled=0)");
/*      */     
/*  963 */     if (wmsPartCount == null || wmsPartCount.size() == 0 || wmsPartCount.get(0) == null) {
/*  964 */       resultMap.put("part", part);
/*  965 */       resultMap.put("result", "1");
/*  966 */       resultMap.put("list", new ArrayList());
/*  967 */       resultMap.put("count", new BigDecimal(0));
/*  968 */       returnlist.add(resultMap);
/*  969 */       return returnlist;
/*      */     } 
/*      */ 
/*      */     
/*  973 */     if (count.compareTo(wmsPartCount.get(0)) == 1) {
/*  974 */       resultMap.put("part", part);
/*  975 */       resultMap.put("result", "1");
/*  976 */       resultMap.put("list", new ArrayList());
/*  977 */       resultMap.put("count", wmsPartCount.get(0));
/*  978 */       returnlist.add(resultMap);
/*  979 */       return returnlist;
/*      */     } 
/*  981 */     resultMap.put("part", part);
/*  982 */     resultMap.put("result", "0");
/*  983 */     resultMap.put("list", new ArrayList());
/*  984 */     resultMap.put("count", wmsPartCount.get(0));
/*  985 */     returnlist.add(resultMap);
/*  986 */     return returnlist;
/*      */   }
/*      */   
/*      */   public List getSalesPreshiporderByLotSer(String partId, BigDecimal number) {
/*  990 */     List<Map> returnlist = new ArrayList();
/*  991 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/*  992 */     SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
/*      */     
/*  994 */     List<Box> resultList = new ArrayList<Box>();
/*  995 */     List<Box> boxList = this.dao.getObjectList(" from Box box where box.part.id='" + partId + "'  and box.location.basic_storeroom_id.type=4 and box.enabled=0 and box.status_freeze=1 and" + 
/*  996 */         " box.id not in (select bacth.box.id from SalesPreshiporderBatch bacth where bacth.enabled=0) order by box.date,box.id");
/*  997 */     List<Map> listBox = new ArrayList<Map>();
/*  998 */     if (boxList == null) {
/*  999 */       return new ArrayList();
/*      */     }
/*      */     
/* 1002 */     Map<Object, Object> map = null;
/* 1003 */     for (Box box : boxList) {
/* 1004 */       String lotset = box.getLot().getId();
/* 1005 */       BigDecimal count = box.getNumber();
/* 1006 */       if (number.compareTo(new BigDecimal(0)) == 1) {
/* 1007 */         map = new HashMap<Object, Object>();
/* 1008 */         number = number.subtract(count);
/* 1009 */         map.put("id", box);
/* 1010 */         map.put("lotset", lotset);
/* 1011 */         listBox.add(map);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1016 */     if (listBox.size() > 0) {
/* 1017 */       Map rMap = listBox.get(0);
/* 1018 */       String lotStrOne = (String)rMap.get("lotset");
/* 1019 */       String lotStrBegin = lotStrOne.substring(lotStrOne.length() - 12, lotStrOne.length() - 4);
/* 1020 */       rMap = listBox.get(listBox.size() - 1);
/* 1021 */       String lotStrTwo = (String)rMap.get("lotset");
/* 1022 */       String lotStrEnd = lotStrTwo.substring(lotStrTwo.length() - 12, lotStrTwo.length() - 4);
/* 1023 */       if (lotStrBegin.equals(lotStrEnd))
/*      */       {
/* 1025 */         lotStrBegin = "null";
/*      */       }
/* 1027 */       List<Map> listsBox = new ArrayList<Map>();
/* 1028 */       if (!lotStrBegin.equals(lotStrEnd)) {
/* 1029 */         Iterator<Map> iterator = listBox.iterator();
/* 1030 */         while (iterator.hasNext()) {
/* 1031 */           Map map2 = iterator.next();
/* 1032 */           String lotStra = (String)map2.get("lotset");
/* 1033 */           Box box = (Box)map2.get("id");
/* 1034 */           String lotStrEnda = lotStra.substring(lotStra.length() - 12, lotStra.length() - 4);
/* 1035 */           if (lotStrEnda.equals(lotStrEnd) && !lotStrEnda.equals(lotStrBegin)) {
/* 1036 */             iterator.remove();
/* 1037 */             number = number.add(box.getNumber());
/*      */           } 
/*      */         } 
/* 1040 */         String sqlGroup = "select sum(box.number),box.date,box.id from Box box where box.part.id='" + partId + "' and box.lot.id like '%" + lotStrEnd + "%'  and box.location.basic_storeroom_id.type=4 and box.enabled=0 and box.status_freeze=1 and" + 
/* 1041 */           " box.id not in (select bacth.box.id from SalesPreshiporderBatch bacth where bacth.enabled=0) group by box.date,box.id order by box.date,box.id";
/* 1042 */         List listsGroup = this.dao.getObjectList(sqlGroup);
/* 1043 */         BigDecimal numbera = new BigDecimal(0);
/* 1044 */         for (Object object : listsGroup) {
/* 1045 */           Object[] objects = (Object[])object;
/* 1046 */           numbera = numbera.add((BigDecimal)objects[0]);
/*      */         } 
/*      */         
/* 1049 */         String sqlGroups = " from Box box where box.part.id='" + partId + "' and box.lot.id like '%" + lotStrEnd + "%'  and box.location.basic_storeroom_id.type=4 and box.enabled=0 and box.status_freeze=1 and" + 
/* 1050 */           " box.id not in (select bacth.box.id from SalesPreshiporderBatch bacth  where bacth.enabled=0) order by box.date,box.id";
/* 1051 */         List<Box> boxaList = this.dao.getObjectList(sqlGroups);
/* 1052 */         Map<Object, Object> map1 = null;
/* 1053 */         if (numbera.compareTo(number) == 0) {
/* 1054 */           for (Box box : boxaList) {
/* 1055 */             String lotset = box.getLot().getId();
/* 1056 */             BigDecimal count = box.getNumber();
/* 1057 */             if (number.compareTo(new BigDecimal(0)) == 1) {
/* 1058 */               map1 = new HashMap<Object, Object>();
/* 1059 */               number = number.subtract(count);
/* 1060 */               map1.put("id", box);
/* 1061 */               map1.put("lotset", lotset);
/* 1062 */               listBox.add(map1);
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1066 */           for (Box box : boxaList) {
/* 1067 */             String lotset = box.getLot().getId();
/* 1068 */             BigDecimal count = box.getNumber();
/* 1069 */             if (number.compareTo(new BigDecimal(0)) == 1) {
/* 1070 */               map1 = new HashMap<Object, Object>();
/* 1071 */               number = number.subtract(count);
/* 1072 */               map1.put("id", box);
/* 1073 */               map1.put("lotset", null);
/* 1074 */               listsBox.add(map1);
/*      */             } 
/*      */           } 
/*      */         } 
/* 1078 */         returnlist.addAll(listBox);
/*      */       } 
/* 1080 */       returnlist.addAll(listsBox);
/*      */     } 
/* 1082 */     return returnlist;
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/InventoryTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */