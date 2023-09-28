/*     */ package com.aof.web.struts.action.wms;
/*     */ import com.aof.model.basic.UnplannedReasons;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.UnplannedOutboundStatus;
/*     */ import com.aof.model.metadata.WmsPlanToGoOutStatus;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOut;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
/*     */ import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
/*     */ import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryCondition;
/*     */ import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.admin.InventoryMovingManager;
import com.aof.service.basic.UnplannedReasonsManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.action.product.ProductGolineAction;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.WmsPlanToGoOutQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ import jxl.biff.DisplayFormat;
/*     */ import jxl.biff.FontRecord;
/*     */ import jxl.format.Alignment;
/*     */ import jxl.format.Border;
/*     */ import jxl.format.BorderLineStyle;
/*     */ import jxl.format.CellFormat;
/*     */ import jxl.format.Colour;
/*     */ import jxl.format.UnderlineStyle;
/*     */ import jxl.format.VerticalAlignment;
/*     */ import jxl.write.Label;
/*     */ import jxl.write.NumberFormat;
/*     */ import jxl.write.WritableCell;
/*     */ import jxl.write.WritableCellFormat;
/*     */ import jxl.write.WritableFont;
/*     */ import jxl.write.WritableSheet;
/*     */ import jxl.write.WritableWorkbook;
/*     */ import jxl.write.WriteException;
/*     */ import jxl.write.biff.RowsExceededException;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.upload.FormFile;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ public class WmsPlanToGoOutAction extends BaseAction2 {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  85 */     WmsPlanToGoOutQueryForm queryForm = (WmsPlanToGoOutQueryForm)form;
/*  86 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  87 */       queryForm.setOrder(WmsPlanToGoOutQueryOrder.ID.getName());
/*  88 */       queryForm.setDescend(false);
/*     */     } 
/*  90 */     WmsPlanToGoOutManager uwManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/*  91 */     Map conditions = constructQueryMap(queryForm);
/*  92 */     conditions.put(WmsPlanToGoOutQueryCondition.USER_EQ, getCurrentUser(request).getId());
/*     */     
/*  94 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/*  96 */     if (queryForm.isFirstInit()) {
/*  97 */       queryForm.init(uwManager.getWmsPlanToGoOutListCount(conditions));
/*     */     } else {
/*  99 */       queryForm.init();
/*     */     } 
/*     */ 
/*     */     
/* 103 */     String exportType = queryForm.getExportType();
/* 104 */     if (exportType != null && exportType.length() > 0) {
/* 105 */       List data = uwManager.getWmsPlanToGoOutList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), WmsPlanToGoOutQueryOrder.ID, queryForm.isDescend());
/* 106 */       String fileName = "WmsPlanToGoOut";
/* 107 */       int index = SessionTempFile.createNewTempFile(request);
/* 108 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 109 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 110 */           new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 113 */               MessageResources messages = WmsPlanToGoOutAction.this.getResources(request);
/* 114 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsPlanToGoOut.code"));
/* 115 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.applicat_name"));
/* 116 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.date"));
/* 117 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.qty"));
/* 118 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.actual_qty"));
/* 119 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.reason_code.instructions"));
/* 120 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.reason_code.expenses_course"));
/* 121 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.reason_code.department_cost"));
/* 122 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.status.chnShortDescription"));
/* 123 */               row.add(messages.getMessage(WmsPlanToGoOutAction.this.getLocale(request), "WmsUW.remark"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 127 */               WmsPlanToGoOut wmsPlanToGoOut = (WmsPlanToGoOut)data;
/* 128 */               row.add(BeanUtils.getProperty(data, "code"));
/* 129 */               row.add(BeanUtils.getProperty(data, "applicant.name"));
/* 130 */               row.add(BeanUtils.getProperty(data, "date"));
/* 131 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 132 */               row.add(BeanUtils.getProperty(data, "actual_qty"));
/* 133 */               row.add(BeanUtils.getProperty(data, "reason_code.instructions"));
/* 134 */               row.add(BeanUtils.getProperty(data, "reason_code.expenses_course"));
/* 135 */               row.add(BeanUtils.getProperty(data, "reason_code.department_cost"));
/* 136 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/* 137 */               row.add(BeanUtils.getProperty(data, "remark"));
/*     */             }
/*     */           });
/* 140 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 143 */     List<WmsPlanToGoOut> result = uwManager.getWmsPlanToGoOutList(conditions, 
/* 144 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 145 */         WmsPlanToGoOutQueryOrder.ID, true);
/*     */     
/* 147 */     request.setAttribute("x_status", PersistentEnum.getEnumList(WmsPlanToGoOutStatus.class));
/* 148 */     request.setAttribute("x_selType", Integer.valueOf(17));
/* 149 */     request.setAttribute("X_RESULTLIST", result);
/* 150 */     return mapping.findForward("page");
/*     */   }
/*     */   private File file;
/*     */   private String filename;
/*     */   
/*     */   public ActionForward listScann(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 156 */     WmsPlanToGoOutQueryForm queryForm = (WmsPlanToGoOutQueryForm)form;
/* 157 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 158 */       queryForm.setOrder(WmsPlanToGoOutQueryOrder.ID.getName());
/* 159 */       queryForm.setDescend(false);
/*     */     } 
/* 161 */     WmsPlanToGoOutManager uwManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 162 */     Map conditions = constructQueryMap(queryForm);
/* 163 */     conditions.put(WmsPlanToGoOutQueryCondition.USER_EQ, getCurrentUser(request).getId());
/*     */     
/* 165 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/* 167 */     if (queryForm.isFirstInit()) {
/* 168 */       queryForm.init(uwManager.getWmsPlanToGoOutListCount(conditions));
/*     */     } else {
/* 170 */       queryForm.init();
/*     */     } 
/* 172 */     List<WmsPlanToGoOut> result = uwManager.getWmsPlanToGoOutList(conditions, 
/* 173 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 174 */         WmsPlanToGoOutQueryOrder.ID, true);
/*     */     
/* 176 */     request.setAttribute("x_status", PersistentEnum.getEnumList(WmsPlanToGoOutStatus.class));
/* 177 */     request.setAttribute("x_selType", Integer.valueOf(20));
/* 178 */     request.setAttribute("X_RESULTLIST", result);
/* 179 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward getBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 184 */     WmsPlanToGoOutManager manager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 185 */     String id = request.getParameter("array");
/* 186 */     WmsPlanToGoOut planToGoOut = manager.getWmsPlanToGoOut(Integer.valueOf(Integer.parseInt(id)));
/* 187 */     List<WmsPlanToGoOutSub> items = manager.getWmsPlanToGoOutSubByMain(planToGoOut.getId());
/*     */     
/* 189 */     request.setAttribute("x_list", items);
/* 190 */     request.setAttribute("x_planToGoOut", planToGoOut);
/* 191 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward planToGoOutScann(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 196 */     WmsPlanToGoOutManager manager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 197 */     String[] arrays = request.getParameterValues("ids");
/* 198 */     String code = request.getParameter("code"); byte b; int i; String[] arrayOfString1;
/* 199 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 200 */       manager.scanningUnplannedOutbound(lot, code, getCurrentUser(request).getId().toString());
/*     */       b++; }
/*     */     
/* 203 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 209 */     if (!isBack(request)) {
/* 210 */       WmsPlanToGoOut wmsPart = new WmsPlanToGoOut();
/* 211 */       BeanForm wmsPartForm = (BeanForm)getForm("/insertWmsPlanToGoOut", request);
/* 212 */       wmsPartForm.populate(wmsPart, "to_form");
/*     */     } 
/*     */     
/* 215 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/* 216 */     request.setAttribute("x_newdate", format.format(new Date()));
/* 217 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 218 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 224 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 225 */     UnplannedReasonsManager manager = ServiceLocator.getUnplannedReasonsManager(request);
/*     */     
/* 227 */     String reason_code = request.getParameter("reason_code");
/* 228 */     UnplannedReasons reasons = null;
/* 229 */     if (reason_code != null && !reason_code.equals("")) {
/* 230 */       reasons = manager.getUnplannedReasons(Integer.valueOf(Integer.parseInt(reason_code)));
/*     */     }
/*     */     
/* 233 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 234 */     WmsPlanToGoOut wmsPlanToGoOut = new WmsPlanToGoOut();
/* 235 */     purchaseOrderForm.populate(wmsPlanToGoOut, "to_bean");
/*     */     
/* 237 */     wmsPlanToGoOut.setStatus(UnplannedOutboundStatus.WAIT);
/* 238 */     wmsPlanToGoOut.setDate(new Date());
/* 239 */     wmsPlanToGoOut.setApplicant(getCurrentUser(request));
/* 240 */     wmsPlanToGoOut.setActual_qty(new BigDecimal(0));
/* 241 */     wmsPlanToGoOut.setReason_code(reasons);
/* 242 */     goOutManager.insertWmsPlanToGoOut(wmsPlanToGoOut);
/* 243 */     wmsPlanToGoOut = goOutManager.getWmsPlanToGoOut(wmsPlanToGoOut.getId());
/*     */     
/* 245 */     String strList = request.getParameter("strList");
/* 246 */     goOutManager.insertWmsPlanToGoOutItemByMain(wmsPlanToGoOut, strList);
/*     */     
/* 248 */     List<WmsPlanToGoOutItem> sGoOutItems = goOutManager.getWmsPlanToGoOutItemByMain(wmsPlanToGoOut.getId());
/* 249 */     WmsPlanToGoOutItem wpto = sGoOutItems.get(0);
/* 250 */     BigDecimal qty = wpto.getQty();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     request.setAttribute("X_OBJECT", wmsPlanToGoOut);
/* 260 */     request.setAttribute("X_ROWPAGE", "wmsComprehensive/unplannedOutbound/row.jsp");
/* 261 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward lookForStocksByPartAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 267 */     response.setContentType("text/json");
/* 269 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 271 */     String part = request.getParameter("part");
/* 272 */     WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
/* 273 */     WmsPart wmsPart = manager.getWmsPart(part);
/* 274 */     InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
/* 275 */     InventoryDetial total = inventoryManager.getInventoryTotalByPart(wmsPart.getId());
/* 276 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 277 */     if (total != null) {
/* 278 */       map.put("amount", total.getPart_qty());
/*     */     } else {
/* 280 */       map.put("amount", Integer.valueOf(0));
/*     */     } 
/* 282 */     JSONObject jo = JSONObject.fromObject(map, cfg);
/* 283 */     response.getWriter().print(jo);
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 290 */     WmsPlanToGoOutManager manager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 291 */     WmsPlanToGoOut toGoOut = getWmsPlantGoOut(request);
/* 292 */     List<WmsPlanToGoOutItem> goOutItems = manager.getWmsPlanToGoOutItemByMain(toGoOut.getId());
/* 293 */     List<WmsPlanToGoOutSub> subList = manager.getWmsPlanToGoOutSub(goOutItems);
/*     */     
/* 295 */     request.setAttribute("x_subList", subList);
/* 296 */     request.setAttribute("x_items", goOutItems);
/* 297 */     request.setAttribute("x_warehousing", toGoOut);
/* 298 */     request.setAttribute("x_pageHead2", Integer.valueOf(2));
/* 299 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 305 */     int num = 0;
/* 306 */     Box box = null;
/* 311 */     WmsPlanToGoOutManager planToGoOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 312 */     WmsPlanToGoOut planToGoOut = getWmsPlantGoOut(request);
/*     */     
/* 314 */     planToGoOut.setStatus(UnplannedOutboundStatus.HASTHE);
/* 315 */     planToGoOutManager.updateWmsPlanToGoOut(planToGoOut);
/* 316 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 317 */     String[] wmsPlantgooutItem = request.getParameterValues("wmsPlantgooutItem");
/* 318 */     String[] arrayList = array_unique(wmsPlantgooutItem); byte b; int i; String[] arrayOfString1;
/* 319 */     for (i = (arrayOfString1 = arrayList).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 320 */       int id = Integer.parseInt(string);
/* 321 */       box = boxManager.getBox(Integer.valueOf(id));
/* 322 */       box.setStatus(BoxStatus.WMSPLANTOGOOUT);
/* 323 */       box.setOut_date(new Date());
/* 324 */       boxManager.updateBox(box);
/* 325 */       String str = box.getNumber().toString();
/* 326 */       int j = str.indexOf(".");
/* 327 */       String strnum = str.substring(0, j);
/* 328 */       int number = Integer.parseInt(strnum);
/* 329 */       num += number;
/*     */       b++; }
/*     */     
/* 332 */     planToGoOutManager.updateWmsPlanToGoOutByBox(planToGoOut, arrayList);
/* 354 */     return new ActionForward("/editWmsPlanToGoOut.do?id=" + planToGoOut.getId(), true);
/*     */   }
/*     */   public String[] array_unique(String[] a) {
/* 357 */     Map<Object, Object> map = new HashMap<Object, Object>(); byte b; int i; String[] arrayOfString;
/* 358 */     for (i = (arrayOfString = a).length, b = 0; b < i; ) { String string = arrayOfString[b];
/* 359 */       map.put(string, string); b++; }
/*     */     
/* 361 */     List<String> list = new ArrayList<String>();
/* 362 */     Iterator<Object> iterator = map.keySet().iterator();
/* 363 */     while (iterator.hasNext()) {
/* 364 */       String value = (String) iterator.next();
/* 365 */       list.add(value);
/*     */     } 
/* 367 */     return list.<String>toArray(new String[list.size()]);
/*     */   }
/*     */
/*     */   
/*     */   public ActionForward printOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 381 */     WmsPlanToGoOutManager manager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 382 */     WmsPlanToGoOut planToGoOut = getWmsPlantGoOut(request);
/* 383 */     List<WmsPlanToGoOutItem> items = manager.getWmsPlanToGoOutItemByMain(planToGoOut.getId());
/* 384 */     List<WmsPlanToGoOutSub> subs = manager.getWmsPlanToGoOutSubByMain(planToGoOut.getId());
/*     */     
/* 386 */     request.setAttribute("x_planToGoOut", planToGoOut);
/* 387 */     request.setAttribute("x_items", items);
/* 388 */     request.setAttribute("x_subs", subs);
/* 389 */     request.setAttribute("path", request.getContextPath());
/* 390 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insertItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 396 */     String[] str = request.getParameterValues("wmsPlantgooutItem");
/* 397 */     String wmsGoOutId = request.getParameter("wmsGoOut_id");
/* 398 */     WmsPlanToGoOutManager outManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 399 */     WmsPlanToGoOut toGoOut = outManager.getWmsPlanToGoOut(Integer.valueOf(Integer.parseInt(wmsGoOutId)));
/* 400 */     outManager.updateWmsPlanToGoOutItemByList(str, toGoOut);
/*     */     
/* 402 */     return new ActionForward("/editWmsPlantGoOutItem.do?id=" + toGoOut.getId(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printPlantGoOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 408 */     WmsPlanToGoOut toGoOut = getWmsPlantGoOut(request);
/* 409 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 410 */     List<WmsPlanToGoOutItem> goOutItems = goOutManager.getWmsPlanToGoOutItemByMain(toGoOut.getId());
/* 411 */     List<WmsPlanToGoOutSub> subList = goOutManager.getWmsPlanToGoOutSub(goOutItems);
/*     */     
/* 413 */     request.setAttribute("x_items", goOutItems);
/* 414 */     request.setAttribute("x_toGoOut", toGoOut);
/* 415 */     request.setAttribute("x_subList", subList);
/* 416 */     request.setAttribute("path", request.getContextPath());
/*     */     
/* 418 */     if (request.getParameter("type").equals("1")) {
/* 419 */       return mapping.findForward("page");
/*     */     }
/* 421 */     return mapping.findForward("page2");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printPlantGoOutView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 427 */     WmsPlanToGoOut toGoOut = getWmsPlantGoOut(request);
/* 428 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 429 */     List<WmsPlanToGoOutItem> goOutItems = goOutManager.getWmsPlanToGoOutItemByMain(toGoOut.getId());
/*     */     
/* 431 */     request.setAttribute("x_items", goOutItems);
/* 432 */     request.setAttribute("x_toGoOut", toGoOut);
/* 433 */     request.setAttribute("path", request.getContextPath());
/* 434 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private WmsPlanToGoOut getWmsPlantGoOut(HttpServletRequest request) throws Exception {
/* 438 */     String id = request.getParameter("id");
/* 439 */     WmsPlanToGoOutManager outManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 440 */     WmsPlanToGoOut warehousing = outManager.getWmsPlanToGoOut(Integer.valueOf(Integer.parseInt(id)));
/* 441 */     if (warehousing == null)
/* 442 */       throw new ActionException("wmsPlanToGoOut.notFound", id); 
/* 443 */     return warehousing;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward plantGoOutItemByBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 448 */     response.setContentType("text/json");
/* 450 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 452 */     String str = request.getParameter("ids");
/* 453 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 454 */     List<Map> list = boxManager.updateSelectBoxById(str);
/* 455 */     JSONArray jo = JSONArray.fromObject(list, cfg);
/* 456 */     response.getWriter().print(jo);
/* 457 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward checkByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 463 */     response.setContentType("text/json");
/* 465 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 467 */     String arrayList = request.getParameter("arrayList");
/* 468 */     String wmsGoOutid = request.getParameter("wmsGoOutid");
/* 469 */     String type = request.getParameter("type");
/* 470 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 471 */     boolean returnValue = goOutManager.checkWmsPlanToGoOutItemByAmount(wmsGoOutid, arrayList, type);
/*     */     
/* 473 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(returnValue), cfg);
/* 474 */     response.getWriter().print(jo);
/* 475 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 482 */     return new ActionForward("/listWmsUnplannedWarehousing.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward recommendLotset(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 488 */     response.setContentType("text/json");
/* 490 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 492 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 493 */     String id = request.getParameter("id");
/* 494 */     WmsPlanToGoOut wmsPlanToGoOut = goOutManager.getWmsPlanToGoOut(Integer.valueOf(Integer.parseInt(id)));
/* 495 */     List<WmsPlanToGoOutItem> items = goOutManager.getWmsPlanToGoOutItemByMain(wmsPlanToGoOut.getId());
/*     */     
/* 497 */     List<Map> boxs = goOutManager.getRecommendLotset(items);
/*     */     
/* 499 */     JSONArray jo = JSONArray.fromObject(boxs, cfg);
/* 500 */     response.getWriter().print(jo);
/* 501 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward createImportTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 508 */     return createImportTemplate(mapping, form, request, response, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward createImportTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Boolean isforecast) throws Exception {
/* 514 */     List<Character> chars = new ArrayList();
/* 515 */     int charsDouble = 0;
/* 516 */     for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
/* 517 */       chars.add(Character.valueOf(i));
/*     */     }
/*     */     try {
/* 520 */       DateFormatter format = new DateFormatter(Date.class, 
/* 521 */           "HHmmssSS");
/* 522 */       String name = String.valueOf(format.format(new Date())) + ".xls";
/* 523 */       if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
/* 524 */         (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
/*     */       }
/* 526 */       File f = new File(String.valueOf(getSys_url()) + "temp/SupplierImportTemplate" + 
/* 527 */           name);
/* 528 */       while (f.exists()) {
/* 529 */         Thread.sleep(100L);
/* 530 */         name = String.valueOf(format.format(new Date())) + ".xls";
/* 531 */         f = new File(String.valueOf(getSys_url()) + "temp/SupplierImportTemplate" + 
/* 532 */             name);
/*     */       } 
/* 534 */       WritableWorkbook wwb = Workbook.createWorkbook(f);
/* 535 */       WritableSheet ws = wwb.createSheet("�ƻ�����⵼��ģ��", 0);
/*     */       
/* 537 */       Label lable = null;
/* 538 */       WritableSheet sheet = wwb.getSheet(0);
/* 539 */       sheet.setColumnView(0, 15);
/* 540 */       sheet.setColumnView(1, 20);
/* 541 */       NumberFormat nf = new NumberFormat("#.###");
/* 542 */       WritableFont host = new WritableFont(WritableFont.ARIAL, 
/* 543 */           15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 544 */           Colour.CORAL);
/* 545 */       WritableFont red = new WritableFont(WritableFont.ARIAL, 
/* 546 */           10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 547 */           Colour.RED);
/*     */       
/* 549 */       WritableCellFormat hosts = new WritableCellFormat(host);
/* 550 */       hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 551 */       hosts.setWrap(true);
/* 552 */       hosts.setAlignment(Alignment.CENTRE);
/* 553 */       hosts.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 555 */       WritableCellFormat title = new WritableCellFormat();
/* 556 */       title.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 557 */       title.setBackground(Colour.ORANGE);
/* 558 */       title.setWrap(true);
/* 559 */       title.setAlignment(Alignment.CENTRE);
/* 560 */       title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */ 
/*     */       
/* 563 */       WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
/* 564 */       functions.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 565 */       functions.setWrap(true);
/* 566 */       functions.setAlignment(Alignment.RIGHT);
/* 567 */       functions.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 569 */       WritableCellFormat cellFormat = new WritableCellFormat();
/* 570 */       cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 571 */       cellFormat.setWrap(true);
/* 572 */       cellFormat.setAlignment(Alignment.CENTRE);
/* 573 */       cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 575 */       WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
/* 576 */       reds.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 577 */       reds.setFont((FontRecord)red);
/* 578 */       reds.setWrap(true);
/* 579 */       reds.setAlignment(Alignment.CENTRE);
/* 580 */       reds.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 582 */       ws = createheadtoImport(ws, lable, title, hosts, sheet, isforecast);
/*     */       
/* 584 */       WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
/* 585 */       format3.setWrap(true);
/* 586 */       format3.setAlignment(Alignment.RIGHT);
/* 587 */       format3.setVerticalAlignment(VerticalAlignment.CENTRE);
/* 588 */       format3.setBorder(Border.ALL, BorderLineStyle.THIN);
/*     */       
/* 590 */       wwb.write();
/* 591 */       wwb.close();
/* 592 */       response.sendRedirect("temp/SupplierImportTemplate" + name);
/* 593 */       return mapping.findForward("page");
/* 594 */     } catch (Exception e) {
/* 595 */       throw new ActionException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableSheet createheadtoImport(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet, Boolean isforecast) throws RowsExceededException, WriteException {
/* 603 */     List<String> arrhead = new ArrayList<String>();
/*     */     
/* 605 */     WritableCellFormat mainBody = new WritableCellFormat();
/* 606 */     mainBody.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 607 */     mainBody.setBackground(Colour.WHITE);
/* 608 */     mainBody.setWrap(true);
/* 609 */     mainBody.setAlignment(Alignment.RIGHT);
/* 610 */     mainBody.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */     
/* 612 */     lable = new Label(0, 0, "���Ϻ�", (CellFormat)cellFormat);
/* 613 */     ws.addCell((WritableCell)lable);
/* 614 */     lable = new Label(1, 0, "��������", (CellFormat)cellFormat);
/* 615 */     ws.addCell((WritableCell)lable);
/*     */     
/* 617 */     sheet.mergeCells(0, 0, 0, 0);
/* 618 */     return ws;
/*     */   }
/*     */   
/*     */   private String getSys_url() {
/* 622 */     String strClassName = getClass().getName();
/* 623 */     String strPackageName = "";
/* 624 */     if (getClass().getPackage() != null) {
/* 625 */       strPackageName = getClass().getPackage().getName();
/*     */     }
/* 627 */     String strClassFileName = "";
/* 628 */     if (!"".equals(strPackageName)) {
/* 629 */       strClassFileName = strClassName.substring(
/* 630 */           strPackageName.length() + 1, strClassName.length());
/*     */     } else {
/* 632 */       strClassFileName = strClassName;
/*     */     } 
/* 634 */     URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
/* 635 */     String strURL = url.toString();
/* 636 */     strURL = strURL.substring(strURL.indexOf('/') + 1, 
/* 637 */         strURL.lastIndexOf('/'));
/* 638 */     return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward showImportNext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 645 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward upLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 652 */     WmsPlanToGoOutQueryForm uFrom = (WmsPlanToGoOutQueryForm)form;
/* 653 */     String filenames = "";
/* 654 */     String wmsuwid = request.getParameter("wmsuwid");
/* 655 */     PrintWriter out = null;
/*     */     try {
/* 657 */       this.file = new File(this.servlet.getServletContext().getRealPath(
/* 658 */             "/temp/importfile/"));
/* 659 */       if (!this.file.exists()) {
/* 660 */         this.file.mkdir();
/*     */       }
/* 662 */       request.setAttribute("filenames", String.valueOf(this.file.getPath()) + "\\" + uFrom.getMyFile().getFileName());
/* 664 */       out = response.getWriter();
/* 665 */       saveFile(uFrom.getMyFile(), this.file);
/* 666 */       request.setAttribute("x_wmsuwid", wmsuwid);
/* 667 */     } catch (Exception e) {
/* 668 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 671 */     String type = request.getParameter("type");
/* 672 */     request.setAttribute("x_type", type);
/* 673 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveFile(FormFile formFile, File file) throws Exception {
/*     */     try {
/* 679 */       String path = file.getPath();
/*     */       
/* 681 */       InputStream in = formFile.getInputStream();
/*     */       
/* 683 */       FileOutputStream fout = new FileOutputStream(String.valueOf(path) + "\\" + formFile.getFileName());
/* 684 */       this.filename = String.valueOf(path) + "\\" + formFile.getFileName();
/*     */       
/* 686 */       byte[] buffer = new byte[8192];
/* 687 */       int count = 0;
/*     */       
/* 689 */       while ((count = in.read(buffer)) > 0) {
/* 690 */         fout.write(buffer, 0, count);
/*     */       }
/* 692 */       fout.close();
/* 693 */       formFile.destroy();
/* 694 */     } catch (Exception e) {
/* 695 */       throw new ActionException("���ļ�" + this.filename + "�޷��ϴ�");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward importeWmsPlanToGoOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
/* 703 */     WmsPlanToGoOutManager manager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 704 */     List<String> error = new ArrayList<String>();
/* 705 */     Workbook wb = null;
/* 706 */     String filenames = "";
/*     */     try {
/* 708 */       filenames = request.getAttribute("filenames").toString();
/* 709 */       wb = Workbook.getWorkbook(new File(filenames));
/* 710 */     } catch (Exception e) {
/* 711 */       throw new ActionException("���ļ�" + filenames + "������");
/*     */     } 
/* 713 */     Sheet[] sheet = wb.getSheets();
/* 714 */     List<Map> listMap = null;
/* 715 */     if (sheet != null && sheet.length > 0) {
/* 716 */       listMap = manager.getImportWmsPlanToGoOutList(sheet);
/*     */     }
/* 718 */     if (error.size() != 0) {
/* 719 */       request.setAttribute("showerror", Boolean.valueOf(true));
/* 720 */       request.setAttribute("error", error);
/*     */     } else {
/* 722 */       request.setAttribute("showerror", Boolean.valueOf(false));
/*     */     } 
/* 724 */     if (error.size() != 0) {
/* 725 */       throw new ActionException("���ļ�" + filenames + "������" + (String)error.get(0));
/*     */     }
/*     */     
/* 728 */     request.setAttribute("X_RESULTLIST", listMap);
/* 729 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(WmsPlanToGoOutQueryForm queryForm) {
/* 733 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 734 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 735 */     String code = queryForm.getCode();
/* 736 */     String deliver = queryForm.getDeliver();
/* 737 */     String datetime = queryForm.getDatetime();
/* 738 */     String endtime = queryForm.getEndtime();
/* 739 */     String status = queryForm.getStatus();
/* 740 */     String type = queryForm.getType();
/*     */     
/* 742 */     if (code != null && !code.equals("")) {
/* 743 */       conditions.put(WmsPlanToGoOutQueryCondition.CODE_EQ, code);
/*     */     }
/* 745 */     if (type != null && !type.equals("")) {
/* 746 */       conditions.put(WmsPlanToGoOutQueryCondition.TYPE_EQ, type);
/*     */     }
/* 748 */     if (deliver != null && !deliver.equals("")) {
/* 749 */       conditions.put(WmsPlanToGoOutQueryCondition.DELEIVER_EQ, deliver);
/*     */     }
/* 751 */     if (deliver != null && !deliver.equals("")) {
/* 752 */       conditions.put(WmsPlanToGoOutQueryCondition.DEATETIME_EQ, datetime);
/*     */     }
/* 754 */     if (endtime != null && !endtime.equals("")) {
/* 755 */       conditions.put(WmsPlanToGoOutQueryCondition.ENDTIME_EQ, endtime);
/*     */     }
/* 757 */     if (status != null && !status.equals("")) {
/* 758 */       conditions.put(WmsPlanToGoOutQueryCondition.STATUS_EQ, status);
/*     */     }
/*     */     
/* 761 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/wms/WmsPlanToGoOutAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */