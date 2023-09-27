/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.basic.ScanLog;
/*     */ import com.aof.model.basic.query.ScanLogQueryCondition;
/*     */ import com.aof.model.basic.query.ScanLogQueryOrder;
/*     */ import com.aof.service.basic.ScanLogManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.ScanLogQueryForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
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
/*     */ public class ScanLogAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map constructQueryMap(ScanLogQueryForm queryForm) {
/*  42 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  43 */     String type = queryForm.getType();
/*  44 */     String describe = queryForm.getDescribe();
/*  45 */     String startDate = queryForm.getStartDate();
/*  46 */     String endDate = queryForm.getEndDate();
/*     */     
/*  48 */     if (type != null && !type.equals("")) {
/*  49 */       conditions.put(ScanLogQueryCondition.TYPE_EQ, type);
/*     */     }
/*  51 */     if (describe != null && !describe.equals("")) {
/*  52 */       conditions.put(ScanLogQueryCondition.DESCRIBE_EQ, describe);
/*     */     }
/*  54 */     if (startDate != null && !startDate.equals("")) {
/*  55 */       conditions.put(ScanLogQueryCondition.STARTDATE_EQ, startDate);
/*     */     }
/*  57 */     if (endDate != null && !endDate.equals("")) {
/*  58 */       conditions.put(ScanLogQueryCondition.ENDDATE_EQ, endDate);
/*     */     }
/*     */     
/*  61 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  67 */     ScanLogManager cm = ServiceLocator.getScanLogManager(request);
/*  68 */     List siteList = getAndCheckGrantedSiteList(request);
/*     */     
/*  70 */     ScanLogQueryForm queryForm = (ScanLogQueryForm)form;
/*  71 */     Map conditions = constructQueryMap(queryForm);
/*  72 */     conditions = constructQueryMap(queryForm);
/*  73 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  74 */     String exportType = queryForm.getExportType();
/*  75 */     if (exportType != null && exportType.length() > 0) {
/*     */       
/*  77 */       List data = cm.getScanLogList(conditions, 0, -1, ScanLogQueryOrder.getEnum(queryForm.getOrder()), 
/*  78 */           queryForm.isDescend());
/*  79 */       int index = SessionTempFile.createNewTempFile(request);
/*  80 */       String fileName = "systemLog";
/*  81 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  82 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  85 */               MessageResources messages = ScanLogAction.this.getResources(request);
/*  86 */               row.add(messages.getMessage(ScanLogAction.this.getLocale(request), "ScanLog.userId.name"));
/*  87 */               row.add(messages.getMessage(ScanLogAction.this.getLocale(request), "ScanLog.date"));
/*  88 */               row.add(messages.getMessage(ScanLogAction.this.getLocale(request), "ScanLog.type"));
/*  89 */               row.add(messages.getMessage(ScanLogAction.this.getLocale(request), "ScanLog.describe"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  93 */               ScanLog sl = (ScanLog)data;
/*  94 */               row.add(BeanUtils.getProperty(sl, "userId.name"));
/*  95 */               row.add(BeanUtils.getProperty(sl, "date"));
/*  96 */               Integer type = sl.getType();
/*  97 */               if (type.intValue() == 1) {
/*  98 */                 row.add("采购单收货");
/*  99 */               } else if (type.intValue() == 2) {
/* 100 */                 row.add("品质质检");
/* 101 */               } else if (type.intValue() == 3) {
/* 102 */                 row.add("仓库入库");
/* 103 */               } else if (type.intValue() == 4) {
/* 104 */                 row.add("原材料出库");
/* 105 */               } else if (type.intValue() == 5) {
/* 106 */                 row.add("成品装箱");
/* 107 */               } else if (type.intValue() == 6) {
/* 108 */                 row.add("成品出库");
/* 109 */               } else if (type.intValue() == 7) {
/* 110 */                 row.add("移库");
/* 111 */               } else if (type.intValue() == 8) {
/* 112 */                 row.add("非计划出库");
/* 113 */               } else if (type.intValue() == 9) {
/* 114 */                 row.add("非计划 入库");
/* 115 */               } else if (type.intValue() == 10) {
/* 116 */                 row.add("条码拆分");
/* 117 */               } else if (type.intValue() == 11) {
/* 118 */                 row.add("条码合并");
/*     */               } else {
/* 120 */                 row.add("");
/*     */               } 
/* 122 */               row.add(BeanUtils.getProperty(sl, "describe"));
/*     */             }
/*     */           });
/* 125 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 128 */     if (queryForm.isFirstInit()) {
/* 129 */       queryForm.init(cm.getScanLogListCount(conditions));
/*     */     } else {
/* 131 */       queryForm.init();
/*     */     } 
/*     */     
/* 134 */     List list = cm.getScanLogList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 135 */         ScanLogQueryOrder.getEnum(queryForm.getOrder()), 
/* 136 */         queryForm.isDescend());
/*     */     
/* 138 */     request.setAttribute("X_RESULTLIST", list);
/* 139 */     request.setAttribute("X_SITELIST", siteList);
/* 140 */     request.setAttribute("x_selType", Integer.valueOf(93));
/* 141 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/ScanLogAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */