/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.ProvinceQueryCondition;
/*     */ import com.aof.model.admin.query.ProvinceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.CountryManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.ProvinceManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
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
/*     */ 
/*     */ 
/*     */ public class ProvinceAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward promote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  62 */     Province p = getProvinceFromRequest(request);
/*  63 */     if (p.getSite() == null) {
/*  64 */       throw new ActionException("province.error.alreadyPromoted", p.getEngName());
/*     */     }
/*  66 */     if (request.getParameter("confirm") != null) {
/*  67 */       ProvinceManager pm = ServiceLocator.getProvinceManager(request);
/*  68 */       pm.promoteProvince(p);
/*  69 */       request.setAttribute("X_OBJECT", p);
/*  70 */       request.setAttribute("X_ROWPAGE", "province/row.jsp");
/*  71 */       return mapping.findForward("success");
/*     */     } 
/*     */     
/*  74 */     String name = null;
/*  75 */     if (isEnglish(request)) {
/*  76 */       name = p.getEngName();
/*     */     } else {
/*  78 */       name = p.getChnName();
/*  79 */     }  postGlobalMessage("province.promote.confirm", name, request);
/*  80 */     return mapping.findForward("confirm");
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  94 */     Country country = getCountryFromRequest(request);
/*     */     
/*  96 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  97 */     conds.put(ProvinceQueryCondition.COUNTRY_ID_EQ, country.getId());
/*     */     
/*  99 */     ProvinceManager fm = ServiceLocator.getProvinceManager(request);
/* 100 */     List result = fm.getProvinceList(conds, 0, -1, isEnglish(request) ? ProvinceQueryOrder.ENGNAME : ProvinceQueryOrder.CHNNAME, false);
/* 101 */     request.setAttribute("X_RESULTLIST", result);
/* 102 */     if (!result.isEmpty()) {
/* 103 */       request.setAttribute("X_FIRST", result.get(0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Country getCountryFromRequest(HttpServletRequest request) throws Exception {
/* 115 */     Integer country_id = ActionUtils.parseInt(request.getParameter("country_id"));
/* 116 */     CountryManager countryManager = ServiceLocator.getCountryManager(request);
/* 117 */     Country country = countryManager.getCountry(country_id);
/* 118 */     if (country == null) {
/* 119 */       throw new ActionException("country.notFound", country_id);
/*     */     }
/* 121 */     return country;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Province getProvinceFromRequest(HttpServletRequest request) throws Exception {
/* 154 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 155 */     ProvinceManager provinceManager = ServiceLocator.getProvinceManager(request);
/* 156 */     Province province = provinceManager.getProvince(id);
/* 157 */     if (province == null)
/* 158 */       throw new ActionException("province.notFound", id); 
/* 159 */     return province;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 173 */     Province province = getProvinceFromRequest(request);
/*     */     
/* 175 */     if (province.getSite() == null) {
/* 176 */       checkGlobalPower(request);
/*     */     } else {
/* 178 */       checkSite(province.getSite(), request);
/*     */     } 
/* 180 */     request.setAttribute("x_province", province);
/*     */     
/* 182 */     if (!isBack(request)) {
/* 183 */       BeanForm provinceForm = (BeanForm)getForm("/updateProvince", request);
/* 184 */       provinceForm.populate(province, "to_form");
/*     */     } 
/*     */     
/* 187 */     putEnumListToRequest(request);
/* 188 */     return mapping.findForward("page");
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
/* 201 */     Province p = getProvinceFromRequest(request);
/* 202 */     if (p.getSite() == null) {
/* 203 */       checkGlobalPower(request);
/*     */     } else {
/* 205 */       checkSite(p.getSite(), request);
/*     */     } 
/* 207 */     ProvinceManager pm = ServiceLocator.getProvinceManager(request);
/*     */     
/*     */     try {
/* 210 */       pm.deleteProvince(p);
/*     */     }
/* 212 */     catch (Throwable t) {
/*     */       
/* 214 */       throw new ActionException("province.delete.fail");
/*     */     } 
/* 216 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 220 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 234 */     BeanForm provinceForm = (BeanForm)form;
/* 235 */     Province province = new Province();
/* 236 */     provinceForm.populate(province, "to_bean");
/*     */     
/* 238 */     if (province.getSite() == null) {
/* 239 */       checkGlobalPower(request);
/*     */     } else {
/* 241 */       province.setSite(getAndCheckSite(province.getSite().getId(), request));
/*     */     } 
/* 243 */     ProvinceManager provinceManager = ServiceLocator.getProvinceManager(request);
/*     */     
/* 245 */     Province oldProvince = provinceManager.getProvinceByChnName(province.getCountry(), province.getChnName());
/* 246 */     if (oldProvince != null && !oldProvince.equals(province)) throw new BackToInputActionException("province.chnName.repeat");
/*     */     
/* 248 */     oldProvince = provinceManager.getProvinceByEngName(province.getCountry(), province.getEngName());
/* 249 */     if (oldProvince != null && !oldProvince.equals(province)) throw new BackToInputActionException("province.engName.repeat");
/*     */     
/* 251 */     request.setAttribute("X_OBJECT", provinceManager.updateProvince(province));
/* 252 */     request.setAttribute("X_ROWPAGE", "province/row.jsp");
/*     */     
/* 254 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 258 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 262 */     String s = request.getParameter("site_id");
/* 263 */     return (s != null && !s.equals(""));
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
/* 278 */     Site site = null;
/* 279 */     if (hasSite(request)) {
/* 280 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 282 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 286 */     Country country = getCountryFromRequest(request);
/* 287 */     request.setAttribute("x_country", country);
/* 288 */     if (!isBack(request)) {
/* 289 */       Province province = new Province();
/* 290 */       province.setSite(site);
/* 291 */       province.setCountry(country);
/* 292 */       BeanForm provinceForm = (BeanForm)getForm("/insertProvince", request);
/* 293 */       provinceForm.populate(province, "to_form");
/*     */     } 
/* 295 */     putEnumListToRequest(request);
/* 296 */     return mapping.findForward("page");
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
/* 310 */     Site site = null;
/* 311 */     if (hasSite(request)) {
/* 312 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 314 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 318 */     BeanForm provinceForm = (BeanForm)form;
/* 319 */     Province province = new Province();
/* 320 */     provinceForm.populate(province, "to_bean");
/* 321 */     province.setSite(site);
/*     */     
/* 323 */     ProvinceManager pm = ServiceLocator.getProvinceManager(request);
/*     */     
/* 325 */     Province oldProvince = pm.getProvinceByChnName(province.getCountry(), province.getChnName());
/* 326 */     if (oldProvince != null) throw new BackToInputActionException("province.chnName.repeat");
/*     */     
/* 328 */     oldProvince = pm.getProvinceByEngName(province.getCountry(), province.getEngName());
/* 329 */     if (oldProvince != null) throw new BackToInputActionException("province.engName.repeat");
/*     */     
/* 331 */     request.setAttribute("X_OBJECT", pm.insertProvince(province));
/* 332 */     request.setAttribute("X_ROWPAGE", "province/row.jsp");
/*     */     
/* 334 */     if (province.getSite() != null) {
/* 335 */       EmailManager em = ServiceLocator.getEmailManager(request);
/* 336 */       UserManager um = ServiceLocator.getUserManager(request);
/* 337 */       FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 338 */       Function function = fm.getFunction("countryProvinceCityMaintenance");
/* 339 */       List userList = um.getEnabledUserList(function);
/* 340 */       for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/* 341 */         User user = itor.next();
/* 342 */         if (user.getEmail() != null) {
/* 343 */           CountryManager cm = ServiceLocator.getCountryManager(request);
/* 344 */           Map<Object, Object> context = new HashMap<Object, Object>();
/* 345 */           context.put("user", user);
/* 346 */           context.put("province", province);
/* 347 */           context.put("country", cm.getCountry(province.getCountry().getId()));
/* 348 */           em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewProvince.vm", context);
/*     */         } 
/*     */       } 
/*     */     } 
/* 352 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/ProvinceAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */