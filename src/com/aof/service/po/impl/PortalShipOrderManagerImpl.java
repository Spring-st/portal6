/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.po.PortalShipOrderDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PortalShipOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PortalShipOrder;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.model.po.query.PortalShipOrderQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.po.PortalShipOrderManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.shcnc.utils.UUID;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
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
/*     */ public class PortalShipOrderManagerImpl
/*     */   extends BaseManager
/*     */   implements PortalShipOrderManager
/*     */ {
/*     */   private PortalShipOrderDAO dao;
/*     */   private PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager;
/*     */   
/*     */   public PurchaseOrderInspectionPendingManager getPurchaseOrderInspectionPendingManager() {
/*  48 */     return this.purchaseOrderInspectionPendingManager;
/*     */   }
/*     */   
/*     */   public void setPurchaseOrderInspectionPendingManager(PurchaseOrderInspectionPendingManager purchaseOrderInspectionPendingManager) {
/*  52 */     this.purchaseOrderInspectionPendingManager = purchaseOrderInspectionPendingManager;
/*     */   }
/*     */   
/*     */   public void setDao(PortalShipOrderDAO dao) {
/*  56 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public PortalShipOrder getPortalShipOrder(Integer id) {
/*  61 */     return this.dao.getPortalShipOrder(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPortalShipOrderListCount(Map conditions) {
/*  66 */     return this.dao.getPortalShipOrderListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPortalShipOrderList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
/*  72 */     return this.dao.getPortalShipOrderList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrder(PortalShipOrder po) {
/*  77 */     return this.dao.insertPortalShipOrder(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrder(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
/*  82 */     ps.setCreateDate(new Date());
/*  83 */     ps.setSite(site);
/*     */     
/*  85 */     ps.setEnabled(EnabledDisabled.ENABLED);
/*  86 */     ps.setStatus(PortalShipOrderStatus.DRAFT);
/*  87 */     ps.setCode(getPortalShipOrderCode(new Date(), requestor));
/*  88 */     ps.setArrivalDate(arrDate);
/*     */     
/*  90 */     String uuuid = UUID.getUUID();
/*     */     
/*  92 */     return this.dao.insertPortalShipOrder(ps);
/*     */   }
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrderSupplier(PortalShipOrder ps, Site site, Supplier supplier, Date arrDate) {
/*  97 */     ps.setCreateDate(new Date());
/*  98 */     ps.setSite(site);
/*     */     
/* 100 */     ps.setEnabled(EnabledDisabled.ENABLED);
/* 101 */     ps.setStatus(PortalShipOrderStatus.DRAFT);
/*     */     
/* 103 */     ps.setCode(getPortalShipOrderCodeSupplier(new Date(), supplier));
/*     */     
/* 105 */     ps.setArrivalDate(arrDate);
/*     */     
/* 107 */     String uuuid = UUID.getUUID();
/*     */     
/* 109 */     return this.dao.insertPortalShipOrder(ps);
/*     */   }
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrderByJitPart(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
/* 114 */     ps.setCreateDate(new Date());
/* 115 */     ps.setSite(site);
/*     */     
/* 117 */     ps.setEnabled(EnabledDisabled.ENABLED);
/* 118 */     ps.setStatus(PortalShipOrderStatus.DRAFT);
/* 119 */     ps.setCode(getPortalShipOrderCode(new Date(), requestor));
/*     */ 
/*     */     
/* 122 */     String uuuid = UUID.getUUID();
/*     */     
/* 124 */     return this.dao.insertPortalShipOrder(ps);
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
/*     */   private String getPortalShipOrderCode(Date date, User requestor) {
/* 146 */     StringBuffer sb = new StringBuffer("S");
/* 147 */     String supplierCode = requestor.getLoginName();
/* 148 */     sb.append(supplierCode);
/* 149 */     String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
/* 150 */     sb.append(dateStr);
/* 151 */     List<String> PoMaxList = this.dao.getObjectList("select max(po.code) from PortalShipOrder po where po.code like 'S" + 
/* 152 */         supplierCode + dateStr + "%'");
/* 153 */     Integer serialNumber = Integer.valueOf(0);
/* 154 */     if (PoMaxList != null && PoMaxList.size() != 0 && PoMaxList.get(0) != null && !((String)PoMaxList.get(0)).equals("null") && !((String)PoMaxList.get(0)).equals("NULL")) {
/* 155 */       String PoMax = PoMaxList.get(0);
/* 156 */       serialNumber = Integer.valueOf(Integer.parseInt(PoMax.substring(PoMax.length() - 3, PoMax.length())));
/*     */     } 
/* 158 */     DecimalFormat df = new DecimalFormat("000");
/* 159 */     String serialNumbers = df.format((serialNumber.intValue() + 1));
/* 160 */     sb.append(serialNumbers);
/* 161 */     return sb.toString();
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
/*     */   private String getPortalShipOrderCodeSupplier(Date date, Supplier supplier) {
/* 183 */     StringBuffer sb = new StringBuffer("S");
/*     */     
/* 185 */     sb.append(supplier.getCode());
/* 186 */     String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
/* 187 */     sb.append(dateStr);
/* 188 */     List<String> PoMaxList = this.dao.getObjectList("select max(po.code) from PortalShipOrder po where po.code like 'S" + 
/* 189 */         supplier.getCode() + dateStr + "%'");
/* 190 */     Integer serialNumber = Integer.valueOf(0);
/* 191 */     if (PoMaxList != null && PoMaxList.size() != 0 && PoMaxList.get(0) != null && !((String)PoMaxList.get(0)).equals("null") && !((String)PoMaxList.get(0)).equals("NULL")) {
/* 192 */       String PoMax = PoMaxList.get(0);
/* 193 */       serialNumber = Integer.valueOf(Integer.parseInt(PoMax.substring(PoMax.length() - 3, PoMax.length())));
/*     */     } 
/* 195 */     DecimalFormat df = new DecimalFormat("000");
/* 196 */     String serialNumbers = df.format((serialNumber.intValue() + 1));
/* 197 */     sb.append(serialNumbers);
/* 198 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public PortalShipOrder updatePortalShipOrder(PortalShipOrder po) {
/* 202 */     return this.dao.updatePortalShipOrder(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledPortalShipOrderList() {
/* 207 */     return this.dao.getEnabledPortalShipOrderList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortalShipOrderItem> getPortalShipOrderItemListByOrderId(Integer id) {
/* 212 */     return this.dao.getPortalShipOrderItemListByOrderId(id);
/*     */   }
/*     */   
/*     */   public List<Box> getBoxByShipOrderId(Integer id) {
/* 216 */     return this.dao.getBoxByShipOrderId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PortalShipOrderItem> getAllShipOrderItem() {
/* 222 */     return this.dao.getAllShipOrderItem();
/*     */   }
/*     */   public void deletePortalShipOrder(PortalShipOrder portalShipOrder) {
/* 225 */     this.dao.deletePortalShipOrder(portalShipOrder);
/*     */   }
/*     */   
/*     */   public void deletePurchaseOrderBox(Integer id) {
/* 229 */     this.dao.deletePurchaseOrderBox(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
/* 234 */     this.dao.deletePortalShipOrderItem(portalShipOrderItem);
/*     */   }
/*     */   public PortalShipOrderItem getPortalShipOrderItem(Integer id) {
/* 237 */     return this.dao.getPortalShipOrderItem(id);
/*     */   }
/*     */   public List<Box> getBoxList(Integer id) {
/* 240 */     return this.dao.getObjectList("from Box box where box.psoItem.id='" + id + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public void createPortalShipOrderIP(PortalShipOrder portalShipOrder) {
/* 245 */     List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
/* 246 */     PortalShipOrder poip = null;
/* 247 */     for (PortalShipOrderItem portalShipOrderItem : poilist) {
/*     */       
/* 249 */       String supplierCode = portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getCode();
/*     */       
/* 251 */       String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
/* 252 */       String itemNumber = portalShipOrderItem.getPoipItem().getItemNumber().getId();
/* 253 */       BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
/* 254 */       BigDecimal capacity = new BigDecimal(1);
/*     */ 
/*     */       
/* 257 */       capacity = portalShipOrderItem.getPoipItem().getQty_std();
/* 258 */       if (capacity == null || capacity.intValue() == 0)
/*     */       {
/* 260 */         capacity = portalShipOrderItem.getPart().getOrd_mult();
/*     */       }
/*     */       
/* 263 */       Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
/* 264 */       Double doubleCapacity = Double.valueOf(capacity.doubleValue());
/* 265 */       Integer boxCount = Integer.valueOf(0);
/* 266 */       BigDecimal halfATankCount = null;
/* 267 */       if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
/* 268 */         Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
/* 269 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } else {
/* 271 */         halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
/* 272 */         Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
/* 273 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } 
/*     */       
/* 276 */       for (int i = 1; i <= boxCount.intValue(); i++) {
/* 277 */         Box poipiBox = new Box();
/* 278 */         poipiBox.setPsoItem(portalShipOrderItem);
/* 279 */         poipiBox.setPart(portalShipOrderItem.getPoipItem().getItemNumber());
/* 280 */         poipiBox.setInStorageNumber(new BigDecimal(0));
/* 281 */         poipiBox.setReceivedNumber(new BigDecimal(0));
/* 282 */         poipiBox.setReturnsNumber(new BigDecimal(0));
/* 283 */         poipiBox.setVetoQCnumber(new BigDecimal(0));
/* 284 */         poipiBox.setVetoReceivedNumber(new BigDecimal(0));
/*     */         
/* 286 */         poipiBox.setIsReceipt(YesNo.NO);
/*     */         
/* 288 */         poipiBox.setIsPrint(YesNo.NO);
/* 289 */         poipiBox.setStatus_freeze(YesNo.NO);
/* 290 */         poipiBox.setPrint_number(Integer.valueOf(0));
/* 291 */         poipiBox.setStatus(BoxStatus.Wait);
/* 292 */         poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 293 */         poipiBox.setStatus_print(YesNo.NO);
/* 294 */         poipiBox.setEnabled(EnabledDisabled.ENABLED);
/* 295 */         poipiBox.setDate(portalShipOrder.getCreateDate());
/* 296 */         poipiBox.setType(YesNo.NO);
/*     */         
/* 298 */         poipiBox.setPo_number(portalShipOrderItem.getPoipItem().getPoip_number().getPo_number());
/* 299 */         poipiBox.setPo_supp(portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getCode());
/* 300 */         poipiBox.setPo_line(portalShipOrderItem.getPoipItem().getLine());
/* 301 */         poipiBox.setPo_date(portalShipOrderItem.getPoipItem().getDueDate());
/* 302 */         poipiBox.setPo_supp_name(portalShipOrderItem.getPoipItem().getPoip_number().getSupplier().getName());
/* 303 */         if (halfATankCount != null && i == boxCount.intValue()) {
/* 304 */           poipiBox.setNumber(halfATankCount);
/*     */         } else {
/* 306 */           poipiBox.setNumber(capacity);
/*     */         } 
/*     */         
/* 309 */         List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + 
/* 310 */             itemNumber + "/" + dateStr + "/" + "%'");
/* 311 */         Integer serialNumber = Integer.valueOf(0);
/* 312 */         if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
/* 313 */           String lotMax = lotMaxList.get(0);
/*     */           
/* 315 */           serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
/*     */         } 
/* 317 */         DecimalFormat df = new DecimalFormat("0000");
/*     */ 
/*     */         
/* 320 */         String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
/* 321 */         WmsLot lot = new WmsLot(lotSerId);
/* 322 */         lot.setEnabled(EnabledDisabled.ENABLED);
/*     */         try {
/* 324 */           this.dao.saveObject(lot);
/* 325 */           poipiBox.setLot(lot);
/* 326 */         } catch (Exception e) {
/*     */           
/* 328 */           e.printStackTrace();
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 333 */         this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertPortalShipOrderItem(PortalShipOrderItem portalShipOrderItem) {
/* 342 */     this.dao.saveObject(portalShipOrderItem);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createPortalShipOrderByJitPartIP(PortalShipOrder portalShipOrder) {
/* 348 */     List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
/* 349 */     for (PortalShipOrderItem portalShipOrderItem : poilist) {
/*     */       
/* 351 */       String supplierCode = portalShipOrderItem.getPortalShipOrder().getSupplier().getCode();
/*     */       
/* 353 */       String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
/* 354 */       String itemNumber = portalShipOrderItem.getPart().getId();
/* 355 */       BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
/* 356 */       BigDecimal capacity = new BigDecimal(1);
/*     */ 
/*     */       
/* 359 */       capacity = portalShipOrderItem.getPart().getOrd_mult();
/*     */       
/* 361 */       Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
/* 362 */       Double doubleCapacity = Double.valueOf(capacity.doubleValue());
/* 363 */       Integer boxCount = Integer.valueOf(0);
/* 364 */       BigDecimal halfATankCount = null;
/* 365 */       if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
/* 366 */         Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
/* 367 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } else {
/* 369 */         halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
/* 370 */         Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
/* 371 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } 
/*     */       
/* 374 */       for (int i = 1; i <= boxCount.intValue(); i++) {
/* 375 */         Box poipiBox = new Box();
/* 376 */         poipiBox.setPsoItem(portalShipOrderItem);
/* 377 */         poipiBox.setPart(portalShipOrderItem.getPart());
/* 378 */         poipiBox.setInStorageNumber(new BigDecimal(0));
/* 379 */         poipiBox.setReceivedNumber(new BigDecimal(0));
/* 380 */         poipiBox.setReturnsNumber(new BigDecimal(0));
/* 381 */         poipiBox.setVetoQCnumber(new BigDecimal(0));
/* 382 */         poipiBox.setVetoReceivedNumber(new BigDecimal(0));
/*     */         
/* 384 */         poipiBox.setIsReceipt(YesNo.NO);
/*     */         
/* 386 */         poipiBox.setIsPrint(YesNo.NO);
/* 387 */         poipiBox.setStatus_freeze(YesNo.NO);
/* 388 */         poipiBox.setPrint_number(Integer.valueOf(0));
/* 389 */         poipiBox.setStatus(BoxStatus.Wait);
/* 390 */         poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 391 */         poipiBox.setStatus_print(YesNo.NO);
/* 392 */         poipiBox.setEnabled(EnabledDisabled.ENABLED);
/* 393 */         poipiBox.setDate(portalShipOrder.getCreateDate());
/* 394 */         poipiBox.setType(YesNo.NO);
/*     */         
/* 396 */         poipiBox.setPo_supp(portalShipOrderItem.getPortalShipOrder().getSupplier().getCode());
/* 397 */         poipiBox.setPo_date(new Date());
/* 398 */         poipiBox.setPo_supp_name(portalShipOrderItem.getPortalShipOrder().getSupplier().getName());
/* 399 */         if (halfATankCount != null && i == boxCount.intValue()) {
/* 400 */           poipiBox.setNumber(halfATankCount);
/*     */         } else {
/* 402 */           poipiBox.setNumber(capacity);
/*     */         } 
/*     */         
/* 405 */         List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + 
/* 406 */             itemNumber + "/" + dateStr + "/" + "%'");
/* 407 */         Integer serialNumber = Integer.valueOf(0);
/* 408 */         if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
/* 409 */           String lotMax = lotMaxList.get(0);
/*     */           
/* 411 */           serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
/*     */         } 
/* 413 */         DecimalFormat df = new DecimalFormat("0000");
/*     */ 
/*     */         
/* 416 */         String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
/* 417 */         WmsLot lot = new WmsLot(lotSerId);
/* 418 */         lot.setEnabled(EnabledDisabled.ENABLED);
/*     */         try {
/* 420 */           this.dao.saveObject(lot);
/* 421 */           poipiBox.setLot(lot);
/* 422 */         } catch (Exception e) {
/*     */           
/* 424 */           e.printStackTrace();
/*     */         } 
/* 426 */         this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrderByNpoPart(PortalShipOrder ps, Site site, User requestor, Date arrDate) {
/* 435 */     ps.setCreateDate(new Date());
/* 436 */     ps.setSite(site);
/*     */     
/* 438 */     ps.setEnabled(EnabledDisabled.ENABLED);
/* 439 */     ps.setStatus(PortalShipOrderStatus.DRAFT);
/* 440 */     ps.setCode(getPortalShipOrderCode(new Date(), requestor));
/*     */ 
/*     */     
/* 443 */     String uuuid = UUID.getUUID();
/*     */     
/* 445 */     return this.dao.insertPortalShipOrder(ps);
/*     */   }
/*     */   
/*     */   public PortalShipOrder insertPortalShipOrderByNpoPartSupplier(PortalShipOrder ps, Site site, Supplier supplier, Date arrDate) {
/* 449 */     ps.setCreateDate(new Date());
/* 450 */     ps.setSite(site);
/*     */     
/* 452 */     ps.setEnabled(EnabledDisabled.ENABLED);
/* 453 */     ps.setStatus(PortalShipOrderStatus.DRAFT);
/* 454 */     ps.setCode(getPortalShipOrderCodeSupplier(new Date(), supplier));
/*     */ 
/*     */     
/* 457 */     String uuuid = UUID.getUUID();
/*     */     
/* 459 */     return this.dao.insertPortalShipOrder(ps);
/*     */   }
/*     */   
/*     */   public void createPortalShipOrderByNpoPartIP(PortalShipOrder portalShipOrder) {
/* 463 */     List<PortalShipOrderItem> poilist = this.dao.getPortalShipOrderItemListByOrderId(portalShipOrder.getId());
/* 464 */     for (PortalShipOrderItem portalShipOrderItem : poilist) {
/*     */       
/* 466 */       String supplierCode = portalShipOrderItem.getPortalShipOrder().getSupplier().getCode();
/*     */       
/* 468 */       String dateStr = (new SimpleDateFormat("yyMMdd")).format(new Date());
/* 469 */       String itemNumber = portalShipOrderItem.getPart().getId();
/* 470 */       BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
/* 471 */       BigDecimal capacity = new BigDecimal(1);
/*     */ 
/*     */       
/* 474 */       capacity = portalShipOrderItem.getPart().getOrd_mult();
/*     */       
/* 476 */       Double doubleDeliveryNumber = Double.valueOf(deliveryNumber.doubleValue());
/* 477 */       Double doubleCapacity = Double.valueOf(capacity.doubleValue());
/* 478 */       Integer boxCount = Integer.valueOf(0);
/* 479 */       BigDecimal halfATankCount = null;
/* 480 */       if (doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue() == 0.0D) {
/* 481 */         Double number = Double.valueOf(doubleDeliveryNumber.doubleValue() / doubleCapacity.doubleValue());
/* 482 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } else {
/* 484 */         halfATankCount = new BigDecimal(doubleDeliveryNumber.doubleValue() % doubleCapacity.doubleValue());
/* 485 */         Double number = Double.valueOf((doubleDeliveryNumber.doubleValue() - halfATankCount.doubleValue()) / doubleCapacity.doubleValue() + 1.0D);
/* 486 */         boxCount = Integer.valueOf(number.intValue());
/*     */       } 
/*     */       
/* 489 */       for (int i = 1; i <= boxCount.intValue(); i++) {
/* 490 */         Box poipiBox = new Box();
/* 491 */         poipiBox.setPsoItem(portalShipOrderItem);
/* 492 */         poipiBox.setPart(portalShipOrderItem.getPart());
/* 493 */         poipiBox.setInStorageNumber(new BigDecimal(0));
/* 494 */         poipiBox.setReceivedNumber(new BigDecimal(0));
/* 495 */         poipiBox.setReturnsNumber(new BigDecimal(0));
/* 496 */         poipiBox.setVetoQCnumber(new BigDecimal(0));
/* 497 */         poipiBox.setVetoReceivedNumber(new BigDecimal(0));
/*     */         
/* 499 */         poipiBox.setIsReceipt(YesNo.NO);
/*     */         
/* 501 */         poipiBox.setIsPrint(YesNo.NO);
/* 502 */         poipiBox.setStatus_freeze(YesNo.NO);
/* 503 */         poipiBox.setPrint_number(Integer.valueOf(0));
/* 504 */         poipiBox.setStatus(BoxStatus.Wait);
/* 505 */         poipiBox.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 506 */         poipiBox.setStatus_print(YesNo.NO);
/* 507 */         poipiBox.setEnabled(EnabledDisabled.ENABLED);
/* 508 */         poipiBox.setDate(portalShipOrder.getCreateDate());
/* 509 */         poipiBox.setType(YesNo.NO);
/*     */         
/* 511 */         poipiBox.setPo_supp(portalShipOrderItem.getPortalShipOrder().getSupplier().getCode());
/* 512 */         poipiBox.setPo_date(new Date());
/* 513 */         poipiBox.setPo_supp_name(portalShipOrderItem.getPortalShipOrder().getSupplier().getName());
/* 514 */         if (halfATankCount != null && i == boxCount.intValue()) {
/* 515 */           poipiBox.setNumber(halfATankCount);
/*     */         } else {
/* 517 */           poipiBox.setNumber(capacity);
/*     */         } 
/*     */         
/* 520 */         List<String> lotMaxList = this.dao.getObjectList("select max(lot.id) from WmsLot lot where lot.id like '" + 
/* 521 */             itemNumber + "/" + dateStr + "/" + "%'");
/* 522 */         Integer serialNumber = Integer.valueOf(0);
/* 523 */         if (lotMaxList != null && lotMaxList.size() != 0 && lotMaxList.get(0) != null && !((String)lotMaxList.get(0)).equals("null") && !((String)lotMaxList.get(0)).equals("NULL")) {
/* 524 */           String lotMax = lotMaxList.get(0);
/*     */           
/* 526 */           serialNumber = Integer.valueOf(Integer.parseInt(lotMax.substring(lotMax.length() - 4, lotMax.length())));
/*     */         } 
/* 528 */         DecimalFormat df = new DecimalFormat("0000");
/*     */ 
/*     */         
/* 531 */         String lotSerId = String.valueOf(itemNumber) + "/" + dateStr + "/" + df.format((serialNumber.intValue() + 1));
/* 532 */         WmsLot lot = new WmsLot(lotSerId);
/* 533 */         lot.setEnabled(EnabledDisabled.ENABLED);
/*     */         try {
/* 535 */           this.dao.saveObject(lot);
/* 536 */           poipiBox.setLot(lot);
/* 537 */         } catch (Exception e) {
/*     */           
/* 539 */           e.printStackTrace();
/*     */         } 
/* 541 */         this.purchaseOrderInspectionPendingManager.insertBox(poipiBox);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteDeliveryPo(String id) {
/*     */     try {
/* 550 */       boolean bol = false;
/*     */       
/* 552 */       PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 554 */       List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
/* 555 */       if (itemList.size() != 0) {
/*     */         
/* 557 */         for (PortalShipOrderItem portalShipOrderItem : itemList) {
/* 558 */           List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
/* 559 */           for (Box box : boxList) {
/* 560 */             this.dao.removeObject(box.getLot());
/* 561 */             this.dao.removeObject(box);
/*     */           } 
/*     */           
/* 564 */           BigDecimal deliveryNumber = portalShipOrderItem.getDeliveryNumber();
/* 565 */           PurchaseOrderInspectionPendingItem pendingItem = portalShipOrderItem.getPoipItem();
/* 566 */           pendingItem.setQtyOpen(pendingItem.getQtyOpen().add(deliveryNumber));
/* 567 */           this.dao.updateObject(pendingItem);
/* 568 */           this.dao.removeObject(portalShipOrderItem);
/*     */         } 
/* 570 */         bol = true;
/*     */       } 
/* 572 */       if (bol) {
/* 573 */         this.dao.removeObject(shipOrder);
/*     */       }
/* 575 */     } catch (Exception e) {
/*     */       
/* 577 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteDeliveryNPo(String id) {
/*     */     try {
/* 584 */       boolean bol = false;
/*     */       
/* 586 */       PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 588 */       List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
/* 589 */       if (itemList.size() != 0) {
/*     */         
/* 591 */         for (PortalShipOrderItem portalShipOrderItem : itemList) {
/* 592 */           List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
/* 593 */           for (Box box : boxList) {
/* 594 */             this.dao.removeObject(box.getLot());
/* 595 */             this.dao.removeObject(box);
/*     */           } 
/* 597 */           this.dao.removeObject(portalShipOrderItem);
/*     */         } 
/* 599 */         bol = true;
/*     */       } 
/* 601 */       if (bol) {
/* 602 */         this.dao.removeObject(shipOrder);
/*     */       }
/* 604 */     } catch (Exception e) {
/*     */       
/* 606 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteDeliveryJit(String id) {
/*     */     try {
/* 613 */       boolean bol = false;
/*     */       
/* 615 */       PortalShipOrder shipOrder = (PortalShipOrder)this.dao.getObject(PortalShipOrder.class, Integer.valueOf(Integer.parseInt(id)));
/*     */       
/* 617 */       List<PortalShipOrderItem> itemList = this.dao.getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + id + " and (item.isSync!=1 or item.isSync is null)");
/* 618 */       if (itemList.size() != 0) {
/*     */         
/* 620 */         for (PortalShipOrderItem portalShipOrderItem : itemList) {
/* 621 */           List<Box> boxList = this.dao.getObjectList(" from Box box where box.psoItem.id=" + portalShipOrderItem.getId() + " and (box.isSync!=1 or box.isSync is null) ");
/* 622 */           for (Box box : boxList) {
/* 623 */             this.dao.removeObject(box.getLot());
/* 624 */             this.dao.removeObject(box);
/*     */           } 
/* 626 */           this.dao.removeObject(portalShipOrderItem);
/*     */         } 
/* 628 */         bol = true;
/*     */       } 
/* 630 */       if (bol) {
/* 631 */         this.dao.removeObject(shipOrder);
/*     */       }
/* 633 */     } catch (Exception e) {
/*     */       
/* 635 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PortalShipOrderManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */