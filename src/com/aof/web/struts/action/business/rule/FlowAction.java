/*     */ package com.aof.web.struts.action.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.rule.Flow;
/*     */ import com.aof.model.business.rule.FlowRule;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.query.FlowQueryCondition;
/*     */ import com.aof.model.business.rule.query.FlowQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.service.business.rule.FlowManager;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.business.rule.FlowQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.action.BackToInputActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public class FlowAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private ActionForward list(ActionMapping mapping, FlowQueryForm queryForm, HttpServletRequest request, HttpServletResponse response, RuleType type) throws Exception {
/*  58 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/*     */     
/*  60 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */     
/*  62 */     queryForm.getSite().setList(getAndCheckGrantedSiteList(request));
/*     */     
/*  64 */     Map<FlowQueryCondition, Serializable> conditions = constructQueryMap(queryForm);
/*     */     
/*  66 */     conditions.put(FlowQueryCondition.TYPE_EQ, type.getEnumCode());
/*     */     
/*  68 */     String exportType = queryForm.getExportType();
/*  69 */     if (exportType != null && exportType.length() > 0) {
/*  70 */       List data = fm.getFlowList(conditions, 0, -1, FlowQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  71 */       int index = SessionTempFile.createNewTempFile(request);
/*  72 */       String fileName = String.valueOf(type.getPrefixUrl()) + "Flows";
/*  73 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  76 */               MessageResources messages = FlowAction.this.getResources(request);
/*  77 */               row.add(messages.getMessage(FlowAction.this.getLocale(request), "flow.id"));
/*  78 */               row.add(messages.getMessage(FlowAction.this.getLocale(request), "flow.description"));
/*  79 */               row.add(messages.getMessage(FlowAction.this.getLocale(request), "flow.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  83 */               row.add(BeanUtils.getProperty(data, "id"));
/*  84 */               row.add(BeanUtils.getProperty(data, "description"));
/*  85 */               String locale = FlowAction.this.getCurrentUser(request).getLocale();
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
/*  97 */       queryForm.init(fm.getFlowListCount(conditions));
/*     */     } else {
/*  99 */       queryForm.init();
/*     */     } 
/*     */     
/* 102 */     request.setAttribute("X_RESULTLIST", fm.getFlowList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), FlowQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/* 103 */     request.setAttribute("X_RULETYPE", type);
/* 104 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(FlowQueryForm queryForm) {
/* 108 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 110 */     conditions.put(FlowQueryCondition.SITE_ID_EQ, ((Site)queryForm.getSite().getSelectedItem()).getId());
/*     */     
/* 112 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 113 */     if (id != null) conditions.put(FlowQueryCondition.ID_EQ, id);
/*     */     
/* 115 */     String description = queryForm.getDescription();
/* 116 */     if (description != null) {
/* 117 */       description = description.trim();
/* 118 */       if (description.length() == 0) description = null; 
/*     */     } 
/* 120 */     if (description != null) conditions.put(FlowQueryCondition.DESCRIPTION_LIKE, description);
/*     */     
/* 122 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 123 */     if (enabled != null) {
/* 124 */       conditions.put(FlowQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*     */ 
/*     */     
/* 128 */     return conditions;
/*     */   }
/*     */   
/*     */   private ActionForward newObject(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 132 */     Flow f = prepareAndCheckFlow(type, request);
/* 133 */     if (!isBack(request)) {
/* 134 */       BeanForm flowForm = (BeanForm)getForm("/insert" + type.getPrefixUrl() + "Flow", request);
/* 135 */       flowForm.populateToForm(f);
/* 136 */       request.setAttribute("X_FLOWRULES", new ArrayList());
/* 137 */       request.setAttribute("X_FLOWRULESIZE", new Integer(0));
/*     */     } 
/* 139 */     prepareData(f, request);
/* 140 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private ActionForward insert(ActionMapping mapping, BeanForm flowForm, HttpServletRequest request, RuleType type) throws Exception {
/* 144 */     Flow f = prepareAndCheckFlow(type, request);
/* 145 */     flowForm.populateToBean(f);
/* 146 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/*     */     
/* 148 */     Set flowRules = getRulesFromRequest(f, request);
/* 149 */     request.setAttribute("X_FLOWRULES", flowRules);
/* 150 */     request.setAttribute("X_FLOWRULESIZE", new Integer(flowRules.size()));
/*     */     
/* 152 */     switch (fm.validateRules(flowRules)) {
/*     */       case -1:
/* 154 */         throw new BackToInputActionException("flow.detect.loopFound");
/*     */       case -2:
/* 156 */         throw new BackToInputActionException("flow.detect.badSeq");
/*     */       case -3:
/* 158 */         throw new BackToInputActionException("flow.detect.noRule");
/*     */     } 
/*     */     
/* 161 */     Flow[] flows = fm.saveFlow(f, flowRules);
/*     */     
/* 163 */     request.setAttribute("X_OBJECT", flows[0]);
/* 164 */     if (flows.length == 2) request.setAttribute("X_OBJECT2", flows[1]); 
/* 165 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private ActionForward edit(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 169 */     Flow f = getAndCheckFlow(type, request);
/* 170 */     if (!isBack(request)) {
/* 171 */       BeanForm flowForm = (BeanForm)getForm("/update" + type.getPrefixUrl() + "Flow", request);
/* 172 */       flowForm.populateToForm(f);
/* 173 */       Set flowRules = f.getRules();
/* 174 */       request.setAttribute("X_FLOWRULES", flowRules);
/* 175 */       request.setAttribute("X_FLOWRULESIZE", new Integer(flowRules.size()));
/*     */     } 
/*     */     
/* 178 */     prepareData(f, request);
/* 179 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private ActionForward update(ActionMapping mapping, BeanForm flowForm, HttpServletRequest request, RuleType type) throws Exception {
/* 183 */     Flow f = getAndCheckFlow(type, request);
/*     */     
/* 185 */     flowForm.populateToBean(f, (HttpServletRequest) null, new String[] { "site.id" });
/*     */     
/* 187 */     Set flowRules = getRulesFromRequest(f, request);
/* 188 */     request.setAttribute("X_FLOWRULES", flowRules);
/* 189 */     request.setAttribute("X_FLOWRULESIZE", new Integer(flowRules.size()));
/*     */     
/* 191 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/* 192 */     switch (fm.validateRules(flowRules)) {
/*     */       case -1:
/* 194 */         throw new BackToInputActionException("flow.detect.loopFound");
/*     */       case -2:
/* 196 */         throw new BackToInputActionException("flow.detect.badSeq");
/*     */       case -3:
/* 198 */         throw new BackToInputActionException("flow.detect.noRule");
/*     */     } 
/*     */     
/* 201 */     Flow[] flows = fm.saveFlow(f, flowRules);
/* 202 */     request.setAttribute("X_OBJECT", flows[0]);
/* 203 */     if (flows.length == 2) request.setAttribute("X_OBJECT2", flows[1]); 
/* 204 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private ActionForward delete(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 208 */     Flow f = getAndCheckFlow(type, request);
/* 209 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/* 210 */     fm.removeFlow(f.getId());
/*     */     
/* 212 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Flow prepareAndCheckFlow(RuleType type, HttpServletRequest request) throws Exception {
/* 216 */     Site s = getAndCheckSite("site_id", request);
/* 217 */     Flow f = new Flow();
/* 218 */     f.setSite(s);
/* 219 */     f.setType(type);
/* 220 */     return f;
/*     */   }
/*     */   
/*     */   private Flow getAndCheckFlow(RuleType type, HttpServletRequest request) throws Exception {
/* 224 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/* 225 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 226 */     Flow f = fm.getFlow(id, true);
/* 227 */     if (f == null || !type.equals(f.getType())) throw new ActionException("flow." + type.getPrefixUrl() + ".notFound", id); 
/* 228 */     checkSite(f.getSite(), request);
/* 229 */     return f;
/*     */   }
/*     */   
/*     */   private void prepareData(Flow f, HttpServletRequest request) throws Exception {
/* 233 */     FlowManager fm = ServiceLocator.getFlowManager(request);
/* 234 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 235 */     request.setAttribute("X_RULETYPE", f.getType());
/* 236 */     Flow ef = fm.getSiteEnabledFlow(f.getSite().getId(), f.getType());
/* 237 */     if (ef != null) {
/* 238 */       if (f.equals(ef)) {
/* 239 */         request.setAttribute("X_MEENABLED", Boolean.TRUE);
/*     */       } else {
/* 241 */         request.setAttribute("X_OTHERENABLEDFLOW", ef);
/*     */       } 
/*     */     }
/* 244 */     request.setAttribute("X_ENABLED", EnabledDisabled.ENABLED);
/* 245 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 246 */     List ruleList = rm.getSiteEnabledRuleList(f.getSite().getId(), f.getType());
/* 247 */     if (ruleList.isEmpty()) throw new ActionException("flow.error.noRuleCanUse"); 
/* 248 */     request.setAttribute("X_RULELIST", ruleList);
/*     */   }
/*     */   
/*     */   private Set getRulesFromRequest(Flow f, HttpServletRequest request) {
/* 252 */     String[] seq = request.getParameterValues("fr_seq");
/* 253 */     String[] ruleId = request.getParameterValues("fr_rule");
/* 254 */     String[] nextSeqPass = request.getParameterValues("fr_seq_pass");
/* 255 */     String[] nextSeqFail = request.getParameterValues("fr_seq_fail");
/*     */     
/* 257 */     TreeSet<FlowRule> result = new TreeSet(new Comparator()
/*     */         {
/*     */           public int compare(Object o1, Object o2) {
/* 260 */             return ((FlowRule)o1).getSeq() - ((FlowRule)o2).getSeq();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 265 */     if (seq != null) {
/* 266 */       for (int i = 0; i < seq.length; i++) {
/* 267 */         FlowRule fr = new FlowRule();
/* 268 */         fr.setFlow(f);
/* 269 */         fr.setSeq(ActionUtils.parseInt(seq[i]).intValue());
/* 270 */         fr.setRule(new Rule(ActionUtils.parseInt(ruleId[i])));
/* 271 */         fr.setNextSeqWhenPass(ActionUtils.parseInt(nextSeqPass[i]).intValue());
/* 272 */         fr.setNextSeqWhenFail(ActionUtils.parseInt(nextSeqFail[i]).intValue());
/* 273 */         result.add(fr);
/*     */       } 
/*     */     }
/* 276 */     return result;
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
/*     */   public ActionForward listCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 289 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward newCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 302 */     return newObject(mapping, request, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward insertCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 315 */     return insert(mapping, (BeanForm)form, request, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward editCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 328 */     return edit(mapping, request, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward updateCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 341 */     return update(mapping, (BeanForm)form, request, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward deleteCapexApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 354 */     return delete(mapping, request, RuleType.CAPEX_APPROVAL_RULES);
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
/*     */   public ActionForward listPRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 367 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward newPRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 380 */     return newObject(mapping, request, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward insertPRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 393 */     return insert(mapping, (BeanForm)form, request, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward editPRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 406 */     return edit(mapping, request, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward updatePRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 419 */     return update(mapping, (BeanForm)form, request, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward deletePRApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 432 */     return delete(mapping, request, RuleType.PR_APPROVAL_RULES);
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
/*     */   public ActionForward listPurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 445 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward newPurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 458 */     return newObject(mapping, request, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward insertPurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 471 */     return insert(mapping, (BeanForm)form, request, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward editPurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 484 */     return edit(mapping, request, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward updatePurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 497 */     return update(mapping, (BeanForm)form, request, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward deletePurchasing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 510 */     return delete(mapping, request, RuleType.PURCHASING_RULES);
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
/*     */   public ActionForward listPurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 523 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward newPurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 536 */     return newObject(mapping, request, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward insertPurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 549 */     return insert(mapping, (BeanForm)form, request, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward editPurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 562 */     return edit(mapping, request, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward updatePurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 575 */     return update(mapping, (BeanForm)form, request, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward deletePurchasingPriceControl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 588 */     return delete(mapping, request, RuleType.PURCHASING_PRICE_CONTROL_RULES);
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
/*     */   public ActionForward listPOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 601 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward newPOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 614 */     return newObject(mapping, request, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward insertPOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 627 */     return insert(mapping, (BeanForm)form, request, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward editPOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 640 */     return edit(mapping, request, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward updatePOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 653 */     return update(mapping, (BeanForm)form, request, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward deletePOApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 666 */     return delete(mapping, request, RuleType.PO_APPROVAL_RULES);
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
/*     */   public ActionForward listExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 679 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward newExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 692 */     return newObject(mapping, request, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward insertExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 705 */     return insert(mapping, (BeanForm)form, request, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward editExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 718 */     return edit(mapping, request, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward updateExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 731 */     return update(mapping, (BeanForm)form, request, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward deleteExpenseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 744 */     return delete(mapping, request, RuleType.EXPENSE_APPROVAL_RULES);
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
/*     */   public ActionForward listExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 757 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward newExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 770 */     return newObject(mapping, request, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward insertExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 783 */     return insert(mapping, (BeanForm)form, request, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward editExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 796 */     return edit(mapping, request, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward updateExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 809 */     return update(mapping, (BeanForm)form, request, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward deleteExpenseClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 822 */     return delete(mapping, request, RuleType.EXPENSE_CLAIM_RULES);
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
/*     */   public ActionForward listTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 835 */     return list(mapping, (FlowQueryForm)form, request, response, RuleType.TRAVEL_APPROVAL_RULES);
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
/*     */   public ActionForward newTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 848 */     return newObject(mapping, request, RuleType.TRAVEL_APPROVAL_RULES);
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
/*     */   public ActionForward insertTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 861 */     return insert(mapping, (BeanForm)form, request, RuleType.TRAVEL_APPROVAL_RULES);
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
/*     */   public ActionForward editTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 874 */     return edit(mapping, request, RuleType.TRAVEL_APPROVAL_RULES);
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
/*     */   public ActionForward updateTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 887 */     return update(mapping, (BeanForm)form, request, RuleType.TRAVEL_APPROVAL_RULES);
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
/*     */   public ActionForward deleteTravelApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 900 */     return delete(mapping, request, RuleType.TRAVEL_APPROVAL_RULES);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/rule/FlowAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */