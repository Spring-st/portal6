/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.basic.BasicPartPrice;
/*     */ import com.aof.model.basic.query.BasicPartPriceQueryCondition;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.SalesPreshiporderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.product.CustomerPlan;
/*     */ import com.aof.model.product.SalesOrderItem;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryOrder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryCondition;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
/*     */ import com.aof.service.Product.CustomerPlanManager;
/*     */ import com.aof.service.Product.SalesOrderItemManager;
/*     */ import com.aof.service.Product.SalesPreshiporderBatchManager;
/*     */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*     */ import com.aof.service.Product.SalesPreshiporderManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.service.basic.BasicPartPriceManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesPreshiporderQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesWorkorderQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SalesWorkorderAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  83 */     SalesPreshiporderManager salesPreshiporderManager = 
/*  84 */       ServiceLocator.getSalesPreshiporderManager(request);
/*  85 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*  86 */     if (queryForm.getOrder() == "") {
/*  87 */       queryForm.setDescend(true);
/*     */     }
/*  89 */     Map conditions = getQueryConditionsa(queryForm);
/*  90 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  91 */     String exportType = queryForm.getExportType();
/*  92 */     if (exportType != null && exportType.length() > 0) {
/*  93 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/*  94 */           null, false);
/*  95 */       int index = SessionTempFile.createNewTempFile(request);
/*  96 */       String fileName = "salesPickingOrderList";
/*  97 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  98 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  99 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 103 */               MessageResources messages = SalesWorkorderAction.this.getResources(request);
/* 104 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "Box.psoItem"));
/* 105 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesPickingOrderList.createDate"));
/* 106 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesPickingOrderList.customerName"));
/* 107 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesPickingOrderList.customerCode"));
/* 108 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesPickingOrderList.arrivaldate"));
/* 109 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesPickingOrderList.isPrint"));
/* 110 */               row.add("匹配状态");
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 116 */               row.add(BeanUtils.getProperty(data, "code"));
/* 117 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 118 */               row.add(BeanUtils.getProperty(data, "customerName"));
/* 119 */               row.add(BeanUtils.getProperty(data, "customerCode"));
/* 120 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/*     */               
/* 122 */               String shPrint = BeanUtils.getProperty(data, "shPrint");
/* 123 */               if (shPrint.equals("0")) {
/* 124 */                 row.add("已打印");
/*     */               } else {
/* 126 */                 row.add("未打印");
/*     */               } 
/* 128 */               String matchStatus = BeanUtils.getProperty(data, "matchStatus");
/* 129 */               if (matchStatus == null) {
/* 130 */                 row.add("未匹配");
/*     */               }
/* 132 */               else if (matchStatus.equals("0")) {
/* 133 */                 row.add("已匹配");
/*     */               } else {
/* 135 */                 row.add("未匹配");
/*     */               } 
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 141 */       return new ActionForward("download/" + index + "/" + 
/* 142 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 145 */     if (queryForm.isFirstInit()) {
/* 146 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 148 */       queryForm.init();
/*     */     } 
/* 150 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 151 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 152 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 153 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesPreshiporderQueryOrder.ID, queryForm.isDescend());
/* 154 */     request.setAttribute("x_selType", Integer.valueOf(120));
/* 155 */     request.setAttribute("X_RESULTLIST", resultList);
/* 156 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printSalesWorkShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 162 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*     */     
/* 164 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/*     */ 
/*     */     
/* 167 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/*     */     
/* 169 */     BasicPartPriceManager basicPartPriceManager = ServiceLocator.getBasicPartPriceManager(request);
/* 170 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 172 */     String shipOrderId = request.getParameter("id");
/* 173 */     SalesPreshiporder salesPreshiporder = spsom.getById(
/* 174 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 176 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 177 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 178 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 179 */     if (salesPreshiporder.getType().intValue() == 1) {
/* 180 */       for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 181 */         conditions.clear();
/* 182 */         conditions.put(BasicPartPriceQueryCondition.CUSTOMER_EQ, salesPreshiporderItem.getSpsoId().getCustomerCode());
/* 183 */         conditions.put(BasicPartPriceQueryCondition.PARTID_EQ, salesPreshiporderItem.getSoipitemId().getItemNumber().getId());
/* 184 */         conditions.put(BasicPartPriceQueryCondition.STARTDATE_LE, format.format(new Date()));
/* 185 */         conditions.put(BasicPartPriceQueryCondition.EXPIREDATE_GE, format.format(new Date()));
/* 186 */         List<BasicPartPrice> partPriceList = basicPartPriceManager.getList(conditions, 0, -1, null, false);
/* 187 */         if (partPriceList.size() > 0) {
/* 188 */           BasicPartPrice basicPartPrice = partPriceList.get(0);
/* 189 */           if (basicPartPrice.getAmt() != null) {
/* 190 */             salesPreshiporderItem.setPrice(basicPartPrice.getAmt());
/* 191 */             salesPreshiporderItem.setCurr(basicPartPrice.getCurr());
/* 192 */             salesPreshiporderItem.setSotaxc(basicPartPrice.getSotaxc());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
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
/* 218 */     for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 219 */       Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 220 */       conditionsa.put(SalesWorkorderQueryCondition.SHIPITEM_ID_EQ, salesPreshiporderItem.getId());
/* 221 */       conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_GE, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 222 */       List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 223 */       salesPreshiporderItem.setSalesWorkorderList(salesWorkorderList);
/*     */     } 
/*     */     
/* 226 */     request.setAttribute("x_salesShipOrder", salesPreshiporder);
/* 227 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 228 */         SalesPreshiporderItemList);
/*     */     
/* 230 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 232 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 233 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 234 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map getQueryConditionsa(SalesPreshiporderQueryForm queryForm) {
/* 238 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 239 */     String queryStr = "";
/* 240 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 243 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesWorkorderQueryForm queryForm) {
/* 248 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 249 */     String queryStr = "";
/* 250 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 253 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 257 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 262 */     getBasic(request);
/* 263 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 268 */     SalesWorkorderManager manager = ServiceLocator.getSalesWorkorderManager(request);
/* 269 */     BeanForm formBean = (BeanForm)form;
/* 270 */     SalesWorkorder salesWorkorder = new SalesWorkorder();
/* 271 */     formBean.populateToBean(salesWorkorder);
/* 272 */     salesWorkorder = manager.insert(salesWorkorder);
/* 273 */     request.setAttribute("X_OBJECT", salesWorkorder);
/* 274 */     request.setAttribute("X_ROWPAGE", "salesWorkorder/row.jsp");
/* 275 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 280 */     SalesWorkorderManager manager = ServiceLocator.getSalesWorkorderManager(request);
/* 281 */     String idStr = request.getParameter("id");
/* 282 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 283 */     SalesWorkorder salesWorkorder = manager.getById(id);
/* 284 */     if (salesWorkorder == null) throw new ActionException("salesWorkorder.notFound", id); 
/* 285 */     request.setAttribute("X_OBJECT", salesWorkorder);
/* 286 */     getBasic(request);
/* 287 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 292 */     SalesWorkorderManager manager = ServiceLocator.getSalesWorkorderManager(request);
/* 293 */     String idStr = request.getParameter("id");
/* 294 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 295 */     BeanForm formBean = (BeanForm)form;
/* 296 */     SalesWorkorder salesWorkorder = manager.getById(id);
/* 297 */     formBean.populateToBean(salesWorkorder, request);
/* 298 */     salesWorkorder = manager.update(salesWorkorder);
/* 299 */     request.setAttribute("X_OBJECT", salesWorkorder);
/* 300 */     request.setAttribute("X_ROWPAGE", "salesWorkorder/row.jsp");
/* 301 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 306 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 307 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 308 */     salesWorkorderManager.remove(salesWorkorderManager.getById(id));
/* 309 */     return new ActionForward("listSalesWorkorder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward SalesWorkShipOrderClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 315 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 316 */     SalesPreshiporderBatchManager salesPreshiporderBatchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 317 */     SalesOrderItemManager itemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 318 */     SalesWorkorderManager workOrderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 319 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 320 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 321 */     String ids = request.getParameter("id");
/* 322 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/*     */     
/* 324 */     List<SalesPreshiporderBatch> batchsList = salesPreshiporderBatchManager.getNotWorkList(salesPreshiporder.getId());
/* 325 */     for (SalesPreshiporderBatch salesPreshiporderBatch : batchsList) {
/* 326 */       spsom.updateInventoryOccupiedDetial(salesPreshiporderBatch.getBox());
/*     */     }
/* 328 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 329 */     conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 330 */     List<SalesPreshiporderBatch> batchList = salesPreshiporderBatchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.ID, false);
/* 331 */     for (SalesPreshiporderBatch salesPreshiporderBatch : batchList) {
/* 332 */       salesPreshiporderBatch.setEnabled(EnabledDisabled.DISABLED);
/* 333 */       salesPreshiporderBatchManager.update(salesPreshiporderBatch);
/*     */     } 
/* 335 */     if (salesPreshiporder.getType().intValue() == 1) {
/* 336 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 337 */       conditions.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 338 */       conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 339 */       List<SalesWorkorder> salesWorderList = workOrderManager.getList(conditions, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 340 */       for (SalesWorkorder salesWorkorder : salesWorderList) {
/* 341 */         SalesOrderItem item = salesWorkorder.getShipItemId().getSoipitemId();
/* 342 */         Box box = boxManager.getBoxBylotSer2(salesWorkorder.getLotSer().getId());
/* 343 */         if (box != null) {
/* 344 */           if (item.getReceiptQty() == null) {
/* 345 */             item.setReceiptQty(box.getNumber());
/*     */           } else {
/* 347 */             BigDecimal count = item.getReceiptQty().add(box.getNumber());
/* 348 */             item.setReceiptQty(count);
/*     */           } 
/* 350 */           itemManager.update(item);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 354 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 355 */       conditions.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 356 */       conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 357 */       List<SalesWorkorder> salesWorderList = workOrderManager.getList(conditions, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 358 */       for (SalesWorkorder salesWorkorder : salesWorderList) {
/* 359 */         CustomerPlan customerPlan = salesWorkorder.getShipItemId().getCustomerPlanId();
/* 360 */         Box box = boxManager.getBoxBylotSer2(salesWorkorder.getLotSer().getId());
/* 361 */         if (box != null) {
/* 362 */           if (customerPlan.getReceiptQty() == null) {
/* 363 */             customerPlan.setReceiptQty(box.getNumber());
/*     */           } else {
/* 365 */             BigDecimal count = customerPlan.getReceiptQty().add(box.getNumber());
/* 366 */             customerPlan.setReceiptQty(count);
/*     */           } 
/* 368 */           customerPlanManager.update(customerPlan);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 374 */     salesPreshiporder.setStatus(SalesPreshiporderStatus.IN_DELIVERY);
/* 375 */     spsom.update(salesPreshiporder);
/* 376 */     return new ActionForward("/viewSalesPreShipOrder.do?id=" + ids, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward updatePirntSalesWorkShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 381 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 382 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 383 */     SalesPreshiporderBatchManager salesPreshiporderBatchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 384 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 385 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 386 */     String ids = request.getParameter("id");
/* 387 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/* 388 */     if (salesPreshiporder.getShPrint() != YesNo.YES) {
/* 389 */       if (salesPreshiporder.getType().intValue() == 1) {
/* 390 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 391 */         conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 392 */         List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 393 */         for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 394 */           BigDecimal qtyOpen = salesPreshiporderItem.getDeliverynumber().subtract(salesPreshiporderItem.getShipQty());
/* 395 */           if (qtyOpen.compareTo(new BigDecimal(0)) == 1) {
/* 396 */             SalesOrderItem salesOrderItem = salesPreshiporderItem.getSoipitemId();
/* 397 */             salesOrderItem.setQtyopen(salesOrderItem.getQtyopen().add(qtyOpen));
/* 398 */             salesOrderItemManager.update(salesOrderItem);
/*     */           } 
/*     */         } 
/* 401 */       } else if (salesPreshiporder.getType().intValue() == 2) {
/* 402 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 403 */         conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 404 */         List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 405 */         for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 406 */           BigDecimal qtyOpen = salesPreshiporderItem.getDeliverynumber().subtract(salesPreshiporderItem.getShipQty());
/* 407 */           if (qtyOpen.compareTo(new BigDecimal(0)) == 1) {
/* 408 */             CustomerPlan customerPlan = salesPreshiporderItem.getCustomerPlanId();
/* 409 */             customerPlan.setQtyOpen(customerPlan.getQtyOpen().add(qtyOpen));
/* 410 */             customerPlanManager.update(customerPlan);
/*     */           } 
/*     */         } 
/*     */       } 
/* 414 */       salesPreshiporder.setShPrintDate(new Date());
/*     */     } 
/*     */ 
/*     */     
/* 418 */     salesPreshiporder.setShPrint(YesNo.YES);
/* 419 */     spsom.update(salesPreshiporder);
/* 420 */     return new ActionForward("/listSalesWorkorder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesWorkShipOrderOpenAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 426 */     response.setContentType("text/json");
/* 427 */     response.setCharacterEncoding("UTF-8");
/* 428 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 430 */     String itemId = request.getParameter("id");
/* 431 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 432 */     SalesPreshiporderBatchManager salesPreshiporderBatchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 433 */     SalesOrderItemManager itemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 434 */     SalesWorkorderManager workOrderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 435 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 436 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 437 */     String ids = request.getParameter("id");
/* 438 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/*     */     
/* 440 */     Map<Object, Object> conditionss = new HashMap<Object, Object>();
/* 441 */     conditionss.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/*     */     
/* 443 */     List<SalesWorkorder> salesWordersList = workOrderManager.getList(conditionss, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 444 */     boolean sign = true;
/* 445 */     for (SalesWorkorder salesWorkorder : salesWordersList) {
/* 446 */       if (salesWorkorder.getIsSync() == YesNo.YES) {
/* 447 */         sign = false;
/*     */       }
/*     */     } 
/*     */     
/* 451 */     if (sign) {
/*     */       
/* 453 */       List<SalesPreshiporderBatch> batchsList = salesPreshiporderBatchManager.getNotWorkList(salesPreshiporder.getId());
/* 454 */       for (SalesPreshiporderBatch salesPreshiporderBatch : batchsList) {
/* 455 */         spsom.insertInventoryOccupiedDetial(salesPreshiporderBatch.getBox());
/*     */       }
/* 457 */       if (salesPreshiporder.getType().intValue() == 1) {
/* 458 */         Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 459 */         conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 460 */         List<SalesPreshiporderBatch> batchList = salesPreshiporderBatchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.ID, false);
/* 461 */         for (SalesPreshiporderBatch salesPreshiporderBatch : batchList) {
/* 462 */           salesPreshiporderBatch.setEnabled(EnabledDisabled.ENABLED);
/* 463 */           salesPreshiporderBatchManager.update(salesPreshiporderBatch);
/*     */         } 
/* 465 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 466 */         conditions.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 467 */         conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 468 */         List<SalesWorkorder> salesWorderList = workOrderManager.getList(conditions, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 469 */         for (SalesWorkorder salesWorkorder : salesWorderList) {
/* 470 */           SalesOrderItem item = salesWorkorder.getShipItemId().getSoipitemId();
/* 471 */           Box box = boxManager.getBoxBylotSer2(salesWorkorder.getLotSer().getId());
/* 472 */           if (box != null) {
/* 473 */             if (item.getReceiptQty() != null) {
/*     */ 
/*     */               
/* 476 */               BigDecimal count = item.getReceiptQty().subtract(box.getNumber());
/* 477 */               item.setReceiptQty(count);
/*     */             } 
/* 479 */             itemManager.update(item);
/*     */           } 
/*     */         } 
/* 482 */         salesPreshiporder.setStatus(SalesPreshiporderStatus.ON_MATERIAL);
/* 483 */         spsom.update(salesPreshiporder);
/* 484 */         JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 485 */         response.getWriter().print(jo);
/*     */       } else {
/* 487 */         Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 488 */         conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 489 */         List<SalesPreshiporderBatch> batchList = salesPreshiporderBatchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.ID, false);
/* 490 */         for (SalesPreshiporderBatch salesPreshiporderBatch : batchList) {
/* 491 */           salesPreshiporderBatch.setEnabled(EnabledDisabled.ENABLED);
/* 492 */           salesPreshiporderBatchManager.update(salesPreshiporderBatch);
/*     */         } 
/* 494 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 495 */         conditions.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 496 */         conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 497 */         List<SalesWorkorder> salesWorderList = workOrderManager.getList(conditions, 0, -1, SalesWorkorderQueryOrder.ID, false);
/* 498 */         for (SalesWorkorder salesWorkorder : salesWorderList) {
/* 499 */           CustomerPlan customerPlan = salesWorkorder.getShipItemId().getCustomerPlanId();
/* 500 */           Box box = boxManager.getBoxBylotSer2(salesWorkorder.getLotSer().getId());
/* 501 */           if (box != null) {
/* 502 */             if (customerPlan.getReceiptQty() != null) {
/*     */ 
/*     */               
/* 505 */               BigDecimal count = customerPlan.getReceiptQty().subtract(box.getNumber());
/* 506 */               customerPlan.setReceiptQty(count);
/*     */             } 
/* 508 */             customerPlanManager.update(customerPlan);
/*     */           } 
/*     */         } 
/* 511 */         salesPreshiporder.setStatus(SalesPreshiporderStatus.ON_MATERIAL);
/* 512 */         spsom.update(salesPreshiporder);
/* 513 */         JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 514 */         response.getWriter().print(jo);
/*     */       } 
/*     */     } else {
/* 517 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
/* 518 */       response.getWriter().print(jo);
/*     */     } 
/* 520 */     return null;
/*     */   }
/*     */   
/*     */   public ActionForward listOutStorageDetailReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 524 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 525 */     SalesWorkorderQueryForm queryForm = (SalesWorkorderQueryForm)form;
/* 526 */     Map<SalesWorkorderQueryCondition, SalesPreshiporderBatchStatus> conditions = getQueryConditions(queryForm);
/* 527 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 528 */     conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 529 */     String exportType = queryForm.getExportType();
/* 530 */     if (exportType != null && exportType.length() > 0) {
/* 531 */       List data = salesWorkorderManager.getList(conditions, 0, -1, 
/* 532 */           null, false);
/* 533 */       int index = SessionTempFile.createNewTempFile(request);
/* 534 */       String fileName = "salesWorkOrderList";
/* 535 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 536 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 537 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 541 */               MessageResources messages = SalesWorkorderAction.this.getResources(request);
/* 542 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.shipId"));
/* 543 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.lotSer"));
/* 544 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.id"));
/* 545 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.dpiNo"));
/* 546 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.oldCode"));
/* 547 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.describe1"));
/* 548 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.outDate"));
/* 549 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.isSync"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 554 */               row.add(BeanUtils.getProperty(data, "shipId.code"));
/* 555 */               row.add(BeanUtils.getProperty(data, "lotSer.id"));
/* 556 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 557 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/* 558 */               row.add(BeanUtils.getProperty(data, "part.oldCode"));
/* 559 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 560 */               row.add(BeanUtils.getProperty(data, "outDate"));
/*     */               
/* 562 */               String shPrint = BeanUtils.getProperty(data, "isSync");
/* 563 */               if (shPrint.equals("0")) {
/* 564 */                 row.add("已同步");
/*     */               } else {
/* 566 */                 row.add("未同步");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 571 */       return new ActionForward("download/" + index + "/" + 
/* 572 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 575 */     if (queryForm.isFirstInit()) {
/* 576 */       queryForm.init(salesWorkorderManager.getListCount(conditions));
/*     */     } else {
/* 578 */       queryForm.init();
/*     */     } 
/* 580 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 581 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 582 */     List<SalesWorkorder> resultList = salesWorkorderManager.getList(
/* 583 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesWorkorderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 584 */     request.setAttribute("x_selType", Integer.valueOf(146));
/* 585 */     request.setAttribute("X_RESULTLIST", resultList);
/* 586 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward listSalesRecordReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 590 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 591 */     SalesWorkorderQueryForm queryForm = (SalesWorkorderQueryForm)form;
/* 592 */     if (queryForm.getOrder() == "") {
/* 593 */       queryForm.setOrder(SalesWorkorderQueryOrder.ID.getName());
/* 594 */       queryForm.setDescend(true);
/*     */     } 
/* 596 */     Map<SalesWorkorderQueryCondition, SalesPreshiporderBatchStatus> conditions = getQueryConditions(queryForm);
/* 597 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 598 */     conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 599 */     String exportType = queryForm.getExportType();
/* 600 */     if (exportType != null && exportType.length() > 0) {
/* 601 */       List data = salesWorkorderManager.getList(conditions, 0, -1, 
/* 602 */           null, false);
/* 603 */       int index = SessionTempFile.createNewTempFile(request);
/* 604 */       String fileName = "salesWorkOrderList";
/* 605 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 606 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 607 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 611 */               MessageResources messages = SalesWorkorderAction.this.getResources(request);
/* 612 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.shipId"));
/* 613 */               row.add("客户名称");
/* 614 */               row.add("业务员");
/* 615 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.lotSer"));
/* 616 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.id"));
/* 617 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.oldCode"));
/* 618 */               row.add("数量");
/* 619 */               row.add("单价");
/* 620 */               row.add("税率");
/* 621 */               row.add("币种");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 626 */               row.add(BeanUtils.getProperty(data, "shipId.code"));
/* 627 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.customer.name1"));
/* 628 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.operation"));
/* 629 */               row.add(BeanUtils.getProperty(data, "lotSer.id"));
/* 630 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 631 */               row.add(BeanUtils.getProperty(data, "part.oldCode"));
/* 632 */               row.add(BeanUtils.getProperty(data, "count"));
/* 633 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.unitPrice"));
/* 634 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.sotaxc"));
/* 635 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.curr"));
/*     */             }
/*     */           });
/* 638 */       return new ActionForward("download/" + index + "/" + 
/* 639 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 642 */     if (queryForm.isFirstInit()) {
/* 643 */       queryForm.init(salesWorkorderManager.getListCount(conditions));
/*     */     } else {
/* 645 */       queryForm.init();
/*     */     } 
/* 647 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 648 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 649 */     List<SalesWorkorder> resultList = salesWorkorderManager.getList(
/* 650 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesWorkorderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 651 */     request.setAttribute("x_selType", Integer.valueOf(149));
/* 652 */     request.setAttribute("X_RESULTLIST", resultList);
/* 653 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listSalesAnalysisReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 658 */     final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
/* 659 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 660 */     SalesWorkorderQueryForm queryForm = (SalesWorkorderQueryForm)form;
/* 661 */     Map<SalesWorkorderQueryCondition, SalesPreshiporderBatchStatus> conditions = getQueryConditions(queryForm);
/* 662 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 663 */     conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 664 */     String exportType = queryForm.getExportType();
/* 665 */     if (exportType != null && exportType.length() > 0) {
/* 666 */       List data = salesWorkorderManager.getList(conditions, 0, -1, 
/* 667 */           null, false);
/* 668 */       int index = SessionTempFile.createNewTempFile(request);
/* 669 */       String fileName = "SalesAnalysisReport";
/* 670 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 671 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 672 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 676 */               MessageResources messages = SalesWorkorderAction.this.getResources(request);
/* 677 */               row.add("客户编码");
/* 678 */               row.add("客户名称");
/* 679 */               row.add("业务员");
/* 680 */               row.add("发货时间");
/* 681 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.part.id"));
/* 682 */               row.add("品牌");
/* 683 */               row.add("车型");
/* 684 */               row.add("年份起");
/* 685 */               row.add("年份讫");
/* 686 */               row.add("数量");
/* 687 */               row.add("单价");
/* 688 */               row.add("税率");
/* 689 */               row.add("币种");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 694 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.customer.code"));
/* 695 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.customer.name1"));
/* 696 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.operation"));
/* 697 */               SalesWorkorder salesWorkorder = (SalesWorkorder)data;
/* 698 */               row.add(fmt.format(salesWorkorder.getOutDate()));
/* 699 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 700 */               row.add(BeanUtils.getProperty(data, "part.carBrand1"));
/* 701 */               row.add(BeanUtils.getProperty(data, "part.domesticCar1"));
/* 702 */               row.add(BeanUtils.getProperty(data, "part.yearFrom1"));
/* 703 */               row.add(BeanUtils.getProperty(data, "part.yearTo1"));
/* 704 */               row.add(BeanUtils.getProperty(data, "count"));
/* 705 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.unitPrice"));
/* 706 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.sotaxc"));
/* 707 */               row.add(BeanUtils.getProperty(data, "shipItemId.customerPlanId.curr"));
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 713 */       return new ActionForward("download/" + index + "/" + 
/* 714 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 717 */     if (queryForm.isFirstInit()) {
/* 718 */       queryForm.init(salesWorkorderManager.getListCount(conditions));
/*     */     } else {
/* 720 */       queryForm.init();
/*     */     } 
/* 722 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 723 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 724 */     List<SalesWorkorder> resultList = salesWorkorderManager.getList(
/* 725 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesWorkorderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 726 */     request.setAttribute("x_selType", Integer.valueOf(153));
/* 727 */     request.setAttribute("X_RESULTLIST", resultList);
/* 728 */     return mapping.findForward("page");
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
/*     */   public ActionForward salesWorkOrderIsMatchStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 743 */     SalesPreshiporderManager manager = ServiceLocator.getSalesPreshiporderManager(request);
/* 744 */     String arrays = request.getParameter("arrays");
/* 745 */     manager.updateIsMatchStatus(arrays);
/* 746 */     return new ActionForward("/listSalesWorkorder.do", true);
/*     */   }
/*     */   
/*     */   public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 750 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 751 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 752 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 753 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/* 754 */     Map<SalesPreshiporderItemQueryCondition, Integer> conditions = getQueryConditionsa(queryForm);
/* 755 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 756 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*     */       
/* 758 */       queryForm.setOrder(SalesPreshiporderQueryOrder.ID.getName());
/* 759 */       queryForm.setDescend(true);
/*     */     } 
/*     */     
/* 762 */     String exportType = queryForm.getExportType();
/* 763 */     if (exportType != null && exportType.length() > 0) {
/*     */ 
/*     */       
/* 766 */       List<SalesPreshiporder> list = salesPreshiporderManager.getList(conditions, 0, -1, SalesPreshiporderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 767 */       for (SalesPreshiporder salesPreshiporder : list) {
/* 768 */         conditions.clear();
/* 769 */         conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 770 */         List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 771 */         salesPreshiporder.setCustomerPlanList(SalesPreshiporderItemList);
/*     */       } 
/* 773 */       int index = SessionTempFile.createNewTempFile(request);
/* 774 */       String fileName = "SalesSummaryReportList";
/* 775 */       String suffix = ExportUtil.export(exportType, list, request, 
/* 776 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 777 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 781 */               MessageResources messages = SalesWorkorderAction.this.getResources(request);
/* 782 */               row.add(messages.getMessage(SalesWorkorderAction.this.getLocale(request), "salesWorkorder.shipId"));
/* 783 */               row.add("需求计划单号");
/* 784 */               row.add("客户编码");
/* 785 */               row.add("打印日期");
/* 786 */               row.add("发货数量");
/* 787 */               row.add("金额");
/* 788 */               row.add("是否匹配");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 793 */               SalesPreshiporder salesPreshiporder = (SalesPreshiporder)data;
/* 794 */               List<SalesPreshiporderItem> customerPlanList = salesPreshiporder.getCustomerPlanList();
/* 795 */               BigDecimal sumamount = new BigDecimal(0);
/* 796 */               BigDecimal sumPrice = new BigDecimal(0);
/* 797 */               String planNumbers = "";
/* 798 */               String planNumber = "";
/* 799 */               for (int i = 0; i < customerPlanList.size(); i++) {
/* 800 */                 SalesPreshiporderItem salesPreshiporderItem = customerPlanList.get(i);
/* 801 */                 if (!planNumber.equals(salesPreshiporderItem.getCustomerPlanId().getPlanNumbers())) {
/* 802 */                   if (i == 0) {
/* 803 */                     planNumber = salesPreshiporderItem.getCustomerPlanId().getPlanNumbers();
/* 804 */                     planNumbers = String.valueOf(planNumbers) + salesPreshiporderItem.getCustomerPlanId().getPlanNumbers();
/*     */                   } else {
/* 806 */                     planNumber = salesPreshiporderItem.getCustomerPlanId().getPlanNumbers();
/* 807 */                     planNumbers = String.valueOf(planNumbers) + "," + salesPreshiporderItem.getCustomerPlanId().getPlanNumbers();
/*     */                   } 
/*     */                 }
/*     */                 
/* 811 */                 sumamount = sumamount.add(salesPreshiporderItem.getShipQty());
/* 812 */                 sumPrice = sumPrice.add(salesPreshiporderItem.getShipQty().multiply(salesPreshiporderItem.getCustomerPlanId().getUnitPrice()));
/*     */               } 
/*     */               
/* 815 */               row.add(BeanUtils.getProperty(data, "code"));
/* 816 */               row.add(planNumbers);
/* 817 */               row.add(BeanUtils.getProperty(data, "customerCode"));
/* 818 */               row.add(BeanUtils.getProperty(data, "shPrintDate"));
/* 819 */               row.add(sumamount);
/* 820 */               row.add(sumPrice);
/* 821 */               String matchStatus = BeanUtils.getProperty(data, "matchStatus");
/* 822 */               if (matchStatus == null) {
/* 823 */                 row.add("未匹配");
/*     */               }
/* 825 */               else if (matchStatus.equals("0")) {
/* 826 */                 row.add("已匹配");
/*     */               } else {
/* 828 */                 row.add("未匹配");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 833 */       return new ActionForward("download/" + index + "/" + 
/* 834 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 837 */     if (queryForm.isFirstInit()) {
/* 838 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 840 */       queryForm.init();
/*     */     } 
/* 842 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 843 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 844 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), SalesPreshiporderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 845 */     for (SalesPreshiporder salesPreshiporder : resultList) {
/* 846 */       conditions.clear();
/* 847 */       conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 848 */       List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 849 */       salesPreshiporder.setCustomerPlanList(SalesPreshiporderItemList);
/*     */     } 
/* 851 */     request.setAttribute("x_selType", Integer.valueOf(151));
/* 852 */     request.setAttribute("X_RESULTLIST", resultList);
/* 853 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesWorkorderAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */