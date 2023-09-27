/*    */ package com.aof.model.schedule;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class JitProductionPlan extends AbstractJitProductionPlan implements Serializable {
/*    */   public List<JitProductionPlan> jitProductionPlanList;
/*    */   public Integer sign;
/*    */   
/*    */   public JitProductionPlan() {}
/*    */   
/*    */   public JitProductionPlan(Integer id) {
/* 13 */     super(id);
/*    */   }
/*    */   
/*    */   public List<JitProductionPlan> getJitProductionPlanList() {
/* 17 */     return this.jitProductionPlanList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJitProductionPlanList(List<JitProductionPlan> jitProductionPlanList) {
/* 22 */     this.jitProductionPlanList = jitProductionPlanList;
/*    */   }
/*    */   
/*    */   public Integer getSign() {
/* 26 */     return this.sign;
/*    */   }
/*    */   
/*    */   public void setSign(Integer sign) {
/* 30 */     this.sign = sign;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/JitProductionPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */