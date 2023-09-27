/*     */ package com.aof.web.struts.action.kpi;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.kpi.KPISummary;
/*     */ import com.aof.model.kpi.query.KPISummaryQueryCondition;
/*     */ import com.aof.model.kpi.query.KPISummaryQueryOrder;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.kpi.KPIManager;
/*     */ import com.aof.utils.DateUtils;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KPIAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  43 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/*  44 */     List<Site> siteList = siteManager.getAllEnabledSiteList();
/*     */     
/*  46 */     if (siteList != null && siteList.size() > 0) {
/*     */       
/*  48 */       String selectedSiteIDStr = request.getParameter("selectedSiteID");
/*  49 */       Site selectedSite = null;
/*  50 */       Integer selectedSiteID = null;
/*  51 */       if (selectedSiteIDStr == null) {
/*  52 */         selectedSite = siteList.get(0);
/*  53 */         selectedSiteID = selectedSite.getId();
/*     */       } else {
/*  55 */         selectedSiteID = new Integer(selectedSiteIDStr);
/*  56 */         for (int i0 = 0; i0 < siteList.size(); i0++) {
/*  57 */           Site tempSite = siteList.get(i0);
/*  58 */           if (tempSite.getId().equals(selectedSiteID)) {
/*  59 */             selectedSite = tempSite;
/*     */             break;
/*     */           } 
/*     */         } 
/*  63 */         if (selectedSite == null) {
/*  64 */           selectedSite = siteList.get(0);
/*     */         }
/*     */       } 
/*     */       
/*  68 */       Date currEndDate = DateUtils.getExpireDate(new Date());
/*  69 */       Date currStartDate = DateUtils.getStartDate(new Date());
/*  70 */       Date lastWeek = DateUtils.dateAdd(currStartDate, 5, -7);
/*  71 */       Date lastMonth = DateUtils.dateAdd(currStartDate, 2, -1);
/*     */       
/*  73 */       Map<Object, Object> condition = new HashMap<Object, Object>();
/*  74 */       condition.put(KPISummaryQueryCondition.SUMMARYDATE_GE, currEndDate);
/*  75 */       condition.put(KPISummaryQueryCondition.SUMMARYDATE_LE, lastMonth);
/*     */       
/*  77 */       KPIManager kpiManager = ServiceLocator.getKPIManager(request);
/*  78 */       List<KPISummary> kpiList = kpiManager.getKPISummaryList(condition, -1, -1, KPISummaryQueryOrder.SUMMARYDATE, true);
/*     */       
/*  80 */       int numberOfUsers = 0;
/*  81 */       BigDecimal totalResponseTime = new BigDecimal(0);
/*  82 */       int totalRequestClosed = 0;
/*  83 */       int totalRequestCreatedLastWeek = 0;
/*  84 */       int totalRequestCreatedLastMonth = 0;
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
/*  95 */       if (kpiList != null && kpiList.size() > 0) {
/*  96 */         for (int i0 = 0; i0 < kpiList.size(); i0++) {
/*  97 */           KPISummary kpiSummary = kpiList.get(i0);
/*     */           
/*  99 */           numberOfUsers += kpiSummary.getLoginUserCount();
/*     */           
/* 101 */           if (kpiSummary.getClosedCapexNumToday() > 0) {
/* 102 */             totalResponseTime.add(kpiSummary.getAvgCapexClosedDays());
/* 103 */             totalRequestClosed += kpiSummary.getClosedCapexNumToday();
/*     */           } 
/*     */           
/* 106 */           if (kpiSummary.getClosedExpenseNumToday() > 0) {
/* 107 */             totalResponseTime.add(kpiSummary.getAvgExpenseClosedDays());
/* 108 */             totalRequestClosed += kpiSummary.getClosedExpenseNumToday();
/*     */           } 
/*     */           
/* 111 */           if (kpiSummary.getClosedPRNumToday() > 0) {
/* 112 */             totalResponseTime.add(kpiSummary.getAvgPRClosedDays());
/* 113 */             totalRequestClosed += kpiSummary.getClosedPRNumToday();
/*     */           } 
/*     */           
/* 116 */           if (kpiSummary.getClosedTANumToday() > 0) {
/* 117 */             totalResponseTime.add(kpiSummary.getAvgTAClosedDays());
/* 118 */             totalRequestClosed += kpiSummary.getClosedTANumToday();
/*     */           } 
/*     */           
/* 121 */           if (kpiSummary.getSummaryDate().before(lastWeek)) {
/* 122 */             totalRequestCreatedLastWeek += kpiSummary.getCreatedCapexNumToday();
/* 123 */             totalRequestCreatedLastWeek += kpiSummary.getCreatedExpenseNumToday();
/* 124 */             totalRequestCreatedLastWeek += kpiSummary.getCreatedPRNumToday();
/* 125 */             totalRequestCreatedLastWeek += kpiSummary.getCreatedTANumToday();
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 135 */           totalRequestCreatedLastMonth += kpiSummary.getCreatedCapexNumToday();
/* 136 */           totalRequestCreatedLastMonth += kpiSummary.getCreatedExpenseNumToday();
/* 137 */           totalRequestCreatedLastMonth += kpiSummary.getCreatedPRNumToday();
/* 138 */           totalRequestCreatedLastMonth += kpiSummary.getCreatedTANumToday();
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       request.setAttribute("x_RunningSiteNums", (new StringBuilder(String.valueOf(siteList.size()))).toString());
/* 150 */       request.setAttribute("x_NumberOfUsers", (new StringBuilder(String.valueOf(numberOfUsers))).toString());
/* 151 */       request.setAttribute("x_ResponseTimes", (totalRequestClosed != 0) ? totalResponseTime.divide(new BigDecimal(totalRequestClosed), 2, 4) : "");
/* 152 */       request.setAttribute("x_TotalRequestCreatedLastWeek", (new StringBuilder(String.valueOf(totalRequestCreatedLastWeek))).toString());
/* 153 */       request.setAttribute("x_TotalRequestCreatedLastMonth", (new StringBuilder(String.valueOf(totalRequestCreatedLastMonth))).toString());
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
/*     */       
/* 169 */       request.setAttribute("x_SiteList", siteList);
/* 170 */       request.setAttribute("x_SelectedSite", selectedSite);
/* 171 */       request.setAttribute("x_Top5KPIExpenseCategory", kpiManager.getTop5KPIExpenseCategorySummary(selectedSite));
/* 172 */       request.setAttribute("x_Top5KPIPurchaseCategory", kpiManager.getTop5KPIPurchaseCategorySummary(selectedSite));
/*     */     } 
/*     */     
/* 175 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/kpi/KPIAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */