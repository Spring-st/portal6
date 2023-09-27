/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.schedule.NjitNpoPlan;
/*     */ import com.aof.model.schedule.Njitnpoplanhistory;
/*     */ import com.aof.model.sync.shared.XxqadMrpDet;
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
/*     */ public class MrpThread
/*     */   extends Thread
/*     */ {
/*  36 */   private Log log = LogFactory.getLog(MrpThread.class);
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
/*     */   public MrpThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  48 */     this.dao = dao;
/*  49 */     this.time = time;
/*  50 */     this.daoShared = daoShared;
/*  51 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  58 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  59 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("mrp信息");
/*  60 */         if (sycSleepTime != null) {
/*  61 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  62 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  64 */         sleep(Long.parseLong(this.time));
/*  65 */         Date date = new Date();
/*  66 */         SimpleDateFormat format = new SimpleDateFormat(
/*  67 */             "yyyy/MM/dd hh:mm:ss");
/*  68 */         System.out.println("mrp信息同步-------------------------1-" + 
/*  69 */             format.format(date));
/*     */         
/*  71 */         List<String> xxqadMrpDetList = this.daoShared
/*  72 */           .getObjectList("select xmdet.xxqadMrpRev from XxqadMrpDet xmdet where xmdet.xxqadMrpPortalread='0'  group by xmdet.xxqadMrpRev order by xmdet.xxqadMrpRev asc ");
/*     */         
/*     */         try {
/*  75 */           xxqadMrpDetSyncRead(xxqadMrpDetList);
/*  76 */         } catch (KettleException e1) {
/*  77 */           e1.printStackTrace();
/*     */         } 
/*  79 */         System.out.println("-------------------- mrp信息同步完成");
/*  80 */       } catch (InterruptedException e) {
/*  81 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void xxqadMrpDetSyncRead(List<String> sqlList) throws KettleException {
/*  91 */     for (String sql : sqlList) {
/*     */       try {
/*  93 */         List<XxqadMrpDet> syncXxqadMrpDetList = this.daoShared
/*  94 */           .getObjectList("from XxqadMrpDet xmdet where xmdet.xxqadMrpRev='" + 
/*  95 */             sql + "' and xmdet.xxqadMrpPortalread='0'");
/*  96 */         int a = 0;
/*  97 */         for (XxqadMrpDet shared : syncXxqadMrpDetList) {
/*     */           try {
/*  99 */             int i = insertXxqadMrpDet(shared, this.dao, this.daoShared);
/* 100 */             a += i;
/* 101 */           } catch (Exception e) {
/* 102 */             insertSystemLog("RedMinuteSyncJob", 
/* 103 */                 "xxqadMrpDetSyncRead", e.getMessage(), "1");
/*     */           } 
/*     */         } 
/* 106 */         if (a > 0) {
/* 107 */           this.dao.commit();
/* 108 */           this.daoShared.commit();
/* 109 */           deleteNjitNpoPlanDetList(sql);
/*     */         } 
/* 111 */       } catch (Exception e) {
/* 112 */         insertSystemLog("RedMinuteSyncJob", "xxqadMrpDetSyncRead", 
/* 113 */             e.getMessage(), "1");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int insertXxqadMrpDet(XxqadMrpDet shared, SynBaseDAO dao, SyncDAO daoShared) throws Exception {
/* 121 */     int i = 0;
/* 122 */     String part = shared.getXxqadMrpPart();
/* 123 */     WmsPart wmspart = (WmsPart)dao.getObject(WmsPart.class, part);
/* 124 */     NjitNpoPlan njitNpoPlan = null;
/* 125 */     if (wmspart != null) {
/* 126 */       njitNpoPlan = new NjitNpoPlan();
/* 127 */       njitNpoPlan.setPartId(wmspart);
/* 128 */       njitNpoPlan.setCreateDate(new Date());
/* 129 */       njitNpoPlan.setDataset(shared.getXxqadMrpDataset());
/* 130 */       njitNpoPlan.setDetail(shared.getXxqadMrpDetail());
/* 131 */       njitNpoPlan.setIsEnabled(EnabledDisabled.ENABLED);
/* 132 */       njitNpoPlan.setLine(shared.getXxqadMrpLine());
/* 133 */       njitNpoPlan.setNbr(shared.getXxqadMrpNbr());
/* 134 */       njitNpoPlan.setNeedDate(shared.getXxqadMrpDueDate());
/* 135 */       njitNpoPlan.setQty(shared.getXxqadMrpQty());
/* 136 */       njitNpoPlan.setRelDate(shared.getXxqadMrpRelDate());
/* 137 */       njitNpoPlan.setTime(shared.getXxqadMrpTime());
/* 138 */       njitNpoPlan.setType(shared.getXxqadMrpType());
/* 139 */       njitNpoPlan.setVersion(shared.getXxqadMrpRev());
/* 140 */       dao.saveObject(njitNpoPlan);
/*     */       
/* 142 */       shared.setXxqadMrpPortalread("1");
/* 143 */       daoShared.updateObject(shared);
/*     */       
/* 145 */       i++;
/*     */     } 
/* 147 */     njitNpoPlan = null;
/* 148 */     return i;
/*     */   }
/*     */   
/*     */   public void deleteNjitNpoPlanDetList(String str) {
/* 152 */     List<NjitNpoPlan> njitNpoPlanList = this.dao
/* 153 */       .getObjectList("from NjitNpoPlan plan where plan.version !='" + 
/* 154 */         str + "' or  plan.version is null");
/* 155 */     for (NjitNpoPlan njitNpoPlan : njitNpoPlanList) {
/* 156 */       Njitnpoplanhistory njitnpoplanhistory = new Njitnpoplanhistory(
/* 157 */           njitNpoPlan);
/* 158 */       this.dao.saveObject(njitnpoplanhistory);
/* 159 */       this.dao.removeObject(njitNpoPlan);
/*     */     } 
/* 161 */     this.dao.commit();
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 166 */     SyncLog log = new SyncLog();
/* 167 */     log.setSync_date(new Date());
/* 168 */     log.setSync_content(content);
/* 169 */     log.setSync_describe(sync_describe);
/* 170 */     log.setSync_object(action);
/* 171 */     log.setSync_results(syncResults);
/* 172 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 176 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 180 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 184 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 188 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 192 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 196 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/MrpThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */