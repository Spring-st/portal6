/*    */ package com.aof.service.basicDataView.impl;
/*    */ 
/*    */ import com.aof.dao.basicDataView.BasicDataViewDAO;
/*    */ import com.aof.model.basicDataView.PartForecastNeedReport;
/*    */ import com.aof.model.basicDataView.SkPartSumNumber;
/*    */ import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
/*    */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
/*    */ import com.aof.model.basicDataView.query.SkPartSumNumberQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basicDataView.BasicDataViewManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicDataViewManagerImpl
/*    */   extends BaseManager
/*    */   implements BasicDataViewManager
/*    */ {
/*    */   private BasicDataViewDAO dao;
/*    */   
/*    */   public BasicDataViewDAO getDao() {
/* 26 */     return this.dao;
/*    */   }
/*    */   
/*    */   public void setDao(BasicDataViewDAO dao) {
/* 30 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkPartSumNumber getSkPartSumNumber(String id) {
/* 35 */     return this.dao.getSkPartSumNumber(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSkPartSumNumberListCount(Map conditions) {
/* 40 */     return this.dao.getSkPartSumNumberListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getSkPartSumNumberList(Map conditions, int pageNo, int pageSize, SkPartSumNumberQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getSkPartSumNumberList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public int getJitShipPartListCount(Map conditions) {
/* 49 */     return this.dao.getJitShipPartListCount(conditions);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getJitShipPartNumberList(Map conditions, int pageNo, int pageSize, JitShipPartQueryOrder order, boolean descend) {
/* 54 */     return this.dao.getJitShipPartNumberList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getPartForecastNeedReportListCount(Map conditions) {
/* 59 */     return this.dao.getPartForecastNeedReportListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<PartForecastNeedReport> getPartForecastNeedReportList(Map conditions, int pageNum, int pageSize, PartForecastNeedReportQueryOrder order, boolean descend) {
/* 66 */     return this.dao.getPartForecastNeedReportList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basicDataView/impl/BasicDataViewManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */