/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.basic.SycSleepTimeDAO;
/*     */ import com.aof.dao.basic.WmsPartDAO;
/*     */ import com.aof.dao.schedule.JitProductionPlanDao;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.query.SycSleepTimeQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.impl.BomThread;
/*     */ import com.aof.service.admin.impl.CombinePlanThread;
/*     */ import com.aof.service.admin.impl.DailyPlanThread;
/*     */ import com.aof.service.admin.impl.DescompositionThread;
/*     */ import com.aof.service.admin.impl.HourPlanThread;
/*     */ import com.aof.service.admin.impl.IddDetThread;
/*     */ import com.aof.service.admin.impl.MrpThread;
/*     */ import com.aof.service.admin.impl.MstrThread;
/*     */ import com.aof.service.admin.impl.PoThread;
/*     */ import com.aof.service.admin.impl.QtyDetThread;
/*     */ import com.aof.service.admin.impl.ShipOrderThread;
/*     */ import com.aof.service.admin.impl.SupplierThread;
/*     */ import com.aof.service.admin.impl.WmsPartThread;
/*     */ import com.aof.service.admin.impl.XbmwoDetThread;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.schedule.JitProductionPlanHistoryManager;
/*     */ import com.aof.service.schedule.JitProductionPlanManager;
/*     */ import com.aof.service.schedule.ProjectedInventoryManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class SycSleepTimeManagerImpl
/*     */   extends BaseManager
/*     */   implements SycSleepTimeManager
/*     */ {
/*     */   private SycSleepTimeDAO sycSleepTimeDAO;
/*     */   private SynBaseDAO dao;
/*     */   private SyncDAO daoShared;
/*     */   private JitProductionPlanManager jitProductionPlanManager;
/*     */   private JitProductionPlanDao jitProductionPlanDao;
/*     */   private WmsPartDAO wmsPartdao;
/*     */   private JitProductionPlanHistoryManager jitProductionPlanHistoryManager;
/*     */   private ProjectedInventoryManager projectedInventoryManager;
/*     */   
/*     */   public SycSleepTimeDAO getSycSleepTimeDAO() {
/*  59 */     return this.sycSleepTimeDAO;
/*     */   }
/*     */   
/*     */   public void setSycSleepTimeDAO(SycSleepTimeDAO sycSleepTimeDAO) {
/*  63 */     this.sycSleepTimeDAO = sycSleepTimeDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public SycSleepTime getSycSleepTime(Integer id) {
/*  68 */     return this.sycSleepTimeDAO.getSycSleepTime(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSycSleepTimeCount(Map conditions) {
/*  73 */     return this.sycSleepTimeDAO.getSycSleepTimeCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSycSleepTimeList(Map conditions, int pageNo, int pageSize, SycSleepTimeQueryOrder order, boolean descend) {
/*  79 */     return this.sycSleepTimeDAO.getSycSleepTimeList(conditions, pageNo, 
/*  80 */         pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public SycSleepTime insertSycSleepTime(SycSleepTime sycSleepTime) {
/*  85 */     return this.sycSleepTimeDAO.insertSycSleepTime(sycSleepTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public SycSleepTime updateSycSleepTime(SycSleepTime sycSleepTime) {
/*  90 */     return this.sycSleepTimeDAO.updateSycSleepTime(sycSleepTime);
/*     */   }
/*     */   
/*     */   public void deleteSycSleepTime(SycSleepTime sycSleepTime) {
/*  94 */     this.sycSleepTimeDAO.deleteSycSleepTime(sycSleepTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SycSleepTime getSycSleepTime(String type) {
/* 100 */     return this.sycSleepTimeDAO.getSycSleepTime(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 108 */     SycSleepTime wmsPartSleepTime = new SycSleepTime();
/* 109 */     wmsPartSleepTime = getSycSleepTime("物料信息");
/* 110 */     if (wmsPartSleepTime != null) {
/* 111 */       WmsPartThread thread = new WmsPartThread(this, this.dao, this.daoShared, 
/* 112 */           wmsPartSleepTime.getSleepTime());
/* 113 */       thread.start();
/*     */     } 
/*     */     
/* 116 */     SycSleepTime supplierSleepTime = new SycSleepTime();
/* 117 */     supplierSleepTime = getSycSleepTime("供应商信息");
/* 118 */     if (supplierSleepTime != null) {
/* 119 */       SupplierThread thread = new SupplierThread(this, this.dao, this.daoShared, 
/* 120 */           supplierSleepTime.getSleepTime());
/* 121 */       thread.start();
/*     */     } 
/*     */     
/* 124 */     SycSleepTime poSleepTime = new SycSleepTime();
/* 125 */     poSleepTime = getSycSleepTime("采购单信息");
/* 126 */     if (poSleepTime != null) {
/* 127 */       PoThread thread = new PoThread(this, this.dao, this.daoShared, 
/* 128 */           poSleepTime.getSleepTime());
/* 129 */       thread.start();
/*     */     } 
/*     */     
/* 132 */     SycSleepTime bomSleepTime = new SycSleepTime();
/* 133 */     bomSleepTime = getSycSleepTime("Bom信息");
/* 134 */     if (bomSleepTime != null) {
/* 135 */       BomThread thread = new BomThread(this, this.dao, this.daoShared, 
/* 136 */           bomSleepTime.getSleepTime());
/* 137 */       thread.start();
/*     */     } 
/*     */     
/* 140 */     SycSleepTime xbmwoDetSleepTime = new SycSleepTime();
/* 141 */     xbmwoDetSleepTime = getSycSleepTime("成品/半成品信息");
/* 142 */     if (xbmwoDetSleepTime != null) {
/* 143 */       XbmwoDetThread thread = new XbmwoDetThread(this, this.dao, this.daoShared, 
/* 144 */           xbmwoDetSleepTime.getSleepTime());
/* 145 */       thread.start();
/*     */     } 
/*     */     
/* 148 */     SycSleepTime dailyPlanSleepTime = new SycSleepTime();
/* 149 */     dailyPlanSleepTime = getSycSleepTime("日计划信息");
/* 150 */     if (dailyPlanSleepTime != null) {
/* 151 */       DailyPlanThread thread = new DailyPlanThread(this, this.dao, this.daoShared, 
/* 152 */           dailyPlanSleepTime.getSleepTime());
/* 153 */       thread.start();
/*     */     } 
/*     */     
/* 156 */     SycSleepTime mrpSleepTime = new SycSleepTime();
/* 157 */     mrpSleepTime = getSycSleepTime("mrp信息");
/* 158 */     if (mrpSleepTime != null) {
/* 159 */       MrpThread thread = new MrpThread(this, this.dao, this.daoShared, 
/* 160 */           mrpSleepTime.getSleepTime());
/* 161 */       thread.start();
/*     */     } 
/*     */     
/* 164 */     SycSleepTime iddDetSleepTime = new SycSleepTime();
/* 165 */     iddDetSleepTime = getSycSleepTime("库存信息");
/* 166 */     if (iddDetSleepTime != null) {
/* 167 */       IddDetThread thread = new IddDetThread(this, this.dao, this.daoShared, 
/* 168 */           iddDetSleepTime.getSleepTime());
/* 169 */       thread.start();
/*     */     } 
/*     */     
/* 172 */     SycSleepTime mstrSleepTime = new SycSleepTime();
/* 173 */     mstrSleepTime = getSycSleepTime("ASN号与总成对应关系信息");
/* 174 */     if (mstrSleepTime != null) {
/* 175 */       MstrThread thread = new MstrThread(this, this.dao, this.daoShared, 
/* 176 */           mstrSleepTime.getSleepTime());
/* 177 */       thread.start();
/*     */     } 
/*     */     
/* 180 */     SycSleepTime qtyDetSleepTime = new SycSleepTime();
/* 181 */     qtyDetSleepTime = getSycSleepTime("发货单实收数量信息");
/* 182 */     if (qtyDetSleepTime != null) {
/* 183 */       QtyDetThread thread = new QtyDetThread(this, this.dao, this.daoShared, 
/* 184 */           qtyDetSleepTime.getSleepTime());
/* 185 */       thread.start();
/*     */     } 
/*     */     
/* 188 */     SycSleepTime hourPlanSleepTime = new SycSleepTime();
/* 189 */     hourPlanSleepTime = getSycSleepTime("72小时预计需求报表信息");
/* 190 */     if (hourPlanSleepTime != null) {
/* 191 */       HourPlanThread thread = new HourPlanThread(this, this.dao, this.daoShared, 
/* 192 */           hourPlanSleepTime.getSleepTime());
/* 193 */       thread.start();
/*     */     } 
/*     */     
/* 196 */     SycSleepTime descompositionSleepTime = new SycSleepTime();
/* 197 */     descompositionSleepTime = getSycSleepTime("自动分解信息");
/* 198 */     if (descompositionSleepTime != null) {
/* 199 */       DescompositionThread thread = new DescompositionThread(this, this.dao, 
/* 200 */           this.daoShared, this.jitProductionPlanDao, 
/* 201 */           descompositionSleepTime.getSleepTime());
/* 202 */       thread.start();
/*     */     } 
/*     */     
/* 205 */     SycSleepTime shipOrderSleepTime = new SycSleepTime();
/* 206 */     shipOrderSleepTime = getSycSleepTime("送货单信息");
/* 207 */     if (shipOrderSleepTime != null) {
/* 208 */       ShipOrderThread thread = new ShipOrderThread(this, this.dao, this.daoShared, 
/* 209 */           shipOrderSleepTime.getSleepTime());
/* 210 */       thread.start();
/*     */     } 
/*     */ 
/*     */     
/* 214 */     SycSleepTime combinePlanSleepTime = new SycSleepTime();
/* 215 */     combinePlanSleepTime = getSycSleepTime("Jit物料计划自动合并");
/* 216 */     if (combinePlanSleepTime != null) {
/* 217 */       CombinePlanThread thread = new CombinePlanThread(this, combinePlanSleepTime.getSleepTime(), this.jitProductionPlanManager, this.wmsPartdao, this.jitProductionPlanHistoryManager, this.projectedInventoryManager);
/* 218 */       thread.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 223 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 227 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 231 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 235 */     this.daoShared = daoShared;
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
/*     */   
/*     */   public JitProductionPlanManager getJitProductionPlanManager() {
/* 248 */     return this.jitProductionPlanManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJitProductionPlanManager(JitProductionPlanManager jitProductionPlanManager) {
/* 253 */     this.jitProductionPlanManager = jitProductionPlanManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public JitProductionPlanHistoryManager getJitProductionPlanHistoryManager() {
/* 258 */     return this.jitProductionPlanHistoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJitProductionPlanHistoryManager(JitProductionPlanHistoryManager jitProductionPlanHistoryManager) {
/* 263 */     this.jitProductionPlanHistoryManager = jitProductionPlanHistoryManager;
/*     */   }
/*     */   
/*     */   public ProjectedInventoryManager getProjectedInventoryManager() {
/* 267 */     return this.projectedInventoryManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProjectedInventoryManager(ProjectedInventoryManager projectedInventoryManager) {
/* 272 */     this.projectedInventoryManager = projectedInventoryManager;
/*     */   }
/*     */   
/*     */   public WmsPartDAO getWmsPartdao() {
/* 276 */     return this.wmsPartdao;
/*     */   }
/*     */   
/*     */   public void setWmsPartdao(WmsPartDAO wmsPartdao) {
/* 280 */     this.wmsPartdao = wmsPartdao;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/SycSleepTimeManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */