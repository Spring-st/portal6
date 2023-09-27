/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.CurrencyQueryCondition;
/*     */ import com.aof.model.admin.query.CurrencyQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.CurrencyQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
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
/*     */ public class CurrencyAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map constructQueryMap(CurrencyQueryForm queryForm) {
/*  55 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  57 */     String code = queryForm.getCode();
/*  58 */     if (code != null) {
/*  59 */       code = code.trim();
/*  60 */       if (code.length() == 0) code = null; 
/*     */     } 
/*  62 */     if (code != null) conditions.put(CurrencyQueryCondition.CODE_LIKE, code);
/*     */     
/*  64 */     String name = queryForm.getName();
/*  65 */     if (name != null) {
/*  66 */       name = name.trim();
/*  67 */       if (name.length() == 0) name = null; 
/*     */     } 
/*  69 */     if (name != null) conditions.put(CurrencyQueryCondition.NAME_LIKE, name);
/*     */     
/*  71 */     String status = queryForm.getStatus();
/*  72 */     if (status != null && status.trim().length() != 0) {
/*  73 */       if (status.equals(EnabledDisabled.ENABLED.getEnumCode().toString()))
/*  74 */         conditions.put(CurrencyQueryCondition.STATUS_EQ, EnabledDisabled.ENABLED.getEnumCode()); 
/*  75 */       if (status.equals(EnabledDisabled.DISABLED.getEnumCode().toString())) {
/*  76 */         conditions.put(CurrencyQueryCondition.STATUS_EQ, EnabledDisabled.DISABLED.getEnumCode());
/*     */       }
/*     */     } 
/*     */     
/*  80 */     return conditions;
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
/*  93 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/*     */     
/*  95 */     CurrencyQueryForm queryForm = (CurrencyQueryForm)form;
/*  96 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*     */       
/*  98 */       queryForm.setStatus(EnabledDisabled.ENABLED.getEnumCode().toString());
/*  99 */       queryForm.setOrder(CurrencyQueryOrder.CODE.getName());
/* 100 */       queryForm.setDescend(false);
/*     */     } 
/* 102 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 104 */     String exportType = queryForm.getExportType();
/* 105 */     if (exportType != null && exportType.length() > 0) {
/* 106 */       List data = cm.getCurrencyList(conditions, 0, -1, CurrencyQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 108 */       int index = SessionTempFile.createNewTempFile(request);
/* 109 */       String fileName = "currency";
/* 110 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 113 */               MessageResources messages = CurrencyAction.this.getResources(request);
/* 114 */               row.add(messages.getMessage(CurrencyAction.this.getLocale(request), "currency.code"));
/* 115 */               row.add(messages.getMessage(CurrencyAction.this.getLocale(request), "currency.description"));
/* 116 */               row.add(messages.getMessage(CurrencyAction.this.getLocale(request), "currency.status"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/* 120 */               row.add(BeanUtils.getProperty(data, "code"));
/* 121 */               row.add(BeanUtils.getProperty(data, "name"));
/* 122 */               String locale = CurrencyAction.this.getCurrentUser(request).getLocale();
/* 123 */               if ("en".equals(locale)) {
/* 124 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/* 126 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 130 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 133 */     if (queryForm.isFirstInit()) {
/*     */       
/* 135 */       queryForm.init(cm.getCurrencyListCount(conditions));
/*     */     }
/*     */     else {
/*     */       
/* 139 */       queryForm.init();
/*     */     } 
/* 141 */     int pageNo = queryForm.getPageNoAsInt();
/* 142 */     int pageSize = queryForm.getPageSizeAsInt();
/* 143 */     request.setAttribute("X_RESULTLIST", cm.getCurrencyList(conditions, pageNo, pageSize, CurrencyQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 144 */     request.setAttribute("X_STARTSEQ", new Integer(pageNo * pageSize + 1));
/* 145 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 146 */     return mapping.findForward("page");
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
/* 159 */     if (!isBack(request)) {
/* 160 */       Currency c = new Currency();
/* 161 */       BeanForm currencyForm = (BeanForm)getForm("/insertCurrency", request);
/* 162 */       currencyForm.populate(c, "to_form");
/*     */     } 
/* 164 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 165 */     return mapping.findForward("page");
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
/* 178 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 179 */     BeanForm currencyForm = (BeanForm)form;
/*     */     
/* 181 */     Currency c = new Currency();
/* 182 */     currencyForm.populate(c, "to_bean");
/* 183 */     if (cm.getCurrency(c.getCode()) != null) throw new ActionException("currency.error.exist"); 
/* 184 */     request.setAttribute("X_OBJECT", cm.insertCurrency(c));
/* 185 */     request.setAttribute("X_ROWPAGE", "currency/row.jsp");
/* 186 */     return mapping.findForward("success");
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
/* 199 */     if (!isBack(request)) {
/* 200 */       String code = request.getParameter("code");
/* 201 */       String seq = request.getParameter("seq");
/* 202 */       CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 203 */       Currency c = cm.getCurrency(code);
/* 204 */       if (c == null) throw new ActionException("currency.error.notFound", code); 
/* 205 */       BeanForm currencyForm = (BeanForm)getForm("/updateCurrency", request);
/* 206 */       currencyForm.populate(c, "to_form");
/* 207 */       request.setAttribute("seq", seq);
/*     */     } 
/* 209 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 210 */     return mapping.findForward("page");
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
/* 223 */     BeanForm currencyForm = (BeanForm)form;
/* 224 */     String seq = request.getParameter("seq");
/* 225 */     Currency c = new Currency();
/* 226 */     currencyForm.populate(c, "to_bean");
/* 227 */     CurrencyManager cm = ServiceLocator.getCurrencyManager(request);
/* 228 */     request.setAttribute("X_OBJECT", cm.updateCurrency(c));
/* 229 */     request.setAttribute("X_ROWPAGE", "currency/row.jsp");
/* 230 */     if (!seq.equals(""))
/* 231 */       request.setAttribute("X_STARTSEQ", new Integer(seq)); 
/* 232 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/CurrencyAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */