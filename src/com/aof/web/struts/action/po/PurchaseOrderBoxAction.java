/*      */ package com.aof.web.struts.action.po;
/*      */ 
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.basic.BadReasons;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.query.StorageLocationQueryCondition;
/*      */ import com.aof.model.inventory.InventoryMoving;
/*      */ import com.aof.model.metadata.BoxStatus;
/*      */ import com.aof.model.metadata.BoxStatusRqc;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.StoreRoomType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.model.po.PurchaseOrderRqcUnqualified;
/*      */ import com.aof.model.po.query.BoxQueryCondition;
/*      */ import com.aof.model.po.query.BoxQueryOrder;
/*      */ import com.aof.service.admin.InventoryMovingManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.basic.StorageLocationManager;
/*      */ import com.aof.service.basic.WmsToolManager;
/*      */ import com.aof.service.inventory.InventoryManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.ProduceRejectedMaterialManager;
/*      */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*      */ import com.aof.service.po.PurchaseOrderRQCManager;
/*      */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.action.product.ProductGolineAction;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.po.BoxQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.utils.BeanHelper;
/*      */ import com.shcnc.utils.BeanUtils;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.FileOutputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLEncoder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import net.sf.json.JSONArray;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PurchaseOrderBoxAction
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*   99 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  100 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  101 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  102 */       queryForm.setDescend(false);
/*      */     } 
/*  104 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  105 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  107 */     if (queryForm.isFirstInit()) {
/*  108 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  110 */       queryForm.init();
/*      */     } 
/*  112 */     List result = boxManager.getBoxList(conditions, 
/*  113 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  114 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  116 */     request.setAttribute("X_RESULTLIST", result);
/*  117 */     putEnumListToRequest(request);
/*  118 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listInventoryReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  124 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  125 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  126 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  127 */       queryForm.setDescend(false);
/*      */     } 
/*  129 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  130 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  131 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
/*  132 */     conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));
/*  133 */     conditions.put(BoxQueryCondition.NUMBER_GT, Integer.valueOf(0));
/*  134 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  135 */     String exportType = queryForm.getExportType();
/*  136 */     if (StringUtils.isNotEmpty(exportType)) {
/*  137 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/*  138 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/*  139 */           queryForm.isDescend());
/*      */       
/*  141 */       int index = SessionTempFile.createNewTempFile(request);
/*  142 */       String fileName = "listInventoryReport";
/*  143 */       String suffix = ExportUtil.export(
/*  144 */           exportType, 
/*  145 */           data, 
/*  146 */           request, 
/*  147 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  148 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  152 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/*  153 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  154 */                     "Box.lot.id"));
/*  155 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  156 */                     "Box.part.id"));
/*  157 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  158 */                     "Box.part.describe1"));
/*  159 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  160 */                     "Box.po_supp"));
/*  161 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  162 */                     "Box.po_supp_name"));
/*  163 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  164 */                     "DPI"));
/*  165 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  166 */                     "Box.number"));
/*  167 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  168 */                     "Box.location.code"));
/*  169 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  170 */                     "Box.in_date"));
/*  171 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  172 */                     "Box.status_rqc"));
/*  173 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  174 */                     "Box.status_freeze"));
/*  175 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  176 */                     "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  182 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  183 */                     "lot.id"));
/*  184 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  185 */                     "part.id"));
/*  186 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  187 */                     "part.describe1"));
/*  188 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  189 */                     "po_supp"));
/*  190 */               Object suppname = BeanHelper.getBeanPropertyValue(data, 
/*  191 */                   "po_supp_name");
/*  192 */               if (suppname == null) {
/*  193 */                 row.add("");
/*      */               } else {
/*  195 */                 row.add(suppname);
/*      */               } 
/*  197 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  198 */                     "part.dpiNo"));
/*      */               
/*  200 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  201 */                     "number"));
/*  202 */               Object locationCode = 
/*  203 */                 BeanHelper.getBeanPropertyValue(data, "location.code");
/*  204 */               if (locationCode == null) {
/*  205 */                 row.add("");
/*      */               } else {
/*  207 */                 row.add(locationCode);
/*      */               } 
/*      */               
/*  210 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  211 */                     "in_date"));
/*  212 */               String statusrqc = BeanHelper.getBeanPropertyValue(
/*  213 */                   data, "status_rqc").toString();
/*  214 */               String statusshort = 
/*  215 */                 BeanHelper.getBeanPropertyValue(data, 
/*  216 */                   "status_rqc.chnShortDescription")
/*  217 */                 .toString();
/*  218 */               if (statusrqc == null || statusrqc.equals("")) {
/*  219 */                 row.add("待检");
/*      */               } else {
/*  221 */                 row.add(statusshort);
/*      */               } 
/*  223 */               String statusfreeze = 
/*  224 */                 BeanHelper.getBeanPropertyValue(data, "status_freeze")
/*  225 */                 .toString();
/*  226 */               if (statusfreeze.equals("0")) {
/*  227 */                 row.add("已冻结");
/*      */               } else {
/*  229 */                 row.add("未冻结");
/*      */               } 
/*      */               
/*  232 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  233 */                     "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/*  237 */       return new ActionForward("download/" + index + "/" + 
/*  238 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  240 */     if (queryForm.isFirstInit()) {
/*  241 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  243 */       queryForm.init();
/*      */     } 
/*  245 */     List result = boxManager.getBoxList(conditions, 
/*  246 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  247 */         BoxQueryOrder.ID, queryForm.isDescend());
/*  248 */     request.setAttribute("x_selType", Integer.valueOf(106));
/*  249 */     request.setAttribute("X_RESULTLIST", result);
/*  250 */     putEnumListToRequest(request);
/*  251 */     return mapping.findForward("page");
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
/*      */   public ActionForward listMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  267 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  268 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  269 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  270 */       queryForm.setDescend(false);
/*      */     } 
/*  272 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  273 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  274 */     conditions.put(BoxQueryCondition.STA_GT, Integer.valueOf(2));
/*  275 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/*  276 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  277 */     String exportType = queryForm.getExportType();
/*  278 */     if (StringUtils.isNotEmpty(exportType)) {
/*  279 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/*  280 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/*  281 */           queryForm.isDescend());
/*      */       
/*  283 */       int index = SessionTempFile.createNewTempFile(request);
/*  284 */       String fileName = "containerType";
/*  285 */       String suffix = ExportUtil.export(
/*  286 */           exportType, 
/*  287 */           data, 
/*  288 */           request, 
/*  289 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  290 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  294 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/*  295 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  296 */                     "Box.lot.id"));
/*  297 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  298 */                     "Box.part.id"));
/*  299 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  300 */                     "Box.part.describe1"));
/*  301 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  302 */                     "DPI"));
/*  303 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  304 */                     "Box.po_supp"));
/*  305 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  306 */                     "Box.po_supp_name"));
/*  307 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  308 */                     "Box.location.code"));
/*  309 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  310 */                     "Box.number"));
/*  311 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  312 */                     "Box.part.unit"));
/*  313 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  314 */                     "Box.in_date"));
/*  315 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  316 */                     "Box.status_rqc"));
/*  317 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  318 */                     "Box.status_freeze"));
/*  319 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  320 */                     "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  326 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  327 */                     "lot.id"));
/*  328 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  329 */                     "part.id"));
/*  330 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  331 */                     "part.describe1"));
/*  332 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  333 */                     "part.dpiNo"));
/*  334 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  335 */                     "po_supp"));
/*  336 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  337 */                     "po_supp_name"));
/*  338 */               String locationCode = 
/*  339 */                 BeanHelper.getBeanPropertyValue(data, "location.code")
/*  340 */                 .toString();
/*  341 */               row.add("'" + locationCode);
/*  342 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  343 */                     "number"));
/*  344 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  345 */                     "part.unit"));
/*  346 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  347 */                     "in_date"));
/*  348 */               String statusrqc = BeanHelper.getBeanPropertyValue(
/*  349 */                   data, "status_rqc").toString();
/*  350 */               String statusshort = 
/*  351 */                 BeanHelper.getBeanPropertyValue(data, 
/*  352 */                   "status_rqc.chnShortDescription")
/*  353 */                 .toString();
/*  354 */               if (statusrqc == null || statusrqc.equals("")) {
/*  355 */                 row.add("待检");
/*      */               } else {
/*  357 */                 row.add(statusshort);
/*      */               } 
/*  359 */               String statusfreeze = 
/*  360 */                 BeanHelper.getBeanPropertyValue(data, "status_freeze")
/*  361 */                 .toString();
/*  362 */               if (statusfreeze.equals("0")) {
/*  363 */                 row.add("已冻结");
/*      */               } else {
/*  365 */                 row.add("未冻结");
/*      */               } 
/*      */               
/*  368 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  369 */                     "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/*  373 */       return new ActionForward("download/" + index + "/" + 
/*  374 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  376 */     if (queryForm.isFirstInit()) {
/*  377 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  379 */       queryForm.init();
/*      */     } 
/*  381 */     List result = boxManager.getBoxList(conditions, 
/*  382 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  383 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  385 */     request.setAttribute("X_RESULTLIST", result);
/*  386 */     request.setAttribute("x_selType", Integer.valueOf(23));
/*  387 */     putEnumListToRequest(request);
/*  388 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  394 */     String array = request.getParameter("array");
/*      */     
/*  396 */     request.setAttribute("x_array", array);
/*  397 */     return mapping.findForward("page");
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
/*      */   public ActionForward insertMoveLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  414 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  415 */     String array = request.getParameter("array");
/*  416 */     String location = request.getParameter("location");
/*  417 */     String str = "";
/*  418 */     String resu = manager.systemMoveLocation(array, location, 
/*  419 */         getCurrentUser(request));
/*      */     
/*  421 */     if (!resu.equals("ok")) {
/*  422 */       str = String.valueOf(str) + resu + "    ";
/*      */     }
/*  424 */     if (!str.equals(""))
/*      */     {
/*  426 */       request.setAttribute("x_mfg", str);
/*      */     }
/*  428 */     request.setAttribute("x_array", array);
/*  429 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward insertDefeScanningQua(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  436 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  437 */     StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
/*  438 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  439 */     String array = request.getParameter("array");
/*  440 */     String location = request.getParameter("location");
/*  441 */     String[] lot = array.split(","); byte b; int i; String[] arrayOfString1;
/*  442 */     for (i = (arrayOfString1 = lot).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/*  443 */       conditions.clear();
/*  444 */       conditions.put(BoxQueryCondition.LOTSER_EQ, string);
/*  445 */       List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
/*  446 */       Box box = list.get(0);
/*  447 */       box.setStatus_rqc(BoxStatusRqc.QUALIFIED);
/*  448 */       box.setStatus_freeze(YesNo.NO);
/*      */       
/*  450 */       manager.updateBox(box); b++; }
/*      */     
/*  452 */     request.setAttribute("x_array", array);
/*  453 */     return new ActionForward("listPurchaseOrderRQCUnqualified.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward insertDefeScanningNOtQua(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  459 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  460 */     StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
/*  461 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  462 */     String array = request.getParameter("array");
/*  463 */     String location = request.getParameter("location");
/*  464 */     String[] lot = array.split(","); byte b; int i; String[] arrayOfString1;
/*  465 */     for (i = (arrayOfString1 = lot).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/*  466 */       conditions.clear();
/*  467 */       conditions.put(BoxQueryCondition.LOTSER_EQ, string);
/*  468 */       List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
/*  469 */       Box box = list.get(0);
/*  470 */       box.setStatus_rqc(BoxStatusRqc.UNQUALIFIED);
/*  471 */       box.setStatus_freeze(YesNo.YES);
/*      */       
/*  473 */       manager.updateBox(box); b++; }
/*      */     
/*  475 */     request.setAttribute("x_array", array);
/*  476 */     return new ActionForward("listPurchaseOrderRQCUnqualified.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward insertLFLibrary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  483 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  484 */     StorageLocationManager storageManager = ServiceLocator.getStorageLocationManager(request);
/*  485 */     InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
/*  486 */     InventoryMovingManager inventoryMovingManager = ServiceLocator.getInventoryMovingManager(request);
/*  487 */     ProductGolineAction action = new ProductGolineAction();
/*  488 */     InventoryMoving moving = new InventoryMoving();
/*  489 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  490 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*  491 */     String array = request.getParameter("array");
/*  492 */     String location = request.getParameter("location");
/*  493 */     String[] lot = array.split(",");
/*  494 */     conditions.put(BoxQueryCondition.LOTSER_EQ, lot[0]);
/*  495 */     List<Box> list = manager.getBoxList(conditions, 0, -1, null, false);
/*  496 */     Box box = list.get(0);
/*  497 */     StorageLocation loca = box.getLocation();
/*  498 */     WmsPart part = box.getPart();
/*  499 */     String big = box.getNumber().toString();
/*  500 */     int ibig = big.indexOf(".");
/*  501 */     String strbig = big.substring(0, ibig);
/*  502 */     int b = Integer.parseInt(strbig);
/*  503 */     int a = -b;
/*  504 */     int c = b;
/*  505 */     BigDecimal dec = new BigDecimal(c);
/*  506 */     BigDecimal decJian = new BigDecimal(a);
/*      */ 
/*      */     
/*  509 */     map.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, "CK006");
/*  510 */     List<StorageLocation> storagelist = storageManager.getStorageLocationList(map, 0, -1, null, false);
/*  511 */     StorageLocation storage = storagelist.get(0);
/*  512 */     box.setLocation(storage);
/*  513 */     box.setStatus_freeze(YesNo.NO);
/*  514 */     manager.updateBox(box);
/*  515 */     moving.setOld_location(loca);
/*  516 */     moving.setPart(part);
/*  517 */     moving.setNew_location(box.getLocation());
/*  518 */     moving.setQty(box.getNumber());
/*  519 */     moving.setDate(new Date());
/*  520 */     moving.setIs_sync(YesNo.NO);
/*  521 */     moving.setRemark("不良品扫描：" + loca.getCode() + "-" + box.getLocation().getCode() + ",成品号：" + part.getId());
/*  522 */     inventoryMovingManager.insertInventoryMoving(moving);
/*  523 */     ProductGolineAction actiongoline = new ProductGolineAction();
/*  524 */     actiongoline.insertInventoryDetial(part, loca, inventoryManager, decJian);
/*  525 */     actiongoline.insertInventoryDetial(part, box.getLocation(), inventoryManager, dec);
/*  526 */     request.setAttribute("x_array", array);
/*  527 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward selectBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  533 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  534 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  535 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  536 */       queryForm.setDescend(false);
/*      */     } 
/*  538 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  539 */     Map<BoxQueryCondition, String> conditions = constructQueryMap(queryForm);
/*      */     
/*  541 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  542 */     String toGoOutId = request.getParameter("toGoOutId");
/*  543 */     if (toGoOutId != null && toGoOutId.trim().length() > 0) {
/*  544 */       request.setAttribute("toGoOutId", toGoOutId);
/*  545 */       conditions.put(BoxQueryCondition.PART_ID_IN, toGoOutId);
/*  546 */       conditions.put(BoxQueryCondition.STATUS_EQ, BoxStatus.HASBEENINTO);
/*      */     } 
/*  548 */     if (queryForm.isFirstInit()) {
/*  549 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  551 */       queryForm.init();
/*      */     } 
/*  553 */     List result = boxManager.getBoxList(conditions, 
/*  554 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  555 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  557 */     request.setAttribute("X_RESULTLIST", result);
/*  558 */     request.setAttribute("x_close", Boolean.valueOf(true));
/*  559 */     request.setAttribute("x_selType", Integer.valueOf(79));
/*  560 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward productOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  566 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  567 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  568 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  569 */       queryForm.setDescend(false);
/*      */     } 
/*  571 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  572 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  574 */     if (queryForm.isFirstInit()) {
/*  575 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  577 */       queryForm.init();
/*      */     } 
/*  579 */     List result = boxManager.getBoxList(conditions, 
/*  580 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  581 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  583 */     request.setAttribute("X_RESULTLIST", result);
/*  584 */     putEnumListToRequest(request);
/*  585 */     return mapping.findForward("page");
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
/*      */   public ActionForward listProduceRejectedMaterialBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  601 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  602 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  603 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  604 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  607 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  608 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*      */     
/*  610 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(2));
/*  611 */     conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(14));
/*      */     
/*  613 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  615 */     if (queryForm.isFirstInit()) {
/*  616 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  618 */       queryForm.init();
/*      */     } 
/*      */     
/*  621 */     String exportType = queryForm.getExportType();
/*  622 */     if (exportType != null && exportType.length() > 0) {
/*  623 */       List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  624 */       int index = SessionTempFile.createNewTempFile(request);
/*  625 */       String fileName = "box";
/*  626 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  627 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  628 */           new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  631 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/*  632 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/*  633 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/*  634 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/*  635 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/*  636 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
/*  637 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
/*  638 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
/*  639 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp_name"));
/*  640 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
/*  641 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
/*  642 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
/*  643 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
/*  644 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
/*  645 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/*  646 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  651 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/*  652 */               row.add(BeanUtils.getProperty(data, "po_number"));
/*  653 */               row.add(BeanUtils.getProperty(data, "po_line"));
/*  654 */               row.add(BeanUtils.getProperty(data, "part.id"));
/*  655 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/*  656 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/*  657 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/*  658 */               row.add(BeanUtils.getProperty(data, "po_supp_name"));
/*  659 */               row.add(BeanUtils.getProperty(data, "location.code"));
/*  660 */               row.add(BeanUtils.getProperty(data, "number"));
/*  661 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/*  662 */               row.add(BeanUtils.getProperty(data, "po_date"));
/*  663 */               if (BeanUtils.getProperty(data, "status_rqc") != null) {
/*  664 */                 row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/*      */               } else {
/*  666 */                 row.add("未质检");
/*      */               } 
/*  668 */               if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
/*  669 */                 row.add("已冻结");
/*      */               } else {
/*  671 */                 row.add("未冻结");
/*      */               } 
/*  673 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/*  677 */       return new ActionForward("download/" + index + "/" + 
/*  678 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/*  681 */     List result = boxManager.getBoxList(conditions, 
/*  682 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  683 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  685 */     request.setAttribute("boxlist", result);
/*  686 */     request.setAttribute("x_selType", Integer.valueOf(18));
/*  687 */     putEnumListToRequest(request);
/*  688 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseOrderReceiptsBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  694 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  695 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  696 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  697 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  700 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  701 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  702 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*  703 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/*  704 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */     
/*  707 */     String exportType = queryForm.getExportType();
/*  708 */     if (StringUtils.isNotEmpty(exportType)) {
/*  709 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/*  710 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/*  711 */           queryForm.isDescend());
/*  712 */       int index = SessionTempFile.createNewTempFile(request);
/*  713 */       String fileName = "purchaseOrderPutIn";
/*  714 */       String suffix = ExportUtil.export(
/*  715 */           exportType, 
/*  716 */           data, 
/*  717 */           request, 
/*  718 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  719 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  723 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/*  724 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  725 */                     "purchaseOrderPutInStorageBoxList.id"));
/*  726 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  727 */                     "purchaseOrderPutInStorageBoxList.number"));
/*  728 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  729 */                     "purchaseOrderPutInStorageBoxList.row"));
/*  730 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  731 */                     "purchaseOrderPutInStorageBoxList.part.id"));
/*  732 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  733 */                     "purchaseOrderPutInStorageBoxList.code"));
/*  734 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  735 */                     "DPI"));
/*  736 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  737 */                     "purchaseOrderPutInStorageBoxList.po_supp"));
/*      */ 
/*      */ 
/*      */               
/*  741 */               row.add(messages
/*  742 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  743 */                     "purchaseOrderPutInStorageBoxList.part.describe1"));
/*      */               
/*  745 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  746 */                     "purchaseOrderPutInStorageBoxList.num"));
/*  747 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  748 */                     "purchaseOrderPutInStorageBoxList.po_date"));
/*  749 */               row.add(messages
/*  750 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  751 */                     "purchaseOrderPutInStorageBoxList.chnShortDescription"));
/*  752 */               row.add(messages
/*  753 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  754 */                     "purchaseOrderPutInStorageBoxList.status_freeze"));
/*  755 */               row.add(messages
/*  756 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  757 */                     "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  762 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  763 */                     "lot.id"));
/*  764 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  765 */                     "po_number"));
/*  766 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  767 */                     "po_line"));
/*  768 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  769 */                     "part.id"));
/*  770 */               Object object1 = BeanHelper.getBeanPropertyValue(
/*  771 */                   data, "psoItem.portalShipOrder.code");
/*  772 */               Object object2 = BeanHelper.getBeanPropertyValue(
/*  773 */                   data, "single.code");
/*  774 */               if (object1 != null) {
/*  775 */                 row.add(object1);
/*      */               }
/*  777 */               if (object2 != null) {
/*  778 */                 row.add(object2);
/*      */               }
/*  780 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  781 */                     "part.dpiNo"));
/*  782 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  783 */                     "po_supp"));
/*      */ 
/*      */               
/*  786 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  787 */                     "part.describe1"));
/*      */ 
/*      */               
/*  790 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  791 */                     "number"));
/*  792 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  793 */                     "po_date"));
/*  794 */               Object ob1 = BeanHelper.getBeanPropertyValue(data, 
/*  795 */                   "status_rqc");
/*  796 */               Object ob2 = BeanHelper.getBeanPropertyValue(data, 
/*  797 */                   "status_rqc.chnShortDescription");
/*  798 */               if (ob1 == null) {
/*  799 */                 row.add("未质检");
/*      */               }
/*  801 */               if (ob2 != null) {
/*  802 */                 row.add(ob2);
/*      */               }
/*  804 */               Object ob3 = BeanHelper.getBeanPropertyValue(data, 
/*  805 */                   "status_freeze");
/*  806 */               if (ob3 == YesNo.YES) {
/*  807 */                 row.add("已冻结");
/*      */               }
/*  809 */               if (ob3 == YesNo.NO) {
/*  810 */                 row.add("未冻结");
/*      */               }
/*  812 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  813 */                     "status.chnShortDescription"));
/*      */             }
/*      */           });
/*  816 */       return new ActionForward("download/" + index + "/" + 
/*  817 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  822 */     if (queryForm.isFirstInit()) {
/*  823 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  825 */       queryForm.init();
/*      */     } 
/*  827 */     int pageSize = queryForm.getPageSizeAsInt();
/*  828 */     int pageNo = queryForm.getPageNoAsInt();
/*  829 */     List result = boxManager.getBoxList(conditions, pageNo, pageSize, 
/*  830 */         BoxQueryOrder.ID, queryForm.isDescend());
/*  831 */     request.setAttribute("boxlist", result);
/*  832 */     request.setAttribute("x_selType", Integer.valueOf(2));
/*  833 */     putEnumListToRequest(request);
/*  834 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward poRqcBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  841 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  842 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  843 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  844 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  847 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  848 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  849 */     conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));
/*  850 */     conditions.put(BoxQueryCondition.STATUS_NOT_EQ, Integer.valueOf(1));
/*  851 */     conditions.put(BoxQueryCondition.STATUS_RQC_EQ, Integer.valueOf(1));
/*      */     
/*  853 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  855 */     if (queryForm.isFirstInit()) {
/*  856 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/*  858 */       queryForm.init();
/*      */     } 
/*      */     
/*  861 */     String exportType = queryForm.getExportType();
/*  862 */     if (StringUtils.isNotEmpty(exportType)) {
/*  863 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/*  864 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/*  865 */           queryForm.isDescend());
/*  866 */       int index = SessionTempFile.createNewTempFile(request);
/*  867 */       String fileName = "purchaseOrderPutIn";
/*  868 */       String suffix = ExportUtil.export(
/*  869 */           exportType, 
/*  870 */           data, 
/*  871 */           request, 
/*  872 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  873 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  877 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/*  878 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  879 */                     "purchaseOrderPutInStorageBoxList.id"));
/*  880 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  881 */                     "purchaseOrderPutInStorageBoxList.number"));
/*  882 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  883 */                     "purchaseOrderPutInStorageBoxList.row"));
/*  884 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  885 */                     "purchaseOrderPutInStorageBoxList.part.id"));
/*  886 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  887 */                     "purchaseOrderPutInStorageBoxList.code"));
/*  888 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  889 */                     "DPI"));
/*  890 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  891 */                     "purchaseOrderPutInStorageBoxList.po_supp"));
/*      */ 
/*      */               
/*  894 */               row.add(messages
/*  895 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  896 */                     "purchaseOrderPutInStorageBoxList.part.describe1"));
/*  897 */               row.add(messages
/*  898 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  899 */                     "purchaseOrderPutInStorageBoxList.location.code"));
/*  900 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  901 */                     "purchaseOrderPutInStorageBoxList.num"));
/*  902 */               row.add(messages
/*  903 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  904 */                     "Box.part.unit"));
/*  905 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  906 */                     "purchaseOrderPutInStorageBoxList.po_date"));
/*  907 */               row.add(messages
/*  908 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  909 */                     "purchaseOrderPutInStorageBoxList.chnShortDescription"));
/*  910 */               row.add(messages
/*  911 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  912 */                     "purchaseOrderPutInStorageBoxList.status_freeze"));
/*  913 */               row.add(messages
/*  914 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/*  915 */                     "status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  920 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  921 */                     "lot.id"));
/*  922 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  923 */                     "po_number"));
/*  924 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  925 */                     "po_line"));
/*  926 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  927 */                     "part.id"));
/*  928 */               Object object1 = BeanHelper.getBeanPropertyValue(
/*  929 */                   data, "psoItem.portalShipOrder.code");
/*  930 */               Object object2 = BeanHelper.getBeanPropertyValue(
/*  931 */                   data, "single.code");
/*  932 */               if (object1 != null) {
/*  933 */                 row.add(object1);
/*      */               }
/*  935 */               if (object2 != null) {
/*  936 */                 row.add(object2);
/*      */               }
/*  938 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  939 */                     "part.dpiNo"));
/*  940 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  941 */                     "po_supp"));
/*      */               
/*  943 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  944 */                     "part.describe1"));
/*      */               
/*  946 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  947 */                     "location.code"));
/*  948 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  949 */                     "number"));
/*  950 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  951 */                     "part.unit"));
/*  952 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  953 */                     "po_date"));
/*  954 */               Object ob1 = BeanHelper.getBeanPropertyValue(data, 
/*  955 */                   "status_rqc");
/*  956 */               Object ob2 = BeanHelper.getBeanPropertyValue(data, 
/*  957 */                   "status_rqc.chnShortDescription");
/*  958 */               if (ob1 == null) {
/*  959 */                 row.add("未质检");
/*      */               }
/*  961 */               if (ob2 != null) {
/*  962 */                 row.add(ob2);
/*      */               }
/*  964 */               Object ob3 = BeanHelper.getBeanPropertyValue(data, 
/*  965 */                   "status_freeze");
/*  966 */               if (ob3 == YesNo.YES) {
/*  967 */                 row.add("已冻结");
/*      */               }
/*  969 */               if (ob3 == YesNo.NO) {
/*  970 */                 row.add("未冻结");
/*      */               }
/*  972 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/*  973 */                     "status.chnShortDescription"));
/*      */             }
/*      */           });
/*  976 */       return new ActionForward("download/" + index + "/" + 
/*  977 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  982 */     List result = boxManager.getBoxList(conditions, 
/*  983 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  984 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  986 */     request.setAttribute("boxlist", result);
/*  987 */     request.setAttribute("x_selType", Integer.valueOf(25));
/*  988 */     putEnumListToRequest(request);
/*  989 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward poPutInStorageBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  996 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  997 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  998 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  999 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1002 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1003 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1004 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(2));
/* 1005 */     conditions.put(BoxQueryCondition.STATUS_RQC_NOT_EQ, Integer.valueOf(3));
/* 1006 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 1007 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */     
/* 1010 */     String exportType = queryForm.getExportType();
/* 1011 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1012 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/* 1013 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/* 1014 */           queryForm.isDescend());
/* 1015 */       int index = SessionTempFile.createNewTempFile(request);
/* 1016 */       String fileName = "purchaseOrderPutIn";
/* 1017 */       String suffix = ExportUtil.export(
/* 1018 */           exportType, 
/* 1019 */           data, 
/* 1020 */           request, 
/* 1021 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1022 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 1026 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 1027 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1028 */                     "purchaseOrderPutInStorageBoxList.id"));
/* 1029 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1030 */                     "purchaseOrderPutInStorageBoxList.number"));
/* 1031 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1032 */                     "purchaseOrderPutInStorageBoxList.row"));
/* 1033 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1034 */                     "purchaseOrderPutInStorageBoxList.part.id"));
/* 1035 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1036 */                     "purchaseOrderPutInStorageBoxList.code"));
/* 1037 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1038 */                     "DPI"));
/* 1039 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1040 */                     "purchaseOrderPutInStorageBoxList.po_supp"));
/*      */               
/* 1042 */               row.add(messages
/* 1043 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1044 */                     "purchaseOrderPutInStorageBoxList.po_supp_name"));
/* 1045 */               row.add(messages
/* 1046 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1047 */                     "purchaseOrderPutInStorageBoxList.part.describe1"));
/* 1048 */               row.add(messages
/* 1049 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1050 */                     "purchaseOrderPutInStorageBoxList.location.code"));
/* 1051 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1052 */                     "purchaseOrderPutInStorageBoxList.num"));
/* 1053 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1054 */                     "purchaseOrderPutInStorageBoxList.po_date"));
/* 1055 */               row.add(messages
/* 1056 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1057 */                     "purchaseOrderPutInStorageBoxList.chnShortDescription"));
/* 1058 */               row.add(messages
/* 1059 */                   .getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1060 */                     "purchaseOrderPutInStorageBoxList.status_freeze"));
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 1065 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1066 */                     "lot.id"));
/* 1067 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1068 */                     "po_number"));
/* 1069 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1070 */                     "po_line"));
/* 1071 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1072 */                     "part.id"));
/* 1073 */               Object object1 = BeanHelper.getBeanPropertyValue(
/* 1074 */                   data, "psoItem.portalShipOrder.code");
/* 1075 */               Object object2 = BeanHelper.getBeanPropertyValue(
/* 1076 */                   data, "single.code");
/* 1077 */               if (object1 != null) {
/* 1078 */                 row.add(object1);
/*      */               }
/* 1080 */               if (object2 != null) {
/* 1081 */                 row.add(object2);
/*      */               }
/* 1083 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1084 */                     "part.dpiNo"));
/* 1085 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1086 */                     "po_supp"));
/* 1087 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1088 */                     "po_supp_name"));
/* 1089 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1090 */                     "part.describe1"));
/*      */               
/* 1092 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1093 */                     "location.code"));
/* 1094 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1095 */                     "number"));
/* 1096 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1097 */                     "po_date"));
/* 1098 */               Object ob1 = BeanHelper.getBeanPropertyValue(data, 
/* 1099 */                   "status_rqc");
/* 1100 */               Object ob2 = BeanHelper.getBeanPropertyValue(data, 
/* 1101 */                   "status_rqc.chnShortDescription");
/* 1102 */               if (ob1 == null) {
/* 1103 */                 row.add("未质检");
/*      */               }
/* 1105 */               if (ob2 != null) {
/* 1106 */                 row.add(ob2);
/*      */               }
/* 1108 */               Object ob3 = BeanHelper.getBeanPropertyValue(data, 
/* 1109 */                   "status_freeze");
/* 1110 */               if (ob3 == YesNo.YES) {
/* 1111 */                 row.add("已冻结");
/*      */               }
/* 1113 */               if (ob3 == YesNo.NO) {
/* 1114 */                 row.add("未冻结");
/*      */               }
/*      */             }
/*      */           });
/* 1118 */       return new ActionForward("download/" + index + "/" + 
/* 1119 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/* 1122 */     if (queryForm.isFirstInit()) {
/* 1123 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1125 */       queryForm.init();
/*      */     } 
/* 1127 */     List result = boxManager.getBoxList(conditions, 
/* 1128 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1129 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1131 */     request.setAttribute("boxlist", result);
/* 1132 */     request.setAttribute("x_selType", Integer.valueOf(26));
/* 1133 */     putEnumListToRequest(request);
/* 1134 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1141 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1142 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1143 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1144 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1147 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1148 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1149 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*      */     
/* 1151 */     conditions.put(BoxQueryCondition.NUMBER_GT, Integer.valueOf(0));
/* 1152 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 1153 */     conditions.put(BoxQueryCondition.FREEZE_EQ, YesNo.NO);
/* 1154 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1156 */     if (queryForm.isFirstInit()) {
/* 1157 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1159 */       queryForm.init();
/*      */     } 
/* 1161 */     List result = boxManager.getBoxList(conditions, 
/* 1162 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1163 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1165 */     request.setAttribute("boxlist", result);
/* 1166 */     request.setAttribute("x_selType", Integer.valueOf(3));
/* 1167 */     putEnumListToRequest(request);
/* 1168 */     return mapping.findForward("page");
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
/*      */   public ActionForward purchaseOrderHighLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1184 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1185 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1186 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1187 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1190 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1191 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1192 */     conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
/* 1193 */     conditions.put(BoxQueryCondition.PART_TYPE_EQ, Integer.valueOf(2));
/* 1194 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 1195 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1197 */     if (queryForm.isFirstInit()) {
/* 1198 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1200 */       queryForm.init();
/*      */     } 
/* 1202 */     List result = boxManager.getBoxList(conditions, 
/* 1203 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1204 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1206 */     request.setAttribute("boxlist", result);
/* 1207 */     request.setAttribute("x_selType", Integer.valueOf(28));
/* 1208 */     putEnumListToRequest(request);
/* 1209 */     return mapping.findForward("page");
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
/*      */   public ActionForward purchaseOrderHighTwoLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1225 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1226 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1227 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1228 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1231 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1232 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1233 */     conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
/* 1234 */     conditions.put(BoxQueryCondition.PART_TYPE_EQ, Integer.valueOf(1));
/* 1235 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 1236 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1238 */     if (queryForm.isFirstInit()) {
/* 1239 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1241 */       queryForm.init();
/*      */     } 
/* 1243 */     List result = boxManager.getBoxList(conditions, 
/* 1244 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1245 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1247 */     request.setAttribute("boxlist", result);
/* 1248 */     request.setAttribute("x_selType", Integer.valueOf(75));
/* 1249 */     putEnumListToRequest(request);
/* 1250 */     return mapping.findForward("page");
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
/*      */   public ActionForward purchaseOrderBelowLineBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1266 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1267 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1268 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1269 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1272 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1273 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1274 */     conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(12));
/*      */     
/* 1276 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1278 */     if (queryForm.isFirstInit()) {
/* 1279 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1281 */       queryForm.init();
/*      */     } 
/* 1283 */     List result = boxManager.getBoxList(conditions, 
/* 1284 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1285 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1287 */     request.setAttribute("boxlist", result);
/* 1288 */     request.setAttribute("x_selType", Integer.valueOf(29));
/* 1289 */     putEnumListToRequest(request);
/* 1290 */     return mapping.findForward("page");
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
/*      */   public ActionForward highLineBoxOneSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1306 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1307 */     String array = request.getParameter("arrays");
/* 1308 */     String[] arrays = array.split(",");
/* 1309 */     List<Box> listBox = new ArrayList<Box>(); byte b; int i; String[] arrayOfString1;
/* 1310 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/*      */       
/* 1312 */       List<Box> list = manager.gethighLineBoxOneCheck(lot);
/* 1313 */       listBox.addAll(list);
/*      */       b++; }
/*      */     
/* 1316 */     request.setAttribute("x_boxList", listBox);
/* 1317 */     return mapping.findForward("page");
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
/*      */   public ActionForward insertPurchaseOrderHighLineOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1333 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1334 */     String part = request.getParameter("part");
/* 1335 */     String lot = request.getParameter("lots");
/* 1336 */     String partname = request.getParameter("partname");
/* 1337 */     String[] lots = lot.split(",");
/*      */     
/* 1339 */     manager.systemPurchaseOrderHighLineOne(lots, part, 
/* 1340 */         getCurrentUser(request).getId().toString(), partname);
/* 1341 */     return mapping.findForward("success");
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
/*      */   public ActionForward insertPurchaseOrderHighLineTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1357 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1358 */     String array = request.getParameter("arrays");
/* 1359 */     String[] lots = array.split(",");
/*      */     
/* 1361 */     manager.systemPurchaseOrderHighLineTwo(lots, 
/* 1362 */         getCurrentUser(request).getId().toString());
/* 1363 */     return new ActionForward("purchaseOrderHighTwoLineBoxList.do", true);
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
/*      */   public ActionForward validateHighLineTwoByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1379 */     response.setContentType("text/json");
/* 1380 */     response.setCharacterEncoding("UTF-8");
/* 1381 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1383 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1384 */     String array = request.getParameter("id");
/* 1385 */     String[] arrays = array.split(",");
/*      */     
/* 1387 */     boolean sign = true; byte b; int i; String[] arrayOfString1;
/* 1388 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1389 */       List<Box> list = manager.gethighLineBoxOneCheck(lot);
/* 1390 */       if (list.size() > 0) {
/* 1391 */         String newlot = ((Box)list.get(0)).getLot().getId();
/* 1392 */         if (!newlot.equals(lot)) {
/* 1393 */           sign = false;
/*      */         }
/*      */       } 
/*      */       b++; }
/*      */     
/* 1398 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
/* 1399 */     response.getWriter().print(jo);
/* 1400 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseOrderHighLineBoxTwoSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1406 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1407 */     String array = request.getParameter("arrays");
/* 1408 */     String[] lots = array.split(",");
/* 1409 */     List<Box> list = new ArrayList(); byte b; int i; String[] arrayOfString1;
/* 1410 */     for (i = (arrayOfString1 = lots).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1411 */       List<Box> boxs = manager.gethighLineBoxOneCheck(lot);
/* 1412 */       list.addAll(boxs);
/*      */       b++; }
/*      */     
/* 1415 */     request.setAttribute("x_boxList", list);
/* 1416 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1422 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1423 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1424 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1425 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1428 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1429 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 1430 */     conditions.put(BoxQueryCondition.ISENABLED_EQ, Integer.valueOf(1));
/*      */     
/* 1432 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1434 */     if (queryForm.isFirstInit()) {
/* 1435 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1437 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/* 1441 */     String exportType = queryForm.getExportType();
/* 1442 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1443 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/* 1444 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/* 1445 */           queryForm.isDescend());
/*      */       
/* 1447 */       int index = SessionTempFile.createNewTempFile(request);
/* 1448 */       String fileName = "purchaseOrder";
/* 1449 */       String suffix = ExportUtil.export(
/* 1450 */           exportType, 
/* 1451 */           data, 
/* 1452 */           request, 
/* 1453 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1454 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 1458 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 1459 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1460 */                     "Box.lot.id"));
/* 1461 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1462 */                     "Box.po_number"));
/* 1463 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1464 */                     "Box.po_line"));
/* 1465 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1466 */                     "part.id"));
/* 1467 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1468 */                     "DPI"));
/* 1469 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1470 */                     "part.describe1"));
/* 1471 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1472 */                     "part.describe2"));
/* 1473 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1474 */                     "Box.number"));
/* 1475 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1476 */                     "Box.location.code"));
/* 1477 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1478 */                     "Box.in_date"));
/* 1479 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1480 */                     "Box.out_date"));
/* 1481 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1482 */                     "Box.status_rqc"));
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 1489 */               row.add(BeanHelper.getBeanPropertyValue(data, "lot.id"));
/* 1490 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 1491 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_line"));
/* 1492 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 1493 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 1494 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 1495 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 1496 */               row.add(BeanHelper.getBeanPropertyValue(data, "number"));
/* 1497 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 1498 */               row.add(BeanHelper.getBeanPropertyValue(data, "in_date"));
/* 1499 */               row.add(BeanHelper.getBeanPropertyValue(data, "out_date"));
/* 1500 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/* 1503 */       return new ActionForward("download/" + index + "/" + 
/* 1504 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 1508 */     List result = boxManager.getBoxList(conditions, 
/* 1509 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1510 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1512 */     request.setAttribute("X_RESULTLIST", result);
/* 1513 */     request.setAttribute("x_selType", Integer.valueOf(15));
/* 1514 */     putEnumListToRequest(request);
/* 1515 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listBoxReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1521 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1522 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1523 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1524 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1527 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1528 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/* 1530 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1532 */     if (queryForm.isFirstInit()) {
/* 1533 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1535 */       queryForm.init();
/*      */     } 
/*      */     
/* 1538 */     String exportType = queryForm.getExportType();
/* 1539 */     if (exportType != null && exportType.length() > 0) {
/* 1540 */       List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 1541 */       int index = SessionTempFile.createNewTempFile(request);
/* 1542 */       String fileName = "box";
/* 1543 */       String suffix = ExportUtil.export(
/* 1544 */           exportType, 
/* 1545 */           data, 
/* 1546 */           request, 
/* 1547 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1548 */               request)), new Exportable()
/*      */           {
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 1553 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 1554 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/* 1555 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/* 1556 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/* 1557 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.id"));
/* 1558 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/* 1559 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
/* 1560 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
/* 1561 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
/* 1562 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
/* 1563 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
/* 1564 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
/* 1565 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
/* 1566 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
/* 1567 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/* 1568 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1575 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 1576 */               row.add(BeanUtils.getProperty(data, "po_number"));
/* 1577 */               row.add(BeanUtils.getProperty(data, "po_line"));
/* 1578 */               if (BeanUtils.getProperty(data, "psoItem") != null) {
/* 1579 */                 row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
/*      */               } else {
/* 1581 */                 row.add(BeanUtils.getProperty(data, "single.code"));
/*      */               } 
/* 1583 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 1584 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/* 1585 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/* 1586 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 1587 */               row.add(BeanUtils.getProperty(data, "location.code"));
/* 1588 */               row.add(BeanUtils.getProperty(data, "number"));
/* 1589 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/* 1590 */               row.add(BeanUtils.getProperty(data, "po_date"));
/* 1591 */               if (BeanUtils.getProperty(data, "status_rqc") != null) {
/* 1592 */                 row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/*      */               } else {
/* 1594 */                 row.add("待检");
/*      */               } 
/* 1596 */               row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
/* 1597 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 1601 */       return new ActionForward("download/" + index + "/" + 
/* 1602 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 1606 */     List result = boxManager.getBoxList(conditions, 
/* 1607 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1608 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1610 */     putEnumListToRequest(request);
/* 1611 */     request.setAttribute("X_RESULTLIST", result);
/* 1612 */     request.setAttribute("x_selType", Integer.valueOf(89));
/* 1613 */     return mapping.findForward("page");
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
/*      */   public ActionForward listBoxAbolition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1629 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 1630 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1631 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 1632 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 1635 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1636 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/* 1638 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1640 */     if (queryForm.isFirstInit()) {
/* 1641 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 1643 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/* 1647 */     String exportType = queryForm.getExportType();
/* 1648 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1649 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/* 1650 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/* 1651 */           queryForm.isDescend());
/*      */       
/* 1653 */       int index = SessionTempFile.createNewTempFile(request);
/* 1654 */       String fileName = "purchaseOrder";
/* 1655 */       String suffix = ExportUtil.export(
/* 1656 */           exportType, 
/* 1657 */           data, 
/* 1658 */           request, 
/* 1659 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 1660 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 1664 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 1665 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1666 */                     "Box.lot.id"));
/* 1667 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1668 */                     "Box.po_number"));
/* 1669 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1670 */                     "Box.po_line"));
/* 1671 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1672 */                     "Box.psoItem"));
/* 1673 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1674 */                     "Box.part.id"));
/* 1675 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1676 */                     "DPI"));
/* 1677 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1678 */                     "Box.po_supp"));
/* 1679 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1680 */                     "Box.part.describe1"));
/* 1681 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1682 */                     "InventoryTransit.location.code"));
/* 1683 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1684 */                     "Box.number"));
/* 1685 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1686 */                     "InventoryMoving.part.unit"));
/* 1687 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1688 */                     "Box.po_date"));
/* 1689 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1690 */                     "Box.status_rqc"));
/* 1691 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1692 */                     "Box.status_freeze.chnShortDescription"));
/* 1693 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1694 */                     "Box.status.chnShortDescription"));
/* 1695 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 1696 */                     "Box.isPrint.chnDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 1702 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1703 */                     "lot.id"));
/* 1704 */               Object po_number = BeanHelper.getBeanPropertyValue(
/* 1705 */                   data, "po_number");
/* 1706 */               if (po_number != null) {
/* 1707 */                 row.add(po_number);
/*      */               } else {
/* 1709 */                 row.add("");
/*      */               } 
/* 1711 */               Object po_line = BeanHelper.getBeanPropertyValue(
/* 1712 */                   data, "po_line");
/* 1713 */               if (po_line != null) {
/* 1714 */                 row.add(po_line);
/*      */               } else {
/* 1716 */                 row.add("");
/*      */               } 
/* 1718 */               Object psoItem = BeanHelper.getBeanPropertyValue(
/* 1719 */                   data, "psoItem");
/* 1720 */               Object single = BeanHelper.getBeanPropertyValue(
/* 1721 */                   data, "single");
/* 1722 */               if (psoItem == null && single == null) {
/* 1723 */                 row.add("");
/*      */               } else {
/*      */                 
/* 1726 */                 if (psoItem != null)
/*      */                 {
/*      */ 
/*      */                   
/* 1730 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1731 */                         "psoItem.portalShipOrder.code"));
/*      */                 }
/*      */                 
/* 1734 */                 if (single != null)
/*      */                 {
/*      */ 
/*      */                   
/* 1738 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1739 */                         "single.code"));
/*      */                 }
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 1745 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1746 */                     "part.id"));
/* 1747 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1748 */                     "part.dpiNo"));
/* 1749 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1750 */                     "po_supp"));
/* 1751 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1752 */                     "part.describe1"));
/* 1753 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1754 */                     "location.code"));
/* 1755 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1756 */                     "number"));
/* 1757 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1758 */                     "part.unit"));
/* 1759 */               Object po_date = BeanHelper.getBeanPropertyValue(
/* 1760 */                   data, "po_date");
/* 1761 */               if (po_date != null) {
/* 1762 */                 row.add(po_date);
/*      */               } else {
/* 1764 */                 row.add("");
/*      */               } 
/* 1766 */               Object status_rqc = 
/* 1767 */                 BeanHelper.getBeanPropertyValue(data, "status_rqc");
/* 1768 */               if (status_rqc == null) {
/* 1769 */                 row.add("未质检");
/*      */               }
/* 1771 */               if (status_rqc != null) {
/* 1772 */                 row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1773 */                       "status_rqc.chnShortDescription"));
/*      */               }
/*      */               
/* 1776 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1777 */                     "status_freeze.chnShortDescription"));
/* 1778 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 1779 */                     "status.chnShortDescription"));
/* 1780 */               Object isPrint = BeanHelper.getBeanPropertyValue(
/* 1781 */                   data, "isPrint.chnDescription");
/* 1782 */               if (isPrint != null) {
/*      */                 
/* 1784 */                 if (isPrint == YesNo.NO) {
/* 1785 */                   row.add("未打印");
/*      */                 } else {
/* 1787 */                   row.add("已打印");
/*      */                 } 
/*      */               } else {
/* 1790 */                 row.add("");
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/* 1795 */       return new ActionForward("download/" + index + "/" + 
/* 1796 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1798 */     List result = boxManager.getBoxList(conditions, 
/* 1799 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1800 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1802 */     request.setAttribute("X_RESULTLIST", result);
/* 1803 */     request.setAttribute("x_selType", Integer.valueOf(27));
/* 1804 */     putEnumListToRequest(request);
/* 1805 */     return mapping.findForward("page");
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
/*      */   public ActionForward boxAbolitionByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1820 */     response.setContentType("text/json");
/* 1821 */     response.setCharacterEncoding("UTF-8");
/* 1822 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1824 */     String array = request.getParameter("ids");
/* 1825 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1826 */     manager.boxAbolition(array);
/*      */     
/* 1828 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1829 */     response.getWriter().print(jo);
/* 1830 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward updateBoxByRemarkAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1835 */     response.setContentType("text/json");
/* 1836 */     response.setCharacterEncoding("UTF-8");
/* 1837 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1839 */     String id = request.getParameter("id");
/* 1840 */     String remark = request.getParameter("remark");
/* 1841 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 1842 */     Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 1843 */     box.setRemark(remark.trim());
/* 1844 */     manager.updateBox(box);
/* 1845 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1846 */     response.getWriter().print(jo);
/* 1847 */     return null;
/*      */   }
/*      */   
/*      */   private Map constructQueryMap(BoxQueryForm queryForm) {
/* 1851 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1852 */     String lot = queryForm.getLotser();
/* 1853 */     if (lot != null && !lot.equals("")) {
/* 1854 */       conditions.put(BoxQueryCondition.LOTSER_EQ, lot);
/*      */     }
/*      */     
/* 1857 */     return conditions;
/*      */   }
/*      */   
/*      */   private Box getBox(HttpServletRequest request) throws Exception {
/* 1861 */     String id = request.getParameter("id");
/* 1862 */     BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);
/* 1863 */     Box purchaseOrder = purchaseOrderManager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 1864 */     if (purchaseOrder == null)
/* 1865 */       throw new ActionException("purchaseOrder.notFound", id); 
/* 1866 */     return purchaseOrder;
/*      */   }
/*      */   
/*      */   private void putEnumListToRequest(HttpServletRequest request) {
/* 1870 */     request.setAttribute("X_YESNOLIST", 
/* 1871 */         PersistentEnum.getEnumList(YesNo.class));
/* 1872 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1878 */     Box purchaseOrder = getBox(request);
/* 1879 */     SupplierManager supplierManager = 
/* 1880 */       ServiceLocator.getSupplierManager(request);
/* 1881 */     request.setAttribute("x_purchaseOrderBox", purchaseOrder);
/* 1882 */     if (!isBack(request)) {
/*      */       
/* 1884 */       BeanForm purchaseOrderForm = (BeanForm)getForm(
/* 1885 */           "/updatePurchaseOrderBox", request);
/* 1886 */       purchaseOrderForm.populate(purchaseOrder, "to_form");
/*      */     } 
/* 1888 */     putEnumListToRequest(request);
/* 1889 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   private static List removeDuplicateWithOrder(List list) {
/* 1893 */     Set<Object> set = new HashSet();
/* 1894 */     List<Object> newList = new ArrayList();
/* 1895 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/* 1896 */       Object element = iter.next();
/* 1897 */       if (set.add(element))
/* 1898 */         newList.add(element); 
/*      */     } 
/* 1900 */     return newList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateDevanningBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1906 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 1907 */     Box purchaseOrder = new Box();
/* 1908 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/* 1909 */     BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);
/*      */     
/* 1911 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateExchangeBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1917 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 1918 */     Box purchaseOrder = new Box();
/* 1919 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/* 1920 */     BoxManager purchaseOrderManager = ServiceLocator.getBoxManager(request);
/*      */     
/* 1922 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1928 */     BoxManager BoxManager = ServiceLocator.getBoxManager(request);
/* 1929 */     Site site = getCurrentUser(request).getPrimarySite();
/* 1930 */     List<Box> list = BoxManager.getBoxList(null, 0, -1, null, false);
/* 1931 */     if (!isBack(request)) {
/* 1932 */       Box Box = new Box();
/* 1933 */       BeanForm BoxForm = (BeanForm)getForm("/insertPurchaseOrderBox", 
/* 1934 */           request);
/* 1935 */       BoxForm.populate(Box, "to_form");
/*      */     } 
/*      */     
/* 1938 */     request.setAttribute("x_listDept", list);
/*      */     
/* 1940 */     putEnumListToRequest(request);
/* 1941 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseOrderRqcDetermine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1947 */     WmsToolManager manager = ServiceLocator.getWmsToolManager(request);
/* 1948 */     List<BadReasons> reasons = manager.getBadReasons();
/* 1949 */     StorageLocationManager locationManager = 
/* 1950 */       ServiceLocator.getStorageLocationManager(request);
/* 1951 */     PurchaseOrderPutInStorageManager storageManager = 
/* 1952 */       ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 1953 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 1954 */     map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(2));
/* 1955 */     List list = locationManager.getStorageLocationList(map, -1, -1, null, 
/* 1956 */         true);
/*      */     
/* 1958 */     String type = request.getParameter("type");
/* 1959 */     String arrays = request.getParameter("arrays");
/* 1960 */     String sign = "false";
/* 1961 */     if (type.equals("4") && type != null) {
/* 1962 */       List<StorageLocation> locations = storageManager
/* 1963 */         .getRecommendLocationList();
/* 1964 */       request.setAttribute("x_partLocations", locations);
/*      */     } 
/* 1966 */     if (type.equals("5") && type != null) {
/* 1967 */       map.clear();
/* 1968 */       map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(14));
/* 1969 */       List listBlp = locationManager.getStorageLocationList(map, -1, -1, null, 
/* 1970 */           true);
/* 1971 */       request.setAttribute("x_blpLocations", listBlp);
/*      */     } 
/*      */     
/* 1974 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1975 */     List<Box> boxList = new ArrayList<Box>();
/* 1976 */     String[] str = arrays.split(",");
/* 1977 */     BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
/* 1978 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 1979 */       Box box = boxManager.getBoxBylotSer2(lot);
/* 1980 */       boxList.add(box);
/* 1981 */       sum = sum.add(box.getNumber());
/*      */       b++; }
/*      */     
/* 1984 */     request.setAttribute("x_list", list);
/* 1985 */     request.setAttribute("x_reasons", reasons);
/* 1986 */     request.setAttribute("x_arrays", arrays);
/* 1987 */     request.setAttribute("x_type", type);
/* 1988 */     request.setAttribute("x_boxList", boxList);
/* 1989 */     request.setAttribute("x_objsize", Integer.valueOf(boxList.size()));
/* 1990 */     request.setAttribute("x_sum", sum);
/* 1991 */     return mapping.findForward("page");
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
/*      */   public ActionForward updatePurchaseOrderRqcDetermine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2007 */     String arrays = request.getParameter("arrays");
/* 2008 */     String location = request.getParameter("location");
/* 2009 */     String type = request.getParameter("type");
/* 2010 */     String rqcType = request.getParameter("rqcType");
/* 2011 */     String strList = request.getParameter("strList");
/*      */     
/* 2013 */     PurchaseOrderReceiptsManager managerPor = 
/* 2014 */       ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 2015 */     PurchaseOrderRQCManager managerRqc = 
/* 2016 */       ServiceLocator.getPurchaseOrderRQCManager(request);
/* 2017 */     PurchaseOrderPutInStorageManager storageManager = 
/* 2018 */       ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/*      */     
/* 2020 */     String str = "";
/* 2021 */     if (type.equals("2")) {
/* 2022 */       str = managerPor.systemPurchaseOrderReceipts(arrays, location, 
/* 2023 */           getCurrentUser(request));
/* 2024 */       if (!str.equals("") && !str.equals("ok"))
/*      */       {
/* 2026 */         request.setAttribute("x_mfg", str);
/*      */       }
/* 2028 */     } else if (type.equals("3")) {
/* 2029 */       String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 2030 */       for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 2031 */         str = managerRqc.systemPurchaseOrderRQC(array, rqcType, strList, 
/* 2032 */             getCurrentUser(request));
/*      */         b++; }
/*      */     
/* 2035 */     } else if (type.equals("5")) {
/* 2036 */       String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 2037 */       for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 2038 */         String resu = storageManager.scanningPurchaseOrderBLPInStorage(array, 
/* 2039 */             location, getCurrentUser(request).getId()
/* 2040 */             .toString());
/* 2041 */         if (!resu.equals("ok") && !resu.equals(str))
/* 2042 */           str = String.valueOf(str) + resu; 
/*      */         b++; }
/*      */       
/* 2045 */       if (!str.equals(""))
/*      */       {
/* 2047 */         request.setAttribute("x_mfg", str);
/*      */       }
/*      */     } else {
/*      */       
/* 2051 */       String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 2052 */       for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 2053 */         String resu = storageManager.scanningPurchaseOrderPutInStorage(array, 
/* 2054 */             location, getCurrentUser(request).getId()
/* 2055 */             .toString());
/* 2056 */         if (!resu.equals("ok") && !resu.equals(str))
/* 2057 */           str = String.valueOf(str) + resu; 
/*      */         b++; }
/*      */       
/* 2060 */       if (!str.equals(""))
/*      */       {
/* 2062 */         request.setAttribute("x_mfg", str);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2067 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward scanningProductOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2073 */     String array = request.getParameter("array");
/* 2074 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 2075 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 2076 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lotSer = arrayOfString1[b];
/* 2077 */       manager.scanningProductOutbound(lotSer, 
/* 2078 */           getCurrentUser(request).getId().toString());
/*      */       b++; }
/*      */     
/* 2081 */     return mapping.findForward("success");
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
/*      */   public ActionForward listProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2097 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2098 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2099 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2100 */       queryForm.setDescend(false);
/*      */     } 
/* 2102 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2103 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 2104 */     conditions.put(BoxQueryCondition.TYPE_EQ, Integer.valueOf(0));
/*      */     
/* 2106 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2108 */     if (queryForm.isFirstInit()) {
/* 2109 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2111 */       queryForm.init();
/*      */     } 
/* 2113 */     List result = boxManager.getBoxList(conditions, 
/* 2114 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2115 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2117 */     request.setAttribute("X_RESULTLIST", result);
/* 2118 */     request.setAttribute("x_selType", Integer.valueOf(4));
/* 2119 */     putEnumListToRequest(request);
/* 2120 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listPurchaseOrderLotPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2127 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2128 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2129 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2130 */       queryForm.setDescend(false);
/*      */     } 
/* 2132 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2133 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 2134 */     conditions.put(BoxQueryCondition.ENDTIME_EQ, Integer.valueOf(1));
/* 2135 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2137 */     String exportType = queryForm.getExportType();
/* 2138 */     if (StringUtils.isNotEmpty(exportType)) {
/* 2139 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/* 2140 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/* 2141 */           queryForm.isDescend());
/*      */       
/* 2143 */       int index = SessionTempFile.createNewTempFile(request);
/* 2144 */       String fileName = "purchaseOrder";
/* 2145 */       String suffix = ExportUtil.export(
/* 2146 */           exportType, 
/* 2147 */           data, 
/* 2148 */           request, 
/* 2149 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2150 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 2154 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 2155 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2156 */                     "Box.lot.id"));
/* 2157 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2158 */                     "Box.po_number"));
/* 2159 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2160 */                     "Box.po_line"));
/* 2161 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2162 */                     "Box.psoItem"));
/* 2163 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2164 */                     "Box.part.id"));
/* 2165 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2166 */                     "DPI"));
/* 2167 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2168 */                     "Box.po_supp"));
/* 2169 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2170 */                     "Box.part.describe1"));
/* 2171 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2172 */                     "Box.number"));
/* 2173 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2174 */                     "Box.po_date"));
/* 2175 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2176 */                     "Box.status_rqc"));
/* 2177 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2178 */                     "Box.status_freeze.chnShortDescription"));
/* 2179 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2180 */                     "Box.status.chnShortDescription"));
/* 2181 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2182 */                     "Box.isPrint.chnDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 2188 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2189 */                     "lot.id"));
/* 2190 */               Object po_number = BeanHelper.getBeanPropertyValue(
/* 2191 */                   data, "po_number");
/* 2192 */               if (po_number != null) {
/* 2193 */                 row.add(po_number);
/*      */               } else {
/* 2195 */                 row.add("");
/*      */               } 
/* 2197 */               Object po_line = BeanHelper.getBeanPropertyValue(
/* 2198 */                   data, "po_line");
/* 2199 */               if (po_line != null) {
/* 2200 */                 row.add(po_line);
/*      */               } else {
/* 2202 */                 row.add("");
/*      */               } 
/* 2204 */               Object psoItem = BeanHelper.getBeanPropertyValue(
/* 2205 */                   data, "psoItem");
/* 2206 */               Object single = BeanHelper.getBeanPropertyValue(
/* 2207 */                   data, "single");
/* 2208 */               if (psoItem == null && single == null) {
/* 2209 */                 row.add("");
/*      */               } else {
/*      */                 
/* 2212 */                 if (psoItem != null)
/*      */                 {
/*      */ 
/*      */                   
/* 2216 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2217 */                         "psoItem.portalShipOrder.code"));
/*      */                 }
/* 2219 */                 if (single != null)
/*      */                 {
/*      */ 
/*      */                   
/* 2223 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2224 */                         "single.code"));
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/* 2229 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2230 */                     "part.id"));
/* 2231 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2232 */                     "part.dpiNo"));
/* 2233 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2234 */                     "po_supp"));
/* 2235 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2236 */                     "part.describe1"));
/* 2237 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2238 */                     "number"));
/* 2239 */               Object po_date = BeanHelper.getBeanPropertyValue(
/* 2240 */                   data, "po_date");
/* 2241 */               if (po_date != null) {
/* 2242 */                 row.add(po_date);
/*      */               } else {
/* 2244 */                 row.add("");
/*      */               } 
/* 2246 */               Object status_rqc = 
/* 2247 */                 BeanHelper.getBeanPropertyValue(data, "status_rqc");
/* 2248 */               if (status_rqc == null) {
/* 2249 */                 row.add("未质检");
/*      */               }
/* 2251 */               if (status_rqc != null) {
/* 2252 */                 row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2253 */                       "status_rqc.chnShortDescription"));
/*      */               }
/*      */               
/* 2256 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2257 */                     "status_freeze.chnShortDescription"));
/* 2258 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2259 */                     "status.chnShortDescription"));
/* 2260 */               Object isPrint = BeanHelper.getBeanPropertyValue(
/* 2261 */                   data, "isPrint.chnDescription");
/* 2262 */               if (isPrint != null) {
/*      */                 
/* 2264 */                 if (isPrint == YesNo.NO) {
/* 2265 */                   row.add("未打印");
/*      */                 } else {
/* 2267 */                   row.add("已打印");
/*      */                 } 
/*      */               } else {
/* 2270 */                 row.add("");
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/* 2275 */       return new ActionForward("download/" + index + "/" + 
/* 2276 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 2280 */     if (queryForm.isFirstInit()) {
/* 2281 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2283 */       queryForm.init();
/*      */     } 
/* 2285 */     List result = boxManager.getBoxList(conditions, 
/* 2286 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2287 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2289 */     request.setAttribute("X_RESULTLIST", result);
/* 2290 */     request.setAttribute("x_selType", Integer.valueOf(34));
/* 2291 */     putEnumListToRequest(request);
/* 2292 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseOrderLotPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2298 */     String array = request.getParameter("arrays");
/* 2299 */     String[] arrays = array.split(",");
/* 2300 */     List<Box> list = new ArrayList<Box>();
/* 2301 */     BoxManager manager = ServiceLocator.getBoxManager(request); byte b; int i; String[] arrayOfString1;
/* 2302 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2303 */       Box box = manager.getBoxBylotSer2(id);
/* 2304 */       list.add(box);
/*      */       b++; }
/*      */     
/* 2307 */     request.setAttribute("x_listMap", list);
/* 2308 */     request.setAttribute("path", request.getContextPath());
/* 2309 */     request.setAttribute("x_user", getCurrentUser(request));
/* 2310 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward purchaseOrderBoxIdPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2316 */     String array = request.getParameter("arrays");
/* 2317 */     String[] arrays = array.split(",");
/* 2318 */     List<Box> list = new ArrayList<Box>();
/* 2319 */     BoxManager manager = ServiceLocator.getBoxManager(request); byte b; int i; String[] arrayOfString1;
/* 2320 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 2321 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 2322 */       list.add(box);
/*      */       b++; }
/*      */     
/* 2325 */     request.setAttribute("x_listMap", list);
/* 2326 */     request.setAttribute("path", request.getContextPath());
/* 2327 */     request.setAttribute("x_user", getCurrentUser(request));
/* 2328 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateBoxIsPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2334 */     response.setContentType("text/json");
/* 2335 */     response.setCharacterEncoding("UTF-8");
/* 2336 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 2338 */     String ids = request.getParameter("ids");
/* 2339 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2340 */     String[] arrays = ids.split(";"); byte b; int i; String[] arrayOfString1;
/* 2341 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 2342 */       Integer id = Integer.valueOf(Integer.parseInt(lot));
/* 2343 */       Box bo = boxManager.getBox(id);
/* 2344 */       bo.setIsPrint(YesNo.YES);
/* 2345 */       boxManager.updateBox(bo); b++; }
/*      */     
/* 2347 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 2348 */     response.getWriter().print(jo);
/* 2349 */     return null;
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
/*      */   public ActionForward listPurchaseOrderRQCUnqualified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2366 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2367 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2368 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2369 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 2372 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2373 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2378 */     conditions.put(BoxQueryCondition.STATUS_NOT_EQ, Integer.valueOf(9));
/* 2379 */     conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(6));
/*      */ 
/*      */ 
/*      */     
/* 2383 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2385 */     if (queryForm.isFirstInit()) {
/* 2386 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2388 */       queryForm.init();
/*      */     } 
/*      */     
/* 2391 */     String exportType = queryForm.getExportType();
/* 2392 */     if (StringUtils.isNotEmpty(exportType)) {
/* 2393 */       List data = boxManager.getBoxList(conditions, 0, -1, 
/* 2394 */           BoxQueryOrder.getEnum(queryForm.getOrder()), 
/* 2395 */           queryForm.isDescend());
/*      */       
/* 2397 */       int index = SessionTempFile.createNewTempFile(request);
/* 2398 */       String fileName = "purchaseOrder";
/* 2399 */       String suffix = ExportUtil.export(
/* 2400 */           exportType, 
/* 2401 */           data, 
/* 2402 */           request, 
/* 2403 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2404 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 2408 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 2409 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2410 */                     "Box.lot.id"));
/* 2411 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2412 */                     "Box.po_number"));
/* 2413 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2414 */                     "Box.po_line"));
/* 2415 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2416 */                     "Box.psoItem"));
/* 2417 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2418 */                     "Box.part.id"));
/* 2419 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2420 */                     "DPI"));
/* 2421 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2422 */                     "Box.po_supp"));
/* 2423 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2424 */                     "Box.part.describe1"));
/* 2425 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2426 */                     "InventoryTransit.location.code"));
/* 2427 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2428 */                     "Box.number"));
/* 2429 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2430 */                     "Box.po_date"));
/* 2431 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2432 */                     "Box.status_rqc"));
/* 2433 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2434 */                     "Box.status_rqc.chnShortDescription"));
/*      */               
/* 2436 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2437 */                     "Box.status_freeze.chnShortDescription"));
/* 2438 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), 
/* 2439 */                     "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 2445 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2446 */                     "lot.id"));
/* 2447 */               Object po_number = BeanHelper.getBeanPropertyValue(
/* 2448 */                   data, "po_number");
/* 2449 */               if (po_number != null) {
/* 2450 */                 row.add(po_number);
/*      */               } else {
/* 2452 */                 row.add("");
/*      */               } 
/* 2454 */               Object po_line = BeanHelper.getBeanPropertyValue(
/* 2455 */                   data, "po_line");
/* 2456 */               if (po_line != null) {
/* 2457 */                 row.add(po_line);
/*      */               } else {
/* 2459 */                 row.add("");
/*      */               } 
/* 2461 */               Object psoItem = BeanHelper.getBeanPropertyValue(
/* 2462 */                   data, "psoItem");
/* 2463 */               Object single = BeanHelper.getBeanPropertyValue(
/* 2464 */                   data, "single");
/* 2465 */               if (psoItem == null && single == null) {
/* 2466 */                 row.add("");
/*      */               } else {
/*      */                 
/* 2469 */                 if (psoItem != null)
/*      */                 {
/*      */ 
/*      */                   
/* 2473 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2474 */                         "psoItem.portalShipOrder.code"));
/*      */                 }
/* 2476 */                 if (single != null)
/*      */                 {
/*      */ 
/*      */                   
/* 2480 */                   row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2481 */                         "single.code"));
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/* 2486 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2487 */                     "part.id"));
/* 2488 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2489 */                     "part.dpiNo"));
/* 2490 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2491 */                     "po_supp"));
/* 2492 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2493 */                     "part.describe1"));
/* 2494 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2495 */                     "location.code"));
/* 2496 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2497 */                     "number"));
/* 2498 */               Object po_date = BeanHelper.getBeanPropertyValue(
/* 2499 */                   data, "po_date");
/* 2500 */               if (po_date != null) {
/* 2501 */                 row.add(po_date);
/*      */               } else {
/* 2503 */                 row.add("");
/*      */               } 
/* 2505 */               Object status_rqc = 
/* 2506 */                 BeanHelper.getBeanPropertyValue(data, "status_rqc");
/* 2507 */               if (status_rqc == null) {
/* 2508 */                 row.add("未质检");
/*      */               }
/* 2510 */               if (status_rqc != null) {
/* 2511 */                 row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2512 */                       "status_rqc.chnShortDescription"));
/*      */               }
/* 2514 */               if (BeanHelper.getBeanPropertyValue(data, "status_rqc") != null) {
/* 2515 */                 row.add(BeanHelper.getBeanPropertyValue(data, "status_rqc.chnShortDescription"));
/*      */               } else {
/* 2517 */                 row.add("待检");
/*      */               } 
/* 2519 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2520 */                     "status_freeze.chnShortDescription"));
/* 2521 */               row.add(BeanHelper.getBeanPropertyValue(data, 
/* 2522 */                     "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2538 */       return new ActionForward("download/" + index + "/" + 
/* 2539 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2546 */     List result = boxManager.getBoxList(conditions, 
/* 2547 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2548 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2550 */     request.setAttribute("boxlist", result);
/* 2551 */     request.setAttribute("x_selType", Integer.valueOf(35));
/* 2552 */     putEnumListToRequest(request);
/* 2553 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listProductScanningOutbound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2559 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2560 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2561 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2562 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 2565 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2566 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 2567 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*      */     
/* 2569 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2571 */     if (queryForm.isFirstInit()) {
/* 2572 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2574 */       queryForm.init();
/*      */     } 
/* 2576 */     List result = boxManager.getBoxList(conditions, 
/* 2577 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2578 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2580 */     request.setAttribute("boxlist", result);
/*      */     
/* 2582 */     putEnumListToRequest(request);
/* 2583 */     return mapping.findForward("page");
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
/*      */   public ActionForward listPurchaseOrderBoxFreeze(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2599 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2600 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2601 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2602 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 2605 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2606 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 2607 */     conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));
/*      */     
/* 2609 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2611 */     if (queryForm.isFirstInit()) {
/* 2612 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2614 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/* 2618 */     String exportType = queryForm.getExportType();
/* 2619 */     if (exportType != null && exportType.length() > 0) {
/* 2620 */       List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 2621 */       int index = SessionTempFile.createNewTempFile(request);
/* 2622 */       String fileName = "Box";
/* 2623 */       String suffix = ExportUtil.export(
/* 2624 */           exportType, 
/* 2625 */           data, 
/* 2626 */           request, 
/* 2627 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2628 */               request)), new Exportable()
/*      */           {
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 2633 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 2634 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.lot.id"));
/* 2635 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_number"));
/* 2636 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_line"));
/* 2637 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.psoItem"));
/* 2638 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.id"));
/* 2639 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
/* 2640 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_supp"));
/* 2641 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.describe1"));
/* 2642 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.number"));
/* 2643 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_date"));
/* 2644 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc.chnShortDescription"));
/* 2645 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/* 2646 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2652 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 2653 */               row.add(BeanUtils.getProperty(data, "po_number"));
/* 2654 */               row.add(BeanUtils.getProperty(data, "po_line"));
/* 2655 */               if (BeanUtils.getProperty(data, "psoItem") != null) {
/* 2656 */                 row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
/*      */               } else {
/* 2658 */                 row.add(BeanUtils.getProperty(data, "single.code"));
/*      */               } 
/* 2660 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 2661 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/* 2662 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/* 2663 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 2664 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2665 */               row.add(BeanUtils.getProperty(data, "po_date"));
/* 2666 */               row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/* 2667 */               if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
/* 2668 */                 row.add("已冻结");
/*      */               } else {
/* 2670 */                 row.add("未冻结");
/*      */               } 
/* 2672 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 2676 */       return new ActionForward("download/" + index + "/" + 
/* 2677 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/* 2680 */     List result = boxManager.getBoxList(conditions, 
/* 2681 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2682 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2684 */     request.setAttribute("boxlist", result);
/* 2685 */     request.setAttribute("x_selType", Integer.valueOf(77));
/* 2686 */     putEnumListToRequest(request);
/* 2687 */     return mapping.findForward("page");
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
/*      */   public ActionForward updatePurchaseOrderBoxFreeze(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2703 */     response.setContentType("text/json");
/* 2704 */     response.setCharacterEncoding("UTF-8");
/* 2705 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 2707 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 2708 */     String array = request.getParameter("arrays");
/* 2709 */     boolean sign = manager.updatePurchaseOrderBoxFreeze(array, Boolean.valueOf(true));
/*      */     
/* 2711 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
/* 2712 */     response.getWriter().print(jo);
/* 2713 */     return null;
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
/*      */   public ActionForward listPurchaseOrderBoxFreezeOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2729 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2730 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2731 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2732 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 2735 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2736 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 2737 */     conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(0));
/*      */     
/* 2739 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2741 */     if (queryForm.isFirstInit()) {
/* 2742 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2744 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/* 2748 */     String exportType = queryForm.getExportType();
/* 2749 */     if (exportType != null && exportType.length() > 0) {
/* 2750 */       List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 2751 */       int index = SessionTempFile.createNewTempFile(request);
/* 2752 */       String fileName = "Box";
/* 2753 */       String suffix = ExportUtil.export(
/* 2754 */           exportType, 
/* 2755 */           data, 
/* 2756 */           request, 
/* 2757 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2758 */               request)), new Exportable()
/*      */           {
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 2763 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 2764 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.lot.id"));
/* 2765 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_number"));
/* 2766 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_line"));
/* 2767 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.psoItem"));
/* 2768 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.id"));
/* 2769 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
/* 2770 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_supp"));
/* 2771 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.part.describe1"));
/* 2772 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.number"));
/* 2773 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.po_date"));
/* 2774 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc.chnShortDescription"));
/* 2775 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/* 2776 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2782 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 2783 */               row.add(BeanUtils.getProperty(data, "po_number"));
/* 2784 */               row.add(BeanUtils.getProperty(data, "po_line"));
/* 2785 */               if (BeanUtils.getProperty(data, "psoItem") != null) {
/* 2786 */                 row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
/*      */               } else {
/* 2788 */                 row.add(BeanUtils.getProperty(data, "single.code"));
/*      */               } 
/* 2790 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 2791 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/* 2792 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/* 2793 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 2794 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2795 */               row.add(BeanUtils.getProperty(data, "po_date"));
/* 2796 */               row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/* 2797 */               if (BeanUtils.getProperty(data, "status_freeze").equals("0")) {
/* 2798 */                 row.add("已冻结");
/*      */               } else {
/* 2800 */                 row.add("未冻结");
/*      */               } 
/* 2802 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 2806 */       return new ActionForward("download/" + index + "/" + 
/* 2807 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 2811 */     List result = boxManager.getBoxList(conditions, 
/* 2812 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2813 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 2815 */     request.setAttribute("boxlist", result);
/* 2816 */     request.setAttribute("x_selType", Integer.valueOf(78));
/* 2817 */     putEnumListToRequest(request);
/* 2818 */     return mapping.findForward("page");
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
/*      */   public ActionForward updatePurchaseOrderBoxFreezeOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2834 */     response.setContentType("text/json");
/* 2835 */     response.setCharacterEncoding("UTF-8");
/* 2836 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 2838 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 2839 */     String array = request.getParameter("arrays");
/* 2840 */     boolean sign = manager.updatePurchaseOrderBoxFreeze(array, Boolean.valueOf(false));
/*      */     
/* 2842 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
/* 2843 */     response.getWriter().print(jo);
/* 2844 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newDefeScanning(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2851 */     String array = request.getParameter("array");
/*      */     
/* 2853 */     request.setAttribute("x_array", array);
/* 2854 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward poPutInStorageMaterialBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2860 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 2861 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 2862 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 2863 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 2866 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2867 */     Map<BoxQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*      */     
/* 2869 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(3));
/* 2870 */     conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, Integer.valueOf(6));
/* 2871 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 2872 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 2874 */     if (queryForm.isFirstInit()) {
/* 2875 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 2877 */       queryForm.init();
/*      */     } 
/*      */ 
/*      */     
/* 2881 */     String exportType = queryForm.getExportType();
/* 2882 */     if (exportType != null && exportType.length() > 0) {
/* 2883 */       List<Box> data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 2884 */       for (Box box : data) {
/* 2885 */         List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(box.getId());
/* 2886 */         box.setUnqualifiedList(list);
/*      */       } 
/* 2888 */       int index = SessionTempFile.createNewTempFile(request);
/* 2889 */       String fileName = "box";
/* 2890 */       String suffix = ExportUtil.export(
/* 2891 */           exportType, 
/* 2892 */           data, 
/* 2893 */           request, 
/* 2894 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2895 */               request)), new Exportable()
/*      */           {
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 2900 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 2901 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/* 2902 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/* 2903 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/*      */               
/* 2905 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/* 2906 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
/* 2907 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "supplierId.name"));
/* 2908 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
/* 2909 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
/* 2910 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
/* 2911 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
/* 2912 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
/* 2913 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
/* 2914 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "ProduceRejectedMaterial.returnReasons.describe"));
/* 2915 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/* 2916 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 2923 */               Box box = (Box)data;
/* 2924 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 2925 */               row.add(BeanUtils.getProperty(data, "po_number"));
/* 2926 */               row.add(BeanUtils.getProperty(data, "po_line"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2932 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 2933 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/* 2934 */               row.add(BeanUtils.getProperty(data, "po_supp_name"));
/* 2935 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 2936 */               row.add(BeanUtils.getProperty(data, "location.code"));
/* 2937 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2938 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/* 2939 */               row.add(BeanUtils.getProperty(data, "po_date"));
/* 2940 */               if (BeanUtils.getProperty(data, "status_rqc") != null) {
/* 2941 */                 row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/*      */               } else {
/* 2943 */                 row.add("待检");
/*      */               } 
/* 2945 */               String returnReasons = "";
/* 2946 */               List<PurchaseOrderRqcUnqualified> list = box.getUnqualifiedList();
/* 2947 */               for (int i = 0; i < list.size(); i++) {
/* 2948 */                 PurchaseOrderRqcUnqualified purchaseOrderRqcUnqualified = list.get(i);
/* 2949 */                 if (i == 0) {
/* 2950 */                   returnReasons = String.valueOf(returnReasons) + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*      */                 } else {
/* 2952 */                   returnReasons = String.valueOf(returnReasons) + "," + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*      */                 } 
/*      */               } 
/* 2955 */               row.add(returnReasons);
/* 2956 */               row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
/* 2957 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 2961 */       return new ActionForward("download/" + index + "/" + 
/* 2962 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2967 */     List<Box> result = boxManager.getBoxList(conditions, 
/* 2968 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 2969 */         BoxQueryOrder.ID, queryForm.isDescend());
/* 2970 */     for (Box box : result) {
/* 2971 */       List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(box.getId());
/* 2972 */       box.setUnqualifiedList(list);
/*      */     } 
/* 2974 */     request.setAttribute("boxlist", result);
/* 2975 */     request.setAttribute("x_selType", Integer.valueOf(112));
/* 2976 */     putEnumListToRequest(request);
/* 2977 */     return mapping.findForward("page");
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
/*      */   public ActionForward poPutInStorageMaterialBoxByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2991 */     response.setContentType("text/json");
/* 2992 */     response.setCharacterEncoding("UTF-8");
/* 2993 */     JsonConfig cfg = new JsonConfig();
/* 2994 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 2995 */     String array = request.getParameter("array");
/* 2996 */     String str = boxManager.updateProduceInStorageMaterial(array, getCurrentUser(request));
/*      */     
/* 2998 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 2999 */     response.getWriter().print(jo);
/* 3000 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward listBoxDetailReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3005 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/* 3006 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 3007 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/* 3008 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/* 3011 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 3012 */     Map<BoxQueryCondition, BoxStatus> conditions = constructQueryMap(queryForm);
/* 3013 */     conditions.put(BoxQueryCondition.STATUS_EQ, BoxStatus.HASBEENINTO);
/* 3014 */     conditions.put(BoxQueryCondition.LOCATION_STORE_ROOM_TYPE_EQ, StoreRoomType.RAWMATERIALSLIBRARY);
/* 3015 */     conditions.put(BoxQueryCondition.ISENABLED_NOT_EQ, EnabledDisabled.ENABLED);
/* 3016 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 3018 */     if (queryForm.isFirstInit()) {
/* 3019 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*      */     } else {
/* 3021 */       queryForm.init();
/*      */     } 
/*      */     
/* 3024 */     String exportType = queryForm.getExportType();
/* 3025 */     if (exportType != null && exportType.length() > 0) {
/* 3026 */       List data = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 3027 */       int index = SessionTempFile.createNewTempFile(request);
/* 3028 */       String fileName = "BoxDetail";
/* 3029 */       String suffix = ExportUtil.export(
/* 3030 */           exportType, 
/* 3031 */           data, 
/* 3032 */           request, 
/* 3033 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 3034 */               request)), new Exportable()
/*      */           {
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/* 3039 */               MessageResources messages = PurchaseOrderBoxAction.this.getResources(request);
/* 3040 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/* 3041 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/* 3042 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/* 3043 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.id"));
/* 3044 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/* 3045 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "DPI"));
/* 3046 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
/* 3047 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
/* 3048 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
/* 3049 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
/* 3050 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "wmspart.pc"));
/* 3051 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "containerType.date"));
/* 3052 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_rqc"));
/* 3053 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status_freeze"));
/* 3054 */               row.add(messages.getMessage(PurchaseOrderBoxAction.this.getLocale(request), "Box.status.chnShortDescription"));
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 3061 */               row.add(BeanUtils.getProperty(data, "lot.id"));
/* 3062 */               row.add(BeanUtils.getProperty(data, "po_number"));
/* 3063 */               row.add(BeanUtils.getProperty(data, "po_line"));
/* 3064 */               if (BeanUtils.getProperty(data, "psoItem") != null) {
/* 3065 */                 row.add(BeanUtils.getProperty(data, "psoItem.portalShipOrder.code"));
/*      */               } else {
/* 3067 */                 row.add(BeanUtils.getProperty(data, "single.code"));
/*      */               } 
/* 3069 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 3070 */               row.add(BeanUtils.getProperty(data, "part.dpiNo"));
/* 3071 */               row.add(BeanUtils.getProperty(data, "po_supp"));
/* 3072 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/* 3073 */               row.add(BeanUtils.getProperty(data, "location.code"));
/* 3074 */               row.add(BeanUtils.getProperty(data, "number"));
/* 3075 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/* 3076 */               row.add(BeanUtils.getProperty(data, "po_date"));
/* 3077 */               if (BeanUtils.getProperty(data, "status_rqc") != null) {
/* 3078 */                 row.add(BeanUtils.getProperty(data, "status_rqc.chnShortDescription"));
/*      */               } else {
/* 3080 */                 row.add("待检");
/*      */               } 
/* 3082 */               row.add(BeanUtils.getProperty(data, "status_freeze.chnShortDescription"));
/* 3083 */               row.add(BeanUtils.getProperty(data, "status.chnShortDescription"));
/*      */             }
/*      */           });
/*      */       
/* 3087 */       return new ActionForward("download/" + index + "/" + 
/* 3088 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */ 
/*      */     
/* 3092 */     List result = boxManager.getBoxList(conditions, 
/* 3093 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 3094 */         BoxQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 3096 */     putEnumListToRequest(request);
/* 3097 */     request.setAttribute("X_RESULTLIST", result);
/* 3098 */     request.setAttribute("x_selType", Integer.valueOf(135));
/* 3099 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward produceRejectedMaterialBoxByReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3104 */     WmsToolManager manager = ServiceLocator.getWmsToolManager(request);
/* 3105 */     List<BadReasons> reasons = manager.getBadReasons();
/* 3106 */     StorageLocationManager locationManager = 
/* 3107 */       ServiceLocator.getStorageLocationManager(request);
/* 3108 */     PurchaseOrderPutInStorageManager storageManager = 
/* 3109 */       ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 3110 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 3111 */     map.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(2));
/* 3112 */     List list = locationManager.getStorageLocationList(map, -1, -1, null, 
/* 3113 */         true);
/*      */     
/* 3115 */     String arrays = request.getParameter("arrays");
/* 3116 */     String ids = request.getParameter("ids");
/* 3117 */     String type = request.getParameter("type");
/*      */     
/* 3119 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 3120 */     List<Box> boxList = new ArrayList<Box>();
/* 3121 */     String[] str = arrays.split(",");
/* 3122 */     BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
/* 3123 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String lot = arrayOfString1[b];
/* 3124 */       Box box = boxManager.getBoxBylotSer2(lot);
/* 3125 */       boxList.add(box);
/* 3126 */       sum = sum.add(box.getNumber()); b++; }
/*      */     
/* 3128 */     request.setAttribute("x_ids", ids);
/* 3129 */     request.setAttribute("x_type", type);
/* 3130 */     request.setAttribute("x_list", list);
/* 3131 */     request.setAttribute("x_reasons", reasons);
/* 3132 */     request.setAttribute("x_arrays", arrays);
/* 3133 */     request.setAttribute("x_boxList", boxList);
/* 3134 */     request.setAttribute("x_objsize", Integer.valueOf(boxList.size()));
/* 3135 */     request.setAttribute("x_sum", sum);
/* 3136 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateProduceRejectedMaterialBoxByReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3142 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 3143 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 3144 */     PurchaseOrderRQCManager managerRqc = 
/* 3145 */       ServiceLocator.getPurchaseOrderRQCManager(request);
/* 3146 */     String arrays = request.getParameter("arrays");
/* 3147 */     String ids = request.getParameter("ids");
/* 3148 */     String rqcType = request.getParameter("rqcType");
/* 3149 */     String strList = request.getParameter("strList");
/* 3150 */     String type = request.getParameter("type");
/*      */ 
/*      */     
/* 3153 */     String str = "";
/* 3154 */     String[] strs = arrays.split(","); byte b; int i; String[] arrayOfString1;
/* 3155 */     for (i = (arrayOfString1 = strs).length, b = 0; b < i; ) { String array = arrayOfString1[b];
/* 3156 */       str = managerRqc.purchaseOrderRejectedMaterialBoxRQC(array, rqcType, strList, getCurrentUser(request)); b++; }
/*      */     
/* 3158 */     if (type.equals("1")) {
/* 3159 */       String str1 = manager.updateProduceRejectedMaterial(ids, getCurrentUser(request));
/* 3160 */       if (str1.equals("true")) {
/* 3161 */         postGlobalMessage("The.return.of.success", request.getSession());
/*      */       }
/*      */     } else {
/* 3164 */       String str1 = boxManager.updateProduceInStorageMaterial(ids, getCurrentUser(request));
/* 3165 */       if (str1.equals("true")) {
/* 3166 */         postGlobalMessage("The.return.of.success", request.getSession());
/*      */       }
/*      */     } 
/* 3169 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward updateProduceRejectedMaterialBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3174 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 3175 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 3176 */     String ids = request.getParameter("ids");
/* 3177 */     String type = request.getParameter("type");
/* 3178 */     if (type.equals("1")) {
/* 3179 */       String str = manager.updateProduceRejectedMaterial(ids, getCurrentUser(request));
/* 3180 */       if (str.equals("true")) {
/* 3181 */         postGlobalMessage("The.return.of.success", request.getSession());
/*      */       }
/* 3183 */       return new ActionForward("listProduceRejectedMaterialBoxList.do", true);
/*      */     } 
/* 3185 */     String str1 = boxManager.updateProduceInStorageMaterial(ids, getCurrentUser(request));
/* 3186 */     if (str1.equals("true")) {
/* 3187 */       postGlobalMessage("The.return.of.success", request.getSession());
/*      */     }
/* 3189 */     return new ActionForward("listpurchaseOrderPutInStorageMaterialBoxList.do", true);
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
/*      */   public ActionForward purchaseReturnMaterialByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 3206 */     response.setContentType("text/json");
/* 3207 */     response.setCharacterEncoding("UTF-8");
/* 3208 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 3210 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 3211 */     String array = request.getParameter("array");
/* 3212 */     String str = boxManager.purchaseReturnMaterialByBox(array, getCurrentUser(request));
/*      */     
/* 3214 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 3215 */     response.getWriter().print(jo);
/* 3216 */     return null;
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseOrderBoxAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */