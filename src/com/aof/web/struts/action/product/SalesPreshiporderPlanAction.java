/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.CustomerPlan;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.CustomerPlanQueryCondition;
/*     */ import com.aof.model.product.query.CustomerPlanQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryCondition;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
/*     */ import com.aof.service.Product.CustomerPlanManager;
/*     */ import com.aof.service.Product.SalesOrderItemManager;
/*     */ import com.aof.service.Product.SalesOrderManager;
/*     */ import com.aof.service.Product.SalesPreshiporderBatchManager;
/*     */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*     */ import com.aof.service.Product.SalesPreshiporderManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.CustomerPlanQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesOrderItemQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesPreshiporderQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JsonConfig;
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
/*     */ public class SalesPreshiporderPlanAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  64 */     SalesPreshiporderManager salesPreshiporderManager = 
/*  65 */       ServiceLocator.getSalesPreshiporderManager(request);
/*  66 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*  67 */     Map conditions = getQueryConditions(queryForm);
/*  68 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  69 */     String exportType = queryForm.getExportType();
/*  70 */     if (exportType != null && exportType.length() > 0) {
/*  71 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/*  72 */           null, false);
/*  73 */       int index = SessionTempFile.createNewTempFile(request);
/*  74 */       String fileName = "SalesPreshiporder";
/*  75 */       String suffix = ExportUtil.export(
/*  76 */           exportType, 
/*  77 */           data, 
/*  78 */           request, 
/*  79 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  80 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/*  84 */               MessageResources messages = SalesPreshiporderPlanAction.this.getResources(request);
/*  85 */               row.add(messages.getMessage(SalesPreshiporderPlanAction.this.getLocale(request), "salesPreshiporder.id"));
/*  86 */               row.add(messages.getMessage(SalesPreshiporderPlanAction.this.getLocale(request), "salesPreshiporder.arrivaldate"));
/*  87 */               row.add(messages.getMessage(SalesPreshiporderPlanAction.this.getLocale(request), "salesPreshiporder.remark"));
/*  88 */               row.add(messages.getMessage(SalesPreshiporderPlanAction.this.getLocale(request), "salesPreshiporder.status"));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  98 */               row.add(BeanUtils.getProperty(data, "code"));
/*  99 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/* 100 */               row.add(BeanUtils.getProperty(data, "remark"));
/* 101 */               row.add(BeanUtils.getProperty(data, "status"));
/*     */             }
/*     */           });
/*     */       
/* 105 */       return new ActionForward("download/" + index + "/" + 
/* 106 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 109 */     if (queryForm.isFirstInit()) {
/* 110 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 112 */       queryForm.init();
/*     */     } 
/*     */     
/* 115 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 116 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 117 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 118 */         conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 119 */     request.setAttribute("x_selType", Integer.valueOf(118));
/* 120 */     request.setAttribute("X_RESULTLIST", resultList);
/* 121 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesPickingOrderList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 127 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 128 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 129 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/* 130 */     Map conditions = getQueryConditions(queryForm);
/* 131 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 132 */     String exportType = queryForm.getExportType();
/* 133 */     if (exportType != null && exportType.length() > 0) {
/* 134 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/* 135 */           null, false);
/* 136 */       int index = SessionTempFile.createNewTempFile(request);
/* 137 */       String fileName = "salesPickingOrderList";
/* 138 */       String suffix = ExportUtil.export(
/* 139 */           exportType, 
/* 140 */           data, 
/* 141 */           request, 
/* 142 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 143 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/* 147 */               MessageResources messages = SalesPreshiporderPlanAction.this.getResources(request);
/* 148 */               row.add(messages.getMessage(SalesPreshiporderPlanAction.this.getLocale(request), ""));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 157 */               row.add(BeanUtils.getProperty(data, ""));
/*     */             }
/*     */           });
/* 160 */       return new ActionForward("download/" + index + "/" + 
/* 161 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 164 */     if (queryForm.isFirstInit()) {
/* 165 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 167 */       queryForm.init();
/*     */     } 
/*     */     
/* 170 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 171 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 172 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 173 */         conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 174 */     request.setAttribute("x_selType", Integer.valueOf(119));
/* 175 */     request.setAttribute("X_RESULTLIST", resultList);
/* 176 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesPreshiporderQueryForm queryForm) {
/* 181 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 182 */     String queryStr = "";
/* 183 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 186 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 190 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 191 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 197 */     getBasic(request);
/* 198 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 204 */     SalesPreshiporderManager manager = 
/* 205 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 206 */     BeanForm formBean = (BeanForm)form;
/* 207 */     SalesPreshiporder salesPreshiporder = new SalesPreshiporder();
/* 208 */     formBean.populateToBean(salesPreshiporder);
/* 209 */     salesPreshiporder = manager.insert(salesPreshiporder);
/* 210 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 211 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 217 */     SalesPreshiporderManager manager = 
/* 218 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 219 */     String idStr = request.getParameter("id");
/* 220 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 221 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 222 */     if (salesPreshiporder == null) {
/* 223 */       throw new ActionException("salesPreshiporder.notFound", id);
/*     */     }
/* 225 */     if (!isBack(request)) {
/* 226 */       BeanForm cityForm = (BeanForm)getForm("/updateSalesPreshiporder", 
/* 227 */           request);
/* 228 */       cityForm.populate(salesPreshiporder, "to_form");
/*     */     } 
/* 230 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 231 */     getBasic(request);
/* 232 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 238 */     SalesPreshiporderManager manager = 
/* 239 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 240 */     String idStr = request.getParameter("id");
/* 241 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 242 */     BeanForm formBean = (BeanForm)form;
/* 243 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 244 */     formBean.populateToBean(salesPreshiporder, request);
/* 245 */     salesPreshiporder = manager.update(salesPreshiporder);
/* 246 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 247 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 253 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 254 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 255 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 256 */     salesPreshiporderManager.remove(salesPreshiporderManager.getById(id));
/* 257 */     return new ActionForward("listSalesPreshiporder.do", true);
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
/*     */   
/*     */   public ActionForward selectCustomerPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 275 */     CustomerPlanQueryForm queryForm = (CustomerPlanQueryForm)form;
/* 276 */     CustomerPlanManager planManager = 
/* 277 */       ServiceLocator.getCustomerPlanManager(request);
/* 278 */     String idString = queryForm.getPlanId();
/* 279 */     String[] ids = idString.split(";");
/* 280 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 282 */     conditions.put(CustomerPlanQueryCondition.QTYOPEN_DT, Integer.valueOf(0));
/* 283 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 284 */     if (queryForm.isFirstInit()) {
/* 285 */       queryForm.init(planManager.getListCount(conditions));
/*     */     } else {
/* 287 */       queryForm.init();
/*     */     } 
/* 289 */     List<CustomerPlan> result = planManager.getList(conditions, 
/* 290 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 291 */         CustomerPlanQueryOrder.ID, queryForm.isDescend());
/* 292 */     if (ids.length > 0) {
/* 293 */       for (CustomerPlan customerPlan : result) {
/* 294 */         Integer itemId = customerPlan.getId();
/* 295 */         Boolean isClose = Boolean.valueOf(false);
/*     */         
/* 297 */         for (int i = 0; i < ids.length; i++) {
/* 298 */           if (!ids[i].equals("") && 
/* 299 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 300 */             isClose = Boolean.valueOf(true);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 305 */         if (isClose.booleanValue()) {
/* 306 */           customerPlan.setChecked("checked");
/*     */         }
/*     */       } 
/*     */     }
/* 310 */     request.setAttribute("X_RESULTLIST", result);
/* 311 */     request.setAttribute("X_YESNOLIST", 
/* 312 */         PersistentEnum.getEnumList(YesNo.class));
/* 313 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 314 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 315 */     return mapping.findForward("page");
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
/*     */   public ActionForward createSalesPreShipOrderPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 332 */     SalesPreshiporderManager spsom = 
/* 333 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 334 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*     */     
/* 336 */     String arrivalDate = queryForm.getReceivingDate();
/* 337 */     String[] soItemIdsList = request.getParameterValues("soItemIds");
/* 338 */     String[] deliveryNumbersList = request
/* 339 */       .getParameterValues("deliveryNumbers");
/* 340 */     String[] remarksList = request.getParameterValues("remarks");
/* 341 */     User requestor = getCurrentUser(request);
/* 342 */     spsom.insertSalesPreshiporderByItem(soItemIdsList, deliveryNumbersList, 
/* 343 */         remarksList, arrivalDate, requestor);
/* 344 */     return new ActionForward("/listSalesPreshiporder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesShipOrderPlanByBoxAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 350 */     response.setContentType("text/json");
/* 351 */     response.setCharacterEncoding("UTF-8");
/* 352 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 354 */     String itemId = request.getParameter("itemId");
/* 355 */     String numbers = request.getParameter("numbers");
/* 356 */     SalesPreshiporderManager spsom = 
/* 357 */       ServiceLocator.getSalesPreshiporderManager(request);
/*     */     
/* 359 */     Map<String, Object> map = spsom.getinvenDetialByPartBycount(itemId, 
/* 360 */         numbers);
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
/* 373 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 379 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 380 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 381 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 382 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 383 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 385 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 386 */     String shipOrderId = request.getParameter("id");
/* 387 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 389 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 390 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 391 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 393 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 394 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 396 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 398 */     if (formBean.isFirstInit()) {
/* 399 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 401 */       formBean.init();
/*     */     } 
/* 403 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 404 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 405 */         SalesPreshiporderItemList);
/* 406 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 407 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 409 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 411 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 412 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 413 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 419 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 420 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 421 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 422 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 423 */     SalesPreshiporderBatchManager batchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/*     */     
/* 425 */     String shipOrderId = request.getParameter("id");
/* 426 */     SalesPreshiporder salesPreshiporder = spsom.getById(
/* 427 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 429 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 430 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 431 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 433 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 434 */     conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 435 */     List<SalesPreshiporderBatch> batchList = batchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.ID, false);
/*     */     
/* 437 */     request.setAttribute("x_salesShipOrder", salesPreshiporder);
/* 438 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 439 */         SalesPreshiporderItemList);
/* 440 */     request.setAttribute("x_batchList", batchList);
/*     */     
/* 442 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 444 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 445 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 446 */     return mapping.findForward("page");
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
/*     */   public ActionForward updatePirntSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 462 */     SalesPreshiporderManager spsom = 
/* 463 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 464 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/*     */     
/* 466 */     String ids = request.getParameter("id");
/* 467 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/* 468 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 469 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 470 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 471 */     for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 472 */       salesPreshiporderItem.setStatusConfirm(YesNo.YES);
/* 473 */       salesPreshiporderItemManager.update(salesPreshiporderItem);
/*     */     } 
/* 475 */     salesPreshiporder.setIsPrint(YesNo.YES);
/* 476 */     spsom.update(salesPreshiporder);
/* 477 */     return new ActionForward("/listSalesPickingOrder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewCreateSalesPreShipOrderPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 483 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 484 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 485 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 486 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 487 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 489 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 490 */     String shipOrderId = request.getParameter("id");
/* 491 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 493 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 494 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 495 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 497 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 498 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 500 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 502 */     if (formBean.isFirstInit()) {
/* 503 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 505 */       formBean.init();
/*     */     } 
/* 507 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 508 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 509 */         SalesPreshiporderItemList);
/* 510 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 511 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 513 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 515 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 516 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 517 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 522 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 523 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 524 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 525 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 526 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 528 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 529 */     String shipOrderId = request.getParameter("id");
/* 530 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 532 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 533 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 534 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 536 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 537 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/* 538 */     conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 539 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 541 */     if (formBean.isFirstInit()) {
/* 542 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 544 */       formBean.init();
/*     */     } 
/* 546 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 547 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 548 */         SalesPreshiporderItemList);
/* 549 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 550 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 552 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 554 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 555 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 556 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesPreshiporderPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */