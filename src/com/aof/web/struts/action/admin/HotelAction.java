/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Hotel;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.HotelContractQueryCondition;
/*     */ import com.aof.model.admin.query.HotelContractQueryOrder;
/*     */ import com.aof.model.admin.query.HotelQueryCondition;
/*     */ import com.aof.model.admin.query.HotelQueryOrder;
/*     */ import com.aof.model.admin.query.PriceQueryCondition;
/*     */ import com.aof.model.admin.query.PriceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.HotelLevel;
/*     */ import com.aof.model.metadata.HotelPromoteStatus;
/*     */ import com.aof.service.admin.CountryManager;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.HotelContractManager;
/*     */ import com.aof.service.admin.HotelManager;
/*     */ import com.aof.service.admin.PriceManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.HotelQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
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
/*     */ import org.apache.struts.action.ActionMessage;
/*     */ import org.apache.struts.action.ActionMessages;
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
/*     */ public class HotelAction extends BaseAction2 {
/*     */   private List getEnabledCountryProvinceCityList(HttpServletRequest request) {
/*  70 */     CountryManager cm = ServiceLocator.getCountryManager(request);
/*  71 */     return cm.listEnabledCountryProvinceCity();
/*     */   }
/*     */   
/*     */   private void putEnabledCountryProvinceCityListToRequest(HttpServletRequest request) {
/*  75 */     List countryList = getEnabledCountryProvinceCityList(request);
/*  76 */     request.setAttribute("x_countryList", countryList);
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  91 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     HotelQueryForm queryForm = (HotelQueryForm)form;
/*     */     
/*  98 */     if (isSite(request))
/*     */     {
/* 100 */       if (queryForm.getSite_id() == null) {
/*     */         
/* 102 */         Site firstSite = siteList.get(0);
/*     */         
/* 104 */         queryForm.setSite_id(firstSite.getId().toString());
/*     */       } 
/*     */     }
/*     */     
/* 108 */     HotelManager fm = ServiceLocator.getHotelManager(request);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 115 */       queryForm.setEnabled(EnabledDisabled.ENABLED.toString());
/* 116 */       queryForm.setOrder(HotelQueryOrder.NAME.getName());
/* 117 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 120 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 122 */     String exportType = queryForm.getExportType();
/* 123 */     if (StringUtils.isNotEmpty(exportType)) {
/* 124 */       List data = fm.getHotelList(conditions, 0, -1, HotelQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 126 */       int index = SessionTempFile.createNewTempFile(request);
/* 127 */       String fileName = "hotel";
/* 128 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 131 */               MessageResources messages = HotelAction.this.getResources(request);
/* 132 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.name"));
/* 133 */               if (HotelAction.this.isGlobal(request))
/* 134 */                 row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.site")); 
/* 135 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.country"));
/* 136 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.province"));
/* 137 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.city"));
/* 138 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.telephone"));
/* 139 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.level"));
/* 140 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.promoteStatus"));
/* 141 */               row.add(messages.getMessage(HotelAction.this.getLocale(request), "hotel.status"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 145 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/* 146 */               if (HotelAction.this.isGlobal(request)) {
/* 147 */                 row.add(BeanHelper.getBeanPropertyValue(data, "site.name"));
/*     */               }
/* 149 */               if (HotelAction.this.isEnglish(request)) {
/* 150 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.province.country.engName"));
/*     */               } else {
/* 152 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.province.country.chnName"));
/*     */               } 
/* 154 */               if (HotelAction.this.isEnglish(request)) {
/* 155 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.province.engName"));
/*     */               } else {
/* 157 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.province.chnName"));
/*     */               } 
/* 159 */               if (HotelAction.this.isEnglish(request)) {
/* 160 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.engName"));
/*     */               } else {
/* 162 */                 row.add(BeanHelper.getBeanPropertyValue(data, "city.chnName"));
/*     */               } 
/* 164 */               row.add(BeanHelper.getBeanPropertyValue(data, "telephone"));
/*     */               
/* 166 */               if (HotelAction.this.isEnglish(request)) {
/* 167 */                 row.add(BeanHelper.getBeanPropertyValue(data, "promoteStatus.engShortDescription"));
/*     */               } else {
/* 169 */                 row.add(BeanHelper.getBeanPropertyValue(data, "promoteStatus.chnShortDescription"));
/*     */               } 
/* 171 */               if (HotelAction.this.isEnglish(request)) {
/* 172 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.engShortDescription"));
/*     */               } else {
/* 174 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 178 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 181 */     if (queryForm.isFirstInit()) {
/* 182 */       queryForm.init(fm.getHotelListCount(conditions));
/*     */     } else {
/* 184 */       queryForm.init();
/*     */     } 
/* 186 */     int pageNo = queryForm.getPageNoAsInt();
/* 187 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 189 */     List result = fm.getHotelList(conditions, pageNo, pageSize, HotelQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 190 */     request.setAttribute("X_RESULTLIST", result);
/* 191 */     putEnumListToRequest(request);
/*     */     
/* 193 */     request.setAttribute("x_siteList", siteList);
/*     */     
/* 195 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(HotelQueryForm queryForm) {
/* 199 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */ 
/*     */     
/* 202 */     Integer promoteStatus = ActionUtils.parseInt(queryForm.getPromoteStatus());
/* 203 */     if (promoteStatus != null)
/*     */     {
/* 205 */       conditions.put(HotelQueryCondition.PROMOTESTATUS_EQ, promoteStatus);
/*     */     }
/*     */ 
/*     */     
/* 209 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 210 */     if (id != null) {
/* 211 */       conditions.put(HotelQueryCondition.ID_EQ, id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     Integer site_id = ActionUtils.parseInt(queryForm.getSite_id());
/* 220 */     if (site_id != null) {
/* 221 */       conditions.put(HotelQueryCondition.SITE_ID_EQ, site_id);
/* 222 */       conditions.put(HotelQueryCondition.PROMOTESTATUS_NOTEQ, HotelPromoteStatus.GLOBAL.toString());
/*     */     } else {
/* 224 */       conditions.put(HotelQueryCondition.PROMOTESTATUS_EQ, HotelPromoteStatus.GLOBAL.toString());
/*     */     } 
/*     */     
/* 227 */     String currency_code = queryForm.getCurrency_code();
/* 228 */     if (currency_code != null && currency_code.trim().length() != 0) {
/* 229 */       conditions.put(HotelQueryCondition.CURRENCY_CODE_EQ, currency_code);
/*     */     }
/*     */ 
/*     */     
/* 233 */     Integer city_id = ActionUtils.parseInt(queryForm.getCity_id());
/* 234 */     Integer province_id = ActionUtils.parseInt(queryForm.getProvince_id());
/* 235 */     Integer country_id = ActionUtils.parseInt(queryForm.getCountry_id());
/* 236 */     if (city_id != null) {
/* 237 */       conditions.put(HotelQueryCondition.CITY_ID_EQ, city_id);
/*     */     }
/* 239 */     else if (province_id != null) {
/*     */       
/* 241 */       conditions.put(HotelQueryCondition.PROVINCE_ID_EQ, province_id);
/*     */     }
/* 243 */     else if (country_id != null) {
/* 244 */       conditions.put(HotelQueryCondition.COUNTRY_ID_EQ, country_id);
/*     */     } 
/*     */ 
/*     */     
/* 248 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 249 */     if (enabled != null) {
/* 250 */       conditions.put(HotelQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 255 */     String name = queryForm.getName();
/* 256 */     if (name != null && name.trim().length() != 0) {
/* 257 */       conditions.put(HotelQueryCondition.NAME_LIKE, name);
/*     */     }
/* 259 */     String address = queryForm.getAddress();
/* 260 */     if (address != null && address.trim().length() != 0) {
/* 261 */       conditions.put(HotelQueryCondition.ADDRESS_LIKE, address);
/*     */     }
/* 263 */     String description = queryForm.getDescription();
/* 264 */     if (description != null && description.trim().length() != 0) {
/* 265 */       conditions.put(HotelQueryCondition.DESCRIPTION_LIKE, description);
/*     */     }
/* 267 */     String telephone = queryForm.getTelephone();
/* 268 */     if (telephone != null && telephone.trim().length() != 0) {
/* 269 */       conditions.put(HotelQueryCondition.TELEPHONE_LIKE, telephone);
/*     */     }
/* 271 */     String fax = queryForm.getFax();
/* 272 */     if (fax != null && fax.trim().length() != 0) {
/* 273 */       conditions.put(HotelQueryCondition.FAX_LIKE, fax);
/*     */     }
/* 275 */     String level = queryForm.getLevel();
/* 276 */     if (level != null && level.trim().length() != 0) {
/* 277 */       conditions.put(HotelQueryCondition.LEVEL_EQ, level);
/*     */     }
/* 279 */     String contractStartDate = queryForm.getContractStartDate();
/* 280 */     if (contractStartDate != null && contractStartDate.trim().length() != 0) {
/* 281 */       conditions.put(HotelQueryCondition.CONTRACTSTARTDATE_EQ, contractStartDate);
/*     */     }
/* 283 */     String contractExpireDate = queryForm.getContractExpireDate();
/* 284 */     if (contractExpireDate != null && contractExpireDate.trim().length() != 0) {
/* 285 */       conditions.put(HotelQueryCondition.CONTRACTEXPIREDATE_EQ, contractExpireDate);
/*     */     }
/* 287 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) throws Exception {
/* 291 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 292 */     request.setAttribute("x_hotelLevelList", PersistentEnum.getEnumList(HotelLevel.class));
/*     */     
/* 294 */     request.setAttribute("x_hotelPromoteStatusList", PersistentEnum.getEnumList(HotelPromoteStatus.class));
/*     */     
/* 296 */     putEnabledCountryProvinceCityListToRequest(request);
/* 297 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 298 */     request.setAttribute("x_currencyList", cm.getAllEnabledCurrencyList());
/*     */   }
/*     */   
/*     */   private Hotel getHotelFromRequest(HttpServletRequest request) throws Exception {
/* 302 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 303 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/* 304 */     Hotel hotel = hotelManager.getHotel(id);
/* 305 */     if (hotel == null)
/* 306 */       throw new ActionException("hotel.notFound", id); 
/* 307 */     return hotel;
/*     */   }
/*     */   
/*     */   private void checkHotelPromoteStatus(Hotel hotel, HotelPromoteStatus status, HttpServletRequest request) throws Exception {
/* 311 */     if (isSite(request)) {
/* 312 */       if (!status.equals(HotelPromoteStatus.REQUEST)) {
/* 313 */         throw new RuntimeException("status error");
/*     */       }
/* 315 */       checkSite(hotel.getSite(), request);
/*     */     } 
/* 317 */     if (!hotel.getPromoteStatus().equals(status)) {
/* 318 */       throw new ActionException("hotel.promote.statusError", hotel.getName(), status.getLabel());
/*     */     }
/* 320 */     if (hotel.getEnabled().equals(EnabledDisabled.DISABLED)) {
/* 321 */       throw new ActionException("hotel.promote.disabled", hotel.getName());
/*     */     }
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
/*     */   public ActionForward requestPromote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 338 */     Hotel hotel = getHotelFromRequest(request);
/* 339 */     checkHotelPromoteStatus(hotel, HotelPromoteStatus.SITE, request);
/*     */     
/* 341 */     request.setAttribute("x_hotel", hotel);
/* 342 */     if (!isBack(request)) {
/* 343 */       BeanForm hotelForm = (BeanForm)getForm("/requestPromoteHotel_result", request);
/* 344 */       hotelForm.populate(hotel, "to_form");
/*     */     } 
/* 346 */     return mapping.findForward("page");
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
/*     */   public ActionForward responsePromote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 360 */     Hotel hotel = getHotelFromRequest(request);
/*     */ 
/*     */     
/* 363 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/* 364 */     hotel = hotelManager.reponsePromote(hotel.getId());
/*     */     
/* 366 */     request.setAttribute("x_hotel", hotel);
/*     */ 
/*     */     
/* 369 */     ActionMessages messages = new ActionMessages();
/* 370 */     ActionMessage message = new ActionMessage("hotel.response.success");
/* 371 */     messages.add("success", message);
/* 372 */     saveMessages(request.getSession(), messages);
/*     */     
/* 374 */     return mapping.findForward("success");
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
/*     */   public ActionForward requestPromote_result(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 401 */     Hotel hotel = getHotelFromRequest(request);
/* 402 */     checkHotelPromoteStatus(hotel, HotelPromoteStatus.SITE, request);
/*     */     
/* 404 */     String promteMsg = request.getParameter("promoteMessage");
/*     */     
/* 406 */     hotel.setPromoteMessage(promteMsg);
/*     */     
/* 408 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/*     */     
/* 410 */     hotel = hotelManager.requestPromote(hotel.getId(), hotel.getPromoteMessage());
/*     */     
/* 412 */     EmailManager em = ServiceLocator.getEmailManager(request);
/* 413 */     UserManager um = ServiceLocator.getUserManager(request);
/* 414 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 415 */     Function function = fm.getFunction("siteHotelMaintenance");
/* 416 */     List userList = um.getEnabledUserList(function, hotel.getSite());
/* 417 */     for (Iterator<User> itor = userList.iterator(); itor.hasNext(); ) {
/* 418 */       User user = itor.next();
/* 419 */       if (user.getEmail() != null) {
/* 420 */         Map<Object, Object> context = new HashMap<Object, Object>();
/* 421 */         context.put("user", user);
/* 422 */         context.put("hotel", hotel);
/* 423 */         context.put("promoteMessage", promteMsg);
/* 424 */         em.insertEmail(user.getPrimarySite(), user.getEmail(), "HotelPromote.vm", context);
/*     */       } 
/*     */     } 
/*     */     
/* 428 */     request.setAttribute("PROMOTE_MESSAGE", promteMsg);
/*     */ 
/*     */     
/* 431 */     return mapping.findForward("success");
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
/* 447 */     Hotel hotel = null;
/*     */     
/* 449 */     if (request.getAttribute("x_hotel") == null) {
/* 450 */       hotel = getHotelFromRequest(request);
/* 451 */       request.setAttribute("x_hotel", hotel);
/*     */     } else {
/* 453 */       hotel = (Hotel)request.getAttribute("x_hotel");
/*     */     } 
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
/* 471 */     if (!isBack(request)) {
/* 472 */       BeanForm hotelForm = (BeanForm)getForm("/updateHotel", request);
/* 473 */       hotelForm.populate(hotel, "to_form");
/*     */     } 
/* 475 */     putContractListToRequest(hotel, request);
/* 476 */     putPriceListToRequest(hotel, request);
/*     */     
/* 478 */     putEnumListToRequest(request);
/*     */     
/* 480 */     request.setAttribute("x_action", "/updateHotel" + request.getAttribute("x_version"));
/* 481 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void putPriceListToRequest(Hotel hotel, HttpServletRequest request) throws Exception {
/* 485 */     PriceManager fm = ServiceLocator.getPriceManager(request);
/* 486 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 487 */     conditions.put(PriceQueryCondition.HOTEL_ID_EQ, hotel.getId());
/* 488 */     List result = fm.getPriceList(conditions, 0, -1, PriceQueryOrder.ID, false);
/* 489 */     request.setAttribute("x_priceList", result);
/*     */   }
/*     */ 
/*     */   
/*     */   private void putContractListToRequest(Hotel hotel, HttpServletRequest request) throws Exception {
/* 494 */     HotelContractManager fm = ServiceLocator.getHotelContractManager(request);
/* 495 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 496 */     conditions.put(HotelContractQueryCondition.HOTEL_ID_EQ, hotel.getId());
/* 497 */     List result = fm.getHotelContractList(conditions, 0, -1, HotelContractQueryOrder.ID, false);
/* 498 */     request.setAttribute("x_contractList", result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/* 505 */     if (hasSite(request)) {
/* 506 */       if (isGlobal(request)) {
/* 507 */         throw new ActionException("hotel.error.globalNewSite");
/*     */       }
/* 509 */       return getAndCheckSite("site_id", request);
/*     */     } 
/*     */     
/* 512 */     if (isGlobal(request)) {
/* 513 */       return null;
/*     */     }
/* 515 */     throw new ActionException("hotel.error.siteNewGlobal");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 521 */     return !StringUtils.isEmpty(request.getParameter("site_id"));
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
/* 535 */     Site site = getAndCheckSite(request);
/*     */     
/* 537 */     if (!isBack(request)) {
/* 538 */       Hotel hotel = new Hotel();
/* 539 */       hotel.setSite(site);
/* 540 */       if (site != null) {
/* 541 */         hotel.setCurrency(site.getBaseCurrency());
/*     */       }
/* 543 */       BeanForm hotelForm = (BeanForm)getForm("/insertHotel", request);
/* 544 */       hotel.setPromoteStatus(HotelPromoteStatus.SITE);
/* 545 */       hotelForm.populate(hotel, "to_form");
/*     */     } 
/*     */     
/* 548 */     putEnumListToRequest(request);
/* 549 */     return mapping.findForward("page");
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
/* 563 */     Hotel oldHotel = getHotelFromRequest(request);
/*     */ 
/*     */ 
/*     */     
/* 567 */     if (isSite(request)) {
/* 568 */       if (oldHotel.getPromoteStatus().equals(HotelPromoteStatus.GLOBAL)) {
/* 569 */         throw new ActionException("hotel.error.siteEditGlobal");
/*     */       }
/* 571 */       checkSite(oldHotel.getSite(), request);
/*     */     }
/* 573 */     else if (isGlobal(request)) {
/*     */       
/* 575 */       if (!oldHotel.getPromoteStatus().equals(HotelPromoteStatus.GLOBAL)) {
/* 576 */         throw new ActionException("hotel.error.globalEditSite");
/*     */       }
/*     */     } 
/*     */     
/* 580 */     BeanForm hotelForm = (BeanForm)form;
/* 581 */     Hotel hotel = new Hotel();
/* 582 */     hotelForm.populate(hotel, "to_bean");
/* 583 */     hotel.setSite(oldHotel.getSite());
/*     */     
/* 585 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/* 586 */     hotelManager.updateHotel(hotel);
/*     */     
/* 588 */     ActionMessages messages = new ActionMessages();
/* 589 */     ActionMessage message = new ActionMessage("hotel.update.success");
/* 590 */     messages.add("success", message);
/* 591 */     saveMessages(request.getSession(), messages);
/*     */     
/* 593 */     return getForwardFor(hotel, request);
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
/* 607 */     Site site = getAndCheckSite(request);
/*     */     
/* 609 */     BeanForm hotelForm = (BeanForm)form;
/* 610 */     Hotel hotel = new Hotel();
/* 611 */     hotelForm.populateToBean(hotel, request);
/* 612 */     hotel.setSite(site);
/*     */     
/* 614 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/* 615 */     hotelManager.insertHotel(hotel);
/*     */     
/* 617 */     ActionMessages messages = new ActionMessages();
/* 618 */     ActionMessage message = new ActionMessage("hotel.insert.success");
/* 619 */     messages.add("success", message);
/* 620 */     saveMessages(request.getSession(), messages);
/*     */     
/* 622 */     return getForwardFor(hotel, request);
/*     */   }
/*     */   
/*     */   private ActionForward getForwardFor(Hotel hotel, HttpServletRequest request) {
/* 626 */     String url = "editHotel" + request.getAttribute("x_version") + ".do?id=" + hotel.getId();
/* 627 */     ActionForward forward = new ActionForward(url);
/* 628 */     forward.setRedirect(true);
/* 629 */     return forward;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/HotelAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */