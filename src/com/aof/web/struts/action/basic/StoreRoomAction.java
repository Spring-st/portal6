/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.basic.query.StoreRoomQueryCondition;
/*     */ import com.aof.model.basic.query.StoreRoomQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.basic.StoreRoomManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.StoreRoomQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StoreRoomAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  82 */     StoreRoomQueryForm queryForm = (StoreRoomQueryForm)form;
/*  83 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  84 */       queryForm.setOrder(StoreRoomQueryOrder.ID.getName());
/*  85 */       queryForm.setDescend(false);
/*  86 */       queryForm.setStatus("0");
/*     */     } 
/*     */     
/*  89 */     StoreRoomManager fm = ServiceLocator.getStoreRoomManager(request);
/*  90 */     Map conditions = constructQueryMap(queryForm);
/*  91 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  93 */     String exportType = queryForm.getExportType();
/*  94 */     if (StringUtils.isNotEmpty(exportType)) {
/*  95 */       List data = fm.getStoreRoomList(conditions, 0, -1, StoreRoomQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  97 */       int index = SessionTempFile.createNewTempFile(request);
/*  98 */       String fileName = "storeRoom";
/*  99 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 102 */               MessageResources messages = StoreRoomAction.this.getResources(request);
/* 103 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.code"));
/* 104 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.kfname"));
/* 105 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.address"));
/* 106 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.type"));
/* 107 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.date"));
/* 108 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.largest_inventory"));
/* 109 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.status.color"));
/* 110 */               row.add(messages.getMessage(StoreRoomAction.this.getLocale(request), "storeRoom.remark"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 114 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/* 115 */               row.add(BeanHelper.getBeanPropertyValue(data, "name"));
/* 116 */               row.add(BeanHelper.getBeanPropertyValue(data, "address"));
/* 117 */               row.add(BeanHelper.getBeanPropertyValue(data, "type"));
/* 118 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 119 */               row.add(BeanHelper.getBeanPropertyValue(data, "largest_inventory"));
/* 120 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/* 121 */               row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
/*     */             }
/*     */           });
/*     */       
/* 125 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 127 */     if (queryForm.isFirstInit()) {
/* 128 */       queryForm.init(fm.getStoreRoomListCount(conditions));
/*     */     } else {
/* 130 */       queryForm.init();
/*     */     } 
/* 132 */     List result = fm.getStoreRoomList(conditions, 
/* 133 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 134 */         StoreRoomQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 136 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 137 */     request.setAttribute("x_siteList", siteList);
/* 138 */     request.setAttribute("X_RESULTLIST", result);
/* 139 */     request.setAttribute("x_selType", Integer.valueOf(10));
/* 140 */     putEnumListToRequest(request);
/* 141 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map constructQueryMap(StoreRoomQueryForm queryForm) {
/* 144 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 145 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 146 */     if (id != null && !id.equals("")) {
/* 147 */       conditions.put(StoreRoomQueryCondition.ID_EQ, id);
/*     */     }
/* 149 */     String address = queryForm.getAddress();
/* 150 */     if (address != null && !address.equals("")) {
/* 151 */       conditions.put(StoreRoomQueryCondition.ADDRESS_EQ, address);
/*     */     }
/* 153 */     String describe = queryForm.getDescribe();
/* 154 */     if (describe != null && !describe.equals("")) {
/* 155 */       conditions.put(StoreRoomQueryCondition.DESCRIBE_EQ, describe);
/*     */     }
/* 157 */     String type = queryForm.getType();
/* 158 */     if (type != null && !type.equals("")) {
/* 159 */       conditions.put(StoreRoomQueryCondition.TYPE_EQ, type);
/*     */     }
/* 161 */     String status = queryForm.getStatus();
/* 162 */     if (status != null && !status.equals("")) {
/* 163 */       conditions.put(StoreRoomQueryCondition.ENABLED_EQ, status);
/*     */     }
/* 165 */     String safetyInventory = queryForm.getSafetyInventory();
/* 166 */     if (safetyInventory != null && !safetyInventory.equals("")) {
/* 167 */       conditions.put(StoreRoomQueryCondition.SAFETYINVENTORY_EQ, safetyInventory);
/*     */     }
/* 169 */     String siteid = queryForm.getSiteId();
/* 170 */     if (siteid != null && !siteid.equals("")) {
/* 171 */       conditions.put(StoreRoomQueryCondition.SITE_EQ, siteid);
/*     */     }
/* 173 */     return conditions;
/*     */   }
/*     */   private StoreRoom getStoreRoom(HttpServletRequest request) throws Exception {
/* 176 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 177 */     StoreRoomManager storeRoomManager = ServiceLocator.getStoreRoomManager(request);
/* 178 */     StoreRoom storeRoom = storeRoomManager.getStoreRoom(id);
/* 179 */     if (storeRoom == null)
/* 180 */       throw new ActionException("storeRoom.notFound", id); 
/* 181 */     return storeRoom;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 185 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 186 */     request.setAttribute("x_STOREROOMTYPELIST", PersistentEnum.getEnumList(StoreRoomType.class));
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
/* 202 */     StoreRoom storeRoom = getStoreRoom(request);
/*     */     
/* 204 */     request.setAttribute("x_storeRoom", storeRoom);
/* 205 */     if (!isBack(request)) {
/*     */       
/* 207 */       BeanForm storeRoomForm = (BeanForm)getForm("/updateStoreRoom", request);
/* 208 */       storeRoomForm.populate(storeRoom, "to_form");
/*     */     } 
/* 210 */     putEnumListToRequest(request);
/* 211 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/* 212 */     request.setAttribute("x_siteList", siteList);
/* 213 */     return mapping.findForward("page");
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
/* 226 */     StoreRoom storeRoom = getStoreRoom(request);
/*     */     
/* 228 */     StoreRoomManager cm = ServiceLocator.getStoreRoomManager(request);
/*     */     try {
/* 230 */       cm.deleteStoreRoom(storeRoom);
/*     */     }
/* 232 */     catch (Throwable t) {
/*     */       
/* 234 */       throw new ActionException("storeRoom.delete.fail");
/*     */     } 
/* 236 */     return mapping.findForward("success");
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
/* 252 */     BeanForm storeRoomForm = (BeanForm)form;
/* 253 */     StoreRoom storeRoom = new StoreRoom();
/* 254 */     storeRoomForm.populate(storeRoom, "to_bean");
/* 255 */     StoreRoomManager storeRoomManager = ServiceLocator.getStoreRoomManager(request);
/* 256 */     storeRoom = storeRoomManager.updateStoreRoom(storeRoom);
/*     */     
/* 258 */     request.setAttribute("X_OBJECT", storeRoom);
/* 259 */     request.setAttribute("X_ROWPAGE", "wmsbasic/storeRoom/row.jsp");
/* 260 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 266 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 270 */     String s = request.getParameter("site_id");
/* 271 */     return (s != null && !s.equals(""));
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 288 */     if (!isBack(request)) {
/* 289 */       StoreRoom storeRoom = new StoreRoom();
/* 290 */       BeanForm storeRoomForm = (BeanForm)getForm("/insertStoreRoom", request);
/* 291 */       storeRoomForm.populate(storeRoom, "to_form");
/*     */     } 
/* 293 */     List<Site> siteList = getGrantedSiteDeparmentList(request);
/*     */     
/* 295 */     request.setAttribute("x_siteList", siteList);
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
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 313 */     Site site = null;
/* 314 */     if (hasSite(request)) {
/* 315 */       site = getSiteFromRequestAndCheckPower(request);
/*     */     } else {
/* 317 */       checkGlobalPower(request);
/*     */     } 
/*     */     
/* 320 */     StoreRoomManager cm = ServiceLocator.getStoreRoomManager(request);
/* 321 */     BeanForm storeRoomForm = (BeanForm)form;
/* 322 */     StoreRoom storeRoom = new StoreRoom();
/* 323 */     storeRoomForm.populate(storeRoom, "to_bean");
/* 324 */     storeRoom = cm.insertStoreRoom(storeRoom);
/*     */     
/* 326 */     request.setAttribute("X_OBJECT", storeRoom);
/* 327 */     request.setAttribute("X_ROWPAGE", "wmsbasic/storeRoom/row.jsp");
/* 328 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward getStoreRoomListBySite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*     */     try {
/* 335 */       List<Map> boxList = new ArrayList<Map>();
/* 336 */       response.setContentType("text/json");
/* 337 */       response.setCharacterEncoding("UTF-8");
/* 338 */       JsonConfig cfg = new JsonConfig();
/*     */       
/* 340 */       PrintWriter out = response.getWriter();
/* 341 */       SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
/*     */       
/* 343 */       SiteManager sm = ServiceLocator.getSiteManager(request);
/* 344 */       Site site = sm.getSite(Integer.valueOf(Integer.parseInt(request.getParameter("site"))));
/* 345 */       Map<Object, Object> condtions = new HashMap<Object, Object>();
/* 346 */       condtions.put(StoreRoomQueryCondition.SITE_EQ, site.getId());
/* 347 */       List<StoreRoom> srlist = ServiceLocator.getStoreRoomManager(request).getStoreRoomList(condtions, -1, -1, null, true);
/* 348 */       for (StoreRoom sr : srlist) {
/* 349 */         Map<Object, Object> map = new HashMap<Object, Object>();
/* 350 */         map.put("property", sr.getId());
/*     */         
/* 352 */         boxList.add(map);
/*     */       } 
/* 354 */       if (srlist.size() == 0) {
/* 355 */         Map<Object, Object> map = new HashMap<Object, Object>();
/* 356 */         map.put("property", "");
/* 357 */         map.put("labelProperty", "null");
/* 358 */         boxList.add(map);
/*     */       } 
/* 360 */       JSONArray jo = JSONArray.fromObject(boxList, cfg);
/* 361 */       response.getWriter().print(jo);
/* 362 */     } catch (Exception e) {
/* 363 */       e.fillInStackTrace();
/*     */     } 
/* 365 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/StoreRoomAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */