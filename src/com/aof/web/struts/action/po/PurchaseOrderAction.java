/*      */ package com.aof.web.struts.action.po;
/*      */ 
/*      */ import com.aof.model.admin.Department;
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.inventory.InventoryTransit;
/*      */ import com.aof.model.inventory.query.InventoryTransitQueryCondition;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.PurchaseOrderCondimentSingleStatus;
/*      */ import com.aof.model.metadata.PurchaseOrderStatus;
/*      */ import com.aof.model.metadata.StoreRoomType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.PortalShipOrderItem;
/*      */ import com.aof.model.po.PurchaseOrder;
/*      */ import com.aof.model.po.PurchaseOrderCondimentSingle;
/*      */ import com.aof.model.po.PurchaseOrderDetial;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*      */ import com.aof.model.po.query.BoxQueryCondition;
/*      */ import com.aof.model.po.query.PortalShipOrderQueryCondition;
/*      */ import com.aof.model.po.query.PortalShipOrderQueryOrder;
/*      */ import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryCondition;
/*      */ import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryOrder;
/*      */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryCondition;
/*      */ import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
/*      */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*      */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*      */ import com.aof.service.admin.DepartmentManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.inventory.InventoryManager;
/*      */ import com.aof.service.inventory.InventoryTransitManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.PortalShipOrderItemManager;
/*      */ import com.aof.service.po.PurchaseOrderCondimentSingleManager;
/*      */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*      */ import com.aof.service.po.PurchaseOrderManager;
/*      */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*      */ import com.aof.service.quartz.job.DeliverMinuteSyncJob;
/*      */ import com.aof.service.quartz.job.RedMinuteSyncJob;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction2;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.po.PurchaseOrderQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.struts.action.ActionUtils;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.utils.BeanHelper;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.FileOutputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PurchaseOrderAction
/*      */   extends BaseAction2
/*      */ {
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  105 */     RedMinuteSyncJob sss = 
/*  106 */       ServiceLocator.getRedMinuteSyncJobManager(request);
/*  107 */     sss.startSyn();
/*  108 */     DeliverMinuteSyncJob ss = 
/*  109 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  110 */     ss.startSyn();
/*      */     
/*  112 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  113 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  114 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  115 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  118 */     PurchaseOrderInspectionPendingManager fm = 
/*  119 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  120 */     Map conditions = constructQueryMap(queryForm);
/*  121 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  123 */     String exportType = queryForm.getExportType();
/*  124 */     if (StringUtils.isNotEmpty(exportType)) {
/*  125 */       List data = fm.getPurchaseOrderInspectionPendingItemList(
/*  126 */           conditions, 0, -1, 
/*  127 */           PurchaseOrderInspectionPendingQueryOrder.getEnum(queryForm.getOrder()), queryForm
/*  128 */           .isDescend());
/*      */       
/*  130 */       int index = SessionTempFile.createNewTempFile(request);
/*  131 */       String fileName = "purchaseOrder";
/*  132 */       String suffix = ExportUtil.export(
/*  133 */           exportType, 
/*  134 */           data, 
/*  135 */           request, 
/*  136 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  137 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  141 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  142 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.po_number"));
/*  143 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.line"));
/*  144 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.itemNumber.id"));
/*  145 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.itemNumber.describe1"));
/*  146 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.itemNumber.describe2"));
/*  147 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.supplier.code"));
/*  148 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.supplier.name"));
/*  149 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.createDate"));
/*  150 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.qty"));
/*  151 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.receiptQtyn"));
/*  152 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.inventoryNumber"));
/*  153 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "storeRoom.status.color"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  159 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.po_number"));
/*  160 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/*  161 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.id"));
/*  162 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.describe1"));
/*  163 */               row.add(BeanHelper.getBeanPropertyValue(data, "itemNumber.describe2"));
/*  164 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.code"));
/*  165 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.supplier.name"));
/*  166 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.createDate"));
/*  167 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*  168 */               row.add(BeanHelper.getBeanPropertyValue(data, "receiptQty"));
/*  169 */               row.add(BeanHelper.getBeanPropertyValue(data, "inventoryNumber"));
/*  170 */               row.add(BeanHelper.getBeanPropertyValue(data, "poip_number.status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/*  174 */       return new ActionForward("download/" + index + "/" + 
/*  175 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  177 */     if (queryForm.isFirstInit()) {
/*  178 */       queryForm
/*  179 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/*  181 */       queryForm.init();
/*      */     } 
/*  183 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/*  184 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  185 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/*  186 */         queryForm.isDescend());
/*      */     
/*  188 */     request.setAttribute("X_RESULTLIST", result);
/*  189 */     request.setAttribute("x_selType", Integer.valueOf(100));
/*  190 */     putEnumListToRequest(request);
/*  191 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listPurchaseOrderCapacity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  197 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  198 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  199 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  200 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  203 */     PurchaseOrderInspectionPendingManager fm = 
/*  204 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  205 */     Map conditions = constructQueryMap(queryForm);
/*  206 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  208 */     String exportType = queryForm.getExportType();
/*  209 */     if (StringUtils.isNotEmpty(exportType)) {
/*  210 */       List data = fm.getPurchaseOrderInspectionPendingItemList(
/*  211 */           conditions, 0, -1, 
/*  212 */           PurchaseOrderInspectionPendingQueryOrder.getEnum(queryForm.getOrder()), queryForm
/*  213 */           .isDescend());
/*      */       
/*  215 */       int index = SessionTempFile.createNewTempFile(request);
/*  216 */       String fileName = "purchaseOrder";
/*  217 */       String suffix = ExportUtil.export(
/*  218 */           exportType, 
/*  219 */           data, 
/*  220 */           request, 
/*  221 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  222 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  226 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  227 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  228 */                     "purchaseOrder.id"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  233 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  237 */       return new ActionForward("download/" + index + "/" + 
/*  238 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  240 */     if (queryForm.isFirstInit()) {
/*  241 */       queryForm
/*  242 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/*  244 */       queryForm.init();
/*      */     } 
/*  246 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/*  247 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  248 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/*  249 */         queryForm.isDescend());
/*      */     
/*  251 */     request.setAttribute("X_RESULTLIST", result);
/*  252 */     request.setAttribute("x_selType", Integer.valueOf(24));
/*  253 */     putEnumListToRequest(request);
/*  254 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward editPurchaseOrderCapacity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  260 */     response.setContentType("text/json");
/*  261 */     //response.setCharacterEncoding("UTF-8");
/*  262 */     JsonConfig cfg = new JsonConfig();
/*      */     
/*  264 */     PurchaseOrderInspectionPendingManager manager = 
/*  265 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  266 */     String array = request.getParameter("ids");
/*  267 */     String[] arrays = array.split(";"); byte b; int i; String[] arrayOfString1;
/*  268 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/*  269 */       String[] idAndQty = str.split(",");
/*  270 */       String id = idAndQty[0];
/*  271 */       String qty = idAndQty[1];
/*      */       
/*  273 */       PurchaseOrderInspectionPendingItem item = manager
/*  274 */         .getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(id)));
/*  275 */       item.setCapacity(new BigDecimal(qty));
/*  276 */       manager.updatePurchaseOrderInspectionPendingItem(item);
/*      */       b++; }
/*      */     
/*  279 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/*  280 */     response.getWriter().print(jo);
/*  281 */     return null;
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
/*      */   public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  297 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  298 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  299 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  300 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  303 */     PurchaseOrderInspectionPendingManager fm = 
/*  304 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  305 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  307 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  309 */     String exportType = queryForm.getExportType();
/*  310 */     if (StringUtils.isNotEmpty(exportType)) {
/*  311 */       List data = fm.getPurchaseOrderInspectionPendingItemList(
/*  312 */           conditions, 0, -1, 
/*  313 */           PurchaseOrderInspectionPendingQueryOrder.getEnum(queryForm.getOrder()), queryForm
/*  314 */           .isDescend());
/*      */       
/*  316 */       int index = SessionTempFile.createNewTempFile(request);
/*  317 */       String fileName = "purchaseOrder";
/*  318 */       String suffix = ExportUtil.export(
/*  319 */           exportType, 
/*  320 */           data, 
/*  321 */           request, 
/*  322 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  323 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  327 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  328 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  329 */                     "purchaseOrder.id"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  334 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  338 */       return new ActionForward("download/" + index + "/" + 
/*  339 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  341 */     if (queryForm.isFirstInit()) {
/*  342 */       queryForm
/*  343 */         .init(fm.getPurchaseOrderInspectionPendingItemListCount(conditions));
/*      */     } else {
/*  345 */       queryForm.init();
/*      */     } 
/*  347 */     List result = fm.getPurchaseOrderInspectionPendingItemList(conditions, 
/*  348 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  349 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/*  350 */         queryForm.isDescend());
/*      */     
/*  352 */     request.setAttribute("X_RESULTLIST", result);
/*  353 */     request.setAttribute("x_selType", Integer.valueOf(7));
/*  354 */     putEnumListToRequest(request);
/*  355 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listPortalShipOrderConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  361 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  362 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  363 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  364 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  367 */     PortalShipOrderItemManager fm = 
/*  368 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  369 */     Map conditions = constructQueryMap(queryForm);
/*  370 */     conditions.put(PortalShipOrderQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*  371 */     conditions.put(PortalShipOrderQueryCondition.STATUS_CONFIRM_EQ, Integer.valueOf(1));
/*      */     
/*  373 */     getConditionAndOrder(queryForm, conditions, request);
/*      */     
/*  375 */     String exportType = queryForm.getExportType();
/*  376 */     if (StringUtils.isNotEmpty(exportType)) {
/*  377 */       List data = fm.getPortalShipOrderItemList(conditions, 0, -1, 
/*  378 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  379 */           queryForm.isDescend());
/*      */       
/*  381 */       int index = SessionTempFile.createNewTempFile(request);
/*  382 */       String fileName = "purchaseOrder";
/*  383 */       String suffix = ExportUtil.export(
/*  384 */           exportType, 
/*  385 */           data, 
/*  386 */           request, 
/*  387 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  388 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  392 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  393 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  394 */                     "purchaseOrder.id"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  399 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  403 */       return new ActionForward("download/" + index + "/" + 
/*  404 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  406 */     if (queryForm.isFirstInit()) {
/*  407 */       queryForm.init(fm.getPortalShipOrderItemListCount(conditions));
/*      */     } else {
/*  409 */       queryForm.init();
/*      */     } 
/*  411 */     List result = fm.getPortalShipOrderItemList(conditions, 
/*  412 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  413 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  415 */     request.setAttribute("X_RESULTLIST", result);
/*  416 */     request.setAttribute("x_selType", Integer.valueOf(22));
/*  417 */     putEnumListToRequest(request);
/*  418 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePurchaseOrderCondimentConfirmByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  424 */     response.setContentType("text/json");
/*  425 */     //response.setCharacterEncoding("UTF-8");
/*  426 */     JsonConfig cfg = new JsonConfig();
/*      */     
/*  428 */     PurchaseOrderCondimentSingleManager manager = 
/*  429 */       ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/*  430 */     String arrays = request.getParameter("ids");
/*  431 */     StoreRoomType s = StoreRoomType.ENTREPOTSTORAGE;
/*      */     
/*  433 */     manager.updatePurchaseOrderCondimentConfirm(arrays, s);
/*      */     
/*  435 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/*  436 */     response.getWriter().print(jo);
/*  437 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  444 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  445 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  446 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  447 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  450 */     PortalShipOrderItemManager fm = 
/*  451 */       ServiceLocator.getPortalShipOrderItemManager(request);
/*  452 */     Map conditions = constructQueryMap(queryForm);
/*  453 */     conditions.put(PortalShipOrderQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*  454 */     conditions.put(PortalShipOrderQueryCondition.STATUS_CONFIRM_EQ, Integer.valueOf(0));
/*  455 */     getConditionAndOrder(queryForm, conditions, request);
/*  456 */     String exportType = queryForm.getExportType();
/*  457 */     if (StringUtils.isNotEmpty(exportType)) {
/*  458 */       List data = fm.getPortalShipOrderItemList(conditions, 0, -1, 
/*  459 */           PortalShipOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  460 */           queryForm.isDescend());
/*      */       
/*  462 */       int index = SessionTempFile.createNewTempFile(request);
/*  463 */       String fileName = "purchaseOrder";
/*  464 */       String suffix = ExportUtil.export(
/*  465 */           exportType, 
/*  466 */           data, 
/*  467 */           request, 
/*  468 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  469 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  473 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  474 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  475 */                     "purchaseOrder.code"));
/*  476 */               row.add(messages
/*  477 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  478 */                     "purchaseOrder.poipItem.poip_number.po_number"));
/*  479 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  480 */                     "purchaseOrder.poipItem.line"));
/*  481 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  482 */                     "purchaseOrder.poipItem.itemNumber.id"));
/*  483 */               row.add(messages
/*  484 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  485 */                     "purchaseOrder.poipItem.itemNumber.describe1"));
/*  486 */               row.add(messages
/*  487 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  488 */                     "purchaseOrder.poipItem.poip_number.supplier.code"));
/*  489 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  490 */                     "purchaseOrder.portalShipOrder.createDate"));
/*  491 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  492 */                     "purchaseOrder.poipItem.capacity"));
/*  493 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  494 */                     "purchaseOrder.poipItem.qty"));
/*  495 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  496 */                     "purchaseOrder.already_season_qty"));
/*  497 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  498 */                     "purchaseOrder.received_qty"));
/*  499 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  500 */                     "purchaseOrder.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  505 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  506 */                     "portalShipOrder.code"));
/*  507 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  508 */                     "poipItem.poip_number.po_number"));
/*  509 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  510 */                     "poipItem.line"));
/*  511 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  512 */                     "poipItem.itemNumber.id"));
/*  513 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  514 */                     "poipItem.itemNumber.describe1"));
/*  515 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  516 */                     "poipItem.poip_number.supplier.code"));
/*  517 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  518 */                     "portalShipOrder.createDate"));
/*  519 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  520 */                     "poipItem.capacity"));
/*  521 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  522 */                     "poipItem.qty"));
/*      */               
/*  524 */               BigDecimal season_qty = 
/*  525 */                 (BigDecimal)BeanHelper.getBeanPropertyValue(data, 
/*  526 */                   "already_season_qty");
/*      */               
/*  528 */               if (season_qty == null) {
/*  529 */                 season_qty = BigDecimal.valueOf(0.0D);
/*      */               }
/*  531 */               row.add(season_qty);
/*      */               
/*  533 */               BigDecimal received_qty = 
/*  534 */                 (BigDecimal)BeanHelper.getBeanPropertyValue(data, "received_qty");
/*  535 */               BigDecimal qty = received_qty.subtract(season_qty);
/*  536 */               if (received_qty != null || 
/*  537 */                 !received_qty.equals("")) {
/*  538 */                 row.add(qty);
/*      */               }
/*      */               
/*  541 */               row.add(
/*  542 */                   BeanHelper.getBeanPropertyValue(data, 
/*  543 */                     "portalShipOrder.status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/*  547 */       return new ActionForward("download/" + index + "/" + 
/*  548 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  550 */     if (queryForm.isFirstInit()) {
/*  551 */       queryForm.init(fm.getPortalShipOrderItemListCount(conditions));
/*      */     } else {
/*  553 */       queryForm.init();
/*      */     } 
/*  555 */     List result = fm.getPortalShipOrderItemList(conditions, 
/*  556 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  557 */         PortalShipOrderQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  559 */     request.setAttribute("X_RESULTLIST", result);
/*  560 */     request.setAttribute("x_selType", Integer.valueOf(1));
/*  561 */     putEnumListToRequest(request);
/*  562 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listCondiment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  568 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  569 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  570 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  571 */       queryForm.setDescend(false);
/*      */     } 
/*  573 */     PurchaseOrderCondimentSingleManager manager = 
/*  574 */       ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/*  575 */     Map conditions = constructQueryMap(queryForm);
/*  576 */     conditions.put(PurchaseOrderCondimentSingleQueryCondition.ENABLED_EQ, Integer.valueOf(1));
/*      */     
/*  578 */     getConditionAndOrder(queryForm, conditions, request);
/*      */     
/*  580 */     String exportType = queryForm.getExportType();
/*  581 */     if (StringUtils.isNotEmpty(exportType)) {
/*  582 */       List data = manager.getPurchaseOrderCondimentSingleList(conditions, 
/*  583 */           0, -1, 
/*  584 */           PurchaseOrderCondimentSingleQueryOrder.getEnum(queryForm.getOrder()), queryForm
/*  585 */           .isDescend());
/*      */       
/*  587 */       int index = SessionTempFile.createNewTempFile(request);
/*  588 */       String fileName = "purchaseOrder";
/*  589 */       String suffix = ExportUtil.export(
/*  590 */           exportType, 
/*  591 */           data, 
/*  592 */           request, 
/*  593 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  594 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  598 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  599 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  600 */                     "purchaseOrder.code.num"));
/*  601 */               row.add(messages
/*  602 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  603 */                     "purchaseOrder.poipItem.poip_number.po_number"));
/*  604 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  605 */                     "purchaseOrder.poipItem.line"));
/*  606 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  607 */                     "purchaseOrder.poipItem.itemNumber.id"));
/*  608 */               row.add(messages
/*  609 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  610 */                     "purchaseOrder.poipItem.itemNumber.describe1"));
/*  611 */               row.add(messages
/*  612 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  613 */                     "purchaseOrder.poipItem.poip_number.supplier.code"));
/*  614 */               row.add(messages
/*  615 */                   .getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  616 */                     "purchaseOrder.po_detial_id.poip_number.createDate"));
/*  617 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  618 */                     "purchaseOrder.portalShipOrder.createDate"));
/*  619 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  620 */                     "purchaseOrder.poipItem.qty"));
/*  621 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  622 */                     "purchaseOrder.createnumber"));
/*  623 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  624 */                     "purchaseOrder.delivery_qty"));
/*  625 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  626 */                     "purchaseOrder.putIn_qty"));
/*  627 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  628 */                     "purchaseOrder.status.chnShortDescription"));
/*  629 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  630 */                     "purchaseOrder.isPrint"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  636 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  637 */                     "code"));
/*  638 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  639 */                     "po_detial_id.poip_number.po_number"));
/*  640 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  641 */                     "po_detial_id.line"));
/*  642 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  643 */                     "part.id"));
/*  644 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  645 */                     "part.describe1"));
/*  646 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  647 */                     "supplier.code"));
/*  648 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  649 */                     "po_detial_id.poip_number.createDate"));
/*  650 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  651 */                     "date"));
/*  652 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  653 */                     "po_detial_id.qty"));
/*  654 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  655 */                     "number"));
/*  656 */               BigDecimal deqty = 
/*  657 */                 (BigDecimal)BeanHelper.getBeanPropertyValue(data, "delivery_qty");
/*  658 */               if (deqty == null) {
/*  659 */                 deqty = BigDecimal.valueOf(0.0D);
/*      */               }
/*  661 */               row.add(deqty);
/*  662 */               BigDecimal putqty = 
/*  663 */                 (BigDecimal)BeanHelper.getBeanPropertyValue(data, "putIn_qty");
/*  664 */               if (putqty == null) {
/*  665 */                 putqty = BigDecimal.valueOf(0.0D);
/*      */               }
/*  667 */               row.add(putqty);
/*  668 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  669 */                     "status.chnShortDescription"));
/*      */ 
/*      */               
/*  672 */               Integer isPrint = Integer.valueOf(Integer.parseInt(
/*  673 */                     BeanHelper.getBeanPropertyValue(data, "isPrint")
/*  674 */                     .toString()));
/*  675 */               if (isPrint.intValue() == 0) {
/*  676 */                 row.add("未打印");
/*      */               }
/*  678 */               if (isPrint.intValue() == 1) {
/*  679 */                 row.add("已打印");
/*      */               }
/*      */             }
/*      */           });
/*      */       
/*  684 */       return new ActionForward("download/" + index + "/" + 
/*  685 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  687 */     if (queryForm.isFirstInit()) {
/*  688 */       queryForm.init(manager
/*  689 */           .getPurchaseOrderCondimentSingleListCount(conditions));
/*      */     } else {
/*  691 */       queryForm.init();
/*      */     } 
/*  693 */     List result = manager.getPurchaseOrderCondimentSingleList(conditions, 
/*  694 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  695 */         PurchaseOrderCondimentSingleQueryOrder.ID, 
/*  696 */         queryForm.isDescend());
/*      */     
/*  698 */     request.setAttribute("X_RESULTLIST", result);
/*  699 */     request.setAttribute("x_selType", Integer.valueOf(21));
/*  700 */     putEnumListToRequest(request);
/*  701 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printPurchaseOrderCondiment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  707 */     PurchaseOrderCondimentSingleManager manager = 
/*  708 */       ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/*      */     
/*  710 */     String ids = request.getParameter("array");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  721 */     PurchaseOrderCondimentSingle item = new PurchaseOrderCondimentSingle();
/*  722 */     item = manager.getPurchaseOrderCondimentSingle(Integer.valueOf(Integer.parseInt(ids)));
/*  723 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
/*  724 */     request.setAttribute("path", request.getContextPath());
/*  725 */     request.setAttribute("x_date", format.format(new Date()));
/*  726 */     request.setAttribute("item", item);
/*  727 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   public ActionForward condimentPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  764 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  765 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  766 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  767 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  770 */     PurchaseOrderCondimentSingleManager manager = 
/*  771 */       ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/*  772 */     Map conditions = constructQueryMap(queryForm);
/*  773 */     conditions.put(PortalShipOrderQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*      */     
/*  775 */     getConditionAndOrder(queryForm, conditions, request);
/*      */     
/*  777 */     String exportType = queryForm.getExportType();
/*  778 */     if (StringUtils.isNotEmpty(exportType)) {
/*  779 */       List data = manager.getPurchaseOrderCondimentSingleList(conditions, 
/*  780 */           0, -1, 
/*  781 */           PurchaseOrderCondimentSingleQueryOrder.getEnum(queryForm.getOrder()), queryForm
/*  782 */           .isDescend());
/*      */       
/*  784 */       int index = SessionTempFile.createNewTempFile(request);
/*  785 */       String fileName = "purchaseOrder";
/*  786 */       String suffix = ExportUtil.export(
/*  787 */           exportType, 
/*  788 */           data, 
/*  789 */           request, 
/*  790 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  791 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/*  795 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/*  796 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), 
/*  797 */                     "purchaseOrder.id"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  802 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  806 */       return new ActionForward("download/" + index + "/" + 
/*  807 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  809 */     if (queryForm.isFirstInit()) {
/*  810 */       queryForm.init(manager
/*  811 */           .getPurchaseOrderCondimentSingleListCount(conditions));
/*      */     } else {
/*  813 */       queryForm.init();
/*      */     } 
/*  815 */     List result = manager.getPurchaseOrderCondimentSingleList(conditions, 
/*  816 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  817 */         PurchaseOrderCondimentSingleQueryOrder.ID, 
/*  818 */         queryForm.isDescend());
/*      */     
/*  820 */     request.setAttribute("X_RESULTLIST", result);
/*  821 */     request.setAttribute("x_selType", Integer.valueOf(6));
/*  822 */     putEnumListToRequest(request);
/*  823 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  829 */     PurchaseOrderManager sm = 
/*  830 */       ServiceLocator.getPurchaseOrderManager(request);
/*  831 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/*  832 */     Map conditions = constructQueryMap(queryForm);
/*  833 */     queryForm.setPageSize("10");
/*  834 */     if (queryForm.isFirstInit()) {
/*  835 */       queryForm.init(sm.getPurchaseOrderListCount(conditions), 
/*  836 */           queryForm.getPageSizeAsInt());
/*      */     } else {
/*  838 */       queryForm.init();
/*      */     } 
/*      */     
/*  841 */     List result = sm.getPurchaseOrderList(conditions, 
/*  842 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  843 */         PurchaseOrderQueryOrder.getEnum(queryForm.getOrder()), 
/*  844 */         queryForm.isDescend());
/*      */     
/*  846 */     request.setAttribute("X_RESULTLIST", result);
/*  847 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   private Map constructQueryMap(PurchaseOrderQueryForm queryForm) {
/*  851 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  852 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*  853 */     if (id != null && !id.equals("")) {
/*  854 */       conditions.put(PurchaseOrderQueryCondition.ID_EQ, id);
/*      */     }
/*  856 */     String poOrder = queryForm.getPoOrder();
/*  857 */     if (poOrder != null && !poOrder.equals("")) {
/*  858 */       conditions.put(PurchaseOrderQueryCondition.CODE_EQ, poOrder);
/*      */     }
/*  860 */     String site = queryForm.getSite();
/*  861 */     if (site != null && !site.equals("")) {
/*  862 */       conditions.put(PurchaseOrderQueryCondition.SITE_EQ, site);
/*      */     }
/*  864 */     String startDate = queryForm.getStartPoDate();
/*  865 */     if (startDate != null && !startDate.equals("")) {
/*  866 */       conditions.put(PurchaseOrderQueryCondition.STARTPODATE_EQ, 
/*  867 */           startDate);
/*      */     }
/*  869 */     String endDate = queryForm.getEndPoDate();
/*  870 */     if (endDate != null && !endDate.equals("")) {
/*  871 */       conditions.put(PurchaseOrderQueryCondition.ENDPODATE_EQ, endDate);
/*      */     }
/*  873 */     String supplier = queryForm.getSupplier();
/*  874 */     if (supplier != null && !supplier.equals("")) {
/*  875 */       conditions.put(PurchaseOrderQueryCondition.SUPPLIER_EQ, supplier);
/*      */     }
/*  877 */     String status = queryForm.getStatus();
/*  878 */     if (status != null && !status.equals("")) {
/*  879 */       conditions.put(PurchaseOrderQueryCondition.STATUS_EQ, status);
/*      */     }
/*  881 */     String supplierCode = queryForm.getSupplierCode();
/*  882 */     if (supplierCode != null && !supplierCode.equals("")) {
/*  883 */       conditions.put(PurchaseOrderQueryCondition.SUPPLIERCODE_EQ, 
/*  884 */           supplierCode);
/*      */     }
/*  886 */     String department = queryForm.getDepartment();
/*  887 */     if (department != null && !department.equals("")) {
/*  888 */       conditions.put(PurchaseOrderQueryCondition.DEPARTMENT_EQ, 
/*  889 */           department);
/*      */     }
/*      */     
/*  892 */     return conditions;
/*      */   }
/*      */ 
/*      */   
/*      */   private PurchaseOrderInspectionPendingItem getPurchaseOrderInspectionPendingItem(HttpServletRequest request) throws Exception {
/*  897 */     String id = request.getParameter("id");
/*  898 */     PurchaseOrderInspectionPendingManager manager = 
/*  899 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/*  900 */     PurchaseOrderInspectionPendingItem item = manager
/*  901 */       .getPurchaseOrderInspectionPendingItem(Integer.valueOf(Integer.parseInt(id)));
/*  902 */     if (item == null) {
/*  903 */       throw new ActionException("item.notFound", id);
/*      */     }
/*  905 */     return item;
/*      */   }
/*      */ 
/*      */   
/*      */   private PurchaseOrderDetial getPurchaseOrderItem(HttpServletRequest request) throws Exception {
/*  910 */     String id = request.getParameter("id");
/*  911 */     SupplierManager supplierManager = 
/*  912 */       ServiceLocator.getSupplierManager(request);
/*  913 */     PurchaseOrderManager manager = 
/*  914 */       ServiceLocator.getPurchaseOrderManager(request);
/*  915 */     PurchaseOrderDetial detial = manager.getPurchaseOrderDetial(
/*  916 */         Integer.valueOf(Integer.parseInt(id)));
/*      */     
/*  918 */     if (detial == null) {
/*  919 */       throw new ActionException("purchaseOrder.notFound", id);
/*      */     }
/*  921 */     return detial;
/*      */   }
/*      */   
/*      */   private void putEnumListToRequest(HttpServletRequest request) {
/*  925 */     request.setAttribute("X_YESNOLIST", 
/*  926 */         PersistentEnum.getEnumList(YesNo.class));
/*  927 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/*  928 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/*  929 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*  930 */     request.setAttribute("x_status", 
/*  931 */         PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/*      */   }
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  975 */     putEnumListToRequest(request);
/*  976 */     return mapping.findForward("page");
/*      */   }
/*      */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  992 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  998 */     BeanForm purchaseOrderForm = (BeanForm)form;
/*  999 */     PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 1000 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/*      */     
/* 1002 */     PurchaseOrderManager purchaseOrderManager = 
/* 1003 */       ServiceLocator.getPurchaseOrderManager(request);
/*      */ 
/* 1009 */     return new ActionForward("editPurchaseOrder.do?id=" + 
/* 1010 */         purchaseOrder.getId(), true);
/*      */   }
/*      */   
/*      */   public ActionForward closeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1027 */     return null;
/*      */   }
/*      */   
/*      */   public ActionForward createPurchaseOrderIP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1044 */     return null;
/*      */   }
/*      */ 
private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 1049 */     return getAndCheckSite("site_id", request);
/*      */   }
/*      */   
/*      */   private boolean hasSite(HttpServletRequest request) {
/* 1053 */     String s = request.getParameter("site_id");
/* 1054 */     return (s != null && !s.equals(""));
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
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1070 */     DepartmentManager departmentManager = 
/* 1071 */       ServiceLocator.getDepartmentManager(request);
/* 1072 */     Site site = getCurrentUser(request).getPrimarySite();
/* 1073 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*      */     
/* 1075 */     List<Department> list = departmentManager.getDepartmentList(map, -1, 
/* 1076 */         -1, null, true);
/* 1077 */     if (!isBack(request)) {
/* 1078 */       PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 1079 */       BeanForm purchaseOrderForm = (BeanForm)getForm(
/* 1080 */           "/insertPurchaseOrder", request);
/* 1081 */       purchaseOrderForm.populate(purchaseOrder, "to_form");
/*      */     } 
/*      */     
/* 1084 */     request.setAttribute("x_listDept", list);
/* 1085 */     putEnumListToRequest(request);
/* 1086 */     return mapping.findForward("page");
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
/*      */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1102 */     PurchaseOrderManager cm = 
/* 1103 */       ServiceLocator.getPurchaseOrderManager(request);
/* 1104 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 1105 */     PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 1106 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/*      */ 
/*      */     
/* 1109 */     cm.insertPurchaseOrder(purchaseOrder);
/*      */ 
/*      */ 
/*      */     
/* 1113 */     return new ActionForward("editPurchaseOrder.do?id=" + 
/* 1114 */         purchaseOrder.getId(), true);
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
/*      */   public ActionForward editItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1131 */     PurchaseOrderDetial purchaseOrderItem = 
/* 1132 */       getPurchaseOrderItem(request);
/*      */     
/* 1134 */     request.setAttribute("x_purchaseOrderItem", purchaseOrderItem);
/*      */     
/* 1136 */     if (!isBack(request)) {
/*      */       
/* 1138 */       BeanForm purchaseOrderItemForm = (BeanForm)getForm(
/* 1139 */           "/updatePurchaseOrderItem", request);
/* 1140 */       purchaseOrderItemForm.populate(purchaseOrderItem, "to_form");
/*      */     } 
/* 1142 */     putEnumListToRequest(request);
/* 1143 */     return mapping.findForward("page");
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
/*      */   public ActionForward updateItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1159 */     BeanForm purchaseOrderItemForm = (BeanForm)form;
/* 1160 */     PurchaseOrderDetial detial = new PurchaseOrderDetial();
/* 1161 */     purchaseOrderItemForm.populate(detial, "to_bean");
/* 1162 */     PurchaseOrderManager manager = 
/* 1163 */       ServiceLocator.getPurchaseOrderManager(request);
/* 1164 */     manager.updatePurchaseOrderDetial(detial);
/*      */     
/* 1166 */     request.setAttribute("X_OBJECT", 
/* 1167 */         manager.getPurchaseOrderDetial(detial.getId()));
/* 1168 */     request.setAttribute("X_ROWPAGE", "wmspo/purchaseOrder/itemRow.jsp");
/* 1169 */     return mapping.findForward("success");
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
/*      */   public ActionForward newObjectItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1199 */     return mapping.findForward("page");
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
/*      */   public ActionForward insertCondiment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1215 */     response.setContentType("text/json");
/* 1216 */     //response.setCharacterEncoding("UTF-8");
/* 1217 */     JsonConfig cfg = new JsonConfig();
/*      */ 
/*      */     
/* 1220 */     PurchaseOrderCondimentSingleManager manager = 
/* 1221 */       ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/* 1222 */     String arrays = request.getParameter("ids");
/* 1223 */     System.out.println(arrays);
/* 1224 */     manager.insertPurchaseOrderCondimentSingle(arrays);
/*      */ 
/*      */     
/* 1227 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1228 */     response.getWriter().print(jo);
/* 1229 */     return null;
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
/*      */   public ActionForward insertReceiptsByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1245 */     response.setContentType("text/json");
/* 1246 */     //response.setCharacterEncoding("UTF-8");
/* 1247 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1249 */     PurchaseOrderReceiptsManager manager = 
/* 1250 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 1251 */     String arrays = request.getParameter("ids");
/* 1252 */     manager.insertPurchaseOrderReceiptsDetial(arrays);
/*      */     
/* 1254 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1255 */     response.getWriter().print(jo);
/* 1256 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePortalShipOrderWithdraw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1262 */     PortalShipOrderItemManager manager = 
/* 1263 */       ServiceLocator.getPortalShipOrderItemManager(request);
/* 1264 */     String array = request.getParameter("array");
/* 1265 */     manager.updatePortalShipOrderWithdraw(array);
/*      */     
/* 1267 */     return new ActionForward("listPortalShipOrder.do", true);
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
/*      */   public ActionForward listColse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1283 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/* 1284 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1285 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 1286 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1289 */     PurchaseOrderInspectionPendingManager fm = 
/* 1290 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1291 */     Map conditions = constructQueryMap(queryForm);
/* 1292 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 1293 */         Integer.valueOf(1));
/*      */     
/* 1295 */     getConditionAndOrder(queryForm, conditions, request);
/*      */     
/* 1297 */     String exportType = queryForm.getExportType();
/* 1298 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1299 */       List list1 = fm.getPurchaseOrderInspectionPendingList(conditions, 0, 
/* 1300 */           -1, 
/* 1301 */           PurchaseOrderInspectionPendingQueryOrder.getEnum(queryForm.getOrder()), queryForm
/* 1302 */           .isDescend());
/* 1303 */       List data = fm.updatePurchaseOrderInspectionPending(list1);
/*      */       
/* 1305 */       int index = SessionTempFile.createNewTempFile(request);
/* 1306 */       String fileName = "purchaseOrder";
/* 1307 */       String suffix = ExportUtil.export(
/* 1308 */           exportType, 
/* 1309 */           data, 
/* 1310 */           request, 
/* 1311 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1312 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/* 1316 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/* 1317 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.po_number"));
/* 1318 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.supplier.code"));
/* 1319 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.supplier.name"));
/* 1320 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.createDate"));
/* 1321 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.qty"));
/* 1322 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.receiptQty1"));
/* 1323 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.inventoryNumber"));
/* 1324 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "storeRoom.status.color"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 1329 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 1330 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplier.code"));
/* 1331 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplier.name"));
/* 1332 */               row.add(BeanHelper.getBeanPropertyValue(data, "createDate"));
/* 1333 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 1334 */               row.add(BeanHelper.getBeanPropertyValue(data, "receiptQty"));
/* 1335 */               row.add(BeanHelper.getBeanPropertyValue(data, "inventoryNumber"));
/* 1336 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 1340 */       return new ActionForward("download/" + index + "/" + 
/* 1341 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1343 */     if (queryForm.isFirstInit()) {
/* 1344 */       queryForm.init(fm
/* 1345 */           .getPurchaseOrderInspectionPendingListCount(conditions));
/*      */     } else {
/* 1347 */       queryForm.init();
/*      */     } 
/* 1349 */     List result = fm.getPurchaseOrderInspectionPendingList(conditions, 
/* 1350 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1351 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 1352 */         queryForm.isDescend());
/*      */     
/* 1354 */     List resultNew = fm.updatePurchaseOrderInspectionPending(result);
/*      */     
/* 1356 */     request.setAttribute("X_RESULTLIST", resultNew);
/* 1357 */     request.setAttribute("x_selType", Integer.valueOf(31));
/* 1358 */     putEnumListToRequest(request);
/* 1359 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1365 */     response.setContentType("text/json");
/* 1366 */     //response.setCharacterEncoding("UTF-8");
/* 1367 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1369 */     PurchaseOrderInspectionPendingManager manager = 
/* 1370 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1371 */     String array = request.getParameter("arrays");
/* 1372 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 1373 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 1374 */       PurchaseOrderInspectionPending po = manager
/* 1375 */         .getPurchaseOrderInspectionPending(id);
/* 1376 */       po.setStatus(PurchaseOrderStatus.CLOSE);
/* 1377 */       manager.updatePurchaseOrderInspectionPending(po);
/*      */       b++; }
/*      */     
/* 1380 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1381 */     response.getWriter().print(jo);
/* 1382 */     return null;
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
/*      */   public ActionForward listOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1398 */     PurchaseOrderQueryForm queryForm = (PurchaseOrderQueryForm)form;
/* 1399 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1400 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 1401 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1404 */     PurchaseOrderInspectionPendingManager fm = 
/* 1405 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1406 */     Map conditions = constructQueryMap(queryForm);
/* 1407 */     conditions.put(PurchaseOrderInspectionPendingQueryCondition.STATUS_EQ, 
/* 1408 */         Integer.valueOf(2));
/*      */     
/* 1410 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1412 */     String exportType = queryForm.getExportType();
/* 1413 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1414 */       List list1 = fm.getPurchaseOrderInspectionPendingList(conditions, 0, 
/* 1415 */           -1, 
/* 1416 */           PurchaseOrderInspectionPendingQueryOrder.getEnum(queryForm.getOrder()), queryForm
/* 1417 */           .isDescend());
/* 1418 */       List data = fm.updatePurchaseOrderInspectionPending(list1);
/*      */       
/* 1420 */       int index = SessionTempFile.createNewTempFile(request);
/* 1421 */       String fileName = "purchaseOrder";
/* 1422 */       String suffix = ExportUtil.export(
/* 1423 */           exportType, 
/* 1424 */           data, 
/* 1425 */           request, 
/* 1426 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1427 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*      */             {
/* 1431 */               MessageResources messages = PurchaseOrderAction.this.getResources(request);
/* 1432 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.po_number"));
/* 1433 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.supplier.code"));
/* 1434 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.supplier.name"));
/* 1435 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.poip_number.createDate"));
/* 1436 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.qty"));
/* 1437 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.receiptQty1"));
/* 1438 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "purchaseOrder.inventoryNumber"));
/* 1439 */               row.add(messages.getMessage(PurchaseOrderAction.this.getLocale(request), "storeRoom.status.color"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 1444 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 1445 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplier.code"));
/* 1446 */               row.add(BeanHelper.getBeanPropertyValue(data, "supplier.name"));
/* 1447 */               row.add(BeanHelper.getBeanPropertyValue(data, "createDate"));
/* 1448 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 1449 */               row.add(BeanHelper.getBeanPropertyValue(data, "receiptQty"));
/* 1450 */               row.add(BeanHelper.getBeanPropertyValue(data, "inventoryNumber"));
/* 1451 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 1455 */       return new ActionForward("download/" + index + "/" + 
/* 1456 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1458 */     if (queryForm.isFirstInit()) {
/* 1459 */       queryForm.init(fm
/* 1460 */           .getPurchaseOrderInspectionPendingListCount(conditions));
/*      */     } else {
/* 1462 */       queryForm.init();
/*      */     } 
/* 1464 */     List result = fm.getPurchaseOrderInspectionPendingList(conditions, 
/* 1465 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1466 */         PurchaseOrderInspectionPendingQueryOrder.ID, 
/* 1467 */         queryForm.isDescend());
/*      */     
/* 1469 */     List resultNew = fm.updatePurchaseOrderInspectionPending(result);
/*      */     
/* 1471 */     request.setAttribute("X_RESULTLIST", resultNew);
/* 1472 */     request.setAttribute("x_selType", Integer.valueOf(32));
/* 1473 */     putEnumListToRequest(request);
/* 1474 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward open(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1480 */     response.setContentType("text/json");
/* 1481 */     //response.setCharacterEncoding("UTF-8");
/* 1482 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1484 */     PurchaseOrderInspectionPendingManager manager = 
/* 1485 */       ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 1486 */     String array = request.getParameter("arrays");
/* 1487 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 1488 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 1489 */       PurchaseOrderInspectionPending po = manager
/* 1490 */         .getPurchaseOrderInspectionPending(id);
/* 1491 */       po.setStatus(PurchaseOrderStatus.WAIT);
/* 1492 */       manager.updatePurchaseOrderInspectionPending(po);
/*      */       b++; }
/*      */     
/* 1495 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1496 */     response.getWriter().print(jo);
/* 1497 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward updateCondimentIsPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1502 */     response.setContentType("text/json");
/* 1503 */     ////response.setCharacterEncoding("UTF-8");
/* 1504 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1506 */     String ids = request.getParameter("ids");
/* 1507 */     PurchaseOrderCondimentSingleManager condimentManager = ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/* 1508 */     String[] arrays = ids.split(";"); byte b; int i; String[] arrayOfString1;
/* 1509 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1510 */       Integer id = Integer.valueOf(Integer.parseInt(lot));
/* 1511 */       PurchaseOrderCondimentSingle bo = condimentManager.getPurchaseOrderCondimentSingle(id);
/*      */ 
/*      */       
/* 1514 */       bo.setIsPrint(Integer.valueOf(1));
/* 1515 */       condimentManager.updatePurchaseOrderCondimentSingle(bo); b++; }
/*      */     
/* 1517 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1518 */     response.getWriter().print(jo);
/* 1519 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updatePurchaseOrderCondiment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1526 */     response.setContentType("text/json");
///* 1527 */     ////response.setCharacterEncoding("UTF-8");
/* 1528 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/* 1530 */     PurchaseOrderCondimentSingleManager manager = ServiceLocator.getPurchaseOrderCondimentSingleManager(request);
/*      */     
/* 1532 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*      */     
/* 1534 */     PortalShipOrderItemManager itemManager = ServiceLocator.getPortalShipOrderItemManager(request);
/*      */     
/* 1536 */     InventoryTransitManager transitManager = ServiceLocator.getInventoryTransitManager(request);
/* 1537 */     InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
/* 1538 */     Box box = null;
/* 1539 */     PurchaseOrderCondimentSingle sigle = null;
/* 1540 */     int num = 0;
/* 1541 */     BigDecimal decimal = new BigDecimal(0);
/* 1542 */     String ids = request.getParameter("ids");
/* 1543 */     String[] arrays = ids.split(",");
/* 1544 */     BigDecimal big = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
/* 1545 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1546 */       Integer id = Integer.valueOf(Integer.parseInt(lot));
/* 1547 */       sigle = manager.getPurchaseOrderCondimentSingle(id);
/* 1548 */       Integer sigId = sigle.getId();
/* 1549 */       BigDecimal deciQty = sigle.getNumber();
/* 1550 */       conditions.put(BoxQueryCondition.SINGLE_EQ, sigId);
/* 1551 */       List<Box> boxList = boxManager.getBoxList(conditions, 0, -1, null, false);
/* 1552 */       for (int j = 0; j < boxList.size(); j++) {
/* 1553 */         Box box1 = boxList.get(j);
/* 1554 */         String s = box1.getStatus().toString();
/* 1555 */         int st = Integer.parseInt(s);
/* 1556 */         if (st == 1) {
/* 1557 */           num++;
/*      */         }
/*      */       } 
/* 1560 */       for (int k = 0; k < boxList.size(); k++) {
/* 1561 */         box = boxList.get(k);
/* 1562 */         String status = box.getStatus().toString();
/* 1563 */         int statusInt = Integer.parseInt(status);
/* 1564 */         decimal = box.getPsoItem().getAlready_season_qty();
/* 1565 */         if (num == boxList.size()) {
/*      */           
/* 1567 */           box.setEnabled(EnabledDisabled.DISABLED);
/* 1568 */           boxManager.updateBox(box);
/*      */         } else {
/* 1570 */           JSONObject jSONObject = new JSONObject();
/* 1571 */           jSONObject.put("Not", "Not");
/* 1572 */           response.getWriter().print(jSONObject);
/* 1573 */           return null;
/*      */         } 
/*      */       } 
/*      */       
/* 1577 */       BigDecimal bigDecimal1 = decimal.subtract(deciQty);
/* 1578 */       PortalShipOrderItem boxId = box.getPsoItem();
/* 1579 */       Integer bid = boxId.getId();
/* 1580 */       PortalShipOrderItem portalItem = itemManager.getPortalShipOrderItem(bid);
/* 1581 */       portalItem.setAlready_season_qty(bigDecimal1);
/* 1582 */       portalItem.setStatus(YesNo.NO);
/* 1583 */       itemManager.updatePortalShipOrderItem(portalItem);
/* 1584 */       sigle.setStatus(PurchaseOrderCondimentSingleStatus.Withdraw);
/* 1585 */       manager.updatePurchaseOrderCondimentSingle(sigle);
/* 1586 */       Map<Object, Object> condition = new HashMap<Object, Object>();
/* 1587 */       conditions.put(InventoryTransitQueryCondition.PART_CODE_EQ, sigle.getPart().getId());
/* 1588 */       List<InventoryTransit> inventoryTransitList = transitManager.getInventoryTransitList(condition, 0, -1, null, false);
/* 1589 */       if (inventoryTransitList.size() > 0) {
/* 1590 */         InventoryTransit inventoryTransit = inventoryTransitList.get(0);
/* 1591 */         inventoryTransit.setQty(inventoryTransit.getQty().add(deciQty));
/* 1592 */         transitManager.updateInventoryTransit(inventoryTransit);
/*      */       } 
/*      */       
/*      */       b++; }
/*      */     
/* 1597 */     JSONObject ob = new JSONObject();
/* 1598 */     ob.put("ABC", "1");
/* 1599 */     response.getWriter().print(ob);
/* 1600 */     return null;
/*      */   }
/*      */ }