/*      */ package com.aof.web.struts.action.po;
/*      */ 
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.admin.query.SiteQueryCondition;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.PortalShipOrderStatus;
/*      */ import com.aof.model.metadata.PurchaseOrderStatus;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.PortalShipOrder;
/*      */ import com.aof.model.po.PortalShipOrderItem;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*      */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*      */ import com.aof.model.po.query.PortalShipOrderQueryOrder;
/*      */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryCondition;
/*      */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
/*      */ import com.aof.model.schedule.ProjectedInventory;
/*      */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*      */ import com.aof.service.admin.SiteManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.basic.WmsPartManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.PortalShipOrderItemManager;
/*      */ import com.aof.service.po.PortalShipOrderManager;
/*      */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*      */ import com.aof.service.po.PurchaseOrderManager;
/*      */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*      */ import com.aof.service.quartz.job.DeliverMinuteSyncJob;
/*      */ import com.aof.service.schedule.ProjectedInventoryManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.ActionUtils;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.admin.PortalShipOrderMainQueryForm;
/*      */ import com.aof.web.struts.form.po.PurchaseOrderInspectionPendingItemQueryForm;
/*      */ import com.aof.web.struts.form.po.PurchaseOrderInspectionPendingQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.utils.BeanUtils;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import com.shcnc.utils.MD5;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import net.sf.json.JSONArray;
/*      */ import net.sf.json.JSONObject;
/*      */ import net.sf.json.JsonConfig;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.struts.action.ActionForm;
/*      */ import org.apache.struts.action.ActionForward;
/*      */ import org.apache.struts.action.ActionMapping;
/*      */ import org.apache.struts.util.MessageResources;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PortalShipOrderAction
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward shippingOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*   87 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*   88 */     PortalShipOrderManager fm = 
/*   89 */       ServiceLocator.getPortalShipOrderManager(request);
/*   90 */     if (queryForm.getOrder() == "" && StringUtils.isEmpty(queryForm.getOrder())) {
/*   91 */       queryForm.setOrder("createDate");
/*   92 */       queryForm.setDescend(true);
/*      */     } 
/*   94 */     queryForm.setOrder("createDate");
/*      */     
/*   96 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*   97 */     getConditionsFrom(queryForm, conditions);
/*   98 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */ 
/*      */     
/*  102 */     conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "NJIT_PO");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  113 */     String exportType = queryForm.getExportType();
/*  114 */     if (exportType != null && exportType.length() > 0) {
/*  115 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/*  116 */           false);
/*  117 */       int index = SessionTempFile.createNewTempFile(request);
/*  118 */       String fileName = "PortalShipOrder";
/*  119 */       String suffix = ExportUtil.export(
/*  120 */           exportType, 
/*  121 */           data, 
/*  122 */           request, 
/*  123 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  124 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  127 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/*  128 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  129 */                     "SO.No1"));
/*  130 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  131 */                     "supplier1"));
/*  132 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  133 */                     "supplier.code1"));
/*  134 */               row.add("创建时间");
/*  135 */               row.add("发货时间");
/*  136 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  137 */                     "salesOrderItem.issync"));
/*  138 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  139 */                     "portalShipOrder.status"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  144 */               row.add(BeanUtils.getProperty(data, "code"));
/*  145 */               row.add(
/*  146 */                   BeanUtils.getProperty(data, "supplier.name"));
/*  147 */               row.add(
/*  148 */                   BeanUtils.getProperty(data, "supplier.code"));
/*      */               
/*  150 */               row.add(BeanUtils.getProperty(data, "createDate"));
/*  151 */               row.add(BeanUtils.getProperty(data, "arrivalDate"));
/*  152 */               PortalShipOrder order = (PortalShipOrder)data;
/*  153 */               if (order.getIsSync() != null) {
/*  154 */                 if (order.getIsSync().equals("0")) {
/*  155 */                   row.add("未同步");
/*  156 */                 } else if (order.getIsSync().equals("1")) {
/*  157 */                   row.add("已同步");
/*  158 */                 } else if (order.getIsSync().equals("")) {
/*  159 */                   row.add("未同步");
/*      */                 } 
/*      */               } else {
/*  162 */                 row.add("未同步");
/*      */               } 
/*  164 */               String locale = PortalShipOrderAction.this.getCurrentUser(request).getLocale();
/*  165 */               if ("en".equals(locale)) {
/*  166 */                 row.add(BeanUtils.getProperty(data, 
/*  167 */                       "status.engShortDescription"));
/*      */               } else {
/*  169 */                 row.add(BeanUtils.getProperty(data, 
/*  170 */                       "status.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/*  174 */       return new ActionForward("download/" + index + "/" + 
/*  175 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  177 */     if (queryForm.isFirstInit()) {
/*  178 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  180 */       queryForm.init();
/*      */     } 
/*  182 */     List result = fm.getPortalShipOrderList(conditions, 
/*  183 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  184 */         PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  185 */         queryForm.isDescend());
/*  186 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  187 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  188 */     request.setAttribute("x_statusList", 
/*  189 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  190 */     request.setAttribute("X_RESULTLIST", result);
/*  191 */     request.setAttribute("x_selType", Integer.valueOf(159));
/*  192 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listShipOrderReport1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  199 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*  200 */     PortalShipOrderManager fm = 
/*  201 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/*  203 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  204 */     conditions.put(PortalShipOrderQueryCondition.SITE_EQ, 
/*  205 */         getCurrentUser(request).getPrimarySite().getId());
/*  206 */     getConditionsFrom(queryForm, conditions);
/*  207 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  209 */     String exportType = queryForm.getExportType();
/*  210 */     if (queryForm.isFirstInit()) {
/*  211 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  213 */       queryForm.init();
/*      */     } 
/*  215 */     List result = fm.getPortalShipOrderList(conditions, 
/*  216 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  217 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*  218 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  219 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  220 */     request.setAttribute("x_statusList", 
/*  221 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  222 */     request.setAttribute("X_RESULTLIST", result);
/*  223 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listShipOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  231 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*  232 */     PortalShipOrderManager fm = 
/*  233 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/*  235 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */ 
/*      */     
/*  238 */     getConditionsFrom(queryForm, conditions);
/*  239 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  241 */     if (queryForm.isFirstInit()) {
/*  242 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  244 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/*  248 */     String exportType = queryForm.getExportType();
/*  249 */     if (StringUtils.isNotEmpty(exportType)) {
/*  250 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, 
/*  251 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  252 */           queryForm.isDescend());
/*  253 */       int index = SessionTempFile.createNewTempFile(request);
/*  254 */       String fileName = "PortalShip";
/*  255 */       String suffix = ExportUtil.export(
/*  256 */           exportType, 
/*  257 */           data, 
/*  258 */           request, 
/*  259 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  260 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  264 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/*  265 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  266 */                     "PortalShip.code"));
/*  267 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  268 */                     "PortalShip.createDate"));
/*  269 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  270 */                     "WmsUW.remark"));
/*  271 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/*  272 */                     "WmsUW.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  277 */               row.add(BeanUtils.getProperty(data, "code"));
/*  278 */               row.add(BeanUtils.getProperty(data, "createDate"));
/*  279 */               row.add(BeanUtils.getProperty(data, "remark"));
/*  280 */               if (BeanUtils.getProperty(data, "status") == "0") {
/*  281 */                 row.add("成功");
/*      */               } else {
/*  283 */                 row.add(BeanUtils.getProperty(data, "status"));
/*      */               } 
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  289 */       return new ActionForward("download/" + index + "/" + 
/*  290 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  292 */     List result = fm.getPortalShipOrderList(conditions, 
/*  293 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  294 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*  295 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  296 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  297 */     request.setAttribute("x_statusList", 
/*  298 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  299 */     request.setAttribute("X_RESULTLIST", result);
/*  300 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void getConditionsFrom(PortalShipOrderMainQueryForm queryForm, Map<PortalShipOrderQueryCondition, String> conditions) {
/*  306 */     String code = queryForm.getCode();
/*  307 */     if (code != null && code.trim().length() != 0) {
/*  308 */       conditions.put(PortalShipOrderQueryCondition.CODE_EQ, code);
/*      */     }
/*  310 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/*  311 */     if (enabled != null && enabled.intValue() != 0) {
/*  312 */       conditions.put(PortalShipOrderQueryCondition.ENABLED_EQ, enabled);
/*      */     }
/*  314 */     String status = queryForm.getStatus();
/*  315 */     if (status != null && status.trim().length() != 0) {
/*  316 */       conditions.put(
/*  317 */           PortalShipOrderQueryCondition.PORTALSHIPORDER_STATUS_EQ, 
/*  318 */           status);
/*      */     }
/*  320 */     String startDate1 = queryForm.getCreateDate1();
/*  321 */     String startDate2 = queryForm.getCreateDate2();
/*  322 */     if (startDate1 != null && startDate1.trim().length() != 0) {
/*  323 */       conditions.put(PortalShipOrderQueryCondition.CREATEDATE_GE, 
/*  324 */           startDate1);
/*      */     }
/*  326 */     if (startDate2 != null && startDate2.trim().length() != 0) {
/*  327 */       conditions.put(PortalShipOrderQueryCondition.CREATEDATE_LT, 
/*  328 */           startDate2);
/*      */     }
/*  330 */     String pocode = queryForm.getPocode();
/*  331 */     if (pocode != null && pocode.trim().length() != 0) {
/*  332 */       conditions.put(PortalShipOrderQueryCondition.PO_CODE_EQ, pocode);
/*      */     }
/*  334 */     String partCode = queryForm.getPart_code();
/*  335 */     if (partCode != null && partCode.trim().length() != 0) {
/*  336 */       conditions
/*  337 */         .put(PortalShipOrderQueryCondition.PART_CODE_EQ, partCode);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  345 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/*  346 */     PurchaseOrderInspectionPendingManager poipm = 
/*  347 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  348 */     PortalShipOrderManager portalShipOrderManager = 
/*  349 */       ServiceLocator.getPortalShipOrderManager(request);
/*  350 */     String shipOrderId = request.getParameter("id");
/*  351 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  352 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  353 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  354 */       .getPortalShipOrderItemListByOrderId(
/*  355 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  356 */     User user = getCurrentUser(request);
/*  357 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  358 */     request.setAttribute("x_portalShipOrderItemList", 
/*  359 */         portalShipOrderItemList);
/*  360 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/*  362 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  363 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  364 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPurchaseOrderActualReceipts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  370 */     PurchaseOrderInspectionPendingManager poipm = 
/*  371 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  372 */     PortalShipOrderManager portalShipOrderManager = 
/*  373 */       ServiceLocator.getPortalShipOrderManager(request);
/*  374 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/*  375 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  376 */     String shipOrderId = request.getParameter("id");
/*  377 */     String itemIds = request.getParameter("item");
/*  378 */     if (itemIds != "") {
/*  379 */       String[] arrays = itemIds.split(";"); byte b; int i; String[] arrayOfString1;
/*  380 */       for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/*  381 */         String[] item = array.split(",");
/*  382 */         String itemId = item[0];
/*  383 */         String actual = item[1];
/*  384 */         PortalShipOrderItem portalShipOrderItem = portalShipOrderItemManager
/*  385 */           .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(itemId)));
/*      */         
/*  387 */         portalShipOrderItem.setActual_qty(new BigDecimal(actual));
/*      */         
/*  389 */         portalShipOrderItemManager
/*  390 */           .updatePortalShipOrderItem(portalShipOrderItem); b++; }
/*      */     
/*      */     } 
/*  393 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  394 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  395 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  396 */       .getPortalShipOrderItemListByOrderId(
/*  397 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  398 */     Supplier supplier = ((PortalShipOrderItem)portalShipOrderItemList.get(0)).getPoipItem()
/*  399 */       .getPoip_number().getSupplier();
/*  400 */     request.setAttribute("supplier", supplier);
/*  401 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  402 */     request.setAttribute("x_portalShipOrderItemList", 
/*  403 */         portalShipOrderItemList);
/*  404 */     request.getSession().setAttribute("path", request.getContextPath());
/*  405 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  406 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  407 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PortalShipOrder getPortalShipOrder(HttpServletRequest request) throws Exception {
/*  424 */     String id = request.getParameter("id");
/*  425 */     PortalShipOrderManager portalShipOrderManager = 
/*  426 */       ServiceLocator.getPortalShipOrderManager(request);
/*  427 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  428 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/*  429 */     if (portalShipOrder == null)
/*  430 */       throw new ActionException("portalShipOrder.notFound", id); 
/*  431 */     return portalShipOrder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalShipOrderItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  447 */     PortalShipOrderItemManager itemsmanager = 
/*  448 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  449 */     String ids = request.getParameter("id");
/*  450 */     String[] id = ids.split(";"); byte b; int i; String[] arrayOfString1;
/*  451 */     for (i = (arrayOfString1 = id).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/*  452 */       PortalShipOrderItem item = itemsmanager
/*  453 */         .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(str)));
/*  454 */       item.setPrintStatus(YesNo.YES);
/*  455 */       itemsmanager.updatePortalShipOrderItem(item);
/*      */       b++; }
/*      */     
/*  458 */     return new ActionForward("listPurchaseOrderActualReceipts.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  466 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/*  467 */     PurchaseOrderInspectionPendingManager poipm = 
/*  468 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  469 */     PortalShipOrderManager portalShipOrderManager = 
/*  470 */       ServiceLocator.getPortalShipOrderManager(request);
/*  471 */     String shipOrderId = request.getParameter("id");
/*  472 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  473 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  474 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  475 */       .getPortalShipOrderItemListByOrderId(
/*  476 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  477 */     User user = getCurrentUser(request);
/*  478 */     String supplierCode = user.getLoginName();
/*  479 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/*  480 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/*  481 */     request.setAttribute("supplier", supplier);
/*  482 */     List<Box> resultList = portalShipOrderManager
/*  483 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/*  485 */     int totalCount = resultList.size();
/*      */     
/*  487 */     int pageCount = 0;
/*      */     
/*  489 */     int endNum = formBean.getPageSizeAsInt();
/*  490 */     if (endNum == -1) {
/*  491 */       endNum = resultList.size();
/*      */     }
/*      */     
/*  494 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/*  496 */     if (totalCount % endNum > 0) {
/*      */       
/*  498 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/*  501 */       pageCount = totalCount / endNum;
/*      */     } 
/*  503 */     if (totalCount > 0) {
/*  504 */       if (startNum <= pageCount) {
/*  505 */         if (startNum == 1) {
/*      */           
/*  507 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/*  511 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/*  515 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/*  517 */           int toIndex = startNum * endNum;
/*      */           
/*  519 */           if ((totalCount - toIndex) % endNum >= 0) {
/*  520 */             toIndex = startNum * endNum;
/*      */           } else {
/*  522 */             toIndex = (startNum - 1) * endNum + 
/*  523 */               totalCount % endNum;
/*      */           } 
/*  525 */           if (totalCount >= toIndex) {
/*  526 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/*  530 */         resultList = null;
/*      */       } 
/*      */     }
/*  533 */     if (formBean.isFirstInit()) {
/*  534 */       formBean.init(totalCount);
/*      */     } else {
/*  536 */       formBean.init();
/*      */     } 
/*  538 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/*  539 */     request.setAttribute("X_DATE", format.format(new Date()));
/*  540 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  541 */     request.setAttribute("x_portalShipOrderItemList", 
/*  542 */         portalShipOrderItemList);
/*  543 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/*  544 */     int size = resultList.size();
/*  545 */     for (Box poIpiBox : resultList) {
/*  546 */       if (poIpiBox.getSupplierBatch() != "" && 
/*  547 */         poIpiBox.getSupplierBatch() != null) {
/*  548 */         size--;
/*  549 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/*  552 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*  553 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/*  555 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  556 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  557 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  563 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/*  564 */     PortalShipOrderManager portalShipOrderManager = 
/*  565 */       ServiceLocator.getPortalShipOrderManager(request);
/*  566 */     String id = request.getParameter("id");
/*  567 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  568 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/*  569 */     PurchaseOrderInspectionPendingManager poipm = 
/*  570 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  571 */     List<Box> resultList = portalShipOrderManager
/*  572 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*  573 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/*  575 */     String str = request.getParameter("supplierBatchStr");
/*  576 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/*  578 */     if ((str != null || poipBoxIds != null) && (
/*  579 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/*  580 */       String[] supplierBatchList = str.split(",");
/*  581 */       String[] poipBoxIdList = poipBoxIds.split(",");
/*  582 */       if (supplierBatchList.length > 0) {
/*  583 */         for (int i = 0; i < supplierBatchList.length; i++) {
/*  584 */           if (poipBoxIdList[i].length() > 0) {
/*  585 */             Integer poipBoxId = 
/*  586 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/*  587 */             String supplierBatch = supplierBatchList[i];
/*  588 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/*  589 */             if (supplierBatch != null || supplierBatch != "") {
/*  590 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/*  592 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/*  594 */               poIpiBox.setSupplierBatch("");
/*  595 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  605 */     int totalCount = resultList.size();
/*      */     
/*  607 */     int pageCount = 0;
/*      */     
/*  609 */     int endNum = formBean.getPageSizeAsInt();
/*  610 */     if (endNum == -1) {
/*  611 */       endNum = resultList.size();
/*      */     }
/*      */     
/*  614 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/*  616 */     if (totalCount % endNum > 0) {
/*      */       
/*  618 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/*  621 */       pageCount = totalCount / endNum;
/*      */     } 
/*  623 */     if (totalCount > 0) {
/*  624 */       if (startNum <= pageCount) {
/*  625 */         if (startNum == 1) {
/*      */           
/*  627 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/*  631 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/*  635 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/*  637 */           int toIndex = startNum * endNum;
/*      */           
/*  639 */           if ((totalCount - toIndex) % endNum >= 0) {
/*  640 */             toIndex = startNum * endNum;
/*      */           } else {
/*  642 */             toIndex = (startNum - 1) * endNum + 
/*  643 */               totalCount % endNum;
/*      */           } 
/*  645 */           if (totalCount >= toIndex) {
/*  646 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/*  650 */         resultList = null;
/*      */       } 
/*      */     }
/*  653 */     if (formBean.isFirstInit()) {
/*  654 */       formBean.init(totalCount);
/*      */     } else {
/*  656 */       formBean.init();
/*      */     } 
/*      */     
/*  659 */     request.setAttribute("x_supplierBatchStr", str);
/*  660 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/*  661 */     request.setAttribute("x_id", id);
/*  662 */     request.setAttribute("x_boxList", resultList);
/*  663 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  664 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward podeletePortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  670 */     PortalShipOrderManager portalShipOrderManager = 
/*  671 */       ServiceLocator.getPortalShipOrderManager(request);
/*  672 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  673 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  674 */     PurchaseOrderManager poManager = 
/*  675 */       ServiceLocator.getPurchaseOrderManager(request);
/*      */     try {
/*  677 */       PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*      */       
/*  679 */       System.out.println(portalShipOrder.getId() + "----");
/*  680 */       List<PortalShipOrderItem> itemlist = portalShipOrderManager
/*  681 */         .getPortalShipOrderItemListByOrderId(portalShipOrder
/*  682 */           .getId());
/*  683 */       for (PortalShipOrderItem portalShipOrderItem : itemlist) {
/*  684 */         PurchaseOrderInspectionPendingItem poipItem = poipManager
/*  685 */           .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
/*  686 */             .getPoipItem().getId());
/*  687 */         BigDecimal wBigDecimal = portalShipOrderItem
/*  688 */           .getDeliveryNumber();
/*      */         
/*  690 */         poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));
/*      */         
/*  692 */         poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);
/*      */         
/*  694 */         portalShipOrderManager
/*  695 */           .deletePurchaseOrderBox(portalShipOrderItem.getId());
/*  696 */         portalShipOrderManager
/*  697 */           .deletePortalShipOrderItem(portalShipOrderItem);
/*      */         
/*  699 */         Boolean isClose = poipManager
/*  700 */           .isclosePurchaseOrderInspectionPendingByItem(poipItem
/*  701 */             .getPoip_number());
/*  702 */         if (!isClose.booleanValue()) {
/*  703 */           poipItem.getPoip_number().setStatus(
/*  704 */               PurchaseOrderStatus.CLOSE);
/*  705 */           poipManager.updatePurchaseOrderInspectionPending(poipItem
/*  706 */               .getPoip_number());
/*      */         } 
/*      */       } 
/*      */       
/*  710 */       portalShipOrderManager.deletePortalShipOrder(portalShipOrder);
/*  711 */     } catch (Exception e) {
/*  712 */       e.printStackTrace();
/*  713 */       e.fillInStackTrace();
/*      */     } 
/*  715 */     return new ActionForward("listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward podeletePortalShipOrderTwoItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  722 */     PortalShipOrderManager portalShipOrderManager = 
/*  723 */       ServiceLocator.getPortalShipOrderManager(request);
/*  724 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  725 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  726 */     PurchaseOrderManager poManager = 
/*  727 */       ServiceLocator.getPurchaseOrderManager(request);
/*      */     try {
/*  729 */       PortalShipOrderItem portalShipOrderItem = portalShipOrderManager
/*  730 */         .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(request
/*  731 */               .getParameter("id"))));
/*  732 */       PurchaseOrderInspectionPendingItem poipItem = poipManager
/*  733 */         .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
/*  734 */           .getPoipItem().getId());
/*  735 */       BigDecimal wBigDecimal = portalShipOrderItem.getDeliveryNumber();
/*      */       
/*  737 */       poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));
/*      */       
/*  739 */       poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);
/*      */ 
/*      */       
/*  742 */       portalShipOrderManager.deletePurchaseOrderBox(portalShipOrderItem
/*  743 */           .getId());
/*      */       
/*  745 */       portalShipOrderManager
/*  746 */         .deletePortalShipOrderItem(portalShipOrderItem);
/*  747 */       poipItem.getPoip_number().setStatus(PurchaseOrderStatus.CLOSE);
/*  748 */       poipManager.updatePurchaseOrderInspectionPending(poipItem
/*  749 */           .getPoip_number());
/*      */     }
/*  751 */     catch (Exception e) {
/*  752 */       e.printStackTrace();
/*  753 */       e.fillInStackTrace();
/*      */     } 
/*  755 */     return new ActionForward("listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  762 */     PortalShipOrderManager portalShipOrderManager = 
/*  763 */       ServiceLocator.getPortalShipOrderManager(request);
/*  764 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  765 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  766 */     PurchaseOrderManager poManager = 
/*  767 */       ServiceLocator.getPurchaseOrderManager(request);
/*  768 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*  769 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/*  771 */     List<Box> list = new ArrayList();
/*  772 */     List<Box> poipBoxList = null;
/*      */     try {
/*  774 */       if (!portalShipOrder.getStatus().equals(
/*  775 */           PortalShipOrderStatus.RECEIVE)) {
/*  776 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/*  777 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/*  779 */         portalShipOrderItemlist = portalShipOrderManager
/*  780 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/*  781 */             .getId());
/*  782 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/*  785 */           poipBoxList = portalShipOrderManager
/*  786 */             .getBoxList(portalShipOrderItem.getId());
/*  787 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/*  790 */     } catch (Exception e) {
/*  791 */       e.fillInStackTrace();
/*  792 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  795 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  796 */     request.setAttribute("x_portalShipOrderItemList", 
/*  797 */         portalShipOrderItemlist);
/*  798 */     request.setAttribute("x_portalShopOrderBoxList", list);
/*  799 */     int size = poipBoxList.size();
/*  800 */     for (Box poIpiBox : poipBoxList) {
/*  801 */       if (poIpiBox.getSupplierBatch() != "" && 
/*  802 */         poIpiBox.getSupplierBatch() != null) {
/*  803 */         size--;
/*  804 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/*  807 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/*  810 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  816 */     PortalShipOrderManager portalShipOrderManager = 
/*  817 */       ServiceLocator.getPortalShipOrderManager(request);
/*  818 */     String id = request.getParameter("id");
/*  819 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*  820 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/*  823 */     return new ActionForward("viewPortalShipOrderTwo.do?id=" + 
/*  824 */         portalShipOrder.getId(), true);
/*      */   }
/*      */   
/*      */   public static void main(String[] str) throws Exception {
/*  828 */     System.out.println(MD5.getDigestString("root"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  834 */     PurchaseOrderInspectionPendingManager poip = 
/*  835 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  836 */     String string = request.getParameter("str");
/*  837 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */     
/*  839 */     String[] strings = string.split(",");
/*  840 */     List<Box> boxlist = new ArrayList<Box>();
/*  841 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/*  842 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/*  843 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/*  845 */       String strNo = box.getPsoItem().getPortalShipOrder().getCode();
/*  846 */       String beathNo = strNo.substring(strNo.length() - 9, strNo.length());
/*  847 */       box.setHuCodeOrderNumber(beathNo);
/*  848 */       boxlist.add(box);
/*      */       b++; }
/*      */     
/*  851 */     request.setAttribute("x_item", item);
/*  852 */     request.setAttribute("X_RESULTLIST", boxlist);
/*  853 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/*  855 */     request.setAttribute("path", request.getContextPath());
/*  856 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  862 */     PurchaseOrderInspectionPendingManager poip = 
/*  863 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  864 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/*  865 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/*  866 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  867 */     boolean bol = false;
/*  868 */     JSONObject object = new JSONObject();
/*  869 */     String ids = request.getParameter("ids");
/*  870 */     String[] idr = ids.split(";");
/*  871 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/*  872 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  873 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/*  874 */       box.setIsPrint(YesNo.YES);
/*  875 */       manager.updateBox(box);
/*  876 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/*  879 */     bol = true;
/*  880 */     object.put("object", Boolean.valueOf(bol));
/*  881 */     object.put("id", idInteger);
/*  882 */     PrintWriter print = response.getWriter();
/*  883 */     print.print(object);
/*  884 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  893 */     String ids = request.getParameter("id");
/*  894 */     PortalShipOrderManager manager = 
/*  895 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */ 
/*      */     
/*  898 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  899 */         Integer.valueOf(Integer.parseInt(ids)));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  913 */     if (order.getPrintDate() == null) {
/*  914 */       order.setPrintDate(new Date());
/*      */     }
/*  916 */     order.setIsPrint(YesNo.YES);
/*  917 */     manager.updatePortalShipOrder(order);
/*  918 */     DeliverMinuteSyncJob ss = 
/*  919 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  920 */     ss.startSynOne(order);
/*  921 */     return new ActionForward("viewPortalShipOrderTwo.do?id=" + 
/*  922 */         Integer.parseInt(ids), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePirntPortalShipOrderByJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  928 */     JSONObject object = new JSONObject();
/*  929 */     boolean bol = false;
/*  930 */     String ids = request.getParameter("id");
/*  931 */     PortalShipOrderManager manager = 
/*  932 */       ServiceLocator.getPortalShipOrderManager(request);
/*  933 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  934 */         Integer.valueOf(Integer.parseInt(ids)));
/*  935 */     if (order.getPrintDate() == null) {
/*  936 */       order.setPrintDate(new Date());
/*      */     }
/*  938 */     order.setIsPrint(YesNo.YES);
/*  939 */     manager.updatePortalShipOrder(order);
/*  940 */     DeliverMinuteSyncJob ss = 
/*  941 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  942 */     ss.startSynOne(order);
/*  943 */     bol = true;
/*  944 */     PrintWriter print = response.getWriter();
/*  945 */     object.put("str", Boolean.valueOf(bol));
/*  946 */     print.print(object);
/*  947 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePirntPortalShipOrderByNpoPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  955 */     JSONObject object = new JSONObject();
/*  956 */     boolean bol = false;
/*  957 */     String ids = request.getParameter("id");
/*  958 */     PortalShipOrderManager manager = 
/*  959 */       ServiceLocator.getPortalShipOrderManager(request);
/*  960 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  961 */         Integer.valueOf(Integer.parseInt(ids)));
/*  962 */     if (order.getPrintDate() == null) {
/*  963 */       order.setPrintDate(new Date());
/*      */     }
/*  965 */     order.setIsPrint(YesNo.YES);
/*  966 */     manager.updatePortalShipOrder(order);
/*  967 */     DeliverMinuteSyncJob ss = 
/*  968 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  969 */     ss.startSynOne(order);
/*  970 */     bol = true;
/*  971 */     PrintWriter print = response.getWriter();
/*  972 */     object.put("str", Boolean.valueOf(bol));
/*  973 */     print.print(object);
/*  974 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1012 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1018 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1019 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1020 */     PortalShipOrderManager portalShipOrderManager = 
/* 1021 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1022 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 1024 */     PortalShipOrder portalShipOrder = null;
/* 1025 */     SupplierManager supplierManager = 
/* 1026 */       ServiceLocator.getSupplierManager(request);
/* 1027 */     String supplierCode = request.getParameter("supplierCode");
/*      */     
/* 1029 */     Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
/* 1030 */     if (supplier != null) {
/*      */       try {
/* 1032 */         String[] poipItemIdsList = request
/* 1033 */           .getParameterValues("poipItemIds");
/* 1034 */         String[] deliveryNumberList = request
/* 1035 */           .getParameterValues("deliveryNumbers");
/* 1036 */         String createType = request.getParameter("createType");
/* 1037 */         for (int i = 0; i < poipItemIdsList.length; i++) {
/* 1038 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */             
/* 1042 */             if (portalShipOrder == null) {
/* 1043 */               portalShipOrder = new PortalShipOrder();
/* 1044 */               Site site = getCurrentUser(request)
/* 1045 */                 .getPrimarySite();
/* 1046 */               User requestor = getCurrentUser(request);
/* 1047 */               SimpleDateFormat sdf = new SimpleDateFormat(
/* 1048 */                   "yyyy/MM/dd");
/* 1049 */               String arrivalDate = queryForm.getReceivingDate();
/* 1050 */               Date arrDate = null;
/* 1051 */               if (arrivalDate != null && 
/* 1052 */                 arrivalDate.trim().length() != 0) {
/* 1053 */                 arrDate = sdf.parse(arrivalDate);
/*      */               }
/* 1055 */               portalShipOrder.setType(Integer.valueOf(1));
/* 1056 */               portalShipOrder.setCreateType(createType);
/* 1057 */               portalShipOrder.setSupplier(supplier);
/*      */               
/* 1059 */               portalShipOrderManager.insertPortalShipOrderSupplier(
/* 1060 */                   portalShipOrder, site, supplier, arrDate);
/*      */             } 
/* 1062 */             String poipItem = poipItemIdsList[i];
/* 1063 */             BigDecimal deliveryNumber = new BigDecimal(
/* 1064 */                 deliveryNumberList[i]);
/*      */             
/* 1066 */             PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 1067 */               .getPurchaseOrderInspectionPendingItem(
/* 1068 */                 Integer.valueOf(Integer.parseInt(poipItem)));
/*      */             
/* 1070 */             BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 1071 */               .getQtyOpen();
/* 1072 */             purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 1073 */                 .subtract(deliveryNumber));
/* 1074 */             poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */             
/* 1077 */             Boolean isClose = poipm
/* 1078 */               .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 1079 */                 .getPoip_number());
/* 1080 */             if (isClose.booleanValue()) {
/* 1081 */               purchaseOrderInspectionPendingItem1.getPoip_number()
/* 1082 */                 .setStatus(PurchaseOrderStatus.WAIT);
/* 1083 */               poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 1084 */                   .getPoip_number());
/*      */             } 
/*      */             
/* 1087 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */             
/* 1089 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 1090 */             portalShipOrderItem
/* 1091 */               .setPart(purchaseOrderInspectionPendingItem1
/* 1092 */                 .getItemNumber());
/* 1093 */             portalShipOrderItem
/* 1094 */               .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 1095 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 1096 */             portalShipOrderItem
/* 1097 */               .setQty_std(purchaseOrderInspectionPendingItem1
/* 1098 */                 .getQty_std());
/* 1099 */             portalShipOrderManager
/* 1100 */               .insertPortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1111 */         PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 1112 */           .getPurchaseOrderInspectionPendingItem(
/* 1113 */             Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 1114 */         if (!"T".equals(purchaseOrderInspectionPendingItem
/* 1115 */             .getVd_promo())) {
/* 1116 */           portalShipOrderManager
/* 1117 */             .createPortalShipOrderIP(portalShipOrder);
/*      */         }
/* 1119 */       } catch (Exception e) {
/* 1120 */         e.fillInStackTrace();
/*      */       } 
/* 1122 */       return new ActionForward("/viewPortalShipOrderTwo.do?id=" + 
/* 1123 */           portalShipOrder.getId(), true);
/*      */     } 
/* 1125 */     return new ActionForward("/listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1132 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 1133 */     PurchaseOrderInspectionPendingManager fm = 
/* 1134 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1135 */     String idString = queryForm.getSelectPoipItemId();
/* 1136 */     String supplierCode = request.getParameter("supplierCode");
/* 1137 */     String[] ids = idString.split(";");
/* 1138 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 1140 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 1141 */         Integer.valueOf(0));
/* 1142 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 1143 */     String poNumber = queryForm.getPoip_number();
/* 1144 */     if (poNumber != null && !poNumber.equals("")) {
/* 1145 */       conditions.put(
/* 1146 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 1147 */           poNumber);
/*      */     }
/*      */     
/* 1150 */     String itemNumber = queryForm.getItemNumber();
/* 1151 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 1152 */       conditions.put(
/* 1153 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 1154 */           itemNumber);
/*      */     }
/*      */     
/* 1157 */     String endPoDate = queryForm.getDueDate();
/* 1158 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 1159 */       conditions.put(
/* 1160 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 1161 */           endPoDate);
/*      */     }
/* 1163 */     String status = queryForm.getStatus();
/* 1164 */     if (status != null && !status.equals("")) {
/* 1165 */       conditions.put(
/* 1166 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 1167 */           status);
/*      */     } else {
/* 1169 */       conditions.put(
/* 1170 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 1172 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 1173 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/* 1174 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 1175 */       supplier != null)
/*      */     {
/* 1177 */       conditions
/* 1178 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 1179 */           supplier.getId());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1195 */     if (queryForm.isFirstInit()) {
/* 1196 */       queryForm
/* 1197 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 1199 */       queryForm.init();
/*      */     } 
/* 1201 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 1202 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 1203 */         queryForm.getPageNoAsInt(), 
/* 1204 */         queryForm.getPageSizeAsInt(), 
/* 1205 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 1206 */         queryForm.isDescend());
/* 1207 */     if (ids.length > 0) {
/* 1208 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 1209 */         Integer itemId = pendingItem.getId();
/* 1210 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 1212 */         for (int i = 0; i < ids.length; i++) {
/* 1213 */           if (!ids[i].equals("") && 
/* 1214 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 1215 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1220 */         if (isClose.booleanValue()) {
/* 1221 */           pendingItem.setChecked("checked");
/*      */         }
/*      */       } 
/*      */     }
/* 1225 */     request.setAttribute("X_RESULTLIST", result);
/* 1226 */     request.setAttribute("X_YESNOLIST", 
/* 1227 */         PersistentEnum.getEnumList(YesNo.class));
/* 1228 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 1229 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 1230 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     try {
/* 1238 */       PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 1239 */       PortalShipOrderManager fm = 
/* 1240 */         ServiceLocator.getPortalShipOrderManager(request);
/* 1241 */       if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1242 */         queryForm.setOrder("createDate");
/* 1243 */         queryForm.setDescend(true);
/*      */       } 
/* 1245 */       queryForm.setOrder("createDate");
/*      */       
/* 1247 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1248 */       getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */       
/* 1251 */       getConditionsFrom(queryForm, conditions);
/*      */       
/* 1253 */       conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, 
/* 1254 */           "NJIT_NPO");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1268 */       String exportType = queryForm.getExportType();
/* 1269 */       if (exportType != null && exportType.length() > 0) {
/* 1270 */         List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 1271 */             false);
/* 1272 */         int index = SessionTempFile.createNewTempFile(request);
/* 1273 */         String fileName = "PortalShipOrder";
/* 1274 */         String suffix = ExportUtil.export(
/* 1275 */             exportType, 
/* 1276 */             data, 
/* 1277 */             request, 
/* 1278 */             new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1279 */                 request)), new Exportable()
/*      */             {
/*      */               public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */               {
/* 1283 */                 MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 1284 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1285 */                       "SO.No1"));
/* 1286 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1287 */                       "supplier1"));
/* 1288 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1289 */                       "supplier.code1"));
/* 1290 */                 row.add("创建时间");
/* 1291 */                 row.add("发货时间");
/* 1292 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1293 */                       "salesOrderItem.isprintlabels"));
/* 1294 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1295 */                       "salesOrderItem.issync"));
/* 1296 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1297 */                       "portalShipOrder.status"));
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*      */               public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1303 */                 row.add(BeanUtils.getProperty(data, "code"));
/* 1304 */                 row.add(BeanUtils.getProperty(data, 
/* 1305 */                       "site.supplier.name"));
/* 1306 */                 row.add(BeanUtils.getProperty(data, 
/* 1307 */                       "site.supplier.code"));
/*      */ 
/*      */                 
/* 1310 */                 row.add(BeanUtils.getProperty(data, 
/* 1311 */                       "createDate"));
/* 1312 */                 row.add(BeanUtils.getProperty(data, 
/* 1313 */                       "arrivalDate"));
/*      */                 
/* 1315 */                 PortalShipOrder order = (PortalShipOrder)data;
/* 1316 */                 if (order.getIsPrint().equals(YesNo.YES)) {
/* 1317 */                   row.add("已打印");
/* 1318 */                 } else if (order.getIsPrint().equals(YesNo.NO)) {
/* 1319 */                   row.add("未打印");
/*      */                 } else {
/* 1321 */                   row.add("未打印");
/*      */                 } 
/* 1323 */                 if (order.getIsSync() != null) {
/* 1324 */                   if (order.getIsSync().equals("0")) {
/* 1325 */                     row.add("未同步");
/* 1326 */                   } else if (order.getIsSync().equals("1")) {
/* 1327 */                     row.add("已同步");
/* 1328 */                   } else if (order.getIsSync().equals("")) {
/* 1329 */                     row.add("未同步");
/*      */                   } 
/*      */                 } else {
/* 1332 */                   row.add("未同步");
/*      */                 } 
/* 1334 */                 String locale = PortalShipOrderAction.this.getCurrentUser(request)
/* 1335 */                   .getLocale();
/* 1336 */                 if ("en".equals(locale)) {
/* 1337 */                   row.add(BeanUtils.getProperty(data, 
/* 1338 */                         "status.engShortDescription"));
/*      */                 } else {
/* 1340 */                   row.add(BeanUtils.getProperty(data, 
/* 1341 */                         "status.chnShortDescription"));
/*      */                 } 
/*      */               }
/*      */             });
/*      */         
/* 1346 */         return new ActionForward("download/" + index + "/" + 
/* 1347 */             URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 1348 */             true);
/*      */       } 
/* 1350 */       if (queryForm.isFirstInit()) {
/* 1351 */         queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */       } else {
/* 1353 */         queryForm.init();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1359 */       List result = fm.getPortalShipOrderList(conditions, 
/* 1360 */           queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1361 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 1362 */       request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1363 */           PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1364 */       request.setAttribute("x_statusList", 
/* 1365 */           PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 1366 */       request.setAttribute("X_RESULTLIST", result);
/* 1367 */       request.setAttribute("x_selType", Integer.valueOf(169));
/* 1368 */     } catch (Exception e) {
/*      */       
/* 1370 */       e.printStackTrace();
/*      */     } 
/* 1372 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderWrongSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     try {
/* 1380 */       PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 1381 */       PortalShipOrderManager fm = 
/* 1382 */         ServiceLocator.getPortalShipOrderManager(request);
/* 1383 */       if (queryForm.getOrder() == "") {
/* 1384 */         queryForm.setOrder("createDate");
/* 1385 */         queryForm.setDescend(true);
/*      */       } 
/*      */       
/* 1388 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1389 */       getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */       
/* 1392 */       getConditionsFrom(queryForm, conditions);
/*      */       
/* 1394 */       conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, 
/* 1395 */           "NJIT_NPO");
/* 1396 */       User user = getCurrentUser(request);
/* 1397 */       SupplierManager supplierManager = 
/* 1398 */         ServiceLocator.getSupplierManager(request);
/* 1399 */       Supplier supplier = supplierManager.getSupplierByCode(user
/* 1400 */           .getLoginName());
/* 1401 */       if (supplier != null) {
/* 1402 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, 
/* 1403 */             supplier.getId());
/*      */       } else {
/* 1405 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
/*      */       } 
/*      */       
/* 1408 */       String exportType = queryForm.getExportType();
/* 1409 */       if (exportType != null && exportType.length() > 0) {
/* 1410 */         List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 1411 */             false);
/* 1412 */         int index = SessionTempFile.createNewTempFile(request);
/* 1413 */         String fileName = "PortalShipOrder";
/* 1414 */         String suffix = ExportUtil.export(
/* 1415 */             exportType, 
/* 1416 */             data, 
/* 1417 */             request, 
/* 1418 */             new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1419 */                 request)), new Exportable()
/*      */             {
/*      */               public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */               {
/* 1423 */                 MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 1424 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1425 */                       "SO.No1"));
/* 1426 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1427 */                       "supplier1"));
/* 1428 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1429 */                       "supplier.code1"));
/* 1430 */                 row.add("创建时间");
/* 1431 */                 row.add("发货时间");
/* 1432 */                 row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 1433 */                       "portalShipOrder.status"));
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*      */               public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1439 */                 row.add(BeanUtils.getProperty(data, "code"));
/* 1440 */                 row.add(BeanUtils.getProperty(data, 
/* 1441 */                       "site.supplier.name"));
/* 1442 */                 row.add(BeanUtils.getProperty(data, 
/* 1443 */                       "site.supplier.code"));
/* 1444 */                 row.add("");
/* 1445 */                 row.add(BeanUtils.getProperty(data, 
/* 1446 */                       "createDate"));
/* 1447 */                 String locale = PortalShipOrderAction.this.getCurrentUser(request)
/* 1448 */                   .getLocale();
/* 1449 */                 if ("en".equals(locale)) {
/* 1450 */                   row.add(BeanUtils.getProperty(data, 
/* 1451 */                         "status.engShortDescription"));
/*      */                 } else {
/* 1453 */                   row.add(BeanUtils.getProperty(data, 
/* 1454 */                         "status.chnShortDescription"));
/*      */                 } 
/*      */               }
/*      */             });
/* 1458 */         return new ActionForward("download/" + index + "/" + 
/* 1459 */             URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 1460 */             true);
/*      */       } 
/* 1462 */       if (queryForm.isFirstInit()) {
/* 1463 */         queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */       } else {
/* 1465 */         queryForm.init();
/*      */       } 
/* 1467 */       List result = fm.getPortalShipOrderList(conditions, 
/* 1468 */           queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1469 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/* 1470 */           queryForm.isDescend());
/* 1471 */       request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1472 */           PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1473 */       request.setAttribute("x_statusList", 
/* 1474 */           PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 1475 */       request.setAttribute("X_RESULTLIST", result);
/* 1476 */       request.setAttribute("x_selType", Integer.valueOf(177));
/* 1477 */     } catch (Exception e) {
/*      */       
/* 1479 */       e.printStackTrace();
/*      */     } 
/* 1481 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1487 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/*      */     
/* 1489 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1490 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1491 */     PortalShipOrderManager portalShipOrderManager = 
/* 1492 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1493 */     String shipOrderId = request.getParameter("id");
/* 1494 */     String GrantedSite = request.getParameter("GrantedSite");
/* 1495 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1496 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1497 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1498 */       .getPortalShipOrderItemListByOrderId(
/* 1499 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1500 */     User user = getCurrentUser(request);
/* 1501 */     String supplierCode = user.getLoginName();
/* 1502 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 1503 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/* 1504 */     request.setAttribute("supplier", supplier);
/* 1505 */     List<Box> resultList = portalShipOrderManager
/* 1506 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/* 1508 */     int totalCount = resultList.size();
/*      */     
/* 1510 */     int pageCount = 0;
/*      */     
/* 1512 */     int endNum = formBean.getPageSizeAsInt();
/* 1513 */     if (endNum == -1) {
/* 1514 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 1517 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 1519 */     if (totalCount % endNum > 0) {
/*      */       
/* 1521 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 1524 */       pageCount = totalCount / endNum;
/*      */     } 
/* 1526 */     if (totalCount > 0) {
/* 1527 */       if (startNum <= pageCount) {
/* 1528 */         if (startNum == 1) {
/*      */           
/* 1530 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 1534 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 1538 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 1540 */           int toIndex = startNum * endNum;
/*      */           
/* 1542 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 1543 */             toIndex = startNum * endNum;
/*      */           } else {
/* 1545 */             toIndex = (startNum - 1) * endNum + 
/* 1546 */               totalCount % endNum;
/*      */           } 
/* 1548 */           if (totalCount >= toIndex) {
/* 1549 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 1553 */         resultList = null;
/*      */       } 
/*      */     }
/* 1556 */     String exportType = formBean.getExportType();
/* 1557 */     if (exportType != null && exportType.length() > 0) {
/* 1558 */       int index = SessionTempFile.createNewTempFile(request);
/* 1559 */       String fileName = "PortalShipOrderBoxItem";
/* 1560 */       String suffix = ExportUtil.export(
/* 1561 */           exportType, 
/* 1562 */           resultList, 
/* 1563 */           request, 
/* 1564 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1565 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 1568 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 1569 */               row.add("箱单号");
/* 1570 */               row.add("供应商批次");
/* 1571 */               row.add("组件/物料编码");
/* 1572 */               row.add("品名");
/* 1573 */               row.add("包装箱容量");
/* 1574 */               row.add("批次数量");
/* 1575 */               row.add("发货数量");
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1580 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 1581 */               row.add(
/* 1582 */                   BeanUtils.getProperty(data, "supplierBatch"));
/* 1583 */               row.add(BeanUtils.getProperty(data, 
/* 1584 */                     "psoItem.poipItem.itemNumber.id"));
/* 1585 */               row.add(BeanUtils.getProperty(data, 
/* 1586 */                     "psoItem.poipItem.itemNumber.dpiNo"));
/* 1587 */               row.add(BeanUtils.getProperty(data, 
/* 1588 */                     "psoItem.qty_std"));
/* 1589 */               row.add("");
/* 1590 */               row.add(BeanUtils.getProperty(data, "number"));
/* 1591 */               row.add(BeanUtils.getProperty(data, 
/* 1592 */                     "isPrint.chnShortDescription"));
/*      */             }
/*      */           });
/* 1595 */       return new ActionForward("download/" + index + "/" + 
/* 1596 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1598 */     if (formBean.isFirstInit()) {
/* 1599 */       formBean.init(totalCount);
/*      */     } else {
/* 1601 */       formBean.init();
/*      */     } 
/*      */     
/* 1604 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1605 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1606 */         portalShipOrderItemList);
/* 1607 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/* 1608 */     int size = resultList.size();
/* 1609 */     for (Box poIpiBox : resultList) {
/* 1610 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 1611 */         poIpiBox.getSupplierBatch() != null) {
/* 1612 */         size--;
/* 1613 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 1616 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/* 1617 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/* 1619 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1620 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1621 */     request.setAttribute("X_GRANTEDSITE", GrantedSite);
/* 1622 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPortalShipOrderViewWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1628 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 1629 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1630 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1631 */     PortalShipOrderManager portalShipOrderManager = 
/* 1632 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1633 */     String shipOrderId = request.getParameter("id");
/* 1634 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1635 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1636 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1637 */       .getPortalShipOrderItemListByOrderId(
/* 1638 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1639 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 1640 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1641 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1642 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1643 */         portalShipOrderItemList);
/* 1644 */     request.getSession().setAttribute("path", request.getContextPath());
/* 1645 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPortalShipOrderViewProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1651 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 1652 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1653 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1654 */     PortalShipOrderManager portalShipOrderManager = 
/* 1655 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1656 */     String shipOrderId = request.getParameter("id");
/* 1657 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1658 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1659 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1660 */       .getPortalShipOrderItemListByOrderId(
/* 1661 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1662 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 1663 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1664 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1665 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1666 */         portalShipOrderItemList);
/* 1667 */     request.getSession().setAttribute("path", request.getContextPath());
/* 1668 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotListWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1674 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 1675 */     PortalShipOrderManager portalShipOrderManager = 
/* 1676 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1677 */     String id = request.getParameter("id");
/* 1678 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1679 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 1680 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1681 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1682 */     List<Box> resultList = portalShipOrderManager
/* 1683 */       .getBoxByShipOrderId(portalShipOrder.getId());
/* 1684 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/* 1686 */     String str = request.getParameter("supplierBatchStr");
/* 1687 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/* 1689 */     if ((str != null || poipBoxIds != null) && (
/* 1690 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/* 1691 */       String[] supplierBatchList = str.split(",");
/* 1692 */       String[] poipBoxIdList = poipBoxIds.split(",");
/* 1693 */       if (supplierBatchList.length > 0) {
/* 1694 */         for (int i = 0; i < supplierBatchList.length; i++) {
/* 1695 */           if (poipBoxIdList[i].length() > 0) {
/* 1696 */             Integer poipBoxId = 
/* 1697 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/* 1698 */             String supplierBatch = supplierBatchList[i];
/* 1699 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/* 1700 */             if (supplierBatch != null || supplierBatch != "") {
/* 1701 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/* 1703 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/* 1705 */               poIpiBox.setSupplierBatch("");
/* 1706 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1716 */     int totalCount = resultList.size();
/*      */     
/* 1718 */     int pageCount = 0;
/*      */     
/* 1720 */     int endNum = formBean.getPageSizeAsInt();
/* 1721 */     if (endNum == -1) {
/* 1722 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 1725 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 1727 */     if (totalCount % endNum > 0) {
/*      */       
/* 1729 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 1732 */       pageCount = totalCount / endNum;
/*      */     } 
/* 1734 */     if (totalCount > 0) {
/* 1735 */       if (startNum <= pageCount) {
/* 1736 */         if (startNum == 1) {
/*      */           
/* 1738 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 1742 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 1746 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 1748 */           int toIndex = startNum * endNum;
/*      */           
/* 1750 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 1751 */             toIndex = startNum * endNum;
/*      */           } else {
/* 1753 */             toIndex = (startNum - 1) * endNum + 
/* 1754 */               totalCount % endNum;
/*      */           } 
/* 1756 */           if (totalCount >= toIndex) {
/* 1757 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 1761 */         resultList = null;
/*      */       } 
/*      */     }
/* 1764 */     if (formBean.isFirstInit()) {
/* 1765 */       formBean.init(totalCount);
/*      */     } else {
/* 1767 */       formBean.init();
/*      */     } 
/*      */     
/* 1770 */     request.setAttribute("x_supplierBatchStr", str);
/* 1771 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/* 1772 */     request.setAttribute("x_id", id);
/* 1773 */     request.setAttribute("x_boxList", resultList);
/* 1774 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1775 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1781 */     PortalShipOrderManager portalShipOrderManager = 
/* 1782 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1783 */     PurchaseOrderInspectionPendingManager poipManager = 
/* 1784 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1785 */     PurchaseOrderManager poManager = 
/* 1786 */       ServiceLocator.getPurchaseOrderManager(request);
/* 1787 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 1788 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/* 1790 */     List<Box> list = new ArrayList();
/* 1791 */     List<Box> poipBoxList = null;
/*      */     try {
/* 1793 */       if (!portalShipOrder.getStatus().equals(
/* 1794 */           PortalShipOrderStatus.RECEIVE)) {
/* 1795 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/* 1796 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/* 1798 */         portalShipOrderItemlist = portalShipOrderManager
/* 1799 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/* 1800 */             .getId());
/* 1801 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/* 1804 */           poipBoxList = portalShipOrderManager
/* 1805 */             .getBoxList(portalShipOrderItem.getId());
/* 1806 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/* 1809 */     } catch (Exception e) {
/* 1810 */       e.fillInStackTrace();
/* 1811 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1814 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1815 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1816 */         portalShipOrderItemlist);
/* 1817 */     request.setAttribute("x_portalShopOrderBoxList", list);
/* 1818 */     int size = poipBoxList.size();
/* 1819 */     for (Box poIpiBox : poipBoxList) {
/* 1820 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 1821 */         poIpiBox.getSupplierBatch() != null) {
/* 1822 */         size--;
/* 1823 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 1826 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/* 1829 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1835 */     PortalShipOrderManager portalShipOrderManager = 
/* 1836 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1837 */     String id = request.getParameter("id");
/* 1838 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 1839 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/* 1842 */     return new ActionForward("viewPortalShipOrderTwoWrong.do?id=" + 
/* 1843 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReportWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1849 */     PurchaseOrderInspectionPendingManager poip = 
/* 1850 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1851 */     String string = request.getParameter("str");
/* 1852 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */     
/* 1854 */     String[] strings = string.split(",");
/* 1855 */     List<Box> boxlist = new ArrayList<Box>();
/* 1856 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 1857 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 1858 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/* 1860 */       String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
/* 1861 */       if (beatchNO != null && !"".equals(beatchNO)) {
/* 1862 */         String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
/* 1863 */         box.setHuCodeOrderNumber(baetchNo);
/*      */       } 
/* 1865 */       boxlist.add(box); b++; }
/*      */     
/* 1867 */     request.setAttribute("x_item", item);
/* 1868 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 1869 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/* 1871 */     request.setAttribute("path", request.getContextPath());
/* 1872 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printQrCodeMin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1886 */     PurchaseOrderInspectionPendingManager poip = 
/* 1887 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1888 */     String string = request.getParameter("str");
/* 1889 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/* 1890 */     String[] strings = string.split(",");
/* 1891 */     List<Box> boxlist = new ArrayList<Box>();
/* 1892 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 1893 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 1894 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/* 1896 */       String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
/* 1897 */       if (beatchNO != null && !"".equals(beatchNO)) {
/* 1898 */         String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
/* 1899 */         box.setHuCodeOrderNumber(baetchNo);
/*      */       } 
/* 1901 */       boxlist.add(box); b++; }
/*      */     
/* 1903 */     request.setAttribute("x_item", item);
/* 1904 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 1905 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1906 */     request.setAttribute("path", request.getContextPath());
/* 1907 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItemsWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1913 */     PurchaseOrderInspectionPendingManager poip = 
/* 1914 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1915 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/* 1916 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 1917 */     JSONObject object = new JSONObject();
/* 1918 */     boolean bol = false;
/* 1919 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1920 */     String ids = request.getParameter("ids");
/* 1921 */     String[] idr = ids.split(";");
/* 1922 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/* 1923 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 1924 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 1925 */       box.setIsPrint(YesNo.YES);
/* 1926 */       manager.updateBox(box);
/* 1927 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/* 1930 */     bol = true;
/* 1931 */     PrintWriter print = response.getWriter();
/* 1932 */     object.put("str", Boolean.valueOf(bol));
/* 1933 */     print.print(object);
/* 1934 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObjectWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1943 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
/* 1944 */     PurchaseOrderInspectionPendingQueryForm pendingQueryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/* 1945 */     pendingQueryForm.setReceivingDate(simpleDateFormat.format(new Date()));
/*      */     
/* 1947 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1953 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1954 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1955 */     PortalShipOrderManager portalShipOrderManager = 
/* 1956 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1957 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 1959 */     PortalShipOrder portalShipOrder = null;
/*      */     
/*      */     try {
/* 1962 */       String[] poipItemIdsList = request
/* 1963 */         .getParameterValues("poipItemIds");
/* 1964 */       String[] deliveryNumberList = request
/* 1965 */         .getParameterValues("deliveryNumbers");
/* 1966 */       for (int i = 0; i < poipItemIdsList.length; i++) {
/* 1967 */         if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */           
/* 1971 */           if (portalShipOrder == null) {
/* 1972 */             portalShipOrder = new PortalShipOrder();
/* 1973 */             Site site = getCurrentUser(request).getPrimarySite();
/* 1974 */             User requestor = getCurrentUser(request);
/* 1975 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 1976 */             String arrivalDate = queryForm.getReceivingDate();
/* 1977 */             Date arrDate = null;
/* 1978 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 1979 */               arrDate = sdf.parse(arrivalDate);
/*      */             }
/* 1981 */             portalShipOrder.setType(Integer.valueOf(2));
/* 1982 */             portalShipOrderManager.insertPortalShipOrder(
/* 1983 */                 portalShipOrder, site, requestor, arrDate);
/*      */           } 
/*      */           
/* 1986 */           String poipItem = poipItemIdsList[i];
/* 1987 */           BigDecimal deliveryNumber = new BigDecimal(
/* 1988 */               deliveryNumberList[i]);
/*      */           
/* 1990 */           PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 1991 */             .getPurchaseOrderInspectionPendingItem(
/* 1992 */               Integer.valueOf(Integer.parseInt(poipItem)));
/*      */           
/* 1994 */           BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 1995 */             .getQtyOpen();
/* 1996 */           purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 1997 */               .subtract(deliveryNumber));
/* 1998 */           poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */           
/* 2001 */           Boolean isClose = poipm
/* 2002 */             .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 2003 */               .getPoip_number());
/* 2004 */           if (isClose.booleanValue()) {
/* 2005 */             purchaseOrderInspectionPendingItem1.getPoip_number()
/* 2006 */               .setStatus(PurchaseOrderStatus.WAIT);
/* 2007 */             poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 2008 */                 .getPoip_number());
/*      */           } 
/*      */           
/* 2011 */           PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */           
/* 2013 */           portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2014 */           portalShipOrderItem
/* 2015 */             .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 2016 */           portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2017 */           portalShipOrderItem
/* 2018 */             .setQty_std(purchaseOrderInspectionPendingItem1
/* 2019 */               .getQty_std());
/* 2020 */           portalShipOrderManager
/* 2021 */             .insertPortalShipOrderItem(portalShipOrderItem);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2032 */       PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 2033 */         .getPurchaseOrderInspectionPendingItem(
/* 2034 */           Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 2035 */       if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
/* 2036 */         portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */       }
/* 2038 */     } catch (Exception e) {
/* 2039 */       e.fillInStackTrace();
/*      */     } 
/* 2041 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + 
/* 2042 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2049 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2050 */     PurchaseOrderInspectionPendingManager fm = 
/* 2051 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2052 */     String idString = queryForm.getSelectPoipItemId();
/* 2053 */     String[] ids = idString.split(";");
/* 2054 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 2056 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 2057 */         Integer.valueOf(0));
/* 2058 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2059 */     String poNumber = queryForm.getPoip_number();
/* 2060 */     if (poNumber != null && !poNumber.equals("")) {
/* 2061 */       conditions.put(
/* 2062 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 2063 */           poNumber);
/*      */     }
/*      */     
/* 2066 */     String itemNumber = queryForm.getItemNumber();
/* 2067 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 2068 */       conditions.put(
/* 2069 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 2070 */           itemNumber);
/*      */     }
/*      */     
/* 2073 */     String endPoDate = queryForm.getDueDate();
/* 2074 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 2075 */       conditions.put(
/* 2076 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 2077 */           endPoDate);
/*      */     }
/* 2079 */     String status = queryForm.getStatus();
/* 2080 */     if (status != null && !status.equals("")) {
/* 2081 */       conditions.put(
/* 2082 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 2083 */           status);
/*      */     } else {
/* 2085 */       conditions.put(
/* 2086 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 2088 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 2089 */     User user = getCurrentUser(request);
/* 2090 */     Supplier supplier = manager.getSupplierByCode(user.getLoginName());
/* 2091 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 2092 */       supplier != null) {
/*      */       
/* 2094 */       conditions
/* 2095 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2096 */           supplier.getId());
/*      */     } else {
/* 2098 */       conditions
/* 2099 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2100 */           Integer.valueOf(0));
/*      */     } 
/* 2102 */     conditions.put(
/* 2103 */         PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(3));
/* 2104 */     if (queryForm.isFirstInit()) {
/* 2105 */       queryForm
/* 2106 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 2108 */       queryForm.init();
/*      */     } 
/* 2110 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2111 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 2112 */         queryForm.getPageNoAsInt(), 
/* 2113 */         queryForm.getPageSizeAsInt(), 
/* 2114 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 2115 */         queryForm.isDescend());
/* 2116 */     if (ids.length > 0) {
/* 2117 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2118 */         Integer itemId = pendingItem.getId();
/* 2119 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 2121 */         for (int i = 0; i < ids.length; i++) {
/* 2122 */           if (!ids[i].equals("") && 
/* 2123 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 2124 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2129 */         if (isClose.booleanValue()) {
/* 2130 */           pendingItem.setChecked("checked");
/*      */         }
/*      */       } 
/*      */     }
/* 2134 */     request.setAttribute("X_RESULTLIST", result);
/* 2135 */     request.setAttribute("X_YESNOLIST", 
/* 2136 */         PersistentEnum.getEnumList(YesNo.class));
/* 2137 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 2138 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 2139 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2146 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 2147 */     PortalShipOrderManager fm = 
/* 2148 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2149 */     if (queryForm.getOrder() == "") {
/* 2150 */       queryForm.setOrder("createDate");
/*      */     }
/*      */ 
/*      */     
/* 2154 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 2155 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2156 */     getConditionsFrom(queryForm, conditions);
/*      */     
/* 2158 */     conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "JIT");
/* 2159 */     User user = getCurrentUser(request);
/* 2160 */     SupplierManager supplierManager = 
/* 2161 */       ServiceLocator.getSupplierManager(request);
/* 2162 */     Supplier supplier = supplierManager.getSupplierByCode(user
/* 2163 */         .getLoginName());
/* 2164 */     if (!hasGlobalPower(request)) {
/* 2165 */       conditions.put(PortalShipOrderQueryCondition.SITE_EQ, 
/* 2166 */           getCurrentUser(request).getPrimarySite().getId());
/* 2167 */       if (supplier != null) {
/* 2168 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, 
/* 2169 */             supplier.getId());
/*      */       } else {
/* 2171 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
/*      */       } 
/*      */     } 
/*      */     
/* 2175 */     String exportType = queryForm.getExportType();
/* 2176 */     if (exportType != null && exportType.length() > 0) {
/* 2177 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 2178 */           false);
/* 2179 */       int index = SessionTempFile.createNewTempFile(request);
/* 2180 */       String fileName = "PortalShipOrder";
/* 2181 */       String suffix = ExportUtil.export(
/* 2182 */           exportType, 
/* 2183 */           data, 
/* 2184 */           request, 
/* 2185 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2186 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 2189 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 2190 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 2191 */                     "SO.No1"));
/* 2192 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 2193 */                     "supplier1"));
/* 2194 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 2195 */                     "supplier.code1"));
/* 2196 */               row.add("创建时间");
/* 2197 */               row.add("发货时间");
/* 2198 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 2199 */                     "portalShipOrder.status"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2204 */               row.add(BeanUtils.getProperty(data, "code"));
/* 2205 */               row.add(BeanUtils.getProperty(data, 
/* 2206 */                     "site.supplier.name"));
/* 2207 */               row.add(BeanUtils.getProperty(data, 
/* 2208 */                     "site.supplier.code"));
/* 2209 */               row.add("");
/* 2210 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 2211 */               String locale = PortalShipOrderAction.this.getCurrentUser(request).getLocale();
/* 2212 */               if ("en".equals(locale)) {
/* 2213 */                 row.add(BeanUtils.getProperty(data, 
/* 2214 */                       "status.engShortDescription"));
/*      */               } else {
/* 2216 */                 row.add(BeanUtils.getProperty(data, 
/* 2217 */                       "status.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/* 2221 */       return new ActionForward("download/" + index + "/" + 
/* 2222 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 2224 */     if (queryForm.isFirstInit()) {
/* 2225 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/* 2227 */       queryForm.init();
/*      */     } 
/* 2229 */     List result = fm.getPortalShipOrderList(conditions, 
/* 2230 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2231 */         PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/* 2232 */         queryForm.isDescend());
/* 2233 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 2234 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 2235 */     request.setAttribute("x_statusList", 
/* 2236 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 2237 */     request.setAttribute("X_RESULTLIST", result);
/* 2238 */     request.setAttribute("x_selType", Integer.valueOf(170));
/* 2239 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2245 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 2246 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2247 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2248 */     PortalShipOrderManager portalShipOrderManager = 
/* 2249 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2250 */     String shipOrderId = request.getParameter("id");
/* 2251 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 2252 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 2253 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 2254 */       .getPortalShipOrderItemListByOrderId(
/* 2255 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 2256 */     User user = getCurrentUser(request);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2261 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 2262 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 2263 */     List<Box> resultList = portalShipOrderManager
/* 2264 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/* 2266 */     int totalCount = resultList.size();
/*      */     
/* 2268 */     int pageCount = 0;
/*      */     
/* 2270 */     int endNum = formBean.getPageSizeAsInt();
/* 2271 */     if (endNum == -1) {
/* 2272 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 2275 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 2277 */     if (totalCount % endNum > 0) {
/*      */       
/* 2279 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 2282 */       pageCount = totalCount / endNum;
/*      */     } 
/* 2284 */     if (totalCount > 0) {
/* 2285 */       if (startNum <= pageCount) {
/* 2286 */         if (startNum == 1) {
/*      */           
/* 2288 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 2292 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 2296 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 2298 */           int toIndex = startNum * endNum;
/*      */           
/* 2300 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 2301 */             toIndex = startNum * endNum;
/*      */           } else {
/* 2303 */             toIndex = (startNum - 1) * endNum + 
/* 2304 */               totalCount % endNum;
/*      */           } 
/* 2306 */           if (totalCount >= toIndex) {
/* 2307 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 2311 */         resultList = null;
/*      */       } 
/*      */     }
/* 2314 */     String exportType = formBean.getExportType();
/* 2315 */     if (exportType != null && exportType.length() > 0) {
/* 2316 */       int index = SessionTempFile.createNewTempFile(request);
/* 2317 */       String fileName = "PortalShipOrderBoxItem";
/* 2318 */       String suffix = ExportUtil.export(
/* 2319 */           exportType, 
/* 2320 */           resultList, 
/* 2321 */           request, 
/* 2322 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2323 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 2326 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 2327 */               row.add("箱单号");
/* 2328 */               row.add("供应商批次");
/* 2329 */               row.add("组件/物料编码");
/* 2330 */               row.add("品名");
/* 2331 */               row.add("包装箱容量");
/* 2332 */               row.add("批次数量");
/* 2333 */               row.add("发货数量");
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2338 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 2339 */               row.add(
/* 2340 */                   BeanUtils.getProperty(data, "supplierBatch"));
/* 2341 */               row.add(BeanUtils.getProperty(data, 
/* 2342 */                     "psoItem.poipItem.itemNumber.id"));
/* 2343 */               row.add(BeanUtils.getProperty(data, 
/* 2344 */                     "psoItem.poipItem.itemNumber.dpiNo"));
/* 2345 */               row.add(BeanUtils.getProperty(data, 
/* 2346 */                     "psoItem.qty_std"));
/* 2347 */               row.add("");
/* 2348 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2349 */               row.add(BeanUtils.getProperty(data, 
/* 2350 */                     "isPrint.chnShortDescription"));
/*      */             }
/*      */           });
/* 2353 */       return new ActionForward("download/" + index + "/" + 
/* 2354 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 2356 */     if (formBean.isFirstInit()) {
/* 2357 */       formBean.init(totalCount);
/*      */     } else {
/* 2359 */       formBean.init();
/*      */     } 
/*      */     
/* 2362 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2363 */     request.setAttribute("x_portalShipOrderItemList", 
/* 2364 */         portalShipOrderItemList);
/* 2365 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/* 2366 */     int size = resultList.size();
/* 2367 */     for (Box poIpiBox : resultList) {
/* 2368 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 2369 */         poIpiBox.getSupplierBatch() != null) {
/* 2370 */         size--;
/* 2371 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 2374 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/* 2375 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/* 2377 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 2378 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 2379 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotListProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2385 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2386 */     PortalShipOrderManager portalShipOrderManager = 
/* 2387 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2388 */     String id = request.getParameter("id");
/* 2389 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 2390 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 2391 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2392 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2393 */     List<Box> resultList = portalShipOrderManager
/* 2394 */       .getBoxByShipOrderId(portalShipOrder.getId());
/* 2395 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/* 2397 */     String str = request.getParameter("supplierBatchStr");
/* 2398 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/* 2400 */     if ((str != null || poipBoxIds != null) && (
/* 2401 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/* 2402 */       String[] supplierBatchList = str.split(",");
/* 2403 */       String[] poipBoxIdList = poipBoxIds.split(",");
/* 2404 */       if (supplierBatchList.length > 0) {
/* 2405 */         for (int i = 0; i < supplierBatchList.length; i++) {
/* 2406 */           if (poipBoxIdList[i].length() > 0) {
/* 2407 */             Integer poipBoxId = 
/* 2408 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/* 2409 */             String supplierBatch = supplierBatchList[i];
/* 2410 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/* 2411 */             if (supplierBatch != null || supplierBatch != "") {
/* 2412 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/* 2414 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/* 2416 */               poIpiBox.setSupplierBatch("");
/* 2417 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2427 */     int totalCount = resultList.size();
/*      */     
/* 2429 */     int pageCount = 0;
/*      */     
/* 2431 */     int endNum = formBean.getPageSizeAsInt();
/* 2432 */     if (endNum == -1) {
/* 2433 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 2436 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 2438 */     if (totalCount % endNum > 0) {
/*      */       
/* 2440 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 2443 */       pageCount = totalCount / endNum;
/*      */     } 
/* 2445 */     if (totalCount > 0) {
/* 2446 */       if (startNum <= pageCount) {
/* 2447 */         if (startNum == 1) {
/*      */           
/* 2449 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 2453 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 2457 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 2459 */           int toIndex = startNum * endNum;
/*      */           
/* 2461 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 2462 */             toIndex = startNum * endNum;
/*      */           } else {
/* 2464 */             toIndex = (startNum - 1) * endNum + 
/* 2465 */               totalCount % endNum;
/*      */           } 
/* 2467 */           if (totalCount >= toIndex) {
/* 2468 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 2472 */         resultList = null;
/*      */       } 
/*      */     }
/* 2475 */     if (formBean.isFirstInit()) {
/* 2476 */       formBean.init(totalCount);
/*      */     } else {
/* 2478 */       formBean.init();
/*      */     } 
/*      */     
/* 2481 */     request.setAttribute("x_supplierBatchStr", str);
/* 2482 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/* 2483 */     request.setAttribute("x_id", id);
/* 2484 */     request.setAttribute("x_boxList", resultList);
/* 2485 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2486 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2492 */     PortalShipOrderManager portalShipOrderManager = 
/* 2493 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2494 */     PurchaseOrderInspectionPendingManager poipManager = 
/* 2495 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2496 */     PurchaseOrderManager poManager = 
/* 2497 */       ServiceLocator.getPurchaseOrderManager(request);
/* 2498 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 2499 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/* 2501 */     List<Box> list = new ArrayList();
/* 2502 */     List<Box> poipBoxList = null;
/*      */     try {
/* 2504 */       if (!portalShipOrder.getStatus().equals(
/* 2505 */           PortalShipOrderStatus.RECEIVE)) {
/* 2506 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/* 2507 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/* 2509 */         portalShipOrderItemlist = portalShipOrderManager
/* 2510 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/* 2511 */             .getId());
/* 2512 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/* 2515 */           poipBoxList = portalShipOrderManager
/* 2516 */             .getBoxList(portalShipOrderItem.getId());
/* 2517 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/* 2520 */     } catch (Exception e) {
/* 2521 */       e.fillInStackTrace();
/* 2522 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 2525 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2526 */     request.setAttribute("x_portalShipOrderItemList", 
/* 2527 */         portalShipOrderItemlist);
/* 2528 */     request.setAttribute("x_portalShopOrderBoxList", list);
/* 2529 */     int size = poipBoxList.size();
/* 2530 */     for (Box poIpiBox : poipBoxList) {
/* 2531 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 2532 */         poIpiBox.getSupplierBatch() != null) {
/* 2533 */         size--;
/* 2534 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 2537 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/* 2540 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2546 */     PortalShipOrderManager portalShipOrderManager = 
/* 2547 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2548 */     String id = request.getParameter("id");
/* 2549 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 2550 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/* 2553 */     return new ActionForward("viewPortalShipOrderTwoProduct.do?id=" + 
/* 2554 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReportProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2560 */     PurchaseOrderInspectionPendingManager poip = 
/* 2561 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2562 */     String string = request.getParameter("str");
/* 2563 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */ 
/*      */     
/* 2566 */     String[] strings = string.split(",");
/* 2567 */     List<Box> boxlist = new ArrayList<Box>();
/* 2568 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 2569 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 2570 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/* 2571 */       String batchStr = box.getPsoItem().getPortalShipOrder().getCode();
/* 2572 */       String batchNo = batchStr.substring(batchStr.length() - 9, batchStr.length());
/* 2573 */       box.setHuCodeOrderNumber(batchNo);
/* 2574 */       boxlist.add(box);
/*      */       b++; }
/*      */     
/* 2577 */     request.setAttribute("x_item", item);
/* 2578 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 2579 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/* 2581 */     request.setAttribute("path", request.getContextPath());
/* 2582 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItemsProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2588 */     PurchaseOrderInspectionPendingManager poip = 
/* 2589 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2590 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/* 2591 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 2592 */     JSONObject object = new JSONObject();
/* 2593 */     boolean bol = false;
/* 2594 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 2595 */     String ids = request.getParameter("ids");
/* 2596 */     String[] idr = ids.split(";");
/* 2597 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/* 2598 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2599 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 2600 */       box.setIsPrint(YesNo.YES);
/* 2601 */       manager.updateBox(box);
/* 2602 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/* 2605 */     bol = true;
/* 2606 */     PrintWriter print = response.getWriter();
/* 2607 */     object.put("str", Boolean.valueOf(bol));
/* 2608 */     print.print(object);
/* 2609 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObjectProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2618 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2624 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2625 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2626 */     PortalShipOrderManager portalShipOrderManager = 
/* 2627 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2628 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 2630 */     PortalShipOrder portalShipOrder = null;
/*      */     
/*      */     try {
/* 2633 */       String[] poipItemIdsList = request
/* 2634 */         .getParameterValues("poipItemIds");
/* 2635 */       String[] deliveryNumberList = request
/* 2636 */         .getParameterValues("deliveryNumbers");
/* 2637 */       for (int i = 0; i < poipItemIdsList.length; i++) {
/* 2638 */         if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */           
/* 2642 */           if (portalShipOrder == null) {
/* 2643 */             portalShipOrder = new PortalShipOrder();
/* 2644 */             Site site = getCurrentUser(request).getPrimarySite();
/* 2645 */             User requestor = getCurrentUser(request);
/* 2646 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 2647 */             String arrivalDate = queryForm.getReceivingDate();
/* 2648 */             Date arrDate = null;
/* 2649 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 2650 */               arrDate = sdf.parse(arrivalDate);
/*      */             }
/* 2652 */             portalShipOrder.setType(Integer.valueOf(3));
/* 2653 */             portalShipOrderManager.insertPortalShipOrder(
/* 2654 */                 portalShipOrder, site, requestor, arrDate);
/*      */           } 
/*      */           
/* 2657 */           String poipItem = poipItemIdsList[i];
/* 2658 */           BigDecimal deliveryNumber = new BigDecimal(
/* 2659 */               deliveryNumberList[i]);
/*      */           
/* 2661 */           PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 2662 */             .getPurchaseOrderInspectionPendingItem(
/* 2663 */               Integer.valueOf(Integer.parseInt(poipItem)));
/*      */           
/* 2665 */           BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 2666 */             .getQtyOpen();
/* 2667 */           purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 2668 */               .subtract(deliveryNumber));
/* 2669 */           poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */           
/* 2672 */           Boolean isClose = poipm
/* 2673 */             .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 2674 */               .getPoip_number());
/* 2675 */           if (isClose.booleanValue()) {
/* 2676 */             purchaseOrderInspectionPendingItem1.getPoip_number()
/* 2677 */               .setStatus(PurchaseOrderStatus.WAIT);
/* 2678 */             poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 2679 */                 .getPoip_number());
/*      */           } 
/*      */           
/* 2682 */           PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */           
/* 2684 */           portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2685 */           portalShipOrderItem
/* 2686 */             .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 2687 */           portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2688 */           portalShipOrderItem
/* 2689 */             .setQty_std(purchaseOrderInspectionPendingItem1
/* 2690 */               .getQty_std());
/* 2691 */           portalShipOrderManager
/* 2692 */             .insertPortalShipOrderItem(portalShipOrderItem);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2703 */       PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 2704 */         .getPurchaseOrderInspectionPendingItem(
/* 2705 */           Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 2706 */       if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
/* 2707 */         portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */       }
/* 2709 */     } catch (Exception e) {
/* 2710 */       e.fillInStackTrace();
/*      */     } 
/* 2712 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + 
/* 2713 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2720 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2721 */     PurchaseOrderInspectionPendingManager fm = 
/* 2722 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2723 */     ProjectedInventoryManager projectedInventoryManager = 
/* 2724 */       ServiceLocator.getProjectedInventoryManager(request);
/* 2725 */     String idString = queryForm.getSelectPoipItemId();
/* 2726 */     String[] ids = idString.split(";");
/* 2727 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 2729 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 2730 */         Integer.valueOf(0));
/* 2731 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2732 */     String poNumber = queryForm.getPoip_number();
/* 2733 */     if (poNumber != null && !poNumber.equals("")) {
/* 2734 */       conditions.put(
/* 2735 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 2736 */           poNumber);
/*      */     }
/*      */     
/* 2739 */     String itemNumber = queryForm.getItemNumber();
/* 2740 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 2741 */       conditions.put(
/* 2742 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 2743 */           itemNumber);
/*      */     }
/*      */     
/* 2746 */     String endPoDate = queryForm.getDueDate();
/* 2747 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 2748 */       conditions.put(
/* 2749 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 2750 */           endPoDate);
/*      */     }
/* 2752 */     String status = queryForm.getStatus();
/* 2753 */     if (status != null && !status.equals("")) {
/* 2754 */       conditions.put(
/* 2755 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 2756 */           status);
/*      */     } else {
/* 2758 */       conditions.put(
/* 2759 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 2761 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 2762 */     User user = getCurrentUser(request);
/* 2763 */     Supplier supplier = manager.getSupplierByCode(user.getLoginName());
/* 2764 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 2765 */       supplier != null) {
/*      */       
/* 2767 */       conditions
/* 2768 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2769 */           supplier.getId());
/*      */     } else {
/* 2771 */       conditions
/* 2772 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2773 */           Integer.valueOf(0));
/*      */     } 
/* 2775 */     conditions.put(
/* 2776 */         PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(1));
/* 2777 */     if (queryForm.isFirstInit()) {
/* 2778 */       queryForm
/* 2779 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 2781 */       queryForm.init();
/*      */     } 
/* 2783 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2784 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 2785 */         queryForm.getPageNoAsInt(), 
/* 2786 */         queryForm.getPageSizeAsInt(), 
/* 2787 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 2788 */         queryForm.isDescend());
/* 2789 */     if (ids.length > 0) {
/* 2790 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2791 */         Integer itemId = pendingItem.getId();
/* 2792 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 2794 */         for (int i = 0; i < ids.length; i++) {
/* 2795 */           if (!ids[i].equals("") && 
/* 2796 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 2797 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2802 */         if (isClose.booleanValue()) {
/* 2803 */           pendingItem.setChecked("checked");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2826 */     request.setAttribute("X_RESULTLIST", result);
/* 2827 */     request.setAttribute("X_YESNOLIST", 
/* 2828 */         PersistentEnum.getEnumList(YesNo.class));
/* 2829 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 2830 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 2831 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward computePurchaseItemTwoProductByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2842 */     response.setContentType("text/json");
/* 2843 */     response.setCharacterEncoding("UTF-8");
/* 2844 */     JsonConfig cfg = new JsonConfig();
/* 2845 */     ProjectedInventoryManager projectedInventoryManager = 
/* 2846 */       ServiceLocator.getProjectedInventoryManager(request);
/* 2847 */     PurchaseOrderInspectionPendingManager fm = 
/* 2848 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2849 */     String arrays = request.getParameter("arrays");
/* 2850 */     String[] str = arrays.split(",");
/* 2851 */     List<Map> mapList = new ArrayList<Map>();
/* 2852 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 2853 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.ID_IN, str);
/* 2854 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2855 */       .getPurchaseOrderInspectionPendingItemList(conditions, 0, -1, 
/* 2856 */         PurchaseOrderInspectionPendingQueryOrder.ID, false);
/* 2857 */     for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2858 */       pendingItem = fm.getPurchaseOrderInspectionPendingItem(pendingItem);
/* 2859 */       pendingItem.setTransitQty(pendingItem.getQty()
/* 2860 */           .subtract(pendingItem.getQtyOpen())
/* 2861 */           .subtract(pendingItem.getReceiptQty()));
/* 2862 */       Map<Object, Object> conditions1 = new HashMap<Object, Object>();
/* 2863 */       conditions1.put(ProjectedInventoryQueryCondition.PART_ID_EQ, 
/* 2864 */           pendingItem.getItemNumber().getId());
/* 2865 */       List<ProjectedInventory> projectedInventoryList = projectedInventoryManager
/* 2866 */         .getList(conditions1, 0, -1, null, true);
/* 2867 */       if (projectedInventoryList != null && 
/* 2868 */         projectedInventoryList.size() > 0) {
/* 2869 */         pendingItem.setCurrentQty(
/* 2870 */             (((ProjectedInventory)projectedInventoryList.get(0)).getCurrentQty() == null) ? new BigDecimal(0) : (
/* 2871 */             (ProjectedInventory)projectedInventoryList.get(0)).getCurrentQty()); continue;
/*      */       } 
/* 2873 */       pendingItem.setCurrentQty(new BigDecimal(0));
/*      */     } 
/*      */     
/* 2876 */     Map<String, String> map1 = new LinkedHashMap<String, String>();
/* 2877 */     for (PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem : result) {
/* 2878 */       map1.put(
/* 2879 */           purchaseOrderInspectionPendingItem.getItemNumber().getId(), 
/* 2880 */           purchaseOrderInspectionPendingItem.getItemNumber().getId());
/*      */     }
/* 2882 */     for (String str1 : map1.keySet()) {
/* 2883 */       Map<Object, Object> map2 = new LinkedHashMap<Object, Object>();
/* 2884 */       map2.put(map1.get(str1), map1.get(str1));
/* 2885 */       mapList.add(map2);
/*      */     } 
/* 2887 */     for (PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem : result) {
/* 2888 */       for (Map<String, String> map : mapList) {
/* 2889 */         if (map.containsKey(purchaseOrderInspectionPendingItem
/* 2890 */             .getItemNumber().getId())) {
/* 2891 */           map.put("part", purchaseOrderInspectionPendingItem
/* 2892 */               .getItemNumber().getId());
/* 2893 */           map.put("highQty", purchaseOrderInspectionPendingItem
/* 2894 */               .getItemNumber().getHighQty());
/* 2895 */           map.put("lowQty", purchaseOrderInspectionPendingItem
/* 2896 */               .getItemNumber().getLowQty());
/* 2897 */           map.put("currentQty", 
/* 2898 */               purchaseOrderInspectionPendingItem.getCurrentQty());
/* 2899 */           if (map.containsKey("transitQty")) {
/* 2900 */             BigDecimal transitQty = (BigDecimal)map
/* 2901 */               .get("transitQty");
/* 2902 */             transitQty.add(purchaseOrderInspectionPendingItem
/* 2903 */                 .getTransitQty());
/* 2904 */             map.put("transitQty", transitQty);
/*      */           } else {
/* 2906 */             map.put("transitQty", 
/* 2907 */                 purchaseOrderInspectionPendingItem
/* 2908 */                 .getTransitQty());
/*      */           } 
/* 2910 */           if (map.containsKey("qty")) {
/* 2911 */             BigDecimal qty = (BigDecimal)map.get("qty");
/* 2912 */             qty.add(purchaseOrderInspectionPendingItem.getQty());
/* 2913 */             map.put("qty", qty); continue;
/*      */           } 
/* 2915 */           map.put("qty", 
/* 2916 */               purchaseOrderInspectionPendingItem.getQty());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2921 */     for (Map<String, BigDecimal> map : mapList) {
/* 2922 */       BigDecimal qty = (BigDecimal)map.get("qty");
/* 2923 */       BigDecimal transitQty = (BigDecimal)map.get("transitQty");
/* 2924 */       BigDecimal currentQty = (BigDecimal)map.get("currentQty");
/* 2925 */       BigDecimal highQty = (BigDecimal)map.get("highQty");
/*      */ 
/*      */       
/* 2928 */       map.put("recommendQty", 
/* 2929 */           highQty.subtract(transitQty).subtract(currentQty));
/* 2930 */       BigDecimal recommendQty = map.get("recommendQty");
/*      */ 
/*      */ 
/*      */       
/* 2934 */       if (recommendQty.compareTo(highQty) == 1) {
/* 2935 */         map.put("message", "推荐数量已高于高储数量"); continue;
/*      */       } 
/* 2937 */       map.put("message", "");
/*      */     } 
/*      */     
/* 2940 */     JSONArray jo = JSONArray.fromObject(mapList, cfg);
/* 2941 */     response.getWriter().print(jo);
/* 2942 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2948 */     SupplierManager supplierManager = 
/* 2949 */       ServiceLocator.getSupplierManager(request);
/* 2950 */     WmsPartManager wmsPartManager = 
/* 2951 */       ServiceLocator.getWmsPartManager(request);
/* 2952 */     PortalShipOrderManager portalShipOrderManager = 
/* 2953 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2954 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/* 2955 */       ServiceLocator.getPortalShipOrderItemManager(request);
/* 2956 */     User user = getCurrentUser(request);
/* 2957 */     Supplier supplier = supplierManager.getSupplierByCode(user
/* 2958 */         .getLoginName());
/* 2959 */     PortalShipOrder portalShipOrder = null;
/* 2960 */     if (supplier != null) {
/*      */       try {
/* 2962 */         String[] partIdsList = request.getParameterValues("ids");
/* 2963 */         String[] deliveryNumberList = request
/* 2964 */           .getParameterValues("deliveryNumbers");
/* 2965 */         String arrivalDate = request.getParameter("receivingDate");
/* 2966 */         String createType = request.getParameter("createType");
/* 2967 */         for (int i = 0; i < partIdsList.length; i++) {
/* 2968 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */             
/* 2972 */             if (portalShipOrder == null) {
/* 2973 */               portalShipOrder = new PortalShipOrder();
/* 2974 */               Site site = getCurrentUser(request)
/* 2975 */                 .getPrimarySite();
/* 2976 */               User requestor = getCurrentUser(request);
/* 2977 */               portalShipOrder.setCreateType(createType);
/* 2978 */               portalShipOrder.setSupplier(supplier);
/* 2979 */               portalShipOrder.setCreateDate(new Date());
/* 2980 */               portalShipOrder.setIsPrint(YesNo.NO);
/* 2981 */               portalShipOrder.setType(Integer.valueOf(3));
/* 2982 */               portalShipOrder.setArrivalDate(new Date(arrivalDate));
/* 2983 */               portalShipOrderManager.insertPortalShipOrderByJitPart(
/* 2984 */                   portalShipOrder, site, requestor, new Date());
/*      */             } 
/* 2986 */             String partId = partIdsList[i];
/* 2987 */             BigDecimal deliveryNumber = new BigDecimal(
/* 2988 */                 deliveryNumberList[i]);
/* 2989 */             WmsPart part = wmsPartManager.getWmsPart(partId);
/*      */             
/* 2991 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/* 2992 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2993 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2994 */             portalShipOrderItem.setPart(part);
/* 2995 */             portalShipOrderManager
/* 2996 */               .insertPortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/* 2998 */         }  portalShipOrderManager
/* 2999 */           .createPortalShipOrderByJitPartIP(portalShipOrder);
/* 3000 */       } catch (Exception e) {
/* 3001 */         e.fillInStackTrace();
/*      */       } 
/* 3003 */       return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + 
/* 3004 */           portalShipOrder.getId(), true);
/*      */     } 
/* 3006 */     return new ActionForward("/listShippingOrderProduct.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderTwoWrongByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3012 */     WmsPartManager wmsPartManager = 
/* 3013 */       ServiceLocator.getWmsPartManager(request);
/* 3014 */     PortalShipOrderManager portalShipOrderManager = 
/* 3015 */       ServiceLocator.getPortalShipOrderManager(request);
/* 3016 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/* 3017 */       ServiceLocator.getPortalShipOrderItemManager(request);
/* 3018 */     SupplierManager supplierManager = 
/* 3019 */       ServiceLocator.getSupplierManager(request);
/* 3020 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/* 3021 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/*      */ 
/*      */ 
/*      */     
/* 3025 */     String supplierCode = request.getParameter("supplierCode");
/* 3026 */     Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
/* 3027 */     Map<Object, Object> mapSite = new HashMap<Object, Object>();
/* 3028 */     mapSite.put(SiteQueryCondition.NAME_EQ, supplierCode);
/* 3029 */     List<Site> siteList = siteManager.getSiteList(mapSite, 0, -1, null, 
/* 3030 */         false);
/* 3031 */     PortalShipOrder portalShipOrder = null;
/* 3032 */     if (supplier != null) {
/*      */       try {
/* 3034 */         Site site = siteList.get(0);
/* 3035 */         String[] partIdsList = request.getParameterValues("ids");
/* 3036 */         String[] deliveryNumberList = request
/* 3037 */           .getParameterValues("deliveryNumbers");
/* 3038 */         String createType = request.getParameter("createType");
/* 3039 */         String arrivalDate = request.getParameter("receivingDate");
/* 3040 */         for (int i = 0; i < partIdsList.length; i++) {
/* 3041 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3046 */             if (portalShipOrder == null) {
/* 3047 */               portalShipOrder = new PortalShipOrder();
/*      */ 
/*      */               
/* 3050 */               User requestor = getCurrentUser(request);
/* 3051 */               portalShipOrder.setCreateType(createType);
/* 3052 */               portalShipOrder.setSupplier(supplier);
/* 3053 */               portalShipOrder.setCreateDate(new Date());
/* 3054 */               portalShipOrder.setArrivalDate(format
/* 3055 */                   .parse(arrivalDate));
/* 3056 */               portalShipOrder.setIsPrint(YesNo.NO);
/* 3057 */               portalShipOrder.setType(Integer.valueOf(2));
/* 3058 */               portalShipOrderManager
/* 3059 */                 .insertPortalShipOrderByNpoPartSupplier(
/* 3060 */                   portalShipOrder, site, supplier, 
/* 3061 */                   new Date());
/*      */             } 
/* 3063 */             String partId = partIdsList[i];
/*      */ 
/*      */ 
/*      */             
/* 3067 */             String a = deliveryNumberList[i].replace(",", "");
/* 3068 */             BigDecimal deliveryNumber = new BigDecimal(a);
/* 3069 */             WmsPart part = wmsPartManager.getWmsPart(partId);
/* 3070 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/* 3071 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 3072 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 3073 */             portalShipOrderItem.setPart(part);
/* 3074 */             portalShipOrderManager
/* 3075 */               .insertPortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/* 3077 */         }  portalShipOrderManager
/* 3078 */           .createPortalShipOrderByNpoPartIP(portalShipOrder);
/* 3079 */       } catch (Exception e) {
/* 3080 */         e.fillInStackTrace();
/*      */       } 
/* 3082 */       return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + 
/* 3083 */           portalShipOrder.getId(), true);
/*      */     } 
/* 3085 */     return new ActionForward("/listShippingOrderWrong.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateJitCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3092 */     String id = request.getParameter("id");
/*      */     try {
/* 3094 */       PortalShipOrderManager shipOrderManager = 
/* 3095 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3096 */       PortalShipOrder shipOrder = shipOrderManager
/* 3097 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3098 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3099 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3100 */     } catch (Exception e) {
/*      */       
/* 3102 */       e.printStackTrace();
/*      */     } 
/* 3104 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id, 
/* 3105 */         true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateJitOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3112 */     String id = request.getParameter("id");
/*      */     try {
/* 3114 */       PortalShipOrderManager shipOrderManager = 
/* 3115 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3116 */       PortalShipOrder shipOrder = shipOrderManager
/* 3117 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3118 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3119 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3120 */     } catch (Exception e) {
/*      */       
/* 3122 */       e.printStackTrace();
/*      */     } 
/* 3124 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id, 
/* 3125 */         true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3132 */     boolean bol = false;
/* 3133 */     JSONObject object = new JSONObject();
/* 3134 */     String id = request.getParameter("id");
/*      */     try {
/* 3136 */       PortalShipOrderManager shipOrderManager = 
/* 3137 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3138 */       PortalShipOrder shipOrder = shipOrderManager
/* 3139 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3140 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3141 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3142 */       bol = true;
/* 3143 */     } catch (Exception e) {
/*      */       
/* 3145 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3150 */     return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3158 */     String id = request.getParameter("id");
/*      */     try {
/* 3160 */       PortalShipOrderManager shipOrderManager = 
/* 3161 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3162 */       PortalShipOrder shipOrder = shipOrderManager
/* 3163 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3164 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3165 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3166 */     } catch (Exception e) {
/*      */       
/* 3168 */       e.printStackTrace();
/*      */     } 
/* 3170 */     return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateNPoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3177 */     String id = request.getParameter("id");
/*      */     try {
/* 3179 */       PortalShipOrderManager shipOrderManager = 
/* 3180 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3181 */       PortalShipOrder shipOrder = shipOrderManager
/* 3182 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3183 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3184 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3185 */     } catch (Exception e) {
/*      */       
/* 3187 */       e.printStackTrace();
/*      */     } 
/* 3189 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id + 
/* 3190 */         "&GrantedSite=1", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateNPoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3197 */     String id = request.getParameter("id");
/*      */     try {
/* 3199 */       PortalShipOrderManager shipOrderManager = 
/* 3200 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3201 */       PortalShipOrder shipOrder = shipOrderManager
/* 3202 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3203 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3204 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3205 */     } catch (Exception e) {
/*      */       
/* 3207 */       e.printStackTrace();
/*      */     } 
/* 3209 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id + 
/* 3210 */         "&GrantedSite=1", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteDeliveryPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3221 */     PortalShipOrderManager manager = 
/* 3222 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3224 */     String id = request.getParameter("id");
/* 3225 */     manager.deleteDeliveryPo(id);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3270 */     return new ActionForward("/listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteDeliveryNPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3280 */     PortalShipOrderManager manager = 
/* 3281 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3283 */     String id = request.getParameter("id");
/* 3284 */     manager.deleteDeliveryNPo(id);
/* 3285 */     return new ActionForward("/listShippingOrderWrong.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteDeliveryJit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3295 */     PortalShipOrderManager manager = 
/* 3296 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3298 */     String id = request.getParameter("id");
/* 3299 */     manager.deleteDeliveryJit(id);
/* 3300 */     return new ActionForward("/listShippingOrderProduct.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3433 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 3434 */     PortalShipOrderManager fm = 
/* 3435 */       ServiceLocator.getPortalShipOrderManager(request);
/* 3436 */     if (queryForm.getOrder() == "") {
/* 3437 */       queryForm.setOrder("createDate");
/* 3438 */       queryForm.setDescend(true);
/*      */     } 
/* 3440 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */ 
/*      */     
/* 3443 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 3444 */     String exportType = queryForm.getExportType();
/* 3445 */     if (exportType != null && exportType.length() > 0) {
/* 3446 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 3447 */           false);
/* 3448 */       int index = SessionTempFile.createNewTempFile(request);
/* 3449 */       String fileName = "PortalShipOrder";
/* 3450 */       String suffix = ExportUtil.export(
/* 3451 */           exportType, 
/* 3452 */           data, 
/* 3453 */           request, 
/* 3454 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 3455 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 3458 */               MessageResources messages = PortalShipOrderAction.this.getResources(request);
/* 3459 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 3460 */                     "SO.No1"));
/* 3461 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 3462 */                     "supplier1"));
/* 3463 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 3464 */                     "supplier.code1"));
/* 3465 */               row.add("创建时间");
/* 3466 */               row.add("发货时间");
/* 3467 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 3468 */                     "salesOrderItem.issync"));
/* 3469 */               row.add(messages.getMessage(PortalShipOrderAction.this.getLocale(request), 
/* 3470 */                     "portalShipOrder.status"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 3475 */               row.add(BeanUtils.getProperty(data, "code"));
/* 3476 */               row.add(
/* 3477 */                   BeanUtils.getProperty(data, "supplier.name"));
/* 3478 */               row.add(
/* 3479 */                   BeanUtils.getProperty(data, "supplier.code"));
/*      */               
/* 3481 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 3482 */               row.add(BeanUtils.getProperty(data, "arrivalDate"));
/* 3483 */               PortalShipOrder order = (PortalShipOrder)data;
/* 3484 */               if (order.getIsSync() != null) {
/* 3485 */                 if (order.getIsSync().equals("0")) {
/* 3486 */                   row.add("未同步");
/* 3487 */                 } else if (order.getIsSync().equals("1")) {
/* 3488 */                   row.add("已同步");
/* 3489 */                 } else if (order.getIsSync().equals("")) {
/* 3490 */                   row.add("未同步");
/*      */                 } 
/*      */               } else {
/* 3493 */                 row.add("未同步");
/*      */               } 
/* 3495 */               String locale = PortalShipOrderAction.this.getCurrentUser(request).getLocale();
/* 3496 */               if ("en".equals(locale)) {
/* 3497 */                 row.add(BeanUtils.getProperty(data, 
/* 3498 */                       "status.engShortDescription"));
/*      */               } else {
/* 3500 */                 row.add(BeanUtils.getProperty(data, 
/* 3501 */                       "status.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/* 3505 */       return new ActionForward("download/" + index + "/" + 
/* 3506 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 3508 */     if (queryForm.isFirstInit()) {
/* 3509 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/* 3511 */       queryForm.init();
/*      */     } 
/* 3513 */     int a = queryForm.getPageSizeAsInt();
/* 3514 */     List result = fm.getPortalShipOrderList(conditions, 
/* 3515 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 3516 */         PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/* 3517 */         queryForm.isDescend());
/* 3518 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 3519 */     request.setAttribute("X_DATE", simpleDateFormat.format(new Date()));
/* 3520 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 3521 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 3522 */     request.setAttribute("x_statusList", 
/* 3523 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 3524 */     request.setAttribute("X_RESULTLIST", result);
/* 3525 */     request.setAttribute("x_selType", Integer.valueOf(180));
/* 3526 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward getQrCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3539 */     String msg = request.getParameter("msg");
/*      */     
/* 3541 */     return new ActionForward("page");
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PortalShipOrderAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */