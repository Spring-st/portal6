/*     */ package com.aof.web.struts.action.business;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.CustomerQueryCondition;
/*     */ import com.aof.model.admin.query.CustomerQueryOrder;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.business.BaseRecharge;
/*     */ import com.aof.model.business.Rechargeable;
/*     */ import com.aof.model.metadata.CustomerType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RechargeType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.CustomerManager;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.business.RechargeCustomerQueryForm;
/*     */ import com.aof.web.struts.form.business.RechargePersonQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class RechargeAction
/*     */   extends BaseAction
/*     */ {
/*     */   protected void setRechargeInfoToRequest(Rechargeable rechargeTarget, Collection rechargeList, Site s, BeanForm form, HttpServletRequest request) {
/*  57 */     setRechargeInfoToRequest(rechargeTarget, rechargeList, s, form, request, true);
/*     */   }
/*     */   
/*     */   protected void setRechargeInfoToRequest(Rechargeable rechargeTarget, Collection rechargeList, Site s, BeanForm form, HttpServletRequest request, boolean canChangeRecharge) {
/*  61 */     String rechargeTargetClassName = rechargeTarget.getClass().getName();
/*  62 */     rechargeTargetClassName = rechargeTargetClassName.substring(rechargeTargetClassName.lastIndexOf('.') + 1);
/*  63 */     request.setAttribute("X_RECHARGETARGET", rechargeTarget);
/*  64 */     request.setAttribute("X_RECHARGETARGETTYPE", rechargeTargetClassName);
/*  65 */     request.setAttribute("X_RECHARGESITE", s);
/*  66 */     request.setAttribute("x_canChangeRecharge", Boolean.valueOf(canChangeRecharge));
/*     */     
/*  68 */     boolean canRecharge = canChangeRecharge ? YesNo.YES.equals(s.getCanRecharge()) : YesNo.YES.equals(rechargeTarget.getIsRecharge());
/*  69 */     request.setAttribute("x_canRecharge", Boolean.valueOf(canRecharge));
/*  70 */     if (canRecharge) {
/*  71 */       if (!isBack(request)) {
/*  72 */         request.setAttribute("X_RECHARGELIST", rechargeList);
/*     */       }
/*  74 */       request.setAttribute("X_RECHARGETYPELIST", PersistentEnum.getEnumList(RechargeType.class));
/*  75 */       request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/*     */     } else {
/*  77 */       form.set("isRecharge", YesNo.NO.getEnumCode().toString());
/*     */     } 
/*  79 */     rechargeTarget.setIsRecharge((YesNo)PersistentEnum.fromEnumCode(YesNo.class, ActionUtils.parseInt((String)form.get("isRecharge"))));
/*     */     
/*  81 */     if (!canChangeRecharge);
/*     */ 
/*     */ 
/*     */     
/*  85 */     ServiceLocator.getDepartmentManager(request).fillDepartment(s, true);
/*  86 */     request.setAttribute("X_DEPARTMENTLIST", s.getDepartments());
/*  87 */     request.setAttribute("x_rechargeTypeCustomer", RechargeType.CUSTOMER);
/*  88 */     request.setAttribute("x_rechargeTypeEntity", RechargeType.ENTITY);
/*  89 */     request.setAttribute("x_rechargeTypePerson", RechargeType.PERSON);
/*  90 */     request.setAttribute("x_yes", YesNo.YES);
/*     */   }
/*     */   
/*     */   protected void setRechargeInfoToRequestForView(Rechargeable rechargeTarget, Collection rechargeList, HttpServletRequest request) {
/*  94 */     request.setAttribute("X_RECHARGETARGET", rechargeTarget);
/*  95 */     request.setAttribute("X_RECHARGELIST", rechargeList);
/*  96 */     request.setAttribute("x_recharged", Boolean.valueOf(YesNo.YES.equals(rechargeTarget.getIsRecharge())));
/*  97 */     request.setAttribute("x_showBuyFor", Boolean.valueOf(rechargeTarget instanceof com.aof.model.business.BuyForTarget));
/*  98 */     request.setAttribute("x_rechargeTypePerson", RechargeType.PERSON);
/*     */   }
/*     */   
/*     */   protected List getRechargeInfoFromRequest(Rechargeable rechargeTarget, HttpServletRequest request) {
/* 102 */     String[] customer_code = request.getParameterValues("recharge_customer_code");
/* 103 */     String[] person_dep_id = request.getParameterValues("recharge_person_dep_id");
/* 104 */     String[] person_id = request.getParameterValues("recharge_person_id");
/* 105 */     String[] recharge_amount = request.getParameterValues("recharge_amount");
/*     */     
/* 107 */     List<BaseRecharge> result = new ArrayList();
/*     */     
/* 109 */     if (YesNo.YES.equals(rechargeTarget.getIsRecharge())) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       CustomerManager cm = ServiceLocator.getCustomerManager(request);
/* 119 */       UserManager um = ServiceLocator.getUserManager(request);
/* 120 */       DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 121 */       if (customer_code != null) {
/* 122 */         for (int i = 1; i < customer_code.length; i++) {
/* 123 */           BaseRecharge br = rechargeTarget.createNewRechargeObj();
/* 124 */           if (customer_code[i] != null && customer_code[i].length() > 0) {
/* 125 */             br.setCustomer(cm.getCustomer(customer_code[i]));
/*     */           } else {
/* 127 */             Integer personDepartmentId = ActionUtils.parseInt(person_dep_id[i]);
/* 128 */             if (person_dep_id != null) br.setPersonDepartment(dm.getDepartment(personDepartmentId)); 
/* 129 */             Integer personId = ActionUtils.parseInt(person_id[i]);
/* 130 */             if (person_id != null) br.setPerson(um.getUser(personId)); 
/*     */           } 
/* 132 */           br.setAmount(new BigDecimal(recharge_amount[i]));
/* 133 */           result.add(br);
/*     */         } 
/*     */       }
/*     */     } 
/* 137 */     request.setAttribute("X_RECHARGELIST", result);
/* 138 */     return result;
/*     */   }
/*     */   
/*     */   protected ActionForward selectRechargeCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 142 */     RechargeCustomerQueryForm queryForm = (RechargeCustomerQueryForm)form;
/* 143 */     Map<CustomerQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 144 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 145 */     if (siteId == null) {
/* 146 */       throw new ActionException("site.notFound", siteId);
/*     */     }
/* 148 */     conditions.put(CustomerQueryCondition.SITE_ID_EQ, siteId);
/* 149 */     conditions.put(CustomerQueryCondition.TYPE_EQ, CustomerType.CUSTOMER);
/* 150 */     conditions.put(CustomerQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/* 151 */     CustomerManager cm = ServiceLocator.getCustomerManager(request);
/* 152 */     if (queryForm.isFirstInit()) {
/* 153 */       queryForm.init(cm.getCustomerListCount(conditions), 10);
/*     */     } else {
/* 155 */       queryForm.init();
/*     */     } 
/*     */     
/* 158 */     request.setAttribute("X_ACTION", mapping.getPath());
/* 159 */     request.setAttribute("X_RESULTLIST", cm.getCustomerList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 160 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   protected ActionForward selectRechargeEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 164 */     RechargeCustomerQueryForm queryForm = (RechargeCustomerQueryForm)form;
/* 165 */     Map<CustomerQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 166 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 167 */     if (siteId == null) {
/* 168 */       throw new ActionException("site.notFound", siteId);
/*     */     }
/* 170 */     conditions.put(CustomerQueryCondition.SITE_ID_EQ, siteId);
/* 171 */     conditions.put(CustomerQueryCondition.TYPE_EQ, CustomerType.ENTITY);
/* 172 */     conditions.put(CustomerQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/* 173 */     CustomerManager cm = ServiceLocator.getCustomerManager(request);
/* 174 */     if (queryForm.isFirstInit()) {
/* 175 */       queryForm.init(cm.getCustomerListCount(conditions), 10);
/*     */     } else {
/* 177 */       queryForm.init();
/*     */     } 
/*     */     
/* 180 */     request.setAttribute("X_ACTION", mapping.getPath());
/* 181 */     request.setAttribute("X_RESULTLIST", cm.getCustomerList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), CustomerQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 182 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(RechargeCustomerQueryForm queryForm) {
/* 186 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 188 */     String code = queryForm.getCode();
/* 189 */     if (code != null) {
/* 190 */       code = code.trim();
/* 191 */       if (code.length() == 0) code = null; 
/*     */     } 
/* 193 */     if (code != null) conditions.put(CustomerQueryCondition.CODE_LIKE, code);
/*     */     
/* 195 */     String description = queryForm.getDescription();
/* 196 */     if (description != null) {
/* 197 */       description = description.trim();
/* 198 */       if (description.length() == 0) description = null; 
/*     */     } 
/* 200 */     if (description != null) conditions.put(CustomerQueryCondition.DESCRIPTION_LIKE, description);
/*     */     
/* 202 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ActionForward selectRechargePerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 207 */     RechargePersonQueryForm queryForm = (RechargePersonQueryForm)form;
/* 208 */     Map<CustomerQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/* 209 */     DepartmentManager dm = ServiceLocator.getDepartmentManager(request);
/* 210 */     if (request.getParameter("siteId") == null) {
/* 211 */       Integer departmentId = ActionUtils.parseInt(queryForm.getDepartmentId());
/* 212 */       Department d = dm.getDepartment(departmentId);
/* 213 */       if (d == null) {
/* 214 */         throw new ActionException("department.notFound", departmentId);
/*     */       }
/* 216 */       request.setAttribute("x_department", d);
/*     */     } else {
/* 218 */       Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/* 219 */       Site site = ServiceLocator.getSiteManager(request).getSite(siteId);
/* 220 */       if (site == null) {
/* 221 */         throw new ActionException("site.notFound", siteId);
/*     */       }
/* 223 */       dm.fillDepartment(site, true);
/* 224 */       request.setAttribute("X_DEPARTMENTLIST", site.getDepartments());
/* 225 */       conditions.put(CustomerQueryCondition.SITE_ID_EQ, siteId);
/*     */     } 
/* 227 */     conditions.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 228 */     UserManager um = ServiceLocator.getUserManager(request);
/* 229 */     if (queryForm.isFirstInit()) {
/* 230 */       queryForm.init(um.getUserListCount(conditions), 10);
/*     */     } else {
/* 232 */       queryForm.init();
/*     */     } 
/*     */     
/* 235 */     request.setAttribute("X_ACTION", mapping.getPath());
/* 236 */     request.setAttribute("X_RESULTLIST", um.getUserList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), UserQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 237 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(RechargePersonQueryForm queryForm) {
/* 241 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 243 */     String loginName = queryForm.getLoginName();
/* 244 */     if (loginName != null) {
/* 245 */       loginName = loginName.trim();
/* 246 */       if (loginName.length() == 0) loginName = null; 
/*     */     } 
/* 248 */     if (loginName != null) conditions.put(UserQueryCondition.LOGINNAME_LIKE, loginName);
/*     */     
/* 250 */     String name = queryForm.getName();
/* 251 */     if (name != null) {
/* 252 */       name = name.trim();
/* 253 */       if (name.length() == 0) name = null; 
/*     */     } 
/* 255 */     if (name != null) conditions.put(UserQueryCondition.NAME_LIKE, name);
/*     */     
/* 257 */     Integer departmentId = ActionUtils.parseInt(queryForm.getDepartmentId());
/* 258 */     if (departmentId != null) {
/* 259 */       conditions.put(UserQueryCondition.DEPARTMENT_ID_EQ, departmentId);
/*     */     }
/*     */     
/* 262 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/RechargeAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */