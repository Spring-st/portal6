/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.DailyProductPlan;
/*     */ import com.aof.service.Product.DailyProductPlanManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.DailyProductPlanQueryForm;
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
/*     */ public class DailyProductPlanAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  36 */     DailyProductPlanManager dailyProductPlanManager = ServiceLocator.getDailyProductPlanManager(request);
/*  37 */     DailyProductPlanQueryForm queryForm = (DailyProductPlanQueryForm)form;
/*  38 */     Map conditions = getQueryConditions(queryForm);
/*  39 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  40 */     String exportType = queryForm.getExportType();
/*  41 */     if (exportType != null && exportType.length() > 0) {
/*  42 */       List data = dailyProductPlanManager.getList(conditions, 0, -1, null, false);
/*  43 */       int index = SessionTempFile.createNewTempFile(request);
/*  44 */       String fileName = "DailyProductPlan";
/*  45 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  46 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  49 */               MessageResources messages = DailyProductPlanAction.this.getResources(request);
/*  50 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.id"));
/*  51 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.part"));
/*  52 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.ordertype"));
/*  53 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.orderattribute"));
/*  54 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.site"));
/*  55 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.lineno"));
/*  56 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.qty"));
/*  57 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.golinedate"));
/*  58 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.offlinedate"));
/*  59 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.procedurecode"));
/*  60 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.bomcode"));
/*  61 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.shift"));
/*  62 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.bomoutfinish"));
/*  63 */               row.add(messages.getMessage(DailyProductPlanAction.this.getLocale(request), "dailyproductplan.domain"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  67 */               row.add(BeanUtils.getProperty(data, "workOrderNo"));
/*  68 */               row.add(BeanUtils.getProperty(data, "part"));
/*  69 */               row.add(BeanUtils.getProperty(data, "orderType"));
/*  70 */               row.add(BeanUtils.getProperty(data, "orderAttribute"));
/*  71 */               row.add(BeanUtils.getProperty(data, "site"));
/*  72 */               row.add(BeanUtils.getProperty(data, "lineNo"));
/*  73 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  74 */               row.add(BeanUtils.getProperty(data, "golineDate"));
/*  75 */               row.add(BeanUtils.getProperty(data, "offlineDate"));
/*  76 */               row.add(BeanUtils.getProperty(data, "procedureCode"));
/*  77 */               row.add(BeanUtils.getProperty(data, "bomCode"));
/*  78 */               row.add(BeanUtils.getProperty(data, "shift"));
/*  79 */               row.add(BeanUtils.getProperty(data, "bomOutFinish"));
/*  80 */               row.add(BeanUtils.getProperty(data, "domain"));
/*     */             }
/*     */           });
/*  83 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  86 */     if (queryForm.isFirstInit()) {
/*  87 */       queryForm.init(dailyProductPlanManager.getListCount(conditions));
/*     */     } else {
/*  89 */       queryForm.init();
/*     */     } 
/*     */     
/*  92 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/*  93 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/*  94 */     List<DailyProductPlan> resultList = dailyProductPlanManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/*  95 */     request.setAttribute("x_selType", Integer.valueOf(115));
/*  96 */     request.setAttribute("X_RESULTLIST", resultList);
/*  97 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(DailyProductPlanQueryForm queryForm) {
/* 102 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 103 */     String queryStr = "";
/* 104 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 107 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 111 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 116 */     getBasic(request);
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 122 */     DailyProductPlanManager manager = ServiceLocator.getDailyProductPlanManager(request);
/* 123 */     BeanForm formBean = (BeanForm)form;
/* 124 */     DailyProductPlan dailyProductPlan = new DailyProductPlan();
/* 125 */     formBean.populateToBean(dailyProductPlan);
/* 126 */     dailyProductPlan = manager.insert(dailyProductPlan);
/* 127 */     request.setAttribute("X_OBJECT", dailyProductPlan);
/* 128 */     request.setAttribute("X_ROWPAGE", "wmsbasic/dailyProductPlan/row.jsp");
/* 129 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 134 */     DailyProductPlanManager manager = ServiceLocator.getDailyProductPlanManager(request);
/* 135 */     String idStr = request.getParameter("id");
/* 136 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 137 */     DailyProductPlan dailyProductPlan = manager.getById(id);
/* 138 */     if (dailyProductPlan == null) throw new ActionException("dailyProductPlan.notFound", id); 
/* 139 */     if (!isBack(request)) {
/* 140 */       BeanForm cityForm = (BeanForm)getForm("/updateDailyProductPlan", request);
/* 141 */       cityForm.populate(dailyProductPlan, "to_form");
/*     */     } 
/* 143 */     request.setAttribute("X_OBJECT", dailyProductPlan);
/* 144 */     getBasic(request);
/* 145 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 150 */     DailyProductPlanManager manager = ServiceLocator.getDailyProductPlanManager(request);
/* 151 */     String idStr = request.getParameter("id");
/* 152 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 153 */     BeanForm formBean = (BeanForm)form;
/* 154 */     DailyProductPlan dailyProductPlan = manager.getById(id);
/* 155 */     formBean.populateToBean(dailyProductPlan, request);
/* 156 */     dailyProductPlan = manager.update(dailyProductPlan);
/* 157 */     request.setAttribute("X_OBJECT", dailyProductPlan);
/* 158 */     request.setAttribute("X_ROWPAGE", "wmsbasic/dailyProductPlan/row.jsp");
/* 159 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 164 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 165 */     DailyProductPlanManager dailyProductPlanManager = ServiceLocator.getDailyProductPlanManager(request);
/* 166 */     dailyProductPlanManager.remove(dailyProductPlanManager.getById(id));
/* 167 */     return new ActionForward("listDailyProductPlan.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/DailyProductPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */