/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.WorkOrderBom;
/*     */ import com.aof.service.Product.WorkOrderBomManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.WorkOrderBomQueryForm;
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
/*     */ public class WorkOrderBomAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  36 */     WorkOrderBomManager workOrderBomManager = ServiceLocator.getWorkOrderBomManager(request);
/*  37 */     WorkOrderBomQueryForm queryForm = (WorkOrderBomQueryForm)form;
/*  38 */     Map conditions = getQueryConditions(queryForm);
/*  39 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  40 */     String exportType = queryForm.getExportType();
/*  41 */     if (exportType != null && exportType.length() > 0) {
/*  42 */       List data = workOrderBomManager.getList(conditions, 0, -1, null, false);
/*  43 */       int index = SessionTempFile.createNewTempFile(request);
/*  44 */       String fileName = "WorkOrderBom";
/*  45 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  46 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  49 */               MessageResources messages = WorkOrderBomAction.this.getResources(request);
/*  50 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.workorderno"));
/*  51 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.productno"));
/*  52 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.partno"));
/*  53 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.allneedqty"));
/*  54 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.workingorder"));
/*  55 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.workingposition"));
/*  56 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.singleneedqty"));
/*  57 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.site"));
/*  58 */               row.add(messages.getMessage(WorkOrderBomAction.this.getLocale(request), "workorderbom.domain"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  62 */               row.add(BeanUtils.getProperty(data, "workOrderNo"));
/*  63 */               row.add(BeanUtils.getProperty(data, "productNo"));
/*  64 */               row.add(BeanUtils.getProperty(data, "partNo"));
/*  65 */               row.add(BeanUtils.getProperty(data, "allNeedQty"));
/*  66 */               row.add(BeanUtils.getProperty(data, "workingOrder"));
/*  67 */               row.add(BeanUtils.getProperty(data, "workingPosition"));
/*  68 */               row.add(BeanUtils.getProperty(data, "singleNeedQty"));
/*  69 */               row.add(BeanUtils.getProperty(data, "site"));
/*  70 */               row.add(BeanUtils.getProperty(data, "domain"));
/*     */             }
/*     */           });
/*  73 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  76 */     if (queryForm.isFirstInit()) {
/*  77 */       queryForm.init(workOrderBomManager.getListCount(conditions));
/*     */     } else {
/*  79 */       queryForm.init();
/*     */     } 
/*     */     
/*  82 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/*  83 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/*  84 */     List<WorkOrderBom> resultList = workOrderBomManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/*  85 */     request.setAttribute("x_selType", Integer.valueOf(117));
/*  86 */     request.setAttribute("X_RESULTLIST", resultList);
/*  87 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(WorkOrderBomQueryForm queryForm) {
/*  92 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  93 */     String queryStr = "";
/*  94 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/*  97 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 101 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 106 */     getBasic(request);
/* 107 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 112 */     WorkOrderBomManager manager = ServiceLocator.getWorkOrderBomManager(request);
/* 113 */     BeanForm formBean = (BeanForm)form;
/* 114 */     WorkOrderBom workOrderBom = new WorkOrderBom();
/* 115 */     formBean.populateToBean(workOrderBom);
/* 116 */     workOrderBom = manager.insert(workOrderBom);
/* 117 */     request.setAttribute("X_OBJECT", workOrderBom);
/* 118 */     request.setAttribute("X_ROWPAGE", "wmsbasic/workOrderBom/row.jsp");
/* 119 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 124 */     WorkOrderBomManager manager = ServiceLocator.getWorkOrderBomManager(request);
/* 125 */     String idStr = request.getParameter("id");
/* 126 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 127 */     WorkOrderBom workOrderBom = manager.getById(id);
/* 128 */     if (workOrderBom == null) throw new ActionException("workOrderBom.notFound", id); 
/* 129 */     if (!isBack(request)) {
/* 130 */       BeanForm cityForm = (BeanForm)getForm("/updateWorkOrderBom", request);
/* 131 */       cityForm.populate(workOrderBom, "to_form");
/*     */     } 
/* 133 */     request.setAttribute("X_OBJECT", workOrderBom);
/* 134 */     getBasic(request);
/* 135 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 140 */     WorkOrderBomManager manager = ServiceLocator.getWorkOrderBomManager(request);
/* 141 */     String idStr = request.getParameter("id");
/* 142 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 143 */     BeanForm formBean = (BeanForm)form;
/* 144 */     WorkOrderBom workOrderBom = manager.getById(id);
/* 145 */     formBean.populateToBean(workOrderBom, request);
/* 146 */     workOrderBom = manager.update(workOrderBom);
/* 147 */     request.setAttribute("X_OBJECT", workOrderBom);
/* 148 */     request.setAttribute("X_ROWPAGE", "wmsbasic/workOrderBom/row.jsp");
/* 149 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 154 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 155 */     WorkOrderBomManager workOrderBomManager = ServiceLocator.getWorkOrderBomManager(request);
/* 156 */     workOrderBomManager.remove(workOrderBomManager.getById(id));
/* 157 */     return new ActionForward("listWorkOrderBom.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/WorkOrderBomAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */