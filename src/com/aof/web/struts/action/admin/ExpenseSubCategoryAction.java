/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryCondition;
/*     */ import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import com.aof.service.admin.ExpenseSubCategoryManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.ExpenseSubCategoryQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class ExpenseSubCategoryAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  47 */     ExpenseSubCategoryManager fm = ServiceLocator.getExpenseSubCategoryManager(request);
/*     */     
/*  49 */     ExpenseSubCategoryQueryForm queryForm = (ExpenseSubCategoryQueryForm)form;
/*     */     
/*  51 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  53 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*     */       
/*  55 */       queryForm.setOrder(ExpenseSubCategoryQueryOrder.DESCRIPTION.getName());
/*  56 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  59 */     if (queryForm.isFirstInit()) {
/*  60 */       queryForm.init(fm.getExpenseSubCategoryListCount(conditions), 5);
/*     */     } else {
/*  62 */       queryForm.init();
/*     */     } 
/*  64 */     int pageNo = queryForm.getPageNoAsInt();
/*  65 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/*  67 */     request.setAttribute("X_RESULTLIST", fm.getExpenseSubCategoryList(conditions, pageNo, pageSize, ExpenseSubCategoryQueryOrder.getEnum(queryForm
/*  68 */             .getOrder()), queryForm.isDescend()));
/*  69 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ExpenseSubCategoryQueryForm queryForm) {
/*  73 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  75 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*  76 */     if (id != null) {
/*  77 */       conditions.put(ExpenseSubCategoryQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/*  80 */     Integer expenseCategory_id = ActionUtils.parseInt(queryForm.getExpenseCategory_id());
/*  81 */     if (expenseCategory_id != null) {
/*  82 */       conditions.put(ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_ID_EQ, expenseCategory_id);
/*     */     }
/*     */     
/*  85 */     String description = queryForm.getDescription();
/*  86 */     if (description != null && description.trim().length() != 0) {
/*  87 */       conditions.put(ExpenseSubCategoryQueryCondition.DESCRIPTION_LIKE, description);
/*     */     }
/*  89 */     String referenceErpId = queryForm.getReferenceErpId();
/*  90 */     if (referenceErpId != null && referenceErpId.trim().length() != 0) {
/*  91 */       conditions.put(ExpenseSubCategoryQueryCondition.REFERENCEERPID_LIKE, referenceErpId);
/*     */     }
/*  93 */     String enabled = queryForm.getEnabled();
/*  94 */     if (enabled != null && enabled.trim().length() != 0) {
/*  95 */       conditions.put(ExpenseSubCategoryQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*  97 */     return conditions;
/*     */   }
/*     */   
/*     */   private ExpenseSubCategory getExpenseSubCategoryFromRequest(HttpServletRequest request) throws Exception {
/* 101 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 102 */     ExpenseSubCategoryManager expenseSubCategoryManager = ServiceLocator.getExpenseSubCategoryManager(request);
/* 103 */     ExpenseSubCategory expenseSubCategory = expenseSubCategoryManager.getExpenseSubCategory(id);
/* 104 */     if (expenseSubCategory == null)
/* 105 */       throw new ActionException("expenseSubCategory.notFound", id); 
/* 106 */     return expenseSubCategory;
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
/* 120 */     ExpenseSubCategory expenseSubCategory = getExpenseSubCategoryFromRequest(request);
/* 121 */     checkSite(expenseSubCategory.getExpenseCategory().getSite(), request);
/* 122 */     request.setAttribute("x_esc", expenseSubCategory);
/*     */     
/* 124 */     if (!isBack(request)) {
/* 125 */       BeanForm expenseSubCategoryForm = (BeanForm)getForm("/updateExpenseSubCategory", request);
/* 126 */       expenseSubCategoryForm.populate(expenseSubCategory, "to_form");
/*     */     } 
/*     */     
/* 129 */     if (expenseSubCategory.getExpenseCategory().getType().equals(ExpenseType.TRAVEL)) {
/* 130 */       request.setAttribute("x_isTravel", Boolean.TRUE);
/*     */     }
/*     */     
/* 133 */     putEnumListToRequest(request);
/* 134 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 138 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 139 */     request.setAttribute("x_yesNoList", PersistentEnum.getEnumList(YesNo.class));
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 153 */     ExpenseSubCategory oldEsc = getExpenseSubCategoryFromRequest(request);
/*     */     
/* 155 */     ExpenseCategory ec = oldEsc.getExpenseCategory();
/* 156 */     checkSite(ec.getSite(), request);
/*     */     
/* 158 */     ExpenseSubCategory expenseSubCategory = new ExpenseSubCategory();
/* 159 */     BeanForm expenseSubCategoryForm = (BeanForm)form;
/* 160 */     expenseSubCategoryForm.populate(expenseSubCategory, "to_bean");
/* 161 */     expenseSubCategory.setReferenceErpId(oldEsc.getReferenceErpId());
/*     */     
/* 163 */     expenseSubCategory.setExpenseCategory(ec);
/*     */     
/* 165 */     if (ec.getType().equals(ExpenseType.TRAVEL)) {
/*     */       
/* 167 */       boolean newIsHotel = (expenseSubCategory.getEnabled().equals(EnabledDisabled.ENABLED) && expenseSubCategory.getIsHotel().equals(YesNo.YES));
/*     */       
/* 169 */       boolean oldIsNotHotel = !(!oldEsc.getEnabled().equals(EnabledDisabled.DISABLED) && !oldEsc.getIsHotel().equals(YesNo.NO));
/*     */       
/* 171 */       if (oldIsNotHotel && newIsHotel && 
/* 172 */         getHotelExpenseSubCategoryCount(ec, request) > 0) {
/* 173 */         throw new ActionException("expenseSubCategory.error.alreadyHasHotel");
/*     */       }
/*     */     } else {
/*     */       
/* 177 */       expenseSubCategory.setIsHotel(YesNo.NO);
/*     */     } 
/*     */     
/* 180 */     ExpenseSubCategoryManager expenseSubCategoryManager = ServiceLocator.getExpenseSubCategoryManager(request);
/*     */     
/* 182 */     request.setAttribute("X_OBJECT", expenseSubCategoryManager.updateExpenseSubCategory(expenseSubCategory));
/* 183 */     request.setAttribute("X_ROWPAGE", "expenseSubCategory/row.jsp");
/*     */     
/* 185 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private ExpenseCategory getExpenseCategoryFromRequest(HttpServletRequest request) throws NumberFormatException, Exception {
/* 189 */     ExpenseCategoryManager cm = ServiceLocator.getExpenseCategoryManager(request);
/*     */     
/* 191 */     String expenseCategory_id = request.getParameter("expenseCategory_id");
/* 192 */     ExpenseCategory expenseCategory = cm.getExpenseCategory(Integer.valueOf(expenseCategory_id));
/*     */     
/* 194 */     return expenseCategory;
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
/* 208 */     ExpenseCategory expenseCategory = getExpenseCategoryFromRequest(request);
/* 209 */     checkSite(expenseCategory.getSite(), request);
/*     */     
/* 211 */     if (!isBack(request)) {
/* 212 */       ExpenseSubCategory expenseSubCategory = new ExpenseSubCategory();
/* 213 */       expenseSubCategory.setExpenseCategory(expenseCategory);
/*     */       
/* 215 */       BeanForm expenseSubCategoryForm = (BeanForm)getForm("/insertExpenseSubCategory", request);
/* 216 */       expenseSubCategoryForm.populate(expenseSubCategory, "to_form");
/*     */     } 
/*     */     
/* 219 */     if (expenseCategory.getType().equals(ExpenseType.TRAVEL)) {
/* 220 */       request.setAttribute("x_isTravel", Boolean.TRUE);
/*     */     }
/*     */     
/* 223 */     putEnumListToRequest(request);
/* 224 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 239 */     ExpenseCategory expenseCategory = getExpenseCategoryFromRequest(request);
/* 240 */     checkSite(expenseCategory.getSite(), request);
/*     */     
/* 242 */     BeanForm expenseSubCategoryForm = (BeanForm)form;
/* 243 */     ExpenseSubCategory expenseSubCategory = new ExpenseSubCategory();
/* 244 */     expenseSubCategoryForm.populate(expenseSubCategory, "to_bean");
/* 245 */     expenseSubCategory.setExpenseCategory(expenseCategory);
/*     */     
/* 247 */     if (expenseCategory.getType().equals(ExpenseType.TRAVEL)) {
/* 248 */       if (expenseSubCategory.getEnabled().equals(EnabledDisabled.ENABLED) && expenseSubCategory.getIsHotel().equals(YesNo.YES) && 
/* 249 */         getHotelExpenseSubCategoryCount(expenseCategory, request) > 0) {
/* 250 */         throw new ActionException("expenseSubCategory.error.alreadyHasHotel");
/*     */       }
/*     */     } else {
/*     */       
/* 254 */       expenseSubCategory.setIsHotel(YesNo.NO);
/*     */     } 
/*     */     
/* 257 */     ExpenseSubCategoryManager cm = ServiceLocator.getExpenseSubCategoryManager(request);
/*     */     
/* 259 */     request.setAttribute("X_OBJECT", cm.insertExpenseSubCategory(expenseSubCategory));
/* 260 */     request.setAttribute("X_ROWPAGE", "expenseSubCategory/row.jsp");
/*     */     
/* 262 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   private int getHotelExpenseSubCategoryCount(ExpenseCategory expenseCategory, HttpServletRequest request) throws Exception {
/* 267 */     ExpenseSubCategoryManager cm = ServiceLocator.getExpenseSubCategoryManager(request);
/* 268 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 269 */     conds.put(ExpenseSubCategoryQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 270 */     conds.put(ExpenseSubCategoryQueryCondition.ISHOTEL_EQ, YesNo.YES.getEnumCode());
/* 271 */     conds.put(ExpenseSubCategoryQueryCondition.EXPENSECATEGORY_ID_EQ, expenseCategory.getId());
/* 272 */     return cm.getExpenseSubCategoryListCount(conds);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/ExpenseSubCategoryAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */