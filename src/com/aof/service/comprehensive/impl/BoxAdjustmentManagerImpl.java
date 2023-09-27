/*     */ package com.aof.service.comprehensive.impl;
/*     */ 
/*     */ import com.aof.dao.DAO;
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.comprehensive.BoxAdjustmentDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.comprehensive.BoxAdjustment;
/*     */ import com.aof.model.comprehensive.BoxAdjustmentBox;
/*     */ import com.aof.model.comprehensive.query.BoxAdjustmentQueryOrder;
/*     */ import com.aof.model.inventory.InventoryMoving;
/*     */ import com.aof.model.metadata.BoxAdjustmentType;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.InventoryTool;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.comprehensive.BoxAdjustmentManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BoxAdjustmentManagerImpl
/*     */   extends BaseManager
/*     */   implements BoxAdjustmentManager
/*     */ {
/*     */   private BoxAdjustmentDAO dao;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  39 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setDao(BoxAdjustmentDAO dao) {
/*  43 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public BoxAdjustment getBoxAdjustment(Integer id) {
/*  48 */     return this.dao.getBoxAdjustment(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBoxAdjustmentListCount(Map conditions) {
/*  53 */     return this.dao.getBoxAdjustmentListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBoxAdjustmentList(Map conditions, int pageNo, int pageSize, BoxAdjustmentQueryOrder order, boolean descend) {
/*  59 */     return this.dao.getBoxAdjustmentList(conditions, pageNo, pageSize, order, 
/*  60 */         descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public BoxAdjustment insertBoxAdjustment(BoxAdjustment batchAdjustment) {
/*  65 */     return this.dao.insertBoxAdjustment(batchAdjustment);
/*     */   }
/*     */ 
/*     */   
/*     */   public BoxAdjustment updateBoxAdjustment(BoxAdjustment batchAdjustment) {
/*  70 */     return this.dao.updateBoxAdjustment(batchAdjustment);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxBoxAdjustmentIdBeginWith(String prefix) {
/*  75 */     return this.dao.getMaxBoxAdjustmentIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public String ListBoxInfoNumber(String lot) {
/*  80 */     List<Box> list = this.dao.getObjectList(" from Box box where box.lot.id = '" + 
/*  81 */         lot + "'");
/*  82 */     String str = "";
/*  83 */     String strnum = "";
/*  84 */     if (list.size() > 0) {
/*  85 */       Box box = list.get(0);
/*  86 */       BigDecimal big = box.getNumber();
/*  87 */       str = big.toString();
/*  88 */       strnum = str.substring(0, str.indexOf("."));
/*     */     } else {
/*  90 */       return "Null";
/*     */     } 
/*  92 */     return strnum;
/*     */   }
/*     */ 
/*     */   
/*     */   public String insertBoxAdjustmentBox(String lot, String str, String type, String userId) {
/*  97 */     String lotIdNum = "";
/*  98 */     String[] newlot = lot.split(",");
/*  99 */     if (type.equals("0")) {
/* 100 */       String[] arrayOfString; if ((arrayOfString = newlot).length != 0) { String id = arrayOfString[0];
/* 101 */         List<Box> list = this.dao
/* 102 */           .getObjectList("from Box box where box.lot.id = '" + id + 
/* 103 */             "'");
/* 104 */         if (list.size() > 0) {
/* 105 */           Box box = list.get(0);
/*     */           
/* 107 */           List<Box> boxs = insertBox(box, str);
/*     */ 
/*     */           
/* 110 */           BoxAdjustment adjustment = new BoxAdjustment();
/* 111 */           adjustment.setDate(new Date());
/* 112 */           adjustment.setNew_box_id(box);
/* 113 */           adjustment.setOld_location(box.getLocation());
/* 114 */           adjustment.setOperation(new User(Integer.valueOf(Integer.parseInt(userId))));
/* 115 */           adjustment.setType(BoxAdjustmentType.BREAKUP);
/* 116 */           this.dao.insertBoxAdjustment(adjustment);
/*     */ 
/*     */           
/* 119 */           for (Box boxNew : boxs) {
/* 120 */             BoxAdjustmentBox adjustmentBox = new BoxAdjustmentBox();
/* 121 */             adjustmentBox.setBox_adjustment_id(adjustment);
/* 122 */             adjustmentBox.setBox_id(boxNew);
/* 123 */             this.dao.saveObject(adjustmentBox);
/* 124 */             String lotId = boxNew.getLot().getId();
/* 125 */             String num = boxNew.getNumber().toString();
/* 126 */             lotIdNum = String.valueOf(lotIdNum) + lotId + "," + num + ";";
/* 127 */             if (boxNew.getLocation() != null && box.getLocation() != null) {
/* 128 */               systemMoveLocation(boxNew, box, userId, Integer.valueOf(1));
/*     */             }
/*     */           } 
/*     */         } 
/* 132 */         return lotIdNum; }
/*     */     
/*     */     } else {
/*     */       
/* 136 */       BigDecimal sum = new BigDecimal(0);
/* 137 */       Date date = null;
/* 138 */       List<BoxAdjustment> listBox = new ArrayList<BoxAdjustment>();
/* 139 */       Box box = null; byte b; int i; String[] arrayOfString;
/* 140 */       for (i = (arrayOfString = newlot).length, b = 0; b < i; ) { String id = arrayOfString[b];
/*     */         try {
/* 142 */           List<Box> list = this.dao
/* 143 */             .getObjectList("from Box box where box.lot.id = '" + 
/* 144 */               id + "'");
/* 145 */           if (list.size() > 0) {
/* 146 */             box = list.get(0);
/* 147 */             sum = sum.add(box.getNumber());
/* 148 */             if (date == null)
/*     */             {
/* 150 */               date = box.getDate();
/*     */             }
/*     */ 
/*     */             
/* 154 */             box.setEnabled(EnabledDisabled.DISABLED);
/*     */ 
/*     */             
/* 157 */             this.dao.updateObject(box);
/*     */ 
/*     */             
/* 160 */             BoxAdjustment adjustment = new BoxAdjustment();
/* 161 */             adjustment.setDate(new Date());
/* 162 */             adjustment.setNew_box_id(box);
/* 163 */             adjustment.setOld_location(box.getLocation());
/* 164 */             adjustment.setOperation(new User(
/* 165 */                   Integer.valueOf(Integer.parseInt(userId))));
/* 166 */             adjustment.setType(BoxAdjustmentType.MERGE);
/* 167 */             this.dao.insertBoxAdjustment(adjustment);
/*     */             
/* 169 */             listBox.add(adjustment);
/*     */           } 
/* 171 */         } catch (Exception e) {
/* 172 */           e.getMessage();
/*     */         } 
/*     */         b++; }
/*     */       
/* 176 */       Box newBox = insertBoxByMerge(box, date, sum);
/*     */ 
/*     */       
/* 179 */       for (BoxAdjustment adjustment : listBox) {
/* 180 */         BoxAdjustmentBox adjustmentBox = new BoxAdjustmentBox();
/* 181 */         adjustmentBox.setBox_adjustment_id(adjustment);
/* 182 */         adjustmentBox.setBox_id(newBox);
/* 183 */         this.dao.saveObject(adjustmentBox);
/*     */       } 
/* 185 */       if (newBox.getLocation() != null && box.getLocation() != null) {
/* 186 */         systemMoveLocation(newBox, box, userId, Integer.valueOf(2));
/*     */       }
/*     */     } 
/* 189 */     return "ok";
/*     */   }
/*     */   public String systemMoveLocation(Box box, Box oldBox, String userId, Integer type) {
/*     */     try {
/* 193 */       ScanLog scanLog = new ScanLog();
/* 194 */       scanLog.setDate(new Date());
/* 195 */       scanLog.setDescribe(String.valueOf(box.getLot().getId()) + "|" + oldBox.getLocation().getCode());
/* 196 */       scanLog.setType(Integer.valueOf(15));
/* 197 */       User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 198 */       if (user != null) {
/* 199 */         scanLog.setUserId(user);
/*     */       }
/* 201 */       this.scanLogDAO.insertScanLog(scanLog);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       InventoryMoving imr = new InventoryMoving();
/* 229 */       imr.setOld_location(box.getLocation());
/* 230 */       imr.setNew_location(oldBox.getLocation());
/* 231 */       imr.setPart(box.getPart());
/* 232 */       imr.setDomain("YA01");
/* 233 */       Site site = (Site)this.dao.getObject(Site.class, Integer.valueOf(2));
/* 234 */       if (site != null) {
/* 235 */         imr.setSite(site);
/*     */       }
/* 237 */       imr.setQty(box.getNumber());
/* 238 */       imr.setDate(new Date());
/* 239 */       imr.setLotSer(box.getLot());
/* 240 */       imr.setOldLotSer(oldBox.getLot());
/* 241 */       imr.setIs_sync(YesNo.NO);
/* 242 */       imr.setRemark("移库扫描：" + box.getLot().getId());
/* 243 */       this.dao.saveObject(imr);
/*     */       
/* 245 */       box.setLocation(oldBox.getLocation());
/* 246 */       this.dao.updateObject(box);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 254 */     catch (Exception e) {
/* 255 */       System.out.println(e.getMessage());
/* 256 */       return e.getMessage();
/*     */     } 
/* 258 */     return "ok";
/*     */   }
/*     */   
/*     */   private Box insertBoxByMerge(Box box, Date date, BigDecimal sum) {
/* 262 */     String suppCode = box.getPo_supp();
/*     */ 
/*     */     
/* 265 */     WmsLot wl = (new InventoryTool((DAO)this.dao)).insertWOBoxAdjustment(box.getPo_supp(), 
/* 266 */         "yyyyMMdd", box.getLot().getId());
/*     */     
/* 268 */     Box boxNew = new Box();
/* 269 */     boxNew.setLot(wl);
/* 270 */     boxNew.setPsoItem(box.getPsoItem());
/* 271 */     boxNew.setDate(box.getDate());
/* 272 */     boxNew.setIn_date(box.getIn_date());
/* 273 */     boxNew.setOut_date(box.getOut_date());
/* 274 */     boxNew.setLocation(box.getLocation());
/* 275 */     boxNew.setPart(box.getPart());
/* 276 */     boxNew.setNumber(sum);
/* 277 */     boxNew.setPrint_number(Integer.valueOf(0));
/* 278 */     boxNew.setStatus(box.getStatus());
/* 279 */     boxNew.setStatus_rqc(box.getStatus_rqc());
/* 280 */     boxNew.setStatus_freeze(box.getStatus_freeze());
/* 281 */     boxNew.setSupplierCode(box.getSupplierCode());
/* 282 */     boxNew.setEnabled(EnabledDisabled.ENABLED);
/* 283 */     boxNew.setIsPrint(YesNo.NO);
/* 284 */     boxNew.setPo_number(box.getPo_number());
/* 285 */     boxNew.setPo_line(box.getPo_line());
/* 286 */     boxNew.setPo_date(box.getPo_date());
/* 287 */     boxNew.setPo_supp(box.getPo_supp());
/* 288 */     boxNew.setSingle(box.getSingle());
/* 289 */     boxNew.setPo_supp_name(box.getPo_supp_name());
/* 290 */     this.dao.saveObject(boxNew);
/* 291 */     return boxNew;
/*     */   }
/*     */   
/*     */   private List<Box> insertBox(Box box, String str) {
/* 295 */     List<Box> boxList = new ArrayList<Box>();
/*     */     
/* 297 */     String[] array = str.split(",");
/* 298 */     String supp = "";
/* 299 */     if (box.getSingle() == null && box.getPsoItem() != null) {
/* 300 */       supp = box.getPsoItem().getPoipItem().getPoip_number()
/* 301 */         .getSupplier().getCode();
/* 302 */     } else if (box.getSingle() != null && box.getPsoItem() == null) {
/* 303 */       supp = box.getSingle().getPo_detial_id().getPoip_number()
/* 304 */         .getSupplier().getCode();
/*     */     } else {
/* 306 */       supp = box.getPo_supp();
/*     */     }  byte b; int i;
/*     */     String[] arrayOfString1;
/* 309 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String arr = arrayOfString1[b];
/* 310 */       Box boxNew = new Box();
/*     */ 
/*     */       
/* 313 */       WmsLot wl = (new InventoryTool((DAO)this.dao)).insertWOBoxAdjustment(supp, "yyyyMMdd", 
/* 314 */           box.getLot().getId());
/* 315 */       boxNew.setLot(wl);
/* 316 */       boxNew.setPsoItem(box.getPsoItem());
/* 317 */       boxNew.setDate(box.getDate());
/* 318 */       boxNew.setIn_date(box.getIn_date());
/* 319 */       boxNew.setOut_date(box.getOut_date());
/* 320 */       boxNew.setLocation(box.getLocation());
/* 321 */       boxNew.setPart(box.getPart());
/* 322 */       boxNew.setNumber(new BigDecimal(arr));
/* 323 */       boxNew.setReceivedNumber(new BigDecimal(arr));
/* 324 */       boxNew.setInStorageNumber(new BigDecimal(arr));
/* 325 */       boxNew.setPrint_number(Integer.valueOf(0));
/* 326 */       boxNew.setStatus(box.getStatus());
/* 327 */       boxNew.setStatus_rqc(box.getStatus_rqc());
/* 328 */       boxNew.setStatus_freeze(box.getStatus_freeze());
/* 329 */       boxNew.setSupplierCode(box.getSupplierCode());
/* 330 */       boxNew.setEnabled(EnabledDisabled.ENABLED);
/* 331 */       boxNew.setIsPrint(YesNo.NO);
/* 332 */       boxNew.setPo_number(box.getPo_number());
/* 333 */       boxNew.setPo_line(box.getPo_line());
/* 334 */       boxNew.setPo_date(box.getPo_date());
/* 335 */       boxNew.setPo_supp(box.getPo_supp());
/* 336 */       boxNew.setSingle(box.getSingle());
/* 337 */       boxNew.setPo_supp_name(box.getPo_supp_name());
/* 338 */       this.dao.saveObject(boxNew);
/*     */       
/* 340 */       boxList.add(boxNew);
/*     */       
/*     */       b++; }
/*     */     
/* 344 */     box.setEnabled(EnabledDisabled.DISABLED);
/*     */ 
/*     */     
/* 347 */     this.dao.updateObject(box);
/* 348 */     return boxList;
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningLotBreakUp(String lotSer, String nums, String userId) {
/* 353 */     ScanLog scanLog = new ScanLog();
/* 354 */     scanLog.setDate(new Date());
/* 355 */     scanLog.setDescribe(String.valueOf(lotSer) + ":" + nums);
/* 356 */     scanLog.setType(Integer.valueOf(10));
/* 357 */     User user = (User)this.scanLogDAO.getObject(User.class, 
/* 358 */         Integer.valueOf(Integer.parseInt(userId)));
/* 359 */     if (user != null) {
/* 360 */       scanLog.setUserId(user);
/*     */     }
/* 362 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 365 */       List<Box> list = this.dao
/* 366 */         .getObjectList("from Box box where box.lot.id = '" + lotSer + 
/* 367 */           "' and box.enabled =0 and box.status_freeze=1 ");
/* 368 */       if (list.size() > 0)
/* 369 */       { Box box = list.get(0);
/* 370 */         String supp = box.getPsoItem().getPoipItem().getPoip_number()
/* 371 */           .getSupplier().getCode();
/* 372 */         Date date = box.getPsoItem().getPortalShipOrder()
/* 373 */           .getCreateDate();
/*     */         
/* 375 */         BoxAdjustment adjustment = new BoxAdjustment();
/* 376 */         adjustment.setDate(new Date());
/* 377 */         adjustment.setNew_box_id(box);
/* 378 */         adjustment.setOld_location(box.getLocation());
/* 379 */         adjustment.setOperation(user);
/* 380 */         adjustment.setType(BoxAdjustmentType.BREAKUP);
/* 381 */         this.dao.insertBoxAdjustment(adjustment);
/* 382 */         String[] arrays = nums.split(",");
/* 383 */         List<Box> boxs = new ArrayList<Box>(); byte b; int i; String[] arrayOfString1;
/* 384 */         for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String qty = arrayOfString1[b];
/* 385 */           Box boxEnd = insertBox(supp, box.getPart(), 
/* 386 */               new BigDecimal(qty), date);
/* 387 */           boxs.add(boxEnd);
/*     */           b++; }
/*     */         
/* 390 */         for (Box boxNew : boxs) {
/* 391 */           BoxAdjustmentBox adjustmentBox = new BoxAdjustmentBox();
/* 392 */           adjustmentBox.setBox_adjustment_id(adjustment);
/* 393 */           adjustmentBox.setBox_id(boxNew);
/* 394 */           this.dao.saveObject(adjustmentBox);
/*     */         } 
/*     */ 
/*     */         
/* 398 */         box.setLocation(null);
/* 399 */         box.setEnabled(EnabledDisabled.DISABLED);
/* 400 */         box.setStatus(BoxStatus.HASTHE);
/* 401 */         this.dao.updateObject(box); }
/*     */       else
/* 403 */       { return String.valueOf(lotSer) + 
/* 404 */           ":" + 
/*     */           
/* 406 */           Properties.getPropertiesValye("scan.sync.error.lot.null"); } 
/* 407 */     } catch (Exception e) {
/* 408 */       return e.getMessage();
/*     */     } 
/*     */     
/* 411 */     return "ok";
/*     */   }
/*     */ 
/*     */   
/*     */   public Box insertBox(String supplierCode, WmsPart part, BigDecimal amount, Date enterTime) {
/* 416 */     WmsLot wl = (new InventoryTool((DAO)this.dao))
/* 417 */       .insertWOBox(supplierCode, "yyyyMMdd");
/* 418 */     Box box = new Box();
/* 419 */     box.setLot(wl);
/* 420 */     box.setDate(enterTime);
/* 421 */     box.setEnabled(EnabledDisabled.ENABLED);
/* 422 */     box.setPart(part);
/* 423 */     box.setNumber(amount);
/* 424 */     box.setPrint_number(Integer.valueOf(0));
/* 425 */     box.setStatus_rqc(BoxStatusRqc.NotTheQuality);
/* 426 */     box.setStatus(BoxStatus.Wait);
/* 427 */     box.setStatus_freeze(YesNo.NO);
/* 428 */     box.setIsPrint(YesNo.NO);
/* 429 */     this.dao.saveObject(box);
/* 430 */     return box;
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningLotMerge(String lots, String userId) {
/* 435 */     ScanLog scanLog = new ScanLog();
/* 436 */     scanLog.setDate(new Date());
/* 437 */     scanLog.setDescribe(lots);
/* 438 */     scanLog.setType(Integer.valueOf(11));
/* 439 */     User user = (User)this.scanLogDAO.getObject(User.class, 
/* 440 */         Integer.valueOf(Integer.parseInt(userId)));
/* 441 */     if (user != null) {
/* 442 */       scanLog.setUserId(user);
/*     */     }
/* 444 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */     
/*     */     try {
/* 447 */       BigDecimal sum = new BigDecimal(0);
/* 448 */       Date date = null;
/* 449 */       List<BoxAdjustment> listBox = new ArrayList<BoxAdjustment>();
/* 450 */       Box box = null;
/* 451 */       String[] arrays = lots.split(","); byte b; int i; String[] arrayOfString1;
/* 452 */       for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 453 */         List<Box> list = this.dao
/* 454 */           .getObjectList("from Box box where box.lot.id = '" + 
/* 455 */             id + 
/* 456 */             "' and box.enabled =0 and box.status_freeze=1 ");
/* 457 */         if (list.size() > 0) {
/* 458 */           box = list.get(0);
/* 459 */           sum = sum.add(box.getNumber());
/* 460 */           if (date == null) {
/* 461 */             date = box.getPsoItem().getPortalShipOrder()
/* 462 */               .getCreateDate();
/*     */           }
/*     */           
/* 465 */           box.setEnabled(EnabledDisabled.DISABLED);
/* 466 */           box.setLocation(null);
/* 467 */           box.setStatus(BoxStatus.HASTHE);
/* 468 */           this.dao.updateObject(box);
/*     */ 
/*     */           
/* 471 */           BoxAdjustment adjustment = new BoxAdjustment();
/* 472 */           adjustment.setDate(new Date());
/* 473 */           adjustment.setNew_box_id(box);
/* 474 */           adjustment.setOld_location(box.getLocation());
/* 475 */           adjustment.setOperation(user);
/* 476 */           adjustment.setType(BoxAdjustmentType.MERGE);
/* 477 */           this.dao.insertBoxAdjustment(adjustment);
/*     */           
/* 479 */           listBox.add(adjustment);
/*     */         } 
/*     */         b++; }
/*     */       
/* 483 */       Box newBox = insertBoxByMerge(box, date, sum);
/*     */ 
/*     */       
/* 486 */       for (BoxAdjustment adjustment : listBox) {
/* 487 */         BoxAdjustmentBox adjustmentBox = new BoxAdjustmentBox();
/* 488 */         adjustmentBox.setBox_adjustment_id(adjustment);
/* 489 */         adjustmentBox.setBox_id(newBox);
/* 490 */         this.dao.saveObject(adjustmentBox);
/*     */       } 
/* 492 */     } catch (Exception e) {
/* 493 */       return e.getMessage();
/*     */     } 
/*     */     
/* 496 */     return "ok";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BoxAdjustmentBox> getBoxAdjustmentBoxByMain(Integer id) {
/* 501 */     return this.dao.getBoxAdjustmentBoxByMain(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBoxAdjustmentBoxListCount(Map conditions) {
/* 506 */     return this.dao.getBoxAdjustmentBoxListCount(conditions);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getBoxAdjustmentBoxList(Map conditions, int pageNo, int pageSize, BoxAdjustmentQueryOrder order, boolean descend) {
/* 511 */     return this.dao.getBoxAdjustmentBoxList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/impl/BoxAdjustmentManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */