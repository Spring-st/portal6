/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.PortalShipOrder;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.sync.shared.XbqtyDet;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public class QtyDetThread
/*     */   extends Thread
/*     */ {
/*  34 */   private Log log = LogFactory.getLog(QtyDetThread.class);
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   
/*     */   private String time;
/*     */ 
/*     */   
/*     */   public QtyDetThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  46 */     this.dao = dao;
/*  47 */     this.time = time;
/*  48 */     this.daoShared = daoShared;
/*  49 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  56 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  57 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("发货单实收数量信息");
/*  58 */         if (sycSleepTime != null) {
/*  59 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  60 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  62 */         sleep(Long.parseLong(this.time));
/*  63 */         Date date = new Date();
/*  64 */         SimpleDateFormat format = new SimpleDateFormat(
/*  65 */             "yyyy/MM/dd hh:mm:ss");
/*  66 */         System.out.println("发货单实收数量信息同步-------------------------1-" + 
/*  67 */             format.format(date));
/*     */         
/*     */         try {
/*  70 */           xbqtyDetSyncRead();
/*  71 */         } catch (Exception e1) {
/*     */           
/*  73 */           e1.printStackTrace();
/*     */         } 
/*  75 */         System.out.println("-------------------- 发货单实收数量信息同步完成");
/*  76 */       } catch (InterruptedException e) {
/*  77 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  79 */         System.gc();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void xbqtyDetSyncRead() {
/*     */     try {
/* 118 */       List<XbqtyDet> xbqtyDetList = this.daoShared.getObjectList(" from XbqtyDet det  where (det.xbqty_rev01=0 or det.xbqty_rev01 is null)");
/* 119 */       for (XbqtyDet xbqtyDet : xbqtyDetList) {
/* 120 */         List<PortalShipOrderItem> orderItemList = this.dao.getObjectList(" from PortalShipOrderItem item  where item.portalShipOrder.code='" + xbqtyDet.getXbqty_nbr() + "' and item.part.id='" + xbqtyDet.getXbqty_part() + "'");
/*     */         
/* 122 */         if (orderItemList.size() == 0) {
/* 123 */           xbqtyDet.setXbqty_rev01("2");
/* 124 */           this.daoShared.updateObject(xbqtyDet);
/*     */           
/*     */           continue;
/*     */         } 
/* 128 */         for (PortalShipOrderItem portalShipOrderItem : orderItemList) {
/*     */           
/* 130 */           portalShipOrderItem.setActual_qty(xbqtyDet.getXbqty_qty_real());
/* 131 */           portalShipOrderItem.setReceived_qty(xbqtyDet.getXbqty_qty_arr());
/* 132 */           this.dao.updateObject(portalShipOrderItem);
/*     */           
/* 134 */           xbqtyDet.setXbqty_rev01("1");
/* 135 */           this.daoShared.updateObject(xbqtyDet);
/*     */         } 
/* 137 */         boolean f = true;
/* 138 */         BigDecimal arrCount = null;
/* 139 */         BigDecimal realCount = null;
/* 140 */         BigDecimal arrNum = null;
/* 141 */         List<PortalShipOrder> portalShipOrderList = this.dao.getObjectList("from PortalShipOrder pso where code='" + xbqtyDet.getXbqty_nbr() + "'");
/* 142 */         for (int i = 0; i < portalShipOrderList.size(); i++) {
/* 143 */           arrCount = new BigDecimal(0);
/* 144 */           realCount = new BigDecimal(0);
/* 145 */           arrNum = new BigDecimal(0);
/*     */           
/* 147 */           PortalShipOrder portalShipOrder = portalShipOrderList.get(i);
/* 148 */           List<PortalShipOrderItem> orderItemList1 = this.dao.getObjectList(" from PortalShipOrderItem item  where item.portalShipOrder.id='" + portalShipOrder.getId() + "'");
/* 149 */           for (int j = 0; j < orderItemList1.size(); j++) {
/* 150 */             PortalShipOrderItem portalShipOrderItem1 = orderItemList1.get(j);
/* 151 */             arrCount = portalShipOrderItem1.getDeliveryNumber().add(arrCount);
/* 152 */             realCount = portalShipOrderItem1.getActual_qty().add(realCount);
/*     */ 
/*     */             
/* 155 */             arrNum = xbqtyDet.getXbqty_qty_arr();
/* 156 */             int res = 0;
/* 157 */             if (portalShipOrderItem1.getReceived_qty() != null) {
/* 158 */               res = portalShipOrderItem1.getReceived_qty().compareTo(portalShipOrderItem1.getDeliveryNumber());
/*     */             }
/* 160 */             if (res != 0) {
/* 161 */               f = false;
/*     */             }
/*     */           } 
/* 164 */           if (f) {
/* 165 */             portalShipOrder.setEnabled(EnabledDisabled.DISABLED);
/*     */           }
/* 167 */           portalShipOrder.setRealDate(xbqtyDet.getXbqty_date_arr());
/* 168 */           portalShipOrder.setDifferenceCount(arrCount.subtract(realCount));
/* 169 */           this.dao.updateObject(portalShipOrder);
/*     */         } 
/* 171 */         arrCount = null;
/* 172 */         realCount = null;
/* 173 */         arrNum = null;
/*     */       } 
/* 175 */     } catch (Exception e) {
/*     */       
/* 177 */       insertSystemLog("RedMinuteSyncJob", "xbqtyDetList", e.getMessage(), 
/* 178 */           "1");
/*     */       
/* 180 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 186 */     SyncLog log = new SyncLog();
/* 187 */     log.setSync_date(new Date());
/* 188 */     log.setSync_content(content);
/* 189 */     log.setSync_describe(sync_describe);
/* 190 */     log.setSync_object(action);
/* 191 */     log.setSync_results(syncResults);
/* 192 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 196 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 200 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 204 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 208 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 212 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 216 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/QtyDetThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */