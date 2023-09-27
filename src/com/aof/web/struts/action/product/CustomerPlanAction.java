/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.CustomerPlan;
/*     */ import com.aof.model.product.query.CustomerPlanQueryOrder;
/*     */ import com.aof.service.Product.CustomerPlanManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.CustomerPlanQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class CustomerPlanAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  48 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/*  49 */     CustomerPlanQueryForm queryForm = (CustomerPlanQueryForm)form;
/*  50 */     Map conditions = getQueryConditions(queryForm);
/*  51 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  52 */     if (queryForm.getOrder() == "") {
/*  53 */       queryForm.setDescend(true);
/*     */     }
/*  55 */     String exportType = queryForm.getExportType();
/*  56 */     if (exportType != null && exportType.length() > 0) {
/*  57 */       List data = customerPlanManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerPlanQueryOrder.ID, false);
/*  58 */       int index = SessionTempFile.createNewTempFile(request);
/*  59 */       String fileName = "CustomerPlan";
/*  60 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  61 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  64 */               MessageResources messages = CustomerPlanAction.this.getResources(request);
/*  65 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.line"));
/*  66 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.planNumbers"));
/*  67 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.code"));
/*  68 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.address"));
/*  69 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.wmspart.id"));
/*  70 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "DPI"));
/*  71 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.wmspart.describe1"));
/*  72 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.um"));
/*  73 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.entryDate"));
/*  74 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.qty"));
/*  75 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.receiptQty"));
/*  76 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.qtyOpen"));
/*     */               
/*  78 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.operation"));
/*  79 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.shipmentDate"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  84 */               CustomerPlan custPlan = (CustomerPlan)data;
/*  85 */               row.add(BeanUtils.getProperty(data, "line"));
/*  86 */               row.add(BeanUtils.getProperty(data, "planNumbers"));
/*  87 */               row.add(BeanUtils.getProperty(data, "customer.code"));
/*  88 */               row.add(BeanUtils.getProperty(data, "customer.address"));
/*  89 */               row.add(BeanUtils.getProperty(data, "wmspart.id"));
/*  90 */               row.add(BeanUtils.getProperty(data, "wmspart.dpiNo"));
/*  91 */               row.add(BeanUtils.getProperty(data, "wmspart.describe1"));
/*  92 */               row.add(BeanUtils.getProperty(data, "um"));
/*     */               
/*  94 */               String entryDate = BeanUtils.getProperty(data, "entryDate");
/*  95 */               SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
/*  96 */               String date = adf.format(custPlan.getEntryDate());
/*  97 */               row.add(date);
/*  98 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  99 */               row.add(BeanUtils.getProperty(data, "receiptQty"));
/* 100 */               row.add(BeanUtils.getProperty(data, "qtyOpen"));
/*     */               
/* 102 */               row.add(BeanUtils.getProperty(data, "operation"));
/*     */               
/* 104 */               String shipmentDate = BeanUtils.getProperty(data, "shipmentDate");
/* 105 */               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 106 */               String time = adf.format(custPlan.getShipmentDate());
/* 107 */               row.add(time);
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 122 */     if (queryForm.isFirstInit()) {
/* 123 */       queryForm.init(customerPlanManager.getListCount(conditions));
/*     */     } else {
/* 125 */       queryForm.init();
/*     */     } 
/*     */     
/* 128 */     List<CustomerPlan> resultList = customerPlanManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerPlanQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 130 */     request.setAttribute("X_RESULTLIST", resultList);
/* 131 */     request.setAttribute("x_selType", Integer.valueOf(113));
/* 132 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(CustomerPlanQueryForm queryForm) {
/* 137 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 138 */     String queryStr = "";
/* 139 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 142 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 146 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 151 */     getBasic(request);
/* 152 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 157 */     CustomerPlanManager manager = ServiceLocator.getCustomerPlanManager(request);
/* 158 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 159 */     User requestor = getCurrentUser(request);
/* 160 */     String planNumber = manager.getPlanNumbers(new Date(), requestor);
/* 161 */     BeanForm formBean = (BeanForm)form;
/* 162 */     String[] partIdList = request.getParameterValues("partId");
/* 163 */     String[] partPriceList = request.getParameterValues("partPrices");
/* 164 */     String[] currList = request.getParameterValues("currs");
/* 165 */     String[] sotaxcList = request.getParameterValues("sotaxcs");
/*     */     
/* 167 */     String[] deliveryNumbersList = request.getParameterValues("deliveryNumbers");
/*     */     try {
/* 169 */       Integer sum = Integer.valueOf(1);
/* 170 */       for (int i = 0; i < partIdList.length; i++) {
/* 171 */         if (!deliveryNumbersList[i].equals(""))
/*     */         
/*     */         { 
/* 174 */           String partId = partIdList[i];
/* 175 */           BigDecimal deliveryNumber = new BigDecimal(deliveryNumbersList[i]);
/* 176 */           WmsPart part = partManager.getWmsPart(partId);
/* 177 */           CustomerPlan customerPlan = new CustomerPlan();
/* 178 */           formBean.populateToBean(customerPlan);
/* 179 */           customerPlan.setPlanNumbers(planNumber);
/* 180 */           customerPlan.setEntryDate(new Date());
/* 181 */           customerPlan.setWmspart(part);
/* 182 */           customerPlan.setUm(part.getUnit());
/* 183 */           customerPlan.setLine(sum.toString());
/* 184 */           customerPlan.setReturnQtySum(new BigDecimal(0));
/* 185 */           customerPlan.setReceiptQty(new BigDecimal(0));
/* 186 */           customerPlan.setQtyOpen(deliveryNumber);
/* 187 */           customerPlan.setQty(deliveryNumber);
/* 188 */           String price = partPriceList[i];
/* 189 */           price = price.replace(",", "");
/* 190 */           BigDecimal partPrice = new BigDecimal(price);
/* 191 */           customerPlan.setUnitPrice(partPrice);
/* 192 */           customerPlan.setSotaxc(sotaxcList[i]);
/* 193 */           customerPlan.setOperation(getCurrentUser(request).getName());
/* 194 */           customerPlan.setCurr(currList[i]);
/* 195 */           customerPlan.setStatus(Integer.valueOf(0));
/* 196 */           customerPlan = manager.insert(customerPlan);
/* 197 */           sum = Integer.valueOf(sum.intValue() + 1); } 
/*     */       } 
/* 199 */     } catch (Exception e) {
/* 200 */       e.fillInStackTrace();
/* 201 */       System.out.println(e.getMessage());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     return new ActionForward("listCustomerPlan.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 215 */     CustomerPlanManager manager = ServiceLocator.getCustomerPlanManager(request);
/* 216 */     String idStr = request.getParameter("id");
/* 217 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 218 */     CustomerPlan customerPlan = manager.getById(id);
/* 219 */     if (customerPlan == null) throw new ActionException("customerPlan.notFound", id); 
/* 220 */     if (!isBack(request)) {
/* 221 */       BeanForm cityForm = (BeanForm)getForm("/updateCustomerPlan", request);
/* 222 */       cityForm.populate(customerPlan, "to_form");
/*     */     } 
/* 224 */     request.setAttribute("X_OBJECT", customerPlan);
/* 225 */     getBasic(request);
/* 226 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 231 */     CustomerPlanManager manager = ServiceLocator.getCustomerPlanManager(request);
/* 232 */     String idStr = request.getParameter("id");
/* 233 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 234 */     BeanForm formBean = (BeanForm)form;
/* 235 */     CustomerPlan customerPlan = manager.getById(id);
/* 236 */     formBean.populateToBean(customerPlan, request);
/* 237 */     customerPlan = manager.update(customerPlan);
/* 238 */     request.setAttribute("X_OBJECT", customerPlan);
/* 239 */     request.setAttribute("X_ROWPAGE", "customerPlan/row.jsp");
/* 240 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 245 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 246 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 247 */     customerPlanManager.remove(customerPlanManager.getById(id));
/* 248 */     return new ActionForward("listCustomerPlan.do", true);
/*     */   }
/*     */   
/*     */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 252 */     CustomerPlanManager manager = ServiceLocator.getCustomerPlanManager(request);
/* 253 */     String id = request.getParameter("id");
/* 254 */     CustomerPlan customerPlan = manager.getById(Integer.valueOf(Integer.parseInt(id)));
/* 255 */     if (customerPlan == null) throw new ActionException("customerPlan.notFound", id); 
/* 256 */     request.setAttribute("X_OBJECT", customerPlan);
/* 257 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward customerPlanListCloseOrOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 262 */     CustomerPlanManager customerPlanManager = ServiceLocator.getCustomerPlanManager(request);
/* 263 */     CustomerPlanQueryForm queryForm = (CustomerPlanQueryForm)form;
/* 264 */     Map conditions = getQueryConditions(queryForm);
/* 265 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 267 */     String exportType = queryForm.getExportType();
/* 268 */     if (exportType != null && exportType.length() > 0) {
/* 269 */       List data = customerPlanManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerPlanQueryOrder.ID, false);
/* 270 */       int index = SessionTempFile.createNewTempFile(request);
/* 271 */       String fileName = "CustomerPlan";
/* 272 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 273 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 276 */               MessageResources messages = CustomerPlanAction.this.getResources(request);
/* 277 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.line"));
/* 278 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.planNumbers"));
/* 279 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.code"));
/* 280 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.address"));
/* 281 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.wmspart.id"));
/* 282 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "DPI"));
/* 283 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.wmspart.describe1"));
/* 284 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.um"));
/* 285 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.entryDate"));
/* 286 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.qty"));
/* 287 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.receiptQty"));
/* 288 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.qtyOpen"));
/* 289 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.returnQtySum"));
/* 290 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.shipmentDate"));
/* 291 */               row.add(messages.getMessage(CustomerPlanAction.this.getLocale(request), "customer.status"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 295 */               CustomerPlan custPlan = (CustomerPlan)data;
/* 296 */               row.add(BeanUtils.getProperty(data, "line"));
/* 297 */               row.add(BeanUtils.getProperty(data, "planNumbers"));
/* 298 */               row.add(BeanUtils.getProperty(data, "customer.code"));
/* 299 */               row.add(BeanUtils.getProperty(data, "customer.address"));
/* 300 */               row.add(BeanUtils.getProperty(data, "wmspart.id"));
/* 301 */               row.add(BeanUtils.getProperty(data, "wmspart.dpiNo"));
/* 302 */               row.add(BeanUtils.getProperty(data, "wmspart.describe1"));
/* 303 */               row.add(BeanUtils.getProperty(data, "um"));
/*     */               
/* 305 */               String entryDate = BeanUtils.getProperty(data, "entryDate");
/* 306 */               SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
/* 307 */               String date = adf.format(custPlan.getEntryDate());
/* 308 */               row.add(date);
/* 309 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 310 */               row.add(BeanUtils.getProperty(data, "receiptQty"));
/* 311 */               row.add(BeanUtils.getProperty(data, "qtyOpen"));
/* 312 */               row.add(BeanUtils.getProperty(data, "returnQtySum"));
/*     */               
/* 314 */               String shipmentDate = BeanUtils.getProperty(data, "shipmentDate");
/* 315 */               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 316 */               String time = adf.format(custPlan.getShipmentDate());
/* 317 */               row.add(time);
/*     */               
/* 319 */               String status = BeanUtils.getProperty(data, "status");
/* 320 */               if (status.equals("0")) {
/* 321 */                 row.add("打开 ");
/*     */               }
/* 323 */               if (!status.equals("0")) {
/* 324 */                 row.add("关闭");
/*     */               }
/*     */             }
/*     */           });
/*     */       
/* 329 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 332 */     if (queryForm.isFirstInit()) {
/* 333 */       queryForm.init(customerPlanManager.getListCount(conditions));
/*     */     } else {
/* 335 */       queryForm.init();
/*     */     } 
/*     */     
/* 338 */     List<CustomerPlan> resultList = customerPlanManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerPlanQueryOrder.ID, false);
/*     */     
/* 340 */     request.setAttribute("X_RESULTLIST", resultList);
/* 341 */     request.setAttribute("x_selType", Integer.valueOf(148));
/* 342 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward customerPlanCloseOrOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 347 */     CustomerPlanManager manager = ServiceLocator.getCustomerPlanManager(request);
/* 348 */     String idStr = request.getParameter("id");
/* 349 */     String type = request.getParameter("type");
/* 350 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 351 */     CustomerPlan customerPlan = manager.getById(id);
/* 352 */     if (customerPlan == null) throw new ActionException("customerPlan.notFound", id); 
/* 353 */     if (type.equals("1")) {
/* 354 */       customerPlan.setStatus(Integer.valueOf(1));
/* 355 */     } else if (type.equals("0")) {
/* 356 */       customerPlan.setStatus(Integer.valueOf(0));
/*     */     } 
/* 358 */     manager.update(customerPlan);
/* 359 */     return new ActionForward("listCustomerPlanCloseOrOpen.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/CustomerPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */