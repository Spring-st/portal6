/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.schedule.NjitNpoPlan;
/*     */ import com.aof.model.schedule.query.NjitNpoPlanQueryCondition;
/*     */ import com.aof.model.schedule.query.Portalv6MrpPartPlanViewQueryOrder;
/*     */ import com.aof.service.schedule.NjitNpoPlanManager;
/*     */ import com.aof.service.schedule.Portalv6MrpPartPlanViewManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.NjitNpoPlanQueryForm;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NjitNpoPlanAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map getConditions(NjitNpoPlanQueryForm formBean) {
/*  50 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  51 */     String str = "";
/*     */     
/*  53 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     Portalv6MrpPartPlanViewManager manager = ServiceLocator.getPortalv6MrpPartPlanViewManager(request);
/*     */     
/*  62 */     NjitNpoPlanQueryForm queryForm = (NjitNpoPlanQueryForm)form;
/*  63 */     Map<NjitNpoPlanQueryCondition, String> conditions = getConditions(queryForm);
/*  64 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  65 */     if (!hasGlobalPower(request)) {
/*  66 */       User user = getCurrentUser(request);
/*  67 */       conditions.put(NjitNpoPlanQueryCondition.PARTID_VEND_EQ, user.getLoginName());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     String exportType = queryForm.getExportType();
/*  78 */     if (exportType != null && exportType.length() > 0) {
/*  79 */       List datas = manager.getList(conditions, 0, -1, 
/*  80 */           Portalv6MrpPartPlanViewQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  81 */       int index = SessionTempFile.createNewTempFile(request);
/*     */       
/*  83 */       String fileName = "Portalv6MrpPartPlanView";
/*  84 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  85 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  86 */           new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  89 */               MessageResources message = NjitNpoPlanAction.this.getResources(request);
/*     */               
/*  91 */               row.add("物料编码");
/*  92 */               row.add("中文描述");
/*  93 */               row.add("当前库存");
/*  94 */               row.add("库存低储");
/*  95 */               row.add("安全库存");
/*  96 */               row.add("库存高储");
/*  97 */               row.add("产品类型");
/*  98 */               row.add("类型");
/*  99 */               row.add("车型");
/* 100 */               row.add("供应商");
/* 101 */               List<String> strList = new ArrayList<String>();
/* 102 */               SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
/* 103 */               Date date = new Date(); int i;
/* 104 */               for (i = 0; i < 45; i++) {
/* 105 */                 Calendar calendar = Calendar.getInstance();
/* 106 */                 calendar.setTime(date);
/* 107 */                 calendar.add(5, i);
/* 108 */                 strList.add(sdf.format(calendar.getTime()));
/*     */               } 
/* 110 */               if (strList != null && strList.size() > 0) {
/* 111 */                 for (i = 0; i < strList.size(); i++) {
/* 112 */                   row.add(strList.get(i));
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 118 */               row.add(BeanUtils.getProperty(data, "partId"));
/* 119 */               row.add(BeanUtils.getProperty(data, "name"));
/* 120 */               row.add(BeanUtils.getProperty(data, "current_qty"));
/* 121 */               row.add(BeanUtils.getProperty(data, "b"));
/* 122 */               row.add(BeanUtils.getProperty(data, "c"));
/* 123 */               row.add(BeanUtils.getProperty(data, "d"));
/* 124 */               row.add(BeanUtils.getProperty(data, "e"));
/* 125 */               row.add(BeanUtils.getProperty(data, "f"));
/* 126 */               row.add(BeanUtils.getProperty(data, "g"));
/* 127 */               row.add(BeanUtils.getProperty(data, "h"));
/* 128 */               for (int i = 0; i < 45; i++) {
/* 129 */                 int num = i + 1;
/* 130 */                 row.add(BeanUtils.getProperty(data, "d" + num));
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 135 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 137 */     if (queryForm.isFirstInit()) {
/* 138 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 140 */       queryForm.init();
/*     */     } 
/* 142 */     int pageNum = queryForm.getPageNoAsInt();
/* 143 */     int pageSize = queryForm.getPageSizeAsInt();
/* 144 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 145 */         Portalv6MrpPartPlanViewQueryOrder.getEnum(queryForm.getOrder()), 
/* 146 */         queryForm.isDescend());
/* 147 */     request.setAttribute("x_selType", Integer.valueOf(168));
/* 148 */     request.setAttribute("X_RESULTLIST", entityList);
/* 149 */     calculatedByDate(request);
/* 150 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 156 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 161 */     NjitNpoPlanManager manager = ServiceLocator.getNjitNpoPlanManager(request);
/* 162 */     BeanForm formBean = (BeanForm)form;
/* 163 */     NjitNpoPlan entity = new NjitNpoPlan();
/* 164 */     formBean.populateToBean(entity);
/* 165 */     request.setAttribute("X_OBJECT", manager.save(entity));
/* 166 */     request.setAttribute("X_ROWPAGE", "njitNpoPlan/row.jsp");
/* 167 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 172 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 173 */     NjitNpoPlanManager manager = ServiceLocator.getNjitNpoPlanManager(request);
/* 174 */     NjitNpoPlan entity = manager.getNjitNpoPlan(id);
/* 175 */     request.setAttribute("X_OBJECT", entity);
/* 176 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 181 */     NjitNpoPlanManager manager = ServiceLocator.getNjitNpoPlanManager(request);
/* 182 */     BeanForm formBean = (BeanForm)form;
/* 183 */     NjitNpoPlan entity = new NjitNpoPlan();
/* 184 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 185 */     formBean.populateToBean(entity, request);
/* 186 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 187 */     request.setAttribute("X_ROWPAGE", "njitNpoPlan/row.jsp");
/* 188 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 193 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 194 */     NjitNpoPlanManager manager = ServiceLocator.getNjitNpoPlanManager(request);
/* 195 */     manager.delete(manager.getNjitNpoPlan(id));
/* 196 */     return new ActionForward("listNjitNpoPlanAction.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculatedByDate(HttpServletRequest request) {
/* 203 */     List<String> strList = new ArrayList<String>();
/* 204 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 205 */     Date date = new Date();
/* 206 */     for (int i = 0; i < 45; i++) {
/* 207 */       Calendar calendar = Calendar.getInstance();
/* 208 */       calendar.setTime(date);
/* 209 */       calendar.add(5, i);
/* 210 */       strList.add(sdf.format(calendar.getTime()));
/*     */     } 
/* 212 */     request.setAttribute("dateList", strList);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/NjitNpoPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */