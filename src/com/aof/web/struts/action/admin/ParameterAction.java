/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.model.admin.GlobalMailReminder;
/*     */ import com.aof.model.admin.GlobalParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.SiteMailReminder;
/*     */ import com.aof.model.metadata.ExportFileType;
/*     */ import com.aof.model.metadata.GlobalMailReminderType;
/*     */ import com.aof.model.metadata.MetadataType;
/*     */ import com.aof.model.metadata.SiteMailReminderType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.DataTransferManager;
/*     */ import com.aof.service.admin.ParameterManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.converter.Converter;
/*     */ import com.shcnc.struts.form.converter.ConverterLocator;
/*     */ import com.shcnc.struts.form.converterLocators.DefaultConvertLocator;
/*     */ import com.shcnc.struts.form.converters.DateConverter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
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
/*     */ public class ParameterAction
/*     */   extends BaseAction2
/*     */ {
/*  54 */   protected static DateConverter timeConverter = new DateConverter(); static {
/*  55 */     timeConverter.setFormat("HH:mm");
/*     */   }
/*     */   
/*  58 */   private static ConverterLocator dataTransferConverterLocator = new ConverterLocator()
/*     */     {
/*     */       public Converter getConverter(Class clazz) {
/*  61 */         return DefaultConvertLocator.getInstance().getConverter(clazz);
/*     */       }
/*     */       
/*     */       public Converter getConverter(String convertId) {
/*  65 */         return null;
/*     */       }
/*     */       
/*     */       public Converter getConverter(Class clazz, String propName) {
/*  69 */         if (propName.endsWith("Time")) {
/*  70 */           return (Converter)ParameterAction.timeConverter;
/*     */         }
/*  72 */         return null;
/*     */       }
/*     */     };
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
/*     */   public ActionForward editGlobal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  88 */     ParameterManager gm = ServiceLocator.getParameterManager(request);
/*  89 */     GlobalParameter globalParameter = gm.getGlobalParameter();
/*  90 */     request.setAttribute("x_globalParameter", globalParameter);
/*  91 */     if (!isBack(request)) {
/*  92 */       BeanForm globalParameterForm = (BeanForm)getForm("/updateGlobalParameter", request);
/*  93 */       globalParameterForm.populateToForm(globalParameter);
/*     */     } 
/*  95 */     putGlobalMailReminderToRequest(gm, request);
/*  96 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void putGlobalMailReminderToRequest(ParameterManager gm, HttpServletRequest request) {
/* 100 */     List reminderList = PersistentEnum.getEnumList(MetadataType.GLOBAL_MAIL_REMINDER_TYPE.getDetailClass());
/* 101 */     List dbReminderList = gm.getGlobalMailReminderList();
/* 102 */     List<GlobalMailReminder> result = new ArrayList();
/* 103 */     for (Iterator<GlobalMailReminderType> itor = reminderList.iterator(); itor.hasNext(); ) {
/* 104 */       GlobalMailReminderType type = itor.next();
/* 105 */       result.add(findReminderFromList(dbReminderList, type));
/*     */     } 
/* 107 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 108 */     request.setAttribute("x_mailReminderList", result);
/*     */   }
/*     */   
/*     */   private GlobalMailReminder findReminderFromList(List<GlobalMailReminder> dbReminderList, GlobalMailReminderType type) {
/* 112 */     for (int index = 0; index < dbReminderList.size(); index++) {
/* 113 */       GlobalMailReminder globalMailReminder = dbReminderList.get(index);
/* 114 */       if (globalMailReminder.getType().equals(type)) {
/* 115 */         dbReminderList.remove(index);
/* 116 */         return globalMailReminder;
/*     */       } 
/*     */     } 
/* 119 */     GlobalMailReminder reminder = new GlobalMailReminder();
/* 120 */     reminder.setType(type);
/* 121 */     return reminder;
/*     */   }
/*     */   
/*     */   private SiteMailReminder findReminderFromList(List<SiteMailReminder> dbReminderList, SiteMailReminderType type) {
/* 125 */     for (int index = 0; index < dbReminderList.size(); index++) {
/* 126 */       SiteMailReminder siteMailReminder = dbReminderList.get(index);
/* 127 */       if (siteMailReminder.getType().equals(type)) {
/* 128 */         dbReminderList.remove(index);
/* 129 */         return siteMailReminder;
/*     */       } 
/*     */     } 
/* 132 */     SiteMailReminder reminder = new SiteMailReminder();
/* 133 */     reminder.setType(type);
/* 134 */     return reminder;
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
/*     */   public ActionForward updateGlobal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 147 */     BeanForm globalParameterForm = (BeanForm)form;
/* 148 */     GlobalParameter globalParameter = new GlobalParameter();
/* 149 */     globalParameterForm.populateToBean(globalParameter, request);
/*     */     
/* 151 */     List reminderList = getReminderListFromRequest(request, (Site)null);
/*     */     
/* 153 */     ParameterManager pm = ServiceLocator.getParameterManager(request);
/* 154 */     pm.updateGlobalParameter(globalParameter, reminderList);
/*     */     
/* 156 */     postGlobalMessage("globalParameter.updateSuccess", request.getSession());
/*     */     
/* 158 */     ActionForward forward = new ActionForward();
/* 159 */     forward.setRedirect(true);
/* 160 */     forward.setPath("editGlobalParameter.do");
/* 161 */     return forward;
/*     */   }
/*     */   
/*     */   private List getReminderListFromRequest(HttpServletRequest request, Site site) {
/* 165 */     String[] typeId = request.getParameterValues("type");
/* 166 */     String[] dueDay = request.getParameterValues("dueDay");
/* 167 */     String[] intervalDay = request.getParameterValues("intervalDay");
/* 168 */     String[] maxTime = request.getParameterValues("maxTime");
/*     */     
/* 170 */     List reminderList = new ArrayList();
/* 171 */     for (int index = 0; index < typeId.length; index++) {
/* 172 */       if (isGlobal(request)) {
/* 173 */         GlobalMailReminder reminder = new GlobalMailReminder();
/* 174 */         reminder.setType((GlobalMailReminderType)PersistentEnum.fromEnumCode(GlobalMailReminderType.class, new Integer(typeId[index])));
/* 175 */         reminder.setDueDay(ActionUtils2.parseInt(dueDay[index]).intValue());
/* 176 */         reminder.setIntervalDay(ActionUtils2.parseInt(intervalDay[index]).intValue());
/* 177 */         reminder.setMaxTime(ActionUtils2.parseInt(maxTime[index]).intValue());
/* 178 */         reminderList.add(reminder);
/*     */       } else {
/* 180 */         SiteMailReminder reminder = new SiteMailReminder();
/* 181 */         reminder.setSite(site);
/* 182 */         reminder.setType((SiteMailReminderType)PersistentEnum.fromEnumCode(SiteMailReminderType.class, new Integer(typeId[index])));
/* 183 */         reminder.setDueDay(ActionUtils2.parseInt(dueDay[index]).intValue());
/* 184 */         reminder.setIntervalDay(ActionUtils2.parseInt(intervalDay[index]).intValue());
/* 185 */         reminder.setMaxTime(ActionUtils2.parseInt(maxTime[index]).intValue());
/* 186 */         reminderList.add(reminder);
/*     */       } 
/*     */     } 
/* 189 */     return reminderList;
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
/*     */   public ActionForward editSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 202 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/* 203 */     Site site = getSiteFromRequest(request);
/* 204 */     if (site != null) {
/* 205 */       checkSite(site, request);
/*     */     } else {
/* 207 */       site = siteList.get(0);
/*     */     } 
/* 209 */     request.setAttribute("x_siteList", siteList);
/*     */     
/* 211 */     ParameterManager gm = ServiceLocator.getParameterManager(request);
/* 212 */     DataTransferParameter dataTransferParameter = gm.getDataTransferParameter(site);
/*     */     
/* 214 */     request.setAttribute("x_dataTransferParameter", dataTransferParameter);
/* 215 */     if (!isBack(request)) {
/* 216 */       BeanForm siteParameterForm = (BeanForm)getForm("/updateSiteParameter", request);
/* 217 */       siteParameterForm.setConverterLocator(dataTransferConverterLocator);
/* 218 */       siteParameterForm.populateToForm(dataTransferParameter);
/*     */     } 
/* 220 */     putSiteMailReminderToRequest(site, gm, request);
/* 221 */     request.setAttribute("x_exportFileTypeList", PersistentEnum.getEnumList(ExportFileType.class));
/* 222 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequest(HttpServletRequest request) {
/* 226 */     Integer id = ActionUtils2.parseInt(request.getParameter("site_id"));
/* 227 */     if (id != null) {
/* 228 */       SiteManager sm = ServiceLocator.getSiteManager(request);
/* 229 */       return sm.getSite(id);
/*     */     } 
/* 231 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void putSiteMailReminderToRequest(Site site, ParameterManager gm, HttpServletRequest request) {
/* 236 */     List reminderList = PersistentEnum.getEnumList(MetadataType.SITE_MAIL_REMINDER_TYPE.getDetailClass());
/* 237 */     List dbReminderList = gm.getSiteMailReminderList(site);
/* 238 */     List<SiteMailReminder> result = new ArrayList();
/* 239 */     for (Iterator<SiteMailReminderType> itor = reminderList.iterator(); itor.hasNext(); ) {
/* 240 */       SiteMailReminderType type = itor.next();
/* 241 */       result.add(findReminderFromList(dbReminderList, type));
/*     */     } 
/* 243 */     request.setAttribute("x_mailReminderList", result);
/*     */   }
/*     */   
/*     */   public ActionForward updateSite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 247 */     BeanForm siteParameterForm = (BeanForm)form;
/* 248 */     Site site = getSiteFromRequest(request);
/* 249 */     checkSite(site, request);
/*     */     
/* 251 */     ParameterManager gm = ServiceLocator.getParameterManager(request);
/* 252 */     DataTransferParameter dataTransferParameter = gm.getDataTransferParameter(site);
/*     */     
/* 254 */     siteParameterForm.setConverterLocator(dataTransferConverterLocator);
/* 255 */     siteParameterForm.populateToBean(dataTransferParameter, request);
/*     */     
/* 257 */     List reminderList = getReminderListFromRequest(request, site);
/*     */     
/* 259 */     ParameterManager pm = ServiceLocator.getParameterManager(request);
/* 260 */     pm.updateSiteParameter(dataTransferParameter, reminderList);
/*     */     
/* 262 */     postGlobalMessage("siteParameter.updateSuccess", request.getSession());
/*     */     
/* 264 */     ActionForward forward = new ActionForward();
/* 265 */     forward.setRedirect(true);
/* 266 */     forward.setPath("editSiteParameter.do?site_id=" + site.getId());
/* 267 */     return forward;
/*     */   }
/*     */   
/*     */   public ActionForward startJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 271 */     Site site = getSiteFromRequest(request);
/* 272 */     checkSite(site, request);
/*     */     
/* 274 */     ParameterManager gm = ServiceLocator.getParameterManager(request);
/* 275 */     DataTransferParameter dataTransferParameter = gm.getDataTransferParameter(site);
/*     */     
/* 277 */     DataTransferManager dm = ServiceLocator.getDataTransferManager(request);
/* 278 */     dm.startJob(dataTransferParameter);
/*     */     
/* 280 */     postGlobalMessage("dataTransferParameter.startJobSuccess", request.getSession());
/*     */     
/* 282 */     ActionForward forward = new ActionForward();
/* 283 */     forward.setRedirect(true);
/* 284 */     forward.setPath("editSiteParameter.do?site_id=" + site.getId());
/* 285 */     return forward;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/ParameterAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */