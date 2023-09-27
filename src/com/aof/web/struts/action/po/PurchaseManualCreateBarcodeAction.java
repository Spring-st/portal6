/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.PurchaseManualCreateBarcode;
/*     */ import com.aof.model.po.query.PurchaseManualCreateBarcodeQueryOrder;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.po.PurchaseManualCreateBarcodeManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.PurchaseManualCreateBarcodeQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDataFormat;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.usermodel.IndexedColors;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.upload.FormFile;
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
/*     */ public class PurchaseManualCreateBarcodeAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  70 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/*  71 */     PurchaseManualCreateBarcodeQueryForm queryForm = (PurchaseManualCreateBarcodeQueryForm)form;
/*  72 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  73 */       queryForm.setOrder(PurchaseManualCreateBarcodeQueryOrder.id.getName());
/*  74 */       queryForm.setDescend(true);
/*     */     } 
/*  76 */     Map conditions = constructQueryMap(queryForm);
/*  77 */     getConditionAndOrder(queryForm, conditions, request);
/*  78 */     String exportType = queryForm.getExportType();
/*  79 */     if (exportType != null && exportType.length() > 0) {
/*  80 */       List data = manager.list(conditions, 0, -1, null, queryForm.isDescend());
/*  81 */       int index = SessionTempFile.createNewTempFile(request);
/*  82 */       String fileName = "CadStage";
/*  83 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  85 */               MessageResources messages = PurchaseManualCreateBarcodeAction.this.getResources(request);
/*  86 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "customer.materialCode"));
/*  87 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "customer.materialName"));
/*  88 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "basicpartprice.pcUm"));
/*  89 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "purchaseManualCreateBarcode.model"));
/*  90 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "ediproduction.supplier"));
/*  91 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "PurchaseManualCreateBarcode.date"));
/*  92 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "PurchaseManualCreateBarcode.seq"));
/*  93 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "PurchaseManualCreateBarcode.qty"));
/*  94 */               row.add(messages.getMessage(PurchaseManualCreateBarcodeAction.this.getLocale(request), "salesOrderItem.isprintlabels"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  98 */               row.add(BeanUtils.getProperty(data, "part"));
/*  99 */               row.add(BeanUtils.getProperty(data, "partName"));
/* 100 */               row.add(BeanUtils.getProperty(data, "partUnit"));
/* 101 */               row.add(BeanUtils.getProperty(data, "model"));
/* 102 */               row.add(BeanUtils.getProperty(data, "supplierName"));
/* 103 */               row.add(BeanUtils.getProperty(data, "serialDate"));
/* 104 */               PurchaseManualCreateBarcode order = (PurchaseManualCreateBarcode)data;
/* 105 */               row.add(BeanUtils.getProperty(data, "serialNumber"));
/* 106 */               row.add(BeanUtils.getProperty(data, "qty"));
/*     */               
/* 108 */               if (order.getPrintStatus().intValue() == 0) {
/* 109 */                 row.add("未打印");
/* 110 */               } else if (order.getPrintStatus().intValue() == 1) {
/* 111 */                 row.add("已打印");
/*     */               } else {
/* 113 */                 row.add("未打印");
/*     */               } 
/*     */             }
/*     */           });
/* 117 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 119 */     if (queryForm.isFirstInit()) {
/* 120 */       queryForm.init(manager.getListCount(conditions));
/*     */     } else {
/* 122 */       queryForm.init();
/*     */     } 
/* 124 */     int a = queryForm.getPageNoAsInt();
/* 125 */     int b = queryForm.getPageSizeAsInt();
/* 126 */     int c = queryForm.getPage();
/* 127 */     List<PurchaseManualCreateBarcode> list = manager.list(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), PurchaseManualCreateBarcodeQueryOrder.id, queryForm.isDescend());
/* 128 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 129 */     request.setAttribute("X_USER", getCurrentUser(request));
/* 130 */     request.setAttribute("X_RESULTLIST", list);
/* 131 */     request.setAttribute("x_selType", Integer.valueOf(178));
/* 132 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(PurchaseManualCreateBarcodeQueryForm queryForm) {
/* 141 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 143 */     return conditions;
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
/*     */   public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 155 */     if (!isBack(request)) {
/* 156 */       PurchaseManualCreateBarcode model = new PurchaseManualCreateBarcode();
/* 157 */       BeanForm roleForm = (BeanForm)getForm("/insertPurchaseManualCreateBarcode", request);
/* 158 */       roleForm.populateToForm(model);
/*     */     } 
/* 160 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 161 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 174 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/*     */     try {
/* 176 */       BeanForm beanForm = (BeanForm)form;
/* 177 */       PurchaseManualCreateBarcode model = new PurchaseManualCreateBarcode();
/* 178 */       beanForm.populateToBean(model);
/* 179 */       manager.insert(model);
/* 180 */       request.setAttribute("X_OBJECT", model);
/* 181 */       request.setAttribute("X_ROWPAGE", "PurchaseManualCreateBarcode/row.jsp");
/* 182 */     } catch (Exception e) {
/* 183 */       postGlobalMessage("creat.fail", request.getSession());
/* 184 */       e.printStackTrace();
/*     */     } 
/* 186 */     postGlobalMessage("creat.success", request.getSession());
/* 187 */     return mapping.findForward("success");
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 200 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/*     */     try {
/* 202 */       Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 203 */       PurchaseManualCreateBarcode model = manager.getPurchaseManualCreateBarcode(id);
/* 204 */       request.setAttribute("X_OBJECT", model);
/* 205 */       if (!isBack(request)) {
/* 206 */         BeanForm beanForm = (BeanForm)getForm("/updatePurchaseManualCreateBarcode", request);
/* 207 */         beanForm.populate(model, "to_form");
/*     */       } 
/* 209 */     } catch (Exception e) {
/* 210 */       e.printStackTrace();
/*     */     } 
/* 212 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 213 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 226 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/*     */     try {
/* 228 */       BeanForm beanForm = (BeanForm)form;
/* 229 */       Integer id = ActionUtils.parseInt((String)beanForm.get("id"));
/* 230 */       PurchaseManualCreateBarcode model = manager.getPurchaseManualCreateBarcode(id);
/* 231 */       beanForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 232 */       beanForm.populateToBean(model);
/* 233 */       manager.update(model);
/* 234 */       request.setAttribute("X_OBJECT", model);
/* 235 */       request.setAttribute("X_ROWPAGE", "PurchaseManualCreateBarcode/row.jsp");
/* 236 */     } catch (Exception e) {
/* 237 */       postGlobalMessage("modify.fail", request.getSession());
/* 238 */       e.printStackTrace();
/*     */     } 
/* 240 */     postGlobalMessage("modify.successful", request.getSession());
/* 241 */     return mapping.findForward("success");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 254 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/*     */     try {
/* 256 */       Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 257 */       PurchaseManualCreateBarcode model = manager.getPurchaseManualCreateBarcode(id);
/* 258 */       manager.delete(model);
/* 259 */     } catch (Exception e) {
/* 260 */       postGlobalMessage("delete.fail", request.getSession());
/* 261 */       e.printStackTrace();
/*     */     } 
/* 263 */     postGlobalMessage("delete.successful", request.getSession());
/* 264 */     return new ActionForward("listPurchaseManualCreateBarcode.do", true);
/*     */   }
/*     */   public ActionForward purchaseManualCreateBarcodeMin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 267 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 268 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 269 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 270 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 272 */       String str = request.getParameter("str");
/* 273 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 274 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 275 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 282 */         barcodes.add(code); b++; }
/*     */       
/* 284 */       request.setAttribute("X_CODELIST", barcodes);
/* 285 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 286 */       request.setAttribute("path", request.getContextPath());
/* 287 */     } catch (Exception e) {
/*     */       
/* 289 */       e.printStackTrace();
/*     */     } 
/* 291 */     return mapping.findForward("page");
/*     */   }
/*     */   public ActionForward purchaseManualCreateBarcodeZhong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 294 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 295 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 296 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 297 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 299 */       String str = request.getParameter("str");
/* 300 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 301 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 302 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 310 */         barcodes.add(code); b++; }
/*     */       
/* 312 */       request.setAttribute("X_CODELIST", barcodes);
/* 313 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 314 */       request.setAttribute("path", request.getContextPath());
/* 315 */     } catch (Exception e) {
/*     */       
/* 317 */       e.printStackTrace();
/*     */     } 
/* 319 */     return mapping.findForward("page");
/*     */   }
/*     */   public ActionForward purchaseManualCreateBarcodeMax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 322 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 323 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 324 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 325 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 327 */       String str = request.getParameter("str");
/* 328 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 329 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 330 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 339 */         barcodes.add(code); b++; }
/*     */       
/* 341 */       request.setAttribute("X_CODELIST", barcodes);
/* 342 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 343 */       request.setAttribute("path", request.getContextPath());
/* 344 */     } catch (Exception e) {
/*     */       
/* 346 */       e.printStackTrace();
/*     */     } 
/* 348 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward purchaseManualCreateBarQEcodeMin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 354 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 355 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 356 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 357 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 359 */       String str = request.getParameter("str");
/* 360 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 361 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 362 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 369 */         barcodes.add(code); b++; }
/*     */       
/* 371 */       request.setAttribute("X_CODELIST", barcodes);
/* 372 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 373 */       request.setAttribute("path", request.getContextPath());
/* 374 */     } catch (Exception e) {
/*     */       
/* 376 */       e.printStackTrace();
/*     */     } 
/* 378 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward purchaseManualCreateBarQEcodeMax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 382 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 383 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 384 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 385 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 387 */       String str = request.getParameter("str");
/* 388 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 389 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 390 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 397 */         barcodes.add(code); b++; }
/*     */       
/* 399 */       request.setAttribute("X_CODELIST", barcodes);
/* 400 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 401 */       request.setAttribute("path", request.getContextPath());
/* 402 */     } catch (Exception e) {
/*     */       
/* 404 */       e.printStackTrace();
/*     */     } 
/* 406 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward purchaseManualCreateBarQEcodeZhong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 410 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 411 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 412 */     List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 413 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*     */     try {
/* 415 */       String str = request.getParameter("str");
/* 416 */       String[] s = str.split(","); byte b; int i; String[] arrayOfString1;
/* 417 */       for (i = (arrayOfString1 = s).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 418 */         PurchaseManualCreateBarcode code = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 425 */         barcodes.add(code); b++; }
/*     */       
/* 427 */       request.setAttribute("X_CODELIST", barcodes);
/* 428 */       request.setAttribute("X_DATE", format.format(new Date()));
/* 429 */       request.setAttribute("path", request.getContextPath());
/* 430 */     } catch (Exception e) {
/*     */       
/* 432 */       e.printStackTrace();
/*     */     } 
/* 434 */     return mapping.findForward("page");
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
/*     */   public ActionForward excelCreateBarcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 447 */     HSSFWorkbook wb = new HSSFWorkbook();
/*     */     
/* 449 */     HSSFSheet sheet = wb.createSheet();
/* 450 */     wb.setSheetName(0, "期初打印条码");
/*     */     
/* 452 */     sheet.setColumnWidth((short)0, (short)4000);
/* 453 */     sheet.setColumnWidth((short)1, (short)7000);
/* 454 */     sheet.setColumnWidth((short)2, (short)4000);
/* 455 */     sheet.setColumnWidth((short)3, (short)4000);
/* 456 */     sheet.setColumnWidth((short)4, (short)7000);
/* 457 */     sheet.setColumnWidth((short)5, (short)5000);
/* 458 */     sheet.setColumnWidth((short)6, (short)4000);
/* 459 */     sheet.setColumnWidth((short)7, (short)3000);
/* 460 */     sheet.setColumnWidth((short)8, (short)3000);
/* 461 */     sheet.setColumnWidth((short)9, (short)3000);
/* 462 */     sheet.setColumnWidth((short)10, (short)3000);
/*     */     
/* 464 */     HSSFCellStyle style = wb.createCellStyle();
/*     */     
/* 466 */     style.setVerticalAlignment((short)1);
/*     */     
/* 468 */     style.setAlignment((short)2);
/* 469 */     style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
/* 470 */     style.setFillPattern((short)1);
/* 471 */     style.setBorderBottom((short)1);
/* 472 */     style.setBorderLeft((short)1);
/* 473 */     style.setBorderTop((short)1);
/* 474 */     style.setBorderRight((short)1);
/*     */     
/* 476 */     HSSFCellStyle cellStyle2 = wb.createCellStyle();
/* 477 */     HSSFDataFormat format = wb.createDataFormat();
/* 478 */     cellStyle2.setDataFormat(format.getFormat("@"));
/*     */     
/* 480 */     cellStyle2.setVerticalAlignment((short)1);
/*     */     
/* 482 */     cellStyle2.setAlignment((short)2);
/* 483 */     cellStyle2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
/* 484 */     cellStyle2.setFillPattern((short)1);
/* 485 */     cellStyle2.setBorderBottom((short)1);
/* 486 */     cellStyle2.setBorderLeft((short)1);
/* 487 */     cellStyle2.setBorderTop((short)1);
/* 488 */     cellStyle2.setBorderRight((short)1);
/*     */     
/* 490 */     HSSFRow row = sheet.createRow(0);
/*     */     
/* 492 */     row.setHeight((short)300);
/*     */     
/* 494 */     HSSFCell ce = row.createCell((short)0);
/* 496 */     ce.setCellValue("物料号");
/* 497 */     ce.setCellStyle(style);
/* 498 */     ce = row.createCell((short)1);
/* 500 */     ce.setCellValue("物料名称");
/* 501 */     ce.setCellStyle(style);
/*     */     
/* 503 */     ce = row.createCell((short)2);
/* 505 */     ce.setCellValue("单位");
/* 506 */     ce.setCellStyle(style);
/*     */     
/* 508 */     ce = row.createCell((short)3);
/* 510 */     ce.setCellValue("车型");
/* 511 */     ce.setCellStyle(style);
/*     */     
/* 513 */     ce = row.createCell((short)4);
/* 515 */     ce.setCellValue("供应商名称");
/* 516 */     ce.setCellStyle(cellStyle2);
/*     */     
/* 518 */     ce = row.createCell((short)5);
/* 520 */     ce.setCellValue("日期(yyyyMMdd)");
/* 521 */     ce.setCellStyle(style);
/*     */     
/* 523 */     ce = row.createCell((short)6);
/* 525 */     ce.setCellValue("流水号");
/* 526 */     ce.setCellStyle(cellStyle2);
/*     */     
/* 528 */     ce = row.createCell((short)7);
/* 530 */     ce.setCellValue("数量");
/* 531 */     ce.setCellStyle(style);
/* 532 */     response.setContentType("appliction/x-msdownload");
///* 533 */     response.setCharacterEncoding("utf-8");
/* 534 */     String fileName = "printBarCode";
/* 535 */     response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8") + 
/* 536 */         ".xls");
/* 537 */     ServletOutputStream outStream = null;
/*     */     try {
/* 539 */       outStream = response.getOutputStream();
/* 540 */       wb.write((OutputStream)outStream);
/* 541 */     } catch (Exception e) {
/* 542 */       e.printStackTrace();
/*     */     } finally {
/* 544 */       outStream.flush();
/* 545 */       outStream.close();
/*     */     } 
/* 547 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward importBarCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 552 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 553 */     ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
/* 554 */     List<String> data = new ArrayList<String>();
/* 555 */     PurchaseManualCreateBarcodeQueryForm queryForm = (PurchaseManualCreateBarcodeQueryForm)form;
/* 556 */     FormFile uploadfile = queryForm.getFileContent();
/* 557 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhssSS");
/* 558 */     SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
/* 559 */     String asnNo = format1.format(new Date());
/* 560 */     int fileSize = 0;
/* 561 */     String fileName = "";
/* 562 */     if (uploadfile != null)
/* 563 */       fileSize = uploadfile.getFileSize(); 
/* 564 */     InputStream inputstream = null;
/* 565 */     HSSFWorkbook wb = null;
/* 566 */     Date date = new Date();
/*     */     
/* 568 */     User user = getCurrentUser(request);
/* 569 */     String supplierNo = user.getLoginName();
/* 570 */     SupplierManager sManager = ServiceLocator.getSupplierManager(request);
/* 571 */     Supplier supplier = sManager.getSupplierByCode(supplierNo);
/* 572 */     if (fileSize > 0) {
/* 573 */       if (uploadfile != null && uploadfile.getFileSize() > 0) {
/* 574 */         fileName = uploadfile.getFileName();
/* 575 */         String xls = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
/* 576 */         if (xls.indexOf("xls") == -1) {
/* 577 */           postGlobalMessage("Import.the.file.is.not", request.getSession());
/* 578 */           return new ActionForward("listUnfinishPlanGroup.do", true);
/*     */         } 
/*     */       } 
/*     */       
/* 582 */       boolean isread = true;
/*     */       try {
/* 584 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/* 585 */       } catch (Exception ex) {
/* 586 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/*     */       } 
/*     */       
/* 589 */       List<List<String>> allList = new ArrayList<List<String>>();
/* 590 */       HSSFSheet sheet11 = wb.getSheetAt(0);
/* 591 */       if (sheet11 == null) {
/* 592 */         System.out.println("SHEET不存在");
/*     */       }
/*     */       
/* 595 */       for (int rowNum = 1; rowNum <= sheet11.getLastRowNum() + 1; rowNum++) {
/* 596 */         List<String> list = new ArrayList<String>();
/* 597 */         HSSFRow row = sheet11.getRow(rowNum);
/* 598 */         if (row != null) {
/*     */ 
/*     */           
/* 601 */           for (short cellNum = 0; cellNum <= row.getLastCellNum(); cellNum = (short)(cellNum + 1)) {
/* 602 */             HSSFCell cell = row.getCell(cellNum);
/* 603 */             System.out.println(cellNum);
/* 604 */             if (cell != null) {
/* 605 */               String de = getValue(sheet11.getRow(rowNum).getCell(cellNum));
/* 606 */               list.add(de);
/*     */             } else {
/* 608 */               list.add("");
/*     */             } 
/*     */           } 
/* 611 */           allList.add(list);
/*     */         } 
/* 613 */       }  List<PurchaseManualCreateBarcode> barcodes = new ArrayList<PurchaseManualCreateBarcode>();
/* 614 */       int b = allList.size();
/* 615 */       for (int j = 0; j < allList.size(); j++) {
/* 616 */         List<String> list = allList.get(j);
/* 617 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 618 */         int a = list.size();
/* 619 */         PurchaseManualCreateBarcode barcode = new PurchaseManualCreateBarcode();
/* 620 */         for (int k = 0; k <= list.size(); k++) {
/* 621 */           if (k == 0) {
/* 622 */             if (((String)list.get(k)).equals("")) {
/* 623 */               data.add("物料号为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 624 */               barcode.setPart(null);
/*     */             } else {
/* 626 */               barcode.setPart(list.get(k));
/*     */             } 
/* 628 */           } else if (k == 1) {
/* 629 */             if (((String)list.get(k)).equals("")) {
/* 630 */               data.add("物料名称为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 631 */               barcode.setPartName(null);
/*     */             } else {
/* 633 */               barcode.setPartName(list.get(k));
/*     */             } 
/* 635 */           } else if (k == 2) {
/* 636 */             if (((String)list.get(k)).equals("")) {
/* 637 */               data.add("单位为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 638 */               barcode.setPartUnit(null);
/*     */             } else {
/* 640 */               barcode.setPartUnit(list.get(k));
/*     */             } 
/* 642 */           } else if (k == 3) {
/* 643 */             if (((String)list.get(k)).equals("")) {
/* 644 */               data.add("车型为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 645 */               barcode.setModel(null);
/*     */             } else {
/* 647 */               barcode.setModel(list.get(k));
/*     */             }
/*     */           
/* 650 */           } else if (k == 4) {
/* 651 */             if (((String)list.get(k)).equals("")) {
/* 652 */               data.add("供应商名称为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 653 */               barcode.setSupplierName(null);
/*     */             } else {
/* 655 */               barcode.setSupplierName(list.get(k));
/*     */             }
/*     */           
/* 658 */           } else if (k == 5) {
/* 659 */             if (((String)list.get(k)).equals("")) {
/* 660 */               data.add("日期为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 661 */               barcode.setSerialDate(null);
/*     */             } else {
/* 663 */               SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 664 */               barcode.setSerialDate(format.parse(list.get(k)));
/*     */             }
/*     */           
/* 667 */           } else if (k == 6) {
/* 668 */             if (((String)list.get(k)).equals("")) {
/* 669 */               data.add("流水号为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 670 */               barcode.setSerialNumber(null);
/*     */             } else {
/* 672 */               barcode.setSerialNumber(list.get(k));
/*     */             }
/*     */           
/* 675 */           } else if (k == 7) {
/* 676 */             if (((String)list.get(k)).equals("")) {
/* 677 */               data.add("数量为空：第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 678 */               barcode.setQty(null);
/*     */             } else {
/* 680 */               barcode.setQty(Integer.valueOf(Integer.parseInt(list.get(k))));
/*     */             } 
/*     */           } 
/*     */         } 
/* 684 */         barcode.setPrintStatus(Integer.valueOf(0));
/* 685 */         barcodes.add(barcode);
/*     */       } 
/*     */       
/* 688 */       if (data.size() != 0) {
/* 689 */         for (int k = 0; k < barcodes.size(); k++) {
/* 690 */           PurchaseManualCreateBarcode code = barcodes.get(k);
/* 691 */           manager.insert(code);
/*     */         } 
/* 693 */         request.setAttribute("msg", data);
/* 694 */         return mapping.findForward("add");
/*     */       } 
/* 696 */       for (int i = 0; i < barcodes.size(); i++) {
/* 697 */         PurchaseManualCreateBarcode code = barcodes.get(i);
/* 698 */         manager.insert(code);
/*     */       } 
/* 700 */       if (barcodes.size() == 0) {
/* 701 */         data.add("该文件没有数据!");
/* 702 */         request.setAttribute("msg", data);
/* 703 */         return mapping.findForward("add");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 708 */     return new ActionForward("listPurchaseManualCreateBarcode.do", true);
/*     */   }
/*     */   private String getValue(HSSFCell cell) {
/* 711 */     if (cell.getCellType() == 4)
/* 712 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 713 */         .toPlainString(); 
/* 714 */     if (cell.getCellType() == 0)
/* 715 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 716 */         .setScale(0, 4).toPlainString(); 
/* 717 */     if (cell.getCellType() == 2) {
/* 718 */       return String.valueOf(cell.getNumericCellValue());
/*     */     }
/*     */     
/* 721 */     return String.valueOf(cell.getStringCellValue().trim());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward updatePurchaseManualCreateBarcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 727 */     PurchaseManualCreateBarcodeManager manager = ServiceLocator.getPurchaseManualCreateBarcodeManager(request);
/* 728 */     JSONObject object = new JSONObject();
/* 729 */     boolean bol = false;
/*     */     try {
/* 731 */       String str = request.getParameter("ids");
/* 732 */       String[] strId = str.split(";"); byte b; int i; String[] arrayOfString1;
/* 733 */       for (i = (arrayOfString1 = strId).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 734 */         PurchaseManualCreateBarcode barcode = manager.getPurchaseManualCreateBarcode(Integer.valueOf(Integer.parseInt(string)));
/* 735 */         barcode.setPrintStatus(Integer.valueOf(1));
/* 736 */         manager.update(barcode); b++; }
/*     */       
/* 738 */       bol = true;
/* 739 */       PrintWriter writer = response.getWriter();
/* 740 */       object.put("str", Boolean.valueOf(bol));
/* 741 */       writer.print(object);
/* 742 */     } catch (Exception e) {
/*     */       
/* 744 */       e.printStackTrace();
/*     */     } 
/* 746 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseManualCreateBarcodeAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */