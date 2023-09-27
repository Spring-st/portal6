/*     */ package com.aof.web.struts.action.inventory;
/*     */ 
/*     */ import com.aof.model.admin.query.InventoryMovingQueryOrder;
/*     */ import com.aof.model.basic.query.BadReasonsQueryCondition;
/*     */ import com.aof.model.basic.query.BadReasonsQueryOrder;
/*     */ import com.aof.model.basicDataView.query.LocationPartNumberQueryOrder;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.inventory.query.InventoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.admin.InventoryMovingManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.InventoryOccupiedDetialQueryForm;
/*     */ import com.aof.web.struts.form.basic.InventoryQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
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
/*     */ public class InventoryAction
/*     */   extends BaseAction
/*     */ {
/*  58 */   private static final String[] Object = null;
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  72 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/*  73 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  74 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/*  75 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  78 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/*  79 */     Map<InventoryQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  80 */     conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*     */     
/*  82 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  84 */     String exportType = queryForm.getExportType();
/*  85 */     if (StringUtils.isNotEmpty(exportType)) {
/*  86 */       List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/*  87 */           queryForm.isDescend());
/*     */       
/*  89 */       int index = SessionTempFile.createNewTempFile(request);
/*  90 */       String fileName = "BadReasons";
/*  91 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  92 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  95 */               MessageResources messages = InventoryAction.this.getResources(request);
/*  96 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 100 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 105 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 107 */     if (queryForm.isFirstInit()) {
/* 108 */       queryForm.init(manager.getInventoryListCount(conditions));
/*     */     } else {
/* 110 */       queryForm.init();
/*     */     } 
/* 112 */     List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 113 */         InventoryQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 115 */     request.setAttribute("X_RESULTLIST", result);
/* 116 */     request.setAttribute("x_selType", Integer.valueOf(3));
/* 117 */     putEnumListToRequest(request);
/* 118 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listInventoryLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 123 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 124 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 125 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 126 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 129 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 130 */     Map<InventoryQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 131 */     conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*     */     
/* 133 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 135 */     String exportType = queryForm.getExportType();
/* 136 */     if (StringUtils.isNotEmpty(exportType)) {
/* 137 */       List data = manager.getInventoryDetialList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 138 */           queryForm.isDescend());
/*     */       
/* 140 */       int index = SessionTempFile.createNewTempFile(request);
/* 141 */       String fileName = "InventoryLocation";
/* 142 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 143 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 146 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 147 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.location.code"));
/* 148 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.location.basic_storeroom_id.name"));
/* 149 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryLocation.number"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 153 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 154 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.basic_storeroom_id.name"));
/* 155 */               row.add(BeanHelper.getBeanPropertyValue(data, "number"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 160 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 162 */     if (queryForm.isFirstInit()) {
/* 163 */       queryForm.init(manager.getInventoryDetialListCount(conditions));
/*     */     } else {
/* 165 */       queryForm.init();
/*     */     } 
/* 167 */     List result = manager.getInventoryDetialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 168 */         InventoryQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 170 */     request.setAttribute("X_RESULTLIST", result);
/* 171 */     putEnumListToRequest(request);
/* 172 */     request.setAttribute("x_selType", Integer.valueOf(127));
/* 173 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listInventoryPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 178 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 179 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 180 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 181 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 184 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 185 */     Map<InventoryQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 186 */     conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*     */     
/* 188 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 190 */     String exportType = queryForm.getExportType();
/* 191 */     if (StringUtils.isNotEmpty(exportType)) {
/* 192 */       List data = manager.getInventoryTotalList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 193 */           queryForm.isDescend());
/*     */       
/* 195 */       int index = SessionTempFile.createNewTempFile(request);
/* 196 */       String fileName = "InventoryPart";
/* 197 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 198 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 201 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 202 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.id"));
/* 203 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.describe1"));
/* 204 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.describe1"));
/* 205 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.number"));
/* 206 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryPart.part.unit"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 210 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 211 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 212 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 213 */               row.add(BeanHelper.getBeanPropertyValue(data, "number"));
/* 214 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 219 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 221 */     if (queryForm.isFirstInit()) {
/* 222 */       queryForm.init(manager.getInventoryTotalList(conditions));
/*     */     } else {
/* 224 */       queryForm.init();
/*     */     } 
/* 226 */     List result = manager.getInventoryTotalList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 227 */         InventoryQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 229 */     request.setAttribute("X_RESULTLIST", result);
/* 230 */     putEnumListToRequest(request);
/* 231 */     request.setAttribute("x_selType", Integer.valueOf(126));
/* 232 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 237 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 238 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 239 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 240 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 243 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 244 */     Map conditions = constructQueryMap(queryForm);
/* 245 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 247 */     String exportType = queryForm.getExportType();
/* 248 */     if (StringUtils.isNotEmpty(exportType)) {
/* 249 */       List data = manager.getInventoryDetialList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 250 */           queryForm.isDescend());
/*     */       
/* 252 */       int index = SessionTempFile.createNewTempFile(request);
/* 253 */       String fileName = "InventoryDetial";
/* 254 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 255 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 258 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 259 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.location.code"));
/* 260 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.id"));
/* 261 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
/* 262 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe1"));
/* 263 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe2"));
/* 264 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.number"));
/* 265 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part_qty"));
/* 266 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.unit"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 270 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 271 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 272 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 273 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 274 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 275 */               row.add(BeanHelper.getBeanPropertyValue(data, "number"));
/* 276 */               row.add(BeanHelper.getBeanPropertyValue(data, "part_qty"));
/* 277 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 282 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 284 */     if (queryForm.isFirstInit()) {
/* 285 */       queryForm.init(manager.getInventoryDetialListCount(conditions));
/*     */     } else {
/* 287 */       queryForm.init();
/*     */     } 
/* 289 */     List result = manager.getInventoryDetialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 290 */         InventoryQueryOrder.PART_ID, queryForm.isDescend());
/*     */     
/* 292 */     request.setAttribute("X_RESULTLIST", result);
/* 293 */     request.setAttribute("x_selType", Integer.valueOf(95));
/* 294 */     putEnumListToRequest(request);
/* 295 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 300 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 301 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 302 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 303 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 306 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 307 */     Map<InventoryQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 308 */     conditions.put(InventoryQueryCondition.TYPE_EQ, Integer.valueOf(0));
/*     */     
/* 310 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 312 */     String exportType = queryForm.getExportType();
/* 313 */     if (StringUtils.isNotEmpty(exportType)) {
/* 314 */       List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 315 */           queryForm.isDescend());
/*     */       
/* 317 */       int index = SessionTempFile.createNewTempFile(request);
/* 318 */       String fileName = "BadReasons";
/* 319 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 320 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 323 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 324 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 328 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 332 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 334 */     if (queryForm.isFirstInit()) {
/* 335 */       queryForm.init(manager.getInventoryListCount(conditions));
/*     */     } else {
/* 337 */       queryForm.init();
/*     */     } 
/* 339 */     List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 340 */         InventoryQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 342 */     request.setAttribute("X_RESULTLIST", result);
/* 343 */     request.setAttribute("x_selType", Integer.valueOf(4));
/* 344 */     putEnumListToRequest(request);
/* 345 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(InventoryQueryForm queryForm) {
/* 349 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 350 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 351 */     if (id != null && !id.equals("")) {
/* 352 */       conditions.put(BadReasonsQueryCondition.ID_EQ, id);
/*     */     }
/* 354 */     conditions.put(InventoryQueryCondition.NUMBER_NE, Integer.valueOf(0));
/* 355 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 359 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/*     */   public ActionForward getRecommendLot(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 374 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 375 */     String arrays = request.getParameter("arrays");
/* 376 */     List<Box> boxs = manager.getRecommendLot(arrays);
/*     */     
/* 378 */     request.setAttribute("x_box", boxs);
/* 379 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward checkLot(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 384 */     response.setContentType("text/json");
/* 385 */     response.setCharacterEncoding("UTF-8");
/* 386 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 387 */     String arrays = request.getParameter("arrays");
/* 388 */     String message = manager.checkLot(arrays);
/* 389 */     JSONObject json = new JSONObject();
/* 390 */     json.put("message", message);
/* 391 */     response.getWriter().print(json);
/* 392 */     return null;
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
/*     */   public ActionForward purchaseOrderOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 407 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 408 */     String arrays = request.getParameter("arrays");
/* 409 */     String[] array = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 410 */     for (i = (arrayOfString1 = array).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 411 */       manager.scanningPurchaseOrderOutbound(lot, getCurrentUser(request).getId().toString());
/*     */       b++; }
/*     */     
/* 414 */     return mapping.findForward("page");
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
/*     */   public ActionForward listProductScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 429 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 430 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 431 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 432 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 435 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 436 */     Map<InventoryQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 437 */     conditions.put(InventoryQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*     */ 
/*     */     
/* 440 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 442 */     String exportType = queryForm.getExportType();
/* 443 */     if (StringUtils.isNotEmpty(exportType)) {
/* 444 */       List data = manager.getInventoryList(conditions, 0, -1, InventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 445 */           queryForm.isDescend());
/*     */       
/* 447 */       int index = SessionTempFile.createNewTempFile(request);
/* 448 */       String fileName = "BadReasons";
/* 449 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 450 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 453 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 454 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "BadReasons.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 458 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 463 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 465 */     if (queryForm.isFirstInit()) {
/* 466 */       queryForm.init(manager.getInventoryListCount(conditions));
/*     */     } else {
/* 468 */       queryForm.init();
/*     */     } 
/* 470 */     List result = manager.getInventoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 471 */         InventoryQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 473 */     request.setAttribute("X_RESULTLIST", result);
/* 474 */     request.setAttribute("x_selType", Integer.valueOf(5));
/* 475 */     putEnumListToRequest(request);
/* 476 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward productScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 481 */     response.setContentType("text/json");
/* 482 */     response.setCharacterEncoding("UTF-8");
/* 483 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 485 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 486 */     String arrays = request.getParameter("ids");
/* 487 */     manager.scanningOutboundByProduct(arrays, getCurrentUser(request).getId().toString());
/*     */     
/* 489 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 490 */     response.getWriter().print(jo);
/* 491 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward inventoryMovingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 496 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/* 497 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 498 */       queryForm.setOrder(BadReasonsQueryOrder.ID.getName());
/* 499 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 502 */     InventoryMovingManager manager = ServiceLocator.getInventoryMovingManager(request);
/* 503 */     Map conditions = constructQueryMap(queryForm);
/* 504 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 506 */     String exportType = queryForm.getExportType();
/* 507 */     if (StringUtils.isNotEmpty(exportType)) {
/* 508 */       List data = manager.getInventoryMovingList(conditions, 0, -1, 
/* 509 */           InventoryMovingQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 511 */       int index = SessionTempFile.createNewTempFile(request);
/* 512 */       String fileName = "InventoryMoving";
/* 513 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 514 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 517 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 518 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/* 519 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.id"));
/* 520 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
/* 521 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.describe1"));
/* 522 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.describe2"));
/* 523 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.old_location.code"));
/* 524 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.new_location.code"));
/* 525 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.qty"));
/* 526 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.part.unit"));
/* 527 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.date"));
/* 528 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.is_sync"));
/* 529 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.is_sync_date"));
/* 530 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryMoving.remark"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 534 */               row.add(BeanHelper.getBeanPropertyValue(data, "lotSer.id"));
/* 535 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 536 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 537 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 538 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 539 */               row.add(BeanHelper.getBeanPropertyValue(data, "old_location.code"));
/* 540 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_location.code"));
/* 541 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 542 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/* 543 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 544 */               if (InventoryAction.this.isEnglish(request)) {
/* 545 */                 row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.engShortDescription"));
/*     */               } else {
/* 547 */                 row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.chnShortDescription"));
/* 548 */               }  row.add(BeanHelper.getBeanPropertyValue(data, "is_sync_date"));
/* 549 */               row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 554 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 556 */     if (queryForm.isFirstInit()) {
/* 557 */       queryForm.init(manager.getInventoryMovingListCount(conditions));
/*     */     } else {
/* 559 */       queryForm.init();
/*     */     } 
/* 561 */     List result = manager.getInventoryMovingList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 562 */         InventoryMovingQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 564 */     request.setAttribute("X_RESULTLIST", result);
/* 565 */     request.setAttribute("x_selType", Integer.valueOf(94));
/* 566 */     putEnumListToRequest(request);
/* 567 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward updateByBoxCout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
/* 571 */     response.setContentType("application/json");
/* 572 */     response.setCharacterEncoding("utf-8");
/* 573 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 574 */     manager.updateInventoryDetialCount();
/* 575 */     String res = "";
/* 576 */     res = "{\"msg\":\"订单冻结成功\"}";
/* 577 */     response.getWriter().print(res);
/* 578 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward occupiedDetial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 583 */     InventoryOccupiedDetialQueryForm queryForm = (InventoryOccupiedDetialQueryForm)form;
/* 584 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 585 */       queryForm.setOrder(LocationPartNumberQueryOrder.LOCATION_ID.getName());
/* 586 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 589 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 590 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 591 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 593 */     String exportType = queryForm.getExportType();
/* 594 */     if (StringUtils.isNotEmpty(exportType)) {
/* 595 */       List data = manager.getLocationPartNumberList(conditions, 0, -1, LocationPartNumberQueryOrder.getEnum(queryForm.getOrder()), 
/* 596 */           queryForm.isDescend());
/*     */       
/* 598 */       int index = SessionTempFile.createNewTempFile(request);
/* 599 */       String fileName = "LocationPartNumber";
/* 600 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 601 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 604 */               MessageResources messages = InventoryAction.this.getResources(request);
/* 605 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.location.code"));
/* 606 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.id"));
/* 607 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "DPI"));
/* 608 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe1"));
/* 609 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.describe2"));
/* 610 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.number"));
/* 611 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part_qty"));
/* 612 */               row.add("占用数量");
/* 613 */               row.add(messages.getMessage(InventoryAction.this.getLocale(request), "InventoryDetial.part.unit"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 617 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 618 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 619 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 620 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 621 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 622 */               row.add(BeanHelper.getBeanPropertyValue(data, "number"));
/* 623 */               row.add(BeanHelper.getBeanPropertyValue(data, "partQty"));
/* 624 */               row.add(BeanHelper.getBeanPropertyValue(data, "occupiedNumber"));
/* 625 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 630 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 632 */     if (queryForm.isFirstInit()) {
/* 633 */       queryForm.init(manager.getLocationPartNumberListCount(conditions));
/*     */     } else {
/* 635 */       queryForm.init();
/*     */     } 
/* 637 */     List result = manager.getLocationPartNumberList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 638 */         LocationPartNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */     
/* 640 */     request.setAttribute("X_RESULTLIST", result);
/* 641 */     request.setAttribute("x_selType", Integer.valueOf(144));
/* 642 */     putEnumListToRequest(request);
/* 643 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/inventory/InventoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */