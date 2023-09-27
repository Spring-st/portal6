/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.schedule.QadOrEdi;
/*     */ import com.aof.model.schedule.query.QadOrEdiQueryOrder;
/*     */ import com.aof.service.schedule.QadOrEdiManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.QadOrEdiQueryForm;
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
/*     */ public class QadOrEdiAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private Map getConditions(QadOrEdiQueryForm formBean) {
/*  43 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  44 */     String str = "";
/*     */     
/*  46 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  52 */     QadOrEdiManager manager = ServiceLocator.getQadOrEdiManager(request);
/*  53 */     QadOrEdiQueryForm queryForm = (QadOrEdiQueryForm)form;
/*  54 */     Map conditions = getConditions(queryForm);
/*  55 */     if (queryForm.getOrder() == "") {
/*  56 */       queryForm.setDescend(false);
/*     */     }
/*  58 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  59 */     String exportType = queryForm.getExportType();
/*  60 */     if (exportType != null && exportType.length() > 0) {
/*  61 */       List datas = manager.getList(conditions, 0, -1, 
/*  62 */           QadOrEdiQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  63 */       int index = SessionTempFile.createNewTempFile(request);
/*  64 */       String fileName = "QadOrEdi";
/*  65 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  66 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  67 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/*  71 */               row.add(BeanUtils.getProperty(data, "models"));
/*  72 */               row.add(BeanUtils.getProperty(data, "qadPart.id"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  77 */               MessageResources message = QadOrEdiAction.this.getResources(request);
/*  78 */               row.add(message.getMessage(QadOrEdiAction.this.getLocale(request), "qadoredi.models"));
/*  79 */               row.add(message.getMessage(QadOrEdiAction.this.getLocale(request), "qadoredi.qadpart"));
/*     */             }
/*     */           });
/*     */       
/*  83 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/*  85 */     if (queryForm.isFirstInit()) {
/*  86 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/*  88 */       queryForm.init();
/*     */     } 
/*  90 */     int pageNum = queryForm.getPageNoAsInt();
/*  91 */     int pageSize = queryForm.getPageSizeAsInt();
/*  92 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/*  93 */         QadOrEdiQueryOrder.getEnum(queryForm.getOrder()), 
/*  94 */         queryForm.isDescend());
/*  95 */     request.setAttribute("x_selType", Integer.valueOf(167));
/*  96 */     request.setAttribute("X_RESULTLIST", entityList);
/*  97 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 103 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 108 */     QadOrEdiManager manager = ServiceLocator.getQadOrEdiManager(request);
/* 109 */     BeanForm formBean = (BeanForm)form;
/* 110 */     QadOrEdi entity = new QadOrEdi();
/* 111 */     formBean.populateToBean(entity);
/* 112 */     request.setAttribute("X_OBJECT", manager.save(entity));
/* 113 */     request.setAttribute("X_ROWPAGE", "schedule/qadoredi/row.jsp");
/* 114 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 119 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 120 */     QadOrEdiManager manager = ServiceLocator.getQadOrEdiManager(request);
/* 121 */     QadOrEdi entity = manager.getQadOrEdi(id);
/* 122 */     request.setAttribute("X_OBJECT", entity);
/* 123 */     if (!isBack(request)) {
/*     */       
/* 125 */       BeanForm qadOrEdiForm = (BeanForm)getForm("/updateQadOrEdiAction", request);
/* 126 */       qadOrEdiForm.populate(entity, "to_form");
/*     */     } 
/* 128 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 133 */     QadOrEdiManager manager = ServiceLocator.getQadOrEdiManager(request);
/* 134 */     BeanForm formBean = (BeanForm)form;
/* 135 */     QadOrEdi entity = new QadOrEdi();
/* 136 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 137 */     formBean.populateToBean(entity, request);
/* 138 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 139 */     request.setAttribute("X_ROWPAGE", "schedule/qadoredi/row.jsp");
/* 140 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 145 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 146 */     QadOrEdiManager manager = ServiceLocator.getQadOrEdiManager(request);
/* 147 */     manager.delete(manager.getQadOrEdi(id));
/* 148 */     return new ActionForward("listQadOrEdiAction.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/QadOrEdiAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */