/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.CountryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.CountryManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
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
/*     */ public class CountryAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward promote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  57 */     Country country = getCountryFromRequest(request);
/*  58 */     if (country.getSite() == null) {
/*  59 */       throw new ActionException("country.error.alreadyPromoted", country.getEngName());
/*     */     }
/*     */     
/*  62 */     if (request.getParameter("confirm") != null) {
/*  63 */       CountryManager cm = ServiceLocator.getCountryManager(request);
/*  64 */       cm.promoteCountry(country);
/*  65 */       request.setAttribute("X_OBJECT", country);
/*  66 */       request.setAttribute("X_ROWPAGE", "country/row.jsp");
/*  67 */       return mapping.findForward("success");
/*     */     } 
/*     */     
/*  70 */     String name = null;
/*  71 */     if (isEnglish(request)) {
/*  72 */       name = country.getEngName();
/*     */     } else {
/*  74 */       name = country.getChnName();
/*  75 */     }  postGlobalMessage("country.promote.confirm", name, request);
/*     */     
/*  77 */     return mapping.findForward("confirm");
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
/*     */   public ActionForward listCountryProvinceCity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  92 */     if (isSite(request)) {
/*  93 */       request.setAttribute("x_siteList", getAndCheckGrantedSiteList(request));
/*     */     }
/*     */     
/*  96 */     if (hasGlobalPower(request)) {
/*  97 */       request.setAttribute("x_promote", Boolean.TRUE);
/*     */     }
/*     */     
/* 100 */     return mapping.findForward("page");
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 113 */     CountryManager fm = ServiceLocator.getCountryManager(request);
/* 114 */     List result = fm.getCountryList(new HashMap<Object, Object>(), 0, -1, isEnglish(request) ? CountryQueryOrder.ENGNAME : CountryQueryOrder.CHNNAME, false);
/* 115 */     request.setAttribute("X_RESULTLIST", result);
/* 116 */     if (!result.isEmpty()) {
/* 117 */       request.setAttribute("X_FIRST", result.get(0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     return mapping.findForward("page");
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
/*     */   private Country getCountryFromRequest(HttpServletRequest request) throws Exception {
/* 151 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 152 */     CountryManager countryManager = ServiceLocator.getCountryManager(request);
/* 153 */     Country country = countryManager.getCountry(id);
/* 154 */     if (country == null)
/* 155 */       throw new ActionException("country.notFound", id); 
/* 156 */     return country;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 160 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 173 */     Country country = getCountryFromRequest(request);
/*     */     
/* 175 */     if (country.getSite() == null) {
/* 176 */       checkGlobalPower(request);
/*     */     } else {
/* 178 */       checkSite(country.getSite(), request);
/*     */     } 
/* 180 */     request.setAttribute("x_country", country);
/* 181 */     if (!isBack(request)) {
/* 182 */       BeanForm countryForm = (BeanForm)getForm("/updateCountry", request);
/* 183 */       countryForm.populate(country, "to_form");
/*     */     } 
/* 185 */     putEnumListToRequest(request);
/* 186 */     return mapping.findForward("page");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 199 */     Country country = getCountryFromRequest(request);
/* 200 */     if (country.getSite() == null) {
/* 201 */       checkGlobalPower(request);
/*     */     } else {
/* 203 */       checkSite(country.getSite(), request);
/*     */     } 
/* 205 */     CountryManager cm = ServiceLocator.getCountryManager(request);
/*     */     try {
/* 207 */       cm.deleteCountry(country);
/*     */     }
/* 209 */     catch (Throwable t) {
/*     */       
/* 211 */       throw new ActionException("country.delete.fail");
/*     */     } 
/* 213 */     return mapping.findForward("success");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 227 */     BeanForm countryForm = (BeanForm)form;
/* 228 */     Country country = new Country();
/* 229 */     countryForm.populate(country, "to_bean");
/*     */     
/* 231 */     if (country.getSite() == null) {
/* 232 */       checkGlobalPower(request);
/*     */     } else {
/* 234 */       country.setSite(getAndCheckSite(country.getSite().getId(), request));
/*     */     } 
/* 236 */     CountryManager countryManager = ServiceLocator.getCountryManager(request);
/*     */     
/* 238 */     Country oldCountry = countryManager.getCountryByChnName(country.getChnName());
/* 239 */     if (oldCountry != null && !oldCountry.equals(country))
/*     */     {
/* 241 */       throw new BackToInputActionException("country.chnName.repeat");
/*     */     }
/*     */     
/* 244 */     oldCountry = countryManager.getCountryByEngName(country.getEngName());
/* 245 */     if (oldCountry != null && !oldCountry.equals(country))
/*     */     {
/* 247 */       throw new BackToInputActionException("country.engName.repeat");
/*     */     }
/*     */     
/* 250 */     oldCountry = countryManager.getCountryByShortName(country.getShortName());
/* 251 */     if (oldCountry != null && !oldCountry.equals(country))
/*     */     {
/* 253 */       throw new BackToInputActionException("country.shortName.repeat");
/*     */     }
/*     */     
/* 256 */     request.setAttribute("X_OBJECT", countryManager.updateCountry(country));
/* 257 */     request.setAttribute("X_ROWPAGE", "country/row.jsp");
/*     */     
/* 259 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 263 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 267 */     String s = request.getParameter("site_id");
/* 268 */     return (s != null && !s.equals(""));
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 282 */     Site site = null;
/* 283 */     if (hasSite(request)) {
/* 284 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 286 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 290 */     if (!isBack(request)) {
/* 291 */       Country country = new Country();
/* 292 */       country.setSite(site);
/* 293 */       BeanForm countryForm = (BeanForm)getForm("/insertCountry", request);
/* 294 */       countryForm.populate(country, "to_form");
/*     */     } 
/* 296 */     putEnumListToRequest(request);
/* 297 */     return mapping.findForward("page");
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
/* 311 */     Site site = null;
/* 312 */     if (hasSite(request)) {
/* 313 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 315 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 319 */     BeanForm countryForm = (BeanForm)form;
/* 320 */     Country country = new Country();
/* 321 */     countryForm.populate(country, "to_bean");
/* 322 */     country.setSite(site);
/*     */     
/* 324 */     CountryManager cm = ServiceLocator.getCountryManager(request);
/*     */     
/* 326 */     Country oldCountry = cm.getCountryByChnName(country.getChnName());
/* 327 */     if (oldCountry != null)
/*     */     {
/* 329 */       throw new BackToInputActionException("country.chnName.repeat");
/*     */     }
/*     */     
/* 332 */     oldCountry = cm.getCountryByEngName(country.getEngName());
/* 333 */     if (oldCountry != null)
/*     */     {
/* 335 */       throw new BackToInputActionException("country.engName.repeat");
/*     */     }
/*     */     
/* 338 */     oldCountry = cm.getCountryByShortName(country.getShortName());
/* 339 */     if (oldCountry != null)
/*     */     {
/* 341 */       throw new BackToInputActionException("country.shortName.repeat");
/*     */     }
/*     */     
/* 344 */     request.setAttribute("X_OBJECT", cm.insertCountry(country));
/* 345 */     request.setAttribute("X_ROWPAGE", "country/row.jsp");
/*     */     
/* 347 */     if (country.getSite() != null) {
/* 348 */       EmailManager em = ServiceLocator.getEmailManager(request);
/* 349 */       UserManager um = ServiceLocator.getUserManager(request);
/* 350 */       FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 351 */       Function function = fm.getFunction("countryProvinceCityMaintenance");
/* 352 */       List userList = um.getEnabledUserList(function);
/* 353 */       for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/* 354 */         User user = itor.next();
/* 355 */         if (user.getEmail() != null) {
/* 356 */           Map<Object, Object> context = new HashMap<Object, Object>();
/* 357 */           context.put("user", user);
/* 358 */           context.put("country", country);
/* 359 */           em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewCountry.vm", context);
/*     */         } 
/*     */       } 
/*     */     } 
/* 363 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/CountryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */