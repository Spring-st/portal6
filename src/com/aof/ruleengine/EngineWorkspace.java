/*    */ package com.aof.ruleengine;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EngineWorkspace
/*    */ {
/* 26 */   private Map flows = new HashMap<Object, Object>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EngineFlow getFlow(String name) {
/* 37 */     return (EngineFlow)this.flows.get(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EngineFlow removeFlow(String name) {
/* 48 */     EngineFlow ef = getFlow(name);
/* 49 */     if (ef == null) return null; 
/* 50 */     ef.lockForUpdate();
/*    */     try {
/* 52 */       this.flows.remove(name);
/*    */     } finally {
/* 54 */       ef.releaseLock();
/*    */     } 
/* 56 */     return ef;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EngineFlow createFlow(String name) {
/* 67 */     if (this.flows.containsKey(name))
/* 68 */       throw new RuntimeException("Flow '" + name + "' already existed"); 
/* 69 */     EngineFlow f = new EngineFlow();
/* 70 */     this.flows.put(name, f);
/* 71 */     return f;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/ruleengine/EngineWorkspace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */