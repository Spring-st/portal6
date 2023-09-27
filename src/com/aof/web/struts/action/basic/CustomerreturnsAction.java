/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.CustomerReturnItem;
/*     */ import com.aof.model.basic.Customerreturns;
/*     */ import com.aof.model.basic.query.CustomerReturnItemQueryCondition;
/*     */ import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
/*     */ import com.aof.model.basic.query.CustomerreturnsQueryCondition;
/*     */ import com.aof.model.basic.query.CustomerreturnsQueryOrder;
/*     */ import com.aof.model.basic.query.StorageLocationQueryCondition;
/*     */ import com.aof.model.basic.query.StorageLocationQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.BasicCustomer;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.BasicCustomerQueryCondition;
/*     */ import com.aof.model.product.query.BasicCustomerQueryOrder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryCondition;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
/*     */ import com.aof.service.Product.BasicCustomerManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.basic.CustomerReturnItemManager;
/*     */ import com.aof.service.basic.CustomerreturnsManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.StoreRoomManager;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.CustomerReturnItemQueryForm;
/*     */ import com.aof.web.struts.form.basic.CustomerreturnsQueryForm;
/*     */ import com.aof.web.struts.form.basic.StorageLocationQueryForm;
/*     */ import com.aof.web.struts.form.product.BasicCustomerQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomerreturnsAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map getConditions(CustomerreturnsQueryForm formBean) {
/*  82 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  83 */     String returnNumber = formBean.getReturnNumber();
/*  84 */     if (returnNumber != null && returnNumber.trim().length() != 0) {
/*  85 */       conditions.put(CustomerreturnsQueryCondition.RETURNNUMBER_EQ, returnNumber);
/*     */     }
/*  87 */     conditions.put(CustomerreturnsQueryCondition.DELSTATUS_EQ, Integer.valueOf(1));
/*  88 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  93 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/*  94 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/*  95 */     CustomerreturnsQueryForm formBean = (CustomerreturnsQueryForm)form;
/*  96 */     Map<CustomerreturnsQueryCondition, Integer> conditions = getConditions(formBean);
/*  97 */     getConditionAndOrder((BaseSessionQueryForm)formBean, conditions, request);
/*  98 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*  99 */     List<Site> sitesList = siteManager.getAllEnabledSiteList();
/* 100 */     if (sitesList.size() != siteList.size()) {
/* 101 */       Site site = siteList.get(0);
/* 102 */       conditions.put(CustomerreturnsQueryCondition.CUSTOMER_SITE_ID_EQ, site.getId());
/*     */     } 
/* 104 */     String exportType = formBean.getExportType();
/* 105 */     if (exportType != null && exportType.length() > 0) {
/* 106 */       List datas = manager.getList(conditions, 0, -1, CustomerreturnsQueryOrder.getEnum(formBean.getOrder()), 
/* 107 */           formBean.isDescend());
/* 108 */       int index = SessionTempFile.createNewTempFile(request);
/* 109 */       String fileName = "Customerreturns";
/* 110 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 111 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 114 */               row.add(BeanUtils.getProperty(data, "id"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 119 */               MessageResources message = CustomerreturnsAction.this.getResources(request);
/* 120 */               row.add(message.getMessage(CustomerreturnsAction.this.getLocale(request), "mapping.id"));
/*     */             }
/*     */           });
/*     */       
/* 124 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 126 */     if (formBean.isFirstInit()) {
/* 127 */       formBean.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 129 */       formBean.init();
/*     */     } 
/* 131 */     int pageNum = formBean.getPageNoAsInt();
/* 132 */     int pageSize = formBean.getPageSizeAsInt();
/* 133 */     List entityList = manager.getList(conditions, pageNum, pageSize, CustomerreturnsQueryOrder.getEnum(formBean.getOrder()), 
/* 134 */         formBean.isDescend());
/* 135 */     request.setAttribute("x_selType", Integer.valueOf(136));
/* 136 */     putEnumListToRequest(request);
/* 137 */     request.setAttribute("X_RESULTLIST", entityList);
/* 138 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 144 */       int num = 0;
/* 145 */       num++;
/* 146 */       String numberStr = "";
/* 147 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 148 */       StringBuffer buffer = new StringBuffer();
/* 149 */       SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
/* 150 */       Date date = new Date();
/* 151 */       BeanForm formBean = (BeanForm)form;
/* 152 */       Customerreturns entity = new Customerreturns();
/* 153 */       formBean.populateToBean(entity);
/* 154 */       CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 155 */       BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
/*     */       
/* 157 */       SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 158 */       List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
/*     */       
/* 160 */       CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/* 161 */       CustomerReturnItem returnitem = new CustomerReturnItem();
/*     */ 
/*     */       
/* 164 */       BasicCustomer basicCustomers = basicCustomerManager.getById(entity.getBasicCustomer().getId());
/* 165 */       entity.setDelStatus(Integer.valueOf(1));
/*     */       
/* 167 */       buffer.append(basicCustomers.getCode());
/* 168 */       buffer.append(format.format(date));
/*     */       
/* 170 */       conditions.put(CustomerreturnsQueryCondition.RETURNNUMBER_LIKE, buffer);
/* 171 */       List<Customerreturns> customerList = manager.getList(conditions, 0, 1, CustomerreturnsQueryOrder.ID, true);
/* 172 */       String str = String.valueOf(num);
/* 173 */       if (customerList == null || customerList.size() == 0) {
/* 174 */         for (int i = 0; i < 3 - str.length(); i++) {
/* 175 */           numberStr = String.valueOf(numberStr) + "0";
/*     */         }
/* 177 */         buffer.append(numberStr);
/* 178 */         buffer.append(num);
/*     */       } else {
/*     */         
/* 181 */         String returnNumber = ((Customerreturns)customerList.get(0)).getReturnNumber();
/*     */         
/* 183 */         String LSH = returnNumber.substring(14, returnNumber.length());
/* 184 */         num = Integer.valueOf(LSH).intValue();
/* 185 */         num++;
/* 186 */         String strLSH = String.valueOf(num);
/* 187 */         for (int i = 0; i < 3 - strLSH.length(); i++) {
/* 188 */           numberStr = String.valueOf(numberStr) + "0";
/*     */         }
/* 190 */         buffer.append(numberStr);
/* 191 */         buffer.append(num);
/*     */       } 
/*     */ 
/*     */       
/* 195 */       String chanpinCode = entity.getChanpinCode();
/* 196 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 197 */       String[] strCode = null;
/* 198 */       strCode = chanpinCode.split(",");
/* 199 */       map.put(SalesWorkorderQueryCondition.ID_IN, strCode);
/* 200 */       workorderList = workorderManager.getList(map, 0, -1, null, false);
/*     */ 
/*     */       
/* 203 */       entity.setReturnNumber(buffer.toString());
/* 204 */       entity.setCustomerDescription(basicCustomers.getName2());
/* 205 */       request.setAttribute("X_OBJECT", manager.save(entity));
/*     */ 
/*     */       
/* 208 */       for (SalesWorkorder order : workorderList) {
/*     */         
/* 210 */         returnitem.setCustomerreturns(entity);
/* 211 */         returnitem.setBatchNumber(order.getLotSer().getId());
/* 212 */         returnitem.setPart(order.getPart());
/* 213 */         returnitem.setMaterialDescription(order.getPart().getDescribe1());
/*     */         
/* 215 */         returnitem.setQty(Integer.valueOf(order.getCount().intValue()));
/*     */         
/* 217 */         returnitem.setReturnStorage(entity.getReturnStorage());
/* 218 */         returnitem.setSalesDeliveryDate(order.getOutDate());
/* 219 */         returnitem.setWorkorderId(order);
/* 220 */         customerReturnItemManager.save(returnitem);
/*     */         
/* 222 */         order.setStatus1(SalesPreshiporderBatchStatus.SALES_RETURN);
/* 223 */         workorderManager.update(order);
/*     */       } 
/*     */ 
/*     */       
/* 227 */       return new ActionForward("/listCustomerreturnsAction.do");
/* 228 */     } catch (Exception e) {
/*     */       
/* 230 */       e.printStackTrace();
/*     */       
/* 232 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 238 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/*     */     
/* 240 */     Customerreturns entity = new Customerreturns();
/* 241 */     putEnumListToRequest(request);
/* 242 */     request.setAttribute("X_OBJECT", entity);
/* 243 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 248 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 249 */     BeanForm formBean = (BeanForm)form;
/* 250 */     Customerreturns entity = new Customerreturns();
/* 251 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 252 */     formBean.populateToBean(entity, request);
/* 253 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 254 */     request.setAttribute("X_ROWPAGE", "customerreturns/row.jsp");
/* 255 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward updateDelStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 261 */       CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 262 */       CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/* 263 */       SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 264 */       Customerreturns entity = new Customerreturns();
/* 265 */       String id = request.getParameter("id");
/* 266 */       entity = manager.getCustomerreturns(Integer.valueOf(Integer.parseInt(id)));
/* 267 */       entity.setDelStatus(Integer.valueOf(0));
/*     */       
/* 269 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 270 */       conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, entity.getId());
/* 271 */       List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
/* 272 */       for (CustomerReturnItem customerReturnItem : customerReturnItemList) {
/* 273 */         SalesWorkorder salesWorkorder = customerReturnItem.getWorkorderId();
/* 274 */         salesWorkorder.setStatus1(SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 275 */         workorderManager.update(salesWorkorder);
/*     */       } 
/* 277 */       request.setAttribute("X_OBJECT", manager.update(entity));
/* 278 */       return new ActionForward("/listCustomerreturnsAction.do");
/* 279 */     } catch (Exception e) {
/*     */       
/* 281 */       e.printStackTrace();
/*     */       
/* 283 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 288 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 289 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/*     */ 
/*     */     
/* 292 */     Customerreturns customerreturns = manager.getCustomerreturns(id);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     manager.delete(customerreturns);
/* 302 */     return new ActionForward("listCustomerreturnsAction.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward itemlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 307 */     CustomerreturnsQueryForm customerqueryform = (CustomerreturnsQueryForm)form;
/* 308 */     SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 309 */     List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
/* 310 */     String chanpinId = request.getParameter("chanpinId");
/* 311 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 312 */     String[] str = null;
/* 313 */     str = chanpinId.split(",");
/* 314 */     map.put(SalesWorkorderQueryCondition.ID_IN, str);
/* 315 */     getConditionAndOrder((BaseSessionQueryForm)customerqueryform, map, request);
/* 316 */     if (customerqueryform.isFirstInit()) {
/* 317 */       customerqueryform.init(workorderManager.getListCount(map));
/*     */     } else {
/* 319 */       customerqueryform.init();
/*     */     } 
/* 321 */     int pageNum = customerqueryform.getPageNoAsInt();
/* 322 */     int pageSize = customerqueryform.getPageSizeAsInt();
/* 323 */     workorderList = workorderManager.getList(map, pageNum, pageSize, null, false);
/* 324 */     putEnumListToRequest(request);
/* 325 */     request.setAttribute("workOrderList", workorderList);
/* 326 */     request.setAttribute("chanpin_id", chanpinId);
/* 327 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 332 */       CustomerreturnsQueryForm customerqueryform = (CustomerreturnsQueryForm)form;
/* 333 */       SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 334 */       List<SalesWorkorder> workorderList = new ArrayList<SalesWorkorder>();
/* 335 */       String chanpinId = request.getParameter("chanpinId");
/* 336 */       String[] str = null;
/* 337 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 338 */       if (chanpinId != null) {
/* 339 */         str = chanpinId.split(",");
/* 340 */         map.put(SalesWorkorderQueryCondition.ID_IN, str);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 345 */         workorderList = workorderManager.getList(map, customerqueryform.getPageNoAsInt(), customerqueryform.getPageSizeAsInt(), null, false);
/* 346 */         putEnumListToRequest(request);
/* 347 */         if (customerqueryform.isFirstInit()) {
/* 348 */           customerqueryform.init(workorderList.size());
/*     */         } else {
/* 350 */           customerqueryform.init();
/*     */         } 
/* 352 */         request.setAttribute("workOrderList", workorderList);
/*     */         
/* 354 */         request.setAttribute("chanpin_id", chanpinId);
/*     */         
/* 356 */         return mapping.findForward("page");
/*     */       } 
/* 358 */       customerqueryform.setPageSize("1");
/* 359 */       customerqueryform.init(2);
/* 360 */       System.out.println("123");
/* 361 */       putEnumListToRequest(request);
/* 362 */       request.setAttribute("workOrderList", workorderList);
/* 363 */       request.setAttribute("chanpin_id", chanpinId);
/* 364 */       return mapping.findForward("page");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 370 */     catch (Exception e) {
/*     */       
/* 372 */       e.printStackTrace();
/*     */       
/* 374 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 379 */     BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
/* 380 */     BasicCustomerQueryForm queryForm = (BasicCustomerQueryForm)form;
/* 381 */     Map<BasicCustomerQueryCondition, Integer> conditions = getQueryConditions(queryForm);
/* 382 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 383 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/* 384 */     Site site = siteList.get(0);
/* 385 */     if (site.getId().intValue() != 1) {
/* 386 */       conditions.put(BasicCustomerQueryCondition.SITEID_ID_EQ, site.getId());
/*     */     }
/* 388 */     String exportType = queryForm.getExportType();
/* 389 */     if (exportType != null && exportType.length() > 0) {
/* 390 */       List data = basicCustomerManager.getList(conditions, 0, -1, null, false);
/* 391 */       int index = SessionTempFile.createNewTempFile(request);
/* 392 */       String fileName = "BasicCustomer";
/* 393 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 394 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 397 */               MessageResources messages = CustomerreturnsAction.this.getResources(request);
/* 398 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.code"));
/* 399 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.name1"));
/* 400 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.name2"));
/* 401 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address"));
/* 402 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address2"));
/* 403 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.address3"));
/* 404 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.country"));
/* 405 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.city"));
/* 406 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.fax"));
/* 407 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.post_id"));
/* 408 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.currency_type"));
/* 409 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.contacts"));
/* 410 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.phone"));
/* 411 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.email"));
/* 412 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.product"));
/* 413 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.site"));
/* 414 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.domain"));
/* 415 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.remarks"));
/* 416 */               row.add(messages.getMessage(CustomerreturnsAction.this.getLocale(request), "basic_customer.enabled"));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 424 */               row.add(BeanUtils.getProperty(data, "code"));
/* 425 */               row.add(BeanUtils.getProperty(data, "name1"));
/* 426 */               row.add(BeanUtils.getProperty(data, "name2"));
/* 427 */               row.add(BeanUtils.getProperty(data, "address"));
/* 428 */               row.add(BeanUtils.getProperty(data, "address2"));
/* 429 */               row.add(BeanUtils.getProperty(data, "address3"));
/* 430 */               row.add(BeanUtils.getProperty(data, "country"));
/* 431 */               row.add(BeanUtils.getProperty(data, "city"));
/* 432 */               row.add(BeanUtils.getProperty(data, "fax"));
/* 433 */               row.add(BeanUtils.getProperty(data, "postId"));
/* 434 */               row.add(BeanUtils.getProperty(data, "currencyType"));
/* 435 */               row.add(BeanUtils.getProperty(data, "contacts"));
/* 436 */               row.add(BeanUtils.getProperty(data, "phone"));
/* 437 */               row.add(BeanUtils.getProperty(data, "email"));
/* 438 */               row.add(BeanUtils.getProperty(data, "product"));
/* 439 */               row.add(BeanUtils.getProperty(data, "site"));
/* 440 */               row.add(BeanUtils.getProperty(data, "domain"));
/* 441 */               row.add(BeanUtils.getProperty(data, "remarks"));
/* 442 */               String locale = CustomerreturnsAction.this.getCurrentUser(request).getLocale();
/* 443 */               if ("en".equals(locale)) {
/* 444 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/* 446 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 450 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 453 */     if (queryForm.isFirstInit()) {
/* 454 */       queryForm.init(basicCustomerManager.getListCount(conditions));
/*     */     } else {
/* 456 */       queryForm.init();
/*     */     } 
/*     */     
/* 459 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 460 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 461 */     List<BasicCustomer> resultList = basicCustomerManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), BasicCustomerQueryOrder.ID, 
/* 462 */         queryForm.isDescend());
/*     */     
/* 464 */     putEnumListToRequest(request);
/* 465 */     request.setAttribute("X_RESULTLIST", resultList);
/* 466 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 472 */       SalesWorkorderManager workorderManager = ServiceLocator.getSalesWorkorderManager(request);
/* 473 */       CustomerreturnsQueryForm returns = (CustomerreturnsQueryForm)form;
/* 474 */       String customerCode = request.getParameter("id");
/*     */       
/* 476 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 477 */       getConditionAndOrder((BaseSessionQueryForm)returns, conditions, request);
/* 478 */       conditions.put(SalesWorkorderQueryCondition.CUSTOMERCODE_EQ, customerCode);
/* 479 */       conditions.put(SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, SalesPreshiporderBatchStatus.IN_DELIVERYBATCH);
/* 480 */       if (returns.isFirstInit()) {
/* 481 */         returns.init(workorderManager.getListCount(conditions));
/*     */       } else {
/* 483 */         returns.init();
/*     */       } 
/* 485 */       List<SalesWorkorder> salesWorkorderList = workorderManager.getList(conditions, returns.getPageNoAsInt(), returns.getPageSizeAsInt(), SalesWorkorderQueryOrder.ID, false);
/* 486 */       request.setAttribute("customerCode", customerCode);
/* 487 */       request.setAttribute("x_salesWorkorderList", salesWorkorderList);
/* 488 */       return mapping.findForward("page");
/* 489 */     } catch (Exception e) {
/*     */       
/* 491 */       e.printStackTrace();
/*     */       
/* 493 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Map getQueryConditions(BasicCustomerQueryForm queryForm) {
/* 498 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 499 */     String queryStr = "";
/* 500 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 503 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 507 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/*     */   }
/*     */   
/*     */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 511 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 512 */     CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/* 513 */     CustomerReturnItemQueryForm queryForm = (CustomerReturnItemQueryForm)form;
/*     */     
/* 515 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 516 */     Customerreturns customerreturns = manager.getCustomerreturns(id);
/*     */ 
/*     */     
/* 519 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 520 */     conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, customerreturns.getId());
/* 521 */     List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerReturnItemQueryOrder.ID, false);
/*     */     
/* 523 */     if (queryForm.isFirstInit()) {
/* 524 */       queryForm.init(customerReturnItemManager.getListCount(conditions).intValue());
/*     */     } else {
/* 526 */       queryForm.init();
/*     */     } 
/* 528 */     request.setAttribute("x_customerreturns", customerreturns);
/* 529 */     request.setAttribute("x_customerReturnItemList", 
/* 530 */         customerReturnItemList);
/* 531 */     request.setAttribute("x_Id", id);
/*     */     
/* 533 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 535 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 536 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 537 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 541 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 542 */     CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/*     */     
/* 544 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 545 */     Customerreturns customerreturns = manager.getCustomerreturns(id);
/*     */ 
/*     */     
/* 548 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 549 */     conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, id);
/* 550 */     List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
/* 551 */     Map<String, CustomerReturnItem> maps = new HashMap<String, CustomerReturnItem>();
/* 552 */     for (CustomerReturnItem customerReturnItem : customerReturnItemList) {
/* 553 */       if (maps.size() == 0) {
/* 554 */         maps.put(customerReturnItem.getPart().getId(), customerReturnItem); continue;
/* 555 */       }  if (maps.containsKey(customerReturnItem.getPart().getId())) {
/* 556 */         CustomerReturnItem customerReturnItema = maps.get(customerReturnItem.getPart().getId());
/* 557 */         customerReturnItema.setQty(Integer.valueOf(customerReturnItema.getQty().intValue() + customerReturnItem.getQty().intValue()));
/* 558 */         maps.put(customerReturnItema.getPart().getId(), customerReturnItema); continue;
/*     */       } 
/* 560 */       maps.put(customerReturnItem.getPart().getId(), customerReturnItem);
/*     */     } 
/*     */     
/* 563 */     List<CustomerReturnItem> customerReturnByItemList = new ArrayList<CustomerReturnItem>();
/* 564 */     for (String partId : maps.keySet()) {
/* 565 */       customerReturnByItemList.add(maps.get(partId));
/*     */     }
/* 567 */     request.setAttribute("x_customerreturns", customerreturns);
/* 568 */     request.setAttribute("x_customerReturnItemList", 
/* 569 */         customerReturnByItemList);
/* 570 */     request.setAttribute("x_Id", id);
/*     */     
/* 572 */     request.getSession().setAttribute("path", request.getContextPath());
/*     */     
/* 574 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 575 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/* 576 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward updateCustomerreturnsByItemPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 580 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/* 581 */     CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/* 582 */     PurchaseOrderPutInStorageManager inStorageManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/*     */     
/* 584 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 585 */     Customerreturns customerreturns = manager.getCustomerreturns(id);
/*     */     
/* 587 */     if (customerreturns.getPrintStatus() != YesNo.YES) {
/* 588 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 589 */       conditions.put(CustomerReturnItemQueryCondition.CUSTOMERRETURNS_ID_EQ, customerreturns.getId());
/* 590 */       List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.ID, false);
/* 591 */       for (CustomerReturnItem customerReturnItem : customerReturnItemList) {
/* 592 */         inStorageManager.scanningCustomerreturnInStorages(customerReturnItem.getBatchNumber(), customerReturnItem.getReturnStorage(), getCurrentUser(request).getId().toString());
/*     */       }
/* 594 */       customerreturns.setPrintStatus(YesNo.YES);
/* 595 */       manager.update(customerreturns);
/*     */     } 
/* 597 */     return new ActionForward("listCustomerreturnsAction.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward selectCustomerReturnLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 602 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 603 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 604 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 605 */       queryForm.setDescend(false);
/*     */     } 
/* 607 */     User user = getCurrentUser(request);
/* 608 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 609 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 610 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 611 */     conditions.put(StorageLocationQueryCondition.SITE_EQ, user.getPrimarySite().getId());
/* 612 */     conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(3));
/*     */ 
/*     */     
/* 615 */     if (queryForm.isFirstInit()) {
/* 616 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/*     */     } else {
/* 618 */       queryForm.init();
/*     */     } 
/* 620 */     int pageNo = queryForm.getPageNoAsInt();
/* 621 */     int pageSize = queryForm.getPageSizeAsInt();
/* 622 */     List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */     
/* 625 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/* 626 */     request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
/* 627 */     request.setAttribute("X_RESULTLIST", result);
/* 628 */     putEnumListToRequest(request);
/*     */     
/* 630 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/CustomerreturnsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */