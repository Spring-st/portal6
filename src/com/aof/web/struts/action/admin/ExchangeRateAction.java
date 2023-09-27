/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryCondition;
/*     */ import com.aof.model.admin.query.ExchangeRateQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.service.admin.ExchangeRateManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.ExchangeRateQueryForm;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
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
/*     */ public class ExchangeRateAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private Map constructQueryMap(ExchangeRateQueryForm queryForm) {
/*  58 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  60 */     String id = queryForm.getSiteId();
/*  61 */     if (id != null) {
/*  62 */       id = id.trim();
/*  63 */       if (id.length() == 0) id = null; 
/*     */     } 
/*  65 */     if (id != null) conditions.put(ExchangeRateQueryCondition.SITE_ID_EQ, id);
/*     */     
/*  67 */     String code = queryForm.getCurrencyCode();
/*  68 */     if (code != null) {
/*  69 */       code = code.trim();
/*  70 */       if (code.length() == 0) code = null; 
/*     */     } 
/*  72 */     if (code != null) conditions.put(ExchangeRateQueryCondition.CURRENCY_CODE_LIKE, code);
/*     */     
/*  74 */     conditions.put(ExchangeRateQueryCondition.CURRENCY_STATUS_EQ, EnabledDisabled.ENABLED.getEnumCode().toString());
/*     */     
/*  76 */     return conditions;
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
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  89 */     ExchangeRateManager em = ServiceLocator.getExchangeRateManager(request);
/*  90 */     SiteManager sm = ServiceLocator.getSiteManager(request);
/*  91 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*  92 */     request.setAttribute("X_SITELIST", siteList);
/*  93 */     ExchangeRateQueryForm queryForm = (ExchangeRateQueryForm)form;
/*  94 */     if (queryForm.getSiteId() == null) {
/*  95 */       if (siteList.size() > 0) {
/*  96 */         queryForm.setSiteId(((Site)siteList.get(0)).getId().toString());
/*  97 */         request.setAttribute("X_BASECURRENCY", ((Site)siteList.get(0)).getBaseCurrency().getCode());
/*     */       } 
/*     */     } else {
/* 100 */       request.setAttribute("X_BASECURRENCY", sm.getSite(new Integer(queryForm.getSiteId())).getBaseCurrency().getCode());
/*     */     } 
/* 102 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 104 */     String exportType = queryForm.getExportType();
/* 105 */     if (exportType != null && exportType.length() > 0) {
/* 106 */       List data = em.getExchangeRateList(conditions, 0, -1, ExchangeRateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 108 */       int index = SessionTempFile.createNewTempFile(request);
/* 109 */       String fileName = "exchangeRate";
/* 110 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 113 */               MessageResources messages = ExchangeRateAction.this.getResources(request);
/* 114 */               row.add(messages.getMessage(ExchangeRateAction.this.getLocale(request), "exchangeRate.tablehead.code"));
/* 115 */               row.add(messages.getMessage(ExchangeRateAction.this.getLocale(request), "exchangeRate.tablehead.name"));
/* 116 */               row.add(messages.getMessage(ExchangeRateAction.this.getLocale(request), "exchangeRate.tablehead.exchangeRate"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 120 */               row.add(BeanUtils.getProperty(data, "currency.code"));
/* 121 */               row.add(BeanUtils.getProperty(data, "currency.name"));
/* 122 */               row.add(BeanUtils.getProperty(data, "exchangeRate"));
/*     */             }
/*     */           });
/* 125 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 128 */     if (queryForm.isFirstInit()) {
/*     */       
/* 130 */       queryForm.init(em.getExchangeRateListCount(conditions));
/*     */     }
/*     */     else {
/*     */       
/* 134 */       queryForm.init();
/*     */     } 
/* 136 */     int pageNo = queryForm.getPageNoAsInt();
/* 137 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/* 139 */     request.setAttribute("X_RESULTLIST", em.getExchangeRateList(conditions, pageNo, pageSize, ExchangeRateQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 140 */     request.setAttribute("X_STARTSEQ", new Integer(pageNo * pageSize + 1));
/* 141 */     return mapping.findForward("page");
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
/* 154 */     if (!isBack(request)) {
/* 155 */       String siteId = request.getParameter("siteId");
/* 156 */       SiteManager sm = ServiceLocator.getSiteManager(request);
/* 157 */       Site site = sm.getSite(new Integer(siteId));
/* 158 */       checkSite(site, request);
/* 159 */       ExchangeRate e = new ExchangeRate();
/* 160 */       e.setSite(site);
/* 161 */       e.setExchangeRate(new BigDecimal(0.0D));
/* 162 */       BeanForm exchangeRateForm = (BeanForm)getForm("/insertExchangeRate", request);
/* 163 */       exchangeRateForm.populate(e, "to_form");
/*     */     } 
/* 165 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 166 */     request.setAttribute("X_CURRENCYLIST", cm.getAllEnabledCurrencyList());
/* 167 */     return mapping.findForward("page");
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
/* 180 */     Site site = getAndCheckSite(request);
/* 181 */     ExchangeRateManager em = ServiceLocator.getExchangeRateManager(request);
/* 182 */     BeanForm exchangeRateForm = (BeanForm)form;
/* 183 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 184 */     ExchangeRate e = new ExchangeRate();
/* 185 */     exchangeRateForm.populate(e, "to_bean");
/* 186 */     e.setSite(site);
/* 187 */     e.setCurrency(cm.getCurrency(e.getCurrency().getCode()));
/* 188 */     if (em.getExchangeRate(e.getCurrency(), e.getSite()) != null) throw new ActionException("exchangeRate.error.exist"); 
/* 189 */     request.setAttribute("X_OBJECT", em.saveExchangeRate(e));
/* 190 */     request.setAttribute("X_ROWPAGE", "exchangeRate/row.jsp");
/*     */     
/* 192 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/* 196 */     return getAndCheckSite("site_id", request);
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 208 */     if (!isBack(request)) {
/* 209 */       ExchangeRate e = getExchangeRateFromRequest(request);
/* 210 */       checkSite(e.getSite(), request);
/* 211 */       BeanForm exchangeRateForm = (BeanForm)getForm("/updateExchangeRate", request);
/* 212 */       exchangeRateForm.populate(e, "to_form");
/*     */     } 
/* 214 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private ExchangeRate getExchangeRateFromRequest(HttpServletRequest request) {
/* 218 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 219 */     ExchangeRateManager em = ServiceLocator.getExchangeRateManager(request);
/* 220 */     ExchangeRate exchangeRate = em.getExchangeRate(id);
/* 221 */     if (exchangeRate == null)
/* 222 */       throw new ActionException("exchangeRate.error.notFound"); 
/* 223 */     return exchangeRate;
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
/* 236 */     Site site = getAndCheckSite(request);
/* 237 */     BeanForm exchangeRateForm = (BeanForm)form;
/* 238 */     ExchangeRate e = new ExchangeRate();
/* 239 */     exchangeRateForm.populate(e, "to_bean");
/* 240 */     e.setSite(site);
/* 241 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 242 */     e.setCurrency(cm.getCurrency(e.getCurrency().getCode()));
/* 243 */     ExchangeRateManager em = ServiceLocator.getExchangeRateManager(request);
/* 244 */     request.setAttribute("X_OBJECT", em.saveExchangeRate(e));
/* 245 */     request.setAttribute("X_ROWPAGE", "exchangeRate/row.jsp");
/* 246 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/ExchangeRateAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */