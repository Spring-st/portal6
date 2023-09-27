/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryOrder;
/*     */ import com.aof.service.schedule.ProjectedInventoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.ProjectedInventoryQueryForm;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProjectedInventoryAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward kucun(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  50 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/*  51 */     ProjectedInventoryQueryForm queryForm = (ProjectedInventoryQueryForm)form;
/*  52 */     Map<String, String> conditions = getConditions(queryForm);
/*  53 */     if (queryForm.getOrder() == "") {
/*  54 */       queryForm.setOrder(ProjectedInventoryQueryOrder.ID.getName());
/*  55 */       queryForm.setDescend(false);
/*     */     } 
/*  57 */     if (queryForm.getHeight() != null && queryForm.getHeight() != "") {
/*  58 */       conditions.put("a", queryForm.getHeight());
/*     */     }
/*  60 */     if (queryForm.getLow() != null && queryForm.getLow() != "") {
/*  61 */       conditions.put("b", queryForm.getLow());
/*     */     }
/*  63 */     if (queryForm.getEq() != null && queryForm.getEq() != "") {
/*  64 */       conditions.put("e", queryForm.getEq());
/*     */     }
/*  66 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  67 */     if (!hasGlobalPower(request)) {
/*  68 */       User user = getCurrentUser(request);
/*  69 */       conditions.put(ProjectedInventoryQueryCondition.Part_VEND_EQ, user.getLoginName());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     String exportType = queryForm.getExportType();
/*  78 */     if (exportType != null && exportType.length() > 0) {
/*  79 */       List datas = manager.getList(conditions, 0, -1, 
/*  80 */           ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  81 */       int index = SessionTempFile.createNewTempFile(request);
/*  82 */       String fileName = "ProjectedInventory";
/*  83 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  84 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  85 */           new Exportable() {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  87 */               MessageResources message = ProjectedInventoryAction.this.getResources(request);
/*  88 */               row.add("物料");
/*  89 */               row.add("描述");
/*  90 */               row.add("单位");
/*  91 */               row.add("属性");
/*  92 */               row.add("售后件/量产件");
/*  93 */               row.add("车型");
/*  94 */               row.add("供应商");
/*  95 */               row.add("当前库存");
/*  96 */               row.add("高储库存");
/*  97 */               row.add("低储库存");
/*  98 */               row.add("安全库存");
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 102 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 103 */               row.add(BeanUtils.getProperty(data, "part.name"));
/* 104 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/* 105 */               row.add(BeanUtils.getProperty(data, "part.productClass"));
/* 106 */               row.add(BeanUtils.getProperty(data, "part.drwgLoc"));
/* 107 */               row.add(BeanUtils.getProperty(data, "part.productSpecifications"));
/* 108 */               row.add(BeanUtils.getProperty(data, "part.vend"));
/* 109 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 110 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 111 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 112 */               row.add(BeanUtils.getProperty(data, "part.securityQty"));
/*     */             }
/*     */           });
/*     */       
/* 116 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (queryForm.isFirstInit()) {
/* 124 */       queryForm.init(manager.getJitShipPartNumberList(conditions, 0, -1, null, true).size());
/*     */     } else {
/* 126 */       queryForm.init();
/*     */     } 
/* 128 */     int pageNum = queryForm.getPageNoAsInt();
/* 129 */     int pageSize = queryForm.getPageSizeAsInt();
/* 130 */     List<ProjectedInventory> entityList = manager.getJitShipPartNumberList(conditions, pageNum, pageSize, 
/* 131 */         ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     request.setAttribute("x_selType", Integer.valueOf(184));
/* 137 */     request.setAttribute("X_RESULTLIST", entityList);
/* 138 */     return mapping.findForward("page");
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
/*     */   private Map getConditions(ProjectedInventoryQueryForm formBean) {
/* 150 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 151 */     String str = "";
/*     */     
/* 153 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProjected(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 159 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 160 */     ProjectedInventoryQueryForm queryForm = (ProjectedInventoryQueryForm)form;
/* 161 */     Map<ProjectedInventoryQueryCondition, String> conditions = getConditions(queryForm);
/* 162 */     if (queryForm.getOrder() == "") {
/* 163 */       queryForm.setOrder(ProjectedInventoryQueryOrder.ID.getName());
/* 164 */       queryForm.setDescend(false);
/*     */     } 
/* 166 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 167 */     if (!hasGlobalPower(request)) {
/* 168 */       User user = getCurrentUser(request);
/* 169 */       conditions.put(ProjectedInventoryQueryCondition.Part_VEND_EQ, user.getLoginName());
/*     */     } 
/* 171 */     String exportType = queryForm.getExportType();
/* 172 */     if (exportType != null && exportType.length() > 0) {
/* 173 */       List datas = manager.getList(conditions, 0, -1, 
/* 174 */           ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 175 */       int index = SessionTempFile.createNewTempFile(request);
/* 176 */       String fileName = "ProjectedInventory";
/* 177 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 178 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 179 */           new Exportable() {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 181 */               MessageResources message = ProjectedInventoryAction.this.getResources(request);
/* 182 */               row.add("物料编码");
/* 183 */               row.add("物料名称");
/* 184 */               row.add("供应商编码");
/* 185 */               row.add("高储库存");
/* 186 */               row.add("低储库存");
/* 187 */               row.add("安全库存");
/* 188 */               row.add("当前库存");
/* 189 */               row.add("单位");
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 194 */               row.add(BeanUtils.getProperty(data, "part.id"));
/* 195 */               row.add(BeanUtils.getProperty(data, "part.name"));
/* 196 */               row.add(BeanUtils.getProperty(data, "part.vend"));
/* 197 */               row.add(BeanUtils.getProperty(data, "part.highQty"));
/* 198 */               row.add(BeanUtils.getProperty(data, "part.lowQty"));
/* 199 */               row.add(BeanUtils.getProperty(data, "part.securityQty"));
/* 200 */               row.add(BeanUtils.getProperty(data, "currentQty"));
/* 201 */               row.add(BeanUtils.getProperty(data, "part.unit"));
/*     */             }
/*     */           });
/*     */       
/* 205 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 207 */     if (queryForm.isFirstInit()) {
/* 208 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 210 */       queryForm.init();
/*     */     } 
/* 212 */     int pageNum = queryForm.getPageNoAsInt();
/* 213 */     int pageSize = queryForm.getPageSizeAsInt();
/* 214 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 215 */         ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 216 */         queryForm.isDescend());
/* 217 */     request.setAttribute("x_selType", Integer.valueOf(173));
/* 218 */     request.setAttribute("X_RESULTLIST", entityList);
/* 219 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listForecastPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 224 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 225 */     ProjectedInventoryQueryForm queryForm = (ProjectedInventoryQueryForm)form;
/* 226 */     Map conditions = getConditions(queryForm);
/* 227 */     if (queryForm.getOrder() == "") {
/* 228 */       queryForm.setOrder(ProjectedInventoryQueryOrder.ID.getName());
/* 229 */       queryForm.setDescend(false);
/*     */     } 
/* 231 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 232 */     String exportType = queryForm.getExportType();
/* 233 */     if (exportType != null && exportType.length() > 0) {
/* 234 */       List datas = manager.getList(conditions, 0, -1, 
/* 235 */           ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 236 */       int index = SessionTempFile.createNewTempFile(request);
/* 237 */       String fileName = "ProjectedInventory";
/* 238 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 239 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 240 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 244 */               row.add(BeanUtils.getProperty(data, "id"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 249 */               MessageResources message = ProjectedInventoryAction.this.getResources(request);
/* 250 */               row.add(message.getMessage(ProjectedInventoryAction.this.getLocale(request), "mapping.id"));
/*     */             }
/*     */           });
/*     */       
/* 254 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 256 */     if (queryForm.isFirstInit()) {
/* 257 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 259 */       queryForm.init();
/*     */     } 
/* 261 */     int pageNum = queryForm.getPageNoAsInt();
/* 262 */     int pageSize = queryForm.getPageSizeAsInt();
/* 263 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 264 */         ProjectedInventoryQueryOrder.getEnum(queryForm.getOrder()), 
/* 265 */         queryForm.isDescend());
/* 266 */     request.setAttribute("x_selType", Integer.valueOf(172));
/* 267 */     request.setAttribute("X_RESULTLIST", entityList);
/* 268 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 273 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 278 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 279 */     BeanForm formBean = (BeanForm)form;
/* 280 */     ProjectedInventory entity = new ProjectedInventory();
/* 281 */     formBean.populateToBean(entity);
/* 282 */     request.setAttribute("X_OBJECT", manager.save(entity));
/* 283 */     request.setAttribute("X_ROWPAGE", "projectedInventory/row.jsp");
/* 284 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 289 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 290 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 291 */     ProjectedInventory entity = manager.getProjectedInventory(id);
/* 292 */     request.setAttribute("X_OBJECT", entity);
/* 293 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 298 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 299 */     BeanForm formBean = (BeanForm)form;
/* 300 */     ProjectedInventory entity = new ProjectedInventory();
/* 301 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 302 */     formBean.populateToBean(entity, request);
/* 303 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 304 */     request.setAttribute("X_ROWPAGE", "projectedInventory/row.jsp");
/* 305 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 310 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 311 */     ProjectedInventoryManager manager = ServiceLocator.getProjectedInventoryManager(request);
/* 312 */     manager.delete(manager.getProjectedInventory(id));
/* 313 */     return new ActionForward("listProjectedInventoryAction.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/ProjectedInventoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */