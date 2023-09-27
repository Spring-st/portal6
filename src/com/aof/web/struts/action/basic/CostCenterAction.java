/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.CostCenter;
/*     */ import com.aof.model.basic.query.CostCenterQueryCondition;
/*     */ import com.aof.model.basic.query.CostCenterQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.basic.CostCenterManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.CostCenterQueryForm;
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
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class CostCenterAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  62 */     CostCenterQueryForm queryForm = (CostCenterQueryForm)form;
/*  63 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  64 */       queryForm.setOrder(CostCenterQueryOrder.ID.getName());
/*  65 */       queryForm.setDescend(false);
/*  66 */       queryForm.setStatus("0");
/*     */     } 
/*  68 */     CostCenterManager fm = ServiceLocator.getCostCenterManager(request);
/*  69 */     Map conditions = constructQueryMap(queryForm);
/*  70 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  71 */     String exportType = queryForm.getExportType();
/*  72 */     if (StringUtils.isNotEmpty(exportType)) {
/*  73 */       List data = fm.getCostCenterList(conditions, 0, -1, 
/*  74 */           CostCenterQueryOrder.getEnum(queryForm.getOrder()), 
/*  75 */           queryForm.isDescend());
/*     */       
/*  77 */       int index = SessionTempFile.createNewTempFile(request);
/*  78 */       String fileName = "costCenter";
/*  79 */       String suffix = ExportUtil.export(
/*  80 */           exportType, 
/*  81 */           data, 
/*  82 */           request, 
/*  83 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  84 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/*  88 */               MessageResources messages = CostCenterAction.this.getResources(request);
/*  89 */               row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.id"));
/*  90 */               row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.code"));
/*  91 */               row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "costCenter.description"));
/*  92 */               row.add(messages.getMessage(CostCenterAction.this.getLocale(request), "enabled.chnShortDescription"));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  98 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*  99 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/* 100 */               row.add(BeanHelper.getBeanPropertyValue(data, "description"));
/* 101 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */             }
/*     */           });
/*     */       
/* 105 */       return new ActionForward("download/" + index + "/" + 
/* 106 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 108 */     if (queryForm.isFirstInit()) {
/* 109 */       queryForm.init(fm.getCostCenterListCount(conditions));
/*     */     } else {
/* 111 */       queryForm.init();
/*     */     } 
/* 113 */     List result = fm.getCostCenterList(conditions, 
/* 114 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 115 */         CostCenterQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 117 */     request.setAttribute("X_RESULTLIST", result);
/* 118 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 119 */     request.setAttribute("x_selType", Integer.valueOf(83));
/* 120 */     putEnumListToRequest(request);
/* 121 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 127 */     CostCenterQueryForm queryForm = (CostCenterQueryForm)form;
/* 128 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 129 */       queryForm.setOrder(CostCenterQueryOrder.ID.getName());
/* 130 */       queryForm.setDescend(false);
/* 131 */       queryForm.setStatus("0");
/*     */     } 
/* 133 */     CostCenterManager fm = ServiceLocator.getCostCenterManager(request);
/* 134 */     Map conditions = constructQueryMap(queryForm);
/* 135 */     if (queryForm.isFirstInit()) {
/* 136 */       queryForm.init(fm.getCostCenterListCount(conditions));
/*     */     } else {
/* 138 */       queryForm.init();
/*     */     } 
/* 140 */     List result = fm.getCostCenterList(conditions, 
/* 141 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 142 */         CostCenterQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 144 */     request.setAttribute("X_RESULTLIST", result);
/* 145 */     putEnumListToRequest(request);
/* 146 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(CostCenterQueryForm queryForm) {
/* 150 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 151 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 152 */     if (id != null && !id.equals("")) {
/* 153 */       conditions.put(CostCenterQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 156 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   private CostCenter getCostCenter(HttpServletRequest request) throws Exception {
/* 161 */     String id = request.getParameter("id");
/* 162 */     CostCenterManager costCenterManager = ServiceLocator.getCostCenterManager(request);
/* 163 */     CostCenter costCenter = costCenterManager.getCostCenter(Integer.valueOf(Integer.parseInt(id)));
/* 164 */     if (costCenter == null)
/* 165 */       throw new ActionException("costCenter.notFound", id); 
/* 166 */     return costCenter;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 170 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 171 */         PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 187 */     CostCenter costCenter = getCostCenter(request);
/* 188 */     request.setAttribute("x_costCenter", costCenter);
/* 189 */     if (!isBack(request)) {
/*     */       
/* 191 */       BeanForm costCenterForm = (BeanForm)getForm("/updateCostCenter", 
/* 192 */           request);
/* 193 */       costCenterForm.populate(costCenter, "to_form");
/*     */     } 
/* 195 */     putEnumListToRequest(request);
/* 196 */     return mapping.findForward("page");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 212 */     CostCenter costCenter = getCostCenter(request);
/*     */     
/* 214 */     CostCenterManager cm = ServiceLocator.getCostCenterManager(request);
/*     */     try {
/* 216 */       cm.deleteCostCenter(costCenter);
/* 217 */     } catch (Throwable t) {
/* 218 */       throw new ActionException("costCenter.delete.fail");
/*     */     } 
/* 220 */     return mapping.findForward("success");
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
/* 236 */     BeanForm costCenterForm = (BeanForm)form;
/* 237 */     CostCenter costCenter = new CostCenter();
/* 238 */     costCenterForm.populate(costCenter, "to_bean");
/*     */     
/* 240 */     CostCenterManager costCenterManager = ServiceLocator.getCostCenterManager(request);
/*     */     
/* 242 */     request.setAttribute("X_OBJECT", costCenterManager.updateCostCenter(costCenter));
/* 243 */     request.setAttribute("X_ROWPAGE", "wmsbasic/costCenter/row.jsp");
/*     */     
/* 245 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 250 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 254 */     String s = request.getParameter("site_id");
/* 255 */     return (s != null && !s.equals(""));
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 278 */     if (!isBack(request)) {
/* 279 */       CostCenter costCenter = new CostCenter();
/* 280 */       BeanForm costCenterForm = (BeanForm)getForm("/insertCostCenter", 
/* 281 */           request);
/* 282 */       costCenterForm.populate(costCenter, "to_form");
/*     */     } 
/* 284 */     putEnumListToRequest(request);
/* 285 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 310 */     CostCenterManager cm = ServiceLocator.getCostCenterManager(request);
/* 311 */     BeanForm costCenterForm = (BeanForm)form;
/* 312 */     CostCenter costCenter = new CostCenter();
/* 313 */     costCenterForm.populate(costCenter, "to_bean");
/*     */     
/* 315 */     request.setAttribute("X_OBJECT", cm.insertCostCenter(costCenter));
/* 316 */     request.setAttribute("X_ROWPAGE", "wmsbasic/costCenter/row.jsp");
/*     */     
/* 318 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/CostCenterAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */