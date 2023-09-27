/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.sync.shared.Xxqadidddet;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
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
/*     */ public class IddDetThread
/*     */   extends Thread
/*     */ {
/*  38 */   private Log log = LogFactory.getLog(IddDetThread.class);
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
/*     */   public IddDetThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  50 */     this.dao = dao;
/*  51 */     this.time = time;
/*  52 */     this.daoShared = daoShared;
/*  53 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  60 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  61 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("库存信息");
/*  62 */         if (sycSleepTime != null) {
/*  63 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  64 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  66 */         sleep(Long.parseLong(this.time));
/*  67 */         Date date = new Date();
/*  68 */         SimpleDateFormat format = new SimpleDateFormat(
/*  69 */             "yyyy/MM/dd hh:mm:ss");
/*  70 */         System.out.println("库存信息同步-------------------------1-" + 
/*  71 */             format.format(date));
/*     */         
/*  73 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  76 */         List<String> xxqadidddetList = this.daoShared
/*  77 */           .getObjectList(String.valueOf(beginSql) + 
/*  78 */             " and  ctrl.xxqad_table='xxqad_ld_det' group by ctrl.xxqad_seq");
/*     */         try {
/*  80 */           xxqadidddetSyncRead(xxqadidddetList);
/*  81 */         } catch (Exception e1) {
/*     */           
/*  83 */           e1.printStackTrace();
/*     */         } 
/*  85 */         System.out.println("-------------------- 库存信息同步完成");
/*  86 */       } catch (InterruptedException e) {
/*  87 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  89 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void xxqadidddetSyncRead(List<String> sqlList) throws KettleException {
/*     */     try {
/*  99 */       List<Xxqadidddet> syncXxqadidddetList = this.daoShared
/* 100 */         .getObjectList("from Xxqadidddet det where ( det.xxqadLdPortalread = 0 or det.xxqadLdPortalread is null) order by det.xxqadLdId");
/* 101 */       Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
/* 102 */       for (Xxqadidddet shared : syncXxqadidddetList) {
/* 103 */         if (map.containsKey(shared.getXxqadLdPart())) {
/* 104 */           Map<String, Object> map1 = map.get(shared.getXxqadLdPart());
/* 105 */           if (map1.containsKey("qty")) {
/* 106 */             Integer qty = (Integer)map1.get("qty");
/* 107 */             map1.put("qty", Integer.valueOf(qty.intValue() + shared.getXxqadLdQtyOh().intValue()));
/* 108 */             map1.put("createDate", shared.getXxqadLdCreatedt());
/*     */           }  continue;
/*     */         } 
/* 111 */         Map<String, Object> mapo = new HashMap<String, Object>();
/* 112 */         mapo.put("qty", shared.getXxqadLdQtyOh());
/* 113 */         mapo.put("createDate", shared.getXxqadLdCreatedt());
/* 114 */         map.put(shared.getXxqadLdPart(), mapo);
/*     */       } 
/*     */       
/* 117 */       Boolean issyncok = Boolean.valueOf(true);
/*     */       try {
/* 119 */         for (String key : map.keySet()) {
/* 120 */           boolean sign = insertXxqadidddetListMap(key, 
/* 121 */               map.get(key), this.dao);
/* 122 */           if (sign) {
/* 123 */             for (Xxqadidddet shared : syncXxqadidddetList) {
/* 124 */               if (shared.getXxqadLdPart().equals(key)) {
/* 125 */                 shared.setXxqadLdPortalread("1");
/* 126 */                 this.daoShared.updateObject(shared);
/*     */               } 
/*     */             } 
/*     */           } else {
/* 130 */             for (Xxqadidddet shared : syncXxqadidddetList) {
/* 131 */               if (shared.getXxqadLdPart().equals(key)) {
/* 132 */                 shared.setXxqadLdPortalread("2");
/* 133 */                 this.daoShared.updateObject(shared);
/*     */               } 
/*     */             } 
/*     */           } 
/* 137 */           this.dao.commit();
/* 138 */           this.daoShared.commit();
/*     */         }
/*     */       
/*     */       }
/* 142 */       catch (Exception e) {
/* 143 */         insertSystemLog("RedMinuteSyncJob", "syncXxqadidddetList", 
/* 144 */             e.getMessage(), "1");
/* 145 */         issyncok = Boolean.valueOf(false);
/*     */       } 
/* 147 */     } catch (Exception e) {
/* 148 */       insertSystemLog("RedMinuteSyncJob", "syncXxqadidddetList", 
/* 149 */           e.getMessage(), "1");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean insertXxqadidddetListMap(String partId, Map<String, Object> mapo, SynBaseDAO dao) {
/* 155 */     boolean sign = false;
/* 156 */     List<ProjectedInventory> inventoryList = dao
/* 157 */       .getObjectList("from ProjectedInventory pji  where pji.part.id ='" + 
/* 158 */         partId + "'");
/* 159 */     ProjectedInventory projectedInventory = null;
/* 160 */     if (inventoryList != null && inventoryList.size() > 0) {
/* 161 */       projectedInventory = inventoryList.get(0);
/* 162 */       projectedInventory.setCreateDate((Date)mapo.get("createDate"));
/* 163 */       projectedInventory.setCurrentQty(new BigDecimal(((Integer)mapo
/* 164 */             .get("qty")).intValue()));
/* 165 */       projectedInventory.setSyncDate(new Date());
/* 166 */       dao.updateObject(projectedInventory);
/* 167 */       sign = true;
/*     */     } else {
/* 169 */       projectedInventory = new ProjectedInventory();
/* 170 */       WmsPart part = (WmsPart)dao.getObject(WmsPart.class, partId);
/* 171 */       if (part != null) {
/* 172 */         projectedInventory.setPart(part);
/* 173 */         projectedInventory.setCreateDate((Date)mapo.get("createDate"));
/* 174 */         projectedInventory.setCurrentQty(new BigDecimal(((Integer)mapo
/* 175 */               .get("qty")).intValue()));
/* 176 */         projectedInventory.setSyncDate(new Date());
/* 177 */         dao.saveObject(projectedInventory);
/* 178 */         sign = true;
/*     */       } else {
/* 180 */         sign = false;
/*     */       } 
/*     */     } 
/* 183 */     projectedInventory = null;
/* 184 */     return sign;
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 189 */     SyncLog log = new SyncLog();
/* 190 */     log.setSync_date(new Date());
/* 191 */     log.setSync_content(content);
/* 192 */     log.setSync_describe(sync_describe);
/* 193 */     log.setSync_object(action);
/* 194 */     log.setSync_results(syncResults);
/* 195 */     this.dao.saveObject(log);
/* 196 */     log = null;
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 200 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 204 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 208 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 212 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 216 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 220 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/IddDetThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */