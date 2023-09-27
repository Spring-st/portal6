/*     */ package com.aof.service.plantWarehouse.impl;
/*     */ 
/*     */ import com.aof.dao.basic.ScanLogDAO;
/*     */ import com.aof.dao.plantWarehouse.WmsPlanToGoOutDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.InventoryType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOut;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
/*     */ import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.Properties;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.impl.BoxManagerImpl;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ 
/*     */ public class WmsPlanToGoOutManagerImpl
/*     */   extends BaseManager
/*     */   implements WmsPlanToGoOutManager
/*     */ {
/*     */   private WmsPlanToGoOutDAO wmsPlanToGoOutDAO;
/*     */   private BoxManager boxManager;
/*     */   private WmsPartManager wmsPartManager;
/*     */   private InventoryManager inventoryManager;
/*     */   private ScanLogDAO scanLogDAO;
/*     */   
/*     */   public void setBoxManager(BoxManager boxManager) {
/*  48 */     this.boxManager = boxManager;
/*     */   }
/*     */   
/*     */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/*  52 */     this.scanLogDAO = scanLogDAO;
/*     */   }
/*     */   
/*     */   public void setInventoryManager(InventoryManager inventoryManager) {
/*  56 */     this.inventoryManager = inventoryManager;
/*     */   }
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  60 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */   
/*     */   public void setWmsPlanToGoOutDAO(WmsPlanToGoOutDAO wmsPlanToGoOutDAO) {
/*  64 */     this.wmsPlanToGoOutDAO = wmsPlanToGoOutDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOut getWmsPlanToGoOut(Integer id) {
/*  69 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOut(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWmsPlanToGoOutListCount(Map conditions) {
/*  74 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsPlanToGoOutList(Map conditions, int pageNo, int pageSize, WmsPlanToGoOutQueryOrder order, boolean descend) {
/*  80 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutList(conditions, pageNo, 
/*  81 */         pageSize, order, descend);
/*     */   }
/*     */   
/*     */   public WmsPlanToGoOut insertWmsPlanToGoOut(WmsPlanToGoOut pwom) {
/*  85 */     pwom.setCode(getLastCode(pwom.getSite(), pwom.getDate()));
/*  86 */     return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOut(pwom);
/*     */   }
/*     */   
/*     */   private String getLastCode(Site site, Date date) {
/*  90 */     StringBuffer sb = new StringBuffer("TG");
/*  91 */     sb.append(StringUtils.right(ActionUtils.get8CharsFromDate(date), 6));
/*  92 */     String prefix = sb.toString();
/*  93 */     String maxId = this.wmsPlanToGoOutDAO.getMaxPoApplicationIdBeginWith(prefix);
/*     */     
/*  95 */     int serialNo = 1;
/*  96 */     if (maxId != null) {
/*  97 */       Integer maxSN = ActionUtils.parseInt(StringUtils.right(maxId, 5));
/*  98 */       if (maxSN == null)
/*  99 */         throw new RuntimeException("max serial no. is not digit"); 
/* 100 */       serialNo = maxSN.intValue() + 1;
/*     */     } 
/* 102 */     String sn = String.valueOf(serialNo);
/* 103 */     for (int i = 0; i < 5 - sn.length(); i++)
/* 104 */       sb.append('0'); 
/* 105 */     sb.append(sn);
/* 106 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOut updateWmsPlanToGoOut(WmsPlanToGoOut pwom) {
/* 111 */     return this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(pwom);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMaxPoApplicationIdBeginWith(String prefix) {
/* 116 */     return this.wmsPlanToGoOutDAO.getMaxPoApplicationIdBeginWith(prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem getWmsPlanToGoOutItem(int id) {
/* 121 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItem(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem insertWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
/* 126 */     return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOutItem(pwom);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem updateWmsPlanToGoOutItem(WmsPlanToGoOutItem pwom) {
/* 131 */     return this.wmsPlanToGoOutDAO.updateWmsPlanToGoOutItem(pwom);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsPlanToGoOutItem(WmsPlanToGoOutItem item) {
/* 136 */     this.wmsPlanToGoOutDAO.deleteWmsPlanToGoOutItem(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutItem updateWmsPlanToGoOutItemByList(String[] str, WmsPlanToGoOut toGoOut) {
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByMain(Integer id) {
/* 148 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItemByMain(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningUnplannedOutbound(String lotser, String planToGoOutId, String userId) {
/* 153 */     ScanLog scanLog = new ScanLog();
/* 154 */     scanLog.setDate(new Date());
/* 155 */     scanLog.setDescribe(lotser);
/* 156 */     scanLog.setType(Integer.valueOf(8));
/* 157 */     User user = (User)this.scanLogDAO.getObject(User.class, Integer.valueOf(Integer.parseInt(userId)));
/* 158 */     if (user != null) {
/* 159 */       scanLog.setUserId(user);
/*     */     }
/* 161 */     this.scanLogDAO.insertScanLog(scanLog);
/*     */ 
/*     */     
/*     */     try {
/* 165 */       boolean sign = false;
/* 166 */       WmsPlanToGoOut goOut = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutByCode(planToGoOutId);
/* 167 */       if (goOut == null) {
/* 168 */         return String.valueOf(Properties.getPropertiesValye("scan.sync.error.planToGoOutId.is.null")) + planToGoOutId;
/*     */       }
/*     */       
/* 171 */       List<WmsPlanToGoOutItem> goOutItems = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutItemByMain(goOut.getId());
/* 172 */       if (goOutItems == null || goOutItems.size() == 0) {
/* 173 */         return String.valueOf(Properties.getPropertiesValye("scan.sync.error.planToGoOutId.item.is.null")) + planToGoOutId;
/*     */       }
/*     */       
/* 176 */       for (WmsPlanToGoOutItem item : goOutItems) {
/* 177 */         List<WmsPlanToGoOutSub> subList = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByItem(item.getId());
/* 178 */         for (WmsPlanToGoOutSub sub : subList) {
/*     */           
/* 180 */           if (sub.getBox_id() != null) {
/* 181 */             Box box = sub.getBox_id();
/* 182 */             if (box.getStatus().equals(BoxStatus.HASBEENINTO)) {
/* 183 */               if (box.getLot().getId().equals(lotser)) {
/* 184 */                 sub.setIs_sync(YesNo.NO);
/* 185 */                 sub.setLocation(box.getLocation());
/* 186 */                 sub.setQty(box.getNumber());
/* 187 */                 updateWmsPlanToGoOutItem(item);
/*     */                 
/* 189 */                 this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.PLANTOGOOUT, Boolean.valueOf(true));
/* 190 */                 sign = true; continue;
/*     */               } 
/* 192 */               return String.valueOf(lotser) + ":" + Properties.getPropertiesValye("scan.sync.error.lot.item.is.null") + planToGoOutId;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 197 */       if (sign) {
/* 198 */         return "ok";
/*     */       }
/* 200 */       return String.valueOf(lotser) + ":" + Properties.getPropertiesValye("scan.sync.error.polotSer.not.is.plan") + lotser;
/*     */     }
/* 202 */     catch (Exception e) {
/* 203 */       return e.getMessage();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String scanningWmsPlanToGoOut(String lotSet, Integer userId) {
/* 209 */     return "error";
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsPlanToGoOut(WmsPlanToGoOut goOut) {
/* 214 */     this.wmsPlanToGoOutDAO.deleteWmsPlanToGoOut(goOut);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertWmsPlanToGoOutItemByMain(WmsPlanToGoOut toGoOut, String str) {
/* 219 */     String[] arrays = str.split(";");
/* 220 */     BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
/* 221 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 222 */       String[] partandnumber = array.split(",");
/* 223 */       String part = partandnumber[0];
/* 224 */       String amount = partandnumber[1];
/* 225 */       WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/*     */       
/* 227 */       WmsPlanToGoOutItem item = new WmsPlanToGoOutItem();
/* 228 */       item.setQty(new BigDecimal(amount));
/* 229 */       item.setPart(wmsPart);
/* 230 */       item.setActual_qty(new BigDecimal(0));
/* 231 */       item.setStatus(YesNo.NO);
/* 232 */       item.setUnplanned_outbound_id(toGoOut);
/* 233 */       insertWmsPlanToGoOutItem(item);
/*     */       
/* 235 */       sum = sum.add(new BigDecimal(amount));
/*     */       b++; }
/*     */     
/* 238 */     toGoOut.setQty(sum);
/* 239 */     this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(toGoOut);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkWmsPlanToGoOutItemByAmount(String wmsGoOutid, String arrayList, String type) {
/* 244 */     boolean sign = false;
/* 245 */     if (type.equals("0")) {
/* 246 */       List<Box> boxList = new ArrayList<Box>();
/* 247 */       String[] array = arrayList.split(","); byte b; int i; String[] arrayOfString1;
/* 248 */       for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String boxId = arrayOfString1[b];
/* 249 */         Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
/* 250 */         boxList.add(box); b++; }
/*     */       
/* 252 */       Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
/* 253 */       for (Box box : boxList) {
/* 254 */         if (map.get(box.getPart().getId()) == null) {
/* 255 */           map.put(box.getPart().getId(), box.getNumber()); continue;
/*     */         } 
/* 257 */         map.put(box.getPart().getId(), ((BigDecimal)map.get(box.getPart().getId())).add(box.getNumber()));
/*     */       } 
/*     */       
/* 260 */       List<WmsPlanToGoOutItem> itemList = 
/* 261 */         getWmsPlanToGoOutItemByMain(Integer.valueOf(Integer.parseInt(wmsGoOutid)));
/* 262 */       Set<String> set = map.keySet();
/* 263 */       for (WmsPlanToGoOutItem item : itemList) {
/* 264 */         int j = 0;
/* 265 */         for (String wmsPart : set) {
/* 266 */           BigDecimal amount = map.get(wmsPart);
/* 267 */           String part = item.getPart().getId();
/*     */           
/* 269 */           if (wmsPart.equals(part) && amount.compareTo(item.getQty()) == 0) {
/* 270 */             j++;
/* 271 */             sign = true;
/*     */           } else {
/* 273 */             sign = false;
/* 274 */           }  if (j > 0)
/* 275 */             sign = true; 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 279 */       List<Box> boxList = new ArrayList<Box>();
/* 280 */       String[] array = arrayList.split(","); byte b; int i; String[] arrayOfString1;
/* 281 */       for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String boxId = arrayOfString1[b];
/* 282 */         Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
/* 283 */         boxList.add(box); b++; }
/*     */       
/* 285 */       Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
/* 286 */       for (Box box : boxList) {
/* 287 */         if (map.get(box.getPart().getId()) == null) {
/* 288 */           map.put(box.getPart().getId(), box.getNumber()); continue;
/*     */         } 
/* 290 */         map.put(box.getPart().getId(), (
/* 291 */             (BigDecimal)map.get(box.getPart().getId())).add(
/* 292 */               box.getNumber()));
/*     */       } 
/*     */       
/* 295 */       List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(Integer.valueOf(Integer.parseInt(wmsGoOutid)));
/* 296 */       Set<String> set = map.keySet();
/* 297 */       for (WmsPlanToGoOutItem item : itemList) {
/* 298 */         int j = 0;
/* 299 */         for (String wmsPart : set) {
/* 300 */           BigDecimal amount = map.get(wmsPart);
/* 301 */           String part = item.getPart().getId();
/*     */           
/* 303 */           if (wmsPart.equals(part) && amount.compareTo(item.getQty()) == 0) {
/* 304 */             j++;
/* 305 */             sign = true;
/*     */           } else {
/* 307 */             sign = false;
/* 308 */           }  if (j > 0) {
/* 309 */             sign = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 314 */     return sign;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWmsPlanToGoOutByBox(WmsPlanToGoOut planToGoOut, String[] arrayList) {
/* 319 */     List<Box> boxList = new ArrayList<Box>();
/* 320 */     List<Box> orderBoxList = new ArrayList<Box>();
/* 321 */     BoxManagerImpl manager = new BoxManagerImpl(); byte b; int i; String[] arrayOfString;
/* 322 */     for (i = (arrayOfString = arrayList).length, b = 0; b < i; ) { String boxId = arrayOfString[b];
/* 323 */       Box box = this.boxManager.getBox(Integer.valueOf(Integer.parseInt(boxId)));
/* 324 */       boxList.add(box);
/*     */       b++; }
/*     */     
/* 327 */     List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(planToGoOut.getId());
/* 328 */     for (WmsPlanToGoOutItem item : itemList) {
/* 329 */       for (Box box : boxList) {
/* 330 */         if (item.getPart().getId().equals(box.getPart().getId())) {
/* 331 */           WmsPlanToGoOutSub sub = new WmsPlanToGoOutSub();
/* 332 */           sub.setUnplanned_outbound_detial_id(item);
/* 333 */           sub.setBox_id(box);
/* 334 */           insertWmsPlanToGoOutSub(sub);
/*     */           try {
/* 336 */             this.inventoryManager.inventoryAdjustmentByWoLot(box.getLocation().getCode(), box, InventoryType.PLANTOGOOUT, Boolean.valueOf(true));
/* 337 */           } catch (Exception e) {
/* 338 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPlanToGoOutSub insertWmsPlanToGoOutSub(WmsPlanToGoOutSub sub) {
/* 347 */     return this.wmsPlanToGoOutDAO.insertWmsPlanToGoOutSub(sub);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSub(List<WmsPlanToGoOutItem> itemList) {
/* 352 */     List<WmsPlanToGoOutSub> subList = new ArrayList<WmsPlanToGoOutSub>();
/* 353 */     for (WmsPlanToGoOutItem item : itemList) {
/* 354 */       List<WmsPlanToGoOutSub> subs = this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByItem(item.getId());
/* 355 */       subList.addAll(subs);
/*     */     } 
/* 357 */     return subList;
/*     */   }
/*     */   
/*     */   public List<Map> getRecommendLotset(List<WmsPlanToGoOutItem> items) {
/* 361 */     List<Map> listMap = new ArrayList<Map>();
/* 362 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*     */     
/* 364 */     for (WmsPlanToGoOutItem item : items) {
/* 365 */       BigDecimal amount = item.getQty();
/* 366 */       String sql = "from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" + 
/*     */         
/* 368 */         item.getPart().getId() + 
/* 369 */         "' " + 
/* 370 */         " and box.location.is_recommend <> 1 " + 
/* 371 */         " order by box.date ";
/*     */       
/* 373 */       List<Box> list = this.wmsPlanToGoOutDAO
/* 374 */         .getObjectList(sql);
/* 375 */       for (Box orderBox : list) {
/* 376 */         if (amount.compareTo(orderBox.getNumber()) == 1 || 
/* 377 */           amount.compareTo(orderBox.getNumber()) == 0) {
/* 378 */           amount = amount.subtract(orderBox.getNumber());
/*     */           
/* 380 */           map = new HashMap<Object, Object>();
/* 381 */           map.put("boxId", orderBox.getId());
/* 382 */           map.put("part", orderBox.getPart().getId());
/* 383 */           map.put("describe1", orderBox.getPart().getDescribe1());
/* 384 */           map.put("describe2", orderBox.getPart().getDescribe2());
/* 385 */           map.put("lotset", orderBox.getLot().getId());
/* 386 */           map.put("amount", orderBox.getNumber());
/* 387 */           map.put("unit", orderBox.getPart().getUnit());
/* 388 */           map.put("code", orderBox.getLocation().getCode());
/*     */           
/* 390 */           if (orderBox.getLocation() != null) {
/* 391 */             map.put("location", orderBox.getLocation().getCode());
/*     */           } else {
/* 393 */             listMap.clear();
/* 394 */             map = new HashMap<Object, Object>();
/* 395 */             map.put("sign", Boolean.valueOf(false));
/* 396 */             map.put("part", item.getPart().getId());
/* 397 */             listMap.add(map);
/*     */             
/*     */             break;
/*     */           } 
/* 401 */           listMap.add(map);
/*     */           continue;
/*     */         } 
/* 404 */         if (amount.compareTo(new BigDecimal(0)) == -1) {
/* 405 */           listMap.clear();
/* 406 */           map = new HashMap<Object, Object>();
/* 407 */           map.put("sign", Boolean.valueOf(false));
/* 408 */           map.put("part", item.getPart().getId());
/* 409 */           listMap.add(map);
/*     */         } 
/*     */       } 
/*     */       
/* 413 */       if (amount.compareTo(new BigDecimal(0)) == 1) {
/* 414 */         listMap.clear();
/* 415 */         map = new HashMap<Object, Object>();
/* 416 */         map.put("sign", Boolean.valueOf(false));
/* 417 */         map.put("part", item.getPart().getId());
/* 418 */         listMap.add(map);
/*     */       } 
/*     */     } 
/*     */     
/* 422 */     return listMap;
/*     */   }
/*     */   
/*     */   public List<Map> getImportWmsPlanToGoOutList(Sheet[] sheet) {
/* 426 */     int rowNum = 0;
/* 427 */     List<Map> listMap = new ArrayList<Map>();
/* 428 */     Map<Object, Object> map = new HashMap<Object, Object>(); byte b; int i;
/*     */     Sheet[] arrayOfSheet;
/* 430 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 431 */       int k = 0;
/* 432 */       rowNum = sheet[k].getRows();
/* 433 */       for (int j = 1; j < rowNum; j++) {
/* 434 */         Cell[] cells = sh.getRow(j);
/* 435 */         String part = cells[0].getContents();
/* 436 */         String amount = cells[1].getContents();
/*     */         
/* 438 */         map = new HashMap<Object, Object>();
/* 439 */         map.put("part", part);
/* 440 */         listMap.add(map);
/*     */       } 
/*     */       b++; }
/*     */     
/* 444 */     return listMap;
/*     */   }
/*     */   
/*     */   public Map updateWmsPlanToGoOutByManual(WmsPlanToGoOut plan) {
/* 448 */     List<WmsPlanToGoOutItem> itemList = this.wmsPlanToGoOutDAO.getObjectList("from WmsPlanToGoOutItem item where item.wmsGoOut.id = " + plan.getId() + " ");
/* 449 */     List<Map> listMap = getRecommendLotsetByBox(itemList);
/* 450 */     Map<Object, Object> mapvalue = new HashMap<Object, Object>();
/* 451 */     if (listMap.size() > 0) {
/* 452 */       Map map = listMap.get(0);
/* 453 */       String part = (String)map.get("part");
/* 454 */       if (part != null) {
/* 455 */         mapvalue = new HashMap<Object, Object>();
/* 456 */         mapvalue.put("sign", part);
/* 457 */         return mapvalue;
/*     */       } 
/* 459 */       List<Box> listBox = (List<Box>)map.get("boxs");
/* 460 */       updateWmsPlanToGoOutByBox2(plan, listBox);
/* 461 */       mapvalue = new HashMap<Object, Object>();
/* 462 */       mapvalue.put("sign", "true");
/*     */     } 
/*     */ 
/*     */     
/* 466 */     this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(plan);
/* 467 */     return mapvalue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWmsPlanToGoOutByBox2(WmsPlanToGoOut planToGoOut, List<Box> orderBoxList) {
/* 472 */     List<WmsPlanToGoOutItem> itemList = getWmsPlanToGoOutItemByMain(planToGoOut.getId());
/* 473 */     for (WmsPlanToGoOutItem item : itemList) {
/* 474 */       for (Box box : orderBoxList) {
/* 475 */         if (item.getPart().getId().equals(box.getPart().getId())) {
/* 476 */           WmsPlanToGoOutSub sub = new WmsPlanToGoOutSub();
/* 477 */           sub.setUnplanned_outbound_detial_id(item);
/* 478 */           sub.setBox_id(box);
/* 479 */           insertWmsPlanToGoOutSub(sub);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Map> getRecommendLotsetByBox(List<WmsPlanToGoOutItem> items) {
/* 486 */     List<Box> listBox = new ArrayList<Box>();
/* 487 */     List<Map> listMap = new ArrayList<Map>();
/* 488 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*     */     
/* 490 */     for (WmsPlanToGoOutItem item : items) {
/* 491 */       BigDecimal amount = item.getQty();
/* 492 */       String part = item.getPart().getId();
/*     */       
/* 494 */       String sql2 = "select sum(box.count) from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" + 
/*     */         
/* 496 */         part + 
/* 497 */         "' and box.location.code not in ('SUPP','DYSUPP') ";
/*     */       
/* 499 */       List<Object> valiedate = this.wmsPlanToGoOutDAO.getObjectList(sql2);
/* 500 */       if (valiedate.size() > 0 && valiedate.get(0) != null) {
/* 501 */         BigDecimal valuedateAmount = (BigDecimal)valiedate.get(0);
/* 502 */         if (valuedateAmount.compareTo(amount) == -1) {
/* 503 */           listBox.clear();
/* 504 */           map = new HashMap<Object, Object>();
/* 505 */           map.put("part", part);
/* 506 */           listMap.add(map);
/* 507 */           return listMap;
/*     */         } 
/*     */       } 
/*     */       
/* 511 */       String sql = "from Box box where box.isInStorage = 0 and box.isOutStorage = 1  and box.enabled = 0 and box.wmsPart.id='" + 
/*     */         
/* 513 */         item.getPart().getId() + 
/* 514 */         "' and box.location.code not in ('SUPP','DYSUPP') order by box.date ";
/*     */       
/* 516 */       List<Box> list = this.wmsPlanToGoOutDAO
/* 517 */         .getObjectList(sql);
/* 518 */       for (Box orderBox : list) {
/*     */         
/* 520 */         if (amount.compareTo(orderBox.getNumber()) == 1 || 
/* 521 */           amount.compareTo(orderBox.getNumber()) == 0) {
/* 522 */           amount = amount.subtract(orderBox.getNumber());
/*     */           
/* 524 */           listBox.add(orderBox);
/*     */         } 
/*     */       } 
/*     */       
/* 528 */       if (amount.compareTo(new BigDecimal(0)) == 1) {
/* 529 */         listBox.clear();
/* 530 */         map = new HashMap<Object, Object>();
/* 531 */         map.put("part", part);
/* 532 */         listMap.add(map);
/* 533 */         return listMap;
/*     */       } 
/*     */       
/* 536 */       if (amount.compareTo(new BigDecimal(0)) == -1) {
/* 537 */         listBox.clear();
/* 538 */         map = new HashMap<Object, Object>();
/* 539 */         map.put("part", part);
/* 540 */         listMap.add(map);
/* 541 */         return listMap;
/*     */       } 
/*     */     } 
/* 544 */     map = new HashMap<Object, Object>();
/* 545 */     map.put("boxs", listBox);
/* 546 */     listMap.add(map);
/*     */     
/* 548 */     return listMap;
/*     */   }
/*     */   
/*     */   public void updateWmsPlanToGoOutByManualScannery(WmsPlanToGoOut plan) {
/* 552 */     String sql = "from WmsPlanToGoOutSub sub where sub.item.id in  (select item.id from WmsPlanToGoOutItem item where item.wmsGoOut.id = " + 
/*     */       
/* 554 */       plan.getId() + ") ";
/*     */     
/* 556 */     List<WmsPlanToGoOutSub> list = this.wmsPlanToGoOutDAO.getObjectList(sql);
/* 557 */     for (WmsPlanToGoOutSub sub : list) {
/* 558 */       Box box = sub.getBox_id();
/*     */       
/* 560 */       scanningUnplannedOutbound(box.getLot().getId(), plan.getCode(), plan.getApplicant().getId().toString());
/*     */     } 
/*     */     
/* 563 */     this.wmsPlanToGoOutDAO.updateWmsPlanToGoOut(plan);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsPlanToGoOut> updateWmsPlanToGoOutByPlanNum(List<WmsPlanToGoOut> planToGoOut) {
/* 568 */     List<WmsPlanToGoOut> list = new ArrayList<WmsPlanToGoOut>();
/* 569 */     for (WmsPlanToGoOut goOut : planToGoOut) {
/*     */       
/* 571 */       String sql1 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " + 
/* 572 */         goOut.getId() + " ";
/*     */       
/* 574 */       String sql2 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " + 
/* 575 */         goOut.getId() + " and sub.box.isPickingOutboundFinish=0";
/* 576 */       String sql3 = "from WmsPlanToGoOutSub sub where sub.item.wmsGoOut.id = " + 
/* 577 */         goOut.getId() + " and sub.orderBox.isOutStorage=0";
/*     */       
/* 579 */       List list1 = this.wmsPlanToGoOutDAO.getObjectList(sql1);
/* 580 */       List list2 = this.wmsPlanToGoOutDAO.getObjectList(sql2);
/* 581 */       List list3 = this.wmsPlanToGoOutDAO.getObjectList(sql3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 597 */       list.add(goOut);
/*     */     } 
/*     */     
/* 600 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByMain(Integer id) {
/* 605 */     return this.wmsPlanToGoOutDAO.getWmsPlanToGoOutSubByMain(id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/plantWarehouse/impl/WmsPlanToGoOutManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */