/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.query.StorageLocationQueryCondition;
/*     */ import com.aof.model.basic.query.StorageLocationQueryOrder;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.StoreRoomManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.StorageLocationQueryForm;
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
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
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
/*     */ public class StorageLocationAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  73 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/*  74 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  75 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/*  76 */       queryForm.setDescend(false);
/*     */     } 
/*  78 */     List grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/*  79 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*     */     
/*  81 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/*  82 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  84 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  86 */     String exportType = queryForm.getExportType();
/*  87 */     if (StringUtils.isNotEmpty(exportType)) {
/*  88 */       List data = fm.getStorageLocationList(conditions, 0, -1, StorageLocationQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  90 */       int index = SessionTempFile.createNewTempFile(request);
/*  91 */       String fileName = "storageLocation";
/*  92 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  95 */               MessageResources messages = StorageLocationAction.this.getResources(request);
/*  96 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.codeid"));
/*  97 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.address"));
/*  98 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.max_inventory"));
/*  99 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.basic_storeroom_id.code"));
/* 100 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.basic_recommend_status"));
/* 101 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.freeae_status"));
/* 102 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.LOGIN_USER.locale"));
/* 103 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.f_in_f_out_status"));
/* 104 */               row.add(messages.getMessage(StorageLocationAction.this.getLocale(request), "storageLocation.description"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 108 */               String codeid = BeanHelper.getBeanPropertyValue(data, "code").toString();
/* 109 */               row.add("'" + codeid);
/* 110 */               row.add(BeanHelper.getBeanPropertyValue(data, "address"));
/* 111 */               row.add(BeanHelper.getBeanPropertyValue(data, "max_inventory"));
/* 112 */               row.add(BeanHelper.getBeanPropertyValue(data, "basic_storeroom_id.code"));
/* 113 */               String code = BeanHelper.getBeanPropertyValue(data, "recommend_status").toString();
/* 114 */               if (code.equals("1")) {
/* 115 */                 row.add("不参与");
/*     */               }
/* 117 */               if (code.equals("0") || code == null || code.equals("")) {
/* 118 */                 row.add("参与");
/*     */               }
/* 120 */               String status = BeanHelper.getBeanPropertyValue(data, "freeae_status").toString();
/* 121 */               if (status.equals("0")) {
/* 122 */                 row.add("已冻结");
/*     */               }
/* 124 */               if (status.equals("1") || status == null || status.equals("")) {
/* 125 */                 row.add("未冻结");
/*     */               }
/* 127 */               String locale = BeanHelper.getBeanPropertyValue(data, "enabled.engShortDescription").toString();
/* 128 */               if (locale.equals("Enabled")) {
/* 129 */                 row.add("可用");
/*     */               } else {
/* 131 */                 row.add("不可用");
/*     */               } 
/*     */               
/* 134 */               String outstatus = BeanHelper.getBeanPropertyValue(data, "f_in_f_out_status").toString();
/* 135 */               if (outstatus.equals("0")) {
/* 136 */                 row.add("强行限定");
/*     */               }
/* 138 */               if (outstatus.equals("") || outstatus.equals("1") || outstatus == null) {
/* 139 */                 row.add("不限定");
/*     */               }
/*     */ 
/*     */               
/* 143 */               row.add(BeanHelper.getBeanPropertyValue(data, "description"));
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 148 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 150 */     if (queryForm.isFirstInit()) {
/* 151 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/*     */     } else {
/* 153 */       queryForm.init();
/*     */     } 
/* 155 */     List result = fm.getStorageLocationList(conditions, 
/* 156 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 157 */         StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 159 */     request.setAttribute("X_RESULTLIST", result);
/* 160 */     request.setAttribute("x_selType", Integer.valueOf(9));
/* 161 */     putEnumListToRequest(request);
/* 162 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(StorageLocationQueryForm queryForm) {
/* 166 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 167 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 168 */     if (id != null && !id.equals("")) {
/* 169 */       conditions.put(StorageLocationQueryCondition.ID_EQ, id);
/*     */     }
/* 171 */     String describe = queryForm.getDescribe();
/* 172 */     if (describe != null && !describe.equals("")) {
/* 173 */       conditions.put(StorageLocationQueryCondition.DESCRIBE_EQ, describe);
/*     */     }
/* 175 */     String stromType = queryForm.getStromType();
/* 176 */     if (stromType != null && !stromType.equals("")) {
/* 177 */       conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, stromType);
/*     */     }
/* 179 */     String notContain = queryForm.getNotContain();
/* 180 */     if (notContain != null && !notContain.equals("")) {
/* 181 */       conditions.put(StorageLocationQueryCondition.NOTCONTAIN_EQ, notContain);
/*     */     }
/* 183 */     String address = queryForm.getAddress();
/* 184 */     if (address != null && !address.equals("")) {
/* 185 */       conditions.put(StorageLocationQueryCondition.ADDRESS_EQ, address);
/*     */     }
/* 187 */     String storeroom = queryForm.getStoreroom_id();
/* 188 */     if (storeroom != null && !storeroom.equals("")) {
/* 189 */       conditions.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, storeroom);
/*     */     }
/* 191 */     String status = queryForm.getStatus();
/* 192 */     if (status != null && !status.equals("")) {
/* 193 */       conditions.put(StorageLocationQueryCondition.ENABLED_EQ, status);
/*     */     }
/* 195 */     String site = queryForm.getSite();
/* 196 */     if (site != null && !site.equals("")) {
/* 197 */       conditions.put(StorageLocationQueryCondition.SITE_EQ, site);
/*     */     }
/* 199 */     String code = queryForm.getCode();
/* 200 */     if (code != null && !code.equals("")) {
/* 201 */       conditions.put(StorageLocationQueryCondition.CODE_EQ, code);
/*     */     }
/* 203 */     String codemany = queryForm.getCodemany();
/* 204 */     if (codemany != null && !codemany.equals("")) {
/* 205 */       String[] str = codemany.split(",");
/* 206 */       conditions.put(StorageLocationQueryCondition.CODEMANY_EQ, str);
/*     */     } 
/* 208 */     return conditions;
/*     */   }
/*     */   
/*     */   private StorageLocation getStorageLocation(HttpServletRequest request) throws Exception {
/* 212 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 213 */     StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
/* 214 */     StorageLocation storageLocation = storageLocationManager.getStorageLocation(id);
/* 215 */     if (storageLocation == null)
/* 216 */       throw new ActionException("storageLocation.notFound", id); 
/* 217 */     return storageLocation;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 221 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 222 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 223 */     request.setAttribute("X_SITEID", ServiceLocator.getSiteManager(request).getAllEnabledSiteList());
/* 224 */     request.setAttribute("X_STOREROOMLIST", ServiceLocator.getStoreRoomManager(request).getStoreRoomListEnabledAll());
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
/* 240 */     StorageLocation storageLocation = getStorageLocation(request);
/*     */     
/* 242 */     request.setAttribute("x_storageLocation", storageLocation);
/* 243 */     if (!isBack(request)) {
/*     */       
/* 245 */       BeanForm storageLocationForm = (BeanForm)getForm("/updateStorageLocation", request);
/* 246 */       storageLocationForm.populate(storageLocation, "to_form");
/*     */     } 
/* 248 */     putEnumListToRequest(request);
/* 249 */     return mapping.findForward("page");
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
/* 262 */     StorageLocation storageLocation = getStorageLocation(request);
/*     */     
/* 264 */     StorageLocationManager cm = ServiceLocator.getStorageLocationManager(request);
/*     */     try {
/* 266 */       cm.deleteStorageLocation(storageLocation);
/*     */     }
/* 268 */     catch (Throwable t) {
/*     */       
/* 270 */       throw new ActionException("storageLocation.delete.fail");
/*     */     } 
/* 272 */     return mapping.findForward("success");
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
/* 288 */     BeanForm storageLocationForm = (BeanForm)form;
/* 289 */     StorageLocation storageLocation = new StorageLocation();
/* 290 */     storageLocationForm.populate(storageLocation, "to_bean");
/* 291 */     StorageLocationManager storageLocationManager = ServiceLocator.getStorageLocationManager(request);
/* 292 */     storageLocationManager.updateStorageLocation(storageLocation);
/*     */     
/* 294 */     request.setAttribute("X_OBJECT", storageLocationManager.getStorageLocation(storageLocation.getId()));
/* 295 */     request.setAttribute("X_ROWPAGE", "wmsbasic/storageLocation/row.jsp");
/* 296 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 300 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 304 */     String s = request.getParameter("site_id");
/* 305 */     return (s != null && !s.equals(""));
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
/* 320 */     if (!isBack(request)) {
/* 321 */       StorageLocation storageLocation = new StorageLocation();
/* 322 */       BeanForm storageLocationForm = (BeanForm)getForm("/insertStorageLocation", request);
/* 323 */       storageLocationForm.populate(storageLocation, "to_form");
/*     */     } 
/* 325 */     putEnumListToRequest(request);
/* 326 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 346 */     StorageLocationManager cm = ServiceLocator.getStorageLocationManager(request);
/* 347 */     BeanForm storageLocationForm = (BeanForm)form;
/* 348 */     StorageLocation storageLocation = new StorageLocation();
/* 349 */     storageLocationForm.populate(storageLocation, "to_bean");
/*     */     
/* 351 */     request.setAttribute("X_OBJECT", cm.insertStorageLocation(storageLocation));
/*     */     
/* 353 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 359 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 360 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 361 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 362 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 365 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 366 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 368 */     int pageNo = queryForm.getPageNoAsInt();
/* 369 */     int pageSize = queryForm.getPageSizeAsInt();
/* 370 */     if (queryForm.isFirstInit()) {
/* 371 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/* 372 */       queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
/* 373 */       if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
/* 374 */         pageNo = 0;
/* 375 */         queryForm.setPageNo("0");
/*     */       } 
/* 377 */       queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
/*     */     } else {
/* 379 */       queryForm.init();
/*     */     } 
/* 381 */     List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */     
/* 384 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/*     */     
/* 386 */     request.setAttribute("X_RESULTLIST", result);
/* 387 */     putEnumListToRequest(request);
/*     */     
/* 389 */     String type = request.getParameter("type");
/* 390 */     if (type != null && type.equals("1")) {
/* 391 */       request.setAttribute("type", type);
/* 392 */       request.setAttribute("x_selType", Integer.valueOf(37));
/*     */     } 
/*     */ 
/*     */     
/* 396 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectLF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 403 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 404 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 405 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 406 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 409 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 410 */     Map<StorageLocationQueryCondition, String> conditions = constructQueryMap(queryForm);
/* 411 */     conditions.put(StorageLocationQueryCondition.STOREROOM_ID_EQ, "CK006");
/* 412 */     int pageNo = queryForm.getPageNoAsInt();
/* 413 */     int pageSize = queryForm.getPageSizeAsInt();
/* 414 */     if (queryForm.isFirstInit()) {
/* 415 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/* 416 */       queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
/* 417 */       if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
/* 418 */         pageNo = 0;
/* 419 */         queryForm.setPageNo("0");
/*     */       } 
/* 421 */       queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
/*     */     } else {
/* 423 */       queryForm.init();
/*     */     } 
/* 425 */     List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 427 */     String type = request.getParameter("type");
/* 428 */     if (type != null && type.equals("1")) {
/* 429 */       request.setAttribute("x_selType", Integer.valueOf(37));
/*     */     }
/*     */     
/* 432 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/* 433 */     request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
/* 434 */     request.setAttribute("X_RESULTLIST", result);
/* 435 */     putEnumListToRequest(request);
/* 436 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 442 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 443 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 444 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 445 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 448 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 449 */     Map<StorageLocationQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 450 */     conditions.put(StorageLocationQueryCondition.STROMTYPE_EQ, Integer.valueOf(8));
/* 451 */     int pageNo = queryForm.getPageNoAsInt();
/* 452 */     int pageSize = queryForm.getPageSizeAsInt();
/* 453 */     if (queryForm.isFirstInit()) {
/* 454 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/* 455 */       queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
/* 456 */       if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
/* 457 */         pageNo = 0;
/* 458 */         queryForm.setPageNo("0");
/*     */       } 
/* 460 */       queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
/*     */     } else {
/* 462 */       queryForm.init();
/*     */     } 
/* 464 */     List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/* 465 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/* 466 */     request.setAttribute("X_RESULTLIST", result);
/* 467 */     putEnumListToRequest(request);
/* 468 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectStorage1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 475 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 476 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 477 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 478 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 481 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 482 */     Map<StorageLocationQueryCondition, Object[]> conditions = constructQueryMap(queryForm);
/* 483 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 484 */     conditions.put(StorageLocationQueryCondition.STROMTYPE_IN, new Object[] { Integer.valueOf(5), Integer.valueOf(8) });
/* 485 */     int pageNo = queryForm.getPageNoAsInt();
/* 486 */     int pageSize = queryForm.getPageSizeAsInt();
/* 487 */     if (queryForm.isFirstInit()) {
/* 488 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/* 489 */       queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
/* 490 */       if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
/* 491 */         pageNo = 0;
/* 492 */         queryForm.setPageNo("0");
/*     */       } 
/* 494 */       queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
/*     */     } else {
/* 496 */       queryForm.init();
/*     */     } 
/* 498 */     List result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/* 499 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/* 500 */     request.setAttribute("X_RESULTLIST", result);
/* 501 */     putEnumListToRequest(request);
/* 502 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 507 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 508 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 509 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 510 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 511 */       queryForm.setDescend(false);
/* 512 */       queryForm.setStatus("0");
/*     */     } 
/* 514 */     List<Site> grantedSiteList = getAndCheckGrantedSiteDeparmentList(request);
/* 515 */     request.setAttribute("X_SITELIST", grantedSiteList);
/* 516 */     if (hasGlobalPower(request)) {
/* 517 */       request.setAttribute("X_GLOBAL", Boolean.TRUE);
/*     */     } else {
/*     */       
/* 520 */       Integer siteId = ActionUtils.parseInt(queryForm.getSite());
/* 521 */       if (siteId == null) {
/* 522 */         siteId = ((Site)grantedSiteList.get(0)).getId();
/* 523 */         queryForm.setSite(siteId.toString());
/*     */       } else {
/* 525 */         checkSite(siteId, request);
/*     */       } 
/* 527 */       request.setAttribute("X_GLOBAL", Boolean.FALSE);
/*     */     } 
/*     */     
/* 530 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 532 */     if (queryForm.isFirstInit()) {
/* 533 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/*     */     } else {
/* 535 */       queryForm.init();
/*     */     } 
/* 537 */     int pageNo = queryForm.getPageNoAsInt();
/* 538 */     int pageSize = queryForm.getPageSizeAsInt();
/* 539 */     List result = fm.getStorageLocationList(conditions, -1, -1, 
/* 540 */         StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 542 */     String array = request.getParameter("array");
/* 543 */     if (!array.equals("")) {
/* 544 */       List<StorageLocation> list = new ArrayList();
/* 545 */       String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 546 */       for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 547 */         StorageLocation location = fm.getStorageLocation(Integer.valueOf(Integer.parseInt(id)));
/* 548 */         list.add(location); b++; }
/*     */       
/* 550 */       request.setAttribute("X_RESULTLIST", list);
/*     */     } else {
/* 552 */       request.setAttribute("X_RESULTLIST", result);
/*     */     } 
/*     */     
/* 555 */     putEnumListToRequest(request);
/* 556 */     request.setAttribute("path", request.getContextPath());
/* 557 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward checkLocationCodeAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 563 */     response.setContentType("text/json");
/* 564 */     response.setCharacterEncoding("UTF-8");
/* 565 */     JsonConfig cfg = new JsonConfig();
/* 566 */     String code = request.getParameter("code");
/* 567 */     boolean sign = false;
/* 568 */     StorageLocationManager locationManager = ServiceLocator.getStorageLocationManager(request);
/* 569 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 570 */     map.put(StorageLocationQueryCondition.CODE_EQ, code);
/* 571 */     List list = locationManager.getStorageLocationList(map, -1, -1, null, true);
/* 572 */     if (list.size() == 0 || list == null) {
/* 573 */       sign = true;
/*     */     }
/*     */     
/* 576 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(sign), cfg);
/* 577 */     response.getWriter().print(jo);
/* 578 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 584 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 585 */     String array = request.getParameter("array");
/* 586 */     List<StorageLocation> list = new ArrayList();
/* 587 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 588 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 589 */       StorageLocation location = fm.getStorageLocation(Integer.valueOf(Integer.parseInt(id)));
/* 590 */       list.add(location);
/*     */       b++; }
/*     */     
/* 593 */     request.setAttribute("X_RESULTLIST", list);
/* 594 */     request.setAttribute("path", request.getContextPath());
/* 595 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward lookForLocationQtyByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 601 */     response.setContentType("text/json");
/* 602 */     response.setCharacterEncoding("UTF-8");
/* 603 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 605 */     String code = request.getParameter("str");
/* 606 */     StorageLocationManager manager = ServiceLocator.getStorageLocationManager(request);
/* 607 */     Map map = manager.lookForLocationQtyByAjax(code);
/*     */     
/* 609 */     JSONObject jo = JSONObject.fromObject(map, cfg);
/* 610 */     response.getWriter().print(jo);
/* 611 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward selectStorageLocationType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 616 */     StorageLocationQueryForm queryForm = (StorageLocationQueryForm)form;
/* 617 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 618 */       queryForm.setOrder(StorageLocationQueryOrder.ID.getName());
/* 619 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 622 */     StorageLocationManager fm = ServiceLocator.getStorageLocationManager(request);
/* 623 */     InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
/* 624 */     Map<InventoryQueryCondition, Object[]> conditions = constructQueryMap(queryForm);
/* 625 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 626 */     int pageNo = queryForm.getPageNoAsInt();
/* 627 */     int pageSize = queryForm.getPageSizeAsInt();
/* 628 */     if (queryForm.isFirstInit()) {
/* 629 */       queryForm.init(fm.getStorageLocationListCount(conditions));
/* 630 */       queryForm.setCount((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString());
/* 631 */       if (Integer.parseInt((new StringBuilder(String.valueOf(fm.getStorageLocationListCount(conditions)))).toString()) / Integer.parseInt((new StringBuilder(String.valueOf(pageSize))).toString()) <= pageNo) {
/* 632 */         pageNo = 0;
/* 633 */         queryForm.setPageNo("0");
/*     */       } 
/* 635 */       queryForm.setPageSize((new StringBuilder(String.valueOf(pageSize))).toString());
/*     */     } else {
/* 637 */       queryForm.init();
/*     */     } 
/*     */ 
/*     */     
/* 641 */     List<StorageLocation> result = fm.getStorageLocationList(conditions, pageNo, pageSize, StorageLocationQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 664 */     Object[] parama = new Object[result.size()];
/* 665 */     for (int j0 = 0; j0 < result.size(); j0++) {
/* 666 */       StorageLocation location = result.get(j0);
/* 667 */       parama[j0] = location.getId();
/*     */     } 
/* 669 */     conditions.clear();
/* 670 */     conditions.put(InventoryQueryCondition.LOCATION_ID_IN, parama);
/* 671 */     List<InventoryDetial> datialList = inventoryManager.getInventoryDetialList(conditions, 0, -1, null, false);
/* 672 */     for (StorageLocation location : result) {
/* 673 */       for (InventoryDetial inventoryDetial : datialList) {
/* 674 */         if (location.getCode().equals(inventoryDetial.getLocation().getCode())) {
/* 675 */           BigDecimal count = location.getNumber().add(inventoryDetial.getNumber());
/* 676 */           location.setNumber(count);
/*     */         } 
/*     */       } 
/*     */     } 
/* 680 */     StoreRoomManager manager = ServiceLocator.getStoreRoomManager(request);
/* 681 */     request.setAttribute("x_stromList", manager.getStoreRoomListEnabledAll());
/* 682 */     request.setAttribute("X_RESULTLIST", result);
/* 683 */     putEnumListToRequest(request);
/*     */     
/* 685 */     String type = request.getParameter("type");
/* 686 */     if (type != null && type.equals("1")) {
/* 687 */       request.setAttribute("x_selType", Integer.valueOf(37));
/*     */     }
/* 689 */     request.setAttribute("x_selType", Integer.valueOf(37));
/* 690 */     request.setAttribute("x_selType", Integer.valueOf(37));
/* 691 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/StorageLocationAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */