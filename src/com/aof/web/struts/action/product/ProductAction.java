/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.Product;
/*     */ import com.aof.model.product.ProductBelowLine;
/*     */ import com.aof.model.product.ProductBelowlineCasade;
/*     */ import com.aof.model.product.query.ProductBelowLineQueryCondition;
/*     */ import com.aof.model.product.query.ProductBelowLineQueryOrder;
/*     */ import com.aof.model.product.query.ProductQueryCondition;
/*     */ import com.aof.model.product.query.ProductQueryOrder;
/*     */ import com.aof.service.Product.ProductBelowLineManager;
/*     */ import com.aof.service.Product.ProductBelowlineCasadeManager;
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.InventoryQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductBelowLineQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
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
/*     */ public class ProductAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  81 */     ProductQueryForm queryForm = (ProductQueryForm)form;
/*  82 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  83 */       queryForm.setOrder(ProductQueryOrder.ID.getName());
/*  84 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  87 */     ProductManager fm = ServiceLocator.getProductManager(request);
/*  88 */     Map conditions = constructQueryMap(queryForm);
/*  89 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  91 */     String exportType = queryForm.getExportType();
/*  92 */     if (StringUtils.isNotEmpty(exportType)) {
/*  93 */       List data = fm.getProductList(conditions, 0, -1, ProductQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  95 */       int index = SessionTempFile.createNewTempFile(request);
/*  96 */       String fileName = "product";
/*  97 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 100 */               MessageResources messages = ProductAction.this.getResources(request);
/* 101 */               row.add(messages.getMessage(ProductAction.this.getLocale(request), "Product.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 105 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 109 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 111 */     if (queryForm.isFirstInit()) {
/* 112 */       queryForm.init(fm.getProductListCount(conditions));
/*     */     } else {
/* 114 */       queryForm.init();
/*     */     } 
/* 116 */     List result = fm.getProductList(conditions, 
/* 117 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 118 */         ProductQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 120 */     request.setAttribute("X_RESULTLIST", result);
/* 121 */     request.setAttribute("x_selType", Integer.valueOf(8));
/* 122 */     putEnumListToRequest(request);
/* 123 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ProductQueryForm queryForm) {
/* 127 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 128 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 129 */     if (id != null && !id.equals("")) {
/* 130 */       conditions.put(ProductQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 133 */     return conditions;
/*     */   }
/*     */   
/*     */   private Product getProduct(HttpServletRequest request) throws Exception {
/* 137 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 138 */     ProductManager ProductManager = ServiceLocator.getProductManager(request);
/* 139 */     Product Product = ProductManager.getProduct(id);
/* 140 */     if (Product == null)
/* 141 */       throw new ActionException("Product.notFound", id); 
/* 142 */     return Product;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 146 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 162 */     Product Product = getProduct(request);
/*     */     
/* 164 */     request.setAttribute("x_Product", Product);
/* 165 */     if (!isBack(request)) {
/*     */       
/* 167 */       BeanForm ProductForm = (BeanForm)getForm("/updateProduct", request);
/* 168 */       ProductForm.populate(Product, "to_form");
/*     */     } 
/* 170 */     putEnumListToRequest(request);
/* 171 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 172 */     request.setAttribute("x_siteList", siteList);
/* 173 */     return mapping.findForward("page");
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
/* 189 */     BeanForm ProductForm = (BeanForm)form;
/* 190 */     Product Product = new Product();
/* 191 */     ProductForm.populate(Product, "to_bean");
/* 192 */     ProductManager ProductManager = ServiceLocator.getProductManager(request);
/* 193 */     Product = ProductManager.updateProduct(Product);
/*     */     
/* 195 */     request.setAttribute("X_OBJECT", Product);
/* 196 */     request.setAttribute("X_ROWPAGE", "wmsbasic/Product/row.jsp");
/* 197 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 203 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 207 */     String s = request.getParameter("site_id");
/* 208 */     return (s != null && !s.equals(""));
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
/* 225 */     if (!isBack(request)) {
/* 226 */       Product Product = new Product();
/* 227 */       BeanForm ProductForm = (BeanForm)getForm("/insertProduct", request);
/* 228 */       ProductForm.populate(Product, "to_form");
/*     */     } 
/* 230 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/*     */     
/* 232 */     request.setAttribute("x_siteList", siteList);
/* 233 */     putEnumListToRequest(request);
/* 234 */     return mapping.findForward("page");
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
/* 250 */     ProductManager cm = ServiceLocator.getProductManager(request);
/* 251 */     BeanForm ProductForm = (BeanForm)form;
/* 252 */     Product Product = new Product();
/* 253 */     ProductForm.populate(Product, "to_bean");
/* 254 */     Product = cm.insertProduct(Product);
/*     */     
/* 256 */     request.setAttribute("X_OBJECT", Product);
/* 257 */     request.setAttribute("X_ROWPAGE", "wmsbasic/Product/row.jsp");
/* 258 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 266 */     ProductQueryForm queryForm = (ProductQueryForm)form;
/*     */ 
/*     */     
/* 269 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 270 */       queryForm.setOrder(ProductQueryOrder.ID.getName());
/* 271 */       queryForm.setDescend(false);
/*     */     } 
/* 273 */     Map conditions = constructCondtions(queryForm);
/* 274 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 276 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 277 */     if (queryForm.isFirstInit()) {
/* 278 */       queryForm.init(manager.getProductListCount(conditions));
/*     */     } else {
/* 280 */       queryForm.init();
/*     */     } 
/*     */     
/* 283 */     int pageNo = queryForm.getPageNoAsInt();
/* 284 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 286 */     List<Product> list = manager.getProductList(conditions, pageNo, pageSize, ProductQueryOrder.ID, queryForm.isDescend());
/* 287 */     request.setAttribute("X_RESLUTLIST", list);
/* 288 */     request.setAttribute("x_selType", Integer.valueOf(101));
/* 289 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProductOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 295 */     ProductQueryForm queryForm = (ProductQueryForm)form;
/*     */ 
/*     */     
/* 298 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 299 */       queryForm.setOrder(ProductQueryOrder.ID.getName());
/* 300 */       queryForm.setDescend(false);
/*     */     } 
/* 302 */     Map conditions = constructCondtions(queryForm);
/* 303 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 305 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 306 */     if (queryForm.isFirstInit()) {
/* 307 */       queryForm.init(manager.getProductListCount(conditions));
/*     */     } else {
/* 309 */       queryForm.init();
/*     */     } 
/*     */     
/* 312 */     int pageNo = queryForm.getPageNoAsInt();
/* 313 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 315 */     List<Product> list = manager.getProductList(conditions, pageNo, pageSize, ProductQueryOrder.ID, queryForm.isDescend());
/* 316 */     request.setAttribute("X_RESLUTLIST", list);
/* 317 */     request.setAttribute("x_selType", Integer.valueOf(101));
/* 318 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProductIn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 324 */     ProductQueryForm queryForm = (ProductQueryForm)form;
/*     */ 
/*     */     
/* 327 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 328 */       queryForm.setOrder(ProductQueryOrder.ID.getName());
/* 329 */       queryForm.setDescend(false);
/*     */     } 
/* 331 */     Map conditions = constructCondtions(queryForm);
/* 332 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 334 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 335 */     if (queryForm.isFirstInit()) {
/* 336 */       queryForm.init(manager.getProductListCount(conditions));
/*     */     } else {
/* 338 */       queryForm.init();
/*     */     } 
/*     */     
/* 341 */     int pageNo = queryForm.getPageNoAsInt();
/* 342 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 344 */     List<Product> list = manager.getProductList(conditions, pageNo, pageSize, ProductQueryOrder.ID, queryForm.isDescend());
/* 345 */     request.setAttribute("X_RESLUTLIST", list);
/* 346 */     request.setAttribute("x_selType", Integer.valueOf(101));
/* 347 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructCondtions(ProductQueryForm queryForm) {
/* 351 */     Map<Object, Object> condition = new HashMap<Object, Object>();
/* 352 */     String part = queryForm.getPart();
/* 353 */     if (part != null && !part.equals("")) {
/* 354 */       condition.put(ProductQueryCondition.PARTID_EQ, part);
/*     */     }
/*     */     
/* 357 */     return condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 364 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward careateProductOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 370 */     String str = request.getParameter("str");
/* 371 */     String[] arr = str.split(",");
/* 372 */     String partId = arr[0];
/* 373 */     String location = arr[1];
/* 374 */     String date = arr[2];
/* 375 */     String qty = arr[3];
/* 376 */     String id = arr[4];
/* 377 */     User user = getCurrentUser(request);
/*     */     
/* 379 */     WmsPartManager pManager = ServiceLocator.getWmsPartManager(request);
/* 380 */     StorageLocationManager sManager = ServiceLocator.getStorageLocationManager(request);
/*     */     
/* 382 */     WmsPart part = pManager.getWmsPart(partId);
/* 383 */     StorageLocation storageLocation = sManager.getStorageLocation(location);
/* 384 */     SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
/* 385 */     Date newDate = fmt.parse(date);
/* 386 */     Product product = new Product();
/* 387 */     product.setDate(newDate);
/* 388 */     product.setLocation(storageLocation);
/* 389 */     product.setPart(part);
/* 390 */     product.setUserId(user);
/* 391 */     product.setQty(new BigDecimal(qty));
/*     */     
/* 393 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 394 */     manager.insertProduct(product);
/* 395 */     String type = request.getParameter("type");
/* 396 */     ProductBelowLine belowLine = manager.getProductBelowLineById(Integer.valueOf(Integer.parseInt(id)));
/* 397 */     belowLine.setQty(belowLine.getQty().subtract(new BigDecimal(qty)));
/*     */     
/* 399 */     manager.updateProductBelowLine(belowLine);
/* 400 */     if (type.equals("0")) {
/* 401 */       InventoryManager iManager = ServiceLocator.getInventoryManager(request);
/* 402 */       InventoryDetial inventoryDetial = iManager.getInventoryDetialByPartAndLocation(partId, storageLocation.getId());
/* 403 */       inventoryDetial.setNumber(inventoryDetial.getNumber().subtract(new BigDecimal(qty)));
/* 404 */       inventoryDetial.setPart_qty(inventoryDetial.getPart_qty().subtract(new BigDecimal(qty)));
/* 405 */       iManager.updateInventoryDetial(inventoryDetial);
/*     */     } 
/*     */     
/* 408 */     ProductBelowlineCasade casade = new ProductBelowlineCasade();
/* 409 */     casade.setProduct(product);
/* 410 */     casade.setProductBelowLine(belowLine);
/* 411 */     ProductBelowlineCasadeManager pbManager = ServiceLocator.getProductBelowlineCasadeManager(request);
/* 412 */     pbManager.insert(casade);
/*     */     
/* 414 */     return new ActionForward("listProductScanningOutbound.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ProductBelowLine> updateAllProductBelowLines(List<ProductBelowLine> reslut) {
/* 420 */     List<ProductBelowLine> list = new ArrayList<ProductBelowLine>();
/*     */     
/* 422 */     for (ProductBelowLine line : reslut) {
/* 423 */       if (!line.getLocation().getCode().equals("CCP-00A")) {
/* 424 */         list.add(line);
/*     */       }
/*     */     } 
/*     */     
/* 428 */     return list;
/*     */   }
/*     */   
/*     */   private Map constructConditions(ProductBelowLineQueryForm queryForm) {
/* 432 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 433 */     String part = queryForm.getPart();
/* 434 */     if (part != null && !part.equals("")) {
/* 435 */       conditions.put(ProductBelowLineQueryCondition.PARTID_EQ, part);
/*     */     }
/*     */     
/* 438 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProductBelowLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 444 */     ProductBelowLineQueryForm queryForm = (ProductBelowLineQueryForm)form;
/* 445 */     String type = request.getParameter("type");
/*     */     
/* 447 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 448 */       queryForm.setOrder(ProductBelowLineQueryOrder.ID.getName());
/* 449 */       queryForm.setDescend(false);
/*     */     } 
/* 451 */     Map<ProductBelowLineQueryCondition, String> conditions = constructConditions(queryForm);
/* 452 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 453 */     if (type.equals("0")) {
/* 454 */       conditions.put(ProductBelowLineQueryCondition.LOCATION_EQ, "CCP-00A");
/*     */     }
/*     */     
/* 457 */     ProductBelowLineManager manager = ServiceLocator.getProductBelowLineManager(request);
/*     */     
/* 459 */     int pageNo = queryForm.getPageNoAsInt();
/* 460 */     int pageSize = queryForm.getPageSizeAsInt();
/* 461 */     List<ProductBelowLine> list = manager.getProductBelowLineList(conditions, pageNo, pageSize, ProductBelowLineQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 463 */     if (type.equals("1")) {
/* 464 */       list = updateAllProductBelowLines(list);
/* 465 */       request.setAttribute("other", Integer.valueOf(1));
/*     */     } else {
/* 467 */       request.setAttribute("other", Integer.valueOf(0));
/*     */     } 
/*     */     
/* 470 */     if (queryForm.isFirstInit()) {
/* 471 */       queryForm.init(list.size());
/*     */     } else {
/* 473 */       queryForm.init();
/*     */     } 
/* 475 */     request.setAttribute("X_RESULTLIST", list);
/* 476 */     request.setAttribute("user", getCurrentUser(request));
/* 477 */     request.setAttribute("x_selType", Integer.valueOf(200));
/* 478 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructConditions1(InventoryQueryForm queryForm) {
/* 482 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 483 */     String part = queryForm.getPart();
/*     */     
/* 485 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printOutBoundCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 496 */     String str = request.getParameter("str");
/* 497 */     String[] arr = str.split(",");
/* 498 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 499 */     List<Product> list = new ArrayList<Product>();
/* 500 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss"); byte b; int i; String[] arrayOfString1;
/* 501 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 502 */       Product product = manager.getProduct(Integer.valueOf(Integer.parseInt(id)));
/* 503 */       list.add(product); b++; }
/*     */     
/* 505 */     Date date = new Date();
/* 506 */     String code = format.format(date);
/* 507 */     request.setAttribute("code", code);
/*     */     
/* 509 */     request.setAttribute("path", request.getContextPath());
/* 510 */     request.setAttribute("X_RESULTLIST", list);
/*     */ 
/*     */ 
/*     */     
/* 514 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward updateProductOutStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 522 */     String strId = request.getParameter("ids");
/* 523 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 524 */     String[] ids = strId.split(","); byte b; int i; String[] arrayOfString1;
/* 525 */     for (i = (arrayOfString1 = ids).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 526 */       Product product = manager.getProduct(Integer.valueOf(Integer.parseInt(id)));
/* 527 */       product.setIsPrint(YesNo.YES);
/* 528 */       manager.updateProduct(product);
/*     */       b++; }
/*     */     
/* 531 */     return new ActionForward("listProductScanningOutbound.do", true);
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
/*     */   public ActionForward cancleProductOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 543 */     ProductBelowlineCasadeManager pManager = ServiceLocator.getProductBelowlineCasadeManager(request);
/* 544 */     ProductBelowLineManager bManager = ServiceLocator.getProductBelowLineManager(request);
/* 545 */     InventoryManager iManager = ServiceLocator.getInventoryManager(request);
/* 546 */     ProductManager manager = ServiceLocator.getProductManager(request);
/* 547 */     String str = request.getParameter("str");
/*     */     
/* 549 */     String[] arr = str.split(","); byte b; int i; String[] arrayOfString1;
/* 550 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 551 */       ProductBelowlineCasade productBelowlineCasadeByProduct = pManager.getProductBelowlineCasadeByProduct(Integer.valueOf(Integer.parseInt(id)));
/* 552 */       ProductBelowLine productBelowLine = productBelowlineCasadeByProduct.getProductBelowLine();
/* 553 */       Product product = productBelowlineCasadeByProduct.getProduct();
/* 554 */       BigDecimal qty = product.getQty();
/* 555 */       productBelowLine.setQty(productBelowLine.getQty().add(qty));
/* 556 */       bManager.updateProductBelowLine(productBelowLine);
/* 557 */       if (product.getLocation().getCode().equals("CCP-00A")) {
/* 558 */         InventoryDetial inventoryDetial = iManager.getInventoryDetialByPartAndLocation(product.getPart().getId(), product.getLocation().getId());
/* 559 */         inventoryDetial.setNumber(inventoryDetial.getNumber().add(qty));
/* 560 */         inventoryDetial.setPart_qty(inventoryDetial.getPart_qty().add(qty));
/* 561 */         iManager.updateInventoryDetial(inventoryDetial);
/*     */       } 
/*     */       
/* 564 */       pManager.delete(productBelowlineCasadeByProduct);
/* 565 */       manager.delete(product);
/*     */       b++; }
/*     */     
/* 568 */     return new ActionForward("listProductScanningOutbound.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/ProductAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */