/*     */ package com.aof.service.Product.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.product.SalesPreshiporderDao;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.inventory.InventoryMoving;
/*     */ import com.aof.model.inventory.InventoryOccupiedDetial;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.SalesPreshiporderStatus;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.product.CustomerPlan;
/*     */ import com.aof.model.product.SalesOrderItem;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.Product.CustomerPlanManager;
/*     */ import com.aof.service.Product.SalesOrderItemManager;
/*     */ import com.aof.service.Product.SalesOrderManager;
/*     */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*     */ import com.aof.service.Product.SalesPreshiporderManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class SalesPreshiporderManagerImpl
/*     */   extends BaseManager
/*     */   implements SalesPreshiporderManager
/*     */ {
/*     */   private SalesPreshiporderDao dao;
/*     */   private SalesOrderItemManager salesOrderItemManager;
/*     */   private SalesOrderManager salesOrderManager;
/*     */   private SalesPreshiporderItemManager salesPreshiporderItemManager;
/*     */   private BoxManager boxManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private CustomerPlanManager customerPlanManager;
/*     */   private StorageLocationManager storageLocationManager;
/*     */   
/*     */   public CustomerPlanManager getCustomerPlanManager() {
/*  77 */     return this.customerPlanManager;
/*     */   }
/*     */   
/*     */   public void setCustomerPlanManager(CustomerPlanManager customerPlanManager) {
/*  81 */     this.customerPlanManager = customerPlanManager;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  85 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  89 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public BoxManager getBoxManager() {
/*  93 */     return this.boxManager;
/*     */   }
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  97 */     this.boxManager = boxManager;
/*     */   }
/*     */   
/*     */   public SalesOrderItemManager getSalesOrderItemManager() {
/* 101 */     return this.salesOrderItemManager;
/*     */   }
/*     */   
/*     */   public void setSalesOrderItemManager(SalesOrderItemManager salesOrderItemManager) {
/* 105 */     this.salesOrderItemManager = salesOrderItemManager;
/*     */   }
/*     */   
/*     */   public SalesOrderManager getSalesOrderManager() {
/* 109 */     return this.salesOrderManager;
/*     */   }
/*     */   
/*     */   public void setSalesOrderManager(SalesOrderManager salesOrderManager) {
/* 113 */     this.salesOrderManager = salesOrderManager;
/*     */   }
/*     */   
/*     */   public SalesPreshiporderItemManager getSalesPreshiporderItemManager() {
/* 117 */     return this.salesPreshiporderItemManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSalesPreshiporderItemManager(SalesPreshiporderItemManager salesPreshiporderItemManager) {
/* 122 */     this.salesPreshiporderItemManager = salesPreshiporderItemManager;
/*     */   }
/*     */   
/*     */   public SalesPreshiporderDao getDao() {
/* 126 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SalesPreshiporderDao dao) {
/* 130 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SalesPreshiporder getById(Integer id) {
/* 134 */     return this.dao.getById(id);
/*     */   }
/*     */   
/*     */   public SalesPreshiporder insert(SalesPreshiporder salesPreshiporder) {
/* 138 */     return this.dao.insert(salesPreshiporder);
/*     */   }
/*     */   
/*     */   public void remove(SalesPreshiporder salesPreshiporder) {
/* 142 */     this.dao.remove(salesPreshiporder);
/*     */   }
/*     */   
/*     */   public SalesPreshiporder update(SalesPreshiporder salesPreshiporder) {
/* 146 */     return this.dao.update(salesPreshiporder);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListCount(Map conditions) {
/* 151 */     return this.dao.getListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderQueryOrder order, boolean descend) {
/* 156 */     return this.dao.getList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SalesPreshiporder insertSalesPreshiporde(SalesPreshiporder salesPreshiporder, Site site, User requestor, Date arrDate) {
/* 162 */     salesPreshiporder.setCreateDate(new Date());
/* 163 */     salesPreshiporder.setSite(site);
/*     */     
/* 165 */     salesPreshiporder.setIsEnabled(EnabledDisabled.ENABLED);
/* 166 */     salesPreshiporder.setStatus(SalesPreshiporderStatus.ON_MATERIAL);
/* 167 */     salesPreshiporder.setCode(getPortalShipOrderCode(new Date(), requestor));
/* 168 */     salesPreshiporder.setIsPrint(YesNo.NO);
/* 169 */     salesPreshiporder.setShPrint(YesNo.NO);
/* 170 */     salesPreshiporder.setMatchStatus(YesNo.NO);
/* 171 */     salesPreshiporder.setArrivaldate(arrDate);
/*     */ 
/*     */     
/* 174 */     return this.dao.insert(salesPreshiporder);
/*     */   }
/*     */   
/*     */   private String getPortalShipOrderCode(Date date, User requestor) {
/* 178 */     StringBuffer sb = new StringBuffer("SO");
/*     */     
/* 180 */     StringBuffer siteIds = new StringBuffer("00001");
/*     */ 
/*     */     
/* 183 */     String site = siteIds.substring(siteIds.length() - 4, siteIds.length());
/* 184 */     sb.append(site);
/* 185 */     String dateStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
/* 186 */     sb.append(dateStr);
/* 187 */     List<String> soMaxList = this.dao.getObjectList("select max(so.code) from SalesPreshiporder so where so.code like '" + 
/* 188 */         sb.toString() + "%'");
/* 189 */     Integer serialNumber = Integer.valueOf(0);
/* 190 */     if (soMaxList != null && soMaxList.size() != 0 && soMaxList.get(0) != null && !((String)soMaxList.get(0)).equals("null") && !((String)soMaxList.get(0)).equals("NULL")) {
/* 191 */       String soMax = soMaxList.get(0);
/* 192 */       serialNumber = Integer.valueOf(Integer.parseInt(soMax.substring(soMax.length() - 4, soMax.length())));
/*     */     } 
/* 194 */     DecimalFormat df = new DecimalFormat("0000");
/* 195 */     String serialNumbers = df.format((serialNumber.intValue() + 1));
/* 196 */     sb.append(serialNumbers);
/* 197 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertCustomerPlanPreshiporderIP(SalesPreshiporder salesPreshiporder) {
/* 202 */     List<SalesPreshiporderItem> poilist = this.dao.getSalesPreshiporderListByOrderId(salesPreshiporder.getId());
/* 203 */     for (SalesPreshiporderItem salesPreshiporderItem : poilist) {
/* 204 */       BigDecimal count = salesPreshiporderItem.getDeliverynumber();
/* 205 */       String partId = salesPreshiporderItem.getCustomerPlanId().getWmspart().getId();
/* 206 */       InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 207 */       List<Map> resultList = tool.getSalesPreshiporderByLotSer(partId, count);
/* 208 */       if (resultList != null) {
/* 209 */         BigDecimal amountTop = new BigDecimal(0);
/* 210 */         for (Map map3 : resultList) {
/*     */ 
/*     */           
/* 213 */           Box box = (Box)map3.get("id");
/*     */           
/* 215 */           String lotset = (String)map3.get("lotset");
/*     */           
/* 217 */           SalesPreshiporderBatch bacth = new SalesPreshiporderBatch();
/* 218 */           bacth.setBox(box);
/* 219 */           bacth.setSpsoitemId(salesPreshiporderItem);
/* 220 */           if (lotset != null) {
/* 221 */             bacth.setStatus(YesNo.YES);
/*     */           } else {
/* 223 */             bacth.setStatus(YesNo.NO);
/*     */           } 
/* 225 */           bacth.setEnabled(EnabledDisabled.ENABLED);
/* 226 */           this.dao.saveObject(bacth);
/* 227 */           insertInventoryOccupiedDetial(box);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void insertInventoryOccupiedDetial(Box box) {
/* 233 */     List<InventoryOccupiedDetial> inventoryOccupiedDetialList = this.dao.getObjectList(" from InventoryOccupiedDetial inventoryOccupiedDetial where inventoryOccupiedDetial.part.id='" + box.getPart().getId() + "'  and inventoryOccupiedDetial.location.id='" + box.getLocation().getId() + "'");
/* 234 */     if (inventoryOccupiedDetialList.size() > 0) {
/* 235 */       InventoryOccupiedDetial inventoryOccupiedDetial = inventoryOccupiedDetialList.get(0);
/* 236 */       BigDecimal count = inventoryOccupiedDetial.getNumber().add(box.getNumber());
/* 237 */       inventoryOccupiedDetial.setNumber(count);
/* 238 */       this.dao.updateObject(inventoryOccupiedDetial);
/*     */     } else {
/* 240 */       InventoryOccupiedDetial inventoryOccupiedDetial = new InventoryOccupiedDetial();
/* 241 */       inventoryOccupiedDetial.setLocation(box.getLocation());
/* 242 */       inventoryOccupiedDetial.setPart(box.getPart());
/* 243 */       inventoryOccupiedDetial.setNumber(box.getNumber());
/* 244 */       this.dao.saveObject(inventoryOccupiedDetial);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertSalesPreshiporderIP(SalesPreshiporder salesPreshiporder) {
/* 250 */     List<SalesPreshiporderItem> poilist = this.dao.getSalesPreshiporderListByOrderId(salesPreshiporder.getId());
/* 251 */     for (SalesPreshiporderItem salesPreshiporderItem : poilist) {
/* 252 */       BigDecimal count = salesPreshiporderItem.getDeliverynumber();
/* 253 */       String partId = salesPreshiporderItem.getSoipitemId().getItemNumber().getId();
/* 254 */       InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 255 */       List<Map> resultList = tool.getSalesPreshiporderByLotSer(partId, count);
/* 256 */       if (resultList != null) {
/* 257 */         BigDecimal amountTop = new BigDecimal(0);
/* 258 */         for (Map map3 : resultList) {
/*     */ 
/*     */           
/* 261 */           Box box = (Box)map3.get("id");
/*     */           
/* 263 */           String lotset = (String)map3.get("lotset");
/*     */           
/* 265 */           SalesPreshiporderBatch bacth = new SalesPreshiporderBatch();
/* 266 */           bacth.setBox(box);
/* 267 */           bacth.setSpsoitemId(salesPreshiporderItem);
/* 268 */           if (lotset != null) {
/* 269 */             bacth.setStatus(YesNo.YES);
/*     */           } else {
/* 271 */             bacth.setStatus(YesNo.NO);
/*     */           } 
/* 273 */           bacth.setEnabled(EnabledDisabled.ENABLED);
/* 274 */           this.dao.saveObject(bacth);
/* 275 */           insertInventoryOccupiedDetial(box);
/*     */         } 
/*     */       } 
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
/*     */   public Map<String, Object> getinvenDetialByPartBycount(String itemIds, String soNumbers) {
/* 306 */     String[] str = itemIds.split(",");
/* 307 */     String[] soNumber = soNumbers.split(",");
/* 308 */     for (int i = 0; i < str.length; i++) {
/* 309 */       if (!soNumber[i].equals(""))
/*     */       {
/*     */         
/* 312 */         String str1 = "select count(*) from InventoryDetial detial where detial. ";
/*     */       }
/*     */     } 
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertCustomerPlanPreshiporderByItem(String[] soItemIdsList, String[] deliveryNumbersList, String[] remarksList, String date, User user) {
/* 321 */     SalesPreshiporder salesPreshiporder = null;
/*     */     try {
/* 323 */       for (int i = 0; i < soItemIdsList.length; i++) {
/* 324 */         if (!deliveryNumbersList[i].equals("")) {
/*     */ 
/*     */           
/* 327 */           String soItemIds = soItemIdsList[i];
/* 328 */           BigDecimal deliveryNumber = new BigDecimal(deliveryNumbersList[i]);
/*     */           
/* 330 */           CustomerPlan customerPlan = this.customerPlanManager.getById(Integer.valueOf(Integer.parseInt(soItemIds)));
/*     */ 
/*     */ 
/*     */           
/* 334 */           if (salesPreshiporder == null) {
/* 335 */             salesPreshiporder = new SalesPreshiporder();
/* 336 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 337 */             String arrivalDate = date;
/* 338 */             Date arrDate = null;
/* 339 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 340 */               arrDate = sdf.parse(arrivalDate);
/*     */             }
/* 342 */             salesPreshiporder.setType(Integer.valueOf(2));
/* 343 */             salesPreshiporder.setCustomerName(customerPlan.getCustomer().getName1());
/* 344 */             salesPreshiporder.setCustomerCode(customerPlan.getCustomer().getCode());
/* 345 */             insertSalesPreshiporde(salesPreshiporder, user.getPrimarySite(), user, arrDate);
/*     */           } 
/*     */ 
/*     */           
/* 349 */           BigDecimal wBigDecimal = customerPlan.getQtyOpen();
/* 350 */           customerPlan.setQtyOpen(wBigDecimal.subtract(deliveryNumber));
/* 351 */           this.customerPlanManager.update(customerPlan);
/*     */           
/* 353 */           SalesPreshiporderItem salesPreshiporderItem = new SalesPreshiporderItem();
/* 354 */           salesPreshiporderItem.setSpsoId(salesPreshiporder);
/* 355 */           salesPreshiporderItem.setCustomerPlanId(customerPlan);
/* 356 */           salesPreshiporderItem.setDeliverynumber(deliveryNumber);
/* 357 */           salesPreshiporderItem.setShipQty(new BigDecimal(0));
/* 358 */           salesPreshiporderItem.setStockQty(new BigDecimal(0));
/*     */           
/* 360 */           if (remarksList.length > 0 && 
/* 361 */             !remarksList[i].equals(" ")) {
/* 362 */             salesPreshiporderItem.setSoRemark(remarksList[i]);
/*     */           }
/*     */           
/* 365 */           this.salesPreshiporderItemManager.insert(salesPreshiporderItem);
/*     */         } 
/*     */       } 
/* 368 */       insertCustomerPlanPreshiporderIP(salesPreshiporder);
/* 369 */     } catch (Exception e) {
/* 370 */       e.fillInStackTrace();
/* 371 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertSalesPreshiporderByItem(String[] soItemIdsList, String[] deliveryNumbersList, String[] remarksList, String date, User user) {
/* 378 */     SalesPreshiporder salesPreshiporder = null;
/*     */     try {
/* 380 */       for (int i = 0; i < soItemIdsList.length; i++) {
/* 381 */         if (!deliveryNumbersList[i].equals("")) {
/*     */ 
/*     */           
/* 384 */           String soItemIds = soItemIdsList[i];
/* 385 */           BigDecimal deliveryNumber = new BigDecimal(deliveryNumbersList[i]);
/*     */           
/* 387 */           SalesOrderItem salesOrderItem = this.salesOrderItemManager.getById(Integer.valueOf(Integer.parseInt(soItemIds)));
/*     */ 
/*     */           
/* 390 */           if (salesPreshiporder == null) {
/* 391 */             salesPreshiporder = new SalesPreshiporder();
/* 392 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 393 */             String arrivalDate = date;
/* 394 */             Date arrDate = null;
/* 395 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 396 */               arrDate = sdf.parse(arrivalDate);
/*     */             }
/* 398 */             salesPreshiporder.setType(Integer.valueOf(1));
/* 399 */             salesPreshiporder.setCustomerName(salesOrderItem.getSoId().getCustName());
/* 400 */             salesPreshiporder.setCustomerCode(salesOrderItem.getSoId().getCustCode());
/* 401 */             insertSalesPreshiporde(salesPreshiporder, user.getPrimarySite(), user, arrDate);
/*     */           } 
/*     */ 
/*     */           
/* 405 */           BigDecimal wBigDecimal = salesOrderItem.getQtyopen();
/* 406 */           salesOrderItem.setQtyopen(wBigDecimal.subtract(deliveryNumber));
/* 407 */           this.salesOrderItemManager.update(salesOrderItem);
/*     */           
/* 409 */           Boolean isClose = this.salesOrderItemManager.iscloseSalesOrderItem(salesOrderItem.getSoId());
/* 410 */           if (isClose.booleanValue()) {
/* 411 */             salesOrderItem.getSoId().setStatus(PurchaseOrderStatus.CLOSE);
/* 412 */             this.salesOrderManager.update(salesOrderItem.getSoId());
/*     */           } 
/* 414 */           SalesPreshiporderItem salesPreshiporderItem = new SalesPreshiporderItem();
/* 415 */           salesPreshiporderItem.setSpsoId(salesPreshiporder);
/* 416 */           salesPreshiporderItem.setSoipitemId(salesOrderItem);
/* 417 */           salesPreshiporderItem.setDeliverynumber(deliveryNumber);
/* 418 */           salesPreshiporderItem.setShipQty(new BigDecimal(0));
/* 419 */           salesPreshiporderItem.setStockQty(new BigDecimal(0));
/* 420 */           salesPreshiporderItem.setQtyStd(salesOrderItem.getBoxcount());
/* 421 */           if (remarksList.length > 0 && 
/* 422 */             !remarksList[i].equals(" ")) {
/* 423 */             salesPreshiporderItem.setSoRemark(remarksList[i]);
/*     */           }
/*     */           
/* 426 */           this.salesPreshiporderItemManager.insert(salesPreshiporderItem);
/*     */         } 
/*     */       } 
/* 429 */       insertSalesPreshiporderIP(salesPreshiporder);
/* 430 */     } catch (Exception e) {
/* 431 */       e.fillInStackTrace();
/* 432 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateInventoryOccupiedDetial(Box box) {
/* 437 */     List<InventoryOccupiedDetial> inventoryOccupiedDetialList = this.dao.getObjectList(" from InventoryOccupiedDetial inventoryOccupiedDetial where inventoryOccupiedDetial.part.id='" + box.getPart().getId() + "'  and inventoryOccupiedDetial.location.id='" + box.getLocation().getId() + "'");
/*     */     
/* 439 */     InventoryOccupiedDetial inventoryOccupiedDetial = inventoryOccupiedDetialList.get(0);
/* 440 */     if (inventoryOccupiedDetialList.size() > 0 && inventoryOccupiedDetial.getNumber().compareTo(new BigDecimal(0)) == 1) {
/* 441 */       BigDecimal count = inventoryOccupiedDetial.getNumber().subtract(box.getNumber());
/* 442 */       inventoryOccupiedDetial.setNumber(count);
/* 443 */       this.dao.updateObject(inventoryOccupiedDetial);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningSalesOutStock(String lotSer, String order, String userId) {
/* 450 */     StringBuffer strBuffer = new StringBuffer();
/*     */     try {
/* 452 */       SalesPreshiporder salesPreshiporder = this.dao.getSalesPreshiporder(order);
/* 453 */       if (salesPreshiporder != null)
/* 454 */       { if (salesPreshiporder.getStatus() == SalesPreshiporderStatus.IN_DELIVERY) {
/* 455 */           return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.inexistence");
/*     */         }
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
/* 472 */         List<Box> boxs = this.dao.getObjectList("from Box box where box.lot.id= '" + lotSer + "'");
/* 473 */         if (boxs.size() > 0) {
/*     */           
/* 475 */           String str = this.dao.getSalesPreshiporderBacthByLotStatus(order, lotSer);
/*     */           
/* 477 */           if (str.equals("not"))
/* 478 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not.Bacth"); 
/* 479 */           if (str.equals("disabled")) {
/* 480 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.disabled");
/*     */           }
/*     */           
/* 483 */           Box box = boxs.get(0);
/*     */           
/* 485 */           if (box.getStatus_freeze() == YesNo.YES) {
/* 486 */             return String.valueOf(lotSer) + "," + box.getLocation().getCode() + Properties.getPropertiesValye("scan.sync.error.lotSer.inexistence");
/*     */           }
/* 488 */           if (box.getLocation().getFreeae_status() == YesNo.YES) {
/* 489 */             return String.valueOf(lotSer) + "," + box.getLocation().getCode() + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 495 */           if (box.getLocation().getBasic_storeroom_id().getType() != StoreRoomType.RAWMATERIALSLIBRARY) {
/* 496 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotSer.inexistence");
/*     */           }
/* 498 */           if (box.getEnabled() == EnabledDisabled.DISABLED) {
/* 499 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotSer.inexistence");
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 507 */           List<SalesPreshiporderBatch> batchList = this.dao.getObjectList(" from SalesPreshiporderBatch batch where batch.spsoitemId.spsoId.code='" + order + "' and batch.box.lot.id='" + lotSer + "'");
/*     */ 
/*     */ 
/*     */           
/* 511 */           List<StorageLocation> locationList = this.dao.getObjectList(" from StorageLocation location where location.freeae_status='1' and location.basic_storeroom_id.type='9'");
/* 512 */           if (locationList.size() != 0) {
/* 513 */             StorageLocation location = locationList.get(0);
/* 514 */             if (salesPreshiporder.getType().intValue() == 1) {
/* 515 */               boolean sign = false;
/* 516 */               SalesPreshiporderItem item = null;
/* 517 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.soipitemId.itemNumber.id='" + box.getPart().getId() + "'");
/* 518 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 519 */                 if (salesPreshiporderItem.getStockQty().compareTo(salesPreshiporderItem.getDeliverynumber()) == -1) {
/* 520 */                   BigDecimal stockQty = salesPreshiporderItem.getStockQty().add(box.getNumber());
/* 521 */                   salesPreshiporderItem.setStockQty(stockQty);
/* 522 */                   this.dao.updateObject(salesPreshiporderItem);
/* 523 */                   item = salesPreshiporderItem;
/* 524 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 528 */               if (sign) {
/* 529 */                 ScanLog scanLog = new ScanLog();
/* 530 */                 scanLog.setDate(new Date());
/* 531 */                 scanLog.setDescribe(String.valueOf(lotSer) + "|" + location.getCode());
/* 532 */                 scanLog.setType(Integer.valueOf(15));
/* 533 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 534 */                 if (user != null) {
/* 535 */                   scanLog.setUserId(user);
/*     */                 }
/* 537 */                 this.scanLogDAO.insertScanLog(scanLog);
/*     */                 
/* 539 */                 if (batchList.size() > 0) {
/* 540 */                   SalesPreshiporderBatch bacth = batchList.get(0);
/* 541 */                   bacth.setEnabled(EnabledDisabled.DISABLED);
/* 542 */                   this.dao.updateObject(bacth);
/*     */                 } 
/*     */                 
/* 545 */                 InventoryMoving imr = new InventoryMoving();
/* 546 */                 imr.setOld_location(box.getLocation());
/* 547 */                 imr.setNew_location(location);
/* 548 */                 imr.setPart(box.getPart());
/* 549 */                 Site site = (Site)this.dao.getObject(Site.class, Integer.valueOf(2));
/* 550 */                 if (site != null) {
/* 551 */                   imr.setSite(site);
/*     */                 }
/* 553 */                 imr.setOldLotSer(box.getLot());
/* 554 */                 imr.setDomain("YA01");
/* 555 */                 imr.setQty(box.getNumber());
/* 556 */                 imr.setDate(new Date());
/* 557 */                 imr.setLotSer(box.getLot());
/* 558 */                 imr.setIs_sync(YesNo.NO);
/* 559 */                 imr.setRemark("扫描备货：" + box.getLot().getId());
/* 560 */                 this.dao.saveObject(imr);
/*     */                 
/* 562 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 563 */                 this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*     */                 
/* 565 */                 updateInventoryOccupiedDetial(box);
/*     */                 
/* 567 */                 SalesWorkorder salesWorkorder = new SalesWorkorder();
/* 568 */                 salesWorkorder.setLotSer(box.getLot());
/* 569 */                 salesWorkorder.setLocation(location);
/* 570 */                 salesWorkorder.setPart(box.getPart());
/* 571 */                 salesWorkorder.setCount(box.getNumber());
/* 572 */                 salesWorkorder.setScanUser(user);
/* 573 */                 salesWorkorder.setScanDate(new Date());
/* 574 */                 salesWorkorder.setIsSync(YesNo.NO);
/* 575 */                 if (item != null) {
/* 576 */                   salesWorkorder.setShipItemId(item);
/*     */                 }
/* 578 */                 salesWorkorder.setShipId(salesPreshiporder);
/* 579 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.ON_LOADINGBATCH);
/* 580 */                 this.dao.saveObject(salesWorkorder);
/*     */                 
/* 582 */                 box.setLocation(location);
/* 583 */                 this.dao.updateObject(box);
/* 584 */                 strBuffer.append("ok");
/* 585 */                 strBuffer.append(",");
/* 586 */                 strBuffer.append(location.getCode());
/*     */               } else {
/* 588 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.inexistence");
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 593 */               boolean sign = false;
/* 594 */               SalesPreshiporderItem item = null;
/* 595 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.customerPlanId.wmspart.id='" + box.getPart().getId() + "'");
/* 596 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 597 */                 if (salesPreshiporderItem.getStockQty().compareTo(salesPreshiporderItem.getDeliverynumber()) == -1) {
/* 598 */                   BigDecimal stockQty = salesPreshiporderItem.getStockQty().add(box.getNumber());
/* 599 */                   salesPreshiporderItem.setStockQty(stockQty);
/* 600 */                   this.dao.updateObject(salesPreshiporderItem);
/* 601 */                   item = salesPreshiporderItem;
/* 602 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 606 */               if (sign) {
/* 607 */                 ScanLog scanLog = new ScanLog();
/* 608 */                 scanLog.setDate(new Date());
/* 609 */                 scanLog.setDescribe(String.valueOf(lotSer) + "|" + location.getCode());
/* 610 */                 scanLog.setType(Integer.valueOf(15));
/* 611 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 612 */                 if (user != null) {
/* 613 */                   scanLog.setUserId(user);
/*     */                 }
/* 615 */                 this.scanLogDAO.insertScanLog(scanLog);
/*     */                 
/* 617 */                 if (batchList.size() > 0) {
/* 618 */                   SalesPreshiporderBatch bacth = batchList.get(0);
/* 619 */                   bacth.setEnabled(EnabledDisabled.DISABLED);
/* 620 */                   this.dao.updateObject(bacth);
/*     */                 } 
/* 622 */                 InventoryMoving imr = new InventoryMoving();
/* 623 */                 imr.setOld_location(box.getLocation());
/* 624 */                 imr.setNew_location(location);
/* 625 */                 imr.setPart(box.getPart());
/* 626 */                 Site site = (Site)this.dao.getObject(Site.class, Integer.valueOf(2));
/* 627 */                 if (site != null) {
/* 628 */                   imr.setSite(site);
/*     */                 }
/* 630 */                 imr.setOldLotSer(box.getLot());
/* 631 */                 imr.setDomain("YA01");
/* 632 */                 imr.setQty(box.getNumber());
/* 633 */                 imr.setDate(new Date());
/* 634 */                 imr.setLotSer(box.getLot());
/* 635 */                 imr.setIs_sync(YesNo.NO);
/* 636 */                 imr.setRemark("扫描备货：" + box.getLot().getId());
/* 637 */                 this.dao.saveObject(imr);
/*     */                 
/* 639 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 640 */                 this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*     */                 
/* 642 */                 updateInventoryOccupiedDetial(box);
/*     */                 
/* 644 */                 SalesWorkorder salesWorkorder = new SalesWorkorder();
/* 645 */                 salesWorkorder.setLotSer(box.getLot());
/* 646 */                 salesWorkorder.setLocation(location);
/* 647 */                 salesWorkorder.setPart(box.getPart());
/* 648 */                 salesWorkorder.setCount(box.getNumber());
/* 649 */                 salesWorkorder.setScanUser(user);
/* 650 */                 salesWorkorder.setScanDate(new Date());
/* 651 */                 salesWorkorder.setIsSync(YesNo.NO);
/* 652 */                 if (item != null) {
/* 653 */                   salesWorkorder.setShipItemId(item);
/*     */                 }
/* 655 */                 salesWorkorder.setShipId(salesPreshiporder);
/* 656 */                 salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.ON_LOADINGBATCH);
/* 657 */                 this.dao.saveObject(salesWorkorder);
/*     */                 
/* 659 */                 box.setLocation(location);
/* 660 */                 this.dao.updateObject(box);
/* 661 */                 strBuffer.append("ok");
/* 662 */                 strBuffer.append(",");
/* 663 */                 strBuffer.append(location.getCode());
/*     */               } else {
/* 665 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.inexistence");
/*     */               } 
/*     */             } 
/*     */           } else {
/* 669 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.StorageLocation.inexistence");
/*     */           } 
/*     */         } else {
/* 672 */           return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not");
/*     */         }  }
/* 674 */       else { return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not"); } 
/* 675 */     } catch (Exception e) {
/* 676 */       return String.valueOf(lotSer) + "," + e.getMessage();
/*     */     } 
/* 678 */     return strBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningSalesStockToWithdraw(String lotSer, String locationCode, String order, String userId) {
/*     */     try {
/* 684 */       SalesPreshiporder salesPreshiporder = this.dao.getSalesPreshiporder(order);
/* 685 */       if (salesPreshiporder != null)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 690 */         List<SalesWorkorder> salesWorkorderList = this.dao.getObjectList("from SalesWorkorder salesWorkorder where salesWorkorder.shipId.code = '" + order + "' and salesWorkorder.lotSer.id='" + lotSer + "'");
/*     */         
/* 692 */         if (salesWorkorderList.size() > 0) {
/* 693 */           SalesWorkorder salesWorkorder = salesWorkorderList.get(0);
/*     */           
/* 695 */           if (salesWorkorder.getStatus1() == SalesPreshiporderBatchStatus.IN_DELIVERYBATCH) {
/* 696 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.deliverybatch");
/*     */           }
/*     */           
/* 699 */           if (salesWorkorder.getStatus1() == SalesPreshiporderBatchStatus.SALES_RETURN) {
/* 700 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.Sales.Return");
/*     */           }
/*     */           
/* 703 */           List<SalesPreshiporderBatch> batchList = this.dao.getObjectList(" from SalesPreshiporderBatch batch where batch.spsoitemId.spsoId.code='" + order + "' and batch.box.lot.id='" + lotSer + "'");
/* 704 */           if (batchList.size() == 0) {
/* 705 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not.Bacth");
/*     */           }
/* 707 */           SalesPreshiporderBatch bacth = batchList.get(0);
/* 708 */           Box box = bacth.getBox();
/*     */           
/* 710 */           StorageLocation location = this.storageLocationManager.getStorageLocation(locationCode);
/* 711 */           if (location != null) {
/*     */             
/* 713 */             if (location.getFreeae_status() == YesNo.YES) {
/* 714 */               return String.valueOf(lotSer) + "," + box.getLocation().getCode() + Properties.getPropertiesValye("scan.sync.error.lotSerLocation.freeze");
/*     */             }
/*     */             
/* 717 */             if (salesPreshiporder.getType().intValue() == 1) {
/* 718 */               boolean sign = false;
/* 719 */               SalesPreshiporderItem item = null;
/* 720 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.soipitemId.itemNumber.id='" + box.getPart().getId() + "'");
/* 721 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 722 */                 if (salesPreshiporderItem.getStockQty().compareTo(new BigDecimal(0)) == 1) {
/* 723 */                   BigDecimal stockQty = salesPreshiporderItem.getStockQty().subtract(box.getNumber());
/* 724 */                   salesPreshiporderItem.setStockQty(stockQty);
/* 725 */                   this.dao.updateObject(salesPreshiporderItem);
/* 726 */                   item = salesPreshiporderItem;
/* 727 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 731 */               if (sign) {
/* 732 */                 ScanLog scanLog = new ScanLog();
/* 733 */                 scanLog.setDate(new Date());
/* 734 */                 scanLog.setDescribe(String.valueOf(lotSer) + "|" + location.getCode());
/* 735 */                 scanLog.setType(Integer.valueOf(44));
/* 736 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 737 */                 if (user != null) {
/* 738 */                   scanLog.setUserId(user);
/*     */                 }
/* 740 */                 this.scanLogDAO.insertScanLog(scanLog);
/*     */                 
/* 742 */                 bacth.setEnabled(EnabledDisabled.ENABLED);
/* 743 */                 this.dao.updateObject(bacth);
/*     */                 
/* 745 */                 InventoryMoving imr = new InventoryMoving();
/* 746 */                 imr.setOld_location(box.getLocation());
/* 747 */                 imr.setNew_location(location);
/* 748 */                 imr.setPart(box.getPart());
/* 749 */                 Site site = (Site)this.dao.getObject(Site.class, Integer.valueOf(2));
/* 750 */                 if (site != null) {
/* 751 */                   imr.setSite(site);
/*     */                 }
/* 753 */                 imr.setOldLotSer(box.getLot());
/* 754 */                 imr.setDomain("YA01");
/* 755 */                 imr.setQty(box.getNumber());
/* 756 */                 imr.setDate(new Date());
/* 757 */                 imr.setLotSer(box.getLot());
/* 758 */                 imr.setIs_sync(YesNo.NO);
/* 759 */                 imr.setRemark("备货撤回：" + box.getLot().getId());
/* 760 */                 this.dao.saveObject(imr);
/*     */                 
/* 762 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 763 */                 this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*     */                 
/* 765 */                 box.setLocation(location);
/* 766 */                 this.dao.updateObject(box);
/*     */                 
/* 768 */                 insertInventoryOccupiedDetial(box);
/*     */                 
/* 770 */                 this.dao.removeObject(salesWorkorder);
/*     */               } else {
/*     */                 
/* 773 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.inexistence");
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 778 */               boolean sign = false;
/* 779 */               SalesPreshiporderItem item = null;
/* 780 */               List<SalesPreshiporderItem> itemList = this.dao.getObjectList(" from SalesPreshiporderItem salesPreshiporderItem where salesPreshiporderItem.spsoId.code='" + order + "' and salesPreshiporderItem.customerPlanId.wmspart.id='" + box.getPart().getId() + "'");
/* 781 */               for (SalesPreshiporderItem salesPreshiporderItem : itemList) {
/* 782 */                 if (salesPreshiporderItem.getStockQty().compareTo(new BigDecimal(0)) == 1) {
/* 783 */                   BigDecimal stockQty = salesPreshiporderItem.getStockQty().subtract(box.getNumber());
/* 784 */                   salesPreshiporderItem.setStockQty(stockQty);
/* 785 */                   this.dao.updateObject(salesPreshiporderItem);
/* 786 */                   item = salesPreshiporderItem;
/* 787 */                   sign = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 791 */               if (sign) {
/* 792 */                 ScanLog scanLog = new ScanLog();
/* 793 */                 scanLog.setDate(new Date());
/* 794 */                 scanLog.setDescribe(String.valueOf(lotSer) + "|" + location.getCode());
/* 795 */                 scanLog.setType(Integer.valueOf(44));
/* 796 */                 User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 797 */                 if (user != null) {
/* 798 */                   scanLog.setUserId(user);
/*     */                 }
/* 800 */                 this.scanLogDAO.insertScanLog(scanLog);
/*     */                 
/* 802 */                 bacth.setEnabled(EnabledDisabled.ENABLED);
/* 803 */                 this.dao.updateObject(bacth);
/*     */                 
/* 805 */                 InventoryMoving imr = new InventoryMoving();
/* 806 */                 imr.setOld_location(box.getLocation());
/* 807 */                 imr.setNew_location(location);
/* 808 */                 imr.setPart(box.getPart());
/* 809 */                 Site site = (Site)this.dao.getObject(Site.class, Integer.valueOf(2));
/* 810 */                 if (site != null) {
/* 811 */                   imr.setSite(site);
/*     */                 }
/* 813 */                 imr.setOldLotSer(box.getLot());
/* 814 */                 imr.setDomain("YA01");
/* 815 */                 imr.setQty(box.getNumber());
/* 816 */                 imr.setDate(new Date());
/* 817 */                 imr.setLotSer(box.getLot());
/* 818 */                 imr.setIs_sync(YesNo.NO);
/* 819 */                 imr.setRemark("备货撤回：" + box.getLot().getId());
/* 820 */                 this.dao.saveObject(imr);
/*     */                 
/* 822 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.COMMONOUT, Boolean.valueOf(true));
/* 823 */                 this.inventoryManager.inventoryAdjustmentByWoLot(location.getCode(), box, InventoryType.COMMONIN, Boolean.valueOf(false));
/*     */                 
/* 825 */                 box.setLocation(location);
/* 826 */                 this.dao.updateObject(box);
/*     */ 
/*     */                 
/* 829 */                 insertInventoryOccupiedDetial(box);
/*     */                 
/* 831 */                 this.dao.removeObject(salesWorkorder);
/*     */               } else {
/* 833 */                 return String.valueOf(box.getPart().getId()) + "," + Properties.getPropertiesValye("scan.sync.error.partId.inexistence");
/*     */               } 
/*     */             } 
/*     */           } else {
/* 837 */             return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.StorageLocation.inexistence");
/*     */           } 
/*     */         } else {
/* 840 */           return String.valueOf(lotSer) + "," + Properties.getPropertiesValye("scan.sync.error.lotser.is.not.salesPreshiporder");
/*     */         }  }
/* 842 */       else { return String.valueOf(order) + "," + Properties.getPropertiesValye("scan.sync.error.salesPreshiporder.not"); } 
/* 843 */     } catch (Exception e) {
/* 844 */       return String.valueOf(lotSer) + "," + e.getMessage();
/*     */     } 
/* 846 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> insertSalesPreshiporderConfirm(String[] soItemIdsList, String[] deliveryNumbersList, String[] remarksList, String date, User user) {
/* 852 */     Map<String, BigDecimal> mapList = new HashMap<String, BigDecimal>();
/* 853 */     for (int i = 0; i < soItemIdsList.length; i++) {
/* 854 */       if (!deliveryNumbersList[i].equals("")) {
/*     */ 
/*     */         
/* 857 */         String soItemIds = soItemIdsList[i];
/* 858 */         String deca = deliveryNumbersList[i];
/* 859 */         BigDecimal deliveryNumber = new BigDecimal(deca);
/* 860 */         SalesOrderItem salesOrderItem = this.salesOrderItemManager.getById(Integer.valueOf(Integer.parseInt(soItemIds)));
/* 861 */         if (mapList.get(salesOrderItem.getItemNumber().getId()) == null) {
/* 862 */           mapList.put(salesOrderItem.getItemNumber().getId(), deliveryNumber);
/*     */         } else {
/* 864 */           BigDecimal count = ((BigDecimal)mapList.get(salesOrderItem.getItemNumber().getId())).add(deliveryNumber);
/* 865 */           mapList.put(salesOrderItem.getItemNumber().getId(), count);
/*     */         } 
/*     */       } 
/*     */     }  try {
/* 869 */       for (String part : mapList.keySet()) {
/* 870 */         InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 871 */         List<Map<String, Object>> list = tool.getinvenDetialByPartBycount(part, mapList.get(part));
/* 872 */         Map<String, Object> map = list.get(0);
/* 873 */         if (map.get("result") == "1") {
/* 874 */           return map;
/*     */         }
/*     */       } 
/* 877 */       insertSalesPreshiporderByItem(soItemIdsList, deliveryNumbersList, 
/* 878 */           remarksList, date, user);
/* 879 */     } catch (Exception e) {
/* 880 */       e.fillInStackTrace();
/* 881 */       System.out.println(e.getMessage());
/*     */     } 
/* 883 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/* 884 */     resultMap.put("result", Integer.valueOf(0));
/* 885 */     return resultMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> insertCustomerPlanPreshiporderConfirm(String[] soItemIdsList, String[] deliveryNumbersList, String[] remarksList, String date, User user) {
/* 891 */     Map<String, BigDecimal> mapList = new HashMap<String, BigDecimal>();
/* 892 */     for (int i = 0; i < soItemIdsList.length; i++) {
/* 893 */       if (!deliveryNumbersList[i].equals("")) {
/*     */ 
/*     */         
/* 896 */         String soItemIds = soItemIdsList[i];
/* 897 */         String deca = deliveryNumbersList[i];
/* 898 */         BigDecimal deliveryNumber = new BigDecimal(deca);
/* 899 */         CustomerPlan customerPlan = this.customerPlanManager.getById(Integer.valueOf(Integer.parseInt(soItemIds)));
/* 900 */         if (mapList.get(customerPlan.getWmspart().getId()) == null) {
/* 901 */           mapList.put(customerPlan.getWmspart().getId(), deliveryNumber);
/*     */         } else {
/* 903 */           BigDecimal count = ((BigDecimal)mapList.get(customerPlan.getWmspart().getId())).add(deliveryNumber);
/* 904 */           mapList.put(customerPlan.getWmspart().getId(), count);
/*     */         } 
/*     */       } 
/*     */     }  try {
/* 908 */       for (String part : mapList.keySet()) {
/* 909 */         InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 910 */         List<Map<String, Object>> list = tool.getinvenDetialByPartBycount(part, mapList.get(part));
/* 911 */         Map<String, Object> map = list.get(0);
/* 912 */         if (map.get("result") == "1") {
/* 913 */           return map;
/*     */         }
/*     */       } 
/* 916 */       insertCustomerPlanPreshiporderByItem(soItemIdsList, deliveryNumbersList, 
/* 917 */           remarksList, date, user);
/* 918 */     } catch (Exception e) {
/* 919 */       e.fillInStackTrace();
/* 920 */       System.out.println(e.getMessage());
/*     */     } 
/* 922 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/* 923 */     resultMap.put("result", Integer.valueOf(0));
/* 924 */     return resultMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertSalesPickingOrderReplaceBatchByItem(SalesPreshiporder order, Box box) {}
/*     */ 
/*     */   
/*     */   public void insertSalesPickingOrderReplaceBatchByItem(SalesPreshiporderBatch batch) {
/* 933 */     updateInventoryOccupiedDetial(batch.getBox());
/* 934 */     InventoryTool tool = new InventoryTool((DAO)this.dao);
/* 935 */     List<Map> resultList = tool.getSalesPreshiporderByLotSer(batch.getBox().getPart().getId(), batch.getBox().getNumber());
/* 936 */     if (resultList != null) {
/* 937 */       BigDecimal amountTop = new BigDecimal(0);
/* 938 */       for (Map map3 : resultList) {
/*     */ 
/*     */         
/* 941 */         Box box = (Box)map3.get("id");
/*     */         
/* 943 */         String lotset = (String)map3.get("lotset");
/*     */         
/* 945 */         batch.setBox(box);
/* 946 */         if (lotset != null) {
/* 947 */           batch.setStatus(YesNo.YES);
/*     */         } else {
/* 949 */           batch.setStatus(YesNo.NO);
/*     */         } 
/*     */         
/* 952 */         this.dao.updateObject(batch);
/* 953 */         insertInventoryOccupiedDetial(box);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void updateIsMatchStatus(String arrays) {
/* 958 */     String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 959 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 960 */       SalesPreshiporder salesPreshiporder = this.dao.getById(Integer.valueOf(Integer.parseInt(id)));
/* 961 */       salesPreshiporder.setMatchStatus(YesNo.YES);
/* 962 */       this.dao.update(salesPreshiporder);
/*     */       b++; }
/*     */   
/*     */   }
/*     */   public StorageLocationManager getStorageLocationManager() {
/* 967 */     return this.storageLocationManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/* 972 */     this.storageLocationManager = storageLocationManager;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/impl/SalesPreshiporderManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */