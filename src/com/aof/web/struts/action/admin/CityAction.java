/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.CityQueryCondition;
/*     */ import com.aof.model.admin.query.CityQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.CityManager;
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
/*     */ public class CityAction extends BaseAction2 {
/*     */   public ActionForward promote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     City city = getCity(request);
/*  61 */     if (city.getSite() == null) {
/*  62 */       throw new ActionException("city.error.alreadyPromoted", city.getEngName());
/*     */     }
/*     */     
/*  65 */     if (request.getParameter("confirm") != null) {
/*  66 */       CityManager cm = ServiceLocator.getCityManager(request);
/*  67 */       cm.promoteCity(city);
/*  68 */       request.setAttribute("X_OBJECT", city);
/*  69 */       request.setAttribute("X_ROWPAGE", "city/row.jsp");
/*     */       
/*  71 */       return mapping.findForward("success");
/*     */     } 
/*     */     
/*  74 */     String name = null;
/*  75 */     if (isEnglish(request)) {
/*  76 */       name = city.getEngName();
/*     */     } else {
/*  78 */       name = city.getChnName();
/*     */     } 
/*  80 */     postGlobalMessage("city.promote.confirm", name, request);
/*  81 */     return mapping.findForward("confirm");
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
/*  95 */     Province province = getProvinceFromRequest(request);
/*  96 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/*  97 */     conds.put(CityQueryCondition.PROVINCE_ID_EQ, province.getId());
/*     */     
/*  99 */     CityManager fm = ServiceLocator.getCityManager(request);
/* 100 */     List result = fm.getCityList(conds, 0, -1, isEnglish(request) ? CityQueryOrder.ENGNAME : CityQueryOrder.CHNNAME, false);
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
/*     */   private Province getProvinceFromRequest(HttpServletRequest request) throws Exception {
/* 115 */     Integer province_id = ActionUtils.parseInt(request.getParameter("province_id"));
/* 116 */     ProvinceManager provinceManager = ServiceLocator.getProvinceManager(request);
/* 117 */     Province province = provinceManager.getProvince(province_id);
/* 118 */     if (province == null)
/* 119 */       throw new ActionException("province.notFound", province_id); 
/* 120 */     return province;
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
/*     */   private City getCity(HttpServletRequest request) throws Exception {
/* 150 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 151 */     CityManager cityManager = ServiceLocator.getCityManager(request);
/* 152 */     City city = cityManager.getCity(id);
/* 153 */     if (city == null)
/* 154 */       throw new ActionException("city.notFound", id); 
/* 155 */     return city;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 159 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 173 */     City city = getCity(request);
/*     */     
/* 175 */     if (city.getSite() == null) {
/* 176 */       checkGlobalPower(request);
/*     */     } else {
/* 178 */       checkSite(city.getSite(), request);
/*     */     } 
/* 180 */     request.setAttribute("x_city", city);
/* 181 */     if (!isBack(request)) {
/*     */       
/* 183 */       BeanForm cityForm = (BeanForm)getForm("/updateCity", request);
/* 184 */       cityForm.populate(city, "to_form");
/*     */     } 
/* 186 */     putEnumListToRequest(request);
/* 187 */     return mapping.findForward("page");
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
/* 200 */     City city = getCity(request);
/* 201 */     if (city.getSite() == null) {
/* 202 */       checkGlobalPower(request);
/*     */     } else {
/* 204 */       checkSite(city.getSite(), request);
/*     */     } 
/* 206 */     CityManager cm = ServiceLocator.getCityManager(request);
/*     */     try {
/* 208 */       cm.deleteCity(city);
/*     */     }
/* 210 */     catch (Throwable t) {
/*     */       
/* 212 */       throw new ActionException("city.delete.fail");
/*     */     } 
/* 214 */     return mapping.findForward("success");
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
/* 228 */     BeanForm cityForm = (BeanForm)form;
/* 229 */     City city = new City();
/* 230 */     cityForm.populate(city, "to_bean");
/*     */     
/* 232 */     if (city.getSite() == null) {
/* 233 */       checkGlobalPower(request);
/*     */     } else {
/* 235 */       city.setSite(getAndCheckSite(city.getSite().getId(), request));
/*     */     } 
/* 237 */     CityManager cityManager = ServiceLocator.getCityManager(request);
/* 238 */     City oldCity = cityManager.getCityByChnName(city.getProvince(), city.getChnName());
/* 239 */     if (oldCity != null && !oldCity.equals(city)) {
/* 240 */       throw new BackToInputActionException("city.chnName.repeat");
/*     */     }
/* 242 */     oldCity = cityManager.getCityByEngName(city.getProvince(), city.getEngName());
/* 243 */     if (oldCity != null && !oldCity.equals(city)) {
/* 244 */       throw new BackToInputActionException("city.engName.repeat");
/*     */     }
/* 246 */     request.setAttribute("X_OBJECT", cityManager.updateCity(city));
/* 247 */     request.setAttribute("X_ROWPAGE", "city/row.jsp");
/*     */     
/* 249 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 253 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 257 */     String s = request.getParameter("site_id");
/* 258 */     return (s != null && !s.equals(""));
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
/* 273 */     Site site = null;
/* 274 */     if (hasSite(request)) {
/* 275 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 277 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 281 */     Province province = getProvinceFromRequest(request);
/* 282 */     request.setAttribute("x_province", province);
/* 283 */     if (!isBack(request)) {
/* 284 */       City city = new City();
/* 285 */       city.setSite(site);
/* 286 */       city.setProvince(province);
/* 287 */       BeanForm cityForm = (BeanForm)getForm("/insertCity", request);
/* 288 */       cityForm.populate(city, "to_form");
/*     */     } 
/* 290 */     putEnumListToRequest(request);
/* 291 */     return mapping.findForward("page");
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
/* 306 */     Site site = null;
/* 307 */     if (hasSite(request)) {
/* 308 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 310 */       checkGlobalPower(request);
/*     */     } 
/*     */ 
/*     */     
/* 314 */     CityManager cm = ServiceLocator.getCityManager(request);
/* 315 */     BeanForm cityForm = (BeanForm)form;
/* 316 */     City city = new City();
/* 317 */     cityForm.populate(city, "to_bean");
/* 318 */     city.setSite(site);
/*     */     
/* 320 */     if (cm.getCityByChnName(city.getProvince(), city.getChnName()) != null) {
/* 321 */       throw new BackToInputActionException("city.chnName.repeat");
/*     */     }
/* 323 */     if (cm.getCityByEngName(city.getProvince(), city.getEngName()) != null) {
/* 324 */       throw new BackToInputActionException("city.engName.repeat");
/*     */     }
/* 326 */     request.setAttribute("X_OBJECT", cm.insertCity(city));
/* 327 */     request.setAttribute("X_ROWPAGE", "city/row.jsp");
/*     */     
/* 329 */     if (city.getSite() != null) {
/* 330 */       EmailManager em = ServiceLocator.getEmailManager(request);
/* 331 */       UserManager um = ServiceLocator.getUserManager(request);
/* 332 */       FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 333 */       Function function = fm.getFunction("countryProvinceCityMaintenance");
/* 334 */       List userList = um.getEnabledUserList(function);
/* 335 */       for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/* 336 */         User user = itor.next();
/* 337 */         if (user.getEmail() != null) {
/* 338 */           ProvinceManager pm = ServiceLocator.getProvinceManager(request);
/* 339 */           Map<Object, Object> context = new HashMap<Object, Object>();
/* 340 */           context.put("user", user);
/* 341 */           context.put("city", city);
/* 342 */           context.put("province", pm.getProvince(city.getProvince().getId()));
/* 343 */           em.insertEmail(user.getPrimarySite(), user.getEmail(), "NewCity.vm", context);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 348 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/CityAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */