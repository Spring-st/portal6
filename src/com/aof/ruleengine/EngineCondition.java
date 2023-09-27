/*    */ package com.aof.ruleengine;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EngineCondition
/*    */ {
/*    */   public static final int COMPARE_PASS_CONDITION_LESS_THAN = 1;
/*    */   public static final int COMPARE_PASS_CONDITION_LESS_EQUAL = 2;
/*    */   public static final int COMPARE_PASS_CONDITION_GREATER_EQUAL = 3;
/*    */   public static final int COMPARE_PASS_CONDITION_GREATER_THAN = 4;
/*    */   public static final int COMPARE_PASS_CONDITION_EQUAL = 5;
/*    */   public static final int COMPARE_PASS_CONDITION_NOT_EQUAL = 6;
/*    */   private String compareSource;
/*    */   private Object compareTarget;
/*    */   private int comparePassCondition;
/*    */   
/*    */   EngineCondition(String compareSource, int comparePassCondition, Object compareTarget) {
/* 57 */     this.compareSource = compareSource;
/*    */     
/* 59 */     switch (comparePassCondition) {
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/*    */       case 4:
/*    */       case 5:
/*    */       case 6:
/*    */         break;
/*    */       default:
/* 68 */         throw new RuntimeException("Compare pass condition code " + comparePassCondition + " is not supported");
/*    */     } 
/* 70 */     this.comparePassCondition = comparePassCondition;
/*    */     
/* 72 */     this.compareTarget = compareTarget;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getComparePassCondition() {
/* 79 */     return this.comparePassCondition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCompareSource() {
/* 86 */     return this.compareSource;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getCompareTarget() {
/* 93 */     return this.compareTarget;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/ruleengine/EngineCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */