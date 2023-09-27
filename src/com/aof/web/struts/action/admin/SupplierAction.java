/*      */ package com.aof.web.struts.action.admin;
/*      */ 
/*      */ import com.aof.model.admin.Country;
/*      */ import com.aof.model.admin.Function;
/*      */ import com.aof.model.admin.PurchaseSubCategory;
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.admin.query.SupplierContractQueryCondition;
/*      */ import com.aof.model.admin.query.SupplierContractQueryOrder;
/*      */ import com.aof.model.admin.query.SupplierQueryCondition;
/*      */ import com.aof.model.admin.query.SupplierQueryOrder;
/*      */ import com.aof.model.metadata.ContractStatus;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.ExportStatus;
/*      */ import com.aof.model.metadata.SupplierConfirmStatus;
/*      */ import com.aof.model.metadata.SupplierPromoteStatus;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.service.admin.CountryManager;
/*      */ import com.aof.service.admin.CurrencyManager;
/*      */ import com.aof.service.admin.EmailManager;
/*      */ import com.aof.service.admin.FunctionManager;
/*      */ import com.aof.service.admin.PurchaseCategoryManager;
/*      */ import com.aof.service.admin.PurchaseSubCategoryManager;
/*      */ import com.aof.service.admin.SupplierContractManager;
/*      */ import com.aof.service.admin.SupplierItemManager;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.admin.UserManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.admin.SupplierQueryForm;
/*      */ import com.shcnc.hibernate.PersistentEnum;
/*      */ import com.shcnc.struts.action.ActionException;
/*      */ import com.shcnc.struts.action.ActionUtils;
/*      */ import com.shcnc.struts.action.BackToInputActionException;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.utils.BeanUtils;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.FileOutputStream;
/*      */ import java.net.URLEncoder;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
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
/*      */ public class SupplierAction
/*      */   extends BaseAction
/*      */ {
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*   87 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   93 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*      */     
/*   95 */     SupplierQueryForm queryForm = (SupplierQueryForm)form;
/*      */ 
/*      */     
/*   98 */     if (isSite(request) && queryForm.getSite_id() == null) {
/*   99 */       queryForm.setSite_id(((Site)siteList.get(0)).getId().toString());
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
/*  110 */     Map conditions = constructQueryMap(queryForm);
/*      */     
/*  112 */     String exportType = queryForm.getExportType();
/*  113 */     if (exportType != null && exportType.length() > 0) {
/*  114 */       List data = sm.getSupplierList(conditions, 0, -1, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/*  116 */       int index = SessionTempFile.createNewTempFile(request);
/*  117 */       String fileName = "supplier";
/*  118 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  121 */               MessageResources messages = SupplierAction.this.getResources(request);
/*  122 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.code"));
/*  123 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.name"));
/*  124 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.telephone"));
/*  125 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.fax"));
/*  126 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.post"));
/*  127 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.contactor"));
/*  128 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.contractStatus"));
/*  129 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.promoteStatus"));
/*  130 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.status"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  134 */               row.add(BeanUtils.getProperty(data, "code"));
/*  135 */               row.add(BeanUtils.getProperty(data, "name"));
/*  136 */               row.add(BeanUtils.getProperty(data, "telephone1"));
/*  137 */               row.add(BeanUtils.getProperty(data, "fax1"));
/*  138 */               row.add(BeanUtils.getProperty(data, "post"));
/*  139 */               row.add(BeanUtils.getProperty(data, "contact"));
/*  140 */               String locale = SupplierAction.this.getCurrentUser(request).getLocale();
/*  141 */               if ("en".equals(locale)) {
/*  142 */                 row.add(BeanUtils.getProperty(data, "contractStatus.engShortDescription"));
/*  143 */                 row.add(BeanUtils.getProperty(data, "promoteStatus.engShortDescription"));
/*  144 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*      */               } else {
/*  146 */                 row.add(BeanUtils.getProperty(data, "contractStatus.chnShortDescription"));
/*  147 */                 row.add(BeanUtils.getProperty(data, "promoteStatus.chnShortDescription"));
/*  148 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*      */               } 
/*      */             }
/*      */           });
/*  152 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/*  155 */     if (queryForm.isFirstInit()) {
/*  156 */       queryForm.init(sm.getSupplierListCount(conditions));
/*      */     } else {
/*  158 */       queryForm.init();
/*      */     } 
/*  160 */     int pageNo = queryForm.getPageNoAsInt();
/*  161 */     int pageSize = queryForm.getPageSizeAsInt();
/*      */     
/*  163 */     List result = sm.getSupplierList(conditions, pageNo, pageSize, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  164 */     request.setAttribute("X_RESULTLIST", result);
/*  165 */     putEnumListToRequest(request);
/*      */     
/*  167 */     request.setAttribute("X_SITELIST", siteList);
/*      */     
/*  169 */     return mapping.findForward("page");
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
/*      */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  184 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*      */     
/*  186 */     SupplierQueryForm queryForm = (SupplierQueryForm)form;
/*  187 */     queryForm.setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/*  188 */     Map conditions = constructQueryMap(queryForm);
/*  189 */     queryForm.setPageSize("10");
/*  190 */     if (queryForm.isFirstInit()) {
/*  191 */       queryForm.init(sm.getSupplierListCount(conditions), queryForm.getPageSizeAsInt());
/*      */     } else {
/*  193 */       queryForm.init();
/*      */     } 
/*  195 */     int pageNo = queryForm.getPageNoAsInt();
/*  196 */     int pageSize = queryForm.getPageSizeAsInt();
/*      */     
/*  198 */     List result = sm.getSupplierList(conditions, pageNo, pageSize, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  199 */     request.setAttribute("X_RESULTLIST", result);
/*      */     
/*  201 */     putEnabledCountryCityListToRequest(request);
/*  202 */     return mapping.findForward("page");
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
/*      */   public ActionForward listConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  216 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*      */     
/*  218 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*  219 */     SupplierQueryForm queryForm = (SupplierQueryForm)form;
/*  220 */     if (isSite(request) && queryForm.getSite_id() == null) {
/*  221 */       queryForm.setSite_id(((Site)siteList.get(0)).getId().toString());
/*      */     }
/*  223 */     if (queryForm.getConfirmType() == null) {
/*  224 */       queryForm.setConfirmType("ALL");
/*      */     }
/*  226 */     if (isGlobal(request)) {
/*  227 */       queryForm.setConfirmGlobal(true);
/*      */     } else {
/*  229 */       queryForm.setConfirmGlobal(false);
/*  230 */     }  Map conditions = constructQueryMap(queryForm);
/*      */     
/*  232 */     String exportType = queryForm.getExportType();
/*  233 */     if (exportType != null && exportType.length() > 0) {
/*  234 */       List data = sm.getSupplierList(conditions, 0, -1, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */       
/*  236 */       int index = SessionTempFile.createNewTempFile(request);
/*  237 */       String fileName = "supplierConfirm";
/*  238 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*      */           {
/*      */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  241 */               MessageResources messages = SupplierAction.this.getResources(request);
/*  242 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.code"));
/*  243 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.name"));
/*  244 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.confirm.type"));
/*  245 */               row.add(messages.getMessage(SupplierAction.this.getLocale(request), "supplier.lastModifyDate"));
/*      */             }
/*      */             
/*      */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  249 */               row.add(BeanUtils.getProperty(data, "code"));
/*  250 */               row.add(BeanUtils.getProperty(data, "name"));
/*  251 */               String locale = SupplierAction.this.getCurrentUser(request).getLocale();
/*  252 */               if ("en".equals(locale)) {
/*  253 */                 row.add(BeanUtils.getProperty(data, "confirmStatus.engShortDescription"));
/*      */               } else {
/*  255 */                 row.add(BeanUtils.getProperty(data, "confirmStatus.chnShortDescription"));
/*      */               } 
/*  257 */               row.add(BeanUtils.getProperty(data, "lastModifyDate"));
/*      */             }
/*      */           });
/*  260 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*      */     } 
/*      */     
/*  263 */     if (queryForm.isFirstInit()) {
/*  264 */       queryForm.init(sm.getSupplierListCount(conditions));
/*      */     } else {
/*  266 */       queryForm.init();
/*      */     } 
/*  268 */     int pageNo = queryForm.getPageNoAsInt();
/*  269 */     int pageSize = queryForm.getPageSizeAsInt();
/*      */     
/*  271 */     List result = sm.getSupplierList(conditions, pageNo, pageSize, SupplierQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*      */     
/*  273 */     request.setAttribute("X_RESULTLIST", result);
/*  274 */     request.setAttribute("X_SITELIST", siteList);
/*  275 */     request.setAttribute("X_CONFIRMSTATUSLIST", PersistentEnum.getEnumList(SupplierConfirmStatus.class));
/*  276 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List getEnabledCountryCityList(HttpServletRequest request) {
/*  286 */     CountryManager cm = ServiceLocator.getCountryManager(request);
/*  287 */     return cm.listEnabledCountryCity();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putEnabledCountryCityListToRequest(HttpServletRequest request) {
/*  296 */     List countryList = getEnabledCountryCityList(request);
/*  297 */     request.setAttribute("x_countryList", countryList);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map constructQueryMap(SupplierQueryForm queryForm) {
/*  307 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*      */     
/*  309 */     String id = queryForm.getId();
/*  310 */     if (id != null && id.trim().length() != 0) {
/*  311 */       conditions.put(SupplierQueryCondition.ID_EQ, id);
/*      */     }
/*      */     
/*  314 */     String code = queryForm.getCode();
/*  315 */     if (code != null && code.trim().length() != 0) {
/*  316 */       conditions.put(SupplierQueryCondition.CODE_LIKE, code);
/*      */     }
/*      */     
/*  319 */     Integer site_id = ActionUtils.parseInt(queryForm.getSite_id());
/*  320 */     if (site_id != null) {
/*  321 */       boolean includeGlobal = queryForm.isIncludeGlobal();
/*  322 */       if (includeGlobal) {
/*  323 */         conditions.put(SupplierQueryCondition.GLOBAL_OR_SITE_ID_EQ, site_id);
/*      */       } else {
/*  325 */         conditions.put(SupplierQueryCondition.SITE_ID_EQ, site_id);
/*  326 */         conditions.put(SupplierQueryCondition.PROMOTE_STATUS_NEQ, SupplierPromoteStatus.GLOBAL);
/*      */       } 
/*      */     } else {
/*  329 */       conditions.put(SupplierQueryCondition.PROMOTE_STATUS_EQ, SupplierPromoteStatus.GLOBAL);
/*      */     } 
/*      */     
/*  332 */     Integer country_id = ActionUtils.parseInt(queryForm.getCountry_id());
/*  333 */     if (country_id != null && country_id.intValue() != -1) {
/*  334 */       conditions.put(SupplierQueryCondition.COUNTRY_ID_EQ, country_id);
/*      */     }
/*      */     
/*  337 */     Integer city_id = ActionUtils.parseInt(queryForm.getCity_id());
/*  338 */     if (city_id != null && city_id.intValue() != -1) {
/*  339 */       conditions.put(SupplierQueryCondition.CITY_ID_EQ, city_id);
/*      */     }
/*      */     
/*  342 */     String name = queryForm.getName();
/*  343 */     if (name != null && name.trim().length() != 0) {
/*  344 */       conditions.put(SupplierQueryCondition.NAME_LIKE, name);
/*      */     }
/*      */     
/*  347 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/*  348 */     if (enabled != null) {
/*  349 */       conditions.put(SupplierQueryCondition.ENABLED_EQ, enabled);
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
/*  363 */     String contractStatus = queryForm.getContractStatus();
/*  364 */     if (contractStatus != null && contractStatus.trim().length() != 0) {
/*  365 */       Date today = new Date();
/*  366 */       if (contractStatus.equals(ContractStatus.NOT_ACTIVED.getEnumCode().toString())) {
/*  367 */         conditions.put(SupplierQueryCondition.CONTRACT_NOT_ACTIVE, today);
/*      */       }
/*  369 */       if (contractStatus.equals(ContractStatus.ACTIVED.getEnumCode().toString())) {
/*  370 */         conditions.put(SupplierQueryCondition.CONTRACT_ACTIVE, new Object[] { today, today, today, today });
/*      */       }
/*  372 */       if (contractStatus.equals(ContractStatus.EXPIRED.getEnumCode().toString())) {
/*  373 */         conditions.put(SupplierQueryCondition.CONTRACT_EXPIRED, today);
/*      */       }
/*      */     } 
/*  376 */     String confirmType = queryForm.getConfirmType();
/*  377 */     if (confirmType != null) {
/*  378 */       if (confirmType.equals(SupplierConfirmStatus.NEW.getEnumCode().toString())) {
/*  379 */         conditions.put(SupplierQueryCondition.CONFIRM_TYPE_EQ, SupplierConfirmStatus.NEW.getEnumCode());
/*      */       }
/*  381 */       if (confirmType.equals(SupplierConfirmStatus.MODIFY.getEnumCode().toString())) {
/*  382 */         conditions.put(SupplierQueryCondition.CONFIRM_TYPE_EQ, SupplierConfirmStatus.MODIFY.getEnumCode());
/*      */       }
/*  384 */       conditions.put(SupplierQueryCondition.CONFIRM_EQ, YesNo.NO);
/*  385 */       boolean confirmGlobal = queryForm.isConfirmGlobal();
/*  386 */       if (confirmGlobal) {
/*  387 */         conditions.put(SupplierQueryCondition.PROMOTE_STATUS_EQ, SupplierPromoteStatus.GLOBAL);
/*      */       } else {
/*  389 */         conditions.put(SupplierQueryCondition.PROMOTE_STATUS_NEQ, SupplierPromoteStatus.GLOBAL);
/*      */       } 
/*      */     } 
/*  392 */     String promoteStatus = queryForm.getPromoteStatus();
/*  393 */     if (promoteStatus != null && 
/*  394 */       promoteStatus.trim().length() != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  401 */       conditions.put(SupplierQueryCondition.PROMOTE_STATUS_EQ, promoteStatus);
/*      */     }
/*      */ 
/*      */     
/*  405 */     return conditions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putEnumListToRequest(HttpServletRequest request) throws Exception {
/*  415 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/*  416 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*  417 */     request.setAttribute("X_SUPPLIERSTATUSLIST", PersistentEnum.getEnumList(ContractStatus.class));
/*  418 */     List list = PersistentEnum.getEnumList(SupplierPromoteStatus.class);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  423 */     request.setAttribute("X_PROMOTESTATUSLIST", list);
/*  424 */     putEnabledCountryCityListToRequest(request);
/*  425 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/*  426 */     request.setAttribute("X_CURRENCYLIST", cm.getAllEnabledCurrencyList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Supplier getSupplierFromRequest(HttpServletRequest request) throws Exception {
/*  437 */     Integer id = new Integer(request.getParameter("id"));
/*  438 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/*  439 */     Supplier supplier = supplierManager.getSupplier(id);
/*  440 */     if (supplier == null)
/*  441 */       throw new ActionException("supplier.notFound", id); 
/*  442 */     return supplier;
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
/*      */   public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  456 */     Supplier supplier = getSupplierFromRequest(request);
/*      */     
/*  458 */     checkSupplier(supplier, request);
/*      */     
/*  460 */     request.setAttribute("x_supplier", supplier);
/*  461 */     if (!isBack(request)) {
/*  462 */       BeanForm supplierForm = (BeanForm)getForm("/confirmSupplierResult", request);
/*  463 */       supplierForm.populateToForm(supplier);
/*      */     } 
/*  465 */     putContractListToRequest(supplier, request);
/*  466 */     putEnumListToRequest(request);
/*  467 */     request.setAttribute("x_action", "/confirmSupplierResult" + request.getAttribute("x_version"));
/*  468 */     request.setAttribute("x_confirm", "true");
/*      */ 
/*      */ 
/*      */     
/*  472 */     if (supplier.getConfirmStatus() == SupplierConfirmStatus.NEW) {
/*  473 */       return mapping.findForward("pageConfirmNew");
/*      */     }
/*  475 */     return mapping.findForward("pageConfirmModify");
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
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     Supplier supplier;
/*  491 */     if (request.getAttribute("x_supplier") == null) {
/*  492 */       supplier = getSupplierFromRequest(request);
/*  493 */       request.setAttribute("x_supplier", supplier);
/*      */     } else {
/*  495 */       supplier = (Supplier)request.getAttribute("x_supplier");
/*      */     } 
/*      */     
/*  498 */     putContractListToRequest(supplier, request);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  513 */     if (!isBack(request)) {
/*  514 */       BeanForm supplierForm = (BeanForm)getForm("/updateSupplier", request);
/*  515 */       supplierForm.populateToForm(supplier);
/*      */     } 
/*      */     
/*  518 */     putEnumListToRequest(request);
/*  519 */     request.setAttribute("x_action", "/updateSupplier" + request.getAttribute("x_version"));
/*      */     
/*  521 */     return mapping.findForward("page");
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
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  536 */     Site site = getAndCheckSite(request);
/*      */     
/*  538 */     if (!isBack(request)) {
/*  539 */       Supplier supplier = new Supplier();
/*  540 */       supplier.setSite(site);
/*      */       
/*  542 */       if (site != null) supplier.setCurrency(site.getBaseCurrency());
/*      */       
/*  544 */       BeanForm supplierForm = (BeanForm)getForm("/insertSupplier", request);
/*  545 */       if (isGlobal(request)) {
/*  546 */         supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/*      */       } else {
/*  548 */         supplier.setPromoteStatus(SupplierPromoteStatus.SITE);
/*  549 */       }  supplier.setConfirmed(YesNo.NO);
/*  550 */       supplier.setAirTicket(YesNo.NO);
/*  551 */       supplier.setLastModifyDate(new Date());
/*  552 */       supplierForm.populateToForm(supplier);
/*      */     } 
/*  554 */     putEnumListToRequest(request);
/*      */     
/*  556 */     return mapping.findForward("page");
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
/*      */   public ActionForward confirmResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  571 */     Supplier supplier = getSupplierFromRequest(request);
/*  572 */     boolean cancel = !request.getParameter("cancel").equals("false");
/*      */     
/*  574 */     checkSupplier(supplier, request);
/*      */     
/*  576 */     BeanForm supplierForm = (BeanForm)form;
/*  577 */     if (!cancel) {
/*  578 */       supplierForm.populateToBean(supplier, request);
/*      */     }
/*      */     
/*  581 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/*  582 */     if (!cancel) {
/*  583 */       supplierManager.confirmSupplier(supplier);
/*      */     } else {
/*  585 */       supplierManager.cancelSupplier(supplier);
/*      */     } 
/*  587 */     return getForwardFor("listConfirm", (Integer)null, request);
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
/*      */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     List userList;
/*  602 */     Supplier supplier = getSupplierFromRequest(request);
/*      */     
/*  604 */     checkSupplier(supplier, request);
/*      */     
/*  606 */     BeanForm supplierForm = (BeanForm)form;
/*  607 */     supplierForm.populateToBean(supplier, request);
/*  608 */     supplier.setLastModifyDate(new Date());
/*      */     
/*  610 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/*  611 */     supplierManager.updateSupplier(supplier);
/*  612 */     postGlobalMessage("supplier.update.success", request.getSession());
/*      */     
/*  614 */     EmailManager em = ServiceLocator.getEmailManager(request);
/*  615 */     UserManager um = ServiceLocator.getUserManager(request);
/*  616 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*      */ 
/*      */     
/*  619 */     if (supplier.getSite() != null) {
/*  620 */       Function function = fm.getFunction("siteSupplierConfirm");
/*  621 */       userList = um.getEnabledUserList(function, supplier.getSite());
/*      */     } else {
/*  623 */       Function function = fm.getFunction("globalSupplierConfirm");
/*  624 */       userList = um.getEnabledUserList(function);
/*      */     } 
/*  626 */     for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/*  627 */       User user = itor.next();
/*  628 */       if (user.getEmail() != null) {
/*  629 */         CountryManager cm1 = ServiceLocator.getCountryManager(request);
/*  630 */         Country country = cm1.getCountry(supplier.getCountry().getId());
/*  631 */         Map<Object, Object> context = new HashMap<Object, Object>();
/*  632 */         context.put("user", user);
/*  633 */         context.put("supplier", supplier);
/*  634 */         context.put("country", country);
/*  635 */         em.insertEmail(user.getPrimarySite(), user.getEmail(), "ModifySupplier.vm", context);
/*      */       } 
/*      */     } 
/*      */     
/*  639 */     return getForwardFor("edit", supplier.getId(), request);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSupplier(Supplier supplier, HttpServletRequest request) throws Exception {
/*  645 */     if (isSite(request)) {
/*  646 */       if (supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL)) {
/*  647 */         throw new ActionException("supplier.error.siteEditGlobal");
/*      */       }
/*  649 */       checkSite(supplier.getSite(), request);
/*      */     } 
/*      */     
/*  652 */     if (isGlobal(request) && 
/*  653 */       !supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL)) {
/*  654 */       throw new ActionException("supplier.error.globalEditSite");
/*      */     }
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
/*      */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*      */     List userList;
/*  669 */     Site site = getAndCheckSite(request);
/*  670 */     BeanForm supplierForm = (BeanForm)form;
/*  671 */     Supplier supplier = new Supplier();
/*  672 */     supplierForm.populateToBean(supplier, request);
/*      */     
/*  674 */     supplier.setSite(site);
/*      */     
/*  676 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/*  677 */     if (supplier.getCode() != null) {
/*  678 */       supplier.setCode(supplier.getCode().trim());
/*  679 */       if (supplierManager.isCodeUsed(supplier.getCode().trim(), supplier.getSite())) {
/*  680 */         throw new BackToInputActionException("supplier.error.codeExist");
/*      */       }
/*      */     } 
/*  683 */     supplier.setLastModifyDate(new Date());
/*  684 */     supplier.setExportStatus(ExportStatus.UNEXPORTED);
/*  685 */     supplierManager.insertSupplier(supplier);
/*  686 */     postGlobalMessage("supplier.insert.success", request.getSession());
/*      */     
/*  688 */     EmailManager em = ServiceLocator.getEmailManager(request);
/*  689 */     UserManager um = ServiceLocator.getUserManager(request);
/*  690 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*      */ 
/*      */     
/*  693 */     if (supplier.getSite() != null) {
/*  694 */       Function function = fm.getFunction("siteSupplierConfirm");
/*  695 */       userList = um.getEnabledUserList(function, supplier.getSite());
/*      */     } else {
/*  697 */       Function function = fm.getFunction("globalSupplierConfirm");
/*  698 */       userList = um.getEnabledUserList(function);
/*      */     } 
/*  700 */     for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/*  701 */       User user = itor.next();
/*  702 */       if (user.getEmail() != null) {
/*  703 */         CountryManager cm1 = ServiceLocator.getCountryManager(request);
/*  704 */         Country country = cm1.getCountry(supplier.getCountry().getId());
/*  705 */         Map<Object, Object> context = new HashMap<Object, Object>();
/*  706 */         context.put("user", user);
/*  707 */         context.put("supplier", supplier);
/*  708 */         context.put("country", country);
/*  709 */         em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewSupplier.vm", context);
/*      */       } 
/*      */     } 
/*      */     
/*  713 */     return getForwardFor("edit", supplier.getId(), request);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ActionForward getForwardFor(String actionName, Integer id, HttpServletRequest request) {
/*  724 */     String url = String.valueOf(actionName) + "Supplier" + request.getAttribute("x_version") + ".do" + ((id == null) ? "" : ("?id=" + id));
/*  725 */     ActionForward forward = new ActionForward(url);
/*  726 */     forward.setRedirect(true);
/*  727 */     return forward;
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
/*      */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/*  741 */     if (hasSite(request)) {
/*  742 */       if (isGlobal(request)) {
/*  743 */         throw new ActionException("supplier.error.globalNewSite");
/*      */       }
/*  745 */       return getAndCheckSite("site_id", request);
/*      */     } 
/*      */     
/*  748 */     if (isGlobal(request)) {
/*  749 */       return null;
/*      */     }
/*  751 */     throw new ActionException("supplier.error.siteNewGlobal");
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
/*      */   private boolean hasSite(HttpServletRequest request) {
/*  763 */     return !StringUtils.isEmpty(request.getParameter("site_id"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putContractListToRequest(Supplier supplier, HttpServletRequest request) throws Exception {
/*  774 */     SupplierContractManager scm = ServiceLocator.getSupplierContractManager(request);
/*  775 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  776 */     conditions.put(SupplierContractQueryCondition.SUPPLIER_ID_EQ, supplier.getId());
/*  777 */     List result = scm.getSupplierContractList(conditions, 0, -1, SupplierContractQueryOrder.ID, false);
/*  778 */     request.setAttribute("x_contractList", result);
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
/*      */   public ActionForward requestPromote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  793 */     Supplier supplier = getSupplierFromRequest(request);
/*  794 */     checkSupplierPromoteStatus(supplier, SupplierPromoteStatus.SITE, request);
/*      */     
/*  796 */     request.setAttribute("x_supplier", supplier);
/*      */     
/*  798 */     if (!isBack(request)) {
/*  799 */       BeanForm supplierForm = (BeanForm)getForm("/requestPromoteSupplierResult", request);
/*  800 */       supplierForm.populateToForm(supplier);
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
/*  811 */     return mapping.findForward("page");
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
/*      */   public ActionForward requestPromoteResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  827 */     Supplier supplier = getSupplierFromRequest(request);
/*  828 */     checkSupplierPromoteStatus(supplier, SupplierPromoteStatus.SITE, request);
/*      */     
/*  830 */     String promteMsg = request.getParameter("promoteMessage");
/*  831 */     supplier.setPromoteMessage(promteMsg);
/*      */     
/*  833 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*  834 */     sm.requestPromote(supplier.getId(), supplier.getPromoteMessage());
/*      */     
/*  836 */     EmailManager em = ServiceLocator.getEmailManager(request);
/*  837 */     UserManager um = ServiceLocator.getUserManager(request);
/*  838 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*  839 */     Function function = fm.getFunction("siteSupplierMaintenance");
/*  840 */     List userList = um.getEnabledUserList(function, supplier.getSite());
/*  841 */     for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/*  842 */       User user = itor.next();
/*  843 */       if (user.getEmail() != null) {
/*  844 */         Map<Object, Object> context = new HashMap<Object, Object>();
/*  845 */         context.put("user", user);
/*  846 */         context.put("supplier", supplier);
/*  847 */         em.insertEmail(user.getPrimarySite(), user.getEmail(), "SupplierPromote.vm", context);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  854 */     request.setAttribute("PROMOTE_MESSAGE", promteMsg);
/*      */     
/*  856 */     return mapping.findForward("success");
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
/*      */   public ActionForward responsePromote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  871 */     Supplier supplier = getSupplierFromRequest(request);
/*  872 */     changeSubCategory(supplier, request);
/*  873 */     checkSupplierPromoteStatus(supplier, SupplierPromoteStatus.REQUEST, request);
/*      */     
/*  875 */     SupplierItemManager sim = ServiceLocator.getSupplierItemManager(request);
/*  876 */     List conflictItemList = sim.getSupplierItemListConflictWithPromoteBySupplierGroupByPurchaseSubCategory(supplier);
/*  877 */     if (conflictItemList.size() > 0) {
/*  878 */       request.setAttribute("x_conflictItemList", conflictItemList);
/*  879 */       request.setAttribute("x_supplier", supplier);
/*  880 */       putEnabledGlobalPurchaseCategoryToRequest(request);
/*  881 */       postGlobalMessage("supplier.response.fail", request.getSession());
/*  882 */       return mapping.findForward("fail");
/*      */     } 
/*  884 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*  885 */     supplier = sm.responsePromote(supplier.getId());
/*  886 */     request.setAttribute("x_supplier", supplier);
/*  887 */     postGlobalMessage("supplier.response.success", request.getSession());
/*  888 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */   
/*      */   private void changeSubCategory(Supplier supplier, HttpServletRequest request) {
/*  893 */     SupplierItemManager sim = ServiceLocator.getSupplierItemManager(request);
/*  894 */     String[] changeId = request.getParameterValues("changeSubCategoryId");
/*  895 */     if (changeId != null) {
/*  896 */       for (int index = 0; index < changeId.length; index++) {
/*  897 */         Integer originalId = ActionUtils.parseInt(changeId[index]);
/*  898 */         Integer destId = ActionUtils.parseInt(request.getParameter("purchaseSubCategory_" + changeId[index]));
/*  899 */         PurchaseSubCategoryManager pm = ServiceLocator.getPurchaseSubCategoryManager(request);
/*  900 */         PurchaseSubCategory destPurchaseSubCategory = pm.getPurchaseSubCategory(destId);
/*  901 */         sim.changePurchaseSubCategoryBySupplierAndPurchaseSubCategory(supplier.getId(), originalId, destPurchaseSubCategory);
/*      */       } 
/*      */     }
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
/*      */   public ActionForward responsePromoteResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  925 */     Supplier supplier = getSupplierFromRequest(request);
/*  926 */     checkSupplierPromoteStatus(supplier, SupplierPromoteStatus.REQUEST, request);
/*      */     
/*  928 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*  929 */     sm.responsePromote(supplier.getId());
/*  930 */     return getForwardFor("list", (Integer)null, request);
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
/*      */   public ActionForward viewBaseInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  945 */     Supplier supplier = getSupplierFromRequest(request);
/*  946 */     request.setAttribute("x_supplier", supplier);
/*  947 */     return mapping.findForward("page");
/*      */   }
/*      */   
/*      */   public ActionForward viewAviableSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  951 */     Integer siteId = new Integer(request.getParameter("siteId"));
/*  952 */     Integer purchaseSubCategoryId = new Integer(request.getParameter("subCategoryValue"));
/*  953 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/*  954 */     List result = sm.getSuitableSupplierListForPurchase(new Site(siteId), new PurchaseSubCategory(purchaseSubCategoryId), null);
/*  955 */     request.setAttribute("X_RESULTLIST", result);
/*  956 */     return mapping.findForward("page");
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
/*      */   private void checkSupplierPromoteStatus(Supplier supplier, SupplierPromoteStatus status, HttpServletRequest request) throws Exception {
/* 1080 */     if (isSite(request)) {
/* 1081 */       if (!status.equals(SupplierPromoteStatus.REQUEST)) {
/* 1082 */         throw new RuntimeException("status error");
/*      */       }
/* 1084 */       checkSite(supplier.getSite(), request);
/*      */     } 
/* 1086 */     if (!supplier.getPromoteStatus().equals(status)) {
/* 1087 */       throw new ActionException("supplier.promote.statusError", supplier.getName(), status.getLabel());
/*      */     }
/* 1089 */     if (supplier.getEnabled().equals(EnabledDisabled.DISABLED)) {
/* 1090 */       throw new ActionException("supplier.promote.disabled", supplier.getName());
/*      */     }
/*      */   }
/*      */   
/*      */   private void putEnabledGlobalPurchaseCategoryToRequest(HttpServletRequest request) throws Exception {
/* 1095 */     PurchaseCategoryManager pcm = ServiceLocator.getPurchaseCategoryManager(request);
/* 1096 */     request.setAttribute("x_purchaseCategoryList", pcm.getEnabledPurchaseCategorySubCategoryOfGlobal());
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/SupplierAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */