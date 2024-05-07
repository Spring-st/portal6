/*     */ package com.aof.ruleengine;
/*     */ 
/*     */ import com.aof.utils.RWLock;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EngineFlow
/*     */ {
/*     */   boolean canUse = false;
/*  46 */   ThreadLocal locking = new ThreadLocal();
/*  47 */   private List<EngineRule> rules = new ArrayList();
/*  48 */   private RWLock lock = new RWLock();
/*  49 */   private Map externalIdToRules = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearRules() {
/*  57 */     if (this.locking.get() == null)
/*  58 */       throw new RuntimeException("Lock flow before clear rules."); 
/*  59 */     this.rules.clear();
/*  60 */     this.externalIdToRules.clear();
/*  61 */     this.canUse = false;
/*  62 */     for (Iterator itor = this.rules.iterator(); itor.hasNext();) {
/*  63 */       ((EngineRule)itor.next()).flow = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRule(EngineRule r) {
/*  74 */     if (this.locking.get() == null)
/*  75 */       throw new RuntimeException("Lock flow before add rule."); 
/*  76 */     this.rules.add(r);
/*  77 */     Object externalId = r.getExternalId();
/*  78 */     Set<EngineRule> s = (Set)this.externalIdToRules.get(externalId);
/*  79 */     if (s == null) {
/*  80 */       s = new HashSet();
/*  81 */       this.externalIdToRules.put(externalId, s);
/*     */     } 
/*  83 */     s.add(r);
/*  84 */     this.canUse = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getRulesByExternalId(Object externalId) {
/*  95 */     return (Set)this.externalIdToRules.get(externalId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lockForUpdate() {
/* 102 */     if (this.locking.get() != null)
/*     */       return; 
/* 104 */     this.lock.getWriteLock();
/* 105 */     this.locking.set(Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseLock() {
/* 112 */     if (this.locking.get() == null)
/*     */       return; 
/* 114 */     this.locking.set(null);
/* 115 */     this.lock.releaseLock();
/*     */   }
/*     */   
/* 118 */   private final int EXECUTE_RESULT_INIT = 0;
/* 119 */   private final int EXECUTE_RESULT_PASS = 1;
/* 120 */   private final int EXECUTE_RESULT_FAIL = 2;
/* 121 */   private final int EXECUTE_RESULT_TEMP_FAIL = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List execute(Object target) {
/* 131 */     if (this.locking.get() != null)
/* 132 */       throw new RuntimeException("Cannot execute rules when flow is locked."); 
/* 133 */     this.lock.getReadLock();
/*     */     try {
/* 135 */       if (!this.canUse) {
/* 136 */         this.canUse = (validateRules(this.rules) == 0);
/*     */       }
/* 138 */       if (!this.canUse) {
/* 139 */         throw new RuntimeException("Validate rules failed, cannot execute flow");
/*     */       }
/* 141 */       if (target == null) {
/* 142 */         throw new RuntimeException("Cannot execute flow on null target");
/*     */       }
/* 144 */       List<Object> result = new ArrayList();
/* 145 */       EngineRule currentRule = (EngineRule) this.rules.get(0);
/* 146 */       while (currentRule != null) {
/* 147 */         int executeResult = 0;
/* 148 */         for (Iterator<EngineCondition> itor = currentRule.getConditions().iterator(); itor.hasNext(); ) {
/* 149 */           EngineCondition condition = itor.next();
/* 150 */           Object ruleResult = null;
/*     */           try {
/* 152 */             ruleResult = BeanHelper.getBeanPropertyValue(target, condition.getCompareSource());
/* 153 */           } catch (RuntimeException e) {
/* 154 */             Throwable t = e.getCause();
/* 155 */             if (t instanceof InvocationTargetException && ((InvocationTargetException)t).getTargetException() instanceof FailRuleWhenAllConditionThrowMeException) {
/* 156 */               if (executeResult == 0) {
/* 157 */                 executeResult = 3;
/*     */               }
/*     */               continue;
/*     */             } 
/* 161 */             throw e;
/*     */           } 
/* 163 */           int comparePassCondition = condition.getComparePassCondition();
/* 164 */           Object compareTarget = condition.getCompareTarget();
/* 165 */           if (ruleResult == null) {
/* 166 */             if (comparePassCondition == 5 && compareTarget == null) {
/* 167 */               executeResult = 1;
/*     */               continue;
/*     */             } 
/* 170 */             if (comparePassCondition == 6 && compareTarget != null) {
/* 171 */               executeResult = 1;
/*     */               
/*     */               continue;
/*     */             } 
/* 175 */           } else if (ruleResult instanceof Comparable) {
/* 176 */             int compareResult = ((Comparable<Object>)ruleResult).compareTo(compareTarget);
/* 177 */             switch (comparePassCondition) {
/*     */               case 5:
/* 179 */                 if (compareResult == 0) {
/* 180 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 6:
/* 185 */                 if (compareResult != 0) {
/* 186 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 3:
/* 191 */                 if (compareResult >= 0) {
/* 192 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 4:
/* 197 */                 if (compareResult > 0) {
/* 198 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 2:
/* 203 */                 if (compareResult <= 0) {
/* 204 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 1:
/* 209 */                 if (compareResult < 0) {
/* 210 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */             } 
/* 215 */           } else if (ruleResult instanceof Collection) {
/* 216 */             boolean found; Iterator itor2; switch (comparePassCondition) {
/*     */               case 5:
/* 218 */                 found = false;
/* 219 */                 for (itor2 = ((Collection)ruleResult).iterator(); itor2.hasNext(); ) {
/* 220 */                   Object element = itor2.next();
/* 221 */                   if ((element == null) ? (
/* 222 */                     compareTarget != null) : 
/*     */                     
/* 224 */                     !element.equals(compareTarget))
/*     */                     continue; 
/* 226 */                   found = true;
/*     */                   break;
/*     */                 } 
/* 229 */                 if (found) {
/* 230 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */           } else {
/* 236 */             switch (comparePassCondition) {
/*     */               case 5:
/* 238 */                 if (ruleResult.equals(compareTarget)) {
/* 239 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               case 6:
/* 244 */                 if (!ruleResult.equals(compareTarget)) {
/* 245 */                   executeResult = 1;
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */           
/*     */           } 
/* 252 */           executeResult = 2;
/*     */           break;
/*     */         } 
/* 255 */         Object consequences = null;
/* 256 */         if (executeResult == 1 || executeResult == 0) {
/* 257 */           consequences = currentRule.getConsequencesPass();
/* 258 */           currentRule = currentRule.nextRulePass;
/*     */         } else {
/* 260 */           consequences = currentRule.getConsequencesFail();
/* 261 */           currentRule = currentRule.nextRuleFail;
/*     */         } 
/* 263 */         if (consequences != null) {
/* 264 */           if (consequences instanceof Collection) {
/* 265 */             result.addAll((Collection)consequences); continue;
/*     */           } 
/* 267 */           result.add(consequences);
/*     */         } 
/*     */       } 
/*     */       
/* 271 */       return result;
/*     */     } finally {
/* 273 */       this.lock.releaseLock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int validateRules(List<EngineRule> rules) {
/* 285 */     int ruleCount = rules.size();
/* 286 */     if (ruleCount == 0) {
/* 287 */       return -3;
/*     */     }
/* 289 */     Stack<EngineRule> stack = new Stack();
/* 290 */     EngineRule[] ruleArray = new EngineRule[ruleCount];
/* 291 */     ruleArray[0] = rules.get(0);
/* 292 */     stack.push(ruleArray[0]);
/*     */     
/* 294 */     while (!stack.isEmpty()) {
/* 295 */       EngineRule r = stack.pop();
/* 296 */       int nextSeq = r.getNextSeqPass();
/* 297 */       if (nextSeq > ruleCount - 1)
/* 298 */         return -2; 
/* 299 */       if (nextSeq <= 0) {
/* 300 */         r.nextRulePass = null;
/*     */       } else {
/* 302 */         EngineRule engineRule = ruleArray[nextSeq];
/* 303 */         if (engineRule == null) {
/* 304 */           engineRule = rules.get(nextSeq);
/* 305 */           ruleArray[nextSeq] = engineRule;
/* 306 */           engineRule.inDegree = 1;
/* 307 */           stack.push(engineRule);
/*     */         } else {
/* 309 */           engineRule.inDegree++;
/*     */         } 
/* 311 */         r.nextRulePass = engineRule;
/*     */       } 
/*     */       
/* 314 */       nextSeq = r.getNextSeqFail();
/* 315 */       if (nextSeq > ruleCount - 1)
/* 316 */         return -2; 
/* 317 */       if (nextSeq <= 0) {
/* 318 */         r.nextRuleFail = null; continue;
/*     */       } 
/* 320 */       EngineRule nr = ruleArray[nextSeq];
/* 321 */       if (nr == null) {
/* 322 */         nr = rules.get(nextSeq);
/* 323 */         ruleArray[nextSeq] = nr;
/* 324 */         nr.inDegree = 1;
/* 325 */         stack.push(nr);
/*     */       } else {
/* 327 */         nr.inDegree++;
/*     */       } 
/* 329 */       r.nextRuleFail = nr;
/*     */     } 
/*     */ 
/*     */     
/* 333 */     stack.push(ruleArray[0]);
/* 334 */     while (!stack.isEmpty()) {
/* 335 */       EngineRule r = stack.pop();
/* 336 */       EngineRule nr = r.nextRulePass;
/* 337 */       if (nr != null) {
/* 338 */         nr.inDegree--;
/* 339 */         if (nr.inDegree == 0)
/* 340 */           stack.push(nr); 
/*     */       } 
/* 342 */       nr = r.nextRuleFail;
/* 343 */       if (nr != null) {
/* 344 */         nr.inDegree--;
/* 345 */         if (nr.inDegree == 0) {
/* 346 */           stack.push(nr);
/*     */         }
/*     */       } 
/*     */     } 
/* 350 */     for (int i = 1; i < ruleCount; i++) {
/* 351 */       EngineRule r = ruleArray[i];
/* 352 */       if (r != null && 
/* 353 */         r.inDegree > 0) {
/* 354 */         return -1;
/*     */       }
/*     */     } 
/* 357 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/ruleengine/EngineFlow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */