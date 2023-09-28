/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.BasicCustomer;
/*     */ import com.aof.model.product.query.BasicCustomerQueryCondition;
/*     */ import com.aof.model.product.query.BasicCustomerQueryOrder;
/*     */ import com.aof.service.Product.BasicCustomerManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.BasicCustomerQueryForm;
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
/*     */ public class BasicCustomerAction extends BaseAction2 {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  40 */     BasicCustomerManager basicCustomerManager = 
/*  41 */       ServiceLocator.getBasicCustomerManager(request);
/*  42 */     BasicCustomerQueryForm queryForm = (BasicCustomerQueryForm)form;
/*  43 */     Map conditions = getQueryConditions(queryForm);
/*  44 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  45 */     String exportType = queryForm.getExportType();
/*  46 */     if (exportType != null && exportType.length() > 0) {
/*  47 */       List data = basicCustomerManager.getList(conditions, 0, -1, null, 
/*  48 */           false);
/*  49 */       int index = SessionTempFile.createNewTempFile(request);
/*  50 */       String fileName = "BasicCustomer";
/*  51 */       String suffix = ExportUtil.export(
/*  52 */           exportType, 
/*  53 */           data, 
/*  54 */           request, 
/*  55 */           new FileOutputStream(SessionTempFile.getTempFile(index, 
/*  56 */               request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception
/*     */             {
/*  60 */               MessageResources messages = BasicCustomerAction.this.getResources(request);
/*  61 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  62 */                     "basic_customer.code"));
/*  63 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  64 */                     "basic_customer.name1"));
/*  65 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  66 */                     "basic_customer.name2"));
/*  67 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  68 */                     "basic_customer.address"));
/*  69 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  70 */                     "basic_customer.address2"));
/*  71 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  72 */                     "basic_customer.address3"));
/*  73 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  74 */                     "basic_customer.country"));
/*  75 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  76 */                     "basic_customer.city"));
/*  77 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  78 */                     "basic_customer.fax"));
/*  79 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  80 */                     "basic_customer.post_id"));
/*  81 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  82 */                     "basic_customer.currency_type"));
/*  83 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  84 */                     "basic_customer.contacts"));
/*  85 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  86 */                     "basic_customer.phone"));
/*  87 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  88 */                     "basic_customer.email"));
/*  89 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  90 */                     "basic_customer.product"));
/*  91 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  92 */                     "basic_customer.site"));
/*  93 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  94 */                     "basic_customer.domain"));
/*  95 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  96 */                     "basic_customer.remarks"));
/*  97 */               row.add(messages.getMessage(BasicCustomerAction.this.getLocale(request), 
/*  98 */                     "basic_customer.enabled"));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 107 */               row.add(BeanUtils.getProperty(data, "code"));
/* 108 */               row.add(BeanUtils.getProperty(data, "name1"));
/* 109 */               row.add(BeanUtils.getProperty(data, "name2"));
/* 110 */               row.add(BeanUtils.getProperty(data, "address"));
/* 111 */               row.add(BeanUtils.getProperty(data, "address2"));
/* 112 */               row.add(BeanUtils.getProperty(data, "address3"));
/* 113 */               row.add(BeanUtils.getProperty(data, "country"));
/* 114 */               row.add(BeanUtils.getProperty(data, "city"));
/* 115 */               row.add(BeanUtils.getProperty(data, "fax"));
/* 116 */               row.add(BeanUtils.getProperty(data, "postId"));
/* 117 */               row.add(BeanUtils.getProperty(data, "currencyType"));
/* 118 */               row.add(BeanUtils.getProperty(data, "contacts"));
/* 119 */               row.add(BeanUtils.getProperty(data, "phone"));
/* 120 */               row.add(BeanUtils.getProperty(data, "email"));
/* 121 */               row.add(BeanUtils.getProperty(data, "product"));
/* 122 */               row.add(BeanUtils.getProperty(data, "site"));
/* 123 */               row.add(BeanUtils.getProperty(data, "domain"));
/* 124 */               row.add(BeanUtils.getProperty(data, "remarks"));
/* 125 */               String locale = BasicCustomerAction.this.getCurrentUser(request).getLocale();
/* 126 */               if ("en".equals(locale)) {
/* 127 */                 row.add(BeanUtils.getProperty(data, 
/* 128 */                       "enabled.engShortDescription"));
/*     */               } else {
/* 130 */                 row.add(BeanUtils.getProperty(data, 
/* 131 */                       "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/* 135 */       return new ActionForward("download/" + index + "/" + 
/* 136 */           URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 139 */     if (queryForm.isFirstInit()) {
/* 140 */       queryForm.init(basicCustomerManager.getListCount(conditions));
/*     */     } else {
/* 142 */       queryForm.init();
/*     */     } 
/*     */     
/* 145 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 146 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 147 */     List<BasicCustomer> resultList = basicCustomerManager.getList(
/* 148 */         conditions, pageNo.intValue(), pageSize.intValue(), BasicCustomerQueryOrder.ID, 
/* 149 */         queryForm.isDescend());
/* 150 */     request.setAttribute("x_selType", Integer.valueOf(114));
/* 151 */     request.setAttribute("X_RESULTLIST", resultList);
/* 152 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(BasicCustomerQueryForm queryForm) {
/* 157 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 158 */     String queryStr = "";
/* 159 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/* 162 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/* 166 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 167 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */   private void getAllEnabledSiteList(HttpServletRequest request) {
/* 170 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/* 171 */     List siteList = siteManager.getAllEnabledSiteList();
/* 172 */     request.setAttribute("X_SITELIST", siteList);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 178 */     getBasic(request);
/* 179 */     getAllEnabledSiteList(request);
/* 180 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 186 */     BasicCustomerManager manager = 
/* 187 */       ServiceLocator.getBasicCustomerManager(request);
/* 188 */     BeanForm formBean = (BeanForm)form;
/* 189 */     BasicCustomer basicCustomer = new BasicCustomer();
/* 190 */     formBean.populateToBean(basicCustomer);
/* 191 */     basicCustomer = manager.insert(basicCustomer);
/* 192 */     request.setAttribute("X_OBJECT", basicCustomer);
/* 193 */     request.setAttribute("X_ROWPAGE", "basicCustomer/row.jsp");
/* 194 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 200 */     BasicCustomerManager manager = 
/* 201 */       ServiceLocator.getBasicCustomerManager(request);
/* 202 */     String idStr = request.getParameter("id");
/* 203 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 204 */     BasicCustomer basicCustomer = manager.getById(id);
/* 205 */     if (basicCustomer == null) {
/* 206 */       throw new ActionException("basicCustomer.notFound", id);
/*     */     }
/* 208 */     if (!isBack(request)) {
/* 209 */       BeanForm cityForm = (BeanForm)getForm("/updateBasicCustomer", 
/* 210 */           request);
/* 211 */       cityForm.populate(basicCustomer, "to_form");
/*     */     } 
/* 213 */     request.setAttribute("X_OBJECT", basicCustomer);
/* 214 */     getBasic(request);
/* 215 */     getAllEnabledSiteList(request);
/* 216 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 222 */     BasicCustomerManager manager = 
/* 223 */       ServiceLocator.getBasicCustomerManager(request);
/* 224 */     String idStr = request.getParameter("id");
/* 225 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 226 */     BeanForm formBean = (BeanForm)form;
/* 227 */     BasicCustomer basicCustomer = manager.getById(id);
/* 228 */     formBean.populateToBean(basicCustomer, request);
/* 229 */     basicCustomer = manager.update(basicCustomer);
/* 230 */     request.setAttribute("X_OBJECT", basicCustomer);
/* 231 */     request.setAttribute("X_ROWPAGE", "basicCustomer/row.jsp");
/* 232 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 238 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 239 */     BasicCustomerManager basicCustomerManager = 
/* 240 */       ServiceLocator.getBasicCustomerManager(request);
/* 241 */     basicCustomerManager.remove(basicCustomerManager.getById(id));
/* 242 */     return new ActionForward("listBasicCustomer.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 248 */     BasicCustomerManager basicCustomerManager = 
/* 249 */       ServiceLocator.getBasicCustomerManager(request);
/* 250 */     BasicCustomerQueryForm queryForm = (BasicCustomerQueryForm)form;
/* 251 */     Map conditions = getQueryConditions(queryForm);
/* 252 */     getConditionAndOrder(queryForm, conditions, request);
/* 253 */     conditions.put(BasicCustomerQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/* 254 */     if (queryForm.isFirstInit()) {
/* 255 */       queryForm.init(basicCustomerManager.getListCount(conditions));
/*     */     } else {
/* 257 */       queryForm.init();
/*     */     } 
/* 259 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 260 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 261 */     List<BasicCustomer> resultList = basicCustomerManager.getList(
/* 262 */         conditions, pageNo.intValue(), pageSize.intValue(), BasicCustomerQueryOrder.ID, 
/* 263 */         false);
/* 264 */     request.setAttribute("X_RESULTLIST", resultList);
/* 265 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward AJAXCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 272 */     String code = request.getParameter("code");
/* 273 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 274 */     if (code != null && code.trim().length() > 0) {
/* 275 */       conditions.put(BasicCustomerQueryCondition.CODE_EQ, code);
/*     */     }
/* 277 */     BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
/* 278 */     int result = basicCustomerManager.getListCount(conditions);
/* 279 */     response.setContentType("text/html;charset=utf-8");
/* 281 */     response.getWriter().print(result);
/*     */     
/* 283 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward AJAXEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 289 */     String code = request.getParameter("code");
/* 290 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 291 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 292 */     if (code != null && code.trim().length() > 0) {
/* 293 */       conditions.put(BasicCustomerQueryCondition.CODE_EQ, code);
/* 294 */       conditions.put(BasicCustomerQueryCondition.EDITID_NE, id);
/*     */     } 
/* 296 */     BasicCustomerManager basicCustomerManager = ServiceLocator.getBasicCustomerManager(request);
/* 297 */     int result = basicCustomerManager.getListCount(conditions);
/* 298 */     response.setContentType("text/html;charset=utf-8");
/* 300 */     response.getWriter().print(result);
/*     */     
/* 302 */     return null;
/*     */   }
/*     */ }