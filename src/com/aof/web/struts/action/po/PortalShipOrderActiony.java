/*      */ package com.aof.web.struts.action.po;
/*      */ 
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.admin.query.SiteQueryCondition;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basicDataView.JitShipPart;
/*      */ import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
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
/*      */ import com.aof.service.basicDataView.BasicDataViewManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.PortalShipOrderItemManager;
/*      */ import com.aof.service.po.PortalShipOrderManager;
/*      */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*      */ import com.aof.service.po.PurchaseOrderManager;
/*      */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*      */ import com.aof.service.quartz.job.DeliverMinuteSyncJob;
/*      */ import com.aof.service.schedule.ProjectedInventoryManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.ActionUtils2;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.admin.PortalShipOrderMainQueryForm;
/*      */ import com.aof.web.struts.form.basicDataView.BasicDataViewQueryForm;
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
/*      */ 
/*      */ public class PortalShipOrderActiony
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward shippingOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*   92 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*   93 */     PortalShipOrderManager fm = 
/*   94 */       ServiceLocator.getPortalShipOrderManager(request);
/*   95 */     if (queryForm.getOrder() == "") {
/*   96 */       queryForm.setOrder("createDate");
/*   97 */       queryForm.setDescend(true);
/*      */     } 
/*      */     
/*  100 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  101 */     getConditionsFrom(queryForm, conditions);
/*  102 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */ 
/*      */     
/*  106 */     conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "NJIT_PO");
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
/*  117 */     String exportType = queryForm.getExportType();
/*  118 */     if (exportType != null && exportType.length() > 0) {
/*  119 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/*  120 */           false);
/*  121 */       int index = SessionTempFile.createNewTempFile(request);
/*  122 */       String fileName = "PortalShipOrder";
/*  123 */       String suffix = ExportUtil.export(
/*  124 */           exportType, 
/*  125 */           data, 
/*  126 */           request, 
/*  127 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  128 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  131 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/*  132 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  133 */                     "SO.No1"));
/*  134 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  135 */                     "supplier1"));
/*  136 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  137 */                     "supplier.code1"));
/*  138 */               row.add("创建时间");
/*  139 */               row.add("发货时间");
/*  140 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  141 */                     "salesOrderItem.issync"));
/*  142 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  143 */                     "portalShipOrder.status"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  148 */               row.add(BeanUtils.getProperty(data, "code"));
/*  149 */               row.add(
/*  150 */                   BeanUtils.getProperty(data, "supplier.name"));
/*  151 */               row.add(
/*  152 */                   BeanUtils.getProperty(data, "supplier.code"));
/*      */               
/*  154 */               row.add(BeanUtils.getProperty(data, "createDate"));
/*  155 */               row.add(BeanUtils.getProperty(data, "arrivalDate"));
/*  156 */               PortalShipOrder order = (PortalShipOrder)data;
/*  157 */               if (order.getIsSync() != null) {
/*  158 */                 if (order.getIsSync().equals("0")) {
/*  159 */                   row.add("未同步");
/*  160 */                 } else if (order.getIsSync().equals("1")) {
/*  161 */                   row.add("已同步");
/*  162 */                 } else if (order.getIsSync().equals("")) {
/*  163 */                   row.add("未同步");
/*      */                 } 
/*      */               } else {
/*  166 */                 row.add("未同步");
/*      */               } 
/*  168 */               String locale = PortalShipOrderActiony.this.getCurrentUser(request).getLocale();
/*  169 */               if ("en".equals(locale)) {
/*  170 */                 row.add(BeanUtils.getProperty(data, 
/*  171 */                       "status.engShortDescription"));
/*      */               } else {
/*  173 */                 row.add(BeanUtils.getProperty(data, 
/*  174 */                       "status.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/*  178 */       return new ActionForward("download/" + index + "/" + 
/*  179 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  181 */     if (queryForm.isFirstInit()) {
/*  182 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  184 */       queryForm.init();
/*      */     } 
/*  186 */     List result = fm.getPortalShipOrderList(conditions, 
/*  187 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  188 */         PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  189 */         queryForm.isDescend());
/*  190 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  191 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  192 */     request.setAttribute("x_statusList", 
/*  193 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  194 */     request.setAttribute("X_RESULTLIST", result);
/*  195 */     request.setAttribute("x_selType", Integer.valueOf(159));
/*  196 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listShipOrderReport1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  203 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*  204 */     PortalShipOrderManager fm = 
/*  205 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/*  207 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  208 */     conditions.put(PortalShipOrderQueryCondition.SITE_EQ, 
/*  209 */         getCurrentUser(request).getPrimarySite().getId());
/*  210 */     getConditionsFrom(queryForm, conditions);
/*  211 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  213 */     String exportType = queryForm.getExportType();
/*  214 */     if (queryForm.isFirstInit()) {
/*  215 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  217 */       queryForm.init();
/*      */     } 
/*  219 */     List result = fm.getPortalShipOrderList(conditions, 
/*  220 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  221 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*  222 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  223 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  224 */     request.setAttribute("x_statusList", 
/*  225 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  226 */     request.setAttribute("X_RESULTLIST", result);
/*  227 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listShipOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  235 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/*  236 */     PortalShipOrderManager fm = 
/*  237 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/*  239 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */ 
/*      */     
/*  242 */     getConditionsFrom(queryForm, conditions);
/*  243 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  245 */     if (queryForm.isFirstInit()) {
/*  246 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/*  248 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/*  252 */     String exportType = queryForm.getExportType();
/*  253 */     if (StringUtils.isNotEmpty(exportType)) {
/*  254 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, 
/*  255 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  256 */           queryForm.isDescend());
/*  257 */       int index = SessionTempFile.createNewTempFile(request);
/*  258 */       String fileName = "PortalShip";
/*  259 */       String suffix = ExportUtil.export(
/*  260 */           exportType, 
/*  261 */           data, 
/*  262 */           request, 
/*  263 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  264 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  268 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/*  269 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  270 */                     "PortalShip.code"));
/*  271 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  272 */                     "PortalShip.createDate"));
/*  273 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  274 */                     "WmsUW.remark"));
/*  275 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/*  276 */                     "WmsUW.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  281 */               row.add(BeanUtils.getProperty(data, "code"));
/*  282 */               row.add(BeanUtils.getProperty(data, "createDate"));
/*  283 */               row.add(BeanUtils.getProperty(data, "remark"));
/*  284 */               if (BeanUtils.getProperty(data, "status") == "0") {
/*  285 */                 row.add("成功");
/*      */               } else {
/*  287 */                 row.add(BeanUtils.getProperty(data, "status"));
/*      */               } 
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  293 */       return new ActionForward("download/" + index + "/" + 
/*  294 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  296 */     List result = fm.getPortalShipOrderList(conditions, 
/*  297 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  298 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*  299 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  300 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  301 */     request.setAttribute("x_statusList", 
/*  302 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/*  303 */     request.setAttribute("X_RESULTLIST", result);
/*  304 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void getConditionsFrom(PortalShipOrderMainQueryForm queryForm, Map<PortalShipOrderQueryCondition, String> conditions) {
/*  310 */     String code = queryForm.getCode();
/*  311 */     if (code != null && code.trim().length() != 0) {
/*  312 */       conditions.put(PortalShipOrderQueryCondition.CODE_EQ, code);
/*      */     }
/*  314 */     Integer enabled = ActionUtils2.parseInt(queryForm.getEnabled());
/*  315 */     if (enabled != null && enabled.intValue() != 0) {
/*  316 */       conditions.put(PortalShipOrderQueryCondition.ENABLED_EQ, enabled);
/*      */     }
/*  318 */     String status = queryForm.getStatus();
/*  319 */     if (status != null && status.trim().length() != 0) {
/*  320 */       conditions.put(
/*  321 */           PortalShipOrderQueryCondition.PORTALSHIPORDER_STATUS_EQ, 
/*  322 */           status);
/*      */     }
/*  324 */     String startDate1 = queryForm.getCreateDate1();
/*  325 */     String startDate2 = queryForm.getCreateDate2();
/*  326 */     if (startDate1 != null && startDate1.trim().length() != 0) {
/*  327 */       conditions.put(PortalShipOrderQueryCondition.CREATEDATE_GE, 
/*  328 */           startDate1);
/*      */     }
/*  330 */     if (startDate2 != null && startDate2.trim().length() != 0) {
/*  331 */       conditions.put(PortalShipOrderQueryCondition.CREATEDATE_LT, 
/*  332 */           startDate2);
/*      */     }
/*  334 */     String pocode = queryForm.getPocode();
/*  335 */     if (pocode != null && pocode.trim().length() != 0) {
/*  336 */       conditions.put(PortalShipOrderQueryCondition.PO_CODE_EQ, pocode);
/*      */     }
/*  338 */     String partCode = queryForm.getPart_code();
/*  339 */     if (partCode != null && partCode.trim().length() != 0) {
/*  340 */       conditions
/*  341 */         .put(PortalShipOrderQueryCondition.PART_CODE_EQ, partCode);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  349 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/*  350 */     PurchaseOrderInspectionPendingManager poipm = 
/*  351 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  352 */     PortalShipOrderManager portalShipOrderManager = 
/*  353 */       ServiceLocator.getPortalShipOrderManager(request);
/*  354 */     String shipOrderId = request.getParameter("id");
/*  355 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  356 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  357 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  358 */       .getPortalShipOrderItemListByOrderId(
/*  359 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  360 */     User user = getCurrentUser(request);
/*  361 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  362 */     request.setAttribute("x_portalShipOrderItemList", 
/*  363 */         portalShipOrderItemList);
/*  364 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/*  366 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  367 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  368 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPurchaseOrderActualReceipts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  374 */     PurchaseOrderInspectionPendingManager poipm = 
/*  375 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  376 */     PortalShipOrderManager portalShipOrderManager = 
/*  377 */       ServiceLocator.getPortalShipOrderManager(request);
/*  378 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/*  379 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  380 */     String shipOrderId = request.getParameter("id");
/*  381 */     String itemIds = request.getParameter("item");
/*  382 */     if (itemIds != "") {
/*  383 */       String[] arrays = itemIds.split(";"); byte b; int i; String[] arrayOfString1;
/*  384 */       for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/*  385 */         String[] item = array.split(",");
/*  386 */         String itemId = item[0];
/*  387 */         String actual = item[1];
/*  388 */         PortalShipOrderItem portalShipOrderItem = portalShipOrderItemManager
/*  389 */           .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(itemId)));
/*      */         
/*  391 */         portalShipOrderItem.setActual_qty(new BigDecimal(actual));
/*      */         
/*  393 */         portalShipOrderItemManager
/*  394 */           .updatePortalShipOrderItem(portalShipOrderItem); b++; }
/*      */     
/*      */     } 
/*  397 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  398 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  399 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  400 */       .getPortalShipOrderItemListByOrderId(
/*  401 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  402 */     Supplier supplier = ((PortalShipOrderItem)portalShipOrderItemList.get(0)).getPoipItem()
/*  403 */       .getPoip_number().getSupplier();
/*  404 */     request.setAttribute("supplier", supplier);
/*  405 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  406 */     request.setAttribute("x_portalShipOrderItemList", 
/*  407 */         portalShipOrderItemList);
/*  408 */     request.getSession().setAttribute("path", request.getContextPath());
/*  409 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  410 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  411 */     return mapping.findForward("page");
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
/*  428 */     String id = request.getParameter("id");
/*  429 */     PortalShipOrderManager portalShipOrderManager = 
/*  430 */       ServiceLocator.getPortalShipOrderManager(request);
/*  431 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  432 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/*  433 */     if (portalShipOrder == null)
/*  434 */       throw new ActionException("portalShipOrder.notFound", id); 
/*  435 */     return portalShipOrder;
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
/*  451 */     PortalShipOrderItemManager itemsmanager = 
/*  452 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  453 */     String ids = request.getParameter("id");
/*  454 */     String[] id = ids.split(";"); byte b; int i; String[] arrayOfString1;
/*  455 */     for (i = (arrayOfString1 = id).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/*  456 */       PortalShipOrderItem item = itemsmanager
/*  457 */         .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(str)));
/*  458 */       item.setPrintStatus(YesNo.YES);
/*  459 */       itemsmanager.updatePortalShipOrderItem(item);
/*      */       b++; }
/*      */     
/*  462 */     return new ActionForward("listPurchaseOrderActualReceipts.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  470 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/*  471 */     PurchaseOrderInspectionPendingManager poipm = 
/*  472 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  473 */     PortalShipOrderManager portalShipOrderManager = 
/*  474 */       ServiceLocator.getPortalShipOrderManager(request);
/*  475 */     String shipOrderId = request.getParameter("id");
/*  476 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  477 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  478 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/*  479 */       .getPortalShipOrderItemListByOrderId(
/*  480 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/*  481 */     User user = getCurrentUser(request);
/*  482 */     String supplierCode = user.getLoginName();
/*  483 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/*  484 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/*  485 */     request.setAttribute("supplier", supplier);
/*  486 */     List<Box> resultList = portalShipOrderManager
/*  487 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/*  489 */     int totalCount = resultList.size();
/*      */     
/*  491 */     int pageCount = 0;
/*      */     
/*  493 */     int endNum = formBean.getPageSizeAsInt();
/*  494 */     if (endNum == -1) {
/*  495 */       endNum = resultList.size();
/*      */     }
/*      */     
/*  498 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/*  500 */     if (totalCount % endNum > 0) {
/*      */       
/*  502 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/*  505 */       pageCount = totalCount / endNum;
/*      */     } 
/*  507 */     if (totalCount > 0) {
/*  508 */       if (startNum <= pageCount) {
/*  509 */         if (startNum == 1) {
/*      */           
/*  511 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/*  515 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/*  519 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/*  521 */           int toIndex = startNum * endNum;
/*      */           
/*  523 */           if ((totalCount - toIndex) % endNum >= 0) {
/*  524 */             toIndex = startNum * endNum;
/*      */           } else {
/*  526 */             toIndex = (startNum - 1) * endNum + 
/*  527 */               totalCount % endNum;
/*      */           } 
/*  529 */           if (totalCount >= toIndex) {
/*  530 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/*  534 */         resultList = null;
/*      */       } 
/*      */     }
/*  537 */     if (formBean.isFirstInit()) {
/*  538 */       formBean.init(totalCount);
/*      */     } else {
/*  540 */       formBean.init();
/*      */     } 
/*  542 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/*  543 */     request.setAttribute("X_DATE", format.format(new Date()));
/*  544 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  545 */     request.setAttribute("x_portalShipOrderItemList", 
/*  546 */         portalShipOrderItemList);
/*  547 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/*  548 */     int size = resultList.size();
/*  549 */     for (Box poIpiBox : resultList) {
/*  550 */       if (poIpiBox.getSupplierBatch() != "" && 
/*  551 */         poIpiBox.getSupplierBatch() != null) {
/*  552 */         size--;
/*  553 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/*  556 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*  557 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/*  559 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  560 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  561 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  567 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/*  568 */     PortalShipOrderManager portalShipOrderManager = 
/*  569 */       ServiceLocator.getPortalShipOrderManager(request);
/*  570 */     String id = request.getParameter("id");
/*  571 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/*  572 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/*  573 */     PurchaseOrderInspectionPendingManager poipm = 
/*  574 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  575 */     List<Box> resultList = portalShipOrderManager
/*  576 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*  577 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/*  579 */     String str = request.getParameter("supplierBatchStr");
/*  580 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/*  582 */     if ((str != null || poipBoxIds != null) && (
/*  583 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/*  584 */       String[] supplierBatchList = str.split(",");
/*  585 */       String[] poipBoxIdList = poipBoxIds.split(",");
/*  586 */       if (supplierBatchList.length > 0) {
/*  587 */         for (int i = 0; i < supplierBatchList.length; i++) {
/*  588 */           if (poipBoxIdList[i].length() > 0) {
/*  589 */             Integer poipBoxId = 
/*  590 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/*  591 */             String supplierBatch = supplierBatchList[i];
/*  592 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/*  593 */             if (supplierBatch != null || supplierBatch != "") {
/*  594 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/*  596 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/*  598 */               poIpiBox.setSupplierBatch("");
/*  599 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  609 */     int totalCount = resultList.size();
/*      */     
/*  611 */     int pageCount = 0;
/*      */     
/*  613 */     int endNum = formBean.getPageSizeAsInt();
/*  614 */     if (endNum == -1) {
/*  615 */       endNum = resultList.size();
/*      */     }
/*      */     
/*  618 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/*  620 */     if (totalCount % endNum > 0) {
/*      */       
/*  622 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/*  625 */       pageCount = totalCount / endNum;
/*      */     } 
/*  627 */     if (totalCount > 0) {
/*  628 */       if (startNum <= pageCount) {
/*  629 */         if (startNum == 1) {
/*      */           
/*  631 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/*  635 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/*  639 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/*  641 */           int toIndex = startNum * endNum;
/*      */           
/*  643 */           if ((totalCount - toIndex) % endNum >= 0) {
/*  644 */             toIndex = startNum * endNum;
/*      */           } else {
/*  646 */             toIndex = (startNum - 1) * endNum + 
/*  647 */               totalCount % endNum;
/*      */           } 
/*  649 */           if (totalCount >= toIndex) {
/*  650 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/*  654 */         resultList = null;
/*      */       } 
/*      */     }
/*  657 */     if (formBean.isFirstInit()) {
/*  658 */       formBean.init(totalCount);
/*      */     } else {
/*  660 */       formBean.init();
/*      */     } 
/*      */     
/*  663 */     request.setAttribute("x_supplierBatchStr", str);
/*  664 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/*  665 */     request.setAttribute("x_id", id);
/*  666 */     request.setAttribute("x_boxList", resultList);
/*  667 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  668 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward podeletePortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  674 */     PortalShipOrderManager portalShipOrderManager = 
/*  675 */       ServiceLocator.getPortalShipOrderManager(request);
/*  676 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  677 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  678 */     PurchaseOrderManager poManager = 
/*  679 */       ServiceLocator.getPurchaseOrderManager(request);
/*      */     try {
/*  681 */       PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*      */       
/*  683 */       System.out.println(portalShipOrder.getId() + "----");
/*  684 */       List<PortalShipOrderItem> itemlist = portalShipOrderManager
/*  685 */         .getPortalShipOrderItemListByOrderId(portalShipOrder
/*  686 */           .getId());
/*  687 */       for (PortalShipOrderItem portalShipOrderItem : itemlist) {
/*  688 */         PurchaseOrderInspectionPendingItem poipItem = poipManager
/*  689 */           .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
/*  690 */             .getPoipItem().getId());
/*  691 */         BigDecimal wBigDecimal = portalShipOrderItem
/*  692 */           .getDeliveryNumber();
/*      */         
/*  694 */         poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));
/*      */         
/*  696 */         poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);
/*      */         
/*  698 */         portalShipOrderManager
/*  699 */           .deletePurchaseOrderBox(portalShipOrderItem.getId());
/*  700 */         portalShipOrderManager
/*  701 */           .deletePortalShipOrderItem(portalShipOrderItem);
/*      */         
/*  703 */         Boolean isClose = poipManager
/*  704 */           .isclosePurchaseOrderInspectionPendingByItem(poipItem
/*  705 */             .getPoip_number());
/*  706 */         if (!isClose.booleanValue()) {
/*  707 */           poipItem.getPoip_number().setStatus(
/*  708 */               PurchaseOrderStatus.CLOSE);
/*  709 */           poipManager.updatePurchaseOrderInspectionPending(poipItem
/*  710 */               .getPoip_number());
/*      */         } 
/*      */       } 
/*      */       
/*  714 */       portalShipOrderManager.deletePortalShipOrder(portalShipOrder);
/*  715 */     } catch (Exception e) {
/*  716 */       e.printStackTrace();
/*  717 */       e.fillInStackTrace();
/*      */     } 
/*  719 */     return new ActionForward("listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward podeletePortalShipOrderTwoItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  726 */     PortalShipOrderManager portalShipOrderManager = 
/*  727 */       ServiceLocator.getPortalShipOrderManager(request);
/*  728 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  729 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  730 */     PurchaseOrderManager poManager = 
/*  731 */       ServiceLocator.getPurchaseOrderManager(request);
/*      */     try {
/*  733 */       PortalShipOrderItem portalShipOrderItem = portalShipOrderManager
/*  734 */         .getPortalShipOrderItem(Integer.valueOf(Integer.parseInt(request
/*  735 */               .getParameter("id"))));
/*  736 */       PurchaseOrderInspectionPendingItem poipItem = poipManager
/*  737 */         .getPurchaseOrderInspectionPendingItem(portalShipOrderItem
/*  738 */           .getPoipItem().getId());
/*  739 */       BigDecimal wBigDecimal = portalShipOrderItem.getDeliveryNumber();
/*      */       
/*  741 */       poipItem.setQtyOpen(poipItem.getQtyOpen().add(wBigDecimal));
/*      */       
/*  743 */       poipManager.updatePurchaseOrderInspectionPendingItem2(poipItem);
/*      */ 
/*      */       
/*  746 */       portalShipOrderManager.deletePurchaseOrderBox(portalShipOrderItem
/*  747 */           .getId());
/*      */       
/*  749 */       portalShipOrderManager
/*  750 */         .deletePortalShipOrderItem(portalShipOrderItem);
/*  751 */       poipItem.getPoip_number().setStatus(PurchaseOrderStatus.CLOSE);
/*  752 */       poipManager.updatePurchaseOrderInspectionPending(poipItem
/*  753 */           .getPoip_number());
/*      */     }
/*  755 */     catch (Exception e) {
/*  756 */       e.printStackTrace();
/*  757 */       e.fillInStackTrace();
/*      */     } 
/*  759 */     return new ActionForward("listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  766 */     PortalShipOrderManager portalShipOrderManager = 
/*  767 */       ServiceLocator.getPortalShipOrderManager(request);
/*  768 */     PurchaseOrderInspectionPendingManager poipManager = 
/*  769 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  770 */     PurchaseOrderManager poManager = 
/*  771 */       ServiceLocator.getPurchaseOrderManager(request);
/*  772 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*  773 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/*  775 */     List<Box> list = new ArrayList();
/*  776 */     List<Box> poipBoxList = null;
/*      */     try {
/*  778 */       if (!portalShipOrder.getStatus().equals(
/*  779 */           PortalShipOrderStatus.RECEIVE)) {
/*  780 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/*  781 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/*  783 */         portalShipOrderItemlist = portalShipOrderManager
/*  784 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/*  785 */             .getId());
/*  786 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/*  789 */           poipBoxList = portalShipOrderManager
/*  790 */             .getBoxList(portalShipOrderItem.getId());
/*  791 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/*  794 */     } catch (Exception e) {
/*  795 */       e.fillInStackTrace();
/*  796 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  799 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/*  800 */     request.setAttribute("x_portalShipOrderItemList", 
/*  801 */         portalShipOrderItemlist);
/*  802 */     request.setAttribute("x_portalShopOrderBoxList", list);
/*  803 */     int size = poipBoxList.size();
/*  804 */     for (Box poIpiBox : poipBoxList) {
/*  805 */       if (poIpiBox.getSupplierBatch() != "" && 
/*  806 */         poIpiBox.getSupplierBatch() != null) {
/*  807 */         size--;
/*  808 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/*  811 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/*  814 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  820 */     PortalShipOrderManager portalShipOrderManager = 
/*  821 */       ServiceLocator.getPortalShipOrderManager(request);
/*  822 */     String id = request.getParameter("id");
/*  823 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/*  824 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/*  827 */     return new ActionForward("viewPortalShipOrderTwo.do?id=" + 
/*  828 */         portalShipOrder.getId(), true);
/*      */   }
/*      */   
/*      */   public static void main(String[] str) throws Exception {
/*  832 */     System.out.println(MD5.getDigestString("root"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  838 */     PurchaseOrderInspectionPendingManager poip = 
/*  839 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  840 */     String string = request.getParameter("str");
/*  841 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */     
/*  843 */     String[] strings = string.split(",");
/*  844 */     List<Box> boxlist = new ArrayList<Box>();
/*  845 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/*  846 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/*  847 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/*  849 */       String strNo = box.getPsoItem().getPortalShipOrder().getCode();
/*  850 */       String beathNo = strNo.substring(strNo.length() - 9, strNo.length());
/*  851 */       box.setHuCodeOrderNumber(beathNo);
/*  852 */       boxlist.add(box);
/*      */       b++; }
/*      */     
/*  855 */     request.setAttribute("x_item", item);
/*  856 */     request.setAttribute("X_RESULTLIST", boxlist);
/*  857 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/*  859 */     request.setAttribute("path", request.getContextPath());
/*  860 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  866 */     PurchaseOrderInspectionPendingManager poip = 
/*  867 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  868 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/*  869 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/*  870 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  871 */     boolean bol = false;
/*  872 */     JSONObject object = new JSONObject();
/*  873 */     String ids = request.getParameter("ids");
/*  874 */     String[] idr = ids.split(";");
/*  875 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/*  876 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  877 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/*  878 */       box.setIsPrint(YesNo.YES);
/*  879 */       manager.updateBox(box);
/*  880 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/*  883 */     bol = true;
/*  884 */     object.put("object", Boolean.valueOf(bol));
/*  885 */     object.put("id", idInteger);
/*  886 */     PrintWriter print = response.getWriter();
/*  887 */     print.print(object);
/*  888 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  897 */     String ids = request.getParameter("id");
/*  898 */     PortalShipOrderManager manager = 
/*  899 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */ 
/*      */     
/*  902 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  903 */         Integer.valueOf(Integer.parseInt(ids)));
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
/*  917 */     if (order.getPrintDate() == null) {
/*  918 */       order.setPrintDate(new Date());
/*      */     }
/*  920 */     order.setIsPrint(YesNo.YES);
/*  921 */     manager.updatePortalShipOrder(order);
/*  922 */     DeliverMinuteSyncJob ss = 
/*  923 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  924 */     ss.startSynOne(order);
/*  925 */     return new ActionForward("viewPortalShipOrderTwo.do?id=" + 
/*  926 */         Integer.parseInt(ids), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePirntPortalShipOrderByJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  932 */     JSONObject object = new JSONObject();
/*  933 */     boolean bol = false;
/*  934 */     String ids = request.getParameter("id");
/*  935 */     PortalShipOrderManager manager = 
/*  936 */       ServiceLocator.getPortalShipOrderManager(request);
/*  937 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  938 */         Integer.valueOf(Integer.parseInt(ids)));
/*  939 */     if (order.getPrintDate() == null) {
/*  940 */       order.setPrintDate(new Date());
/*      */     }
/*  942 */     order.setIsPrint(YesNo.YES);
/*  943 */     manager.updatePortalShipOrder(order);
/*  944 */     DeliverMinuteSyncJob ss = 
/*  945 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  946 */     ss.startSynOne(order);
/*  947 */     bol = true;
/*  948 */     PrintWriter print = response.getWriter();
/*  949 */     object.put("str", Boolean.valueOf(bol));
/*  950 */     print.print(object);
/*  951 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePirntPortalShipOrderByNpoPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  959 */     JSONObject object = new JSONObject();
/*  960 */     boolean bol = false;
/*  961 */     String ids = request.getParameter("id");
/*  962 */     PortalShipOrderManager manager = 
/*  963 */       ServiceLocator.getPortalShipOrderManager(request);
/*  964 */     PortalShipOrder order = manager.getPortalShipOrder(
/*  965 */         Integer.valueOf(Integer.parseInt(ids)));
/*  966 */     if (order.getPrintDate() == null) {
/*  967 */       order.setPrintDate(new Date());
/*      */     }
/*  969 */     order.setIsPrint(YesNo.YES);
/*  970 */     manager.updatePortalShipOrder(order);
/*  971 */     DeliverMinuteSyncJob ss = 
/*  972 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  973 */     ss.startSynOne(order);
/*  974 */     bol = true;
/*  975 */     PrintWriter print = response.getWriter();
/*  976 */     object.put("str", Boolean.valueOf(bol));
/*  977 */     print.print(object);
/*  978 */     return null;
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
/* 1016 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1022 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1023 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1024 */     PortalShipOrderManager portalShipOrderManager = 
/* 1025 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1026 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 1028 */     PortalShipOrder portalShipOrder = null;
/* 1029 */     SupplierManager supplierManager = 
/* 1030 */       ServiceLocator.getSupplierManager(request);
/* 1031 */     String supplierCode = request.getParameter("supplierCode");
/*      */     
/* 1033 */     Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
/* 1034 */     if (supplier != null) {
/*      */       try {
/* 1036 */         String[] poipItemIdsList = request
/* 1037 */           .getParameterValues("poipItemIds");
/* 1038 */         String[] deliveryNumberList = request
/* 1039 */           .getParameterValues("deliveryNumbers");
/* 1040 */         String createType = request.getParameter("createType");
/* 1041 */         for (int i = 0; i < poipItemIdsList.length; i++) {
/* 1042 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */             
/* 1046 */             if (portalShipOrder == null) {
/* 1047 */               portalShipOrder = new PortalShipOrder();
/* 1048 */               Site site = getCurrentUser(request)
/* 1049 */                 .getPrimarySite();
/* 1050 */               User requestor = getCurrentUser(request);
/* 1051 */               SimpleDateFormat sdf = new SimpleDateFormat(
/* 1052 */                   "yyyy/MM/dd");
/* 1053 */               String arrivalDate = queryForm.getReceivingDate();
/* 1054 */               Date arrDate = null;
/* 1055 */               if (arrivalDate != null && 
/* 1056 */                 arrivalDate.trim().length() != 0) {
/* 1057 */                 arrDate = sdf.parse(arrivalDate);
/*      */               }
/* 1059 */               portalShipOrder.setType(Integer.valueOf(1));
/* 1060 */               portalShipOrder.setCreateType(createType);
/* 1061 */               portalShipOrder.setSupplier(supplier);
/*      */               
/* 1063 */               portalShipOrderManager.insertPortalShipOrderSupplier(
/* 1064 */                   portalShipOrder, site, supplier, arrDate);
/*      */             } 
/* 1066 */             String poipItem = poipItemIdsList[i];
/* 1067 */             BigDecimal deliveryNumber = new BigDecimal(
/* 1068 */                 deliveryNumberList[i]);
/*      */             
/* 1070 */             PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 1071 */               .getPurchaseOrderInspectionPendingItem(
/* 1072 */                 Integer.valueOf(Integer.parseInt(poipItem)));
/*      */             
/* 1074 */             BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 1075 */               .getQtyOpen();
/* 1076 */             purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 1077 */                 .subtract(deliveryNumber));
/* 1078 */             poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */             
/* 1081 */             Boolean isClose = poipm
/* 1082 */               .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 1083 */                 .getPoip_number());
/* 1084 */             if (isClose.booleanValue()) {
/* 1085 */               purchaseOrderInspectionPendingItem1.getPoip_number()
/* 1086 */                 .setStatus(PurchaseOrderStatus.WAIT);
/* 1087 */               poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 1088 */                   .getPoip_number());
/*      */             } 
/*      */             
/* 1091 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */             
/* 1093 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 1094 */             portalShipOrderItem
/* 1095 */               .setPart(purchaseOrderInspectionPendingItem1
/* 1096 */                 .getItemNumber());
/* 1097 */             portalShipOrderItem
/* 1098 */               .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 1099 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 1100 */             portalShipOrderItem
/* 1101 */               .setQty_std(purchaseOrderInspectionPendingItem1
/* 1102 */                 .getQty_std());
/* 1103 */             portalShipOrderManager
/* 1104 */               .insertPortalShipOrderItem(portalShipOrderItem);
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
/* 1115 */         PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 1116 */           .getPurchaseOrderInspectionPendingItem(
/* 1117 */             Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 1118 */         if (!"T".equals(purchaseOrderInspectionPendingItem
/* 1119 */             .getVd_promo())) {
/* 1120 */           portalShipOrderManager
/* 1121 */             .createPortalShipOrderIP(portalShipOrder);
/*      */         }
/* 1123 */       } catch (Exception e) {
/* 1124 */         e.fillInStackTrace();
/*      */       } 
/* 1126 */       return new ActionForward("/viewPortalShipOrderTwo.do?id=" + 
/* 1127 */           portalShipOrder.getId(), true);
/*      */     } 
/* 1129 */     return new ActionForward("/listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1136 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 1137 */     PurchaseOrderInspectionPendingManager fm = 
/* 1138 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1139 */     String idString = queryForm.getSelectPoipItemId();
/* 1140 */     String supplierCode = request.getParameter("supplierCode");
/* 1141 */     String[] ids = idString.split(";");
/* 1142 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 1144 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 1145 */         Integer.valueOf(0));
/* 1146 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 1147 */     String poNumber = queryForm.getPoip_number();
/* 1148 */     if (poNumber != null && !poNumber.equals("")) {
/* 1149 */       conditions.put(
/* 1150 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 1151 */           poNumber);
/*      */     }
/*      */     
/* 1154 */     String itemNumber = queryForm.getItemNumber();
/* 1155 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 1156 */       conditions.put(
/* 1157 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 1158 */           itemNumber);
/*      */     }
/*      */     
/* 1161 */     String endPoDate = queryForm.getDueDate();
/* 1162 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 1163 */       conditions.put(
/* 1164 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 1165 */           endPoDate);
/*      */     }
/* 1167 */     String status = queryForm.getStatus();
/* 1168 */     if (status != null && !status.equals("")) {
/* 1169 */       conditions.put(
/* 1170 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 1171 */           status);
/*      */     } else {
/* 1173 */       conditions.put(
/* 1174 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 1176 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 1177 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/* 1178 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 1179 */       supplier != null)
/*      */     {
/* 1181 */       conditions
/* 1182 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 1183 */           supplier.getId());
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
/* 1199 */     if (queryForm.isFirstInit()) {
/* 1200 */       queryForm
/* 1201 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 1203 */       queryForm.init();
/*      */     } 
/* 1205 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 1206 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 1207 */         queryForm.getPageNoAsInt(), 
/* 1208 */         queryForm.getPageSizeAsInt(), 
/* 1209 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 1210 */         queryForm.isDescend());
/* 1211 */     if (ids.length > 0) {
/* 1212 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 1213 */         Integer itemId = pendingItem.getId();
/* 1214 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 1216 */         for (int i = 0; i < ids.length; i++) {
/* 1217 */           if (!ids[i].equals("") && 
/* 1218 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 1219 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1224 */         if (isClose.booleanValue()) {
/* 1225 */           pendingItem.setChecked("checked");
/*      */         }
/*      */       } 
/*      */     }
/* 1229 */     request.setAttribute("X_RESULTLIST", result);
/* 1230 */     request.setAttribute("X_YESNOLIST", 
/* 1231 */         PersistentEnum.getEnumList(YesNo.class));
/* 1232 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 1233 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 1234 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     try {
/* 1242 */       PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 1243 */       PortalShipOrderManager fm = 
/* 1244 */         ServiceLocator.getPortalShipOrderManager(request);
/* 1245 */       if (queryForm.getOrder() == "") {
/* 1246 */         queryForm.setOrder("id");
/* 1247 */         queryForm.setDescend(true);
/*      */       } 
/*      */       
/* 1250 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1251 */       getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */       
/* 1254 */       getConditionsFrom(queryForm, conditions);
/*      */       
/* 1256 */       conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, 
/* 1257 */           "NJIT_NPO");
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
/* 1270 */       String exportType = queryForm.getExportType();
/* 1271 */       if (exportType != null && exportType.length() > 0) {
/* 1272 */         List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 1273 */             false);
/* 1274 */         int index = SessionTempFile.createNewTempFile(request);
/* 1275 */         String fileName = "PortalShipOrder";
/* 1276 */         String suffix = ExportUtil.export(
/* 1277 */             exportType, 
/* 1278 */             data, 
/* 1279 */             request, 
/* 1280 */             new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1281 */                 request)), new Exportable()
/*      */             {
/*      */               public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */               {
/* 1285 */                 MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 1286 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1287 */                       "SO.No1"));
/* 1288 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1289 */                       "supplier1"));
/* 1290 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1291 */                       "supplier.code1"));
/* 1292 */                 row.add("创建时间");
/* 1293 */                 row.add("发货时间");
/* 1294 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1295 */                       "salesOrderItem.isprintlabels"));
/* 1296 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1297 */                       "salesOrderItem.issync"));
/* 1298 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1299 */                       "portalShipOrder.status"));
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*      */               public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1305 */                 row.add(BeanUtils.getProperty(data, "code"));
/* 1306 */                 row.add(BeanUtils.getProperty(data, 
/* 1307 */                       "site.supplier.name"));
/* 1308 */                 row.add(BeanUtils.getProperty(data, 
/* 1309 */                       "site.supplier.code"));
/*      */ 
/*      */                 
/* 1312 */                 row.add(BeanUtils.getProperty(data, 
/* 1313 */                       "createDate"));
/* 1314 */                 row.add(BeanUtils.getProperty(data, 
/* 1315 */                       "arrivalDate"));
/*      */                 
/* 1317 */                 PortalShipOrder order = (PortalShipOrder)data;
/* 1318 */                 if (order.getIsPrint().equals(YesNo.YES)) {
/* 1319 */                   row.add("已打印");
/* 1320 */                 } else if (order.getIsPrint().equals(YesNo.NO)) {
/* 1321 */                   row.add("未打印");
/*      */                 } else {
/* 1323 */                   row.add("未打印");
/*      */                 } 
/* 1325 */                 if (order.getIsSync() != null) {
/* 1326 */                   if (order.getIsSync().equals("0")) {
/* 1327 */                     row.add("未同步");
/* 1328 */                   } else if (order.getIsSync().equals("1")) {
/* 1329 */                     row.add("已同步");
/* 1330 */                   } else if (order.getIsSync().equals("")) {
/* 1331 */                     row.add("未同步");
/*      */                   } 
/*      */                 } else {
/* 1334 */                   row.add("未同步");
/*      */                 } 
/* 1336 */                 String locale = PortalShipOrderActiony.this.getCurrentUser(request)
/* 1337 */                   .getLocale();
/* 1338 */                 if ("en".equals(locale)) {
/* 1339 */                   row.add(BeanUtils.getProperty(data, 
/* 1340 */                         "status.engShortDescription"));
/*      */                 } else {
/* 1342 */                   row.add(BeanUtils.getProperty(data, 
/* 1343 */                         "status.chnShortDescription"));
/*      */                 } 
/*      */               }
/*      */             });
/*      */         
/* 1348 */         return new ActionForward("download/" + index + "/" + 
/* 1349 */             URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 1350 */             true);
/*      */       } 
/* 1352 */       if (queryForm.isFirstInit()) {
/* 1353 */         queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */       } else {
/* 1355 */         queryForm.init();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1361 */       List result = fm.getPortalShipOrderList(conditions, 
/* 1362 */           queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1363 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 1364 */       request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1365 */           PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1366 */       request.setAttribute("x_statusList", 
/* 1367 */           PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 1368 */       request.setAttribute("X_RESULTLIST", result);
/* 1369 */       request.setAttribute("x_selType", Integer.valueOf(169));
/* 1370 */     } catch (Exception e) {
/*      */       
/* 1372 */       e.printStackTrace();
/*      */     } 
/* 1374 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderWrongSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     try {
/* 1382 */       PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 1383 */       PortalShipOrderManager fm = 
/* 1384 */         ServiceLocator.getPortalShipOrderManager(request);
/* 1385 */       if (queryForm.getOrder() == "") {
/* 1386 */         queryForm.setOrder("createDate");
/* 1387 */         queryForm.setDescend(true);
/*      */       } 
/*      */       
/* 1390 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1391 */       getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */       
/* 1394 */       getConditionsFrom(queryForm, conditions);
/*      */       
/* 1396 */       conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, 
/* 1397 */           "NJIT_NPO");
/* 1398 */       User user = getCurrentUser(request);
/* 1399 */       SupplierManager supplierManager = 
/* 1400 */         ServiceLocator.getSupplierManager(request);
/* 1401 */       Supplier supplier = supplierManager.getSupplierByCode(user
/* 1402 */           .getLoginName());
/* 1403 */       if (supplier != null) {
/* 1404 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, 
/* 1405 */             supplier.getId());
/*      */       } else {
/* 1407 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
/*      */       } 
/*      */       
/* 1410 */       String exportType = queryForm.getExportType();
/* 1411 */       if (exportType != null && exportType.length() > 0) {
/* 1412 */         List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 1413 */             false);
/* 1414 */         int index = SessionTempFile.createNewTempFile(request);
/* 1415 */         String fileName = "PortalShipOrder";
/* 1416 */         String suffix = ExportUtil.export(
/* 1417 */             exportType, 
/* 1418 */             data, 
/* 1419 */             request, 
/* 1420 */             new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1421 */                 request)), new Exportable()
/*      */             {
/*      */               public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */               {
/* 1425 */                 MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 1426 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1427 */                       "SO.No1"));
/* 1428 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1429 */                       "supplier1"));
/* 1430 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1431 */                       "supplier.code1"));
/* 1432 */                 row.add("创建时间");
/* 1433 */                 row.add("发货时间");
/* 1434 */                 row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 1435 */                       "portalShipOrder.status"));
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*      */               public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1441 */                 row.add(BeanUtils.getProperty(data, "code"));
/* 1442 */                 row.add(BeanUtils.getProperty(data, 
/* 1443 */                       "site.supplier.name"));
/* 1444 */                 row.add(BeanUtils.getProperty(data, 
/* 1445 */                       "site.supplier.code"));
/* 1446 */                 row.add("");
/* 1447 */                 row.add(BeanUtils.getProperty(data, 
/* 1448 */                       "createDate"));
/* 1449 */                 String locale = PortalShipOrderActiony.this.getCurrentUser(request)
/* 1450 */                   .getLocale();
/* 1451 */                 if ("en".equals(locale)) {
/* 1452 */                   row.add(BeanUtils.getProperty(data, 
/* 1453 */                         "status.engShortDescription"));
/*      */                 } else {
/* 1455 */                   row.add(BeanUtils.getProperty(data, 
/* 1456 */                         "status.chnShortDescription"));
/*      */                 } 
/*      */               }
/*      */             });
/* 1460 */         return new ActionForward("download/" + index + "/" + 
/* 1461 */             URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 1462 */             true);
/*      */       } 
/* 1464 */       if (queryForm.isFirstInit()) {
/* 1465 */         queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */       } else {
/* 1467 */         queryForm.init();
/*      */       } 
/* 1469 */       List result = fm.getPortalShipOrderList(conditions, 
/* 1470 */           queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1471 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/* 1472 */           queryForm.isDescend());
/* 1473 */       request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1474 */           PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1475 */       request.setAttribute("x_statusList", 
/* 1476 */           PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 1477 */       request.setAttribute("X_RESULTLIST", result);
/* 1478 */       request.setAttribute("x_selType", Integer.valueOf(177));
/* 1479 */     } catch (Exception e) {
/*      */       
/* 1481 */       e.printStackTrace();
/*      */     } 
/* 1483 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1489 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/*      */     
/* 1491 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1492 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1493 */     PortalShipOrderManager portalShipOrderManager = 
/* 1494 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1495 */     String shipOrderId = request.getParameter("id");
/* 1496 */     String GrantedSite = request.getParameter("GrantedSite");
/* 1497 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1498 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1499 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1500 */       .getPortalShipOrderItemListByOrderId(
/* 1501 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1502 */     User user = getCurrentUser(request);
/* 1503 */     String supplierCode = user.getLoginName();
/* 1504 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 1505 */     Supplier supplier = manager.getSupplierByCode(supplierCode);
/* 1506 */     request.setAttribute("supplier", supplier);
/* 1507 */     List<Box> resultList = portalShipOrderManager
/* 1508 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/* 1510 */     int totalCount = resultList.size();
/*      */     
/* 1512 */     int pageCount = 0;
/*      */     
/* 1514 */     int endNum = formBean.getPageSizeAsInt();
/* 1515 */     if (endNum == -1) {
/* 1516 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 1519 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 1521 */     if (totalCount % endNum > 0) {
/*      */       
/* 1523 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 1526 */       pageCount = totalCount / endNum;
/*      */     } 
/* 1528 */     if (totalCount > 0) {
/* 1529 */       if (startNum <= pageCount) {
/* 1530 */         if (startNum == 1) {
/*      */           
/* 1532 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 1536 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 1540 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 1542 */           int toIndex = startNum * endNum;
/*      */           
/* 1544 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 1545 */             toIndex = startNum * endNum;
/*      */           } else {
/* 1547 */             toIndex = (startNum - 1) * endNum + 
/* 1548 */               totalCount % endNum;
/*      */           } 
/* 1550 */           if (totalCount >= toIndex) {
/* 1551 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 1555 */         resultList = null;
/*      */       } 
/*      */     }
/* 1558 */     String exportType = formBean.getExportType();
/* 1559 */     if (exportType != null && exportType.length() > 0) {
/* 1560 */       int index = SessionTempFile.createNewTempFile(request);
/* 1561 */       String fileName = "PortalShipOrderBoxItem";
/* 1562 */       String suffix = ExportUtil.export(
/* 1563 */           exportType, 
/* 1564 */           resultList, 
/* 1565 */           request, 
/* 1566 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1567 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 1570 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 1571 */               row.add("箱单号");
/* 1572 */               row.add("供应商批次");
/* 1573 */               row.add("组件/物料编码");
/* 1574 */               row.add("品名");
/* 1575 */               row.add("包装箱容量");
/* 1576 */               row.add("批次数量");
/* 1577 */               row.add("发货数量");
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1582 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 1583 */               row.add(
/* 1584 */                   BeanUtils.getProperty(data, "supplierBatch"));
/* 1585 */               row.add(BeanUtils.getProperty(data, 
/* 1586 */                     "psoItem.poipItem.itemNumber.id"));
/* 1587 */               row.add(BeanUtils.getProperty(data, 
/* 1588 */                     "psoItem.poipItem.itemNumber.dpiNo"));
/* 1589 */               row.add(BeanUtils.getProperty(data, 
/* 1590 */                     "psoItem.qty_std"));
/* 1591 */               row.add("");
/* 1592 */               row.add(BeanUtils.getProperty(data, "number"));
/* 1593 */               row.add(BeanUtils.getProperty(data, 
/* 1594 */                     "isPrint.chnShortDescription"));
/*      */             }
/*      */           });
/* 1597 */       return new ActionForward("download/" + index + "/" + 
/* 1598 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1600 */     if (formBean.isFirstInit()) {
/* 1601 */       formBean.init(totalCount);
/*      */     } else {
/* 1603 */       formBean.init();
/*      */     } 
/*      */     
/* 1606 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1607 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1608 */         portalShipOrderItemList);
/* 1609 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/* 1610 */     int size = resultList.size();
/* 1611 */     for (Box poIpiBox : resultList) {
/* 1612 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 1613 */         poIpiBox.getSupplierBatch() != null) {
/* 1614 */         size--;
/* 1615 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 1618 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/* 1619 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/* 1621 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 1622 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 1623 */     request.setAttribute("X_GRANTEDSITE", GrantedSite);
/* 1624 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPortalShipOrderViewWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1630 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 1631 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1632 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1633 */     PortalShipOrderManager portalShipOrderManager = 
/* 1634 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1635 */     String shipOrderId = request.getParameter("id");
/* 1636 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1637 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1638 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1639 */       .getPortalShipOrderItemListByOrderId(
/* 1640 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1641 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 1642 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1643 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1644 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1645 */         portalShipOrderItemList);
/* 1646 */     request.getSession().setAttribute("path", request.getContextPath());
/* 1647 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPortalShipOrderViewProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1653 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 1654 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1655 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1656 */     PortalShipOrderManager portalShipOrderManager = 
/* 1657 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1658 */     String shipOrderId = request.getParameter("id");
/* 1659 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1660 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1661 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 1662 */       .getPortalShipOrderItemListByOrderId(
/* 1663 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 1664 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 1665 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1666 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1667 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1668 */         portalShipOrderItemList);
/* 1669 */     request.getSession().setAttribute("path", request.getContextPath());
/* 1670 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotListWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1676 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 1677 */     PortalShipOrderManager portalShipOrderManager = 
/* 1678 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1679 */     String id = request.getParameter("id");
/* 1680 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 1681 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 1682 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1683 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1684 */     List<Box> resultList = portalShipOrderManager
/* 1685 */       .getBoxByShipOrderId(portalShipOrder.getId());
/* 1686 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/* 1688 */     String str = request.getParameter("supplierBatchStr");
/* 1689 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/* 1691 */     if ((str != null || poipBoxIds != null) && (
/* 1692 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/* 1693 */       String[] supplierBatchList = str.split(",");
/* 1694 */       String[] poipBoxIdList = poipBoxIds.split(",");
/* 1695 */       if (supplierBatchList.length > 0) {
/* 1696 */         for (int i = 0; i < supplierBatchList.length; i++) {
/* 1697 */           if (poipBoxIdList[i].length() > 0) {
/* 1698 */             Integer poipBoxId = 
/* 1699 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/* 1700 */             String supplierBatch = supplierBatchList[i];
/* 1701 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/* 1702 */             if (supplierBatch != null || supplierBatch != "") {
/* 1703 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/* 1705 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/* 1707 */               poIpiBox.setSupplierBatch("");
/* 1708 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1718 */     int totalCount = resultList.size();
/*      */     
/* 1720 */     int pageCount = 0;
/*      */     
/* 1722 */     int endNum = formBean.getPageSizeAsInt();
/* 1723 */     if (endNum == -1) {
/* 1724 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 1727 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 1729 */     if (totalCount % endNum > 0) {
/*      */       
/* 1731 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 1734 */       pageCount = totalCount / endNum;
/*      */     } 
/* 1736 */     if (totalCount > 0) {
/* 1737 */       if (startNum <= pageCount) {
/* 1738 */         if (startNum == 1) {
/*      */           
/* 1740 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 1744 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 1748 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 1750 */           int toIndex = startNum * endNum;
/*      */           
/* 1752 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 1753 */             toIndex = startNum * endNum;
/*      */           } else {
/* 1755 */             toIndex = (startNum - 1) * endNum + 
/* 1756 */               totalCount % endNum;
/*      */           } 
/* 1758 */           if (totalCount >= toIndex) {
/* 1759 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 1763 */         resultList = null;
/*      */       } 
/*      */     }
/* 1766 */     if (formBean.isFirstInit()) {
/* 1767 */       formBean.init(totalCount);
/*      */     } else {
/* 1769 */       formBean.init();
/*      */     } 
/*      */     
/* 1772 */     request.setAttribute("x_supplierBatchStr", str);
/* 1773 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/* 1774 */     request.setAttribute("x_id", id);
/* 1775 */     request.setAttribute("x_boxList", resultList);
/* 1776 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1777 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1783 */     PortalShipOrderManager portalShipOrderManager = 
/* 1784 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1785 */     PurchaseOrderInspectionPendingManager poipManager = 
/* 1786 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1787 */     PurchaseOrderManager poManager = 
/* 1788 */       ServiceLocator.getPurchaseOrderManager(request);
/* 1789 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 1790 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/* 1792 */     List<Box> list = new ArrayList();
/* 1793 */     List<Box> poipBoxList = null;
/*      */     try {
/* 1795 */       if (!portalShipOrder.getStatus().equals(
/* 1796 */           PortalShipOrderStatus.RECEIVE)) {
/* 1797 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/* 1798 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/* 1800 */         portalShipOrderItemlist = portalShipOrderManager
/* 1801 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/* 1802 */             .getId());
/* 1803 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/* 1806 */           poipBoxList = portalShipOrderManager
/* 1807 */             .getBoxList(portalShipOrderItem.getId());
/* 1808 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/* 1811 */     } catch (Exception e) {
/* 1812 */       e.fillInStackTrace();
/* 1813 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1816 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 1817 */     request.setAttribute("x_portalShipOrderItemList", 
/* 1818 */         portalShipOrderItemlist);
/* 1819 */     request.setAttribute("x_portalShopOrderBoxList", list);
/* 1820 */     int size = poipBoxList.size();
/* 1821 */     for (Box poIpiBox : poipBoxList) {
/* 1822 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 1823 */         poIpiBox.getSupplierBatch() != null) {
/* 1824 */         size--;
/* 1825 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 1828 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/* 1831 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1837 */     PortalShipOrderManager portalShipOrderManager = 
/* 1838 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1839 */     String id = request.getParameter("id");
/* 1840 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 1841 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/* 1844 */     return new ActionForward("viewPortalShipOrderTwoWrong.do?id=" + 
/* 1845 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReportWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1851 */     PurchaseOrderInspectionPendingManager poip = 
/* 1852 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1853 */     String string = request.getParameter("str");
/* 1854 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */     
/* 1856 */     String[] strings = string.split(",");
/* 1857 */     List<Box> boxlist = new ArrayList<Box>();
/* 1858 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 1859 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 1860 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/* 1862 */       String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
/* 1863 */       if (beatchNO != null && !"".equals(beatchNO)) {
/* 1864 */         String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
/* 1865 */         box.setHuCodeOrderNumber(baetchNo);
/*      */       } 
/* 1867 */       boxlist.add(box); b++; }
/*      */     
/* 1869 */     request.setAttribute("x_item", item);
/* 1870 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 1871 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/* 1873 */     request.setAttribute("path", request.getContextPath());
/* 1874 */     return mapping.findForward("page");
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
/* 1888 */     PurchaseOrderInspectionPendingManager poip = 
/* 1889 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1890 */     String string = request.getParameter("str");
/* 1891 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/* 1892 */     String[] strings = string.split(",");
/* 1893 */     List<Box> boxlist = new ArrayList<Box>();
/* 1894 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 1895 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 1896 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/*      */       
/* 1898 */       String beatchNO = box.getPsoItem().getPortalShipOrder().getCode();
/* 1899 */       if (beatchNO != null && !"".equals(beatchNO)) {
/* 1900 */         String baetchNo = beatchNO.substring(beatchNO.length() - 9, beatchNO.length());
/* 1901 */         box.setHuCodeOrderNumber(baetchNo);
/*      */       } 
/* 1903 */       boxlist.add(box); b++; }
/*      */     
/* 1905 */     request.setAttribute("x_item", item);
/* 1906 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 1907 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 1908 */     request.setAttribute("path", request.getContextPath());
/* 1909 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItemsWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1915 */     PurchaseOrderInspectionPendingManager poip = 
/* 1916 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1917 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/* 1918 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 1919 */     JSONObject object = new JSONObject();
/* 1920 */     boolean bol = false;
/* 1921 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1922 */     String ids = request.getParameter("ids");
/* 1923 */     String[] idr = ids.split(";");
/* 1924 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/* 1925 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 1926 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 1927 */       box.setIsPrint(YesNo.YES);
/* 1928 */       manager.updateBox(box);
/* 1929 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/* 1932 */     bol = true;
/* 1933 */     PrintWriter print = response.getWriter();
/* 1934 */     object.put("str", Boolean.valueOf(bol));
/* 1935 */     print.print(object);
/* 1936 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObjectWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1945 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
/* 1946 */     PurchaseOrderInspectionPendingQueryForm pendingQueryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/* 1947 */     pendingQueryForm.setReceivingDate(simpleDateFormat.format(new Date()));
/*      */     
/* 1949 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1955 */     PurchaseOrderInspectionPendingManager poipm = 
/* 1956 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1957 */     PortalShipOrderManager portalShipOrderManager = 
/* 1958 */       ServiceLocator.getPortalShipOrderManager(request);
/* 1959 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 1961 */     PortalShipOrder portalShipOrder = null;
/*      */     
/*      */     try {
/* 1964 */       String[] poipItemIdsList = request
/* 1965 */         .getParameterValues("poipItemIds");
/* 1966 */       String[] deliveryNumberList = request
/* 1967 */         .getParameterValues("deliveryNumbers");
/* 1968 */       for (int i = 0; i < poipItemIdsList.length; i++) {
/* 1969 */         if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */           
/* 1973 */           if (portalShipOrder == null) {
/* 1974 */             portalShipOrder = new PortalShipOrder();
/* 1975 */             Site site = getCurrentUser(request).getPrimarySite();
/* 1976 */             User requestor = getCurrentUser(request);
/* 1977 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 1978 */             String arrivalDate = queryForm.getReceivingDate();
/* 1979 */             Date arrDate = null;
/* 1980 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 1981 */               arrDate = sdf.parse(arrivalDate);
/*      */             }
/* 1983 */             portalShipOrder.setType(Integer.valueOf(2));
/* 1984 */             portalShipOrderManager.insertPortalShipOrder(
/* 1985 */                 portalShipOrder, site, requestor, arrDate);
/*      */           } 
/*      */           
/* 1988 */           String poipItem = poipItemIdsList[i];
/* 1989 */           BigDecimal deliveryNumber = new BigDecimal(
/* 1990 */               deliveryNumberList[i]);
/*      */           
/* 1992 */           PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 1993 */             .getPurchaseOrderInspectionPendingItem(
/* 1994 */               Integer.valueOf(Integer.parseInt(poipItem)));
/*      */           
/* 1996 */           BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 1997 */             .getQtyOpen();
/* 1998 */           purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 1999 */               .subtract(deliveryNumber));
/* 2000 */           poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */           
/* 2003 */           Boolean isClose = poipm
/* 2004 */             .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 2005 */               .getPoip_number());
/* 2006 */           if (isClose.booleanValue()) {
/* 2007 */             purchaseOrderInspectionPendingItem1.getPoip_number()
/* 2008 */               .setStatus(PurchaseOrderStatus.WAIT);
/* 2009 */             poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 2010 */                 .getPoip_number());
/*      */           } 
/*      */           
/* 2013 */           PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */           
/* 2015 */           portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2016 */           portalShipOrderItem
/* 2017 */             .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 2018 */           portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2019 */           portalShipOrderItem
/* 2020 */             .setQty_std(purchaseOrderInspectionPendingItem1
/* 2021 */               .getQty_std());
/* 2022 */           portalShipOrderManager
/* 2023 */             .insertPortalShipOrderItem(portalShipOrderItem);
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
/* 2034 */       PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 2035 */         .getPurchaseOrderInspectionPendingItem(
/* 2036 */           Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 2037 */       if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
/* 2038 */         portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */       }
/* 2040 */     } catch (Exception e) {
/* 2041 */       e.fillInStackTrace();
/*      */     } 
/* 2043 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + 
/* 2044 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwoWrong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2051 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2052 */     PurchaseOrderInspectionPendingManager fm = 
/* 2053 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2054 */     String idString = queryForm.getSelectPoipItemId();
/* 2055 */     String[] ids = idString.split(";");
/* 2056 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 2058 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 2059 */         Integer.valueOf(0));
/* 2060 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2061 */     String poNumber = queryForm.getPoip_number();
/* 2062 */     if (poNumber != null && !poNumber.equals("")) {
/* 2063 */       conditions.put(
/* 2064 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 2065 */           poNumber);
/*      */     }
/*      */     
/* 2068 */     String itemNumber = queryForm.getItemNumber();
/* 2069 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 2070 */       conditions.put(
/* 2071 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 2072 */           itemNumber);
/*      */     }
/*      */     
/* 2075 */     String endPoDate = queryForm.getDueDate();
/* 2076 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 2077 */       conditions.put(
/* 2078 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 2079 */           endPoDate);
/*      */     }
/* 2081 */     String status = queryForm.getStatus();
/* 2082 */     if (status != null && !status.equals("")) {
/* 2083 */       conditions.put(
/* 2084 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 2085 */           status);
/*      */     } else {
/* 2087 */       conditions.put(
/* 2088 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 2090 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 2091 */     User user = getCurrentUser(request);
/* 2092 */     Supplier supplier = manager.getSupplierByCode(user.getLoginName());
/* 2093 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 2094 */       supplier != null) {
/*      */       
/* 2096 */       conditions
/* 2097 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2098 */           supplier.getId());
/*      */     } else {
/* 2100 */       conditions
/* 2101 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2102 */           Integer.valueOf(0));
/*      */     } 
/* 2104 */     conditions.put(
/* 2105 */         PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(3));
/* 2106 */     if (queryForm.isFirstInit()) {
/* 2107 */       queryForm
/* 2108 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 2110 */       queryForm.init();
/*      */     } 
/* 2112 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2113 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 2114 */         queryForm.getPageNoAsInt(), 
/* 2115 */         queryForm.getPageSizeAsInt(), 
/* 2116 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 2117 */         queryForm.isDescend());
/* 2118 */     if (ids.length > 0) {
/* 2119 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2120 */         Integer itemId = pendingItem.getId();
/* 2121 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 2123 */         for (int i = 0; i < ids.length; i++) {
/* 2124 */           if (!ids[i].equals("") && 
/* 2125 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 2126 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2131 */         if (isClose.booleanValue()) {
/* 2132 */           pendingItem.setChecked("checked");
/*      */         }
/*      */       } 
/*      */     }
/* 2136 */     request.setAttribute("X_RESULTLIST", result);
/* 2137 */     request.setAttribute("X_YESNOLIST", 
/* 2138 */         PersistentEnum.getEnumList(YesNo.class));
/* 2139 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 2140 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 2141 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward shippingOrderProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2148 */     PortalShipOrderMainQueryForm queryForm = (PortalShipOrderMainQueryForm)form;
/* 2149 */     PortalShipOrderManager fm = 
/* 2150 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2151 */     if (queryForm.getOrder() == "") {
/* 2152 */       queryForm.setOrder("createDate");
/*      */     }
/*      */ 
/*      */     
/* 2156 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 2157 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2158 */     getConditionsFrom(queryForm, conditions);
/*      */     
/* 2160 */     conditions.put(PortalShipOrderQueryCondition.CREATETYPE_EQ, "JIT");
/* 2161 */     User user = getCurrentUser(request);
/* 2162 */     SupplierManager supplierManager = 
/* 2163 */       ServiceLocator.getSupplierManager(request);
/* 2164 */     Supplier supplier = supplierManager.getSupplierByCode(user
/* 2165 */         .getLoginName());
/* 2166 */     if (!hasGlobalPower(request)) {
/* 2167 */       conditions.put(PortalShipOrderQueryCondition.SITE_EQ, 
/* 2168 */           getCurrentUser(request).getPrimarySite().getId());
/* 2169 */       if (supplier != null) {
/* 2170 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, 
/* 2171 */             supplier.getId());
/*      */       } else {
/* 2173 */         conditions.put(PortalShipOrderQueryCondition.SUPPLIER_ID_EQ, Integer.valueOf(0));
/*      */       } 
/*      */     } 
/*      */     
/* 2177 */     String exportType = queryForm.getExportType();
/* 2178 */     if (exportType != null && exportType.length() > 0) {
/* 2179 */       List data = fm.getPortalShipOrderList(conditions, 0, -1, null, 
/* 2180 */           false);
/* 2181 */       int index = SessionTempFile.createNewTempFile(request);
/* 2182 */       String fileName = "PortalShipOrder";
/* 2183 */       String suffix = ExportUtil.export(
/* 2184 */           exportType, 
/* 2185 */           data, 
/* 2186 */           request, 
/* 2187 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2188 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 2191 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 2192 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 2193 */                     "SO.No1"));
/* 2194 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 2195 */                     "supplier1"));
/* 2196 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 2197 */                     "supplier.code1"));
/* 2198 */               row.add("创建时间");
/* 2199 */               row.add("发货时间");
/* 2200 */               row.add(messages.getMessage(PortalShipOrderActiony.this.getLocale(request), 
/* 2201 */                     "portalShipOrder.status"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2206 */               row.add(BeanUtils.getProperty(data, "code"));
/* 2207 */               row.add(BeanUtils.getProperty(data, 
/* 2208 */                     "site.supplier.name"));
/* 2209 */               row.add(BeanUtils.getProperty(data, 
/* 2210 */                     "site.supplier.code"));
/* 2211 */               row.add("");
/* 2212 */               row.add(BeanUtils.getProperty(data, "createDate"));
/* 2213 */               String locale = PortalShipOrderActiony.this.getCurrentUser(request).getLocale();
/* 2214 */               if ("en".equals(locale)) {
/* 2215 */                 row.add(BeanUtils.getProperty(data, 
/* 2216 */                       "status.engShortDescription"));
/*      */               } else {
/* 2218 */                 row.add(BeanUtils.getProperty(data, 
/* 2219 */                       "status.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/* 2223 */       return new ActionForward("download/" + index + "/" + 
/* 2224 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 2226 */     if (queryForm.isFirstInit()) {
/* 2227 */       queryForm.init(fm.getPortalShipOrderListCount(conditions));
/*      */     } else {
/* 2229 */       queryForm.init();
/*      */     } 
/* 2231 */     List result = fm.getPortalShipOrderList(conditions, 
/* 2232 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2233 */         PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/* 2234 */         queryForm.isDescend());
/* 2235 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 2236 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 2237 */     request.setAttribute("x_statusList", 
/* 2238 */         PortalShipOrderStatus.getEnumList(PortalShipOrderStatus.class));
/* 2239 */     request.setAttribute("X_RESULTLIST", result);
/* 2240 */     request.setAttribute("x_selType", Integer.valueOf(170));
/* 2241 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2247 */     PortalShipOrderMainQueryForm formBean = (PortalShipOrderMainQueryForm)form;
/* 2248 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2249 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2250 */     PortalShipOrderManager portalShipOrderManager = 
/* 2251 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2252 */     String shipOrderId = request.getParameter("id");
/* 2253 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 2254 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 2255 */     List<PortalShipOrderItem> portalShipOrderItemList = portalShipOrderManager
/* 2256 */       .getPortalShipOrderItemListByOrderId(
/* 2257 */         Integer.valueOf(Integer.parseInt(shipOrderId)));
/* 2258 */     User user = getCurrentUser(request);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2263 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 2264 */     request.setAttribute("X_DATE", format.format(new Date()));
/* 2265 */     List<Box> resultList = portalShipOrderManager
/* 2266 */       .getBoxByShipOrderId(portalShipOrder.getId());
/*      */     
/* 2268 */     int totalCount = resultList.size();
/*      */     
/* 2270 */     int pageCount = 0;
/*      */     
/* 2272 */     int endNum = formBean.getPageSizeAsInt();
/* 2273 */     if (endNum == -1) {
/* 2274 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 2277 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 2279 */     if (totalCount % endNum > 0) {
/*      */       
/* 2281 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 2284 */       pageCount = totalCount / endNum;
/*      */     } 
/* 2286 */     if (totalCount > 0) {
/* 2287 */       if (startNum <= pageCount) {
/* 2288 */         if (startNum == 1) {
/*      */           
/* 2290 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 2294 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 2298 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 2300 */           int toIndex = startNum * endNum;
/*      */           
/* 2302 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 2303 */             toIndex = startNum * endNum;
/*      */           } else {
/* 2305 */             toIndex = (startNum - 1) * endNum + 
/* 2306 */               totalCount % endNum;
/*      */           } 
/* 2308 */           if (totalCount >= toIndex) {
/* 2309 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 2313 */         resultList = null;
/*      */       } 
/*      */     }
/* 2316 */     String exportType = formBean.getExportType();
/* 2317 */     if (exportType != null && exportType.length() > 0) {
/* 2318 */       int index = SessionTempFile.createNewTempFile(request);
/* 2319 */       String fileName = "PortalShipOrderBoxItem";
/* 2320 */       String suffix = ExportUtil.export(
/* 2321 */           exportType, 
/* 2322 */           resultList, 
/* 2323 */           request, 
/* 2324 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2325 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 2328 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 2329 */               row.add("箱单号");
/* 2330 */               row.add("供应商批次");
/* 2331 */               row.add("组件/物料编码");
/* 2332 */               row.add("品名");
/* 2333 */               row.add("包装箱容量");
/* 2334 */               row.add("批次数量");
/* 2335 */               row.add("发货数量");
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2340 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 2341 */               row.add(
/* 2342 */                   BeanUtils.getProperty(data, "supplierBatch"));
/* 2343 */               row.add(BeanUtils.getProperty(data, 
/* 2344 */                     "psoItem.poipItem.itemNumber.id"));
/* 2345 */               row.add(BeanUtils.getProperty(data, 
/* 2346 */                     "psoItem.poipItem.itemNumber.dpiNo"));
/* 2347 */               row.add(BeanUtils.getProperty(data, 
/* 2348 */                     "psoItem.qty_std"));
/* 2349 */               row.add("");
/* 2350 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2351 */               row.add(BeanUtils.getProperty(data, 
/* 2352 */                     "isPrint.chnShortDescription"));
/*      */             }
/*      */           });
/* 2355 */       return new ActionForward("download/" + index + "/" + 
/* 2356 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 2358 */     if (formBean.isFirstInit()) {
/* 2359 */       formBean.init(totalCount);
/*      */     } else {
/* 2361 */       formBean.init();
/*      */     } 
/*      */     
/* 2364 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2365 */     request.setAttribute("x_portalShipOrderItemList", 
/* 2366 */         portalShipOrderItemList);
/* 2367 */     request.setAttribute("x_portalShopOrderBoxList", resultList);
/* 2368 */     int size = resultList.size();
/* 2369 */     for (Box poIpiBox : resultList) {
/* 2370 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 2371 */         poIpiBox.getSupplierBatch() != null) {
/* 2372 */         size--;
/* 2373 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 2376 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/* 2377 */     request.getSession().setAttribute("path", request.getContextPath());
/*      */     
/* 2379 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 2380 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 2381 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printLotListProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2387 */     PurchaseOrderInspectionPendingItemQueryForm formBean = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2388 */     PortalShipOrderManager portalShipOrderManager = 
/* 2389 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2390 */     String id = request.getParameter("id");
/* 2391 */     PortalShipOrder portalShipOrder = portalShipOrderManager
/* 2392 */       .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 2393 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2394 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2395 */     List<Box> resultList = portalShipOrderManager
/* 2396 */       .getBoxByShipOrderId(portalShipOrder.getId());
/* 2397 */     BoxManager poipbm = ServiceLocator.getBoxManager(request);
/*      */     
/* 2399 */     String str = request.getParameter("supplierBatchStr");
/* 2400 */     String poipBoxIds = request.getParameter("poipBoxIds");
/*      */     
/* 2402 */     if ((str != null || poipBoxIds != null) && (
/* 2403 */       !str.equals("undefined") || !poipBoxIds.equals("undefined"))) {
/* 2404 */       String[] supplierBatchList = str.split(",");
/* 2405 */       String[] poipBoxIdList = poipBoxIds.split(",");
/* 2406 */       if (supplierBatchList.length > 0) {
/* 2407 */         for (int i = 0; i < supplierBatchList.length; i++) {
/* 2408 */           if (poipBoxIdList[i].length() > 0) {
/* 2409 */             Integer poipBoxId = 
/* 2410 */               Integer.valueOf(Integer.parseInt(poipBoxIdList[i]));
/* 2411 */             String supplierBatch = supplierBatchList[i];
/* 2412 */             Box poIpiBox = poipbm.getBox(poipBoxId);
/* 2413 */             if (supplierBatch != null || supplierBatch != "") {
/* 2414 */               poIpiBox.setSupplierBatch(supplierBatch);
/*      */               
/* 2416 */               poipbm.updateBox(poIpiBox);
/*      */             } else {
/* 2418 */               poIpiBox.setSupplierBatch("");
/* 2419 */               poipbm.updateBox(poIpiBox);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2429 */     int totalCount = resultList.size();
/*      */     
/* 2431 */     int pageCount = 0;
/*      */     
/* 2433 */     int endNum = formBean.getPageSizeAsInt();
/* 2434 */     if (endNum == -1) {
/* 2435 */       endNum = resultList.size();
/*      */     }
/*      */     
/* 2438 */     int startNum = formBean.getPageNoAsInt() + 1;
/*      */     
/* 2440 */     if (totalCount % endNum > 0) {
/*      */       
/* 2442 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 2445 */       pageCount = totalCount / endNum;
/*      */     } 
/* 2447 */     if (totalCount > 0) {
/* 2448 */       if (startNum <= pageCount) {
/* 2449 */         if (startNum == 1) {
/*      */           
/* 2451 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 2455 */             resultList = resultList.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 2459 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 2461 */           int toIndex = startNum * endNum;
/*      */           
/* 2463 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 2464 */             toIndex = startNum * endNum;
/*      */           } else {
/* 2466 */             toIndex = (startNum - 1) * endNum + 
/* 2467 */               totalCount % endNum;
/*      */           } 
/* 2469 */           if (totalCount >= toIndex) {
/* 2470 */             resultList = resultList.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 2474 */         resultList = null;
/*      */       } 
/*      */     }
/* 2477 */     if (formBean.isFirstInit()) {
/* 2478 */       formBean.init(totalCount);
/*      */     } else {
/* 2480 */       formBean.init();
/*      */     } 
/*      */     
/* 2483 */     request.setAttribute("x_supplierBatchStr", str);
/* 2484 */     request.setAttribute("x_poipBoxIds", poipBoxIds);
/* 2485 */     request.setAttribute("x_id", id);
/* 2486 */     request.setAttribute("x_boxList", resultList);
/* 2487 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2488 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward withdrawPortalShipOrderTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2494 */     PortalShipOrderManager portalShipOrderManager = 
/* 2495 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2496 */     PurchaseOrderInspectionPendingManager poipManager = 
/* 2497 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2498 */     PurchaseOrderManager poManager = 
/* 2499 */       ServiceLocator.getPurchaseOrderManager(request);
/* 2500 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 2501 */     List<PortalShipOrderItem> portalShipOrderItemlist = null;
/*      */     
/* 2503 */     List<Box> list = new ArrayList();
/* 2504 */     List<Box> poipBoxList = null;
/*      */     try {
/* 2506 */       if (!portalShipOrder.getStatus().equals(
/* 2507 */           PortalShipOrderStatus.RECEIVE)) {
/* 2508 */         portalShipOrder.setStatus(PortalShipOrderStatus.DRAFT);
/* 2509 */         portalShipOrderManager.updatePortalShipOrder(portalShipOrder);
/*      */         
/* 2511 */         portalShipOrderItemlist = portalShipOrderManager
/* 2512 */           .getPortalShipOrderItemListByOrderId(portalShipOrder
/* 2513 */             .getId());
/* 2514 */         for (PortalShipOrderItem portalShipOrderItem : portalShipOrderItemlist) {
/*      */ 
/*      */           
/* 2517 */           poipBoxList = portalShipOrderManager
/* 2518 */             .getBoxList(portalShipOrderItem.getId());
/* 2519 */           list.addAll(poipBoxList);
/*      */         } 
/*      */       } 
/* 2522 */     } catch (Exception e) {
/* 2523 */       e.fillInStackTrace();
/* 2524 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 2527 */     request.setAttribute("x_portalShipOrder", portalShipOrder);
/* 2528 */     request.setAttribute("x_portalShipOrderItemList", 
/* 2529 */         portalShipOrderItemlist);
/* 2530 */     request.setAttribute("x_portalShopOrderBoxList", list);
/* 2531 */     int size = poipBoxList.size();
/* 2532 */     for (Box poIpiBox : poipBoxList) {
/* 2533 */       if (poIpiBox.getSupplierBatch() != "" && 
/* 2534 */         poIpiBox.getSupplierBatch() != null) {
/* 2535 */         size--;
/* 2536 */         System.out.println(String.valueOf(size) + "---");
/*      */       } 
/*      */     } 
/* 2539 */     request.getSession().setAttribute("size", Integer.valueOf(size));
/*      */ 
/*      */     
/* 2542 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderIpTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2548 */     PortalShipOrderManager portalShipOrderManager = 
/* 2549 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2550 */     String id = request.getParameter("id");
/* 2551 */     PortalShipOrder portalShipOrder = getPortalShipOrder(request);
/* 2552 */     portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */ 
/*      */     
/* 2555 */     return new ActionForward("viewPortalShipOrderTwoProduct.do?id=" + 
/* 2556 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward portalPirntCodeItemReportProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2562 */     PurchaseOrderInspectionPendingManager poip = 
/* 2563 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2564 */     String string = request.getParameter("str");
/* 2565 */     SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
/*      */ 
/*      */     
/* 2568 */     String[] strings = string.split(",");
/* 2569 */     List<Box> boxlist = new ArrayList<Box>();
/* 2570 */     PurchaseOrderInspectionPendingItem item = null; byte b; int i; String[] arrayOfString1;
/* 2571 */     for (i = (arrayOfString1 = strings).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 2572 */       Box box = poip.getBox(Integer.valueOf(Integer.parseInt(str)));
/* 2573 */       String batchStr = box.getPsoItem().getPortalShipOrder().getCode();
/* 2574 */       String batchNo = batchStr.substring(batchStr.length() - 9, batchStr.length());
/* 2575 */       box.setHuCodeOrderNumber(batchNo);
/* 2576 */       boxlist.add(box);
/*      */       b++; }
/*      */     
/* 2579 */     request.setAttribute("x_item", item);
/* 2580 */     request.setAttribute("X_RESULTLIST", boxlist);
/* 2581 */     request.setAttribute("X_DATE", format.format(new Date()));
/*      */     
/* 2583 */     request.setAttribute("path", request.getContextPath());
/* 2584 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalPirntCodeItemsProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2590 */     PurchaseOrderInspectionPendingManager poip = 
/* 2591 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2592 */     PurchaseOrderReceiptsManager pReceiptsManager = 
/* 2593 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 2594 */     JSONObject object = new JSONObject();
/* 2595 */     boolean bol = false;
/* 2596 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 2597 */     String ids = request.getParameter("ids");
/* 2598 */     String[] idr = ids.split(";");
/* 2599 */     Integer idInteger = null; byte b; int i; String[] arrayOfString1;
/* 2600 */     for (i = (arrayOfString1 = idr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2601 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 2602 */       box.setIsPrint(YesNo.YES);
/* 2603 */       manager.updateBox(box);
/* 2604 */       idInteger = box.getPsoItem().getPortalShipOrder().getId();
/*      */       b++; }
/*      */     
/* 2607 */     bol = true;
/* 2608 */     PrintWriter print = response.getWriter();
/* 2609 */     object.put("str", Boolean.valueOf(bol));
/* 2610 */     print.print(object);
/* 2611 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObjectProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2620 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2626 */     PurchaseOrderInspectionPendingManager poipm = 
/* 2627 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2628 */     PortalShipOrderManager portalShipOrderManager = 
/* 2629 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2630 */     PurchaseOrderInspectionPendingQueryForm queryForm = (PurchaseOrderInspectionPendingQueryForm)form;
/*      */     
/* 2632 */     PortalShipOrder portalShipOrder = null;
/*      */     
/*      */     try {
/* 2635 */       String[] poipItemIdsList = request
/* 2636 */         .getParameterValues("poipItemIds");
/* 2637 */       String[] deliveryNumberList = request
/* 2638 */         .getParameterValues("deliveryNumbers");
/* 2639 */       for (int i = 0; i < poipItemIdsList.length; i++) {
/* 2640 */         if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */           
/* 2644 */           if (portalShipOrder == null) {
/* 2645 */             portalShipOrder = new PortalShipOrder();
/* 2646 */             Site site = getCurrentUser(request).getPrimarySite();
/* 2647 */             User requestor = getCurrentUser(request);
/* 2648 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 2649 */             String arrivalDate = queryForm.getReceivingDate();
/* 2650 */             Date arrDate = null;
/* 2651 */             if (arrivalDate != null && arrivalDate.trim().length() != 0) {
/* 2652 */               arrDate = sdf.parse(arrivalDate);
/*      */             }
/* 2654 */             portalShipOrder.setType(Integer.valueOf(3));
/* 2655 */             portalShipOrderManager.insertPortalShipOrder(
/* 2656 */                 portalShipOrder, site, requestor, arrDate);
/*      */           } 
/*      */           
/* 2659 */           String poipItem = poipItemIdsList[i];
/* 2660 */           BigDecimal deliveryNumber = new BigDecimal(
/* 2661 */               deliveryNumberList[i]);
/*      */           
/* 2663 */           PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem1 = poipm
/* 2664 */             .getPurchaseOrderInspectionPendingItem(
/* 2665 */               Integer.valueOf(Integer.parseInt(poipItem)));
/*      */           
/* 2667 */           BigDecimal wBigDecimal = purchaseOrderInspectionPendingItem1
/* 2668 */             .getQtyOpen();
/* 2669 */           purchaseOrderInspectionPendingItem1.setQtyOpen(wBigDecimal
/* 2670 */               .subtract(deliveryNumber));
/* 2671 */           poipm.updatePurchaseOrderInspectionPendingItem3(purchaseOrderInspectionPendingItem1);
/*      */ 
/*      */           
/* 2674 */           Boolean isClose = poipm
/* 2675 */             .isclosePurchaseOrderInspectionPendingByItem(purchaseOrderInspectionPendingItem1
/* 2676 */               .getPoip_number());
/* 2677 */           if (isClose.booleanValue()) {
/* 2678 */             purchaseOrderInspectionPendingItem1.getPoip_number()
/* 2679 */               .setStatus(PurchaseOrderStatus.WAIT);
/* 2680 */             poipm.updatePurchaseOrderInspectionPending(purchaseOrderInspectionPendingItem1
/* 2681 */                 .getPoip_number());
/*      */           } 
/*      */           
/* 2684 */           PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/*      */           
/* 2686 */           portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2687 */           portalShipOrderItem
/* 2688 */             .setPoipItem(purchaseOrderInspectionPendingItem1);
/* 2689 */           portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2690 */           portalShipOrderItem
/* 2691 */             .setQty_std(purchaseOrderInspectionPendingItem1
/* 2692 */               .getQty_std());
/* 2693 */           portalShipOrderManager
/* 2694 */             .insertPortalShipOrderItem(portalShipOrderItem);
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
/* 2705 */       PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem = poipm
/* 2706 */         .getPurchaseOrderInspectionPendingItem(
/* 2707 */           Integer.valueOf(Integer.parseInt(poipItemIdsList[0])));
/* 2708 */       if (!"T".equals(purchaseOrderInspectionPendingItem.getVd_promo())) {
/* 2709 */         portalShipOrderManager.createPortalShipOrderIP(portalShipOrder);
/*      */       }
/* 2711 */     } catch (Exception e) {
/* 2712 */       e.fillInStackTrace();
/*      */     } 
/* 2714 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + 
/* 2715 */         portalShipOrder.getId(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseItemTwoProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2722 */     PurchaseOrderInspectionPendingItemQueryForm queryForm = (PurchaseOrderInspectionPendingItemQueryForm)form;
/* 2723 */     PurchaseOrderInspectionPendingManager fm = 
/* 2724 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2725 */     ProjectedInventoryManager projectedInventoryManager = 
/* 2726 */       ServiceLocator.getProjectedInventoryManager(request);
/* 2727 */     String idString = queryForm.getSelectPoipItemId();
/* 2728 */     String[] ids = idString.split(";");
/* 2729 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 2731 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.qtyOpen_DT, 
/* 2732 */         Integer.valueOf(0));
/* 2733 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2734 */     String poNumber = queryForm.getPoip_number();
/* 2735 */     if (poNumber != null && !poNumber.equals("")) {
/* 2736 */       conditions.put(
/* 2737 */           PurchaseOrderInspectionPendingQueryCondition.PONUMBER_EQ, 
/* 2738 */           poNumber);
/*      */     }
/*      */     
/* 2741 */     String itemNumber = queryForm.getItemNumber();
/* 2742 */     if (itemNumber != null && !itemNumber.equals("")) {
/* 2743 */       conditions.put(
/* 2744 */           PurchaseOrderInspectionPendingQueryCondition.ITEMNUMBER_EQ, 
/* 2745 */           itemNumber);
/*      */     }
/*      */     
/* 2748 */     String endPoDate = queryForm.getDueDate();
/* 2749 */     if (endPoDate != null && !endPoDate.equals("")) {
/* 2750 */       conditions.put(
/* 2751 */           PurchaseOrderInspectionPendingQueryCondition.DUEDATE_EQ, 
/* 2752 */           endPoDate);
/*      */     }
/* 2754 */     String status = queryForm.getStatus();
/* 2755 */     if (status != null && !status.equals("")) {
/* 2756 */       conditions.put(
/* 2757 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 2758 */           status);
/*      */     } else {
/* 2760 */       conditions.put(
/* 2761 */           PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     } 
/* 2763 */     SupplierManager manager = ServiceLocator.getSupplierManager(request);
/* 2764 */     User user = getCurrentUser(request);
/* 2765 */     Supplier supplier = manager.getSupplierByCode(user.getLoginName());
/* 2766 */     if (getCurrentUser(request).getPrimarySite() != null && 
/* 2767 */       supplier != null) {
/*      */       
/* 2769 */       conditions
/* 2770 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2771 */           supplier.getId());
/*      */     } else {
/* 2773 */       conditions
/* 2774 */         .put(PurchaseOrderInspectionPendingQueryCondition.SUPPLIER_ID_EQ, 
/* 2775 */           Integer.valueOf(0));
/*      */     } 
/* 2777 */     conditions.put(
/* 2778 */         PurchaseOrderInspectionPendingQueryCondition.CREATETYPE_EQ, Integer.valueOf(1));
/* 2779 */     if (queryForm.isFirstInit()) {
/* 2780 */       queryForm
/* 2781 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/* 2783 */       queryForm.init();
/*      */     } 
/* 2785 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2786 */       .getPurchaseOrderInspectionPendingItemList(conditions, 
/* 2787 */         queryForm.getPageNoAsInt(), 
/* 2788 */         queryForm.getPageSizeAsInt(), 
/* 2789 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 2790 */         queryForm.isDescend());
/* 2791 */     if (ids.length > 0) {
/* 2792 */       for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2793 */         Integer itemId = pendingItem.getId();
/* 2794 */         Boolean isClose = Boolean.valueOf(false);
/*      */         
/* 2796 */         for (int i = 0; i < ids.length; i++) {
/* 2797 */           if (!ids[i].equals("") && 
/* 2798 */             itemId.intValue() == Integer.parseInt(ids[i])) {
/* 2799 */             isClose = Boolean.valueOf(true);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2804 */         if (isClose.booleanValue()) {
/* 2805 */           pendingItem.setChecked("checked");
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
/* 2828 */     request.setAttribute("X_RESULTLIST", result);
/* 2829 */     request.setAttribute("X_YESNOLIST", 
/* 2830 */         PersistentEnum.getEnumList(YesNo.class));
/* 2831 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", 
/* 2832 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/* 2833 */     return mapping.findForward("page");
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
/* 2844 */     response.setContentType("text/json");
/* 2845 */     response.setCharacterEncoding("UTF-8");
/* 2846 */     JsonConfig cfg = new JsonConfig();
/* 2847 */     ProjectedInventoryManager projectedInventoryManager = 
/* 2848 */       ServiceLocator.getProjectedInventoryManager(request);
/* 2849 */     PurchaseOrderInspectionPendingManager fm = 
/* 2850 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 2851 */     String arrays = request.getParameter("arrays");
/* 2852 */     String[] str = arrays.split(",");
/* 2853 */     List<Map> mapList = new ArrayList<Map>();
/* 2854 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 2855 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.ID_IN, str);
/* 2856 */     List<PurchaseOrderInspectionPendingItem> result = fm
/* 2857 */       .getPurchaseOrderInspectionPendingItemList(conditions, 0, -1, 
/* 2858 */         PurchaseOrderInspectionPendingQueryOrder.ID, false);
/* 2859 */     for (PurchaseOrderInspectionPendingItem pendingItem : result) {
/* 2860 */       pendingItem = fm.getPurchaseOrderInspectionPendingItem(pendingItem);
/* 2861 */       pendingItem.setTransitQty(pendingItem.getQty()
/* 2862 */           .subtract(pendingItem.getQtyOpen())
/* 2863 */           .subtract(pendingItem.getReceiptQty()));
/* 2864 */       Map<Object, Object> conditions1 = new HashMap<Object, Object>();
/* 2865 */       conditions1.put(ProjectedInventoryQueryCondition.PART_ID_EQ, 
/* 2866 */           pendingItem.getItemNumber().getId());
/* 2867 */       List<ProjectedInventory> projectedInventoryList = projectedInventoryManager
/* 2868 */         .getList(conditions1, 0, -1, null, true);
/* 2869 */       if (projectedInventoryList != null && 
/* 2870 */         projectedInventoryList.size() > 0) {
/* 2871 */         pendingItem.setCurrentQty(
/* 2872 */             (((ProjectedInventory)projectedInventoryList.get(0)).getCurrentQty() == null) ? new BigDecimal(0) : (
/* 2873 */             (ProjectedInventory)projectedInventoryList.get(0)).getCurrentQty()); continue;
/*      */       } 
/* 2875 */       pendingItem.setCurrentQty(new BigDecimal(0));
/*      */     } 
/*      */     
/* 2878 */     Map<String, String> map1 = new LinkedHashMap<String, String>();
/* 2879 */     for (PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem : result) {
/* 2880 */       map1.put(
/* 2881 */           purchaseOrderInspectionPendingItem.getItemNumber().getId(), 
/* 2882 */           purchaseOrderInspectionPendingItem.getItemNumber().getId());
/*      */     }
/* 2884 */     for (String str1 : map1.keySet()) {
/* 2885 */       Map<Object, Object> map2 = new LinkedHashMap<Object, Object>();
/* 2886 */       map2.put(map1.get(str1), map1.get(str1));
/* 2887 */       mapList.add(map2);
/*      */     } 
/* 2889 */     for (PurchaseOrderInspectionPendingItem purchaseOrderInspectionPendingItem : result) {
/* 2890 */       for (Map<String, String> map : mapList) {
/* 2891 */         if (map.containsKey(purchaseOrderInspectionPendingItem
/* 2892 */             .getItemNumber().getId())) {
/* 2893 */           map.put("part", purchaseOrderInspectionPendingItem
/* 2894 */               .getItemNumber().getId());
/* 2895 */           map.put("highQty", purchaseOrderInspectionPendingItem
/* 2896 */               .getItemNumber().getHighQty());
/* 2897 */           map.put("lowQty", purchaseOrderInspectionPendingItem
/* 2898 */               .getItemNumber().getLowQty());
/* 2899 */           map.put("currentQty", 
/* 2900 */               purchaseOrderInspectionPendingItem.getCurrentQty());
/* 2901 */           if (map.containsKey("transitQty")) {
/* 2902 */             BigDecimal transitQty = (BigDecimal)map
/* 2903 */               .get("transitQty");
/* 2904 */             transitQty.add(purchaseOrderInspectionPendingItem
/* 2905 */                 .getTransitQty());
/* 2906 */             map.put("transitQty", transitQty);
/*      */           } else {
/* 2908 */             map.put("transitQty", 
/* 2909 */                 purchaseOrderInspectionPendingItem
/* 2910 */                 .getTransitQty());
/*      */           } 
/* 2912 */           if (map.containsKey("qty")) {
/* 2913 */             BigDecimal qty = (BigDecimal)map.get("qty");
/* 2914 */             qty.add(purchaseOrderInspectionPendingItem.getQty());
/* 2915 */             map.put("qty", qty); continue;
/*      */           } 
/* 2917 */           map.put("qty", 
/* 2918 */               purchaseOrderInspectionPendingItem.getQty());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2923 */     for (Map<String, BigDecimal> map : mapList) {
/* 2924 */       BigDecimal qty = (BigDecimal)map.get("qty");
/* 2925 */       BigDecimal transitQty = (BigDecimal)map.get("transitQty");
/* 2926 */       BigDecimal currentQty = (BigDecimal)map.get("currentQty");
/* 2927 */       BigDecimal highQty = (BigDecimal)map.get("highQty");
/*      */ 
/*      */       
/* 2930 */       map.put("recommendQty", 
/* 2931 */           highQty.subtract(transitQty).subtract(currentQty));
/* 2932 */       BigDecimal recommendQty = map.get("recommendQty");
/*      */ 
/*      */ 
/*      */       
/* 2936 */       if (recommendQty.compareTo(highQty) == 1) {
/* 2937 */         map.put("message", "推荐数量已高于高储数量"); continue;
/*      */       } 
/* 2939 */       map.put("message", "");
/*      */     } 
/*      */     
/* 2942 */     JSONArray jo = JSONArray.fromObject(mapList, cfg);
/* 2943 */     response.getWriter().print(jo);
/* 2944 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipJitPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2950 */     SupplierManager supplierManager = 
/* 2951 */       ServiceLocator.getSupplierManager(request);
/* 2952 */     WmsPartManager wmsPartManager = 
/* 2953 */       ServiceLocator.getWmsPartManager(request);
/* 2954 */     PortalShipOrderManager portalShipOrderManager = 
/* 2955 */       ServiceLocator.getPortalShipOrderManager(request);
/* 2956 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/* 2957 */       ServiceLocator.getPortalShipOrderItemManager(request);
/* 2958 */     User user = getCurrentUser(request);
/* 2959 */     Supplier supplier = supplierManager.getSupplierByCode(user
/* 2960 */         .getLoginName());
/* 2961 */     PortalShipOrder portalShipOrder = null;
/* 2962 */     if (supplier != null) {
/*      */       try {
/* 2964 */         String[] partIdsList = request.getParameterValues("ids");
/* 2965 */         String[] deliveryNumberList = request
/* 2966 */           .getParameterValues("deliveryNumbers");
/* 2967 */         String arrivalDate = request.getParameter("receivingDate");
/* 2968 */         String createType = request.getParameter("createType");
/* 2969 */         for (int i = 0; i < partIdsList.length; i++) {
/* 2970 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */             
/* 2974 */             if (portalShipOrder == null) {
/* 2975 */               portalShipOrder = new PortalShipOrder();
/* 2976 */               Site site = getCurrentUser(request)
/* 2977 */                 .getPrimarySite();
/* 2978 */               User requestor = getCurrentUser(request);
/* 2979 */               portalShipOrder.setCreateType(createType);
/* 2980 */               portalShipOrder.setSupplier(supplier);
/* 2981 */               portalShipOrder.setCreateDate(new Date());
/* 2982 */               portalShipOrder.setIsPrint(YesNo.NO);
/* 2983 */               portalShipOrder.setType(Integer.valueOf(3));
/* 2984 */               portalShipOrder.setArrivalDate(new Date(arrivalDate));
/* 2985 */               portalShipOrderManager.insertPortalShipOrderByJitPart(
/* 2986 */                   portalShipOrder, site, requestor, new Date());
/*      */             } 
/* 2988 */             String partId = partIdsList[i];
/* 2989 */             BigDecimal deliveryNumber = new BigDecimal(
/* 2990 */                 deliveryNumberList[i]);
/* 2991 */             WmsPart part = wmsPartManager.getWmsPart(partId);
/*      */             
/* 2993 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/* 2994 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 2995 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 2996 */             portalShipOrderItem.setPart(part);
/* 2997 */             portalShipOrderManager
/* 2998 */               .insertPortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/* 3000 */         }  portalShipOrderManager
/* 3001 */           .createPortalShipOrderByJitPartIP(portalShipOrder);
/* 3002 */       } catch (Exception e) {
/* 3003 */         e.fillInStackTrace();
/*      */       } 
/* 3005 */       return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + 
/* 3006 */           portalShipOrder.getId(), true);
/*      */     } 
/* 3008 */     return new ActionForward("/listShippingOrderProduct.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createPortalShipOrderTwoWrongByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3014 */     WmsPartManager wmsPartManager = 
/* 3015 */       ServiceLocator.getWmsPartManager(request);
/* 3016 */     PortalShipOrderManager portalShipOrderManager = 
/* 3017 */       ServiceLocator.getPortalShipOrderManager(request);
/* 3018 */     PortalShipOrderItemManager portalShipOrderItemManager = 
/* 3019 */       ServiceLocator.getPortalShipOrderItemManager(request);
/* 3020 */     SupplierManager supplierManager = 
/* 3021 */       ServiceLocator.getSupplierManager(request);
/* 3022 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/* 3023 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/*      */ 
/*      */ 
/*      */     
/* 3027 */     String supplierCode = request.getParameter("supplierCode");
/* 3028 */     Supplier supplier = supplierManager.getSupplierByCode(supplierCode);
/* 3029 */     Map<Object, Object> mapSite = new HashMap<Object, Object>();
/* 3030 */     mapSite.put(SiteQueryCondition.NAME_EQ, supplierCode);
/* 3031 */     List<Site> siteList = siteManager.getSiteList(mapSite, 0, -1, null, 
/* 3032 */         false);
/* 3033 */     PortalShipOrder portalShipOrder = null;
/* 3034 */     if (supplier != null) {
/*      */       try {
/* 3036 */         Site site = siteList.get(0);
/* 3037 */         String[] partIdsList = request.getParameterValues("ids");
/* 3038 */         String[] deliveryNumberList = request
/* 3039 */           .getParameterValues("deliveryNumbers");
/* 3040 */         String createType = request.getParameter("createType");
/* 3041 */         String arrivalDate = request.getParameter("receivingDate");
/* 3042 */         for (int i = 0; i < partIdsList.length; i++) {
/* 3043 */           if (!deliveryNumberList[i].equals("")) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3048 */             if (portalShipOrder == null) {
/* 3049 */               portalShipOrder = new PortalShipOrder();
/*      */ 
/*      */               
/* 3052 */               User requestor = getCurrentUser(request);
/* 3053 */               portalShipOrder.setCreateType(createType);
/* 3054 */               portalShipOrder.setSupplier(supplier);
/* 3055 */               portalShipOrder.setCreateDate(new Date());
/* 3056 */               portalShipOrder.setArrivalDate(format
/* 3057 */                   .parse(arrivalDate));
/* 3058 */               portalShipOrder.setIsPrint(YesNo.NO);
/* 3059 */               portalShipOrder.setType(Integer.valueOf(2));
/* 3060 */               portalShipOrderManager
/* 3061 */                 .insertPortalShipOrderByNpoPartSupplier(
/* 3062 */                   portalShipOrder, site, supplier, 
/* 3063 */                   new Date());
/*      */             } 
/* 3065 */             String partId = partIdsList[i];
/*      */ 
/*      */ 
/*      */             
/* 3069 */             String a = deliveryNumberList[i].replace(",", "");
/* 3070 */             BigDecimal deliveryNumber = new BigDecimal(
/* 3071 */                 a);
/* 3072 */             WmsPart part = wmsPartManager.getWmsPart(partId);
/* 3073 */             PortalShipOrderItem portalShipOrderItem = new PortalShipOrderItem();
/* 3074 */             portalShipOrderItem.setPortalShipOrder(portalShipOrder);
/* 3075 */             portalShipOrderItem.setDeliveryNumber(deliveryNumber);
/* 3076 */             portalShipOrderItem.setPart(part);
/* 3077 */             portalShipOrderManager
/* 3078 */               .insertPortalShipOrderItem(portalShipOrderItem);
/*      */           } 
/* 3080 */         }  portalShipOrderManager
/* 3081 */           .createPortalShipOrderByNpoPartIP(portalShipOrder);
/* 3082 */       } catch (Exception e) {
/* 3083 */         e.fillInStackTrace();
/*      */       } 
/* 3085 */       return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + 
/* 3086 */           portalShipOrder.getId(), true);
/*      */     } 
/* 3088 */     return new ActionForward("/listShippingOrderWrong.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateJitCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3095 */     String id = request.getParameter("id");
/*      */     try {
/* 3097 */       PortalShipOrderManager shipOrderManager = 
/* 3098 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3099 */       PortalShipOrder shipOrder = shipOrderManager
/* 3100 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3101 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3102 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3103 */     } catch (Exception e) {
/*      */       
/* 3105 */       e.printStackTrace();
/*      */     } 
/* 3107 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id, 
/* 3108 */         true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateJitOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3115 */     String id = request.getParameter("id");
/*      */     try {
/* 3117 */       PortalShipOrderManager shipOrderManager = 
/* 3118 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3119 */       PortalShipOrder shipOrder = shipOrderManager
/* 3120 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3121 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3122 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3123 */     } catch (Exception e) {
/*      */       
/* 3125 */       e.printStackTrace();
/*      */     } 
/* 3127 */     return new ActionForward("/viewPortalShipOrderTwoProduct.do?id=" + id, 
/* 3128 */         true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3135 */     boolean bol = false;
/* 3136 */     JSONObject object = new JSONObject();
/* 3137 */     String id = request.getParameter("id");
/*      */     try {
/* 3139 */       PortalShipOrderManager shipOrderManager = 
/* 3140 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3141 */       PortalShipOrder shipOrder = shipOrderManager
/* 3142 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3143 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3144 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3145 */       bol = true;
/* 3146 */     } catch (Exception e) {
/*      */       
/* 3148 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3153 */     return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3161 */     String id = request.getParameter("id");
/*      */     try {
/* 3163 */       PortalShipOrderManager shipOrderManager = 
/* 3164 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3165 */       PortalShipOrder shipOrder = shipOrderManager
/* 3166 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3167 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3168 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3169 */     } catch (Exception e) {
/*      */       
/* 3171 */       e.printStackTrace();
/*      */     } 
/* 3173 */     return new ActionForward("/viewPortalShipOrderTwo.do?id=" + id, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateNPoCloseIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3180 */     String id = request.getParameter("id");
/*      */     try {
/* 3182 */       PortalShipOrderManager shipOrderManager = 
/* 3183 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3184 */       PortalShipOrder shipOrder = shipOrderManager
/* 3185 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3186 */       shipOrder.setEnabled(EnabledDisabled.DISABLED);
/* 3187 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3188 */     } catch (Exception e) {
/*      */       
/* 3190 */       e.printStackTrace();
/*      */     } 
/* 3192 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id + 
/* 3193 */         "&GrantedSite=1", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateNPoOpenIsEnabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3200 */     String id = request.getParameter("id");
/*      */     try {
/* 3202 */       PortalShipOrderManager shipOrderManager = 
/* 3203 */         ServiceLocator.getPortalShipOrderManager(request);
/* 3204 */       PortalShipOrder shipOrder = shipOrderManager
/* 3205 */         .getPortalShipOrder(Integer.valueOf(Integer.parseInt(id)));
/* 3206 */       shipOrder.setEnabled(EnabledDisabled.ENABLED);
/* 3207 */       shipOrderManager.updatePortalShipOrder(shipOrder);
/* 3208 */     } catch (Exception e) {
/*      */       
/* 3210 */       e.printStackTrace();
/*      */     } 
/* 3212 */     return new ActionForward("/viewPortalShipOrderTwoWrong.do?id=" + id + 
/* 3213 */         "&GrantedSite=1", true);
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
/* 3224 */     PortalShipOrderManager manager = 
/* 3225 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3227 */     String id = request.getParameter("id");
/* 3228 */     manager.deleteDeliveryPo(id);
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
/* 3273 */     return new ActionForward("/listShippingOrder.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteDeliveryNPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3283 */     PortalShipOrderManager manager = 
/* 3284 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3286 */     String id = request.getParameter("id");
/* 3287 */     manager.deleteDeliveryNPo(id);
/* 3288 */     return new ActionForward("/listShippingOrderWrong.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteDeliveryJit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 3298 */     PortalShipOrderManager manager = 
/* 3299 */       ServiceLocator.getPortalShipOrderManager(request);
/*      */     
/* 3301 */     String id = request.getParameter("id");
/* 3302 */     manager.deleteDeliveryJit(id);
/* 3303 */     return new ActionForward("/listShippingOrderProduct.do", true);
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
/*      */   public ActionForward shippingOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3323 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/* 3324 */     BasicDataViewManager manager = ServiceLocator.getBasicDataViewManager(request);
/* 3325 */     String idString = request.getParameter("portalShipJitPartId");
/* 3326 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*      */ 
/*      */       
/* 3329 */       queryForm.setOrder(JitShipPartQueryOrder.highQty.getName());
/* 3330 */       queryForm.setOrder(JitShipPartQueryOrder.lowQty.getName());
/* 3331 */       queryForm.setOrder(JitShipPartQueryOrder.securityQty.getName());
/* 3332 */       queryForm.setOrder(JitShipPartQueryOrder.currentQty.getName());
/* 3333 */       queryForm.setDescend(false);
/*      */     } 
/* 3335 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 3336 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 3337 */     if (queryForm.getHeight() != null && queryForm.getHeight() != "") {
/* 3338 */       conditions.put("a", queryForm.getHeight());
/*      */     }
/* 3340 */     if (queryForm.getLow() != null && queryForm.getLow() != "") {
/* 3341 */       conditions.put("b", queryForm.getLow());
/*      */     }
/* 3343 */     if (queryForm.getEq() != null && queryForm.getEq() != "") {
/* 3344 */       conditions.put("e", queryForm.getEq());
/*      */     }
/* 3346 */     SupplierManager managera = ServiceLocator.getSupplierManager(request);
/* 3347 */     User user = getCurrentUser(request);
/* 3348 */     Supplier supplier = managera.getSupplierByCode(user.getLoginName());
/* 3349 */     if (queryForm.isFirstInit()) {
/* 3350 */       queryForm.init(manager.getJitShipPartListCount(new HashMap<Object, Object>()));
/*      */     } else {
/* 3352 */       queryForm.init();
/*      */     } 
/* 3354 */     int a = Integer.parseInt(queryForm.getPageNo());
/* 3355 */     int b = Integer.parseInt(queryForm.getPageSize());
/* 3356 */     List<JitShipPart> result = manager.getJitShipPartNumberList(conditions, Integer.parseInt(queryForm.getPageNo()), Integer.parseInt(queryForm.getPageSize()), 
/* 3357 */         JitShipPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */     
/* 3359 */     String exportType = queryForm.getExportType();
/* 3360 */     if (exportType != null && exportType.length() > 0) {
/* 3361 */       int index = SessionTempFile.createNewTempFile(request);
/* 3362 */       String fileName = "PortalShipOrder";
/* 3363 */       String suffix = ExportUtil.export(
/* 3364 */           exportType, 
/* 3365 */           result, 
/* 3366 */           request, 
/* 3367 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 3368 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 3371 */               MessageResources messages = PortalShipOrderActiony.this.getResources(request);
/* 3372 */               row.add(
/* 3373 */                   "物料 ");
/* 3374 */               row.add(
/* 3375 */                   "描述");
/* 3376 */               row.add("单位");
/* 3377 */               row.add("属性");
/* 3378 */               row.add("售后件/量产件");
/* 3379 */               row.add("车型 ");
/* 3380 */               row.add("供应商");
/* 3381 */               row.add("描述");
/* 3382 */               row.add("当前库存");
/* 3383 */               row.add("高储库存 ");
/* 3384 */               row.add("低储库存  ");
/* 3385 */               row.add("安全库存 ");
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 3391 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 3392 */               row.add(
/* 3393 */                   BeanUtils.getProperty(data, "part.name"));
/* 3394 */               row.add(
/* 3395 */                   BeanUtils.getProperty(data, "part.unit"));
/*      */               
/* 3397 */               row.add("");
/* 3398 */               row.add(BeanUtils.getProperty(data, "part.drwgLoc"));
/* 3399 */               row.add(BeanUtils.getProperty(data, "part.productSpecifications"));
/*      */ 
/*      */               
/* 3402 */               row.add(BeanUtils.getProperty(data, "part.vend"));
/* 3403 */               row.add("");
/* 3404 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 3405 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 3406 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 3407 */               row.add(BeanUtils.getProperty(data, "part.securityQty"));
/*      */             }
/*      */           });
/* 3410 */       return new ActionForward("download/" + index + "/" + 
/* 3411 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 3415 */     for (JitShipPart ship : result) {
/* 3416 */       if (ship.getQty().compareTo(new BigDecimal(0)) == -1) {
/* 3417 */         ship.setQty(new BigDecimal(0));
/*      */       }
/*      */     } 
/*      */     
/* 3421 */     request.setAttribute("portalShipJitPartId", idString);
/* 3422 */     request.setAttribute("X_RESULTLIST", result);
/* 3423 */     request.setAttribute("x_selType", Integer.valueOf(184));
/* 3424 */     return mapping.findForward("page");
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
/* 3437 */     String msg = request.getParameter("msg");
/*      */     
/* 3439 */     return new ActionForward("page");
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PortalShipOrderActiony.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */