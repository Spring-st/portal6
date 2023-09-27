/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.ExpensesCourse;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryCondition;
/*     */ import com.aof.model.basic.query.ExpensesCourseQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.basic.ExpensesCourseManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.basic.ExpensesCourseQueryForm;
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
/*     */ 
/*     */ public class ExpensesCourseAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  62 */     ExpensesCourseQueryForm queryForm = (ExpensesCourseQueryForm)form;
/*  63 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  64 */       queryForm.setOrder(ExpensesCourseQueryOrder.ID.getName());
/*  65 */       queryForm.setDescend(false);
/*     */     } 
/*  67 */     ExpensesCourseManager fm = ServiceLocator.getExpensesCourseManager(request);
/*  68 */     Map conditions = constructQueryMap(queryForm);
/*  69 */     String exportType = queryForm.getExportType();
/*  70 */     if (StringUtils.isNotEmpty(exportType)) {
/*  71 */       List data = fm.getExpensesCourseList(conditions, 0, -1, 
/*  72 */           ExpensesCourseQueryOrder.getEnum(queryForm.getOrder()), 
/*  73 */           queryForm.isDescend());
/*     */       
/*  75 */       int index = SessionTempFile.createNewTempFile(request);
/*  76 */       String fileName = "expensesCourse";
/*  77 */       String suffix = ExportUtil.export(
/*  78 */           exportType, 
/*  79 */           data, 
/*  80 */           request, 
/*  81 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  82 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception
/*     */             {
/*  86 */               MessageResources messages = ExpensesCourseAction.this.getResources(request);
/*  87 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.id"));
/*  88 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.code"));
/*  89 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.description"));
/*  90 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.type"));
/*  91 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.currency"));
/*  92 */               row.add(messages.getMessage(ExpensesCourseAction.this.getLocale(request), "expensesCourse.enabled.chnShortDescription"));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  98 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*  99 */               row.add(BeanHelper.getBeanPropertyValue(data, "code"));
/* 100 */               row.add(BeanHelper.getBeanPropertyValue(data, "description"));
/* 101 */               row.add(BeanHelper.getBeanPropertyValue(data, "type"));
/* 102 */               row.add(BeanHelper.getBeanPropertyValue(data, "currency"));
/* 103 */               row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */             }
/*     */           });
/*     */       
/* 107 */       return new ActionForward("download/" + index + "/" + 
/* 108 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 110 */     if (queryForm.isFirstInit()) {
/* 111 */       queryForm.init(fm.getExpensesCourseListCount(conditions));
/*     */     } else {
/* 113 */       queryForm.init();
/*     */     } 
/* 115 */     List result = fm.getExpensesCourseList(conditions, 
/* 116 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 117 */         ExpensesCourseQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 119 */     request.setAttribute("X_RESULTLIST", result);
/* 120 */     request.setAttribute("x_selType", Integer.valueOf(84));
/* 121 */     putEnumListToRequest(request);
/* 122 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 128 */     ExpensesCourseQueryForm queryForm = (ExpensesCourseQueryForm)form;
/* 129 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 130 */       queryForm.setOrder(ExpensesCourseQueryOrder.ID.getName());
/* 131 */       queryForm.setDescend(false);
/* 132 */       queryForm.setStatus("0");
/*     */     } 
/* 134 */     ExpensesCourseManager fm = ServiceLocator.getExpensesCourseManager(request);
/* 135 */     Map conditions = constructQueryMap(queryForm);
/* 136 */     if (queryForm.isFirstInit()) {
/* 137 */       queryForm.init(fm.getExpensesCourseListCount(conditions));
/*     */     } else {
/* 139 */       queryForm.init();
/*     */     } 
/* 141 */     List result = fm.getExpensesCourseList(conditions, 
/* 142 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 143 */         ExpensesCourseQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 145 */     request.setAttribute("X_RESULTLIST", result);
/* 146 */     putEnumListToRequest(request);
/* 147 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ExpensesCourseQueryForm queryForm) {
/* 151 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 152 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 153 */     if (id != null && !id.equals("")) {
/* 154 */       conditions.put(ExpensesCourseQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 157 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   private ExpensesCourse getExpensesCourse(HttpServletRequest request) throws Exception {
/* 162 */     String id = request.getParameter("id");
/* 163 */     ExpensesCourseManager expensesCourseManager = ServiceLocator.getExpensesCourseManager(request);
/* 164 */     ExpensesCourse expensesCourse = expensesCourseManager.getExpensesCourse(Integer.valueOf(Integer.parseInt(id)));
/* 165 */     if (expensesCourse == null)
/* 166 */       throw new ActionException("expensesCourse.notFound", id); 
/* 167 */     return expensesCourse;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 171 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 172 */         PersistentEnum.getEnumList(EnabledDisabled.class));
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
/* 188 */     ExpensesCourse expensesCourse = getExpensesCourse(request);
/* 189 */     request.setAttribute("x_expensesCourse", expensesCourse);
/* 190 */     if (!isBack(request)) {
/* 191 */       BeanForm expensesCourseForm = (BeanForm)getForm("/updateExpensesCourse", request);
/* 192 */       expensesCourseForm.populate(expensesCourse, "to_form");
/*     */     } 
/*     */     
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
/* 212 */     ExpensesCourse expensesCourse = getExpensesCourse(request);
/*     */     
/* 214 */     ExpensesCourseManager cm = 
/* 215 */       ServiceLocator.getExpensesCourseManager(request);
/*     */     try {
/* 217 */       cm.deleteExpensesCourse(expensesCourse);
/* 218 */     } catch (Throwable t) {
/* 219 */       throw new ActionException("expensesCourse.delete.fail");
/*     */     } 
/* 221 */     return mapping.findForward("success");
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
/* 237 */     BeanForm expensesCourseForm = (BeanForm)form;
/* 238 */     ExpensesCourse expensesCourse = new ExpensesCourse();
/* 239 */     expensesCourseForm.populate(expensesCourse, "to_bean");
/*     */     
/* 241 */     ExpensesCourseManager expensesCourseManager = ServiceLocator.getExpensesCourseManager(request);
/*     */     
/* 243 */     request.setAttribute("X_OBJECT", expensesCourseManager.updateExpensesCourse(expensesCourse));
/* 244 */     request.setAttribute("X_ROWPAGE", "wmsbasic/expensesCourse/row.jsp");
/*     */     
/* 246 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 251 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 255 */     String s = request.getParameter("site_id");
/* 256 */     return (s != null && !s.equals(""));
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
/* 272 */     if (!isBack(request)) {
/* 273 */       ExpensesCourse expensesCourse = new ExpensesCourse();
/* 274 */       BeanForm expensesCourseForm = (BeanForm)getForm("/insertExpensesCourse", request);
/* 275 */       expensesCourseForm.populate(expensesCourse, "to_form");
/*     */     } 
/* 277 */     putEnumListToRequest(request);
/* 278 */     return mapping.findForward("page");
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
/* 294 */     ExpensesCourseManager cm = ServiceLocator.getExpensesCourseManager(request);
/* 295 */     BeanForm expensesCourseForm = (BeanForm)form;
/* 296 */     ExpensesCourse expensesCourse = new ExpensesCourse();
/* 297 */     expensesCourseForm.populate(expensesCourse, "to_bean");
/*     */     
/* 299 */     request.setAttribute("X_OBJECT", cm.insertExpensesCourse(expensesCourse));
/* 300 */     request.setAttribute("X_ROWPAGE", "wmsbasic/expensesCourse/row.jsp");
/*     */     
/* 302 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/ExpensesCourseAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */