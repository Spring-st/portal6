/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseType;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import com.aof.service.admin.ExpenseSubCategoryManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.ExpenseCategoryQueryForm;
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
/*     */
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
/*     */ public class ExpenseCategoryAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  63 */     ExpenseCategoryManager fm = ServiceLocator.getExpenseCategoryManager(request);
/*     */     
/*  65 */     List<Site> siteList = getAndCheckGrantedSiteList(request);
/*     */     
/*  67 */     ExpenseCategoryQueryForm queryForm = (ExpenseCategoryQueryForm)form;
/*  68 */     if (queryForm.getSite_id() == null && 
/*  69 */       siteList.size() > 0) {
/*  70 */       queryForm.setSite_id(((Site)siteList.get(0)).getId().toString());
/*     */     }
/*     */ 
/*     */     
/*  74 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  76 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  77 */       queryForm.setOrder(ExpenseCategoryQueryOrder.DESCRIPTION.getName());
/*  78 */       queryForm.setDescend(false);
/*     */     }
/*  80 */     else if (ExpenseCategoryQueryOrder.getEnum(queryForm.getOrder()) == null) {
/*     */       
/*  82 */       throw new RuntimeException("order not found");
/*     */     } 
/*     */     
/*  85 */     String exportType = queryForm.getExportType();
/*  86 */     if (StringUtils.isNotEmpty(exportType)) {
/*  87 */       List data = fm.getExpenseCategoryList(conditions, 0, -1, ExpenseCategoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  89 */       int index = SessionTempFile.createNewTempFile(request);
/*  90 */       String fileName = "expenseCategory";
/*  91 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  94 */               MessageResources messages = ExpenseCategoryAction.this.getResources(request);
/*  95 */               row.add(messages.getMessage(ExpenseCategoryAction.this.getLocale(request), "expenseCategory.description"));
/*  96 */               row.add(messages.getMessage(ExpenseCategoryAction.this.getLocale(request), "expenseCategory.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 100 */               row.add(BeanHelper.getBeanPropertyValue(data, "description"));
/* 101 */               if (ExpenseCategoryAction.this.isEnglish(request)) {
/* 102 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.engShortDescription"));
/*     */               } else {
/* 104 */                 row.add(BeanHelper.getBeanPropertyValue(data, "enabled.chnShortDescription"));
/*     */               }  }
/*     */           });
/* 107 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 110 */     if (queryForm.isFirstInit()) {
/* 111 */       queryForm.init(fm.getExpenseCategoryListCount(conditions));
/*     */     } else {
/* 113 */       queryForm.init();
/*     */     } 
/*     */     
/* 116 */     List result = fm.getExpenseCategoryList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 117 */         ExpenseCategoryQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 118 */     request.setAttribute("X_RESULTLIST", result);
/* 119 */     request.setAttribute("x_siteList", siteList);
/* 120 */     putEnumListToRequest(request);
/* 121 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ExpenseCategoryQueryForm queryForm) {
/* 125 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 127 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 128 */     if (id != null) {
/* 129 */       conditions.put(ExpenseCategoryQueryCondition.ID_EQ, id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     Integer site_id = ActionUtils.parseInt(queryForm.getSite_id());
/* 138 */     if (site_id != null) {
/* 139 */       conditions.put(ExpenseCategoryQueryCondition.SITE_ID_EQ, site_id);
/*     */     }
/*     */ 
/*     */     
/* 143 */     String description = queryForm.getDescription();
/* 144 */     if (description != null && description.trim().length() != 0) {
/* 145 */       conditions.put(ExpenseCategoryQueryCondition.DESCRIPTION_LIKE, description);
/*     */     }
/* 147 */     String type = queryForm.getType();
/* 148 */     if (type != null && type.trim().length() != 0) {
/* 149 */       conditions.put(ExpenseCategoryQueryCondition.TYPE_EQ, type);
/*     */     }
/* 151 */     String referenceErpId = queryForm.getReferenceErpId();
/* 152 */     if (referenceErpId != null && referenceErpId.trim().length() != 0) {
/* 153 */       conditions.put(ExpenseCategoryQueryCondition.REFERENCEERPID_LIKE, referenceErpId);
/*     */     }
/* 155 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 156 */     if (enabled != null) {
/* 157 */       conditions.put(ExpenseCategoryQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/* 159 */     return conditions;
/*     */   }
/*     */   
/*     */   private ExpenseCategory getExpenseCategoryFormRequest(HttpServletRequest request) throws Exception {
/* 163 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 164 */     ExpenseCategoryManager expenseCategoryManager = ServiceLocator.getExpenseCategoryManager(request);
/* 165 */     ExpenseCategory expenseCategory = expenseCategoryManager.getExpenseCategory(id);
/* 166 */     if (expenseCategory == null)
/* 167 */       throw new ActionException("expenseCategory.notFound", id); 
/* 168 */     return expenseCategory;
/*     */   }
/*     */   
/*     */   private List getChildrenOfExpenseCategory(ExpenseCategory expenseCategory, HttpServletRequest request) throws Exception {
/* 172 */     ExpenseSubCategoryManager expenseSubCategoryManager = ServiceLocator.getExpenseSubCategoryManager(request);
/* 173 */     List subList = expenseSubCategoryManager.getChildrenOfExpenseCategory(expenseCategory.getId());
/* 174 */     return subList;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 188 */     ExpenseCategory expenseCategory = getExpenseCategoryFormRequest(request);
/*     */     
/* 190 */     checkSite(expenseCategory.getSite(), request);
/* 191 */     request.setAttribute("x_expenseCategory", expenseCategory);
/*     */     
/* 193 */     List subList = getChildrenOfExpenseCategory(expenseCategory, request);
/* 194 */     request.setAttribute("x_subList", subList);
/*     */     
/* 196 */     if (!isBack(request)) {
/* 197 */       BeanForm expenseCategoryForm = (BeanForm)getForm("/updateExpenseCategory", request);
/* 198 */       expenseCategoryForm.populate(expenseCategory, "to_form");
/*     */     } 
/* 200 */     putEnumListToRequest(request);
/* 201 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Site getAndCheckSite(HttpServletRequest request) throws Exception {
/* 205 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 209 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 210 */     request.setAttribute("x_typeList", PersistentEnum.getEnumList(ExpenseType.class));
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 225 */     ExpenseCategory oldEc = getExpenseCategoryFormRequest(request);
/*     */     
/* 227 */     Site site = getAndCheckSite(request);
/*     */     
/* 229 */     BeanForm expenseCategoryForm = (BeanForm)form;
/*     */     
/* 231 */     ExpenseCategory expenseCategory = new ExpenseCategory();
/* 232 */     expenseCategoryForm.populate(expenseCategory, "to_bean");
/* 233 */     expenseCategory.setReferenceErpId(oldEc.getReferenceErpId());
/* 234 */     expenseCategory.setSite(site);
/*     */     
/* 236 */     ExpenseCategoryManager expenseCategoryManager = ServiceLocator.getExpenseCategoryManager(request);
/*     */     
/* 238 */     boolean newIsTravel = (expenseCategory.getEnabled().equals(EnabledDisabled.ENABLED) && expenseCategory.getType().equals(ExpenseType.TRAVEL));
/*     */     
/* 240 */     boolean oldIsNotTravel = !(!oldEc.getEnabled().equals(EnabledDisabled.DISABLED) && oldEc.getType().equals(ExpenseType.TRAVEL));
/*     */     
/* 242 */     if (oldIsNotTravel && newIsTravel && 
/* 243 */       getTravelExpenseCategoryCountOfSite(site, request) > 0) {
/* 244 */       throw new ActionException("expenseCategory.error.alreadyHasTravel");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 249 */     expenseCategoryManager.updateExpenseCategory(expenseCategory);
/*     */     
/* 251 */     request.setAttribute("X_OBJECT", expenseCategory);
/* 252 */     request.setAttribute("X_ROWPAGE", "expenseCategory/row.jsp");
/*     */     
/* 254 */     return mapping.findForward("success");
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 268 */     Site site = getAndCheckSite(request);
/* 269 */     request.setAttribute("x_site", site);
/*     */     
/* 271 */     if (!isBack(request)) {
/* 272 */       ExpenseCategory expenseCategory = new ExpenseCategory();
/* 273 */       expenseCategory.setSite(site);
/* 274 */       BeanForm expenseCategoryForm = (BeanForm)getForm("/insertExpenseCategory", request);
/* 275 */       expenseCategoryForm.populate(expenseCategory, "to_form");
/*     */     } 
/*     */     
/* 278 */     putEnumListToRequest(request);
/*     */     
/* 280 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 294 */     Site site = getAndCheckSite(request);
/*     */     
/* 296 */     ExpenseCategory expenseCategory = new ExpenseCategory();
/* 297 */     BeanForm expenseCategoryForm = (BeanForm)form;
/* 298 */     expenseCategoryForm.populate(expenseCategory, "to_bean");
/*     */     
/* 300 */     expenseCategory.setSite(site);
/*     */     
/* 302 */     ExpenseCategoryManager cm = ServiceLocator.getExpenseCategoryManager(request);
/*     */     
/* 304 */     if (expenseCategory.getEnabled().equals(EnabledDisabled.ENABLED) && expenseCategory.getType().equals(ExpenseType.TRAVEL) && 
/* 305 */       getTravelExpenseCategoryCountOfSite(site, request) > 0) {
/* 306 */       throw new ActionException("expenseCategory.error.alreadyHasTravel");
/*     */     }
/*     */ 
/*     */     
/* 310 */     cm.insertExpenseCategory(expenseCategory);
/*     */     
/* 312 */     request.setAttribute("X_OBJECT", expenseCategory);
/* 313 */     request.setAttribute("X_ROWPAGE", "expenseCategory/row.jsp");
/*     */     
/* 315 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private int getTravelExpenseCategoryCountOfSite(Site site, HttpServletRequest request) throws Exception {
/* 319 */     ExpenseCategoryManager cm = ServiceLocator.getExpenseCategoryManager(request);
/* 320 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 321 */     conds.put(ExpenseCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 322 */     conds.put(ExpenseCategoryQueryCondition.TYPE_EQ, ExpenseType.TRAVEL.getEnumCode());
/* 323 */     conds.put(ExpenseCategoryQueryCondition.SITE_ID_EQ, site.getId());
/*     */     
/* 325 */     return cm.getExpenseCategoryListCount(conds);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/ExpenseCategoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */