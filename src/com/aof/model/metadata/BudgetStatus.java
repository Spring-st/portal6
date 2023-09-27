/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BudgetStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BudgetStatus OPEN = new BudgetStatus(1, "Open", MetadataType.BUDGET_STATUS);
/* 12 */   public static final BudgetStatus CLOSE = new BudgetStatus(2, "Close", MetadataType.BUDGET_STATUS);
/*    */ 
/*    */   
/*    */   public BudgetStatus() {}
/*    */   
/*    */   private BudgetStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BudgetStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */