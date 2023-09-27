/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.UnplannedReasons;
/*     */ import com.aof.model.basic.query.UnplannedReasonsQueryCondition;
/*     */ import com.aof.model.basic.query.UnplannedReasonsQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.basic.UnplannedReasonsManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.UnplannedReasonsQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Date;
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
/*     */ public class UnplannedReasonsAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  65 */     UnplannedReasonsQueryForm queryForm = (UnplannedReasonsQueryForm)form;
/*  66 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  67 */       queryForm.setOrder(UnplannedReasonsQueryOrder.ID.getName());
/*  68 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  71 */     UnplannedReasonsManager fm = ServiceLocator.getUnplannedReasonsManager(request);
/*  72 */     Map conditions = constructQueryMap(queryForm);
/*  73 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  75 */     String exportType = queryForm.getExportType();
/*  76 */     if (StringUtils.isNotEmpty(exportType)) {
/*  77 */       List data = fm.getUnplannedReasonsList(conditions, 0, -1, UnplannedReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  79 */       int index = SessionTempFile.createNewTempFile(request);
/*  80 */       String fileName = "UnplannedReasons";
/*  81 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  84 */               MessageResources messages = UnplannedReasonsAction.this.getResources(request);
/*  85 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.instructions"));
/*  86 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.describe"));
/*  87 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.expenses_course"));
/*  88 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.department_cost"));
/*  89 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.date"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  93 */               row.add(BeanHelper.getBeanPropertyValue(data, "instructions"));
/*  94 */               row.add(BeanHelper.getBeanPropertyValue(data, "describe"));
/*  95 */               row.add(BeanHelper.getBeanPropertyValue(data, "expenses_course"));
/*  96 */               row.add(BeanHelper.getBeanPropertyValue(data, "department_cost"));
/*  97 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/*     */             }
/*     */           });
/*     */       
/* 101 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 103 */     if (queryForm.isFirstInit()) {
/* 104 */       queryForm.init(fm.getUnplannedReasonsListCount(conditions));
/*     */     } else {
/* 106 */       queryForm.init();
/*     */     } 
/* 108 */     List result = fm.getUnplannedReasonsList(conditions, 
/* 109 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 110 */         UnplannedReasonsQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 112 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 113 */     request.setAttribute("x_siteList", siteList);
/* 114 */     request.setAttribute("X_RESULTLIST", result);
/* 115 */     request.setAttribute("x_selType", Integer.valueOf(80));
/* 116 */     putEnumListToRequest(request);
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 123 */     UnplannedReasonsQueryForm queryForm = (UnplannedReasonsQueryForm)form;
/* 124 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 125 */       queryForm.setOrder(UnplannedReasonsQueryOrder.ID.getName());
/* 126 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 129 */     UnplannedReasonsManager fm = ServiceLocator.getUnplannedReasonsManager(request);
/* 130 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 132 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 134 */     String exportType = queryForm.getExportType();
/* 135 */     if (StringUtils.isNotEmpty(exportType)) {
/* 136 */       List data = fm.getUnplannedReasonsList(conditions, 0, -1, UnplannedReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 138 */       int index = SessionTempFile.createNewTempFile(request);
/* 139 */       String fileName = "UnplannedReasons";
/* 140 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 143 */               MessageResources messages = UnplannedReasonsAction.this.getResources(request);
/* 144 */               row.add(messages.getMessage(UnplannedReasonsAction.this.getLocale(request), "UnplannedReasons.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 148 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 152 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 154 */     if (queryForm.isFirstInit()) {
/* 155 */       queryForm.init(fm.getUnplannedReasonsListCount(conditions));
/*     */     } else {
/* 157 */       queryForm.init();
/*     */     } 
/* 159 */     List result = fm.getUnplannedReasonsList(conditions, 
/* 160 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 161 */         UnplannedReasonsQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 163 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 164 */     request.setAttribute("x_siteList", siteList);
/* 165 */     request.setAttribute("X_RESULTLIST", result);
/* 166 */     putEnumListToRequest(request);
/* 167 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(UnplannedReasonsQueryForm queryForm) {
/* 171 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 172 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 173 */     if (id != null && !id.equals("")) {
/* 174 */       conditions.put(UnplannedReasonsQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 177 */     String type = queryForm.getType();
/* 178 */     if (type != null && !type.equals("")) {
/* 179 */       conditions.put(UnplannedReasonsQueryCondition.TYPE_EQ, type);
/*     */     }
/* 181 */     String status = queryForm.getStatus();
/* 182 */     if (status != null && !status.equals("")) {
/* 183 */       conditions.put(UnplannedReasonsQueryCondition.ENABLED_EQ, status);
/*     */     }
/*     */     
/* 186 */     return conditions;
/*     */   }
/*     */   private UnplannedReasons getUnplannedReasons(HttpServletRequest request) throws Exception {
/* 189 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 190 */     UnplannedReasonsManager UnplannedReasonsManager = ServiceLocator.getUnplannedReasonsManager(request);
/* 191 */     UnplannedReasons UnplannedReasons = UnplannedReasonsManager.getUnplannedReasons(id);
/* 192 */     if (UnplannedReasons == null)
/* 193 */       throw new ActionException("UnplannedReasons.notFound", id); 
/* 194 */     return UnplannedReasons;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 198 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 199 */     request.setAttribute("x_type", PersistentEnum.getEnumList(YesNo.class));
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 215 */     UnplannedReasons reasons = getUnplannedReasons(request);
/* 216 */     request.setAttribute("x_unplanned", reasons);
/* 217 */     if (!isBack(request)) {
/*     */       
/* 219 */       BeanForm UnplannedReasonsForm = (BeanForm)getForm("/updateUnplannedReasons", request);
/* 220 */       UnplannedReasonsForm.populate(reasons, "to_form");
/*     */     } 
/* 222 */     putEnumListToRequest(request);
/* 223 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 224 */     request.setAttribute("x_siteList", siteList);
/* 225 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 241 */     BeanForm UnplannedReasonsForm = (BeanForm)form;
/* 242 */     UnplannedReasons UnplannedReasons = new UnplannedReasons();
/* 243 */     UnplannedReasonsForm.populate(UnplannedReasons, "to_bean");
/* 244 */     UnplannedReasonsManager UnplannedReasonsManager = ServiceLocator.getUnplannedReasonsManager(request);
/* 245 */     UnplannedReasons.setDate(new Date());
/* 246 */     UnplannedReasons.setUser(getCurrentUser(request));
/* 247 */     UnplannedReasons = UnplannedReasonsManager.updateUnplannedReasons(UnplannedReasons);
/*     */     
/* 249 */     request.setAttribute("X_OBJECT", UnplannedReasons);
/* 250 */     request.setAttribute("X_ROWPAGE", "wmsbasic/unplannedReasons/row.jsp");
/* 251 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 257 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 261 */     String s = request.getParameter("site_id");
/* 262 */     return (s != null && !s.equals(""));
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
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 279 */     if (!isBack(request)) {
/* 280 */       UnplannedReasons UnplannedReasons = new UnplannedReasons();
/* 281 */       BeanForm UnplannedReasonsForm = (BeanForm)getForm("/insertUnplannedReasons", request);
/* 282 */       UnplannedReasonsForm.populate(UnplannedReasons, "to_form");
/*     */     } 
/* 284 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/*     */     
/* 286 */     request.setAttribute("x_siteList", siteList);
/* 287 */     putEnumListToRequest(request);
/* 288 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 304 */     UnplannedReasonsManager cm = ServiceLocator.getUnplannedReasonsManager(request);
/* 305 */     BeanForm beanForm = (BeanForm)form;
/* 306 */     UnplannedReasons reasons = new UnplannedReasons();
/* 307 */     reasons.setUser(getCurrentUser(request));
/* 308 */     reasons.setDate(new Date());
/* 309 */     beanForm.populate(reasons, "to_bean");
/* 310 */     reasons = cm.insertUnplannedReasons(reasons);
/*     */     
/* 312 */     request.setAttribute("X_OBJECT", reasons);
/* 313 */     request.setAttribute("X_ROWPAGE", "wmsbasic/unplannedReasons/row.jsp");
/* 314 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/UnplannedReasonsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */