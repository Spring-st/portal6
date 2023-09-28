/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.BasicPartLocation;
/*     */ import com.aof.model.basic.query.BasicPartLocationQueryCondition;
/*     */ import com.aof.model.basic.query.BasicPartLocationQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.basic.BasicPartLocationManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.BasicPartLocationQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import com.shcnc.utils.BeanHelper;
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
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class BasicPartLocationAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private File file;
/*     */   private String filename;
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  85 */     BasicPartLocationQueryForm queryForm = (BasicPartLocationQueryForm)form;
/*  86 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  87 */       queryForm.setOrder(BasicPartLocationQueryOrder.ID.getName());
/*  88 */       queryForm.setDescend(false);
/*  89 */       queryForm.setStatus("0");
/*     */     } 
/*     */     
/*  92 */     BasicPartLocationManager fm = ServiceLocator.getBasicPartLocationManager(request);
/*  93 */     Map conditions = constructQueryMap(queryForm);
/*  94 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  96 */     String exportType = queryForm.getExportType();
/*  97 */     if (StringUtils.isNotEmpty(exportType)) {
/*  98 */       List data = fm.getBasicPartLocationList(conditions, 0, -1, BasicPartLocationQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 100 */       int index = SessionTempFile.createNewTempFile(request);
/* 101 */       String fileName = "BasicPartLocation";
/* 102 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 105 */               MessageResources messages = BasicPartLocationAction.this.getResources(request);
/* 106 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.id"));
/* 107 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.location.code"));
/* 108 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.location.basic_storeroom_id.code"));
/* 109 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.part.describe1"));
/* 110 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.part.describe2"));
/* 111 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.part.unit"));
/* 112 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.part.productClass"));
/* 113 */               row.add(messages.getMessage(BasicPartLocationAction.this.getLocale(request), "BasicPartLocation.part.recommend_date"));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 119 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 120 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 121 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.basic_storeroom_id.code"));
/* 122 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 123 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 124 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/* 125 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.productClass"));
/* 126 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.recommend_date"));
/*     */             }
/*     */           });
/* 129 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 131 */     if (queryForm.isFirstInit()) {
/* 132 */       queryForm.init(fm.getBasicPartLocationListCount(conditions));
/*     */     } else {
/* 134 */       queryForm.init();
/*     */     } 
/* 136 */     List result = fm.getBasicPartLocationList(conditions, 
/* 137 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 138 */         BasicPartLocationQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 140 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 141 */     request.setAttribute("x_siteList", siteList);
/* 142 */     request.setAttribute("X_RESULTLIST", result);
/* 143 */     request.setAttribute("x_selType", Integer.valueOf(13));
/* 144 */     putEnumListToRequest(request);
/* 145 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map constructQueryMap(BasicPartLocationQueryForm queryForm) {
/* 148 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 149 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 150 */     if (id != null && !id.equals("")) {
/* 151 */       conditions.put(BasicPartLocationQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 154 */     String status = queryForm.getStatus();
/* 155 */     if (status != null && !status.equals("")) {
/* 156 */       conditions.put(BasicPartLocationQueryCondition.ENABLED_EQ, status);
/*     */     }
/*     */     
/* 159 */     return conditions;
/*     */   }
/*     */   private BasicPartLocation getBasicPartLocation(HttpServletRequest request) throws Exception {
/* 162 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 163 */     BasicPartLocationManager BasicPartLocationManager = ServiceLocator.getBasicPartLocationManager(request);
/* 164 */     BasicPartLocation BasicPartLocation = BasicPartLocationManager.getBasicPartLocation(id);
/* 165 */     if (BasicPartLocation == null)
/* 166 */       throw new ActionException("BasicPartLocation.notFound", id); 
/* 167 */     return BasicPartLocation;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 171 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 187 */     BasicPartLocation partLocation = getBasicPartLocation(request);
/*     */     
/* 189 */     request.setAttribute("x_partLoc", partLocation);
/* 190 */     if (!isBack(request)) {
/*     */       
/* 192 */       BeanForm BasicPartLocationForm = (BeanForm)getForm("/updatePartLocation", request);
/* 193 */       BasicPartLocationForm.populate(partLocation, "to_form");
/*     */     } 
/* 195 */     putEnumListToRequest(request);
/* 196 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 197 */     request.setAttribute("x_siteList", siteList);
/* 198 */     return mapping.findForward("page");
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
/* 214 */     BeanForm BasicPartLocationForm = (BeanForm)form;
/* 215 */     BasicPartLocation BasicPartLocation = new BasicPartLocation();
/* 216 */     BasicPartLocationForm.populate(BasicPartLocation, "to_bean");
/* 217 */     BasicPartLocationManager BasicPartLocationManager = ServiceLocator.getBasicPartLocationManager(request);
/* 218 */     BasicPartLocation = BasicPartLocationManager.updateBasicPartLocation(BasicPartLocation);
/*     */     
/* 220 */     request.setAttribute("X_OBJECT", BasicPartLocation);
/* 221 */     request.setAttribute("X_ROWPAGE", "wmsbasic/BasicPartLocation/row.jsp");
/* 222 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 228 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 232 */     String s = request.getParameter("site_id");
/* 233 */     return (s != null && !s.equals(""));
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
/* 250 */     if (!isBack(request)) {
/* 251 */       BasicPartLocation BasicPartLocation = new BasicPartLocation();
/* 252 */       BeanForm BasicPartLocationForm = (BeanForm)getForm("/insertPartLocation", request);
/* 253 */       BasicPartLocationForm.populate(BasicPartLocation, "to_form");
/*     */     } 
/* 255 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/*     */     
/* 257 */     request.setAttribute("x_siteList", siteList);
/* 258 */     putEnumListToRequest(request);
/* 259 */     return mapping.findForward("page");
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
/* 275 */     BasicPartLocationManager cm = ServiceLocator.getBasicPartLocationManager(request);
/* 276 */     BeanForm BasicPartLocationForm = (BeanForm)form;
/* 277 */     BasicPartLocation BasicPartLocation = new BasicPartLocation();
/* 278 */     BasicPartLocationForm.populate(BasicPartLocation, "to_bean");
/* 279 */     BasicPartLocation = cm.insertBasicPartLocation(BasicPartLocation);
/*     */     
/* 281 */     request.setAttribute("X_OBJECT", BasicPartLocation);
/* 282 */     request.setAttribute("X_ROWPAGE", "wmsbasic/basicPartLocation/row.jsp");
/* 283 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward exportsPartLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 289 */     List<Character> chars = new ArrayList();
/* 290 */     int charsDouble = 0;
/* 291 */     for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
/* 292 */       chars.add(Character.valueOf(i));
/*     */     }
/*     */     
/*     */     try {
/* 296 */       DateFormatter format = new DateFormatter(Date.class, 
/* 297 */           "HHmmssSS");
/* 298 */       String name = String.valueOf(format.format(new Date())) + ".xls";
/* 299 */       if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
/* 300 */         (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
/*     */       }
/* 302 */       File f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" + 
/* 303 */           name);
/* 304 */       while (f.exists()) {
/* 305 */         Thread.sleep(100L);
/* 306 */         name = String.valueOf(format.format(new Date())) + ".xls";
/* 307 */         f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" + 
/* 308 */             name);
/*     */       } 
/* 310 */       WritableWorkbook wwb = Workbook.createWorkbook(f);
/* 311 */       WritableSheet ws = wwb.createSheet("物料库位维护", 0);
/*     */       
/* 313 */       Label lable = null;
/* 314 */       WritableSheet sheet = wwb.getSheet(0);
/* 315 */       sheet.setColumnView(0, 15);
/* 316 */       sheet.setColumnView(1, 20);
/* 317 */       NumberFormat nf = new NumberFormat("#.###");
/* 318 */       WritableFont host = new WritableFont(WritableFont.ARIAL, 
/* 319 */           15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 320 */           Colour.CORAL);
/* 321 */       WritableFont red = new WritableFont(WritableFont.ARIAL, 
/* 322 */           10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 323 */           Colour.RED);
/*     */       
/* 325 */       WritableCellFormat hosts = new WritableCellFormat(host);
/* 326 */       hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 327 */       hosts.setWrap(true);
/* 328 */       hosts.setAlignment(Alignment.CENTRE);
/* 329 */       hosts.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 331 */       WritableCellFormat title = new WritableCellFormat();
/* 332 */       title.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 333 */       title.setBackground(Colour.ORANGE);
/* 334 */       title.setWrap(true);
/* 335 */       title.setAlignment(Alignment.CENTRE);
/* 336 */       title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */ 
/*     */       
/* 339 */       WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
/* 340 */       functions.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 341 */       functions.setWrap(true);
/* 342 */       functions.setAlignment(Alignment.RIGHT);
/* 343 */       functions.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 345 */       WritableCellFormat cellFormat = new WritableCellFormat();
/* 346 */       cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 347 */       cellFormat.setWrap(true);
/* 348 */       cellFormat.setAlignment(Alignment.CENTRE);
/* 349 */       cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 351 */       WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
/* 352 */       reds.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 353 */       reds.setFont((FontRecord)red);
/* 354 */       reds.setWrap(true);
/* 355 */       reds.setAlignment(Alignment.CENTRE);
/* 356 */       reds.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 358 */       ws = createheadtoImportNew(ws, lable, title, hosts, sheet);
/*     */       
/* 360 */       WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
/* 361 */       format3.setWrap(true);
/* 362 */       format3.setAlignment(Alignment.RIGHT);
/* 363 */       format3.setVerticalAlignment(VerticalAlignment.CENTRE);
/* 364 */       format3.setBorder(Border.ALL, BorderLineStyle.THIN);
/*     */       
/* 366 */       wwb.write();
/* 367 */       wwb.close();
/* 368 */       response.sendRedirect("temp/partLocationImportTemplate" + name);
/* 369 */       return mapping.findForward("page");
/* 370 */     } catch (Exception e) {
/* 371 */       throw new ActionException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableSheet createheadtoImportNew(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet) throws RowsExceededException, WriteException {
/* 379 */     List<String> arrhead = new ArrayList<String>();
/*     */ 
/*     */     
/* 382 */     for (int s = 0; s < arrhead.size(); s++) {
/* 383 */       lable = new Label(s, 4, arrhead.get(s), (CellFormat)cellFormat);
/* 384 */       ws.addCell((WritableCell)lable);
/*     */     } 
/*     */     
/* 387 */     WritableCellFormat mainBody = new WritableCellFormat();
/* 388 */     mainBody.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 389 */     mainBody.setBackground(Colour.WHITE);
/* 390 */     mainBody.setWrap(true);
/* 391 */     mainBody.setAlignment(Alignment.RIGHT);
/* 392 */     mainBody.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */     
/* 394 */     lable = new Label(0, 0, "物料号", (CellFormat)cellFormat);
/* 395 */     ws.addCell((WritableCell)lable);
/* 396 */     lable = new Label(1, 0, "库位编码", (CellFormat)cellFormat);
/* 397 */     ws.addCell((WritableCell)lable);
/*     */     
/* 399 */     sheet.mergeCells(0, 0, 0, 0);
/* 400 */     return ws;
/*     */   }
/*     */   
/*     */   private String getSys_url() {
/* 404 */     String strClassName = getClass().getName();
/* 405 */     String strPackageName = "";
/* 406 */     if (getClass().getPackage() != null) {
/* 407 */       strPackageName = getClass().getPackage().getName();
/*     */     }
/* 409 */     String strClassFileName = "";
/* 410 */     if (!"".equals(strPackageName)) {
/* 411 */       strClassFileName = strClassName.substring(
/* 412 */           strPackageName.length() + 1, strClassName.length());
/*     */     } else {
/* 414 */       strClassFileName = strClassName;
/*     */     } 
/* 416 */     URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
/* 417 */     String strURL = url.toString();
/* 418 */     strURL = strURL.substring(strURL.indexOf('/') + 1, 
/* 419 */         strURL.lastIndexOf('/'));
/* 420 */     return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward showImportPartLocationNext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 427 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward upLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 434 */     BasicPartLocationQueryForm uFrom = (BasicPartLocationQueryForm)form;
/* 435 */     String filenames = "";
/* 436 */     PrintWriter out = null;
/*     */     try {
/* 438 */       this.file = new File(this.servlet.getServletContext().getRealPath(
/* 439 */             "/temp/importfile/"));
/* 440 */       if (!this.file.exists()) {
/* 441 */         this.file.mkdir();
/*     */       }
/* 443 */       request.setAttribute("filenames", String.valueOf(this.file.getPath()) + "\\" + uFrom.getMyFile().getFileName());
/* 445 */       out = response.getWriter();
/* 446 */       saveFile(uFrom.getMyFile(), this.file);
/* 447 */     } catch (Exception e) {
/* 448 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 451 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveFile(FormFile formFile, File file) throws Exception {
/*     */     try {
/* 458 */       String path = file.getPath();
/*     */       
/* 460 */       InputStream in = formFile.getInputStream();
/*     */       
/* 462 */       FileOutputStream fout = new FileOutputStream(String.valueOf(path) + "\\" + formFile.getFileName());
/* 463 */       this.filename = String.valueOf(path) + "\\" + formFile.getFileName();
/*     */       
/* 465 */       byte[] buffer = new byte[8192];
/* 466 */       int count = 0;
/*     */       
/* 468 */       while ((count = in.read(buffer)) > 0) {
/* 469 */         fout.write(buffer, 0, count);
/*     */       }
/* 471 */       fout.close();
/* 472 */       formFile.destroy();
/* 473 */     } catch (Exception e) {
/* 474 */       throw new ActionException("该文件" + this.filename + "无法上传");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward importePartLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
/* 481 */     BasicPartLocationManager manager = ServiceLocator.getBasicPartLocationManager(request);
/* 482 */     List<String> error = new ArrayList<String>();
/* 483 */     Workbook wb = null;
/* 484 */     String filenames = "";
/*     */     try {
/* 486 */       filenames = request.getAttribute("filenames").toString();
/* 487 */       wb = Workbook.getWorkbook(new File(filenames));
/* 488 */     } catch (Exception e) {
/* 489 */       throw new ActionException("该文件" + filenames + "不存在");
/*     */     } 
/* 491 */     Sheet[] sheet = wb.getSheets();
/* 492 */     if (sheet != null && sheet.length > 0) {
/* 493 */       manager.insertBasicPartLocation(sheet);
/*     */     }
/* 495 */     if (error.size() != 0) {
/* 496 */       request.setAttribute("showerror", Boolean.valueOf(true));
/* 497 */       request.setAttribute("error", error);
/*     */     } else {
/* 499 */       request.setAttribute("showerror", Boolean.valueOf(false));
/*     */     } 
/* 501 */     if (error.size() != 0) {
/* 502 */       throw new ActionException("该文件" + filenames + "不存在" + (String)error.get(0));
/*     */     }
/*     */     
/* 505 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/BasicPartLocationAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */