/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.schedule.JitProductionPlan;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryCondition;
/*     */ import com.aof.model.schedule.query.JitProductionPlanQueryOrder;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*     */ import com.aof.service.schedule.EdiProductionManager;
/*     */ import com.aof.service.schedule.JitProductionPlanManager;
/*     */ import com.aof.service.schedule.ProjectedInventoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.JitProductionPlanQueryForm;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JsonConfig;
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
/*     */ public class JitProductionPlanAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private Map getConditions(JitProductionPlanQueryForm formBean) {
/*  65 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  66 */     String str = "";
/*     */     
/*  68 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listMergerPlanSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  74 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/*  75 */     ProjectedInventoryManager inventoryManager = ServiceLocator.getProjectedInventoryManager(request);
/*  76 */     JitProductionPlanQueryForm queryForm = (JitProductionPlanQueryForm)form;
/*  77 */     List<JitProductionPlan> planLists = new ArrayList<JitProductionPlan>();
/*  78 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*  79 */     Map conditions = getConditions(queryForm);
/*  80 */     if (queryForm.getOrder() == "") {
/*  81 */       queryForm.setDescend(true);
/*     */     }
/*  83 */     getConditionAndOrder(queryForm, conditions, request);
/*     */     
/*  85 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/*  86 */     conditions.put(JitProductionPlanQueryCondition.DATE_GE, format.format(new Date()));
/*  87 */     conditions.put(JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/*  88 */     String exportType = queryForm.getExportType();
/*  89 */     if (exportType != null && exportType.length() > 0) {
/*  90 */       List datas = manager.getList(conditions, 0, -1, 
/*  91 */           JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  92 */       int index = SessionTempFile.createNewTempFile(request);
/*  93 */       String fileName = "BanChengPinJiHuaHeBing";
/*  94 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  95 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  96 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 100 */               row.add(BeanUtils.getProperty(data, "productionId.asnNo"));
/* 101 */               row.add(BeanUtils.getProperty(data, "childPart.id"));
/* 102 */               row.add(BeanUtils.getProperty(data, "childPart.describe1"));
/* 103 */               row.add(BeanUtils.getProperty(data, "childPart.unit"));
/* 104 */               row.add(BeanUtils.getProperty(data, "childPart.productClass"));
/* 105 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 106 */               row.add(BeanUtils.getProperty(data, "date"));
/* 107 */               row.add(BeanUtils.getProperty(data, "hour2DemandQty"));
/* 108 */               row.add(BeanUtils.getProperty(data, "hour4DemandQty"));
/* 109 */               row.add(BeanUtils.getProperty(data, "hour6DemandQty"));
/* 110 */               row.add(BeanUtils.getProperty(data, "hour8DemandQty"));
/* 111 */               row.add(BeanUtils.getProperty(data, "hour10DemandQty"));
/* 112 */               row.add(BeanUtils.getProperty(data, "hour12DemandQty"));
/* 113 */               row.add(BeanUtils.getProperty(data, "hour14DemandQty"));
/* 114 */               row.add(BeanUtils.getProperty(data, "hour16DemandQty"));
/* 115 */               row.add(BeanUtils.getProperty(data, "hour18DemandQty"));
/* 116 */               row.add(BeanUtils.getProperty(data, "hour20DemandQty"));
/* 117 */               row.add(BeanUtils.getProperty(data, "hour22DemandQty"));
/* 118 */               row.add(BeanUtils.getProperty(data, "hour24DemandQty"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 122 */               MessageResources message = JitProductionPlanAction.this.getResources(request);
/* 123 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "ediproduction.asnno"));
/* 124 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.id"));
/* 125 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.describe1"));
/* 126 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "ediproduction.unit"));
/* 127 */               row.add("产品类");
/* 128 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "projectedinventory.currentqty"));
/* 129 */               row.add("任务日期");
/* 130 */               row.add("2小时需求数量");
/* 131 */               row.add("4小时需求数量");
/* 132 */               row.add("6小时需求数量");
/* 133 */               row.add("8小时需求数量");
/* 134 */               row.add("10小时需求数量");
/* 135 */               row.add("12小时需求数量");
/* 136 */               row.add("14小时需求数量");
/* 137 */               row.add("16小时需求数量");
/* 138 */               row.add("18小时需求数量");
/* 139 */               row.add("20小时需求数量");
/* 140 */               row.add("22小时需求数量");
/* 141 */               row.add("24小时需求数量");
/*     */             }
/*     */           });
/* 144 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 146 */     if (queryForm.isFirstInit()) {
/* 147 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 149 */       queryForm.init();
/*     */     } 
/* 151 */     int pageNum = queryForm.getPageNoAsInt();
/* 152 */     int pageSize = queryForm.getPageSizeAsInt();
/* 153 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 154 */         JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), 
/* 155 */         queryForm.isDescend());
/*     */     
/* 157 */     for (Object object : entityList) {
/* 158 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 159 */       JitProductionPlan jit = (JitProductionPlan)object;
/* 160 */       map.put(ProjectedInventoryQueryCondition.PART_ID_EQ, jit.getChildPart().getId());
/* 161 */       List<ProjectedInventory> toryList = inventoryManager.getList(map, 0, -1, null, false);
/* 162 */       if (toryList.size() == 0) {
/* 163 */         jit.setCurrentQty(new BigDecimal(0));
/*     */       } else {
/* 165 */         jit.setCurrentQty(((ProjectedInventory)toryList.get(0)).getCurrentQty());
/*     */       } 
/* 167 */       planLists.add(jit);
/*     */     } 
/*     */     
/* 170 */     request.setAttribute("x_selType", Integer.valueOf(164));
/* 171 */     request.setAttribute("X_RESULTLIST", planLists);
/* 172 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listPurchaseMerge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 177 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 178 */     ProjectedInventoryManager inventoryManager = ServiceLocator.getProjectedInventoryManager(request);
/* 179 */     JitProductionPlanQueryForm queryForm = (JitProductionPlanQueryForm)form;
/* 180 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 181 */     Map conditions = getConditions(queryForm);
/* 182 */     if (queryForm.getOrder() == "") {
/* 183 */       queryForm.setOrder("date");
/* 184 */       queryForm.setDescend(false);
/*     */     } 
/* 186 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 187 */     if (!hasGlobalPower(request)) {
/* 188 */       User user = getCurrentUser(request);
/* 189 */       conditions.put(JitProductionPlanQueryCondition.PARTID_VEND_EQ, user.getLoginName());
/*     */     } 
/*     */     
/* 192 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/* 193 */     conditions.put(JitProductionPlanQueryCondition.DATE_GE, format.format(new Date()));
/* 194 */     conditions.put(JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/* 195 */     String exportType = queryForm.getExportType();
/* 196 */     if (exportType != null && exportType.length() > 0) {
/* 197 */       List datas = manager.getList(conditions, 0, -1, 
/* 198 */           JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 199 */       int index = SessionTempFile.createNewTempFile(request);
/* 200 */       String fileName = "BanChengPinJiHuaHeBing";
/* 201 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 202 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 203 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 207 */               row.add(BeanUtils.getProperty(data, "childPart.id"));
/* 208 */               row.add(BeanUtils.getProperty(data, "childPart.describe1"));
/* 209 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 210 */               row.add(BeanUtils.getProperty(data, "childPart.securityQty"));
/* 211 */               row.add(BeanUtils.getProperty(data, "childPart.highQty"));
/* 212 */               row.add(BeanUtils.getProperty(data, "childPart.lowQty"));
/* 213 */               row.add(BeanUtils.getProperty(data, "date"));
/* 214 */               row.add(BeanUtils.getProperty(data, "hour2DemandQty"));
/* 215 */               row.add(BeanUtils.getProperty(data, "hour4DemandQty"));
/* 216 */               row.add(BeanUtils.getProperty(data, "hour6DemandQty"));
/* 217 */               row.add(BeanUtils.getProperty(data, "hour8DemandQty"));
/* 218 */               row.add(BeanUtils.getProperty(data, "hour10DemandQty"));
/* 219 */               row.add(BeanUtils.getProperty(data, "hour12DemandQty"));
/* 220 */               row.add(BeanUtils.getProperty(data, "hour14DemandQty"));
/* 221 */               row.add(BeanUtils.getProperty(data, "hour16DemandQty"));
/* 222 */               row.add(BeanUtils.getProperty(data, "hour18DemandQty"));
/* 223 */               row.add(BeanUtils.getProperty(data, "hour20DemandQty"));
/* 224 */               row.add(BeanUtils.getProperty(data, "hour22DemandQty"));
/* 225 */               row.add(BeanUtils.getProperty(data, "hour24DemandQty"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 229 */               MessageResources message = JitProductionPlanAction.this.getResources(request);
/* 230 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.id"));
/* 231 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.describe1"));
/* 232 */               row.add("当前库存");
/* 233 */               row.add("安全库存");
/* 234 */               row.add("高储库存");
/* 235 */               row.add("低储库存");
/* 236 */               row.add("任务日期");
/* 237 */               row.add("2小时需求数量");
/* 238 */               row.add("4小时需求数量");
/* 239 */               row.add("6小时需求数量");
/* 240 */               row.add("8小时需求数量");
/* 241 */               row.add("10小时需求数量");
/* 242 */               row.add("12小时需求数量");
/* 243 */               row.add("14小时需求数量");
/* 244 */               row.add("16小时需求数量");
/* 245 */               row.add("18小时需求数量");
/* 246 */               row.add("20小时需求数量");
/* 247 */               row.add("22小时需求数量");
/* 248 */               row.add("24小时需求数量");
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 297 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 299 */     if (queryForm.isFirstInit()) {
/* 300 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 302 */       queryForm.init();
/*     */     } 
/* 304 */     int pageNum = queryForm.getPageNoAsInt();
/* 305 */     int pageSize = queryForm.getPageSizeAsInt();
/* 306 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 307 */         JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), 
/* 308 */         queryForm.isDescend());
/*     */     
/* 310 */     for (Object object : entityList) {
/* 311 */       Map<Object, Object> map = new HashMap<Object, Object>();
/* 312 */       JitProductionPlan jit = (JitProductionPlan)object;
/* 313 */       map.put(ProjectedInventoryQueryCondition.PART_ID_EQ, jit.getChildPart().getId());
/* 314 */       List<ProjectedInventory> toryList = inventoryManager.getList(map, 0, -1, null, false);
/* 315 */       if (toryList.size() == 0) {
/* 316 */         jit.setCurrentQty(new BigDecimal(0)); continue;
/*     */       } 
/* 318 */       jit.setCurrentQty(((ProjectedInventory)toryList.get(0)).getCurrentQty());
/*     */     } 
/*     */ 
/*     */     
/* 322 */     request.setAttribute("x_selType", Integer.valueOf(171));
/* 323 */     request.setAttribute("X_RESULTLIST", entityList);
/* 324 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 329 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 334 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 335 */     BeanForm formBean = (BeanForm)form;
/* 336 */     JitProductionPlan entity = new JitProductionPlan();
/* 337 */     formBean.populateToBean(entity);
/* 338 */     request.setAttribute("X_OBJECT", manager.save(entity));
/* 339 */     request.setAttribute("X_ROWPAGE", "jitProductionPlan/row.jsp");
/* 340 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 345 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 346 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 347 */     JitProductionPlan entity = manager.getJitProductionPlan(id);
/* 348 */     request.setAttribute("X_OBJECT", entity);
/* 349 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 354 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 355 */     BeanForm formBean = (BeanForm)form;
/* 356 */     JitProductionPlan entity = new JitProductionPlan();
/* 357 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 358 */     formBean.populateToBean(entity, request);
/* 359 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 360 */     request.setAttribute("X_ROWPAGE", "jitProductionPlan/row.jsp");
/* 361 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 366 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 367 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 368 */     manager.delete(manager.getJitProductionPlan(id));
/* 369 */     return new ActionForward("listJitProductionPlanAction.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 374 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 375 */     JitProductionPlanQueryForm queryForm = (JitProductionPlanQueryForm)form;
/* 376 */     Map conditions = getConditions(queryForm);
/* 377 */     String productionId = request.getParameter("productionId");
/* 378 */     if (queryForm.getOrder() == "") {
/* 379 */       queryForm.setDescend(false);
/*     */     }
/* 381 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 382 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTION_ID_EQ, productionId);
/* 383 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/* 384 */     conditions.put(JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/* 385 */     String exportType = queryForm.getExportType();
/* 386 */     if (exportType != null && exportType.length() > 0) {
/* 387 */       List datas = manager.getList(conditions, 0, -1, 
/* 388 */           JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 389 */       int index = SessionTempFile.createNewTempFile(request);
/* 390 */       String fileName = "ZuJianXiangXi";
/* 391 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 392 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 393 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 397 */               row.add(BeanUtils.getProperty(data, "fatherPart.id"));
/* 398 */               row.add(BeanUtils.getProperty(data, "childPart.id"));
/* 399 */               row.add(BeanUtils.getProperty(data, "childPart.productClass"));
/* 400 */               row.add(BeanUtils.getProperty(data, "childPart.describe1"));
/* 401 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 402 */               row.add(BeanUtils.getProperty(data, "childPart.unit"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 407 */               MessageResources message = JitProductionPlanAction.this.getResources(request);
/* 408 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "qadoredi.qadpart"));
/* 409 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.id"));
/* 410 */               row.add("产品类");
/* 411 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "jitproductionplan.childpart.describe1"));
/* 412 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "ediproduction.qty"));
/* 413 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "ediproduction.unit"));
/*     */             }
/*     */           });
/* 416 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 418 */     if (queryForm.isFirstInit()) {
/* 419 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 421 */       queryForm.init();
/*     */     } 
/* 423 */     int pageNum = queryForm.getPageNoAsInt();
/* 424 */     int pageSize = queryForm.getPageSizeAsInt();
/* 425 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 426 */         JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), 
/* 427 */         queryForm.isDescend());
/* 428 */     request.setAttribute("productionId", productionId);
/* 429 */     request.setAttribute("x_selType", Integer.valueOf(163));
/* 430 */     request.setAttribute("X_RESULTLIST", entityList);
/* 431 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listJitProductionPlanByUnfinishPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 436 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 437 */     EdiProductionManager ediProductionManager = ServiceLocator.getEdiProductionManager(request);
/* 438 */     JitProductionPlanQueryForm queryForm = (JitProductionPlanQueryForm)form;
/* 439 */     Map conditions = getConditions(queryForm);
/* 440 */     String productionId = request.getParameter("productionId");
/* 441 */     EdiProduction ediProduction = ediProductionManager.getEdiProduction(Integer.valueOf(Integer.parseInt(productionId)));
/* 442 */     if (queryForm.getOrder() == "") {
/* 443 */       queryForm.setDescend(false);
/*     */     }
/* 445 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 446 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTION_ID_EQ, productionId);
/* 447 */     conditions.put(JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/* 448 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/* 449 */     String exportType = queryForm.getExportType();
/* 450 */     if (exportType != null && exportType.length() > 0) {
/* 451 */       List datas = manager.getList(conditions, 0, -1, 
/* 452 */           JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 453 */       int index = SessionTempFile.createNewTempFile(request);
/* 454 */       String fileName = "JitProductionPlan";
/* 455 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 456 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 457 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 461 */               row.add(BeanUtils.getProperty(data, "id"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 466 */               MessageResources message = JitProductionPlanAction.this.getResources(request);
/* 467 */               row.add(message.getMessage(JitProductionPlanAction.this.getLocale(request), "mapping.id"));
/*     */             }
/*     */           });
/*     */       
/* 471 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 473 */     if (queryForm.isFirstInit()) {
/* 474 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 476 */       queryForm.init();
/*     */     } 
/* 478 */     int pageNum = queryForm.getPageNoAsInt();
/* 479 */     int pageSize = queryForm.getPageSizeAsInt();
/* 480 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 481 */         JitProductionPlanQueryOrder.getEnum(queryForm.getOrder()), 
/* 482 */         queryForm.isDescend());
/* 483 */     request.setAttribute("productionId", productionId);
/* 484 */     request.setAttribute("x_selType", Integer.valueOf(163));
/* 485 */     request.setAttribute("X_RESULTLIST", entityList);
/* 486 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward combinePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 491 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 492 */     List<JitProductionPlan> planList = new ArrayList<JitProductionPlan>();
/* 493 */     String arrays = request.getParameter("arrays");
/*     */     
/* 495 */     String[] str = arrays.split(",");
/* 496 */     BigDecimal sum = new BigDecimal(0); byte b; int i; String[] arrayOfString1;
/* 497 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 498 */       planList.add(manager.getJitProductionPlan(Integer.valueOf(Integer.parseInt(id)))); b++; }
/*     */     
/* 500 */     manager.combinePlan(planList);
/* 501 */     return new ActionForward("listMergerPlanSummary.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward automaticCombinePlanAllAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 506 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 507 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 508 */     conditions.put(JitProductionPlanQueryCondition.PRODUCTCLASS_EQ, "JIT");
/* 509 */     List<JitProductionPlan> planList = manager.getList(conditions, 0, -1, null, false);
/* 510 */     manager.combinePlan(planList);
/* 511 */     return new ActionForward("listMergerPlanSummary.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward computeCombinePlanByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 516 */     response.setContentType("text/json");
///* 517 */     response.setCharacterEncoding("UTF-8");
/* 518 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 520 */     JitProductionPlanManager manager = ServiceLocator.getJitProductionPlanManager(request);
/* 521 */     String arrays = request.getParameter("arrays");
/* 522 */     List<Map> mapList = manager.computeCombinePlan(arrays);
/* 523 */     String[] str = arrays.split(",");
/* 524 */     Map<String, String> map = new LinkedHashMap<String, String>(); byte b; int i; String[] arrayOfString1;
/* 525 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 526 */       if (!map.containsKey(string))
/* 527 */         map.put(string, string); 
/*     */       b++; }
/*     */     
/* 530 */     List<Map> itemList = new ArrayList<Map>();
/* 531 */     for (String string : map.keySet()) {
/* 532 */       for (Map map1 : mapList) {
/* 533 */         if (map1.containsKey(string)) {
/* 534 */           itemList.add(map1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 540 */     JSONArray jo = JSONArray.fromObject(itemList, cfg);
/* 541 */     response.getWriter().print(jo);
/* 542 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/JitProductionPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */