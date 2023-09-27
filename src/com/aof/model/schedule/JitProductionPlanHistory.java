/*    */ package com.aof.model.schedule;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class JitProductionPlanHistory
/*    */   extends AbstractJitProductionPlanHistory
/*    */   implements Serializable {
/*    */   public List<JitProductionPlanHistory> JitProductionPlanHistoryList;
/*    */   
/*    */   public JitProductionPlanHistory(Integer id) {
/* 12 */     super(id);
/*    */   } public Integer sign;
/*    */   public JitProductionPlanHistory() {}
/*    */   public List<JitProductionPlanHistory> getJitProductionPlanHistoryList() {
/* 16 */     return this.JitProductionPlanHistoryList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJitProductionPlanHistoryList(List<JitProductionPlanHistory> JitProductionPlanHistoryList) {
/* 21 */     this.JitProductionPlanHistoryList = JitProductionPlanHistoryList;
/*    */   }
/*    */   
/*    */   public Integer getSign() {
/* 25 */     return this.sign;
/*    */   }
/*    */   
/*    */   public void setSign(Integer sign) {
/* 29 */     this.sign = sign;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/JitProductionPlanHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */