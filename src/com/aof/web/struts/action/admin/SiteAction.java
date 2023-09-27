/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.SiteQueryCondition;
/*     */ import com.aof.model.admin.query.SiteQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.SiteQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletOutputStream;
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
/*     */ public class SiteAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  63 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/*     */     
/*  65 */     SiteQueryForm queryForm = (SiteQueryForm)form;
/*  66 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */     
/*  68 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  70 */     String exportType = queryForm.getExportType();
/*  71 */     if (exportType != null && exportType.length() > 0) {
/*  72 */       List data = sm.getSiteList(conditions, 0, -1, SiteQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  73 */       int index = SessionTempFile.createNewTempFile(request);
/*  74 */       String fileName = "site";
/*  75 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  78 */               MessageResources messages = SiteAction.this.getResources(request);
/*  79 */               row.add(messages.getMessage(SiteAction.this.getLocale(request), "site.name"));
/*  80 */               row.add(messages.getMessage(SiteAction.this.getLocale(request), "site.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  84 */               row.add(BeanUtils.getProperty(data, "name"));
/*  85 */               String locale = SiteAction.this.getCurrentUser(request).getLocale();
/*  86 */               if ("en".equals(locale)) {
/*  87 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/*  89 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/*  93 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  96 */     if (queryForm.isFirstInit()) {
/*  97 */       queryForm.init(sm.getSiteListCount(conditions));
/*     */     } else {
/*  99 */       queryForm.init();
/*     */     } 
/*     */     
/* 102 */     request.setAttribute("X_RESULTLIST", sm.getSiteList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), SiteQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 103 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(SiteQueryForm queryForm) {
/* 107 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 109 */     String name = queryForm.getName();
/* 110 */     if (name != null) {
/* 111 */       name = name.trim();
/* 112 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 114 */     if (name != null) conditions.put(SiteQueryCondition.NAME_LIKE, name);
/*     */     
/* 116 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 117 */     if (enabled != null) {
/* 118 */       conditions.put(SiteQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*     */     
/* 121 */     return conditions;
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 134 */     if (!isBack(request)) {
/* 135 */       Site s = new Site();
/* 136 */       BeanForm siteForm = (BeanForm)getForm("/insertSite", request);
/* 137 */       siteForm.populateToForm(s);
/*     */     } 
/* 139 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 140 */     request.setAttribute("X_CURRENCYLIST", cm.getAllEnabledCurrencyList());
/* 141 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 142 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 143 */     request.setAttribute("X_COUNTRYLIST", ServiceLocator.getCountryManager(request).listEnabledCountryProvinceCity());
/* 144 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 157 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 158 */     BeanForm siteForm = (BeanForm)form;
/* 159 */     Site s = new Site();
/* 160 */     siteForm.populateToBean(s);
/* 161 */     FormFile logo = (FormFile)siteForm.get("logo");
/* 162 */     int logoSize = 0;
/* 163 */     if (logo != null) logoSize = logo.getFileSize(); 
/* 164 */     if (logoSize > 0) {
/* 165 */       request.setAttribute("X_OBJECT", sm.saveSite(s, logo.getInputStream()));
/*     */     } else {
/* 167 */       request.setAttribute("X_OBJECT", sm.saveSite(s));
/*     */     } 
/* 169 */     request.setAttribute("X_ROWPAGE", "site/row.jsp");
/*     */     
/* 171 */     return mapping.findForward("success");
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
/* 184 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 185 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 186 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 187 */     if (!isBack(request)) {
/* 188 */       Site s = sm.getSite(id);
/* 189 */       if (s == null) throw new ActionException("site.notFound", id); 
/* 190 */       BeanForm siteForm = (BeanForm)getForm("/updateSite", request);
/* 191 */       siteForm.populateToForm(s);
/* 192 */       request.setAttribute("X_CURRENCYLIST", cm.getAllEnabledCurrencyListAndIncludeThis(siteForm.getString("baseCurrency_code")));
/*     */     } else {
/* 194 */       request.setAttribute("X_CURRENCYLIST", cm.getAllEnabledCurrencyListAndIncludeThis(request.getParameter("baseCurrency_code")));
/*     */     } 
/* 196 */     request.setAttribute("X_SITEHASLOGO", Boolean.valueOf(sm.isSiteHasLogo(id)));
/* 197 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 198 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 199 */     request.setAttribute("X_COUNTRYLIST", ServiceLocator.getCountryManager(request).listEnabledCountryProvinceCity());
/* 200 */     return mapping.findForward("page");
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
/* 213 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 214 */     BeanForm siteForm = (BeanForm)form;
/* 215 */     Integer id = ActionUtils.parseInt((String)siteForm.get("id"));
/* 216 */     Site s = sm.getSite(id);
/* 217 */     if (s == null) throw new ActionException("site.notFound", id); 
/* 218 */     siteForm.populateToBean(s);
/* 219 */     FormFile logo = (FormFile)siteForm.get("logo");
/* 220 */     int logoSize = 0;
/* 221 */     if (logo != null) logoSize = logo.getFileSize(); 
/* 222 */     if (logoSize > 0) {
/* 223 */       request.setAttribute("X_OBJECT", sm.saveSite(s, logo.getInputStream()));
/*     */     } else {
/* 225 */       request.setAttribute("X_OBJECT", sm.saveSite(s));
/*     */     } 
/* 227 */     request.setAttribute("X_ROWPAGE", "site/row.jsp");
/*     */     
/* 229 */     return mapping.findForward("success");
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
/*     */   public ActionForward showLogo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 242 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/* 243 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 244 */     InputStream in = sm.getSiteLogo(id);
/* 245 */     if (in != null) {
/* 246 */       ServletOutputStream servletOutputStream = response.getOutputStream();
/*     */       int buf;
/* 248 */       while ((buf = in.read()) != -1) {
/* 249 */         servletOutputStream.write(buf);
/*     */       }
/* 251 */       servletOutputStream.close();
/* 252 */       in.close();
/*     */     } 
/*     */     
/* 255 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/SiteAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */