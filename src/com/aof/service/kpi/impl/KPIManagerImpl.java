/*    */ package com.aof.service.kpi.impl;
/*    */ 
/*    */ import com.aof.dao.kpi.KPISummaryDAO;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.kpi.query.KPISummaryQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.SiteManager;
/*    */ import com.aof.service.kpi.KPIManager;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KPIManagerImpl
/*    */   extends BaseManager
/*    */   implements KPIManager
/*    */ {
/*    */   private KPISummaryDAO kpiSummaryDAO;
/*    */   private SiteManager siteManager;
/*    */   
/*    */   public void setKpiSummaryDAO(KPISummaryDAO dao) {
/* 35 */     this.kpiSummaryDAO = dao;
/*    */   }
/*    */   
/*    */   public void setSiteManager(SiteManager siteManager) {
/* 39 */     this.siteManager = siteManager;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getKPISummaryList(Map conditions, int pageNo, int pageSize, KPISummaryQueryOrder order, boolean descend) {
/* 48 */     return this.kpiSummaryDAO.getKPISummaryList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public List getTop5KPIExpenseCategorySummary(Site site) {
/* 52 */     return this.kpiSummaryDAO.getTop5KPIExpenseCategorySummary(site);
/*    */   }
/*    */   
/*    */   public List getTop5KPIPurchaseCategorySummary(Site site) {
/* 56 */     return this.kpiSummaryDAO.getTop5KPIPurchaseCategorySummary(site);
/*    */   }
/*    */   
/*    */   public void doSummary() {}
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/kpi/impl/KPIManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */