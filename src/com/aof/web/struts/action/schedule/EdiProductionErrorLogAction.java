/*    */ package com.aof.web.struts.action.schedule;
/*    */ 
/*    */ import com.aof.model.schedule.EdiProductionErrorLog;
/*    */ import com.aof.model.schedule.query.EdiProductionErrorLogQueryOrder;
/*    */ import com.aof.service.schedule.EdiProductionErrorLogManager;
/*    */ import com.aof.utils.SessionTempFile;
/*    */ import com.aof.web.struts.action.BaseAction2;
/*    */ import com.aof.web.struts.action.ServiceLocator;
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ import com.aof.web.struts.form.schedule.EdiProductionErrorLogQueryForm;
/*    */ import com.shcnc.utils.BeanUtils;
/*    */ import com.shcnc.utils.ExportUtil;
/*    */ import com.shcnc.utils.Exportable;
/*    */ import java.io.FileOutputStream;
/*    */ import java.net.URLEncoder;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.struts.action.ActionForm;
/*    */ import org.apache.struts.action.ActionForward;
/*    */ import org.apache.struts.action.ActionMapping;
/*    */ import org.apache.struts.util.MessageResources;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EdiProductionErrorLogAction
/*    */   extends BaseAction2
/*    */ {
/*    */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 33 */     EdiProductionErrorLogManager manager = ServiceLocator.getEdiProductionErrorLogManager(request);
/* 34 */     EdiProductionErrorLogQueryForm queryForm = (EdiProductionErrorLogQueryForm)form;
/* 35 */     Map conditions = getConditions(queryForm);
/* 36 */     getConditionAndOrder(queryForm, conditions, request);
/* 37 */     String exportType = queryForm.getExportType();
/* 38 */     if (exportType != null && exportType.length() > 0) {
/* 39 */       List datas = manager.getList(conditions, 0, -1, EdiProductionErrorLogQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 40 */       int index = SessionTempFile.createNewTempFile(request);
/* 41 */       String fileName = "ZiDongFenJieErrorLog";
/* 42 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 43 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 44 */           new Exportable()
/*    */           {
/*    */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 47 */               row.add(BeanUtils.getProperty(data, "asnNo"));
/* 48 */               row.add(BeanUtils.getProperty(data, "productlinecode"));
/* 49 */               row.add(BeanUtils.getProperty(data, "shiftcode"));
/* 50 */               row.add(BeanUtils.getProperty(data, "staffcode"));
/* 51 */               row.add(BeanUtils.getProperty(data, "number"));
/* 52 */               row.add(BeanUtils.getProperty(data, "taskDate"));
/* 53 */               row.add(BeanUtils.getProperty(data, "time"));
/* 54 */               row.add(BeanUtils.getProperty(data, "qty"));
/* 55 */               row.add(BeanUtils.getProperty(data, "syncTime"));
/* 56 */               row.add(BeanUtils.getProperty(data, "errorInfo"));
/* 57 */               EdiProductionErrorLog prodution = (EdiProductionErrorLog)data;
/*    */             }
/*    */             
/*    */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 61 */               MessageResources message = EdiProductionErrorLogAction.this.getResources(request);
/* 62 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.asnno"));
/* 63 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.productlinecode"));
/* 64 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.shiftcode"));
/* 65 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.staffcode"));
/* 66 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.number"));
/* 67 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.taskdate"));
/* 68 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.time"));
/* 69 */               row.add(message.getMessage(EdiProductionErrorLogAction.this.getLocale(request), "ediproduction.qty"));
/* 70 */               row.add("同步时间");
/* 71 */               row.add("错误信息");
/*    */             }
/*    */           });
/* 74 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*    */     } 
/* 76 */     if (queryForm.isFirstInit()) {
/* 77 */       queryForm.init(manager.getListCount(conditions).intValue());
/*    */     } else {
/* 79 */       queryForm.init();
/*    */     } 
/* 81 */     List<EdiProductionErrorLog> errorLogList = manager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), EdiProductionErrorLogQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 82 */     request.setAttribute("X_RESULTLIST", errorLogList);
/* 83 */     request.setAttribute("x_selType", Integer.valueOf(179));
/* 84 */     return mapping.findForward("page");
/*    */   }
/*    */   
/*    */   private Map getConditions(EdiProductionErrorLogQueryForm formBean) {
/* 88 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 89 */     String str = "";
/*    */     
/* 91 */     return conditions;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/EdiProductionErrorLogAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */