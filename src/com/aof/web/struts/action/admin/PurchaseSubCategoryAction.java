/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.PurchaseCategoryManager;
/*     */ import com.aof.service.admin.PurchaseSubCategoryManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.PurchaseSubCategoryQueryForm;
/*     */ import com.aof.web.struts.form.admin.UserQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
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
/*     */ 
/*     */ 
/*     */ public class PurchaseSubCategoryAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  65 */     PurchaseSubCategoryQueryForm queryForm = (PurchaseSubCategoryQueryForm)form;
/*  66 */     PurchaseCategoryManager pcm = ServiceLocator.getPurchaseCategoryManager(request);
/*  67 */     PurchaseCategory purchaseCategory = pcm.getPurchaseCategory(new Integer(queryForm.getPurchaseCategory_id()));
/*  68 */     if (purchaseCategory == null) throw new ActionException("purchaseCategory.notFound", queryForm.getPurchaseCategory_id()); 
/*  69 */     request.setAttribute("X_PURCHASECATEGORY", purchaseCategory);
/*     */     
/*  71 */     PurchaseSubCategoryManager fm = ServiceLocator.getPurchaseSubCategoryManager(request);
/*  72 */     Map conditions = constructQueryMap(queryForm);
/*  73 */     queryForm.setPageSize("5");
/*  74 */     if (queryForm.isFirstInit()) {
/*     */       
/*  76 */       queryForm.init(fm.getPurchaseSubCategoryListCount(conditions), queryForm.getPageSizeAsInt());
/*     */     }
/*     */     else {
/*     */       
/*  80 */       queryForm.init();
/*     */     } 
/*  82 */     int pageNo = queryForm.getPageNoAsInt();
/*  83 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/*  85 */     List result = fm.getPurchaseSubCategoryList(
/*  86 */         conditions, pageNo, pageSize, PurchaseSubCategoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  87 */     request.setAttribute("X_RESULTLIST", result);
/*  88 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward selectInspector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  93 */     UserQueryForm queryForm = (UserQueryForm)form;
/*     */     
/*  95 */     UserManager um = ServiceLocator.getUserManager(request);
/*     */     
/*  97 */     Map conditions = constructUserQueryMap(queryForm);
/*     */     
/*  99 */     String exportType = queryForm.getExportType();
/* 100 */     if (exportType != null && exportType.length() > 0) {
/* 101 */       List data = um.getUserList(conditions, 0, -1, UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 102 */       int index = SessionTempFile.createNewTempFile(request);
/* 103 */       String fileName = "user";
/* 104 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 107 */               MessageResources messages = PurchaseSubCategoryAction.this.getResources(request);
/* 108 */               row.add(messages.getMessage(PurchaseSubCategoryAction.this.getLocale(request), "user.loginName"));
/* 109 */               row.add(messages.getMessage(PurchaseSubCategoryAction.this.getLocale(request), "user.name"));
/* 110 */               row.add(messages.getMessage(PurchaseSubCategoryAction.this.getLocale(request), "user.email"));
/* 111 */               row.add(messages.getMessage(PurchaseSubCategoryAction.this.getLocale(request), "user.telephone"));
/* 112 */               row.add(messages.getMessage(PurchaseSubCategoryAction.this.getLocale(request), "user.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 116 */               row.add(BeanUtils.getProperty(data, "loginName"));
/* 117 */               row.add(BeanUtils.getProperty(data, "name"));
/* 118 */               row.add(BeanUtils.getProperty(data, "email"));
/* 119 */               row.add(BeanUtils.getProperty(data, "telephone"));
/* 120 */               String locale = PurchaseSubCategoryAction.this.getCurrentUser(request).getLocale();
/* 121 */               if ("en".equals(locale)) {
/* 122 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/* 124 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 128 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 131 */     if (queryForm.isFirstInit()) {
/* 132 */       queryForm.init(um.getUserListCount(conditions));
/*     */     } else {
/* 134 */       queryForm.init();
/*     */     } 
/*     */     
/* 137 */     request.setAttribute("X_RESULTLIST", um.getUserList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 138 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(PurchaseSubCategoryQueryForm queryForm) {
/* 149 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 150 */     Integer purchaseCategory_id = 
/* 151 */       ActionUtils.parseInt(queryForm.getPurchaseCategory_id());
/* 152 */     if (purchaseCategory_id != null)
/*     */     {
/* 154 */       conditions.put(PurchaseSubCategoryQueryCondition.PURCHASECATEGORY_ID_EQ, 
/* 155 */           purchaseCategory_id);
/*     */     }
/* 157 */     String defaultSupplier_id = 
/* 158 */       queryForm.getDefaultSupplier_id();
/* 159 */     if (defaultSupplier_id != null && defaultSupplier_id.trim().length() != 0)
/*     */     {
/* 161 */       conditions.put(PurchaseSubCategoryQueryCondition.DEFAULTSUPPLIER_ID_EQ, 
/* 162 */           defaultSupplier_id);
/*     */     }
/* 164 */     String description = 
/* 165 */       queryForm.getDescription();
/* 166 */     if (description != null && description.trim().length() != 0)
/*     */     {
/* 168 */       conditions.put(PurchaseSubCategoryQueryCondition.DESCRIPTION_LIKE, 
/* 169 */           description);
/*     */     }
/* 171 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructUserQueryMap(UserQueryForm queryForm) {
/* 177 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 179 */     String loginName = queryForm.getLoginName();
/* 180 */     if (loginName != null) {
/* 181 */       loginName = loginName.trim();
/* 182 */       if (loginName.length() == 0) loginName = null; 
/*     */     } 
/* 184 */     if (loginName != null) conditions.put(UserQueryCondition.LOGINNAME_LIKE, loginName);
/*     */     
/* 186 */     String name = queryForm.getName();
/* 187 */     if (name != null) {
/* 188 */       name = name.trim();
/* 189 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 191 */     if (name != null) conditions.put(UserQueryCondition.NAME_LIKE, name);
/*     */     
/* 193 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 194 */     conditions.put(UserQueryCondition.SITE_ID_EQ, siteId);
/* 195 */     conditions.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/*     */     
/* 197 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 205 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 206 */         PersistentEnum.getEnumList(EnabledDisabled.class));
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
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 222 */     PurchaseSubCategory purchaseSubCategory = getPurchaseSubCategoryFromRequest(request);
/* 223 */     checkPurchaseCategory(purchaseSubCategory.getPurchaseCategory(), request, false);
/*     */     
/* 225 */     request.setAttribute("x_purchaseSubCategory", purchaseSubCategory);
/* 226 */     if (isSite(request))
/* 227 */       request.setAttribute("x_site_id", purchaseSubCategory.getPurchaseCategory().getSite().getId()); 
/* 228 */     if (!isBack(request)) {
/* 229 */       BeanForm purchaseSubCategoryForm = (BeanForm)getForm("/updatePurchaseSubCategory", request);
/* 230 */       purchaseSubCategoryForm.populateToForm(purchaseSubCategory);
/*     */     } 
/* 232 */     putEnumListToRequest(request);
/*     */     
/* 234 */     return mapping.findForward("page");
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
/* 247 */     PurchaseCategory purchaseCategory = getPurchaseCategoryFromRequest(request);
/* 248 */     checkPurchaseCategory(purchaseCategory, request, true);
/* 249 */     if (!isBack(request)) {
/* 250 */       if (isSite(request))
/* 251 */         request.setAttribute("x_site_id", purchaseCategory.getSite().getId()); 
/* 252 */       PurchaseSubCategory purchaseSubCategory = new PurchaseSubCategory();
/* 253 */       purchaseSubCategory.setPurchaseCategory(purchaseCategory);
/* 254 */       BeanForm purchaseSubCategoryForm = (BeanForm)getForm("/insertPurchaseSubCategory", request);
/* 255 */       purchaseSubCategoryForm.populateToForm(purchaseSubCategory);
/*     */     } 
/* 257 */     putEnumListToRequest(request);
/* 258 */     return mapping.findForward("page");
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
/* 271 */     PurchaseSubCategory purchaseSubCategory = getPurchaseSubCategoryFromRequest(request);
/* 272 */     checkPurchaseCategory(purchaseSubCategory.getPurchaseCategory(), request, false);
/* 273 */     BeanForm purchaseSubCategoryForm = (BeanForm)form;
/* 274 */     purchaseSubCategoryForm.populateToBean(purchaseSubCategory, request);
/* 275 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/* 276 */     purchaseSubCategory.setDefaultSupplier(sm.getSupplier(ActionUtils.parseInt((String)purchaseSubCategoryForm.get("defaultSupplier_id"))));
/* 277 */     PurchaseSubCategoryManager purchaseSubCategoryManager = ServiceLocator.getPurchaseSubCategoryManager(request);
/* 278 */     request.setAttribute("X_OBJECT", purchaseSubCategoryManager.updatePurchaseSubCategory(purchaseSubCategory));
/* 279 */     request.setAttribute("X_ROWPAGE", "purchaseSubCategory/row.jsp");
/* 280 */     return mapping.findForward("success");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 293 */     PurchaseCategory purchaseCategory = getPurchaseCategoryFromRequest(request);
/* 294 */     checkPurchaseCategory(purchaseCategory, request, true);
/*     */     
/* 296 */     BeanForm purchaseSubCategoryForm = (BeanForm)form;
/* 297 */     PurchaseSubCategory purchaseSubCategory = new PurchaseSubCategory();
/* 298 */     purchaseSubCategoryForm.populateToBean(purchaseSubCategory, request);
/* 299 */     purchaseSubCategory.setPurchaseCategory(purchaseCategory);
/*     */     
/* 301 */     SupplierManager sm = ServiceLocator.getSupplierManager(request);
/* 302 */     purchaseSubCategory.setDefaultSupplier(sm.getSupplier(ActionUtils.parseInt((String)purchaseSubCategoryForm.get("defaultSupplier_id"))));
/*     */     
/* 304 */     PurchaseSubCategoryManager purchaseSubCategoryManager = ServiceLocator.getPurchaseSubCategoryManager(request);
/* 305 */     request.setAttribute("X_OBJECT", purchaseSubCategoryManager.insertPurchaseSubCategory(purchaseSubCategory));
/* 306 */     request.setAttribute("X_ROWPAGE", "purchaseSubCategory/row.jsp");
/*     */     
/* 308 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PurchaseSubCategory getPurchaseSubCategoryFromRequest(HttpServletRequest request) throws Exception {
/* 319 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 320 */     PurchaseSubCategoryManager purchaseSubCategoryManager = ServiceLocator.getPurchaseSubCategoryManager(request);
/* 321 */     PurchaseSubCategory purchaseSubCategory = purchaseSubCategoryManager.getPurchaseSubCategory(id);
/* 322 */     if (purchaseSubCategory == null)
/* 323 */       throw new ActionException("purchaseSubCategory.notFound", id); 
/* 324 */     return purchaseSubCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PurchaseCategory getPurchaseCategoryFromRequest(HttpServletRequest request) {
/* 334 */     Integer id = new Integer(request.getParameter("purchaseCategory_id"));
/* 335 */     PurchaseCategoryManager purchaseCategoryManager = ServiceLocator.getPurchaseCategoryManager(request);
/* 336 */     PurchaseCategory purchaseCategory = purchaseCategoryManager.getPurchaseCategory(id);
/* 337 */     if (purchaseCategory == null)
/* 338 */       throw new ActionException("purchaseCategory.notFound", id); 
/* 339 */     return purchaseCategory;
/*     */   }
/*     */   
/*     */   private void checkPurchaseCategory(PurchaseCategory purchaseCategory, HttpServletRequest request, boolean isNew) throws Exception {
/* 343 */     if (isSite(request)) {
/* 344 */       if (isGlobalPurchaseCategory(purchaseCategory)) {
/* 345 */         if (isNew) {
/* 346 */           throw new ActionException("purchaseSubCategory.error.siteNewGlobal");
/*     */         }
/* 348 */         throw new ActionException("purchaseSubCategory.error.siteEditGlobal");
/*     */       } 
/* 350 */       checkSite(purchaseCategory.getSite(), request);
/*     */     } 
/*     */     
/* 353 */     if (isGlobal(request) && 
/* 354 */       !isGlobalPurchaseCategory(purchaseCategory)) {
/* 355 */       if (isNew) {
/* 356 */         throw new ActionException("purchaseSubCategory.error.globalNewSite");
/*     */       }
/* 358 */       throw new ActionException("purchaseSubCategory.error.globalEditSite");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isGlobalPurchaseCategory(PurchaseCategory purchaseCategory) {
/* 363 */     return (purchaseCategory.getSite() == null);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/PurchaseSubCategoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */