/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.basic.query.WmsToolQueryCondition;
/*     */ import com.aof.model.basic.query.WmsToolQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.basic.WmsToolManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.WmsToolQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
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
/*     */ public class WmsToolAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  61 */     WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
/*  62 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  63 */       queryForm.setOrder(WmsToolQueryOrder.ID.getName());
/*  64 */       queryForm.setDescend(false);
/*     */     } 
/*  66 */     WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
/*  67 */     Map conditions = constructQueryMap(queryForm);
/*  68 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  70 */     String exportType = queryForm.getExportType();
/*  71 */     if (StringUtils.isNotEmpty(exportType)) {
/*  72 */       List data = fm.getWmsToolList(conditions, 0, -1, WmsToolQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  74 */       int index = SessionTempFile.createNewTempFile(request);
/*  75 */       String fileName = "containerType";
/*  76 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  79 */               MessageResources messages = WmsToolAction.this.getResources(request);
/*  80 */               row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.code"));
/*  81 */               row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.capacity"));
/*  82 */               row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.date"));
/*  83 */               row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.enabled.chnShortDescription"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  87 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/*  88 */               row.add(BeanHelper.getBeanPropertyValue(data, "capacity"));
/*  89 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/*  90 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */             }
/*     */           });
/*     */       
/*  94 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  96 */     if (queryForm.isFirstInit()) {
/*  97 */       queryForm.init(fm.getWmsToolListCount(conditions));
/*     */     } else {
/*  99 */       queryForm.init();
/*     */     } 
/* 101 */     List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(), 
/* 102 */         queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID, 
/* 103 */         queryForm.isDescend());
/*     */     
/* 105 */     request.setAttribute("X_RESULTLIST", result);
/* 106 */     request.setAttribute("x_selType", Integer.valueOf(14));
/* 107 */     putEnumListToRequest(request);
/*     */     
/* 109 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 115 */     WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
/* 116 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 117 */       queryForm.setOrder(WmsToolQueryOrder.ID.getName());
/* 118 */       queryForm.setDescend(false);
/*     */     } 
/* 120 */     WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
/* 121 */     Map<WmsToolQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 122 */     conditions.put(WmsToolQueryCondition.STATUS_EQ, Integer.valueOf(0));
/*     */     
/* 124 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 126 */     String exportType = queryForm.getExportType();
/* 127 */     if (StringUtils.isNotEmpty(exportType)) {
/* 128 */       List data = fm.getWmsToolList(conditions, 0, -1, WmsToolQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 130 */       int index = SessionTempFile.createNewTempFile(request);
/* 131 */       String fileName = "containerType";
/* 132 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 135 */               MessageResources messages = WmsToolAction.this.getResources(request);
/* 136 */               row.add(messages.getMessage(WmsToolAction.this.getLocale(request), "containerType.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 140 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 144 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 146 */     if (queryForm.isFirstInit()) {
/* 147 */       queryForm.init(fm.getWmsToolListCount(conditions));
/*     */     } else {
/* 149 */       queryForm.init();
/*     */     } 
/* 151 */     List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(), 
/* 152 */         queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID, 
/* 153 */         queryForm.isDescend());
/*     */     
/* 155 */     request.setAttribute("X_RESULTLIST", result);
/* 156 */     request.setAttribute("x_selType", Integer.valueOf(4));
/* 157 */     putEnumListToRequest(request);
/* 158 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectTool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 164 */     WmsToolQueryForm queryForm = (WmsToolQueryForm)form;
/* 165 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 166 */       queryForm.setOrder(WmsToolQueryOrder.ID.getName());
/*     */     }
/* 168 */     WmsToolManager fm = ServiceLocator.getWmsToolManager(request);
/* 169 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 171 */     if (queryForm.isFirstInit()) {
/* 172 */       queryForm.init(fm.getWmsToolListCount(conditions));
/*     */     } else {
/* 174 */       queryForm.init();
/*     */     } 
/* 176 */     List result = fm.getWmsToolList(conditions, queryForm.getPageNoAsInt(), 
/* 177 */         queryForm.getPageSizeAsInt(), WmsToolQueryOrder.ID, 
/* 178 */         queryForm.isDescend());
/*     */ 
/*     */     
/* 181 */     request.setAttribute("X_RESULTLIST", result);
/* 182 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 183 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(WmsToolQueryForm queryForm) {
/* 187 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 188 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 189 */     if (id != null && !id.equals("")) {
/* 190 */       conditions.put(WmsToolQueryCondition.ID_EQ, id);
/*     */     }
/* 192 */     String code = queryForm.getCode();
/* 193 */     if (code != null && !code.equals("")) {
/* 194 */       conditions.put(WmsToolQueryCondition.CODE_EQ, code);
/*     */     }
/* 196 */     String isPrint = queryForm.getIsPrint();
/* 197 */     if (isPrint != null && !isPrint.equals("")) {
/* 198 */       conditions.put(WmsToolQueryCondition.ISPRINT_EQ, isPrint);
/*     */     }
/* 200 */     return conditions;
/*     */   }
/*     */   private WmsTool getWmsTool(HttpServletRequest request) throws Exception {
/* 203 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 204 */     WmsToolManager wmsToolManager = ServiceLocator.getWmsToolManager(request);
/* 205 */     WmsTool wmsTool = wmsToolManager.getWmsTool(id);
/* 206 */     if (wmsTool == null)
/* 207 */       throw new ActionException("wmsTool.notFound", id); 
/* 208 */     return wmsTool;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 212 */     List siteList = getAndCheckGrantedSiteList(request);
/* 213 */     request.setAttribute("X_SITELIST", siteList);
/* 214 */     request.setAttribute("X_ISPRINT", PersistentEnum.getEnumList(YesNo.class));
/* 215 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 231 */     WmsTool wmsTool = getWmsTool(request);
/* 232 */     request.setAttribute("x_containerType", wmsTool);
/* 233 */     if (!isBack(request)) {
/*     */       
/* 235 */       BeanForm wmsToolForm = (BeanForm)getForm("/updateWmsTool", request);
/* 236 */       wmsToolForm.populate(wmsTool, "to_form");
/*     */     } 
/*     */     
/* 239 */     putEnumListToRequest(request);
/*     */     
/* 241 */     return mapping.findForward("page");
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
/* 258 */     WmsTool containerType = getWmsTool(request);
/* 259 */     WmsToolManager cm = ServiceLocator.getWmsToolManager(request);
/*     */     try {
/* 261 */       cm.deleteWmsTool(containerType);
/*     */     }
/* 263 */     catch (Throwable t) {
/*     */       
/* 265 */       throw new ActionException("containerType.delete.fail");
/*     */     } 
/* 267 */     return mapping.findForward("success");
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
/* 283 */     BeanForm containerTypeForm = (BeanForm)form;
/* 284 */     WmsTool containerType = new WmsTool();
/* 285 */     containerTypeForm.populate(containerType, "to_bean");
/*     */     
/* 287 */     WmsToolManager containerTypeManager = ServiceLocator.getWmsToolManager(request);
/*     */     
/* 289 */     request.setAttribute("X_OBJECT", containerTypeManager.updateWmsTool(containerType));
/* 290 */     request.setAttribute("X_ROWPAGE", "wmsbasic/wmsTool/row.jsp");
/* 291 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 295 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 299 */     String s = request.getParameter("site_id");
/* 300 */     return (s != null && !s.equals(""));
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
/* 317 */     Site site = null;
/* 318 */     if (!isBack(request)) {
/* 319 */       WmsTool containerType = new WmsTool();
/* 320 */       BeanForm containerTypeForm = (BeanForm)getForm("/insertWmsTool", request);
/* 321 */       containerTypeForm.populate(containerType, "to_form");
/*     */     } 
/*     */     
/* 324 */     putEnumListToRequest(request);
/* 325 */     return mapping.findForward("page");
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
/* 342 */     Site site = null;
/* 343 */     WmsToolManager cm = ServiceLocator.getWmsToolManager(request);
/* 344 */     BeanForm containerTypeForm = (BeanForm)form;
/* 345 */     WmsTool containerType = new WmsTool();
/* 346 */     containerTypeForm.populate(containerType, "to_bean");
/* 347 */     containerType.setStatus(YesNo.NO);
/*     */     
/* 349 */     request.setAttribute("X_OBJECT", cm.insertWmsTool(containerType));
/* 350 */     request.setAttribute("X_ROWPAGE", "wmsbasic/wmsTool/row.jsp");
/* 351 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward pirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 357 */     WmsToolManager porm = ServiceLocator.getWmsToolManager(request);
/* 358 */     String string = request.getParameter("str");
/* 359 */     String[] strings = string.split(",");
/* 360 */     List<WmsTool> boxlist = new ArrayList<WmsTool>();
/* 361 */     for (int i = 0; i < strings.length; i++) {
/* 362 */       Integer str = Integer.valueOf(Integer.parseInt(strings[i]));
/* 363 */       boxlist.add(porm.getWmsTool(str));
/*     */     } 
/*     */     
/* 366 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 367 */     putEnumListToRequest(request);
/* 368 */     request.setAttribute("path", request.getContextPath());
/* 369 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward updatePirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 375 */     WmsToolManager poip = ServiceLocator.getWmsToolManager(request);
/* 376 */     String ids = request.getParameter("ids");
/* 377 */     String[] idr = ids.split(";");
/* 378 */     String idInteger = null; byte b; int i; String[] arrayOfString1;
/* 379 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 380 */       WmsTool box = poip.getWmsTool(Integer.valueOf(Integer.parseInt(id)));
/* 381 */       poip.updateWmsTool(box);
/*     */       b++; }
/*     */     
/* 384 */     return new ActionForward("listWmsTool.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectToolNext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 391 */     request.setAttribute("x_arrays", request.getParameter("arrays"));
/* 392 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/WmsToolAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */