/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.FinishedToolPutnumber;
/*     */ import com.aof.model.admin.query.FinishedToolPutnumberQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.FinishedToolPutnumberManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.admin.FinishedToolPutnumberQueryForm;
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
/*     */ public class FinishedToolPutnumberAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  37 */     FinishedToolPutnumberQueryForm queryForm = (FinishedToolPutnumberQueryForm)form;
/*  38 */     FinishedToolPutnumberManager fm = 
/*  39 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/*  40 */     Map conditions = constructQueryMap(queryForm);
/*  41 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  42 */     String exportType = queryForm.getExportType();
/*  43 */     if (StringUtils.isNotEmpty(exportType)) {
/*  44 */       List data = fm.getFinishedToolPutnumberList(conditions, 0, -1, 
/*  45 */           FinishedToolPutnumberQueryOrder.ID, queryForm.isDescend());
/*  46 */       int index = SessionTempFile.createNewTempFile(request);
/*  47 */       String fileName = "FinishedToolPutnumber";
/*  48 */       String suffix = ExportUtil.export(
/*  49 */           exportType, 
/*  50 */           data, 
/*  51 */           request, 
/*  52 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  53 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  56 */               MessageResources messages = FinishedToolPutnumberAction.this.getResources(request);
/*  57 */               row.add(messages.getMessage(FinishedToolPutnumberAction.this.getLocale(request), 
/*  58 */                     "finishedtoolputnumber.toolcode"));
/*  59 */               row.add(messages.getMessage(FinishedToolPutnumberAction.this.getLocale(request), 
/*  60 */                     "finishedtoolputnumber.finishedcode"));
/*  61 */               row.add(messages.getMessage(FinishedToolPutnumberAction.this.getLocale(request), 
/*  62 */                     "finishedtoolputnumber.putnumber"));
/*  63 */               row.add(messages.getMessage(FinishedToolPutnumberAction.this.getLocale(request), 
/*  64 */                     "finishedtoolputnumber.status"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  69 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  70 */                     "toolCode"));
/*  71 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  72 */                     "finishedCode"));
/*  73 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  74 */                     "putNumber"));
/*  75 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  76 */                     "status.engShortDescription"));
/*     */             }
/*     */           });
/*     */       
/*  80 */       return new ActionForward("download/" + index + "/" + 
/*  81 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  83 */     if (queryForm.isFirstInit()) {
/*  84 */       queryForm.init(fm.getFinishedToolPutnumberListCount(conditions));
/*     */     } else {
/*  86 */       queryForm.init();
/*     */     } 
/*  88 */     int pageNo = queryForm.getPageNoAsInt();
/*  89 */     int pageSize = queryForm.getPageSizeAsInt();
/*  90 */     List result = fm.getFinishedToolPutnumberList(conditions, pageNo, 
/*  91 */         pageSize, FinishedToolPutnumberQueryOrder.ID, 
/*  92 */         queryForm.isDescend());
/*  93 */     request.setAttribute("X_RESULTLIST", result);
/*  94 */     request.setAttribute("x_selType", Integer.valueOf(105));
/*  95 */     putEnumListToRequest(request);
/*  96 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectToolCode1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 102 */     FinishedToolPutnumberQueryForm queryForm = (FinishedToolPutnumberQueryForm)form;
/* 103 */     FinishedToolPutnumberManager fm = 
/* 104 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/* 105 */     Map conditions = constructQueryMap(queryForm);
/* 106 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 108 */     if (queryForm.isFirstInit()) {
/* 109 */       queryForm.init(fm.getFinishedToolPutnumberListCount(conditions));
/*     */     } else {
/* 111 */       queryForm.init();
/*     */     } 
/* 113 */     int pageNo = queryForm.getPageNoAsInt();
/* 114 */     int pageSize = queryForm.getPageSizeAsInt();
/* 115 */     List result = fm.getFinishedToolPutnumberList(conditions, pageNo, 
/* 116 */         pageSize, FinishedToolPutnumberQueryOrder.ID, 
/* 117 */         queryForm.isDescend());
/* 118 */     request.setAttribute("X_RESULTLIST", result);
/*     */     
/* 120 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map constructQueryMap(FinishedToolPutnumberQueryForm queryForm) {
/* 127 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 128 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 134 */     putEnumListToRequest(request);
/* 135 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 142 */     BeanForm finishedToolPutnumberForm = (BeanForm)form;
/* 143 */     FinishedToolPutnumberManager fm = 
/* 144 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/* 145 */     FinishedToolPutnumber fsr = new FinishedToolPutnumber();
/* 146 */     finishedToolPutnumberForm.populate(fsr, "to_bean");
/* 147 */     fm.insertFinishedToolPutnumber(fsr);
/* 148 */     request.setAttribute("X_OBJECT", fsr);
/* 149 */     request.setAttribute("X_ROWPAGE", "finishedToolPutnumber/row.jsp");
/* 150 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 155 */     String id = request.getParameter("id");
/* 156 */     FinishedToolPutnumberManager fm = 
/* 157 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/* 158 */     FinishedToolPutnumber fsr = fm.getFinishedToolPutnumber(
/* 159 */         Integer.valueOf(Integer.parseInt(id)));
/* 160 */     request.setAttribute("fsr", fsr);
/* 161 */     if (!isBack(request)) {
/*     */       
/* 163 */       BeanForm finishedToolPutnumberForm = (BeanForm)getForm(
/* 164 */           "/updatefinishedToolPutnumber", request);
/* 165 */       finishedToolPutnumberForm.populate(fsr, "to_form");
/*     */     } 
/* 167 */     putEnumListToRequest(request);
/* 168 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 173 */     String id = request.getParameter("id");
/* 174 */     FinishedToolPutnumberManager fm = 
/* 175 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/* 176 */     fm.deleteFinishedToolPutnumber(fm.getFinishedToolPutnumber(
/* 177 */           Integer.valueOf(Integer.parseInt(id))));
/* 178 */     ActionForward forward = new ActionForward();
/* 179 */     forward.setRedirect(true);
/* 180 */     forward.setPath("listFinishedToolPutnumber.do");
/* 181 */     return forward;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 186 */     BeanForm finishedToolPutnumberForm = (BeanForm)form;
/* 187 */     FinishedToolPutnumber fsr = new FinishedToolPutnumber();
/* 188 */     finishedToolPutnumberForm.populate(fsr, "to_bean");
/* 189 */     FinishedToolPutnumberManager fm = 
/* 190 */       ServiceLocator.getFinishedToolPutnumberManager(request);
/* 191 */     fm.updateFinishedToolPutnumber(fsr);
/* 192 */     request.setAttribute("X_OBJECT", fsr);
/* 193 */     request.setAttribute("X_ROWPAGE", "finishedToolPutnumber/row.jsp");
/* 194 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 198 */     request.setAttribute("X_YESNOLIST", 
/* 199 */         PersistentEnum.getEnumList(YesNo.class));
/* 200 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 201 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 202 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/FinishedToolPutnumberAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */