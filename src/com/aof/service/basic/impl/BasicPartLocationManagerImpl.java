/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.BasicPartLocationDAO;
/*    */ import com.aof.model.basic.BasicPartLocation;
/*    */ import com.aof.model.basic.StorageLocation;
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import com.aof.model.basic.query.BasicPartLocationQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.BasicPartLocationManager;
/*    */ import com.aof.service.basic.StorageLocationManager;
/*    */ import com.aof.service.basic.WmsPartManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import jxl.Cell;
/*    */ import jxl.Sheet;
/*    */ 
/*    */ public class BasicPartLocationManagerImpl
/*    */   extends BaseManager
/*    */   implements BasicPartLocationManager {
/*    */   private BasicPartLocationDAO dao;
/*    */   private WmsPartManager wmsPartManager;
/*    */   private StorageLocationManager storageLocationManager;
/*    */   
/*    */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/* 25 */     this.wmsPartManager = wmsPartManager;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStorageLocationManager(StorageLocationManager storageLocationManager) {
/* 30 */     this.storageLocationManager = storageLocationManager;
/*    */   }
/*    */   
/*    */   public void setDao(BasicPartLocationDAO dao) {
/* 34 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public BasicPartLocation getBasicPartLocation(Integer id) {
/* 39 */     return this.dao.getBasicPartLocation(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBasicPartLocationListCount(Map conditions) {
/* 44 */     return this.dao.getBasicPartLocationListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getBasicPartLocationList(Map conditions, int pageNo, int pageSize, BasicPartLocationQueryOrder order, boolean descend) {
/* 50 */     return this.dao.getBasicPartLocationList(conditions, pageNo, pageSize, 
/* 51 */         order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public BasicPartLocation insertBasicPartLocation(BasicPartLocation city) {
/* 56 */     return this.dao.insertBasicPartLocation(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public BasicPartLocation updateBasicPartLocation(BasicPartLocation city) {
/* 61 */     return this.dao.updateBasicPartLocation(city);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledBasicPartLocationList() {
/* 66 */     return this.dao.getEnabledBasicPartLocationList();
/*    */   }
/*    */   
/*    */   public void insertBasicPartLocation(Sheet[] sheet) {
/* 70 */     int rowNum = 0; byte b; int i; Sheet[] arrayOfSheet;
/* 71 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 72 */       int k = 0;
/* 73 */       rowNum = sheet[k].getRows();
/* 74 */       for (int j = 1; j < rowNum; j++) {
/* 75 */         Cell[] cells = sh.getRow(j);
/* 76 */         String part = cells[0].getContents();
/* 77 */         String location = cells[1].getContents();
/*    */         
/* 79 */         WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/* 80 */         StorageLocation storageLocation = this.storageLocationManager.getStorageLocation(location);
/*    */         
/* 82 */         BasicPartLocation partLocation = new BasicPartLocation();
/* 83 */         partLocation.setPart(wmsPart);
/* 84 */         partLocation.setLocation(storageLocation);
/* 85 */         this.dao.insertBasicPartLocation(partLocation);
/*    */       } 
/*    */       b++; }
/*    */   
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/BasicPartLocationManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */