/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.product.SalesOrderItem;
/*     */ import com.aof.model.product.query.SalesOrderItemQueryCondition;
/*     */ import com.aof.service.Product.SalesOrderItemManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesOrderItemQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
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
/*     */ public class SalesOrderItemAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  45 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/*  46 */     SalesOrderItemQueryForm queryForm = (SalesOrderItemQueryForm)form;
/*  47 */     Map conditions = getQueryConditions(queryForm);
/*  48 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  49 */     String exportType = queryForm.getExportType();
/*  50 */     if (exportType != null && exportType.length() > 0) {
/*  51 */       List data = salesOrderItemManager.getList(conditions, 0, -1, null, false);
/*  52 */       int index = SessionTempFile.createNewTempFile(request);
/*  53 */       String fileName = "SalesOrderItem";
/*  54 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  55 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  58 */               MessageResources messages = SalesOrderItemAction.this.getResources(request);
/*  59 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.soipnumber"));
/*  60 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.soId.custName"));
/*  61 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.soId.custCode"));
/*  62 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.soId.custAddress"));
/*  63 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.line"));
/*  64 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.itemNumber.id"));
/*  65 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "DPI"));
/*  66 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.um"));
/*  67 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.qty"));
/*  68 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.dueDate"));
/*  69 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.receiptQty"));
/*  70 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.boxcount"));
/*  71 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.describe"));
/*  72 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.returnQtySum"));
/*  73 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.factory"));
/*  74 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.poDomain"));
/*  75 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.site.name"));
/*  76 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.istxt"));
/*  77 */               row.add(messages.getMessage(SalesOrderItemAction.this.getLocale(request), "salesOrderItem.sotype"));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  83 */               row.add(BeanUtils.getProperty(data, "soipNumber"));
/*  84 */               row.add(BeanUtils.getProperty(data, "soId.custName"));
/*  85 */               row.add(BeanUtils.getProperty(data, "soId.custCode"));
/*  86 */               row.add(BeanUtils.getProperty(data, "soId.custAddress"));
/*  87 */               row.add(BeanUtils.getProperty(data, "line"));
/*  88 */               row.add(BeanUtils.getProperty(data, "itemNumber.id"));
/*  89 */               row.add(BeanUtils.getProperty(data, "itemNumber.dpiNo"));
/*     */ 
/*     */ 
/*     */               
/*  93 */               row.add(BeanUtils.getProperty(data, "um"));
/*  94 */               row.add(BeanUtils.getProperty(data, "qty"));
/*     */               
/*  96 */               row.add(BeanUtils.getProperty(data, "dueDate"));
/*     */               
/*  98 */               row.add(BeanUtils.getProperty(data, "receiptQty"));
/*  99 */               row.add(BeanUtils.getProperty(data, "boxcount"));
/* 100 */               row.add(BeanUtils.getProperty(data, "describe"));
/* 101 */               row.add(BeanUtils.getProperty(data, "returnQtySum"));
/* 102 */               row.add(BeanUtils.getProperty(data, "factory"));
/* 103 */               row.add(BeanUtils.getProperty(data, "poDomain"));
/* 104 */               row.add(BeanUtils.getProperty(data, "site.name"));
/* 105 */               row.add(BeanUtils.getProperty(data, "istxt.chnShortDescription"));
/*     */               
/* 107 */               row.add(BeanUtils.getProperty(data, "sotype"));
/*     */             }
/*     */           });
/*     */       
/* 111 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 114 */     if (queryForm.isFirstInit()) {
/* 115 */       queryForm.init(salesOrderItemManager.getListCount(conditions));
/*     */     } else {
/* 117 */       queryForm.init();
/*     */     } 
/*     */     
/* 120 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 121 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 122 */     List<SalesOrderItem> resultList = salesOrderItemManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 123 */     request.setAttribute("x_selType", Integer.valueOf(116));
/* 124 */     request.setAttribute("X_RESULTLIST", resultList);
/* 125 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesOrderItemQueryForm queryForm) {
/* 130 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 131 */     String queryStr = "";
/* 132 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 135 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 139 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 140 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 145 */     getBasic(request);
/* 146 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 151 */     SalesOrderItemManager manager = ServiceLocator.getSalesOrderItemManager(request);
/* 152 */     BeanForm formBean = (BeanForm)form;
/* 153 */     SalesOrderItem salesOrderItem = new SalesOrderItem();
/* 154 */     formBean.populateToBean(salesOrderItem);
/* 155 */     salesOrderItem = manager.insert(salesOrderItem);
/* 156 */     request.setAttribute("X_OBJECT", salesOrderItem);
/* 157 */     request.setAttribute("X_ROWPAGE", "wmsbasic/salesOrder/row.jsp");
/* 158 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 163 */     SalesOrderItemManager manager = ServiceLocator.getSalesOrderItemManager(request);
/* 164 */     String idStr = request.getParameter("id");
/* 165 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 166 */     SalesOrderItem salesOrderItem = manager.getById(id);
/* 167 */     if (salesOrderItem == null) throw new ActionException("salesOrderItem.notFound", id); 
/* 168 */     if (!isBack(request)) {
/* 169 */       BeanForm cityForm = (BeanForm)getForm("/updateSalesOrderItem", request);
/* 170 */       cityForm.populate(salesOrderItem, "to_form");
/*     */     } 
/* 172 */     request.setAttribute("X_OBJECT", salesOrderItem);
/* 173 */     getBasic(request);
/* 174 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 179 */     SalesOrderItemManager manager = ServiceLocator.getSalesOrderItemManager(request);
/* 180 */     String idStr = request.getParameter("id");
/* 181 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 182 */     BeanForm formBean = (BeanForm)form;
/* 183 */     SalesOrderItem salesOrderItem = manager.getById(id);
/* 184 */     formBean.populateToBean(salesOrderItem, request);
/* 185 */     salesOrderItem = manager.update(salesOrderItem);
/* 186 */     request.setAttribute("X_OBJECT", salesOrderItem);
/* 187 */     request.setAttribute("X_ROWPAGE", "wmsbasic/salesOrder/row.jsp");
/* 188 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 193 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 194 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 195 */     salesOrderItemManager.remove(salesOrderItemManager.getById(id));
/* 196 */     return new ActionForward("listSalesOrderItem.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward closeSalesOrderItemList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 201 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 202 */     SalesOrderItemQueryForm queryForm = (SalesOrderItemQueryForm)form;
/* 203 */     Map conditions = getQueryConditions(queryForm);
/* 204 */     getConditionAndOrder(queryForm, conditions, request);
/* 205 */     conditions.put(SalesOrderItemQueryCondition.STATUS_OPEN_EQ, Integer.valueOf(0));
/* 206 */     if (queryForm.isFirstInit()) {
/* 207 */       queryForm.init(salesOrderItemManager.getListCount(conditions));
/*     */     } else {
/* 209 */       queryForm.init();
/*     */     } 
/*     */     
/* 212 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 213 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 214 */     List<SalesOrderItem> resultList = salesOrderItemManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 215 */     request.setAttribute("x_selType", Integer.valueOf(141));
/* 216 */     request.setAttribute("X_RESULTLIST", resultList);
/* 217 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward openSalesOrderItemList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 222 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 223 */     SalesOrderItemQueryForm queryForm = (SalesOrderItemQueryForm)form;
/* 224 */     Map conditions = getQueryConditions(queryForm);
/* 225 */     getConditionAndOrder(queryForm, conditions, request);
/* 226 */     conditions.put(SalesOrderItemQueryCondition.STATUS_EQ, Integer.valueOf(1));
/* 227 */     if (queryForm.isFirstInit()) {
/* 228 */       queryForm.init(salesOrderItemManager.getListCount(conditions));
/*     */     } else {
/* 230 */       queryForm.init();
/*     */     } 
/*     */     
/* 233 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 234 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 235 */     List<SalesOrderItem> resultList = salesOrderItemManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/* 236 */     request.setAttribute("x_selType", Integer.valueOf(142));
/* 237 */     request.setAttribute("X_RESULTLIST", resultList);
/* 238 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward salesOrderItemClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 244 */     response.setContentType("text/json");
/* 246 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 248 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 249 */     String array = request.getParameter("arrays");
/* 250 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 251 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 252 */       SalesOrderItem salesOrderItem = salesOrderItemManager.getById(Integer.valueOf(Integer.parseInt(id)));
/* 253 */       salesOrderItem.setStatus(Integer.valueOf(1));
/* 254 */       salesOrderItemManager.update(salesOrderItem);
/*     */       b++; }
/*     */     
/* 257 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 258 */     response.getWriter().print(jo);
/* 259 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward salesOrderItemOpen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 264 */     response.setContentType("text/json");
/* 266 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 268 */     SalesOrderItemManager salesOrderItemManager = ServiceLocator.getSalesOrderItemManager(request);
/* 269 */     String array = request.getParameter("arrays");
/* 270 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 271 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 272 */       SalesOrderItem salesOrderItem = salesOrderItemManager.getById(Integer.valueOf(Integer.parseInt(id)));
/* 273 */       salesOrderItem.setStatus(Integer.valueOf(0));
/* 274 */       salesOrderItemManager.update(salesOrderItem);
/*     */       b++; }
/*     */     
/* 277 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 278 */     response.getWriter().print(jo);
/* 279 */     return null;
/*     */   }
/*     */ }