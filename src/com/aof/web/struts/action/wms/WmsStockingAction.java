/*      */ package com.aof.web.struts.action.wms;
/*      */ 
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.query.StorageLocationQueryCondition;
/*      */ import com.aof.model.comprehensive.Stocking;
/*      */ import com.aof.model.comprehensive.StockingDetial;
/*      */ import com.aof.model.comprehensive.StockingRecord;
/*      */ import com.aof.model.comprehensive.StockingScanRecord;
/*      */ import com.aof.model.comprehensive.query.StockingDetialQueryCondition;
/*      */ import com.aof.model.comprehensive.query.StockingQueryCondition;
/*      */ import com.aof.model.comprehensive.query.StockingQueryOrder;
/*      */ import com.aof.model.comprehensive.query.StockingScanRecordQueryCondition;
/*      */ import com.aof.model.comprehensive.query.StockingScanRecordQueryOrder;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.plantWarehouse.WmsPlanToGoOut;
/*      */ import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
/*      */ import com.aof.model.po.Box;
/*      */ import com.aof.service.admin.FunctionManager;
/*      */ import com.aof.service.admin.UserManager;
/*      */ import com.aof.service.basic.StorageLocationManager;
/*      */ import com.aof.service.comprehensive.StockingManager;
/*      */ import com.aof.service.comprehensive.impl.StockingConfirmStatusTransferThread;
/*      */ import com.aof.service.comprehensive.impl.StockingTransferThread;
/*      */ import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.basic.InventoryQueryForm;
/*      */ import com.aof.web.struts.form.basic.StorageLocationQueryForm;
/*      */ import com.aof.web.struts.form.basic.WmsStockingDetialQueryForm;
/*      */ import com.aof.web.struts.form.basic.WmsStockingQueryForm;
/*      */ import com.aof.web.struts.form.basic.WmsStockingScanRecordQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.struts.action.ActionUtils;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.struts.form.DateFormatter;
/*      */ import com.shcnc.utils.BeanHelper;
/*      */ import com.shcnc.utils.BeanUtils;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.net.URLEncoder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import jxl.Workbook;
/*      */ import jxl.biff.DisplayFormat;
/*      */ import jxl.biff.FontRecord;
/*      */ import jxl.format.Alignment;
/*      */ import jxl.format.Border;
/*      */ import jxl.format.BorderLineStyle;
/*      */ import jxl.format.CellFormat;
/*      */ import jxl.format.Colour;
/*      */ import jxl.format.UnderlineStyle;
/*      */ import jxl.format.VerticalAlignment;
/*      */ import jxl.write.Colour;
/*      */ import jxl.write.Label;
/*      */ import jxl.write.NumberFormat;
/*      */ import jxl.write.WritableCell;
/*      */ import jxl.write.WritableCellFormat;
/*      */ import jxl.write.WritableFont;
/*      */ import jxl.write.WritableSheet;
/*      */ import jxl.write.WritableWorkbook;
/*      */ import jxl.write.WriteException;
/*      */ import jxl.write.biff.RowsExceededException;
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
/*      */ public class WmsStockingAction
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  106 */     WmsStockingQueryForm queryForm = (WmsStockingQueryForm)form;
/*  107 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  108 */       queryForm.setOrder(StockingQueryOrder.ID.getName());
/*  109 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  112 */     StockingManager fm = ServiceLocator.getStockingManager(request);
/*  113 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  115 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  117 */     String exportType = queryForm.getExportType();
/*  118 */     if (StringUtils.isNotEmpty(exportType)) {
/*  119 */       List data = fm.getStockingList(conditions, 0, -1, 
/*  120 */           StockingQueryOrder.getEnum(queryForm.getOrder()), 
/*  121 */           queryForm.isDescend());
/*  122 */       int index = SessionTempFile.createNewTempFile(request);
/*  123 */       String fileName = "wmsStocking";
/*  124 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  125 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  126 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  129 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/*  130 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.id"));
/*  131 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.operation.name"));
/*  132 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.start_date"));
/*  133 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.end_date"));
/*  134 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.plan_sumQty"));
/*  135 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.actual_sumQty"));
/*  136 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.differences_sumQty"));
/*  137 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.status.chnShortDescription"));
/*  138 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "WmsUW.remark"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  142 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/*  143 */               row.add(BeanHelper.getBeanPropertyValue(data, "operation.name"));
/*  144 */               row.add(BeanHelper.getBeanPropertyValue(data, "start_date"));
/*  145 */               row.add(BeanHelper.getBeanPropertyValue(data, "end_date"));
/*  146 */               row.add(BeanHelper.getBeanPropertyValue(data, "plan_sumQty"));
/*  147 */               row.add(BeanHelper.getBeanPropertyValue(data, "actual_sumQty"));
/*  148 */               row.add(BeanHelper.getBeanPropertyValue(data, "differences_sumQty"));
/*  149 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*  150 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/*      */             }
/*      */           });
/*      */       
/*  154 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  156 */     if (queryForm.isFirstInit()) {
/*  157 */       queryForm.init(fm.getStockingListCount(conditions));
/*      */     } else {
/*  159 */       queryForm.init();
/*      */     } 
/*  161 */     List result = fm.getStockingList(conditions, 
/*  162 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  163 */         StockingQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  165 */     request.setAttribute("X_RESULTLIST", result);
/*  166 */     List grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/*  167 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*  168 */     putEnumListToRequest(request);
/*  169 */     request.setAttribute("x_selType", Integer.valueOf(36));
/*  170 */     return mapping.findForward("page");
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
/*      */   public ActionForward listDifference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  185 */     WmsStockingQueryForm queryForm = (WmsStockingQueryForm)form;
/*  186 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  187 */       queryForm.setOrder(StockingQueryOrder.ID.getName());
/*  188 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  191 */     StockingManager fm = ServiceLocator.getStockingManager(request);
/*  192 */     Map<StockingQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  193 */     conditions.put(StockingQueryCondition.STATUS_EQ, Integer.valueOf(1));
/*  194 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  196 */     String exportType = queryForm.getExportType();
/*  197 */     if (StringUtils.isNotEmpty(exportType)) {
/*  198 */       List data = fm.getStockingList(conditions, 0, -1, 
/*  199 */           StockingQueryOrder.getEnum(queryForm.getOrder()), 
/*  200 */           queryForm.isDescend());
/*  201 */       int index = SessionTempFile.createNewTempFile(request);
/*  202 */       String fileName = "wmsStocking";
/*  203 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  204 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  205 */               request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  208 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/*  209 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.id"));
/*  210 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.operation.name"));
/*  211 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.start_date"));
/*  212 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.end_date"));
/*  213 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.plan_sumQty"));
/*  214 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.actual_sumQty"));
/*  215 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.differences_sumQty"));
/*  216 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.status.chnShortDescription"));
/*  217 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "WmsUW.remark"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  221 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/*  222 */               row.add(BeanHelper.getBeanPropertyValue(data, "operation.name"));
/*  223 */               row.add(BeanHelper.getBeanPropertyValue(data, "start_date"));
/*  224 */               row.add(BeanHelper.getBeanPropertyValue(data, "end_date"));
/*  225 */               row.add(BeanHelper.getBeanPropertyValue(data, "plan_sumQty"));
/*  226 */               row.add(BeanHelper.getBeanPropertyValue(data, "actual_sumQty"));
/*  227 */               row.add(BeanHelper.getBeanPropertyValue(data, "differences_sumQty"));
/*  228 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*  229 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/*      */             }
/*      */           });
/*      */       
/*  233 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  235 */     if (queryForm.isFirstInit()) {
/*  236 */       queryForm.init(fm.getStockingListCount(conditions));
/*      */     } else {
/*  238 */       queryForm.init();
/*      */     } 
/*  240 */     List result = fm.getStockingList(conditions, 
/*  241 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  242 */         StockingQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  244 */     request.setAttribute("X_RESULTLIST", result);
/*  245 */     List grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/*  246 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*  247 */     putEnumListToRequest(request);
/*  248 */     request.setAttribute("x_selType", Integer.valueOf(86));
/*  249 */     return mapping.findForward("page");
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
/*      */   public ActionForward editWmsStockingAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  305 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/*  306 */     Stocking wmsStocking = getStocking(request);
/*  307 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  308 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  309 */     conditions.put(StockingDetialQueryCondition.STOCKING_ID_EQ, wmsStocking.getId());
/*  310 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  311 */     List<StockingDetial> stockingItems = wsm.getStockingDetialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), null, true);
/*  312 */     if (queryForm.isFirstInit()) {
/*  313 */       queryForm.init(wsm.getStockingDetialListCount(conditions));
/*      */     } else {
/*  315 */       queryForm.init();
/*      */     } 
/*      */     
/*  318 */     String exportType = queryForm.getExportType();
/*  319 */     if (StringUtils.isNotEmpty(exportType)) {
/*  320 */       List data = wsm.getStockingDetialList(conditions, 0, -1, null, true);
/*  321 */       int index = SessionTempFile.createNewTempFile(request);
/*  322 */       String fileName = "Stocking";
/*  323 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  324 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  325 */           new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  328 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/*  329 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.location.code"));
/*  330 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.location.address"));
/*  331 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.location.basic_storeroom_id.code"));
/*  332 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.location.description"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  336 */               row.add(BeanUtils.getProperty(data, "location.code"));
/*  337 */               row.add(BeanUtils.getProperty(data, "location.address"));
/*  338 */               row.add(BeanUtils.getProperty(data, "location.basic_storeroom_id.code"));
/*  339 */               row.add(BeanUtils.getProperty(data, "location.description"));
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  344 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/*  347 */     request.setAttribute("X_RESULTLIST", stockingItems);
/*  348 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*  349 */     putEnumListToRequest(request);
/*  350 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deletemsStocking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  356 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/*  357 */     Stocking wmsStocking = getStocking(request);
/*  358 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  359 */     wsm.deleteStockingByAll(wmsStocking);
/*  360 */     return new ActionForward("listWmsStocking.do", true);
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward detailDeleteLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  365 */     response.setContentType("text/json");
/*  366 */     response.setCharacterEncoding("UTF-8");
/*  367 */     JsonConfig cfg = new JsonConfig();
/*      */     
/*  369 */     String stockingId = request.getParameter("stockingId");
/*  370 */     String locationId = request.getParameter("locationId");
/*  371 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  372 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  373 */     conditions.put(StockingDetialQueryCondition.STOCKING_ID_EQ, stockingId);
/*  374 */     conditions.put(StockingDetialQueryCondition.LOCATION_ID_EQ, locationId);
/*      */     
/*  376 */     List<StockingDetial> stockingItems = wsm.getStockingDetialList(conditions, 0, -1, null, true);
/*  377 */     for (StockingDetial stockingDetial : stockingItems) {
/*  378 */       wsm.deleteStockingDetial(stockingDetial);
/*      */     }
/*  380 */     JSONArray jo = JSONArray.fromObject(null, cfg);
/*  381 */     response.getWriter().print(jo);
/*  382 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewDifference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  388 */     WmsStockingDetialQueryForm queryForm = (WmsStockingDetialQueryForm)form;
/*      */     
/*  390 */     Stocking wmsStocking = getStocking(request);
/*  391 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  392 */     List<StockingDetial> stockingItems = wsm.getStockingByMain(wmsStocking.getId());
/*  393 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  394 */     conditions.put(StockingQueryCondition.STOCKING_ID_EQ, wmsStocking.getId());
/*  395 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  396 */     List<StockingRecord> records = wsm.getStockingRecordList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), StockingQueryOrder.LOCATION_ID, false);
/*      */     
/*  398 */     if (queryForm.isFirstInit()) {
/*  399 */       queryForm.init(wsm.getStockingRecordListCount(conditions));
/*      */     } else {
/*  401 */       queryForm.init();
/*      */     } 
/*      */     
/*  404 */     String exportType = queryForm.getExportType();
/*  405 */     if (StringUtils.isNotEmpty(exportType)) {
/*  406 */       List data = wsm.getStockingRecordList(conditions, 0, -1, StockingQueryOrder.LOCATION_ID, false);
/*  407 */       int index = SessionTempFile.createNewTempFile(request);
/*  408 */       String fileName = "Stocking";
/*  409 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  410 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  411 */           new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  414 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/*  415 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.location.code"));
/*  416 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.location.code"));
/*  417 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.id"));
/*  418 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.dpiNo"));
/*  419 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.describe1"));
/*  420 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.describe2"));
/*  421 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.lot.id"));
/*  422 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.stocking_qty"));
/*  423 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.inventory_qty"));
/*  424 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.differences"));
/*  425 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "salesOrderItem.sotype"));
/*  426 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.type"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  430 */               row.add(BeanUtils.getProperty(data, "box.location.code"));
/*  431 */               row.add(BeanUtils.getProperty(data, "location.code"));
/*  432 */               row.add(BeanUtils.getProperty(data, "box.part.id"));
/*  433 */               row.add(BeanUtils.getProperty(data, "box.part.dpiNo"));
/*  434 */               row.add(BeanUtils.getProperty(data, "box.part.describe1"));
/*  435 */               row.add(BeanUtils.getProperty(data, "box.part.describe2"));
/*  436 */               row.add(BeanUtils.getProperty(data, "box.lot.id"));
/*  437 */               row.add(BeanUtils.getProperty(data, "stocking_qty"));
/*  438 */               row.add(BeanUtils.getProperty(data, "inventory_qty"));
/*  439 */               row.add(BeanUtils.getProperty(data, "differences"));
/*  440 */               Double differences = Double.valueOf(Double.parseDouble(BeanUtils.getProperty(data, "differences")));
/*  441 */               if (differences.doubleValue() > 0.0D) {
/*  442 */                 row.add("盘盈");
/*  443 */               } else if (differences.doubleValue() < 0.0D) {
/*  444 */                 row.add("盘亏");
/*      */               } 
/*      */               
/*  447 */               String strType = BeanUtils.getProperty(data, "type");
/*  448 */               if (strType != null) {
/*  449 */                 int type = Integer.parseInt(strType);
/*  450 */                 if (type == 1) {
/*  451 */                   row.add("人工处理");
/*  452 */                 } else if (type == 2) {
/*  453 */                   row.add("系统处理");
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  460 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  462 */     request.setAttribute("x_InventoryCountReport", stockingItems);
/*  463 */     request.setAttribute("x_records", records);
/*  464 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*  465 */     request.setAttribute("x_selType", Integer.valueOf(129));
/*  466 */     putEnumListToRequest(request);
/*  467 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward viewDifferenceByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  472 */     WmsStockingDetialQueryForm queryForm = (WmsStockingDetialQueryForm)form;
/*      */     
/*  474 */     Stocking wmsStocking = getStocking(request);
/*  475 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  476 */     List<StockingDetial> stockingItems = wsm.getStockingByMain(wmsStocking.getId());
/*  477 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  478 */     conditions.put(StockingQueryCondition.STOCKING_ID_EQ, wmsStocking.getId());
/*  479 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  480 */     List<StockingRecord> records = wsm.getStockingRecordList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), StockingQueryOrder.LOCATION_ID, false);
/*      */     
/*  482 */     if (queryForm.isFirstInit()) {
/*  483 */       queryForm.init(wsm.getStockingRecordListCount(conditions));
/*      */     } else {
/*  485 */       queryForm.init();
/*      */     } 
/*      */     
/*  488 */     String exportType = queryForm.getExportType();
/*  489 */     if (StringUtils.isNotEmpty(exportType)) {
/*  490 */       List data = wsm.getStockingRecordList(conditions, 0, -1, StockingQueryOrder.LOCATION_ID, false);
/*  491 */       int index = SessionTempFile.createNewTempFile(request);
/*  492 */       String fileName = "Stocking";
/*  493 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  494 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  495 */           new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  498 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/*  499 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.location.code"));
/*  500 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.id"));
/*  501 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.dpiNo"));
/*  502 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.describe1"));
/*  503 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.part.describe2"));
/*  504 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.box.lot.id"));
/*  505 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.stocking_qty"));
/*  506 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.inventory_qty"));
/*  507 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.differences"));
/*  508 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "salesOrderItem.sotype"));
/*  509 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "Stocking.type"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  513 */               row.add(BeanUtils.getProperty(data, "box.location.code"));
/*  514 */               row.add(BeanUtils.getProperty(data, "box.part.id"));
/*  515 */               row.add(BeanUtils.getProperty(data, "box.part.dpiNo"));
/*  516 */               row.add(BeanUtils.getProperty(data, "box.part.describe1"));
/*  517 */               row.add(BeanUtils.getProperty(data, "box.part.describe2"));
/*  518 */               row.add(BeanUtils.getProperty(data, "box.lot.id"));
/*  519 */               row.add(BeanUtils.getProperty(data, "stocking_qty"));
/*  520 */               row.add(BeanUtils.getProperty(data, "inventory_qty"));
/*  521 */               row.add(BeanUtils.getProperty(data, "differences"));
/*  522 */               Double differences = Double.valueOf(Double.parseDouble(BeanUtils.getProperty(data, "differences")));
/*  523 */               if (differences.doubleValue() > 0.0D) {
/*  524 */                 row.add("盘盈");
/*  525 */               } else if (differences.doubleValue() < 0.0D) {
/*  526 */                 row.add("盘亏");
/*      */               } 
/*      */               
/*  529 */               String strType = BeanUtils.getProperty(data, "type");
/*  530 */               if (strType != null) {
/*  531 */                 int type = Integer.parseInt(strType);
/*  532 */                 if (type == 1) {
/*  533 */                   row.add("人工处理");
/*  534 */                 } else if (type == 2) {
/*  535 */                   row.add("系统处理");
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  542 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  544 */     request.setAttribute("x_InventoryCountReport", stockingItems);
/*  545 */     request.setAttribute("x_records", records);
/*  546 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*  547 */     request.setAttribute("x_selType", Integer.valueOf(155));
/*  548 */     putEnumListToRequest(request);
/*  549 */     return mapping.findForward("page");
/*      */   }
/*      */   private Map constructQueryMap(WmsStockingQueryForm queryForm) {
/*  552 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  553 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*      */     
/*  555 */     return conditions;
/*      */   }
/*      */ 
/*      */   
/*      */   private Stocking getWmsStocking(HttpServletRequest request) throws Exception {
/*  560 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/*  561 */     StockingManager wmsStockingManager = ServiceLocator.getStockingManager(request);
/*  562 */     Stocking wmsStocking = wmsStockingManager.getStocking(id);
/*  563 */     if (wmsStocking == null)
/*  564 */       throw new ActionException("wmsStocking.notFound", id); 
/*  565 */     return wmsStocking;
/*      */   }
/*      */   
/*      */   private void putEnumListToRequest(HttpServletRequest request) {
/*  569 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*  570 */     request.setAttribute("path", request.getContextPath());
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
/*      */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  586 */     Stocking wmsStocking = getWmsStocking(request);
/*      */     
/*  588 */     StockingManager cm = ServiceLocator.getStockingManager(request);
/*      */     try {
/*  590 */       cm.deleteStocking(wmsStocking);
/*  591 */     } catch (Throwable t) {
/*  592 */       throw new ActionException("wmsStocking.delete.fail");
/*      */     } 
/*  594 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward exportEXCELAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  600 */     String ids = request.getParameter("ids");
/*  601 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  607 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward createImportTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Boolean isforecast, List data) throws Exception {
/*  613 */     List<Character> chars = new ArrayList();
/*  614 */     int charsDouble = 0;
/*  615 */     for (char i = 'a'; i <= 'z'; i = (char)(i + 1)) {
/*  616 */       chars.add(Character.valueOf(i));
/*      */     }
/*      */     try {
/*  619 */       DateFormatter format = new DateFormatter(Date.class, "HHmmssSS");
/*  620 */       String name = String.valueOf(format.format(new Date())) + ".xls";
/*  621 */       if (!(new File(String.valueOf(getSys_url()) + "temp/")).exists()) {
/*  622 */         (new File(String.valueOf(getSys_url()) + "temp/")).mkdir();
/*      */       }
/*  624 */       File f = new File(String.valueOf(getSys_url()) + "temp/WmsStockingImportTemplate" + name);
/*  625 */       while (f.exists()) {
/*  626 */         Thread.sleep(100L);
/*  627 */         name = String.valueOf(format.format(new Date())) + ".xls";
/*  628 */         f = new File(String.valueOf(getSys_url()) + "temp/WmsStockingImportTemplate" + name);
/*      */       } 
/*  630 */       WritableWorkbook wwb = Workbook.createWorkbook(f);
/*  631 */       WritableSheet ws = wwb.createSheet("盘点计划", 0);
/*      */       
/*  633 */       Label lable = null;
/*  634 */       WritableSheet sheet = wwb.getSheet(0);
/*  635 */       sheet.setColumnView(0, 15);
/*  636 */       sheet.setColumnView(1, 20);
/*  637 */       NumberFormat nf = new NumberFormat("#.###");
/*  638 */       WritableFont host = new WritableFont(WritableFont.ARIAL, 
/*  639 */           15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/*  640 */           Colour.CORAL);
/*  641 */       WritableFont red = new WritableFont(WritableFont.ARIAL, 
/*  642 */           10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, 
/*  643 */           Colour.RED);
/*      */       
/*  645 */       WritableCellFormat hosts = new WritableCellFormat(host);
/*  646 */       hosts.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  647 */       hosts.setWrap(true);
/*  648 */       hosts.setAlignment(Alignment.CENTRE);
/*  649 */       hosts.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */       
/*  651 */       WritableCellFormat title = new WritableCellFormat();
/*  652 */       title.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  653 */       title.setBackground(Colour.ORANGE);
/*  654 */       title.setWrap(true);
/*  655 */       title.setAlignment(Alignment.CENTRE);
/*  656 */       title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */ 
/*      */       
/*  659 */       WritableCellFormat functions = new WritableCellFormat((DisplayFormat)nf);
/*  660 */       functions.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  661 */       functions.setWrap(true);
/*  662 */       functions.setAlignment(Alignment.RIGHT);
/*  663 */       functions.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */       
/*  665 */       WritableCellFormat cellFormat = new WritableCellFormat();
/*  666 */       cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  667 */       cellFormat.setWrap(true);
/*  668 */       cellFormat.setAlignment(Alignment.CENTRE);
/*  669 */       cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */       
/*  671 */       WritableCellFormat reds = new WritableCellFormat((DisplayFormat)nf);
/*  672 */       reds.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  673 */       reds.setFont((FontRecord)red);
/*  674 */       reds.setWrap(true);
/*  675 */       reds.setAlignment(Alignment.CENTRE);
/*  676 */       reds.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */       
/*  678 */       ws = createheadtoImport(ws, lable, title, hosts, sheet, isforecast, data, request);
/*      */       
/*  680 */       WritableCellFormat format3 = new WritableCellFormat((DisplayFormat)nf);
/*  681 */       format3.setWrap(true);
/*  682 */       format3.setAlignment(Alignment.RIGHT);
/*  683 */       format3.setVerticalAlignment(VerticalAlignment.CENTRE);
/*  684 */       format3.setBorder(Border.ALL, BorderLineStyle.THIN);
/*      */       
/*  686 */       wwb.write();
/*  687 */       wwb.close();
/*  688 */       response.sendRedirect("temp/WmsStockingImportTemplate" + name);
/*  689 */       return mapping.findForward("page");
/*  690 */     } catch (Exception e) {
/*  691 */       throw new ActionException(e.getMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WritableSheet createheadtoImport(WritableSheet ws, Label lable, WritableCellFormat cellFormat, WritableCellFormat hosts, WritableSheet sheet, Boolean isforecast, List<List<Map>> data, HttpServletRequest request) throws RowsExceededException, WriteException {
/*  700 */     ws.getSettings().setDefaultColumnWidth(15);
/*      */     
/*  702 */     WritableCellFormat title = new WritableCellFormat();
/*  703 */     title.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  704 */     title.setBackground(Colour.WHITE);
/*  705 */     title.setWrap(true);
/*  706 */     title.setAlignment(Alignment.CENTRE);
/*  707 */     title.setVerticalAlignment(VerticalAlignment.CENTRE);
/*  708 */     WritableCellFormat title2 = new WritableCellFormat();
/*  709 */     title2.setBorder(Border.ALL, BorderLineStyle.THIN);
/*  710 */     title2.setBackground(Colour.LIGHT_GREEN);
/*  711 */     title2.setWrap(true);
/*  712 */     title2.setAlignment(Alignment.CENTRE);
/*  713 */     title2.setVerticalAlignment(VerticalAlignment.CENTRE);
/*      */     
/*  715 */     lable = new Label(0, 0, "库位", (CellFormat)cellFormat);
/*  716 */     ws.addCell((WritableCell)lable);
/*  717 */     lable = new Label(1, 0, "物料", (CellFormat)cellFormat);
/*  718 */     ws.addCell((WritableCell)lable);
/*  719 */     lable = new Label(2, 0, "批次", (CellFormat)cellFormat);
/*  720 */     ws.addCell((WritableCell)lable);
/*  721 */     lable = new Label(3, 0, "描述", (CellFormat)cellFormat);
/*  722 */     ws.addCell((WritableCell)lable);
/*  723 */     lable = new Label(4, 0, "供应商规格", (CellFormat)cellFormat);
/*  724 */     ws.addCell((WritableCell)lable);
/*  725 */     lable = new Label(5, 0, "库存量", (CellFormat)cellFormat);
/*  726 */     ws.addCell((WritableCell)lable);
/*  727 */     lable = new Label(6, 0, "盘点数量", (CellFormat)cellFormat);
/*  728 */     ws.addCell((WritableCell)lable);
/*  729 */     lable = new Label(7, 0, "差异", (CellFormat)cellFormat);
/*  730 */     ws.addCell((WritableCell)lable);
/*  731 */     lable = new Label(8, 0, "盘点编号", (CellFormat)cellFormat);
/*  732 */     ws.addCell((WritableCell)lable);
/*      */     
/*  734 */     int n = 1;
/*  735 */     for (int i = 0; i < data.size(); i++) {
/*  736 */       List<Map> datas = data.get(i);
/*  737 */       for (Map map : datas) {
/*  738 */         WmsPart wmsPart = (WmsPart)map.get("wmsPart");
/*  739 */         lable = new Label(0, n, (String)map.get("location"), (CellFormat)title);
/*  740 */         ws.addCell((WritableCell)lable);
/*  741 */         if (wmsPart != null) {
/*  742 */           lable = new Label(1, n, (new StringBuilder(String.valueOf(wmsPart.getId()))).toString(), (CellFormat)title);
/*  743 */           ws.addCell((WritableCell)lable);
/*      */         } else {
/*  745 */           lable = new Label(1, n, "", (CellFormat)title);
/*  746 */           ws.addCell((WritableCell)lable);
/*      */         } 
/*      */         
/*  749 */         lable = new Label(2, n, (String)map.get("lotSer"), (CellFormat)title);
/*  750 */         ws.addCell((WritableCell)lable);
/*  751 */         if (wmsPart != null) {
/*  752 */           lable = new Label(3, n, (new StringBuilder(String.valueOf(wmsPart.getDescribe1()))).toString(), (CellFormat)title);
/*  753 */           ws.addCell((WritableCell)lable);
/*  754 */           lable = new Label(4, n, (new StringBuilder(String.valueOf(wmsPart.getDescribe2()))).toString(), (CellFormat)title);
/*  755 */           ws.addCell((WritableCell)lable);
/*      */         } else {
/*  757 */           lable = new Label(3, n, "", (CellFormat)title);
/*  758 */           ws.addCell((WritableCell)lable);
/*  759 */           lable = new Label(4, n, "", (CellFormat)title);
/*  760 */           ws.addCell((WritableCell)lable);
/*      */         } 
/*      */         
/*  763 */         lable = new Label(5, n, (String)map.get("stockingAmount"), (CellFormat)title);
/*  764 */         ws.addCell((WritableCell)lable);
/*  765 */         lable = new Label(6, n, (String)map.get("amount"), (CellFormat)title);
/*  766 */         ws.addCell((WritableCell)lable);
/*  767 */         lable = new Label(7, n, (String)map.get("difference"), (CellFormat)title);
/*  768 */         ws.addCell((WritableCell)lable);
/*  769 */         lable = new Label(8, n, (String)map.get("code"), (CellFormat)title);
/*  770 */         ws.addCell((WritableCell)lable);
/*  771 */         n++;
/*      */       } 
/*      */     } 
/*      */     
/*  775 */     return ws;
/*      */   }
/*      */   
/*      */   private String getSys_url() {
/*  779 */     String strClassName = getClass().getName();
/*  780 */     String strPackageName = "";
/*  781 */     if (getClass().getPackage() != null) {
/*  782 */       strPackageName = getClass().getPackage().getName();
/*      */     }
/*  784 */     String strClassFileName = "";
/*  785 */     if (!"".equals(strPackageName)) {
/*  786 */       strClassFileName = strClassName.substring(
/*  787 */           strPackageName.length() + 1, strClassName.length());
/*      */     } else {
/*  789 */       strClassFileName = strClassName;
/*      */     } 
/*  791 */     URL url = getClass().getResource(String.valueOf(strClassFileName) + ".class");
/*  792 */     String strURL = url.toString();
/*  793 */     strURL = strURL.substring(strURL.indexOf('/') + 1, strURL.lastIndexOf('/'));
/*      */     
/*  795 */     return "/" + strURL.substring(0, strURL.lastIndexOf("WEB-INF"));
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
/*      */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  811 */     long start = System.currentTimeMillis();
/*  812 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/*  813 */     StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
/*  814 */     BeanForm wmsStockingForm = (BeanForm)form;
/*  815 */     Stocking wmsStocking = new Stocking();
/*  816 */     wmsStockingForm.populate(wmsStocking, "to_bean");
/*  817 */     String code = request.getParameter("code");
/*  818 */     String id = request.getParameter("id");
/*  819 */     wmsStocking = manager.getStocking(Integer.valueOf(Integer.parseInt(id)));
/*  820 */     StockingTransferThread stockingTread = new StockingTransferThread(storageLocationManager, manager, wmsStocking);
/*  821 */     if (stockingTread.isAlive()) {
/*  822 */       postGlobalMessage("The.system.is.processing.data.please.later ", request.getSession());
/*      */     } else {
/*      */       
/*  825 */       wmsStocking.setStatus(EnabledDisabled.DISABLED);
/*  826 */       wmsStocking.setEnd_date(new Date());
/*  827 */       manager.updateStocking(wmsStocking);
/*  828 */       wmsStocking = manager.updateLocationByStocking(wmsStocking);
/*  829 */       stockingTread.start();
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
/*  847 */     long end = System.currentTimeMillis();
/*  848 */     System.out.println(end - start);
/*      */     
/*  850 */     return new ActionForward("listWmsStocking.do", true);
/*      */   }
/*      */ 
/*      */   
/*      */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/*  855 */     return getAndCheckSite("site_id", request);
/*      */   }
/*      */   
/*      */   private boolean hasSite(HttpServletRequest request) {
/*  859 */     String s = request.getParameter("site_id");
/*  860 */     return (s != null && !s.equals(""));
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
/*  876 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  877 */     if (!isBack(request)) {
/*  878 */       Stocking wmsStocking = new Stocking();
/*  879 */       BeanForm wmsStockingForm = (BeanForm)getForm("/insertWmsStocking", request);
/*  880 */       wmsStockingForm.populate(wmsStocking, "to_form");
/*      */     } 
/*  882 */     putEnumListToRequest(request);
/*  883 */     List<Site> grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/*  884 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*      */     
/*  886 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward selectLocationById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  892 */     response.setContentType("text/json");
/*  893 */     response.setCharacterEncoding("UTF-8");
/*  894 */     JsonConfig cfg = new JsonConfig();
/*      */     
/*  896 */     StorageLocationManager locationManager = ServiceLocator.getStorageLocationManager(request);
/*  897 */     List<Map> list = new ArrayList<Map>();
/*  898 */     String str = request.getParameter("ids");
/*  899 */     String[] ids = str.split(",");
/*  900 */     Map<Object, Object> map = new HashMap<Object, Object>(); byte b; int i; String[] arrayOfString1;
/*  901 */     for (i = (arrayOfString1 = ids).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  902 */       map = new HashMap<Object, Object>();
/*  903 */       StorageLocation location = locationManager.getStorageLocation(Integer.valueOf(Integer.parseInt(id)));
/*  904 */       map.put("code", location.getCode());
/*  905 */       map.put("id", location.getId());
/*  906 */       map.put("address", location.getAddress());
/*  907 */       map.put("describe", location.getDescription());
/*      */       
/*  909 */       map.put("name", location.getBasic_storeroom_id().getCode());
/*  910 */       list.add(map);
/*      */       b++; }
/*      */     
/*  913 */     JSONArray jo = JSONArray.fromObject(list, cfg);
/*  914 */     response.getWriter().print(jo);
/*  915 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward selectLocationByIdAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  921 */     StockingManager stockManager = ServiceLocator.getStockingManager(request);
/*  922 */     StorageLocationManager locationManager = ServiceLocator.getStorageLocationManager(request);
/*  923 */     String str = request.getParameter("reamk");
/*  924 */     Stocking wmsStocking = new Stocking();
/*      */     
/*  926 */     wmsStocking.setOperation(getCurrentUser(request));
/*  927 */     wmsStocking.setStatus(EnabledDisabled.ENABLED);
/*  928 */     wmsStocking.setName(str);
/*  929 */     wmsStocking.setInventoryType(Integer.valueOf(1));
/*  930 */     wmsStocking = stockManager.insertStocking(wmsStocking);
/*  931 */     stockManager.insertWmsStockingItemAllList(wmsStocking);
/*      */     
/*  933 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  934 */     conditions.put(StorageLocationQueryCondition.FREEAE_STATUS_EQ, Integer.valueOf(1));
/*  935 */     conditions.put(StorageLocationQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  936 */     List<StorageLocation> locationList = locationManager.getStorageLocationList(conditions, 0, -1, null, false);
/*  937 */     Integer sumt = stockManager.insertWmsStockingItemNotBylocationList(wmsStocking, locationList);
/*  938 */     return new ActionForward("editWmsStockingAll.do?id=" + wmsStocking.getId(), true);
/*      */   }
/*      */ 
/*      */   
/*      */   private Stocking getStocking(HttpServletRequest request) throws Exception {
/*  943 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/*  944 */     StockingManager wmsStockingManager = ServiceLocator.getStockingManager(request);
/*  945 */     Stocking wmsStocking = wmsStockingManager.getStocking(id);
/*  946 */     if (wmsStocking == null) {
/*  947 */       throw new ActionException("wmsStocking.notFound", id);
/*      */     }
/*  949 */     return wmsStocking;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  955 */     Stocking wmsStocking = getStocking(request);
/*  956 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/*  957 */     List<StockingDetial> stockingItems = wsm.getStockingByMain(wmsStocking.getId());
/*      */ 
/*      */     
/*  960 */     request.setAttribute("x_InventoryCountReport", stockingItems);
/*      */     
/*  962 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*  963 */     putEnumListToRequest(request);
/*  964 */     return mapping.findForward("page");
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
/*      */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1008 */     String[] ids = request.getParameterValues("ids");
/* 1009 */     String type = request.getParameter("type");
/* 1010 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/* 1011 */     BeanForm wmsStockingForm = (BeanForm)form;
/* 1012 */     Stocking wmsStocking = new Stocking();
/* 1013 */     wmsStockingForm.populate(wmsStocking, "to_bean");
/*      */ 
/*      */     
/* 1016 */     wmsStocking.setOperation(getCurrentUser(request));
/* 1017 */     wmsStocking.setStatus(EnabledDisabled.ENABLED);
/* 1018 */     manager.insertStocking(wmsStocking);
/* 1019 */     if (type.equals("1")) {
/* 1020 */       String[] locationsa = array_unique(ids);
/* 1021 */       manager.insertWmsStockingItemList(wmsStocking, locationsa);
/* 1022 */       Integer sumt = manager.insertWmsStockingItemNotBylocationList(wmsStocking, ids);
/* 1023 */       BigDecimal sum = manager.getInventoryDetialByPlanSum(wmsStocking.getId());
/* 1024 */       wmsStocking.setStart_date(new Date());
/* 1025 */       wmsStocking.setPlan_sumQty(sum);
/* 1026 */       wmsStocking.setInventoryType(Integer.valueOf(Integer.parseInt(type)));
/* 1027 */       manager.updateStocking(wmsStocking);
/* 1028 */       manager.updateLocationFreeaeByStocking(wmsStocking);
/* 1029 */     } else if (type.equals("2")) {
/* 1030 */       String[] parts = array_unique(ids);
/* 1031 */       manager.insertWmsStockingItemListByPart(wmsStocking, parts);
/* 1032 */       BigDecimal sum = manager.getInventoryDetialByPlanSum(wmsStocking.getId());
/* 1033 */       wmsStocking.setStart_date(new Date());
/* 1034 */       wmsStocking.setPlan_sumQty(sum);
/* 1035 */       wmsStocking.setInventoryType(Integer.valueOf(Integer.parseInt(type)));
/* 1036 */       manager.updateStocking(wmsStocking);
/* 1037 */       return new ActionForward("editWmsStockingByPart.do?id=" + wmsStocking.getId(), true);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1043 */     return new ActionForward("editWmsStocking.do?id=" + wmsStocking.getId(), true);
/*      */   }
/*      */   public String[] array_unique(String[] a) {
/* 1046 */     Map<Object, Object> map = new HashMap<Object, Object>(); byte b; int i; String[] arrayOfString;
/* 1047 */     for (i = (arrayOfString = a).length, b = 0; b < i; ) { String string = arrayOfString[b];
/* 1048 */       map.put(string, string); b++; }
/*      */     
/* 1050 */     List<String> list = new ArrayList<String>();
/* 1051 */     Iterator<String> iterator = map.keySet().iterator();
/* 1052 */     while (iterator.hasNext()) {
/* 1053 */       String value = iterator.next();
/* 1054 */       list.add(value);
/*      */     } 
/* 1056 */     return list.<String>toArray(new String[list.size()]);
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
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1074 */     Stocking wmsStocking = getStocking(request);
/* 1075 */     request.setAttribute("x_wmsStocking", wmsStocking);
/* 1076 */     if (!isBack(request)) {
/* 1077 */       BeanForm wmsStockingForm = (BeanForm)getForm("/updateWmsStocking", request);
/* 1078 */       wmsStockingForm.populate(wmsStocking, "to_form");
/*      */     } 
/*      */     
/* 1081 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/* 1082 */     List<StockingDetial> stockingItems = wsm.getStockingByMain(wmsStocking.getId());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1088 */     FunctionManager functionManager = ServiceLocator.getFunctionManager(request);
/* 1089 */     UserManager userManager = ServiceLocator.getUserManager(request);
/* 1090 */     Boolean chieckCreateMaterial = Boolean.valueOf(userManager.hasGlobalPowers(getCurrentUser(request), functionManager.getFunction(Integer.valueOf(21150106))));
/* 1091 */     if (chieckCreateMaterial.booleanValue()) {
/* 1092 */       request.setAttribute("mfg", Boolean.valueOf(true));
/*      */     }
/*      */     
/* 1095 */     request.setAttribute("x_InventoryCountReport", stockingItems);
/* 1096 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*      */     
/* 1098 */     request.setAttribute("path", request.getContextPath());
/* 1099 */     putEnumListToRequest(request);
/* 1100 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward editByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1106 */     Stocking wmsStocking = getStocking(request);
/* 1107 */     request.setAttribute("x_wmsStocking", wmsStocking);
/* 1108 */     if (!isBack(request)) {
/* 1109 */       BeanForm wmsStockingForm = (BeanForm)getForm("/updateWmsStocking", request);
/* 1110 */       wmsStockingForm.populate(wmsStocking, "to_form");
/*      */     } 
/*      */     
/* 1113 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/* 1114 */     List<StockingDetial> stockingItems = wsm.getStockingByMain(wmsStocking.getId());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1120 */     FunctionManager functionManager = ServiceLocator.getFunctionManager(request);
/* 1121 */     UserManager userManager = ServiceLocator.getUserManager(request);
/* 1122 */     Boolean chieckCreateMaterial = Boolean.valueOf(userManager.hasGlobalPowers(getCurrentUser(request), functionManager.getFunction(Integer.valueOf(21150106))));
/* 1123 */     if (chieckCreateMaterial.booleanValue()) {
/* 1124 */       request.setAttribute("mfg", Boolean.valueOf(true));
/*      */     }
/*      */     
/* 1127 */     request.setAttribute("x_InventoryCountReport", stockingItems);
/* 1128 */     request.setAttribute("x_wmsStocking", wmsStocking);
/*      */     
/* 1130 */     request.setAttribute("path", request.getContextPath());
/* 1131 */     putEnumListToRequest(request);
/* 1132 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteWmsPlanToGoOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1138 */     response.setContentType("text/json");
/* 1139 */     response.setCharacterEncoding("UTF-8");
/* 1140 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1142 */     String id = request.getParameter("id");
/* 1143 */     WmsPlanToGoOutManager goOutManager = ServiceLocator.getWmsPlanToGoOutManager(request);
/* 1144 */     WmsPlanToGoOut planToGoOut = goOutManager.getWmsPlanToGoOut(Integer.valueOf(Integer.parseInt(id)));
/* 1145 */     List<WmsPlanToGoOutItem> itemList = goOutManager.getWmsPlanToGoOutItemByMain(planToGoOut.getId());
/* 1146 */     for (WmsPlanToGoOutItem item : itemList) {
/* 1147 */       goOutManager.deleteWmsPlanToGoOutItem(item);
/*      */     }
/* 1149 */     goOutManager.deleteWmsPlanToGoOut(planToGoOut);
/*      */     
/* 1151 */     JSONArray jo = JSONArray.fromObject(null, cfg);
/* 1152 */     response.getWriter().print(jo);
/* 1153 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   private Map constructInventoryQueryMap(InventoryQueryForm queryForm) {
/* 1157 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1158 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*      */     
/* 1160 */     return conditions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listWmsStockingItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1167 */     StockingManager stockingManager = ServiceLocator.getStockingManager(request);
/* 1168 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*      */     
/* 1170 */     String id = request.getParameter("stocking");
/* 1171 */     String location = request.getParameter("locationId");
/* 1172 */     String part = request.getParameter("part");
/*      */     
/* 1174 */     List<Box> boxs = boxManager.getBoxByLocation(location, part);
/* 1175 */     List<Box> boxNew = new ArrayList<Box>();
/* 1176 */     for (Box box : boxs) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1184 */       StockingScanRecord scanRecord = stockingManager.getStockingScanRecordByLotser(box.getLot().getId(), 
/* 1185 */           Integer.valueOf(Integer.parseInt(id)));
/* 1186 */       if (scanRecord == null) {
/* 1187 */         box.setIs_scan(YesNo.NO);
/*      */       } else {
/* 1189 */         box.setIs_scan(YesNo.YES);
/*      */       } 
/* 1191 */       boxNew.add(box);
/*      */     } 
/*      */     
/* 1194 */     request.setAttribute("x_boxs", boxNew);
/* 1195 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward listWmsStockingItemByPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1200 */     StockingManager stockingManager = ServiceLocator.getStockingManager(request);
/* 1201 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*      */     
/* 1203 */     String id = request.getParameter("stocking");
/* 1204 */     String part = request.getParameter("part");
/*      */     
/* 1206 */     List<Box> boxs = boxManager.getBoxByPart(part);
/* 1207 */     List<Box> boxNew = new ArrayList<Box>();
/* 1208 */     for (Box box : boxs) {
/* 1209 */       StockingScanRecord scanRecord = stockingManager.getStockingScanRecordByLotser(box.getLot().getId(), 
/* 1210 */           Integer.valueOf(Integer.parseInt(id)));
/* 1211 */       if (scanRecord == null) {
/* 1212 */         box.setIs_scan(YesNo.NO);
/*      */       } else {
/* 1214 */         box.setIs_scan(YesNo.YES);
/*      */       } 
/* 1216 */       boxNew.add(box);
/*      */     } 
/*      */     
/* 1219 */     request.setAttribute("x_boxs", boxNew);
/* 1220 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1226 */     WmsStockingQueryForm queryForm = (WmsStockingQueryForm)form;
/* 1227 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1228 */       queryForm.setOrder(StockingQueryOrder.ID.getName());
/* 1229 */       queryForm.setDescend(false);
/* 1230 */       queryForm.setStatus("0");
/*      */     } 
/* 1232 */     StockingManager fm = ServiceLocator.getStockingManager(request);
/* 1233 */     Map conditions = constructQueryMap(queryForm);
/* 1234 */     String exportType = queryForm.getExportType();
/* 1235 */     if (StringUtils.isNotEmpty(exportType)) {
/* 1236 */       List data = fm.getStockingList(conditions, 0, -1, StockingQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/* 1238 */       int index = SessionTempFile.createNewTempFile(request);
/* 1239 */       String fileName = "wmsStocking";
/* 1240 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 1243 */               MessageResources messages = WmsStockingAction.this.getResources(request);
/* 1244 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.id"));
/* 1245 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.name"));
/* 1246 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.createDate"));
/* 1247 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.createUser"));
/* 1248 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.count"));
/* 1249 */               row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.enabled"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 1253 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/* 1254 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/* 1255 */               row.add(BeanHelper.getBeanPropertyValue(data, "createDate"));
/* 1256 */               row.add(BeanHelper.getBeanPropertyValue(data, "createUser"));
/* 1257 */               row.add(BeanHelper.getBeanPropertyValue(data, "count"));
/* 1258 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled"));
/*      */             }
/*      */           });
/* 1261 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/* 1263 */     if (queryForm.isFirstInit()) {
/* 1264 */       queryForm.init(fm.getStockingListCount(conditions));
/*      */     } else {
/* 1266 */       queryForm.init();
/*      */     } 
/* 1268 */     List result = fm.getStockingList(conditions, 
/* 1269 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1270 */         StockingQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1272 */     request.setAttribute("X_RESULTLIST", result);
/* 1273 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteDeparmentList(request));
/* 1274 */     putEnumListToRequest(request);
/* 1275 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward viewWmsStockingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1281 */     String id = request.getParameter("id");
/* 1282 */     StockingManager stockingManager = ServiceLocator.getStockingManager(request);
/* 1283 */     Stocking stocking = stockingManager.getStocking(Integer.valueOf(Integer.parseInt(id)));
/* 1284 */     List<StockingDetial> stockingItems = stockingManager.getWmsStockingByStocking(stocking.getCode());
/*      */ 
/*      */     
/* 1287 */     request.setAttribute("x_stockingItems", stockingItems);
/* 1288 */     request.setAttribute("x_stocking", stocking);
/* 1289 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward lookOver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1294 */     StockingManager stockingManager = ServiceLocator.getStockingManager(request);
/* 1295 */     String id = request.getParameter("id");
/* 1296 */     Stocking stocking = stockingManager.getStocking(Integer.valueOf(Integer.parseInt(id)));
/* 1297 */     List<StockingDetial> stockingItems = stockingManager.getWmsStockingByStocking(stocking.getCode());
/* 1298 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/* 1299 */     List<StockingRecord> data = wsm.getStockingDifferenceByStockingRecord(stocking.getId());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1306 */     request.setAttribute("x_stockingItems", stockingItems);
/* 1307 */     request.setAttribute("x_stocking", stocking);
/* 1308 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward exportEXCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1314 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/* 1315 */     Stocking stocking = getWmsStocking(request);
/* 1316 */     String exportType = "EXCEL";
/* 1317 */     List<Map> data = manager.getWmsStockingDifference(stocking);
/*      */     
/* 1319 */     int index = SessionTempFile.createNewTempFile(request);
/* 1320 */     String fileName = "wmsStocking";
/* 1321 */     String suffix = ExportUtil.export(exportType, data, request, 
/* 1322 */         new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */         {
/*      */           public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 1325 */             MessageResources messages = WmsStockingAction.this.getResources(request);
/* 1326 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.location"));
/* 1327 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.wmsPart"));
/* 1328 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.lotser"));
/* 1329 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.describe"));
/* 1330 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.describe2"));
/* 1331 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.amount"));
/* 1332 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.stockAmount"));
/* 1333 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.difference"));
/* 1334 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.person"));
/*      */           }
/*      */ 
/*      */           
/*      */           public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 1339 */             Map map = (Map)data;
/* 1340 */             WmsPart wmsPart = (WmsPart)map.get("wmsPart");
/* 1341 */             row.add(map.get("location"));
/* 1342 */             if (wmsPart != null) {
/* 1343 */               row.add(wmsPart.getId());
/*      */             } else {
/* 1345 */               row.add("");
/*      */             } 
/* 1347 */             row.add((String)map.get("lotSer"));
/* 1348 */             if (wmsPart != null) {
/* 1349 */               row.add(wmsPart.getDescribe1());
/* 1350 */               row.add(wmsPart.getDescribe2());
/*      */             } else {
/* 1352 */               row.add("");
/* 1353 */               row.add("");
/*      */             } 
/*      */             
/* 1356 */             row.add((String)map.get("amount"));
/* 1357 */             row.add((String)map.get("stockingAmount"));
/* 1358 */             row.add((String)map.get("difference"));
/* 1359 */             row.add("");
/*      */           }
/*      */         });
/*      */     
/* 1363 */     return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward exportEndEXCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1369 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/* 1370 */     Stocking stocking = getWmsStocking(request);
/* 1371 */     String exportType = "EXCEL";
/* 1372 */     List<StockingRecord> data = manager.getStockingDifferenceByStockingRecord(stocking.getId());
/*      */     
/* 1374 */     int index = SessionTempFile.createNewTempFile(request);
/* 1375 */     String fileName = "wmsStocking";
/* 1376 */     String suffix = ExportUtil.export(exportType, data, request, 
/* 1377 */         new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */         {
/*      */           public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 1380 */             MessageResources messages = WmsStockingAction.this.getResources(request);
/* 1381 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "location"));
/* 1382 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.lotser"));
/* 1383 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.wmsPart"));
/* 1384 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.describe"));
/* 1385 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.describe2"));
/* 1386 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.stockAmount"));
/* 1387 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.amount"));
/* 1388 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.difference"));
/* 1389 */             row.add(messages.getMessage(WmsStockingAction.this.getLocale(request), "wmsStocking.person"));
/*      */           }
/*      */           
/*      */           public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 1393 */             row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 1394 */             Box box = (Box)BeanHelper.getBeanPropertyValue(data, "orderBox");
/* 1395 */             if (box != null) {
/* 1396 */               row.add(BeanHelper.getBeanPropertyValue(data, "orderBox.lotSer.id"));
/* 1397 */               row.add(BeanHelper.getBeanPropertyValue(data, "orderBox.wmsPart.id"));
/* 1398 */               row.add(BeanHelper.getBeanPropertyValue(data, "orderBox.wmsPart.describe1"));
/* 1399 */               row.add(BeanHelper.getBeanPropertyValue(data, "orderBox.wmsPart.describe2"));
/*      */             } else {
/* 1401 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.lotSer.id"));
/* 1402 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.wmsPart.id"));
/* 1403 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.wmsPart.describe1"));
/* 1404 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.wmsPart.describe2"));
/*      */             } 
/* 1406 */             row.add(BeanHelper.getBeanPropertyValue(data, "inventoryAmount"));
/* 1407 */             row.add(BeanHelper.getBeanPropertyValue(data, "stockAmount"));
/* 1408 */             row.add(BeanHelper.getBeanPropertyValue(data, "differenceAmount"));
/*      */           }
/*      */         });
/*      */     
/* 1412 */     return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
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
/*      */   public ActionForward getTest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1518 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/* 1519 */     String[] array = { "SM00215010500082;1013-20141008-0006;A111;1" };
/*      */ 
/*      */     
/* 1522 */     return new ActionForward("listWmsStocking.do", true);
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward updateWmsStockingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1527 */     Stocking wmsStocking = getStocking(request);
/* 1528 */     StockingManager manager = ServiceLocator.getStockingManager(request);
/* 1529 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 1530 */     User user = getCurrentUser(request);
/* 1531 */     StockingConfirmStatusTransferThread stockingConfirmStatusTransferThread = new StockingConfirmStatusTransferThread(boxManager, manager, wmsStocking, user);
/* 1532 */     if (stockingConfirmStatusTransferThread.isAlive()) {
/* 1533 */       postGlobalMessage("The.system.is.processing.data.please.later ", request.getSession());
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1539 */       stockingConfirmStatusTransferThread.start();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1546 */     return new ActionForward("listWmsStockingDifference.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward startStockingAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1552 */     Stocking wmsStocking = getStocking(request);
/* 1553 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/* 1554 */     StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
/* 1555 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1556 */     conditions.put(StorageLocationQueryCondition.FREEAE_STATUS_EQ, Integer.valueOf(1));
/* 1557 */     conditions.put(StorageLocationQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*      */     
/* 1559 */     List<StorageLocation> locationList = storageLocationManager.getStorageLocationList(conditions, 0, -1, null, false);
/*      */     
/* 1561 */     BigDecimal sum = wsm.getInventoryDetialByPlanSum(wmsStocking.getId());
/* 1562 */     wmsStocking.setStart_date(new Date());
/* 1563 */     wmsStocking.setPlan_sumQty(sum);
/* 1564 */     wsm.updateStocking(wmsStocking);
/* 1565 */     wsm.updateLocationFreeaeByStocking(wmsStocking);
/*      */     
/* 1567 */     return new ActionForward("editWmsStocking.do?id=" + wmsStocking.getId(), true);
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
/*      */   public ActionForward selectLocationByFreeae(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1615 */     response.setContentType("text/json");
/* 1616 */     response.setCharacterEncoding("UTF-8");
/* 1617 */     JsonConfig cfg = new JsonConfig();
/*      */     
/* 1619 */     StorageLocationManager locationManager = ServiceLocator.getStorageLocationManager(request);
/* 1620 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1621 */     conditions.put(StorageLocationQueryCondition.FREEAE_STATUS_EQ, YesNo.YES);
/* 1622 */     List<StorageLocation> locationList = locationManager.getStorageLocationList(conditions, 0, -1, null, false);
/* 1623 */     List<Map> list = new ArrayList<Map>();
/*      */ 
/*      */     
/* 1626 */     for (StorageLocation location : locationList) {
/* 1627 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 1628 */       map.put("code", location.getCode());
/* 1629 */       map.put("freeae", "已冻结");
/* 1630 */       list.add(map);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1637 */     JSONArray jo = JSONArray.fromObject(list, cfg);
/* 1638 */     response.getWriter().print(jo);
/* 1639 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward updateRecordsByType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1644 */     response.setContentType("text/json");
/* 1645 */     response.setCharacterEncoding("UTF-8");
/* 1646 */     JsonConfig cfg = new JsonConfig();
/* 1647 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1648 */     StockingManager wsm = ServiceLocator.getStockingManager(request);
/* 1649 */     String boxId = request.getParameter("boxId");
/* 1650 */     String stockingId = request.getParameter("stockingId");
/* 1651 */     String type = request.getParameter("type");
/* 1652 */     String[] arrays = boxId.split(","); byte b; int i; String[] arrayOfString1;
/* 1653 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String str = arrayOfString1[b];
/* 1654 */       conditions.clear();
/* 1655 */       conditions.put(StockingQueryCondition.STOCKING_ID_EQ, stockingId);
/* 1656 */       conditions.put(StockingQueryCondition.BOX_LOT_ID_EQ, str);
/* 1657 */       List<StockingRecord> list = wsm.getStockingRecordList(conditions, 0, -1, null, false);
/* 1658 */       for (StockingRecord stockingRecord : list) {
/* 1659 */         stockingRecord.setType(Integer.valueOf(Integer.parseInt(type)));
/* 1660 */         wsm.updateStockingRecord(stockingRecord);
/*      */       }  b++; }
/*      */     
/* 1663 */     JSONArray jo = JSONArray.fromObject(null, cfg);
/* 1664 */     response.getWriter().print(jo);
/* 1665 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward selectScanRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1671 */     WmsStockingScanRecordQueryForm queryForm = (WmsStockingScanRecordQueryForm)form;
/*      */     
/* 1673 */     Stocking stocking = getStocking(request);
/* 1674 */     StockingManager fm = ServiceLocator.getStockingManager(request);
/* 1675 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1676 */     conditions.put(StockingScanRecordQueryCondition.STOCKING_EQ, stocking.getCode());
/* 1677 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/* 1679 */     if (queryForm.isFirstInit()) {
/* 1680 */       queryForm.init(fm.getStockingScanRecordListCount(conditions));
/*      */     } else {
/* 1682 */       queryForm.init();
/*      */     } 
/* 1684 */     List result = fm.getStockingScanRecordList(conditions, 
/* 1685 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 1686 */         StockingScanRecordQueryOrder.ID, queryForm.isDescend());
/*      */     
/* 1688 */     request.setAttribute("X_RESULTLIST", result);
/* 1689 */     request.setAttribute("x_wmsStocking", stocking);
/*      */     
/* 1691 */     request.setAttribute("x_selType", Integer.valueOf(130));
/* 1692 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward verifyWmsStockingDifference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1697 */     response.setContentType("text/json");
/* 1698 */     response.setCharacterEncoding("UTF-8");
/* 1699 */     JsonConfig cfg = new JsonConfig();
/* 1700 */     StockingManager fm = ServiceLocator.getStockingManager(request);
/* 1701 */     Stocking stocking = getStocking(request);
/*      */     
/* 1703 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1704 */     conditions.put(StockingQueryCondition.STOCKING_ID_EQ, stocking.getId());
/* 1705 */     int count = fm.getStockingRecordListCount(conditions);
/* 1706 */     conditions.put(StockingQueryCondition.TYPE_IS_NOT_NULL, null);
/* 1707 */     List<StockingRecord> records = fm.getStockingRecordList(conditions, 0, -1, StockingQueryOrder.ID, false);
/* 1708 */     if (records.size() == count) {
/* 1709 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1710 */       response.getWriter().print(jo);
/*      */     } else {
/* 1712 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
/* 1713 */       response.getWriter().print(jo);
/*      */     } 
/*      */     
/* 1716 */     return null;
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/wms/WmsStockingAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */