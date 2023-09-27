/*     */ package com.aof.service.comprehensive.impl;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.comprehensive.Stocking;
/*     */ import com.aof.model.comprehensive.StockingRecord;
/*     */ import com.aof.model.comprehensive.query.StockingQueryCondition;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.comprehensive.StockingManager;
/*     */ import com.aof.service.po.BoxManager;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StockingConfirmStatusTransferThread
/*     */   extends Thread
/*     */ {
/*  44 */   private Log log = LogFactory.getLog(StockingConfirmStatusTransferThread.class);
/*     */   private BoxManager boxManager;
/*     */   private StockingManager stockingManager;
/*  47 */   private Stocking stocking = null;
/*     */   
/*     */   private User user;
/*  50 */   SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StockingConfirmStatusTransferThread(BoxManager boxManager, StockingManager stockingManager, Stocking stocking, User user) {
/*  56 */     this.boxManager = boxManager;
/*  57 */     this.stockingManager = stockingManager;
/*  58 */     this.stocking = stocking;
/*  59 */     this.user = user;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/*  64 */       doJob();
/*  65 */     } catch (Exception e) {
/*  66 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void doJob() {
/*  71 */     System.out.println("Start------------StockingConfirmStatusTransferThread-------------1---" + this.format.format(new Date()));
/*  72 */     if (this.stocking != null) {
/*  73 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  74 */       conditions.put(StockingQueryCondition.STOCKING_ID_EQ, this.stocking.getId());
/*  75 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_STATUS_NOT_EQ, Integer.valueOf(1));
/*  76 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_TYPE_EQ, Integer.valueOf(2));
/*  77 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_DIFFERENCES_GT, Integer.valueOf(0));
/*  78 */       List<StockingRecord> records = this.stockingManager.getStockingRecordList(conditions, 0, -1, null, false);
/*  79 */       for (StockingRecord stockingRecord : records) {
/*  80 */         if (stockingRecord.getBox().getLocation() == null) {
/*  81 */           String str = this.boxManager.scanningStockingInStorage(stockingRecord.getBox().getLot().getId(), stockingRecord.getLocation().getCode(), this.user.getId().toString());
/*  82 */           if (str.equals("ok")) {
/*  83 */             stockingRecord.setStatus(Integer.valueOf(1));
/*  84 */             this.stockingManager.updateStockingRecord(stockingRecord);
/*     */             continue;
/*     */           } 
/*  87 */           stockingRecord.setStatus(Integer.valueOf(2));
/*  88 */           this.stockingManager.updateStockingRecord(stockingRecord);
/*     */           
/*     */           continue;
/*     */         } 
/*  92 */         if (stockingRecord.getLocation().getId() != stockingRecord.getBox().getLocation().getId()) {
/*     */           
/*  94 */           String str = this.boxManager.scanningStockingInStorage(stockingRecord.getBox().getLot().getId(), stockingRecord.getLocation().getCode(), this.user.getId().toString());
/*  95 */           if (str.equals("ok")) {
/*  96 */             stockingRecord.setStatus(Integer.valueOf(1));
/*  97 */             this.stockingManager.updateStockingRecord(stockingRecord);
/*     */             continue;
/*     */           } 
/* 100 */           stockingRecord.setStatus(Integer.valueOf(2));
/* 101 */           this.stockingManager.updateStockingRecord(stockingRecord);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 107 */       conditions.clear();
/* 108 */       conditions.put(StockingQueryCondition.STOCKING_ID_EQ, this.stocking.getId());
/* 109 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_STATUS_NOT_EQ, Integer.valueOf(1));
/* 110 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_TYPE_EQ, Integer.valueOf(2));
/* 111 */       conditions.put(StockingQueryCondition.STOCKINGRECORD_DIFFERENCES_LT, Integer.valueOf(0));
/* 112 */       List<StockingRecord> recordList = this.stockingManager.getStockingRecordList(conditions, 0, -1, null, false);
/* 113 */       for (StockingRecord stockingRecord : recordList) {
/* 114 */         if (stockingRecord.getLocation() == null) {
/* 115 */           String str = this.boxManager.scanningMaterialsOutbound(stockingRecord.getBox().getLot().getId(), this.user.getId().toString());
/* 116 */           if (str.equals("ok")) {
/* 117 */             this.stockingManager.updateStockingRecordStatus(stockingRecord, Integer.valueOf(1)); continue;
/*     */           } 
/* 119 */           this.stockingManager.updateStockingRecordStatus(stockingRecord, Integer.valueOf(2));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     this.stocking.setConfirmStatus(YesNo.YES);
/* 125 */     this.stockingManager.updateStocking(this.stocking);
/* 126 */     System.out.println("end------------StockingConfirmStatusTransferThread-------------2---" + this.format.format(new Date()));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/impl/StockingConfirmStatusTransferThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */