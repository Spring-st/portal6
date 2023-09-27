/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.query.SycSleepTimeQueryCondition;
/*     */ import com.aof.model.basic.query.SycSleepTimeQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.query.BasicCustomerQueryCondition;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.SycSleepTimeQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
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
/*     */ 
/*     */ public class SycSleepTimeAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward listSycSleepTime(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  69 */     SycSleepTimeQueryForm queryForm = (SycSleepTimeQueryForm)form;
/*  70 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  71 */       queryForm.setOrder(SycSleepTimeQueryOrder.ID.getName());
/*  72 */       queryForm.setDescend(false);
/*     */     } 
/*  74 */     SycSleepTimeManager fm = ServiceLocator.getSycSleepTimeManager(request);
/*  75 */     Map conditions = constructQueryMap(queryForm);
/*  76 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  78 */     String exportType = queryForm.getExportType();
/*  79 */     if (StringUtils.isNotEmpty(exportType)) {
/*  80 */       List data = fm.getSycSleepTimeList(conditions, 0, -1, SycSleepTimeQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  82 */       int index = SessionTempFile.createNewTempFile(request);
/*  83 */       String fileName = "containerType";
/*  84 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  87 */               MessageResources messages = SycSleepTimeAction.this.getResources(request);
/*  88 */               row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.code"));
/*  89 */               row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.capacity"));
/*  90 */               row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.date"));
/*  91 */               row.add(messages.getMessage(SycSleepTimeAction.this.getLocale(request), "containerType.enabled.chnShortDescription"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  95 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/*  96 */               row.add(BeanHelper.getBeanPropertyValue(data, "capacity"));
/*  97 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/*  98 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */             }
/*     */           });
/*     */       
/* 102 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 104 */     if (queryForm.isFirstInit()) {
/* 105 */       queryForm.init(fm.getSycSleepTimeCount(conditions));
/*     */     } else {
/* 107 */       queryForm.init();
/*     */     } 
/* 109 */     List result = fm.getSycSleepTimeList(conditions, queryForm.getPageNoAsInt(), 
/* 110 */         queryForm.getPageSizeAsInt(), SycSleepTimeQueryOrder.ID, 
/* 111 */         queryForm.isDescend());
/*     */     
/* 113 */     request.setAttribute("X_RESULTLIST", result);
/* 114 */     request.setAttribute("x_selType", Integer.valueOf(180));
/*     */ 
/*     */     
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(SycSleepTimeQueryForm queryForm) {
/* 124 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 125 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 126 */     if (id != null && !id.equals("")) {
/* 127 */       conditions.put(SycSleepTimeQueryCondition.ID_EQ, id);
/*     */     }
/* 129 */     String type = queryForm.getType();
/* 130 */     if (type != null && !type.equals("")) {
/* 131 */       conditions.put(SycSleepTimeQueryCondition.TYPE_EQ, type);
/*     */     }
/* 133 */     String sleepTime = queryForm.getSleepTime();
/* 134 */     if (sleepTime != null && !sleepTime.equals("")) {
/* 135 */       conditions.put(SycSleepTimeQueryCondition.SLEEPTIME_EQ, sleepTime);
/*     */     }
/* 137 */     return conditions;
/*     */   }
/*     */   private SycSleepTime getSycSleepTime(HttpServletRequest request) throws Exception {
/* 140 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 141 */     SycSleepTimeManager SycSleepTimeManager = ServiceLocator.getSycSleepTimeManager(request);
/* 142 */     SycSleepTime SycSleepTime = SycSleepTimeManager.getSycSleepTime(id);
/* 143 */     if (SycSleepTime == null)
/* 144 */       throw new ActionException("SycSleepTime.notFound", id); 
/* 145 */     return SycSleepTime;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 149 */     List siteList = getAndCheckGrantedSiteList(request);
/* 150 */     request.setAttribute("X_SITELIST", siteList);
/* 151 */     request.setAttribute("X_ISPRINT", PersistentEnum.getEnumList(YesNo.class));
/* 152 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 168 */     SycSleepTime SycSleepTime = getSycSleepTime(request);
/* 169 */     request.setAttribute("x_containerType", SycSleepTime);
/* 170 */     if (!isBack(request)) {
/*     */       
/* 172 */       BeanForm SycSleepTimeForm = (BeanForm)getForm("/updateSycSleepTimeMaintenanceAction", request);
/* 173 */       SycSleepTimeForm.populate(SycSleepTime, "to_form");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     return mapping.findForward("page");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 195 */     SycSleepTime containerType = getSycSleepTime(request);
/* 196 */     SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
/*     */     try {
/* 198 */       cm.deleteSycSleepTime(containerType);
/*     */     }
/* 200 */     catch (Throwable t) {
/*     */       
/* 202 */       throw new ActionException("containerType.delete.fail");
/*     */     } 
/* 204 */     return new ActionForward("listSycSleepTimeListAction.do", true);
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
/* 220 */     BeanForm sycSleepTimeForm = (BeanForm)form;
/* 221 */     SycSleepTime sycSleepTime = new SycSleepTime();
/* 222 */     sycSleepTimeForm.populate(sycSleepTime, "to_bean");
/* 223 */     SycSleepTimeManager sycSleepTimeManager = ServiceLocator.getSycSleepTimeManager(request);
/* 224 */     sycSleepTime = sycSleepTimeManager.updateSycSleepTime(sycSleepTime);
/*     */     
/* 226 */     request.setAttribute("X_OBJECT", sycSleepTime);
/* 227 */     request.setAttribute("X_ROWPAGE", "wmsbasic/sycSleepTime/row.jsp");
/* 228 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 232 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 236 */     String s = request.getParameter("site_id");
/* 237 */     return (s != null && !s.equals(""));
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
/* 254 */     Site site = null;
/* 255 */     if (!isBack(request)) {
/* 256 */       SycSleepTime containerType = new SycSleepTime();
/* 257 */       BeanForm containerTypeForm = (BeanForm)getForm("/insertSycSleepTimeMaintenanceAction", request);
/* 258 */       containerTypeForm.populate(containerType, "to_form");
/*     */     } 
/*     */ 
/*     */     
/* 262 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 279 */     Site site = null;
/* 280 */     SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
/* 281 */     BeanForm containerTypeForm = (BeanForm)form;
/* 282 */     SycSleepTime containerType = new SycSleepTime();
/* 283 */     containerTypeForm.populate(containerType, "to_bean");
/*     */     
/* 285 */     request.setAttribute("X_OBJECT", cm.insertSycSleepTime(containerType));
/* 286 */     request.setAttribute("X_ROWPAGE", "wmsbasic/sycSleepTime/row.jsp");
/* 287 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward AJAXType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 295 */     String code = request.getParameter("type");
/* 296 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 297 */     if (code != null && code.trim().length() > 0) {
/* 298 */       conditions.put(SycSleepTimeQueryCondition.TYPE_EQ, code);
/*     */     }
/* 300 */     SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
/* 301 */     int result = cm.getSycSleepTimeCount(conditions);
/* 302 */     response.setContentType("text/html;charset=utf-8");
/* 303 */     response.setCharacterEncoding("UTF-8");
/* 304 */     response.getWriter().print(result);
/* 305 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward AJAXEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 311 */     String code = request.getParameter("type");
/* 312 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 313 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 314 */     if (code != null && code.trim().length() > 0) {
/* 315 */       conditions.put(BasicCustomerQueryCondition.CODE_EQ, code);
/* 316 */       conditions.put(BasicCustomerQueryCondition.EDITID_NE, id);
/*     */     } 
/* 318 */     SycSleepTimeManager cm = ServiceLocator.getSycSleepTimeManager(request);
/* 319 */     int result = cm.getSycSleepTimeCount(conditions);
/* 320 */     response.setContentType("text/html;charset=utf-8");
/* 321 */     response.setCharacterEncoding("UTF-8");
/* 322 */     response.getWriter().print(result);
/* 323 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/SycSleepTimeAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */