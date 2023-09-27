/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.PurchaseCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.PurchaseCategoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.PurchaseCategoryQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ public class PurchaseCategoryAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  59 */     List<Site> siteList = new ArrayList();
/*  60 */     if (isSite(request)) {
/*  61 */       siteList = getAndCheckGrantedSiteList(request);
/*     */     }
/*  63 */     PurchaseCategoryManager pcm = ServiceLocator.getPurchaseCategoryManager(request);
/*  64 */     PurchaseCategoryQueryForm queryForm = (PurchaseCategoryQueryForm)form;
/*  65 */     if (isSite(request)) {
/*  66 */       if (queryForm.getSiteId() == null) {
/*  67 */         queryForm.setSiteId(((Site)siteList.get(0)).getId().toString());
/*     */       }
/*     */     } else {
/*  70 */       queryForm.setSiteId(null);
/*     */     } 
/*  72 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  74 */     String exportType = queryForm.getExportType();
/*  75 */     if (exportType != null && exportType.length() > 0) {
/*  76 */       List data = pcm.getPurchaseCategoryList(conditions, 0, -1, PurchaseCategoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  78 */       int index = SessionTempFile.createNewTempFile(request);
/*  79 */       String fileName = "purchaseCategory";
/*  80 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  83 */               MessageResources messages = PurchaseCategoryAction.this.getResources(request);
/*  84 */               row.add(messages.getMessage(PurchaseCategoryAction.this.getLocale(request), "purchaseCategory.id"));
/*  85 */               row.add(messages.getMessage(PurchaseCategoryAction.this.getLocale(request), "purchaseCategory.description"));
/*  86 */               row.add(messages.getMessage(PurchaseCategoryAction.this.getLocale(request), "purchaseCategory.status"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  90 */               row.add(BeanUtils.getProperty(data, "id"));
/*  91 */               row.add(BeanUtils.getProperty(data, "description"));
/*  92 */               String locale = PurchaseCategoryAction.this.getCurrentUser(request).getLocale();
/*  93 */               if ("en".equals(locale)) {
/*  94 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/*  96 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 100 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 103 */     if (queryForm.isFirstInit()) {
/*     */       
/* 105 */       queryForm.init(pcm.getPurchaseCategoryListCount(conditions));
/*     */     }
/*     */     else {
/*     */       
/* 109 */       queryForm.init();
/*     */     } 
/* 111 */     int pageNo = queryForm.getPageNoAsInt();
/* 112 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 114 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 115 */     request.setAttribute("X_SITELIST", siteList);
/* 116 */     request.setAttribute("X_RESULTLIST", pcm.getPurchaseCategoryList(conditions, pageNo, pageSize, PurchaseCategoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(PurchaseCategoryQueryForm queryForm) {
/* 126 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 128 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 129 */     if (id != null)
/* 130 */       conditions.put(PurchaseCategoryQueryCondition.ID_EQ, id); 
/* 131 */     Integer site_id = ActionUtils.parseInt(queryForm.getSiteId());
/* 132 */     if (site_id != null) {
/*     */       
/* 134 */       conditions.put(PurchaseCategoryQueryCondition.SITE_ID_EQ, site_id);
/*     */     } else {
/* 136 */       conditions.put(PurchaseCategoryQueryCondition.GLOBAL, null);
/*     */     } 
/* 138 */     String description = queryForm.getDescription();
/* 139 */     if (description != null && description.trim().length() != 0)
/*     */     {
/* 141 */       conditions.put(PurchaseCategoryQueryCondition.DESCRIPTION_LIKE, description.trim());
/*     */     }
/* 143 */     String status = queryForm.getStatus();
/* 144 */     if (status != null && status.trim().length() != 0) {
/* 145 */       if (status.equals(EnabledDisabled.ENABLED.getEnumCode().toString()))
/* 146 */         conditions.put(PurchaseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode()); 
/* 147 */       if (status.equals(EnabledDisabled.DISABLED.getEnumCode().toString()))
/* 148 */         conditions.put(PurchaseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.DISABLED.getEnumCode()); 
/*     */     } 
/* 150 */     return conditions;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 163 */     if (!isBack(request)) {
/* 164 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 165 */       PurchaseCategoryManager purchaseCategoryManager = ServiceLocator.getPurchaseCategoryManager(request);
/* 166 */       PurchaseCategory purchaseCategory = purchaseCategoryManager.getPurchaseCategory(id);
/* 167 */       if (purchaseCategory == null) throw new ActionException("purchaseCategory.notFound", id); 
/* 168 */       BeanForm purchaseCategoryForm = (BeanForm)getForm("/updatePurchaseCategory", request);
/* 169 */       purchaseCategoryForm.populateToForm(purchaseCategory);
/*     */     } 
/* 171 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 172 */     return mapping.findForward("page");
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
/* 185 */     PurchaseCategory purchaseCategory = getPurchaseCategoryFromRequest(request);
/* 186 */     checkPurchaseCategory(purchaseCategory, request);
/*     */     
/* 188 */     BeanForm purchaseCategoryForm = (BeanForm)form;
/* 189 */     purchaseCategoryForm.populateToBean(purchaseCategory, request);
/* 190 */     PurchaseCategoryManager purchaseCategoryManager = ServiceLocator.getPurchaseCategoryManager(request);
/* 191 */     request.setAttribute("X_OBJECT", purchaseCategoryManager.updatePurchaseCategory(purchaseCategory));
/* 192 */     request.setAttribute("X_ROWPAGE", "purchaseCategory/row.jsp");
/* 193 */     return mapping.findForward("success");
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 208 */     Site site = getAndCheckSite(request);
/* 209 */     if (!isBack(request)) {
/* 210 */       PurchaseCategory purchaseCategory = new PurchaseCategory();
/* 211 */       purchaseCategory.setSite(site);
/* 212 */       BeanForm purchaseCategoryForm = (BeanForm)getForm("/insertPurchaseCategory", request);
/* 213 */       purchaseCategoryForm.populateToForm(purchaseCategory);
/*     */     } 
/* 215 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 216 */     return mapping.findForward("page");
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
/* 229 */     Site site = getAndCheckSite(request);
/* 230 */     PurchaseCategoryManager cm = ServiceLocator.getPurchaseCategoryManager(request);
/* 231 */     BeanForm purchaseCategoryForm = (BeanForm)form;
/* 232 */     PurchaseCategory purchaseCategory = new PurchaseCategory();
/* 233 */     purchaseCategoryForm.populateToBean(purchaseCategory, request);
/* 234 */     purchaseCategory.setSite(site);
/* 235 */     request.setAttribute("X_OBJECT", cm.insertPurchaseCategory(purchaseCategory));
/* 236 */     request.setAttribute("X_ROWPAGE", "purchaseCategory/row.jsp");
/* 237 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private void checkPurchaseCategory(PurchaseCategory purchaseCategory, HttpServletRequest request) throws Exception {
/* 241 */     if (isSite(request)) {
/* 242 */       if (isGlobalPurchaseCategory(purchaseCategory)) {
/* 243 */         throw new ActionException("purchaseCategory.error.siteEditGlobal");
/*     */       }
/* 245 */       checkSite(purchaseCategory.getSite(), request);
/*     */     } 
/*     */     
/* 248 */     if (isGlobal(request) && 
/* 249 */       !isGlobalPurchaseCategory(purchaseCategory)) {
/* 250 */       throw new ActionException("purchaseCategory.error.globalEditSite");
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isGlobalPurchaseCategory(PurchaseCategory purchaseCategory) {
/* 255 */     return (purchaseCategory.getSite() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/* 261 */     if (hasSite(request)) {
/* 262 */       if (isGlobal(request)) {
/* 263 */         throw new ActionException("purchaseCategory.error.globalNewSite");
/*     */       }
/* 265 */       return getAndCheckSite("site_id", request);
/*     */     } 
/*     */     
/* 268 */     if (isGlobal(request)) {
/* 269 */       return null;
/*     */     }
/* 271 */     throw new ActionException("purchaseCategory.error.siteNewGlobal");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 277 */     return !StringUtils.isEmpty(request.getParameter("site_id"));
/*     */   }
/*     */   
/*     */   private PurchaseCategory getPurchaseCategoryFromRequest(HttpServletRequest request) {
/* 281 */     Integer id = new Integer(request.getParameter("id"));
/* 282 */     PurchaseCategoryManager purchaseCategoryManager = ServiceLocator.getPurchaseCategoryManager(request);
/* 283 */     PurchaseCategory purchaseCategory = purchaseCategoryManager.getPurchaseCategory(id);
/* 284 */     if (purchaseCategory == null)
/* 285 */       throw new ActionException("purchaseCategory.notFound", id); 
/* 286 */     return purchaseCategory;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/PurchaseCategoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */