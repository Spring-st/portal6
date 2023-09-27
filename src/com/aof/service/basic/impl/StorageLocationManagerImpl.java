/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.basic.StorageLocationDAO;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.query.StorageLocationQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class StorageLocationManagerImpl
/*     */   extends BaseManager
/*     */   implements StorageLocationManager
/*     */ {
/*     */   private StorageLocationDAO dao;
/*     */   
/*     */   public void setDao(StorageLocationDAO dao) {
/*  20 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation getStorageLocation(Integer id) {
/*  25 */     return this.dao.getStorageLocation(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStorageLocationListCount(Map conditions) {
/*  30 */     return this.dao.getStorageLocationListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStorageLocationList(Map conditions, int pageNo, int pageSize, StorageLocationQueryOrder order, boolean descend) {
/*  36 */     return this.dao.getStorageLocationList(conditions, pageNo, pageSize, order, 
/*  37 */         descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation insertStorageLocation(StorageLocation city) {
/*  42 */     return this.dao.insertStorageLocation(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation updateStorageLocation(StorageLocation city) {
/*  47 */     return this.dao.updateStorageLocation(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledStorageLocationList() {
/*  52 */     return this.dao.getEnabledStorageLocationList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteStorageLocation(StorageLocation city) {
/*  57 */     this.dao.deleteStorageLocation(city);
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation getStorageLocation(String code) {
/*  62 */     return this.dao.getStorageLocation(code);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StorageLocation> getStorageLocationLineLibrary() {
/*  67 */     return this.dao.getStorageLocationLineLibrary();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<StorageLocation> getStorageLocationByWmsStockingItemLocation(Integer id) {
/*  73 */     return this.dao.getStorageLocationByWmsStockingItemLocation(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validateIsScatteredLocation(String location) {
/*  78 */     return this.dao.validateIsScatteredLocation(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation getStorageLocationByDYK() {
/*  83 */     return this.dao.getStorageLocationByDYK();
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation getProduceLineLocation() {
/*  88 */     return this.dao.getProduceLineLocation();
/*     */   }
/*     */   
/*     */   public Map lookForLocationQtyByAjax(String code) {
/*  92 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*  93 */     StorageLocation location = getStorageLocation(code);
/*  94 */     map.put("maxQty", location.getMax_inventory());
/*     */     
/*  96 */     String sql = "select sum(item.number) from InventoryDetial item where item.location.code = '" + code + "' ";
/*  97 */     List<Object> objs = this.dao.getObjectList(sql);
/*  98 */     if (objs.size() > 0 && objs.get(0) != null) {
/*  99 */       BigDecimal sum = (BigDecimal)objs.get(0);
/* 100 */       map.put("sumQty", sum);
/*     */     } else {
/* 102 */       map.put("sumQty", Integer.valueOf(0));
/*     */     } 
/*     */     
/* 105 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   public StorageLocation getProduceLineLocationLine() {
/* 110 */     return this.dao.getProduceLineLocationLine();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/StorageLocationManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */