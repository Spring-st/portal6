/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.query.SupplierQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierQueryOrder;
/*     */ import com.aof.model.basic.SupplierPart;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.SupplierPartQueryCondition;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.admin.SupplierPartQueryForm;
/*     */ import com.aof.web.struts.form.admin.SupplierQueryForm;
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
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
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
/*     */ import jxl.write.Colour;
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
/*     */ import net.sf.json.JsonConfig;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupplierPartAction
/*     */   extends BaseAction
/*     */ {
/*     */   private File file;
/*     */   private String filename;
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  96 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/*     */     
/*  98 */     Integer id = ActionUtils.parseInt(request.getParameter("supplierId"));
/*  99 */     Supplier supplier = um.getSupplier(id);
/* 100 */     request.setAttribute("X_SUPPLIER", supplier);
/* 101 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 102 */     conditions.put(SupplierPartQueryCondition.SUPPLIER_ID_EQ, id);
/*     */     
/* 104 */     List sublist = um.getEnabledSupplierPartList(conditions, 1, -1, null, false);
/* 105 */     request.setAttribute("X_SUBLIST", sublist);
/* 106 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 112 */     SupplierPartQueryForm queryForm = (SupplierPartQueryForm)form;
/* 113 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 114 */       queryForm.setDescend(false);
/*     */     }
/*     */     
/* 117 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 118 */     Map conditions = constructQueryMap(queryForm);
/* 119 */     if (queryForm.isFirstInit()) {
/* 120 */       queryForm.init(manager.getSupplierPartListCount(conditions));
/*     */     } else {
/* 122 */       queryForm.init();
/*     */     } 
/*     */     
/* 125 */     List result = manager.getEnabledSupplierPartList(conditions, 
/* 126 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 127 */         SupplierQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 129 */     List list = manager.updateSupplierProportion(result);
/*     */     
/* 131 */     request.setAttribute("X_RESULTLIST", list);
/* 132 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 133 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(SupplierPartQueryForm queryForm) {
/* 137 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 138 */     String id = queryForm.getId();
/*     */     
/* 140 */     if (id != null && !id.equals("")) {
/* 141 */       conditions.put(SupplierPartQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 144 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward supplierPartProportionByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 150 */     response.setContentType("text/json");
/* 151 */     response.setCharacterEncoding("UTF-8");
/* 152 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 154 */     String part = request.getParameter("part");
/* 155 */     String code = request.getParameter("code");
/* 156 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 157 */     Supplier supplier = manager.getSupplierByCode(code);
/*     */     
/* 159 */     boolean returnValue = manager.validateSupplierPartProportion(supplier, part);
/*     */ 
/*     */     
/* 162 */     if (!returnValue) {
/* 163 */       BigDecimal amount = manager.getSupplierPartProportion(supplier, part);
/* 164 */       JSONArray jo = JSONArray.fromObject(amount, cfg);
/* 165 */       response.getWriter().print(jo);
/*     */     } else {
/* 167 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(returnValue), cfg);
/* 168 */       response.getWriter().print(jo);
/*     */     } 
/*     */     
/* 171 */     return null;
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 186 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/* 187 */     if (!isBack(request)) {
/* 188 */       Integer id = ActionUtils.parseInt(request.getParameter("supplierId"));
/* 189 */       Supplier supplier = um.getSupplier(id);
/* 190 */       request.setAttribute("X_supplierList", supplier);
/* 191 */       SupplierPart ur = new SupplierPart();
/* 192 */       ur.setSupplierId(supplier);
/* 193 */       BeanForm supplierPartForm = (BeanForm)getForm("/insertSupplierPart", request);
/* 194 */       supplierPartForm.populate(ur, "to_form");
/*     */     } 
/*     */     
/* 197 */     WmsPartManager wp = ServiceLocator.getWmsPartManager(request);
/* 198 */     List<WmsPart> wpList = wp.getWmsPartListEnabledAll();
/* 199 */     request.setAttribute("X_wpList", wpList);
/* 200 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 215 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/* 216 */     BeanForm supplierPartForm = (BeanForm)form;
/*     */     
/* 218 */     Integer id = ActionUtils.parseInt(request.getParameter("supplierId_id"));
/* 219 */     String partId = request.getParameter("partId_id");
/* 220 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 221 */     conditions.put(SupplierPartQueryCondition.SUPPLIER_ID_EQ, id);
/* 222 */     List<SupplierPart> sublist = um.getEnabledSupplierPartList(conditions, 1, -1, null, false);
/*     */     
/* 224 */     for (SupplierPart supPart : sublist) {
/* 225 */       if (supPart.getPartId().getId().equals(partId)) {
/* 226 */         throw new ActionException("supplierPart.exist", partId);
/*     */       }
/*     */     } 
/* 229 */     SupplierPart ur = new SupplierPart();
/* 230 */     supplierPartForm.populateToBean(ur);
/*     */     
/* 232 */     request.setAttribute("X_OBJECT", um.insertSupplierPart(ur));
/* 233 */     request.setAttribute("X_ROWPAGE", "wmsbasic/supplierPart/row.jsp");
/* 234 */     return mapping.findForward("success");
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 249 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/* 250 */     if (!isBack(request)) {
/*     */       
/* 252 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 253 */       SupplierPart ur = um.getSupplierPart(id);
/* 254 */       if (ur == null) throw new ActionException("userRole.notFound", id); 
/* 255 */       request.setAttribute("X_supplierList", ur);
/*     */       
/* 257 */       BeanForm supplierPartForm = (BeanForm)getForm("/updateSupplierPart", request);
/* 258 */       supplierPartForm.populateToForm(ur);
/*     */ 
/*     */       
/* 261 */       BigDecimal amount = um.getSupplierPartProportion(ur.getSupplierId(), ur.getPartId().getId());
/* 262 */       request.setAttribute("x_alreadyAmount", amount);
/*     */     } 
/* 264 */     WmsPartManager rm = ServiceLocator.getWmsPartManager(request);
/* 265 */     List wpList = rm.getWmsPartListEnabledAll();
/*     */     
/* 267 */     request.setAttribute("X_wpList", wpList);
/* 268 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 283 */     BeanForm craftForm = (BeanForm)form;
/* 284 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/* 285 */     String id = request.getParameter("id");
/* 286 */     SupplierPart supplierPart = um.getSupplierPart(Integer.valueOf(Integer.parseInt(id)));
/* 287 */     craftForm.populate(supplierPart, "to_bean");
/*     */     
/* 289 */     SupplierManager craftManager = ServiceLocator.getSupplierManager(request);
/*     */     
/* 291 */     request.setAttribute("X_OBJECT", craftManager.updateSupplierPart(supplierPart));
/* 292 */     request.setAttribute("X_ROWPAGE", "wmsbasic/supplierPart/row.jsp");
/* 293 */     return mapping.findForward("success");
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
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 308 */     SupplierManager um = ServiceLocator.getSupplierManager(request);
/* 309 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 310 */     SupplierPart ur = um.getSupplierPart(id);
/* 311 */     if (ur == null) {
/* 312 */       throw new ActionException("userRole.notFound", id);
/*     */     }
/* 314 */     um.deleteSupplierPart(ur);
/* 315 */     return new ActionForward("listSupplierPart.do?supplierId=" + ur.getSupplierId().getId(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listSupplierByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 321 */     String partId = request.getParameter("partId");
/* 322 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 323 */     List list = manager.getSupplierPart(partId);
/*     */     
/* 325 */     request.setAttribute("x_result", list);
/* 326 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward selectSupplierByPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 330 */     SupplierQueryForm queryForm = (SupplierQueryForm)form;
/* 331 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 332 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 333 */       queryForm.setOrder(SupplierQueryOrder.ID.getName());
/* 334 */       queryForm.setDescend(false);
/*     */     } 
/* 336 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 337 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 338 */     conditions.put(SupplierQueryCondition.CODE_LIKE, "LD");
/*     */     try {
/* 340 */       if (queryForm.isFirstInit()) {
/* 341 */         queryForm.init(manager.getSupplierListCount(conditions));
/*     */       } else {
/* 343 */         queryForm.init();
/*     */       } 
/* 345 */       List<Supplier> supplierList = manager.getSupplierList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 346 */       request.setAttribute("X_RESULTLIST", supplierList);
/* 347 */     } catch (Exception e) {
/*     */       
/* 349 */       e.printStackTrace();
/*     */     } 
/* 351 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward exportsSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 357 */     List<Character> chars = new ArrayList();
/* 358 */     int charsDouble = 0;
/* 359 */     for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
/* 360 */       chars.add(Character.valueOf(i));
/*     */     }
/*     */     
/*     */     try {
/* 364 */       DateFormatter format = new DateFormatter(Date.class, 
/* 365 */           "HHmmssSS");
/* 366 */       String name = String.valueOf(format.format(new Date())) + ".xls";
/* 367 */       if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
/* 368 */         (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
/*     */       }
/* 370 */       File f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" + 
/* 371 */           name);
/* 372 */       while (f.exists()) {
/* 373 */         Thread.sleep(100L);
/* 374 */         name = String.valueOf(format.format(new Date())) + ".xls";
/* 375 */         f = new File(String.valueOf(getSys_url()) + "temp/partLocationImportTemplate" + 
/* 376 */             name);
/*     */       } 
/* 378 */       WritableWorkbook wwb = Workbook.createWorkbook(f);
/* 379 */       WritableSheet ws = wwb.createSheet("供应商物料质检比例", 0);
/*     */       
/* 381 */       Label lable = null;
/* 382 */       WritableSheet sheet = wwb.getSheet(0);
/* 383 */       sheet.setColumnView(0, 15);
/* 384 */       sheet.setColumnView(1, 20);
/* 385 */       NumberFormat nf = new NumberFormat("#.###");
/* 386 */       WritableFont host = new WritableFont(WritableFont.ARIAL, 
/* 387 */           15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 388 */           Colour.CORAL);
/* 389 */       WritableFont red = new WritableFont(WritableFont.ARIAL, 
/* 390 */           10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/* 391 */           Colour.RED);
/*     */       
/* 393 */       WritableCellFormat hosts = new WritableCellFormat(host);
/* 394 */       hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 395 */       hosts.setWrap(true);
/* 396 */       hosts.setAlignment(Alignment.CENTRE);
/* 397 */       hosts.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 399 */       WritableCellFormat title = new WritableCellFormat();
/* 400 */       title.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 401 */       title.setBackground(Colour.ORANGE);
/* 402 */       title.setWrap(true);
/* 403 */       title.setAlignment(Alignment.CENTRE);
/* 404 */       title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */ 
/*     */       
/* 407 */       WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
/* 408 */       functions.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 409 */       functions.setWrap(true);
/* 410 */       functions.setAlignment(Alignment.RIGHT);
/* 411 */       functions.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 413 */       WritableCellFormat cellFormat = new WritableCellFormat();
/* 414 */       cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 415 */       cellFormat.setWrap(true);
/* 416 */       cellFormat.setAlignment(Alignment.CENTRE);
/* 417 */       cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 419 */       WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
/* 420 */       reds.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 421 */       reds.setFont((FontRecord)red);
/* 422 */       reds.setWrap(true);
/* 423 */       reds.setAlignment(Alignment.CENTRE);
/* 424 */       reds.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */       
/* 426 */       ws = createheadtoImportNew(ws, lable, title, hosts, sheet);
/*     */       
/* 428 */       WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
/* 429 */       format3.setWrap(true);
/* 430 */       format3.setAlignment(Alignment.RIGHT);
/* 431 */       format3.setVerticalAlignment(VerticalAlignment.CENTRE);
/* 432 */       format3.setBorder(Border.ALL, BorderLineStyle.THIN);
/*     */       
/* 434 */       wwb.write();
/* 435 */       wwb.close();
/* 436 */       response.sendRedirect("temp/partLocationImportTemplate" + name);
/* 437 */       return mapping.findForward("page");
/* 438 */     } catch (Exception e) {
/* 439 */       throw new ActionException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableSheet createheadtoImportNew(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet) throws RowsExceededException, WriteException {
/* 447 */     List<String> arrhead = new ArrayList<String>();
/*     */ 
/*     */     
/* 450 */     for (int s = 0; s < arrhead.size(); s++) {
/* 451 */       lable = new Label(s, 4, arrhead.get(s), (CellFormat)cellFormat);
/* 452 */       ws.addCell((WritableCell)lable);
/*     */     } 
/*     */     
/* 455 */     WritableCellFormat mainBody = new WritableCellFormat();
/* 456 */     mainBody.setBorder(Border.ALL, BorderLineStyle.THIN);
/* 457 */     mainBody.setBackground(Colour.WHITE);
/* 458 */     mainBody.setWrap(true);
/* 459 */     mainBody.setAlignment(Alignment.RIGHT);
/* 460 */     mainBody.setVerticalAlignment(VerticalAlignment.CENTRE);
/*     */     
/* 462 */     lable = new Label(0, 0, "供应商编码", (CellFormat)cellFormat);
/* 463 */     ws.addCell((WritableCell)lable);
/* 464 */     lable = new Label(1, 0, "物料号", (CellFormat)cellFormat);
/* 465 */     ws.addCell((WritableCell)lable);
/* 466 */     lable = new Label(2, 0, "质检比例", (CellFormat)cellFormat);
/* 467 */     ws.addCell((WritableCell)lable);
/*     */     
/* 469 */     sheet.mergeCells(0, 0, 0, 0);
/* 470 */     return ws;
/*     */   }
/*     */   
/*     */   private String getSys_url() {
/* 474 */     String strClassName = getClass().getName();
/* 475 */     String strPackageName = "";
/* 476 */     if (getClass().getPackage() != null) {
/* 477 */       strPackageName = getClass().getPackage().getName();
/*     */     }
/* 479 */     String strClassFileName = "";
/* 480 */     if (!"".equals(strPackageName)) {
/* 481 */       strClassFileName = strClassName.substring(
/* 482 */           strPackageName.length() + 1, strClassName.length());
/*     */     } else {
/* 484 */       strClassFileName = strClassName;
/*     */     } 
/* 486 */     URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
/* 487 */     String strURL = url.toString();
/* 488 */     strURL = strURL.substring(strURL.indexOf('/') + 1, 
/* 489 */         strURL.lastIndexOf('/'));
/* 490 */     return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward showImportSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 497 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward upLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 504 */     SupplierPartQueryForm uFrom = (SupplierPartQueryForm)form;
/* 505 */     String filenames = "";
/* 506 */     PrintWriter out = null;
/*     */     try {
/* 508 */       this.file = new File(this.servlet.getServletContext().getRealPath(
/* 509 */             "/temp/importfile/"));
/* 510 */       if (!this.file.exists()) {
/* 511 */         this.file.mkdir();
/*     */       }
/* 513 */       request.setAttribute("filenames", String.valueOf(this.file.getPath()) + "\\" + uFrom.getMyFile().getFileName());
/* 514 */       response.setCharacterEncoding("GBK");
/* 515 */       out = response.getWriter();
/* 516 */       saveFile(uFrom.getMyFile(), this.file);
/* 517 */     } catch (Exception e) {
/* 518 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 521 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveFile(FormFile formFile, File file) throws Exception {
/*     */     try {
/* 528 */       String path = file.getPath();
/*     */       
/* 530 */       InputStream in = formFile.getInputStream();
/*     */       
/* 532 */       FileOutputStream fout = new FileOutputStream(String.valueOf(path) + "\\" + formFile.getFileName());
/* 533 */       this.filename = String.valueOf(path) + "\\" + formFile.getFileName();
/*     */       
/* 535 */       byte[] buffer = new byte[8192];
/* 536 */       int count = 0;
/*     */       
/* 538 */       while ((count = in.read(buffer)) > 0) {
/* 539 */         fout.write(buffer, 0, count);
/*     */       }
/* 541 */       fout.close();
/* 542 */       formFile.destroy();
/* 543 */     } catch (Exception e) {
/* 544 */       throw new ActionException("该文件" + this.filename + "无法上传");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward importeSupplierPartSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
/* 551 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 552 */     List<String> error = new ArrayList<String>();
/* 553 */     Workbook wb = null;
/* 554 */     String filenames = "";
/*     */     try {
/* 556 */       filenames = request.getAttribute("filenames").toString();
/* 557 */       wb = Workbook.getWorkbook(new File(filenames));
/* 558 */     } catch (Exception e) {
/* 559 */       throw new ActionException("该文件" + filenames + "不存在");
/*     */     } 
/* 561 */     Sheet[] sheet = wb.getSheets();
/* 562 */     if (sheet != null && sheet.length > 0) {
/* 563 */       manager.insertSupplierPartSamplingRatio(sheet, getCurrentUser(request));
/*     */     }
/* 565 */     if (error.size() != 0) {
/* 566 */       request.setAttribute("showerror", Boolean.valueOf(true));
/* 567 */       request.setAttribute("error", error);
/*     */     } else {
/* 569 */       request.setAttribute("showerror", Boolean.valueOf(false));
/*     */     } 
/* 571 */     if (error.size() != 0) {
/* 572 */       throw new ActionException("该文件" + filenames + "不存在" + (String)error.get(0));
/*     */     }
/*     */     
/* 575 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listSamplingRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 581 */     SupplierPartQueryForm queryForm = (SupplierPartQueryForm)form;
/* 582 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 583 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 584 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 587 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 588 */     Map conditions = constructQueryMap(queryForm);
/* 589 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 591 */     String exportType = queryForm.getExportType();
/* 592 */     if (StringUtils.isNotEmpty(exportType)) {
/* 593 */       List data = manager.getSupplierPartSamplingRatioList(conditions, 0, -1, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 595 */       int index = SessionTempFile.createNewTempFile(request);
/* 596 */       String fileName = "purchaseOrder";
/* 597 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 600 */               MessageResources messages = SupplierPartAction.this.getResources(request);
/* 601 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "supplierId.code"));
/* 602 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "supplierId.name"));
/* 603 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.id"));
/* 604 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.describe1"));
/* 605 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.describe2"));
/* 606 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "part.unit"));
/* 607 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "date"));
/* 608 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "qty"));
/* 609 */               row.add(messages.getMessage(SupplierPartAction.this.getLocale(request), "enabled.chnShortDescription"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 613 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplierId.code"));
/* 614 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplierId.name"));
/* 615 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 616 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 617 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 618 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.unit"));
/* 619 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 620 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 621 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 626 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 628 */     if (queryForm.isFirstInit()) {
/* 629 */       queryForm.init(manager.getSupplierPartSamplingRatioCount(conditions));
/*     */     } else {
/* 631 */       queryForm.init();
/*     */     } 
/* 633 */     List result = manager.getSupplierPartSamplingRatioList(conditions, 
/* 634 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 635 */         SupplierQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 637 */     request.setAttribute("X_RESULTLIST", result);
/* 638 */     request.setAttribute("x_selType", Integer.valueOf(30));
/* 639 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/SupplierPartAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */