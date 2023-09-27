/*     */ package com.aof.service.plantWarehouse.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.plantWarehouse.WmsUWDAO;
/*     */ import com.aof.dao.po.BoxDAO;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.UnplannedWarehousingStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousing;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
/*     */ import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryOrder;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.plantWarehouse.WmsUWManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jxl.Cell;
/*     */ import jxl.NumberCell;
/*     */ import jxl.Sheet;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class WmsUWManagerImpl
/*     */   extends BaseManager
/*     */   implements WmsUWManager {
/*     */   private WmsUWDAO wmsUWDAO;
/*     */   private WmsPartManager wmsPartManager;
/*     */   private StorageLocationManager storageLocationManager;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private SupplierManager supplierManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private BoxDAO boxDAO;
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  57 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setWmsUWDAO(WmsUWDAO wmsUWDAO) {
/*  61 */     this.wmsUWDAO = wmsUWDAO;
/*     */   }
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  65 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/*  70 */     this.storageLocationManager = storageLocationManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  74 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setSupplierManager(SupplierManager supplierManager) {
/*  78 */     this.supplierManager = supplierManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing getWmsUnplannedWarehousing(Integer id) {
/*  83 */     return this.wmsUWDAO.getWmsUnplannedWarehousing(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWmsUnplannedWarehousingListCount(Map conditions) {
/*  88 */     return this.wmsUWDAO.getWmsUnplannedWarehousingListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsUnplannedWarehousingList(Map conditions, int pageNo, int pageSize, WmsUnplannedWarehousingQueryOrder order, boolean descend) {
/*  95 */     return this.wmsUWDAO.getWmsUnplannedWarehousingList(conditions, pageNo, 
/*  96 */         pageSize, order, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing insertWmsUnplannedWarehousing(WmsUnplannedWarehousing pwom) {
/* 101 */     pwom.setCode(getLastCode(pwom.getDate()));
/* 102 */     return this.wmsUWDAO.insertWmsUnplannedWarehousing(pwom);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing updateWmsUnplannedWarehousing(WmsUnplannedWarehousing pwom) {
/* 108 */     return this.wmsUWDAO.updateWmsUnplannedWarehousing(pwom);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 113 */     return this.wmsUWDAO.getMaxPoApplicationIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousingItem getWmsUnplannedWarehousingItem(int id) {
/* 118 */     return this.wmsUWDAO.getWmsUnplannedWarehousingItem(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> insertWmsUnplannedWarehousingItem(Sheet[] sheet, Integer id, String type) {
/* 123 */     int rowNum = 0;
/* 124 */     SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
/* 125 */     WmsUnplannedWarehousing warehousing = getWmsUnplannedWarehousing(id);
/*     */     
/* 127 */     List<WmsUnplannedWarehousingItem> list = new ArrayList<WmsUnplannedWarehousingItem>(); byte b; int i; Sheet[] arrayOfSheet;
/* 128 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 129 */       int k = 0;
/* 130 */       rowNum = sheet[k].getRows();
/* 131 */       for (int j = 1; j < rowNum; j++) {
/* 132 */         Cell[] cells = sh.getRow(j);
/* 133 */         String supplierCode = cells[0].getContents();
/* 134 */         String partId = cells[1].getContents();
/* 135 */         String enterTime = cells[2].getContents();
/* 136 */         NumberCell nc = (NumberCell)cells[3];
/* 137 */         String amount = (new StringBuilder(String.valueOf(nc.getValue()))).toString();
/* 138 */         StorageLocation storageLocation = null;
/* 139 */         if (cells.length > 4) {
/* 140 */           String location = cells[4].getContents();
/* 141 */           storageLocation = this.storageLocationManager.getStorageLocation(location);
/*     */         } 
/*     */         
/* 144 */         Supplier suppliers = this.supplierManager.getSupplierByCode(supplierCode);
/* 145 */         WmsPart wmsPart = this.wmsPartManager.getWmsPart(partId);
/*     */         
/* 147 */         if (wmsPart != null && suppliers != null) {
/* 148 */           if (supplierCode != "" && partId != "") {
/*     */             try {
/* 150 */               Date date = format.parse(enterTime);
/* 151 */               BigDecimal big_amount = new BigDecimal(amount);
/* 152 */               Box box = insertBox(suppliers, partId, big_amount, date, storageLocation);
/*     */               
/* 154 */               WmsUnplannedWarehousingItem item = new WmsUnplannedWarehousingItem();
/* 155 */               item.setUnplanned_warehousing_id(warehousing);
/* 156 */               item.setLocation(storageLocation);
/* 157 */               item.setQty(new BigDecimal(amount));
/* 158 */               item.setActual_qty(new BigDecimal(0));
/*     */               
/* 160 */               if (type.equals("1")) {
/* 161 */                 item.setIs_sync(YesNo.YES);
/* 162 */                 item.setIs_sync_date(new Date());
/*     */               } else {
/* 164 */                 item.setIs_sync(YesNo.NO);
/*     */               } 
/* 166 */               item.setStatus(YesNo.NO);
/* 167 */               item.setBox_id(box);
/*     */               
/* 169 */               this.wmsUWDAO.insertWmsUnplannedWarehousingItem(item);
/* 170 */             } catch (ParseException e) {
/* 171 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         } else {
/* 175 */           throw new ActionException("-->物料编号" + partId + "，或者供应商" + supplierCode + "不存在<--!");
/*     */         } 
/*     */       }  b++; }
/*     */     
/* 179 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public Box insertBox(Supplier supplier, String wmsPart, BigDecimal amount, Date enterTime, StorageLocation location) {
/* 184 */     WmsPart part = this.wmsPartManager.getWmsPart(wmsPart);
/* 185 */     WmsLot wl = null;
/* 186 */     if (supplier == null) {
/* 187 */       wl = (new InventoryTool((DAO)this.wmsUWDAO)).insertWOBox(part.getId(), "yyyyMMdd");
/*     */     } else {
/* 189 */       wl = (new InventoryTool((DAO)this.wmsUWDAO)).insertWOBox(supplier.getCode(), "yyyyMMdd");
/*     */     } 
/*     */     
/* 192 */     Box box = new Box();
/* 193 */     box.setLot(wl);
/* 194 */     box.setDate(enterTime);
/* 195 */     box.setPart(part);
/* 196 */     box.setNumber(amount);
/* 197 */     box.setPrint_number(Integer.valueOf(0));
/* 198 */     box.setStatus_rqc(BoxStatusRqc.EXEMPTION);
/* 199 */     box.setEnabled(EnabledDisabled.ENABLED);
/* 200 */     box.setIsPrint(YesNo.NO);
/* 201 */     if (location == null) {
/* 202 */       box.setStatus(BoxStatus.Wait);
/*     */     } else {
/* 204 */       box.setLocation(location);
/* 205 */       box.setStatus(BoxStatus.HASBEENINTO);
/* 206 */       box.setIn_date(enterTime);
/*     */     } 
/* 208 */     box.setPo_date(new Date());
/* 209 */     box.setStatus_freeze(YesNo.NO);
/* 210 */     box.setPo_supp(supplier.getCode());
/* 211 */     box.setPo_supp_name(supplier.getName());
/* 212 */     box.setIsPrint(YesNo.NO);
/* 213 */     this.wmsUWDAO.saveObject(box);
/* 214 */     return box;
/*     */   }
/*     */   
/*     */   private String getLastCode(Date date) {
/* 218 */     StringBuffer sb = new StringBuffer("UW");
/* 219 */     sb.append('0');
/* 220 */     sb.append(StringUtils.right(ActionUtils2.get8CharsFromDate(date), 6));
/* 221 */     String prefix = sb.toString();
/* 222 */     String maxId = this.wmsUWDAO.getMaxPoApplicationIdBeginWith(prefix);
/*     */     
/* 224 */     int serialNo = 1;
/* 225 */     if (maxId != null) {
/* 226 */       Integer maxSN = ActionUtils2.parseInt(StringUtils.right(maxId, 3));
/* 227 */       if (maxSN == null)
/* 228 */         throw new RuntimeException("max serial no. is not digit"); 
/* 229 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/* 231 */     String sn = String.valueOf(serialNo);
/* 232 */     for (int i = 0; i < 3 - sn.length(); i++)
/* 233 */       sb.append('0'); 
/* 234 */     sb.append(sn);
/* 235 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousingItem updateWmsUnplannedWarehousingItem(WmsUnplannedWarehousingItem pwom) {
/* 241 */     return this.wmsUWDAO.updateWmsUnplannedWarehousingItem(pwom);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> getWmsUnplannedWarehousingItemByMain(Integer id) {
/* 247 */     return this.wmsUWDAO.getWmsUnplannedWarehousingItemByMain(id);
/*     */   }
/*     */   
/*     */   public void deleteWmsUWItem(WmsUnplannedWarehousingItem item) {
/* 251 */     this.wmsUWDAO.deleteWmsUWItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningWmsUWPutInStorage(String code, String lotSer, String locationCode, String userId) {
/* 256 */     ScanLog scanLog = new ScanLog();
/* 257 */     scanLog.setDate(new Date());
/* 258 */     scanLog.setDescribe(String.valueOf(lotSer) + "," + locationCode);
/* 259 */     scanLog.setType(Integer.valueOf(9));
/* 260 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 261 */     if (user != null) {
/* 262 */       scanLog.setUserId(user);
/*     */     }
/* 264 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 267 */       List<StorageLocation> locationList = this.wmsUWDAO.getObjectList("from StorageLocation location where location.code='" + locationCode + "' and location.freeae_status <>0  ");
/* 268 */       if (locationList.size() == 0) {
/* 269 */         return String.valueOf(Properties.getPropertiesValye("scan.sync.error.location.null")) + locationCode;
/*     */       }
/*     */       
/* 272 */       StorageLocation location = locationList.get(0);
/* 273 */       List<Box> boxs = this.wmsUWDAO.getObjectList("from Box box where box.lot='" + lotSer + "'");
/* 274 */       if (boxs != null && boxs.size() > 0) {
/* 275 */         Box box = boxs.get(0);
/* 276 */         if (box.getStatus().equals(BoxStatus.HASBEENINTO)) {
/*     */           try {
/* 278 */             box.setLocation(location);
/* 279 */             SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
/* 280 */             List<WmsUnplannedWarehousingItem> items = this.wmsUWDAO
/* 281 */               .getObjectList("from WmsUnplannedWarehousingItem item where item.unplanned_warehousing_id.id = '" + 
/* 282 */                 code + "' and item.box_id.id='" + 
/* 283 */                 box.getId() + "' ");
/*     */             
/* 285 */             if (items.size() > 0) {
/* 286 */               WmsUnplannedWarehousingItem item = items.get(0);
/* 287 */               if (item.getActual_qty() == null) {
/* 288 */                 item.setActual_qty(new BigDecimal(0));
/*     */               }
/* 290 */               item.setActual_qty(item.getActual_qty().add(box.getNumber()));
/* 291 */               item.setIs_sync(YesNo.NO);
/* 292 */               updateWmsUnplannedWarehousingItem(item);
/*     */ 
/*     */               
/* 295 */               this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.PLANDELIVERY, Boolean.valueOf(false));
/*     */ 
/*     */               
/* 298 */               box.setStatus(BoxStatus.HASBEENINTO);
/* 299 */               box.setDate(new Date());
/* 300 */               this.wmsUWDAO.updateObject(box);
/*     */             } 
/* 302 */           } catch (Exception e) {
/* 303 */             return String.valueOf(Properties.getPropertiesValye("scan.sync.error")) + e.getMessage();
/*     */           } 
/*     */         } else {
/* 306 */           return String.valueOf(Properties.getPropertiesValye("scan.sync.error.scl")) + location;
/*     */         } 
/* 308 */         return "ok";
/*     */       } 
/* 310 */     } catch (Exception e) {
/* 311 */       return e.getMessage();
/*     */     } 
/*     */     
/* 314 */     return "ok";
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
/*     */   public List<WmsUnplannedWarehousing> updateWmsUnplannedWarehousingByBox(List<WmsUnplannedWarehousing> result) {
/* 339 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> insertWmsUnplannedWarehousingItemByFinishedProduct(Sheet[] sheet, Integer wmsuwid) {
/* 344 */     int rowNum = 0;
/* 345 */     WmsUnplannedWarehousing warehousing = 
/* 346 */       getWmsUnplannedWarehousing(wmsuwid);
/*     */     
/* 348 */     List<WmsUnplannedWarehousingItem> list = new ArrayList<WmsUnplannedWarehousingItem>(); byte b; int i; Sheet[] arrayOfSheet;
/* 349 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 350 */       int k = 0;
/* 351 */       rowNum = sheet[k].getRows();
/* 352 */       for (int j = 5; j < rowNum; j++) {
/* 353 */         Cell[] cells = sh.getRow(j);
/* 354 */         String code = cells[0].getContents();
/* 355 */         String workOrderId = cells[1].getContents();
/* 356 */         String partId = cells[2].getContents();
/* 357 */         String amount = cells[3].getContents();
/* 358 */         String putInDate = cells[4].getContents();
/* 359 */         String location = "";
/*     */         
/* 361 */         String manualNo = "";
/* 362 */         String productionLine = "";
/* 363 */         String packing = "";
/*     */         
/* 365 */         if (cells.length > 5) {
/* 366 */           location = cells[5].getContents();
/*     */         }
/* 368 */         if (cells.length > 6) {
/* 369 */           manualNo = cells[6].getContents();
/*     */         }
/* 371 */         if (cells.length > 7) {
/* 372 */           productionLine = cells[7].getContents();
/*     */         }
/* 374 */         if (cells.length > 8) {
/* 375 */           packing = cells[8].getContents();
/*     */         }
/*     */         
/* 378 */         StorageLocation storageLocation = null;
/*     */         
/* 380 */         if (location.equals("") || location.length() == 0) {
/* 381 */           storageLocation = null;
/*     */         } else {
/* 383 */           storageLocation = getLocation(location);
/*     */         } 
/*     */         
/* 386 */         WmsPart wmsPart = this.wmsPartManager.getWmsPart(partId);
/* 387 */         if (wmsPart != null) {
/*     */           try {
/* 389 */             if (packing.equals("") || packing.length() == 0) {
/* 390 */               insertUWITEM(list, warehousing, wmsPart, putInDate, 
/* 391 */                   amount, productionLine, location, 
/* 392 */                   workOrderId, code, manualNo, 
/* 393 */                   storageLocation);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 398 */               BigDecimal amounts = new BigDecimal(amount);
/*     */               
/* 400 */               BigDecimal packings = new BigDecimal(packing);
/* 401 */               if (amounts.doubleValue() % packings.doubleValue() == 0.0D) {
/* 402 */                 for (int s = 0; s < amounts.doubleValue() / 
/* 403 */                   packings.doubleValue(); s++) {
/* 404 */                   insertUWITEM(list, warehousing, wmsPart, 
/* 405 */                       putInDate, packings.toString(), 
/* 406 */                       productionLine, location, 
/* 407 */                       workOrderId, code, manualNo, 
/* 408 */                       storageLocation);
/*     */                 }
/*     */               } else {
/* 411 */                 insertUWITEM(
/* 412 */                     list, 
/* 413 */                     warehousing, 
/* 414 */                     wmsPart, 
/* 415 */                     putInDate, (
/* 416 */                     new StringBuilder(
/* 417 */                       String.valueOf(amounts.doubleValue() % packings.doubleValue()))).toString(), 
/* 418 */                     productionLine, location, workOrderId, 
/* 419 */                     code, manualNo, storageLocation);
/* 420 */                 for (int s = 0; s < (amounts.doubleValue() - amounts
/* 421 */                   .doubleValue() % packings.doubleValue()) / 
/* 422 */                   packings.doubleValue(); s++) {
/* 423 */                   insertUWITEM(list, warehousing, wmsPart, 
/* 424 */                       putInDate, packings.toString(), 
/* 425 */                       productionLine, location, 
/* 426 */                       workOrderId, code, manualNo, 
/* 427 */                       storageLocation);
/*     */                 }
/*     */               } 
/*     */             } 
/* 431 */           } catch (Exception e) {
/* 432 */             e.printStackTrace();
/*     */           } 
/*     */         } else {
/* 435 */           throw new ActionException("��-->" + partId + 
/* 436 */               "<--���Ϻ�ϵͳ�����ڣ���ά�����ڵ������!");
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */     
/* 441 */     return list;
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
/*     */   private void insertUWITEM(List<WmsUnplannedWarehousingItem> list, WmsUnplannedWarehousing warehousing, WmsPart wmsPart, String putInDate, String amount, String productionLine, String location, String workOrderId, String code, String manualNo, StorageLocation storageLocation) throws ParseException {}
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
/*     */   private StorageLocation getLocation(String location) {
/* 483 */     StorageLocation storageLocation = null;
/* 484 */     List<StorageLocation> lists = this.wmsUWDAO
/* 485 */       .getObjectList("from StorageLocation storageLocation where storageLocation.code='" + 
/* 486 */         location + "'");
/* 487 */     if (lists != null && lists.size() > 0) {
/* 488 */       storageLocation = lists.get(0);
/*     */     }
/* 490 */     if (storageLocation == null) {
/* 491 */       throw new ActionException("storageLocation:" + location + 
/* 492 */           "-is.null");
/*     */     }
/* 494 */     if (storageLocation.getFreeae_status() != null && 
/* 495 */       storageLocation.getFreeae_status().equals(YesNo.YES)) {
/* 496 */       throw new ActionException("storageLocation.Freeze");
/*     */     }
/* 498 */     return storageLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsUnplannedWarehousing getWmsUnplannedWarehousingByWorkOrderBox(Integer id) {
/* 504 */     return this.wmsUWDAO.getWmsUnplannedWarehousingByWorkOrderBox(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsUnplannedWarehousingItem> getBoxByWmsUnplannedWarehousing(Integer id) {
/* 509 */     return this.wmsUWDAO.getBoxByWmsUnplannedWarehousing(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public Box insertBox(String supplierCode, String wmsPart, BigDecimal amount, Date enterTime) {
/* 514 */     WmsPart part = this.wmsPartManager.getWmsPart(wmsPart);
/* 515 */     WmsLot wl = (new InventoryTool((DAO)this.wmsUWDAO)).insertWOBox(supplierCode, "yyyyMMdd");
/*     */     
/* 517 */     Box box = new Box();
/* 518 */     box.setLot(wl);
/* 519 */     box.setDate(enterTime);
/* 520 */     box.setPart(part);
/* 521 */     box.setNumber(amount);
/* 522 */     box.setPrint_number(Integer.valueOf(0));
/* 523 */     box.setStatus_rqc(BoxStatusRqc.EXEMPTION);
/* 524 */     box.setEnabled(EnabledDisabled.ENABLED);
/* 525 */     box.setStatus(BoxStatus.Wait);
/* 526 */     box.setStatus_freeze(YesNo.NO);
/* 527 */     box.setIsPrint(YesNo.NO);
/* 528 */     this.wmsUWDAO.saveObject(box);
/* 529 */     return box;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWmsUnplannedWarehousingByStatusToConfirm(WmsUnplannedWarehousing warehousing) {
/* 534 */     warehousing.setStatus(UnplannedWarehousingStatus.CONFIRM);
/*     */     
/* 536 */     List<WmsUnplannedWarehousingItem> items = getWmsUnplannedWarehousingItemByMain(warehousing.getId());
/* 537 */     for (WmsUnplannedWarehousingItem item : items) {
/* 538 */       item.getBox_id().setEnabled(EnabledDisabled.ENABLED);
/* 539 */       this.wmsUWDAO.updateObject(item.getBox_id());
/*     */ 
/*     */       
/*     */       try {
/* 543 */         Box box = item.getBox_id();
/* 544 */         this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.PLANDELIVERY, Boolean.valueOf(false));
/* 545 */       } catch (Exception e) {
/* 546 */         throw new ActionException("库位冻结、或者物料信息不存在，请检查后在导入！");
/*     */       } 
/*     */     } 
/*     */     
/* 550 */     updateWmsUnplannedWarehousing(warehousing);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsUnplannedWarehousingItem(WmsUnplannedWarehousing pwom, Integer boxId) {
/* 555 */     List<WmsUnplannedWarehousingItem> items = this.wmsUWDAO
/* 556 */       .getObjectList("from WmsUnplannedWarehousingItem item where item.unplanned_warehousing_id.id = '" + 
/* 557 */         pwom.getId() + "' and item.box_id.id='" + 
/* 558 */         boxId + "' ");
/* 559 */     if (items.size() > 0) {
/* 560 */       this.boxDAO.deleteBoxByWmsUWItem(((WmsUnplannedWarehousingItem)items.get(0)).getBox_id());
/* 561 */       this.wmsUWDAO.deleteWmsUWItem(items.get(0));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBoxDAO(BoxDAO boxDAO) {
/* 566 */     this.boxDAO = boxDAO;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/plantWarehouse/impl/WmsUWManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */