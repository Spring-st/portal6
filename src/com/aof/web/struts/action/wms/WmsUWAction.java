/*     */ package com.aof.web.struts.action.wms;
/*     */ import com.aof.model.basic.UnplannedReasons;
/*     */ import com.aof.model.metadata.UnplannedWarehousingStatus;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousing;
/*     */ import com.aof.model.plantWarehouse.WmsUnplannedWarehousingItem;
/*     */ import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryCondition;
/*     */ import com.aof.model.plantWarehouse.query.WmsUnplannedWarehousingQueryOrder;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.service.basic.UnplannedReasonsManager;
/*     */ import com.aof.service.plantWarehouse.WmsUWManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.WmsUnplannedWarehousingQueryForm;
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
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ import jxl.biff.DisplayFormat;
/*     */ import jxl.biff.FontRecord;
import jxl.format.Alignment;
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
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.upload.FormFile;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ public class WmsUWAction extends BaseAction2 {
/*     */   private File file;
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  72 */     WmsUnplannedWarehousingQueryForm queryForm = (WmsUnplannedWarehousingQueryForm)form;
/*  73 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  74 */       queryForm.setOrder(WmsUnplannedWarehousingQueryOrder.ID.getName());
/*  75 */       queryForm.setDescend(true);
/*     */     } 
/*  77 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/*  78 */     Map conditions = constructQueryMap(queryForm);
/*  79 */     conditions.put(WmsUnplannedWarehousingQueryCondition.USER_EQ, getCurrentUser(request).getId());
/*     */     
/*  81 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/*  83 */     if (queryForm.isFirstInit()) {
/*  84 */       queryForm.init(uwManager.getWmsUnplannedWarehousingListCount(conditions));
/*     */     } else {
/*  86 */       queryForm.init();
/*     */     } 
/*     */     
/*  89 */     String exportType = queryForm.getExportType();
/*  90 */     if (exportType != null && exportType.length() > 0) {
/*  91 */       List data = uwManager.getWmsUnplannedWarehousingList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), WmsUnplannedWarehousingQueryOrder.ID, queryForm.isDescend());
/*  92 */       int index = SessionTempFile.createNewTempFile(request);
/*  93 */       String fileName = "WmsUW";
/*  94 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  95 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  96 */           new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  99 */               MessageResources messages = WmsUWAction.this.getResources(request);
/* 100 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.code"));
/* 101 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.applicat_name"));
/* 102 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.date"));
/* 103 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.qty"));
/* 104 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.actual_qty"));
/* 105 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.reason_code.instructions"));
/* 106 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.reason_code.expenses_course"));
/* 107 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.reason_code.department_cost"));
/* 108 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.status.chnShortDescription"));
/* 109 */               row.add(messages.getMessage(WmsUWAction.this.getLocale(request), "WmsUW.remark"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 114 */               WmsUnplannedWarehousing cusPlan = (WmsUnplannedWarehousing)data;
/* 115 */               row.add(BeanUtils.getProperty(data, "code"));
/* 116 */               row.add(BeanUtils.getProperty(data, "applicant.name"));
/* 117 */               row.add(BeanUtils.getProperty(data, "date"));
/* 118 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 119 */               row.add(BeanUtils.getProperty(data, "actual_qty"));
/* 120 */               row.add(BeanUtils.getProperty(data, "reason_code.instructions"));
/* 121 */               row.add(BeanUtils.getProperty(data, "reason_code.expenses_course"));
/* 122 */               row.add(BeanUtils.getProperty(data, "reason_code.department_cost"));
/* 123 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/* 124 */               row.add(BeanUtils.getProperty(data, "remark"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 129 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 134 */     List<WmsUnplannedWarehousing> result = uwManager.getWmsUnplannedWarehousingList(conditions, 
/* 135 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 136 */         WmsUnplannedWarehousingQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */     
/* 139 */     request.setAttribute("X_RESULTLIST", result);
/* 140 */     request.setAttribute("x_selType", Integer.valueOf(16));
/* 141 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private String filename;
/*     */   
/*     */   public ActionForward listScann(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 147 */     WmsUnplannedWarehousingQueryForm queryForm = (WmsUnplannedWarehousingQueryForm)form;
/* 148 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 149 */       queryForm.setOrder(WmsUnplannedWarehousingQueryOrder.ID.getName());
/* 150 */       queryForm.setDescend(true);
/*     */     } 
/* 152 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 153 */     Map conditions = constructQueryMap(queryForm);
/* 154 */     conditions.put(WmsUnplannedWarehousingQueryCondition.USER_EQ, getCurrentUser(request).getId());
/*     */     
/* 156 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 158 */     if (queryForm.isFirstInit()) {
/* 159 */       queryForm.init(uwManager.getWmsUnplannedWarehousingListCount(conditions));
/*     */     } else {
/* 161 */       queryForm.init();
/*     */     } 
/* 163 */     List<WmsUnplannedWarehousing> result = uwManager.getWmsUnplannedWarehousingList(conditions, 
/* 164 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 165 */         WmsUnplannedWarehousingQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */     
/* 168 */     request.setAttribute("X_RESULTLIST", result);
/* 169 */     request.setAttribute("x_selType", Integer.valueOf(19));
/* 170 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 176 */     if (!isBack(request)) {
/* 177 */       WmsUnplannedWarehousing wmsPart = new WmsUnplannedWarehousing();
/* 178 */       BeanForm wmsPartForm = (BeanForm)getForm("/insertWmsUnplannedWarehousing", request);
/* 179 */       wmsPartForm.populate(wmsPart, "to_form");
/*     */     } 
/*     */     
/* 182 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 187 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 188 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 189 */     UnplannedReasonsManager manager = ServiceLocator.getUnplannedReasonsManager(request);
/* 190 */     String reason_code = request.getParameter("reason_code");
/* 191 */     UnplannedReasons reasons = null;
/* 192 */     if (reason_code != null && !reason_code.equals("")) {
/* 193 */       reasons = manager.getUnplannedReasons(Integer.valueOf(Integer.parseInt(reason_code)));
/*     */     }
/*     */     
/* 196 */     WmsUnplannedWarehousing pwom = new WmsUnplannedWarehousing();
/* 197 */     purchaseOrderForm.populate(pwom, "to_bean");
/* 198 */     pwom.setApplicant(getCurrentUser(request));
/* 199 */     pwom.setStatus(UnplannedWarehousingStatus.WAIT);
/* 200 */     pwom.setReason_code(reasons);
/* 201 */     uwManager.insertWmsUnplannedWarehousing(pwom);
/*     */     
/* 203 */     request.setAttribute("X_OBJECT", uwManager.getWmsUnplannedWarehousing(pwom.getId()));
/* 204 */     request.setAttribute("X_ROWPAGE", "wmsComprehensive/unplannedWarehousing/row.jsp");
/* 205 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 211 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 212 */     WmsUnplannedWarehousing warehousing = getWmsUW(request);
/* 213 */     List<WmsUnplannedWarehousingItem> list = uwManager.getWmsUnplannedWarehousingItemByMain(warehousing.getId());
/*     */     
/* 215 */     request.setAttribute("x_items", list);
/* 216 */     request.setAttribute("x_warehousing", warehousing);
/* 217 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 223 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 224 */     WmsUnplannedWarehousing warehousing = getWmsUW(request);
/*     */     
/* 226 */     List<WmsUnplannedWarehousingItem> list = uwManager.getWmsUnplannedWarehousingItemByMain(warehousing.getId());
/*     */ 
/*     */     
/* 229 */     request.setAttribute("x_warehousing", warehousing);
/* 230 */     request.setAttribute("x_items", list);
/* 231 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 237 */     WmsUWManager manager = ServiceLocator.getWmsUWManager(request);
/* 238 */     String array = request.getParameter("array");
/* 239 */     String arrayPo = request.getParameter("array_po");
/* 240 */     String id = request.getParameter("wmsUWId");
/*     */     
/* 242 */     return new ActionForward("/listWmsUnplannedWarehousing.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward deleteWmsUWByBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 247 */     WmsUWManager manager = ServiceLocator.getWmsUWManager(request);
/* 248 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 249 */     String boxId = request.getParameter("boxId");
/* 250 */     WmsUnplannedWarehousing warehousing = getWmsUW(request);
/* 251 */     manager.deleteWmsUnplannedWarehousingItem(warehousing, Integer.valueOf(Integer.parseInt(boxId)));
/* 252 */     return new ActionForward("/editWmsUnplannedWarehousing.do?id=" + warehousing.getId(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 262 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 263 */     WmsUnplannedWarehousing warehousing = getWmsUW(request);
/*     */     
/* 265 */     uwManager.updateWmsUnplannedWarehousingByStatusToConfirm(warehousing);
/*     */     
/* 267 */     return new ActionForward("editWmsUnplannedWarehousing.do?id=" + warehousing.getId(), true);
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
/*     */   public ActionForward getBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 281 */     WmsUWManager manager = ServiceLocator.getWmsUWManager(request);
/* 282 */     String id = request.getParameter("array");
/* 283 */     WmsUnplannedWarehousing warehousing = manager.getWmsUnplannedWarehousing(Integer.valueOf(Integer.parseInt(id)));
/* 284 */     List<WmsUnplannedWarehousingItem> items = manager.getBoxByWmsUnplannedWarehousing(warehousing.getId());
/*     */     
/* 286 */     request.setAttribute("x_list", items);
/* 287 */     request.setAttribute("x_warehousing", warehousing);
/* 288 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward unplannedWarehousingScann(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 294 */     WmsUWManager manager = ServiceLocator.getWmsUWManager(request);
/* 295 */     String[] arrays = request.getParameterValues("ids");
/* 296 */     String code = request.getParameter("code");
/* 297 */     String location = request.getParameter("locationCode1"); byte b; int i; String[] arrayOfString1;
/* 298 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 299 */       manager.scanningWmsUWPutInStorage(code, lot, location, 
/* 300 */           getCurrentUser(request).getId().toString());
/*     */       b++; }
/*     */     
/* 303 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 309 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 310 */     String str = request.getParameter("str");
/* 311 */     String[] arrays = str.split(",");
/* 312 */     List<Box> list = new ArrayList<Box>(); byte b; int i; String[] arrayOfString1;
/* 313 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 314 */       Box box = manager.getBoxBylotSer2(id);
/* 315 */       list.add(box);
/*     */       b++; }
/*     */     
/* 318 */     request.setAttribute("x_listMap", list);
/* 319 */     request.setAttribute("path", request.getContextPath());
/* 320 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 326 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 327 */     String id = request.getParameter("id");
/* 328 */     WmsUnplannedWarehousing warehousing = uwManager.getWmsUnplannedWarehousing(Integer.valueOf(Integer.parseInt(id)));
/* 329 */     List<WmsUnplannedWarehousingItem> items = uwManager.getBoxByWmsUnplannedWarehousing(warehousing.getId());
/*     */     
/* 331 */     request.setAttribute("x_toGoOut", warehousing);
/* 332 */     request.setAttribute("x_items", items);
/* 333 */     request.setAttribute("path", request.getContextPath());
/* 334 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private WmsUnplannedWarehousing getWmsUW(HttpServletRequest request) throws Exception {
/* 338 */     String id = request.getParameter("id");
/* 339 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 340 */     WmsUnplannedWarehousing warehousing = uwManager.getWmsUnplannedWarehousing(Integer.valueOf(Integer.parseInt(id)));
/* 341 */     if (warehousing == null)
/* 342 */       throw new ActionException("wmsUnplannedWarehousing.notFound", id); 
/* 343 */     return warehousing;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward printLotList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 348 */     WmsUnplannedWarehousing warehousing = getWmsUW(request);
/* 349 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 350 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 351 */     String str = request.getParameter("wmsUwItem");
/* 352 */     String[] wmsUwItems = str.split(",");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward createImportTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 365 */     List<Character> chars = new ArrayList();
/* 366 */     int charsDouble = 0;
/* 367 */     for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
/* 368 */       chars.add(Character.valueOf(i));
/*     */     }
/*     */     
/*     */     try {
/* 372 */       DateFormatter format = new DateFormatter(Date.class, 
/* 373 */           "HHmmssSS");
/* 374 */       String name = String.valueOf(format.format(new Date())) + ".xls";
/* 375 */       if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
/* 376 */         (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
/*     */       }
/* 378 */       File f = new File(String.valueOf(getSys_url()) + "temp/SupplierImportTemplate" + 
/* 379 */           name);
/* 380 */       while (f.exists()) {
/* 381 */         Thread.sleep(100L);
/* 382 */         name = String.valueOf(format.format(new Date())) + ".xls";
/* 383 */         f = new File(String.valueOf(getSys_url()) + "temp/SupplierImportTemplate" + 
/* 384 */             name);
/*     */       } 
/* 386 */       WritableWorkbook wwb = Workbook.createWorkbook(f);
/* 387 */       WritableSheet ws = wwb.createSheet("非计划导入模版", 0);
/*     */       
/* 389 */       Label lable = null;
/* 390 */       WritableSheet sheet = wwb.getSheet(0);
/* 391 */       sheet.setColumnView(0, 15);
/* 392 */       sheet.setColumnView(1, 20);
/* 393 */       NumberFormat nf = new NumberFormat("#.###");
/* 394 */       WritableFont host = new WritableFont(WritableFont.ARIAL, 
/* 395 */           15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 396 */           Colour.CORAL);
/* 397 */       WritableFont red = new WritableFont(WritableFont.ARIAL, 
/* 398 */           10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 399 */           Colour.RED);
/*     */       
/* 401 */       WritableCellFormat hosts = new WritableCellFormat(host);
/* 402 */       hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 403 */       hosts.setWrap(true);
/* 404 */       hosts.setAlignment(Alignment.CENTRE);
/* 405 */       hosts.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 407 */       WritableCellFormat title = new WritableCellFormat();
/* 408 */       title.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 409 */       title.setBackground(Colour.ORANGE);
/* 410 */       title.setWrap(true);
/* 411 */       title.setAlignment(Alignment.CENTRE);
/* 412 */       title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */ 
/*     */       
/* 415 */       WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
/* 416 */       functions.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 417 */       functions.setWrap(true);
/* 418 */       functions.setAlignment(Alignment.RIGHT);
/* 419 */       functions.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 421 */       WritableCellFormat cellFormat = new WritableCellFormat();
/* 422 */       cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 423 */       cellFormat.setWrap(true);
/* 424 */       cellFormat.setAlignment(Alignment.CENTRE);
/* 425 */       cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 427 */       WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
/* 428 */       reds.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 429 */       reds.setFont((FontRecord) red);
/* 430 */       reds.setWrap(true);
/* 431 */       reds.setAlignment(Alignment.CENTRE);
/* 432 */       reds.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 434 */       ws = createheadtoImportNew(ws, lable, title, hosts, sheet);
/*     */       
/* 436 */       WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
/* 437 */       format3.setWrap(true);
/* 438 */       format3.setAlignment(Alignment.RIGHT);
/* 439 */       format3.setVerticalAlignment(VerticalAlignment.CENTRE);
/* 440 */       format3.setBorder(Border.ALL, BorderLineStyle.THIN);
/*     */       
/* 442 */       wwb.write();
/* 443 */       wwb.close();
/* 444 */       response.sendRedirect("temp/SupplierImportTemplate" + name);
/* 445 */       return mapping.findForward("page");
/* 446 */     } catch (Exception e) {
/* 447 */       throw new ActionException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableSheet createheadtoImportNew(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet) throws RowsExceededException, WriteException {
/* 455 */     List<String> arrhead = new ArrayList<String>();
/*     */     
/* 457 */     for (int s = 0; s < arrhead.size(); s++) {
/* 458 */       lable = new Label(s, 4, arrhead.get(s), (CellFormat)cellFormat);
/* 459 */       ws.addCell((WritableCell)lable);
/*     */     } 
/*     */     
/* 462 */     WritableCellFormat mainBody = new WritableCellFormat();
/* 463 */     mainBody.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 464 */     mainBody.setBackground(Colour.WHITE);
/* 465 */     mainBody.setWrap(true);
/* 466 */     mainBody.setAlignment(Alignment.RIGHT);
/* 467 */     mainBody.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */     
/* 469 */     lable = new Label(0, 0, "供应商编号*", (CellFormat)cellFormat);
/* 470 */     ws.addCell((WritableCell)lable);
/* 471 */     lable = new Label(1, 0, "物料号*", (CellFormat)cellFormat);
/* 472 */     ws.addCell((WritableCell)lable);
/* 473 */     lable = new Label(2, 0, "入库时间*", (CellFormat)cellFormat);
/* 474 */     ws.addCell((WritableCell)lable);
/* 475 */     lable = new Label(3, 0, "数量*", (CellFormat)cellFormat);
/* 476 */     ws.addCell((WritableCell)lable);
/* 477 */     lable = new Label(4, 0, "库位*", (CellFormat)cellFormat);
/* 478 */     ws.addCell((WritableCell)lable);
/*     */     
/* 480 */     sheet.mergeCells(0, 0, 0, 0);
/* 481 */     return ws;
/*     */   }
/*     */   
/*     */   private String getSys_url() {
/* 485 */     String strClassName = getClass().getName();
/* 486 */     String strPackageName = "";
/* 487 */     if (getClass().getPackage() != null) {
/* 488 */       strPackageName = getClass().getPackage().getName();
/*     */     }
/* 490 */     String strClassFileName = "";
/* 491 */     if (!"".equals(strPackageName)) {
/* 492 */       strClassFileName = strClassName.substring(
/* 493 */           strPackageName.length() + 1, strClassName.length());
/*     */     } else {
/* 495 */       strClassFileName = strClassName;
/*     */     } 
/* 497 */     URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
/* 498 */     String strURL = url.toString();
/* 499 */     strURL = strURL.substring(strURL.indexOf('/') + 1, 
/* 500 */         strURL.lastIndexOf('/'));
/* 501 */     return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward showImportNext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 508 */     request.setAttribute("x_type", request.getParameter("type"));
/* 509 */     request.setAttribute("x_wmsuwid", request.getParameter("wmsuwid"));
/* 510 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward upLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 517 */     WmsUnplannedWarehousingQueryForm uFrom = (WmsUnplannedWarehousingQueryForm)form;
/* 518 */     String filenames = "";
/* 519 */     String wmsuwid = request.getParameter("wmsuwid");
/* 520 */     PrintWriter out = null;
/*     */     try {
/* 522 */       this.file = new File(this.servlet.getServletContext().getRealPath(
/* 523 */             "/temp/importfile/"));
/* 524 */       if (!this.file.exists()) {
/* 525 */         this.file.mkdir();
/*     */       }
/* 527 */       request.setAttribute("filenames", String.valueOf(this.file.getPath()) + "\\" + uFrom.getMyFile().getFileName());
///* 528 */       response.setCharacterEncoding("GBK");
/* 529 */       out = response.getWriter();
/* 530 */       saveFile(uFrom.getMyFile(), this.file);
/* 531 */       request.setAttribute("x_wmsuwid", wmsuwid);
/* 532 */     } catch (Exception e) {
/* 533 */       e.printStackTrace();
/*     */     } 
/* 535 */     String id = request.getParameter("id");
/* 536 */     request.setAttribute("x_id", id);
/* 537 */     String type = request.getParameter("type");
/* 538 */     request.setAttribute("x_type", type);
/* 539 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   protected void saveFile(FormFile formFile, File file) throws Exception {
/*     */     try {
/* 544 */       String path = file.getPath();
/* 545 */       InputStream in = formFile.getInputStream();
/* 546 */       FileOutputStream fout = new FileOutputStream(String.valueOf(path) + "\\" + formFile.getFileName());
/* 547 */       this.filename = String.valueOf(path) + "\\" + formFile.getFileName();
/* 548 */       byte[] buffer = new byte[8192];
/* 549 */       int count = 0;
/* 550 */       while ((count = in.read(buffer)) > 0) {
/* 551 */         fout.write(buffer, 0, count);
/*     */       }
/* 553 */       fout.close();
/* 554 */       formFile.destroy();
/* 555 */     } catch (Exception e) {
/* 556 */       throw new ActionException("文件" + this.filename + "不存在");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward importeWmsUW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
/* 563 */     WmsUWManager uwManager = ServiceLocator.getWmsUWManager(request);
/* 564 */     List<String> error = new ArrayList<String>();
/* 565 */     Workbook wb = null;
/* 566 */     String filenames = "";
/* 567 */     String wmsuwid = (String)request.getAttribute("x_id");
/* 568 */     String type = (String)request.getAttribute("x_type");
/*     */     
/*     */     try {
/* 571 */       filenames = request.getAttribute("filenames").toString();
/* 572 */       wb = Workbook.getWorkbook(new File(filenames));
/* 573 */     } catch (Exception e) {
/* 574 */       throw new ActionException("文件" + filenames + "不存在");
/*     */     } 
/* 576 */     Sheet[] sheet = wb.getSheets();
/* 577 */     List<WmsUnplannedWarehousingItem> items = null;
/* 578 */     if (sheet != null && sheet.length > 0) {
/* 579 */       String str = "";
/* 580 */       int rowNum = 0; byte b; int i; Sheet[] arrayOfSheet;
/* 581 */       for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 582 */         int k = 0;
/* 583 */         rowNum = sheet[k].getRows();
/* 584 */         for (int j = 1; j < rowNum; j++) {
/* 585 */           Cell[] cells = sh.getRow(j);
/* 586 */           String supplierCode = cells[0].getContents();
/* 587 */           String partId = cells[1].getContents();
/* 588 */           String enterTime = cells[2].getContents();
/* 589 */           String location = cells[4].getContents();
/* 590 */           if (supplierCode.equals("")) {
/* 591 */             str = String.valueOf(str) + "第" + j + "行,第" + '\001' + "列:供应商编号不能为空" + "<br/>";
/*     */           }
/* 593 */           if (partId.equals("")) {
/* 594 */             str = String.valueOf(str) + "第" + j + "行,第" + '\002' + "列:物料号不能为空" + "<br/>";
/*     */           }
/* 596 */           if (enterTime.equals("")) {
/* 597 */             str = String.valueOf(str) + "第" + j + "行,第" + '\003' + "列:入库时间不能为空" + "<br/>";
/*     */           }
/* 599 */           if (cells[3].getContents().equals("")) {
/* 600 */             str = String.valueOf(str) + "第" + j + "行,第" + '\004' + "列:数量不能为空" + "<br/>";
/*     */           }
/* 602 */           if (location.equals(""))
/* 603 */             str = String.valueOf(str) + "第" + j + "行,第" + '\005' + "列:库位不能为空" + "<br/>"; 
/*     */         } 
/*     */         b++; }
/*     */       
/* 607 */       if (str.equals(""))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 616 */         items = uwManager.insertWmsUnplannedWarehousingItem(sheet, Integer.valueOf(Integer.parseInt(wmsuwid)), type);
/*     */       }
/*     */     } 
/* 619 */     if (error.size() != 0) {
/* 620 */       request.setAttribute("showerror", Boolean.valueOf(true));
/* 621 */       request.setAttribute("error", error);
/*     */     } else {
/* 623 */       request.setAttribute("showerror", Boolean.valueOf(false));
/*     */     } 
/* 625 */     if (error.size() != 0) {
/* 626 */       throw new ActionException("文件" + filenames + "不存在" + (String)error.get(0));
/*     */     }
/*     */     
/* 629 */     request.setAttribute("X_RESULTLIST", items);
/* 630 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(WmsUnplannedWarehousingQueryForm queryForm) {
/* 634 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 635 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 636 */     String datetime = queryForm.getDatetime();
/*     */     
/* 638 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/wms/WmsUWAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */