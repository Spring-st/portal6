/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.schedule.QadOrEdi;
/*     */ import com.aof.model.sync.shared.MesSeatType;
/*     */ import com.aof.model.sync.shared.XxqadXxptMstr;
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
/*     */ public class MstrThread
/*     */   extends Thread
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(MstrThread.class);
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
/*     */   public MstrThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
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
/*  56 */         sycSleepTime = this.sycSleepTimeManager
/*  57 */           .getSycSleepTime("ASN号与总成对应关系信息");
/*  58 */         if (sycSleepTime != null) {
/*  59 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  60 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  62 */         sleep(Long.parseLong(this.time));
/*  63 */         Date date = new Date();
/*  64 */         SimpleDateFormat format = new SimpleDateFormat(
/*  65 */             "yyyy/MM/dd hh:mm:ss");
/*  66 */         System.out.println("ASN号与总成对应关系信息同步-------------------------1-" + 
/*  67 */             format.format(date));
/*     */         
/*     */         try {
/*  70 */           xxqadXxptMstr();
/*  71 */         } catch (Exception e1) {
/*     */           
/*  73 */           e1.printStackTrace();
/*     */         } 
/*  75 */         System.out.println("-------------------- ASN号与总成对应关系信息同步完成");
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
/*     */   private void xxqadXxptMstr() {
/*     */     try {
/*  90 */       List<XxqadXxptMstr> mstrList = this.daoShared
/*  91 */         .getObjectList(" from XxqadXxptMstr mstr where (mstr.xxqad_xxpt_portalread = 0 or mstr.xxqad_xxpt_portalread is null)");
/*  92 */       QadOrEdi qad = null;
/*  93 */       for (XxqadXxptMstr xxqadXxptMstr : mstrList) {
/*     */         
/*  95 */         WmsPart part = (WmsPart)this.dao.getObject(WmsPart.class, 
/*  96 */             xxqadXxptMstr.getXxqad_xxpt_part());
/*     */         
/*  98 */         if (part == null) {
/*     */           
/* 100 */           System.out.println("ASN与总成对应中没有总成号：" + 
/* 101 */               xxqadXxptMstr.getXxqad_xxpt_part());
/*     */           
/*     */           continue;
/*     */         } 
/* 105 */         List<QadOrEdi> qadOrEdiList = this.dao
/* 106 */           .getObjectList(" from QadOrEdi qad where qad.models='" + 
/* 107 */             xxqadXxptMstr.getXxqad_xxpt_alc_code() + 
/* 108 */             "' and qad.qadPart.id='" + 
/* 109 */             xxqadXxptMstr.getXxqad_xxpt_part() + "'");
/* 110 */         List<MesSeatType> seatTypes = this.daoShared
/* 111 */           .getObjectList(" from MesSeatType mes where mes.alc='" + 
/* 112 */             xxqadXxptMstr.getXxqad_xxpt_alc_code() + 
/* 113 */             "'");
/* 114 */         if (qadOrEdiList.size() != 0) {
/*     */           
/* 116 */           qad = qadOrEdiList.get(0);
/* 117 */           qad.setQty(xxqadXxptMstr.getXxqad_xxpt_qty());
/* 118 */           qad.setCreateDate(xxqadXxptMstr
/* 119 */               .getXxqad_xxpt_createdt());
/* 120 */           if (seatTypes.size() == 0) {
/* 121 */             qad.setDes(null);
/*     */           } else {
/* 123 */             qad.setDes(((MesSeatType)seatTypes.get(0)).getDes());
/*     */           } 
/* 125 */           this.dao.updateObject(qad);
/*     */         } else {
/*     */           
/* 128 */           qad = new QadOrEdi();
/* 129 */           qad.setModels(xxqadXxptMstr.getXxqad_xxpt_alc_code());
/* 130 */           qad.setQadPart(part);
/* 131 */           qad.setQty(xxqadXxptMstr.getXxqad_xxpt_qty());
/* 132 */           qad.setCreateDate(xxqadXxptMstr
/* 133 */               .getXxqad_xxpt_createdt());
/* 134 */           if (seatTypes.size() == 0) {
/* 135 */             qad.setDes(null);
/*     */           } else {
/* 137 */             qad.setDes(((MesSeatType)seatTypes.get(0)).getDes());
/*     */           } 
/* 139 */           this.dao.saveObject(qad);
/*     */         } 
/* 141 */         xxqadXxptMstr.setXxqad_xxpt_portalread("1");
/*     */         
/* 143 */         this.daoShared.updateObject(xxqadXxptMstr);
/* 144 */         this.dao.commit();
/* 145 */         this.daoShared.commit();
/*     */       } 
/*     */       
/* 148 */       qad = null;
/* 149 */       mstrList = null;
/* 150 */     } catch (Exception e) {
/* 151 */       insertSystemLog("RedMinuteSyncJob", "syncXxqadXxptMstr", 
/* 152 */           e.getMessage(), "1");
/*     */       
/* 154 */       e.printStackTrace();
/*     */     } 
/*     */   }
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


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/MstrThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */