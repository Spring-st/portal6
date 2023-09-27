/*      */ package com.aof.web.struts.action.schedule;
/*      */ 
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*      */ import com.aof.model.comprehensive.Bom;
/*      */ import com.aof.model.comprehensive.query.BomQueryCondition;
/*      */ import com.aof.model.schedule.EdiProduction;
/*      */ import com.aof.model.schedule.JitProductionPlan;
/*      */ import com.aof.model.schedule.QadOrEdi;
/*      */ import com.aof.model.schedule.query.EdiProductionQueryCondition;
/*      */ import com.aof.model.schedule.query.EdiProductionQueryOrder;
/*      */ import com.aof.model.schedule.query.JitProductionPlanQueryCondition;
/*      */ import com.aof.model.schedule.query.QadOrEdiQueryCondition;
/*      */ import com.aof.service.admin.SupplierManager;
/*      */ import com.aof.service.basic.WmsPartManager;
/*      */ import com.aof.service.comprehensive.BomManager;
/*      */ import com.aof.service.quartz.job.DeliverMinuteSyncJob;
/*      */ import com.aof.service.quartz.job.RedMinuteSyncJob;
/*      */ import com.aof.service.schedule.EdiProductionManager;
/*      */ import com.aof.service.schedule.JitProductionPlanManager;
/*      */ import com.aof.service.schedule.QadOrEdiManager;
/*      */ import com.aof.utils.SessionTempFile;
/*      */ import com.aof.web.struts.action.BaseAction2;
/*      */ import com.aof.web.struts.action.ServiceLocator;
/*      */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*      */ import com.aof.web.struts.form.schedule.EdiProductionQueryForm;
/*      */ import com.shcnc.struts.form.BeanForm;
/*      */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*      */ import com.shcnc.utils.BeanUtils;
/*      */ import com.shcnc.utils.ExportUtil;
/*      */ import com.shcnc.utils.Exportable;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import javax.servlet.ServletOutputStream;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import javax.servlet.http.HttpSession;
/*      */ import net.sf.json.JSONArray;
/*      */ import net.sf.json.JSONObject;
/*      */ import net.sf.json.JsonConfig;
/*      */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*      */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*      */ import org.apache.poi.hssf.usermodel.HSSFDataFormat;
/*      */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*      */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*      */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*      */ import org.apache.poi.ss.usermodel.Cell;
/*      */ import org.apache.poi.ss.usermodel.IndexedColors;
/*      */ import org.apache.poi.ss.usermodel.Row;
/*      */ import org.apache.poi.ss.usermodel.Sheet;
/*      */ import org.apache.poi.ss.util.CellRangeAddress;
/*      */ import org.apache.struts.action.ActionForm;
/*      */ import org.apache.struts.action.ActionForward;
/*      */ import org.apache.struts.action.ActionMapping;
/*      */ import org.apache.struts.upload.FormFile;
/*      */ import org.apache.struts.util.MessageResources;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EdiProductionAction
/*      */   extends BaseAction2
/*      */ {
/*      */   private Map getConditions(EdiProductionQueryForm formBean) {
/*   87 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*   88 */     String str = "";
/*      */     
/*   90 */     return conditions;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*   96 */     EdiProductionManager manager = 
/*   97 */       ServiceLocator.getEdiProductionManager(request);
/*   98 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/*   99 */     Map conditions = getConditions(queryForm);
/*  100 */     if (queryForm.getOrder() == "") {
/*  101 */       queryForm.setOrder("syncTime");
/*  102 */       queryForm.setDescend(true);
/*      */     } 
/*  104 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(1));
/*  105 */     conditions.put(EdiProductionQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  106 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  107 */     String exportType = queryForm.getExportType();
/*  108 */     if (exportType != null && exportType.length() > 0) {
/*  109 */       List datas = manager.getList(conditions, 0, -1, 
/*  110 */           EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  111 */           queryForm.isDescend());
/*  112 */       int index = SessionTempFile.createNewTempFile(request);
/*  113 */       String fileName = "ChengPinBeiJianJiHuaMingXi";
/*  114 */       String suffix = ExportUtil.export(
/*  115 */           exportType, 
/*  116 */           datas, 
/*  117 */           request, 
/*  118 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  119 */               request)), new Exportable()
/*      */           {
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  122 */               row.add(BeanUtils.getProperty(data, "asnNo"));
/*  123 */               row.add(BeanUtils.getProperty(data, 
/*  124 */                     "productlinecode"));
/*  125 */               row.add(BeanUtils.getProperty(data, "shiftcode"));
/*  126 */               row.add(BeanUtils.getProperty(data, "staffcode"));
/*  127 */               row.add(BeanUtils.getProperty(data, "number"));
/*  128 */               row.add(BeanUtils.getProperty(data, "taskDate"));
/*  129 */               row.add(BeanUtils.getProperty(data, "time"));
/*  130 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  131 */               row.add(BeanUtils.getProperty(data, "syncTime"));
/*  132 */               EdiProduction prodution = (EdiProduction)data;
/*  133 */               if (prodution.getStatus().intValue() == 0) {
/*  134 */                 row.add("未分解");
/*  135 */               } else if (prodution.getStatus().intValue() == 1) {
/*  136 */                 row.add("已分解");
/*  137 */               } else if (prodution.getStatus().intValue() == 2) {
/*  138 */                 row.add("失败");
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  144 */               MessageResources message = EdiProductionAction.this.getResources(request);
/*  145 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  146 */                     "ediproduction.asnno"));
/*  147 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  148 */                     "ediproduction.productlinecode"));
/*  149 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  150 */                     "ediproduction.shiftcode"));
/*  151 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  152 */                     "ediproduction.staffcode"));
/*  153 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  154 */                     "ediproduction.number"));
/*  155 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  156 */                     "ediproduction.taskdate"));
/*  157 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  158 */                     "ediproduction.time"));
/*  159 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  160 */                     "ediproduction.qty"));
/*  161 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  162 */                     "ediproduction.synctime"));
/*  163 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  164 */                     "ediproduction.status"));
/*      */             }
/*      */           });
/*  167 */       return new ActionForward("download/" + index + "/" + 
/*  168 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*      */     } 
/*  170 */     if (queryForm.isFirstInit()) {
/*  171 */       queryForm.init(manager.getListCount(conditions).intValue());
/*      */     } else {
/*  173 */       queryForm.init();
/*      */     } 
/*  175 */     int pageNum = queryForm.getPageNoAsInt();
/*  176 */     int pageSize = queryForm.getPageSizeAsInt();
/*  177 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/*  178 */         EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  179 */         queryForm.isDescend());
/*  180 */     request.setAttribute("x_selType", Integer.valueOf(162));
/*  181 */     request.setAttribute("X_RESULTLIST", entityList);
/*  182 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  188 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  194 */     EdiProductionManager manager = 
/*  195 */       ServiceLocator.getEdiProductionManager(request);
/*  196 */     BeanForm formBean = (BeanForm)form;
/*  197 */     EdiProduction entity = new EdiProduction();
/*  198 */     formBean.populateToBean(entity);
/*  199 */     request.setAttribute("X_OBJECT", manager.save(entity));
/*  200 */     request.setAttribute("X_ROWPAGE", "ediProduction/row.jsp");
/*  201 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  207 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/*  208 */     EdiProductionManager manager = 
/*  209 */       ServiceLocator.getEdiProductionManager(request);
/*  210 */     EdiProduction entity = manager.getEdiProduction(id);
/*  211 */     request.setAttribute("X_OBJECT", entity);
/*  212 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  218 */     EdiProductionManager manager = 
/*  219 */       ServiceLocator.getEdiProductionManager(request);
/*  220 */     BeanForm formBean = (BeanForm)form;
/*  221 */     EdiProduction entity = new EdiProduction();
/*  222 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/*  223 */     formBean.populateToBean(entity, request);
/*  224 */     request.setAttribute("X_OBJECT", manager.update(entity));
/*  225 */     request.setAttribute("X_ROWPAGE", "ediProduction/row.jsp");
/*  226 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  232 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/*  233 */     EdiProductionManager manager = 
/*  234 */       ServiceLocator.getEdiProductionManager(request);
/*  235 */     manager.delete(manager.getEdiProduction(id));
/*  236 */     return new ActionForward("listEdiProductionAction.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listRawData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  242 */     EdiProductionManager manager = 
/*  243 */       ServiceLocator.getEdiProductionManager(request);
/*  244 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/*  245 */     Map conditions = getConditions(queryForm);
/*  246 */     if (queryForm.getOrder() == "") {
/*  247 */       queryForm.setOrder("syncTime");
/*  248 */       queryForm.setDescend(true);
/*      */     } 
/*  250 */     getConditionAndOrder(queryForm, conditions, request);
/*  251 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(1));
/*  252 */     conditions.put(EdiProductionQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  253 */     String exportType = queryForm.getExportType();
/*  254 */     if (exportType != null && exportType.length() > 0) {
/*  255 */       List datas = manager.getList(conditions, 0, -1, 
/*  256 */           EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  257 */           queryForm.isDescend());
/*  258 */       int index = SessionTempFile.createNewTempFile(request);
/*  259 */       String fileName = "ChengPinBeiJianJiHuaChaKan";
/*  260 */       String suffix = ExportUtil.export(
/*  261 */           exportType, 
/*  262 */           datas, 
/*  263 */           request, 
/*  264 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  265 */               request)), new Exportable()
/*      */           {
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  268 */               row.add(BeanUtils.getProperty(data, "asnNo"));
/*  269 */               row.add(BeanUtils.getProperty(data, 
/*  270 */                     "productlinecode"));
/*  271 */               row.add(BeanUtils.getProperty(data, "shiftcode"));
/*  272 */               row.add(BeanUtils.getProperty(data, "staffcode"));
/*  273 */               row.add(BeanUtils.getProperty(data, "number"));
/*  274 */               row.add(BeanUtils.getProperty(data, "taskDate"));
/*  275 */               row.add(BeanUtils.getProperty(data, "time"));
/*  276 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  277 */               row.add(BeanUtils.getProperty(data, "syncTime"));
/*  278 */               EdiProduction prodution = (EdiProduction)data;
/*  279 */               if (prodution.getStatus().intValue() == 0) {
/*  280 */                 row.add("未分解");
/*  281 */               } else if (prodution.getStatus().intValue() == 1) {
/*  282 */                 row.add("已分解");
/*  283 */               } else if (prodution.getStatus().intValue() == 2) {
/*  284 */                 row.add("失败");
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  290 */               MessageResources message = EdiProductionAction.this.getResources(request);
/*  291 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  292 */                     "ediproduction.asnno"));
/*  293 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  294 */                     "ediproduction.productlinecode"));
/*  295 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  296 */                     "ediproduction.shiftcode"));
/*  297 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  298 */                     "ediproduction.staffcode"));
/*  299 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  300 */                     "ediproduction.number"));
/*  301 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  302 */                     "ediproduction.taskdate"));
/*  303 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  304 */                     "ediproduction.time"));
/*  305 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  306 */                     "ediproduction.qty"));
/*  307 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  308 */                     "ediproduction.synctime"));
/*  309 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  310 */                     "ediproduction.status"));
/*      */             }
/*      */           });
/*  313 */       return new ActionForward("download/" + index + "/" + 
/*  314 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*      */     } 
/*  316 */     if (queryForm.isFirstInit()) {
/*  317 */       queryForm.init(manager.getListCount(conditions).intValue());
/*      */     } else {
/*  319 */       queryForm.init();
/*      */     } 
/*  321 */     int pageNum = queryForm.getPageNoAsInt();
/*  322 */     int pageSize = queryForm.getPageSizeAsInt();
/*  323 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/*  324 */         EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  325 */         queryForm.isDescend());
/*  326 */     request.setAttribute("x_selType", Integer.valueOf(161));
/*  327 */     request.setAttribute("X_RESULTLIST", entityList);
/*  328 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward startTimingTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  334 */     DeliverMinuteSyncJob ss = 
/*  335 */       ServiceLocator.getDeliverMinuteSyncJobManager(request);
/*  336 */     ss.startSyn();
/*  337 */     RedMinuteSyncJob sss = 
/*  338 */       ServiceLocator.getRedMinuteSyncJobManager(request);
/*  339 */     sss.startSyn();
/*  340 */     return new ActionForward("listEdiProductionRawDataAction.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward decompositionProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  346 */     EdiProductionManager manager = 
/*  347 */       ServiceLocator.getEdiProductionManager(request);
/*  348 */     QadOrEdiManager qadOrEdiManager = 
/*  349 */       ServiceLocator.getQadOrEdiManager(request);
/*  350 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/*  351 */     HttpSession session = request.getSession();
/*  352 */     JSONObject json = new JSONObject();
/*  353 */     String str = "";
/*  354 */     JitProductionPlanManager jitProductionPlanManager = 
/*  355 */       ServiceLocator.getJitProductionPlanManager(request);
/*  356 */     BomManager bomManager = ServiceLocator.getBomManager(request);
/*  357 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  358 */     conditions.put(EdiProductionQueryCondition.STATUS_NOT_EQ, Integer.valueOf(1));
/*  359 */     conditions.put(EdiProductionQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  360 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(1));
/*  361 */     List<EdiProduction> ediProductionList = manager.getList(conditions, 0, 
/*  362 */         -1, null, false);
/*  363 */     for (EdiProduction ediProduction : ediProductionList) {
/*  364 */       conditions.clear();
/*      */ 
/*      */       
/*  367 */       conditions.put(QadOrEdiQueryCondition.MODELS_EQ, 
/*  368 */           ediProduction.getAsnNo());
/*      */ 
/*      */       
/*  371 */       List<QadOrEdi> qadEdiList = qadOrEdiManager.getList(conditions, 0, 
/*  372 */           -1, null, false);
/*  373 */       if (qadEdiList.size() > 0) {
/*  374 */         boolean sign = true;
/*  375 */         for (QadOrEdi qadOrEdi : qadEdiList) {
/*  376 */           conditions.clear();
/*  377 */           conditions.put(BomQueryCondition.FATHER_PART_EQ, qadOrEdi
/*  378 */               .getQadPart().getId());
/*  379 */           List<Bom> bomList = bomManager.getBomList(conditions, 0, 
/*  380 */               -1, null, false);
/*  381 */           if (bomList == null || bomList.size() == 0) {
/*  382 */             sign = false;
/*  383 */             str = String.valueOf(str) + "分解失败,在物料bom里面找不到父料号" + 
/*  384 */               qadOrEdi.getQadPart().getId() + "\n";
/*      */           } 
/*      */         } 
/*  387 */         if (sign) {
/*  388 */           for (QadOrEdi qadOrEdi : qadEdiList) {
/*  389 */             conditions.clear();
/*  390 */             conditions.put(BomQueryCondition.FATHER_PART_EQ, 
/*  391 */                 qadOrEdi.getQadPart().getId());
/*  392 */             List<Bom> bomList = bomManager.getBomList(conditions, 
/*  393 */                 0, -1, null, false);
/*  394 */             if (bomList != null && bomList.size() > 0) {
/*  395 */               jitProductionPlanManager.DismantlingBom(
/*  396 */                   ediProduction, qadOrEdi.getQadPart(), 
/*  397 */                   new BigDecimal(
/*  398 */                     (ediProduction.getQty() == null) ? 0 : 
/*  399 */                     ediProduction.getQty().intValue()));
/*      */               
/*  401 */               ediProduction.setStatus(Integer.valueOf(1));
/*  402 */               manager.update(ediProduction);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */         
/*  411 */         ediProduction.setStatus(Integer.valueOf(2));
/*  412 */         manager.update(ediProduction);
/*      */         continue;
/*      */       } 
/*  415 */       ediProduction.setStatus(Integer.valueOf(2));
/*  416 */       manager.update(ediProduction);
/*  417 */       str = String.valueOf(str) + "分解失败,ASN与总成对应关系找不到" + ediProduction.getAsnNo() + "\n";
/*      */     } 
/*      */     
///*  420 */     response.setCharacterEncoding("utf-8");
/*  421 */     response.setContentType("text/json");
/*  422 */     PrintWriter out = response.getWriter();
/*  423 */     json.put("str", str);
/*  424 */     out.print(json.toString());
/*  425 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listUnfinishPlanGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  431 */     EdiProductionManager manager = 
/*  432 */       ServiceLocator.getEdiProductionManager(request);
/*  433 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/*  434 */     Map conditions = getConditions(queryForm);
/*  435 */     if (queryForm.getOrder() == "") {
/*  436 */       queryForm.setDescend(false);
/*      */     }
/*  438 */     getConditionAndOrder(queryForm, conditions, request);
/*  439 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(2));
/*  440 */     conditions.put(EdiProductionQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  441 */     conditions.put(EdiProductionQueryCondition.ID_IN, Integer.valueOf(2));
/*  442 */     String exportType = queryForm.getExportType();
/*  443 */     if (exportType != null && exportType.length() > 0) {
/*  444 */       List datas = manager.getGroupList(conditions, 0, -1, 
/*  445 */           EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  446 */           queryForm.isDescend());
/*  447 */       int index = SessionTempFile.createNewTempFile(request);
/*  448 */       String fileName = "EdiProduction";
/*  449 */       String suffix = ExportUtil.export(
/*  450 */           exportType, 
/*  451 */           datas, 
/*  452 */           request, 
/*  453 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  454 */               request)), new Exportable()
/*      */           {
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  457 */               row.add(BeanUtils.getProperty(data, "id"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  463 */               MessageResources message = EdiProductionAction.this.getResources(request);
/*  464 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  465 */                     "mapping.id"));
/*      */             }
/*      */           });
/*      */       
/*  469 */       return new ActionForward("download/" + index + "/" + 
/*  470 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*      */     } 
/*  472 */     if (queryForm.isFirstInit()) {
/*  473 */       queryForm.init(manager.getListGroupCount(conditions).intValue());
/*      */     } else {
/*  475 */       queryForm.init();
/*      */     } 
/*  477 */     int pageNum = queryForm.getPageNoAsInt();
/*  478 */     int pageSize = queryForm.getPageSizeAsInt();
/*  479 */     List entityList = manager.getGroupList(conditions, pageNum, pageSize, 
/*  480 */         EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  481 */         queryForm.isDescend());
/*  482 */     request.setAttribute("x_selType", Integer.valueOf(165));
/*  483 */     request.setAttribute("X_RESULTLIST", entityList);
/*  484 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listUnfinishPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  490 */     EdiProductionManager manager = 
/*  491 */       ServiceLocator.getEdiProductionManager(request);
/*  492 */     String productionId = request.getParameter("productionId");
/*  493 */     EdiProduction ediProduction = manager.getEdiProduction(
/*  494 */         Integer.valueOf(Integer.parseInt(productionId)));
/*  495 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/*  496 */     Map conditions = getConditions(queryForm);
/*  497 */     if (queryForm.getOrder() == "") {
/*  498 */       queryForm.setDescend(true);
/*      */     }
/*  500 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  501 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(2));
/*  502 */     conditions.put(EdiProductionQueryCondition.ENABLED_EQ, Integer.valueOf(0));
/*  503 */     if (ediProduction.getSupplier() != null) {
/*  504 */       conditions.put(EdiProductionQueryCondition.SUPPLIER_EQ, 
/*  505 */           ediProduction.getSupplier());
/*      */     }
/*  507 */     conditions.put(EdiProductionQueryCondition.UPLOADER_EQ, 
/*  508 */         ediProduction.getUploader());
/*  509 */     conditions.put(EdiProductionQueryCondition.UPLOADFILENAME_EQ, 
/*  510 */         ediProduction.getUploadFileName());
/*  511 */     conditions.put(EdiProductionQueryCondition.UPLOADTIME_EQ, 
/*  512 */         ediProduction.getUploadTime());
/*  513 */     String exportType = queryForm.getExportType();
/*  514 */     if (exportType != null && exportType.length() > 0) {
/*  515 */       List datas = manager.getList(conditions, 0, -1, 
/*  516 */           EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  517 */           queryForm.isDescend());
/*  518 */       int index = SessionTempFile.createNewTempFile(request);
/*  519 */       String fileName = "EdiProduction";
/*  520 */       String suffix = ExportUtil.export(
/*  521 */           exportType, 
/*  522 */           datas, 
/*  523 */           request, 
/*  524 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  525 */               request)), new Exportable()
/*      */           {
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  528 */               row.add(BeanUtils.getProperty(data, "id"));
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  534 */               MessageResources message = EdiProductionAction.this.getResources(request);
/*  535 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/*  536 */                     "mapping.id"));
/*      */             }
/*      */           });
/*      */       
/*  540 */       return new ActionForward("download/" + index + "/" + 
/*  541 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*      */     } 
/*  543 */     if (queryForm.isFirstInit()) {
/*  544 */       queryForm.init(manager.getListCount(conditions).intValue());
/*      */     } else {
/*  546 */       queryForm.init();
/*      */     } 
/*  548 */     int pageNum = queryForm.getPageNoAsInt();
/*  549 */     int pageSize = queryForm.getPageSizeAsInt();
/*  550 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/*  551 */         EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/*  552 */         queryForm.isDescend());
/*      */     
/*  554 */     request.setAttribute("productionId", productionId);
/*  555 */     request.setAttribute("x_selType", Integer.valueOf(166));
/*  556 */     request.setAttribute("X_RESULTLIST", entityList);
/*  557 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward drexcelmoban(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  664 */     String id = request.getParameter("id");
/*      */     
/*  666 */     HSSFWorkbook wb = new HSSFWorkbook();
/*      */     
/*  668 */     HSSFSheet sheet = wb.createSheet();
/*  669 */     wb.setSheetName(0, "半成品计划");
/*      */     
/*  671 */     sheet.setColumnWidth((short)0, (short)4000);
/*  672 */     sheet.setColumnWidth((short)1, (short)7000);
/*  673 */     sheet.setColumnWidth((short)2, (short)4000);
/*  674 */     sheet.setColumnWidth((short)3, (short)10000);
/*  675 */     sheet.setColumnWidth((short)4, (short)7000);
/*  676 */     sheet.setColumnWidth((short)5, (short)3000);
/*  677 */     sheet.setColumnWidth((short)6, (short)3000);
/*  678 */     sheet.setColumnWidth((short)7, (short)3000);
/*  679 */     sheet.setColumnWidth((short)8, (short)3000);
/*  680 */     sheet.setColumnWidth((short)9, (short)3000);
/*  681 */     sheet.setColumnWidth((short)10, (short)3000);
/*      */     
/*  683 */     HSSFCellStyle style = wb.createCellStyle();
/*      */     
/*  685 */     style.setVerticalAlignment((short)1);
/*      */     
/*  687 */     style.setAlignment((short)2);
/*  688 */     style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
/*  689 */     style.setFillPattern((short)1);
/*  690 */     style.setBorderBottom((short)1);
/*  691 */     style.setBorderLeft((short)1);
/*  692 */     style.setBorderTop((short)1);
/*  693 */     style.setBorderRight((short)1);
/*      */     
/*  695 */     HSSFCellStyle cellStyle2 = wb.createCellStyle();
/*  696 */     HSSFDataFormat format = wb.createDataFormat();
/*  697 */     cellStyle2.setDataFormat(format.getFormat("@"));
/*      */     
/*  699 */     cellStyle2.setVerticalAlignment((short)1);
/*      */     
/*  701 */     cellStyle2.setAlignment((short)2);
/*  702 */     cellStyle2
/*  703 */       .setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
/*  704 */     cellStyle2.setFillPattern((short)1);
/*  705 */     cellStyle2.setBorderBottom((short)1);
/*  706 */     cellStyle2.setBorderLeft((short)1);
/*  707 */     cellStyle2.setBorderTop((short)1);
/*  708 */     cellStyle2.setBorderRight((short)1);
/*      */     
/*  710 */     HSSFRow row = sheet.createRow(0);
/*      */     
/*  712 */     row.setHeight((short)300);
/*      */     
/*  714 */     HSSFCell ce = row.createCell((short)0);
/*  716 */     ce.setCellValue("物料号");
/*  717 */     ce.setCellStyle(style);
/*      */
/*  725 */     ce = row.createCell((short)1);
/*  727 */     ce.setCellValue("描述");
/*  728 */     ce.setCellStyle(style);
/*      */     
/*  730 */     ce = row.createCell((short)2);
/*  732 */     ce.setCellValue("计划产量");
/*  733 */     ce.setCellStyle(style);
/*      */     
/*  735 */     ce = row.createCell((short)3);
/*  737 */     ce.setCellValue("生产日期（yyyyMMdd）");
/*  738 */     ce.setCellStyle(style);
/*      */     
/*  740 */     ce = row.createCell((short)4);
/*  742 */     ce.setCellValue("时间（从当天0点到当前时间）（HHmmss）");
/*  743 */     ce.setCellStyle(cellStyle2);
/*      */     
/*  745 */     response.setContentType("appliction/x-msdownload");
///*  746 */     response.setCharacterEncoding("utf-8");
/*  747 */     String fileName = "BanChengPinJiHua";
/*  748 */     response.setHeader("Content-Disposition", "attachment; filename=" + 
/*  749 */         new String(fileName.getBytes("UTF-8"), "UTF-8") + ".xls");
/*  750 */     ServletOutputStream outStream = null;
/*      */     try {
/*  752 */       outStream = response.getOutputStream();
/*  753 */       wb.write((OutputStream)outStream);
/*  754 */     } catch (Exception e) {
/*  755 */       e.printStackTrace();
/*      */     } finally {
/*  757 */       outStream.flush();
/*  758 */       outStream.close();
/*      */     } 
/*  760 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward importeUnfinishPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  767 */     EdiProductionManager manager = 
/*  768 */       ServiceLocator.getEdiProductionManager(request);
/*  769 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/*  770 */     ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
/*  771 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/*  772 */     FormFile uploadfile = queryForm.getFileContent();
/*  773 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhssSS");
/*  774 */     SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
/*  775 */     String asnNo = format1.format(new Date());
/*  776 */     int fileSize = 0;
/*  777 */     String fileName = "";
/*  778 */     if (uploadfile != null)
/*  779 */       fileSize = uploadfile.getFileSize(); 
/*  780 */     InputStream inputstream = null;
/*  781 */     HSSFWorkbook wb = null;
/*  782 */     Date date = new Date();
/*      */     
/*  784 */     User user = getCurrentUser(request);
/*  785 */     String supplierNo = user.getLoginName();
/*  786 */     SupplierManager sManager = ServiceLocator.getSupplierManager(request);
/*  787 */     Supplier supplier = sManager.getSupplierByCode(supplierNo);
/*  788 */     if (fileSize > 0) {
/*  789 */       if (uploadfile != null && uploadfile.getFileSize() > 0) {
/*  790 */         fileName = uploadfile.getFileName();
/*  791 */         String xls = fileName.substring(fileName.indexOf(".") + 1, 
/*  792 */             fileName.length());
/*  793 */         if (xls.indexOf("xls") == -1) {
/*  794 */           postGlobalMessage("Import.the.file.is.not", 
/*  795 */               request.getSession());
/*  796 */           return new ActionForward("listUnfinishPlanGroup.do", true);
/*      */         } 
/*      */       } 
/*      */       
/*  800 */       boolean isread = true;
/*      */       try {
/*  802 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/*  803 */       } catch (Exception ex) {
/*  804 */         wb = new HSSFWorkbook(uploadfile.getInputStream());
/*      */       } 
/*      */       
/*  807 */       Map<String, Object> data = new HashMap<String, Object>();
/*  808 */       List<List<String>> allList = new ArrayList<List<String>>();
/*  809 */       HSSFSheet sheet11 = wb.getSheetAt(0);
/*  810 */       if (sheet11 == null) {
/*  811 */         System.out.println("SHEET不存在");
/*      */       }
/*  813 */       System.out.println(sheet11.getLastRowNum());
/*  814 */       for (int rowNum = 1; rowNum <= sheet11.getLastRowNum() + 1; rowNum++) {
/*  815 */         List<String> list = new ArrayList<String>();
/*  816 */         HSSFRow row = sheet11.getRow(rowNum);
/*  817 */         if (row != null) {
/*      */ 
/*      */           
/*  820 */           for (short cellNum = 0; cellNum <= row.getLastCellNum(); cellNum = (short)(cellNum + 1)) {
/*  821 */             HSSFCell cell = row.getCell(cellNum);
/*  822 */             System.out.println(cellNum);
/*  823 */             if (cell != null) {
/*  824 */               String de = getValue(sheet11.getRow(rowNum).getCell(
/*  825 */                     cellNum));
/*  826 */               list.add(de);
/*      */             } else {
/*  828 */               list.add("");
/*      */             } 
/*      */           } 
/*      */           
/*  832 */           allList.add(list);
/*      */         } 
/*      */       } 
/*      */       
/*  836 */       List<EdiProduction> productions = new ArrayList<EdiProduction>();
/*  837 */       int b = allList.size();
/*  838 */       for (int j = 0; j < allList.size(); j++) {
/*  839 */         List<String> list = allList.get(j);
/*  840 */         Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  841 */         EdiProduction ediProduction = new EdiProduction();
/*  842 */         conditions.put(WmsPartQueryCondition.ID_EQ, list.get(0));
/*  843 */         List<WmsPart> partList = partManager.getWmsPartList(conditions, 
/*  844 */             0, -1, null, false);
/*  845 */         WmsPart part = null;
/*  846 */         if (partList.size() > 0) {
/*  847 */           part = partList.get(0);
/*      */         } else {
/*  849 */           part = new WmsPart();
/*      */         } 
/*  851 */         ediProduction.setAsnNo(asnNo);
/*      */ 
/*      */         
/*  854 */         if (supplier == null) {
/*  855 */           ediProduction.setSupplier(null);
/*      */         } else {
/*  857 */           ediProduction.setSupplier(supplier.getCode());
/*      */         } 
/*  859 */         ediProduction.setUploader(user.getName());
/*  860 */         ediProduction.setUploadFileName(fileName);
/*  861 */         ediProduction.setUploadTime(date);
/*  862 */         ediProduction.setType(Integer.valueOf(2));
/*  863 */         ediProduction.setStatus(Integer.valueOf(0));
/*  864 */         int a = list.size();
/*  865 */         for (int k = 0; k <= list.size(); k++) {
/*  866 */           if (k == 0) {
/*  867 */             if (((String)list.get(k)).equals("")) {
/*  868 */               data.put("key", "itemNumber.code");
/*  869 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  870 */                   k + 1) + "列");
/*  871 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  874 */             if (partList.size() == 0) {
/*  875 */               data.put("key", "itemNumber.codeNot");
/*  876 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  877 */                   k + 1) + "列" + " " + (String)list.get(k));
/*  878 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  881 */             ediProduction.setModels(list.get(k));
/*      */           }
/*  883 */           else if (k == 1) {
/*  884 */             if (((String)list.get(k)).equals("")) {
/*  885 */               data.put("key", "itemNumber.desc");
/*  886 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  887 */                   k + 1) + "列");
/*  888 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  891 */             if (partList.size() == 0) {
/*  892 */               data.put("key", "itemNumber.desc");
/*  893 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  894 */                   k + 1) + "列" + " " + (String)list.get(k));
/*  895 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  898 */             ediProduction.setDescribe(list.get(k));
/*      */           }
/*  900 */           else if (k == 2) {
/*  901 */             if (((String)list.get(k)).equals("")) {
/*  902 */               data.put("key", "ediProduction.qty");
/*  903 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  904 */                   k + 1) + "列");
/*  905 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  908 */             ediProduction.setQty(Integer.valueOf(Integer.parseInt(list.get(k))));
/*      */           }
/*  910 */           else if (k == 3) {
/*  911 */             if (((String)list.get(k)).equals("")) {
/*  912 */               data.put("key", "ediProduction.date");
/*  913 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  914 */                   k + 1) + "列");
/*  915 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  918 */             SimpleDateFormat format = new SimpleDateFormat(
/*  919 */                 "yyyyMMdd");
/*  920 */             ediProduction
/*  921 */               .setTaskDate(format.parse(list.get(k)));
/*      */           
/*      */           }
/*  924 */           else if (k == 4) {
/*  925 */             if (((String)list.get(k)).equals("")) {
/*  926 */               data.put("key", "ediProduction.time");
/*  927 */               data.put("num", "第" + (j + 2) + "行" + " " + "第" + (
/*  928 */                   k + 1) + "列");
/*  929 */               datas.add(data);
/*      */               break;
/*      */             } 
/*  932 */             ediProduction.setTime(list.get(k));
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  937 */         if (datas.size() != 0) {
/*  938 */           request.setAttribute("msg", datas);
/*  939 */           return mapping.findForward("add");
/*      */         } 
/*  941 */         productions.add(ediProduction);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  946 */       if (datas.size() != 0) {
/*  947 */         request.setAttribute("msg", datas);
/*  948 */         return mapping.findForward("add");
/*      */       } 
/*  950 */       for (int i = 0; i < productions.size(); i++) {
/*  951 */         EdiProduction ediProduction = productions.get(i);
/*  952 */         manager.save(ediProduction);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  957 */     postGlobalMessage("Import.the.file.date.null", 
/*  958 */         request.getSession());
/*  959 */     return new ActionForward("listUnfinishPlanGroup.do", true);
/*      */   }
/*      */   
/*      */   public List<Integer> digital(String s) {
/*  963 */     List<Integer> thr = new ArrayList<Integer>();
/*  964 */     List<String> strList = resolution(s);
/*  965 */     String str = "";
/*  966 */     for (int i = 0; i < strList.size(); i++) {
/*  967 */       while (isNumeric(strList.get(i)).intValue() == 1) {
/*  968 */         str = String.valueOf(str) + (String)strList.get(i);
/*  969 */         i++;
/*      */       } 
/*  971 */       if (str != "") {
/*  972 */         thr.add(Integer.valueOf(Integer.parseInt(str)));
/*  973 */         str = "";
/*      */       } 
/*      */     } 
/*  976 */     return thr;
/*      */   }
/*      */   
/*      */   public List<String> resolution(String str) {
/*  980 */     List<String> strList = new ArrayList<String>();
/*  981 */     for (int i = 0; i < str.length(); i++) {
/*  982 */       strList.add(str.substring(i, i + 1));
/*      */     }
/*  984 */     return strList;
/*      */   }
/*      */   
/*      */   public Integer isNumeric(String str) {
/*      */     try {
/*  989 */       Integer num = Integer.valueOf(str);
/*  990 */       return Integer.valueOf(1);
/*  991 */     } catch (Exception e) {
/*      */       try {
/*  993 */         BigDecimal d = new BigDecimal(str);
/*  994 */         return Integer.valueOf(2);
/*  995 */       } catch (Exception e2) {
/*  996 */         return Integer.valueOf(3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getMergedRegionValue(Sheet sheet, int row, int column) {
/* 1011 */     int sheetMergeCount = sheet.getNumMergedRegions();
/*      */     
/* 1013 */     for (int i = 0; i < sheetMergeCount; i++) {
/* 1014 */       CellRangeAddress ca = sheet.getMergedRegion(i);
/* 1015 */       int firstColumn = ca.getFirstColumn();
/* 1016 */       int lastColumn = ca.getLastColumn();
/* 1017 */       int firstRow = ca.getFirstRow();
/* 1018 */       int lastRow = ca.getLastRow();
/*      */       
/* 1020 */       if (row >= firstRow && row <= lastRow)
/*      */       {
/* 1022 */         if (column >= firstColumn && column <= lastColumn) {
/* 1023 */           Row srow = sheet.getRow(firstRow);
/* 1024 */           Cell fCell = srow.getCell((short)firstColumn);
/* 1025 */           return getValue(fCell);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1030 */     return null;
/*      */   }
/*      */
/*      */   private boolean isMergedRow(Sheet sheet, int row, int column) {
/* 1056 */     int sheetMergeCount = sheet.getNumMergedRegions();
/* 1057 */     for (int i = 0; i < sheetMergeCount; i++) {
/* 1058 */       CellRangeAddress range = sheet.getMergedRegion(i);
/* 1059 */       int firstColumn = range.getFirstColumn();
/* 1060 */       int lastColumn = range.getLastColumn();
/* 1061 */       int firstRow = range.getFirstRow();
/* 1062 */       int lastRow = range.getLastRow();
/* 1063 */       if (row == firstRow && row == lastRow && 
/* 1064 */         column >= firstColumn && column <= lastColumn) {
/* 1065 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1069 */     return false;
/*      */   }
/*      */
/*      */   private boolean isMergedRegion(Sheet sheet, int row, int column) {
/* 1083 */     int sheetMergeCount = sheet.getNumMergedRegions();
/* 1084 */     for (int i = 0; i < sheetMergeCount; i++) {
/* 1085 */       CellRangeAddress range = sheet.getMergedRegion(i);
/* 1086 */       int firstColumn = range.getFirstColumn();
/* 1087 */       int lastColumn = range.getLastColumn();
/* 1088 */       int firstRow = range.getFirstRow();
/* 1089 */       int lastRow = range.getLastRow();
/* 1090 */       if (row >= firstRow && row <= lastRow && 
/* 1091 */         column >= firstColumn && column <= lastColumn) {
/* 1092 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1096 */     return false;
/*      */   }
/*      */   
/*      */   private static String getValue(Cell hssfCell) {
/* 1100 */     if (hssfCell.getCellType() == 4)
/* 1101 */       return String.valueOf(hssfCell.getBooleanCellValue()); 
/* 1102 */     if (hssfCell.getCellType() == 0) {
/* 1103 */       return String.valueOf(hssfCell.getNumericCellValue());
/*      */     }
/* 1105 */     return String.valueOf(hssfCell.getStringCellValue());
/*      */   }
/*      */ 
/*      */   
/*      */   private String getValue(HSSFCell cell) {
/* 1110 */     if (cell.getCellType() == 4)
/* 1111 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 1112 */         .toPlainString(); 
/* 1113 */     if (cell.getCellType() == 0)
/* 1114 */       return (new BigDecimal(String.valueOf(cell.getNumericCellValue())))
/* 1115 */         .setScale(0, 4).toPlainString(); 
/* 1116 */     if (cell.getCellType() == 2) {
/* 1117 */       return String.valueOf(cell.getNumericCellValue());
/*      */     }
/* 1119 */     return String.valueOf(cell.getStringCellValue().trim());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward decompositiontUnfinishPlanAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1126 */     response.setContentType("text/json");
/* 1128 */     JsonConfig cfg = new JsonConfig();
/* 1129 */     EdiProductionManager manager = 
/* 1130 */       ServiceLocator.getEdiProductionManager(request);
/* 1131 */     JitProductionPlanManager jitProductionPlanManager = 
/* 1132 */       ServiceLocator.getJitProductionPlanManager(request);
/* 1133 */     BomManager bomManager = ServiceLocator.getBomManager(request);
/* 1134 */     String productionId = request.getParameter("productionId");
/* 1135 */     EdiProduction ediProductiona = manager.getEdiProduction(
/* 1136 */         Integer.valueOf(Integer.parseInt(productionId)));
/*      */     
/* 1138 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1139 */     conditions.put(EdiProductionQueryCondition.STATUS_NOT_EQ, Integer.valueOf(1));
/* 1140 */     conditions.put(EdiProductionQueryCondition.TYPE_EQ, Integer.valueOf(2));
/* 1141 */     if (ediProductiona.getSupplier() != null && 
/* 1142 */       ediProductiona.getSupplier().trim().length() > 0) {
/* 1143 */       conditions.put(EdiProductionQueryCondition.SUPPLIER_EQ, 
/* 1144 */           ediProductiona.getSupplier());
/*      */     }
/* 1146 */     conditions.put(EdiProductionQueryCondition.UPLOADER_EQ, 
/* 1147 */         ediProductiona.getUploader());
/* 1148 */     conditions.put(EdiProductionQueryCondition.UPLOADFILENAME_EQ, 
/* 1149 */         ediProductiona.getUploadFileName());
/* 1150 */     conditions.put(EdiProductionQueryCondition.UPLOADTIME_EQ, 
/* 1151 */         ediProductiona.getUploadTime());
/* 1152 */     List<EdiProduction> ediProductionList = manager.getList(conditions, 0, 
/* 1153 */         -1, null, false);
/* 1154 */     for (EdiProduction ediProduction : ediProductionList) {
/* 1155 */       conditions.clear();
/* 1156 */       conditions.put(BomQueryCondition.FATHER_PART_EQ, 
/* 1157 */           ediProduction.getModels());
/* 1158 */       List<Bom> bomList = bomManager.getBomList(conditions, 0, -1, null, 
/* 1159 */           false);
/* 1160 */       if (bomList != null && bomList.size() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1165 */         ediProduction.setStatus(Integer.valueOf(1));
/* 1166 */         manager.update(ediProduction); continue;
/*      */       } 
/* 1168 */       ediProduction.setStatus(Integer.valueOf(2));
/* 1169 */       manager.update(ediProduction);
/*      */     } 
/*      */ 
/*      */     
/* 1173 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 1174 */     response.getWriter().print(jo);
/* 1175 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward deleteEdiProduction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 1182 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
/* 1183 */     EdiProductionManager manager = 
/* 1184 */       ServiceLocator.getEdiProductionManager(request);
/* 1185 */     QadOrEdiManager qadOrEdiManager = 
/* 1186 */       ServiceLocator.getQadOrEdiManager(request);
/* 1187 */     BomManager bomManager = ServiceLocator.getBomManager(request);
/* 1188 */     JitProductionPlanManager jitProductionPlanManager = 
/* 1189 */       ServiceLocator.getJitProductionPlanManager(request);
/* 1190 */     String id = request.getParameter("id");
/* 1191 */     EdiProduction ediProduction = manager.getEdiProduction(
/* 1192 */         Integer.valueOf(Integer.parseInt(id)));
/* 1193 */     if (ediProduction.getStatus().intValue() == 1) {
/* 1194 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 1195 */       conditions.clear();
/* 1196 */       conditions.put(QadOrEdiQueryCondition.MODELS_EQ, 
/* 1197 */           ediProduction.getAsnNo());
/* 1198 */       List<QadOrEdi> qadOrList = qadOrEdiManager.getList(conditions, 0, 
/* 1199 */           -1, null, false);
/* 1200 */       String str = "";
/* 1201 */       if (qadOrList.size() > 0) {
/* 1202 */         boolean sign = true;
/* 1203 */         for (QadOrEdi qadOrEdi : qadOrList) {
/* 1204 */           conditions.clear();
/* 1205 */           conditions.put(BomQueryCondition.FATHER_PART_EQ, 
/* 1206 */               qadOrEdi.getQadPart());
/* 1207 */           List<Bom> bomList = bomManager.getBomList(conditions, 0, 
/* 1208 */               -1, null, false);
/* 1209 */           if (bomList == null || bomList.size() == 0) {
/* 1210 */             sign = false;
/* 1211 */             str = String.valueOf(str) + "在物料bom里面找不到父料号" + qadOrEdi.getQadPart().getId() + 
/* 1212 */               "\n";
/*      */           } 
/*      */         } 
/* 1215 */         if (sign) {
/* 1216 */           for (QadOrEdi qadOrEdi : qadOrList) {
/* 1217 */             conditions.clear();
/* 1218 */             conditions.put(BomQueryCondition.FATHER_PART_EQ, 
/* 1219 */                 qadOrEdi.getQadPart());
/* 1220 */             List<Bom> bomList = bomManager.getBomList(conditions, 
/* 1221 */                 0, -1, null, false);
/* 1222 */             if (bomList != null && bomList.size() > 0) {
/* 1223 */               Map<String, BigDecimal> mapStr = jitProductionPlanManager
/* 1224 */                 .getPartNeedQtyByBom(
/* 1225 */                   ediProduction, (
/* 1226 */                   (Bom)bomList.get(0)).getFather_part(), 
/* 1227 */                   new BigDecimal(
/* 1228 */                     (ediProduction.getQty() == null) ? 0 : 
/* 1229 */                     ediProduction.getQty().intValue()));
/*      */               
/* 1231 */               conditions.clear();
/* 1232 */               conditions
/* 1233 */                 .put(JitProductionPlanQueryCondition.PRODUCTION_ID_EQ, 
/* 1234 */                   ediProduction.getId());
/* 1235 */               conditions.put(
/* 1236 */                   JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/* 1237 */               List<JitProductionPlan> jitProductionPlanList = jitProductionPlanManager
/* 1238 */                 .getList(conditions, 0, -1, null, false);
/* 1239 */               Iterator<String> iterator = mapStr.keySet().iterator();
/* 1240 */               while (iterator.hasNext()) {
/* 1241 */                 String strk = iterator.next();
/* 1242 */                 if (((BigDecimal)mapStr.get(strk)).compareTo(
/* 1243 */                     new BigDecimal(0)) == 1) {
/* 1244 */                   String[] strarr = strk.split("\\+");
/* 1245 */                   String partId = strarr[0].toString();
/* 1246 */                   String TaskDate = strarr[1].toString();
/* 1247 */                   Integer hour = Integer.valueOf(Integer.parseInt(strarr[2]));
/*      */                   
/* 1249 */                   for (JitProductionPlan jitProductionPlan : jitProductionPlanList) {
/* 1250 */                     if (((BigDecimal)mapStr.get(strk)).compareTo(
/* 1251 */                         new BigDecimal(0)) == 1) {
/* 1252 */                       if (jitProductionPlan
/* 1253 */                         .getChildPart().getId()
/* 1254 */                         .equals(partId)) {
/* 1255 */                         if (TaskDate
/* 1256 */                           .equals(sdf1
/* 1257 */                             .format(jitProductionPlan
/* 1258 */                               .getDate()))) {
/* 1259 */                           if (hour.intValue() == 2) {
/* 1260 */                             if (jitProductionPlan
/* 1261 */                               .getHour2DemandQty() != null) {
/* 1262 */                               if (((BigDecimal)mapStr
/* 1263 */                                 .get(strk))
/* 1264 */                                 .compareTo(
/* 1265 */                                   jitProductionPlan
/* 1266 */                                   .getHour2DemandQty()) != -1) {
/* 1267 */                                 BigDecimal bigDecimal = mapStr
/* 1268 */                                   .get(strk);
/* 1269 */                                 mapStr.put(
/* 1270 */                                     strk, 
/* 1271 */                                     bigDecimal.subtract(jitProductionPlan
/* 1272 */                                       .getHour2DemandQty()));
/*      */                                 
/* 1274 */                                 jitProductionPlan
/* 1275 */                                   .setQty(jitProductionPlan
/* 1276 */                                     .getQty()
/* 1277 */                                     .subtract(
/* 1278 */                                       jitProductionPlan
/* 1279 */                                       .getHour2DemandQty()));
/* 1280 */                                 jitProductionPlan
/* 1281 */                                   .setHour2DemandQty(new BigDecimal(
/* 1282 */                                       0));
/* 1283 */                                 jitProductionPlan = jitProductionPlanManager
/* 1284 */                                   .update(jitProductionPlan); continue;
/*      */                               } 
/* 1286 */                               BigDecimal qtysk = mapStr
/* 1287 */                                 .get(strk);
/* 1288 */                               mapStr.put(
/* 1289 */                                   strk, 
/* 1290 */                                   new BigDecimal(
/* 1291 */                                     0));
/* 1292 */                               jitProductionPlan
/* 1293 */                                 .setQty(jitProductionPlan
/* 1294 */                                   .getQty()
/* 1295 */                                   .subtract(
/* 1296 */                                     qtysk));
/* 1297 */                               jitProductionPlan
/* 1298 */                                 .setHour2DemandQty(jitProductionPlan
/* 1299 */                                   .getHour2DemandQty()
/* 1300 */                                   .subtract(
/* 1301 */                                     qtysk));
/* 1302 */                               jitProductionPlan = jitProductionPlanManager
/* 1303 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1307 */                           if (hour.intValue() == 4) {
/* 1308 */                             if (jitProductionPlan
/* 1309 */                               .getHour4DemandQty() != null) {
/* 1310 */                               if (((BigDecimal)mapStr
/* 1311 */                                 .get(strk))
/* 1312 */                                 .compareTo(
/* 1313 */                                   jitProductionPlan
/* 1314 */                                   .getHour4DemandQty()) != -1) {
/* 1315 */                                 BigDecimal bigDecimal = mapStr
/* 1316 */                                   .get(strk);
/* 1317 */                                 mapStr.put(
/* 1318 */                                     strk, 
/* 1319 */                                     bigDecimal.subtract(jitProductionPlan
/* 1320 */                                       .getHour4DemandQty()));
/*      */                                 
/* 1322 */                                 jitProductionPlan
/* 1323 */                                   .setQty(jitProductionPlan
/* 1324 */                                     .getQty()
/* 1325 */                                     .subtract(
/* 1326 */                                       jitProductionPlan
/* 1327 */                                       .getHour4DemandQty()));
/* 1328 */                                 jitProductionPlan
/* 1329 */                                   .setHour4DemandQty(new BigDecimal(
/* 1330 */                                       0));
/* 1331 */                                 jitProductionPlan = jitProductionPlanManager
/* 1332 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1335 */                               BigDecimal qtysk = mapStr
/* 1336 */                                 .get(strk);
/* 1337 */                               mapStr.put(
/* 1338 */                                   strk, 
/* 1339 */                                   new BigDecimal(
/* 1340 */                                     0));
/* 1341 */                               jitProductionPlan
/* 1342 */                                 .setQty(jitProductionPlan
/* 1343 */                                   .getQty()
/* 1344 */                                   .subtract(
/* 1345 */                                     qtysk));
/* 1346 */                               jitProductionPlan
/* 1347 */                                 .setHour4DemandQty(jitProductionPlan
/* 1348 */                                   .getHour4DemandQty()
/* 1349 */                                   .subtract(
/* 1350 */                                     qtysk));
/* 1351 */                               jitProductionPlan = jitProductionPlanManager
/* 1352 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1356 */                           if (hour.intValue() == 6) {
/* 1357 */                             if (jitProductionPlan
/* 1358 */                               .getHour6DemandQty() != null) {
/* 1359 */                               if (((BigDecimal)mapStr
/* 1360 */                                 .get(strk))
/* 1361 */                                 .compareTo(
/* 1362 */                                   jitProductionPlan
/* 1363 */                                   .getHour6DemandQty()) != -1) {
/* 1364 */                                 BigDecimal bigDecimal = mapStr
/* 1365 */                                   .get(strk);
/* 1366 */                                 mapStr.put(
/* 1367 */                                     strk, 
/* 1368 */                                     bigDecimal.subtract(jitProductionPlan
/* 1369 */                                       .getHour6DemandQty()));
/*      */                                 
/* 1371 */                                 jitProductionPlan
/* 1372 */                                   .setQty(jitProductionPlan
/* 1373 */                                     .getQty()
/* 1374 */                                     .subtract(
/* 1375 */                                       jitProductionPlan
/* 1376 */                                       .getHour6DemandQty()));
/* 1377 */                                 jitProductionPlan
/* 1378 */                                   .setHour6DemandQty(new BigDecimal(
/* 1379 */                                       0));
/* 1380 */                                 jitProductionPlan = jitProductionPlanManager
/* 1381 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1384 */                               BigDecimal qtysk = mapStr
/* 1385 */                                 .get(strk);
/* 1386 */                               mapStr.put(
/* 1387 */                                   strk, 
/* 1388 */                                   new BigDecimal(
/* 1389 */                                     0));
/* 1390 */                               jitProductionPlan
/* 1391 */                                 .setQty(jitProductionPlan
/* 1392 */                                   .getQty()
/* 1393 */                                   .subtract(
/* 1394 */                                     qtysk));
/* 1395 */                               jitProductionPlan
/* 1396 */                                 .setHour6DemandQty(jitProductionPlan
/* 1397 */                                   .getHour6DemandQty()
/* 1398 */                                   .subtract(
/* 1399 */                                     qtysk));
/* 1400 */                               jitProductionPlan = jitProductionPlanManager
/* 1401 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1405 */                           if (hour.intValue() == 8) {
/* 1406 */                             if (jitProductionPlan
/* 1407 */                               .getHour8DemandQty() != null) {
/* 1408 */                               if (((BigDecimal)mapStr
/* 1409 */                                 .get(strk))
/* 1410 */                                 .compareTo(
/* 1411 */                                   jitProductionPlan
/* 1412 */                                   .getHour8DemandQty()) != -1) {
/* 1413 */                                 BigDecimal bigDecimal = mapStr
/* 1414 */                                   .get(strk);
/* 1415 */                                 mapStr.put(
/* 1416 */                                     strk, 
/* 1417 */                                     bigDecimal.subtract(jitProductionPlan
/* 1418 */                                       .getHour8DemandQty()));
/*      */                                 
/* 1420 */                                 jitProductionPlan
/* 1421 */                                   .setQty(jitProductionPlan
/* 1422 */                                     .getQty()
/* 1423 */                                     .subtract(
/* 1424 */                                       jitProductionPlan
/* 1425 */                                       .getHour8DemandQty()));
/* 1426 */                                 jitProductionPlan
/* 1427 */                                   .setHour8DemandQty(new BigDecimal(
/* 1428 */                                       0));
/* 1429 */                                 jitProductionPlan = jitProductionPlanManager
/* 1430 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1433 */                               BigDecimal qtysk = mapStr
/* 1434 */                                 .get(strk);
/* 1435 */                               mapStr.put(
/* 1436 */                                   strk, 
/* 1437 */                                   new BigDecimal(
/* 1438 */                                     0));
/* 1439 */                               jitProductionPlan
/* 1440 */                                 .setQty(jitProductionPlan
/* 1441 */                                   .getQty()
/* 1442 */                                   .subtract(
/* 1443 */                                     qtysk));
/* 1444 */                               jitProductionPlan
/* 1445 */                                 .setHour8DemandQty(jitProductionPlan
/* 1446 */                                   .getHour8DemandQty()
/* 1447 */                                   .subtract(
/* 1448 */                                     qtysk));
/* 1449 */                               jitProductionPlan = jitProductionPlanManager
/* 1450 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1454 */                           if (hour.intValue() == 10) {
/* 1455 */                             if (jitProductionPlan
/* 1456 */                               .getHour10DemandQty() != null) {
/* 1457 */                               if (((BigDecimal)mapStr
/* 1458 */                                 .get(strk))
/* 1459 */                                 .compareTo(
/* 1460 */                                   jitProductionPlan
/* 1461 */                                   .getHour10DemandQty()) != -1) {
/* 1462 */                                 BigDecimal bigDecimal = mapStr
/* 1463 */                                   .get(strk);
/* 1464 */                                 mapStr.put(
/* 1465 */                                     strk, 
/* 1466 */                                     bigDecimal.subtract(jitProductionPlan
/* 1467 */                                       .getHour10DemandQty()));
/*      */                                 
/* 1469 */                                 jitProductionPlan
/* 1470 */                                   .setQty(jitProductionPlan
/* 1471 */                                     .getQty()
/* 1472 */                                     .subtract(
/* 1473 */                                       jitProductionPlan
/* 1474 */                                       .getHour10DemandQty()));
/* 1475 */                                 jitProductionPlan
/* 1476 */                                   .setHour10DemandQty(new BigDecimal(
/* 1477 */                                       0));
/* 1478 */                                 jitProductionPlan = jitProductionPlanManager
/* 1479 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1482 */                               BigDecimal qtysk = mapStr
/* 1483 */                                 .get(strk);
/* 1484 */                               mapStr.put(
/* 1485 */                                   strk, 
/* 1486 */                                   new BigDecimal(
/* 1487 */                                     0));
/* 1488 */                               jitProductionPlan
/* 1489 */                                 .setQty(jitProductionPlan
/* 1490 */                                   .getQty()
/* 1491 */                                   .subtract(
/* 1492 */                                     qtysk));
/* 1493 */                               jitProductionPlan
/* 1494 */                                 .setHour10DemandQty(jitProductionPlan
/* 1495 */                                   .getHour10DemandQty()
/* 1496 */                                   .subtract(
/* 1497 */                                     qtysk));
/* 1498 */                               jitProductionPlan = jitProductionPlanManager
/* 1499 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1503 */                           if (hour.intValue() == 12) {
/* 1504 */                             if (jitProductionPlan
/* 1505 */                               .getHour12DemandQty() != null) {
/* 1506 */                               if (((BigDecimal)mapStr
/* 1507 */                                 .get(strk))
/* 1508 */                                 .compareTo(
/* 1509 */                                   jitProductionPlan
/* 1510 */                                   .getHour12DemandQty()) != -1) {
/* 1511 */                                 BigDecimal bigDecimal = mapStr
/* 1512 */                                   .get(strk);
/* 1513 */                                 mapStr.put(
/* 1514 */                                     strk, 
/* 1515 */                                     bigDecimal.subtract(jitProductionPlan
/* 1516 */                                       .getHour12DemandQty()));
/*      */                                 
/* 1518 */                                 jitProductionPlan
/* 1519 */                                   .setQty(jitProductionPlan
/* 1520 */                                     .getQty()
/* 1521 */                                     .subtract(
/* 1522 */                                       jitProductionPlan
/* 1523 */                                       .getHour12DemandQty()));
/* 1524 */                                 jitProductionPlan
/* 1525 */                                   .setHour12DemandQty(new BigDecimal(
/* 1526 */                                       0));
/* 1527 */                                 jitProductionPlan = jitProductionPlanManager
/* 1528 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1531 */                               BigDecimal qtysk = mapStr
/* 1532 */                                 .get(strk);
/* 1533 */                               mapStr.put(
/* 1534 */                                   strk, 
/* 1535 */                                   new BigDecimal(
/* 1536 */                                     0));
/* 1537 */                               jitProductionPlan
/* 1538 */                                 .setQty(jitProductionPlan
/* 1539 */                                   .getQty()
/* 1540 */                                   .subtract(
/* 1541 */                                     qtysk));
/* 1542 */                               jitProductionPlan
/* 1543 */                                 .setHour12DemandQty(jitProductionPlan
/* 1544 */                                   .getHour12DemandQty()
/* 1545 */                                   .subtract(
/* 1546 */                                     qtysk));
/* 1547 */                               jitProductionPlan = jitProductionPlanManager
/* 1548 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1552 */                           if (hour.intValue() == 14) {
/* 1553 */                             if (jitProductionPlan
/* 1554 */                               .getHour14DemandQty() != null) {
/* 1555 */                               if (((BigDecimal)mapStr
/* 1556 */                                 .get(strk))
/* 1557 */                                 .compareTo(
/* 1558 */                                   jitProductionPlan
/* 1559 */                                   .getHour14DemandQty()) != -1) {
/* 1560 */                                 BigDecimal bigDecimal = mapStr
/* 1561 */                                   .get(strk);
/* 1562 */                                 mapStr.put(
/* 1563 */                                     strk, 
/* 1564 */                                     bigDecimal.subtract(jitProductionPlan
/* 1565 */                                       .getHour14DemandQty()));
/*      */                                 
/* 1567 */                                 jitProductionPlan
/* 1568 */                                   .setQty(jitProductionPlan
/* 1569 */                                     .getQty()
/* 1570 */                                     .subtract(
/* 1571 */                                       jitProductionPlan
/* 1572 */                                       .getHour14DemandQty()));
/* 1573 */                                 jitProductionPlan
/* 1574 */                                   .setHour14DemandQty(new BigDecimal(
/* 1575 */                                       0));
/* 1576 */                                 jitProductionPlan = jitProductionPlanManager
/* 1577 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1580 */                               BigDecimal qtysk = mapStr
/* 1581 */                                 .get(strk);
/* 1582 */                               mapStr.put(
/* 1583 */                                   strk, 
/* 1584 */                                   new BigDecimal(
/* 1585 */                                     0));
/* 1586 */                               jitProductionPlan
/* 1587 */                                 .setQty(jitProductionPlan
/* 1588 */                                   .getQty()
/* 1589 */                                   .subtract(
/* 1590 */                                     qtysk));
/* 1591 */                               jitProductionPlan
/* 1592 */                                 .setHour14DemandQty(jitProductionPlan
/* 1593 */                                   .getHour14DemandQty()
/* 1594 */                                   .subtract(
/* 1595 */                                     qtysk));
/* 1596 */                               jitProductionPlan = jitProductionPlanManager
/* 1597 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1601 */                           if (hour.intValue() == 16) {
/* 1602 */                             if (jitProductionPlan
/* 1603 */                               .getHour16DemandQty() != null) {
/* 1604 */                               if (((BigDecimal)mapStr
/* 1605 */                                 .get(strk))
/* 1606 */                                 .compareTo(
/* 1607 */                                   jitProductionPlan
/* 1608 */                                   .getHour16DemandQty()) != -1) {
/* 1609 */                                 BigDecimal bigDecimal = mapStr
/* 1610 */                                   .get(strk);
/* 1611 */                                 mapStr.put(
/* 1612 */                                     strk, 
/* 1613 */                                     bigDecimal.subtract(jitProductionPlan
/* 1614 */                                       .getHour16DemandQty()));
/*      */                                 
/* 1616 */                                 jitProductionPlan
/* 1617 */                                   .setQty(jitProductionPlan
/* 1618 */                                     .getQty()
/* 1619 */                                     .subtract(
/* 1620 */                                       jitProductionPlan
/* 1621 */                                       .getHour16DemandQty()));
/* 1622 */                                 jitProductionPlan
/* 1623 */                                   .setHour16DemandQty(new BigDecimal(
/* 1624 */                                       0));
/* 1625 */                                 jitProductionPlan = jitProductionPlanManager
/* 1626 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1629 */                               BigDecimal qtysk = mapStr
/* 1630 */                                 .get(strk);
/* 1631 */                               mapStr.put(
/* 1632 */                                   strk, 
/* 1633 */                                   new BigDecimal(
/* 1634 */                                     0));
/* 1635 */                               jitProductionPlan
/* 1636 */                                 .setQty(jitProductionPlan
/* 1637 */                                   .getQty()
/* 1638 */                                   .subtract(
/* 1639 */                                     qtysk));
/* 1640 */                               jitProductionPlan
/* 1641 */                                 .setHour16DemandQty(jitProductionPlan
/* 1642 */                                   .getHour16DemandQty()
/* 1643 */                                   .subtract(
/* 1644 */                                     qtysk));
/* 1645 */                               jitProductionPlan = jitProductionPlanManager
/* 1646 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1650 */                           if (hour.intValue() == 18) {
/* 1651 */                             if (jitProductionPlan
/* 1652 */                               .getHour18DemandQty() != null) {
/* 1653 */                               if (((BigDecimal)mapStr
/* 1654 */                                 .get(strk))
/* 1655 */                                 .compareTo(
/* 1656 */                                   jitProductionPlan
/* 1657 */                                   .getHour18DemandQty()) != -1) {
/* 1658 */                                 BigDecimal bigDecimal = mapStr
/* 1659 */                                   .get(strk);
/* 1660 */                                 mapStr.put(
/* 1661 */                                     strk, 
/* 1662 */                                     bigDecimal.subtract(jitProductionPlan
/* 1663 */                                       .getHour18DemandQty()));
/*      */                                 
/* 1665 */                                 jitProductionPlan
/* 1666 */                                   .setQty(jitProductionPlan
/* 1667 */                                     .getQty()
/* 1668 */                                     .subtract(
/* 1669 */                                       jitProductionPlan
/* 1670 */                                       .getHour18DemandQty()));
/* 1671 */                                 jitProductionPlan
/* 1672 */                                   .setHour18DemandQty(new BigDecimal(
/* 1673 */                                       0));
/* 1674 */                                 jitProductionPlan = jitProductionPlanManager
/* 1675 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1678 */                               BigDecimal qtysk = mapStr
/* 1679 */                                 .get(strk);
/* 1680 */                               mapStr.put(
/* 1681 */                                   strk, 
/* 1682 */                                   new BigDecimal(
/* 1683 */                                     0));
/* 1684 */                               jitProductionPlan
/* 1685 */                                 .setQty(jitProductionPlan
/* 1686 */                                   .getQty()
/* 1687 */                                   .subtract(
/* 1688 */                                     qtysk));
/* 1689 */                               jitProductionPlan
/* 1690 */                                 .setHour18DemandQty(jitProductionPlan
/* 1691 */                                   .getHour18DemandQty()
/* 1692 */                                   .subtract(
/* 1693 */                                     qtysk));
/* 1694 */                               jitProductionPlan = jitProductionPlanManager
/* 1695 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1699 */                           if (hour.intValue() == 20) {
/* 1700 */                             if (jitProductionPlan
/* 1701 */                               .getHour20DemandQty() != null) {
/* 1702 */                               if (((BigDecimal)mapStr
/* 1703 */                                 .get(strk))
/* 1704 */                                 .compareTo(
/* 1705 */                                   jitProductionPlan
/* 1706 */                                   .getHour20DemandQty()) != -1) {
/* 1707 */                                 BigDecimal bigDecimal = mapStr
/* 1708 */                                   .get(strk);
/* 1709 */                                 mapStr.put(
/* 1710 */                                     strk, 
/* 1711 */                                     bigDecimal.subtract(jitProductionPlan
/* 1712 */                                       .getHour20DemandQty()));
/*      */                                 
/* 1714 */                                 jitProductionPlan
/* 1715 */                                   .setQty(jitProductionPlan
/* 1716 */                                     .getQty()
/* 1717 */                                     .subtract(
/* 1718 */                                       jitProductionPlan
/* 1719 */                                       .getHour20DemandQty()));
/* 1720 */                                 jitProductionPlan
/* 1721 */                                   .setHour20DemandQty(new BigDecimal(
/* 1722 */                                       0));
/* 1723 */                                 jitProductionPlan = jitProductionPlanManager
/* 1724 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1727 */                               BigDecimal qtysk = mapStr
/* 1728 */                                 .get(strk);
/* 1729 */                               mapStr.put(
/* 1730 */                                   strk, 
/* 1731 */                                   new BigDecimal(
/* 1732 */                                     0));
/* 1733 */                               jitProductionPlan
/* 1734 */                                 .setQty(jitProductionPlan
/* 1735 */                                   .getQty()
/* 1736 */                                   .subtract(
/* 1737 */                                     qtysk));
/* 1738 */                               jitProductionPlan
/* 1739 */                                 .setHour20DemandQty(jitProductionPlan
/* 1740 */                                   .getHour20DemandQty()
/* 1741 */                                   .subtract(
/* 1742 */                                     qtysk));
/* 1743 */                               jitProductionPlan = jitProductionPlanManager
/* 1744 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1748 */                           if (hour.intValue() == 22) {
/* 1749 */                             if (jitProductionPlan
/* 1750 */                               .getHour22DemandQty() != null) {
/* 1751 */                               if (((BigDecimal)mapStr
/* 1752 */                                 .get(strk))
/* 1753 */                                 .compareTo(
/* 1754 */                                   jitProductionPlan
/* 1755 */                                   .getHour22DemandQty()) != -1) {
/* 1756 */                                 BigDecimal bigDecimal = mapStr
/* 1757 */                                   .get(strk);
/* 1758 */                                 mapStr.put(
/* 1759 */                                     strk, 
/* 1760 */                                     bigDecimal.subtract(jitProductionPlan
/* 1761 */                                       .getHour22DemandQty()));
/*      */                                 
/* 1763 */                                 jitProductionPlan
/* 1764 */                                   .setQty(jitProductionPlan
/* 1765 */                                     .getQty()
/* 1766 */                                     .subtract(
/* 1767 */                                       jitProductionPlan
/* 1768 */                                       .getHour22DemandQty()));
/* 1769 */                                 jitProductionPlan
/* 1770 */                                   .setHour22DemandQty(new BigDecimal(
/* 1771 */                                       0));
/* 1772 */                                 jitProductionPlan = jitProductionPlanManager
/* 1773 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1776 */                               BigDecimal qtysk = mapStr
/* 1777 */                                 .get(strk);
/* 1778 */                               mapStr.put(
/* 1779 */                                   strk, 
/* 1780 */                                   new BigDecimal(
/* 1781 */                                     0));
/* 1782 */                               jitProductionPlan
/* 1783 */                                 .setQty(jitProductionPlan
/* 1784 */                                   .getQty()
/* 1785 */                                   .subtract(
/* 1786 */                                     qtysk));
/* 1787 */                               jitProductionPlan
/* 1788 */                                 .setHour22DemandQty(jitProductionPlan
/* 1789 */                                   .getHour22DemandQty()
/* 1790 */                                   .subtract(
/* 1791 */                                     qtysk));
/* 1792 */                               jitProductionPlan = jitProductionPlanManager
/* 1793 */                                 .update(jitProductionPlan); break;
/*      */                             }  continue;
/*      */                           } 
/* 1796 */                           if (hour.intValue() == 24 && 
/* 1797 */                             jitProductionPlan
/* 1798 */                             .getHour24DemandQty() != null) {
/* 1799 */                             if (((BigDecimal)mapStr
/* 1800 */                               .get(strk))
/* 1801 */                               .compareTo(
/* 1802 */                                 jitProductionPlan
/* 1803 */                                 .getHour24DemandQty()) != -1) {
/* 1804 */                               BigDecimal bigDecimal = mapStr
/* 1805 */                                 .get(strk);
/* 1806 */                               mapStr.put(
/* 1807 */                                   strk, 
/* 1808 */                                   bigDecimal.subtract(jitProductionPlan
/* 1809 */                                     .getHour24DemandQty()));
/*      */                               
/* 1811 */                               jitProductionPlan
/* 1812 */                                 .setQty(jitProductionPlan
/* 1813 */                                   .getQty()
/* 1814 */                                   .subtract(
/* 1815 */                                     jitProductionPlan
/* 1816 */                                     .getHour24DemandQty()));
/* 1817 */                               jitProductionPlan
/* 1818 */                                 .setHour24DemandQty(new BigDecimal(
/* 1819 */                                     0));
/* 1820 */                               jitProductionPlan = jitProductionPlanManager
/* 1821 */                                 .update(jitProductionPlan);
/*      */                               continue;
/*      */                             } 
/* 1824 */                             BigDecimal qtysk = mapStr
/* 1825 */                               .get(strk);
/* 1826 */                             mapStr.put(
/* 1827 */                                 strk, 
/* 1828 */                                 new BigDecimal(
/* 1829 */                                   0));
/* 1830 */                             jitProductionPlan
/* 1831 */                               .setQty(jitProductionPlan
/* 1832 */                                 .getQty()
/* 1833 */                                 .subtract(
/* 1834 */                                   qtysk));
/* 1835 */                             jitProductionPlan
/* 1836 */                               .setHour24DemandQty(jitProductionPlan
/* 1837 */                                 .getHour24DemandQty()
/* 1838 */                                 .subtract(
/* 1839 */                                   qtysk));
/* 1840 */                             jitProductionPlan = jitProductionPlanManager
/* 1841 */                               .update(jitProductionPlan);
/*      */ 
/*      */                             
/*      */                             break;
/*      */                           } 
/*      */                         } 
/*      */                       }
/*      */                     }
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/* 1854 */               conditions.clear();
/* 1855 */               conditions.put(
/* 1856 */                   JitProductionPlanQueryCondition.QTY_GT, Integer.valueOf(0));
/* 1857 */               List<JitProductionPlan> jitProductionPlanList2 = jitProductionPlanManager
/* 1858 */                 .getList(conditions, 0, -1, null, false);
/* 1859 */               Iterator<String> iterator2 = mapStr.keySet().iterator();
/* 1860 */               while (iterator2.hasNext()) {
/* 1861 */                 String strk = iterator2.next();
/* 1862 */                 if (((BigDecimal)mapStr.get(strk)).compareTo(
/* 1863 */                     new BigDecimal(0)) == 1) {
/* 1864 */                   String[] strarr = strk.split("\\+");
/* 1865 */                   String partId = strarr[0].toString();
/* 1866 */                   String TaskDate = strarr[1].toString();
/* 1867 */                   Integer hour = Integer.valueOf(Integer.parseInt(strarr[2]));
/*      */                   
/* 1869 */                   for (JitProductionPlan jitProductionPlan : jitProductionPlanList2) {
/* 1870 */                     if (((BigDecimal)mapStr.get(strk)).compareTo(
/* 1871 */                         new BigDecimal(0)) == 1) {
/* 1872 */                       if (jitProductionPlan
/* 1873 */                         .getChildPart().getId()
/* 1874 */                         .equals(partId)) {
/* 1875 */                         if (TaskDate
/* 1876 */                           .equals(sdf1
/* 1877 */                             .format(jitProductionPlan
/* 1878 */                               .getDate()))) {
/* 1879 */                           if (hour.intValue() == 2) {
/* 1880 */                             if (jitProductionPlan
/* 1881 */                               .getHour2DemandQty() != null) {
/* 1882 */                               if (((BigDecimal)mapStr
/* 1883 */                                 .get(strk))
/* 1884 */                                 .compareTo(
/* 1885 */                                   jitProductionPlan
/* 1886 */                                   .getHour2DemandQty()) != -1) {
/* 1887 */                                 BigDecimal bigDecimal = mapStr
/* 1888 */                                   .get(strk);
/* 1889 */                                 mapStr.put(
/* 1890 */                                     strk, 
/* 1891 */                                     bigDecimal.subtract(jitProductionPlan
/* 1892 */                                       .getHour2DemandQty()));
/*      */                                 
/* 1894 */                                 jitProductionPlan
/* 1895 */                                   .setQty(jitProductionPlan
/* 1896 */                                     .getQty()
/* 1897 */                                     .subtract(
/* 1898 */                                       jitProductionPlan
/* 1899 */                                       .getHour2DemandQty()));
/* 1900 */                                 jitProductionPlan
/* 1901 */                                   .setHour2DemandQty(new BigDecimal(
/* 1902 */                                       0));
/* 1903 */                                 jitProductionPlan = jitProductionPlanManager
/* 1904 */                                   .update(jitProductionPlan); continue;
/*      */                               } 
/* 1906 */                               BigDecimal qtysk = mapStr
/* 1907 */                                 .get(strk);
/* 1908 */                               mapStr.put(
/* 1909 */                                   strk, 
/* 1910 */                                   new BigDecimal(
/* 1911 */                                     0));
/* 1912 */                               jitProductionPlan
/* 1913 */                                 .setQty(jitProductionPlan
/* 1914 */                                   .getQty()
/* 1915 */                                   .subtract(
/* 1916 */                                     qtysk));
/* 1917 */                               jitProductionPlan
/* 1918 */                                 .setHour2DemandQty(jitProductionPlan
/* 1919 */                                   .getHour2DemandQty()
/* 1920 */                                   .subtract(
/* 1921 */                                     qtysk));
/* 1922 */                               jitProductionPlan = jitProductionPlanManager
/* 1923 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1927 */                           if (hour.intValue() == 4) {
/* 1928 */                             if (jitProductionPlan
/* 1929 */                               .getHour4DemandQty() != null) {
/* 1930 */                               if (((BigDecimal)mapStr
/* 1931 */                                 .get(strk))
/* 1932 */                                 .compareTo(
/* 1933 */                                   jitProductionPlan
/* 1934 */                                   .getHour4DemandQty()) != -1) {
/* 1935 */                                 BigDecimal bigDecimal = mapStr
/* 1936 */                                   .get(strk);
/* 1937 */                                 mapStr.put(
/* 1938 */                                     strk, 
/* 1939 */                                     bigDecimal.subtract(jitProductionPlan
/* 1940 */                                       .getHour4DemandQty()));
/*      */                                 
/* 1942 */                                 jitProductionPlan
/* 1943 */                                   .setQty(jitProductionPlan
/* 1944 */                                     .getQty()
/* 1945 */                                     .subtract(
/* 1946 */                                       jitProductionPlan
/* 1947 */                                       .getHour4DemandQty()));
/* 1948 */                                 jitProductionPlan
/* 1949 */                                   .setHour4DemandQty(new BigDecimal(
/* 1950 */                                       0));
/* 1951 */                                 jitProductionPlan = jitProductionPlanManager
/* 1952 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 1955 */                               BigDecimal qtysk = mapStr
/* 1956 */                                 .get(strk);
/* 1957 */                               mapStr.put(
/* 1958 */                                   strk, 
/* 1959 */                                   new BigDecimal(
/* 1960 */                                     0));
/* 1961 */                               jitProductionPlan
/* 1962 */                                 .setQty(jitProductionPlan
/* 1963 */                                   .getQty()
/* 1964 */                                   .subtract(
/* 1965 */                                     qtysk));
/* 1966 */                               jitProductionPlan
/* 1967 */                                 .setHour4DemandQty(jitProductionPlan
/* 1968 */                                   .getHour4DemandQty()
/* 1969 */                                   .subtract(
/* 1970 */                                     qtysk));
/* 1971 */                               jitProductionPlan = jitProductionPlanManager
/* 1972 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 1976 */                           if (hour.intValue() == 6) {
/* 1977 */                             if (jitProductionPlan
/* 1978 */                               .getHour6DemandQty() != null) {
/* 1979 */                               if (((BigDecimal)mapStr
/* 1980 */                                 .get(strk))
/* 1981 */                                 .compareTo(
/* 1982 */                                   jitProductionPlan
/* 1983 */                                   .getHour6DemandQty()) != -1) {
/* 1984 */                                 BigDecimal bigDecimal = mapStr
/* 1985 */                                   .get(strk);
/* 1986 */                                 mapStr.put(
/* 1987 */                                     strk, 
/* 1988 */                                     bigDecimal.subtract(jitProductionPlan
/* 1989 */                                       .getHour6DemandQty()));
/*      */                                 
/* 1991 */                                 jitProductionPlan
/* 1992 */                                   .setQty(jitProductionPlan
/* 1993 */                                     .getQty()
/* 1994 */                                     .subtract(
/* 1995 */                                       jitProductionPlan
/* 1996 */                                       .getHour6DemandQty()));
/* 1997 */                                 jitProductionPlan
/* 1998 */                                   .setHour6DemandQty(new BigDecimal(
/* 1999 */                                       0));
/* 2000 */                                 jitProductionPlan = jitProductionPlanManager
/* 2001 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2004 */                               BigDecimal qtysk = mapStr
/* 2005 */                                 .get(strk);
/* 2006 */                               mapStr.put(
/* 2007 */                                   strk, 
/* 2008 */                                   new BigDecimal(
/* 2009 */                                     0));
/* 2010 */                               jitProductionPlan
/* 2011 */                                 .setQty(jitProductionPlan
/* 2012 */                                   .getQty()
/* 2013 */                                   .subtract(
/* 2014 */                                     qtysk));
/* 2015 */                               jitProductionPlan
/* 2016 */                                 .setHour6DemandQty(jitProductionPlan
/* 2017 */                                   .getHour6DemandQty()
/* 2018 */                                   .subtract(
/* 2019 */                                     qtysk));
/* 2020 */                               jitProductionPlan = jitProductionPlanManager
/* 2021 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2025 */                           if (hour.intValue() == 8) {
/* 2026 */                             if (jitProductionPlan
/* 2027 */                               .getHour8DemandQty() != null) {
/* 2028 */                               if (((BigDecimal)mapStr
/* 2029 */                                 .get(strk))
/* 2030 */                                 .compareTo(
/* 2031 */                                   jitProductionPlan
/* 2032 */                                   .getHour8DemandQty()) != -1) {
/* 2033 */                                 BigDecimal bigDecimal = mapStr
/* 2034 */                                   .get(strk);
/* 2035 */                                 mapStr.put(
/* 2036 */                                     strk, 
/* 2037 */                                     bigDecimal.subtract(jitProductionPlan
/* 2038 */                                       .getHour8DemandQty()));
/*      */                                 
/* 2040 */                                 jitProductionPlan
/* 2041 */                                   .setQty(jitProductionPlan
/* 2042 */                                     .getQty()
/* 2043 */                                     .subtract(
/* 2044 */                                       jitProductionPlan
/* 2045 */                                       .getHour8DemandQty()));
/* 2046 */                                 jitProductionPlan
/* 2047 */                                   .setHour8DemandQty(new BigDecimal(
/* 2048 */                                       0));
/* 2049 */                                 jitProductionPlan = jitProductionPlanManager
/* 2050 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2053 */                               BigDecimal qtysk = mapStr
/* 2054 */                                 .get(strk);
/* 2055 */                               mapStr.put(
/* 2056 */                                   strk, 
/* 2057 */                                   new BigDecimal(
/* 2058 */                                     0));
/* 2059 */                               jitProductionPlan
/* 2060 */                                 .setQty(jitProductionPlan
/* 2061 */                                   .getQty()
/* 2062 */                                   .subtract(
/* 2063 */                                     qtysk));
/* 2064 */                               jitProductionPlan
/* 2065 */                                 .setHour8DemandQty(jitProductionPlan
/* 2066 */                                   .getHour8DemandQty()
/* 2067 */                                   .subtract(
/* 2068 */                                     qtysk));
/* 2069 */                               jitProductionPlan = jitProductionPlanManager
/* 2070 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2074 */                           if (hour.intValue() == 10) {
/* 2075 */                             if (jitProductionPlan
/* 2076 */                               .getHour10DemandQty() != null) {
/* 2077 */                               if (((BigDecimal)mapStr
/* 2078 */                                 .get(strk))
/* 2079 */                                 .compareTo(
/* 2080 */                                   jitProductionPlan
/* 2081 */                                   .getHour10DemandQty()) != -1) {
/* 2082 */                                 BigDecimal bigDecimal = mapStr
/* 2083 */                                   .get(strk);
/* 2084 */                                 mapStr.put(
/* 2085 */                                     strk, 
/* 2086 */                                     bigDecimal.subtract(jitProductionPlan
/* 2087 */                                       .getHour10DemandQty()));
/*      */                                 
/* 2089 */                                 jitProductionPlan
/* 2090 */                                   .setQty(jitProductionPlan
/* 2091 */                                     .getQty()
/* 2092 */                                     .subtract(
/* 2093 */                                       jitProductionPlan
/* 2094 */                                       .getHour10DemandQty()));
/* 2095 */                                 jitProductionPlan
/* 2096 */                                   .setHour10DemandQty(new BigDecimal(
/* 2097 */                                       0));
/* 2098 */                                 jitProductionPlan = jitProductionPlanManager
/* 2099 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2102 */                               BigDecimal qtysk = mapStr
/* 2103 */                                 .get(strk);
/* 2104 */                               mapStr.put(
/* 2105 */                                   strk, 
/* 2106 */                                   new BigDecimal(
/* 2107 */                                     0));
/* 2108 */                               jitProductionPlan
/* 2109 */                                 .setQty(jitProductionPlan
/* 2110 */                                   .getQty()
/* 2111 */                                   .subtract(
/* 2112 */                                     qtysk));
/* 2113 */                               jitProductionPlan
/* 2114 */                                 .setHour10DemandQty(jitProductionPlan
/* 2115 */                                   .getHour10DemandQty()
/* 2116 */                                   .subtract(
/* 2117 */                                     qtysk));
/* 2118 */                               jitProductionPlan = jitProductionPlanManager
/* 2119 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2123 */                           if (hour.intValue() == 12) {
/* 2124 */                             if (jitProductionPlan
/* 2125 */                               .getHour12DemandQty() != null) {
/* 2126 */                               if (((BigDecimal)mapStr
/* 2127 */                                 .get(strk))
/* 2128 */                                 .compareTo(
/* 2129 */                                   jitProductionPlan
/* 2130 */                                   .getHour12DemandQty()) != -1) {
/* 2131 */                                 BigDecimal bigDecimal = mapStr
/* 2132 */                                   .get(strk);
/* 2133 */                                 mapStr.put(
/* 2134 */                                     strk, 
/* 2135 */                                     bigDecimal.subtract(jitProductionPlan
/* 2136 */                                       .getHour12DemandQty()));
/*      */                                 
/* 2138 */                                 jitProductionPlan
/* 2139 */                                   .setQty(jitProductionPlan
/* 2140 */                                     .getQty()
/* 2141 */                                     .subtract(
/* 2142 */                                       jitProductionPlan
/* 2143 */                                       .getHour12DemandQty()));
/* 2144 */                                 jitProductionPlan
/* 2145 */                                   .setHour12DemandQty(new BigDecimal(
/* 2146 */                                       0));
/* 2147 */                                 jitProductionPlan = jitProductionPlanManager
/* 2148 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2151 */                               BigDecimal qtysk = mapStr
/* 2152 */                                 .get(strk);
/* 2153 */                               mapStr.put(
/* 2154 */                                   strk, 
/* 2155 */                                   new BigDecimal(
/* 2156 */                                     0));
/* 2157 */                               jitProductionPlan
/* 2158 */                                 .setQty(jitProductionPlan
/* 2159 */                                   .getQty()
/* 2160 */                                   .subtract(
/* 2161 */                                     qtysk));
/* 2162 */                               jitProductionPlan
/* 2163 */                                 .setHour12DemandQty(jitProductionPlan
/* 2164 */                                   .getHour12DemandQty()
/* 2165 */                                   .subtract(
/* 2166 */                                     qtysk));
/* 2167 */                               jitProductionPlan = jitProductionPlanManager
/* 2168 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2172 */                           if (hour.intValue() == 14) {
/* 2173 */                             if (jitProductionPlan
/* 2174 */                               .getHour14DemandQty() != null) {
/* 2175 */                               if (((BigDecimal)mapStr
/* 2176 */                                 .get(strk))
/* 2177 */                                 .compareTo(
/* 2178 */                                   jitProductionPlan
/* 2179 */                                   .getHour14DemandQty()) != -1) {
/* 2180 */                                 BigDecimal bigDecimal = mapStr
/* 2181 */                                   .get(strk);
/* 2182 */                                 mapStr.put(
/* 2183 */                                     strk, 
/* 2184 */                                     bigDecimal.subtract(jitProductionPlan
/* 2185 */                                       .getHour14DemandQty()));
/*      */                                 
/* 2187 */                                 jitProductionPlan
/* 2188 */                                   .setQty(jitProductionPlan
/* 2189 */                                     .getQty()
/* 2190 */                                     .subtract(
/* 2191 */                                       jitProductionPlan
/* 2192 */                                       .getHour14DemandQty()));
/* 2193 */                                 jitProductionPlan
/* 2194 */                                   .setHour14DemandQty(new BigDecimal(
/* 2195 */                                       0));
/* 2196 */                                 jitProductionPlan = jitProductionPlanManager
/* 2197 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2200 */                               BigDecimal qtysk = mapStr
/* 2201 */                                 .get(strk);
/* 2202 */                               mapStr.put(
/* 2203 */                                   strk, 
/* 2204 */                                   new BigDecimal(
/* 2205 */                                     0));
/* 2206 */                               jitProductionPlan
/* 2207 */                                 .setQty(jitProductionPlan
/* 2208 */                                   .getQty()
/* 2209 */                                   .subtract(
/* 2210 */                                     qtysk));
/* 2211 */                               jitProductionPlan
/* 2212 */                                 .setHour14DemandQty(jitProductionPlan
/* 2213 */                                   .getHour14DemandQty()
/* 2214 */                                   .subtract(
/* 2215 */                                     qtysk));
/* 2216 */                               jitProductionPlan = jitProductionPlanManager
/* 2217 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2221 */                           if (hour.intValue() == 16) {
/* 2222 */                             if (jitProductionPlan
/* 2223 */                               .getHour16DemandQty() != null) {
/* 2224 */                               if (((BigDecimal)mapStr
/* 2225 */                                 .get(strk))
/* 2226 */                                 .compareTo(
/* 2227 */                                   jitProductionPlan
/* 2228 */                                   .getHour16DemandQty()) != -1) {
/* 2229 */                                 BigDecimal bigDecimal = mapStr
/* 2230 */                                   .get(strk);
/* 2231 */                                 mapStr.put(
/* 2232 */                                     strk, 
/* 2233 */                                     bigDecimal.subtract(jitProductionPlan
/* 2234 */                                       .getHour16DemandQty()));
/*      */                                 
/* 2236 */                                 jitProductionPlan
/* 2237 */                                   .setQty(jitProductionPlan
/* 2238 */                                     .getQty()
/* 2239 */                                     .subtract(
/* 2240 */                                       jitProductionPlan
/* 2241 */                                       .getHour16DemandQty()));
/* 2242 */                                 jitProductionPlan
/* 2243 */                                   .setHour16DemandQty(new BigDecimal(
/* 2244 */                                       0));
/* 2245 */                                 jitProductionPlan = jitProductionPlanManager
/* 2246 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2249 */                               BigDecimal qtysk = mapStr
/* 2250 */                                 .get(strk);
/* 2251 */                               mapStr.put(
/* 2252 */                                   strk, 
/* 2253 */                                   new BigDecimal(
/* 2254 */                                     0));
/* 2255 */                               jitProductionPlan
/* 2256 */                                 .setQty(jitProductionPlan
/* 2257 */                                   .getQty()
/* 2258 */                                   .subtract(
/* 2259 */                                     qtysk));
/* 2260 */                               jitProductionPlan
/* 2261 */                                 .setHour16DemandQty(jitProductionPlan
/* 2262 */                                   .getHour16DemandQty()
/* 2263 */                                   .subtract(
/* 2264 */                                     qtysk));
/* 2265 */                               jitProductionPlan = jitProductionPlanManager
/* 2266 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2270 */                           if (hour.intValue() == 18) {
/* 2271 */                             if (jitProductionPlan
/* 2272 */                               .getHour18DemandQty() != null) {
/* 2273 */                               if (((BigDecimal)mapStr
/* 2274 */                                 .get(strk))
/* 2275 */                                 .compareTo(
/* 2276 */                                   jitProductionPlan
/* 2277 */                                   .getHour18DemandQty()) != -1) {
/* 2278 */                                 BigDecimal bigDecimal = mapStr
/* 2279 */                                   .get(strk);
/* 2280 */                                 mapStr.put(
/* 2281 */                                     strk, 
/* 2282 */                                     bigDecimal.subtract(jitProductionPlan
/* 2283 */                                       .getHour18DemandQty()));
/*      */                                 
/* 2285 */                                 jitProductionPlan
/* 2286 */                                   .setQty(jitProductionPlan
/* 2287 */                                     .getQty()
/* 2288 */                                     .subtract(
/* 2289 */                                       jitProductionPlan
/* 2290 */                                       .getHour18DemandQty()));
/* 2291 */                                 jitProductionPlan
/* 2292 */                                   .setHour18DemandQty(new BigDecimal(
/* 2293 */                                       0));
/* 2294 */                                 jitProductionPlan = jitProductionPlanManager
/* 2295 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2298 */                               BigDecimal qtysk = mapStr
/* 2299 */                                 .get(strk);
/* 2300 */                               mapStr.put(
/* 2301 */                                   strk, 
/* 2302 */                                   new BigDecimal(
/* 2303 */                                     0));
/* 2304 */                               jitProductionPlan
/* 2305 */                                 .setQty(jitProductionPlan
/* 2306 */                                   .getQty()
/* 2307 */                                   .subtract(
/* 2308 */                                     qtysk));
/* 2309 */                               jitProductionPlan
/* 2310 */                                 .setHour18DemandQty(jitProductionPlan
/* 2311 */                                   .getHour18DemandQty()
/* 2312 */                                   .subtract(
/* 2313 */                                     qtysk));
/* 2314 */                               jitProductionPlan = jitProductionPlanManager
/* 2315 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2319 */                           if (hour.intValue() == 20) {
/* 2320 */                             if (jitProductionPlan
/* 2321 */                               .getHour20DemandQty() != null) {
/* 2322 */                               if (((BigDecimal)mapStr
/* 2323 */                                 .get(strk))
/* 2324 */                                 .compareTo(
/* 2325 */                                   jitProductionPlan
/* 2326 */                                   .getHour20DemandQty()) != -1) {
/* 2327 */                                 BigDecimal bigDecimal = mapStr
/* 2328 */                                   .get(strk);
/* 2329 */                                 mapStr.put(
/* 2330 */                                     strk, 
/* 2331 */                                     bigDecimal.subtract(jitProductionPlan
/* 2332 */                                       .getHour20DemandQty()));
/*      */                                 
/* 2334 */                                 jitProductionPlan
/* 2335 */                                   .setQty(jitProductionPlan
/* 2336 */                                     .getQty()
/* 2337 */                                     .subtract(
/* 2338 */                                       jitProductionPlan
/* 2339 */                                       .getHour20DemandQty()));
/* 2340 */                                 jitProductionPlan
/* 2341 */                                   .setHour20DemandQty(new BigDecimal(
/* 2342 */                                       0));
/* 2343 */                                 jitProductionPlan = jitProductionPlanManager
/* 2344 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2347 */                               BigDecimal qtysk = mapStr
/* 2348 */                                 .get(strk);
/* 2349 */                               mapStr.put(
/* 2350 */                                   strk, 
/* 2351 */                                   new BigDecimal(
/* 2352 */                                     0));
/* 2353 */                               jitProductionPlan
/* 2354 */                                 .setQty(jitProductionPlan
/* 2355 */                                   .getQty()
/* 2356 */                                   .subtract(
/* 2357 */                                     qtysk));
/* 2358 */                               jitProductionPlan
/* 2359 */                                 .setHour20DemandQty(jitProductionPlan
/* 2360 */                                   .getHour20DemandQty()
/* 2361 */                                   .subtract(
/* 2362 */                                     qtysk));
/* 2363 */                               jitProductionPlan = jitProductionPlanManager
/* 2364 */                                 .update(jitProductionPlan); break;
/*      */                             } 
/*      */                             continue;
/*      */                           } 
/* 2368 */                           if (hour.intValue() == 22) {
/* 2369 */                             if (jitProductionPlan
/* 2370 */                               .getHour22DemandQty() != null) {
/* 2371 */                               if (((BigDecimal)mapStr
/* 2372 */                                 .get(strk))
/* 2373 */                                 .compareTo(
/* 2374 */                                   jitProductionPlan
/* 2375 */                                   .getHour22DemandQty()) != -1) {
/* 2376 */                                 BigDecimal bigDecimal = mapStr
/* 2377 */                                   .get(strk);
/* 2378 */                                 mapStr.put(
/* 2379 */                                     strk, 
/* 2380 */                                     bigDecimal.subtract(jitProductionPlan
/* 2381 */                                       .getHour22DemandQty()));
/*      */                                 
/* 2383 */                                 jitProductionPlan
/* 2384 */                                   .setQty(jitProductionPlan
/* 2385 */                                     .getQty()
/* 2386 */                                     .subtract(
/* 2387 */                                       jitProductionPlan
/* 2388 */                                       .getHour22DemandQty()));
/* 2389 */                                 jitProductionPlan
/* 2390 */                                   .setHour22DemandQty(new BigDecimal(
/* 2391 */                                       0));
/* 2392 */                                 jitProductionPlan = jitProductionPlanManager
/* 2393 */                                   .update(jitProductionPlan);
/*      */                                 continue;
/*      */                               } 
/* 2396 */                               BigDecimal qtysk = mapStr
/* 2397 */                                 .get(strk);
/* 2398 */                               mapStr.put(
/* 2399 */                                   strk, 
/* 2400 */                                   new BigDecimal(
/* 2401 */                                     0));
/* 2402 */                               jitProductionPlan
/* 2403 */                                 .setQty(jitProductionPlan
/* 2404 */                                   .getQty()
/* 2405 */                                   .subtract(
/* 2406 */                                     qtysk));
/* 2407 */                               jitProductionPlan
/* 2408 */                                 .setHour22DemandQty(jitProductionPlan
/* 2409 */                                   .getHour22DemandQty()
/* 2410 */                                   .subtract(
/* 2411 */                                     qtysk));
/* 2412 */                               jitProductionPlan = jitProductionPlanManager
/* 2413 */                                 .update(jitProductionPlan); break;
/*      */                             }  continue;
/*      */                           } 
/* 2416 */                           if (hour.intValue() == 24 && 
/* 2417 */                             jitProductionPlan
/* 2418 */                             .getHour24DemandQty() != null) {
/* 2419 */                             if (((BigDecimal)mapStr
/* 2420 */                               .get(strk))
/* 2421 */                               .compareTo(
/* 2422 */                                 jitProductionPlan
/* 2423 */                                 .getHour24DemandQty()) != -1) {
/* 2424 */                               BigDecimal bigDecimal = mapStr
/* 2425 */                                 .get(strk);
/* 2426 */                               mapStr.put(
/* 2427 */                                   strk, 
/* 2428 */                                   bigDecimal.subtract(jitProductionPlan
/* 2429 */                                     .getHour24DemandQty()));
/*      */                               
/* 2431 */                               jitProductionPlan
/* 2432 */                                 .setQty(jitProductionPlan
/* 2433 */                                   .getQty()
/* 2434 */                                   .subtract(
/* 2435 */                                     jitProductionPlan
/* 2436 */                                     .getHour24DemandQty()));
/* 2437 */                               jitProductionPlan
/* 2438 */                                 .setHour24DemandQty(new BigDecimal(
/* 2439 */                                     0));
/* 2440 */                               jitProductionPlan = jitProductionPlanManager
/* 2441 */                                 .update(jitProductionPlan);
/*      */                               continue;
/*      */                             } 
/* 2444 */                             BigDecimal qtysk = mapStr
/* 2445 */                               .get(strk);
/* 2446 */                             mapStr.put(
/* 2447 */                                 strk, 
/* 2448 */                                 new BigDecimal(
/* 2449 */                                   0));
/* 2450 */                             jitProductionPlan
/* 2451 */                               .setQty(jitProductionPlan
/* 2452 */                                 .getQty()
/* 2453 */                                 .subtract(
/* 2454 */                                   qtysk));
/* 2455 */                             jitProductionPlan
/* 2456 */                               .setHour24DemandQty(jitProductionPlan
/* 2457 */                                 .getHour24DemandQty()
/* 2458 */                                 .subtract(
/* 2459 */                                   qtysk));
/* 2460 */                             jitProductionPlan = jitProductionPlanManager
/* 2461 */                               .update(jitProductionPlan);
/*      */ 
/*      */ 
/*      */                             
/*      */                             break;
/*      */                           } 
/*      */                         } 
/*      */                       }
/*      */                     }
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 2477 */           ediProduction.setEnabled(Integer.valueOf(1));
/* 2478 */           manager.update(ediProduction);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 2484 */       ediProduction.setEnabled(Integer.valueOf(1));
/* 2485 */       manager.update(ediProduction);
/*      */     } 
/* 2487 */     return new ActionForward("listEdiProductionRawDataAction.do", true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward newEdiProduction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2493 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward insertEdiProdution(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2499 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
/* 2500 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
/* 2501 */     EdiProductionManager manager = 
/* 2502 */       ServiceLocator.getEdiProductionManager(request);
/* 2503 */     BeanForm beanForm = (BeanForm)form;
/* 2504 */     EdiProduction production = new EdiProduction();
/* 2505 */     beanForm.populateToBean(production);
/* 2506 */     production.setType(Integer.valueOf(1));
/* 2507 */     production.setHandStatus(Integer.valueOf(1));
/* 2508 */     production.setSyncTime(new Date());
/* 2509 */     production.setStatus(Integer.valueOf(0));
/* 2510 */     production.setTaskTime(sdf.parse(String.valueOf(sdf1.format(production.getTaskDate())) + 
/* 2511 */           production.getTime().toString()));
/* 2512 */     request.setAttribute("X_OBJECT", manager.save(production));
/* 2513 */     request.setAttribute("X_ROWPAGE", 
/* 2514 */         "schedule/ediproduction/rowRawData.jsp");
/* 2515 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward editEdiProdution(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2521 */     EdiProductionManager manager = 
/* 2522 */       ServiceLocator.getEdiProductionManager(request);
/* 2523 */     String id = request.getParameter("id");
/* 2524 */     EdiProduction production = manager.getEdiProduction(
/* 2525 */         Integer.valueOf(Integer.parseInt(id)));
/* 2526 */     BeanForm beanForm = (BeanForm)getForm("/updateEdiProduction", request);
/* 2527 */     beanForm.populate(production, "to_form");
/* 2528 */     request.setAttribute("X_OBJECT", production);
/* 2529 */     return mapping.findForward("page");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward updateEdiProduction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2535 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
/* 2536 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
/* 2537 */     EdiProductionManager manager = 
/* 2538 */       ServiceLocator.getEdiProductionManager(request);
/* 2539 */     BeanForm beanForm = (BeanForm)form;
/* 2540 */     EdiProduction production = new EdiProduction();
/* 2541 */     beanForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 2542 */     beanForm.populateToBean(production, request);
/* 2543 */     production.setHandStatus(Integer.valueOf(1));
/* 2544 */     production.setType(Integer.valueOf(1));
/* 2545 */     production.setStatus(Integer.valueOf(0));
/* 2546 */     production.setSyncTime(new Date());
/* 2547 */     production.setTaskTime(sdf.parse(String.valueOf(sdf1.format(production.getTaskDate())) + 
/* 2548 */           production.getTime().toString()));
/* 2549 */     manager.update(production);
/* 2550 */     request.setAttribute("X_ROWPAGE", 
/* 2551 */         "schedule/ediproduction/rowRawData.jsp");
/* 2552 */     return mapping.findForward("success");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionForward listRawDataReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 2570 */     EdiProductionManager manager = 
/* 2571 */       ServiceLocator.getEdiProductionManager(request);
/* 2572 */     EdiProductionQueryForm queryForm = (EdiProductionQueryForm)form;
/* 2573 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 2574 */     Map conditions = getConditions(queryForm);
/* 2575 */     if (queryForm.getOrder() == "") {
/* 2576 */       queryForm.setOrder("taskDate");
/* 2577 */       queryForm.setDescend(true);
/*      */     } 
/* 2579 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 2580 */     String date = format.format(new Date());
/* 2581 */     conditions.put(JitProductionPlanQueryCondition.DATE_GE, date);
/* 2582 */     String exportType = queryForm.getExportType();
/* 2583 */     List dataSize = manager.getListReport(conditions, 0, -1, null, false);
/* 2584 */     if (exportType != null && exportType.length() > 0) {
/* 2585 */       List datas = manager.getListReport(conditions, 0, -1, 
/* 2586 */           EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/* 2587 */           queryForm.isDescend());
/* 2588 */       int index = SessionTempFile.createNewTempFile(request);
/* 2589 */       String fileName = "ChengPinBeiJianJiHuaChaKan";
/* 2590 */       String suffix = ExportUtil.export(
/* 2591 */           exportType, 
/* 2592 */           datas, 
/* 2593 */           request, 
/* 2594 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/* 2595 */               request)), new Exportable()
/*      */           {
/*      */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 2598 */               row.add(BeanUtils.getProperty(data, "asnNo"));
/* 2599 */               row.add(BeanUtils.getProperty(data, 
/* 2600 */                     "productlinecode"));
/* 2601 */               row.add(BeanUtils.getProperty(data, "shiftcode"));
/* 2602 */               row.add(BeanUtils.getProperty(data, "staffcode"));
/* 2603 */               row.add(BeanUtils.getProperty(data, "number"));
/* 2604 */               row.add(BeanUtils.getProperty(data, "taskDate"));
/* 2605 */               row.add(BeanUtils.getProperty(data, "time"));
/* 2606 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 2607 */               row.add(BeanUtils.getProperty(data, "syncTime"));
/* 2608 */               EdiProduction prodution = (EdiProduction)data;
/* 2609 */               if (prodution.getStatus().intValue() == 0) {
/* 2610 */                 row.add("未分解");
/* 2611 */               } else if (prodution.getStatus().intValue() == 1) {
/* 2612 */                 row.add("已分解");
/* 2613 */               } else if (prodution.getStatus().intValue() == 2) {
/* 2614 */                 row.add("失败");
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 2620 */               MessageResources message = EdiProductionAction.this.getResources(request);
/* 2621 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2622 */                     "ediproduction.asnno"));
/* 2623 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2624 */                     "ediproduction.productlinecode"));
/* 2625 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2626 */                     "ediproduction.shiftcode"));
/* 2627 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2628 */                     "ediproduction.staffcode"));
/* 2629 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2630 */                     "ediproduction.number"));
/* 2631 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2632 */                     "ediproduction.taskdate"));
/* 2633 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2634 */                     "ediproduction.time"));
/* 2635 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2636 */                     "ediproduction.qty"));
/* 2637 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2638 */                     "ediproduction.synctime"));
/* 2639 */               row.add(message.getMessage(EdiProductionAction.this.getLocale(request), 
/* 2640 */                     "ediproduction.status"));
/*      */             }
/*      */           });
/* 2643 */       return new ActionForward("download/" + index + "/" + 
/* 2644 */           URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*      */     } 
/* 2646 */     if (queryForm.isFirstInit()) {
/* 2647 */       queryForm.init(dataSize.size());
/*      */     } else {
/* 2649 */       queryForm.init();
/*      */     } 
/* 2651 */     int pageNum = queryForm.getPageNoAsInt();
/* 2652 */     int pageSize = queryForm.getPageSizeAsInt();
/*      */     
/* 2654 */     List entityList = manager.getListReport(conditions, pageNum, pageSize, 
/* 2655 */         EdiProductionQueryOrder.getEnum(queryForm.getOrder()), 
/* 2656 */         queryForm.isDescend());
/* 2657 */     request.setAttribute("x_selType", Integer.valueOf(183));
/* 2658 */     request.setAttribute("X_RESULTLIST", entityList);
/* 2659 */     return mapping.findForward("page");
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/EdiProductionAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */