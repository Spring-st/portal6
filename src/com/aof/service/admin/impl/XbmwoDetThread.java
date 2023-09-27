/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.sync.shared.XbmwoDet;
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
/*     */ 
/*     */ public class XbmwoDetThread
/*     */   extends Thread
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(XbmwoDetThread.class);
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
/*     */   public XbmwoDetThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  45 */     this.dao = dao;
/*  46 */     this.time = time;
/*  47 */     this.daoShared = daoShared;
/*  48 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  55 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  56 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("成品/半成品信息");
/*  57 */         if (sycSleepTime != null) {
/*  58 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  59 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  61 */         sleep(Long.parseLong(this.time));
/*  62 */         Date date = new Date();
/*  63 */         SimpleDateFormat format = new SimpleDateFormat(
/*  64 */             "yyyy/MM/dd hh:mm:ss");
/*  65 */         System.out.println("成品/半成品信息同步-------------------------1-" + 
/*  66 */             format.format(date));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  71 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  74 */         List<String> xbmwoDetList = this.daoShared
/*  75 */           .getObjectList(String.valueOf(beginSql) + 
/*  76 */             " and  ctrl.xxqad_table='xbmwo_det' group by ctrl.xxqad_seq");
/*     */ 
/*     */         
/*     */         try {
/*  80 */           xbmwoDetListSyncRead(xbmwoDetList);
/*  81 */         } catch (KettleException e1) {
/*     */           
/*  83 */           e1.printStackTrace();
/*     */         } 
/*  85 */         System.out.println("-------------------- 成品/半成品信息同步完成");
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
/*     */   private void xbmwoDetListSyncRead(List<String> sqlList) throws KettleException {
/*     */     try {
/*  99 */       List<XbmwoDet> syncXbmwoDetList = this.daoShared
/* 100 */         .getObjectList("from XbmwoDet xbmwodet where (xbmwodet.xbmwo_portalread=0 or xbmwodet.xbmwo_portalread is Null)");
/* 101 */       for (XbmwoDet shared : syncXbmwoDetList) {
/*     */         try {
/* 103 */           insertXbmwoDet(shared, this.dao, this.daoShared);
/* 104 */         } catch (Exception e) {
/* 105 */           insertSystemLog("RedMinuteSyncJob", "xbmwoDetListSyncRead", 
/* 106 */               e.getMessage(), "1");
/*     */         } 
/*     */       } 
/* 109 */     } catch (Exception e) {
/* 110 */       insertSystemLog("RedMinuteSyncJob", "xbmwoDetListSyncRead", 
/* 111 */           e.getMessage(), "1");
/* 112 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertXbmwoDet(XbmwoDet shared, SynBaseDAO dao, SyncDAO daoShared) throws Exception {
/* 120 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 121 */     SimpleDateFormat sdfHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
/* 122 */     SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     EdiProduction ediProduction = new EdiProduction();
/* 133 */     ediProduction.setModels(shared.getXbmwo_hd_code());
/* 134 */     ediProduction.setAsnNo(shared.getXbmwo_type());
/* 135 */     ediProduction.setNumber((shared.getXbmwo_seq() == null) ? "" : shared.getXbmwo_seq());
/* 136 */     ediProduction.setQty(shared.getXbmwo_qty());
/* 137 */     ediProduction.setType(Integer.valueOf(1));
/* 138 */     ediProduction.setProductlinecode(shared.getXbmwo_line());
/* 139 */     ediProduction.setShiftcode(shared.getXbmwo_shift());
/* 140 */     ediProduction.setStaffcode(shared.getXbmwo_emp());
/* 141 */     ediProduction.setTaskDate(sdfHHmmss.parse(shared.getXbmwo_date()));
/* 142 */     ediProduction.setTime(sdf1.format(sdfHHmmss.parse(shared.getXbmwo_date())));
/* 143 */     ediProduction.setStatus(Integer.valueOf(0));
/* 144 */     ediProduction.setTaskTime(sdfHHmmss.parse(shared.getXbmwo_date()));
/* 145 */     ediProduction.setSyncTime(new Date());
/* 146 */     ediProduction.setHandStatus(Integer.valueOf(0));
/* 147 */     ediProduction.setEnabled(Integer.valueOf(0));
/* 148 */     dao.saveObject(ediProduction);
/*     */     
/* 150 */     shared.setXbmwo_portalread("1");
/* 151 */     daoShared.updateObject(shared);
/*     */     
/* 153 */     dao.commit();
/* 154 */     daoShared.commit();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 160 */     SyncLog log = new SyncLog();
/* 161 */     log.setSync_date(new Date());
/* 162 */     log.setSync_content(content);
/* 163 */     log.setSync_describe(sync_describe);
/* 164 */     log.setSync_object(action);
/* 165 */     log.setSync_results(syncResults);
/* 166 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 170 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 174 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 178 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 182 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 186 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 190 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/XbmwoDetThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */