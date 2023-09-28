/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryOrder;
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
/*     */ import com.aof.web.struts.action.BaseAction2;
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
/*     */ import net.sf.json.JSONObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomerPlanPreshiporderAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  74 */     SalesPreshiporderManager salesPreshiporderManager = 
/*  75 */       ServiceLocator.getSalesPreshiporderManager(request);
/*  76 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*  77 */     if (queryForm.getOrder() == "") {
/*  78 */       queryForm.setDescend(true);
/*     */     }
/*  80 */     Map conditions = getQueryConditions(queryForm);
/*  81 */     getConditionAndOrder(queryForm, conditions, request);
/*  82 */     conditions.put(SalesPreshiporderQueryCondition.TYPE_EQ, Integer.valueOf(2));
/*  83 */     String exportType = queryForm.getExportType();
/*  84 */     if (exportType != null && exportType.length() > 0) {
/*  85 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/*  86 */           null, false);
/*  87 */       int index = SessionTempFile.createNewTempFile(request);
/*  88 */       String fileName = "SalesPreshiporder";
/*  89 */       String suffix = ExportUtil.export(
/*  90 */           exportType, 
/*  91 */           data, 
/*  92 */           request, 
/*  93 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  94 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*     */             {
/*  98 */               MessageResources messages = CustomerPlanPreshiporderAction.this.getResources(request);
/*  99 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPreshiporder.id"));
/* 100 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPreshiporder.arrivaldate"));
/* 101 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPreshiporder.remark"));
/* 102 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPreshiporder.status"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 107 */               SalesPreshiporder salesPreshiporder = (SalesPreshiporder)data;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 112 */               row.add(BeanUtils.getProperty(data, "code"));
/* 113 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/* 114 */               row.add(BeanUtils.getProperty(data, "remark"));
/* 115 */               row.add(BeanUtils.getProperty(data, "status"));
/*     */             }
/*     */           });
/* 118 */       return new ActionForward("download/" + index + "/" + 
/* 119 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 122 */     if (queryForm.isFirstInit()) {
/* 123 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 125 */       queryForm.init();
/*     */     } 
/*     */     
/* 128 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 129 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 130 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 131 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesPreshiporderQueryOrder.ID, queryForm.isDescend());
/* 132 */     request.setAttribute("x_selType", Integer.valueOf(133));
/* 133 */     request.setAttribute("X_RESULTLIST", resultList);
/* 134 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesPickingOrderList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 140 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 141 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 142 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/* 143 */     Map conditions = getQueryConditions(queryForm);
/* 144 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 145 */     String exportType = queryForm.getExportType();
/* 146 */     if (exportType != null && exportType.length() > 0) {
/* 147 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/* 148 */           null, false);
/* 149 */       int index = SessionTempFile.createNewTempFile(request);
/* 150 */       String fileName = "salesPickingOrderList";
/* 151 */       String suffix = ExportUtil.export(
/* 152 */           exportType, 
/* 153 */           data, 
/* 154 */           request, 
/* 155 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 156 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*     */             {
/* 160 */               MessageResources messages = CustomerPlanPreshiporderAction.this.getResources(request);
/* 161 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.code"));
/* 162 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.createDate"));
/* 163 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.customerName"));
/* 164 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.customerCode"));
/* 165 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.arrivaldate"));
/* 166 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.status"));
/* 167 */               row.add(messages.getMessage(CustomerPlanPreshiporderAction.this.getLocale(request), "salesPickingOrderList.isPrint"));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 176 */               row.add(BeanUtils.getProperty(data, "code"));
/* 177 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 178 */               row.add(BeanUtils.getProperty(data, "customerName"));
/* 179 */               row.add(BeanUtils.getProperty(data, "customerCode"));
/* 180 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/* 181 */               String status = BeanUtils.getProperty(data, "status");
/* 182 */               if (status.equals("1")) {
/* 183 */                 row.add("待检料");
/*     */               }
/* 185 */               if (status.equals("2")) {
/* 186 */                 row.add("已发货");
/*     */               }
/* 188 */               String isPrint = BeanUtils.getProperty(data, "isPrint");
/* 189 */               if (isPrint.equals("0")) {
/* 190 */                 row.add("已打印");
/*     */               }
/* 192 */               if (isPrint.equals("1")) {
/* 193 */                 row.add("未打印");
/*     */               }
/*     */             }
/*     */           });
/*     */       
/* 198 */       return new ActionForward("download/" + index + "/" + 
/* 199 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 202 */     if (queryForm.isFirstInit()) {
/* 203 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 205 */       queryForm.init();
/*     */     } 
/*     */     
/* 208 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 209 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 210 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 211 */         conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 212 */     request.setAttribute("x_selType", Integer.valueOf(119));
/* 213 */     request.setAttribute("X_RESULTLIST", resultList);
/* 214 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesPreshiporderQueryForm queryForm) {
/* 219 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 220 */     String queryStr = "";
/* 221 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 224 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 228 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 229 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 235 */     getBasic(request);
/* 236 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 242 */     SalesPreshiporderManager manager = 
/* 243 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 244 */     BeanForm formBean = (BeanForm)form;
/* 245 */     SalesPreshiporder salesPreshiporder = new SalesPreshiporder();
/* 246 */     formBean.populateToBean(salesPreshiporder);
/* 247 */     salesPreshiporder = manager.insert(salesPreshiporder);
/* 248 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 249 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 255 */     SalesPreshiporderManager manager = 
/* 256 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 257 */     String idStr = request.getParameter("id");
/* 258 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 259 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 260 */     if (salesPreshiporder == null) {
/* 261 */       throw new ActionException("salesPreshiporder.notFound", id);
/*     */     }
/* 263 */     if (!isBack(request)) {
/* 264 */       BeanForm cityForm = (BeanForm)getForm("/updateSalesPreshiporder", 
/* 265 */           request);
/* 266 */       cityForm.populate(salesPreshiporder, "to_form");
/*     */     } 
/* 268 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 269 */     getBasic(request);
/* 270 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 276 */     SalesPreshiporderManager manager = 
/* 277 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 278 */     String idStr = request.getParameter("id");
/* 279 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 280 */     BeanForm formBean = (BeanForm)form;
/* 281 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 282 */     formBean.populateToBean(salesPreshiporder, request);
/* 283 */     salesPreshiporder = manager.update(salesPreshiporder);
/* 284 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 285 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 291 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 292 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 293 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 294 */     salesPreshiporderManager.remove(salesPreshiporderManager.getById(id));
/* 295 */     return new ActionForward("listSalesPreshiporder.do", true);
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
/* 313 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 314 */     CustomerPlanQueryForm queryForm = (CustomerPlanQueryForm)form;
/* 315 */     String idString = request.getParameter("customerPlamId");
/* 316 */     Map conditions = new HashMap();
/* 317 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/* 319 */     conditions.put(CustomerPlanQueryCondition.QTYOPEN_DT, Integer.valueOf(0));
/* 320 */     conditions.put(CustomerPlanQueryCondition.STATUS_OPEN_EQ, Integer.valueOf(0));
/*     */     
/* 322 */     if (queryForm.isFirstInit()) {
/* 323 */       queryForm.init(customerPlanManager.getListCount(conditions));
/*     */     } else {
/* 325 */       queryForm.init();
/*     */     } 
/*     */     
/* 328 */     List<CustomerPlan> resultList = customerPlanManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerPlanQueryOrder.ID, false);
/* 329 */     if (idString != null && 
/* 330 */       !idString.equals("")) {
/* 331 */       String[] ids = idString.split(";");
/* 332 */       if (ids.length > 0) {
/* 333 */         for (CustomerPlan customerPlan : resultList) {
/* 334 */           Integer customerPlamId = customerPlan.getId();
/* 335 */           Boolean isClose = Boolean.valueOf(false);
/*     */           
/* 337 */           for (int i = 0; i < ids.length; i++) {
/* 338 */             if (!ids[i].equals("") && 
/* 339 */               customerPlamId.intValue() == Integer.parseInt(ids[i])) {
/* 340 */               isClose = Boolean.valueOf(true);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 345 */           if (isClose.booleanValue()) {
/* 346 */             customerPlan.setChecked("checked");
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 352 */     request.setAttribute("X_RESULTLIST", resultList);
/* 353 */     request.setAttribute("x_selType", Integer.valueOf(113));
/* 354 */     return mapping.findForward("page");
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
/*     */   public ActionForward createCustomerPlanPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 371 */     SalesPreshiporderManager spsom = 
/* 372 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 373 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*     */     
/* 375 */     String arrivalDate = queryForm.getReceivingDate();
/* 376 */     String[] soItemIdsList = request.getParameterValues("soItemIds");
/* 377 */     String[] deliveryNumbersList = request
/* 378 */       .getParameterValues("deliveryNumbers");
/* 379 */     String[] remarksList = request.getParameterValues("remarks");
/* 380 */     User requestor = getCurrentUser(request);
/* 381 */     spsom.insertSalesPreshiporderByItem(soItemIdsList, deliveryNumbersList, 
/* 382 */         remarksList, arrivalDate, requestor);
/* 383 */     return new ActionForward("/listCustomerPlanPreshiporder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesShipOrderByBoxAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 389 */     response.setContentType("text/json");
/* 391 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 393 */     String itemId = request.getParameter("itemId");
/* 394 */     String numbers = request.getParameter("numbers");
/* 395 */     SalesPreshiporderManager spsom = 
/* 396 */       ServiceLocator.getSalesPreshiporderManager(request);
/*     */     
/* 398 */     Map<String, Object> map = spsom.getinvenDetialByPartBycount(itemId, 
/* 399 */         numbers);
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
/* 412 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 418 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 419 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 420 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 421 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 422 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 424 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 425 */     String shipOrderId = request.getParameter("id");
/* 426 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 428 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 429 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 430 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 432 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 433 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 435 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 437 */     if (formBean.isFirstInit()) {
/* 438 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 440 */       formBean.init();
/*     */     } 
/* 442 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 443 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 444 */         SalesPreshiporderItemList);
/* 445 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 446 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 448 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 450 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 451 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 452 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 458 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 459 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 460 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 461 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 462 */     SalesPreshiporderBatchManager batchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/*     */     
/* 464 */     String shipOrderId = request.getParameter("id");
/* 465 */     String type = request.getParameter("type");
/* 466 */     SalesPreshiporder salesPreshiporder = spsom.getById(
/* 467 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 469 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 470 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 471 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 473 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 474 */     conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 475 */     List<SalesPreshiporderBatch> batchList = batchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.LOCATIONID, false);
/*     */     
/* 477 */     request.setAttribute("x_salesShipOrder", salesPreshiporder);
/* 478 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 479 */         SalesPreshiporderItemList);
/* 480 */     request.setAttribute("x_batchList", batchList);
/*     */     
/* 482 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 484 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 485 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 486 */     if (type.equals("1")) {
/* 487 */       return mapping.findForward("page");
/*     */     }
/* 489 */     return mapping.findForward("page2");
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
/* 505 */     SalesPreshiporderManager spsom = 
/* 506 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 507 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/*     */     
/* 509 */     String ids = request.getParameter("id");
/* 510 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/* 511 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 512 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 513 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 514 */     for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 515 */       salesPreshiporderItem.setStatusConfirm(YesNo.YES);
/* 516 */       salesPreshiporderItemManager.update(salesPreshiporderItem);
/*     */     } 
/* 518 */     salesPreshiporder.setIsPrint(YesNo.YES);
/* 519 */     spsom.update(salesPreshiporder);
/* 520 */     return new ActionForward("/listSalesPickingOrder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewCreateCustomerPlanPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 526 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 527 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 528 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 529 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 530 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 532 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 533 */     String shipOrderId = request.getParameter("id");
/* 534 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 536 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 537 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 538 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 540 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 541 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 543 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 545 */     if (formBean.isFirstInit()) {
/* 546 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 548 */       formBean.init();
/*     */     } 
/* 550 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 551 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 552 */         SalesPreshiporderItemList);
/* 553 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 554 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 556 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 558 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 559 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 560 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 565 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 566 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 567 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 568 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 569 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 571 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 572 */     String shipOrderId = request.getParameter("id");
/* 573 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 575 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 576 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 577 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 579 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 580 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/* 581 */     conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 582 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 584 */     if (formBean.isFirstInit()) {
/* 585 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 587 */       formBean.init();
/*     */     } 
/* 589 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 590 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 591 */         SalesPreshiporderItemList);
/* 592 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 593 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 595 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 597 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 598 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 599 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward createCustomerPlanPreShipOrderAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 604 */     response.setContentType("text/json");
/* 606 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 608 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 609 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*     */     
/* 611 */     String arrivalDate = queryForm.getReceivingDate();
/* 612 */     String[] soItemIdsList = request.getParameter("soItemIds").split(",");
/* 613 */     String[] deliveryNumbersList = request.getParameter("deliveryNumbers").split(",");
/* 614 */     String[] remarksList = request.getParameter("remarks").split(",");
/* 615 */     User requestor = getCurrentUser(request);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 620 */     Map<String, Object> resultMap = spsom.insertCustomerPlanPreshiporderConfirm(soItemIdsList, deliveryNumbersList, 
/* 621 */         remarksList, arrivalDate, requestor);
/*     */     
/* 623 */     String sing = resultMap.get("result").toString();
/*     */     
/* 625 */     JSONObject jo = JSONObject.fromObject(resultMap, cfg);
/* 626 */     response.getWriter().print(jo);
/* 627 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward SalesPickingOrderReplaceBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 632 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 633 */     SalesPreshiporderBatchManager manager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 634 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 635 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 636 */     SalesPreshiporderBatch batch = manager.getById(id);
/* 637 */     salesPreshiporderManager.insertSalesPickingOrderReplaceBatchByItem(batch);
/* 638 */     return new ActionForward("viewalesPickingOrderBatch.do?id=" + batch.getSpsoitemId().getSpsoId().getId(), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward deleteCustomerPlanPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 643 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 644 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 645 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 646 */     SalesPreshiporderBatchManager batchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/*     */     
/* 648 */     String shipOrderId = request.getParameter("id");
/* 649 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 651 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 652 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 653 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 655 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 656 */     conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 657 */     List<SalesPreshiporderBatch> batchList = batchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.ID, false);
/* 658 */     for (SalesPreshiporderBatch salesPreshiporderBatch : batchList) {
/* 659 */       spsom.updateInventoryOccupiedDetial(salesPreshiporderBatch.getBox());
/* 660 */       batchManager.remove(salesPreshiporderBatch);
/*     */     } 
/*     */     
/* 663 */     for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 664 */       CustomerPlan customerPlan = customerPlanManager.getById(salesPreshiporderItem.getCustomerPlanId().getId());
/* 665 */       customerPlan.setQtyOpen(customerPlan.getQtyOpen().add(salesPreshiporderItem.getDeliverynumber()));
/* 666 */       customerPlanManager.update(customerPlan);
/* 667 */       salesPreshiporderItemManager.remove(salesPreshiporderItem);
/*     */     } 
/* 669 */     spsom.remove(salesPreshiporder);
/* 670 */     return new ActionForward("listCustomerPlanPreshiporder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward verificationCustomerPlanPreshiporderAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 676 */     response.setContentType("text/json");
/* 678 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 680 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 681 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 682 */     Map<String, Object> resultMap = new HashMap<String, Object>();
/*     */     
/* 684 */     String shipOrderId = request.getParameter("id");
/* 685 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 687 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 688 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 689 */     conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 690 */     List<SalesWorkorder> salesWorkorderDeliveryList = salesWorkorderManager.getList(conditionsa, 0, -1, SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 692 */     conditionsa.clear();
/* 693 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, salesPreshiporder.getId());
/* 694 */     conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.ON_LOADINGBATCH);
/* 695 */     List<SalesWorkorder> salesWorkorderLoadingList = salesWorkorderManager.getList(conditionsa, 0, -1, SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 697 */     if (salesWorkorderDeliveryList.size() > 0) {
/* 698 */       resultMap.put("result", "3");
/* 699 */       resultMap.put("order", salesPreshiporder.getCode());
/* 700 */       resultMap.put("count", Integer.valueOf(salesWorkorderDeliveryList.size()));
/*     */     }
/* 702 */     else if (salesWorkorderLoadingList.size() > 0) {
/* 703 */       resultMap.put("result", "2");
/* 704 */       resultMap.put("order", salesPreshiporder.getCode());
/* 705 */       resultMap.put("count", Integer.valueOf(salesWorkorderLoadingList.size()));
/*     */     } else {
/*     */       
/* 708 */       resultMap.put("result", "1");
/*     */     } 
/*     */     
/* 711 */     JSONObject jo = JSONObject.fromObject(resultMap, cfg);
/* 712 */     response.getWriter().print(jo);
/* 713 */     return null;
/*     */   }
/*     */ }
