/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.basic.SupplierPartDAO;
/*     */ import com.aof.dao.basic.WmsPartDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.SupplierPart;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*     */ import com.aof.model.basic.query.WmsPartQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.impl.WmsPartThread;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WmsPartManagerImpl
/*     */   extends BaseManager
/*     */   implements WmsPartManager {
/*     */   private WmsPartDAO dao;
/*     */   private SupplierPartDAO supplierPartDAO;
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*     */   private SynBaseDAO baseDao;
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   public void setSupplierPartDAO(SupplierPartDAO supplierPartDAO) {
/*  32 */     this.supplierPartDAO = supplierPartDAO;
/*     */   }
/*     */   
/*     */   public void setDao(WmsPartDAO dao) {
/*  36 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPart getWmsPart(String id) {
/*  41 */     return this.dao.getWmsPart(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWmsPartListCount(Map conditions) {
/*  46 */     return this.dao.getWmsPartListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getWmsPartList(Map conditions, int pageNo, int pageSize, WmsPartQueryOrder order, boolean descend) {
/*  52 */     return this.dao.getWmsPartList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPart insertWmsPart(WmsPart city) {
/*  57 */     return this.dao.insertWmsPart(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public WmsPart updateWmsPart(WmsPart city) {
/*  62 */     return this.dao.updateWmsPart(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledWmsPartList() {
/*  67 */     return this.dao.getEnabledWmsPartList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteWmsPart(WmsPart city) {
/*  72 */     this.dao.deleteWmsPart(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExits(String workCenter, String partCode) {
/*  77 */     return this.dao.isExits(workCenter, partCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WmsPart> getWmsPartListBySupplierPartCode(List<WmsPart> list, Supplier supplier) {
/*  82 */     for (WmsPart part : list) {
/*  83 */       SupplierPart supplierPart = this.supplierPartDAO.supplierPart(
/*  84 */           supplier.getId(), (new StringBuilder(String.valueOf(part.getId()))).toString());
/*     */       
/*  86 */       part.setSupplierPartCode(supplierPart.getSupplierPart());
/*     */     } 
/*  88 */     return list;
/*     */   }
/*     */   
/*     */   public List getWmsPartListEnabledAll() {
/*  92 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  93 */     conditions.put(WmsPartQueryCondition.ENABLED_EQ, 
/*  94 */         EnabledDisabled.ENABLED);
/*  95 */     return this.dao.getWmsPartList(conditions, -1, -1, null, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validatePartIsFreeze(String part) {
/* 100 */     return this.dao.validatePartIsFreeze(part);
/*     */   }
/*     */   
/*     */   public void init() {
/* 104 */     SycSleepTime sycSleepTime = new SycSleepTime();
/*     */     
/* 106 */     sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("物料信息");
/* 107 */     if (sycSleepTime != null) {
/* 108 */       WmsPartThread thread = new WmsPartThread(this.sycSleepTimeManager, this.baseDao, this.daoShared, sycSleepTime.getSleepTime());
/* 109 */       thread.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SycSleepTimeManager getSycSleepTimeManager() {
/* 115 */     return this.sycSleepTimeManager;
/*     */   }
/*     */   
/*     */   public void setSycSleepTimeManager(SycSleepTimeManager sycSleepTimeManager) {
/* 119 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */   
/*     */   public SynBaseDAO getBaseDao() {
/* 123 */     return this.baseDao;
/*     */   }
/*     */   
/*     */   public void setBaseDao(SynBaseDAO baseDao) {
/* 127 */     this.baseDao = baseDao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 131 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 135 */     this.daoShared = daoShared;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/WmsPartManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */