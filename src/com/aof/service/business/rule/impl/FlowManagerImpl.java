/*     */ package com.aof.service.business.rule.impl;
/*     */ 
/*     */ import com.aof.dao.business.rule.FlowDAO;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.Approvable;
/*     */ import com.aof.model.business.BaseApproveRequest;
/*     */ import com.aof.model.business.BasePurchaser;
/*     */ import com.aof.model.business.Controllable;
/*     */ import com.aof.model.business.Notifiable;
/*     */ import com.aof.model.business.Purchasable;
/*     */ import com.aof.model.business.rule.Flow;
/*     */ import com.aof.model.business.rule.FlowRule;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.RuleConsequence;
/*     */ import com.aof.model.business.rule.query.FlowQueryCondition;
/*     */ import com.aof.model.business.rule.query.FlowQueryOrder;
/*     */ import com.aof.model.metadata.ApproveStatus;
/*     */ import com.aof.model.metadata.ConsequenceType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.RuleType;
/*     */ import com.aof.ruleengine.EngineFlow;
/*     */ import com.aof.ruleengine.EngineRule;
/*     */ import com.aof.ruleengine.EngineWorkspace;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.service.business.rule.ExecuteFlowEmptyResultException;
/*     */ import com.aof.service.business.rule.FlowManager;
/*     */ import com.aof.service.business.rule.NoAvailableFlowToExecuteException;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class FlowManagerImpl
/*     */   extends BaseManager
/*     */   implements FlowManager
/*     */ {
/*  62 */   private Log log = LogFactory.getLog(FlowManagerImpl.class);
/*     */ 
/*     */   
/*     */   private FlowDAO dao;
/*     */ 
/*     */   
/*     */   private SiteManager siteManager;
/*     */ 
/*     */   
/*     */   private UserManager userManager;
/*     */ 
/*     */   
/*     */   private RuleManager ruleManager;
/*     */ 
/*     */   
/*     */   private EmailManager emailManager;
/*     */   
/*     */   private EngineWorkspace workspace;
/*     */ 
/*     */   
/*     */   public void setFlowDAO(FlowDAO dao) {
/*  83 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteManager(SiteManager siteManager) {
/*  93 */     this.siteManager = siteManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserManager(UserManager userManager) {
/* 103 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRuleManager(RuleManager ruleManager) {
/* 113 */     this.ruleManager = ruleManager;
/*     */   }
/*     */   
/*     */   public void setEmailManager(EmailManager emailManager) {
/* 117 */     this.emailManager = emailManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorkspace(EngineWorkspace workspace) {
/* 127 */     this.workspace = workspace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow getFlow(Integer id) {
/* 136 */     return getFlow(id, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow getFlow(Integer id, boolean loadLazyProperties) {
/* 146 */     return this.dao.getFlow(id, loadLazyProperties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow[] saveFlow(Flow f) {
/* 155 */     return saveFlow(f, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow[] saveFlow(Flow f, Set flowRules) {
/* 165 */     Flow[] result = null;
/* 166 */     boolean needUpdateWorkspace = false;
/*     */     
/* 168 */     Flow f2 = getSiteEnabledFlow(f.getSite().getId(), f.getType());
/* 169 */     if (f.equals(f2)) {
/* 170 */       f2.setDescription(f.getDescription());
/* 171 */       f2.setEnabled(f.getEnabled());
/* 172 */       f = f2;
/* 173 */       needUpdateWorkspace = true;
/*     */     }
/* 175 */     else if (EnabledDisabled.ENABLED.equals(f.getEnabled())) {
/* 176 */       if (f2 != null) {
/* 177 */         result = new Flow[2];
/* 178 */         f2.setEnabled(EnabledDisabled.DISABLED);
/* 179 */         result[1] = this.dao.saveFlow(f2);
/*     */       } 
/* 181 */       needUpdateWorkspace = true;
/*     */     } 
/*     */ 
/*     */     
/* 185 */     if (result == null)
/* 186 */       result = new Flow[1]; 
/* 187 */     result[0] = this.dao.saveFlow(f);
/* 188 */     if (flowRules != null) {
/* 189 */       for (Iterator<FlowRule> iterator1 = this.dao.getRulesForFlow(f.getId()).iterator(); iterator1.hasNext();) {
/* 190 */         this.dao.removeFlowRule(iterator1.next());
/*     */       }
/* 192 */       for (Iterator<FlowRule> itor = flowRules.iterator(); itor.hasNext();) {
/* 193 */         this.dao.saveFlowRule(itor.next());
/*     */       }
/*     */     } 
/* 196 */     if (needUpdateWorkspace) {
/* 197 */       constructEngineFlow(f.getSite().getId(), f.getType());
/*     */     }
/* 199 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFlow(Integer id) {
/* 208 */     Flow f = getFlow(id, true);
/* 209 */     for (Iterator<FlowRule> itor = f.getRules().iterator(); itor.hasNext();) {
/* 210 */       this.dao.removeFlowRule(itor.next());
/*     */     }
/* 212 */     this.dao.removeFlow(id);
/* 213 */     if (EnabledDisabled.ENABLED.equals(f.getEnabled())) {
/* 214 */       Site s = f.getSite();
/* 215 */       RuleType rt = f.getType();
/* 216 */       this.workspace.removeFlow(s.getId() + rt.getPrefixUrl());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlowListCount(Map conditions) {
/* 226 */     return this.dao.getFlowListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFlowList(Map conditions, int pageNo, int pageSize, FlowQueryOrder order, boolean descend) {
/* 236 */     return this.dao.getFlowList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow getSiteEnabledFlow(Integer siteId, RuleType type) {
/* 246 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 247 */     conditions.put(FlowQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 248 */     conditions.put(FlowQueryCondition.SITE_ID_EQ, siteId);
/* 249 */     conditions.put(FlowQueryCondition.TYPE_EQ, type.getEnumCode());
/* 250 */     List<Flow> l = getFlowList(conditions, 0, 1, null, false);
/* 251 */     if (l.size() > 0)
/* 252 */       return l.get(0); 
/* 253 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int validateRules(Collection flowRules) {
/* 262 */     List<EngineRule> rules = new ArrayList();
/* 263 */     for (Iterator<FlowRule> itor = flowRules.iterator(); itor.hasNext(); ) {
/* 264 */       FlowRule fr = itor.next();
/* 265 */       rules.add(createEngineRuleForFlowRule(fr));
/*     */     } 
/* 267 */     return EngineFlow.validateRules(rules);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initWorkspace() {
/* 276 */     this.log.info("Initialize rule engine workspace");
/* 277 */     List ruleTypeList = PersistentEnum.getEnumList(RuleType.class);
/* 278 */     for (Iterator<Site> itor = this.siteManager.getAllEnabledSiteList().iterator(); itor.hasNext(); ) {
/* 279 */       Site s = itor.next();
/* 280 */       Integer siteId = s.getId();
/* 281 */       for (Iterator<RuleType> itorRuleType = ruleTypeList.iterator(); itorRuleType.hasNext(); ) {
/* 282 */         RuleType rt = itorRuleType.next();
/*     */ 
/*     */         
/* 285 */         constructEngineFlow(siteId, rt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void constructEngineFlow(Integer siteId, RuleType rt) {
/* 291 */     String flowName = siteId + rt.getPrefixUrl();
/* 292 */     Collection<FlowRule> flowRules = null;
/* 293 */     if (ConsequenceType.NOTIFIER.equals(rt.getConsequenceType())) {
/* 294 */       flowRules = new ArrayList();
/* 295 */       List ruleList = this.ruleManager.getEnabledRuleListForRuleType(siteId, rt);
/* 296 */       int i = 2;
/* 297 */       for (Iterator<Rule> itorRule = ruleList.iterator(); itorRule.hasNext(); i++) {
/* 298 */         Rule r = itorRule.next();
/* 299 */         FlowRule fr = new FlowRule();
/* 300 */         fr.setRule(r);
/* 301 */         if (itorRule.hasNext()) {
/* 302 */           fr.setNextSeqWhenFail(i);
/* 303 */           fr.setNextSeqWhenPass(i);
/*     */         } else {
/* 305 */           fr.setNextSeqWhenFail(0);
/* 306 */           fr.setNextSeqWhenPass(0);
/*     */         } 
/* 308 */         flowRules.add(fr);
/*     */       } 
/*     */     } else {
/* 311 */       Flow f = getSiteEnabledFlow(siteId, rt);
/* 312 */       if (f != null) {
/* 313 */         flowRules = this.dao.getRulesForFlow(f.getId());
/*     */       }
/*     */     } 
/*     */     
/* 317 */     if (flowRules == null || flowRules.isEmpty()) {
/* 318 */       this.workspace.removeFlow(flowName);
/*     */       return;
/*     */     } 
/* 321 */     EngineFlow ef = this.workspace.getFlow(flowName);
/* 322 */     if (ef == null) {
/* 323 */       ef = this.workspace.createFlow(flowName);
/*     */     }
/* 325 */     ef.lockForUpdate();
/*     */     try {
/* 327 */       ef.clearRules();
/* 328 */       for (Iterator<FlowRule> itor = flowRules.iterator(); itor.hasNext();) {
/* 329 */         ef.addRule(createEngineRuleForFlowRule(itor.next()));
/*     */       }
/*     */     } finally {
/* 332 */       ef.releaseLock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EngineRule createEngineRuleForFlowRule(FlowRule fr) {
/* 342 */     Integer ruleId = fr.getRule().getId();
/* 343 */     EngineRule er = new EngineRule(ruleId);
/* 344 */     this.ruleManager.updateEngineRuleForRule(ruleId, er);
/* 345 */     er.setNextSeqPass(fr.getNextSeqWhenPass() - 1);
/* 346 */     er.setNextSeqFail(fr.getNextSeqWhenFail() - 1);
/* 347 */     return er;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List executeApproveFlow(Approvable target) throws ExecuteFlowEmptyResultException, NoAvailableFlowToExecuteException {
/* 356 */     if (target == null) {
/* 357 */       throw new RuntimeException("Cannot execute flow on null target");
/*     */     }
/*     */     
/* 360 */     EngineFlow ef = this.workspace.getFlow(target.getApproveFlowName());
/* 361 */     if (ef == null) {
/* 362 */       throw new NoAvailableFlowToExecuteException();
/*     */     }
/* 364 */     List<RuleConsequence> l = ef.execute(target);
/* 365 */     int s = l.size();
/* 366 */     if (s == 0) {
/* 367 */       throw new ExecuteFlowEmptyResultException();
/*     */     }
/* 369 */     List<BaseApproveRequest> results = new ArrayList();
/* 370 */     Set<User> users = new HashSet();
/* 371 */     User requestor = target.getRequestor(); int i;
/* 372 */     for (i = s; i > 0; i--) {
/* 373 */       RuleConsequence rc = l.get(i - 1);
/* 374 */       User approver = rc.getUser();
/* 375 */       User superior = rc.getSuperior();
/* 376 */       if (superior != null && approver.equals(requestor)) {
/* 377 */         approver = superior;
/*     */       }
/* 379 */       if (!users.contains(approver)) {
/* 380 */         BaseApproveRequest bar = target.createNewApproveRequestObj();
/* 381 */         bar.setApprover(approver);
/* 382 */         bar.setCanModify(rc.getCanModify());
/* 383 */         results.add(0, bar);
/* 384 */         users.add(approver);
/*     */       } 
/*     */     } 
/* 387 */     s = results.size();
/* 388 */     for (i = 0; i < s; i++) {
/* 389 */       BaseApproveRequest bar = results.get(i);
/* 390 */       if (i == 0) {
/* 391 */         bar.setStatus(ApproveStatus.WAITING_FOR_APPROVE);
/* 392 */         bar.setYourTurnDate(new Date());
/*     */       } else {
/* 394 */         bar.setStatus(ApproveStatus.NOT_YOUR_TURN);
/*     */       } 
/* 396 */       bar.setSeq(i + 1);
/*     */     } 
/* 398 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List executePurchaseFlow(Purchasable target) throws ExecuteFlowEmptyResultException, NoAvailableFlowToExecuteException {
/* 407 */     if (target == null) {
/* 408 */       throw new RuntimeException("Cannot execute flow on null target");
/*     */     }
/*     */     
/* 411 */     EngineFlow ef = this.workspace.getFlow(target.getPurchaseFlowName());
/* 412 */     if (ef == null)
/* 413 */       throw new NoAvailableFlowToExecuteException(); 
/* 414 */     List l = ef.execute(target);
/* 415 */     int s = l.size();
/* 416 */     if (s == 0) {
/* 417 */       throw new ExecuteFlowEmptyResultException();
/*     */     }
/* 419 */     List<BasePurchaser> results = new ArrayList();
/* 420 */     Set<User> users = new HashSet();
/* 421 */     for (Iterator<RuleConsequence> itor = l.iterator(); itor.hasNext(); ) {
/* 422 */       RuleConsequence rc = itor.next();
/* 423 */       User purchaser = rc.getUser();
/* 424 */       if (!users.contains(purchaser)) {
/* 425 */         BasePurchaser bp = target.createNewPurchaserObj();
/* 426 */         bp.setPurchaser(purchaser);
/* 427 */         results.add(bp);
/* 428 */         users.add(purchaser);
/*     */       } 
/*     */     } 
/* 431 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean executeControlFlow(Controllable target) throws NoAvailableFlowToExecuteException {
/* 440 */     if (target == null) {
/* 441 */       throw new RuntimeException("Cannot execute flow on null target");
/*     */     }
/*     */     
/* 444 */     EngineFlow ef = this.workspace.getFlow(target.getControlFlowName());
/* 445 */     if (ef == null) {
/* 446 */       throw new NoAvailableFlowToExecuteException();
/*     */     }
/* 448 */     List<Boolean> l = ef.execute(target);
/* 449 */     int s = l.size();
/* 450 */     if (s == 0) {
/* 451 */       throw new RuntimeException("Execute control flow return empty result list, this is a coding error.");
/*     */     }
/* 453 */     return ((Boolean)l.get(s - 1)).booleanValue();
/*     */   }
/*     */   
/*     */   public void executeNotifyFlow(Notifiable target) {
/* 457 */     if (target == null) {
/* 458 */       throw new RuntimeException("Cannot execute flow on null target");
/*     */     }
/*     */     
/* 461 */     EngineFlow ef = this.workspace.getFlow(target.getNotifyFlowName());
/* 462 */     if (ef == null) {
/*     */       return;
/*     */     }
/* 465 */     Set<User> users = new HashSet();
/* 466 */     for (Iterator<RuleConsequence> itor = ef.execute(target).iterator(); itor.hasNext(); ) {
/* 467 */       RuleConsequence rc = itor.next();
/* 468 */       User notifier = rc.getUser();
/* 469 */       if (!users.contains(notifier)) {
/*     */ 
/*     */ 
/*     */         
/* 473 */         notifier = this.userManager.getUser(notifier.getId());
/* 474 */         if (EnabledDisabled.ENABLED.equals(notifier.getEnabled())) {
/* 475 */           Map<String, User> context = target.getNotifyEmailContext();
/* 476 */           context.put("user", notifier);
/* 477 */           this.emailManager.insertEmail(target.getLogSite(), notifier.getEmail(), target.getNotifyEmailTemplateName(), context);
/*     */         } 
/*     */         
/* 480 */         users.add(notifier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/rule/impl/FlowManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */