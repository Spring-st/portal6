/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.basic.query.ReportEntersSellsSavesQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.basic.ReportEntersSellsSavesManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.ReportEntersSellsSavesQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JsonConfig;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class ReportEntersSellsSavesAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  61 */     ReportEntersSellsSavesQueryForm queryForm = (ReportEntersSellsSavesQueryForm)form;
/*  62 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  63 */       queryForm.setOrder(ReportEntersSellsSavesQueryOrder.ID.getName());
/*  64 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  67 */     ReportEntersSellsSavesManager fm = ServiceLocator.getReportEntersSellsSavesManager(request);
/*  68 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  70 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  72 */     String exportType = queryForm.getExportType();
/*  73 */     if (StringUtils.isNotEmpty(exportType)) {
/*  74 */       List data = fm.getReportEntersSellsSavesList(conditions, 0, -1, ReportEntersSellsSavesQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  76 */       int index = SessionTempFile.createNewTempFile(request);
/*  77 */       String fileName = "ReportEntersSellsSaves";
/*  78 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  81 */               MessageResources messages = ReportEntersSellsSavesAction.this.getResources(request);
/*     */               
/*  83 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.part.id"));
/*  84 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "DPI"));
/*  85 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "part.describe1"));
/*  86 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "part.describe2"));
/*  87 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.month"));
/*  88 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.start_date"));
/*  89 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.end_date"));
/*  90 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.initial_qty"));
/*  91 */               row.add(messages.getMessage(ReportEntersSellsSavesAction.this.getLocale(request), "ReportEntersSellsSaves.balance_qty"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  96 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/*  97 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/*  98 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/*  99 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 100 */               row.add(BeanHelper.getBeanPropertyValue(data, "month"));
/* 101 */               row.add(BeanHelper.getBeanPropertyValue(data, "start_date"));
/* 102 */               row.add(BeanHelper.getBeanPropertyValue(data, "end_date"));
/* 103 */               row.add(BeanHelper.getBeanPropertyValue(data, "initial_qty"));
/* 104 */               row.add(BeanHelper.getBeanPropertyValue(data, "balance_qty"));
/*     */             }
/*     */           });
/* 107 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 109 */     if (queryForm.isFirstInit()) {
/* 110 */       queryForm.init(fm.getReportEntersSellsSavesListCount(conditions));
/*     */     } else {
/* 112 */       queryForm.init();
/*     */     } 
/* 114 */     List result = fm.getReportEntersSellsSavesList(conditions, 
/* 115 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 116 */         ReportEntersSellsSavesQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 118 */     request.setAttribute("X_RESULTLIST", result);
/* 119 */     request.setAttribute("x_selType", Integer.valueOf(81));
/* 120 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ReportEntersSellsSavesQueryForm queryForm) {
/* 124 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 125 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*     */     
/* 127 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 131 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 146 */     String mouth = request.getParameter("mouth");
/* 147 */     ReportEntersSellsSavesManager manager = ServiceLocator.getReportEntersSellsSavesManager(request);
/*     */     
/* 149 */     manager.insertReportEntersSellsSaves(mouth);
/* 150 */     return mapping.findForward("success");
/*     */   }
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 166 */     List<Map<Object, Object>> list = new ArrayList();
/* 167 */     for (int i = 1; i <= 12; i++) {
/* 168 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 169 */       map.put("mouth", Integer.valueOf(i));
/* 170 */       list.add(map);
/*     */     } 
/*     */     
/* 173 */     request.setAttribute("x_list", list);
/* 174 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward validateReportEntersSellsSaves(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 180 */     response.setContentType("text/json");
/* 181 */     response.setCharacterEncoding("UTF-8");
/* 182 */     JsonConfig cfg = new JsonConfig();
/*     */ 
/*     */     
/* 185 */     ReportEntersSellsSavesManager manager = ServiceLocator.getReportEntersSellsSavesManager(request);
/*     */ 
/*     */     
/* 188 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 189 */     response.getWriter().print(jo);
/* 190 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/ReportEntersSellsSavesAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */