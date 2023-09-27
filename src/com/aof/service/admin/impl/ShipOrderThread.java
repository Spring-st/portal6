/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PortalShipOrder;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.sync.shared.XbipddDet;
/*     */ import com.aof.model.sync.shared.XbipdmMstr;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
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
/*     */ public class ShipOrderThread
/*     */   extends Thread
/*     */ {
/*  35 */   private Log log = LogFactory.getLog(ShipOrderThread.class);
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
/*     */   public ShipOrderThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  47 */     this.dao = dao;
/*  48 */     this.time = time;
/*  49 */     this.daoShared = daoShared;
/*  50 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  57 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  58 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("送货单信息");
/*  59 */         if (sycSleepTime != null) {
/*  60 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  61 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  63 */         sleep(Long.parseLong(this.time));
/*  64 */         Date date = new Date();
/*  65 */         SimpleDateFormat format = new SimpleDateFormat(
/*  66 */             "yyyy/MM/dd hh:mm:ss");
/*  67 */         System.out.println("送货单信息同步-------------------------1-" + 
/*  68 */             format.format(date));
/*     */         
/*     */         try {
/*  71 */           shipOrder();
/*  72 */         } catch (Exception e1) {
/*     */           
/*  74 */           e1.printStackTrace();
/*     */         } 
/*  76 */         System.out.println("送货单信息同步完成-------------------------9 " + 
/*  77 */             format.format(date));
/*  78 */       } catch (InterruptedException e) {
/*  79 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  81 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shipOrder() {
/*     */     try {
/*  91 */       int num = 1;
/*     */       
/*  93 */       List<PortalShipOrder> OrderList = this.dao
/*  94 */         .getObjectList(" from PortalShipOrder shipOrder where shipOrder.isPrint=0 and (shipOrder.isSync !=1 or shipOrder.isSync is null)");
/*  95 */       if (OrderList.size() != 0) {
/*  96 */         for (PortalShipOrder portalShipOrder : OrderList) {
/*     */           
/*  98 */           List<PortalShipOrderItem> orderItemList = this.dao
/*  99 */             .getObjectList(" from PortalShipOrderItem item where item.portalShipOrder.id=" + 
/* 100 */               portalShipOrder.getId() + 
/* 101 */               " and (item.isSync!=1 or item.isSync is null) ");
/* 102 */           if (orderItemList.size() != 0) {
/* 103 */             for (PortalShipOrderItem portalShipOrderItem : orderItemList) {
/*     */               
/* 105 */               List<Box> boxList = this.dao
/* 106 */                 .getObjectList(" from Box box where box.psoItem.id=" + 
/* 107 */                   portalShipOrderItem.getId() + 
/* 108 */                   " and (box.isSync=1 or box.isSync is null)");
/* 109 */               if (boxList.size() != 0) {
/* 110 */                 XbipddDet det = null;
/* 111 */                 for (Box box : boxList) {
/*     */                   
/* 113 */                   det = new XbipddDet();
/*     */                   
/* 115 */                   if (portalShipOrder.getCreateType().equals(
/* 116 */                       "NJIT_PO")) {
/* 117 */                     det.setXbipdd_nbr(portalShipOrder
/* 118 */                         .getCode());
/* 119 */                     det.setXbipdd_line(Integer.valueOf(num));
/* 120 */                     det.setXbipdd_ponbr(portalShipOrderItem
/* 121 */                         .getPoipItem().getPoip_number()
/* 122 */                         .getPoip_number());
/* 123 */                     det.setXbipdd_poline(
/* 124 */                         Integer.valueOf(Integer.parseInt(portalShipOrderItem
/* 125 */                             .getPoipItem()
/* 126 */                             .getLine())));
/* 127 */                     det.setXbipdd_ctnbr(box.getLot()
/* 128 */                         .getId());
/* 129 */                     det.setXbipdd_part(portalShipOrderItem
/* 130 */                         .getPart().getId());
/* 131 */                     det.setXbipdd_lot(null);
/* 132 */                     det.setXbipdd_qty(
/* 133 */                         Integer.valueOf(box.getNumber().intValue()));
/* 134 */                     det.setXbipdd_createdt(new Date());
/* 135 */                     this.daoShared.saveObject(det);
/*     */                   } else {
/*     */                     
/* 138 */                     det.setXbipdd_nbr(portalShipOrder
/* 139 */                         .getCode());
/* 140 */                     det.setXbipdd_line(Integer.valueOf(num));
/*     */ 
/*     */                     
/* 143 */                     det.setXbipdd_ctnbr(box.getLot()
/* 144 */                         .getId());
/* 145 */                     det.setXbipdd_part(portalShipOrderItem
/* 146 */                         .getPart().getId());
/* 147 */                     det.setXbipdd_lot(null);
/* 148 */                     det.setXbipdd_qty(
/* 149 */                         Integer.valueOf(box.getNumber().intValue()));
/* 150 */                     det.setXbipdd_createdt(new Date());
/* 151 */                     this.daoShared.saveObject(det);
/*     */                   } 
/*     */                   
/* 154 */                   box.setIsSync(YesNo.YES);
/* 155 */                   this.dao.updateObject(box);
/*     */                 } 
/* 157 */                 det = null;
/*     */               } 
/* 159 */               num++;
/*     */               
/* 161 */               portalShipOrderItem.setIsSync(Integer.valueOf(1));
/* 162 */               this.dao.updateObject(portalShipOrderItem);
/*     */             } 
/*     */           }
/*     */           
/* 166 */           XbipdmMstr mstr = new XbipdmMstr();
/* 167 */           mstr.setXbipdm_nbr(portalShipOrder.getCode());
/* 168 */           mstr.setXbipdm_vend(portalShipOrder.getSupplier().getCode());
/* 169 */           mstr.setXbipdm_date(portalShipOrder.getCreateDate());
/* 170 */           mstr.setXbipdm_site("");
/* 171 */           mstr.setXbipdm_uf1(null);
/* 172 */           mstr.setXbipdm_uf2(null);
/* 173 */           mstr.setXbipdm_uf3(null);
/* 174 */           mstr.setXbipdm_createdt(new Date());
/* 175 */           this.daoShared.saveObject(mstr);
/*     */           
/* 177 */           portalShipOrder.setIsSync(Integer.valueOf(1));
/* 178 */           this.dao.updateObject(portalShipOrder);
/* 179 */           mstr = null;
/*     */         } 
/*     */       }
/* 182 */     } catch (Exception e) {
/*     */       
/* 184 */       e.printStackTrace();
/* 185 */       insertSystemLog("PortalShipOrder", " SyncShipOrderError", 
/* 186 */           e.getMessage(), "1");
/* 187 */       System.out.println("同步送货单到中间表出错------------------------------>" + (
/* 188 */           new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
/* 189 */           .format(new Date()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 195 */     SyncLog log = new SyncLog();
/* 196 */     log.setSync_date(new Date());
/* 197 */     log.setSync_content(content);
/* 198 */     log.setSync_describe(sync_describe);
/* 199 */     log.setSync_object(action);
/* 200 */     log.setSync_results(syncResults);
/* 201 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 205 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 209 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 213 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 217 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 221 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 225 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ShipOrderThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */