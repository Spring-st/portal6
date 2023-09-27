/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.basicDataView.PartForecastNeedReport;
/*     */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryCondition;
/*     */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
/*     */ import com.aof.service.basicDataView.BasicDataViewManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basicDataView.BasicDataViewQueryForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class PartForecastNeedReportAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map getConditions(BasicDataViewQueryForm formBean) {
/*  44 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  45 */     String str = "";
/*     */     
/*  47 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  53 */     BasicDataViewManager manager = 
/*  54 */       ServiceLocator.getBasicDataViewManager(request);
/*  55 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/*  56 */     if (queryForm.getOrder() == "") {
/*  57 */       queryForm.setDescend(true);
/*  58 */       queryForm.setOrder("part_Id");
/*     */     } 
/*  60 */     Map<PartForecastNeedReportQueryCondition, String> conditions = getConditions(queryForm);
/*     */     
/*  62 */     if (!hasGlobalPower(request)) {
/*  63 */       User user = getCurrentUser(request);
/*  64 */       conditions.put(
/*  65 */           PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, 
/*  66 */           user.getLoginName());
/*     */     } 
/*     */     
/*  69 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  70 */     String exportType = queryForm.getExportType();
/*  71 */     if (exportType != null && exportType.length() > 0) {
/*  72 */       List datas = manager.getPartForecastNeedReportList(conditions, 0, 
/*  73 */           -1, PartForecastNeedReportQueryOrder.getEnum(queryForm
/*  74 */             .getOrder()), queryForm.isDescend());
/*  75 */       int index = SessionTempFile.createNewTempFile(request);
/*  76 */       String fileName = "PartForecastNeedReport";
/*  77 */       String suffix = ExportUtil.export(
/*  78 */           exportType, 
/*  79 */           datas, 
/*  80 */           request, 
/*  81 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  82 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  85 */               MessageResources message = PartForecastNeedReportAction.this.getResources(request);
/*  86 */               row.add("原材料号");
/*  87 */               row.add("中文描述");
/*  88 */               row.add("当前库存");
/*  89 */               row.add("库存高储");
/*  90 */               row.add("库存低储");
/*  91 */               row.add("安全库存");
/*  92 */               row.add("");
/*  93 */               row.add("过去");
/*  94 */               List<String> strList = new ArrayList<String>();
/*  95 */               SimpleDateFormat sdf = new SimpleDateFormat(
/*  96 */                   "yyyy/MM/dd");
/*  97 */               Date date = new Date();
/*  98 */               for (int i = 0; i < 45; i++) {
/*  99 */                 Calendar calendar = Calendar.getInstance();
/* 100 */                 calendar.setTime(date);
/* 101 */                 calendar.add(5, i);
/* 102 */                 strList.add(sdf.format(calendar.getTime()));
/*     */               } 
/* 104 */               for (String datee : strList) {
/* 105 */                 row.add(datee);
/*     */               }
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 112 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 113 */               row.add(BeanUtils.getProperty(data, 
/* 114 */                     "part.describe1"));
/* 115 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 116 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 117 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 118 */               row.add(BeanUtils.getProperty(data, 
/* 119 */                     "part.securityQty"));
/* 120 */               row.add("预计库存");
/* 121 */               row.add("");
/* 122 */               row.add(
/* 123 */                   BeanUtils.getProperty(data, "projected0Qty"));
/* 124 */               row.add(
/* 125 */                   BeanUtils.getProperty(data, "projected1Qty"));
/* 126 */               row.add(
/* 127 */                   BeanUtils.getProperty(data, "projected2Qty"));
/* 128 */               row.add(
/* 129 */                   BeanUtils.getProperty(data, "projected3Qty"));
/* 130 */               row.add(
/* 131 */                   BeanUtils.getProperty(data, "projected4Qty"));
/* 132 */               row.add(
/* 133 */                   BeanUtils.getProperty(data, "projected5Qty"));
/* 134 */               row.add(
/* 135 */                   BeanUtils.getProperty(data, "projected6Qty"));
/* 136 */               row.add(
/* 137 */                   BeanUtils.getProperty(data, "projected7Qty"));
/* 138 */               row.add(
/* 139 */                   BeanUtils.getProperty(data, "projected8Qty"));
/* 140 */               row.add(
/* 141 */                   BeanUtils.getProperty(data, "projected9Qty"));
/* 142 */               row.add(BeanUtils.getProperty(data, 
/* 143 */                     "projected10Qty"));
/* 144 */               row.add(BeanUtils.getProperty(data, 
/* 145 */                     "projected11Qty"));
/* 146 */               row.add(BeanUtils.getProperty(data, 
/* 147 */                     "projected12Qty"));
/* 148 */               row.add(BeanUtils.getProperty(data, 
/* 149 */                     "projected13Qty"));
/* 150 */               row.add(BeanUtils.getProperty(data, 
/* 151 */                     "projected14Qty"));
/* 152 */               row.add(BeanUtils.getProperty(data, 
/* 153 */                     "projected15Qty"));
/* 154 */               row.add(BeanUtils.getProperty(data, 
/* 155 */                     "projected16Qty"));
/* 156 */               row.add(BeanUtils.getProperty(data, 
/* 157 */                     "projected17Qty"));
/* 158 */               row.add(BeanUtils.getProperty(data, 
/* 159 */                     "projected18Qty"));
/* 160 */               row.add(BeanUtils.getProperty(data, 
/* 161 */                     "projected19Qty"));
/* 162 */               row.add(BeanUtils.getProperty(data, 
/* 163 */                     "projected20Qty"));
/* 164 */               row.add(BeanUtils.getProperty(data, 
/* 165 */                     "projected21Qty"));
/* 166 */               row.add(BeanUtils.getProperty(data, 
/* 167 */                     "projected22Qty"));
/* 168 */               row.add(BeanUtils.getProperty(data, 
/* 169 */                     "projected23Qty"));
/* 170 */               row.add(BeanUtils.getProperty(data, 
/* 171 */                     "projected24Qty"));
/* 172 */               row.add(BeanUtils.getProperty(data, 
/* 173 */                     "projected25Qty"));
/* 174 */               row.add(BeanUtils.getProperty(data, 
/* 175 */                     "projected26Qty"));
/* 176 */               row.add(BeanUtils.getProperty(data, 
/* 177 */                     "projected27Qty"));
/* 178 */               row.add(BeanUtils.getProperty(data, 
/* 179 */                     "projected28Qty"));
/* 180 */               row.add(BeanUtils.getProperty(data, 
/* 181 */                     "projected29Qty"));
/* 182 */               row.add(BeanUtils.getProperty(data, 
/* 183 */                     "projected30Qty"));
/* 184 */               row.add(BeanUtils.getProperty(data, 
/* 185 */                     "projected31Qty"));
/* 186 */               row.add(BeanUtils.getProperty(data, 
/* 187 */                     "projected32Qty"));
/* 188 */               row.add(BeanUtils.getProperty(data, 
/* 189 */                     "projected33Qty"));
/* 190 */               row.add(BeanUtils.getProperty(data, 
/* 191 */                     "projected34Qty"));
/* 192 */               row.add(BeanUtils.getProperty(data, 
/* 193 */                     "projected35Qty"));
/* 194 */               row.add(BeanUtils.getProperty(data, 
/* 195 */                     "projected36Qty"));
/* 196 */               row.add(BeanUtils.getProperty(data, 
/* 197 */                     "projected37Qty"));
/* 198 */               row.add(BeanUtils.getProperty(data, 
/* 199 */                     "projected38Qty"));
/* 200 */               row.add(BeanUtils.getProperty(data, 
/* 201 */                     "projected39Qty"));
/* 202 */               row.add(BeanUtils.getProperty(data, 
/* 203 */                     "projected40Qty"));
/* 204 */               row.add(BeanUtils.getProperty(data, 
/* 205 */                     "projected41Qty"));
/* 206 */               row.add(BeanUtils.getProperty(data, 
/* 207 */                     "projected42Qty"));
/* 208 */               row.add(BeanUtils.getProperty(data, 
/* 209 */                     "projected43Qty"));
/* 210 */               row.add(BeanUtils.getProperty(data, 
/* 211 */                     "projected44Qty"));
/*     */             }
/*     */           });
/*     */       
/* 215 */       return new ActionForward("download/" + index + "/" + 
/* 216 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 218 */     if (queryForm.isFirstInit()) {
/* 219 */       queryForm.init(manager
/* 220 */           .getPartForecastNeedReportListCount(conditions).intValue());
/*     */     } else {
/* 222 */       queryForm.init();
/*     */     } 
/* 224 */     int pageNum = queryForm.getPageNoAsInt();
/* 225 */     int pageSize = queryForm.getPageSizeAsInt();
/* 226 */     List<PartForecastNeedReport> entityList = manager
/* 227 */       .getPartForecastNeedReportList(conditions, pageNum, pageSize, 
/* 228 */         PartForecastNeedReportQueryOrder.getEnum(queryForm
/* 229 */           .getOrder()), queryForm.isDescend());
/* 230 */     request.setAttribute("x_selType", Integer.valueOf(174));
/* 231 */     calculatedByDate(request);
/* 232 */     request.setAttribute("X_RESULTLIST", entityList);
/* 233 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void calculatedByDate(HttpServletRequest request) {
/* 237 */     List<String> strList = new ArrayList<String>();
/* 238 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 239 */     Date date = new Date();
/* 240 */     for (int i = 0; i < 45; i++) {
/* 241 */       Calendar calendar = Calendar.getInstance();
/* 242 */       calendar.setTime(date);
/* 243 */       calendar.add(5, i);
/* 244 */       strList.add(sdf.format(calendar.getTime()));
/*     */     } 
/* 246 */     request.setAttribute("dateList", strList);
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
/*     */   public ActionForward listExpectedInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 264 */     BasicDataViewManager manager = 
/* 265 */       ServiceLocator.getBasicDataViewManager(request);
/* 266 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/* 267 */     if (queryForm.getOrder() == "") {
/* 268 */       queryForm.setDescend(true);
/* 269 */       queryForm.setOrder("partId");
/*     */     } 
/* 271 */     Map<PartForecastNeedReportQueryCondition, String> conditions = getConditions(queryForm);
/*     */     
/* 273 */     if (!hasGlobalPower(request)) {
/* 274 */       User user = getCurrentUser(request);
/* 275 */       conditions.put(
/* 276 */           PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, 
/* 277 */           user.getLoginName());
/*     */     } 
/*     */     
/* 280 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 281 */     String exportType = queryForm.getExportType();
/* 282 */     if (exportType != null && exportType.length() > 0) {
/* 283 */       List datas = manager.getPartForecastNeedReportList(conditions, 0, 
/* 284 */           -1, PartForecastNeedReportQueryOrder.getEnum(queryForm
/* 285 */             .getOrder()), queryForm.isDescend());
/* 286 */       int index = SessionTempFile.createNewTempFile(request);
/* 287 */       String fileName = "PartForecastNeedReport";
/* 288 */       String suffix = ExportUtil.export(
/* 289 */           exportType, 
/* 290 */           datas, 
/* 291 */           request, 
/* 292 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 293 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 296 */               MessageResources message = PartForecastNeedReportAction.this.getResources(request);
/* 297 */               row.add("原材料号");
/* 298 */               row.add("中文描述");
/* 299 */               row.add("当前库存");
/* 300 */               row.add("库存高储");
/* 301 */               row.add("库存低储");
/* 302 */               row.add("安全库存");
/* 303 */               row.add("");
/* 304 */               row.add("过去");
/* 305 */               List<String> strList = new ArrayList<String>();
/* 306 */               SimpleDateFormat sdf = new SimpleDateFormat(
/* 307 */                   "yyyy/MM/dd");
/* 308 */               Date date = new Date();
/* 309 */               for (int i = 0; i < 45; i++) {
/* 310 */                 Calendar calendar = Calendar.getInstance();
/* 311 */                 calendar.setTime(date);
/* 312 */                 calendar.add(5, i);
/* 313 */                 strList.add(sdf.format(calendar.getTime()));
/*     */               } 
/* 315 */               for (String datee : strList) {
/* 316 */                 row.add(datee);
/*     */               }
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 323 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 324 */               row.add(BeanUtils.getProperty(data, 
/* 325 */                     "part.describe1"));
/* 326 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 327 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 328 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 329 */               row.add(BeanUtils.getProperty(data, 
/* 330 */                     "part.securityQty"));
/* 331 */               row.add("预计库存");
/* 332 */               row.add("");
/* 333 */               row.add(
/* 334 */                   BeanUtils.getProperty(data, "projected0Qty"));
/* 335 */               row.add(
/* 336 */                   BeanUtils.getProperty(data, "projected1Qty"));
/* 337 */               row.add(
/* 338 */                   BeanUtils.getProperty(data, "projected2Qty"));
/* 339 */               row.add(
/* 340 */                   BeanUtils.getProperty(data, "projected3Qty"));
/* 341 */               row.add(
/* 342 */                   BeanUtils.getProperty(data, "projected4Qty"));
/* 343 */               row.add(
/* 344 */                   BeanUtils.getProperty(data, "projected5Qty"));
/* 345 */               row.add(
/* 346 */                   BeanUtils.getProperty(data, "projected6Qty"));
/* 347 */               row.add(
/* 348 */                   BeanUtils.getProperty(data, "projected7Qty"));
/* 349 */               row.add(
/* 350 */                   BeanUtils.getProperty(data, "projected8Qty"));
/* 351 */               row.add(
/* 352 */                   BeanUtils.getProperty(data, "projected9Qty"));
/* 353 */               row.add(BeanUtils.getProperty(data, 
/* 354 */                     "projected10Qty"));
/* 355 */               row.add(BeanUtils.getProperty(data, 
/* 356 */                     "projected11Qty"));
/* 357 */               row.add(BeanUtils.getProperty(data, 
/* 358 */                     "projected12Qty"));
/* 359 */               row.add(BeanUtils.getProperty(data, 
/* 360 */                     "projected13Qty"));
/* 361 */               row.add(BeanUtils.getProperty(data, 
/* 362 */                     "projected14Qty"));
/* 363 */               row.add(BeanUtils.getProperty(data, 
/* 364 */                     "projected15Qty"));
/* 365 */               row.add(BeanUtils.getProperty(data, 
/* 366 */                     "projected16Qty"));
/* 367 */               row.add(BeanUtils.getProperty(data, 
/* 368 */                     "projected17Qty"));
/* 369 */               row.add(BeanUtils.getProperty(data, 
/* 370 */                     "projected18Qty"));
/* 371 */               row.add(BeanUtils.getProperty(data, 
/* 372 */                     "projected19Qty"));
/* 373 */               row.add(BeanUtils.getProperty(data, 
/* 374 */                     "projected20Qty"));
/* 375 */               row.add(BeanUtils.getProperty(data, 
/* 376 */                     "projected21Qty"));
/* 377 */               row.add(BeanUtils.getProperty(data, 
/* 378 */                     "projected22Qty"));
/* 379 */               row.add(BeanUtils.getProperty(data, 
/* 380 */                     "projected23Qty"));
/* 381 */               row.add(BeanUtils.getProperty(data, 
/* 382 */                     "projected24Qty"));
/* 383 */               row.add(BeanUtils.getProperty(data, 
/* 384 */                     "projected25Qty"));
/* 385 */               row.add(BeanUtils.getProperty(data, 
/* 386 */                     "projected26Qty"));
/* 387 */               row.add(BeanUtils.getProperty(data, 
/* 388 */                     "projected27Qty"));
/* 389 */               row.add(BeanUtils.getProperty(data, 
/* 390 */                     "projected28Qty"));
/* 391 */               row.add(BeanUtils.getProperty(data, 
/* 392 */                     "projected29Qty"));
/* 393 */               row.add(BeanUtils.getProperty(data, 
/* 394 */                     "projected30Qty"));
/* 395 */               row.add(BeanUtils.getProperty(data, 
/* 396 */                     "projected31Qty"));
/* 397 */               row.add(BeanUtils.getProperty(data, 
/* 398 */                     "projected32Qty"));
/* 399 */               row.add(BeanUtils.getProperty(data, 
/* 400 */                     "projected33Qty"));
/* 401 */               row.add(BeanUtils.getProperty(data, 
/* 402 */                     "projected34Qty"));
/* 403 */               row.add(BeanUtils.getProperty(data, 
/* 404 */                     "projected35Qty"));
/* 405 */               row.add(BeanUtils.getProperty(data, 
/* 406 */                     "projected36Qty"));
/* 407 */               row.add(BeanUtils.getProperty(data, 
/* 408 */                     "projected37Qty"));
/* 409 */               row.add(BeanUtils.getProperty(data, 
/* 410 */                     "projected38Qty"));
/* 411 */               row.add(BeanUtils.getProperty(data, 
/* 412 */                     "projected39Qty"));
/* 413 */               row.add(BeanUtils.getProperty(data, 
/* 414 */                     "projected40Qty"));
/* 415 */               row.add(BeanUtils.getProperty(data, 
/* 416 */                     "projected41Qty"));
/* 417 */               row.add(BeanUtils.getProperty(data, 
/* 418 */                     "projected42Qty"));
/* 419 */               row.add(BeanUtils.getProperty(data, 
/* 420 */                     "projected43Qty"));
/* 421 */               row.add(BeanUtils.getProperty(data, 
/* 422 */                     "projected44Qty"));
/*     */             }
/*     */           });
/*     */       
/* 426 */       return new ActionForward("download/" + index + "/" + 
/* 427 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 429 */     if (queryForm.isFirstInit()) {
/* 430 */       queryForm.init(manager
/* 431 */           .getPartForecastNeedReportListCount(conditions).intValue());
/*     */     } else {
/* 433 */       queryForm.init();
/*     */     } 
/* 435 */     int pageNum = queryForm.getPageNoAsInt();
/* 436 */     int pageSize = queryForm.getPageSizeAsInt();
/* 437 */     List<PartForecastNeedReport> entityList = manager
/* 438 */       .getPartForecastNeedReportList(conditions, pageNum, pageSize, 
/* 439 */         PartForecastNeedReportQueryOrder.getEnum(queryForm
/* 440 */           .getOrder()), queryForm.isDescend());
/* 441 */     request.setAttribute("x_selType", Integer.valueOf(181));
/* 442 */     calculatedByDate(request);
/* 443 */     request.setAttribute("X_RESULTLIST", entityList);
/* 444 */     return mapping.findForward("page");
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
/*     */   public ActionForward listPartNeed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 462 */     BasicDataViewManager manager = 
/* 463 */       ServiceLocator.getBasicDataViewManager(request);
/* 464 */     BasicDataViewQueryForm queryForm = (BasicDataViewQueryForm)form;
/* 465 */     if (queryForm.getOrder() == "") {
/* 466 */       queryForm.setDescend(true);
/* 467 */       queryForm.setOrder("partId");
/*     */     } 
/* 469 */     Map<PartForecastNeedReportQueryCondition, String> conditions = getConditions(queryForm);
/*     */     
/* 471 */     if (!hasGlobalPower(request)) {
/* 472 */       User user = getCurrentUser(request);
/* 473 */       conditions.put(
/* 474 */           PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, 
/* 475 */           user.getLoginName());
/*     */     } 
/*     */     
/* 478 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 479 */     String exportType = queryForm.getExportType();
/* 480 */     if (exportType != null && exportType.length() > 0) {
/* 481 */       List datas = manager.getPartForecastNeedReportList(conditions, 0, 
/* 482 */           -1, PartForecastNeedReportQueryOrder.getEnum(queryForm
/* 483 */             .getOrder()), queryForm.isDescend());
/* 484 */       int index = SessionTempFile.createNewTempFile(request);
/* 485 */       String fileName = "PartForecastNeedReport";
/* 486 */       String suffix = ExportUtil.export(
/* 487 */           exportType, 
/* 488 */           datas, 
/* 489 */           request, 
/* 490 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 491 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 494 */               MessageResources message = PartForecastNeedReportAction.this.getResources(request);
/* 495 */               row.add("原材料号");
/* 496 */               row.add("中文描述");
/* 497 */               row.add("当前库存");
/* 498 */               row.add("库存高储");
/* 499 */               row.add("库存低储");
/* 500 */               row.add("安全库存");
/* 501 */               row.add("");
/* 502 */               row.add("过去");
/* 503 */               List<String> strList = new ArrayList<String>();
/* 504 */               SimpleDateFormat sdf = new SimpleDateFormat(
/* 505 */                   "yyyy/MM/dd");
/* 506 */               Date date = new Date();
/* 507 */               for (int i = 0; i < 45; i++) {
/* 508 */                 Calendar calendar = Calendar.getInstance();
/* 509 */                 calendar.setTime(date);
/* 510 */                 calendar.add(5, i);
/* 511 */                 strList.add(sdf.format(calendar.getTime()));
/*     */               } 
/* 513 */               for (String datee : strList) {
/* 514 */                 row.add(datee);
/*     */               }
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 521 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 522 */               row.add(BeanUtils.getProperty(data, 
/* 523 */                     "part.describe1"));
/* 524 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 525 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 526 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 527 */               row.add(BeanUtils.getProperty(data, 
/* 528 */                     "part.securityQty"));
/* 529 */               row.add("预计库存");
/* 530 */               row.add("");
/* 531 */               row.add(
/* 532 */                   BeanUtils.getProperty(data, "projected0Qty"));
/* 533 */               row.add(
/* 534 */                   BeanUtils.getProperty(data, "projected1Qty"));
/* 535 */               row.add(
/* 536 */                   BeanUtils.getProperty(data, "projected2Qty"));
/* 537 */               row.add(
/* 538 */                   BeanUtils.getProperty(data, "projected3Qty"));
/* 539 */               row.add(
/* 540 */                   BeanUtils.getProperty(data, "projected4Qty"));
/* 541 */               row.add(
/* 542 */                   BeanUtils.getProperty(data, "projected5Qty"));
/* 543 */               row.add(
/* 544 */                   BeanUtils.getProperty(data, "projected6Qty"));
/* 545 */               row.add(
/* 546 */                   BeanUtils.getProperty(data, "projected7Qty"));
/* 547 */               row.add(
/* 548 */                   BeanUtils.getProperty(data, "projected8Qty"));
/* 549 */               row.add(
/* 550 */                   BeanUtils.getProperty(data, "projected9Qty"));
/* 551 */               row.add(BeanUtils.getProperty(data, 
/* 552 */                     "projected10Qty"));
/* 553 */               row.add(BeanUtils.getProperty(data, 
/* 554 */                     "projected11Qty"));
/* 555 */               row.add(BeanUtils.getProperty(data, 
/* 556 */                     "projected12Qty"));
/* 557 */               row.add(BeanUtils.getProperty(data, 
/* 558 */                     "projected13Qty"));
/* 559 */               row.add(BeanUtils.getProperty(data, 
/* 560 */                     "projected14Qty"));
/* 561 */               row.add(BeanUtils.getProperty(data, 
/* 562 */                     "projected15Qty"));
/* 563 */               row.add(BeanUtils.getProperty(data, 
/* 564 */                     "projected16Qty"));
/* 565 */               row.add(BeanUtils.getProperty(data, 
/* 566 */                     "projected17Qty"));
/* 567 */               row.add(BeanUtils.getProperty(data, 
/* 568 */                     "projected18Qty"));
/* 569 */               row.add(BeanUtils.getProperty(data, 
/* 570 */                     "projected19Qty"));
/* 571 */               row.add(BeanUtils.getProperty(data, 
/* 572 */                     "projected20Qty"));
/* 573 */               row.add(BeanUtils.getProperty(data, 
/* 574 */                     "projected21Qty"));
/* 575 */               row.add(BeanUtils.getProperty(data, 
/* 576 */                     "projected22Qty"));
/* 577 */               row.add(BeanUtils.getProperty(data, 
/* 578 */                     "projected23Qty"));
/* 579 */               row.add(BeanUtils.getProperty(data, 
/* 580 */                     "projected24Qty"));
/* 581 */               row.add(BeanUtils.getProperty(data, 
/* 582 */                     "projected25Qty"));
/* 583 */               row.add(BeanUtils.getProperty(data, 
/* 584 */                     "projected26Qty"));
/* 585 */               row.add(BeanUtils.getProperty(data, 
/* 586 */                     "projected27Qty"));
/* 587 */               row.add(BeanUtils.getProperty(data, 
/* 588 */                     "projected28Qty"));
/* 589 */               row.add(BeanUtils.getProperty(data, 
/* 590 */                     "projected29Qty"));
/* 591 */               row.add(BeanUtils.getProperty(data, 
/* 592 */                     "projected30Qty"));
/* 593 */               row.add(BeanUtils.getProperty(data, 
/* 594 */                     "projected31Qty"));
/* 595 */               row.add(BeanUtils.getProperty(data, 
/* 596 */                     "projected32Qty"));
/* 597 */               row.add(BeanUtils.getProperty(data, 
/* 598 */                     "projected33Qty"));
/* 599 */               row.add(BeanUtils.getProperty(data, 
/* 600 */                     "projected34Qty"));
/* 601 */               row.add(BeanUtils.getProperty(data, 
/* 602 */                     "projected35Qty"));
/* 603 */               row.add(BeanUtils.getProperty(data, 
/* 604 */                     "projected36Qty"));
/* 605 */               row.add(BeanUtils.getProperty(data, 
/* 606 */                     "projected37Qty"));
/* 607 */               row.add(BeanUtils.getProperty(data, 
/* 608 */                     "projected38Qty"));
/* 609 */               row.add(BeanUtils.getProperty(data, 
/* 610 */                     "projected39Qty"));
/* 611 */               row.add(BeanUtils.getProperty(data, 
/* 612 */                     "projected40Qty"));
/* 613 */               row.add(BeanUtils.getProperty(data, 
/* 614 */                     "projected41Qty"));
/* 615 */               row.add(BeanUtils.getProperty(data, 
/* 616 */                     "projected42Qty"));
/* 617 */               row.add(BeanUtils.getProperty(data, 
/* 618 */                     "projected43Qty"));
/* 619 */               row.add(BeanUtils.getProperty(data, 
/* 620 */                     "projected44Qty"));
/*     */             }
/*     */           });
/*     */       
/* 624 */       return new ActionForward("download/" + index + "/" + 
/* 625 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 627 */     if (queryForm.isFirstInit()) {
/* 628 */       queryForm.init(manager
/* 629 */           .getPartForecastNeedReportListCount(conditions).intValue());
/*     */     } else {
/* 631 */       queryForm.init();
/*     */     } 
/* 633 */     int pageNum = queryForm.getPageNoAsInt();
/* 634 */     int pageSize = queryForm.getPageSizeAsInt();
/* 635 */     List<PartForecastNeedReport> entityList = manager
/* 636 */       .getPartForecastNeedReportList(conditions, pageNum, pageSize, 
/* 637 */         PartForecastNeedReportQueryOrder.getEnum(queryForm
/* 638 */           .getOrder()), queryForm.isDescend());
/* 639 */     request.setAttribute("x_selType", Integer.valueOf(182));
/* 640 */     calculatedByDate(request);
/* 641 */     request.setAttribute("X_RESULTLIST", entityList);
/* 642 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/PartForecastNeedReportAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */