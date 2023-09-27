/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.SystemLogQueryCondition;
/*     */ import com.aof.model.admin.query.SystemLogQueryOrder;
/*     */ import com.aof.service.admin.SystemLogManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.SystemLogQueryForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
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
/*     */ public class SystemLogAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map constructQueryMap(SystemLogQueryForm queryForm) {
/*  46 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  48 */     String siteId = queryForm.getSiteId();
/*  49 */     if (siteId != null) {
/*  50 */       siteId = siteId.trim();
/*  51 */       if (siteId.length() == 0) siteId = null; 
/*     */     } 
/*  53 */     if (siteId != null) conditions.put(SystemLogQueryCondition.SITE_ID_EQ, siteId);
/*     */     
/*  55 */     String userId = queryForm.getUserId();
/*  56 */     if (userId != null) {
/*  57 */       userId = userId.trim();
/*  58 */       if (userId.length() == 0) userId = null; 
/*     */     } 
/*  60 */     if (userId != null) conditions.put(SystemLogQueryCondition.USER_ID_EQ, userId);
/*     */ 
/*     */     
/*  63 */     String userName = queryForm.getUserName();
/*  64 */     if (userName != null) {
/*  65 */       userName = userName.trim();
/*  66 */       if (userName.length() == 0) userName = null; 
/*     */     } 
/*  68 */     if (userName != null) conditions.put(SystemLogQueryCondition.USER_NAME_LIKE, userName);
/*     */     
/*  70 */     String target = queryForm.getTargetObject();
/*  71 */     if (target != null && target.trim().length() != 0) {
/*  72 */       conditions.put(SystemLogQueryCondition.TARGET_EQ, target);
/*     */     }
/*     */     
/*  75 */     if (queryForm.getActionDateFrom() != null && queryForm.getActionDateFrom().trim().length() != 0) {
/*  76 */       Date actionTimeFrom = ActionUtils2.getDateFromDisplayDate(queryForm.getActionDateFrom());
/*  77 */       if (actionTimeFrom != null) {
/*  78 */         conditions.put(SystemLogQueryCondition.ACTION_TIME_GT, actionTimeFrom);
/*     */       }
/*     */     } 
/*     */     
/*  82 */     if (queryForm.getActionDateTo() != null && queryForm.getActionDateTo().trim().length() != 0) {
/*  83 */       Date actionTimeTo = ActionUtils2.getDateFromDisplayDate(queryForm.getActionDateTo());
/*  84 */       if (actionTimeTo != null) {
/*  85 */         conditions.put(SystemLogQueryCondition.ACTION_TIME_LT, actionTimeTo);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return conditions;
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 103 */     List siteList = getAndCheckGrantedSiteList(request);
/*     */     
/* 105 */     SystemLogManager cm = ServiceLocator.getSystemLogManager(request);
/*     */     
/* 107 */     SystemLogQueryForm queryForm = (SystemLogQueryForm)form;
/* 108 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 110 */     if (request.getMethod().equals("POST")) {
/* 111 */       conditions = constructQueryMap(queryForm);
/*     */       
/* 113 */       if (queryForm.isFirstInit()) {
/*     */         
/* 115 */         queryForm.init(cm.getSystemLogListCount(conditions));
/*     */       }
/*     */       else {
/*     */         
/* 119 */         queryForm.init();
/*     */       } 
/* 121 */       int pageNo = queryForm.getPageNoAsInt();
/* 122 */       int pageSize = queryForm.getPageSizeAsInt();
/* 123 */       request.setAttribute("X_RESULTLIST", cm.getSystemLogList(conditions, pageNo, pageSize, SystemLogQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/*     */     }
/* 125 */     else if (queryForm.isFirstInit()) {
/*     */       
/* 127 */       queryForm.init(0);
/*     */     }
/*     */     else {
/*     */       
/* 131 */       queryForm.init();
/*     */     } 
/*     */ 
/*     */     
/* 135 */     String exportType = queryForm.getExportType();
/* 136 */     if (exportType != null && exportType.length() > 0) {
/*     */ 
/*     */       
/* 139 */       List data = cm.getSystemLogList(conditions, 0, -1, SystemLogQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 141 */       int index = SessionTempFile.createNewTempFile(request);
/* 142 */       String fileName = "systemLog";
/* 143 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 146 */               MessageResources messages = SystemLogAction.this.getResources(request);
/* 147 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.site"));
/* 148 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.userId"));
/* 149 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.userName"));
/* 150 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.updateTime"));
/* 151 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.object"));
/* 152 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.objectId"));
/* 153 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.action"));
/* 154 */               row.add(messages.getMessage(SystemLogAction.this.getLocale(request), "systemLog.tablehead.keyField"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 158 */               row.add(BeanUtils.getProperty(data, "site.name"));
/* 159 */               row.add(BeanUtils.getProperty(data, "user.loginName"));
/* 160 */               row.add(BeanUtils.getProperty(data, "user.name"));
/* 161 */               row.add(BeanUtils.getProperty(data, "actionTime"));
/* 162 */               row.add(BeanUtils.getProperty(data, "target"));
/* 163 */               row.add(BeanUtils.getProperty(data, "targetId"));
/* 164 */               row.add(BeanUtils.getProperty(data, "action"));
/* 165 */               row.add(BeanUtils.getProperty(data, "content"));
/*     */             }
/*     */           });
/* 168 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 171 */     request.setAttribute("X_SITELIST", siteList);
/*     */     
/* 173 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/SystemLogAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */