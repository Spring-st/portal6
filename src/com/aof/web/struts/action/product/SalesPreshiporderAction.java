/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.SalesOrderItem;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.SalesOrderItemQueryCondition;
/*     */ import com.aof.model.product.query.SalesOrderItemQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryOrder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryCondition;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
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
/*     */ import java.util.ArrayList;
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
/*     */ public class SalesPreshiporderAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  69 */     SalesPreshiporderManager salesPreshiporderManager = 
/*  70 */       ServiceLocator.getSalesPreshiporderManager(request);
/*  71 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*  72 */     Map conditions = getQueryConditions(queryForm);
/*  73 */     getConditionAndOrder(queryForm, conditions, request);
/*  74 */     conditions.put(SalesPreshiporderQueryCondition.TYPE_EQ, Integer.valueOf(1));
/*  75 */     String exportType = queryForm.getExportType();
/*  76 */     if (exportType != null && exportType.length() > 0) {
/*  77 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/*  78 */           null, false);
/*  79 */       int index = SessionTempFile.createNewTempFile(request);
/*  80 */       String fileName = "SalesPreshiporder";
/*  81 */       String suffix = ExportUtil.export(
/*  82 */           exportType, 
/*  83 */           data, 
/*  84 */           request, 
/*  85 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  86 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*     */             {
/*  90 */               MessageResources messages = SalesPreshiporderAction.this.getResources(request);
/*  91 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPreshiporder.id"));
/*  92 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPreshiporder.arrivaldate"));
/*  93 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPreshiporder.remark"));
/*  94 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPreshiporder.status"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  99 */               SalesPreshiporder salesPreshiporder = (SalesPreshiporder)data;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 104 */               row.add(BeanUtils.getProperty(data, "code"));
/* 105 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/* 106 */               row.add(BeanUtils.getProperty(data, "remark"));
/* 107 */               row.add(BeanUtils.getProperty(data, "status"));
/*     */             }
/*     */           });
/* 110 */       return new ActionForward("download/" + index + "/" + 
/* 111 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 114 */     if (queryForm.isFirstInit()) {
/* 115 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 117 */       queryForm.init();
/*     */     } 
/*     */     
/* 120 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 121 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 122 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 123 */         conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 124 */     request.setAttribute("x_selType", Integer.valueOf(118));
/* 125 */     request.setAttribute("X_RESULTLIST", resultList);
/* 126 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesPickingOrderList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 132 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 133 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 134 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/* 135 */     Map conditions = getQueryConditions(queryForm);
/* 136 */     if (queryForm.getOrder() == "") {
/* 137 */       queryForm.setDescend(true);
/*     */     }
/* 139 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 140 */     String exportType = queryForm.getExportType();
/* 141 */     if (exportType != null && exportType.length() > 0) {
/* 142 */       List data = salesPreshiporderManager.getList(conditions, 0, -1, 
/* 143 */           null, false);
/* 144 */       int index = SessionTempFile.createNewTempFile(request);
/* 145 */       String fileName = "salesPickingOrderList";
/* 146 */       String suffix = ExportUtil.export(
/* 147 */           exportType, 
/* 148 */           data, 
/* 149 */           request, 
/* 150 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 151 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*     */             {
/* 155 */               MessageResources messages = SalesPreshiporderAction.this.getResources(request);
/* 156 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.code"));
/* 157 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.createDate"));
/* 158 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.customerName"));
/* 159 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.customerCode"));
/* 160 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.arrivaldate"));
/* 161 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.status"));
/* 162 */               row.add(messages.getMessage(SalesPreshiporderAction.this.getLocale(request), "salesPickingOrderList.isPrint"));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 171 */               row.add(BeanUtils.getProperty(data, "code"));
/* 172 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 173 */               row.add(BeanUtils.getProperty(data, "customerName"));
/* 174 */               row.add(BeanUtils.getProperty(data, "customerCode"));
/* 175 */               row.add(BeanUtils.getProperty(data, "arrivaldate"));
/* 176 */               String status = BeanUtils.getProperty(data, "status");
/* 177 */               if (status.equals("1")) {
/* 178 */                 row.add("待检料");
/*     */               }
/* 180 */               if (status.equals("2")) {
/* 181 */                 row.add("已发货");
/*     */               }
/* 183 */               String isPrint = BeanUtils.getProperty(data, "isPrint");
/* 184 */               if (isPrint.equals("0")) {
/* 185 */                 row.add("已打印");
/*     */               }
/* 187 */               if (isPrint.equals("1")) {
/* 188 */                 row.add("未打印");
/*     */               }
/*     */             }
/*     */           });
/*     */       
/* 193 */       return new ActionForward("download/" + index + "/" + 
/* 194 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 197 */     if (queryForm.isFirstInit()) {
/* 198 */       queryForm.init(salesPreshiporderManager.getListCount(conditions));
/*     */     } else {
/* 200 */       queryForm.init();
/*     */     } 
/*     */     
/* 203 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 204 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 205 */     List<SalesPreshiporder> resultList = salesPreshiporderManager.getList(
/* 206 */         conditions, pageNo.intValue(), pageSize.intValue(), SalesPreshiporderQueryOrder.ID, queryForm.isDescend());
/* 207 */     request.setAttribute("x_selType", Integer.valueOf(119));
/* 208 */     request.setAttribute("X_RESULTLIST", resultList);
/* 209 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesPreshiporderQueryForm queryForm) {
/* 214 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 215 */     String queryStr = "";
/* 216 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 219 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 223 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 224 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 230 */     getBasic(request);
/* 231 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 237 */     SalesPreshiporderManager manager = 
/* 238 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 239 */     BeanForm formBean = (BeanForm)form;
/* 240 */     SalesPreshiporder salesPreshiporder = new SalesPreshiporder();
/* 241 */     formBean.populateToBean(salesPreshiporder);
/* 242 */     salesPreshiporder = manager.insert(salesPreshiporder);
/* 243 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 244 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 250 */     SalesPreshiporderManager manager = 
/* 251 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 252 */     String idStr = request.getParameter("id");
/* 253 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 254 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 255 */     if (salesPreshiporder == null) {
/* 256 */       throw new ActionException("salesPreshiporder.notFound", id);
/*     */     }
/* 258 */     if (!isBack(request)) {
/* 259 */       BeanForm cityForm = (BeanForm)getForm("/updateSalesPreshiporder", 
/* 260 */           request);
/* 261 */       cityForm.populate(salesPreshiporder, "to_form");
/*     */     } 
/* 263 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 264 */     getBasic(request);
/* 265 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 271 */     SalesPreshiporderManager manager = 
/* 272 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 273 */     String idStr = request.getParameter("id");
/* 274 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 275 */     BeanForm formBean = (BeanForm)form;
/* 276 */     SalesPreshiporder salesPreshiporder = manager.getById(id);
/* 277 */     formBean.populateToBean(salesPreshiporder, request);
/* 278 */     salesPreshiporder = manager.update(salesPreshiporder);
/* 279 */     request.setAttribute("X_OBJECT", salesPreshiporder);
/* 280 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 286 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 287 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 288 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 289 */     salesPreshiporderManager.remove(salesPreshiporderManager.getById(id));
/* 290 */     return new ActionForward("listSalesPreshiporder.do", true);
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
/*     */   public ActionForward selectSalesOrderItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 308 */     SalesOrderItemQueryForm queryForm = (SalesOrderItemQueryForm)form;
/* 309 */     SalesOrderItemManager soim = 
/* 310 */       ServiceLocator.getSalesOrderItemManager(request);
/* 311 */     String idString = queryForm.getSelectSoipitemId();
/* 312 */     String[] ids = idString.split(";");
/* 313 */     Map conditions = new HashMap<Object, Object>();
/*     */     
/* 315 */     conditions.put(SalesOrderItemQueryCondition.QTYOPEN_DT, Integer.valueOf(0));
/* 316 */     conditions.put(SalesOrderItemQueryCondition.STATUS_OPEN_EQ, Integer.valueOf(0));
/* 317 */     getConditionAndOrder(queryForm, conditions, request);
/* 318 */     if (queryForm.isFirstInit()) {
/* 319 */       queryForm.init(soim.getListCount(conditions));
/*     */     } else {
/* 321 */       queryForm.init();
/*     */     } 
/* 323 */     List<SalesOrderItem> result = soim.getList(conditions, 
/* 324 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 325 */         SalesOrderItemQueryOrder.ID, false);
/* 326 */     if (ids.length > 0) {
/* 327 */       for (SalesOrderItem salesOrderItem : result) {
/* 328 */         Integer itemId = salesOrderItem.getId();
/* 329 */         Boolean isClose = Boolean.valueOf(false);
/*     */         
/* 331 */         for (int i = 0; i < ids.length; i++) {
/* 332 */           if (!ids[i].equals("") && 
/* 333 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 334 */             isClose = Boolean.valueOf(true);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 339 */         if (isClose.booleanValue()) {
/* 340 */           salesOrderItem.setChecked("checked");
/*     */         }
/*     */       } 
/*     */     }
/* 344 */     request.setAttribute("X_RESULTLIST", result);
/* 345 */     request.setAttribute("X_YESNOLIST", 
/* 346 */         PersistentEnum.getEnumList(YesNo.class));
/* 347 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 348 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 349 */     return mapping.findForward("page");
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
/*     */   public ActionForward createSalesPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 366 */     SalesPreshiporderManager spsom = 
/* 367 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 368 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*     */     
/* 370 */     String arrivalDate = queryForm.getReceivingDate();
/* 371 */     String[] soItemIdsList = request.getParameterValues("soItemIds");
/* 372 */     String[] deliveryNumbersList = request
/* 373 */       .getParameterValues("deliveryNumbers");
/* 374 */     String[] remarksList = request.getParameterValues("remarks");
/* 375 */     User requestor = getCurrentUser(request);
/* 376 */     spsom.insertSalesPreshiporderByItem(soItemIdsList, deliveryNumbersList, 
/* 377 */         remarksList, arrivalDate, requestor);
/* 378 */     return new ActionForward("/listSalesPreshiporder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesShipOrderByBoxAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 384 */     response.setContentType("text/json");
/* 386 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 388 */     String itemId = request.getParameter("itemId");
/* 389 */     String numbers = request.getParameter("numbers");
/* 390 */     SalesPreshiporderManager spsom = 
/* 391 */       ServiceLocator.getSalesPreshiporderManager(request);
/*     */     
/* 393 */     Map<String, Object> map = spsom.getinvenDetialByPartBycount(itemId, 
/* 394 */         numbers);
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
/* 407 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 413 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 414 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 415 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 416 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 417 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 419 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 420 */     String shipOrderId = request.getParameter("id");
/* 421 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 423 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 424 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 425 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 427 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 428 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 430 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 432 */     if (formBean.isFirstInit()) {
/* 433 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 435 */       formBean.init();
/*     */     } 
/* 437 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 438 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 439 */         SalesPreshiporderItemList);
/* 440 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 441 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 443 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 445 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 446 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 447 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward printSalesPickingOrderBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 453 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 454 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 455 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 456 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 457 */     SalesPreshiporderBatchManager batchManager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/*     */     
/* 459 */     String shipOrderId = request.getParameter("id");
/* 460 */     String type = request.getParameter("type");
/* 461 */     SalesPreshiporder salesPreshiporder = spsom.getById(
/* 462 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 463 */     List<SalesPreshiporderBatch> batchList = new ArrayList<SalesPreshiporderBatch>();
/* 464 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 465 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 466 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 468 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 469 */     conditionsa.put(SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, salesPreshiporder.getId());
/*     */ 
/*     */ 
/*     */     
/* 473 */     request.setAttribute("x_salesShipOrder", salesPreshiporder);
/* 474 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 475 */         SalesPreshiporderItemList);
/*     */ 
/*     */     
/* 478 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 480 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 481 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 482 */     request.setAttribute("x_type", type);
/* 483 */     if (type.equals("1")) {
/* 484 */       batchList = batchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.PARTID, false);
/* 485 */       request.setAttribute("x_batchList", batchList);
/* 486 */       return mapping.findForward("page");
/*     */     } 
/* 488 */     batchList = batchManager.getList(conditionsa, 0, -1, SalesPreshiporderBatchQueryOrder.LOCATIONID, false);
/* 489 */     request.setAttribute("x_batchList", batchList);
/* 490 */     return mapping.findForward("page2");
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
/* 506 */     SalesPreshiporderManager spsom = 
/* 507 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 508 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/*     */     
/* 510 */     String ids = request.getParameter("id");
/* 511 */     SalesPreshiporder salesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(ids)));
/* 512 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 513 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, salesPreshiporder.getId());
/* 514 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/* 515 */     for (SalesPreshiporderItem salesPreshiporderItem : SalesPreshiporderItemList) {
/* 516 */       salesPreshiporderItem.setStatusConfirm(YesNo.YES);
/* 517 */       salesPreshiporderItemManager.update(salesPreshiporderItem);
/*     */     } 
/* 519 */     salesPreshiporder.setIsPrint(YesNo.YES);
/* 520 */     spsom.update(salesPreshiporder);
/* 521 */     return new ActionForward("/listSalesPickingOrder.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewCreateSalesPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 527 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 528 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 529 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 530 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 531 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 533 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 534 */     String shipOrderId = request.getParameter("id");
/* 535 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 537 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 538 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 539 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 541 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 542 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/*     */     
/* 544 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 546 */     if (formBean.isFirstInit()) {
/* 547 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 549 */       formBean.init();
/*     */     } 
/* 551 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 552 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 553 */         SalesPreshiporderItemList);
/* 554 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 555 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 557 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 559 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 560 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 561 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward viewSalesPreShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 566 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 567 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 568 */     SalesOrderManager salesOrderManager = ServiceLocator.getSalesOrderManager(request);
/* 569 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 570 */     SalesWorkorderManager salesWorkorderManager = ServiceLocator.getSalesWorkorderManager(request);
/*     */     
/* 572 */     SalesOrderItemQueryForm formBean = (SalesOrderItemQueryForm)form;
/* 573 */     String shipOrderId = request.getParameter("id");
/* 574 */     SalesPreshiporder SalesPreshiporder = spsom.getById(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*     */     
/* 576 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 577 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_ID_EQ, SalesPreshiporder.getId());
/* 578 */     List<SalesPreshiporderItem> SalesPreshiporderItemList = salesPreshiporderItemManager.getList(conditions, 0, -1, SalesPreshiporderItemQueryOrder.ID, false);
/*     */     
/* 580 */     Map<Object, Object> conditionsa = new HashMap<Object, Object>();
/* 581 */     conditionsa.put(SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, SalesPreshiporder.getId());
/* 582 */     conditionsa.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_GE, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 583 */     List<SalesWorkorder> salesWorkorderList = salesWorkorderManager.getList(conditionsa, formBean.getPageNoAsInt(), formBean.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/*     */     
/* 585 */     if (formBean.isFirstInit()) {
/* 586 */       formBean.init(salesWorkorderManager.getListCount(conditionsa));
/*     */     } else {
/* 588 */       formBean.init();
/*     */     } 
/* 590 */     request.setAttribute("x_salesShipOrder", SalesPreshiporder);
/* 591 */     request.setAttribute("x_salesPreshiporderItemList", 
/* 592 */         SalesPreshiporderItemList);
/* 593 */     request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 594 */     request.setAttribute("x_shipOrderId", shipOrderId);
/*     */     
/* 596 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 598 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 599 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 600 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward createSalesPreShipOrderAJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 605 */     response.setContentType("text/json");
/* 607 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 609 */     SalesPreshiporderManager spsom = ServiceLocator.getSalesPreshiporderManager(request);
/* 610 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*     */     
/* 612 */     String arrivalDate = queryForm.getReceivingDate();
/* 613 */     String[] soItemIdsList = request.getParameter("soItemIds").split(",");
/* 614 */     String[] deliveryNumbersList = request.getParameter("deliveryNumbers").split(",");
/* 615 */     String[] remarksList = request.getParameter("remarks").split(",");
/* 616 */     User requestor = getCurrentUser(request);
/*     */     
/* 618 */     Map<String, Object> resultMap = spsom.insertSalesPreshiporderConfirm(soItemIdsList, deliveryNumbersList, 
/* 619 */         remarksList, arrivalDate, requestor);
/*     */     
/* 621 */     String sing = resultMap.get("result").toString();
/*     */     
/* 623 */     JSONObject jo = JSONObject.fromObject(resultMap, cfg);
/* 624 */     response.getWriter().print(jo);
/* 625 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward SalesPickingOrderReplaceBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 630 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 631 */     String type = request.getParameter("type");
/* 632 */     SalesPreshiporderBatchManager manager = ServiceLocator.getSalesPreshiporderBatchManager(request);
/* 633 */     SalesPreshiporderManager salesPreshiporderManager = 
/* 634 */       ServiceLocator.getSalesPreshiporderManager(request);
/* 635 */     SalesPreshiporderBatch batch = manager.getById(id);
/* 636 */     salesPreshiporderManager.insertSalesPickingOrderReplaceBatchByItem(batch);
/*     */     
/* 638 */     return new ActionForward("printSalesPickingOrderBatch.do?id=" + batch.getSpsoitemId().getSpsoId().getId() + "&type=" + type, true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesPreshiporderAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */