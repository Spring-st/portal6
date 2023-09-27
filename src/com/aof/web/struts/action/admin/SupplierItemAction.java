/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierItem;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.SupplierItemQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierItemQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.service.admin.PurchaseCategoryManager;
/*     */ import com.aof.service.admin.SupplierItemManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.SupplierItemQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
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
/*     */ public class SupplierItemAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  66 */     Supplier supplier = getSupplierFromRequest(request);
/*     */     
/*  68 */     request.setAttribute("x_supplier", supplier);
/*  69 */     SupplierItemQueryForm queryForm = (SupplierItemQueryForm)form;
/*  70 */     request.setAttribute("x_back", queryForm.getBackPage());
/*  71 */     request.setAttribute("x_fromPO", queryForm.getFromPO());
/*  72 */     queryForm.setSupplier_id(supplier.getId().toString());
/*  73 */     SupplierItemManager sm = ServiceLocator.getSupplierItemManager(request);
/*  74 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  75 */     if (request.getMethod().equals("POST")) {
/*  76 */       conditions = constructQueryMap(queryForm);
/*     */       
/*  78 */       if (queryForm.isFirstInit()) {
/*     */         
/*  80 */         queryForm.init(sm.getSupplierItemListCount(conditions));
/*     */       }
/*     */       else {
/*     */         
/*  84 */         queryForm.init();
/*     */       } 
/*  86 */       int pageNo = queryForm.getPageNoAsInt();
/*  87 */       int pageSize = queryForm.getPageSizeAsInt();
/*  88 */       request.setAttribute("X_RESULTLIST", sm.getSupplierItemList(conditions, pageNo, pageSize, SupplierItemQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/*     */     }
/*  90 */     else if (queryForm.isFirstInit()) {
/*     */       
/*  92 */       queryForm.init(0);
/*     */     }
/*     */     else {
/*     */       
/*  96 */       queryForm.init();
/*     */     } 
/*     */ 
/*     */     
/* 100 */     String exportType = queryForm.getExportType();
/* 101 */     if (exportType != null && exportType.length() > 0) {
/*     */ 
/*     */       
/* 104 */       List data = sm.getSupplierItemList(conditions, 0, -1, SupplierItemQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 106 */       int index = SessionTempFile.createNewTempFile(request);
/* 107 */       String fileName = "supplierItem";
/* 108 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 111 */               MessageResources messages = SupplierItemAction.this.getResources(request);
/* 112 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.purchaseCategory"));
/* 113 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.purchaseSubCategory"));
/* 114 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.sepc"));
/* 115 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.unitPrice"));
/* 116 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.currency.code"));
/* 117 */               row.add(messages.getMessage(SupplierItemAction.this.getLocale(request), "supplierItem.status"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 121 */               row.add(BeanUtils.getProperty(data, "purchaseSubCategory.purchaseCategory.description"));
/* 122 */               row.add(BeanUtils.getProperty(data, "purchaseSubCategory.description"));
/* 123 */               row.add(BeanUtils.getProperty(data, "sepc"));
/* 124 */               row.add(BeanUtils.getProperty(data, "unitPrice"));
/* 125 */               row.add(BeanUtils.getProperty(data, "currency.name"));
/* 126 */               String locale = SupplierItemAction.this.getCurrentUser(request).getLocale();
/* 127 */               if ("en".equals(locale)) {
/* 128 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/* 130 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 134 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 136 */     putEnabledPurchaseCategoryToRequest(request, supplier.getSite());
/* 137 */     if (queryForm.getFromPO().equals("false")) {
/* 138 */       return mapping.findForward("page");
/*     */     }
/* 140 */     return mapping.findForward("dialogPage");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(SupplierItemQueryForm queryForm) {
/* 144 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 146 */     Integer supplierId = ActionUtils.parseInt(queryForm.getSupplier_id());
/* 147 */     if (supplierId != null) {
/* 148 */       conditions.put(SupplierItemQueryCondition.SUPPLIER_ID_EQ, supplierId);
/*     */     }
/* 150 */     Integer purchaseCategoryId = ActionUtils.parseInt(queryForm.getPurchaseCategory_id());
/* 151 */     if (purchaseCategoryId != null) {
/* 152 */       conditions.put(SupplierItemQueryCondition.PURCHASECATEGORY_ID_EQ, purchaseCategoryId);
/*     */     }
/* 154 */     Integer purchaseSubCategoryId = ActionUtils.parseInt(queryForm.getPurchaseSubCategory_id());
/* 155 */     if (purchaseSubCategoryId != null) {
/* 156 */       conditions.put(SupplierItemQueryCondition.PURCHASESUBCATEGORY_ID_EQ, purchaseSubCategoryId);
/*     */     }
/* 158 */     return conditions;
/*     */   }
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 171 */     Supplier supplier = getSupplierFromRequest(request);
/* 172 */     request.setAttribute("x_supplier", supplier);
/* 173 */     checkSupplier(supplier, request);
/* 174 */     if (!isBack(request)) {
/* 175 */       SupplierItem supplierItem = new SupplierItem();
/* 176 */       supplierItem.setSupplier(supplier);
/* 177 */       supplierItem.setCurrency(supplier.getCurrency());
/* 178 */       supplierItem.setUnitPrice(new BigDecimal(0.0D));
/*     */       
/* 180 */       PurchaseSubCategory p = new PurchaseSubCategory();
/* 181 */       Integer id = ActionUtils.parseInt(request.getParameter("category"));
/* 182 */       if (id != null)
/* 183 */         p.setPurchaseCategory(new PurchaseCategory(id)); 
/* 184 */       id = ActionUtils.parseInt(request.getParameter("subcategory"));
/* 185 */       if (id != null)
/* 186 */         p.setId(id); 
/* 187 */       supplierItem.setPurchaseSubCategory(p);
/*     */       
/* 189 */       BeanForm supplierItemForm = (BeanForm)getForm("/insertSupplierItem", request);
/* 190 */       supplierItemForm.populateToForm(supplierItem);
/*     */     } 
/* 192 */     putEnumListToRequest(request);
/* 193 */     putCurrencyListToRequest(request);
/* 194 */     putEnabledPurchaseCategoryToRequest(request, supplier.getSite());
/* 195 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 199 */     SupplierItem supplierItem = getSupplierItemFromRequest(request);
/* 200 */     request.setAttribute("x_supplierItem", supplierItem);
/* 201 */     request.setAttribute("x_supplier", supplierItem.getSupplier());
/* 202 */     if (!isBack(request)) {
/* 203 */       BeanForm supplierItemForm = (BeanForm)getForm("/updateSupplierItem", request);
/* 204 */       supplierItemForm.populateToForm(supplierItem);
/*     */     } 
/* 206 */     putEnumListToRequest(request);
/* 207 */     putCurrencyListToRequest(request);
/* 208 */     putEnabledPurchaseCategoryToRequest(request, supplierItem.getSupplier().getSite());
/* 209 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) throws Exception {
/* 213 */     request.setAttribute("x_enabledDisabledList", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */   
/*     */   private void putCurrencyListToRequest(HttpServletRequest request) throws Exception {
/* 217 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 218 */     request.setAttribute("x_currencyList", cm.getAllEnabledCurrencyList());
/*     */   }
/*     */   
/*     */   private void putEnabledPurchaseCategoryToRequest(HttpServletRequest request, Site site) throws Exception {
/* 222 */     PurchaseCategoryManager pcm = ServiceLocator.getPurchaseCategoryManager(request);
/* 223 */     if (isSite(request) && site != null) {
/* 224 */       request.setAttribute("x_purchaseCategoryList", pcm.getEnabledPurchaseCategorySubCategoryOfSiteIncludeGlobal(site));
/*     */     } else {
/* 226 */       request.setAttribute("x_purchaseCategoryList", pcm.getEnabledPurchaseCategorySubCategoryOfGlobal());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkSupplier(Supplier supplier, HttpServletRequest request) throws Exception {
/* 232 */     if (isSite(request)) {
/*     */       
/* 234 */       if (supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL))
/*     */       {
/* 236 */         throw new ActionException("supplier.error.siteEditGlobal");
/*     */       }
/*     */ 
/*     */       
/* 240 */       checkSite(supplier.getSite(), request);
/*     */     } 
/*     */     
/* 243 */     if (isGlobal(request) && 
/* 244 */       !supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL)) {
/* 245 */       throw new ActionException("supplier.error.globalEditSite");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private SupplierItem getSupplierItemFromRequest(HttpServletRequest request) throws Exception {
/* 252 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 253 */     SupplierItemManager supplierItemManager = ServiceLocator.getSupplierItemManager(request);
/* 254 */     SupplierItem supplierItem = supplierItemManager.getSupplierItem(id);
/* 255 */     if (supplierItem == null)
/* 256 */       throw new ActionException("supplierItem.notFound", id); 
/* 257 */     return supplierItem;
/*     */   }
/*     */   
/*     */   private Supplier getSupplierFromRequest(HttpServletRequest request) throws Exception {
/* 261 */     Integer id = new Integer(request.getParameter("supplier_id"));
/* 262 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/* 263 */     Supplier supplier = supplierManager.getSupplier(id);
/* 264 */     if (supplier == null)
/* 265 */       throw new ActionException("supplier.notFound", id); 
/* 266 */     return supplier;
/*     */   }
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 279 */     BeanForm supplierItemForm = (BeanForm)form;
/* 280 */     SupplierItem supplierItem = getSupplierItemFromRequest(request);
/*     */     
/* 282 */     checkSupplier(supplierItem.getSupplier(), request);
/*     */     
/* 284 */     supplierItemForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 285 */     supplierItemForm.populateToBean(supplierItem, request);
/*     */     
/* 287 */     SupplierItemManager sm = ServiceLocator.getSupplierItemManager(request);
/* 288 */     request.setAttribute("X_OBJECT", sm.updateSupplierItem(supplierItem));
/* 289 */     request.setAttribute("X_ROWPAGE", "supplierItem/row.jsp");
/* 290 */     request.setAttribute("modifyable", "true");
/* 291 */     return mapping.findForward("success");
/*     */   }
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 305 */     BeanForm supplierItemForm = (BeanForm)form;
/* 306 */     SupplierItem supplierItem = new SupplierItem();
/* 307 */     supplierItemForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 308 */     supplierItemForm.populateToBean(supplierItem, request);
/*     */     
/* 310 */     checkSupplier(supplierItem.getSupplier(), request);
/*     */     
/* 312 */     SupplierItemManager sm = ServiceLocator.getSupplierItemManager(request);
/* 313 */     request.setAttribute("X_OBJECT", sm.insertSupplierItem(supplierItem));
/* 314 */     request.setAttribute("X_ROWPAGE", "supplierItem/row.jsp");
/* 315 */     request.setAttribute("modifyable", "true");
/* 316 */     return mapping.findForward("success");
/*     */   }
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 331 */     SupplierItem supplierItem = getSupplierItemFromRequest(request);
/* 332 */     checkSupplier(supplierItem.getSupplier(), request);
/*     */     
/* 334 */     SupplierItemManager sm = ServiceLocator.getSupplierItemManager(request);
/* 335 */     sm.removeSupplierItem(supplierItem.getId());
/*     */     
/* 337 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/SupplierItemAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */