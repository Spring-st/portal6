/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.TravelGroup;
/*     */ import com.aof.model.admin.TravelGroupDetail;
/*     */ import com.aof.model.admin.query.TravelGroupQueryCondition;
/*     */ import com.aof.model.admin.query.TravelGroupQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import com.aof.service.admin.ExpenseSubCategoryManager;
/*     */ import com.aof.service.admin.TravelGroupManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.TravelGroupQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TravelGroupAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  70 */     TravelGroupManager fm = null;
/*  71 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*  72 */     request.setAttribute("X_SITELIST", siteList);
/*     */     
/*  74 */     TravelGroupQueryForm queryForm = (TravelGroupQueryForm)form;
/*  75 */     if (queryForm.getSite_id() == null && 
/*  76 */       siteList.size() > 0) {
/*  77 */       queryForm.setSite_id(((Site)siteList.get(0)).getId()
/*  78 */           .toString());
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*     */       
/*  84 */       queryForm.setOrder(TravelGroupQueryOrder.NAME.getName());
/*  85 */       queryForm.setEnabled(EnabledDisabled.ENABLED.toString());
/*  86 */       queryForm.setDescend(false);
/*     */     } 
/*     */ 
/*     */     
/*  90 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  92 */     String exportType = queryForm.getExportType();
/*  93 */     if (StringUtils.isNotEmpty(exportType)) {
/*  94 */       List data = fm.getTravelGroupList(conditions, 0, -1, TravelGroupQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  96 */       int index = SessionTempFile.createNewTempFile(request);
/*  97 */       String fileName = "travelGroup";
/*  98 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 101 */               MessageResources messages = TravelGroupAction.this.getResources(request);
/* 102 */               row.add(messages.getMessage(TravelGroupAction.this.getLocale(request), "travelGroup.name"));
/* 103 */               row.add(messages.getMessage(TravelGroupAction.this.getLocale(request), "travelGroup.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 107 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/* 108 */               if (TravelGroupAction.this.isEnglish(request)) {
/* 109 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.engShortDescription"));
/*     */               } else {
/* 111 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */               }  }
/*     */           });
/* 114 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */ 
/*     */     
/* 118 */     if (queryForm.isFirstInit()) {
/*     */       
/* 120 */       queryForm.init(fm.getTravelGroupListCount(conditions));
/*     */     }
/*     */     else {
/*     */       
/* 124 */       queryForm.init();
/*     */     } 
/*     */     
/* 127 */     int pageNo = queryForm.getPageNoAsInt();
/* 128 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */ 
/*     */     
/* 131 */     List result = fm.getTravelGroupList(conditions, pageNo, pageSize, 
/* 132 */         TravelGroupQueryOrder.getEnum(queryForm.getOrder()), queryForm
/* 133 */         .isDescend());
/* 134 */     request.setAttribute("X_RESULTLIST", result);
/* 135 */     putEnumListToRequest(request);
/*     */     
/* 137 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(TravelGroupQueryForm queryForm) {
/* 141 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 143 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 144 */     if (id != null) {
/* 145 */       conditions.put(TravelGroupQueryCondition.ID_EQ, id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     Integer site_id = ActionUtils.parseInt(queryForm.getSite_id());
/* 154 */     if (site_id != null) {
/* 155 */       conditions.put(TravelGroupQueryCondition.SITE_ID_EQ, site_id);
/*     */     }
/*     */ 
/*     */     
/* 159 */     String name = queryForm.getName();
/* 160 */     if (name != null && name.trim().length() != 0) {
/* 161 */       conditions.put(TravelGroupQueryCondition.NAME_LIKE, name);
/*     */     }
/* 163 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 164 */     if (enabled != null) {
/* 165 */       conditions.put(TravelGroupQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/* 167 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 171 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 172 */         PersistentEnum.getEnumList(EnabledDisabled.class));
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 187 */     TravelGroup travelGroup = getTravelGroupFromRequest(request);
/* 188 */     Site site = travelGroup.getSite();
/* 189 */     checkSite(travelGroup.getSite(), request);
/* 190 */     request.setAttribute("x_travelGroup", travelGroup);
/*     */     
/* 192 */     ExpenseCategory ec = 
/* 193 */       getAndCheckEnabledTravelExpenseCategoryOfSite(site, request);
/*     */     
/* 195 */     request.setAttribute("x_ec", ec);
/*     */     
/* 197 */     if (!isBack(request)) {
/* 198 */       BeanForm travelGroupForm = (BeanForm)getForm(
/* 199 */           "/updateTravelGroup", request);
/* 200 */       travelGroupForm.populate(travelGroup, "to_form");
/*     */ 
/*     */       
/* 203 */       TravelGroupManager travelGroupManager = null;
/* 204 */       List tgdList = travelGroupManager.getDetailOf(travelGroup);
/* 205 */       Map<Object, Object> subs = new HashMap<Object, Object>();
/* 206 */       Map<Object, Object> abroadSubs = new HashMap<Object, Object>();
/* 207 */       for (Iterator<TravelGroupDetail> iter = tgdList.iterator(); iter.hasNext(); ) {
/* 208 */         TravelGroupDetail tgd = iter.next();
/* 209 */         subs.put(String.valueOf(tgd.getExpenseSubCategory().getId()), 
/* 210 */             (tgd.getAmountLimit() == null) ? "" : String.valueOf(tgd
/* 211 */               .getAmountLimit()));
/*     */         
/* 213 */         abroadSubs.put(String.valueOf(tgd.getExpenseSubCategory()
/* 214 */               .getId()), (tgd.getAbroadAmountLimit() == null) ? "" : 
/* 215 */             String.valueOf(tgd.getAbroadAmountLimit()));
/*     */       } 
/*     */       
/* 218 */       travelGroupForm.set("subs", subs);
/* 219 */       travelGroupForm.set("abroadSubs", abroadSubs);
/*     */     } 
/*     */ 
/*     */     
/* 223 */     putEnumListToRequest(request);
/*     */     
/* 225 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private TravelGroup getTravelGroupFromRequest(HttpServletRequest request) throws Exception {
/* 230 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/*     */ 
/*     */     
/* 233 */     TravelGroupManager travelGroupManager = null;
/* 234 */     TravelGroup travelGroup = travelGroupManager.getTravelGroup(id);
/* 235 */     if (travelGroup == null)
/* 236 */       throw new ActionException("travelGroup.notFound", id); 
/* 237 */     return travelGroup;
/*     */   }
/*     */   
/*     */   private ActionForward getForwardFor(TravelGroup travelGroup) {
/* 241 */     ActionForward forward = new ActionForward("listTravelGroup.do?site_id=" + 
/* 242 */         travelGroup.getSite().getId());
/* 243 */     forward.setRedirect(true);
/*     */     
/* 245 */     return forward;
/*     */   }
/*     */   
/*     */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/* 249 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */ 
/*     */   
/*     */   private ExpenseCategory getAndCheckEnabledTravelExpenseCategoryOfSite(Site site, HttpServletRequest request) throws Exception {
/* 254 */     ExpenseCategory ec = getEnabledTravelExpenseCategoryOfSite(site.getId()
/* 255 */         .intValue(), request);
/* 256 */     if (ec == null) {
/* 257 */       throw new ActionException(
/* 258 */           "travelGroup.error.noTravelExpenseCategoryOfSite", site
/* 259 */           .getName());
/*     */     }
/* 261 */     if (ec.getEnabledExpenseSubCategoryList().isEmpty()) {
/* 262 */       throw new ActionException(
/* 263 */           "travelGroup.error.noTravelExpenseSubCategoryOfSite", site
/* 264 */           .getName());
/*     */     }
/* 266 */     return ec;
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 282 */     Site site = getAndCheckSite(request);
/*     */     
/* 284 */     BeanForm travelGroupForm = (BeanForm)form;
/* 285 */     TravelGroup travelGroup = new TravelGroup();
/* 286 */     travelGroupForm.populate(travelGroup, "to_bean");
/* 287 */     travelGroup.setSite(site);
/*     */     
/* 289 */     ExpenseCategory ec = getAndCheckEnabledTravelExpenseCategoryOfSite(
/* 290 */         site, request);
/*     */     
/* 292 */     List<TravelGroupDetail> detailList = new ArrayList();
/*     */     
/* 294 */     List escList = ec.getEnabledExpenseSubCategoryList();
/* 295 */     for (Iterator<ExpenseSubCategory> iterator = escList.iterator(); iterator.hasNext(); ) {
/* 296 */       ExpenseSubCategory esc = iterator.next();
/* 297 */       TravelGroupDetail tgd = new TravelGroupDetail();
/* 298 */       tgd.setExpenseSubCategory(esc);
/* 299 */       tgd.setTravelGroup(travelGroup);
/* 300 */       tgd.setAmountLimit(null);
/* 301 */       tgd.setAbroadAmountLimit(null);
/*     */ 
/*     */       
/* 304 */       String sAmount = (String)travelGroupForm.get("subs", 
/* 305 */           String.valueOf(esc.getId()));
/* 306 */       if (StringUtils.isNotEmpty(sAmount)) {
/* 307 */         tgd.setAmountLimit(new BigDecimal(sAmount));
/*     */       }
/*     */ 
/*     */       
/* 311 */       String sAbroadAmount = (String)travelGroupForm.get(
/* 312 */           "abroadSubs", String.valueOf(esc.getId()));
/* 313 */       if (StringUtils.isNotEmpty(sAbroadAmount)) {
/* 314 */         tgd.setAbroadAmountLimit(new BigDecimal(sAbroadAmount));
/*     */       }
/*     */       
/* 317 */       detailList.add(tgd);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     TravelGroupManager travelGroupManager = null;
/* 324 */     travelGroupManager.updateTravelGroup(travelGroup, detailList);
/*     */     
/* 326 */     return getForwardFor(travelGroup);
/*     */   }
/*     */ 
/*     */   
/*     */   private ExpenseCategory getEnabledTravelExpenseCategoryOfSite(int site_id, HttpServletRequest request) throws Exception {
/* 331 */     ExpenseCategoryManager fm = 
/* 332 */       ServiceLocator.getExpenseCategoryManager(request);
/* 333 */     ExpenseSubCategoryManager ecm = 
/* 334 */       ServiceLocator.getExpenseSubCategoryManager(request);
/* 335 */     ExpenseCategory ec = fm.getEnabledTravelExpenseCategoryOfSite(site_id);
/* 336 */     if (ec != null) {
/* 337 */       ec.setEnabledExpenseSubCategoryList(ecm
/* 338 */           .getEnabledChildrenOfExpenseCategory(ec.getId()));
/*     */     }
/* 340 */     return ec;
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
/* 355 */     Site site = getAndCheckSite(request);
/* 356 */     request.setAttribute("x_site", site);
/*     */     
/* 358 */     ExpenseCategory ec = 
/* 359 */       getAndCheckEnabledTravelExpenseCategoryOfSite(site, request);
/*     */     
/* 361 */     request.setAttribute("x_ec", ec);
/*     */     
/* 363 */     if (!isBack(request)) {
/* 364 */       TravelGroup travelGroup = new TravelGroup();
/* 365 */       travelGroup.setSite(site);
/*     */       
/* 367 */       BeanForm travelGroupForm = (BeanForm)getForm(
/* 368 */           "/insertTravelGroup", request);
/*     */       
/* 370 */       travelGroupForm.populate(travelGroup, "to_form");
/*     */     } 
/*     */     
/* 373 */     putEnumListToRequest(request);
/* 374 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 389 */     Site site = getAndCheckSite(request);
/*     */     
/* 391 */     BeanForm travelGroupForm = (BeanForm)form;
/* 392 */     TravelGroup travelGroup = new TravelGroup();
/* 393 */     travelGroupForm.populate(travelGroup, "to_bean");
/* 394 */     travelGroup.setSite(site);
/*     */     
/* 396 */     ExpenseCategory ec = 
/* 397 */       getAndCheckEnabledTravelExpenseCategoryOfSite(site, request);
/*     */     
/* 399 */     List<TravelGroupDetail> detailList = new ArrayList();
/*     */     
/* 401 */     List escList = ec.getEnabledExpenseSubCategoryList();
/* 402 */     for (Iterator<ExpenseSubCategory> iterator = escList.iterator(); iterator.hasNext(); ) {
/* 403 */       ExpenseSubCategory esc = iterator.next();
/* 404 */       TravelGroupDetail tgd = new TravelGroupDetail();
/* 405 */       tgd.setExpenseSubCategory(esc);
/* 406 */       tgd.setTravelGroup(travelGroup);
/* 407 */       tgd.setAmountLimit(null);
/* 408 */       tgd.setAbroadAmountLimit(null);
/*     */       
/* 410 */       String sAmount = (String)travelGroupForm.get("subs", 
/* 411 */           String.valueOf(esc.getId()));
/* 412 */       if (StringUtils.isNotEmpty(sAmount)) {
/* 413 */         tgd.setAmountLimit(new BigDecimal(sAmount));
/*     */       }
/*     */ 
/*     */       
/* 417 */       String sAbroadAmount = (String)travelGroupForm.get(
/* 418 */           "abroadSubs", String.valueOf(esc.getId()));
/* 419 */       if (StringUtils.isNotEmpty(sAbroadAmount)) {
/* 420 */         tgd.setAbroadAmountLimit(new BigDecimal(sAbroadAmount));
/*     */       }
/*     */       
/* 423 */       detailList.add(tgd);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 428 */     TravelGroupManager cm = null;
/* 429 */     cm.insertTravelGroup(travelGroup, detailList);
/*     */     
/* 431 */     return getForwardFor(travelGroup);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/TravelGroupAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */