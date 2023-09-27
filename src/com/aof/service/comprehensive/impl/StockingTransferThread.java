/*     */ package com.aof.service.comprehensive.impl;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.comprehensive.Stocking;
/*     */ import com.aof.model.comprehensive.StockingDetial;
/*     */ import com.aof.model.comprehensive.StockingScanRecord;
/*     */ import com.aof.model.comprehensive.StockingSweepRecord;
/*     */ import com.aof.model.comprehensive.query.StockingDetialQueryCondition;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.comprehensive.StockingManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StockingTransferThread
/*     */   extends Thread
/*     */ {
/*  39 */   private Log log = LogFactory.getLog(StockingTransferThread.class);
/*     */   private StorageLocationManager storageLocationManager;
/*     */   private StockingManager stockingManager;
/*  42 */   private Stocking stocking = null;
/*     */   
/*  44 */   SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingTransferThread(StorageLocationManager storageLocationManager, StockingManager stockingManager, Stocking stocking) {
/*  50 */     this.storageLocationManager = storageLocationManager;
/*  51 */     this.stockingManager = stockingManager;
/*  52 */     this.stocking = stocking;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/*  57 */       doJob();
/*  58 */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       System.out.println(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void doJob() {
/*  65 */     System.out.println("Start------------StockingTransferThread-------------1---" + this.format.format(new Date()));
/*  66 */     if (this.stocking != null) {
/*     */       
/*  68 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  69 */       List<StockingScanRecord> list = this.stockingManager
/*  70 */         .getStockingScanRecordByList(this.stocking.getCode());
/*  71 */       if (list.size() > 0) {
/*  72 */         System.out.println("StockingScanRecord-----insert-----StockingSweepRecord-----2---" + this.format.format(new Date()));
/*  73 */         if (this.stocking.getInventoryType().intValue() == 2) {
/*  74 */           wmsStockingByPartFinish(list);
/*     */         } else {
/*  76 */           wmsStockingByLocationFinish(list);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  81 */       if (this.stocking.getInventoryType().intValue() == 2) {
/*  82 */         conditions.clear();
/*  83 */         conditions.put(StockingDetialQueryCondition.STOCKING_ID_EQ, 
/*  84 */             this.stocking.getId());
/*  85 */         conditions.put(StockingDetialQueryCondition.PART_IS_NOT_NULL, null);
/*  86 */         List<StockingDetial> datialList = this.stockingManager
/*  87 */           .getStockingDetialList(conditions, 0, -1, null, false);
/*  88 */         int ast = 0;
/*  89 */         for (StockingDetial stockingDetial : datialList) {
/*  90 */           List<Map> listMap = this.stockingManager
/*  91 */             .getStockingDetialByBoxByPart(stockingDetial);
/*  92 */           for (Map map : listMap) {
/*  93 */             long start = System.currentTimeMillis();
/*  94 */             ast++;
/*  95 */             this.stockingManager.insertWmsStockingRecordMap(map, this.stocking);
/*  96 */             long end = System.currentTimeMillis();
/*  97 */             long and = end - start;
/*  98 */             System.out.println("insert-----StockingRecord-----" + ast + "---" + and);
/*     */           } 
/*     */         } 
/* 101 */         BigDecimal ActualSumQty = this.stockingManager.getInventoryDetialByActualSum(this.stocking.getId());
/* 102 */         BigDecimal DifferencesSumQty = this.stockingManager.getInventoryDetialByDifferencesSum(this.stocking.getId());
/*     */         
/* 104 */         this.stocking.setActual_sumQty(ActualSumQty);
/* 105 */         this.stocking.setDifferences_sumQty(DifferencesSumQty);
/* 106 */         this.stockingManager.updateStocking(this.stocking);
/*     */       }
/*     */       else {
/*     */         
/* 110 */         conditions.clear();
/* 111 */         conditions.put(StockingDetialQueryCondition.STOCKING_ID_EQ, 
/* 112 */             this.stocking.getId());
/* 113 */         conditions.put(StockingDetialQueryCondition.PART_IS_NOT_NULL, null);
/* 114 */         List<StockingDetial> datialList = this.stockingManager
/* 115 */           .getStockingDetialList(conditions, 0, -1, null, false);
/* 116 */         int ast = 0;
/* 117 */         for (StockingDetial stockingDetial : datialList) {
/* 118 */           List<Map> listMap = this.stockingManager
/* 119 */             .getStockingDetialByBox(stockingDetial);
/* 120 */           for (Map map : listMap) {
/* 121 */             long start = System.currentTimeMillis();
/* 122 */             ast++;
/* 123 */             this.stockingManager.insertWmsStockingRecordMap(map, this.stocking);
/* 124 */             long end = System.currentTimeMillis();
/* 125 */             long and = end - start;
/* 126 */             System.out.println("insert-----StockingRecord-----" + ast + "---" + and);
/*     */           } 
/*     */         } 
/* 129 */         BigDecimal ActualSumQty = this.stockingManager.getInventoryDetialByActualSum(this.stocking.getId());
/* 130 */         BigDecimal DifferencesSumQty = this.stockingManager.getInventoryDetialByDifferencesSum(this.stocking.getId());
/*     */         
/* 132 */         this.stocking.setActual_sumQty(ActualSumQty);
/* 133 */         this.stocking.setDifferences_sumQty(DifferencesSumQty);
/* 134 */         this.stockingManager.updateStocking(this.stocking);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void wmsStockingByLocationFinish(List<StockingScanRecord> list) {
/* 142 */     for (StockingScanRecord stockingScanRecord : list) {
/* 143 */       String order = stockingScanRecord.getStocking();
/* 144 */       String lotSer = stockingScanRecord.getLotSer();
/* 145 */       String location = stockingScanRecord.getLocation();
/*     */       
/* 147 */       ScanLog scanLog = new ScanLog();
/* 148 */       scanLog.setDate(stockingScanRecord.getDate());
/* 149 */       scanLog.setDescribe(String.valueOf(stockingScanRecord.getLotSer()) + "," + 
/* 150 */           stockingScanRecord.getLocation());
/* 151 */       scanLog.setType(Integer.valueOf(55));
/* 152 */       User user = (User)this.stockingManager.getObject(User.class, 
/* 153 */           Integer.valueOf(Integer.parseInt(stockingScanRecord.getUserId())));
/* 154 */       if (user != null) {
/* 155 */         scanLog.setUserId(user);
/*     */       }
/* 157 */       this.stockingManager.saveObject(scanLog);
/*     */       
/* 159 */       boolean sign = true;
/*     */       
/*     */       try {
/* 162 */         Stocking stocking = this.stockingManager
/* 163 */           .getStockingByCode(order);
/* 164 */         if (stocking != null)
/*     */         {
/* 166 */           StorageLocation storageLocation = this.storageLocationManager
/* 167 */             .getStorageLocation(location);
/* 168 */           if (storageLocation != null)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 175 */             List<Box> boxList = this.stockingManager
/* 176 */               .getObjectList("from Box box where box.lot.id='" + lotSer + "' ");
/* 177 */             if (boxList.size() > 0) {
/* 178 */               Box box = boxList.get(0);
/*     */               
/* 180 */               StockingDetial stockingDetial = this.stockingManager.getStockingDetialByLocation(location, order);
/* 181 */               if (stockingDetial != null)
/*     */               {
/* 183 */                 StockingSweepRecord stockingSweepRecorda = this.stockingManager.getStockingSweepRecordBylotSer(lotSer, location, order);
/* 184 */                 if (stockingSweepRecorda == null)
/*     */                 {
/* 186 */                   List<StockingDetial> item = this.stockingManager.getStockingDetialByLocationList(storageLocation.getCode(), order);
/* 187 */                   if (item.size() > 0) {
/*     */                     
/* 189 */                     StockingDetial datial = null;
/* 190 */                     StockingDetial tiems = item.get(0);
/* 191 */                     if (tiems.getPart() == null) {
/* 192 */                       datial = tiems;
/*     */                     }
/* 194 */                     else if (tiems.getPart().getId().equals(box.getPart().getId())) {
/* 195 */                       datial = tiems;
/*     */                     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 206 */                     if (datial == null) {
/* 207 */                       datial = new StockingDetial();
/* 208 */                       datial.setLocation(storageLocation);
/* 209 */                       datial.setPart(box.getPart());
/* 210 */                       datial.setStocking(stocking);
/* 211 */                       datial.setPlan_sum_qty(new BigDecimal(
/* 212 */                             0));
/* 213 */                       datial.setActual_sum_qty(box
/* 214 */                           .getNumber());
/* 215 */                       datial.setPlan_num(Integer.valueOf(0));
/* 216 */                       datial.setActual_num(Integer.valueOf(1));
/* 217 */                       this.stockingManager
/* 218 */                         .saveObject(datial);
/*     */                     } else {
/* 220 */                       datial.setPart(box.getPart());
/*     */                       
/* 222 */                       if (datial.getActual_num() != null) {
/* 223 */                         datial.setActual_num(Integer.valueOf(datial.getActual_num().intValue() + 1));
/*     */                       } else {
/* 225 */                         datial.setActual_num(Integer.valueOf(1));
/*     */                       } 
/* 227 */                       if (datial.getActual_sum_qty() != null) {
/* 228 */                         datial.setActual_sum_qty(datial.getActual_sum_qty().add(box.getNumber()));
/*     */                       } else {
/* 230 */                         datial.setActual_sum_qty(box.getNumber());
/*     */                       } 
/* 232 */                       this.stockingManager.updateStockingDetial(datial);
/*     */                     } 
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 273 */                   StockingSweepRecord stockingSweepRecord = new StockingSweepRecord();
/* 274 */                   stockingSweepRecord.setBox(box);
/* 275 */                   stockingSweepRecord
/* 276 */                     .setDate(new Date());
/* 277 */                   stockingSweepRecord
/* 278 */                     .setLocation(storageLocation);
/* 279 */                   stockingSweepRecord.setQty(box
/* 280 */                       .getNumber());
/* 281 */                   stockingSweepRecord
/* 282 */                     .setStocking_detail_id(stockingDetial);
/* 283 */                   stockingSweepRecord
/* 284 */                     .setOperation(user);
/* 285 */                   this.stockingManager
/* 286 */                     .saveObject(stockingSweepRecord);
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 298 */       catch (Exception e) {
/* 299 */         e.getStackTrace();
/* 300 */         System.out.println(e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void wmsStockingByPartFinish(List<StockingScanRecord> list) {
/* 307 */     for (StockingScanRecord stockingScanRecord : list) {
/* 308 */       String order = stockingScanRecord.getStocking();
/* 309 */       String lotSer = stockingScanRecord.getLotSer();
/*     */       
/* 311 */       ScanLog scanLog = new ScanLog();
/* 312 */       scanLog.setDate(stockingScanRecord.getDate());
/* 313 */       scanLog.setDescribe(stockingScanRecord.getLotSer());
/* 314 */       scanLog.setType(Integer.valueOf(56));
/* 315 */       User user = (User)this.stockingManager.getObject(User.class, 
/* 316 */           Integer.valueOf(Integer.parseInt(stockingScanRecord.getUserId())));
/* 317 */       if (user != null) {
/* 318 */         scanLog.setUserId(user);
/*     */       }
/* 320 */       this.stockingManager.saveObject(scanLog);
/*     */       
/* 322 */       boolean sign = true;
/*     */       
/*     */       try {
/* 325 */         Stocking stocking = this.stockingManager
/* 326 */           .getStockingByCode(order);
/* 327 */         if (stocking != null) {
/* 328 */           List<Box> boxList = this.stockingManager.getObjectList("from Box box where box.lot.id='" + lotSer + "' ");
/* 329 */           if (boxList.size() > 0) {
/* 330 */             Box box = boxList.get(0);
/*     */             
/* 332 */             StockingDetial stockingDetial = this.stockingManager.getStockingDetialByPart(box.getPart().getId().toString(), order);
/* 333 */             if (stockingDetial != null)
/*     */             {
/* 335 */               StockingSweepRecord stockingSweepRecorda = this.stockingManager.getStockingSweepRecordBylotSer(lotSer, order);
/* 336 */               if (stockingSweepRecorda == null)
/*     */               {
/* 338 */                 if (stockingDetial.getActual_num() != null) {
/* 339 */                   stockingDetial.setActual_num(Integer.valueOf(stockingDetial.getActual_num().intValue() + 1));
/*     */                 } else {
/* 341 */                   stockingDetial.setActual_num(Integer.valueOf(1));
/*     */                 } 
/* 343 */                 if (stockingDetial.getActual_sum_qty() != null) {
/* 344 */                   stockingDetial.setActual_sum_qty(stockingDetial.getActual_sum_qty().add(box.getNumber()));
/*     */                 } else {
/* 346 */                   stockingDetial.setActual_sum_qty(box.getNumber());
/* 347 */                 }  this.stockingManager.updateStockingDetial(stockingDetial);
/*     */                 
/* 349 */                 StockingSweepRecord stockingSweepRecord = new StockingSweepRecord();
/* 350 */                 stockingSweepRecord.setBox(box);
/* 351 */                 stockingSweepRecord
/* 352 */                   .setDate(new Date());
/* 353 */                 stockingSweepRecord
/* 354 */                   .setLocation(box.getLocation());
/* 355 */                 stockingSweepRecord.setQty(box
/* 356 */                     .getNumber());
/* 357 */                 stockingSweepRecord
/* 358 */                   .setStocking_detail_id(stockingDetial);
/* 359 */                 stockingSweepRecord
/* 360 */                   .setOperation(user);
/* 361 */                 this.stockingManager
/* 362 */                   .saveObject(stockingSweepRecord);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 371 */       } catch (Exception e) {
/* 372 */         e.getStackTrace();
/* 373 */         System.out.println(e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/impl/StockingTransferThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */