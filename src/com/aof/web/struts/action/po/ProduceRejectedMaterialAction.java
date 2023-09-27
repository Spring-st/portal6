/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.ProduceRejectedMaterial;
/*     */ import com.aof.model.po.PurchaseOrderRqcUnqualified;
/*     */ import com.aof.model.po.query.ProduceRejectedMaterialQueryCondition;
/*     */ import com.aof.model.po.query.ProduceRejectedMaterialQueryOrder;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.ProduceRejectedMaterialManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProduceRejectedMaterialQueryForm;
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
/*     */ public class ProduceRejectedMaterialAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  79 */     ProduceRejectedMaterialQueryForm queryForm = (ProduceRejectedMaterialQueryForm)form;
/*  80 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  81 */       queryForm.setOrder(ProduceRejectedMaterialQueryOrder.ID.getName());
/*  82 */       queryForm.setDescend(true);
/*     */     } 
/*  84 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  85 */     ProduceRejectedMaterialManager fm = ServiceLocator.getProduceRejectedMaterialManager(request);
/*  86 */     Map conditions = constructQueryMap(queryForm);
/*  87 */     conditions.put(ProduceRejectedMaterialQueryCondition.TYPE_EQ, Integer.valueOf(2));
/*  88 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  90 */     String exportType = queryForm.getExportType();
/*  91 */     if (StringUtils.isNotEmpty(exportType)) {
/*  92 */       List<ProduceRejectedMaterial> data = fm.getProduceRejectedMaterialList(conditions, 0, -1, ProduceRejectedMaterialQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  93 */       for (ProduceRejectedMaterial produceRejectedMaterial : data) {
/*  94 */         List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/*  95 */         produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */       } 
/*  97 */       int index = SessionTempFile.createNewTempFile(request);
/*  98 */       String fileName = "ProduceRejectedMaterial";
/*  99 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 102 */               MessageResources messages = ProduceRejectedMaterialAction.this.getResources(request);
/* 103 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.po_supp"));
/* 104 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.lot.id"));
/* 105 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.part.id"));
/* 106 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.date"));
/* 107 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.createUser.loginName"));
/* 108 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.qty"));
/* 109 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.location.code"));
/* 110 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.status_rqc.chnShortDescription"));
/* 111 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.returnReasons.describe"));
/* 112 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.isPrint"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 116 */               ProduceRejectedMaterial produceRejectedMaterial = (ProduceRejectedMaterial)data;
/* 117 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.po_supp"));
/* 118 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.lot.id"));
/* 119 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.part.id"));
/* 120 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 121 */               row.add(BeanHelper.getBeanPropertyValue(data, "createUser.loginName"));
/* 122 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 123 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 124 */               if (BeanHelper.getBeanPropertyValue(data, "box.status_rqc.chnShortDescription") != null) {
/* 125 */                 row.add(BeanHelper.getBeanPropertyValue(data, "box.status_rqc.chnShortDescription"));
/*     */               } else {
/* 127 */                 row.add("待检");
/*     */               } 
/* 129 */               String returnReasons = "";
/* 130 */               List<PurchaseOrderRqcUnqualified> list = produceRejectedMaterial.getBox().getUnqualifiedList();
/* 131 */               for (int i = 0; i < list.size(); i++) {
/* 132 */                 PurchaseOrderRqcUnqualified purchaseOrderRqcUnqualified = list.get(i);
/* 133 */                 if (i == 0) {
/* 134 */                   returnReasons = String.valueOf(returnReasons) + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*     */                 } else {
/* 136 */                   returnReasons = String.valueOf(returnReasons) + "," + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*     */                 } 
/*     */               } 
/* 139 */               row.add(returnReasons);
/* 140 */               if (BeanHelper.getBeanPropertyValue(data, "isPrint") == "0") {
/* 141 */                 row.add("已打印");
/*     */               } else {
/* 143 */                 row.add("未打印");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 148 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 150 */     if (queryForm.isFirstInit()) {
/* 151 */       queryForm.init(fm.getProduceRejectedMaterialListCount(conditions));
/*     */     } else {
/* 153 */       queryForm.init();
/*     */     } 
/* 155 */     List<ProduceRejectedMaterial> result = fm.getProduceRejectedMaterialList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), ProduceRejectedMaterialQueryOrder.ID, queryForm.isDescend());
/* 156 */     for (ProduceRejectedMaterial produceRejectedMaterial : result) {
/* 157 */       List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/* 158 */       produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */     } 
/* 160 */     putEnumListToRequest(request);
/* 161 */     request.setAttribute("X_RESULTLIST", result);
/* 162 */     request.setAttribute("x_selType", Integer.valueOf(85));
/* 163 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listBefore(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 168 */     ProduceRejectedMaterialQueryForm queryForm = (ProduceRejectedMaterialQueryForm)form;
/* 169 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 170 */       queryForm.setOrder(ProduceRejectedMaterialQueryOrder.ID.getName());
/* 171 */       queryForm.setDescend(true);
/*     */     } 
/*     */     
/* 174 */     ProduceRejectedMaterialManager fm = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 175 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 176 */     Map conditions = constructQueryMap(queryForm);
/* 177 */     conditions.put(ProduceRejectedMaterialQueryCondition.TYPE_EQ, Integer.valueOf(1));
/* 178 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/* 180 */     String exportType = queryForm.getExportType();
/* 181 */     if (StringUtils.isNotEmpty(exportType)) {
/* 182 */       List<ProduceRejectedMaterial> data = fm.getProduceRejectedMaterialList(conditions, 0, -1, ProduceRejectedMaterialQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 183 */       for (ProduceRejectedMaterial produceRejectedMaterial : data) {
/* 184 */         List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/* 185 */         produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */       } 
/* 187 */       int index = SessionTempFile.createNewTempFile(request);
/* 188 */       String fileName = "ProduceRejectedMaterial";
/* 189 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 192 */               MessageResources messages = ProduceRejectedMaterialAction.this.getResources(request);
/* 193 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.po_supp"));
/* 194 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.lot.id"));
/* 195 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.part.id"));
/* 196 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.date"));
/* 197 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.createUser.loginName"));
/* 198 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.qty"));
/* 199 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.location.code"));
/* 200 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.box.status_rqc.chnShortDescription"));
/* 201 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.returnReasons.describe"));
/* 202 */               row.add(messages.getMessage(ProduceRejectedMaterialAction.this.getLocale(request), "ProduceRejectedMaterial.isPrint"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 206 */               ProduceRejectedMaterial produceRejectedMaterial = (ProduceRejectedMaterial)data;
/* 207 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.po_supp"));
/* 208 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.lot.id"));
/* 209 */               row.add(BeanHelper.getBeanPropertyValue(data, "box.part.id"));
/* 210 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 211 */               row.add(BeanHelper.getBeanPropertyValue(data, "createUser.loginName"));
/* 212 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/* 213 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 214 */               if (BeanHelper.getBeanPropertyValue(data, "box.status_rqc.chnShortDescription") != null) {
/* 215 */                 row.add(BeanHelper.getBeanPropertyValue(data, "box.status_rqc.chnShortDescription"));
/*     */               } else {
/* 217 */                 row.add("待检");
/*     */               } 
/* 219 */               String returnReasons = "";
/* 220 */               List<PurchaseOrderRqcUnqualified> list = produceRejectedMaterial.getBox().getUnqualifiedList();
/* 221 */               for (int i = 0; i < list.size(); i++) {
/* 222 */                 PurchaseOrderRqcUnqualified purchaseOrderRqcUnqualified = list.get(i);
/* 223 */                 if (i == 0) {
/* 224 */                   returnReasons = String.valueOf(returnReasons) + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*     */                 } else {
/* 226 */                   returnReasons = String.valueOf(returnReasons) + "," + purchaseOrderRqcUnqualified.getReasons().getDescribe();
/*     */                 } 
/*     */               } 
/* 229 */               row.add(returnReasons);
/* 230 */               if (BeanHelper.getBeanPropertyValue(data, "isPrint") == "0") {
/* 231 */                 row.add("已打印");
/*     */               } else {
/* 233 */                 row.add("未打印");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 238 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 240 */     if (queryForm.isFirstInit()) {
/* 241 */       queryForm.init(fm.getProduceRejectedMaterialListCount(conditions));
/*     */     } else {
/* 243 */       queryForm.init();
/*     */     } 
/* 245 */     List<ProduceRejectedMaterial> result = fm.getProduceRejectedMaterialList(conditions, 
/* 246 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 247 */         ProduceRejectedMaterialQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 249 */     putEnumListToRequest(request);
/* 250 */     for (ProduceRejectedMaterial produceRejectedMaterial : result) {
/* 251 */       List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/* 252 */       produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */     } 
/* 254 */     request.setAttribute("X_RESULTLIST", result);
/* 255 */     request.setAttribute("x_selType", Integer.valueOf(128));
/* 256 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ProduceRejectedMaterialQueryForm queryForm) {
/* 260 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 261 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 262 */     if (id != null && !id.equals("")) {
/* 263 */       conditions.put(ProduceRejectedMaterialQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 266 */     return conditions;
/*     */   }
/*     */   
/*     */   private ProduceRejectedMaterial getProduceRejectedMaterial(HttpServletRequest request) throws Exception {
/* 270 */     String id = request.getParameter("id");
/* 271 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 272 */     ProduceRejectedMaterial item = manager.getProduceRejectedMaterial(Integer.valueOf(Integer.parseInt(id)));
/* 273 */     if (item == null) {
/* 274 */       throw new ActionException("item.notFound", id);
/*     */     }
/* 276 */     return item;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 280 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 281 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 297 */     DepartmentManager departmentManager = ServiceLocator.getDepartmentManager(request);
/* 298 */     Site site = getCurrentUser(request).getPrimarySite();
/* 299 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 300 */     List<Department> list = departmentManager.getDepartmentList(map, -1, -1, null, true);
/* 301 */     if (!isBack(request)) {
/* 302 */       ProduceRejectedMaterial ProduceRejectedMaterial = new ProduceRejectedMaterial();
/* 303 */       BeanForm ProduceRejectedMaterialForm = (BeanForm)getForm("/insertProduceRejectedMaterial", request);
/* 304 */       ProduceRejectedMaterialForm.populate(ProduceRejectedMaterial, "to_form");
/*     */     } 
/*     */     
/* 307 */     request.setAttribute("x_listDept", list);
/* 308 */     putEnumListToRequest(request);
/* 309 */     return mapping.findForward("page");
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
/* 325 */     ProduceRejectedMaterialManager cm = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 326 */     BeanForm ProduceRejectedMaterialForm = (BeanForm)form;
/* 327 */     ProduceRejectedMaterial ProduceRejectedMaterial = new ProduceRejectedMaterial();
/* 328 */     ProduceRejectedMaterialForm.populate(ProduceRejectedMaterial, "to_bean");
/*     */     
/* 330 */     cm.insertProduceRejectedMaterial(ProduceRejectedMaterial);
/* 331 */     return new ActionForward("editProduceRejectedMaterial.do?id=" + ProduceRejectedMaterial.getId(), true);
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
/* 347 */     ProduceRejectedMaterial material = getProduceRejectedMaterial(request);
/*     */     
/* 349 */     if (!isBack(request)) {
/* 350 */       BeanForm ProduceRejectedMaterialItemForm = (BeanForm)getForm("/updateProduceRejectedMaterialItem", request);
/* 351 */       ProduceRejectedMaterialItemForm.populate(material, "to_form");
/*     */     } 
/*     */     
/* 354 */     request.setAttribute("x_material", material);
/* 355 */     putEnumListToRequest(request);
/* 356 */     return mapping.findForward("page");
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
/*     */   public ActionForward poRejectedMaterialBoxByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 371 */     response.setContentType("text/json");
///* 372 */     response.setCharacterEncoding("UTF-8");
/* 373 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 375 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 376 */     String array = request.getParameter("array");
/* 377 */     String str = manager.updateProduceRejectedMaterial(array, getCurrentUser(request));
/*     */     
/* 379 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 380 */     response.getWriter().print(jo);
/* 381 */     return null;
/*     */   }
/*     */ 
/*     */   public ActionForward RecordPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 388 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 389 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 390 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 391 */     String str = request.getParameter("array");
/* 392 */     String[] arr = str.split(",");
/* 393 */     List<ProduceRejectedMaterial> produceRejectedMaterialList = new ArrayList<ProduceRejectedMaterial>(); byte b; int i; String[] arrayOfString1;
/* 394 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 395 */       ProduceRejectedMaterial produceRejectedMaterial = manager.getProduceRejectedMaterial(Integer.valueOf(Integer.parseInt(id)));
/* 396 */       produceRejectedMaterialList.add(produceRejectedMaterial); b++; }
/*     */     
/* 398 */     if (produceRejectedMaterialList.size() > 0) {
/* 399 */       Supplier supplier = ((ProduceRejectedMaterial)produceRejectedMaterialList.get(0)).getBox().getPsoItem().getPoipItem().getPoip_number().getSupplier();
/* 400 */       request.setAttribute("X_SUPPLIER", supplier);
/*     */     } 
/* 402 */     Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
/* 403 */     for (ProduceRejectedMaterial produceRejectedMaterial : produceRejectedMaterialList) {
/* 404 */       String poipNumber = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getPoip_number().getPoip_number();
/* 405 */       String line = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getLine();
/* 406 */       String sta = String.valueOf(poipNumber) + line;
/* 407 */       BigDecimal returnNumbers = produceRejectedMaterial.getBox().getNumber();
/* 408 */       if (map.get(sta) == null) {
/* 409 */         map.put(sta, returnNumbers); continue;
/*     */       } 
/* 411 */       BigDecimal count = map.get(sta);
/* 412 */       count = count.add(returnNumbers);
/* 413 */       map.put(sta, count);
/*     */     } 
/*     */     
/* 416 */     for (ProduceRejectedMaterial produceRejectedMaterial : produceRejectedMaterialList) {
/* 417 */       String poipNumber = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getPoip_number().getPoip_number();
/* 418 */       String line = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getLine();
/* 419 */       String sta = String.valueOf(poipNumber) + line;
/* 420 */       BigDecimal returnNumbers = map.get(sta);
/* 421 */       produceRejectedMaterial.setReturnNumber(returnNumbers);
/*     */     } 
/* 423 */     fm.getPurchaseOrderInspectionPendingItemByPrintReport(produceRejectedMaterialList);
/* 424 */     for (ProduceRejectedMaterial produceRejectedMaterial : produceRejectedMaterialList) {
/* 425 */       List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/* 426 */       produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */     } 
/* 428 */     request.setAttribute("X_REJECTEDMATERIALID", str);
/* 429 */     request.setAttribute("path", request.getContextPath());
/* 430 */     request.setAttribute("X_RESULTLIST", produceRejectedMaterialList);
/*     */ 
/*     */     
/* 433 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward RecordBeforePrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 440 */     PurchaseOrderInspectionPendingManager fm = ServiceLocator.getPurchaseOrderInspectionPendingManager(request);
/* 441 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 442 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 443 */     String str = request.getParameter("array");
/* 444 */     String[] arr = str.split(",");
/* 445 */     List<ProduceRejectedMaterial> produceRejectedMaterialList = new ArrayList<ProduceRejectedMaterial>(); byte b; int i; String[] arrayOfString1;
/* 446 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 447 */       ProduceRejectedMaterial produceRejectedMaterial = manager.getProduceRejectedMaterial(Integer.valueOf(Integer.parseInt(id)));
/* 448 */       produceRejectedMaterialList.add(produceRejectedMaterial); b++; }
/*     */     
/* 450 */     if (produceRejectedMaterialList.size() > 0) {
/* 451 */       Supplier supplier = ((ProduceRejectedMaterial)produceRejectedMaterialList.get(0)).getBox().getPsoItem().getPoipItem().getPoip_number().getSupplier();
/* 452 */       request.setAttribute("X_SUPPLIER", supplier);
/*     */     } 
/* 454 */     Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
/* 455 */     for (ProduceRejectedMaterial produceRejectedMaterial : produceRejectedMaterialList) {
/* 456 */       String poipNumber = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getPoip_number().getPoip_number();
/* 457 */       String line = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getLine();
/* 458 */       String partId = produceRejectedMaterial.getBox().getPart().getId();
/* 459 */       String sta = String.valueOf(poipNumber) + line + partId;
/*     */       
/* 461 */       BigDecimal returnNumbers = produceRejectedMaterial.getBox().getNumber();
/* 462 */       if (map.get(sta) == null) {
/* 463 */         map.put(sta, returnNumbers); continue;
/*     */       } 
/* 465 */       BigDecimal count = map.get(sta);
/* 466 */       count = count.add(returnNumbers);
/* 467 */       map.put(sta, count);
/*     */     } 
/*     */     
/* 470 */     Map<String, ProduceRejectedMaterial> maps = new HashMap<String, ProduceRejectedMaterial>();
/* 471 */     for (ProduceRejectedMaterial produceRejectedMaterial : produceRejectedMaterialList) {
/* 472 */       String poipNumber = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getPoip_number().getPoip_number();
/* 473 */       String line = produceRejectedMaterial.getBox().getPsoItem().getPoipItem().getLine();
/* 474 */       String partId = produceRejectedMaterial.getBox().getPart().getId();
/* 475 */       String sta = String.valueOf(poipNumber) + line + partId;
/* 476 */       BigDecimal returnNumbers = map.get(sta);
/* 477 */       produceRejectedMaterial.setReturnNumber(returnNumbers);
/* 478 */       maps.put(sta, produceRejectedMaterial);
/*     */     } 
/* 480 */     List<ProduceRejectedMaterial> prmList = new ArrayList<ProduceRejectedMaterial>();
/* 481 */     for (String sta : maps.keySet()) {
/* 482 */       ProduceRejectedMaterial prm = maps.get(sta);
/* 483 */       prmList.add(prm);
/*     */     } 
/* 485 */     fm.getPurchaseOrderInspectionPendingItemByPrintReport(produceRejectedMaterialList);
/* 486 */     for (ProduceRejectedMaterial produceRejectedMaterial : prmList) {
/* 487 */       List<PurchaseOrderRqcUnqualified> list = boxManager.getPurchaseOrderRqcUnqualifiedList(produceRejectedMaterial.getBox().getId());
/* 488 */       produceRejectedMaterial.getBox().setUnqualifiedList(list);
/*     */     } 
/* 490 */     request.setAttribute("X_REJECTEDMATERIALID", str);
/* 491 */     request.setAttribute("path", request.getContextPath());
/*     */     
/* 493 */     request.setAttribute("X_RESULTLIST", prmList);
/*     */ 
/*     */     
/* 496 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward updateRejectedMaterialPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 501 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 502 */     String str = request.getParameter("id");
/* 503 */     String[] arr = str.split(","); byte b; int i; String[] arrayOfString1;
/* 504 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 505 */       ProduceRejectedMaterial produceRejectedMaterial = manager.getProduceRejectedMaterial(Integer.valueOf(Integer.parseInt(id)));
/* 506 */       produceRejectedMaterial.setIsPrint(YesNo.YES);
/* 507 */       manager.updateProduceRejectedMaterial(produceRejectedMaterial);
/*     */       b++; }
/*     */     
/* 510 */     return new ActionForward("listProduceRejectedMaterial.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward updateRejectedMaterialBeforePrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 515 */     ProduceRejectedMaterialManager manager = ServiceLocator.getProduceRejectedMaterialManager(request);
/* 516 */     String str = request.getParameter("id");
/* 517 */     String[] arr = str.split(","); byte b; int i; String[] arrayOfString1;
/* 518 */     for (i = (arrayOfString1 = arr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 519 */       ProduceRejectedMaterial produceRejectedMaterial = manager.getProduceRejectedMaterial(Integer.valueOf(Integer.parseInt(id)));
/* 520 */       produceRejectedMaterial.setIsPrint(YesNo.YES);
/* 521 */       manager.updateProduceRejectedMaterial(produceRejectedMaterial);
/*     */       b++; }
/*     */     
/* 524 */     return new ActionForward("listProduceRejectedMaterialBefore.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/ProduceRejectedMaterialAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */