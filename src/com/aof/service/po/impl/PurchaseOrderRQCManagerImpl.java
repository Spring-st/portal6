/*     */ package com.aof.service.po.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.po.PurchaseOrderRQCDAO;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderRqcStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.PurchaseOrderRqc;
/*     */ import com.aof.model.po.PurchaseOrderRqcBox;
/*     */ import com.aof.model.po.PurchaseOrderRqcUnqualified;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.model.po.query.PurchaseOrderRqcQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.BadReasonsManager;
/*     */ import com.aof.service.po.PurchaseOrderRQCManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PurchaseOrderRQCManagerImpl
/*     */   extends BaseManager implements PurchaseOrderRQCManager {
/*     */   private PurchaseOrderRQCDAO dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   private BadReasonsManager badReasonsManager;
/*     */   
/*     */   public void setBadReasonsManager(BadReasonsManager badReasonsManager) {
/*  38 */     this.badReasonsManager = badReasonsManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  42 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setDao(PurchaseOrderRQCDAO dao) {
/*  46 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderRqc getPurchaseOrderRqc(Integer id) {
/*  51 */     return this.dao.getPurchaseOrderRqc(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLastPoApplicationCode() {
/*  56 */     return this.dao.getLastPoApplicationCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/*  61 */     return this.dao.getMaxPoApplicationIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPurchaseOrderRqcListCount(Map conditions) {
/*  66 */     return this.dao.getPurchaseOrderRqcListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPurchaseOrderRqcList(Map conditions, int pageNo, int pageSize, PurchaseOrderRqcQueryOrder order, boolean descend) {
/*  72 */     return this.dao.getPurchaseOrderRqcList(conditions, pageNo, pageSize, order, 
/*  73 */         descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderRqc insertPurchaseOrderRqc(PurchaseOrderRqc po) {
/*  78 */     return this.dao.insertPurchaseOrderRqc(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public PurchaseOrderRqc updatePurchaseOrderRqc(PurchaseOrderRqc po) {
/*  83 */     return this.dao.updatePurchaseOrderRqc(po);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledPurchaseOrderRqcList() {
/*  88 */     return this.dao.getEnabledPurchaseOrderRqcList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deletePurchaseOrderRqc(PurchaseOrderRqc po) {
/*  93 */     this.dao.deletePurchaseOrderRqc(po);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String scanningPurchaseOrderRQC(String lotSer, String type, String reason, String userId) {
/*  99 */     ScanLog scanLog = new ScanLog();
/* 100 */     scanLog.setDate(new Date());
/* 101 */     scanLog.setDescribe(lotSer);
/* 102 */     scanLog.setType(Integer.valueOf(2));
/* 103 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 104 */     if (user != null) {
/* 105 */       scanLog.setUserId(user);
/*     */     }
/* 107 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 110 */       List<Box> boxs = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + "' ");
/* 111 */       if (boxs.size() > 0) {
/* 112 */         int num = 0;
/* 113 */         String strsub = "";
/* 114 */         Box box = boxs.get(0);
/* 115 */         String sql = "from PurchaseOrderRqcBox rqcB where rqcB.box_id.lot.id = '" + lotSer + "' ";
/* 116 */         List<PurchaseOrderRqcBox> list = this.dao.getObjectList(sql);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 195 */         PurchaseOrderRqc rqc = new PurchaseOrderRqc();
/* 196 */         PurchaseOrderInspectionPendingItem item = null;
/* 197 */         if (box.getSingle() == null) {
/* 198 */           item = box.getPsoItem().getPoipItem();
/* 199 */           rqc.setPoipItem(item);
/* 200 */           rqc.setQty(box.getPsoItem().getPoipItem().getQty());
/*     */         } else {
/* 202 */           item = box.getSingle().getPo_detial_id();
/* 203 */           rqc.setPoipItem(item);
/* 204 */           rqc.setSingle(box.getSingle());
/* 205 */           rqc.setQty(box.getSingle().getDelivery_qty());
/*     */         } 
/* 207 */         rqc.setBoxId(box);
/* 208 */         rqc.setStatus(PurchaseOrderRqcStatus.Wait);
/* 209 */         rqc.setCreate_date(new Date());
/*     */         
/* 211 */         rqc.setNeed_qty_rqc(box.getNumber());
/* 212 */         rqc.setActual_qty_rqc(box.getNumber());
/* 213 */         rqc.setRemark(reason);
/*     */         
/* 215 */         rqc.setPart(box.getPart());
/* 216 */         rqc.setPo_date(item.getPoip_number().getCreateDate());
/* 217 */         rqc.setPo_number(item.getPoip_number().getPo_number());
/* 218 */         rqc.setPo_qty(item.getQty());
/* 219 */         rqc.setLine(item.getLine());
/* 220 */         rqc.setSupper(item.getPoip_number().getSupplier());
/*     */         
/* 222 */         if (type.equals("0")) {
/* 223 */           box.setStatus_rqc(BoxStatusRqc.QUALIFIED);
/* 224 */           box.setStatus_freeze(YesNo.NO);
/* 225 */           if (rqc.getQualified_qty() != null) {
/* 226 */             rqc.setQualified_qty(rqc.getQualified_qty().add(box.getNumber()));
/*     */           } else {
/* 228 */             rqc.setQualified_qty(box.getNumber());
/*     */           } 
/* 230 */           rqc.setUnqualified_qty(new BigDecimal(0));
/*     */         }
/*     */         else {
/*     */           
/* 234 */           BigDecimal sum = new BigDecimal(0);
/* 235 */           String[] reasons = reason.split(";"); byte b; int i; String[] arrayOfString1;
/* 236 */           for (i = (arrayOfString1 = reasons).length, b = 0; b < i; ) { String strs = arrayOfString1[b];
/* 237 */             String[] str = strs.split(",");
/* 238 */             String id = str[0];
/*     */ 
/*     */ 
/*     */             
/* 242 */             BadReasons badReasons = this.badReasonsManager.getBadReasons(Integer.valueOf(Integer.parseInt(id)));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 247 */             PurchaseOrderRqcUnqualified unqualified = new PurchaseOrderRqcUnqualified();
/*     */             
/* 249 */             unqualified.setQty(box.getNumber());
/* 250 */             unqualified.setReasons(badReasons);
/* 251 */             unqualified.setRqc_box_id(box);
/* 252 */             this.dao.saveObject(unqualified);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 272 */             box.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
/* 273 */             box.setStatus_freeze(YesNo.YES);
/*     */             
/* 275 */             sum = sum.add(box.getNumber());
/*     */ 
/*     */             
/*     */             b++; }
/*     */ 
/*     */           
/* 281 */           if (rqc.getUnqualified_qty() == null) {
/* 282 */             rqc.setUnqualified_qty(new BigDecimal(0));
/*     */           }
/*     */           
/* 285 */           rqc.setUnqualified_qty(rqc.getUnqualified_qty().add(sum));
/*     */           
/* 287 */           BigDecimal qualified_qty = box.getNumber().subtract(sum);
/* 288 */           rqc.setQualified_qty(qualified_qty);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         PurchaseOrderRqcBox rqcBox = new PurchaseOrderRqcBox();
/* 295 */         rqcBox.setBox_id(box);
/* 296 */         rqcBox.setRqc_id(rqc);
/*     */         
/* 298 */         this.dao.updateObject(box);
/* 299 */         this.dao.saveObject(rqcBox);
/* 300 */         this.dao.saveObject(rqc);
/*     */       } else {
/*     */         
/* 303 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.is.null");
/*     */       } 
/* 305 */     } catch (Exception e) {
/* 306 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 309 */     return "ok";
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Box> insertBox(Box box, String str) {
/* 314 */     List<Box> boxList = new ArrayList<Box>();
/*     */ 
/*     */     
/* 317 */     String supp = "";
/* 318 */     if (box.getSingle() == null && box.getPsoItem() != null) {
/* 319 */       supp = box.getPsoItem().getPoipItem().getPoip_number()
/* 320 */         .getSupplier().getCode();
/* 321 */     } else if (box.getSingle() != null && box.getPsoItem() == null) {
/* 322 */       supp = box.getSingle().getPo_detial_id().getPoip_number()
/* 323 */         .getSupplier().getCode();
/*     */     } else {
/* 325 */       supp = box.getPo_supp();
/*     */     } 
/*     */ 
/*     */     
/* 329 */     Box boxNew = new Box();
/* 330 */     WmsLot wl = (new InventoryTool((DAO)this.dao)).insertWOBox1(supp, "yyyyMMdd", 
/* 331 */         box.getLot().getId());
/* 332 */     boxNew.setLot(wl);
/* 333 */     boxNew.setPsoItem(box.getPsoItem());
/* 334 */     boxNew.setDate(box.getDate());
/* 335 */     boxNew.setIn_date(box.getIn_date());
/* 336 */     boxNew.setOut_date(box.getOut_date());
/* 337 */     boxNew.setLocation(box.getLocation());
/* 338 */     boxNew.setPart(box.getPart());
/* 339 */     boxNew.setNumber(new BigDecimal(str));
/* 340 */     boxNew.setReceivedNumber(new BigDecimal(0));
/* 341 */     boxNew.setInStorageNumber(new BigDecimal(0));
/* 342 */     boxNew.setPrint_number(Integer.valueOf(0));
/* 343 */     boxNew.setStatus(box.getStatus());
/* 344 */     boxNew.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
/* 345 */     boxNew.setStatus_freeze(YesNo.YES);
/* 346 */     boxNew.setSupplierCode(box.getSupplierCode());
/*     */     
/* 348 */     boxNew.setEnabled(EnabledDisabled.ENABLED);
/* 349 */     boxNew.setIsPrint(YesNo.NO);
/* 350 */     boxNew.setPo_number(box.getPo_number());
/* 351 */     boxNew.setPo_line(box.getPo_line());
/* 352 */     boxNew.setPo_date(box.getPo_date());
/* 353 */     boxNew.setPo_supp(box.getPo_supp());
/* 354 */     boxNew.setSingle(box.getSingle());
/* 355 */     boxNew.setPo_supp_name(box.getPo_supp_name());
/* 356 */     this.dao.saveObject(boxNew);
/*     */     
/* 358 */     boxList.add(boxNew);
/*     */     
/* 360 */     return boxList;
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
/*     */   private void insertBox(Box box, BigDecimal qualified_qty, BigDecimal unqualified_qty) {
/* 373 */     if (box.getPsoItem() != null) {
/* 374 */       String supp = box.getPsoItem().getPoipItem().getPoip_number().getSupplier().getCode();
/*     */ 
/*     */       
/* 377 */       Box boxN = new Box();
/* 378 */       WmsLot wlN = (new InventoryTool((DAO)this.dao)).insertNBox(supp, "yyyyMMdd", box.getDate());
/* 379 */       boxN.setLot(wlN);
/* 380 */       boxN.setDate(box.getDate());
/* 381 */       boxN.setIn_date(box.getIn_date());
/* 382 */       boxN.setOut_date(box.getOut_date());
/* 383 */       boxN.setLocation(box.getLocation());
/* 384 */       boxN.setPart(box.getPart());
/* 385 */       boxN.setNumber(unqualified_qty);
/* 386 */       boxN.setPrint_number(Integer.valueOf(0));
/* 387 */       boxN.setStatus(box.getStatus());
/* 388 */       boxN.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
/* 389 */       boxN.setStatus_freeze(YesNo.YES);
/* 390 */       boxN.setSupplierCode(box.getSupplierCode());
/* 391 */       boxN.setEnabled(EnabledDisabled.ENABLED);
/* 392 */       boxN.setIsPrint(YesNo.NO);
/* 393 */       if (box.getSingle() == null) {
/* 394 */         boxN.setPsoItem(box.getPsoItem());
/*     */       } else {
/* 396 */         boxN.setSingle(box.getSingle());
/*     */       } 
/*     */       
/* 399 */       boxN.setPo_number(box.getPo_number());
/* 400 */       boxN.setPo_line(box.getPo_line());
/* 401 */       boxN.setPo_date(box.getPo_date());
/* 402 */       boxN.setPo_supp(box.getPo_supp());
/* 403 */       this.dao.saveObject(boxN);
/*     */ 
/*     */       
/* 406 */       Box boxY = new Box();
/* 407 */       WmsLot wlY = (new InventoryTool((DAO)this.dao)).insertWOBox(supp, "yyyyMMdd", box.getDate());
/* 408 */       boxY.setLot(wlY);
/* 409 */       boxY.setDate(box.getDate());
/* 410 */       boxY.setIn_date(box.getIn_date());
/* 411 */       boxY.setOut_date(box.getOut_date());
/* 412 */       boxY.setLocation(box.getLocation());
/* 413 */       boxY.setPart(box.getPart());
/* 414 */       boxY.setNumber(qualified_qty);
/* 415 */       boxY.setPrint_number(Integer.valueOf(0));
/* 416 */       boxY.setStatus(box.getStatus());
/* 417 */       boxY.setStatus_rqc(BoxStatusRqc.QUALIFIED);
/* 418 */       boxY.setStatus_freeze(YesNo.NO);
/* 419 */       boxY.setSupplierCode(box.getSupplierCode());
/* 420 */       boxY.setEnabled(EnabledDisabled.ENABLED);
/*     */       
/* 422 */       if (box.getSingle() == null) {
/* 423 */         boxY.setPsoItem(box.getPsoItem());
/*     */       } else {
/* 425 */         boxY.setSingle(box.getSingle());
/*     */       } 
/*     */       
/* 428 */       boxY.setIsPrint(YesNo.NO);
/* 429 */       boxY.setPo_number(box.getPo_number());
/* 430 */       boxY.setPo_line(box.getPo_line());
/* 431 */       boxY.setPo_date(box.getPo_date());
/* 432 */       boxY.setPo_supp(box.getPo_supp());
/* 433 */       this.dao.saveObject(boxY);
/*     */     } 
/*     */   }
/*     */   public String purchaseOrderRejectedMaterialBoxRQC(String arrays, String type, String strList, User user) {
/* 437 */     String[] array = arrays.split(",");
/* 438 */     String mfg = ""; byte b; int i; String[] arrayOfString1;
/* 439 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 440 */       String str = scanningPoRejectedMaterialBoxRQC(id, type, strList, user.getId().toString());
/* 441 */       mfg = String.valueOf(mfg) + str + ",";
/*     */       b++; }
/*     */     
/* 444 */     return mfg;
/*     */   }
/*     */   
/*     */   public String systemPurchaseOrderRQC(String arrays, String type, String strList, User user) {
/* 448 */     String[] array = arrays.split(",");
/* 449 */     String mfg = ""; byte b; int i; String[] arrayOfString1;
/* 450 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 451 */       String str = scanningPurchaseOrderRQC(id, type, strList, user.getId().toString());
/* 452 */       mfg = String.valueOf(mfg) + str + ",";
/*     */       b++; }
/*     */     
/* 455 */     return mfg;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] scanningRqcProgress() {
/* 460 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningPoRejectedMaterialBoxRQC(String lotSer, String type, String reason, String userId) {
/*     */     try {
/* 466 */       List<Box> boxs = this.dao.getObjectList("from Box box where box.lot.id = '" + lotSer + "' ");
/* 467 */       if (boxs.size() > 0) {
/* 468 */         int num = 0;
/* 469 */         String strsub = "";
/* 470 */         Box box = boxs.get(0);
/* 471 */         String sql = "from PurchaseOrderRqcBox rqcB where rqcB.box_id.lot.id = '" + lotSer + "' ";
/* 472 */         List<PurchaseOrderRqcBox> list = this.dao.getObjectList(sql);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 551 */         PurchaseOrderRqc rqc = new PurchaseOrderRqc();
/* 552 */         PurchaseOrderInspectionPendingItem item = null;
/* 553 */         if (box.getSingle() == null) {
/* 554 */           item = box.getPsoItem().getPoipItem();
/* 555 */           rqc.setPoipItem(item);
/* 556 */           rqc.setQty(box.getPsoItem().getPoipItem().getQty());
/*     */         } else {
/* 558 */           item = box.getSingle().getPo_detial_id();
/* 559 */           rqc.setPoipItem(item);
/* 560 */           rqc.setSingle(box.getSingle());
/* 561 */           rqc.setQty(box.getSingle().getDelivery_qty());
/*     */         } 
/* 563 */         rqc.setBoxId(box);
/* 564 */         rqc.setStatus(PurchaseOrderRqcStatus.Wait);
/* 565 */         rqc.setCreate_date(new Date());
/*     */         
/* 567 */         rqc.setNeed_qty_rqc(box.getNumber());
/* 568 */         rqc.setActual_qty_rqc(box.getNumber());
/* 569 */         rqc.setRemark(reason);
/*     */         
/* 571 */         rqc.setPart(box.getPart());
/* 572 */         rqc.setPo_date(item.getPoip_number().getCreateDate());
/* 573 */         rqc.setPo_number(item.getPoip_number().getPo_number());
/* 574 */         rqc.setPo_qty(item.getQty());
/* 575 */         rqc.setLine(item.getLine());
/* 576 */         rqc.setSupper(item.getPoip_number().getSupplier());
/*     */         
/* 578 */         if (type.equals("0")) {
/* 579 */           box.setStatus_rqc(BoxStatusRqc.QUALIFIED);
/* 580 */           box.setStatus_freeze(YesNo.NO);
/* 581 */           if (rqc.getQualified_qty() != null) {
/* 582 */             rqc.setQualified_qty(rqc.getQualified_qty().add(box.getNumber()));
/*     */           } else {
/* 584 */             rqc.setQualified_qty(box.getNumber());
/*     */           } 
/* 586 */           rqc.setUnqualified_qty(new BigDecimal(0));
/*     */         }
/*     */         else {
/*     */           
/* 590 */           BigDecimal sum = new BigDecimal(0);
/* 591 */           String[] reasons = reason.split(";"); byte b; int i; String[] arrayOfString1;
/* 592 */           for (i = (arrayOfString1 = reasons).length, b = 0; b < i; ) { String strs = arrayOfString1[b];
/* 593 */             String[] str = strs.split(",");
/* 594 */             String id = str[0];
/*     */ 
/*     */ 
/*     */             
/* 598 */             BadReasons badReasons = this.badReasonsManager.getBadReasons(Integer.valueOf(Integer.parseInt(id)));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 603 */             PurchaseOrderRqcUnqualified unqualified = new PurchaseOrderRqcUnqualified();
/*     */             
/* 605 */             unqualified.setQty(box.getNumber());
/* 606 */             unqualified.setReasons(badReasons);
/* 607 */             unqualified.setRqc_box_id(box);
/* 608 */             this.dao.saveObject(unqualified);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 628 */             box.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
/* 629 */             box.setStatus_freeze(YesNo.YES);
/*     */             
/* 631 */             sum = sum.add(box.getNumber());
/*     */ 
/*     */             
/*     */             b++; }
/*     */ 
/*     */           
/* 637 */           if (rqc.getUnqualified_qty() == null) {
/* 638 */             rqc.setUnqualified_qty(new BigDecimal(0));
/*     */           }
/*     */           
/* 641 */           rqc.setUnqualified_qty(rqc.getUnqualified_qty().add(sum));
/*     */           
/* 643 */           BigDecimal qualified_qty = box.getNumber().subtract(sum);
/* 644 */           rqc.setQualified_qty(qualified_qty);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 650 */         PurchaseOrderRqcBox rqcBox = new PurchaseOrderRqcBox();
/* 651 */         rqcBox.setBox_id(box);
/* 652 */         rqcBox.setRqc_id(rqc);
/*     */         
/* 654 */         this.dao.updateObject(box);
/* 655 */         this.dao.saveObject(rqcBox);
/* 656 */         this.dao.saveObject(rqc);
/*     */       } else {
/*     */         
/* 659 */         return String.valueOf(lotSer) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.is.null");
/*     */       } 
/* 661 */     } catch (Exception e) {
/* 662 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 665 */     return "ok";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PurchaseOrderRQCManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */