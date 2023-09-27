/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.Productionplanning;
/*     */ import com.aof.model.po.query.ProductionplanningQueryCondition;
/*     */ import com.aof.model.po.query.ProductionplanningQueryOrder;
/*     */ import com.aof.service.po.ProductionplanningManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductionplanningQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.servlet.DownloadUploadHelper;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.upload.FormFile;
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
/*     */ public class ProductionplanningAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  54 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*  55 */     ProductionplanningQueryForm queryForm = (ProductionplanningQueryForm)form;
/*  56 */     Map conditions = new HashMap();
/*     */     
/*  58 */     getConditionAndOrder(queryForm, conditions, request);
/*  59 */     String exportType = queryForm.getExportType();
/*  60 */     if (exportType != null && exportType.length() > 0) {
/*  61 */       List data = manager.list(conditions, 0, -1, ProductionplanningQueryOrder.productionPlanningNumber, true);
/*  62 */       int index = SessionTempFile.createNewTempFile(request);
/*  63 */       String fileName = "productionPlanningUpload";
/*  64 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  65 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  67 */               MessageResources messages = ProductionplanningAction.this.getResources(request);
/*  68 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.number"));
/*  69 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.uploadDate"));
/*  70 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.uploadUser"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  74 */               row.add(BeanUtils.getProperty(data, "productionPlanningNumber"));
/*  75 */               row.add(BeanUtils.getProperty(data, "uploadDate"));
/*  76 */               row.add(BeanUtils.getProperty(data, "uploadUser.loginName"));
/*     */             }
/*     */           });
/*  79 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  81 */     if (queryForm.isFirstInit()) {
/*  82 */       queryForm.init(manager.listProductionplanningCount(conditions));
/*     */     } else {
/*  84 */       queryForm.init();
/*     */     } 
/*  86 */     List<Productionplanning> list = manager.list(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  87 */         ProductionplanningQueryOrder.productionPlanningNumber, true);
/*  88 */     List<Productionplanning> listProduction = new ArrayList<Productionplanning>();
/*  89 */     int num = 1;
/*  90 */     for (int i = 0; i < list.size(); i++) {
/*  91 */       Productionplanning pro = list.get(i);
/*  92 */       pro.setNum(num++);
/*  93 */       listProduction.add(pro);
/*     */     } 
/*  95 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*  96 */     request.setAttribute("X_USER", getCurrentUser(request));
/*  97 */     request.setAttribute("X_RESULTLIST", listProduction);
/*  98 */     request.setAttribute("x_selType", Integer.valueOf(156));
/*  99 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 104 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/* 105 */     ProductionplanningQueryForm queryForm = (ProductionplanningQueryForm)form;
/* 106 */     Map conditions = new HashMap();
/*     */     
/* 108 */     getConditionAndOrder(queryForm, conditions, request);
/* 109 */     String exportType = queryForm.getExportType();
/* 110 */     if (exportType != null && exportType.length() > 0) {
/* 111 */       List data = manager.list(conditions, 0, -1, null, queryForm.isDescend());
/* 112 */       int index = SessionTempFile.createNewTempFile(request);
/* 113 */       String fileName = "productionPlanningDownload";
/* 114 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 115 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable() {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 117 */               MessageResources messages = ProductionplanningAction.this.getResources(request);
/* 118 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.number"));
/* 119 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.uploadDate"));
/* 120 */               row.add(messages.getMessage(ProductionplanningAction.this.getLocale(request), "productionPlanning.uploadUser"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 124 */               row.add(BeanUtils.getProperty(data, "productionPlanningNumber"));
/* 125 */               row.add(BeanUtils.getProperty(data, "uploadDate"));
/* 126 */               row.add(BeanUtils.getProperty(data, "uploadUser.loginName"));
/*     */             }
/*     */           });
/* 129 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 131 */     if (queryForm.isFirstInit()) {
/* 132 */       queryForm.init(manager.listProductionplanningCount(conditions));
/*     */     } else {
/* 134 */       queryForm.init();
/*     */     } 
/* 136 */     List<Productionplanning> list = manager.list(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 137 */         ProductionplanningQueryOrder.productionPlanningNumber, true);
/* 138 */     List<Productionplanning> listProduction = new ArrayList<Productionplanning>();
/* 139 */     int num = 1;
/* 140 */     for (int i = 0; i < list.size(); i++) {
/* 141 */       Productionplanning pro = list.get(i);
/* 142 */       pro.setNum(num++);
/* 143 */       listProduction.add(pro);
/*     */     } 
/* 145 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 146 */     request.setAttribute("X_USER", getCurrentUser(request));
/* 147 */     request.setAttribute("X_RESULTLIST", listProduction);
/* 148 */     request.setAttribute("x_selType", Integer.valueOf(157));
/* 149 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(ProductionplanningQueryForm queryForm) {
/* 156 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 158 */     return conditions;
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
/*     */   public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 170 */     if (!isBack(request)) {
/* 171 */       Productionplanning model = new Productionplanning();
/* 172 */       BeanForm roleForm = (BeanForm)getForm("/insertProductionplanning", request);
/* 173 */       roleForm.populateToForm(model);
/*     */     } 
/* 175 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 176 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 188 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*     */     try {
/* 190 */       BeanForm beanForm = (BeanForm)form;
/* 191 */       Productionplanning model = new Productionplanning();
/* 192 */       beanForm.populateToBean(model);
/* 193 */       manager.insert(model);
/* 194 */       request.setAttribute("X_OBJECT", model);
/* 195 */       request.setAttribute("X_ROWPAGE", "Productionplanning/row.jsp");
/* 196 */     } catch (Exception e) {
/* 197 */       postGlobalMessage("creat.fail", request.getSession());
/* 198 */       e.printStackTrace();
/*     */     } 
/* 200 */     postGlobalMessage("creat.success", request.getSession());
/* 201 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward uploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 206 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*     */     try {
/* 208 */       BeanForm beanForm = (BeanForm)form;
/* 209 */       Productionplanning model = new Productionplanning();
/* 210 */       FormFile formFile = (FormFile)beanForm.get("fileContent");
/* 211 */       if (formFile == null) {
/* 212 */         request.setAttribute("X_ROWPAGE", "Productionplanning/row.jsp");
/* 213 */         request.setAttribute("shibai1", "导入失败!");
/* 214 */         return new ActionForward("listProductionplanning.do", true);
/*     */       } 
/* 216 */       model.setFileContent(formFile.getFileName());
/*     */       
/* 218 */       model.setProductionPlanningNumber(getplanningNumber(request));
/* 219 */       model.setUploadDate(new Date());
/* 220 */       model.setUploadFileName(formFile.getFileName());
/* 221 */       User user = getCurrentUser(request);
/* 222 */       model.setUploadUser(user);
/* 223 */       if (formFile.getFileSize() > 0) {
/* 224 */         model.setFileContSize(formFile.getFileSize());
/* 225 */         model.setPath(ActionUtils2.savaFile(formFile.getInputStream()));
/*     */       } else {
/* 227 */         throw new ActionException("inboundOrderAttach.error.f ileSize.zero");
/*     */       } 
/* 229 */       manager.insert(model);
/*     */     }
/* 231 */     catch (Exception e) {
/* 232 */       postGlobalMessage("creat.fail", request.getSession());
/* 233 */       e.printStackTrace();
/*     */     } 
/* 235 */     postGlobalMessage("creat.success", request.getSession());
/* 236 */     return new ActionForward("listProductionplanning.do", true);
/*     */   }
/*     */   
/*     */   public String getplanningNumber(HttpServletRequest request) {
/* 240 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/* 241 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 242 */     String productionPlanningNumber = "";
/* 243 */     String str = "";
/* 244 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 245 */     Date date = new Date();
/* 246 */     conditions.put(ProductionplanningQueryCondition.productionPlanningNumber_Like, format.format(date));
/* 247 */     List<Productionplanning> planningList = manager.list(conditions, 0, -1, 
/* 248 */         ProductionplanningQueryOrder.productionPlanningNumber, true);
/* 249 */     if (planningList.size() == 0) {
/* 250 */       productionPlanningNumber = String.valueOf(format.format(date)) + "001";
/*     */     } else {
/* 252 */       productionPlanningNumber = ((Productionplanning)planningList.get(0)).getProductionPlanningNumber();
/* 253 */       String number = productionPlanningNumber.substring(productionPlanningNumber.length() - 3, 
/* 254 */           productionPlanningNumber.length());
/* 255 */       int num = Integer.parseInt(number) + 1;
/* 256 */       for (int i = String.valueOf(num).length(); i < 3; i++) {
/* 257 */         str = String.valueOf(str) + "0";
/*     */       }
/* 259 */       productionPlanningNumber = String.valueOf(format.format(date)) + str + num;
/*     */     } 
/* 261 */     return productionPlanningNumber;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 273 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*     */     try {
/* 275 */       Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 276 */       Productionplanning model = manager.getProductionplanning(id);
/* 277 */       request.setAttribute("X_OBJECT", model);
/* 278 */       if (!isBack(request)) {
/* 279 */         BeanForm beanForm = (BeanForm)getForm("/updateProductionplanning", request);
/* 280 */         beanForm.populate(model, "to_form");
/*     */       } 
/* 282 */     } catch (Exception e) {
/* 283 */       e.printStackTrace();
/*     */     } 
/* 285 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 286 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 298 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*     */     try {
/* 300 */       BeanForm beanForm = (BeanForm)form;
/* 301 */       Integer id = ActionUtils.parseInt((String)beanForm.get("id"));
/* 302 */       Productionplanning model = manager.getProductionplanning(id);
/* 303 */       beanForm.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 304 */       beanForm.populateToBean(model);
/* 305 */       manager.update(model);
/* 306 */       request.setAttribute("X_OBJECT", model);
/* 307 */       request.setAttribute("X_ROWPAGE", "Productionplanning/row.jsp");
/* 308 */     } catch (Exception e) {
/* 309 */       postGlobalMessage("modify.fail", request.getSession());
/* 310 */       e.printStackTrace();
/*     */     } 
/* 312 */     postGlobalMessage("modify.successful", request.getSession());
/* 313 */     return mapping.findForward("success");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 325 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/*     */     try {
/* 327 */       Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 328 */       Productionplanning model = manager.getProductionplanning(id);
/* 329 */       manager.delete(model);
/* 330 */     } catch (Exception e) {
/* 331 */       postGlobalMessage("delete.fail", request.getSession());
/* 332 */       e.printStackTrace();
/*     */     } 
/* 334 */     postGlobalMessage("delete.successful", request.getSession());
/* 335 */     return new ActionForward("listProductionplanning.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward downloafAttach(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 346 */     ProductionplanningManager manager = ServiceLocator.getProductionplanningManager(request);
/* 347 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 348 */     Productionplanning planning = manager.getProductionplanning(id);
/* 349 */     if (planning.getFileContSize() == 0) {
/* 350 */       throw new ActionException("RemoteReturnAttach.error.fileSize.zero");
/*     */     }
/* 352 */     InputStream in = ActionUtils2.getFile(planning.getPath());
/* 353 */     if (in != null) {
/*     */       try {
/* 355 */         DownloadUploadHelper.download(in, planning.getUploadFileName(), 
/* 356 */             DownloadUploadHelper.getMime(planning.getUploadFileName()), planning.getFileContSize(), request, 
/* 357 */             response, true);
/* 358 */         return null;
/*     */       } finally {
/* 360 */         in.close();
/*     */       } 
/*     */     }
/* 363 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/ProductionplanningAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */