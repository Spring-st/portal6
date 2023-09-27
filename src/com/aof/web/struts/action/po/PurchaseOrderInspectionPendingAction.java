/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.PortalShipOrderItem;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*     */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*     */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.po.PortalShipOrderItemManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.PurchaseOrderInspectionPendingItemQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
/*     */ public class PurchaseOrderInspectionPendingAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward listSupplierPurchaseOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  74 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/*  75 */     PurchaseOrderInspectionPendingManager fm = 
/*  76 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  77 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  78 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  79 */     String exportType = queryForm.getExportType();
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
/* 130 */     if (StringUtils.isNotEmpty(exportType)) {
/* 131 */       List data = fm.getPurchaseOrderInspectionPendingItemList(conditions, 0, -1, PurchaseOrderInspectionPendingQueryOrder.ID, queryForm.isDescend());
/* 132 */       int index = SessionTempFile.createNewTempFile(request);
/* 133 */       String fileName = "purchaseOrder";
/* 134 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 138 */               MessageResources messages = PurchaseOrderInspectionPendingAction.this.getResources(request);
/*     */               
/* 140 */               row.add("采购单号");
/* 141 */               row.add("供应商");
/* 142 */               row.add("供应商编号");
/* 143 */               row.add("行号");
/* 144 */               row.add("物料编号");
/* 145 */               row.add("单位");
/* 146 */               row.add("采购数量");
/* 147 */               row.add("交货日期");
/* 148 */               row.add("未发货数量");
/* 149 */               row.add("订单下达日期");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 154 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.po_number"));
/* 155 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.name"));
/* 156 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.code"));
/* 157 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/* 158 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.id"));
/* 159 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.unit"));
/* 160 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 161 */               row.add(BeanHelper.getBeanPropertyValue(data, "dueDate"));
/* 162 */               row.add(BeanHelper.getBeanPropertyValue(data, "qtyOpen"));
/* 163 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.createDate"));
/*     */             }
/*     */           });
/*     */       
/* 167 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 169 */     if (queryForm.isFirstInit()) {
/* 170 */       queryForm.init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*     */     } else {
/*     */       
/* 173 */       queryForm.init();
/*     */     } 
/*     */     
/* 176 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/* 177 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 178 */         PurchaseOrderInspectionPendingQueryOrder.ID, queryForm
/* 179 */         .isDescend());
/* 180 */     fm.getPurchaseOrderInspectionPendingItemReport(result);
/* 181 */     request.setAttribute("X_YESNOLIST", 
/* 182 */         PersistentEnum.getEnumList(YesNo.class));
/*     */ 
/*     */     
/* 185 */     request.setAttribute("X_RESULTLIST", result);
/* 186 */     request.setAttribute("x_selType", Integer.valueOf(160));
/* 187 */     return mapping.findForward("page");
/*     */   }
/*     */   private Object[] getQuerySiteIdList(List<Site> siteList) {
/* 190 */     List<Integer> idList = new ArrayList();
/* 191 */     for (Site site : siteList) {
/* 192 */       idList.add(site.getId());
/*     */     }
/* 194 */     return idList.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listHeBing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 200 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 201 */     PurchaseOrderInspectionPendingManager fm = 
/* 202 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 203 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 204 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 205 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/* 206 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.SITE_ID_IN, getQuerySiteIdList(siteList));
/* 207 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(1));
/* 208 */     String exportType = queryForm.getExportType();
/* 209 */     if (StringUtils.isNotEmpty(exportType)) {
/* 210 */       List data = fm.getPurchaseOrderInspectionPendingItemList(conditions, 0, -1, PurchaseOrderInspectionPendingQueryOrder.ID, queryForm.isDescend());
/* 211 */       int index = SessionTempFile.createNewTempFile(request);
/* 212 */       String fileName = "purchaseOrderHeBing";
/* 213 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 217 */               MessageResources messages = PurchaseOrderInspectionPendingAction.this.getResources(request);
/*     */ 
/*     */               
/* 220 */               row.add("供应商编号");
/* 221 */               row.add("物料编号");
/* 222 */               row.add("物料描述");
/* 223 */               row.add("数量");
/* 224 */               row.add("单位");
/* 225 */               row.add("类型");
/* 226 */               row.add("2小时需求数量");
/* 227 */               row.add("4小时需求数量");
/* 228 */               row.add("6小时需求数量");
/* 229 */               row.add("8小时需求数量");
/* 230 */               row.add("10小时需求数量");
/* 231 */               row.add("12小时需求数量");
/* 232 */               row.add("14小时需求数量");
/* 233 */               row.add("16小时需求数量");
/* 234 */               row.add("18小时需求数量");
/* 235 */               row.add("20小时需求数量");
/* 236 */               row.add("22小时需求数量");
/* 237 */               row.add("24小时需求数量");
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 243 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.code"));
/* 244 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.id"));
/* 245 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.describe1"));
/* 246 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 247 */               row.add(BeanHelper.getBeanPropertyValue(data, "um"));
/* 248 */               PurchaseOrderInspectionPendingItem pending = (PurchaseOrderInspectionPendingItem)data;
/* 249 */               if (pending.getPoip_number().getCreateType().intValue() == 1) {
/* 250 */                 row.add("JIT");
/* 251 */               } else if (pending.getPoip_number().getCreateType().intValue() == 2) {
/* 252 */                 row.add("NJIT_PO");
/* 253 */               } else if (pending.getPoip_number().getCreateType().intValue() == 3) {
/* 254 */                 row.add("NJIT_NPO");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 259 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 261 */     if (queryForm.isFirstInit()) {
/*     */ 
/*     */ 
/*     */       
/* 265 */       queryForm.init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*     */     } else {
/* 267 */       queryForm.init();
/*     */     } 
/*     */     
/* 270 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/* 271 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 272 */         PurchaseOrderInspectionPendingQueryOrder.ID, queryForm
/* 273 */         .isDescend());
/* 274 */     fm.getPurchaseOrderInspectionPendingItemReport(result);
/* 275 */     request.setAttribute("X_YESNOLIST", 
/* 276 */         PersistentEnum.getEnumList(YesNo.class));
/*     */ 
/*     */     
/* 279 */     request.setAttribute("X_RESULTLIST", result);
/* 280 */     request.setAttribute("x_selType", Integer.valueOf(171));
/* 281 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map consturctMap(PurchaseOrderInspectionPendingItemQueryForm queryForm) {
/* 284 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 285 */     String po = queryForm.getPoip_number();
/* 286 */     if (po != null && !po.equals("")) {
/* 287 */       map.put(PurchaseOrderInspectionPendingQueryCondition.PO_ID_EQ, po);
/*     */     }
/*     */     
/* 290 */     String part = queryForm.getItemNumber();
/* 291 */     if (part == null || !part.equals(""));
/* 292 */     map.put(PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, part);
/*     */ 
/*     */     
/* 295 */     String dueDate = queryForm.getDueDate();
/* 296 */     if (dueDate != null && !dueDate.equals("")) {
/* 297 */       map.put(PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, dueDate);
/*     */     }
/*     */ 
/*     */     
/* 301 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewBySupplierPurchaseOrderItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 311 */     String itemId = request.getParameter("id");
/* 312 */     Integer id = Integer.valueOf(Integer.parseInt(itemId));
/*     */     
/* 314 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*     */     
/* 316 */     PurchaseOrderInspectionPendingItem item = fm.getPurchaseOrderInspectionPendingItem(id);
/* 317 */     fm.getPurchaseOrderInspectionPendingItem(item);
/*     */ 
/*     */     
/* 320 */     PortalShipOrderItemManager pm = ServiceLocator.getPortalShipOrderItemManager(request);
/* 321 */     Map<Object, Object> condition = new HashMap<Object, Object>();
/* 322 */     int purchaseOrderId = item.getId().intValue();
/* 323 */     condition.put(PortalShipOrderQueryCondition.PO_ITEM_EQ, Integer.valueOf(purchaseOrderId));
/* 324 */     List<PortalShipOrderItem> list = pm.getPortalShipOrderItemList(condition, 0, -1, null, false);
/* 325 */     BigDecimal actual = new BigDecimal(0);
/* 326 */     if (list != null && list.size() > 0) {
/* 327 */       for (PortalShipOrderItem portalShipOrderItem : list) {
/* 328 */         if (portalShipOrderItem.getActual_qty() != null) {
/* 329 */           BigDecimal ac = portalShipOrderItem.getActual_qty();
/* 330 */           actual = actual.add(ac);
/*     */         } 
/*     */       } 
/*     */     }
/* 334 */     item.setReceiptQty(actual);
/*     */     
/* 336 */     request.setAttribute("item", item);
/* 337 */     return mapping.findForward("page");
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
/*     */   public ActionForward updateQtyStd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 353 */     String std = request.getParameter("qtyStd");
/* 354 */     String itemId = request.getParameter("id");
/*     */     
/* 356 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 357 */     PurchaseOrderInspectionPendingItem item = fm.getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(itemId)));
/* 358 */     item.setQty_std(new BigDecimal(std));
/* 359 */     fm.updatePurchaseOrderInspectionPendingItem(item);
/* 360 */     return new ActionForward("viewBySupplierPurchaseOrderItem.do?id=" + item.getId(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listSupplierPurchaseOrderWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 367 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 368 */     PurchaseOrderInspectionPendingManager fm = 
/* 369 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 370 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 371 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 372 */     String exportType = queryForm.getExportType();
/*     */     
/* 374 */     User user = getCurrentUser(request);
/* 375 */     String supplierNo = user.getLoginName();
/* 376 */     SupplierManager sManager = ServiceLocator.getSupplierManager(request);
/* 377 */     Supplier supplier = sManager.getSupplierByCode(supplierNo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 383 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(3));
/* 384 */     List<PurchaseOrderInspectionPendingItem> dataList = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/* 385 */         0, -1, 
/* 386 */         PurchaseOrderInspectionPendingQueryOrder.ID, queryForm
/* 387 */         .isDescend());
/* 388 */     List<PurchaseOrderInspectionPendingItem> list = new ArrayList();
/* 389 */     for (int i = 0; i < dataList.size(); i++) {
/* 390 */       PurchaseOrderInspectionPendingItem item = dataList.get(i);
/* 391 */       int qty = 0;
/* 392 */       int inventoryNumber = 0;
/* 393 */       int returnNumber = 0;
/* 394 */       if (item.getQty() == null) {
/* 395 */         qty = 0;
/*     */       } else {
/* 397 */         qty = item.getQty().intValue();
/*     */       } 
/* 399 */       if (item.getInventoryNumber() == null) {
/* 400 */         inventoryNumber = 0;
/*     */       } else {
/* 402 */         inventoryNumber = item.getInventoryNumber().intValue();
/*     */       } 
/* 404 */       if (item.getReturnNumber() == null) {
/* 405 */         returnNumber = 0;
/*     */       } else {
/* 407 */         returnNumber = item.getReturnNumber().intValue();
/*     */       } 
/* 409 */       if (qty - inventoryNumber + returnNumber > 0) {
/* 410 */         list.add(item);
/*     */       }
/*     */     } 
/*     */     
/* 414 */     if (StringUtils.isNotEmpty(exportType)) {
/* 415 */       List data = fm.getPurchaseOrderInspectionPendingItemList(conditions, 0, -1, PurchaseOrderInspectionPendingQueryOrder.ID, queryForm.isDescend());
/* 416 */       int index = SessionTempFile.createNewTempFile(request);
/* 417 */       String fileName = "purchaseOrder";
/* 418 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 422 */               MessageResources messages = PurchaseOrderInspectionPendingAction.this.getResources(request);
/*     */               
/* 424 */               row.add("采购单号");
/* 425 */               row.add("供应商");
/* 426 */               row.add("供应商编号");
/* 427 */               row.add("行号");
/* 428 */               row.add("物料编号");
/* 429 */               row.add("DPI");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 434 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.po_number"));
/* 435 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.name"));
/* 436 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.code"));
/* 437 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/* 438 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.id"));
/* 439 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.dpiNo"));
/*     */             }
/*     */           });
/* 442 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 444 */     if (queryForm.isFirstInit()) {
/*     */ 
/*     */ 
/*     */       
/* 448 */       queryForm.init(list.size());
/*     */     } else {
/* 450 */       queryForm.init();
/*     */     } 
/*     */     
/* 453 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/* 454 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 455 */         PurchaseOrderInspectionPendingQueryOrder.ID, queryForm
/* 456 */         .isDescend());
/* 457 */     fm.getPurchaseOrderInspectionPendingItemReport(result);
/* 458 */     request.setAttribute("X_YESNOLIST", 
/* 459 */         PersistentEnum.getEnumList(YesNo.class));
/*     */ 
/*     */     
/* 462 */     request.setAttribute("X_RESULTLIST", result);
/* 463 */     request.setAttribute("x_selType", Integer.valueOf(168));
/* 464 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewBySupplierPurchaseOrderItemWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 471 */     String itemId = request.getParameter("id");
/* 472 */     Integer id = Integer.valueOf(Integer.parseInt(itemId));
/*     */     
/* 474 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*     */     
/* 476 */     PurchaseOrderInspectionPendingItem item = fm.getPurchaseOrderInspectionPendingItem(id);
/* 477 */     fm.getPurchaseOrderInspectionPendingItem(item);
/* 478 */     request.setAttribute("item", item);
/* 479 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward updateQtyStdWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 485 */     String std = request.getParameter("qtyStd");
/* 486 */     String itemId = request.getParameter("id");
/*     */     
/* 488 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 489 */     PurchaseOrderInspectionPendingItem item = fm.getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(itemId)));
/* 490 */     item.setQty_std(new BigDecimal(std));
/* 491 */     fm.updatePurchaseOrderInspectionPendingItem(item);
/*     */     
/* 493 */     return new ActionForward("viewBySupplierPurchaseOrderItemWrong.do?id=" + item.getId(), true);
/*     */   }
/*     */   
/*     */   public ActionForward drexcelmoban(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 497 */     String id = request.getParameter("id");
/*     */     
/* 499 */     HSSFWorkbook wb = new HSSFWorkbook();
/*     */     
/* 501 */     HSSFSheet sheet = wb.createSheet();
/* 502 */     wb.setSheetName(0, "PO计划查看模版", (short)1);
/*     */     
/* 504 */     sheet.setColumnWidth((short)0, (short)4000);
/* 505 */     sheet.setColumnWidth((short)1, (short)7000);
/* 506 */     sheet.setColumnWidth((short)2, (short)4000);
/* 507 */     sheet.setColumnWidth((short)3, (short)3000);
/* 508 */     sheet.setColumnWidth((short)4, (short)3000);
/* 509 */     sheet.setColumnWidth((short)5, (short)3000);
/* 510 */     sheet.setColumnWidth((short)6, (short)3000);
/* 511 */     sheet.setColumnWidth((short)7, (short)3000);
/* 512 */     sheet.setColumnWidth((short)8, (short)3000);
/* 513 */     sheet.setColumnWidth((short)9, (short)3000);
/* 514 */     sheet.setColumnWidth((short)10, (short)3000);
/*     */ 
/*     */ 
/*     */     
/* 518 */     HSSFCellStyle style = wb.createCellStyle();
/*     */     
/* 520 */     style.setVerticalAlignment((short)1);
/*     */     
/* 522 */     style.setAlignment((short)2);
/* 523 */     style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
/* 524 */     style.setFillPattern((short)1);
/* 525 */     style.setBorderBottom((short)1);
/* 526 */     style.setBorderLeft((short)1);
/* 527 */     style.setBorderTop((short)1);
/* 528 */     style.setBorderRight((short)1);
/*     */     
/* 530 */     HSSFRow row = sheet.createRow(0);
/*     */     
/* 532 */     row.setHeight((short)300);
/*     */     
/* 534 */     HSSFCell ce = row.createCell((short)0);
/* 535 */     ce.setEncoding((short)1);
/* 536 */     ce.setCellValue("物料号");
/* 537 */     ce.setCellStyle(style);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 545 */     ce = row.createCell((short)1);
/* 546 */     ce.setEncoding((short)1);
/* 547 */     ce.setCellValue("描述");
/* 548 */     ce.setCellStyle(style);
/*     */     
/* 550 */     ce = row.createCell((short)2);
/* 551 */     ce.setEncoding((short)1);
/* 552 */     ce.setCellValue("计划产量");
/* 553 */     ce.setCellStyle(style);
/*     */     
/* 555 */     ce = row.createCell((short)3);
/* 556 */     ce.setEncoding((short)1);
/* 557 */     ce.setCellValue("生产日期");
/* 558 */     ce.setCellStyle(style);
/*     */     
/* 560 */     response.setContentType("appliction/x-msdownload");
/* 561 */     response.setCharacterEncoding("utf-8");
/* 562 */     String fileName = "POJiHuaChaKan";
/* 563 */     response.setHeader("Content-Disposition", "attachment; filename=" + 
/* 564 */         new String(fileName.getBytes("UTF-8"), "UTF-8") + ".xls");
/* 565 */     ServletOutputStream outStream = null;
/*     */     try {
/* 567 */       outStream = response.getOutputStream();
/* 568 */       wb.write((OutputStream)outStream);
/* 569 */     } catch (Exception e) {
/* 570 */       e.printStackTrace();
/*     */     } finally {
/* 572 */       outStream.flush();
/* 573 */       outStream.close();
/*     */     } 
/* 575 */     return null;
/*     */   }
/*     */   
/*     */   public ActionForward drexcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 579 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 580 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 581 */     PurchaseOrderInspectionPending pending = new PurchaseOrderInspectionPending();
/* 582 */     ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
/* 583 */     BeanForm materialForm = (BeanForm)form;
/* 584 */     FormFile uploadfile = (FormFile)materialForm.get("fileContent");
/* 585 */     int fileSize = 0;
/* 586 */     if (uploadfile != null)
/* 587 */       fileSize = uploadfile.getFileSize(); 
/* 588 */     InputStream inputstream = null;
/* 589 */     HSSFWorkbook wb = null;
/*     */     
/* 591 */     User user = getCurrentUser(request);
/* 592 */     String supplierNo = user.getLoginName();
/* 593 */     SupplierManager sManager = ServiceLocator.getSupplierManager(request);
/* 594 */     Supplier supplier = sManager.getSupplierByCode(supplierNo);
/* 595 */     if (fileSize > 0) {
/* 596 */       Date date = null;
/* 597 */       boolean isread = true;
/*     */       try {
/* 599 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/* 600 */       } catch (Exception ex) {
/* 601 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/*     */       } 
/*     */       
/* 604 */       Map<String, Object> data = new HashMap<String, Object>();
/* 605 */       List<List<String>> allList = new ArrayList<List<String>>();
/* 606 */       HSSFSheet sheet11 = wb.getSheetAt(0);
/* 607 */       if (sheet11 == null) {
/* 608 */         System.out.println("SHEET不存在");
/*     */       }
/* 610 */       System.out.println(sheet11.getLastRowNum());
/* 611 */       for (int rowNum = 1; rowNum <= sheet11.getLastRowNum() + 1; rowNum++) {
/* 612 */         List<String> list = new ArrayList<String>();
/* 613 */         System.out.println(rowNum);
/* 614 */         HSSFRow row = sheet11.getRow(rowNum);
/* 615 */         if (row != null) {
/*     */ 
/*     */           
/* 618 */           for (short cellNum = 0; cellNum <= row
/* 619 */             .getLastCellNum(); cellNum = (short)(cellNum + 1)) {
/* 620 */             HSSFCell cell = row.getCell(cellNum);
/* 621 */             System.out.println(cellNum);
/* 622 */             if (cell != null) {
/* 623 */               String de = getValue(sheet11.getRow(
/* 624 */                     rowNum).getCell(cellNum));
/* 625 */               list.add(de);
/*     */             } else {
/* 627 */               list.add("");
/*     */             } 
/*     */           } 
/*     */           
/* 631 */           allList.add(list);
/*     */         } 
/*     */       } 
/* 634 */       String poNumber = fm.getMaxPONumber();
/* 635 */       DecimalFormat df = new DecimalFormat("0000");
/* 636 */       if (poNumber == null) {
/* 637 */         poNumber = "PO" + df.format(1L);
/*     */       } else {
/* 639 */         String s = poNumber.substring(2, poNumber.length());
/* 640 */         int intNumber = Integer.parseInt(s) + 1;
/* 641 */         poNumber = "PO" + df.format(intNumber);
/*     */       } 
/* 643 */       pending.setPo_number(poNumber);
/* 644 */       pending.setPoip_number(poNumber);
/* 645 */       pending.setSupplier(supplier);
/* 646 */       pending.setCreateDate(new Date());
/* 647 */       pending.setStatus(PurchaseOrderStatus.WAIT);
/* 648 */       pending.setCreateType(Integer.valueOf(3));
/* 649 */       pending.setSite(user.getPrimarySite());
/* 650 */       List<PurchaseOrderInspectionPendingItem> pendingList = new ArrayList<PurchaseOrderInspectionPendingItem>();
/* 651 */       int b = allList.size();
/* 652 */       int line = 1;
/* 653 */       for (int j = 0; j < allList.size(); j++) {
/* 654 */         List<String> list = allList.get(j);
/* 655 */         System.out.println(j);
/* 656 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 657 */         PurchaseOrderInspectionPendingItem pendingItem = new PurchaseOrderInspectionPendingItem();
/* 658 */         conditions.put(WmsPartQueryCondition.IDEQ, list.get(0));
/* 659 */         List<WmsPart> partList = partManager.getWmsPartList(conditions, 0, -1, null, false);
/* 660 */         WmsPart part = null;
/* 661 */         if (partList.size() > 0) {
/* 662 */           part = partList.get(0);
/* 663 */           pendingItem.setLine(String.valueOf(line));
/* 664 */           line++;
/* 665 */           pendingItem.setUm(part.getUnit());
/* 666 */           pendingItem.setRum(part.getUnit());
/*     */         } else {
/* 668 */           part = new WmsPart();
/*     */         } 
/*     */         
/* 671 */         int a = list.size();
/* 672 */         for (int k = 0; k <= list.size(); k++) {
/* 673 */           if (k == 0) {
/* 674 */             if (((String)list.get(k)).equals("")) {
/* 675 */               data.put("key", "itemNumber.code");
/* 676 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 677 */               datas.add(data);
/*     */               break;
/*     */             } 
/* 680 */             if (partList.size() == 0) {
/* 681 */               data.put("key", "itemNumber.codeNot");
/* 682 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列" + " " + (String)list.get(k));
/* 683 */               datas.add(data);
/*     */               break;
/*     */             } 
/* 686 */             pendingItem.setItemNumber(partList.get(0));
/*     */           
/*     */           }
/* 689 */           else if (k == 1) {
/* 690 */             if (((String)list.get(k)).equals("")) {
/* 691 */               data.put("key", "itemNumber.desc");
/* 692 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 693 */               datas.add(data);
/*     */               break;
/*     */             } 
/* 696 */             if (partList.size() == 0) {
/* 697 */               data.put("key", "itemNumber.desc");
/* 698 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列" + " " + (String)list.get(k));
/* 699 */               datas.add(data);
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/* 704 */           } else if (k == 2) {
/* 705 */             if (((String)list.get(k)).equals("")) {
/* 706 */               data.put("key", "ediProduction.qty");
/* 707 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 708 */               datas.add(data);
/*     */               break;
/*     */             } 
/* 711 */             BigDecimal bigDecimal = new BigDecimal(list.get(k));
/* 712 */             pendingItem.setQty(bigDecimal);
/*     */           }
/* 714 */           else if (k == 3) {
/* 715 */             if (((String)list.get(k)).equals("")) {
/* 716 */               data.put("key", "ediProduction.time");
/* 717 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (k + 1) + "列");
/* 718 */               datas.add(data);
/*     */               break;
/*     */             } 
/* 721 */             SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 722 */             pendingItem.setDueDate(format.parse(list.get(k)));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 728 */         if (datas.size() != 0) {
/* 729 */           request.setAttribute("msg", datas);
/* 730 */           return mapping.findForward("add");
/*     */         } 
/* 732 */         pendingList.add(pendingItem);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 737 */       if (datas.size() != 0) {
/* 738 */         request.setAttribute("msg", datas);
/* 739 */         return mapping.findForward("add");
/*     */       } 
/* 741 */       fm.insertPurchaseOrderInspectionPendingPONumber(pending);
/* 742 */       for (int i = 0; i < pendingList.size(); i++) {
/* 743 */         PurchaseOrderInspectionPendingItem ma = pendingList.get(i);
/* 744 */         ma.setPoip_number(pending);
/* 745 */         fm.insertPurchaseOrderInspectionPendingItem(ma);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 751 */     postGlobalMessage("wmsProject.plan.submit", request.getSession());
/* 752 */     return new ActionForward("listPurchaseItemWrong.do", true);
/*     */   }
/*     */   private String getValue(HSSFCell cell) {
/* 755 */     if (cell.getCellType() == 4)
/* 756 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 757 */         .toPlainString(); 
/* 758 */     if (cell.getCellType() == 0)
/* 759 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 760 */         .setScale(0, 4).toPlainString(); 
/* 761 */     if (cell.getCellType() == 2) {
/* 762 */       return String.valueOf(cell.getNumericCellValue());
/*     */     }
/*     */     
/* 765 */     return String.valueOf(cell.getStringCellValue().trim());
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseOrderInspectionPendingAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */