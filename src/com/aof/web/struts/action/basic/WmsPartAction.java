/*      */ package com.aof.web.struts.action.basic;
/*      */ 
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.basic.BasicPartPrice;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.query.BasicPartPriceQueryCondition;
/*      */ import com.aof.model.basic.query.BasicPartPriceQueryOrder;
/*      */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*      */ import com.aof.model.basic.query.WmsPartQueryOrder;
/*      */ import com.aof.model.basicDataView.JitShipPart;
/*      */ import com.aof.model.basicDataView.PartForecastNeedReport;
/*      */ import com.aof.model.basicDataView.PoPartSumNumber;
/*      */ import com.aof.model.basicDataView.SkPartSumNumber;
/*      */ import com.aof.model.basicDataView.query.JitShipPartQueryCondition;
/*      */ import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
/*      */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryCondition;
/*      */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
/*      */ import com.aof.model.basicDataView.query.PoPartSumNumberQueryOrder;
/*      */ import com.aof.model.basicDataView.query.SkPartSumNumberQueryOrder;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.WmsPartType;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.product.BasicCustomer;
/*      */ import com.aof.model.schedule.NjitNpoPlan;
/*      */ import com.aof.service.Product.BasicCustomerManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.basic.BasicPartPriceManager;
/*      */ import com.aof.service.basic.WmsPartManager;
/*      */ import com.aof.service.basicDataView.BasicDataViewManager;
/*      */ import com.aof.service.po.BoxManager;
/*      */ import com.aof.service.po.PortalShipOrderManager;
/*      */ import com.aof.service.schedule.NjitNpoPlanManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.basic.InventoryQueryForm;
/*      */ import com.aof.web.struts.form.basic.WmsPartQueryForm;
/*      */ import com.aof.web.struts.form.basicDataView.BasicDataViewQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.utils.BeanHelper;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.FileOutputStream;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
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
/*      */ 
/*      */ public class WmsPartAction
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  106 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  107 */     WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
/*  108 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  109 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  110 */       queryForm.setDescend(false);
/*  111 */       queryForm.setStatus("0");
/*      */     } 
/*  113 */     String type = request.getParameter("type");
/*      */     
/*  115 */     Map<WmsPartQueryCondition, String> conditions = constructQueryMap(queryForm);
/*      */     
/*  117 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  118 */     if (type != null && 
/*  119 */       type.equals("1")) {
/*  120 */       conditions.put(WmsPartQueryCondition.QUALITYPE, "S140");
/*  121 */       request.setAttribute("x_type", Integer.valueOf(1));
/*      */     } 
/*      */ 
/*      */     
/*  125 */     if (queryForm.isFirstInit()) {
/*  126 */       queryForm.init(manager.getWmsPartListCount(conditions));
/*      */     } else {
/*  128 */       queryForm.init();
/*      */     } 
/*  130 */     List results = manager.getWmsPartList(conditions, 
/*  131 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  132 */         WmsPartQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  134 */     request.setAttribute("X_RESULTLIST", results);
/*  135 */     putEnumListToRequest(request);
/*  136 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward selectDeliveryWmsPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  141 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  142 */     WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
/*  143 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  144 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  145 */       queryForm.setDescend(false);
/*  146 */       queryForm.setStatus("0");
/*      */     } 
/*      */     
/*  149 */     Map<WmsPartQueryCondition, EnabledDisabled> conditions = constructQueryMap(queryForm);
/*      */     
/*  151 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  152 */     conditions.put(WmsPartQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/*      */     
/*  154 */     if (queryForm.isFirstInit()) {
/*  155 */       queryForm.init(manager.getWmsPartListCount(conditions));
/*      */     } else {
/*  157 */       queryForm.init();
/*      */     } 
/*  159 */     List results = manager.getWmsPartList(conditions, 
/*  160 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  161 */         WmsPartQueryOrder.ID, queryForm.isDescend());
/*  162 */     request.setAttribute("x_selType", Integer.valueOf(126));
/*  163 */     request.setAttribute("X_RESULTLIST", results);
/*  164 */     putEnumListToRequest(request);
/*  165 */     return mapping.findForward("page");
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
/*      */   public ActionForward selectCustomerPlanDeliveryWmsPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  245 */     InventoryQueryForm queryForm = (InventoryQueryForm)form;
/*  246 */     String idString = request.getParameter("partId");
/*  247 */     String customerId = request.getParameter("customerId");
/*  248 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  249 */       queryForm.setOrder(PoPartSumNumberQueryOrder.PART_ID.getName());
/*  250 */       queryForm.setDescend(false);
/*      */     } 
/*  252 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*  253 */     BasicPartPriceManager basicPartPriceManager = ServiceLocator.getBasicPartPriceManager(request);
/*  254 */     BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
/*  255 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*      */     
/*  257 */     BasicCustomer basicCustomer = basicCustomerManager.getById(Integer.valueOf(Integer.parseInt(customerId)));
/*  258 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  259 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  267 */     if (queryForm.isFirstInit()) {
/*  268 */       queryForm.init(manager.getBoxItemListCount(conditions));
/*      */     } else {
/*  270 */       queryForm.init();
/*      */     } 
/*  272 */     List<PoPartSumNumber> result = manager.getBoxItemList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  273 */         PoPartSumNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  274 */     if (basicCustomer != null) {
/*  275 */       for (PoPartSumNumber item : result) {
/*  276 */         conditions = new HashMap<Object, Object>();
/*  277 */         conditions.put(BasicPartPriceQueryCondition.PARTID_EQ, item.getPart().getId());
/*  278 */         conditions.put(BasicPartPriceQueryCondition.STARTDATE_LE, format.format(new Date()));
/*  279 */         conditions.put(BasicPartPriceQueryCondition.EXPIREDATE_GE, format.format(new Date()));
/*      */         
/*  281 */         conditions.put(BasicPartPriceQueryCondition.CUSTOMER_EQ, basicCustomer.getCode());
/*  282 */         List<BasicPartPrice> partPriceList = basicPartPriceManager.getList(conditions, 0, -1, BasicPartPriceQueryOrder.ID, true);
/*  283 */         if (partPriceList.size() > 0) {
/*  284 */           BasicPartPrice basicPartPrice = partPriceList.get(0);
/*  285 */           if (basicPartPrice.getAmt() != null) {
/*  286 */             item.getPart().setPartPrice(basicPartPrice);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  292 */     if (idString != null && 
/*  293 */       !idString.equals("")) {
/*  294 */       String[] ids = idString.split(",");
/*  295 */       if (ids.length > 0) {
/*  296 */         for (PoPartSumNumber item : result) {
/*  297 */           String partId = item.getPart().getId();
/*  298 */           Boolean isClose = Boolean.valueOf(false);
/*      */           
/*  300 */           for (int i = 0; i < ids.length; i++) {
/*  301 */             if (!ids[i].equals("") && 
/*  302 */               partId.equals(ids[i])) {
/*  303 */               isClose = Boolean.valueOf(true);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  308 */           if (isClose.booleanValue()) {
/*  309 */             item.getPart().setChecked("checked");
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  316 */     request.setAttribute("x_customerId", customerId);
/*  317 */     request.setAttribute("x_selType", Integer.valueOf(132));
/*  318 */     request.setAttribute("X_RESULTLIST", result);
/*  319 */     putEnumListToRequest(request);
/*  320 */     return mapping.findForward("page");
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
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  335 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  336 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  337 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  338 */       queryForm.setDescend(false);
/*      */     } 
/*      */     
/*  341 */     WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
/*      */     
/*  343 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  345 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */     
/*  347 */     String exportType = queryForm.getExportType();
/*  348 */     if (StringUtils.isNotEmpty(exportType)) {
/*  349 */       List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/*  351 */       int index = SessionTempFile.createNewTempFile(request);
/*  352 */       String fileName = "wmsPart";
/*  353 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*      */             {
/*  366 */               row.add("ASN号");
/*  367 */               row.add("物料编号");
/*      */               
/*  369 */               row.add("描述1");
/*  370 */               row.add("描述2");
/*  371 */               row.add("类型");
/*  372 */               row.add("采购供应商编码");
/*  373 */               row.add("产品类");
/*  374 */               row.add("标准包装量");
/*  375 */               row.add("高储库存");
/*  376 */               row.add("低储库存");
/*  377 */               row.add("安全库存");
/*  378 */               row.add("单位");
/*  379 */               row.add("是否冻结");
/*  380 */               row.add("状态");
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  415 */               row.add(BeanHelper.getBeanPropertyValue(data, "productSpecifications"));
/*  416 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */               
/*  418 */               row.add(BeanHelper.getBeanPropertyValue(data, "describe1"));
/*  419 */               row.add(BeanHelper.getBeanPropertyValue(data, "describe2"));
/*  420 */               row.add(BeanHelper.getBeanPropertyValue(data, "drwgLoc"));
/*  421 */               row.add(BeanHelper.getBeanPropertyValue(data, "vend"));
/*  422 */               row.add(BeanHelper.getBeanPropertyValue(data, "productClass"));
/*  423 */               row.add(BeanHelper.getBeanPropertyValue(data, "ord_mult"));
/*  424 */               row.add(BeanHelper.getBeanPropertyValue(data, "highQty"));
/*  425 */               row.add(BeanHelper.getBeanPropertyValue(data, "lowQty"));
/*  426 */               row.add(BeanHelper.getBeanPropertyValue(data, "securityQty"));
/*  427 */               row.add(BeanHelper.getBeanPropertyValue(data, "unit"));
/*  428 */               WmsPart part = (WmsPart)data;
/*  429 */               if (part.getFreeze_status() == YesNo.NO) {
/*  430 */                 row.add("No");
/*  431 */               } else if (part.getFreeze_status() == YesNo.YES) {
/*  432 */                 row.add("Yes");
/*      */               } else {
/*  434 */                 row.add("");
/*      */               } 
/*  436 */               if (part.getEnabled() == EnabledDisabled.DISABLED) {
/*  437 */                 row.add("不可用");
/*  438 */               } else if (part.getEnabled() == EnabledDisabled.ENABLED) {
/*  439 */                 row.add("可用");
/*      */               } else {
/*  441 */                 row.add("");
/*      */               } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  476 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  478 */     if (queryForm.isFirstInit()) {
/*  479 */       queryForm.init(fm.getWmsPartListCount(conditions));
/*      */     } else {
/*  481 */       queryForm.init();
/*      */     } 
/*  483 */     int pageNo = queryForm.getPageNoAsInt();
/*  484 */     int pageSize = queryForm.getPageSizeAsInt();
/*  485 */     List result = fm.getWmsPartList(conditions, pageNo, pageSize, 
/*  486 */         WmsPartQueryOrder.ID, queryForm.isDescend());
/*      */     
/*  488 */     request.setAttribute("X_RESULTLIST", result);
/*  489 */     request.setAttribute("x_selType", Integer.valueOf(11));
/*  490 */     request.setAttribute("x_selType1", Integer.valueOf(102));
/*  491 */     putEnumListToRequestNoSite(request);
/*  492 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward listPartBySampling(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  497 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  498 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  499 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  500 */       queryForm.setDescend(false);
/*  501 */       queryForm.setStatus("0");
/*      */     } 
/*  503 */     WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
/*      */     
/*  505 */     Map conditions = constructQueryMap(queryForm);
/*  506 */     String exportType = queryForm.getExportType();
/*  507 */     if (StringUtils.isNotEmpty(exportType)) {
/*  508 */       List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/*  510 */       int index = SessionTempFile.createNewTempFile(request);
/*  511 */       String fileName = "wmsPart";
/*  512 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  515 */               MessageResources messages = WmsPartAction.this.getResources(request);
/*  516 */               row.add(messages.getMessage(WmsPartAction.this.getLocale(request), "wmsPart.id"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  520 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  524 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  526 */     if (queryForm.isFirstInit()) {
/*  527 */       queryForm.init(fm.getWmsPartListCount(conditions));
/*      */     } else {
/*  529 */       queryForm.init();
/*      */     } 
/*  531 */     int pageNo = queryForm.getPageNoAsInt();
/*  532 */     int pageSize = queryForm.getPageSizeAsInt();
/*  533 */     List result = fm.getWmsPartList(conditions, pageNo, pageSize, WmsPartQueryOrder.ID, queryForm.isDescend());
/*  534 */     request.setAttribute("X_RESULTLIST", result);
/*  535 */     putEnumListToRequest(request);
/*  536 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   public ActionForward listPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  540 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  541 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  542 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  543 */       queryForm.setDescend(false);
/*  544 */       queryForm.setStatus("0");
/*      */     } 
/*  546 */     WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
/*      */     
/*  548 */     Map conditions = constructQueryMap(queryForm);
/*  549 */     String exportType = queryForm.getExportType();
/*  550 */     if (StringUtils.isNotEmpty(exportType)) {
/*  551 */       List data = fm.getWmsPartList(conditions, 0, -1, WmsPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/*  553 */       int index = SessionTempFile.createNewTempFile(request);
/*  554 */       String fileName = "wmsPart";
/*  555 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  558 */               MessageResources messages = WmsPartAction.this.getResources(request);
/*  559 */               row.add(messages.getMessage(WmsPartAction.this.getLocale(request), "wmsPart.id"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  563 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*      */             }
/*      */           });
/*      */       
/*  567 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*  569 */     if (queryForm.isFirstInit()) {
/*  570 */       queryForm.init(fm.getWmsPartListCount(conditions));
/*      */     } else {
/*  572 */       queryForm.init();
/*      */     } 
/*  574 */     int pageNo = queryForm.getPageNoAsInt();
/*  575 */     int pageSize = queryForm.getPageSizeAsInt();
/*  576 */     List result = fm.getWmsPartList(conditions, pageNo, pageSize, WmsPartQueryOrder.ID, queryForm.isDescend());
/*  577 */     request.setAttribute("X_RESULTLIST", result);
/*  578 */     putEnumListToRequest(request);
/*  579 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   private Map constructQueryMap(WmsPartQueryForm queryForm) {
/*  583 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  584 */     String id = queryForm.getId();
/*  585 */     if (id != null && !id.equals("")) {
/*  586 */       conditions.put(WmsPartQueryCondition.ID_EQ, id);
/*      */     }
/*  588 */     String productClass = queryForm.getProductClass();
/*  589 */     if (productClass != null && !productClass.equals("")) {
/*  590 */       conditions.put(WmsPartQueryCondition.ID_EQ, productClass);
/*      */     }
/*  592 */     String qualityType = queryForm.getQualityType();
/*  593 */     if (qualityType != null && !qualityType.equals("")) {
/*  594 */       conditions.put(WmsPartQueryCondition.QUALITYPE, qualityType);
/*      */     }
/*  596 */     String group = queryForm.getGroup();
/*  597 */     if (group != null && !group.equals("")) {
/*  598 */       conditions.put(WmsPartQueryCondition.GROUP, group);
/*      */     }
/*  600 */     String name = queryForm.getName();
/*  601 */     if (name != null && !name.equals("")) {
/*  602 */       conditions.put(WmsPartQueryCondition.NAME_EQ, name);
/*      */     }
/*  604 */     String describe1 = queryForm.getDescribe1();
/*  605 */     if (describe1 != null && !describe1.equals("")) {
/*  606 */       conditions.put(WmsPartQueryCondition.DESCRIBE1_EQ, describe1);
/*      */     }
/*  608 */     String describe2 = queryForm.getDescribe2();
/*  609 */     if (describe2 != null && !describe2.equals("")) {
/*  610 */       conditions.put(WmsPartQueryCondition.DESCRIBE2_EQ, describe2);
/*      */     }
/*  612 */     String unit = queryForm.getUnit();
/*  613 */     if (unit != null && !unit.equals("")) {
/*  614 */       conditions.put(WmsPartQueryCondition.UNIT_EQ, unit);
/*      */     }
/*  616 */     String status = queryForm.getStatus();
/*  617 */     if (status != null && !status.equals("")) {
/*  618 */       conditions.put(WmsPartQueryCondition.ENABLED_EQ, status);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  624 */     return conditions;
/*      */   }
/*      */   private WmsPart getWmsPart(HttpServletRequest request) throws Exception {
/*  627 */     String id = request.getParameter("id");
/*  628 */     WmsPartManager wmsPartManager = ServiceLocator.getWmsPartManager(request);
/*  629 */     WmsPart wmsPart = wmsPartManager.getWmsPart(id);
/*  630 */     if (wmsPart == null)
/*  631 */       throw new ActionException("wmsPart.notFound", id); 
/*  632 */     return wmsPart;
/*      */   }
/*      */   
/*      */   private void putEnumListToRequest(HttpServletRequest request) {
/*  636 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*  637 */     request.setAttribute("X_SITEID", ServiceLocator.getSiteManager(request).getAllEnabledSiteList());
/*  638 */     request.setAttribute("X_PartType", PersistentEnum.getEnumList(WmsPartType.class));
/*  639 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/*      */   }
/*      */   
/*      */   private void putEnumListToRequestNoSite(HttpServletRequest request) {
/*  643 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*  644 */     request.setAttribute("X_PartType", PersistentEnum.getEnumList(WmsPartType.class));
/*  645 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
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
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  661 */     WmsPart wmsPart = getWmsPart(request);
/*      */     
/*  663 */     request.setAttribute("x_wmsPart", wmsPart);
/*  664 */     if (!isBack(request)) {
/*      */       
/*  666 */       BeanForm wmsPartForm = (BeanForm)getForm("/updateWmsPart", request);
/*  667 */       wmsPartForm.populate(wmsPart, "to_form");
/*      */     } 
/*  669 */     putEnumListToRequest(request);
/*  670 */     return mapping.findForward("page");
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
/*      */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  685 */     WmsPart wmsPart = getWmsPart(request);
/*  686 */     WmsPartManager cm = ServiceLocator.getWmsPartManager(request);
/*      */     try {
/*  688 */       cm.deleteWmsPart(wmsPart);
/*      */     }
/*  690 */     catch (Throwable t) {
/*      */       
/*  692 */       throw new ActionException("wmsPart.delete.fail");
/*      */     } 
/*  694 */     return mapping.findForward("success");
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
/*  710 */     BeanForm wmsPartForm = (BeanForm)form;
/*  711 */     WmsPart wmsPart = new WmsPart();
/*  712 */     wmsPartForm.populate(wmsPart, "to_bean");
/*  713 */     WmsPartManager wmsPartManager = ServiceLocator.getWmsPartManager(request);
/*      */     
/*  715 */     request.setAttribute("X_OBJECT", wmsPartManager.updateWmsPart(wmsPart));
/*  716 */     request.setAttribute("X_ROWPAGE", "wmsbasic/wmsPart/row.jsp");
/*  717 */     return mapping.findForward("success");
/*      */   }
/*      */   
/*      */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/*  721 */     return getAndCheckSite("site_id", request);
/*      */   }
/*      */   
/*      */   private boolean hasSite(HttpServletRequest request) {
/*  725 */     String s = request.getParameter("site_id");
/*  726 */     return (s != null && !s.equals(""));
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
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  748 */     if (!isBack(request)) {
/*  749 */       WmsPart wmsPart = new WmsPart();
/*  750 */       BeanForm wmsPartForm = (BeanForm)getForm("/insertWmsPart", request);
/*  751 */       wmsPartForm.populate(wmsPart, "to_form");
/*      */     } 
/*      */     
/*  754 */     putEnumListToRequest(request);
/*  755 */     return mapping.findForward("page");
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
/*  771 */     WmsPartManager cm = ServiceLocator.getWmsPartManager(request);
/*  772 */     BeanForm wmsPartForm = (BeanForm)form;
/*  773 */     WmsPart wmsPart = new WmsPart();
/*  774 */     wmsPartForm.populate(wmsPart, "to_bean");
/*      */     
/*  776 */     if (cm.getWmsPart(wmsPart.getId()) != null) {
/*  777 */       throw new ActionException("errors.partCode.existing");
/*      */     }
/*      */     
/*  780 */     request.setAttribute("X_OBJECT", cm.insertWmsPart(wmsPart));
/*  781 */     request.setAttribute("X_ROWPAGE", "wmsbasic/wmsPart/row.jsp");
/*  782 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward rqcTypelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  789 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  790 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  791 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  792 */       queryForm.setDescend(false);
/*  793 */       queryForm.setStatus("0");
/*      */     } 
/*  795 */     WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
/*  796 */     Map conditions = constructQueryMap(queryForm);
/*  797 */     if (queryForm.isFirstInit()) {
/*  798 */       queryForm.init(fm.getWmsPartListCount(conditions));
/*      */     } else {
/*  800 */       queryForm.init();
/*      */     } 
/*  802 */     List result = fm.getWmsPartList(conditions, queryForm.getPageNoAsInt(), 
/*  803 */         queryForm.getPageSizeAsInt(), WmsPartQueryOrder.ID, 
/*  804 */         queryForm.isDescend());
/*      */     
/*  806 */     request.setAttribute("X_RESULTLIST", result);
/*  807 */     putEnumListToRequest(request);
/*  808 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward rqcTypeEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  814 */     WmsPart wmsPart = getWmsPart(request);
/*  815 */     request.setAttribute("x_wmsPart", wmsPart);
/*  816 */     if (!isBack(request)) {
/*  817 */       BeanForm wmsPartForm = (BeanForm)getForm("/updateWmsPart", request);
/*  818 */       wmsPartForm.populate(wmsPart, "to_form");
/*      */     } 
/*      */     
/*  821 */     putEnumListToRequest(request);
/*  822 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listFinishedMaterial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  830 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/*  831 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  832 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  833 */       queryForm.setDescend(false);
/*  834 */       queryForm.setStatus("0");
/*      */     } 
/*  836 */     WmsPartManager fm = ServiceLocator.getWmsPartManager(request);
/*  837 */     Map conditions = constructQueryMap(queryForm);
/*  838 */     if (queryForm.isFirstInit()) {
/*  839 */       queryForm.init(fm.getWmsPartListCount(conditions));
/*      */     } else {
/*  841 */       queryForm.init();
/*      */     } 
/*  843 */     List result = fm.getWmsPartList(conditions, queryForm.getPageNoAsInt(), 
/*  844 */         queryForm.getPageSizeAsInt(), WmsPartQueryOrder.ID, 
/*  845 */         queryForm.isDescend());
/*      */     
/*  847 */     request.setAttribute("X_RESULTLIST", result);
/*  848 */     putEnumListToRequest(request);
/*  849 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward editFinishedMaterial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  855 */     WmsPart wmsPart = getWmsPart(request);
/*  856 */     request.setAttribute("x_wmsPart", wmsPart);
/*  857 */     if (!isBack(request)) {
/*  858 */       BeanForm wmsPartForm = (BeanForm)getForm("/updateFinishedMaterial", request);
/*  859 */       wmsPartForm.populate(wmsPart, "to_form");
/*      */     } 
/*      */     
/*  862 */     putEnumListToRequest(request);
/*  863 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward printBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  869 */     WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
/*  870 */     String arrays = request.getParameter("ids");
/*  871 */     String[] str = arrays.split(",");
/*  872 */     List<WmsPart> parts = new ArrayList<WmsPart>(); byte b; int i; String[] arrayOfString1;
/*  873 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/*  874 */       WmsPart part = manager.getWmsPart(id);
/*  875 */       parts.add(part);
/*      */       b++; }
/*      */     
/*  878 */     request.setAttribute("x_parts", parts);
/*  879 */     request.setAttribute("path", request.getContextPath());
/*  880 */     return mapping.findForward("page");
/*      */   }
/*      */   private Map constructQueryMaps(BasicDataViewQueryForm queryForm) {
/*  883 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  884 */     return conditions;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward selectWmsPartStocking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  889 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/*  890 */     BasicDataViewManager manager = ServiceLocator.getBasicDataViewManager(request);
/*      */     
/*  892 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  893 */       queryForm.setOrder(SkPartSumNumberQueryOrder.PART_ID.getName());
/*  894 */       queryForm.setDescend(false);
/*      */     } 
/*  896 */     Map conditions = constructQueryMaps(queryForm);
/*      */ 
/*      */     
/*  899 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */     
/*  902 */     if (queryForm.isFirstInit()) {
/*  903 */       queryForm.init(manager.getSkPartSumNumberListCount(conditions));
/*      */     } else {
/*  905 */       queryForm.init();
/*      */     } 
/*  907 */     List<SkPartSumNumber> result = manager.getSkPartSumNumberList(conditions, 
/*  908 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  909 */         SkPartSumNumberQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  910 */     request.setAttribute("X_RESULTLIST", result);
/*  911 */     putEnumListToRequest(request);
/*  912 */     request.setAttribute("x_selType", Integer.valueOf(154));
/*  913 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward selectWmsPartShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  923 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/*  924 */     BasicDataViewManager manager = ServiceLocator.getBasicDataViewManager(request);
/*  925 */     String idString = request.getParameter("portalShipJitPartId");
/*  926 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*      */ 
/*      */       
/*  929 */       queryForm.setOrder(JitShipPartQueryOrder.highQty.getName());
/*  930 */       queryForm.setOrder(JitShipPartQueryOrder.lowQty.getName());
/*  931 */       queryForm.setOrder(JitShipPartQueryOrder.securityQty.getName());
/*  932 */       queryForm.setOrder(JitShipPartQueryOrder.currentQty.getName());
/*  933 */       queryForm.setDescend(false);
/*      */     } 
/*  935 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  936 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  937 */     SupplierManager managera = ServiceLocator.getSupplierManager(request);
/*  938 */     User user = getCurrentUser(request);
/*  939 */     Supplier supplier = managera.getSupplierByCode(user.getLoginName());
/*  940 */     if (supplier != null) {
/*  941 */       conditions.put(JitShipPartQueryCondition.PART_VEND_EQ, supplier.getCode());
/*      */     } else {
/*  943 */       conditions.put(JitShipPartQueryCondition.PART_VEND_EQ, "0");
/*      */     } 
/*  945 */     conditions.put(JitShipPartQueryCondition.PART_ENABLED_EQ, Integer.valueOf(0));
/*  946 */     conditions.put(JitShipPartQueryCondition.PART_FREEZE_STATUS_EQ, Integer.valueOf(1));
/*  947 */     conditions.put(JitShipPartQueryCondition.PART_PRODUCTCLASS_EQ, "JIT");
/*      */     
/*  949 */     if (queryForm.isFirstInit()) {
/*  950 */       queryForm.init(manager.getJitShipPartListCount(conditions));
/*      */     } else {
/*  952 */       queryForm.init();
/*      */     } 
/*  954 */     List<JitShipPart> result = manager.getJitShipPartNumberList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  955 */         JitShipPartQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  956 */     for (JitShipPart ship : result) {
/*  957 */       if (ship.getQty().compareTo(new BigDecimal(0)) == -1) {
/*  958 */         ship.setQty(new BigDecimal(0));
/*      */       }
/*      */     } 
/*      */     
/*  962 */     if (idString != null && 
/*  963 */       !idString.equals("")) {
/*  964 */       String[] ids = idString.split(",");
/*  965 */       if (ids.length > 0) {
/*  966 */         for (JitShipPart ship : result) {
/*  967 */           String partId = ship.getPart().getId();
/*  968 */           Boolean isClose = Boolean.valueOf(false);
/*      */           
/*  970 */           for (int i = 0; i < ids.length; i++) {
/*  971 */             if (!ids[i].equals("") && 
/*  972 */               partId.equals(ids[i])) {
/*  973 */               isClose = Boolean.valueOf(true);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  978 */           if (isClose.booleanValue()) {
/*  979 */             ship.getPart().setChecked("checked");
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  985 */     request.setAttribute("portalShipJitPartId", idString);
/*  986 */     request.setAttribute("X_RESULTLIST", result);
/*  987 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   private Map selectWmsPartByPortalShipOrderQueryMaps(Integer dayInt) {
/*  991 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  992 */     if (dayInt.intValue() >= 0 && dayInt.intValue() <= 44) {
/*  993 */       if (dayInt.intValue() == 0) {
/*  994 */         conditions.put(PartForecastNeedReportQueryCondition.NO0NEEDQTY_GT, Integer.valueOf(0));
/*  995 */       } else if (dayInt.intValue() == 1) {
/*  996 */         conditions.put(PartForecastNeedReportQueryCondition.NO1NEEDQTY_GT, Integer.valueOf(0));
/*  997 */       } else if (dayInt.intValue() == 2) {
/*  998 */         conditions.put(PartForecastNeedReportQueryCondition.NO2NEEDQTY_GT, Integer.valueOf(0));
/*  999 */       } else if (dayInt.intValue() == 3) {
/* 1000 */         conditions.put(PartForecastNeedReportQueryCondition.NO3NEEDQTY_GT, Integer.valueOf(0));
/* 1001 */       } else if (dayInt.intValue() == 4) {
/* 1002 */         conditions.put(PartForecastNeedReportQueryCondition.NO4NEEDQTY_GT, Integer.valueOf(0));
/* 1003 */       } else if (dayInt.intValue() == 5) {
/* 1004 */         conditions.put(PartForecastNeedReportQueryCondition.NO5NEEDQTY_GT, Integer.valueOf(0));
/* 1005 */       } else if (dayInt.intValue() == 6) {
/* 1006 */         conditions.put(PartForecastNeedReportQueryCondition.NO6NEEDQTY_GT, Integer.valueOf(0));
/* 1007 */       } else if (dayInt.intValue() == 7) {
/* 1008 */         conditions.put(PartForecastNeedReportQueryCondition.NO7NEEDQTY_GT, Integer.valueOf(0));
/* 1009 */       } else if (dayInt.intValue() == 8) {
/* 1010 */         conditions.put(PartForecastNeedReportQueryCondition.NO8NEEDQTY_GT, Integer.valueOf(0));
/* 1011 */       } else if (dayInt.intValue() == 9) {
/* 1012 */         conditions.put(PartForecastNeedReportQueryCondition.NO9NEEDQTY_GT, Integer.valueOf(0));
/* 1013 */       } else if (dayInt.intValue() == 10) {
/* 1014 */         conditions.put(PartForecastNeedReportQueryCondition.NO10NEEDQTY_GT, Integer.valueOf(0));
/* 1015 */       } else if (dayInt.intValue() == 11) {
/* 1016 */         conditions.put(PartForecastNeedReportQueryCondition.NO11NEEDQTY_GT, Integer.valueOf(0));
/* 1017 */       } else if (dayInt.intValue() == 12) {
/* 1018 */         conditions.put(PartForecastNeedReportQueryCondition.NO12NEEDQTY_GT, Integer.valueOf(0));
/* 1019 */       } else if (dayInt.intValue() == 13) {
/* 1020 */         conditions.put(PartForecastNeedReportQueryCondition.NO13NEEDQTY_GT, Integer.valueOf(0));
/* 1021 */       } else if (dayInt.intValue() == 14) {
/* 1022 */         conditions.put(PartForecastNeedReportQueryCondition.NO14NEEDQTY_GT, Integer.valueOf(0));
/* 1023 */       } else if (dayInt.intValue() == 15) {
/* 1024 */         conditions.put(PartForecastNeedReportQueryCondition.NO15NEEDQTY_GT, Integer.valueOf(0));
/* 1025 */       } else if (dayInt.intValue() == 16) {
/* 1026 */         conditions.put(PartForecastNeedReportQueryCondition.NO16NEEDQTY_GT, Integer.valueOf(0));
/* 1027 */       } else if (dayInt.intValue() == 17) {
/* 1028 */         conditions.put(PartForecastNeedReportQueryCondition.NO17NEEDQTY_GT, Integer.valueOf(0));
/* 1029 */       } else if (dayInt.intValue() == 18) {
/* 1030 */         conditions.put(PartForecastNeedReportQueryCondition.NO18NEEDQTY_GT, Integer.valueOf(0));
/* 1031 */       } else if (dayInt.intValue() == 19) {
/* 1032 */         conditions.put(PartForecastNeedReportQueryCondition.NO19NEEDQTY_GT, Integer.valueOf(0));
/* 1033 */       } else if (dayInt.intValue() == 20) {
/* 1034 */         conditions.put(PartForecastNeedReportQueryCondition.NO20NEEDQTY_GT, Integer.valueOf(0));
/* 1035 */       } else if (dayInt.intValue() == 21) {
/* 1036 */         conditions.put(PartForecastNeedReportQueryCondition.NO21NEEDQTY_GT, Integer.valueOf(0));
/* 1037 */       } else if (dayInt.intValue() == 22) {
/* 1038 */         conditions.put(PartForecastNeedReportQueryCondition.NO22NEEDQTY_GT, Integer.valueOf(0));
/* 1039 */       } else if (dayInt.intValue() == 23) {
/* 1040 */         conditions.put(PartForecastNeedReportQueryCondition.NO23NEEDQTY_GT, Integer.valueOf(0));
/* 1041 */       } else if (dayInt.intValue() == 24) {
/* 1042 */         conditions.put(PartForecastNeedReportQueryCondition.NO24NEEDQTY_GT, Integer.valueOf(0));
/* 1043 */       } else if (dayInt.intValue() == 25) {
/* 1044 */         conditions.put(PartForecastNeedReportQueryCondition.NO25NEEDQTY_GT, Integer.valueOf(0));
/* 1045 */       } else if (dayInt.intValue() == 26) {
/* 1046 */         conditions.put(PartForecastNeedReportQueryCondition.NO26NEEDQTY_GT, Integer.valueOf(0));
/* 1047 */       } else if (dayInt.intValue() == 27) {
/* 1048 */         conditions.put(PartForecastNeedReportQueryCondition.NO27NEEDQTY_GT, Integer.valueOf(0));
/* 1049 */       } else if (dayInt.intValue() == 28) {
/* 1050 */         conditions.put(PartForecastNeedReportQueryCondition.NO28NEEDQTY_GT, Integer.valueOf(0));
/* 1051 */       } else if (dayInt.intValue() == 29) {
/* 1052 */         conditions.put(PartForecastNeedReportQueryCondition.NO29NEEDQTY_GT, Integer.valueOf(0));
/* 1053 */       } else if (dayInt.intValue() == 30) {
/* 1054 */         conditions.put(PartForecastNeedReportQueryCondition.NO30NEEDQTY_GT, Integer.valueOf(0));
/* 1055 */       } else if (dayInt.intValue() == 31) {
/* 1056 */         conditions.put(PartForecastNeedReportQueryCondition.NO31NEEDQTY_GT, Integer.valueOf(0));
/* 1057 */       } else if (dayInt.intValue() == 32) {
/* 1058 */         conditions.put(PartForecastNeedReportQueryCondition.NO32NEEDQTY_GT, Integer.valueOf(0));
/* 1059 */       } else if (dayInt.intValue() == 33) {
/* 1060 */         conditions.put(PartForecastNeedReportQueryCondition.NO33NEEDQTY_GT, Integer.valueOf(0));
/* 1061 */       } else if (dayInt.intValue() == 34) {
/* 1062 */         conditions.put(PartForecastNeedReportQueryCondition.NO34NEEDQTY_GT, Integer.valueOf(0));
/* 1063 */       } else if (dayInt.intValue() == 35) {
/* 1064 */         conditions.put(PartForecastNeedReportQueryCondition.NO35NEEDQTY_GT, Integer.valueOf(0));
/* 1065 */       } else if (dayInt.intValue() == 36) {
/* 1066 */         conditions.put(PartForecastNeedReportQueryCondition.NO36NEEDQTY_GT, Integer.valueOf(0));
/* 1067 */       } else if (dayInt.intValue() == 37) {
/* 1068 */         conditions.put(PartForecastNeedReportQueryCondition.NO37NEEDQTY_GT, Integer.valueOf(0));
/* 1069 */       } else if (dayInt.intValue() == 38) {
/* 1070 */         conditions.put(PartForecastNeedReportQueryCondition.NO38NEEDQTY_GT, Integer.valueOf(0));
/* 1071 */       } else if (dayInt.intValue() == 39) {
/* 1072 */         conditions.put(PartForecastNeedReportQueryCondition.NO39NEEDQTY_GT, Integer.valueOf(0));
/* 1073 */       } else if (dayInt.intValue() == 40) {
/* 1074 */         conditions.put(PartForecastNeedReportQueryCondition.NO40NEEDQTY_GT, Integer.valueOf(0));
/* 1075 */       } else if (dayInt.intValue() == 41) {
/* 1076 */         conditions.put(PartForecastNeedReportQueryCondition.NO41NEEDQTY_GT, Integer.valueOf(0));
/* 1077 */       } else if (dayInt.intValue() == 42) {
/* 1078 */         conditions.put(PartForecastNeedReportQueryCondition.NO42NEEDQTY_GT, Integer.valueOf(0));
/* 1079 */       } else if (dayInt.intValue() == 43) {
/* 1080 */         conditions.put(PartForecastNeedReportQueryCondition.NO43NEEDQTY_GT, Integer.valueOf(0));
/* 1081 */       } else if (dayInt.intValue() == 44) {
/* 1082 */         conditions.put(PartForecastNeedReportQueryCondition.NO44NEEDQTY_GT, Integer.valueOf(0));
/*      */       } 
/*      */     } else {
/* 1085 */       conditions.put(PartForecastNeedReportQueryCondition.PART_ID_EQ, "0");
/*      */     } 
/* 1087 */     return conditions;
/*      */   }
/*      */ 
/*      */   
/*      */   public ActionForward selectWmsPartByPortalShipOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1092 */     WmsPartQueryForm queryForm = (WmsPartQueryForm)form;
/* 1093 */     WmsPartManager manager = ServiceLocator.getWmsPartManager(request);
/* 1094 */     BasicDataViewManager viewManager = ServiceLocator.getBasicDataViewManager(request);
/* 1095 */     NjitNpoPlanManager njitNpoPlanManager = ServiceLocator.getNjitNpoPlanManager(request);
/* 1096 */     PortalShipOrderManager shipOrderManager = ServiceLocator.getPortalShipOrderManager(request);
/* 1097 */     String idString = request.getParameter("portalShipOrderByPartId");
/* 1098 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/* 1099 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 1100 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 1101 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/* 1102 */       queryForm.setDescend(false);
/*      */     } 
/* 1104 */     String supplierCode = request.getParameter("supplierCode");
/*      */     
/* 1106 */     String attivlDate = request.getParameter("receivingDate");
/* 1107 */     String date = format.format(new Date());
/*      */     
/* 1109 */     Calendar cal = Calendar.getInstance();
/* 1110 */     cal.setTime(format.parse(date));
/* 1111 */     long time1 = cal.getTimeInMillis();
/* 1112 */     cal.setTime(format.parse(attivlDate));
/* 1113 */     long time2 = cal.getTimeInMillis();
/* 1114 */     long day = (time2 - time1) / 86400000L;
/*      */     
/* 1116 */     String dayStr = String.valueOf(day);
/*      */     
/* 1118 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1141 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*      */ 
/*      */ 
/*      */     
/* 1145 */     conditions.put(PartForecastNeedReportQueryCondition.PART_ENABLED_EQ, Integer.valueOf(0));
/* 1146 */     conditions.put(PartForecastNeedReportQueryCondition.PART_FREEZE_STATUS_EQ, Integer.valueOf(1));
/* 1147 */     conditions.put(PartForecastNeedReportQueryCondition.PART_PRODUCTCLASS_EQ, "NJIT");
/* 1148 */     SupplierManager managera = ServiceLocator.getSupplierManager(request);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1156 */     if (supplierCode != null && !supplierCode.trim().equals("")) {
/* 1157 */       conditions.put(PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, supplierCode);
/*      */     } else {
/* 1159 */       conditions.put(PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, "0");
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
/* 1172 */     List<PartForecastNeedReport> partForecas = viewManager.getPartForecastNeedReportList(conditions, 0, -1, PartForecastNeedReportQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 1173 */     List<PartForecastNeedReport> result = new ArrayList<PartForecastNeedReport>();
/* 1174 */     for (PartForecastNeedReport partForecastNeedReport : partForecas) {
/*      */       
/* 1176 */       Class<?> cla = partForecastNeedReport.getClass();
/* 1177 */       Field[] field = partForecastNeedReport.getClass().getDeclaredFields();
/*      */       
/* 1179 */       String[] modelName = new String[field.length];
/* 1180 */       for (int i = 0; i < field.length; i++) {
/*      */         
/* 1182 */         String pinjie = "projected" + dayStr + "Qty";
/*      */         
/* 1184 */         String name = field[i].getName();
/* 1185 */         if (name.equals(pinjie)) {
/* 1186 */           name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
/* 1187 */           Method m = partForecastNeedReport.getClass().getMethod("get" + name, new Class[0]);
/*      */           
/* 1189 */           Method m1 = partForecastNeedReport.getClass().getMethod("getNo" + dayStr + "Needqty", new Class[0]);
/*      */           
/* 1191 */           Method m2 = partForecastNeedReport.getClass().getMethod("getNo" + dayStr + "Knotqty", new Class[0]);
/* 1192 */           BigDecimal value = (BigDecimal)m.invoke(partForecastNeedReport, new Object[0]);
/* 1193 */           BigDecimal xuQiu = (BigDecimal)m1.invoke(partForecastNeedReport, new Object[0]);
/* 1194 */           BigDecimal weiJie = (BigDecimal)m2.invoke(partForecastNeedReport, new Object[0]);
/*      */ 
/*      */ 
/*      */           
/* 1198 */           BigDecimal bg = new BigDecimal(0);
/*      */           
/* 1200 */           partForecastNeedReport.setProjectedQty(value);
/* 1201 */           if (xuQiu == null || xuQiu.equals(Integer.valueOf(0))) {
/* 1202 */             xuQiu = new BigDecimal("0.000000");
/* 1203 */             partForecastNeedReport.setNoNeedqty(xuQiu);
/*      */           } else {
/* 1205 */             if (weiJie != null) {
/* 1206 */               xuQiu = xuQiu.subtract(weiJie);
/*      */             }
/* 1208 */             if (xuQiu.compareTo(bg) == -1) {
/* 1209 */               partForecastNeedReport.setNoNeedqty(bg);
/*      */             } else {
/* 1211 */               partForecastNeedReport.setNoNeedqty(xuQiu);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1216 */           result.add(partForecastNeedReport);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           break;
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
/* 1256 */     for (PartForecastNeedReport partForecastNeedReport : result) {
/* 1257 */       NjitNpoPlan njitNpoPlan = njitNpoPlanManager.getNjitNpoPlanByPart(partForecastNeedReport.getPart().getId());
/* 1258 */       if (njitNpoPlan != null) {
/* 1259 */         partForecastNeedReport.part.setNeedQty(njitNpoPlan.getQty()); continue;
/*      */       } 
/* 1261 */       partForecastNeedReport.part.setNeedQty(new BigDecimal(0));
/*      */     } 
/*      */ 
/*      */     
/* 1265 */     if (idString != null && 
/* 1266 */       !idString.equals("")) {
/* 1267 */       String[] ids = idString.split(",");
/* 1268 */       if (ids.length > 0) {
/* 1269 */         for (PartForecastNeedReport partForecastNeedReport : result) {
/* 1270 */           String partId = partForecastNeedReport.getPart().getId();
/* 1271 */           Boolean isClose = Boolean.valueOf(false);
/*      */           
/* 1273 */           for (int i = 0; i < ids.length; i++) {
/* 1274 */             if (!ids[i].equals("") && 
/* 1275 */               partId.equals(ids[i])) {
/* 1276 */               isClose = Boolean.valueOf(true);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 1281 */           if (isClose.booleanValue()) {
/* 1282 */             partForecastNeedReport.part.setChecked("checked");
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1289 */     int totalCount = result.size();
/*      */     
/* 1291 */     int pageCount = 0;
/*      */     
/* 1293 */     int endNum = queryForm.getPageSizeAsInt();
/* 1294 */     if (endNum == -1) {
/* 1295 */       endNum = result.size();
/*      */     }
/*      */     
/* 1298 */     int startNum = queryForm.getPageNoAsInt() + 1;
/*      */     
/* 1300 */     if (totalCount % endNum > 0) {
/*      */       
/* 1302 */       pageCount = totalCount / endNum + 1;
/*      */     } else {
/*      */       
/* 1305 */       pageCount = totalCount / endNum;
/*      */     } 
/* 1307 */     if (totalCount > 0) {
/* 1308 */       if (startNum <= pageCount) {
/* 1309 */         if (startNum == 1) {
/*      */           
/* 1311 */           if (totalCount > endNum)
/*      */           {
/*      */ 
/*      */             
/* 1315 */             result = result.subList(0, endNum);
/*      */           }
/*      */         } else {
/*      */           
/* 1319 */           int fromIndex = (startNum - 1) * endNum;
/*      */           
/* 1321 */           int toIndex = startNum * endNum;
/*      */           
/* 1323 */           if ((totalCount - toIndex) % endNum >= 0) {
/* 1324 */             toIndex = startNum * endNum;
/*      */           } else {
/* 1326 */             toIndex = (startNum - 1) * endNum + totalCount % endNum;
/*      */           } 
/* 1328 */           if (totalCount >= toIndex) {
/* 1329 */             result = result.subList(fromIndex, toIndex);
/*      */           }
/*      */         } 
/*      */       } else {
/* 1333 */         result = null;
/*      */       } 
/*      */     }
/* 1336 */     if (queryForm.isFirstInit()) {
/* 1337 */       queryForm.init(totalCount);
/*      */     } else {
/* 1339 */       queryForm.init();
/*      */     } 
/* 1341 */     request.setAttribute("portalShipOrderByPartId", idString);
/* 1342 */     request.setAttribute("X_RESULTLIST", result);
/* 1343 */     request.setAttribute("receivingDate", attivlDate);
/* 1344 */     request.setAttribute("supplierCode", supplierCode);
/* 1345 */     return mapping.findForward("page");
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/WmsPartAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */