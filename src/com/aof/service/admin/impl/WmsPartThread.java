/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.sync.shared.QADCtrl;
/*     */ import com.aof.model.sync.shared.QADWmsPart;
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
/*     */ 
/*     */ public class WmsPartThread
/*     */   extends Thread
/*     */ {
/*  37 */   private Log log = LogFactory.getLog(WmsPartThread.class);
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
/*     */   public WmsPartThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  49 */     this.dao = dao;
/*  50 */     this.time = time;
/*  51 */     this.daoShared = daoShared;
/*  52 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  59 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  60 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("物料信息");
/*  61 */         if (sycSleepTime != null) {
/*  62 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  63 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  65 */         sleep(Long.parseLong(this.time));
/*  66 */         Date date = new Date();
/*  67 */         SimpleDateFormat format = new SimpleDateFormat(
/*  68 */             "yyyy/MM/dd hh:mm:ss");
/*  69 */         System.out.println("物料信息同步-------------------------1-" + 
/*  70 */             format.format(date));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  75 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */         
/*  77 */         List<String> partList = this.daoShared
/*  78 */           .getObjectList(String.valueOf(beginSql) + 
/*  79 */             " and  ctrl.xxqad_table='xxqad_pt_mstr' group by ctrl.xxqad_seq");
/*     */         
/*     */         try {
/*  82 */           wmsPartSyncRead(partList);
/*  83 */         } catch (KettleException e1) {
/*     */           
/*  85 */           e1.printStackTrace();
/*     */         } 
/*  87 */         System.out.println("--------------------物料信息同步完成");
/*  88 */       } catch (InterruptedException e) {
/*  89 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  91 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void wmsPartSyncRead(List<String> sqlList) throws KettleException {
/*  99 */     for (String sql : sqlList) {
/*     */       try {
/* 101 */         List<QADWmsPart> syncPartList = this.daoShared
/* 102 */           .getObjectList("from QADWmsPart part where part.xxqadPtSeq='" + 
/* 103 */             sql + "' and part.xxqadPtPortalread=0");
/* 104 */         Boolean issyncok = Boolean.valueOf(true);
/* 105 */         for (QADWmsPart shared : syncPartList) {
/*     */ 
/*     */           
/*     */           try {
/* 109 */             SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
/* 110 */             String xxqadPtPart = shared.getXxqadPtPart();
/* 111 */             String xxqadPtUm = shared.getXxqadPtUm();
/* 112 */             String xxqadPtDesc1 = shared.getXxqadPtDesc1();
/* 113 */             String xxqadPtDesc2 = shared.getXxqadPtDesc2();
/* 114 */             String xxqadPtPartType = shared.getXxqadPtPartType();
/* 115 */             String xxqadPtProdLine = shared.getXxqadPtProdLine();
/* 116 */             String xxqadPtStatus = shared.getXxqadPtStatus();
/* 117 */             Integer xxqadPtSize = shared.getXxqadPtSize();
/* 118 */             String xxqadPtGroup = shared.getXxqadPtGroup();
/* 119 */             Integer xxqadPtShipWt = shared.getXxqadPtShipWt();
/* 120 */             Integer xxqadPtNetWt = shared.getXxqadPtNetWt();
/* 121 */             String xxqadPtVend = shared.getXxqadPtVend();
/* 122 */             String xxqadPtSite = shared.getXxqadPtSite();
/* 123 */             String xxqadPtDomain = shared.getXxqadPtDomain();
/* 124 */             String xxqadPtDraw = shared.getXxqadPtDraw();
/* 125 */             BigDecimal xxqadPtFrClass = shared.getXxqadPtFrClass();
/*     */ 
/*     */             
/* 128 */             WmsPart oldPart = (WmsPart)this.dao.getObject(
/* 129 */                 WmsPart.class, xxqadPtPart);
/* 130 */             if (oldPart == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 136 */               WmsPart part = new WmsPart();
/* 137 */               part.setId(xxqadPtPart);
/* 138 */               part.setUnit(xxqadPtUm);
/* 139 */               part.setDescribe1(xxqadPtDesc1);
/* 140 */               part.setDescribe2(xxqadPtDesc2);
/* 141 */               part.setName(xxqadPtDesc1);
/* 142 */               part.setPartType(xxqadPtPartType);
/* 143 */               part.setEnabled(EnabledDisabled.ENABLED);
/* 144 */               part.setProductLine(xxqadPtProdLine);
/* 145 */               if (xxqadPtStatus.equals("AC")) {
/* 146 */                 part.setFreeze_status(YesNo.NO);
/* 147 */               } else if (xxqadPtStatus.equals("HOLD")) {
/* 148 */                 part.setFreeze_status(YesNo.YES);
/*     */               } 
/* 150 */               part.setOrd_mult(new BigDecimal(xxqadPtSize.intValue()));
/* 151 */               part.setProductClass(xxqadPtGroup);
/* 152 */               part.setLowQty(new BigDecimal(xxqadPtShipWt.intValue()));
/* 153 */               part.setHighQty(new BigDecimal(xxqadPtNetWt.intValue()));
/* 154 */               part.setVend(xxqadPtVend);
/* 155 */               part.setProductSpecifications(xxqadPtDraw);
/*     */               
/* 157 */               part.setDomain(xxqadPtDomain);
/* 158 */               part.setSecurityQty(xxqadPtFrClass);
/* 159 */               this.dao.saveObject(part);
/*     */             } else {
/*     */               
/* 162 */               oldPart.setUnit(xxqadPtUm);
/* 163 */               oldPart.setDescribe1(xxqadPtDesc1);
/* 164 */               oldPart.setDescribe2(xxqadPtDesc2);
/* 165 */               oldPart.setName(xxqadPtDesc1);
/* 166 */               oldPart.setPartType(xxqadPtPartType);
/* 167 */               oldPart.setEnabled(EnabledDisabled.ENABLED);
/* 168 */               oldPart.setProductLine(xxqadPtProdLine);
/* 169 */               if (xxqadPtStatus.equals("AC")) {
/* 170 */                 oldPart.setFreeze_status(YesNo.NO);
/* 171 */               } else if (xxqadPtStatus.equals("HOLD")) {
/* 172 */                 oldPart.setFreeze_status(YesNo.YES);
/*     */               } 
/* 174 */               oldPart.setOrd_mult(new BigDecimal(xxqadPtSize.intValue()));
/* 175 */               oldPart.setProductClass(xxqadPtGroup);
/* 176 */               oldPart.setLowQty(new BigDecimal(xxqadPtShipWt.intValue()));
/* 177 */               oldPart.setHighQty(new BigDecimal(xxqadPtNetWt.intValue()));
/* 178 */               oldPart.setVend(xxqadPtVend);
/* 179 */               oldPart.setDomain(xxqadPtDomain);
/* 180 */               oldPart.setSecurityQty(xxqadPtFrClass);
/* 181 */               oldPart.setProductSpecifications(xxqadPtDraw);
/* 182 */               this.dao.updateObject(oldPart);
/*     */             } 
/*     */ 
/*     */             
/* 186 */             shared.setXxqadPtPortalread("1");
/* 187 */             this.daoShared.updateObject(shared);
/* 188 */             this.dao.commit();
/* 189 */             this.daoShared.commit();
/*     */ 
/*     */           
/*     */           }
/* 193 */           catch (Exception e) {
/* 194 */             insertSystemLog("DailySyncJob", "wmsPartSyncRead1", 
/* 195 */                 e.getMessage(), "1");
/* 196 */             issyncok = Boolean.valueOf(false);
/*     */           } 
/*     */         } 
/*     */         
/* 200 */         if (issyncok.booleanValue()) {
/*     */           
/* 202 */           List<QADCtrl> ctrlList = this.daoShared
/* 203 */             .getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + 
/* 204 */               sql + "' and ctrl.xxqad_portal=0");
/* 205 */           for (QADCtrl ctrl : ctrlList) {
/* 206 */             ctrl.setXxqad_portal("1");
/* 207 */             this.daoShared.updateObject(ctrl);
/* 208 */             this.daoShared.commit();
/*     */           } 
/*     */         } 
/* 211 */       } catch (Exception e) {
/* 212 */         insertSystemLog("RedMinuteSyncJob", "wmsPartSyncRead1", 
/* 213 */             e.getMessage(), "1");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 220 */     SyncLog log = new SyncLog();
/* 221 */     log.setSync_date(new Date());
/* 222 */     log.setSync_content(content);
/* 223 */     log.setSync_describe(sync_describe);
/* 224 */     log.setSync_object(action);
/* 225 */     log.setSync_results(syncResults);
/* 226 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 230 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 234 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 238 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 242 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 246 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 250 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/WmsPartThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */