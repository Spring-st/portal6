/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.service.Product.SalesPreshiporderBatchManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.product.SalesPreshiporderBatchQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
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
/*     */ public class SalesPreshiporderBatchAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  36 */     SalesPreshiporderBatchManager salesPreshiporderBatchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/*  37 */     SalesPreshiporderBatchQueryForm queryForm = (SalesPreshiporderBatchQueryForm)form;
/*  38 */     Map conditions = getQueryConditions(queryForm);
/*     */     
/*  40 */     String exportType = queryForm.getExportType();
/*  41 */     if (exportType != null && exportType.length() > 0) {
/*  42 */       List data = salesPreshiporderBatchManager.getList(conditions, 0, -1, null, false);
/*  43 */       int index = SessionTempFile.createNewTempFile(request);
/*  44 */       String fileName = "SalesPreshiporderBatch";
/*  45 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  46 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  49 */               MessageResources messages = SalesPreshiporderBatchAction.this.getResources(request);
/*  50 */               row.add(messages.getMessage(SalesPreshiporderBatchAction.this.getLocale(request), ""));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  58 */               row.add(BeanUtils.getProperty(data, ""));
/*     */             }
/*     */           });
/*  61 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  64 */     if (queryForm.isFirstInit()) {
/*  65 */       queryForm.init(salesPreshiporderBatchManager.getListCount(conditions));
/*     */     } else {
/*  67 */       queryForm.init();
/*     */     } 
/*     */     
/*  70 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/*  71 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/*  72 */     List<SalesPreshiporderBatch> resultList = salesPreshiporderBatchManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/*     */     
/*  74 */     request.setAttribute("X_RESULTLIST", resultList);
/*  75 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesPreshiporderBatchQueryForm queryForm) {
/*  80 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  81 */     String queryStr = "";
/*  82 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/*  85 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/*  89 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  94 */     getBasic(request);
/*  95 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 100 */     SalesPreshiporderBatchManager manager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 101 */     BeanForm formBean = (BeanForm)form;
/* 102 */     SalesPreshiporderBatch salesPreshiporderBatch = new SalesPreshiporderBatch();
/* 103 */     formBean.populateToBean(salesPreshiporderBatch);
/* 104 */     salesPreshiporderBatch = manager.insert(salesPreshiporderBatch);
/* 105 */     request.setAttribute("X_OBJECT", salesPreshiporderBatch);
/* 106 */     request.setAttribute("X_ROWPAGE", "salesPreshiporderBatch/row.jsp");
/* 107 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 112 */     SalesPreshiporderBatchManager manager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 113 */     String idStr = request.getParameter("id");
/* 114 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 115 */     SalesPreshiporderBatch salesPreshiporderBatch = manager.getById(id);
/* 116 */     if (salesPreshiporderBatch == null) throw new ActionException("salesPreshiporderBatch.notFound", id); 
/* 117 */     request.setAttribute("X_OBJECT", salesPreshiporderBatch);
/* 118 */     getBasic(request);
/* 119 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 124 */     SalesPreshiporderBatchManager manager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 125 */     String idStr = request.getParameter("id");
/* 126 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 127 */     BeanForm formBean = (BeanForm)form;
/* 128 */     SalesPreshiporderBatch salesPreshiporderBatch = manager.getById(id);
/* 129 */     formBean.populateToBean(salesPreshiporderBatch, request);
/* 130 */     salesPreshiporderBatch = manager.update(salesPreshiporderBatch);
/* 131 */     request.setAttribute("X_OBJECT", salesPreshiporderBatch);
/* 132 */     request.setAttribute("X_ROWPAGE", "salesPreshiporderBatch/row.jsp");
/* 133 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 138 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 139 */     SalesPreshiporderBatchManager salesPreshiporderBatchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 140 */     salesPreshiporderBatchManager.remove(salesPreshiporderBatchManager.getById(id));
/* 141 */     return new ActionForward("listSalesPreshiporderBatch.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesPreshiporderBatchAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */