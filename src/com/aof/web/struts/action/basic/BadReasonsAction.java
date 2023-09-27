/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import com.aof.model.basic.query.BadReasonsQueryCondition;
/*     */ import com.aof.model.basic.query.BadReasonsQueryOrder;
/*     */ import com.aof.model.metadata.BadReasonsType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.basic.BadReasonsManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.BadReasonsQueryForm;
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
/*     */ 
/*     */ public class BadReasonsAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  66 */     BadReasonsQueryForm queryForm = (BadReasonsQueryForm)form;
/*  67 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  68 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/*  69 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  72 */     BadReasonsManager fm = ServiceLocator.getBadReasonsManager(request);
/*  73 */     Map conditions = constructQueryMap(queryForm);
/*  74 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  76 */     String exportType = queryForm.getExportType();
/*  77 */     if (StringUtils.isNotEmpty(exportType)) {
/*  78 */       List data = fm.getBadReasonsList(conditions, 0, -1, BadReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  80 */       int index = SessionTempFile.createNewTempFile(request);
/*  81 */       String fileName = "BadReasons";
/*  82 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  85 */               MessageResources messages = BadReasonsAction.this.getResources(request);
/*  86 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.describe"));
/*  87 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.type.chnShortDescription"));
/*  88 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.user.name"));
/*  89 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.status.chnShortDescription"));
/*  90 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.remark"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  94 */               row.add(BeanHelper.getBeanPropertyValue(data, "describe"));
/*  95 */               row.add(BeanHelper.getBeanPropertyValue(data, "type.chnShortDescription"));
/*  96 */               row.add(BeanHelper.getBeanPropertyValue(data, "user.name"));
/*  97 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*  98 */               row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
/*     */             }
/*     */           });
/* 101 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 103 */     if (queryForm.isFirstInit()) {
/* 104 */       queryForm.init(fm.getBadReasonsListCount(conditions));
/*     */     } else {
/* 106 */       queryForm.init();
/*     */     } 
/* 108 */     List result = fm.getBadReasonsList(conditions, 
/* 109 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 110 */         BadReasonsQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 112 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 113 */     request.setAttribute("x_siteList", siteList);
/* 114 */     request.setAttribute("X_RESULTLIST", result);
/* 115 */     request.setAttribute("x_selType", Integer.valueOf(12));
/* 116 */     putEnumListToRequest(request);
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 123 */     BadReasonsQueryForm queryForm = (BadReasonsQueryForm)form;
/* 124 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 125 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 126 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 129 */     BadReasonsManager fm = ServiceLocator.getBadReasonsManager(request);
/* 130 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 132 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 134 */     String exportType = queryForm.getExportType();
/* 135 */     if (StringUtils.isNotEmpty(exportType)) {
/* 136 */       List data = fm.getBadReasonsList(conditions, 0, -1, BadReasonsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 138 */       int index = SessionTempFile.createNewTempFile(request);
/* 139 */       String fileName = "BadReasons";
/* 140 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 143 */               MessageResources messages = BadReasonsAction.this.getResources(request);
/* 144 */               row.add(messages.getMessage(BadReasonsAction.this.getLocale(request), "BadReasons.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 148 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 152 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 154 */     if (queryForm.isFirstInit()) {
/* 155 */       queryForm.init(fm.getBadReasonsListCount(conditions));
/*     */     } else {
/* 157 */       queryForm.init();
/*     */     } 
/* 159 */     List result = fm.getBadReasonsList(conditions, 
/* 160 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 161 */         BadReasonsQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 163 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 164 */     request.setAttribute("x_siteList", siteList);
/* 165 */     request.setAttribute("X_RESULTLIST", result);
/* 166 */     putEnumListToRequest(request);
/* 167 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(BadReasonsQueryForm queryForm) {
/* 171 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 172 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 173 */     if (id != null && !id.equals("")) {
/* 174 */       conditions.put(BadReasonsQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 177 */     String type = queryForm.getType();
/* 178 */     if (type != null && !type.equals("")) {
/* 179 */       conditions.put(BadReasonsQueryCondition.TYPE_EQ, type);
/*     */     }
/* 181 */     String status = queryForm.getStatus();
/* 182 */     if (status != null && !status.equals("")) {
/* 183 */       conditions.put(BadReasonsQueryCondition.ENABLED_EQ, status);
/*     */     }
/*     */     
/* 186 */     return conditions;
/*     */   }
/*     */   private BadReasons getBadReasons(HttpServletRequest request) throws Exception {
/* 189 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 190 */     BadReasonsManager BadReasonsManager = ServiceLocator.getBadReasonsManager(request);
/* 191 */     BadReasons BadReasons = BadReasonsManager.getBadReasons(id);
/* 192 */     if (BadReasons == null)
/* 193 */       throw new ActionException("BadReasons.notFound", id); 
/* 194 */     return BadReasons;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 198 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 199 */     request.setAttribute("x_type", PersistentEnum.getEnumList(BadReasonsType.class));
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
/* 215 */     BadReasons BadReasons = getBadReasons(request);
/*     */     
/* 217 */     request.setAttribute("x_BadReasons", BadReasons);
/* 218 */     if (!isBack(request)) {
/*     */       
/* 220 */       BeanForm BadReasonsForm = (BeanForm)getForm("/updateBadReasons", request);
/* 221 */       BadReasonsForm.populate(BadReasons, "to_form");
/*     */     } 
/* 223 */     putEnumListToRequest(request);
/* 224 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 225 */     request.setAttribute("x_siteList", siteList);
/* 226 */     return mapping.findForward("page");
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
/* 242 */     BeanForm BadReasonsForm = (BeanForm)form;
/* 243 */     BadReasons badReasons = new BadReasons();
/* 244 */     BadReasonsForm.populate(badReasons, "to_bean");
/* 245 */     BadReasonsManager BadReasonsManager = ServiceLocator.getBadReasonsManager(request);
/* 246 */     badReasons.setDate(new Date());
/* 247 */     badReasons.setUser(getCurrentUser(request));
/* 248 */     badReasons = BadReasonsManager.updateBadReasons(badReasons);
/*     */     
/* 250 */     request.setAttribute("X_OBJECT", badReasons);
/* 251 */     request.setAttribute("X_ROWPAGE", "wmsbasic/badReasons/row.jsp");
/* 252 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 258 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 262 */     String s = request.getParameter("site_id");
/* 263 */     return (s != null && !s.equals(""));
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
/* 280 */     if (!isBack(request)) {
/* 281 */       BadReasons BadReasons = new BadReasons();
/* 282 */       BeanForm BadReasonsForm = (BeanForm)getForm("/insertBadReasons", request);
/* 283 */       BadReasonsForm.populate(BadReasons, "to_form");
/*     */     } 
/* 285 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/*     */     
/* 287 */     request.setAttribute("x_siteList", siteList);
/* 288 */     putEnumListToRequest(request);
/* 289 */     return mapping.findForward("page");
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
/* 305 */     BadReasonsManager cm = ServiceLocator.getBadReasonsManager(request);
/* 306 */     BeanForm BadReasonsForm = (BeanForm)form;
/* 307 */     BadReasons BadReasons = new BadReasons();
/* 308 */     BadReasons.setUser(getCurrentUser(request));
/* 309 */     BadReasons.setDate(new Date());
/* 310 */     BadReasonsForm.populate(BadReasons, "to_bean");
/* 311 */     BadReasons = cm.insertBadReasons(BadReasons);
/*     */     
/* 313 */     request.setAttribute("X_OBJECT", BadReasons);
/* 314 */     request.setAttribute("X_ROWPAGE", "wmsbasic/badReasons/row.jsp");
/* 315 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/BadReasonsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */