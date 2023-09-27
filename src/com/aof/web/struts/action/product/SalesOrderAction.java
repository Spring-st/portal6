/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.SalesOrder;
/*     */ import com.aof.service.Product.SalesOrderManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesOrderQueryForm;
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
/*     */ public class SalesOrderAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  36 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/*  37 */     SalesOrderQueryForm queryForm = (SalesOrderQueryForm)form;
/*  38 */     Map conditions = getQueryConditions(queryForm);
/*  39 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  40 */     String exportType = queryForm.getExportType();
/*  41 */     if (exportType != null && exportType.length() > 0) {
/*  42 */       List data = salesOrderManager.getList(conditions, 0, -1, null, false);
/*  43 */       int index = SessionTempFile.createNewTempFile(request);
/*  44 */       String fileName = "SalesOrder";
/*  45 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  46 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  49 */               MessageResources messages = SalesOrderAction.this.getResources(request);
/*  50 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.id"));
/*  51 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.customername"));
/*  52 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.lineno"));
/*  53 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.productno"));
/*  54 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.qty"));
/*  55 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.addresscode"));
/*  56 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.addressdesc"));
/*  57 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.site"));
/*  58 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.outerpackingqty"));
/*  59 */               row.add(messages.getMessage(SalesOrderAction.this.getLocale(request), "salesorder.domain"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  63 */               row.add(BeanUtils.getProperty(data, "customerCode"));
/*  64 */               row.add(BeanUtils.getProperty(data, "customerName"));
/*  65 */               row.add(BeanUtils.getProperty(data, "lineNo"));
/*  66 */               row.add(BeanUtils.getProperty(data, "productNo"));
/*  67 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  68 */               row.add(BeanUtils.getProperty(data, "addressCode"));
/*  69 */               row.add(BeanUtils.getProperty(data, "addressDesc"));
/*  70 */               row.add(BeanUtils.getProperty(data, "site"));
/*  71 */               row.add(BeanUtils.getProperty(data, "outerPackingQty"));
/*  72 */               row.add(BeanUtils.getProperty(data, "domain"));
/*     */             }
/*     */           });
/*  75 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  78 */     if (queryForm.isFirstInit()) {
/*  79 */       queryForm.init(salesOrderManager.getListCount(conditions));
/*     */     } else {
/*  81 */       queryForm.init();
/*     */     } 
/*     */     
/*  84 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/*  85 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/*  86 */     List<SalesOrder> resultList = salesOrderManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/*  87 */     request.setAttribute("x_selType", Integer.valueOf(116));
/*  88 */     request.setAttribute("X_RESULTLIST", resultList);
/*  89 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesOrderQueryForm queryForm) {
/*  94 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  95 */     String queryStr = "";
/*  96 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/*  99 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 103 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 108 */     getBasic(request);
/* 109 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 114 */     SalesOrderManager manager = ServiceLocator.getSalesOrderManager(request);
/* 115 */     BeanForm formBean = (BeanForm)form;
/* 116 */     SalesOrder salesOrder = new SalesOrder();
/* 117 */     formBean.populateToBean(salesOrder);
/* 118 */     salesOrder = manager.insert(salesOrder);
/* 119 */     request.setAttribute("X_OBJECT", salesOrder);
/* 120 */     request.setAttribute("X_ROWPAGE", "wmsbasic/salesOrder/row.jsp");
/* 121 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 126 */     SalesOrderManager manager = ServiceLocator.getSalesOrderManager(request);
/* 127 */     String idStr = request.getParameter("id");
/* 128 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 129 */     SalesOrder salesOrder = manager.getById(id);
/* 130 */     if (salesOrder == null) throw new ActionException("salesOrder.notFound", id); 
/* 131 */     if (!isBack(request)) {
/* 132 */       BeanForm cityForm = (BeanForm)getForm("/updateSalesOrder", request);
/* 133 */       cityForm.populate(salesOrder, "to_form");
/*     */     } 
/* 135 */     request.setAttribute("X_OBJECT", salesOrder);
/* 136 */     getBasic(request);
/* 137 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 142 */     SalesOrderManager manager = ServiceLocator.getSalesOrderManager(request);
/* 143 */     String idStr = request.getParameter("id");
/* 144 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 145 */     BeanForm formBean = (BeanForm)form;
/* 146 */     SalesOrder salesOrder = manager.getById(id);
/* 147 */     formBean.populateToBean(salesOrder, request);
/* 148 */     salesOrder = manager.update(salesOrder);
/* 149 */     request.setAttribute("X_OBJECT", salesOrder);
/* 150 */     request.setAttribute("X_ROWPAGE", "wmsbasic/salesOrder/row.jsp");
/* 151 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 156 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 157 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 158 */     salesOrderManager.remove(salesOrderManager.getById(id));
/* 159 */     return new ActionForward("listSalesOrder.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesOrderAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */