/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.query.FunctionQueryCondition;
/*     */ import com.aof.model.admin.query.FunctionQueryOrder;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.FunctionQueryForm;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionAction extends BaseAction2 {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  56 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/*     */     
/*  58 */     FunctionQueryForm queryForm = (FunctionQueryForm)form;
/*     */     
/*  60 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  62 */     String exportType = queryForm.getExportType();
/*  63 */     if (exportType != null && exportType.length() > 0) {
/*  64 */       List data = fm.getFunctionList(conditions, 0, -1, FunctionQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  65 */       int index = SessionTempFile.createNewTempFile(request);
/*  66 */       String fileName = "function";
/*  67 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  70 */               MessageResources messages = FunctionAction.this.getResources(request);
/*  71 */               row.add(messages.getMessage(FunctionAction.this.getLocale(request), "function.id"));
/*  72 */               row.add(messages.getMessage(FunctionAction.this.getLocale(request), "function.name"));
/*  73 */               row.add(messages.getMessage(FunctionAction.this.getLocale(request), "function.description"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  77 */               row.add(BeanUtils.getProperty(data, "id"));
/*  78 */               row.add(BeanUtils.getProperty(data, "name"));
/*  79 */               row.add(BeanUtils.getProperty(data, "description"));
/*     */             }
/*     */           });
/*  82 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  85 */     if (queryForm.isFirstInit()) {
/*  86 */       queryForm.init(fm.getFunctionListCount(conditions));
/*     */     } else {
/*  88 */       queryForm.init();
/*     */     } 
/*     */     
/*  91 */     request.setAttribute("X_RESULTLIST", fm.getFunctionList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), FunctionQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/*  92 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(FunctionQueryForm queryForm) {
/*  96 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  98 */     String name = queryForm.getName();
/*  99 */     if (name != null) {
/* 100 */       name = name.trim();
/* 101 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 103 */     if (name != null) conditions.put(FunctionQueryCondition.NAME_LIKE, name);
/*     */     
/* 105 */     return conditions;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 118 */     if (!isBack(request)) {
/* 119 */       Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 120 */       FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 121 */       Function f = fm.getFunction(id);
/* 122 */       if (f == null) throw new ActionException("function.notFound", id); 
/* 123 */       BeanForm functionForm = (BeanForm)getForm("/updateFunction", request);
/* 124 */       functionForm.populateToForm(f);
/*     */     } 
/* 126 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 139 */     BeanForm functionForm = (BeanForm)form;
/* 140 */     Function f = new Function();
/* 141 */     functionForm.populateToBean(f);
/* 142 */     FunctionManager fm = ServiceLocator.getFunctionManager(request);
/* 143 */     request.setAttribute("X_OBJECT", fm.saveFunction(f));
/* 144 */     request.setAttribute("X_ROWPAGE", "function/row.jsp");
/*     */     
/* 146 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/FunctionAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */