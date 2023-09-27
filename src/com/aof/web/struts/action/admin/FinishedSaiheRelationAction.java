/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.FinishedSaiheRelation;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.FinishedSaiheRelationManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.admin.FinishedSaiheRelationQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
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
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ 
/*     */ public class FinishedSaiheRelationAction extends BaseAction2 {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  37 */     FinishedSaiheRelationQueryForm queryForm = (FinishedSaiheRelationQueryForm)form;
/*  38 */     FinishedSaiheRelationManager fm = 
/*  39 */       ServiceLocator.getFinishedSaiheRelationManager(request);
/*  40 */     Map conditions = constructQueryMap(queryForm);
/*  41 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  42 */     String exportType = queryForm.getExportType();
/*  43 */     if (StringUtils.isNotEmpty(exportType)) {
/*  44 */       List data = fm.getFinishedSaiheRelationList(conditions, 0, -1, 
/*  45 */           FinishedSaiheRelationQueryOrder.ID, queryForm.isDescend());
/*  46 */       int index = SessionTempFile.createNewTempFile(request);
/*  47 */       String fileName = "FinishedSaiheRelation";
/*  48 */       String suffix = ExportUtil.export(
/*  49 */           exportType, 
/*  50 */           data, 
/*  51 */           request, 
/*  52 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  53 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  56 */               MessageResources messages = FinishedSaiheRelationAction.this.getResources(request);
/*  57 */               row.add(messages.getMessage(FinishedSaiheRelationAction.this.getLocale(request), 
/*  58 */                     "finishedSaiheRelation.saihecode"));
/*  59 */               row.add(messages.getMessage(FinishedSaiheRelationAction.this.getLocale(request), 
/*  60 */                     "finishedSaiheRelation.finishedcode"));
/*  61 */               row.add(messages.getMessage(FinishedSaiheRelationAction.this.getLocale(request), 
/*  62 */                     "finishedSaiheRelation.finishedProductDesc"));
/*  63 */               row.add(messages.getMessage(FinishedSaiheRelationAction.this.getLocale(request), 
/*  64 */                     "finishedSaiheRelation.status"));
/*     */             }
/*     */ 
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  69 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  70 */                     "saiheCode"));
/*  71 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  72 */                     "finishedCode"));
/*  73 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  74 */                     "finishedProductDesc"));
/*  75 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  76 */                     "status.engShortDescription"));
/*     */             }
/*     */           });
/*     */       
/*  80 */       return new ActionForward("download/" + index + "/" + 
/*  81 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  83 */     if (queryForm.isFirstInit()) {
/*  84 */       queryForm.init(fm.getFinishedSaiheRelationListCount(conditions));
/*     */     } else {
/*  86 */       queryForm.init();
/*     */     } 
/*  88 */     int pageNo = queryForm.getPageNoAsInt();
/*  89 */     int pageSize = queryForm.getPageSizeAsInt();
/*  90 */     List result = fm.getFinishedSaiheRelationList(conditions, pageNo, pageSize, 
/*  91 */         FinishedSaiheRelationQueryOrder.ID, queryForm.isDescend());
/*  92 */     request.setAttribute("X_RESULTLIST", result);
/*  93 */     request.setAttribute("x_selType", Integer.valueOf(103));
/*  94 */     putEnumListToRequest(request);
/*  95 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map constructQueryMap(FinishedSaiheRelationQueryForm queryForm) {
/* 101 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 102 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 107 */     putEnumListToRequest(request);
/* 108 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 113 */     BeanForm finishedSaiheRelationForm = (BeanForm)form;
/* 114 */     FinishedSaiheRelationManager fm = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 115 */     FinishedSaiheRelation fsr = new FinishedSaiheRelation();
/* 116 */     finishedSaiheRelationForm.populate(fsr, "to_bean");
/* 117 */     fm.insertFinishedSaiheRelation(fsr);
/* 118 */     request.setAttribute("X_OBJECT", fsr);
/* 119 */     request.setAttribute("X_ROWPAGE", "finishedSaiheRelation/row.jsp");
/* 120 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 124 */     String id = request.getParameter("id");
/* 125 */     FinishedSaiheRelationManager fm = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 126 */     FinishedSaiheRelation fsr = fm.getFinishedSaiheRelation(Integer.valueOf(Integer.parseInt(id)));
/* 127 */     request.setAttribute("fsr", fsr);
/* 128 */     if (!isBack(request)) {
/*     */       
/* 130 */       BeanForm finishedSaiheRelationForm = (BeanForm)getForm("/updateFinishedSaiheRelation", request);
/* 131 */       finishedSaiheRelationForm.populate(fsr, "to_form");
/*     */     } 
/* 133 */     putEnumListToRequest(request);
/* 134 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 138 */     String id = request.getParameter("id");
/* 139 */     FinishedSaiheRelationManager fm = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 140 */     fm.deleteFinishedSaiheRelation(fm.getFinishedSaiheRelation(Integer.valueOf(Integer.parseInt(id))));
/* 141 */     ActionForward forward = new ActionForward();
/* 142 */     forward.setRedirect(true);
/* 143 */     forward.setPath("listFinishedSaiheRelation.do");
/* 144 */     return forward;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 149 */     BeanForm finishedSaiheRelationForm = (BeanForm)form;
/* 150 */     FinishedSaiheRelation fsr = new FinishedSaiheRelation();
/* 151 */     finishedSaiheRelationForm.populate(fsr, "to_bean");
/* 152 */     FinishedSaiheRelationManager fm = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 153 */     fm.updateFinishedSaiheRelation(fsr);
/* 154 */     request.setAttribute("X_OBJECT", fsr);
/* 155 */     request.setAttribute("X_ROWPAGE", "finishedSaiheRelation/row.jsp");
/* 156 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 160 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 161 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 162 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/FinishedSaiheRelationAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */