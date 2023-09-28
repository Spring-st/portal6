/*     */ package com.aof.web.struts.action.business.rule;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.query.RuleQueryCondition;
/*     */ import com.aof.model.business.rule.query.RuleQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.business.rule.RuleQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class BaseRuleAction
/*     */   extends BaseAction2
/*     */ {
/*     */   protected ActionForward list(ActionMapping mapping, RuleQueryForm queryForm, HttpServletRequest request, HttpServletResponse response, RuleType type) throws Exception {
/*  49 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/*  51 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */     
/*  53 */     queryForm.getSite().setList(getAndCheckGrantedSiteList(request));
/*     */     
/*  55 */     Map<RuleQueryCondition, Serializable> conditions = constructQueryMap(queryForm);
/*     */     
/*  57 */     conditions.put(RuleQueryCondition.TYPE_EQ, type.getEnumCode());
/*     */     
/*  59 */     String exportType = queryForm.getExportType();
/*  60 */     if (exportType != null && exportType.length() > 0) {
/*  61 */       List data = rm.getRuleList(conditions, 0, -1, RuleQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  62 */       int index = SessionTempFile.createNewTempFile(request);
/*  63 */       String fileName = String.valueOf(type.getPrefixUrl()) + "Rules";
/*  64 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  67 */               MessageResources messages = BaseRuleAction.this.getResources(request);
/*  68 */               row.add(messages.getMessage(BaseRuleAction.this.getLocale(request), "rule.id"));
/*  69 */               row.add(messages.getMessage(BaseRuleAction.this.getLocale(request), "rule.description"));
/*  70 */               row.add(messages.getMessage(BaseRuleAction.this.getLocale(request), "rule.enabled"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  74 */               row.add(BeanUtils.getProperty(data, "id"));
/*  75 */               row.add(BeanUtils.getProperty(data, "description"));
/*  76 */               String locale = BaseRuleAction.this.getCurrentUser(request).getLocale();
/*  77 */               if ("en".equals(locale)) {
/*  78 */                 row.add(BeanUtils.getProperty(data, "enabled.engShortDescription"));
/*     */               } else {
/*  80 */                 row.add(BeanUtils.getProperty(data, "enabled.chnShortDescription"));
/*     */               } 
/*     */             }
/*     */           });
/*  84 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  87 */     if (queryForm.isFirstInit()) {
/*  88 */       queryForm.init(rm.getRuleListCount(conditions));
/*     */     } else {
/*  90 */       queryForm.init();
/*     */     } 
/*     */     
/*  93 */     request.setAttribute("X_RESULTLIST", rm.getRuleList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  94 */           RuleQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend()));
/*  95 */     request.setAttribute("X_RULETYPE", type);
/*  96 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(RuleQueryForm queryForm) {
/* 100 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 102 */     conditions.put(RuleQueryCondition.SITE_ID_EQ, ((Site)queryForm.getSite().getSelectedItem()).getId());
/*     */     
/* 104 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 105 */     if (id != null) {
/* 106 */       conditions.put(RuleQueryCondition.ID_EQ, id);
/*     */     }
/* 108 */     String description = queryForm.getDescription();
/* 109 */     if (description != null) {
/* 110 */       description = description.trim();
/* 111 */       if (description.length() == 0)
/* 112 */         description = null; 
/*     */     } 
/* 114 */     if (description != null) {
/* 115 */       conditions.put(RuleQueryCondition.DESCRIPTION_LIKE, description);
/*     */     }
/* 117 */     Integer enabled = ActionUtils.parseInt(queryForm.getEnabled());
/* 118 */     if (enabled != null) {
/* 119 */       conditions.put(RuleQueryCondition.ENABLED_EQ, enabled);
/*     */     }
/*     */ 
/*     */     
/* 123 */     return conditions;
/*     */   }
/*     */   
/*     */   protected ActionForward newObject(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 127 */     if (!isBack(request)) {
/* 128 */       Rule r = prepareAndCheckRule(type, request);
/* 129 */       BeanForm ruleForm = (BeanForm)getForm("/insert" + type.getPrefixUrl() + "Rule", request);
/* 130 */       ruleForm.populateToForm(r);
/*     */     } 
/* 132 */     prepareData(type, request);
/* 133 */     return mapping.findForward("pageRuleNew");
/*     */   }
/*     */   
/*     */   protected ActionForward insert(ActionMapping mapping, BeanForm ruleForm, HttpServletRequest request, RuleType type) throws Exception {
/* 137 */     Rule r = prepareAndCheckRule(type, request);
/* 138 */     ruleForm.populateToBean(r);
/* 139 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 140 */     request.setAttribute("X_OBJECT", rm.saveRule(r));
/* 141 */     request.setAttribute("X_ROWPAGE", "rule/row.jsp");
/* 142 */     return mapping.findForward("successRuleNew");
/*     */   }
/*     */   
/*     */   protected ActionForward edit(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 146 */     Rule r = getAndCheckRule(type, request);
/* 147 */     if (!isBack(request)) {
/* 148 */       BeanForm ruleForm = (BeanForm)getForm("/update" + type.getPrefixUrl() + "Rule", request);
/* 149 */       ruleForm.populateToForm(r);
/*     */     } 
/* 151 */     prepareData(type, request);
/* 152 */     request.setAttribute("X_RULEINUSE", Boolean.valueOf(ServiceLocator.getRuleManager(request).isRuleInUse(r.getId())));
/* 153 */     request.setAttribute("X_ENABLED", r.getEnabled());
/* 154 */     return mapping.findForward("pageRuleEdit");
/*     */   }
/*     */   
/*     */   protected ActionForward update(ActionMapping mapping, BeanForm ruleForm, HttpServletRequest request, RuleType type) throws Exception {
/* 158 */     Rule r = getAndCheckRule(type, request);
/* 159 */     Site s = r.getSite();
/* 160 */     ruleForm.populateToBean(r);
/*     */     
/* 162 */     r.setSite(s);
/*     */     
/* 164 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     
/* 166 */     if (rm.isRuleInUse(r.getId()))
/* 167 */       r.setEnabled(EnabledDisabled.ENABLED); 
/* 168 */     request.setAttribute("X_OBJECT", rm.saveRule(r));
/* 169 */     request.setAttribute("X_ROWPAGE", "rule/row.jsp");
/*     */     
/* 171 */     return mapping.findForward("successRuleEdit");
/*     */   }
/*     */   
/*     */   protected ActionForward delete(ActionMapping mapping, HttpServletRequest request, RuleType type) throws Exception {
/* 175 */     Rule r = getAndCheckRule(type, request);
/* 176 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/*     */     try {
/* 178 */       rm.removeRule(r.getId());
/* 179 */     } catch (Exception e) {
/* 180 */       throw new ActionException("rule.delete" + type.getPrefixUrl() + ".error");
/*     */     } 
/*     */     
/* 183 */     return mapping.findForward("successRuleDelete");
/*     */   }
/*     */   
/*     */   private Rule prepareAndCheckRule(RuleType type, HttpServletRequest request) throws Exception {
/* 187 */     Site s = getAndCheckSite("site_id", request);
/* 188 */     Rule r = new Rule();
/* 189 */     r.setSite(s);
/* 190 */     r.setType(type);
/* 191 */     return r;
/*     */   }
/*     */   
/*     */   private Rule getAndCheckRule(RuleType type, HttpServletRequest request) throws Exception {
/* 195 */     RuleManager rm = ServiceLocator.getRuleManager(request);
/* 196 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 197 */     Rule r = rm.getRule(id);
/* 198 */     if (r == null || !type.equals(r.getType()))
/* 199 */       throw new ActionException("rule." + type.getPrefixUrl() + ".notFound", id); 
/* 200 */     checkSite(r.getSite(), request);
/* 201 */     return r;
/*     */   }
/*     */   
/*     */   private void prepareData(RuleType type, HttpServletRequest request) {
/* 205 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 206 */     request.setAttribute("X_RULETYPE", type);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/business/rule/BaseRuleAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */