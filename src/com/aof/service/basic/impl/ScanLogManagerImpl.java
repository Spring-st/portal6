/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.ScanLogDAO;
/*    */ import com.aof.model.basic.ScanLog;
/*    */ import com.aof.model.basic.query.ScanLogQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.ScanLogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanLogManagerImpl
/*    */   extends BaseManager
/*    */   implements ScanLogManager
/*    */ {
/*    */   private ScanLogDAO scanLogDAO;
/*    */   
/*    */   public void setScanLogDAO(ScanLogDAO scanLogDAO) {
/* 28 */     this.scanLogDAO = scanLogDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public ScanLog getScanLog(Integer id) {
/* 33 */     return this.scanLogDAO.getScanLog(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public ScanLog insertScanLog(ScanLog scanLog) {
/* 38 */     return this.scanLogDAO.insertScanLog(scanLog);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getScanLogList(Map conditions, int pageNo, int pageSize, ScanLogQueryOrder order, boolean descend) {
/* 44 */     return this.scanLogDAO.getScanLogList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScanLogListCount(Map conditions) {
/* 49 */     return this.scanLogDAO.getScanLogListCount(conditions);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/ScanLogManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */