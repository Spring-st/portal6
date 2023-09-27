/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.schedule.JitProductionPlanDao;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.comprehensive.Bom;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.schedule.EdiProductionErrorLog;
/*     */ import com.aof.model.schedule.JitProductionPlan;
/*     */ import com.aof.model.schedule.QadOrEdi;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
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
/*     */ public class DescompositionThread
/*     */   extends Thread
/*     */ {
/*  37 */   private Log log = LogFactory.getLog(DescompositionThread.class);
/*     */ 
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   
/*     */   private JitProductionPlanDao jitProductionPlanDao;
/*     */   
/*     */   private String time;
/*     */ 
/*     */   
/*     */   public DescompositionThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, JitProductionPlanDao jitProductionPlanDao, String time) {
/*  52 */     this.dao = dao;
/*  53 */     this.time = time;
/*  54 */     this.daoShared = daoShared;
/*  55 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*  56 */     this.jitProductionPlanDao = jitProductionPlanDao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  63 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  64 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("自动分解信息");
/*  65 */         if (sycSleepTime != null) {
/*  66 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  67 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  69 */         sleep(Long.parseLong(this.time));
/*  70 */         Date date = new Date();
/*  71 */         SimpleDateFormat format = new SimpleDateFormat(
/*  72 */             "yyyy/MM/dd hh:mm:ss");
/*  73 */         System.out.println("自动分解信息同步-------------------------1-" + 
/*  74 */             format.format(date));
/*     */         
/*     */         try {
/*  77 */           descompositionProduction();
/*  78 */         } catch (Exception e1) {
/*  79 */           e1.printStackTrace();
/*     */         } 
/*  81 */         System.out.println("-------------------- 自动分解信息同步完成");
/*  82 */       } catch (InterruptedException e) {
/*  83 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  85 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void descompositionProduction() {
/*  92 */     List<EdiProductionErrorLog> errorLogList = new ArrayList<EdiProductionErrorLog>();
/*  93 */     EdiProductionErrorLog errorLog = null;
/*  94 */     List<EdiProduction> ediProductionList = this.dao
/*  95 */       .getObjectList(" from EdiProduction entity where  entity.status!=1 and entity.enabled=0 and entity.type=1");
/*  96 */     for (EdiProduction ediProduction : ediProductionList) {
/*     */       try {
/*  98 */         List<QadOrEdi> qadEdiList = this.dao
/*  99 */           .getObjectList(" from QadOrEdi entity where entity.models ='" + 
/* 100 */             ediProduction.getAsnNo() + "'");
/* 101 */         if (qadEdiList.size() > 0) {
/* 102 */           boolean sign = true;
/* 103 */           for (QadOrEdi qadOrEdi : qadEdiList) {
/* 104 */             List<Bom> bomList = this.dao
/* 105 */               .getObjectList(" from Bom ba where ba.father_part.id ='" + 
/* 106 */                 qadOrEdi.getQadPart().getId() + "'");
/* 107 */             if (bomList == null || bomList.size() == 0) {
/* 108 */               sign = false;
/* 109 */               errorLog = new EdiProductionErrorLog();
/* 110 */               errorLog.setAsnNo(ediProduction.getAsnNo());
/* 111 */               errorLog.setNumber(ediProduction.getNumber());
/* 112 */               errorLog.setModels(ediProduction.getModels());
/* 113 */               errorLog.setDescribe(ediProduction.getDescribe());
/* 114 */               errorLog.setProductlinecode(ediProduction
/* 115 */                   .getProductlinecode());
/* 116 */               errorLog.setShiftcode(ediProduction.getShiftcode());
/* 117 */               errorLog.setStaffcode(ediProduction.getStaffcode());
/* 118 */               errorLog.setTaskDate(ediProduction.getTaskDate());
/* 119 */               errorLog.setTime(ediProduction.getTime());
/* 120 */               errorLog.setSyncTime(ediProduction.getSyncTime());
/* 121 */               errorLog.setQty(ediProduction.getQty());
/* 122 */               errorLog.setType(ediProduction.getType());
/* 123 */               errorLog.setUnit(ediProduction.getUnit());
/* 124 */               errorLog.setErrorInfo("分解失败,在物料bom里面找不到父料号" + 
/* 125 */                   qadOrEdi.getQadPart().getId());
/* 126 */               errorLogList.add(errorLog);
/*     */             } 
/*     */           } 
/* 129 */           if (sign) {
/* 130 */             for (QadOrEdi qadOrEdi : qadEdiList) {
/* 131 */               List<Bom> bomList = this.dao
/* 132 */                 .getObjectList(" from Bom ba where ba.father_part.id='" + 
/* 133 */                   qadOrEdi.getQadPart().getId() + 
/* 134 */                   "'");
/* 135 */               if (bomList != null && bomList.size() > 0) {
/* 136 */                 List<JitProductionPlan> jitProductionPlanList = this.jitProductionPlanDao
/* 137 */                   .DismantlingBom(
/* 138 */                     ediProduction, 
/* 139 */                     qadOrEdi.getQadPart(), 
/* 140 */                     new BigDecimal(
/* 141 */                       (ediProduction.getQty() == null) ? 0 : 
/* 142 */                       ediProduction
/* 143 */                       .getQty().intValue()));
/* 144 */                 insertJitProductionPlan(jitProductionPlanList);
/* 145 */                 ediProduction.setStatus(Integer.valueOf(1));
/* 146 */                 this.dao.updateObject(ediProduction);
/* 147 */                 this.dao.commit();
/*     */               } 
/*     */             }  continue;
/*     */           } 
/* 151 */           ediProduction.setStatus(Integer.valueOf(2));
/* 152 */           this.dao.updateObject(ediProduction);
/* 153 */           this.dao.commit();
/*     */           continue;
/*     */         } 
/* 156 */         ediProduction.setStatus(Integer.valueOf(2));
/* 157 */         this.dao.updateObject(ediProduction);
/* 158 */         errorLog = new EdiProductionErrorLog();
/* 159 */         errorLog.setAsnNo(ediProduction.getAsnNo());
/* 160 */         errorLog.setNumber(ediProduction.getNumber());
/* 161 */         errorLog.setModels(ediProduction.getModels());
/* 162 */         errorLog.setDescribe(ediProduction.getDescribe());
/* 163 */         errorLog.setProductlinecode(ediProduction
/* 164 */             .getProductlinecode());
/* 165 */         errorLog.setShiftcode(ediProduction.getShiftcode());
/* 166 */         errorLog.setStaffcode(ediProduction.getStaffcode());
/* 167 */         errorLog.setTaskDate(ediProduction.getTaskDate());
/* 168 */         errorLog.setTime(ediProduction.getTime());
/* 169 */         errorLog.setSyncTime(ediProduction.getSyncTime());
/* 170 */         errorLog.setQty(ediProduction.getQty());
/* 171 */         errorLog.setType(ediProduction.getType());
/* 172 */         errorLog.setUnit(ediProduction.getUnit());
/* 173 */         errorLog.setErrorInfo("分解失败,ASN与总成对应关系找不到" + 
/* 174 */             ediProduction.getAsnNo());
/* 175 */         errorLogList.add(errorLog);
/*     */       }
/* 177 */       catch (Exception e) {
/*     */         
/* 179 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 182 */     if (errorLogList.size() != 0) {
/* 183 */       for (EdiProductionErrorLog ediProductionErrorLog : errorLogList) {
/* 184 */         this.dao.saveObject(ediProductionErrorLog);
/* 185 */         this.dao.commit();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertJitProductionPlan(List<JitProductionPlan> jitProductionPlanList) {
/* 192 */     for (JitProductionPlan jitProductionPlan : jitProductionPlanList) {
/* 193 */       if (jitProductionPlan.getSign().intValue() == 0) {
/* 194 */         this.dao.saveObject(jitProductionPlan);
/* 195 */         this.dao.commit(); continue;
/*     */       } 
/* 197 */       insertJitProductionPlan(jitProductionPlan
/* 198 */           .getJitProductionPlanList());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 205 */     SyncLog log = new SyncLog();
/* 206 */     log.setSync_date(new Date());
/* 207 */     log.setSync_content(content);
/* 208 */     log.setSync_describe(sync_describe);
/* 209 */     log.setSync_object(action);
/* 210 */     log.setSync_results(syncResults);
/* 211 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 215 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 219 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 223 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 227 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 231 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 235 */     this.time = time;
/*     */   }
/*     */   
/*     */   public JitProductionPlanDao getJitProductionPlanDao() {
/* 239 */     return this.jitProductionPlanDao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJitProductionPlanDao(JitProductionPlanDao jitProductionPlanDao) {
/* 244 */     this.jitProductionPlanDao = jitProductionPlanDao;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/DescompositionThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */