/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import be.ibridge.kettle.core.exception.KettleException;
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.comprehensive.Bom;
/*     */ import com.aof.model.sync.shared.QADBomInfo;
/*     */ import com.aof.model.sync.shared.QADCtrl;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.basic.impl.SycSleepTimeManagerImpl;
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
/*     */ public class BomThread
/*     */   extends Thread
/*     */ {
/*  37 */   private Log log = LogFactory.getLog(BomThread.class);
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   private String time;
/*  45 */   SycSleepTimeManager manager = (SycSleepTimeManager)new SycSleepTimeManagerImpl();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean exit;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BomThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  56 */     this.exit = false;
/*     */     this.dao = dao;
/*     */     this.time = time;
/*     */     this.daoShared = daoShared;
/*  60 */     this.sycSleepTimeManager = sycSleepTimeManager; } public void run() { while (!this.exit) {
/*     */       try {
/*  62 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  63 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("Bom信息");
/*  64 */         if (sycSleepTime != null) {
/*  65 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  66 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         } else {
/*  68 */           this.exit = true;
/*     */         } 
/*  70 */         sleep(Long.parseLong(this.time));
/*  71 */         Date date = new Date();
/*  72 */         SimpleDateFormat format = new SimpleDateFormat(
/*  73 */             "yyyy/MM/dd hh:mm:ss");
/*  74 */         System.out.println("Bom信息同步-------------------------1-" + 
/*  75 */             format.format(date));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  80 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  83 */         List<String> bomList = this.daoShared
/*  84 */           .getObjectList(String.valueOf(beginSql) + 
/*  85 */             " and  ctrl.xxqad_table='xxqad_ps_mstr' group by ctrl.xxqad_seq");
/*     */ 
/*     */         
/*     */         try {
/*  89 */           bomSyncRead(bomList);
/*  90 */         } catch (KettleException e1) {
/*     */           
/*  92 */           e1.printStackTrace();
/*     */         } 
/*  94 */         System.out.println("--------------------Bom信息同步完成");
/*  95 */       } catch (InterruptedException e) {
/*  96 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  98 */         System.gc();
/*     */       } 
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void bomSyncRead(List<String> sqlList) throws KettleException {
/* 106 */     for (String sql : sqlList) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 113 */         List<QADBomInfo> syncBomList = this.daoShared
/* 114 */           .getObjectList("from QADBomInfo bom where bom.xxqad_ps_seq='" + 
/* 115 */             sql + "' and bom.xxqad_ps_portalread=0");
/*     */         
/* 117 */         Boolean issyncok = Boolean.valueOf(true);
/* 118 */         for (QADBomInfo shared : syncBomList) {
/*     */           try {
/* 120 */             boolean bl = insertBom(shared, this.dao, this.daoShared);
/* 121 */             if (!bl) {
/* 122 */               issyncok = Boolean.valueOf(false);
/*     */             }
/* 124 */           } catch (Exception e) {
/* 125 */             insertSystemLog("RedMinuteSyncJob", "bomSyncRead", 
/* 126 */                 e.getMessage(), "1");
/* 127 */             issyncok = Boolean.valueOf(false);
/*     */           } 
/*     */         } 
/* 130 */         if (issyncok.booleanValue()) {
/*     */           
/* 132 */           List<QADCtrl> ctrlList = this.daoShared
/* 133 */             .getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + 
/* 134 */               sql + "' and ctrl.xxqad_portal=0");
/* 135 */           for (QADCtrl ctrl : ctrlList) {
/* 136 */             ctrl.setXxqad_portal("1");
/* 137 */             this.daoShared.updateObject(ctrl);
/*     */           } 
/*     */         } 
/* 140 */       } catch (Exception e) {
/* 141 */         insertSystemLog("RedMinuteSyncJob", "locationSyncRead", 
/* 142 */             e.getMessage(), "1");
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
/*     */   protected boolean insertBom(QADBomInfo shared, SynBaseDAO dao, SyncDAO daoShared) {
/* 155 */     Boolean isRead = Boolean.valueOf(false);
/*     */     
/* 157 */     BigDecimal qty = new BigDecimal(shared.getXxqad_ps_qty_per().intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     String sql = "from Bom bom where bom.father_part.id = '" + shared.getXxqad_ps_par() + 
/* 167 */       "' and bom.child_part.id='" + shared.getXxqad_ps_comp() + "' ";
/* 168 */     List<Bom> list = dao.getObjectList(sql);
/*     */     
/* 170 */     if (list.size() > 0) {
/* 171 */       Bom bom = list.get(0);
/* 172 */       bom.setUnit_qty(qty);
/* 173 */       bom.setStation(shared.getXxqad_ps_ref());
/* 174 */       bom.setProcess((shared.getXxqad_ps_op() == null) ? "" : shared.getXxqad_ps_op().toString());
/* 175 */       bom.setDomain(shared.getXxqad_ps_domain());
/* 176 */       bom.setStart_date(shared.getXxqad_ps_start());
/* 177 */       bom.setEnd_date(shared.getXxqad_ps_end());
/* 178 */       bom.setType(shared.getXxqad_ps_ps_code());
/* 179 */       bom.setRemark(shared.getXxqad_ps_rmks());
/* 180 */       dao.updateObject(bom);
/* 181 */       bom = null;
/* 182 */       shared.setXxqad_ps_portalread("1");
/* 183 */       daoShared.updateObject(shared);
/* 184 */       dao.commit();
/* 185 */       daoShared.commit();
/* 186 */       isRead = Boolean.valueOf(true);
/*     */     } else {
/* 188 */       WmsPart part1 = (WmsPart)dao.getObject(WmsPart.class, shared.getXxqad_ps_par());
/* 189 */       WmsPart part2 = (WmsPart)dao.getObject(WmsPart.class, shared.getXxqad_ps_comp());
/* 190 */       Bom bom = null;
/* 191 */       if (part1 != null && part2 != null) {
/* 192 */         bom = new Bom();
/* 193 */         bom.setFather_part(part1);
/* 194 */         bom.setChild_part(part2);
/* 195 */         bom.setProduct_no(part1);
/* 196 */         bom.setStation(shared.getXxqad_ps_ref());
/* 197 */         bom.setProcess((shared.getXxqad_ps_op() == null) ? "" : shared.getXxqad_ps_op().toString());
/* 198 */         bom.setUnit_qty(qty);
/* 199 */         bom.setDomain(shared.getXxqad_ps_domain());
/* 200 */         bom.setRemark(shared.getXxqad_ps_rmks());
/* 201 */         bom.setStart_date(shared.getXxqad_ps_start());
/* 202 */         bom.setEnd_date(shared.getXxqad_ps_end());
/* 203 */         bom.setType(shared.getXxqad_ps_ps_code());
/* 204 */         dao.saveObject(bom);
/* 205 */         bom = null;
/* 206 */         shared.setXxqad_ps_portalread("1");
/* 207 */         daoShared.updateObject(shared);
/* 208 */         dao.commit();
/* 209 */         daoShared.commit();
/*     */         
/* 211 */         isRead = Boolean.valueOf(true);
/*     */       } 
/*     */     } 
/* 214 */     sql = null;
/* 215 */     list = null;
/* 216 */     return isRead.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 222 */     SyncLog log = new SyncLog();
/* 223 */     log.setSync_date(new Date());
/* 224 */     log.setSync_content(content);
/* 225 */     log.setSync_describe(sync_describe);
/* 226 */     log.setSync_object(action);
/* 227 */     log.setSync_results(syncResults);
/* 228 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 232 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 236 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 240 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 244 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 248 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 252 */     this.time = time;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/BomThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */